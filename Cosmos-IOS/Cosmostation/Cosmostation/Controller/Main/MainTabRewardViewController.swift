//
//  MainTabRewardViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MainTabRewardViewController: BaseViewController {
    
    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    @IBOutlet weak var titleAccountBtn: UIButton!
    @IBOutlet weak var validatorSegment: UISegmentedControl!
    @IBOutlet weak var myValidatorView: UIView!
    @IBOutlet weak var allValidatorView: UIView!
    
    
    var mAllValidators = Array<Validator>()
    var mMyValidators = Array<Validator>()
    var reqCnt = 0;
    
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            myValidatorView.alpha = 1
            allValidatorView.alpha = 0
        } else {
            myValidatorView.alpha = 0
            allValidatorView.alpha = 1
        }
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        myValidatorView.alpha = 1
        allValidatorView.alpha = 0

        // Do any additional setup after loading the view.
        reqCnt = 4;
        onFetchValidatorsInfo();
        let account = Account.init(isNew: false);
        account.account_address = "cosmos1pzllggpn22094j3mwq79u4ql63cwac6enqkjck";
        onFetchAccountInfo(account)
        onFetchBondingInfo(account)
        onFetchUnbondingInfo(account)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        if(segue.identifier == "allValidator"){
//            print("allValidator")
//        }
//    }
    
    func onUpdateViews() {
        self.reqCnt = self.reqCnt - 1
        if(self.reqCnt > 0) { return; }
        print("onUpdateViews")
        
        let myValidatorVC = self.children[0] as! MyValidatorViewController
        myValidatorVC.rewardViewUpdate()
        
        let allValidatorVC = self.children[1] as! AllValidatorViewController
        allValidatorVC.rewardViewUpdate()
        
//        if(validatorSegment.selectedSegmentIndex == 0) {
//
//        } else {
//
//        }
    }
    
    
    
    
    
    
    
    func onFetchValidatorsInfo() {
        print("onFetchValidatorsInfo")
        let request = Alamofire.request(CSS_LCD_URL_VALIDATORS, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print(res)
                let validators = res as! Array<NSDictionary>
                self.mAllValidators.removeAll()
                for validator in validators {
                    self.mAllValidators.append(Validator(validator as! [String : Any]))
                }
                print("size : ", self.mAllValidators.count)
                
            case .failure(let error):
//                self.refresher.endRefreshing()
                print(error)
            }
            self.onUpdateViews()
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        print("onFetchAccountInfo")
        let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print(res)
                guard let info = res as? [String : Any] else {
                    print("no account!!")
                    BaseData.instance.deleteBalance(account: account)
                    self.onUpdateViews()
                    return
                }
                let accountInfo = AccountInfo.init(info)
                //TODO eopn after Key
//                BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
//                BaseData.instance.updateBalances(WUtils.getBalancesWithAccountInfo(account, accountInfo))
//                print("accountInfo", accountInfo)
                
//                print("account_number : ", accountInfo.value.account_number, "  ", accountInfo.value.coins[0].denom, " " , accountInfo.value.coins[0].amount)
//                print(res)
                
            case .failure(let error):
                print(error)
            }
            self.onUpdateViews()
        }
        
    }
    
    func onFetchBondingInfo(_ account: Account) {
        print("onFetchBondingInfo")
        let url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.validate()
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print(res)
                guard let bondinginfos = res as? Array<NSDictionary> else {
                    print("no bonding!!")
                    BaseData.instance.deleteBonding(account: account)
                    self.onUpdateViews()
                    return;
                }
                print("bondinginfos", bondinginfos)
                //TODO open after Key
//                BaseData.instance.updateBondings(WUtils.getBondingwithBondingInfo(account, bondinginfos))
                
            case .failure(let error):
                print(error)
            }
            self.onUpdateViews()
        }
    }
    
    func onFetchUnbondingInfo(_ account: Account) {
        print("onFetchUnbondingInfo")
        let url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("res", res)
                guard let unbondinginfos = res as? Array<NSDictionary> else {
                    print("no unbonding!!")
                    BaseData.instance.deleteUnbonding(account: account)
                    self.onUpdateViews()
                    return
                }
                print("unbondinginfos ", unbondinginfos)
                //TODO open after Key
//                BaseData.instance.updateUnbondings(WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos))
                
            case .failure(let error):
                print(error)
            }
            self.onUpdateViews()
        }
    }
    
//    func onFetchRewardInfo() {
//
//    }

}
