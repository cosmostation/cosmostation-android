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
    @IBOutlet weak var farmingValueLabel: UILabel!
    @IBOutlet weak var unbondingAmountLabel: UILabel!
    @IBOutlet weak var unbondingDenomLabel: UILabel!
    @IBOutlet weak var unbondingValueLabel: UILabel!
    @IBOutlet weak var unbondedAmountLabel: UILabel!
    @IBOutlet weak var unbondedDenomLabel: UILabel!
    @IBOutlet weak var unbondedValueLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!
    
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var availableDenomLabel: UILabel!
    @IBOutlet weak var availableValueLabel: UILabel!
    
    
    
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
        let coin0BaseDenom = BaseData.instance.getBaseDenom(coin0.denom)
        let coin1BaseDenom = BaseData.instance.getBaseDenom(coin1.denom)
        let coin0Symbol = WUtils.getOsmosisTokenName(coin0.denom)
        let coin1Symbol = WUtils.getOsmosisTokenName(coin1.denom)
        let coin0Decimal = WUtils.getOsmosisCoinDecimal(coin0.denom)
        let coin1Decimal = WUtils.getOsmosisCoinDecimal(coin1.denom)
        let lpCoinPrice = WUtils.getOsmoLpTokenPerUsdPrice(pool)
        let nf = WUtils.getNumberFormatter(2)
        
        poolIDLabel.text = "MY FARM #" + String(pool.id)
        poolPairLabel.text = coin0Symbol + " / " + coin1Symbol
        
        
        
        
        
        
        
        var bondedAmount = NSDecimalNumber.zero
        var unbondingAmount = NSDecimalNumber.zero
        var unbondedAmount = NSDecimalNumber.zero
        
//        print("pool ", pool.id, "  ",  lockUps.count)
        
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
        
        
        let farmingCoinValue = bondedAmount.multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let formattedFarmingCoin = "$ " + nf.string(from: farmingCoinValue)!
        let unbondingCoinValue = unbondingAmount.multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let formattedUnbondingCoin = "$ " + nf.string(from: unbondingCoinValue)!
        let unbondedCoinValue = unbondedAmount.multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let formattedUnbondedCoin = "$ " + nf.string(from: unbondedCoinValue)!
        
        farmingValueLabel.attributedText = WUtils.getDpAttributedString(formattedFarmingCoin, 2, farmingValueLabel.font)
        unbondingValueLabel.attributedText = WUtils.getDpAttributedString(formattedUnbondingCoin, 2, unbondingValueLabel.font)
        unbondedValueLabel.attributedText = WUtils.getDpAttributedString(formattedUnbondedCoin, 2, unbondedValueLabel.font)
        
        
        
        let coin0Value = WUtils.usdValue(coin0BaseDenom, NSDecimalNumber.init(string: coin0.amount), coin0Decimal)
        let coin1Value = WUtils.usdValue(coin1BaseDenom, NSDecimalNumber.init(string: coin1.amount), coin1Decimal)
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
        
        
        
        let lpCoin = BaseData.instance.getAvailable_gRPC("gamm/pool/" + String(pool.id))
        let lpCoinValue = NSDecimalNumber.init(string: lpCoin).multiplying(by: lpCoinPrice).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
        let formattedLpCoinValue = "$ " + nf.string(from: lpCoinValue)!
        availableDenomLabel.text = "GAMM-" + String(pool.id)
        availableDenomLabel.adjustsFontSizeToFitWidth = true
        availableAmountLabel.attributedText = WUtils.displayAmount2(lpCoin, availableAmountLabel.font, 18, 6)
        availableValueLabel.attributedText = WUtils.getDpAttributedString(formattedLpCoinValue, 2, availableValueLabel.font)
        
        Osmosis_Lockup_MsgBeginUnlockingAll.with {
            $0.unknownFields
        }
    }
}
