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
    @IBOutlet weak var loadingImg: LoadingImageView!
    var refresher: UIRefreshControl!
    
    var mHardMoneyMarketDenom: String?
    var hardParam: HardParam?
    var interestRates: Array<HardInterestRate>?
    var moduleCoins: Array<Coin>?
    var reserveCoins: Array<Coin>?
    var totalDeposit: Array<Coin>?
    var totalBorrow: Array<Coin>?
    var myDeposit: Array<HardMyDeposit>?
    var myBorrow: Array<HardMyBorrow>?
    var incentiveRewards: IncentiveReward?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        
        self.hardParam = BaseData.instance.mHardParam
        self.incentiveRewards = BaseData.instance.mIncentiveRewards
        
        self.harvestDetailTableView.delegate = self
        self.harvestDetailTableView.dataSource = self
        self.harvestDetailTableView.register(UINib(nibName: "HarvestDetailTopCell", bundle: nil), forCellReuseIdentifier: "HarvestDetailTopCell")
        self.harvestDetailTableView.register(UINib(nibName: "HarvestDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "HarvestDetailMyActionCell")
        self.harvestDetailTableView.register(UINib(nibName: "HardDetailAssetsCell", bundle: nil), forCellReuseIdentifier: "HardDetailAssetsCell")
        self.harvestDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.harvestDetailTableView.rowHeight = UITableView.automaticDimension
        self.harvestDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchHardInfo), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.harvestDetailTableView.addSubview(refresher)
        
        self.loadingImg.onStartAnimation()
        self.onFetchHardInfo()
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
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            return self.onBindTop(tableView, indexPath.row)
        } else if (indexPath.row == 1) {
            return self.onBindAction(tableView, indexPath.row)
        } else {
            return self.onBindAsset(tableView, indexPath.row)
        }
    }
    
    func onBindTop(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestDetailTopCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestDetailTopCell") as? HarvestDetailTopCell
        cell?.harvestImg.af_setImage(withURL: URL(string: KAVA_HARVEST_MARKET_IMG_URL + "lp" + mHardMoneyMarketDenom! + ".png")!)
        
        let marketTitle = mHardMoneyMarketDenom == KAVA_MAIN_DENOM ? "kava" : mHardMoneyMarketDenom
        cell?.harvestTitle.text = marketTitle!.uppercased() + " POOL"
        
        var supplyApy = NSDecimalNumber.zero
        var borrowApy = NSDecimalNumber.zero
        if let interestRate = interestRates?.filter({ $0.denom == mHardMoneyMarketDenom}).first {
            supplyApy = NSDecimalNumber.init(string: interestRate.supply_interest_rate)
            borrowApy = NSDecimalNumber.init(string: interestRate.borrow_interest_rate)
        }
        cell?.supplyAPILabel.attributedText = WUtils.displayPercent(supplyApy.multiplying(byPowerOf10: 2), cell!.supplyAPILabel.font)
        cell?.borrowAPILabel.attributedText = WUtils.displayPercent(borrowApy.multiplying(byPowerOf10: 2), cell!.borrowAPILabel.font)
        
        WUtils.showCoinDp(mHardMoneyMarketDenom!, "0", cell!.systemSuppliedDenom, cell!.systemSuppliedAmount, chainType!)
        WUtils.showCoinDp(mHardMoneyMarketDenom!, "0", cell!.systemBorrowedDenom, cell!.systemBorrowedAmount, chainType!)
        WUtils.showCoinDp(mHardMoneyMarketDenom!, "0", cell!.systemRemainBorrowableDenom, cell!.systemRemainBorrowableAmount, chainType!)
        cell?.systemSuppliedValue.attributedText = WUtils.getDPRawDollor("0", 2, cell!.systemSuppliedValue.font)
        cell?.systemBorrowedValue.attributedText = WUtils.getDPRawDollor("0", 2, cell!.systemBorrowedValue.font)
        cell?.systemRemainBorrowableValue.attributedText = WUtils.getDPRawDollor("0", 2, cell!.systemRemainBorrowableValue.font)

        let dpDecimal = WUtils.getKavaCoinDecimal(mHardMoneyMarketDenom!)
        var targetPrice = NSDecimalNumber.one;
        if (mHardMoneyMarketDenom != "usdx") {
            let targetPriceTic = BaseData.instance.mKavaPrice[hardParam!.getHardMoneyMarket(mHardMoneyMarketDenom!)!.spot_market_id! + ":30"]
            if (targetPriceTic != nil) {
                targetPrice = NSDecimalNumber.init(string: targetPriceTic?.result.price)
            } else {
                targetPrice = NSDecimalNumber.zero
            }
        }
        
        // display system total supplied
        if let totalDepositCoin = totalDeposit?.filter({ $0.denom == mHardMoneyMarketDenom}).first {
            WUtils.showCoinDp(mHardMoneyMarketDenom!, totalDepositCoin.amount, cell!.systemSuppliedDenom, cell!.systemSuppliedAmount, chainType!)
            let supplyValue = NSDecimalNumber.init(string: totalDepositCoin.amount).multiplying(byPowerOf10: -dpDecimal).multiplying(by: targetPrice, withBehavior: WUtils.handler2Down)
            cell?.systemSuppliedValue.attributedText = WUtils.getDPRawDollor(supplyValue.stringValue, 2, cell!.systemSuppliedValue.font)
        }

        // display system total borrowed
        if let totalBorrowedCoin = totalBorrow?.filter({ $0.denom == mHardMoneyMarketDenom}).first {
            WUtils.showCoinDp(mHardMoneyMarketDenom!, totalBorrowedCoin.amount, cell!.systemBorrowedDenom, cell!.systemBorrowedAmount, chainType!)
            let BorrowedValue = NSDecimalNumber.init(string: totalBorrowedCoin.amount).multiplying(byPowerOf10: -dpDecimal).multiplying(by: targetPrice, withBehavior: WUtils.handler2Down)
            cell?.systemBorrowedValue.attributedText = WUtils.getDPRawDollor(BorrowedValue.stringValue, 2, cell!.systemBorrowedValue.font)
        }

        // display system remain borrowable
        var SystemBorrowableAmount = NSDecimalNumber.zero
        var SystemBorrowableValue = NSDecimalNumber.zero
        var moduleAmount = NSDecimalNumber.zero
        var reserveAmount = NSDecimalNumber.zero
        if let moduleCoin = moduleCoins?.filter({ $0.denom == mHardMoneyMarketDenom}).first {
            moduleAmount = NSDecimalNumber.init(string: moduleCoin.amount)
        }
        if let reserveCoin = reserveCoins?.filter({ $0.denom == mHardMoneyMarketDenom}).first {
            reserveAmount = NSDecimalNumber.init(string: reserveCoin.amount)
        }

        let moduleBorrowable = moduleAmount.subtracting(reserveAmount)
        if (hardParam?.getHardMoneyMarket(mHardMoneyMarketDenom!)?.borrow_limit?.has_max_limit == true) {
            let maximum_limit = NSDecimalNumber.init(string: hardParam?.getHardMoneyMarket(mHardMoneyMarketDenom!)?.borrow_limit?.maximum_limit)
            SystemBorrowableAmount = maximum_limit.compare(moduleBorrowable).rawValue > 0 ? moduleBorrowable : maximum_limit
        } else {
            SystemBorrowableAmount = moduleBorrowable
        }
        WUtils.showCoinDp(mHardMoneyMarketDenom!, SystemBorrowableAmount.stringValue, cell!.systemRemainBorrowableDenom, cell!.systemRemainBorrowableAmount, chainType!)
        SystemBorrowableValue = SystemBorrowableAmount.multiplying(byPowerOf10: -dpDecimal).multiplying(by: targetPrice, withBehavior: WUtils.handler2Down)
        cell?.systemRemainBorrowableValue.attributedText = WUtils.getDPRawDollor(SystemBorrowableValue.stringValue, 2, cell!.systemRemainBorrowableValue.font)
        return cell!
    }
    
    func onBindAction(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestDetailMyActionCell") as? HarvestDetailMyActionCell
        cell?.depositImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mHardMoneyMarketDenom! + ".png")!)
        if (mHardMoneyMarketDenom == KAVA_MAIN_DENOM) {
            WUtils.setDenomTitle(chainType, cell!.depositSymbol)
        } else {
            cell?.depositSymbol.textColor = .white
            cell?.depositSymbol.text = mHardMoneyMarketDenom!.uppercased()
        }
        
        let decimal = WUtils.getKavaCoinDecimal(mHardMoneyMarketDenom!)
        let mySuppliedAmount = WUtils.getHardSuppliedAmountByDenom(mHardMoneyMarketDenom!, myDeposit)
        let mySuppliedValue = WUtils.getHardSuppliedValueByDenom(mHardMoneyMarketDenom!, myDeposit)
        cell!.depositAmount.attributedText = WUtils.displayAmount2(mySuppliedAmount.stringValue, cell!.depositAmount.font, decimal, decimal)
        cell?.depositValue.attributedText = WUtils.getDPRawDollor(mySuppliedValue.stringValue, 2, cell!.depositValue.font)
        
        let myBorrowedAmount = WUtils.getHardBorrowedAmountByDenom(mHardMoneyMarketDenom!, myBorrow)
        let myBorrowedValue = WUtils.getHardBorrowedValueByDenom(mHardMoneyMarketDenom!, myBorrow)
        cell!.borrowedAmount.attributedText = WUtils.displayAmount2(myBorrowedAmount.stringValue, cell!.borrowedAmount.font, decimal, decimal)
        cell?.borrowedValue.attributedText = WUtils.getDPRawDollor(myBorrowedValue.stringValue, 2, cell!.borrowedValue.font)
        
        let myBorrowableAmount = WUtils.getHardBorrowableAmountByDenom(mHardMoneyMarketDenom!, myDeposit, myBorrow, moduleCoins, reserveCoins)
        let myBorrowableValue = WUtils.getHardBorrowableValueByDenom(mHardMoneyMarketDenom!, myDeposit, myBorrow, moduleCoins, reserveCoins)
        cell!.borroweableAmount.attributedText = WUtils.displayAmount2(myBorrowableAmount.stringValue, cell!.borroweableAmount.font, decimal, decimal)
        cell?.borroweableValue.attributedText = WUtils.getDPRawDollor(myBorrowableValue.stringValue, 2, cell!.borroweableValue.font)
        
        cell?.actionDepoist = {
            self.onClickDeposit()
        }
        cell?.actionWithdraw = {
            self.onClickWithdraw()
        }
        cell?.actionBorrow = {
            self.onClickBorrow()
        }
        cell?.actionRepay = {
            self.onClickRepay()
        }
        
        return cell!
        
    }
    
    func onBindAsset(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HardDetailAssetsCell? = tableView.dequeueReusableCell(withIdentifier:"HardDetailAssetsCell") as? HardDetailAssetsCell
        cell?.marketImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mHardMoneyMarketDenom! + ".png")!)
        cell?.marketDenom.text = mHardMoneyMarketDenom!.uppercased()

        if (mHardMoneyMarketDenom == KAVA_MAIN_DENOM) {
            cell?.marketLayer.isHidden = true
        }

        let dpDecimal = WUtils.getKavaCoinDecimal(mHardMoneyMarketDenom!)
        let targetAvailable = WUtils.availableAmount(balances, mHardMoneyMarketDenom!)
        var targetPrice = NSDecimalNumber.one;
        if (mHardMoneyMarketDenom != "usdx") {
            let targetPriceTic = BaseData.instance.mKavaPrice[hardParam!.getHardMoneyMarket(mHardMoneyMarketDenom!)!.spot_market_id! + ":30"]
            if (targetPriceTic != nil) {
                targetPrice = NSDecimalNumber.init(string: targetPriceTic?.result.price)
            } else {
                targetPrice = NSDecimalNumber.zero
            }
        }
        let marketValue = targetAvailable.multiplying(byPowerOf10: -dpDecimal).multiplying(by: targetPrice, withBehavior: WUtils.handler2Down)
        cell?.marketAmountLabel.attributedText = WUtils.displayAmount2(targetAvailable.stringValue, cell!.marketAmountLabel.font!, dpDecimal, dpDecimal)
        cell?.marketValueLabel.attributedText = WUtils.getDPRawDollor(marketValue.stringValue, 2, cell!.marketValueLabel.font)


        let kavaAvailable = WUtils.availableAmount(balances, KAVA_MAIN_DENOM)
        let kavaPriceTic = BaseData.instance.mKavaPrice["kava:usd:30"]
        let kavaPrice = NSDecimalNumber.init(string: kavaPriceTic?.result.price)
        let kavaValue = kavaAvailable.multiplying(byPowerOf10: -6).multiplying(by: kavaPrice, withBehavior: WUtils.handler2Down)
        cell?.kavaAmountLabel.attributedText = WUtils.displayAmount2(kavaAvailable.stringValue, cell!.kavaAmountLabel.font!, 6, 6)
        cell?.kavaValueLable.attributedText = WUtils.getDPRawDollor(kavaValue.stringValue, 2, cell!.kavaValueLable.font)
        return cell!
        
    }
    
    
    
    func onClickDeposit() {
//        if (!onCommonCheck()) { return }
//        if (WUtils.availableAmount(balances, mDepositDenom!).compare(NSDecimalNumber.zero).rawValue <= 0) {
//            self.onShowToast(NSLocalizedString("error_no_available_to_deposit", comment: ""))
//            return
//        }
//        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
//        txVC.mType = KAVA_MSG_TYPE_DEPOSIT_HAVEST
//        txVC.mHarvestDepositDenom = mDepositDenom
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickWithdraw() {
//        if (!onCommonCheck()) { return }
//        guard let depositedCoin = myHavestDeposit?.amount, NSDecimalNumber.init(string: depositedCoin.denom).compare(NSDecimalNumber.zero).rawValue <= 0 else {
//            self.onShowToast(NSLocalizedString("error_no_deposited_asset", comment: ""))
//            return
//        }
//        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
//        txVC.mType = KAVA_MSG_TYPE_WITHDRAW_HAVEST
//        txVC.mHarvestDepositDenom = mDepositDenom
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickBorrow() {
        
    }
    
    func onClickRepay() {
        
    }
    
    func onCommonCheck() -> Bool {
//        if (!account!.account_has_private) {
//            self.onShowAddMenomicDialog()
//            return false
//        }
//        if (!distributionSchedule!.active) {
//            self.onShowToast(NSLocalizedString("error_circuit_breaker", comment: ""))
//            return false
//        }
        return true
    }

    
    var mFetchCnt = 0
    @objc func onFetchHardInfo() {
        if(self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 5
        self.onFetchHardInterestRate()
        self.onFetchHardModuleAccount()
        self.onFetchHardReserves()
        self.onFetchHardTotalDeposit()
        self.onFetchHardTotalBorrow()
        self.onFetchHardMyDeposit(account!.account_address)
        self.onFetchHardMyBorrow(account!.account_address)
        
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.harvestDetailTableView.reloadData()
            self.harvestDetailTableView.isHidden = false
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.refresher.endRefreshing()
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
    
    func onFetchHardModuleAccount() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_ACCOUNT
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_ACCOUNT
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardModuleAccount = KavaHardModuleAccount.init(responseData)
                    self.moduleCoins = kavaHardModuleAccount.result?[0].value?.coins
//                    print("self.moduleCoins ", self.moduleCoins)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardModuleAccount ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardReserves() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_HARD_RESERVE
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_HARD_RESERVE
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardReserves = KavaHardReserves.init(responseData)
                    self.reserveCoins = kavaHardReserves.result
//                    print("self.reserveCoins ", self.reserveCoins)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchHardReserves ", error) }
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
    
    /*
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
    */
    
}
