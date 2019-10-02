//
//  WalletDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import QRCode
import Alamofire

class WalletDetailViewController: BaseViewController, PasswordViewDelegate {

    var accountId: Int64?
    var mAccount: Account!
    var userChain: ChainType?
    
    @IBOutlet weak var cardAddress: CardView!
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var walletName: UILabel!
    @IBOutlet weak var walletAddress: UILabel!
    
    @IBOutlet weak var cardInfo: CardView!
    @IBOutlet weak var chainName: UILabel!
    @IBOutlet weak var importDate: UILabel!
    @IBOutlet weak var importState: UILabel!
    @IBOutlet weak var pathTitle: UILabel!
    @IBOutlet weak var keyPath: UILabel!
    @IBOutlet weak var noKeyMsg: UILabel!
    
    @IBOutlet weak var cardReward: CardView!
    @IBOutlet weak var rewardCard: CardView!
    @IBOutlet weak var rewardAddress: UILabel!
    
    @IBOutlet weak var actionBtn: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateView()
    }
    
    
    func updateView() {
        mAccount = BaseData.instance.selectAccountById(id: accountId!)
        userChain = WUtils.getChainType(mAccount!.account_base_chain)
        
        self.onFetchRewardAddress(mAccount.account_address)
        if (mAccount.account_nick_name == "") {
            walletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mAccount.account_id)
        } else {
            walletName.text = mAccount.account_nick_name
        }
        
        walletAddress.text = mAccount.account_address
        walletAddress.adjustsFontSizeToFitWidth = true
        cardAddress.backgroundColor = WUtils.getChainBg(userChain!)
        cardInfo.backgroundColor = WUtils.getChainBg(userChain!)
        cardReward.backgroundColor = WUtils.getChainBg(userChain!)
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            chainImg.image = UIImage(named: "cosmosWhMain")
            chainName.text = "Cosmos Hub"
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            chainImg.image = UIImage(named: "irisWh")
            chainName.text = "Iris Hub"
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
        self.stopAvoidingKeyboard()
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
        
        nameAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .cancel, handler: { _ in
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
        alert.view.subviews.first?.subviews.first?.subviews.first?.backgroundColor = UIColor.init(hexString: "EEEEEE")
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
    
    @IBAction func onClickRewardAddressChange(_ sender: UIButton) {
        if (!mAccount!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber.init(string: "80000000000000000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        
        let noticeAlert = UIAlertController(title: NSLocalizedString("reward_address_notice_title", comment: ""), message: NSLocalizedString("reward_address_notice_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .destructive, handler: { _ in
            let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
            if (self.userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                txVC.mType = COSMOS_MSG_TYPE_WITHDRAW_MIDIFY
            } else if (self.userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                txVC.mType = IRIS_MSG_TYPE_WITHDRAW_MIDIFY
            }
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txVC, animated: true)
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    
    @IBAction func onClickActionBtn(_ sender: Any) {
        if(self.mAccount.account_has_private) {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
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
    
    func confirmDelete() {
        if(self.mAccount.account_has_private) {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
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
    
    func onFetchRewardAddress(_ accountAddr: String) {
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let url = CSS_LCD_URL_REWARD_ADDRESS + accountAddr + CSS_LCD_URL_REWARD_ADDRESS_TAIL
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    guard let responseData = res as? NSDictionary,
//                        let address = responseData.object(forKey: "result") as? String else {
//                            return;
//                    }
                    //TODO rollback cosmos-hub2
                    guard let address = res as? String else {
                            return;
                    }
                    self.rewardCard.isHidden = false
                    let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                    self.rewardAddress.text = trimAddress
                    if(trimAddress != accountAddr) {
                        self.rewardAddress.textColor = UIColor.init(hexString: "f31963")
                    }
                    self.rewardAddress.adjustsFontSizeToFitWidth = true
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
                }
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let url = IRIS_LCD_URL_REWARD_ADDRESS + accountAddr + IRIS_LCD_URL_REWARD_ADDRESS_TAIL
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.responseString { (response) in
                switch response.result {
                case .success(let res):
                    guard let address = res as? String else {
                        return;
                    }
                    self.rewardCard.isHidden = false
                    let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                    self.rewardAddress.text = trimAddress
                    if(trimAddress != accountAddr) {
                        self.rewardAddress.textColor = UIColor.init(hexString: "f31963")
                    }
                    self.rewardAddress.adjustsFontSizeToFitWidth = true
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
                }
            }
        }
    }
    
}
