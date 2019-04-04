//
//  WalletManageViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class WalletManageViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var accountTableView: UITableView!
    
    @IBOutlet weak var controlLayer: UIView!
    @IBOutlet weak var importBtn: UIButton!
    @IBOutlet weak var importView: UIView!
    @IBOutlet weak var importMnemonicMsg: UIStackView!
    @IBOutlet weak var importMnemonicBtn: UIButton!
    @IBOutlet weak var importAddressMsg: UIStackView!
    @IBOutlet weak var importAddressBtn: UIButton!
    
    var mFullAccounts = Array<Account>()
    var mWatchAccounts = Array<Account>()

    override func viewDidLoad() {
        super.viewDidLoad()
        let mAccounts = BaseData.instance.selectAllAccounts()
        if(mAccounts.count > 4) {
            controlLayer.isHidden = true
        }
        
        for account in mAccounts {
            if(account.account_has_private) {
                mFullAccounts.append(account)
            } else {
                mWatchAccounts.append(account)
            }
        }
        
        
        self.accountTableView.delegate = self
        self.accountTableView.dataSource = self
        self.accountTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.accountTableView.register(UINib(nibName: "ManageAccountCell", bundle: nil), forCellReuseIdentifier: "ManageAccountCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "Wallet Manage";
        self.navigationItem.title = "Wallet Manage";
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
//        print("WalletManageViewController viewWillAppear1 ", self.navigationController?.navigationBar.topItem?.title)
//        print("WalletManageViewController viewWillAppear2 ", self.navigationItem.title)
    }

    
    
    func numberOfSections(in tableView: UITableView) -> Int {
        if(mWatchAccounts.count > 0 && mFullAccounts.count > 0) {
            return 2
        } else {
            return 1
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mFullAccounts.count > 0 && section == 0) {
            return mFullAccounts.count
        } else {
            return mWatchAccounts.count
        }
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        if (mFullAccounts.count > 0 && section == 0) {
            let view = ManageHeader(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
            view.headerLabel.text = "With Mnemonics"
            view.keyImg.image = UIImage.init(named: "key_on")
            return view
        } else {
            let view = ManageHeader(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
            view.headerLabel.text = "Only Address"
            view.keyImg.image = UIImage.init(named: "key_off")
            return view
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ManageAccountCell? = tableView.dequeueReusableCell(withIdentifier:"ManageAccountCell") as? ManageAccountCell
        var account: Account?
        if (mFullAccounts.count > 0 && indexPath.section == 0) {
            account = mFullAccounts[indexPath.row]
            cell?.address.text = account?.account_address
            
        } else {
            account = mWatchAccounts[indexPath.row]
            cell?.address.text = account?.account_address
        }
        cell?.address.adjustsFontSizeToFitWidth = true
        
        if (account!.account_nick_name == "") { cell?.nameLabel.text = "Wallet " + String(account!.account_id)
        } else { cell?.nameLabel.text = account!.account_nick_name }
        
        let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account!.account_address,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    cell?.amount.attributedText = WUtils.displayAmout("0", cell!.amount.font!, 6)
                    return
                }
                let accountInfo = AccountInfo.init(info)
                if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT) {
                    cell?.amount.attributedText = WUtils.displayAmout(accountInfo.value.coins[0].amount, cell!.amount.font!, 6)
                } else {
                    cell?.amount.attributedText = WUtils.displayAmout(accountInfo.value.BaseVestingAccount.BaseAccount.coins[0].amount, cell!.amount.font!, 6)
                }
                
            case .failure(let error):
                print(error)
                
            }
        }
        return cell!
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 40
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        var account: Account?
        if (mFullAccounts.count > 0 && indexPath.section == 0) {
            account = mFullAccounts[indexPath.row]
        } else {
            account = mWatchAccounts[indexPath.row]
        }
        //TODO
        print("didSelectRowAt ", account?.account_id)
        let walletDetailVC = WalletDetailViewController(nibName: "WalletDetailViewController", bundle: nil)
        walletDetailVC.hidesBottomBarWhenPushed = true
        walletDetailVC.accountId = account?.account_id
//        self.navigationItem.title = ""
        self.navigationController?.pushViewController(walletDetailVC, animated: true)
    }
    
    
    
    @IBAction func onClickCreate(_ sender: Any) {
        print("onClickCreate");
        self.onStartCreate()
        
    }
    
    @IBAction func onClickImport(_ sender: Any) {
        print("onClickImport");
        UIView.animate(withDuration: 0.2, delay: 0.0, options: .curveEaseOut, animations: {
            self.importBtn.alpha = 0.0
        }, completion: { (finished) -> Void in
            UIView.animate(withDuration: 0.2, delay: 0.0, options: .transitionCurlUp, animations: {
                self.importView.alpha = 1.0
            }, completion: nil)
            UIView.animate(withDuration: 0.1, delay: 0.1, options: .transitionCurlUp, animations: {
                self.importMnemonicMsg.transform = CGAffineTransform(translationX: 0, y: -9.6)
                self.importAddressMsg.transform = CGAffineTransform(translationX: 0, y: -9.6)
            }, completion: nil)
            
        })
    }
    

    
    @IBAction func onClickImportMnemonic(_ sender: Any) {
        print("onClickImportMnemonic");
        self.onStartImportMnemonic()
    }
    
    @IBAction func onClickImportAddress(_ sender: Any) {
        print("onClickImportAddress");
        self.onStartImportAddress()
        
    }
}
