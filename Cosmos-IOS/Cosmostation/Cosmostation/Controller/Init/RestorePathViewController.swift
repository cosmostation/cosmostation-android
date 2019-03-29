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
import Toast_Swift

class RestorePathViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    var userChain: String?
    var userInputWords: [String]?
    var maskerKey: HDPrivateKey?
    @IBOutlet weak var restoreTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        maskerKey = WKey.getMasterKeyFromWords(mnemonic: userInputWords!)
        print("RestorePathViewController userInputWords ", userInputWords)
        
        self.restoreTableView.delegate = self
        self.restoreTableView.dataSource = self
        self.restoreTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.restoreTableView.register(UINib(nibName: "RestorePathCell", bundle: nil), forCellReuseIdentifier: "RestorePathCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = "SELECT WALLET TO RESTORE";
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
        let address = WKey.getCosmosDpAddressWithPath(maskerKey!, indexPath.row)
        cell?.pathLabel.text = BASE_PATH.appending(String(indexPath.row))
        cell?.addressLabel.text = address
        
        let tempAccount = BaseData.instance.selectExistAccount(address: address, chain: userChain!)
        if(tempAccount == nil) {
            cell?.stateLabel.text = "Ready"
            cell?.stateLabel.textColor = UIColor.white
            cell?.rootCardView.backgroundColor = UIColor.init(hexString: "9c6cff", alpha: 0.15)
        } else {
            if(tempAccount?.account_has_private ?? false) {
                cell?.stateLabel.text = "Imported"
                cell?.stateLabel.textColor = UIColor.init(hexString: "7A7f88")
                cell?.rootCardView.backgroundColor = UIColor.init(hexString: "2E2E2E", alpha: 0.4)
            } else {
                cell?.stateLabel.text = "Override"
                cell?.stateLabel.textColor = UIColor.white
                cell?.rootCardView.backgroundColor = UIColor.init(hexString: "9c6cff", alpha: 0.15)
            }
            
        }
        
        let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + address,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                print(res)
                guard let info = res as? [String : Any] else {
                    cell?.atomAmount.attributedText = WUtils.displayAmout("0", font: cell!.atomAmount.font!)
                    return
                }
                let accountInfo = AccountInfo.init(info)
                if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT) {
                    cell?.atomAmount.attributedText = WUtils.displayAmout(accountInfo.value.coins[0].amount, font: cell!.atomAmount.font!)
                } else {
                    cell?.atomAmount.attributedText = WUtils.displayAmout(accountInfo.value.BaseVestingAccount.BaseAccount.coins[0].amount, font: cell!.atomAmount.font!)
                }
                
                case .failure(let error):
                print(error)
                
            }
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell:RestorePathCell? = tableView.cellForRow(at: indexPath) as? RestorePathCell
        if (cell?.stateLabel.text == "Imported") {
            return
        } else if (cell?.stateLabel.text == "Ready") {
            //Add new Account
            self.onGenAccount(WKey.getCosmosDpAddressWithPath(maskerKey!, indexPath.row),
                              self.userChain!,
                              String(indexPath.row))
            
        } else {
            //Update Account with key
            self.onOverrideAccount(WKey.getCosmosDpAddressWithPath(maskerKey!, indexPath.row),
                                   self.userChain!,
                                   String(indexPath.row))
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 86;
    }
    
    func onGenAccount(_ address:String, _ chain:String, _ path:String) {
        print("onGenAccount ", address, " ", chain, " ", path)
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var resource: String = ""
            for word in self.userInputWords! {
                resource = resource + " " + word
            }
            print("resource ", resource)
            
            let newAccount = Account.init(isNew: true)
            let keyResult = KeychainWrapper.standard.set(resource, forKey: newAccount.account_uuid.sha1())
            print("keyResult ", keyResult)
            var insertResult :Int64 = -1
            if(keyResult) {
                newAccount.account_address = address
                newAccount.account_base_chain = chain
                newAccount.account_has_private = true
                newAccount.account_from_mnemonic = true
                newAccount.account_path = path
                newAccount.account_m_size = Int64(self.userInputWords!.count)
                newAccount.account_import_time = Date().millisecondsSince1970
                
                insertResult = BaseData.instance.insertAccount(newAccount)
                print("insertResult ", insertResult)
                
                if(insertResult < 0) {
                    KeychainWrapper.standard.removeObject(forKey: newAccount.account_uuid.sha1())
                }
            }
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                print("keyResult ", keyResult)
                print("insertResult ", insertResult)
                if(keyResult && insertResult > 0) {
                    print("OKOKOK")
                    BaseData.instance.setRecentAccountId(insertResult)
                    self.onStartMainTab()
                } else {
                    print("NONONO")
                    //TODO Error control
                }
            });
        }
    }
    
    
    func onOverrideAccount(_ address:String, _ inchain:String, _ path:String) {
        print("onOverrideAccount")
        self.showWaittingAlert()
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var resource: String = ""
            for word in self.userInputWords! {
                resource = resource + " " + word
            }
            print("resource ", resource)
            
            let existedAccount = BaseData.instance.selectExistAccount(address: address, chain: inchain)
            let keyResult = KeychainWrapper.standard.set(resource, forKey: existedAccount!.account_uuid.sha1())
            var updateResult :Int64 = -1
            if(keyResult) {
                existedAccount!.account_has_private = true
                existedAccount!.account_from_mnemonic = true
                existedAccount!.account_path = path
                existedAccount!.account_m_size = Int64(self.userInputWords!.count)
                
                updateResult = BaseData.instance.overrideAccount(existedAccount!)
                print("updateResult ", updateResult)
                
                if(updateResult < 0) {
                    KeychainWrapper.standard.removeObject(forKey: existedAccount!.account_uuid.sha1())
                }
            }
            
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                print("keyResult ", keyResult)
                print("updateResult ", updateResult)
                if(keyResult && updateResult > 0) {
                    print("OKOKOK")
                    BaseData.instance.setRecentAccountId(updateResult)
                    self.onStartMainTab()
                } else {
                    print("NONONO")
                    //TODO Error control
                }
            });
        }
    }
}
