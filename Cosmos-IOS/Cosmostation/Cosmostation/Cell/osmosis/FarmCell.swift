//
//  FarmCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class FarmCell: UITableViewCell {
    
    @IBOutlet weak var poolIDLabel: UILabel!
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var poolArpLabel: UILabel!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var availableDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        availableAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
    }
    
    func onBindView(_ pool: Osmosis_Gamm_V1beta1_Pool, _ gauges: Array<Osmosis_Incentives_Gauge>) {
        let coin0 = Coin.init(pool.poolAssets[0].token.denom, pool.poolAssets[0].token.amount)
        let coin1 = Coin.init(pool.poolAssets[1].token.denom, pool.poolAssets[1].token.amount)
        
        poolIDLabel.text = "EARNING #" + String(pool.id)
        poolPairLabel.text = WUtils.getOsmosisTokenName(coin0.denom) + " / " + WUtils.getOsmosisTokenName(coin1.denom)
        
        if let lpCoin = BaseData.instance.mMyBalances_gRPC.filter({ $0.denom == "gamm/pool/" + String(pool.id) }).first {
            availableAmountLabel.attributedText = WUtils.displayAmount2(lpCoin.amount, availableAmountLabel.font, 18, 6)
            availableDenomLabel.text = WUtils.getOsmosisTokenName(lpCoin.denom)
        } else {
            availableAmountLabel.attributedText = WUtils.displayAmount2("0", availableAmountLabel.font, 18, 6)
            availableDenomLabel.text = WUtils.getOsmosisTokenName("gamm/pool/" + String(pool.id))
        }
        
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
