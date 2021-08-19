//
//  FarmDashCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class FarmDashCell: UITableViewCell {

    @IBOutlet weak var totalFarmValueLabel: UILabel!
    @IBOutlet weak var totalUnbondingValueLabe: UILabel!
    @IBOutlet weak var totalUnbondedValueLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        totalFarmValueLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        totalUnbondingValueLabe.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        totalUnbondedValueLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        nextRewardAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ myPools: Array<Osmosis_Gamm_V1beta1_Pool>, _ lockUps: Array<Osmosis_Lockup_PeriodLock>) {
        print("lockedCoins ", lockUps.count)
        let nf = WUtils.getNumberFormatter(2)
        let now = Date.init().millisecondsSince1970
        
        var totalBondedValue = NSDecimalNumber.zero
        var totalUnbondingValue = NSDecimalNumber.zero
        var totalUnbondedValue = NSDecimalNumber.zero
        
        lockUps.forEach { lockup in
            let lpCoin = Coin.init(lockup.coins[0].denom, lockup.coins[0].amount)
            var lpCoinValue = NSDecimalNumber.zero
            if let matchedPool = myPools.filter({ $0.id == lpCoin.osmosisAmmPoolId() }).first {
                let totalShares = NSDecimalNumber.init(string: matchedPool.totalShares.amount)
                let myShare = NSDecimalNumber.init(string: lpCoin.amount)
                let denom0 = matchedPool.poolAssets[0].token.denom
                let denom1 = matchedPool.poolAssets[1].token.denom
                let poolAmount0 = NSDecimalNumber.init(string: matchedPool.poolAssets[0].token.amount)
                let poolAmount1 = NSDecimalNumber.init(string: matchedPool.poolAssets[1].token.amount)
                let withdrawableAmount0 = poolAmount0.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                let withdrawableAmount1 = poolAmount1.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                let coin0Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom0), withdrawableAmount0, WUtils.getOsmosisCoinDecimal(denom0))
                let coin1Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom1), withdrawableAmount1, WUtils.getOsmosisCoinDecimal(denom1))
                lpCoinValue = coin0Value.adding(coin1Value)
            }
            
            let endTime = lockup.endTime.date.millisecondsSince1970
            if (endTime == -62135596800000) {
                totalBondedValue = totalBondedValue.adding(lpCoinValue)
                
            } else if (endTime > now) {
                totalUnbondingValue = totalUnbondingValue.adding(lpCoinValue)
                
            } else {
                totalUnbondedValue = totalUnbondedValue.adding(lpCoinValue)
            }
        }
        let formattedBonded = "$ " + nf.string(from: totalBondedValue)!
        totalFarmValueLabel.attributedText = WUtils.getDpAttributedString(formattedBonded, 2, totalFarmValueLabel.font)
        
        let formattedUnbonding = "$ " + nf.string(from: totalUnbondingValue)!
        totalUnbondingValueLabe.attributedText = WUtils.getDpAttributedString(formattedUnbonding, 2, totalUnbondingValueLabe.font)
        
        let formattedUnbonded = "$ " + nf.string(from: totalUnbondedValue)!
        totalUnbondedValueLabel.attributedText = WUtils.getDpAttributedString(formattedUnbonded, 2, totalUnbondedValueLabel.font)
        
        
        /*
        var totalBondedValue = NSDecimalNumber.zero
        lockedCoins.forEach { lpCoin in
            if let matchedPool = myPools.filter{ $0.id == lpCoin.osmosisAmmPoolId() }.first {
                
                let totalShares = NSDecimalNumber.init(string: matchedPool.totalShares.amount)
                let myShare = NSDecimalNumber.init(string: lpCoin.amount)
                let denom0 = matchedPool.poolAssets[0].token.denom
                let denom1 = matchedPool.poolAssets[1].token.denom
                let poolAmount0 = NSDecimalNumber.init(string: matchedPool.poolAssets[0].token.amount)
                let poolAmount1 = NSDecimalNumber.init(string: matchedPool.poolAssets[1].token.amount)
                let withdrawableAmount0 = poolAmount0.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                let withdrawableAmount1 = poolAmount1.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                
                let coin0Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom0), withdrawableAmount0, WUtils.getOsmosisCoinDecimal(denom0))
                let coin1Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom1), withdrawableAmount1, WUtils.getOsmosisCoinDecimal(denom1))
                totalBondedValue = totalBondedValue.adding(coin0Value).adding(coin1Value)
            }
        }
        print("totalBondedValue ", totalBondedValue)
        let formattedBonded = "$ " + nf.string(from: totalBondedValue)!
        totalFarmValueLabel.attributedText = WUtils.getDpAttributedString(formattedBonded, 2, totalFarmValueLabel.font)
        
        
        var totalUnbondingValue = NSDecimalNumber.zero
        unlockingCoins.forEach { lpCoin in
            if let matchedPool = myPools.filter{ $0.id == lpCoin.osmosisAmmPoolId() }.first {
                
                let totalShares = NSDecimalNumber.init(string: matchedPool.totalShares.amount)
                let myShare = NSDecimalNumber.init(string: lpCoin.amount)
                let denom0 = matchedPool.poolAssets[0].token.denom
                let denom1 = matchedPool.poolAssets[1].token.denom
                let poolAmount0 = NSDecimalNumber.init(string: matchedPool.poolAssets[0].token.amount)
                let poolAmount1 = NSDecimalNumber.init(string: matchedPool.poolAssets[1].token.amount)
                let withdrawableAmount0 = poolAmount0.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                let withdrawableAmount1 = poolAmount1.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                
                let coin0Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom0), withdrawableAmount0, WUtils.getOsmosisCoinDecimal(denom0))
                let coin1Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom1), withdrawableAmount1, WUtils.getOsmosisCoinDecimal(denom1))
                totalUnbondingValue = totalUnbondingValue.adding(coin0Value).adding(coin1Value)
            }
        }
        print("totalUnbondingValue ", totalUnbondingValue)
        let formattedUnbonding = "$ " + nf.string(from: totalUnbondingValue)!
        totalUnbondingValueLabe.attributedText = WUtils.getDpAttributedString(formattedUnbonding, 2, totalUnbondingValueLabe.font)
        
        
        var totalUnbondedValue = NSDecimalNumber.zero
        unlockableCoins.forEach { lpCoin in
            if let matchedPool = myPools.filter{ $0.id == lpCoin.osmosisAmmPoolId() }.first {
                
                let totalShares = NSDecimalNumber.init(string: matchedPool.totalShares.amount)
                let myShare = NSDecimalNumber.init(string: lpCoin.amount)
                let denom0 = matchedPool.poolAssets[0].token.denom
                let denom1 = matchedPool.poolAssets[1].token.denom
                let poolAmount0 = NSDecimalNumber.init(string: matchedPool.poolAssets[0].token.amount)
                let poolAmount1 = NSDecimalNumber.init(string: matchedPool.poolAssets[1].token.amount)
                let withdrawableAmount0 = poolAmount0.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                let withdrawableAmount1 = poolAmount1.multiplying(by: myShare).dividing(by: totalShares, withBehavior: WUtils.handler0)
                
                let coin0Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom0), withdrawableAmount0, WUtils.getOsmosisCoinDecimal(denom0))
                let coin1Value = WUtils.usdValue(BaseData.instance.getBaseDenom(denom1), withdrawableAmount1, WUtils.getOsmosisCoinDecimal(denom1))
                totalUnbondedValue = totalUnbondedValue.adding(coin0Value).adding(coin1Value)
            }
        }
        print("totalUnbondedValue ", totalUnbondedValue)
        let formattedUnbonded = "$ " + nf.string(from: totalUnbondedValue)!
        totalUnbondedValueLabel.attributedText = WUtils.getDpAttributedString(formattedUnbonded, 2, totalUnbondedValueLabel.font)
         */
        
    }
    
}
