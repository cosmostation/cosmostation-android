//
//  StepSendAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO

class StepSendAddressViewController: BaseViewController, QrScannerDelegate {
    
    @IBOutlet weak var mTargetAddressTextField: AddressInputTextField!
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var startNameLayer: UIView!
    @IBOutlet weak var CancelBtn: UIButton!
    @IBOutlet weak var NextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        mTargetAddressTextField.attributedPlaceholder = NSAttributedString(string: NSLocalizedString("recipient_address", comment: ""), attributes: [NSAttributedString.Key.foregroundColor: UIColor.gray])
    }
    
    @IBAction func onClickQrCode(_ sender: Any) {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        self.navigationController?.pushViewController(qrScanVC, animated: false)
        
    }
    
    @IBAction func onClickPaste(_ sender: Any) {
        if let myString = UIPasteboard.general.string {
            self.mTargetAddressTextField.text = myString.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))
        }
    }
    
    @IBAction func onClickRecent(_ sender: Any) {
        self.onShowToast(NSLocalizedString("prepare", comment: ""))
        
    }
    
    @IBAction func onClickCancel(_ sender: Any) {
        self.CancelBtn.isUserInteractionEnabled = false
        self.NextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    override func keyboardWillHide(notification: NSNotification) {
        super.keyboardWillHide(notification: notification)
        startNameLayer.isHidden = false
    }
    
    override func keyboardWillShow(notification: NSNotification) {
        super.keyboardWillShow(notification: notification)
        startNameLayer.isHidden = true
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        let userInput = mTargetAddressTextField.text?.trimmingCharacters(in: .whitespaces)
        if (WUtils.isStarnameValidStarName(userInput!.lowercased())) {
            self.onCheckNameservice(userInput!.lowercased())
            return;
        }
        
        if (pageHolderVC.mAccount?.account_address == userInput) {
            self.onShowToast(NSLocalizedString("error_self_send", comment: ""))
            return;
        }
        
        if (!WUtils.isValidChainAddress(pageHolderVC.chainType, userInput)) {
            self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
            return;
        }
        
        self.CancelBtn.isUserInteractionEnabled = false
        self.NextBtn.isUserInteractionEnabled = false
        pageHolderVC.mToSendRecipientAddress = userInput
        if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            pageHolderVC.mToSendRecipientAddress = WKey.convertAddressEthToOkex(userInput!)
        }
        pageHolderVC.onNextPage()
        
    }
    
    override func enableUserInteraction() {
        self.CancelBtn.isUserInteractionEnabled = true
        self.NextBtn.isUserInteractionEnabled = true
    }
    
    func scannedAddress(result: String) {
        mTargetAddressTextField.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
    
    func onCheckNameservice(_ userInput: String) {
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(ChainType.IOV_MAIN, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let req = Starnamed_X_Starname_V1beta1_QueryStarnameRequest.with { $0.starname = userInput }
                let response = try Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).starname(req, callOptions:BaseNetWork.getCallOptions()).response.wait()
                try channel.close().wait()
                DispatchQueue.main.async(execute: {
                    guard let matchedAddress = WUtils.checkStarnameWithResource(self.pageHolderVC.chainType!, response) else {
                        self.onShowToast(NSLocalizedString("error_no_mattched_starname", comment: ""))
                        return
                    }
                    if (self.pageHolderVC.mAccount?.account_address == matchedAddress) {
                        self.onShowToast(NSLocalizedString("error_starname_self_send", comment: ""))
                        return;
                    }
                    self.onShowMatchedStarName(userInput, matchedAddress)
                });
                
            } catch {
                print("onFetchgRPCResolve failed: \(error)")
                DispatchQueue.main.async(execute: {
                    self.onShowToast(NSLocalizedString("error_invalide_starname", comment: ""))
                    return
                });
            }
        }
    }
    
    func onShowMatchedStarName(_ starname: String, _ matchedAddress: String) {
        let msg = String(format: NSLocalizedString("str_starname_confirm_msg", comment: ""), starname, matchedAddress)
        let alertController = UIAlertController(title: NSLocalizedString("str_starname_confirm_title", comment: ""), message: msg, preferredStyle: .alert)
        let settingsAction = UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default) { (_) -> Void in
            self.CancelBtn.isUserInteractionEnabled = false
            self.NextBtn.isUserInteractionEnabled = false
            self.pageHolderVC.mToSendRecipientAddress = matchedAddress
            self.pageHolderVC.onNextPage()
        }
        let cancelAction = UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: nil)
        alertController.addAction(cancelAction)
        alertController.addAction(settingsAction)
        DispatchQueue.main.async {
            self.present(alertController, animated: true, completion: nil)
        }
    }
}
