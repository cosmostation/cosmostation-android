//
//  RegisterAccount0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class RegisterAccount0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
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
        self.userInput.placeholder = "Your Account"
        
        userInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == userInput) {
            let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
            if (userInputData!.count > 0 && !WUtils.isValidAccount(userInputData!)) {
                valideMsg.textColor = UIColor.init(hexString: "f31963")
            } else {
                valideMsg.textColor = .white
            }
            onUpdateStarnameFee()
        }
    }
    
    func onUpdateStarnameFee() {
        let starnameFee = BaseData.instance.mStarNameFee!.getAccountFee("open")
        starnameFeeAmount.attributedText = WUtils.displayAmount2(starnameFee.stringValue, starnameFeeAmount.font, 6, 6)
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        let starnameFee = BaseData.instance.mStarNameFee!.getAccountFee("open")
        if (!WUtils.isValidAccount(userInputData!)) {
            self.onShowToast(NSLocalizedString("error_invalid_account_format", comment: ""))
            return
        }
        
        var myAvailable = NSDecimalNumber.zero
        if (chainType == ChainType.IOV_MAIN) {
            myAvailable = WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).subtracting(NSDecimalNumber.init(string: "300000"))
        } else if (chainType == ChainType.IOV_TEST) {
            myAvailable = WUtils.getTokenAmount(balances, IOV_TEST_DENOM).subtracting(NSDecimalNumber.init(string: "300000"))
        }
        if (myAvailable.compare(starnameFee).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
            return
        }
        self.view.endEditing(true)
        self.onFetchResolve(userInputData!, "iov")
        
    }
    
    func onGoNextPage() {
        pageHolderVC.mStarnameDomain = "iov"
        pageHolderVC.mStarnameAccount = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    func onFetchResolve(_ account: String, _ doamin: String) {
        self.showWaittingAlert()
        var url: String?
        if (chainType == ChainType.IOV_MAIN) {
            url = IOV_CHECK_WITH_STARNAME;
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_CHECK_WITH_STARNAME;
        }
        let request = Alamofire.request(url!, method: .post, parameters: ["starname" : account + "*" + doamin], encoding: JSONEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.hideWaittingAlert()
                guard let info = res as? [String : Any] else {
                    self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
                    return
                }
                if (info["error"] == nil) {
                    self.onShowToast(NSLocalizedString("error_already_registered_account", comment: ""))
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
