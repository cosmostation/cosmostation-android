//
//  VaidatorDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class VaidatorDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
//class VaidatorDetailViewController: BaseViewController {

    @IBOutlet weak var validatorDetailTableView: UITableView!
    
    var mAccount: Account?
    var mValidator: Validator?
    var mBonding: Bonding?
    var mUnbondings = Array<Unbonding>()
    var mRewards = Array<Reward>()
    var mHistories = Array<History.InnerHits>()
    var mFetchCnt = 0
    var mMyValidator = false

    override func viewDidLoad() {
        super.viewDidLoad()
        print("mValidator ", mValidator?.description.moniker)
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        if(mAccount == nil) {
            print("NO ACCOUNT ERROR!!!!")
        }
        
        self.validatorDetailTableView.delegate = self
        self.validatorDetailTableView.dataSource = self
        self.validatorDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailMyDetailCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailMyDetailCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailMyActionCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailHistoryEmpty", bundle: nil), forCellReuseIdentifier: "ValidatorDetailHistoryEmpty")
        self.validatorDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        
        self.onFech()
        
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = "Validator Details";
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    
    func onFech(){
        mUnbondings.removeAll()
        mRewards.removeAll()
        mFetchCnt = 4
        onFetchValidatorInfo(mValidator!)
        onFetchSignleBondingInfo(mAccount!, mValidator!)
        onFetchSignleUnBondingInfo(mAccount!, mValidator!)
        onFetchHistory(mAccount!, mValidator!, "0", "100");
    }
    
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        print("onFetchFinished ", self.mFetchCnt)
        if(mFetchCnt <= 0) {
            print("onFetchFinished mBonding ", mBonding?.bonding_shares)
            print("onFetchFinished mUnbondings ", mUnbondings.count)
            print("onFetchFinished mRewards ", mRewards.count)
            print("onFetchFinished mHistories ", mHistories.count)
            
            if(WUtils.stringToDecimal(mBonding?.bonding_shares ?? "0") != NSDecimalNumber.zero || mUnbondings.count > 0) {
                mMyValidator = true
            } else {
                mMyValidator = false
            }
            print("mMyValidator ", mMyValidator)
            self.validatorDetailTableView.reloadData()
            
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            if(mMyValidator) {
                return 2
            } else {
                return 1
            }
            
        } else {
            if(mHistories.count > 0) {
                return mHistories.count
            } else {
                return 1
            }
        }
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = ValidatorDetailHistoryHeader(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        return view
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if(indexPath.section == 0) {
            if (indexPath.row == 0 && mMyValidator) {
                let cell:ValidatorDetailMyDetailCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailMyDetailCell") as? ValidatorDetailMyDetailCell
                return cell!
            } else if (indexPath.row == 0 && !mMyValidator) {
                let cell:ValidatorDetailCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailCell") as? ValidatorDetailCell
                return cell!
            } else {
                let cell:ValidatorDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailMyActionCell") as? ValidatorDetailMyActionCell
                return cell!
            }
            
        } else {
            if(mHistories.count > 0) {
                let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
                return cell!
            } else {
                let cell:ValidatorDetailHistoryEmpty? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailHistoryEmpty") as? ValidatorDetailHistoryEmpty
                return cell!
            }
        }
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if(indexPath.section == 0) {
            if (indexPath.row == 0 && mMyValidator) {
                return 195;
            } else if (indexPath.row == 0 && !mMyValidator) {
                return 250;
            } else {
                return 240;
            }
        } else {
            if(mHistories.count > 0) {
                return 80;
            } else {
                return 70;
            }
        }
        
    }
    
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if(section == 0) {
            return 0
        } else {
            return 30
        }
        
    }
    
    
    
    func onFetchValidatorInfo(_ validator: Validator) {
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS + "/" + validator.operator_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("onFetchValidatorInfo ", res)
                guard let validator = res as? NSDictionary else {
                    print("no validator Error!!")
                    self.onFetchFinished()
                    return
                }
                self.mValidator = Validator(validator as! [String : Any])
                
            case .failure(let error):
                print("onFetchValidatorInfo ", error)
            }
            print("onFetchValidatorInfo!!! ")
            self.onFetchFinished()
        }
    }
    
    func onFetchSignleBondingInfo(_ account: Account, _ validator: Validator) {
        let url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL + "/" + validator.operator_address
        print("onFetchSignleBondingInfo url ", url)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("onFetchSignleBondingInfo ", res)
                guard let rawData = res as? [String : Any], rawData["error"] == nil else {
                    print("no bondinginfo Error!!")
                    self.onFetchFinished()
                    return
                }
                let bondinginfo = BondingInfo(rawData)
                self.mBonding = Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970)
                if(self.mBonding != nil && WUtils.stringToDecimal(self.mBonding!.bonding_shares) != NSDecimalNumber.zero) {
                    self.mFetchCnt = self.mFetchCnt + 1
                    self.onFetchRewardInfo(account, validator)
                }
                
            case .failure(let error):
                print("onFetchSignleBondingInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSignleUnBondingInfo(_ account: Account, _ validator: Validator) {
        let url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL + "/" + validator.operator_address
         print("onFetchSignleUnBondingInfo url ", url)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("onFetchSignleUnBondingInfo ", res)
                guard let rawData = res as? [String : Any], rawData["error"] == nil else {
                    print("no unbondinginfo Error!!")
                    self.onFetchFinished()
                    return
                }
                let unbondinginfo = UnbondingInfo(rawData)
                for entry in unbondinginfo.entries {
                    self.mUnbondings.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, WUtils.nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
                }
                
            case .failure(let error):
                print("onFetchSignleUnBondingInfo ", error)
            }
            print("onFetchSignleUnBondingInfo!!! ")
            self.onFetchFinished()
        }
    }
    
    func onFetchRewardInfo(_ account: Account, _ validator: Validator) {
        let url = CSS_LCD_URL_REWARD_FROM_VAL + account.account_address + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("onFetchRewardInfo ", res)
                guard let rawRewards = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return;
                }
                let reward = Reward.init()
                reward.reward_v_address = validator.operator_address
                for rawReward in rawRewards {
                    reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.mRewards.append(reward)
                
            case .failure(let error):
                print("onFetchEachReward ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchHistory(_ account: Account, _ validator: Validator, _ from:String, _ size:String) {
        let query = "{\"from\": \"" + from + "\",\"size\": \"" + size + "\", \"query\": { \"bool\": { \"must\": [ { \"multi_match\": { \"query\": \"" + account.account_address + "\", \"fields\": [\"tx.value.msg.value.delegator_addr\", \"tx.value.msg.value.delegator_address\"] } } ], \"filter\": { \"bool\": { \"should\": [ { \"term\": { \"tx.value.msg.value.validator_addr\": \"" + validator.operator_address + "\" } }, { \"term\": { \"tx.value.msg.value.validator_dst_addr\": \"" + validator.operator_address + "\" } }, { \"term\": { \"tx.value.msg.value.validator_address\": \"" + validator.operator_address + "\" } } ]  } } }  }, \"sort\": [ { \"height\": { \"order\": \"desc\"  } } ] }"
        let data = query.data(using: .utf8)
        do {
            let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
            let request = Alamofire.request(CSS_ES_URL, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
            request.responseJSON { response in
                switch response.result {
                case .success(let res):
//                    print("onFetchHistory ", res)
                    guard let history = res as? [String : Any] else {
                        print("no history!!")
                        self.onFetchFinished()
                        return;
                    }
                    let rawHistory = History.init(history)
                    self.mHistories.removeAll()
                    self.mHistories = rawHistory.hits.hits
                case .failure(let error):
                    print("error ", error)
                }
                self.onFetchFinished()
            }
            
        } catch {
            print(error)
        }
        
    }
    
    
    
    
}
