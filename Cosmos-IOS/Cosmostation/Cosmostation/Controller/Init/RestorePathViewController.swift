//
//  RestorePathViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 28/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class RestorePathViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    var userChain: ChainType?
    var userInputWords: [String]?
    var maskerKey: HDPrivateKey?
    @IBOutlet weak var restoreTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        maskerKey = WKey.getMasterKeyFromWords(mnemonic: userInputWords!)
        
        self.restoreTableView.delegate = self
        self.restoreTableView.dataSource = self
        self.restoreTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.restoreTableView.register(UINib(nibName: "RestorePathCell", bundle: nil), forCellReuseIdentifier: "RestorePathCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_path", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 5
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:RestorePathCell? = tableView.dequeueReusableCell(withIdentifier:"RestorePathCell") as? RestorePathCell
        if(maskerKey == nil) {
            return cell!
        }
        let address = WKey.getHDKeyDpAddressWithPath(maskerKey!, path: indexPath.row, chain: userChain!)
        cell?.addressLabel.text = address
        cell?.rootCardView.backgroundColor = WUtils.getChainBg(userChain!)
        WUtils.setDenomTitle(userChain!, cell!.denomTitle)
        
        let tempAccount = BaseData.instance.selectExistAccount(address: address, chain: userChain!.rawValue)
        if (tempAccount == nil) {
            cell?.stateLabel.text = NSLocalizedString("ready", comment: "")
            cell?.stateLabel.textColor = UIColor.white
            cell?.rootCardView.backgroundColor = WUtils.getChainBg(userChain!)
        } else {
            if(tempAccount!.account_has_private) {
                cell?.stateLabel.text = NSLocalizedString("imported", comment: "")
                cell?.stateLabel.textColor = UIColor.init(hexString: "7A7f88")
                cell?.rootCardView.backgroundColor = UIColor.init(hexString: "2E2E2E", alpha: 0.4)
            } else {
                cell?.stateLabel.text = NSLocalizedString("override", comment: "")
                cell?.stateLabel.textColor = UIColor.white
                cell?.rootCardView.backgroundColor = WUtils.getChainBg(userChain!)
            }

        }
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            cell?.pathLabel.text = BASE_PATH.appending(String(indexPath.row))
            let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    guard let responseData = res as? NSDictionary,
//                        let info = responseData.object(forKey: "result") as? [String : Any] else {
//                            cell?.denomAmount.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, self.userChain!)
//                            return
//                    }
                    //TODO rollback cosmos-hub2
                    guard let info = res as? [String : Any] else {
                            cell?.denomAmount.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, self.userChain!)
                            return
                    }

                    let accountInfo = AccountInfo.init(info)
                    if ((accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY || accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) && accountInfo.value.coins.count != 0) {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(accountInfo.value.coins[0].amount, cell!.denomAmount.font!, 6, self.userChain!)
                    } else if (accountInfo.type == COSMOS_AUTH_TYPE_DELAYEDACCOUNT && accountInfo.value.BaseVestingAccount.BaseAccount.coins.count != 0) {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(accountInfo.value.BaseVestingAccount.BaseAccount.coins[0].amount, cell!.denomAmount.font!, 6, self.userChain!)
                    } else {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, self.userChain!)
                    }
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                }
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            cell?.pathLabel.text = BASE_PATH.appending(String(indexPath.row))
            let request = Alamofire.request(IRIS_LCD_URL_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let info = res as? [String : Any] else {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, self.userChain!)
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    if ((accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY || accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) && accountInfo.value.coins.count != 0) {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(accountInfo.value.coins[0].amount, cell!.denomAmount.font!, 6, self.userChain!)
                    } else if (accountInfo.type == COSMOS_AUTH_TYPE_DELAYEDACCOUNT && accountInfo.value.BaseVestingAccount.BaseAccount.coins.count != 0) {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(accountInfo.value.BaseVestingAccount.BaseAccount.coins[0].amount, cell!.denomAmount.font!, 6, self.userChain!)
                    } else {
                        cell?.denomAmount.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, self.userChain!)
                    }
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                }
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            cell?.pathLabel.text = BNB_BASE_PATH.appending(String(indexPath.row))
            cell?.denomAmount.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, self.userChain!)
            let request = Alamofire.request(BNB_URL_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let info = res as? [String : Any] else {
                        return
                    }
                    let bnbAccountInfo = BnbAccountInfo.init(info)
                    for bnbBalance in bnbAccountInfo.balances {
                        if (bnbBalance.symbol == BNB_MAIN_DENOM) {
                            cell?.denomAmount.attributedText = WUtils.displayAmount(bnbBalance.free, cell!.denomAmount.font!, 6, self.userChain!)
                        }
                    }
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                }
            }
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell:RestorePathCell? = tableView.cellForRow(at: indexPath) as? RestorePathCell
        if (cell?.stateLabel.text == NSLocalizedString("imported", comment: "")) {
            return
        } else if (cell?.stateLabel.text == NSLocalizedString("ready", comment: "")) {
//            if(BaseData.instance.selectAllAccounts().count >= 5) {
//                self.onShowToast(NSLocalizedString("error_max_account_over", comment: ""))
//                return
//            }
            BaseData.instance.setLastTab(0)
            self.onGenAccount(WKey.getHDKeyDpAddressWithPath(maskerKey!, path:indexPath.row, chain: self.userChain!),
                              self.userChain!,
                              String(indexPath.row))

        } else {
            BaseData.instance.setLastTab(0)
            self.onOverrideAccount(WKey.getHDKeyDpAddressWithPath(maskerKey!, path:indexPath.row, chain: self.userChain!),
                                   self.userChain!,
                                   String(indexPath.row))
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 86;
    }
    
    func onGenAccount(_ address:String, _ chain:ChainType, _ path:String) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var resource: String = ""
            for word in self.userInputWords! {
                resource = resource + " " + word
            }
            
            let newAccount = Account.init(isNew: true)
            let keyResult = KeychainWrapper.standard.set(resource, forKey: newAccount.account_uuid.sha1(), withAccessibility: .afterFirstUnlockThisDeviceOnly)
            var insertResult :Int64 = -1
            if(keyResult) {
                newAccount.account_address = address
                newAccount.account_base_chain = chain.rawValue
                newAccount.account_has_private = true
                newAccount.account_from_mnemonic = true
                newAccount.account_path = path
                newAccount.account_m_size = Int64(self.userInputWords!.count)
                newAccount.account_import_time = Date().millisecondsSince1970
                insertResult = BaseData.instance.insertAccount(newAccount)
                
                if(insertResult < 0) {
                    KeychainWrapper.standard.removeObject(forKey: newAccount.account_uuid.sha1())
                }
            }
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                if(keyResult && insertResult > 0) {
                    BaseData.instance.setRecentAccountId(insertResult)
                    self.onStartMainTab()
                } else {
                    //TODO Error control
                }
            });
        }
    }
    
    
    func onOverrideAccount(_ address:String, _ chain:ChainType, _ path:String) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var resource: String = ""
            for word in self.userInputWords! {
                resource = resource + " " + word
            }
            
            let existedAccount = BaseData.instance.selectExistAccount(address: address, chain: chain.rawValue)
            let keyResult = KeychainWrapper.standard.set(resource, forKey: existedAccount!.account_uuid.sha1(), withAccessibility: .afterFirstUnlockThisDeviceOnly)
            var updateResult :Int64 = -1
            if(keyResult) {
                existedAccount!.account_has_private = true
                existedAccount!.account_from_mnemonic = true
                existedAccount!.account_path = path
                existedAccount!.account_m_size = Int64(self.userInputWords!.count)
                updateResult = BaseData.instance.overrideAccount(existedAccount!)
                
                if(updateResult < 0) {
                    KeychainWrapper.standard.removeObject(forKey: existedAccount!.account_uuid.sha1())
                }
            }
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                if(keyResult && updateResult > 0) {
                    BaseData.instance.setRecentAccountId(updateResult)
                    self.onStartMainTab()
                } else {
                    //TODO Error control
                }
            });
        }
    }
}
