//
//  EarnBondedCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/15.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class EarnBondedCell: UITableViewCell {

    @IBOutlet weak var lockIdLabel: UILabel!
    @IBOutlet weak var lcokStatusLabel: UILabel!
    @IBOutlet weak var unbondingTimeLabel: UILabel!
    @IBOutlet weak var aprLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountValueLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        lockIdLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lcokStatusLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingTimeLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        aprLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        amountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        nextRewardAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionUnbonding: (() -> Void)? = nil
    @IBAction func onClickUnbonding(_ sender: UIButton) {
        actionUnbonding?()
    }
    
//    func onBindView(_ lock: Osmosis_Lockup_PeriodLock, _ lpCoinPrice: NSDecimalNumber, _ apr1Day: NSDecimalNumber, _ apr7Day: NSDecimalNumber, _ apr14Day: NSDecimalNumber) {
    func onBindView(_ lock: Osmosis_Lockup_PeriodLock, _ pool: Osmosis_Gamm_V1beta1_Pool, _ gauges: Array<Osmosis_Incentives_Gauge>) {
        let coin0 = Coin.init(pool.poolAssets[0].token.denom, pool.poolAssets[0].token.amount)
        let coin1 = Coin.init(pool.poolAssets[1].token.denom, pool.poolAssets[1].token.amount)
        let coin0BaseDenom = BaseData.instance.getBaseDenom(coin0.denom)
        let coin1BaseDenom = BaseData.instance.getBaseDenom(coin1.denom)
        let coin0Decimal = WUtils.getOsmosisCoinDecimal(coin0.denom)
        let coin1Decimal = WUtils.getOsmosisCoinDecimal(coin1.denom)
        let lpCoinPrice = WUtils.getOsmoLpTokenPerUsdPrice(pool)
        let nf = WUtils.getNumberFormatter(2)
        
        //my lp coin
        let lpCoin = Coin.init(lock.coins[0].denom, lock.coins[0].amount)
        let lpCoinValue =  NSDecimalNumber.init(string: lpCoin.amount).multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let lpCoinValueFormatted = "$ " + nf.string(from: lpCoinValue)!
        
        //pool value
        let coin0Value = WUtils.usdValue(coin0BaseDenom, NSDecimalNumber.init(string: coin0.amount), coin0Decimal)
        let coin1Value = WUtils.usdValue(coin1BaseDenom, NSDecimalNumber.init(string: coin1.amount), coin1Decimal)
        let poolValue = coin0Value.adding(coin1Value)
        
        //pool incentives
        let incentive1Day = NSDecimalNumber.init(string: gauges[0].coins[0].amount).subtracting(NSDecimalNumber.init(string: gauges[0].distributedCoins[0].amount))
        let incentiveValue1Day = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), incentive1Day, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
        var incentive7Day = NSDecimalNumber.init(string: gauges[1].coins[0].amount).subtracting(NSDecimalNumber.init(string: gauges[1].distributedCoins[0].amount))
        var incentiveValue7Day = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), incentive7Day, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
        var incentive14Day = NSDecimalNumber.init(string: gauges[2].coins[0].amount).subtracting(NSDecimalNumber.init(string: gauges[2].distributedCoins[0].amount))
        var incentiveValue14Day = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), incentive14Day, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
        
        //sum incentive for lockup time
        incentive14Day = incentive14Day.adding(incentive7Day).adding(incentive1Day)
        incentive7Day = incentive7Day.adding(incentive1Day)
        incentiveValue14Day = incentiveValue14Day.adding(incentiveValue7Day).adding(incentiveValue1Day)
        incentiveValue7Day = incentiveValue7Day.adding(incentiveValue1Day)
        
        //pool apr
        let apr1Day = incentiveValue1Day.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        let apr7Day = incentiveValue7Day.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        let apr14Day = incentiveValue14Day.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        
        //my reward
        let totalShares = NSDecimalNumber.init(string: pool.totalShares.amount)
        let myShare = NSDecimalNumber.init(string: lpCoin.amount)
        let myShareRate = myShare.dividing(by: totalShares, withBehavior: WUtils.handler24Down)
        var myReward = NSDecimalNumber.zero
        
        
        //DP UI
        lockIdLabel.text = "# " + String(lock.id)
        if (lock.duration.seconds == 86400) {
            unbondingTimeLabel.text = "1 Day"
            aprLabel.attributedText = WUtils.displayPercent(apr1Day, aprLabel.font)
            myReward = myReward.adding(myShareRate.multiplying(by: incentive1Day))

        } else if (lock.duration.seconds == 604800) {
            unbondingTimeLabel.text = "7 Days"
            aprLabel.attributedText = WUtils.displayPercent(apr7Day, aprLabel.font)
            myReward = myReward.adding(myShareRate.multiplying(by: incentive7Day))

        } else if (lock.duration.seconds == 1209600) {
            unbondingTimeLabel.text = "14 Days"
            aprLabel.attributedText = WUtils.displayPercent(apr14Day, aprLabel.font)
            myReward = myReward.adding(myShareRate.multiplying(by: incentive14Day))
        }
        
        amountLabel.attributedText = WUtils.displayAmount2(lpCoin.amount, amountLabel.font, 18, 18)
        amountValueLabel.attributedText = WUtils.getDpAttributedString(lpCoinValueFormatted, 2, amountValueLabel.font)
        nextRewardAmountLabel.attributedText = WUtils.displayAmount2(myReward.stringValue, nextRewardAmountLabel.font, 6, 6)
    }
}
