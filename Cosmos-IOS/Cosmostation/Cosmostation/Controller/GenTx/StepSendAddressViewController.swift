//
//  StepSendAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepSendAddressViewController: BaseViewController, QrScannerDelegate {
    

    @IBOutlet weak var mTargetAddressTextField: AddressInputTextField!
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var CancelBtn: UIButton!
    @IBOutlet weak var NextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        mTargetAddressTextField.attributedPlaceholder = NSAttributedString(string: NSLocalizedString("recipient_address", comment: ""), attributes: [NSAttributedString.Key.foregroundColor: UIColor.gray])
    }
    
    @IBAction func onClickQrCode(_ sender: Any) {
//        self.onShowToast(NSLocalizedString("prepare", comment: ""))
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(qrScanVC, animated: true)
        
    }
    
    @IBAction func onClickPaste(_ sender: Any) {
        if let myString = UIPasteboard.general.string {
            self.mTargetAddressTextField.text = myString
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
    
    @IBAction func onClickNext(_ sender: Any) {
        let userInput = mTargetAddressTextField.text?.trimmingCharacters(in: .whitespaces)
        if (pageHolderVC.mAccount?.account_address == userInput) {
            self.onShowToast(NSLocalizedString("error_self_send", comment: ""))
            return;
            
        } else if(WKey.isValidateAddress(userInput!)) {
            self.CancelBtn.isUserInteractionEnabled = false
            self.NextBtn.isUserInteractionEnabled = false
            pageHolderVC.mToSendRecipientAddress = userInput
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
            return;
        }
    }
    
    override func enableUserInteraction() {
        self.CancelBtn.isUserInteractionEnabled = true
        self.NextBtn.isUserInteractionEnabled = true
    }
    
    
    
    func scannedAddress(result: String) {
        print("result")
        mTargetAddressTextField.text = result
    }
}
