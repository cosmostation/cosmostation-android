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
//    var mAllRewards = Array<Coin>()
    var mAtomTic: NSDictionary?
    var mFetchCnt = 0
    
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
                
//            } else if (self.dropDown.dataSource.count == 6 || index == self.dropDown.dataSource.count - 1) {
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
                
                if (tempAccount.account_nick_name == "") { cell.name.text = NSLocalizedString("wallet_dash", comment: "") + String(tempAccount.account_id)
                } else { cell.name.text = tempAccount.account_nick_name }
                
                if(tempAccount.account_has_private) { cell.keystate.image = UIImage(named: "key_on")
                } else { cell.keystate.image = UIImage(named: "key_off") }
                
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
//            print("selectionAction ", item)
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
//        print("mAccount ", mAccount.account_base_chain)
    }
    
    
    func onFetchAccountData() -> Bool {
        if(self.mFetchCnt > 0)  {
            return false
        }
        self.mTopValidators.removeAll()
        self.mOtherValidators.removeAll()
        self.mMyValidators.removeAll()
        self.mRewardList.removeAll()
        
        self.mFetchCnt = 7
        onFetchTopValidatorsInfo()
        onFetchUnbondedValidatorsInfo()
        onFetchUnbondingValidatorsInfo()
        onFetchAccountInfo(mAccount)
        onFetchBondingInfo(mAccount)
        onFetchUnbondingInfo(mAccount)
//        onFetchAllRewards(mAccount)
        onFetchAtomTic()
        return true
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if(mFetchCnt <= 0) {
            //update Validators
//            print("onFetchFinished")
            
            mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
            mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
            mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
            
            mAllValidator.removeAll()
            mAllValidator.append(contentsOf: mTopValidators)
            mAllValidator.append(contentsOf: mOtherValidators)
//            print("mTopValidators Cnt " , mTopValidators.count)
//            print("mOtherValidators Cnt " , mOtherValidators.count)
//            print("mAllValidator Cnt " , mAllValidator.count)
//            print("Reward Cnt " , mRewardList.count)
            
            
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
                if(mine) {
                    self.mMyValidators.append(validator)
                }
            }
            NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
        }
    }
    
    
    
    func onFetchTopValidatorsInfo() {
//        print("onFetchTopValidatorsInfo")
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: ["status":"bonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let validators = res as? Array<NSDictionary> else {
                    print("no validators!!")
                    return
                }
                self.mTopValidators.removeAll()
                for validator in validators {
                    self.mTopValidators.append(Validator(validator as! [String : Any]))
                }
//                print("size : ", self.mTopValidators.count)
                
            case .failure(let error):
                print("onFetchTopValidatorsInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondedValidatorsInfo() {
//        print("onFetchUnbondedValidatorsInfo")
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: ["status":"unbonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let validators = res as? Array<NSDictionary> else {
                    print("no Unbonded!!")
                    return
                }
                for validator in validators {
                    self.mOtherValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                print("onFetchUnbondedValidatorsInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondingValidatorsInfo() {
//        print("onFetchUnbondingValidatorsInfo")
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: ["status":"unbonding"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let validators = res as? Array<NSDictionary> else {
                    print("no Unbonding!!")
                    return
                }
                for validator in validators {
                    self.mOtherValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                print("onFetchUnbondingValidatorsInfo ", error)
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
//                print("onFetchAccountInfo ", res)
                guard let info = res as? [String : Any] else {
//                    print("no account!!")
                    BaseData.instance.deleteBalance(account: account)
                    self.onFetchFinished()
                    return
                }
                let accountInfo = AccountInfo.init(info)
                BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                
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
            print("onFetchEachReward ", validatorAddr)
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
    
//    func onFetchAllRewards(_ account: Account) {
////        print("onFetchAllRewards")
//        let url = CSS_LCD_URL_REWARD_ALL + account.account_address + CSS_LCD_URL_REWARD_ALL_TAIL
//        let request = Alamofire.request(url,
//                                        method: .get,
//                                        parameters: [:],
//                                        encoding: URLEncoding.default,
//                                        headers: [:]);
//        request.responseJSON { (response) in
//            switch response.result {
//            case .success(let res):
////                print("onFetchAllRewards ", res)
//                guard let rewards = res as? Array<NSDictionary> else {
//                    self.onFetchFinished()
//                    return;
//                }
//                self.mAllRewards.removeAll()
//                for reward in rewards {
//                    self.mAllRewards.append(Coin(reward as! [String : Any]))
//                }
//                
//            case .failure(let error):
//                print("onFetchAllRewards error", error)
//            }
//            self.onFetchFinished()
//        }
//    }
    
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
                if(self.mAtomTic != nil){
                    BaseData.instance.setAtomTicCmc(self.mAtomTic!)
                }
                
                
            case .failure(let error):
                print(error)
            }
            self.onFetchFinished()
        }
    }
}
