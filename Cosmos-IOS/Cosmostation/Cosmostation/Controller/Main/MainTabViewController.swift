//
//  MainTabViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import DropDown
import Toast_Swift

class MainTabViewController: UITabBarController, UITabBarControllerDelegate, SBCardPopupDelegate{
    
    var mAccount:Account!
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
    
    var dimView: UIView?
    let window = UIApplication.shared.keyWindow!
    let dropDown = DropDown()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.onUpdateAccountDB()
        self.onFetchAccountData()
        
        dimView = UIView(frame: window.bounds)
        dimView!.backgroundColor = UIColor.black
        dimView!.alpha  = 0.85
        onUpdateDropDownView()
        
        self.delegate = self
        self.selectedIndex = BaseData.instance.getLastTab()
        
    }
    
    func tabBarController(_ tabBarController: UITabBarController, didSelect viewController: UIViewController) {
        BaseData.instance.setLastTab(tabBarController.selectedIndex)
    }
    
    func onUpdateDropDownView() {
        var dropmenu = [String]()
        dropmenu.append("top")
        for account in mAccounts {
            dropmenu.append(String(account.account_id))
        }
        if(dropmenu.count < 6) {
            dropmenu.append("bottom")
        }
        
        dropDown.anchorView = self.view
        dropDown.bottomOffset = CGPoint(x: 0, y:UIApplication.shared.keyWindow?.safeAreaInsets.top ?? 0)
        dropDown.backgroundColor = UIColor.black
        dropDown.dismissMode = .onTap
        
        dropDown.dataSource = dropmenu
        dropDown.downScaleTransform = CGAffineTransform(translationX: 0, y: -500)
        dropDown.animationEntranceOptions = [.allowUserInteraction, .curveEaseInOut]
        dropDown.animationExitOptions = [.allowUserInteraction, .curveEaseInOut]
        
        dropDown.animationduration = 0.3
        dropDown.cellNib = UINib(nibName: "AccountPopupCell", bundle: nil)
        dropDown.cellHeight = 58
        dropDown.separatorColor = UIColor.clear
        
        dropDown.customCellConfiguration = { (index: Index, item: String, cell: DropDownCell) -> Void in
            guard let cell = cell as? AccountPopupCell else { return }
            if(index == 0) {
                cell.topPadding.isHidden = false
                cell.accountView.isHidden = true
                cell.newAccount.isHidden = true
                
            } else if (self.mAccounts.count != 5 && (index == 6 || index == self.dropDown.dataSource.count - 1)) {
                cell.topPadding.isHidden = true
                cell.accountView.isHidden = true
                cell.newAccount.isHidden = false
                
            } else {
                cell.topPadding.isHidden = true
                cell.accountView.isHidden = false
                cell.newAccount.isHidden = true
                let tempAccount = self.mAccounts[index - 1]
                
                cell.address.text = tempAccount.account_address
                
                if (tempAccount.account_id == BaseData.instance.getRecentAccountId()) {
                    cell.cardview.borderColor = UIColor.init(hexString: "#9ca2ac")
                } else {
                    cell.cardview.borderColor = UIColor.init(hexString: "#222426")
                }
                
                if (tempAccount.account_nick_name == "") {
                    cell.name.text = NSLocalizedString("wallet_dash", comment: "") + String(tempAccount.account_id)
                } else {
                    cell.name.text = tempAccount.account_nick_name
                }
                
                cell.chainName.textColor = WUtils.getChainColor(WUtils.getChainType(tempAccount.account_base_chain))
                if (tempAccount.account_base_chain == CHAIN_COSMOS_S) {
                    cell.chainImg.image = UIImage(named: "cosmosWhMain")
                    cell.chainName.text = "(Cosmos Hub)"
                    if (tempAccount.account_has_private) {
                        cell.keystate.image = cell.keystate.image?.withRenderingMode(.alwaysTemplate)
                        cell.keystate.tintColor = COLOR_ATOM
                    }
                    
                } else if (tempAccount.account_base_chain == CHAIN_IRIS_S) {
                    cell.chainImg.image = UIImage(named: "irisWh")
                    cell.chainName.text = "(Iris Hub)"
                    if (tempAccount.account_has_private) {
                        cell.keystate.image = cell.keystate.image?.withRenderingMode(.alwaysTemplate)
                        cell.keystate.tintColor = COLOR_IRIS
                    }
                }
            }
        }
        
        dropDown.willShowAction = { [unowned self] in
            self.window.addSubview(self.dimView!);
        }
        
        dropDown.cancelAction = { [unowned self] in
            self.dimView?.removeFromSuperview()
        }
        
        dropDown.selectionAction = { [unowned self] (index: Int, item: String) in
            self.dimView?.removeFromSuperview()
            if(item == "top") {
                
            } else if (item == "bottom") {
                let popupContent = AddViewController.create()
                let cardPopup = SBCardPopupViewController(contentViewController: popupContent)
                cardPopup.resultDelegate = self
                cardPopup.show(onViewController: self)
                
            } else {
                let id = Int64(item)
                BaseData.instance.setRecentAccountId(id!)
                BaseData.instance.setLastTab(self.selectedIndex)
                
                let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
                let appDelegate = UIApplication.shared.delegate as! AppDelegate
                appDelegate.window?.rootViewController = mainTabVC
                self.present(mainTabVC, animated: true, completion: nil)
            }
        }
        
    }
    
    
    func SBCardPopupResponse(result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(490), execute: {
            let naviVC = self.selectedViewController as? UINavigationController
            var tagetVC:BaseViewController?
            if(result == 1) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "CreateViewController") as! CreateViewController
                
            } else if(result == 2) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestoreViewController") as! RestoreViewController
                
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
            self.mFetchCnt = 7
            self.mAllValidator.removeAll()
            self.irisValidatorPage = 1
            onFetchIrisValidatorsInfo(irisValidatorPage)
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            onFetchIrisReward(mAccount)
            onFetchIrisPool()
            onFetchPriceTic(true)
        }
        
        return true
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if(mFetchCnt <= 0) {
            if (mAccount.account_base_chain == CHAIN_COSMOS_S) {
                mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
                mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
                mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
                mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
                
                mAllValidator.removeAll()
                mAllValidator.append(contentsOf: mTopValidators)
                mAllValidator.append(contentsOf: mOtherValidators)
//                print("mTopValidators Cnt " , mTopValidators.count)
//                print("mOtherValidators Cnt " , mOtherValidators.count)
//                print("mAllValidator Cnt " , mAllValidator.count)
//                print("Reward Cnt " , mRewardList.count)
                
                
                
            } else if (mAccount.account_base_chain == CHAIN_IRIS_S) {
                mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
                mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
                mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
                mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
//                print("mAccount : ", mAccount.account_sequence_number, "  ", mAccount.account_account_numner)
//                print("mBalances : ", mBalances.count, "   ", mBalances[0].balance_denom, "  ", mBalances[0].balance_amount)
//                print("mBondingList : ", mBondingList.count, "  ", mBondingList[0].bonding_shares)
//                print("mUnbondingList : ", mUnbondingList.count, "  ", mUnbondingList[0].unbonding_balance)
//                print("mIrisRewards : ", mIrisRewards?.delegations.count, "  ", mIrisRewards?.total[0].denom, " ", mIrisRewards?.total[0].amount)
//                print("mAllValidator : ", mAllValidator.count)
                
                mTopValidators.removeAll()
                mOtherValidators.removeAll()
                for validator in mAllValidator {
                    if (validator.status == validator.BONDED) {
                        mTopValidators.append(validator)
                    } else {
                        mOtherValidators.append(validator)
                    }
                }
//                print("mTopValidators : ", mTopValidators.count)
//                print("mOtherValidators : ", mOtherValidators.count)
                
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
        }
    }
    
    
    
    func onFetchTopValidatorsInfo() {
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: ["status":"bonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                        self.onFetchFinished()
//                        return
//                }
                //TODO rollback cosmos-hub2
                guard let validators = res as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                }
                self.mTopValidators.removeAll()
                for validator in validators {
                    self.mTopValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTopValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondedValidatorsInfo() {
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: ["status":"unbonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                        self.onFetchFinished()
//                        return
//                }
                //TODO rollback cosmos-hub2
                guard let validators = res as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                }

                for validator in validators {
                    self.mOtherValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondedValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondingValidatorsInfo() {
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: ["status":"unbonding"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                        self.onFetchFinished()
//                        return
//                }
                //TODO rollback cosmos-hub2
                guard let validators = res as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return
                }
                for validator in validators {
                    self.mOtherValidators.append(Validator(validator as! [String : Any]))
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondingValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    
    var irisValidatorPage = 1;
    func onFetchIrisValidatorsInfo(_ page:Int){
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
        if (mAccount.account_base_chain == CHAIN_COSMOS_S) {
            let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    guard let responseData = res as? NSDictionary,
//                        let info = responseData.object(forKey: "result") as? [String : Any] else {
//                        _ = BaseData.instance.deleteBalance(account: account)
//                        self.onFetchFinished()
//                        return
//                    }
                    //TODO rollback cosmos-hub2
                    guard let info = res as? [String : Any] else {
                            _ = BaseData.instance.deleteBalance(account: account)
                            self.onFetchFinished()
                            return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                }
                self.onFetchFinished()
            }
            
        } else if (mAccount.account_base_chain == CHAIN_IRIS_S) {
            let request = Alamofire.request(IRIS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
                }
                self.onFetchFinished()
            }
        }
    }
    
    func onFetchBondingInfo(_ account: Account) {
        if (mAccount.account_base_chain == CHAIN_COSMOS_S) {
            let url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.validate()
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    guard let responseData = res as? NSDictionary,
//                        let bondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                            _ = BaseData.instance.deleteBonding(account: account)
//                            self.onFetchFinished()
//                            return;
//                    }
                    //TODO rollback cosmos-hub2
                    guard let bondinginfos = res as? Array<NSDictionary> else {
                            _ = BaseData.instance.deleteBonding(account: account)
                            self.onFetchFinished()
                            return;
                    }
                    let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos, WUtils.getChainType(self.mAccount.account_base_chain))
                    BaseData.instance.updateBondings(mTempBondings)
                    self.mFetchCnt = self.mFetchCnt + mTempBondings.count
                    for bondig in mTempBondings {
                        self.onFetchEachReward(account.account_address, bondig.bonding_v_address)
                    }
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchBondingInfo ", error) }
                }
                self.onFetchFinished()
            }
            
        } else if (mAccount.account_base_chain == CHAIN_IRIS_S) {
            let url = IRIS_LCD_URL_BONDING + account.account_address + IRIS_LCD_URL_BONDING_TAIL
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.validate()
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let bondinginfos = res as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteBonding(account: account)
                        self.onFetchFinished()
                        return;
                    }
                    let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos, WUtils.getChainType(self.mAccount.account_base_chain))
                    BaseData.instance.updateBondings(mTempBondings)
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchBondingInfo ", error) }
                }
                self.onFetchFinished()
            }
        }
        
    }
    
    func onFetchUnbondingInfo(_ account: Account) {
        if (mAccount.account_base_chain == CHAIN_COSMOS_S) {
            let url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    guard let responseData = res as? NSDictionary,
//                        let unbondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                            _ = BaseData.instance.deleteUnbonding(account: account)
//                            self.onFetchFinished()
//                            return
//                    }
                    //TODO rollback cosmos-hub2
                    guard let unbondinginfos = res as? Array<NSDictionary> else {
                            _ = BaseData.instance.deleteUnbonding(account: account)
                            self.onFetchFinished()
                            return
                    }
                    BaseData.instance.updateUnbondings(self.mAccount.account_id, WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos, WUtils.getChainType(self.mAccount.account_base_chain)))
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchUnbondingInfo ", error) }
                }
                self.onFetchFinished()
            }
            
        } else if (mAccount.account_base_chain == CHAIN_IRIS_S) {
            let url = IRIS_LCD_URL_UNBONDING + account.account_address + IRIS_LCD_URL_UNBONDING_TAIL
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    print("res ", res)
                    guard let unbondinginfos = res as? Array<NSDictionary> else {
                        _ = BaseData.instance.deleteUnbonding(account: account)
                        self.onFetchFinished()
                        return
                    }
                    BaseData.instance.updateUnbondings(self.mAccount.account_id, WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos, WUtils.getChainType(self.mAccount.account_base_chain)))
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchUnbondingInfo ", error) }
                }
                self.onFetchFinished()
            }
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
        let url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                        self.onFetchFinished()
//                        return;
//                }
                //TODO rollback cosmos-hub2
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
                if (SHOW_LOG) { print("onFetchEachReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchInflation() {
        let url = CSS_LCD_URL_INFLATION
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let inflation = responseData.object(forKey: "result") as? String else {
//                        self.onFetchFinished()
//                        return;
//                }
                //TODO rollback cosmos-hub2
                guard let inflation = res as? String else {
                        self.onFetchFinished()
                        return;
                }
                self.mInflation = inflation.replacingOccurrences(of: "\"", with: "")
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchInflation ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchProvision() {
        let url = CSS_LCD_URL_PROVISIONS
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let provisions = responseData.object(forKey: "result") as? String else {
//                        self.onFetchFinished()
//                        return;
//                }
                //TODO rollback cosmos-hub2
                guard let provisions = res as? String else {
                        self.onFetchFinished()
                        return;
                }
                self.mProvision = provisions.replacingOccurrences(of: "\"", with: "")
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchProvision ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchStakingPool() {
        let url = CSS_LCD_URL_STAKING_POOL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let stakingPool = responseData.object(forKey: "result") as? NSDictionary else {
//                        self.onFetchFinished()
//                        return;
//                }
                //TODO rollback cosmos-hub2
                guard let stakingPool = res as? NSDictionary else {
                        self.onFetchFinished()
                        return;
                }
                self.mStakingPool = stakingPool
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
    
    func onFetchPriceTic(_ callback:Bool) {
        var url = ""
        var parameters: Parameters?
        if (mAccount.account_base_chain == CHAIN_COSMOS_S) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "cosmos"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "3794"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mAccount.account_base_chain == CHAIN_IRIS_S) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "iris-network"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "3874"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        }
        let request = Alamofire.request(url, method: .get,  parameters: parameters, encoding: URLEncoding.default, headers: [:]);
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
}
