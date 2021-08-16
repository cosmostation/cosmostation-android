//
//  FarmingDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/14.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class FarmingDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var farmDetailTableView: UITableView!
    
    @IBOutlet weak var topPoolIDLabel: UILabel!
    @IBOutlet weak var topPoolPairLabel: UILabel!
    @IBOutlet weak var topApr1dayLabel: UILabel!
    @IBOutlet weak var topArp7dayLabel: UILabel!
    @IBOutlet weak var topArp14DayLabel: UILabel!
    @IBOutlet weak var topAvailableAmountLabel: UILabel!
    @IBOutlet weak var topAvailableDenomLabel: UILabel!
    @IBOutlet weak var topAvailableValueLabel: UILabel!
    
    var mPool: Osmosis_Gamm_V1beta1_Pool!
    var mPoolGauges: Array<Osmosis_Incentives_Gauge>!
    var mLockUps: Array<Osmosis_Lockup_PeriodLock>!
    var mBondedList: Array<Osmosis_Lockup_PeriodLock> = Array<Osmosis_Lockup_PeriodLock>()
    var mUnbondingList: Array<Osmosis_Lockup_PeriodLock> = Array<Osmosis_Lockup_PeriodLock>()
    var mUnbondedList: Array<Osmosis_Lockup_PeriodLock> = Array<Osmosis_Lockup_PeriodLock>()
    
    var coin0: Coin!
    var coin1: Coin!
    var coin0BaseDenom: String!
    var coin1BaseDenom: String!
    var coin0Symbol: String!
    var coin1Symbol: String!
    var coin0Decimal: Int16!
    var coin1Decimal: Int16!
    var lpCoinPrice = NSDecimalNumber.zero
    var poolValue = NSDecimalNumber.zero
    var nf: NumberFormatter!
    var apr1Day = NSDecimalNumber.zero
    var apr7Day = NSDecimalNumber.zero
    var apr14Day = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        
        topApr1dayLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        topArp7dayLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        topArp14DayLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
        self.farmDetailTableView.delegate = self
        self.farmDetailTableView.dataSource = self
        self.farmDetailTableView.register(UINib(nibName: "EarnBondedCell", bundle: nil), forCellReuseIdentifier: "EarnBondedCell")
        self.farmDetailTableView.register(UINib(nibName: "EarnUnbondingCell", bundle: nil), forCellReuseIdentifier: "EarnUnbondingCell")
        self.farmDetailTableView.register(UINib(nibName: "EarnUnbondedCell", bundle: nil), forCellReuseIdentifier: "EarnUnbondedCell")
        
        print("mPool ", mPool.id)
        print("mPoolGauges ", mPoolGauges.count)
        print("mLockUps ", mLockUps.count)
        
        coin0 = Coin.init(mPool.poolAssets[0].token.denom, mPool.poolAssets[0].token.amount)
        coin1 = Coin.init(mPool.poolAssets[1].token.denom, mPool.poolAssets[1].token.amount)
        coin0BaseDenom = BaseData.instance.getBaseDenom(coin0.denom)
        coin1BaseDenom = BaseData.instance.getBaseDenom(coin1.denom)
        coin0Symbol = WUtils.getOsmosisTokenName(coin0.denom)
        coin1Symbol = WUtils.getOsmosisTokenName(coin1.denom)
        coin0Decimal = WUtils.getOsmosisCoinDecimal(coin0.denom)
        coin1Decimal = WUtils.getOsmosisCoinDecimal(coin1.denom)
        lpCoinPrice = WUtils.getOsmoLpTokenPerUsdPrice(mPool)
        nf = WUtils.getNumberFormatter(2)
        
        let coin0Value = WUtils.usdValue(coin0BaseDenom, NSDecimalNumber.init(string: coin0.amount), coin0Decimal)
        let coin1Value = WUtils.usdValue(coin1BaseDenom, NSDecimalNumber.init(string: coin1.amount), coin1Decimal)
        poolValue = coin0Value.adding(coin1Value)
        
        
        //display title
        topPoolIDLabel.text = "EARNING #" + String(mPool.id)
        topPoolPairLabel.text = coin0Symbol + " / " + coin1Symbol
        
        
        //display ARPs
        let incentive1Day = NSDecimalNumber.init(string: mPoolGauges[0].coins[0].amount).subtracting(NSDecimalNumber.init(string: mPoolGauges[0].distributedCoins[0].amount))
        let incentiveValue1Day = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), incentive1Day, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
        
        let incentive7Day = NSDecimalNumber.init(string: mPoolGauges[1].coins[0].amount).subtracting(NSDecimalNumber.init(string: mPoolGauges[1].distributedCoins[0].amount))
        var incentiveValue7Day = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), incentive7Day, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
        
        let incentive14Day = NSDecimalNumber.init(string: mPoolGauges[2].coins[0].amount).subtracting(NSDecimalNumber.init(string: mPoolGauges[2].distributedCoins[0].amount))
        var incentiveValue14Day = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), incentive14Day, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
        
        incentiveValue14Day = incentiveValue14Day.adding(incentiveValue7Day).adding(incentiveValue1Day)
        incentiveValue7Day = incentiveValue7Day.adding(incentiveValue1Day)
        
        apr1Day = incentiveValue1Day.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        apr7Day = incentiveValue7Day.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        apr14Day = incentiveValue14Day.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        
        topApr1dayLabel.attributedText = WUtils.displayPercent(apr1Day, topApr1dayLabel.font)
        topArp7dayLabel.attributedText = WUtils.displayPercent(apr7Day, topArp7dayLabel.font)
        topArp14DayLabel.attributedText = WUtils.displayPercent(apr14Day, topArp14DayLabel.font)
        
        
        //display LP coin available
        let lpCoin = BaseData.instance.getAvailable_gRPC("gamm/pool/" + String(mPool.id))
        let lpCoinValue = NSDecimalNumber.init(string: lpCoin).multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let formattedLpCoinValue = "$ " + nf.string(from: lpCoinValue)!
        
        topAvailableDenomLabel.text = "GAMM-" + String(mPool.id)
        topAvailableAmountLabel.attributedText = WUtils.displayAmount2(lpCoin, topAvailableAmountLabel.font, 18, 18)
        topAvailableValueLabel.attributedText = WUtils.getDpAttributedString(formattedLpCoinValue, 2, topAvailableValueLabel.font)
        
        
        mLockUps.forEach { lockup in
            let now = Date.init().millisecondsSince1970
            let endTime = lockup.endTime.date.millisecondsSince1970
            if (endTime == -62135596800000) {
                mBondedList.append(lockup)
            } else if (endTime > now) {
                mUnbondingList.append(lockup)
            } else {
                mUnbondedList.append(lockup)
            }
        }
        farmDetailTableView.reloadData()
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_farm_detail_osmosis", comment: "")
        self.navigationItem.title = NSLocalizedString("title_farm_detail_osmosis", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return mBondedList.count
        } else if (section == 1) {
            return mUnbondingList.count
        } else if (section == 2) {
            return mUnbondedList.count
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"EarnBondedCell") as? EarnBondedCell
            let lock = mBondedList[indexPath.row]
            cell?.onBindView(lock, mPool, mPoolGauges)
            return cell!
            
        } else if (indexPath.section == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"EarnUnbondingCell") as? EarnUnbondingCell
            let lock = mUnbondingList[indexPath.row]
            cell?.onBindView(lock, mPool, mPoolGauges)
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"EarnUnbondedCell") as? EarnUnbondedCell
            let lock = mUnbondedList[indexPath.row]
            cell?.onBindView(lock, mPool, mPoolGauges)
            return cell!
        }
    }
    
    @IBAction func onClickNewFarm(_ sender: UIButton) {
        
    }
    
}
