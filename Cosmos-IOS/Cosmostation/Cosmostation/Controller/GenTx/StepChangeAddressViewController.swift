//
//  StepChangeAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import QRCode
import Alamofire

class StepChangeAddressViewController: BaseViewController, QrScannerDelegate {
    
    @IBOutlet weak var newRewardAddressInput: AddressInputTextField!
    @IBOutlet weak var currentRewardAddressLabel: UILabel!
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        self.onFetchRewardAddress(pageHolderVC.mAccount!.account_address)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }

    @IBAction func onClickPaste(_ sender: UIButton) {
        if let myString = UIPasteboard.general.string {
            self.newRewardAddressInput.text = myString.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))
        }
        
    }
    
    @IBAction func onClickQrScan(_ sender: UIButton) {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        self.navigationController?.pushViewController(qrScanVC, animated: false)
        
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if(currentRewardAddressLabel.text == "-") {
            self.onShowToast(NSLocalizedString("error_network", comment: ""))
            return;
        }
        
        let userInput = newRewardAddressInput.text?.trimmingCharacters(in: .whitespaces)
        if (currentRewardAddressLabel.text == userInput) {
            self.onShowToast(NSLocalizedString("error_same_reward_address", comment: ""))
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
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            if (!userInput!.starts(with: "band") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
            return;
            
        }
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.mToChangeRewardAddress = userInput
        pageHolderVC.onNextPage()
        
    }
    
    func onFetchRewardAddress(_ accountAddr: String) {
        var url = ""
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
            url = CSS_LCD_URL_REWARD_ADDRESS + accountAddr + CSS_LCD_URL_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_REWARD_ADDRESS + accountAddr + IRIS_LCD_URL_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_REWARD_ADDRESS + accountAddr + BAND_REWARD_ADDRESS_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            request.responseString { (response) in
                switch response.result {
                case .success(let res):
                    guard let address = res as? String else {
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return;
                    }
                    let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                    self.currentRewardAddressLabel.text = trimAddress
                    if(trimAddress != accountAddr) {
                        self.currentRewardAddressLabel.textColor = UIColor.init(hexString: "f31963")
                    }
                    self.currentRewardAddressLabel.adjustsFontSizeToFitWidth = true
                    self.pageHolderVC.mCurrentRewardAddress = trimAddress
                    
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
                }
            }
        } else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let address = responseData.object(forKey: "result") as? String else {
                            self.onShowToast(NSLocalizedString("error_network", comment: ""))
                            return;
                    }
                    let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                    self.currentRewardAddressLabel.text = trimAddress
                    if(trimAddress != accountAddr) {
                        self.currentRewardAddressLabel.textColor = UIColor.init(hexString: "f31963")
                    }
                    self.currentRewardAddressLabel.adjustsFontSizeToFitWidth = true
                    self.pageHolderVC.mCurrentRewardAddress = trimAddress
                    
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
                }
            }
        }
    }
    
    func scannedAddress(result: String) {
        newRewardAddressInput.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
}
