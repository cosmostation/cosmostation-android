//
//  ReplaceEditAddressViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ReplaceEditAddressViewController: BaseViewController, QrScannerDelegate, SBCardPopupDelegate {
    
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var chainNameLabel: UILabel!
    @IBOutlet weak var addressInput: AddressInputTextField!
    @IBOutlet weak var btnWallet: UIButton!
    @IBOutlet weak var btnQrScan: UIButton!
    @IBOutlet weak var btnPaste: UIButton!
    
    var chainNameResource: String?
    var addressResource: String?
    var resultDelegate: ReplaceEditAddressDelegate?
    var selectableAccounts = Array<Account>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.addressInput.layer.borderWidth = 1
        self.addressInput.layer.borderColor = UIColor.init(hexString: "ebecee").cgColor
        self.addressInput.setLeftPaddingPoints(8)
        
        let walletImg = self.btnWallet.imageView?.image!.withRenderingMode(.alwaysTemplate)
        self.btnWallet.setImage(walletImg, for: .normal)
        self.btnWallet.tintColor = UIColor.init(hexString: "222222")
        
        let qrImg = self.btnQrScan.imageView?.image!.withRenderingMode(.alwaysTemplate)
        self.btnQrScan.setImage(qrImg, for: .normal)
        self.btnQrScan.tintColor = UIColor.init(hexString: "222222")
        
        let pasteImg = self.btnPaste.imageView?.image!.withRenderingMode(.alwaysTemplate)
        self.btnPaste.setImage(pasteImg, for: .normal)
        self.btnPaste.tintColor = UIColor.init(hexString: "222222")
        
        chainImg.af_setImage(withURL: getStarNameChainImgUrl(chainNameResource))
        chainNameLabel.text = getStarNameChainName(chainNameResource)
        addressInput.text = addressResource
        
    }
    
    @IBAction func onClickWallet(_ sender: UIButton) {
        if let asset = getStarnameAssets().filter({ $0.uri == chainNameResource}).first, let chainType = asset.chainType {
            selectableAccounts = BaseData.instance.selectAllAccountsByChain(chainType)
            if (selectableAccounts.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_wallet_this_chain", comment: ""))
                return
                
            } else {
                let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
                popupVC.type = SELECT_POPUP_STARNAME_ACCOUNT
                popupVC.toChain = chainType
                let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
                cardPopup.resultDelegate = self
                cardPopup.show(onViewController: self)
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_not_support_cosmostation", comment: ""))
            return
        }
    }
    
    @IBAction func onClickScan(_ sender: UIButton) {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.modalPresentationStyle = .fullScreen
        qrScanVC.resultDelegate = self
        self.present(qrScanVC, animated: true, completion: nil)
    }
    
    func scannedAddress(result: String) {
        self.addressInput.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
    
    @IBAction func onClickPaste(_ sender: UIButton) {
        if let myString = UIPasteboard.general.string {
            self.addressInput.text = myString
        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))
        }
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        let userInput = addressInput.text?.trimmingCharacters(in: .whitespaces)
        if (userInput!.count <= 0) {
            self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
            return;
            
        } else {
            if let asset = getStarnameAssets().filter({ $0.uri == chainNameResource}).first, let chainType = asset.chainType {
                if (!WUtils.isValidChainAddress(chainType, userInput)) {
                    self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                    return
                }
            }
            self.dismiss(animated: true, completion: {
                self.resultDelegate?.addressEditedCallback(self.chainNameResource!, userInput!)
            })
        }
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        if (type == SELECT_POPUP_STARNAME_ACCOUNT) {
            self.addressInput.text = selectableAccounts[result].account_address
        }
    }
    
}



protocol ReplaceEditAddressDelegate{
    func addressEditedCallback (_ chain: String, _ address: String)
}

