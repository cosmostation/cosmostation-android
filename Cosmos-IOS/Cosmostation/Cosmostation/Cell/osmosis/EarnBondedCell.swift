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
    @IBOutlet weak var amountDenomLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionUnbonding: (() -> Void)? = nil
    @IBAction func onClickUnbonding(_ sender: UIButton) {
        actionUnbonding?()
    }
    
    func onBindView(_ lock: Osmosis_Lockup_PeriodLock, _ pool: Osmosis_Gamm_V1beta1_Pool, _ gauges: Array<Osmosis_Incentives_Gauge>) {
        let lpCoinPrice = WUtils.getOsmoLpTokenPerUsdPrice(pool)
        let nf = WUtils.getNumberFormatter(2)
        
        //my lp coin
        let lpCoin = Coin.init(lock.coins[0].denom, lock.coins[0].amount)
        let lpCoinValue =  NSDecimalNumber.init(string: lpCoin.amount).multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let lpCoinValueFormatted = "$ " + nf.string(from: lpCoinValue)!
        
        //pool incentives
        let incentive1Day = WUtils.getNextIncentiveAmount(pool, gauges, 0)
        let incentive7Day = WUtils.getNextIncentiveAmount(pool, gauges, 1)
        let incentive14Day = WUtils.getNextIncentiveAmount(pool, gauges, 2)
        
        //pool apr
        let apr1Day = WUtils.getPoolArp(pool, gauges, 0)
        let apr7Day = WUtils.getPoolArp(pool, gauges, 1)
        let apr14Day = WUtils.getPoolArp(pool, gauges, 2)
        
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
            myReward = myShareRate.multiplying(by: incentive1Day)

        } else if (lock.duration.seconds == 604800) {
            unbondingTimeLabel.text = "7 Days"
            aprLabel.attributedText = WUtils.displayPercent(apr7Day, aprLabel.font)
            myReward = myShareRate.multiplying(by: incentive7Day)

        } else if (lock.duration.seconds == 1209600) {
            unbondingTimeLabel.text = "14 Days"
            aprLabel.attributedText = WUtils.displayPercent(apr14Day, aprLabel.font)
            myReward = myShareRate.multiplying(by: incentive14Day)
        }
        
        amountDenomLabel.text = "GAMM-" + String(pool.id)
        amountLabel.attributedText = WUtils.displayAmount2(lpCoin.amount, amountLabel.font, 18, 18)
        amountValueLabel.attributedText = WUtils.getDpAttributedString(lpCoinValueFormatted, 2, amountValueLabel.font)
        nextRewardAmountLabel.attributedText = WUtils.displayAmount2(myReward.stringValue, nextRewardAmountLabel.font, 6, 6)
    }
}
