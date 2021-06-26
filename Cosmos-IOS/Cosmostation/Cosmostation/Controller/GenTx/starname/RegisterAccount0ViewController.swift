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
            if (userInputData!.count > 0 && !WUtils.isStarnameValidAccount(userInputData!)) {
                valideMsg.textColor = UIColor.init(hexString: "f31963")
            } else {
                valideMsg.textColor = .white
            }
            onUpdateStarnameFee()
        }
    }
    
    func onUpdateStarnameFee() {
//        let starnameFee = BaseData.instance.mStarNameFee!.getAccountFee("open")
        let starnameFee = WUtils.getStarNameAccountFee("open")
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
        let starnameFee = WUtils.getStarNameAccountFee("open")
//        print("userAvailable ", userAvailable)
//        print("txFee ", txFee)
//        print("starnameFee ", starnameFee)
        
        if (userAvailable.compare(starnameFee.adding(txFee)).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
            return
        }
        
        self.view.endEditing(true)
        self.onFetchgRPCResolve(userInputData!, "iov")
        
    }
    
    func onGoNextPage() {
        pageHolderVC.mStarnameDomain = "iov"
        pageHolderVC.mStarnameAccount = self.userInput.text?.trimmingCharacters(in: .whitespaces)
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
//    func onFetchResolve(_ account: String, _ doamin: String) {
//        self.showWaittingAlert()
//        let request = Alamofire.request(BaseNetWork.resolveStarnameUrl(chainType), method: .post, parameters: ["starname" : account + "*" + doamin], encoding: JSONEncoding.default, headers: [:])
//        request.responseJSON { (response) in
//            switch response.result {
//            case .success(let res):
//                self.hideWaittingAlert()
//                guard let info = res as? [String : Any] else {
//                    self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
//                    return
//                }
//                if (info["error"] == nil) {
//                    self.onShowToast(NSLocalizedString("error_already_registered_account", comment: ""))
//                    return
//                } else {
//                    self.onGoNextPage()
//                }
//
//            case .failure(let error):
//                if (SHOW_LOG) { print("onFetchDomainInfo ", error) }
//                self.hideWaittingAlert()
//                self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
//            }
//        }
//    }
    
    func onFetchgRPCResolve(_ account: String, _ doamin: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Starnamed_X_Starname_V1beta1_QueryStarnameRequest.with {
                    $0.starname = account + "*" + doamin
                }
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
