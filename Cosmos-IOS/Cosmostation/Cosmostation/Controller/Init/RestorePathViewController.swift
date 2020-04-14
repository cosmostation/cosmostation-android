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
    var usingBip44:Bool = false
    @IBOutlet weak var restoreTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
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
        cell?.rootCardView.backgroundColor = WUtils.getChainBg(userChain!)
        WUtils.setDenomTitle(userChain!, cell!.denomTitle)
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            cell?.pathLabel.text = BASE_PATH.appending(String(indexPath.row))
        } else if (userChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || userChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            cell?.pathLabel.text = BNB_BASE_PATH.appending(String(indexPath.row))
        } else if (userChain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            cell?.pathLabel.text = IOV_BASE_PATH.appending(String(indexPath.row)).appending("'")
        } else if (userChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN || userChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if (self.usingBip44) {
                cell?.pathLabel.text = KAVA_BASE_PATH.appending(String(indexPath.row))
            } else {
                cell?.pathLabel.text = BASE_PATH.appending(String(indexPath.row))
            }
        }
        
        DispatchQueue.global().async {
            let address = WKey.getDpAddressPath(self.userInputWords!, indexPath.row, self.userChain!, self.usingBip44)
            
            DispatchQueue.main.async(execute: {
                cell?.addressLabel.text = address
                let tempAccount = BaseData.instance.selectExistAccount(address: address, chain: self.userChain!.rawValue)
                if (tempAccount == nil) {
                    cell?.stateLabel.text = NSLocalizedString("ready", comment: "")
                    cell?.stateLabel.textColor = UIColor.white
                } else {
                    if(tempAccount!.account_has_private) {
                        cell?.stateLabel.text = NSLocalizedString("imported", comment: "")
                        cell?.stateLabel.textColor = UIColor.init(hexString: "7A7f88")
                        cell?.rootCardView.backgroundColor = UIColor.init(hexString: "2E2E2E", alpha: 0.4)
                    } else {
                        cell?.stateLabel.text = NSLocalizedString("override", comment: "")
                        cell?.stateLabel.textColor = UIColor.white
                    }
                }
                
                if (self.userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, 6)
                    let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
                            guard let responseData = res as? NSDictionary,
                                let info = responseData.object(forKey: "result") as? [String : Any] else {
                                    return
                            }
                            let accountInfo = AccountInfo.init(info)
                            if ((accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY || accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) && accountInfo.value.coins.count != 0) {
                                cell?.denomAmount.attributedText = WUtils.displayAmount2(accountInfo.value.coins[0].amount, cell!.denomAmount.font!, 6, 6)
                            } else if (accountInfo.type == COSMOS_AUTH_TYPE_DELAYEDACCOUNT && accountInfo.value.BaseVestingAccount.BaseAccount.coins.count != 0) {
                                cell?.denomAmount.attributedText = WUtils.displayAmount2(accountInfo.value.BaseVestingAccount.BaseAccount.coins[0].amount, cell!.denomAmount.font!, 6, 6)
                            }
                        case .failure(let error):
                            if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                        }
                    }
                    
                } else if (self.userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, 6)
                    let request = Alamofire.request(IRIS_LCD_URL_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
                            guard let info = res as? [String : Any] else {
                                return
                            }
                            let accountInfo = AccountInfo.init(info)
                            if ((accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY || accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) && accountInfo.value.coins.count != 0) {
                                cell?.denomAmount.attributedText = WUtils.displayAmount2(accountInfo.value.coins[0].amount, cell!.denomAmount.font!, 18, 6)
                            } else if (accountInfo.type == COSMOS_AUTH_TYPE_DELAYEDACCOUNT && accountInfo.value.BaseVestingAccount.BaseAccount.coins.count != 0) {
                                cell?.denomAmount.attributedText = WUtils.displayAmount2(accountInfo.value.BaseVestingAccount.BaseAccount.coins[0].amount, cell!.denomAmount.font!, 18, 6)
                            }
                        case .failure(let error):
                            if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                        }
                    }
                    
                } else if (self.userChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, 6)
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
                                    cell?.denomAmount.attributedText = WUtils.displayAmount2(bnbBalance.free, cell!.denomAmount.font!, 0, 6)
                                }
                            }
                        case .failure(let error):
                            if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                        }
                    }
                    
                } else if (self.userChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, 6)
                    let request = Alamofire.request(KAVA_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
                            guard let info = res as? [String : Any] else {
                                return
                            }
                            let tempAccount = Account.init(isNew: true)
                            tempAccount.account_id = -1
                            let balances = WUtils.getBalancesWithKavaAccountInfo(tempAccount, KavaAccountInfo.init(info))
                            cell?.denomAmount.attributedText = WUtils.dpTokenAvailable(balances, cell!.denomAmount.font!, 6, KAVA_MAIN_DENOM, ChainType.SUPPORT_CHAIN_KAVA_MAIN)
                            
                        case .failure(let error):
                            if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                        }
                    }
                    
                } else if (self.userChain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 0, 9)
                    let request = Alamofire.request(IOV_REST_URL_BALANCE, method: .get, parameters: ["address":address], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
                            guard let info = res as? [String : Any] else {
                                return
                            }
                            let iovBalanceInfo = IovBalanceInfo.init(info)
                            for coin in iovBalanceInfo.coins {
                                if (coin.ticker == IOV_MAIN_DENOM) {
                                    cell?.denomAmount.attributedText = WUtils.displayAmount2(coin.getDpAmount(IOV_MAIN_DENOM), cell!.denomAmount.font!, 0, 9)
                                    break
                                }
                            }
                            
                        case .failure(let error):
                            if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                        }
                    }
                }  else if (self.userChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, 6)
                    let request = Alamofire.request(BNB_TEST_URL_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                            case .success(let res):
                                guard let info = res as? [String : Any] else {
                                    return
                                }
                                let bnbAccountInfo = BnbAccountInfo.init(info)
                                for bnbBalance in bnbAccountInfo.balances {
                                    if (bnbBalance.symbol == BNB_MAIN_DENOM) {
                                        cell?.denomAmount.attributedText = WUtils.displayAmount2(bnbBalance.free, cell!.denomAmount.font!, 0, 6)
                                    }
                                }
                            case .failure(let error):
                                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                            }
                    }
                    
                } else if (self.userChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    cell?.denomAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.denomAmount.font!, 6, 6)
                    let request = Alamofire.request(KAVA_TEST_ACCOUNT_INFO + address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                            case .success(let res):
                                guard let info = res as? [String : Any] else {
                                    return
                                }
                                let tempAccount = Account.init(isNew: true)
                                tempAccount.account_id = -1
                                let balances = WUtils.getBalancesWithKavaAccountInfo(tempAccount, KavaAccountInfo.init(info))
                                cell?.denomAmount.attributedText = WUtils.dpTokenAvailable(balances, cell!.denomAmount.font!, 6, KAVA_MAIN_DENOM, ChainType.SUPPORT_CHAIN_KAVA_TEST)
                                
                            case .failure(let error):
                                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                            }
                    }
                    
                }
            });
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell:RestorePathCell? = tableView.cellForRow(at: indexPath) as? RestorePathCell
        if (cell?.stateLabel.text == NSLocalizedString("imported", comment: "")) {
            return
        } else if (cell?.stateLabel.text == NSLocalizedString("ready", comment: "")) {
            BaseData.instance.setLastTab(0)
            self.onGenAccount(self.userInputWords!, self.userChain!, indexPath.row, self.usingBip44)

        } else {
            BaseData.instance.setLastTab(0)
            self.onOverrideAccount(self.userInputWords!, self.userChain!, indexPath.row, self.usingBip44)
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 86;
    }
    
    func onGenAccount(_ mnemonic: [String], _ chain:ChainType, _ path:Int, _ newBip:Bool) {
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
                newAccount.account_address = WKey.getDpAddressPath(mnemonic, path, chain, newBip)
                newAccount.account_base_chain = chain.rawValue
                newAccount.account_has_private = true
                newAccount.account_from_mnemonic = true
                newAccount.account_path = String(path)
                newAccount.account_m_size = Int64(self.userInputWords!.count)
                newAccount.account_import_time = Date().millisecondsSince1970
                newAccount.account_new_bip44 = newBip
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
    
    
    func onOverrideAccount(_ mnemonic: [String], _ chain:ChainType, _ path:Int, _ newBip:Bool) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var resource: String = ""
            for word in self.userInputWords! {
                resource = resource + " " + word
            }
            
            let existedAccount = BaseData.instance.selectExistAccount(address: WKey.getDpAddressPath(mnemonic, path, chain, newBip), chain: chain.rawValue)
            let keyResult = KeychainWrapper.standard.set(resource, forKey: existedAccount!.account_uuid.sha1(), withAccessibility: .afterFirstUnlockThisDeviceOnly)
            var updateResult :Int64 = -1
            if(keyResult) {
                existedAccount!.account_has_private = true
                existedAccount!.account_from_mnemonic = true
                existedAccount!.account_path = String(path)
                existedAccount!.account_m_size = Int64(self.userInputWords!.count)
                existedAccount!.account_new_bip44 = newBip
                updateResult = BaseData.instance.overrideAccount(existedAccount!)
                
                if(updateResult < 0) {
                    KeychainWrapper.standard.removeObject(forKey: existedAccount!.account_uuid.sha1())
                }
            }
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                if(keyResult && updateResult > 0) {
                    BaseData.instance.setRecentAccountId(existedAccount!.account_id)
                    self.onStartMainTab()
                } else {
                    //TODO Error control
                }
            });
        }
    }
}
