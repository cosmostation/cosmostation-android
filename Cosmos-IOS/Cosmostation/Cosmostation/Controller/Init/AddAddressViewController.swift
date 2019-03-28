//
//  AddAddressViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 27/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Toast_Swift

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
        self.navigationController?.navigationBar.topItem?.title = "WATCH ADDRESS";
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func onCheckValidateInput() -> Bool {
        var result = false
        
        return result
    }
    
    
    @objc func dismissKeyboard (_ sender: UITapGestureRecognizer) {
        self.view.endEditing(true)
    }
    
    @IBAction func onClickPaste(_ sender: Any) {
        if let myString = UIPasteboard.general.string {
            self.addAddressInputText.text = myString
        } else {
            var style = ToastStyle()
            style.backgroundColor = UIColor.gray
            self.view.makeToast(NSLocalizedString("error_no_clipboard", comment: ""), duration: 2.0, position: .bottom, style: style)
        }
    }
    
    @IBAction func onClickCancel(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    
    
    @IBAction func onClickNext(_ sender: Any) {
        let userInput = self.addAddressInputText.text ?? ""
        var address = "";
        //TODO check iris or cosmos or else
        if (WKey.isValidateAddressOrPubKey(userInput)) {
            if(userInput.starts(with: "cosmospub")) { address = WKey.getCosmosAddressFromPubKey(userInput)
            } else {  address = userInput }
            self.onGenWatchAccount(SUPPORT_CHAIN_COSMOS_MAIN, address)
            
        } else {
            var style = ToastStyle()
            style.backgroundColor = UIColor.gray
            self.view.makeToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""), duration: 2.0, position: .bottom, style: style)
            self.addAddressInputText.text = ""
        }
        
    }
    
    func onGenWatchAccount(_ chain:String, _ address: String) {
        print("onGenWatchAccount")
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let newAccount = Account.init(isNew: true)
            newAccount.account_address = address
            newAccount.account_base_chain = chain
            newAccount.account_has_private = false
            newAccount.account_from_mnemonic = false
            newAccount.account_import_time = Date().millisecondsSince1970
            
            let insertResult = BaseData.instance.insertAccount(newAccount)
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                if(insertResult > 0) {
                    BaseData.instance.setRecentAccountId(insertResult)
                    self.onStartMainTab()
                    
                } else {
                    print("NONONO")
                    //TODO Error control
                }
                
            });
        }
    }
}
