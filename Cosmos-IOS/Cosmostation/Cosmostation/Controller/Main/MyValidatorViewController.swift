//
//  MyValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class MyValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    @IBOutlet weak var myValidatorTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mMyValidators = Array<Validator>()
    var mMyRewards = Array<Reward>()
    var mRewardFetchCnt: Int!
    var mAccount: Account?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        
        self.myValidatorTableView.delegate = self
        self.myValidatorTableView.dataSource = self
        self.myValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myValidatorTableView.register(UINib(nibName: "MyValidatorCell", bundle: nil), forCellReuseIdentifier: "MyValidatorCell")
        self.myValidatorTableView.register(UINib(nibName: "ClaimRewardAllCell", bundle: nil), forCellReuseIdentifier: "ClaimRewardAllCell")
        self.myValidatorTableView.register(UINib(nibName: "PromotionCell", bundle: nil), forCellReuseIdentifier: "PromotionCell")
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(handleRefresh), for: .valueChanged)
        refresher.tintColor = UIColor.white
        self.myValidatorTableView.addSubview(refresher)
    }
    
//    override func viewDidAppear(_ animated: Bool) {
//        super.viewDidAppear(animated)
//        let rewardTabVC = self.parent as! MainTabRewardViewController
//        print("MyValidatorViewController ", rewardTabVC.mAllValidators.count)
//        
//    }
    
    @objc func handleRefresh() {
        print("handleRefresh")
    }
    
    func rewardViewUpdate() {
        let rewardTabVC = self.parent as! MainTabRewardViewController
        
//        self.mMyRewards.removeAll()
        
        self.mMyValidators.removeAll()
        self.mMyValidators = rewardTabVC.mMyValidators
        print("AllValidatorViewController mMyValidators ", mMyValidators.count)
        
        self.mMyRewards.removeAll()
        self.mRewardFetchCnt = mMyValidators.count
        for validator in mMyValidators {
            onFetchEachReward(mAccount!, validator)
        }
        self.myValidatorTableView.reloadData()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (self.mMyValidators.count < 1) {
            return 1;
        } else if (self.mMyValidators.count == 1) {
            return 1;
        } else {
            return self.mMyValidators.count  + 1;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (self.mMyValidators.count < 1) {
            let cell:PromotionCell? = tableView.dequeueReusableCell(withIdentifier:"PromotionCell") as? PromotionCell
            return cell!
            
        } else if (self.mMyValidators.count == 1) {
            let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
            let validator = mMyValidators[indexPath.row]
            onSetValidatorItem(cell!, validator)
            return cell!
            
        } else {
            if (indexPath.row == self.mMyValidators.count) {
                let cell:ClaimRewardAllCell? = tableView.dequeueReusableCell(withIdentifier:"ClaimRewardAllCell") as? ClaimRewardAllCell
                return cell!
            } else {
                let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                let validator = mMyValidators[indexPath.row]
                onSetValidatorItem(cell!, validator)
                return cell!
            }
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if (self.mMyValidators.count < 1) {
            return 168;
        } else {
            return 80;
        }
    }
    
    
    func onSetValidatorItem(_ cell: MyValidatorCell, _ validator: Validator) {
        cell.monikerLabel.text = validator.description.moniker
        
        if(validator.jailed) { cell.revokedImg.isHidden = false
        } else { cell.revokedImg.isHidden = true }
        
        cell.freeEventImg.isHidden = true
        
        let bonding = BaseData.instance.selectBondingWithValAdd(mAccount!.account_id, validator.operator_address)
        
        if(bonding != nil) { cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmout(bonding!.bonding_shares, cell.myDelegatedAmoutLabel.font, 6)
        } else { cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmout("0", cell.myDelegatedAmoutLabel.font, 6) }
        
        cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.rate, font: cell.commissionLabel.font)
        
        if (validator.description.identity != "") {
            let parameters: Parameters = ["fields": "pictures", "key_suffix": validator.description.identity]
            
            let request = Alamofire.request(KEY_BASE_URL_USER_INFO,
                                            method: .get,
                                            parameters: parameters,
                                            encoding: URLEncoding.default,
                                            headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let keybaseInfo = res as? NSDictionary,
                        let thems = keybaseInfo.value(forKey: "them") as? Array<NSDictionary>,
                        let url = thems[0].value(forKeyPath: "pictures.primary.url") as? String else {
                        return
                    }
                    
                    Alamofire.request(url, method: .get).responseImage { response  in
                        guard let image = response.result.value else {
                            return
                        }
                        cell.validatorImg.image = image
                    }
                
                case .failure(let error):
                    print("error : ", error, " ", request.request)
                }
            }
            
        }
    }
    
    func onSetRewardAllItem() {
        
    }
    
    
    
    func onFetchEachReward(_ account: Account, _ validator:Validator) {
        let url = CSS_LCD_URL_REWARD_FROM_VAL + account.account_address + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validator.operator_address
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawRewards = res as? Array<NSDictionary> else {
                    return;
                }
                let reward = Reward.init()
                reward.reward_v_address = validator.operator_address
                for rawReward in rawRewards {
                    reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.mMyRewards.append(reward)
                
            case .failure(let error):
                print("onFetchEachReward error")
            }
            
            self.mRewardFetchCnt = self.mRewardFetchCnt - 1
            if(self.mRewardFetchCnt <= 0) {
                print("OnFetch each reward Fin")
            }
        }
    }
}
