//
//  WalletDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletDetailViewController: BaseViewController, PasswordViewDelegate {
    
    
    var accountId: Int64?
    var mAccount: Account!
    
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var walletName: UILabel!
    @IBOutlet weak var walletAddress: UILabel!
    
    @IBOutlet weak var chainName: UILabel!
    @IBOutlet weak var importDate: UILabel!
    @IBOutlet weak var importState: UILabel!
    @IBOutlet weak var pathTitle: UILabel!
    @IBOutlet weak var keyPath: UILabel!
    @IBOutlet weak var noKeyMsg: UILabel!
    @IBOutlet weak var actionBtn: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("WalletDetailViewController ", accountId)
        updateView()
    }
    
    
    func updateView() {
        mAccount = BaseData.instance.selectAccountById(id: accountId!)
        if(mAccount == nil) {
            print("WalletDetailViewController no that accout Error")
        }
        
        if (mAccount.account_nick_name == "") { walletName.text = "Wallet " + String(mAccount.account_id)
        } else { walletName.text = mAccount.account_nick_name }
        
        walletAddress.text = mAccount.account_address
        walletAddress.adjustsFontSizeToFitWidth = true
        
        if(mAccount.account_base_chain == SUPPORT_CHAIN_COSMOS_MAIN) {
            chainName.text = "Cosmos Hub"
        } else {
            chainName.text = ""
        }
        
        importDate.text = WUtils.longTimetoString(input:mAccount.account_import_time)
        
        if(mAccount.account_has_private)  {
            actionBtn.setTitle("Check Mnemonic", for: .normal)
            importState.text = "With Mnemonics"
            keyPath.text = BASE_PATH.appending(mAccount.account_path)
            
            pathTitle.isHidden = false
            keyPath.isHidden = false
            noKeyMsg.isHidden = true
            
        } else {
            actionBtn.setTitle("Import Mnemonic", for: .normal)
            importState.text = "Only Address"
            pathTitle.isHidden = true
            keyPath.isHidden = true
            noKeyMsg.isHidden = false
            
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "Wallet Detail";
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }

    
    
    
    
    @IBAction func onClickNameChange(_ sender: Any) {
        let nameAlert = UIAlertController(title: "Change Wallet Name", message: nil, preferredStyle: .alert)
        
        nameAlert.addTextField { (textField) in
            textField.placeholder = "wallet name"
        }
        
        nameAlert.addAction(UIAlertAction(title: "Cancle", style: .cancel, handler: { [weak nameAlert] (_) in
            self.dismiss(animated: true, completion: nil)
        }))
        
        nameAlert.addAction(UIAlertAction(title: "OK", style: .default, handler: { [weak nameAlert] (_) in
            let textField = nameAlert?.textFields![0]
            let trimmedString = textField?.text?.trimmingCharacters(in: .whitespacesAndNewlines)
            if(trimmedString?.count ?? 0 > 0) {
                self.mAccount!.account_nick_name = trimmedString!
                BaseData.instance.updateAccount(self.mAccount!)
                self.updateView()
            }
        }))
        self.present(nameAlert, animated: true, completion:nil)
        
    }
    
    @IBAction func onClickCopy(_ sender: Any) {
        UIPasteboard.general.string = self.mAccount.account_address
        onShowToast(NSLocalizedString("address_copied", comment: ""))
    }
    
    @IBAction func onClickQrCode(_ sender: Any) {
        onShowToast(NSLocalizedString("prepare", comment: ""))
    }
    
    @IBAction func onClickActionBtn(_ sender: Any) {
        if(self.mAccount.account_has_private) {
            //TODO goto check
            
        } else {
            self.onStartImportMnemonic()
        }
    }
    
    @IBAction func onClickDelete(_ sender: Any) {
        let deleteAlert = UIAlertController(title: "Delete Wallet", message: "All information about this wallet will delete. \nIf you do not backup your mnemonics, you can not find it again!", preferredStyle: .alert)
        deleteAlert.addAction(UIAlertAction(title: "Delete", style: .destructive, handler: { [weak deleteAlert] (_) in
            self.confirmDelete()
        }))
        deleteAlert.addAction(UIAlertAction(title: "Close", style: .default, handler: { [weak deleteAlert] (_) in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(deleteAlert, animated: true, completion:nil)
    }
    
    
    func confirmDelete() {
        if(self.mAccount.account_has_private) {
            let transition:CATransition = CATransition()
            transition.duration = 0.3
            transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
            transition.type = CATransitionType.moveIn
            transition.subtype = CATransitionSubtype.fromTop
            
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(transition, forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_DELETE_ACCOUNT
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
            
        } else {
            self.onDeleteWallet(mAccount)
        }
    }
    
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(310), execute: {
                self.onDeleteWallet(self.mAccount)
            })
        }
    }
}
