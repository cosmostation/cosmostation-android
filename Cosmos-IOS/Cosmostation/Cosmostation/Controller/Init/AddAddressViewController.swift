//
//  AddAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 27/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AddAddressViewController: BaseViewController {

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
        if (BaseData.instance.isDupleAccount(userInput)) {
            self.onShowToast(NSLocalizedString("error_duple_address", comment: ""))
            return
        }
        if (userInput.starts(with: "cosmos")) {
            if (userInput.starts(with: "cosmosvaloper")) {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            } else if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_COSMOS_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "iaa")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_IRIS_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "bnb")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_BINANCE_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "kava")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onShowKavaChainSelect(userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
                   
        } else if (userInput.starts(with: "iov")) {
            if (!ChainType.SUPPRT_CHAIN().contains(ChainType.SUPPORT_CHAIN_IOV_MAIN)) {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                return;
                
            }
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_IOV_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "band")) {
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_BAND_MAIN, userInput)
                return;
            } else {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                self.addAddressInputText.text = ""
                return;
            }
            
        } else if (userInput.starts(with: "tbnb")) {
            if (!ChainType.SUPPRT_CHAIN().contains(ChainType.SUPPORT_CHAIN_BINANCE_TEST)) {
                self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
                return;
                
            }
            if (WKey.isValidateBech32(userInput)) {
                self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_BINANCE_TEST, userInput)
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
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let newAccount = Account.init(isNew: true)
            newAccount.account_address = address
            newAccount.account_base_chain = chain.rawValue
            newAccount.account_has_private = false
            newAccount.account_from_mnemonic = false
            newAccount.account_import_time = Date().millisecondsSince1970
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
    
    func onShowKavaChainSelect(_ input:String) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let kavaAction = UIAlertAction(title: NSLocalizedString("chain_title_kava", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_KAVA_MAIN, input)
        })
        kavaAction.setValue(UIImage(named: "kavaImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        let kavaTestAction = UIAlertAction(title: NSLocalizedString("chain_title_kava_test", comment: ""), style: .default, handler: {_ in
            self.onGenWatchAccount(ChainType.SUPPORT_CHAIN_KAVA_TEST, input)
        })
        kavaTestAction.setValue(UIImage(named: "kavaTestImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(kavaAction)
        showAlert.addAction(kavaTestAction)
        self.present(showAlert, animated: true, completion: nil)
    }
}
