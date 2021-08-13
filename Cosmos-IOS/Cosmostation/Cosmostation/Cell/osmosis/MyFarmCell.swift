//
//  MyFarmCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class MyFarmCell: UITableViewCell {
    
    @IBOutlet weak var poolIDLabel: UILabel!
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var poolArpLabel: UILabel!
    @IBOutlet weak var farmingAmountLabel: UILabel!
    @IBOutlet weak var farmingDenomLabel: UILabel!
    @IBOutlet weak var unbondingAmountLabel: UILabel!
    @IBOutlet weak var unbondingDenomLabel: UILabel!
    @IBOutlet weak var unbondedAmountLabel: UILabel!
    @IBOutlet weak var unbondedDenomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        farmingAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondedAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ pool: Osmosis_Gamm_V1beta1_Pool, _ lockUps: Array<Osmosis_Lockup_PeriodLock>, _ gauges: Array<Osmosis_Incentives_Gauge>) {
        let coin0 = Coin.init(pool.poolAssets[0].token.denom, pool.poolAssets[0].token.amount)
        let coin1 = Coin.init(pool.poolAssets[1].token.denom, pool.poolAssets[1].token.amount)
        
        poolIDLabel.text = "POOL #" + String(pool.id)
        poolPairLabel.text = WUtils.getOsmosisTokenName(coin0.denom) + " / " + WUtils.getOsmosisTokenName(coin1.denom)
        
        var bondedAmount = NSDecimalNumber.zero
        var unbondingAmount = NSDecimalNumber.zero
        var unbondedAmount = NSDecimalNumber.zero
        
        lockUps.forEach { lockup in
            let lpCoin = Coin.init(lockup.coins[0].denom, lockup.coins[0].amount)
            let now = Date.init().millisecondsSince1970
            let endTime = lockup.endTime.date.millisecondsSince1970
            if (lpCoin.osmosisAmmPoolId() == pool.id) {
                if (endTime == -62135596800000) {
                    bondedAmount = bondedAmount.adding(NSDecimalNumber.init(string: lpCoin.amount))
                    
                } else if (endTime > now) {
                    unbondingAmount = unbondingAmount.adding(NSDecimalNumber.init(string: lpCoin.amount))
                    
                } else {
                    unbondedAmount = unbondedAmount.adding(NSDecimalNumber.init(string: lpCoin.amount))
                }
            }
        }
        
        farmingAmountLabel.attributedText = WUtils.displayAmount2(bondedAmount.stringValue, farmingAmountLabel.font, 18, 6)
        farmingDenomLabel.text = "GAMM-" + String(pool.id)
        unbondingAmountLabel.attributedText = WUtils.displayAmount2(unbondingAmount.stringValue, unbondingAmountLabel.font, 18, 6)
        unbondingDenomLabel.text = "GAMM-" + String(pool.id)
        unbondedAmountLabel.attributedText = WUtils.displayAmount2(unbondedAmount.stringValue, unbondedAmountLabel.font, 18, 6)
        unbondedDenomLabel.text = "GAMM-" + String(pool.id)
        
        let coin0Value = WUtils.usdValue(BaseData.instance.getBaseDenom(coin0.denom), NSDecimalNumber.init(string: coin0.amount), WUtils.getOsmosisCoinDecimal(coin0.denom))
        let coin1Value = WUtils.usdValue(BaseData.instance.getBaseDenom(coin1.denom), NSDecimalNumber.init(string: coin1.amount), WUtils.getOsmosisCoinDecimal(coin1.denom))
        let poolValue = coin0Value.adding(coin1Value)
//        print("poolValue ", poolValue)
        
        var thisTotalIncentiveValue = NSDecimalNumber.zero
        gauges.forEach { gauge in
            if (gauge.coins.count > 0 && gauge.distributedCoins.count > 0) {
                let cIncentive = gauge.coins[0]
                let dIncentive = gauge.distributedCoins[0]
                
                let thisIncentive = NSDecimalNumber.init(string: cIncentive.amount).subtracting(NSDecimalNumber.init(string: dIncentive.amount))
                let thisIncentiveValue = WUtils.usdValue(BaseData.instance.getBaseDenom(OSMOSIS_MAIN_DENOM), thisIncentive, WUtils.getOsmosisCoinDecimal(OSMOSIS_MAIN_DENOM))
                
                thisTotalIncentiveValue = thisTotalIncentiveValue.adding(thisIncentiveValue)
                
            }
        }
        let apr = thisTotalIncentiveValue.multiplying(by: NSDecimalNumber.init(value: 36500)).dividing(by: poolValue, withBehavior: WUtils.handler12)
        poolArpLabel.attributedText = WUtils.displayPercent(apr, poolArpLabel.font)
    }
}
