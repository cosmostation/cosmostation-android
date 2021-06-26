//
//  RegisterDomain0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

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
        self.userInput.placeholder = "Your Starname"
        
        userInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == userInput) {
            let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
            if (userInputData!.count > 0 && !WUtils.isStarnameValidDomain(userInputData!)) {
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
        let starnameFee = WUtils.getStarNameRegisterDomainFee(userInputData!, domainType)
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
        if (!WUtils.isStarnameValidDomain(userInputData!)) {
            self.onShowToast(NSLocalizedString("error_invalid_domain_format", comment: ""))
            return
        }
        
        let userAvailable = BaseData.instance.getAvailableAmount_gRPC(IOV_MAIN_DENOM)
        let txFee = WUtils.getEstimateGasFeeAmount(chainType!, IOV_MSG_TYPE_REGISTER_DOMAIN, 0)
        let starnameFee = WUtils.getStarNameRegisterDomainFee(userInputData!, domainType)
//        print("userAvailable ", userAvailable)
//        print("txFee ", txFee)
//        print("starnameFee ", starnameFee)
        
        if (userAvailable.compare(starnameFee.adding(txFee)).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
            return
        }
        
        self.view.endEditing(true)
        self.onFetchgRPCDomainInfo(userInputData!)
    }
    
    func onGoNextPage() {
        pageHolderVC.mStarnameDomain = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        pageHolderVC.mStarnameDomainType = typeSwitch.isOn ? "open" : "closed"
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    //check for already exist domain
    func onFetchgRPCDomainInfo(_ domain: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Starnamed_X_Starname_V1beta1_QueryDomainRequest.with {
                    $0.name = domain
                }
                let response = try Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).domain(req, callOptions:BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCDomainInfo ", response)
                DispatchQueue.main.async(execute: {
                    self.onShowToast(NSLocalizedString("error_already_registered_domain", comment: ""))
                    return
                });
                
            } catch {
                print("onFetchgRPCDomainInfo failed: \(error)")
                DispatchQueue.main.async(execute: {
                    self.onGoNextPage()
                });
            }
        }
    }
}
