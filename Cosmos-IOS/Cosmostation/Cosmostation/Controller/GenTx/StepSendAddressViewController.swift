//
//  StepSendAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

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
        if (WUtils.isValidStarName(userInput!.lowercased())) {
            self.onCheckNameservice(userInput!.lowercased())
            return;
        }
        
        if (pageHolderVC.mAccount?.account_address == userInput) {
            self.onShowToast(NSLocalizedString("error_self_send", comment: ""))
            return;
        }
        
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            if (!userInput!.starts(with: "cosmos1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            if (!userInput!.starts(with: "iaa1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN) {
            if (!userInput!.starts(with: "bnb1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (!userInput!.starts(with: "kava1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            if (!userInput!.starts(with: "star1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (!userInput!.starts(with: "tbnb1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            if (!userInput!.starts(with: "band1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            if (!userInput!.starts(with: "secret1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            if (!userInput!.starts(with: "0x") || !WKey.isValidEthAddress(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            if (!userInput!.starts(with: "certik1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            if (!userInput!.starts(with: "akash1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.PERSIS_MAIN) {
            if (!userInput!.starts(with: "persistence1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SENTINEL_MAIN) {
            if (!userInput!.starts(with: "sent1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.FETCH_MAIN) {
            if (!userInput!.starts(with: "fetch1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CRYPTO_MAIN) {
            if (!userInput!.starts(with: "cro1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SIF_MAIN) {
            if (!userInput!.starts(with: "sif1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KI_MAIN) {
            if (!userInput!.starts(with: "ki1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.RIZON_TEST) {
            if (!userInput!.starts(with: "rizon1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.MEDI_TEST) {
            if (!userInput!.starts(with: "panacea1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.ALTHEA_TEST) {
            if (!userInput!.starts(with: "althea1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        }
        
        else {
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
        let request = Alamofire.request(BaseNetWork.resolveStarnameUrl(pageHolderVC.chainType), method: .post, parameters: ["starname" : userInput], encoding: JSONEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any], info["error"] == nil else {
                    self.onShowToast(NSLocalizedString("error_invalide_starname", comment: ""))
                    return
                }
                let nameResolve = IovStarNameResolve.init(info)
                guard let matchedAddress = nameResolve.getAddressWithChain(self.pageHolderVC.chainType!) else {
                    self.onShowToast(NSLocalizedString("error_no_mattched_starname", comment: ""))
                    return
                }
                if (self.pageHolderVC.mAccount?.account_address == matchedAddress) {
                    self.onShowToast(NSLocalizedString("error_starname_self_send", comment: ""))
                    return;
                }
                self.onShowMatchedStarName(userInput, matchedAddress)
                
                
            case .failure(let error):
                print("failure ", error)
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
