//
//  MainTabViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import Toast_Swift

class MainTabViewController: UITabBarController, UITabBarControllerDelegate, SBCardPopupDelegate, AccountSelectDelegate {
    
    var mAccount:Account!
    var mChainType: ChainType!
    var mAccounts = Array<Account>()
    var mBalances = Array<Balance>()
    var mAllValidator = Array<Validator>()
    var mTopValidators = Array<Validator>()
    var mOtherValidators = Array<Validator>()
    var mMyValidators = Array<Validator>()
    var mBondingList = Array<Bonding>()
    var mUnbondingList = Array<Unbonding>()
    var mRewardList = Array<Reward>()
    var mIrisRewards: IrisRewards?
    var mAtomTic: NSDictionary?
    var mPriceTic: NSDictionary?
    var mFetchCnt = 0
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?
    var mIrisStakePool: NSDictionary?
    var mIrisTokenList = Array<IrisToken>()
    
    var mBnbTokenList = Array<BnbToken>()
    
    var waitAlert: UIAlertController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.onUpdateAccountDB()
        self.onFetchAccountData()
        
        self.delegate = self
        self.selectedIndex = BaseData.instance.getLastTab()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if( self.mFetchCnt > 0)  {
            self.showWaittingAlert()
        }
    }
    
    
    func tabBarController(_ tabBarController: UITabBarController, didSelect viewController: UIViewController) {
        BaseData.instance.setLastTab(tabBarController.selectedIndex)
    }
    
    func onShowAccountSwicth() {
        let sourceVC = self.selectedViewController!
        let accountSelectVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "AccountSelectViewController") as! AccountSelectViewController
        accountSelectVC.modalPresentationStyle = .overFullScreen
        accountSelectVC.resultDelegate = self

        sourceVC.view.superview?.insertSubview(accountSelectVC.view, aboveSubview: sourceVC.view)
        accountSelectVC.view.transform = CGAffineTransform(translationX: 0, y: -sourceVC.view.frame.size.height)
        UIView.animate(withDuration: 0.3, animations: {
            accountSelectVC.view.transform = CGAffineTransform(translationX: 0, y: 0)
            }) { (Finished) in
                sourceVC.present(accountSelectVC, animated: false, completion: nil)
        }
    }
    
    func SBCardPopupResponse(result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(490), execute: {
            let naviVC = self.selectedViewController as? UINavigationController
            var tagetVC:BaseViewController?
            if(result == 1) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "CreateViewController") as! CreateViewController
                tagetVC?.chainType = self.targetChain
                
            } else if(result == 2) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestoreViewController") as! RestoreViewController
                tagetVC?.chainType = self.targetChain
                
            } else if(result == 3) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "AddAddressViewController") as! AddAddressViewController
                
            }
            if(tagetVC != nil) {
                tagetVC?.hidesBottomBarWhenPushed = true
                naviVC?.navigationItem.title = ""
                naviVC?.pushViewController(tagetVC!, animated: true)
            }
        })
    }
    
    
    func onUpdateAccountDB() {
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mChainType = ChainType(rawValue: mAccount.account_base_chain)
        mAccounts = BaseData.instance.selectAllAccounts()
        if(mAccount == nil) {
            print("NO ACCOUNT ERROR!!!!")
        }
    }
    
    func onFetchAccountData() -> Bool {
        if(self.mFetchCnt > 0)  {
            return false
        }
        self.mTopValidators.removeAll()
        self.mOtherValidators.removeAll()
        self.mMyValidators.removeAll()
        self.mRewardList.removeAll()
        
        if (mAccount.account_base_chain == CHAIN_COSMOS_S) {
            self.mFetchCnt = 10
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            onFetchPriceTic(true)
            
        } else if (mAccount.account_base_chain == CHAIN_IRIS_S) {
            self.mFetchCnt = 8
            self.mAllValidator.removeAll()
            self.irisValidatorPage = 1
            onFetchIrisValidatorsInfo(irisValidatorPage)
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            onFetchIrisReward(mAccount)
            onFetchIrisPool()
            onFetchIrisTokens()
            onFetchPriceTic(true)
            
        } else if (mAccount.account_base_chain == CHAIN_BINANCE_S) {
            self.mFetchCnt = 3
            self.mAllValidator.removeAll()
            onFetchAccountInfo(mAccount)
            onFetchBnbTokens()
            onFetchPriceTic(true)
            
        } else if (mAccount.account_base_chain == CHAIN_IOV_S) {
            self.mFetchCnt = 1
            self.mAllValidator.removeAll()
            onFetchIovBalance(mAccount)
            
        } else if (mAccount.account_base_chain == CHAIN_KAVA_S) {
            self.mFetchCnt = 10
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            onFetchPriceTic(true)
                   
        }
        return true
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
                mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
                mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
                mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
                
                mAllValidator.removeAll()
                mAllValidator.append(contentsOf: mTopValidators)
                mAllValidator.append(contentsOf: mOtherValidators)
                
            } else if (mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
                mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
                mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
                mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
                
                mTopValidators.removeAll()
                mOtherValidators.removeAll()
                for validator in mAllValidator {
                    if (validator.status == validator.BONDED) {
                        mTopValidators.append(validator)
                    } else {
                        mOtherValidators.append(validator)
                    }
                }
                
            }  else if (mChainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
                mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
                if (mBnbTokenList.count <= 0) {
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                }
                NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
                self.hideWaittingAlert()
                return
                
            } else if (mChainType == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
                mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
                mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
                NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
                self.hideWaittingAlert()
                return
                
            }
            
            for validator in mAllValidator {
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
                if (mine) {
                    self.mMyValidators.append(validator)
                }
            }
            
            if (mAllValidator.count <= 0) {
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
            NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
            self.hideWaittingAlert()
        }
    }
    
    func onFetchTopValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_VALIDATORS
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"bonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                            self.onFetchFinished()
                            return
                    }
                    self.mTopValidators.removeAll()
                    for validator in validators {
                        self.mTopValidators.append(Validator(validator as! [String : Any]))
                    }
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                    }
                    self.mTopValidators.removeAll()
                    for validator in validators {
                        self.mTopValidators.append(Validator(validator as! [String : Any]))
                    }
                }

            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTopValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondedValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_VALIDATORS
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"unbonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                            self.onFetchFinished()
                            return
                    }
                    for validator in validators {
                        self.mOtherValidators.append(Validator(validator as! [String : Any]))
                    }
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                    }
                    for validator in validators {
                        self.mOtherValidators.append(Validator(validator as! [String : Any]))
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondedValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondingValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_VALIDATORS
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"unbonding"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                    }
                    for validator in validators {
                        self.mOtherValidators.append(Validator(validator as! [String : Any]))
                    }
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                    }
                    for validator in validators {
                        self.mOtherValidators.append(Validator(validator as! [String : Any]))
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondingValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    var irisValidatorPage = 1;
    func onFetchIrisValidatorsInfo(_ page:Int) {
        let request = Alamofire.request(IRIS_LCD_URL_VALIDATORS, method: .get, parameters: ["size":"100", "page":String(page)], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let validators = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for validator in validators {
                    self.mAllValidator.append(Validator(validator as! [String : Any]))
                }
                if (validators.count == 100) {
                    self.irisValidatorPage = self.irisValidatorPage + 1
                    self.onFetchIrisValidatorsInfo(self.irisValidatorPage)
                    
                } else {
                    self.onFetchFinished()
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIrisValidatorsInfo ", error) }
                self.onFetchFinished()
            }
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
             url = CSS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN ) {
            url = BNB_URL_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let info = responseData.object(forKey: "result") as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN ) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let bnbAccountInfo = BnbAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithBnbAccountInfo(account, bnbAccountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithBnbAccountInfo(account, bnbAccountInfo))
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let kavaAccountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, kavaAccountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, kavaAccountInfo))
                    
                }
            case .failure(let error):
                if (SHOW_LOG) { print("Cosmos onFetchAccountInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBondingInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
             url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL
        } else if (mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_BONDING + account.account_address + IRIS_LCD_URL_BONDING_TAIL
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_BONDING + account.account_address + KAVA_BONDING_TAIL
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let bondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteBonding(account: account)
                        self.onFetchFinished()
                        return;
                    }
                    let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos, self.mChainType)
                    BaseData.instance.updateBondings(mTempBondings)
                    self.mFetchCnt = self.mFetchCnt + mTempBondings.count
                    for bondig in mTempBondings {
                        self.onFetchEachReward(account.account_address, bondig.bonding_v_address)
                    }
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    guard let bondinginfos = res as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteBonding(account: account)
                        self.onFetchFinished()
                        return;
                    }
                    let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos, self.mChainType)
                    BaseData.instance.updateBondings(mTempBondings)
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let bondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteBonding(account: account)
                        self.onFetchFinished()
                        return;
                    }
                    let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos, self.mChainType)
                    BaseData.instance.updateBondings(mTempBondings)
                    self.mFetchCnt = self.mFetchCnt + mTempBondings.count
                    for bondig in mTempBondings {
                        self.onFetchEachReward(account.account_address, bondig.bonding_v_address)
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchBondingInfo ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchUnbondingInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
             url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL
        } else if (mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_UNBONDING + account.account_address + IRIS_LCD_URL_UNBONDING_TAIL
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_UNBONDING + account.account_address + KAVA_UNBONDING_TAIL
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let unbondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteUnbonding(account: account)
                        self.onFetchFinished()
                        return
                    }
                    BaseData.instance.updateUnbondings(self.mAccount.account_id, WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos, self.mChainType))
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    guard let unbondinginfos = res as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteUnbonding(account: account)
                        self.onFetchFinished()
                        return
                    }
                    BaseData.instance.updateUnbondings(self.mAccount.account_id, WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos, self.mChainType))
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let unbondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteUnbonding(account: account)
                        self.onFetchFinished()
                        return
                    }
                    BaseData.instance.updateUnbondings(self.mAccount.account_id, WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos, self.mChainType))
                    
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondingInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIrisReward(_ account: Account) {
        let url = IRIS_LCD_URL_REWARD + account.account_address + IRIS_LCD_URL_REWARD_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let irisRewards = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                self.mIrisRewards = IrisRewards(irisRewards as! [String : Any])
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIrisReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchEachReward(_ accountAddr: String, _ validatorAddr:String) {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_REWARD_FROM_VAL + accountAddr + KAVA_REWARD_FROM_VAL_TAIL + validatorAddr
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return;
                    }
                    let reward = Reward.init()
                    reward.reward_v_address = validatorAddr
                    for rawReward in rawRewards {
                        reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                    }
                    self.mRewardList.append(reward)
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return;
                    }
                    let reward = Reward.init()
                    reward.reward_v_address = validatorAddr
                    for rawReward in rawRewards {
                        reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                    }
                    self.mRewardList.append(reward)
                    
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchEachReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchInflation() {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_INFLATION
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_INFLATION
        }
        self.mInflation = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let inflation = responseData.object(forKey: "result") as? String else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mInflation = inflation.replacingOccurrences(of: "\"", with: "")
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let inflation = responseData.object(forKey: "result") as? String else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mInflation = inflation.replacingOccurrences(of: "\"", with: "")
                    
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchInflation ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchProvision() {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_PROVISIONS
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_PROVISIONS
        }
        self.mProvision = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let provisions = responseData.object(forKey: "result") as? String else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mProvision = provisions.replacingOccurrences(of: "\"", with: "")
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let provisions = responseData.object(forKey: "result") as? String else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mProvision = provisions.replacingOccurrences(of: "\"", with: "")
                    
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchProvision ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchStakingPool() {
        var url: String?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_STAKING_POOL
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_STAKING_POOL
        }
        self.mStakingPool = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let stakingPool = responseData.object(forKey: "result") as? NSDictionary else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mStakingPool = stakingPool
                    
                } else if (self.mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let stakingPool = responseData.object(forKey: "result") as? NSDictionary else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mStakingPool = stakingPool
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchStakingPool ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIrisPool() {
        let url = IRIS_LCD_URL_STAKING_POOL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let irisStakePool = res as? NSDictionary {
                    self.mIrisStakePool = irisStakePool
                }
            case .failure(let error):
                if (SHOW_LOG) { print("irisStakePool ", error) }
            }
            self.onFetchFinished()
        }
    
    }
    
    
    func onFetchIrisTokens() {
        let url = IRIS_LCD_URL_TOKENS
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let tokens = res as? Array<NSDictionary> {
                    self.mIrisTokenList.removeAll()
                    for token in tokens {
                        self.mIrisTokenList.append(IrisToken(token as! [String : Any]))
                    }
                }
            case .failure(let error):
                if (SHOW_LOG) { print("irisStakePool ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBnbTokens() {
        let url = BNB_URL_TOKENS
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"3000"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let tokens = res as? Array<NSDictionary> {
                    self.mBnbTokenList.removeAll()
                    for token in tokens {
                        self.mBnbTokenList.append(BnbToken(token as! [String : Any]))
                    }
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchBnbTokens ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIovBalance(_ account: Account) {
        let request = Alamofire.request(IOV_URL_BALANCE + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    return
                }
                let iovBalanceInfo = IovBalanceInfo.init(info)
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithIov(account, iovBalanceInfo))
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIovBalance ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    
    func onFetchPriceTic(_ callback:Bool) {
        var url: String?
        var parameters: Parameters?
        if (mChainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "cosmos"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "3794"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mChainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "iris-network"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "3874"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mChainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "binancecoin"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "1839"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mChainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "kava"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "4846"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        }
        
        let request = Alamofire.request(url!, method: .get,  parameters: parameters, encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let priceTic = res as? NSDictionary {
                    if (BaseData.instance.getMarket() == 0) {
                        BaseData.instance.setPriceTicCgc(priceTic)
                    } else {
                        BaseData.instance.setPriceTicCmc(priceTic)
                    }
                    self.mPriceTic = priceTic
                    if(!callback) { self.onShowToast(NSLocalizedString("currency_fetch_success", comment: "")) }
                }
                
            case .failure(let error):
                if(!callback) { self.onShowToast(NSLocalizedString("currency_fetch_failed", comment: "")) }
                if (SHOW_LOG) { print("onFetchPriceTic ", error) }
            }
            if(callback) {
                self.onFetchFinished()
            }
        }
    }
    
    func onShowToast(_ text:String) {
        var style = ToastStyle()
        style.backgroundColor = UIColor.gray
        self.view.makeToast(text, duration: 2.0, position: .bottom, style: style)
    }
    
    public func showWaittingAlert() {
        waitAlert = UIAlertController(title: "", message: "\n\n\n\n", preferredStyle: .alert)
        let image = LoadingImageView(frame: CGRect(x: 0, y: 0, width: 58, height: 58))
        waitAlert!.view.addSubview(image)
        image.translatesAutoresizingMaskIntoConstraints = false
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerX, relatedBy: .equal, toItem: waitAlert!.view, attribute: .centerX, multiplier: 1, constant: 0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerY, relatedBy: .equal, toItem: waitAlert!.view, attribute: .centerY, multiplier: 1, constant: 0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .width, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 58.0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .height, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 58.0))
        WUtils.clearBackgroundColor(of: waitAlert!.view)
        self.present(waitAlert!, animated: true, completion: nil)
        image.onStartAnimation()
        
    }
    
    public func hideWaittingAlert(){
        if (waitAlert != nil) {
            waitAlert?.dismiss(animated: true, completion: nil)
        }
    }
    
    func accountSelected(_ id: Int) {
        if (id != self.mAccount.account_id) {
            BaseData.instance.setRecentAccountId(Int64(id))
            BaseData.instance.setLastTab(self.selectedIndex)

            let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
            let appDelegate = UIApplication.shared.delegate as! AppDelegate            
            appDelegate.window?.rootViewController = mainTabVC
            self.present(mainTabVC, animated: true, completion: nil)
        }
    }
    
    var targetChain:ChainType?
    func addAccount(_ chain: ChainType) {
        targetChain = chain
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
            let popupContent = AddViewController.create()
            let cardPopup = SBCardPopupViewController(contentViewController: popupContent)
            cardPopup.resultDelegate = self
            cardPopup.show(onViewController: self)
        })
    }
    
}
