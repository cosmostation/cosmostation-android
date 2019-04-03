//
//  MainTabViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MainTabViewController: UITabBarController {
    
    var mAccount:Account!
    var mAccounts = Array<Account>()
    var mBalances = Array<Balance>()
    var mAllValidators = Array<Validator>()
    var mMyValidators = Array<Validator>()
    var mBondingList = Array<Bonding>()
    var mUnbondingList = Array<Unbonding>()
    var mRewardList = Array<Reward>()
    var mAllRewards = Array<Coin>()
    var mAtomTic: NSDictionary!
    var mFetchCnt = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mAccounts = BaseData.instance.selectAllAccounts()
        if(mAccount == nil) {
            print("NO ACCOUNT ERROR!!!!")
        }
        self.onFetchAccountData()
    }
    
    
    func onFetchAccountData() -> Bool {
        if(self.mFetchCnt > 0)  {
            return false
        }
        self.mAllValidators.removeAll()
        self.mMyValidators.removeAll()
        self.mRewardList.removeAll()
        
        self.mFetchCnt = 6
        onFetchValidatorsInfo()
        onFetchAccountInfo(mAccount)
        onFetchBondingInfo(mAccount)
        onFetchUnbondingInfo(mAccount)
        onFetchAllRewards(mAccount)
        onFetchAtomTic()
        return true
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if(mFetchCnt <= 0) {
            //update Validators
            print("onFetchFinished")
            
            mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
            mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
            mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
            
            for validator in mAllValidators {
                var mine = false;
                for bonding in mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address) {
                        mine = true;
                        break;
                    }
                }
                for unbonding in mUnbondingList {
                    if(unbonding.unbonding_v_address == validator.operator_address) {
                        mine = true;
                        break;
                    }
                }
                if(mine) {
                    self.mMyValidators.append(validator)
                }
            }
            NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
        }
    }
    
    
    
    func onFetchValidatorsInfo() {
//        print("onFetchValidatorsInfo")
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let validators = res as? Array<NSDictionary> else {
                    print("no validators!!")
                    return
                }
                self.mAllValidators.removeAll()
                for validator in validators {
                    self.mAllValidators.append(Validator(validator as! [String : Any]))
                }
//                print("size : ", self.mAllValidators.count)
                
            case .failure(let error):
                print("onFetchValidatorsInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
//        print("onFetchAccountInfo")
        let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
//                    print("no account!!")
                    BaseData.instance.deleteBalance(account: account)
                    self.onFetchFinished()
                    return
                }
                let accountInfo = AccountInfo.init(info)
                BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(WUtils.getBalancesWithAccountInfo(account, accountInfo))
                
            case .failure(let error):
                print("onFetchAccountInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBondingInfo(_ account: Account) {
//        print("onFetchBondingInfo")
        let url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.validate()
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let bondinginfos = res as? Array<NSDictionary> else {
//                    print("no bonding!!")
                    BaseData.instance.deleteBonding(account: account)
                    self.onFetchFinished()
                    return;
                }
//                print("bondinginfos", bondinginfos)
                let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos)
                BaseData.instance.updateBondings(mTempBondings)
                self.mFetchCnt = self.mFetchCnt + mTempBondings.count
                for bondig in mTempBondings {
                    self.onFetchEachReward(account.account_address, bondig.bonding_v_address)
                }
                
            case .failure(let error):
                print("onFetchBondingInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondingInfo(_ account: Account) {
//        print("onFetchUnbondingInfo")
        let url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let unbondinginfos = res as? Array<NSDictionary> else {
//                    print("no unbonding!!")
                    BaseData.instance.deleteUnbonding(account: account)
                    self.onFetchFinished()
                    return
                }
//                print("unbondinginfos ", unbondinginfos)
                BaseData.instance.updateUnbondings(WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos))
                
            case .failure(let error):
                print("onFetchUnbondingInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchEachReward(_ accountAddr: String, _ validatorAddr:String) {
//        print("onFetchEachReward")
        let url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawRewards = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return;
                }
                let reward = Reward.init()
                reward.reward_v_address = validatorAddr
                for rawReward in rawRewards {
                    reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.mRewardList.append(reward)
                
            case .failure(let error):
                print("onFetchEachReward ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchAllRewards(_ account: Account) {
//        print("onFetchAllRewards")
        let url = CSS_LCD_URL_REWARD_ALL + account.account_address + CSS_LCD_URL_REWARD_ALL_TAIL
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchAllRewards ", res)
                guard let rewards = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return;
                }
                self.mAllRewards.removeAll()
                for reward in rewards {
                    self.mAllRewards.append(Coin(reward as! [String : Any]))
                }
                
            case .failure(let error):
                print("onFetchAllRewards error", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchAtomTic() {
//         print("onFetchAtomTic")
        let request = Alamofire
            .request(CMC_PRICE_TIC+"3794",
                     method: .get,
                     parameters: ["convert":"USD"],
                     encoding: URLEncoding.default,
                     headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.mAtomTic = res as? NSDictionary
                
            case .failure(let error):
                print(error)
            }
            self.onFetchFinished()
        }
    }
}
