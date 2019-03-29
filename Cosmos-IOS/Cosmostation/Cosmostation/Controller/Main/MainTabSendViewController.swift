//
//  MainTabSendViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MainTabSendViewController: BaseViewController {
    
    @IBOutlet weak var mainScrollView: UIScrollView!
    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    @IBOutlet weak var titleAccountBtn: UIButton!
    
    @IBOutlet weak var keyCard: CardView!
    @IBOutlet weak var keyTypeImg: UIImageView!
    @IBOutlet weak var keyAddressLabel: UILabel!
    @IBOutlet weak var keyQrcodeBtn: UIButton!
    
    @IBOutlet weak var AtomCard: CardView!
    @IBOutlet weak var atomPriceLabel: UILabel!
    @IBOutlet weak var atomTotalLabel: UILabel!
    @IBOutlet weak var atomAvailableAmount: UILabel!
    @IBOutlet weak var atomDelegatedAmount: UILabel!
    @IBOutlet weak var atomUnbondingAmount: UILabel!
    @IBOutlet weak var atomRewardAmount: UILabel!
    
    
    @IBOutlet weak var photonCard: CardView!
    @IBOutlet weak var photonPriceLabel: UILabel!
    @IBOutlet weak var photonTotalLabel: UILabel!
    @IBOutlet weak var photonAvailableAmount: UILabel!
    @IBOutlet weak var photonRewardAmount: UILabel!
    @IBOutlet weak var ConstraintPhoton: NSLayoutConstraint!
    @IBOutlet weak var ConstraintAtom: NSLayoutConstraint!
    
    @IBOutlet weak var rewardCard: CardView!
    @IBOutlet weak var rewardImg: UIImageView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    var mBalances = Array<Balance>()
    var mBondings = Array<Bonding>()
    var mUnbondings = Array<Unbonding>()
    var mRewards = Array<Coin>()
    var mAtomTic: NSDictionary!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("password ",          BaseData.instance.hasPassword())
        print("RecentAccountId ",   BaseData.instance.getRecentAccountId())
        
        rewardImg.image = rewardImg.image?.withRenderingMode(.alwaysTemplate)
        rewardImg.tintColor = UIColor.white
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        keyAddressLabel.text = mainTabVC.mAccount.account_address
        
        if (mainTabVC.mAccount.account_nick_name == "") { titleWalletName.text = "Wallet" + String(mainTabVC.mAccount.account_id)
        } else { titleWalletName.text = mainTabVC.mAccount.account_nick_name }
        
        if(mainTabVC.mAccount.account_has_private) { keyTypeImg.image = UIImage(named: "key_on")
        } else { keyTypeImg.image = UIImage(named: "key_off") }
        
        if(mainTabVC.mAccount.account_base_chain == SUPPORT_CHAIN_COSMOS_MAIN) {
            photonCard.isHidden = true
            ConstraintAtom.priority = UILayoutPriority(rawValue: 1000)
            ConstraintPhoton.priority = UILayoutPriority(rawValue: 500)
            titleChainName.text = "(Cosmos Hub)"
        } else {
            titleChainName.text = ""
        }
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(handleRefresh), for: .valueChanged)
        refresher.tintColor = UIColor.white
        mainScrollView.addSubview(refresher)
        
        self.onUpdateViews()
        self.onFetchAll()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    
    func onUpdateViews() {
        print("onUpdateViews")
        mBalances   = BaseData.instance.selectBalanceById(accountId: mainTabVC.mAccount.account_id)
        mBondings   = BaseData.instance.selectBondingById(accountId: mainTabVC.mAccount.account_id)
        mUnbondings = BaseData.instance.selectUnbondingById(accountId: mainTabVC.mAccount.account_id)
        
        
//        print("mBalances count ", mBalances.count)
//        print("mBondings ", mBondings.count)
//        print("mUnbondings ", mUnbondings.count)
//        print("mRewards ", mRewards.count)
        
        
        print("mBalances!!! ", mBalances[0].balance_amount)
        
        if(mBalances.count > 0) {
            atomAvailableAmount.attributedText = WUtils.displayAmout(mBalances[0].balance_amount, atomDelegatedAmount.font, 6)
        } else {
            atomAvailableAmount.attributedText = WUtils.displayAmout("0", atomDelegatedAmount.font, 6)
        }

        if(mBondings.count > 0) {
            var sum = NSDecimalNumber.zero
            for bonding in mBondings {
                sum = sum.adding(WUtils.stringToDecimal(bonding.bonding_shares))
            }
            atomDelegatedAmount.attributedText = WUtils.displayAmout(sum.stringValue, atomDelegatedAmount.font, 6)
            
        } else {
            atomDelegatedAmount.attributedText = WUtils.displayAmout("0", atomDelegatedAmount.font, 6)
        }
        
        
        if(mUnbondings.count > 0) {
            var sum = NSDecimalNumber.zero
            for unbonding in mUnbondings {
                sum = sum.adding(WUtils.stringToDecimal(unbonding.unbonding_balance))
            }
            atomUnbondingAmount.attributedText = WUtils.displayAmout(sum.stringValue, atomUnbondingAmount.font, 6)
            
        } else {
            atomUnbondingAmount.attributedText = WUtils.displayAmout("0", atomUnbondingAmount.font, 6)
        }
        
        if(mRewards.count > 0) { atomRewardAmount.attributedText = WUtils.displayAmout(mRewards[0].amount, atomRewardAmount.font, 6)
        } else { atomRewardAmount.attributedText = WUtils.displayAmout("0", atomRewardAmount.font, 6) }
        
        var totalSum = NSDecimalNumber.zero
        totalSum = totalSum.adding(WUtils.stringToDecimal(atomAvailableAmount.text!.replacingOccurrences(of: ",", with: "")))
        .adding(WUtils.stringToDecimal(atomDelegatedAmount.text!.replacingOccurrences(of: ",", with: "")))
        .adding(WUtils.stringToDecimal(atomUnbondingAmount.text!.replacingOccurrences(of: ",", with: "")))
        .adding(WUtils.stringToDecimal(atomRewardAmount.text!.replacingOccurrences(of: ",", with: "")))
        totalSum = totalSum.multiplying(by: 1000000)
        atomTotalLabel.attributedText = WUtils.displayAmout(totalSum.stringValue, atomTotalLabel.font, 6)
        
        self.refresher.endRefreshing()
    }
    
    func onUpdatePriceViews() {
        print("onUpdatePriceViews")
//        print("mAtomTic ", mAtomTic)
//        print("mAtomTic ", mAtomTic.value(forKeyPath: "data.quotes.USD.price"))
        var priceValue = NSDecimalNumber.zero
        if let price = mAtomTic.value(forKeyPath: "data.quotes.USD.price") {
            priceValue = NSDecimalNumber(string: String(describing: price))
        }

        let dpPrice = priceValue.multiplying(by: WUtils.stringToDecimal(atomTotalLabel.text!.replacingOccurrences(of: ",", with: "")), withBehavior: WUtils.handler2)

        atomPriceLabel.attributedText = WUtils.displayUSD(dpPrice, font: atomPriceLabel.font)
    }
    
    
    
    
    func onFetchAll() {
        onFetchAccountInfo(mainTabVC.mAccount)
        onFetchAtomTic()
    }
    
    @objc func handleRefresh() {
        self.onFetchAll()
    }
    
    
    func onFetchAccountInfo(_ account: Account) {
//        print("onFetchAccountInfo")
        let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account.account_address,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchAccountInfo ", res)
                guard let info = res as? [String : Any] else {
                    BaseData.instance.deleteBalance(account: account)
                    self.onFetchBondingInfo(account)
                    return
                }
                let accountInfo = AccountInfo.init(info)
                BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(WUtils.getBalancesWithAccountInfo(account, accountInfo))
                
            case .failure(let error):
                print(error)
            }
            self.onFetchBondingInfo(account)
        }
    }
    
    func onFetchBondingInfo(_ account: Account) {
//        print("onFetchBondingInfo")
        let url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.validate()
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchBondingInfo ", res)
                guard let bondinginfos = res as? Array<NSDictionary> else {
                    BaseData.instance.deleteBonding(account: account)
                    self.onFetchUnbondingInfo(account)
                    return;
                }
                BaseData.instance.updateBondings(WUtils.getBondingwithBondingInfo(account, bondinginfos))
                
            case .failure(let error):
                print(error)
            }
            self.onFetchUnbondingInfo(account)
        }
    }
    
    func onFetchUnbondingInfo(_ account: Account) {
//        print("onFetchUnbondingInfo")
        let url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchUnbondingInfo ", res)
                guard let unbondinginfos = res as? Array<NSDictionary> else {
                    BaseData.instance.deleteUnbonding(account: account)
                    self.onFetchAllRewards(account)
                    return
                }
                BaseData.instance.updateUnbondings(WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos))
                
            case .failure(let error):
                print("onFetchUnbondingInfo error", error)
            }
            self.onFetchAllRewards(account)
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
                    self.onUpdateViews()
                    return;
                }
                self.mRewards.removeAll()
                for reward in rewards {
                    self.mRewards.append(Coin(reward as! [String : Any]))
                }
                
            case .failure(let error):
                print("onFetchAllRewards error", error)
            }
            self.onUpdateViews()
        }
    }
    
    
    func onFetchAtomTic() {
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
            self.onUpdatePriceViews()
        }
    }
}
