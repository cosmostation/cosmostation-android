//
//  HarvestListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class HardListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var hardTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var hardParam: HardParam?
    var interestRates: Array<HardInterestRate>?
    var totalDeposit: Array<Coin>?
    var totalBorrow: Array<Coin>?
    var myDeposit: Array<HardMyDeposit>?
    var myBorrow: Array<HardMyBorrow>?
    var incentiveRewards: IncentiveReward?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.hardTableView.delegate = self
        self.hardTableView.dataSource = self
        self.hardTableView.register(UINib(nibName: "HarvestListRewardCell", bundle: nil), forCellReuseIdentifier: "HarvestListRewardCell")
        self.hardTableView.register(UINib(nibName: "HardListMyStatusCell", bundle: nil), forCellReuseIdentifier: "HardListMyStatusCell")
        self.hardTableView.register(UINib(nibName: "HarvestListAllCell", bundle: nil), forCellReuseIdentifier: "HarvestListAllCell")
        
        self.hardTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.hardTableView.rowHeight = UITableView.automaticDimension
        self.hardTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchHardData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.hardTableView.addSubview(refresher)
        
        self.onFetchHardData()
    }
    
    var mFetchCnt = 0
    @objc func onFetchHardData() {
        if (self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 7
        self.interestRates?.removeAll()
        self.totalDeposit?.removeAll()
        self.totalBorrow?.removeAll()
        self.myDeposit?.removeAll()
        self.myBorrow?.removeAll()
        
        self.onFetchHardParam()
        self.onFetchHardInterestRate()
        self.onFetchHardTotalDeposit()
        self.onFetchHardTotalBorrow()
        self.onFetchHardMyDeposit(account!.account_address)
        self.onFetchHardMyBorrow(account!.account_address)
        self.onFetchIncentiveReward(account!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.hardTableView.reloadData()
            self.refresher.endRefreshing()
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            if let hardRewardAmount = incentiveRewards?.getHardPoolHardRewardAmount(), hardRewardAmount.compare(NSDecimalNumber.zero).rawValue > 0 {
                return 1
            }
            if let kavaRewardAmount = incentiveRewards?.getHardPoolKavaRewardAmount(), kavaRewardAmount.compare(NSDecimalNumber.zero).rawValue > 0  {
                return 1
            }
            return 0
            
        } else if (section == 1) {
            return 1
            
        } else {
            return hardParam?.money_markets?.count ?? 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            return onBindIncentiveCell(tableView, indexPath.row)

        } else if (indexPath.section == 1) {
            return onBindStatusCell(tableView, indexPath.row)

        } else {
            return onBindHardPoolCell(tableView, indexPath.row)
        }

    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 2) {
            let hardDetailVC = HardDetailViewController(nibName: "HardDetailViewController", bundle: nil)
            hardDetailVC.mHardMoneyMarketDenom = hardParam!.money_markets![indexPath.row].denom
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(hardDetailVC, animated: true)
        }
    }
    
    func onBindIncentiveCell(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestListRewardCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestListRewardCell") as? HarvestListRewardCell
        var kavaIncentive = NSDecimalNumber.zero
        if let incentiveSum = incentiveRewards?.getHardPoolKavaRewardAmount() {
            kavaIncentive = incentiveSum
        }
        var hardIncentive = NSDecimalNumber.zero
        if let incentiveSum = incentiveRewards?.getHardPoolHardRewardAmount() {
            hardIncentive = incentiveSum
        }
        cell?.kavaIncentiveAmount.attributedText = WUtils.displayAmount2(kavaIncentive.stringValue, cell!.kavaIncentiveAmount.font, 6, 6)
        cell?.hardIncentiveAmount.attributedText = WUtils.displayAmount2(hardIncentive.stringValue, cell!.hardIncentiveAmount.font, 6, 6)
        cell?.actionClaim = {
            self.onHardPoolIncentiveClaim()
        }
        return cell!
    }
    
    func onBindStatusCell(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HardListMyStatusCell? = tableView.dequeueReusableCell(withIdentifier:"HardListMyStatusCell") as? HardListMyStatusCell
        
        var totalDepositValue = NSDecimalNumber.zero
        var totalLTVValue = NSDecimalNumber.zero
        if let myDposits = self.myDeposit, let coins = myDposits[0].amount {
            for coin in coins {
                let decimal         = WUtils.getKavaCoinDecimal(coin.denom)
                let LTV             = self.hardParam!.getLTV(coin.denom)
                var depositValue    = NSDecimalNumber.zero
                var ltvValue        = NSDecimalNumber.zero
                if (coin.denom == "usdx" || coin.denom == "busd") {
                    depositValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -decimal, withBehavior: WUtils.handler12Down)
                } else {
                    if let price = BaseData.instance.mKavaPrice[self.hardParam!.getSpotMarketId(coin.denom)!] {
                        depositValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -decimal).multiplying(by: NSDecimalNumber.init(string: price.result.price), withBehavior: WUtils.handler12Down)
                    }
                }
                ltvValue = depositValue.multiplying(by: LTV)
                totalLTVValue = totalLTVValue.adding(ltvValue)
                totalDepositValue = totalDepositValue.adding(depositValue)
            }
        }
        cell?.totalDepositedValue.attributedText = WUtils.getDPRawDollor(totalDepositValue.stringValue, 2, cell!.totalDepositedValue.font)
        cell?.maxBorrowableValue.attributedText = WUtils.getDPRawDollor(totalLTVValue.stringValue, 2, cell!.maxBorrowableValue.font)
        
        var totalBorrowedValue = NSDecimalNumber.zero
        if let myBorrows = self.myBorrow, let coins = myBorrows[0].amount {
            for coin in coins {
                let decimal         = WUtils.getKavaCoinDecimal(coin.denom)
                var borrowValue    = NSDecimalNumber.zero
                if (coin.denom == "usdx" || coin.denom == "busd") {
                    borrowValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -decimal, withBehavior: WUtils.handler12Down)
                } else {
                    if let price = BaseData.instance.mKavaPrice[self.hardParam!.getSpotMarketId(coin.denom)!] {
                        borrowValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -decimal).multiplying(by: NSDecimalNumber.init(string: price.result.price), withBehavior: WUtils.handler12Down)
                    }
                }
                totalBorrowedValue = totalBorrowedValue.adding(borrowValue)
            }
        }
        cell?.totalBorrowedValue.attributedText = WUtils.getDPRawDollor(totalBorrowedValue.stringValue, 2, cell!.totalBorrowedValue.font)
        let remainBorrowable = (totalLTVValue.subtracting(totalBorrowedValue).compare(NSDecimalNumber.zero).rawValue > 0) ? totalLTVValue.subtracting(totalBorrowedValue) : NSDecimalNumber.zero
        cell?.remainingBorrowableValue.attributedText = WUtils.getDPRawDollor(remainBorrowable.stringValue, 2, cell!.remainingBorrowableValue.font)
        return cell!
    }
    
    func onBindHardPoolCell(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestListAllCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestListAllCell") as? HarvestListAllCell
        guard let hardMoneyMarket = hardParam?.money_markets?[position] else {
            return cell!
        }
        let decimal = WUtils.getKavaCoinDecimal(hardMoneyMarket.denom!);
        let url = KAVA_HARVEST_MARKET_IMG_URL + "lp" + hardMoneyMarket.denom! + ".png"
        let title = (hardMoneyMarket.denom! == KAVA_MAIN_DENOM) ? "kava" : hardMoneyMarket.denom!
        cell?.harvestImg.af_setImage(withURL: URL(string: url)!)
        cell?.harvestTitle.text = title.uppercased() + " POOL"
        
        //Display API
        var supplyApy = NSDecimalNumber.zero
        var borrowApy = NSDecimalNumber.zero
        if let rates = self.interestRates {
            for rate in rates {
                if (rate.denom == hardMoneyMarket.denom) {
                    supplyApy = NSDecimalNumber.init(string: rate.supply_interest_rate)
                    borrowApy = NSDecimalNumber.init(string: rate.borrow_interest_rate)
                }
            }
        }
        cell?.supplyAPILabel.attributedText = WUtils.displayPercent(supplyApy.multiplying(byPowerOf10: 2), cell!.supplyAPILabel.font)
        cell?.borrowAPILabel.attributedText = WUtils.displayPercent(borrowApy.multiplying(byPowerOf10: 2), cell!.borrowAPILabel.font)
        
        //Display supplied amounts
        var myDepositAmount = NSDecimalNumber.zero
        var myDepositValue = NSDecimalNumber.zero
        if let deposits = self.myDeposit, let coins = deposits[0].amount {
            for coin in coins {
                if (coin.denom == hardMoneyMarket.denom) {
                    myDepositAmount = NSDecimalNumber.init(string: coin.amount)
                }
            }
        }
        if let price = BaseData.instance.mKavaPrice[self.hardParam!.getSpotMarketId(hardMoneyMarket.denom!)!] {
            myDepositValue = myDepositAmount.multiplying(byPowerOf10: -decimal).multiplying(by: NSDecimalNumber.init(string: price.result.price), withBehavior: WUtils.handler12Down)
        }
        WUtils.showCoinDp(hardMoneyMarket.denom!, myDepositAmount.stringValue, cell!.mySuppliedDenom, cell!.mySuppliedAmount, chainType!)
        cell?.mySuppliedValue.attributedText = WUtils.getDPRawDollor(myDepositValue.stringValue, 2, cell!.mySuppliedValue.font)
        
        
        //Display borrowed amounts
        var myBorrowedAmount = NSDecimalNumber.zero
        var myBorrowedValue = NSDecimalNumber.zero
        if let borrows = self.myBorrow, let coins = borrows[0].amount {
            for coin in coins {
                if (coin.denom == hardMoneyMarket.denom) {
                    myBorrowedAmount = NSDecimalNumber.init(string: coin.amount)
                }
            }
        }
        if let price = BaseData.instance.mKavaPrice[self.hardParam!.getSpotMarketId(hardMoneyMarket.denom!)!] {
            myBorrowedValue = myBorrowedAmount.multiplying(byPowerOf10: -decimal).multiplying(by: NSDecimalNumber.init(string: price.result.price), withBehavior: WUtils.handler12Down)
        }
        WUtils.showCoinDp(hardMoneyMarket.denom!, myBorrowedAmount.stringValue, cell!.myBorrowedDenom, cell!.myBorrowedAmount, chainType!)
        cell?.myBorrowedValue.attributedText = WUtils.getDPRawDollor(myBorrowedValue.stringValue, 2, cell!.myBorrowedValue.font)
        return cell!
    }
    
    func onHardPoolIncentiveClaim() {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    func onFetchHardParam() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_PARAM
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardParam = KavaHardParam.init(responseData)
                    self.hardParam = kavaHardParam.result
                    BaseData.instance.mHardParam = kavaHardParam.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardInterestRate ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardInterestRate() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_INTERESTRATE
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_INTERESTRATE
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardInterestRate = KavaHardInterestRate.init(responseData)
                    self.interestRates = kavaHardInterestRate.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardInterestRate ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardTotalDeposit() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_TOTAL_DEPOIST
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_TOTAL_DEPOIST
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardTotalDeposit = KavaHardTotalDeposit.init(responseData)
                    self.totalDeposit = kavaHardTotalDeposit.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardTotalDeposit ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardTotalBorrow() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_TOTAL_BORROW
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_TOTAL_BORROW
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardTotalBorrow = KavaHardTotalBorrow.init(responseData)
                    self.totalBorrow = kavaHardTotalBorrow.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardTotalBorrow ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardMyDeposit(_ address: String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_MY_DEPOSIT
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_MY_DEPOSIT
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardMyDeposit = KavaHardMyDeposit.init(responseData)
                    self.myDeposit = kavaHardMyDeposit.result
                    BaseData.instance.mMyHardDeposit = kavaHardMyDeposit.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardMyDeposit ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardMyBorrow(_ address: String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_MY_BORROW
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_MY_BORROW
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardMyBorrow = KavaHardMyBorrow.init(responseData)
                    self.myBorrow = kavaHardMyBorrow.result
                    BaseData.instance.mMyHardBorrow = kavaHardMyBorrow.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardMyBorrow ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchIncentiveReward(_ address: String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_INCENTIVE_REWARD
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_INCENTIVE_REWARD
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaIncentiveReward = KavaIncentiveReward.init(responseData)
                    self.incentiveRewards = kavaIncentiveReward.result
                    BaseData.instance.mIncentiveRewards = kavaIncentiveReward.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchIncentiveReward ", error) }
                }
            self.onFetchFinished()
        }
    }
}
