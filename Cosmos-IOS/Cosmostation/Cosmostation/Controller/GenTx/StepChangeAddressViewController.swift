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
import GRPC
import NIO

class StepChangeAddressViewController: BaseViewController, QrScannerDelegate {
    
    @IBOutlet weak var newRewardAddressInput: AddressInputTextField!
    @IBOutlet weak var currentRewardAddressLabel: UILabel!
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            self.onFetchRewardAddress_gRPC(pageHolderVC.mAccount!.account_address)
        } else {
            self.onFetchRewardAddress(pageHolderVC.mAccount!.account_address)
        }
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
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            if (!userInput!.starts(with: "star1") || !WKey.isValidateBech32(userInput!)) {
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
            
        } else if (pageHolderVC.chainType! == ChainType.MEDI_MAIN || pageHolderVC.chainType! == ChainType.MEDI_TEST) {
            if (!userInput!.starts(with: "panacea1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.EMONEY_MAIN) {
            if (!userInput!.starts(with: "emoney1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.JUNO_MAIN) {
            if (!userInput!.starts(with: "juno1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.ALTHEA_TEST) {
            if (!userInput!.starts(with: "althea1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.UMEE_TEST) {
            if (!userInput!.starts(with: "umee1") || !WKey.isValidateBech32(userInput!)) {
                self.onShowToast(NSLocalizedString("error_invalid_address", comment: ""))
                return;
            }
            
        } else if (pageHolderVC.chainType! == ChainType.AXELAR_TEST) {
            if (!userInput!.starts(with: "axelar1") || !WKey.isValidateBech32(userInput!)) {
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
    
    func onFetchRewardAddress(_ address: String) {
        let request = Alamofire.request(BaseNetWork.rewardAddressUrl(pageHolderVC.chainType, address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("res ", res)
                guard let responseData = res as? NSDictionary,
                    let address = responseData.object(forKey: "result") as? String else {
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return;
                }
                let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                self.currentRewardAddressLabel.text = trimAddress
                if (trimAddress != address) {
                    self.currentRewardAddressLabel.textColor = UIColor.init(hexString: "f31963")
                }
                self.currentRewardAddressLabel.adjustsFontSizeToFitWidth = true
                self.pageHolderVC.mCurrentRewardAddress = trimAddress
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
            }
        }
    }
    
    func onFetchRewardAddress_gRPC(_ address: String) {
        DispatchQueue.global().async {
            var responseAddress = ""
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Distribution_V1beta1_QueryDelegatorWithdrawAddressRequest.with {
                $0.delegatorAddress = address
            }
            do {
                let response = try Cosmos_Distribution_V1beta1_QueryClient(channel: channel).delegatorWithdrawAddress(req).response.wait()
                responseAddress = response.withdrawAddress.replacingOccurrences(of: "\"", with: "")
            } catch {
                print("onFetchRedelegation_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.currentRewardAddressLabel.text = responseAddress
                if (responseAddress != address) {
                    self.currentRewardAddressLabel.textColor = UIColor.init(hexString: "f31963")
                }
                self.currentRewardAddressLabel.adjustsFontSizeToFitWidth = true
                self.pageHolderVC.mCurrentRewardAddress = responseAddress
            });
        }
    }
    
    
    
    func scannedAddress(result: String) {
        newRewardAddressInput.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
}
