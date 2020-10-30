//
//  RegisterDomain0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class RegisterDomain0ViewController: BaseViewController {

    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var typeSwitch: UISwitch!
    @IBOutlet weak var typeTitle: UILabel!
    @IBOutlet weak var typeMsg: UILabel!
    @IBOutlet weak var userInput: AddressInputTextField!
    @IBOutlet weak var valideMsg: UILabel!
    @IBOutlet weak var starnameFeeAmount: UILabel!
    @IBOutlet weak var starnameFeeDenom: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.userInput.layer.borderWidth = 0
        self.userInput.placeholder = "Your Domain"
        
        userInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == userInput) {
            let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
            if (userInputData!.count > 0 && !WUtils.isValidDomain(userInputData!)) {
                valideMsg.textColor = UIColor.init(hexString: "f31963")
            } else {
                valideMsg.textColor = .white
            }
            onUpdateStarnameFee()
        }
    }
    
    func onUpdateStarnameFee() {
        let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        let domainType = typeSwitch.isOn ? "open" : "closed"
        let starnameFee = BaseData.instance.mStarNameFee!.getDomainFee(userInputData!, domainType)
        starnameFeeAmount.attributedText = WUtils.displayAmount2(starnameFee.stringValue, starnameFeeAmount.font, 6, 6)
    }
    
    @IBAction func onToggleSwitch(_ sender: UISwitch) {
        if (sender.isOn) {
            typeMsg.text = NSLocalizedString("str_description_open_domain", comment: "")
            typeTitle.text = "OPEN"
            typeTitle.textColor = COLOR_IOV
            
        } else {
            typeMsg.text = NSLocalizedString("str_description_closed_domain", comment: "")
            typeTitle.text = "CLOSED"
            typeTitle.textColor = .white
        }
        onUpdateStarnameFee()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickNext(_ sender: UIButton) {
        let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        let domainType = typeSwitch.isOn ? "open" : "closed"
        let starnameFee = BaseData.instance.mStarNameFee!.getDomainFee(userInputData!, domainType)
        if (!WUtils.isValidDomain(userInputData!)) {
            self.onShowToast(NSLocalizedString("error_invalid_domain_format", comment: ""))
            return
        }
        
        if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(starnameFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(starnameFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else {
            self.onShowToast(NSLocalizedString("error_disable", comment: ""))
            return
        }
        self.view.endEditing(true)
        self.onFetchDomainInfo(userInputData!)
        
    }
    
    func onGoNextPage() {
        pageHolderVC.mStarnameDomain = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        pageHolderVC.mStarnameDomainType = typeSwitch.isOn ? "open" : "closed"
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    func onFetchDomainInfo(_ domain: String) {
        self.showWaittingAlert()
        var url: String?
        if (chainType == ChainType.IOV_MAIN) {
            url = IOV_STARNAME_DOMAIN_INFO;
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_STARNAME_DOMAIN_INFO;
        }
        let request = Alamofire.request(url!, method: .post, parameters: ["name" : domain], encoding: JSONEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.hideWaittingAlert()
                guard let info = res as? [String : Any] else {
                    self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
                    return
                }
                if (info["error"] == nil) {
                    self.onShowToast(NSLocalizedString("error_already_registered_domain", comment: ""))
                    return
                } else {
                    self.onGoNextPage()
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchDomainInfo ", error) }
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
            }
        }
    }
}
