//
//  WalletDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import QRCode

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
//        print("WalletDetailViewController ", accountId)
        updateView()
    }
    
    
    func updateView() {
        mAccount = BaseData.instance.selectAccountById(id: accountId!)
        if(mAccount == nil) {
//            print("WalletDetailViewController no that accout Error")
            return
        }
        
        if (mAccount.account_nick_name == "") { walletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mAccount.account_id)
        } else { walletName.text = mAccount.account_nick_name }
        
        walletAddress.text = mAccount.account_address
        walletAddress.adjustsFontSizeToFitWidth = true
        
        if(mAccount.account_base_chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN.rawValue) {
            chainName.text = "Cosmos Hub"
        } else {
            chainName.text = ""
        }
        
        
        
        importDate.text = WUtils.longTimetoString(input:mAccount.account_import_time)
        
        if(mAccount.account_has_private)  {
            actionBtn.setTitle(NSLocalizedString("check_mnemonic", comment: ""), for: .normal)
            importState.text = NSLocalizedString("with_mnemonic", comment: "")
            keyPath.text = BASE_PATH.appending(mAccount.account_path)
            
            pathTitle.isHidden = false
            keyPath.isHidden = false
            noKeyMsg.isHidden = true
            
        } else {
            actionBtn.setTitle(NSLocalizedString("import_address", comment: ""), for: .normal)
            importState.text = NSLocalizedString("only_address", comment: "")
            pathTitle.isHidden = true
            keyPath.isHidden = true
            noKeyMsg.isHidden = false
            
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_wallet_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_wallet_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }

    
    
    
    
    @IBAction func onClickNameChange(_ sender: Any) {
        let nameAlert = UIAlertController(title: NSLocalizedString("change_wallet_name", comment: ""), message: nil, preferredStyle: .alert)
        
        nameAlert.addTextField { (textField) in
            textField.placeholder = NSLocalizedString("wallet_name", comment: "")
        }
        
        nameAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .cancel, handler: { [weak nameAlert] (_) in
            self.dismiss(animated: true, completion: nil)
        }))
        
        nameAlert.addAction(UIAlertAction(title: NSLocalizedString("ok", comment: ""), style: .default, handler: { [weak nameAlert] (_) in
            let textField = nameAlert?.textFields![0]
            let trimmedString = textField?.text?.trimmingCharacters(in: .whitespacesAndNewlines)
            if(trimmedString?.count ?? 0 > 0) {
                self.mAccount!.account_nick_name = trimmedString!
                BaseData.instance.updateAccount(self.mAccount!)
                BaseData.instance.setNeedRefresh(true)
                self.updateView()
            }
        }))
        self.present(nameAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            nameAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
        
    }
    
    @IBAction func onClickCopy(_ sender: Any) {
        UIPasteboard.general.string = self.mAccount.account_address
        onShowToast(NSLocalizedString("address_copied", comment: ""))
    }
    
    @IBAction func onClickQrCode(_ sender: Any) {
        var qrCode = QRCode(self.mAccount.account_address)
        qrCode?.backgroundColor = CIColor(rgba: "EEEEEE")
        qrCode?.size = CGSize(width: 200, height: 200)
        
        var walletName: String?
        if (self.mAccount!.account_nick_name == "") {
            walletName = NSLocalizedString("wallet_dash", comment: "") + String(self.mAccount!.account_id)
        } else {
            walletName = self.mAccount!.account_nick_name
        }
        
        let alert = UIAlertController(title: walletName, message: "\n\n\n\n\n\n\n\n", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("share", comment: ""), style: .default, handler:  { [weak alert] (_) in
            let shareTypeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
            shareTypeAlert.addAction(UIAlertAction(title: NSLocalizedString("share_text", comment: ""), style: .default, handler: { [weak shareTypeAlert] (_) in
                let text = self.mAccount.account_address
                let textToShare = [ text ]
                let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
                activityViewController.popoverPresentationController?.sourceView = self.view
                self.present(activityViewController, animated: true, completion: nil)
            }))
            shareTypeAlert.addAction(UIAlertAction(title: NSLocalizedString("share_qr", comment: ""), style: .default, handler: { [weak shareTypeAlert] (_) in
                let image = qrCode?.image
                let imageToShare = [ image! ]
                let activityViewController = UIActivityViewController(activityItems: imageToShare, applicationActivities: nil)
                activityViewController.popoverPresentationController?.sourceView = self.view
                self.present(activityViewController, animated: true, completion: nil)
            }))
            self.present(shareTypeAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                shareTypeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
        }))
        
        alert.addAction(UIAlertAction(title: NSLocalizedString("copy", comment: ""), style: .default, handler: { [weak alert] (_) in
            UIPasteboard.general.string = self.mAccount.account_address
            self.onShowToast(NSLocalizedString("address_copied", comment: ""))
        }))
        
        let image = UIImageView(image: qrCode?.image)
        image.contentMode = .scaleAspectFit
        alert.view.addSubview(image)
        image.translatesAutoresizingMaskIntoConstraints = false
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerX, relatedBy: .equal, toItem: alert.view, attribute: .centerX, multiplier: 1, constant: 0))
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerY, relatedBy: .equal, toItem: alert.view, attribute: .centerY, multiplier: 1, constant: 0))
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .width, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 140.0))
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .height, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 140.0))
        self.present(alert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            alert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    @IBAction func onClickActionBtn(_ sender: Any) {
        if(self.mAccount.account_has_private) {
            let transition:CATransition = CATransition()
            transition.duration = 0.3
            transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
            transition.type = CATransitionType.moveIn
            transition.subtype = CATransitionSubtype.fromTop
            
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(transition, forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
            
        } else {
            self.onStartImportMnemonic()
        }
    }
    
    @IBAction func onClickDelete(_ sender: Any) {
        let deleteAlert = UIAlertController(title: NSLocalizedString("delete_wallet", comment: ""), message: NSLocalizedString("delete_wallet_msg", comment: ""), preferredStyle: .alert)
        deleteAlert.addAction(UIAlertAction(title: NSLocalizedString("delete", comment: ""), style: .destructive, handler: { [weak deleteAlert] (_) in
            self.confirmDelete()
        }))
        deleteAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { [weak deleteAlert] (_) in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(deleteAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            deleteAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    @objc func dismissAlertController(){
        self.dismiss(animated: true, completion: nil)
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
                let walletCheckVC = WalletCheckViewController(nibName: "WalletCheckViewController", bundle: nil)
                walletCheckVC.hidesBottomBarWhenPushed = true
                walletCheckVC.accountId = self.accountId
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(walletCheckVC, animated: true)
            })
            
        } else if (result == PASSWORD_RESUKT_OK_FOR_DELETE) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(310), execute: {
                self.onDeleteWallet(self.mAccount)
            })
            
        }
    }
}
