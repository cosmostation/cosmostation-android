//
//  EarnUnbondedCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/15.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class EarnUnbondedCell: UITableViewCell {
    
    @IBOutlet weak var lockIdLabel: UILabel!
    @IBOutlet weak var lcokStatusLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountValueLabel: UILabel!
    @IBOutlet weak var amountDenomLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    var actionUnlock: (() -> Void)? = nil
    @IBAction func onClickUnlock(_ sender: UIButton) {
        actionUnlock?()
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
        
        //my reward
        let totalShares = NSDecimalNumber.init(string: pool.totalShares.amount)
        let myShare = NSDecimalNumber.init(string: lpCoin.amount)
        let myShareRate = myShare.dividing(by: totalShares, withBehavior: WUtils.handler24Down)
        let myReward = myShareRate.multiplying(by: incentive1Day)
        
        //DP UI
        lockIdLabel.text = "# " + String(lock.id)
        
        amountDenomLabel.text = "GAMM-" + String(pool.id)
        amountLabel.attributedText = WUtils.displayAmount2(lpCoin.amount, amountLabel.font, 18, 18)
        amountValueLabel.attributedText = WUtils.getDpAttributedString(lpCoinValueFormatted, 2, amountValueLabel.font)
        nextRewardAmountLabel.attributedText = WUtils.displayAmount2(myReward.stringValue, nextRewardAmountLabel.font, 6, 6)
    }
}
