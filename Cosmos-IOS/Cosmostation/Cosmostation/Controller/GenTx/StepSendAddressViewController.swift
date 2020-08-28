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
    
    @IBAction func onClickNext(_ sender: Any) {
        let userInput = mTargetAddressTextField.text?.trimmingCharacters(in: .whitespaces)
        if (pageHolderVC.mAccount?.account_address == userInput) {
            self.onShowToast(NSLocalizedString("error_self_send", comment: ""))
            return;
            
        }
            
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
            if (!userInput!.starts(with: "cosmos") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            if (!userInput!.starts(with: "iaa") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN) {
            if (!userInput!.starts(with: "bnb") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (!userInput!.starts(with: "kava") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            if (!userInput!.starts(with: "iov") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (!userInput!.starts(with: "tbnb") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            if (!userInput!.starts(with: "band") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            if (!userInput!.starts(with: "star") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.OK_TEST) {
            if (!userInput!.starts(with: "okchain") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
            return;
        }
        
        self.CancelBtn.isUserInteractionEnabled = false
        self.NextBtn.isUserInteractionEnabled = false
        pageHolderVC.mToSendRecipientAddress = userInput
        pageHolderVC.onNextPage()
        
    }
    
    override func enableUserInteraction() {
        self.CancelBtn.isUserInteractionEnabled = true
        self.NextBtn.isUserInteractionEnabled = true
    }
    
    
    
    func scannedAddress(result: String) {
        mTargetAddressTextField.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
}
