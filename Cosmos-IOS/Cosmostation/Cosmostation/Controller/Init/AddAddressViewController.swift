//
//  AddAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 27/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AddAddressViewController: BaseViewController, QrScannerDelegate {

    @IBOutlet weak var addAddressMsgLabel: UILabel!
    @IBOutlet weak var addAddressInputText: AddressInputTextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard (_:)))
        self.view.addGestureRecognizer(tapGesture)
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_watch_wallet", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    @objc func dismissKeyboard (_ sender: UITapGestureRecognizer) {
        self.view.endEditing(true)
    }
    
    @IBAction func onClickScan(_ sender: UIButton) {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        self.navigationController?.pushViewController(qrScanVC, animated: false)
        
    }
    
    @IBAction func onClickPaste(_ sender: Any) {
        if let myString = UIPasteboard.general.string {
            self.addAddressInputText.text = myString
        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))
        }
    }
    
    @IBAction func onClickCancel(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        let userInput = self.addAddressInputText.text?.trimmingCharacters(in: .whitespaces) ?? ""
        if (userInput.starts(with: "cosmos1")) {
            if (WKey.isValidateBech32(userInput)) {
                if (ChainType.SUPPRT_CHAIN().contains(ChainType.COSMOS_TEST)) {
                    self.onShowCosmosChainSelect(userInput)
                    return;
                } else {
                    self.onGenWatchAccount(ChainType.COSMOS_MAIN, userInput)
                    return;
                }
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "iaa1")) {
            if (WKey.isValidateBech32(userInput)) {
                if (ChainType.SUPPRT_CHAIN().contains(ChainType.IRIS_TEST)) {
                    self.onShowIrisChainSelect(userInput)
                    return;
                } else {
                    self.onGenWatchAccount(ChainType.IRIS_MAIN, userInput)
                    return;
                }
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "bnb1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.BINANCE_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "kava1")) {
            if (WKey.isValidateBech32(userInput)) {
                if (ChainType.SUPPRT_CHAIN().contains(ChainType.KAVA_TEST)) {
                    self.onShowKavaChainSelect(userInput)
                    return;
                } else {
                    self.onGenWatchAccount(ChainType.KAVA_MAIN, userInput)
                    return;
                }
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
                   
        } else if (userInput.starts(with: "star1")) {
            if (WKey.isValidateBech32(userInput)) {
                if (ChainType.SUPPRT_CHAIN().contains(ChainType.IOV_TEST)) {
                    self.onShowIovChainSelect(userInput)
                    return;
                } else {
                    self.onGenWatchAccount(ChainType.IOV_MAIN, userInput)
                    return;
                }
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "band1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.BAND_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "secret1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SECRET_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "tbnb1")) {
            if (!ChainType.SUPPRT_CHAIN().contains(ChainType.BINANCE_TEST)) {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                return;
                
            }
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.BINANCE_TEST, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "okexchain1")) {
            if (WKey.isValidateBech32(userInput)) {
                if (ChainType.SUPPRT_CHAIN().contains(ChainType.OKEX_TEST)) {
                    self.onShowOkChainSelect(userInput)
                    return;
                } else {
                    self.onGenWatchAccount(ChainType.OKEX_MAIN, userInput)
                    return;
                }
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "certik1")) {
            if (WKey.isValidateBech32(userInput)) {
                if (ChainType.SUPPRT_CHAIN().contains(ChainType.CERTIK_TEST)) {
                    self.onShowCertikChainSelect(userInput)
                    return;
                } else {
                    self.onGenWatchAccount(ChainType.CERTIK_MAIN, userInput)
                    return;
                }
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "akash1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.AKASH_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "persistence1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.PERSIS_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "sent1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SENTINEL_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "fetch1")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.FETCH_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
            self.addAddressInputText.text = ""
            return;
            
        }
    }
    
    func onGenWatchAccount(_ chain:ChainType, _ address: String) {
        if (BaseData.instance.isDupleAccount(address, WUtils.getChainDBName(chain))) {
            self.onShowToast(NSLocalizedString("error_duple_address", comment: ""))
            return
        }
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let newAccount = Account.init(isNew: true)
            newAccount.account_address = address
            newAccount.account_base_chain = WUtils.getChainDBName(chain)
            newAccount.account_has_private = false
            newAccount.account_from_mnemonic = false
            newAccount.account_import_time = Date().millisecondsSince1970
            newAccount.account_sort_order = 9999
            let insertResult = BaseData.instance.insertAccount(newAccount)
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                if (insertResult > 0) {
                    BaseData.instance.setLastTab(0)
                    BaseData.instance.setRecentAccountId(insertResult)
                    self.onStartMainTab()
                } else {
                    //TODO Error control
                }
            });
        }
    }
    
    func onShowCosmosChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let cosmosAction = UIAlertAction(title: NSLocalizedString("chain_title_cosmos", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.COSMOS_MAIN, input)
        })
        cosmosAction.setValue(UIImage(named: "cosmosWhMain")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let cosmosTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_cosmos", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.COSMOS_TEST, input)
        })
        cosmosTestAction.setValue(UIImage(named: "cosmosTestChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(cosmosAction)
        showAlert.addAction(cosmosTestAction)
        self.present(showAlert, animated: true, completion: nil)
    }
    
    func onShowIrisChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let irisAction = UIAlertAction(title: NSLocalizedString("chain_title_iris", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.IRIS_MAIN, input)
        })
        irisAction.setValue(UIImage(named: "irisWh")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let irisTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_iris", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.IRIS_TEST, input)
        })
        irisTestAction.setValue(UIImage(named: "irisTestChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(irisAction)
        showAlert.addAction(irisTestAction)
        self.present(showAlert, animated: true, completion: nil)
    }
    
    
    func onShowKavaChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let kavaAction = UIAlertAction(title: NSLocalizedString("chain_title_kava", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.KAVA_MAIN, input)
        })
        kavaAction.setValue(UIImage(named: "kavaImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let kavaTestAction = UIAlertAction(title: NSLocalizedString("chain_title_kava_test", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.KAVA_TEST, input)
        })
        kavaTestAction.setValue(UIImage(named: "kavaTestImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(kavaAction)
        showAlert.addAction(kavaTestAction)
        self.present(showAlert, animated: true, completion: nil)
    }
    
    func onShowIovChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let iovAction = UIAlertAction(title: NSLocalizedString("chain_title_iov", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.IOV_MAIN, input)
        })
        iovAction.setValue(UIImage(named: "iovChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let iovTestAction = UIAlertAction(title: NSLocalizedString("chain_title_iov_test", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.IOV_TEST, input)
        })
        iovTestAction.setValue(UIImage(named: "iovTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(iovAction)
        showAlert.addAction(iovTestAction)
        self.present(showAlert, animated: true, completion: nil)
    }
    
    func onShowCertikChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let certikAction = UIAlertAction(title: NSLocalizedString("chain_title_certik", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.CERTIK_MAIN, input)
        })
        certikAction.setValue(UIImage(named: "certikChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let certikTestAction = UIAlertAction(title: NSLocalizedString("chain_title_certik_test", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.CERTIK_TEST, input)
        })
        certikTestAction.setValue(UIImage(named: "certikTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(certikAction)
        showAlert.addAction(certikTestAction)
        self.present(showAlert, animated: true, completion: nil)
    }
    
    func onShowOkChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let okAction = UIAlertAction(title: NSLocalizedString("chain_title_okex", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.OKEX_MAIN, input)
        })
        okAction.setValue(UIImage(named: "okexChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let okTestAction = UIAlertAction(title: NSLocalizedString("chain_title_okex_test", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.OKEX_TEST, input)
        })
        okTestAction.setValue(UIImage(named: "okexTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(okAction)
        showAlert.addAction(okTestAction)
        self.present(showAlert, animated: true, completion: nil)
        
    }
    
    
    
    func scannedAddress(result: String) {
        self.addAddressInputText.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
}
