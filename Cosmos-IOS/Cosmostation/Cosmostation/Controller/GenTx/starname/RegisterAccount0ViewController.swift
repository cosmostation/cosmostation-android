//
//  RegisterAccount0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class RegisterAccount0ViewController: BaseViewController, SBCardPopupDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var userInput: AddressInputTextField!
    @IBOutlet weak var domainCard: CardView!
    @IBOutlet weak var domainLabel: UILabel!
    @IBOutlet weak var valideMsg: UILabel!
    @IBOutlet weak var starnameFeeAmount: UILabel!
    @IBOutlet weak var starnameFeeDenom: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var selectedDomain = "iov"

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.userInput.layer.borderWidth = 0
        self.userInput.placeholder = "Your Starname"
        
        userInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        self.domainCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickDomains (_:))))
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == userInput) {
            let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
            if (userInputData!.count > 0 && !WUtils.isStarnameValidAccount(userInputData!)) {
                valideMsg.textColor = UIColor.init(hexString: "f31963")
            } else {
                valideMsg.textColor = .white
            }
            onUpdateStarnameFee()
        }
    }
    
    func onUpdateStarnameFee() {
        let starnameFee = WUtils.getStarNameRegisterAccountFee("open")
        starnameFeeAmount.attributedText = WUtils.displayAmount2(starnameFee.stringValue, starnameFeeAmount.font, 6, 6)
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        let userInputData = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        if (!WUtils.isStarnameValidAccount(userInputData!)) {
            self.onShowToast(NSLocalizedString("error_invalid_account_format", comment: ""))
            return
        }
        
        let userAvailable = BaseData.instance.getAvailableAmount_gRPC(IOV_MAIN_DENOM)
        let txFee = WUtils.getEstimateGasFeeAmount(chainType!, IOV_MSG_TYPE_REGISTER_ACCOUNT, 0)
        let starnameFee = WUtils.getStarNameRegisterAccountFee("open")
//        print("userAvailable ", userAvailable)
//        print("txFee ", txFee)
//        print("starnameFee ", starnameFee)
        
        if (userAvailable.compare(starnameFee.adding(txFee)).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
            return
        }
        
        self.view.endEditing(true)
        self.onFetchgRPCResolve(userInputData!, selectedDomain)
        
    }
    
    func onGoNextPage() {
        pageHolderVC.mStarnameDomain = selectedDomain
        pageHolderVC.mStarnameAccount = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    @objc func onClickDomains (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_STARNAME_DOMAIN
        popupVC.starnameDomains = BaseData.instance.mParam!.params!.starname_domains
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    func SBCardPopupResponse(type: Int, result: Int) {
        if (type == SELECT_POPUP_STARNAME_DOMAIN) {
            selectedDomain = BaseData.instance.mParam!.params!.starname_domains[result]
            domainLabel.text = selectedDomain
        }
    }
    
    func onFetchgRPCResolve(_ account: String, _ doamin: String) {
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(self.chainType!, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let req = Starnamed_X_Starname_V1beta1_QueryStarnameRequest.with { $0.starname = account + "*" + doamin }
                let response = try Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).starname(req, callOptions:BaseNetWork.getCallOptions()).response.wait()
                DispatchQueue.main.async(execute: {
                    self.onShowToast(NSLocalizedString("error_already_registered_account", comment: ""))
                    return
                });

            } catch {
                print("onFetchgRPCResolve failed: \(error)")
                DispatchQueue.main.async(execute: {
                    self.onGoNextPage()
                });
            }
        }
    }
}
