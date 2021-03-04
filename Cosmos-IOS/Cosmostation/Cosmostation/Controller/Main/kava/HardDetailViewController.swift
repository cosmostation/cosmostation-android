//
//  HardDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class HardDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var harvestDetailTableView: UITableView!
    @IBOutlet weak var startDepositBtn: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var emptyConstraint: NSLayoutConstraint!
    @IBOutlet weak var owenConstraint: NSLayoutConstraint!
    var refresher: UIRefreshControl!
    
    var mDepositDenom: String?
    var distributionSchedule: KavaHavestParam.DistributionSchedule?
    var kavaPoolAccount: KavaHarvestAccount.HarvestAccountValue?
    var myHavestDeposit: KavaHavestDeposit.HavestDeposit?
    var myHavestReward: KavaHavestReward.HavestReward?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        
        self.harvestDetailTableView.delegate = self
        self.harvestDetailTableView.dataSource = self
        self.harvestDetailTableView.register(UINib(nibName: "HarvestDetailTopCell", bundle: nil), forCellReuseIdentifier: "HarvestDetailTopCell")
        self.harvestDetailTableView.register(UINib(nibName: "HarvestDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "HarvestDetailMyActionCell")
        self.harvestDetailTableView.register(UINib(nibName: "CdpDetailAssetsCell", bundle: nil), forCellReuseIdentifier: "CdpDetailAssetsCell")
        self.harvestDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.harvestDetailTableView.rowHeight = UITableView.automaticDimension
        self.harvestDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchHarvestData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.harvestDetailTableView.addSubview(refresher)
        
        self.loadingImg.onStartAnimation()
        self.onFetchHarvestData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_harvest_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_harvest_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (myHavestDeposit == nil && myHavestReward == nil) {
            return 2
        } else {
            return 3
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (myHavestDeposit == nil && myHavestReward == nil) {
            if (indexPath.row == 0) {
                return self.onBindTop(tableView, indexPath.row)
            } else {
                return self.onBindAsset(tableView, indexPath.row)
            }
            
        } else {
            if (indexPath.row == 0) {
                return self.onBindTop(tableView, indexPath.row)
            } else if (indexPath.row == 1) {
                return self.onBindAction(tableView, indexPath.row)
            } else {
                return self.onBindAsset(tableView, indexPath.row)
            }
        }
    }
    
    func onBindTop(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestDetailTopCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestDetailTopCell") as? HarvestDetailTopCell
        if (distributionSchedule != nil && kavaPoolAccount != nil) {
            let title = (distributionSchedule!.deposit_denom == KAVA_MAIN_DENOM) ? "kava" : distributionSchedule!.deposit_denom
            cell?.harvestTitle.text = title.uppercased() + " POOL"
            cell?.eventTime.text = WUtils.txTimetoShortString(input: distributionSchedule!.start) + " ~ " + WUtils.txTimetoShortString(input: distributionSchedule!.end)
            cell?.rewardForSecond.attributedText = WUtils.displayAmount2(distributionSchedule!.rewards_per_second.amount, cell!.rewardForSecond.font, 6, 6)
            
            var poolValue = NSDecimalNumber.zero
            WUtils.showCoinDp(mDepositDenom!, "0", cell!.totolDepositedDenom, cell!.totolDepositedAmount, chainType!)
            if let poolCoin = kavaPoolAccount?.coins.filter({ $0.denom == mDepositDenom}).first {
                WUtils.showCoinDp(poolCoin, cell!.totolDepositedDenom, cell!.totolDepositedAmount, chainType!)
                if (mDepositDenom == "usdx") {
                    poolValue = NSDecimalNumber.init(string: poolCoin.amount).multiplying(byPowerOf10: -6)
                    
                } else if (mDepositDenom == "bnb") {
                    if let bnbPrice = BaseData.instance.mKavaPrice["bnb:usd"] {
                        poolValue = NSDecimalNumber.init(string: poolCoin.amount).multiplying(byPowerOf10: -8).multiplying(by: NSDecimalNumber.init(string: bnbPrice.result.price)).rounding(accordingToBehavior: WUtils.handler2)
                    }
                    
                } else if (mDepositDenom == "ukava") {
                    poolValue = NSDecimalNumber.init(string: poolCoin.amount).multiplying(byPowerOf10: -6).multiplying(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler2Down)
                }
                cell?.totalDepositedValue.attributedText = WUtils.getDPRawDollor(poolValue.stringValue, 2, cell!.totalDepositedValue.font)
            }
            let url = KAVA_HARVEST_MARKET_IMG_URL + "lp" + distributionSchedule!.deposit_denom + ".png"
            cell?.harvestImg.af_setImage(withURL: URL(string: url)!)
        }
        return cell!
    }
    
    func onBindAction(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestDetailMyActionCell") as? HarvestDetailMyActionCell
        if (distributionSchedule != nil && kavaPoolAccount != nil) {
            if (mDepositDenom == KAVA_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, cell!.depositSymbol)
            } else {
                cell?.depositSymbol.textColor = .white
                cell?.depositSymbol.text = mDepositDenom!.uppercased()
            }
            
            WUtils.showCoinDp(mDepositDenom!, "0", cell!.depositDenom, cell!.depositAmount, chainType!)
            WUtils.showCoinDp(KAVA_HARD_DENOM, "0", cell!.dailyReardDenom, cell!.dailyRewardAmount, chainType!)
            WUtils.showCoinDp(KAVA_HARD_DENOM, "0", cell!.rewardDenom, cell!.rewardAmount, chainType!)
            
            if let depositedCoin = myHavestDeposit?.amount {
                WUtils.showCoinDp(depositedCoin, cell!.depositDenom, cell!.depositAmount, chainType!)
                if let poolCoin = kavaPoolAccount?.coins.filter({ $0.denom == mDepositDenom}).first {
                    let dailyReward =  NSDecimalNumber.init(string: depositedCoin.amount).multiplying(by: NSDecimalNumber.init(string: distributionSchedule!.rewards_per_second.amount)).multiplying(by: NSDecimalNumber.init(string: "86400")).dividing(by: NSDecimalNumber.init(string: poolCoin.amount), withBehavior: WUtils.handler0Down);
                    WUtils.showCoinDp(KAVA_HARD_DENOM, dailyReward.stringValue, cell!.dailyReardDenom, cell!.dailyRewardAmount, chainType!)
                }
            }
            
            if let rewardCoin = myHavestReward?.amount {
                WUtils.showCoinDp(rewardCoin, cell!.rewardDenom, cell!.rewardAmount, chainType!)
            }
            let title = (mDepositDenom == KAVA_MAIN_DENOM) ? "kava" : mDepositDenom
            cell?.depositBtn.setTitle(String(format: NSLocalizedString("str_deposit", comment: ""), title!.uppercased()), for: .normal)
            cell?.withdrawBtn.setTitle(String(format: NSLocalizedString("str_withdraw", comment: ""), title!.uppercased()), for: .normal)
            cell?.depositImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mDepositDenom! + ".png")!)
            cell?.rewardImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + "hard" + ".png")!)
            cell?.actionDepoist = {
                self.onClickDeposit()
            }
            cell?.actionWithdraw = {
                self.onClickWithdraw()
            }
            cell?.actionClaim = {
                self.onClickClaim()
            }
        }
        return cell!
        
    }
    
    func onBindAsset(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:CdpDetailAssetsCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailAssetsCell") as? CdpDetailAssetsCell
        cell?.collateralDenom.text = mDepositDenom!.uppercased()
        cell?.principalDenom.text = "HARD"
        cell?.principalDenom.textColor = COLOR_HARD
        cell?.collateralValue.isHidden = true
        cell?.principalValue.isHidden = true
        cell?.kavaValue.isHidden = true
        
        if (mDepositDenom == KAVA_HARD_DENOM || mDepositDenom == KAVA_MAIN_DENOM) {
            cell?.collateralDenom.isHidden = true
            cell?.collateralImg.isHidden = true
            cell?.collateralAmount.isHidden = true
        }
        
        let targetAvailable = WUtils.availableAmount(balances, mDepositDenom!)
        let hardAvailable = WUtils.availableAmount(balances, KAVA_HARD_DENOM)
        let kavaAvailable = WUtils.availableAmount(balances, KAVA_MAIN_DENOM)
//        print("targetAvailable ", targetAvailable)
//        print("hardAvailable ", hardAvailable)
//        print("kavaAvailable ", kavaAvailable)
        
        let dpDecimal = WUtils.getKavaCoinDecimal(mDepositDenom!)
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(targetAvailable.stringValue, cell!.principalAmount.font!, dpDecimal, dpDecimal)
        cell?.principalAmount.attributedText = WUtils.displayAmount2(hardAvailable.stringValue, cell!.principalAmount.font!, 6, 6)
        cell?.kavaAmount.attributedText = WUtils.displayAmount2(kavaAvailable.stringValue, cell!.kavaAmount.font!, 6, 6)
        cell?.collateralImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mDepositDenom! + ".png")!)
        cell?.principalImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + "hard" + ".png")!)
        return cell!
        
    }
    
    

    @IBAction func onStartDeposit(_ sender: UIButton) {
        self.onClickDeposit()
    }
    
    func onClickDeposit() {
        if (!onCommonCheck()) { return }
        if (WUtils.availableAmount(balances, mDepositDenom!).compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_no_available_to_deposit", comment: ""))
            return
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_DEPOSIT_HAVEST
        txVC.mHarvestDepositDenom = mDepositDenom
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickWithdraw() {
        if (!onCommonCheck()) { return }
        guard let depositedCoin = myHavestDeposit?.amount, NSDecimalNumber.init(string: depositedCoin.denom).compare(NSDecimalNumber.zero).rawValue <= 0 else {
            self.onShowToast(NSLocalizedString("error_no_deposited_asset", comment: ""))
            return
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_WITHDRAW_HAVEST
        txVC.mHarvestDepositDenom = mDepositDenom
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickClaim() {
        if (!onCommonCheck()) { return }
        guard let rewardCoin = myHavestReward?.amount, NSDecimalNumber.init(string: rewardCoin.denom).compare(NSDecimalNumber.zero).rawValue <= 0 else  {
            self.onShowToast(NSLocalizedString("error_no_harvest_reward_to_claim", comment: ""))
            return
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_CLAIM_HAVEST
        txVC.mHarvestDepositDenom = mDepositDenom
        txVC.mHarvestDepositType = "lp"
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onCommonCheck() -> Bool {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return false
        }
        if (!distributionSchedule!.active) {
            self.onShowToast(NSLocalizedString("error_circuit_breaker", comment: ""))
            return false
        }
        return true
    }

    
    var mFetchCnt = 0
    @objc func onFetchHarvestData() {
        if(self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 5
        onFetchPriceFeedParam()
        onFetchHavestParam()
        onFetchHavestPool()
        onFetchMyHavestDeposit(account!)
        onFetchMyHavestReward(account!)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            self.distributionSchedule = BaseData.instance.mHavestParam?.result.liquidity_provider_schedules.filter({ $0.deposit_denom == mDepositDenom}).first
            self.myHavestDeposit = BaseData.instance.mHavestDeposits.filter({ $0.amount.denom == mDepositDenom}).first
            self.myHavestReward = BaseData.instance.mHavestRewards.filter({ $0.deposit_denom == mDepositDenom && $0.type == "lp"}).first
            
            print("kavaPoolAccount ", self.kavaPoolAccount?.address)
            print("distributionSchedule ", distributionSchedule?.start)
            print("myHavestDeposit ", myHavestDeposit?.type)
            print("myHavestReward ", myHavestReward?.type)
            
            if (distributionSchedule == nil || kavaPoolAccount == nil) {
                print("error")
            }
            
            if (myHavestDeposit != nil || myHavestReward != nil) {
                startDepositBtn.isHidden = true
            } else {
                startDepositBtn.isHidden = false
            }
            
            self.harvestDetailTableView.reloadData()
            self.harvestDetailTableView.isHidden = false
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.refresher.endRefreshing()
        }
    }
    
    
    func onFetchPriceFeedParam() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PRICE_FEED_PARAM
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_PRICE_FEED_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchPriceFeedParam res ", res)
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let priceParam = KavaPriceFeedParam.init(responseData)
                BaseData.instance.mKavaPrice.removeAll()
                for market in priceParam.result.markets {
                    self.mFetchCnt = self.mFetchCnt + 1
                    self.onFetchPriceFeedPrice(market.market_id)
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchPriceFeedParam ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchPriceFeedPrice(_ market:String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PRICE_FEED_PRICE + market
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_PRICE_FEED_PRICE + market
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchKavaPrice ", res)
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let priceParam = KavaPriceFeedPrice.init(responseData)
                BaseData.instance.mKavaPrice[priceParam.result.market_id] = priceParam
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchKavaPrice ", market , " ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchHavestParam() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HAVEST_PARAM
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HAVEST_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchHavestParam ", res)
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let havestParam = KavaHavestParam.init(responseData)
                BaseData.instance.mHavestParam = havestParam
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchHavestParam ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchMyHavestDeposit(_ account:Account) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HAVEST_DEPOSIT
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HAVEST_DEPOSIT
        }
        let param = ["deposit_type":"lp", "owner":account.account_address]
        let request = Alamofire.request(url!, method: .get, parameters: param, encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchMyHavestDeposit ", res)
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                }
                
                let kavaHavestDeposits = KavaHavestDeposit.init(responseData)
                BaseData.instance.mHavestDeposits = kavaHavestDeposits.result
            
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyHavestDeposit ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchMyHavestReward(_ account:Account) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HAVEST_REWARD
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HAVEST_REWARD
        }
        let param = ["owner":account.account_address]
        let request = Alamofire.request(url!, method: .get, parameters: param, encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                }
                
                let kavaHavestReward = KavaHavestReward.init(responseData)
                BaseData.instance.mHavestRewards = kavaHavestReward.result
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyHavestReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchHavestPool() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HAVEST_ACCOUNT
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HAVEST_ACCOUNT
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                }
                
                let kavaHarvestAccount = KavaHarvestAccount.init(responseData)
                for harvestAccount in kavaHarvestAccount.result {
                    if (harvestAccount.value.name == "harvest") {
                        self.kavaPoolAccount = harvestAccount.value
                    }
                }
                print("kavaPoolAccount ", self.kavaPoolAccount?.address)
            
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyHavestReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    
}
