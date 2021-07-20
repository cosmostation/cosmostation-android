//
//  PoolCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/16.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class PoolCell: UITableViewCell {
    
    @IBOutlet weak var poolIDLabel: UILabel!
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var totalLiquidityValueLabel: UILabel!
    @IBOutlet weak var liquidity1AmountLabel: UILabel!
    @IBOutlet weak var liquidity1DenomLabel: UILabel!
    @IBOutlet weak var liquidity2AmountLabel: UILabel!
    @IBOutlet weak var liquidity2DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        liquidity1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity2AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ pool: Osmosis_Gamm_V1beta1_Pool) {
        let coin0 = Coin.init(pool.poolAssets[0].token.denom, pool.poolAssets[0].token.amount)
        let coin1 = Coin.init(pool.poolAssets[1].token.denom, pool.poolAssets[1].token.amount)
        
        poolIDLabel.text = "POOL #" + String(pool.id)
        poolPairLabel.text = WUtils.getOsmosisTokenName(coin0.denom) + " / " + WUtils.getOsmosisTokenName(coin1.denom)
        
        let coin0Value = WUtils.usdValue(BaseData.instance.getBaseDenom(coin0.denom), NSDecimalNumber.init(string: coin0.amount), WUtils.getOsmosisCoinDecimal(coin0.denom))
        let coin1Value = WUtils.usdValue(BaseData.instance.getBaseDenom(coin1.denom), NSDecimalNumber.init(string: coin1.amount), WUtils.getOsmosisCoinDecimal(coin1.denom))
        let poolValue = coin0Value.adding(coin1Value)
        let nf = WUtils.getNumberFormatter(2)
        let formatted = "$ " + nf.string(from: poolValue)!
        totalLiquidityValueLabel.attributedText = WUtils.getDpAttributedString(formatted, 2, totalLiquidityValueLabel.font)
        
        WUtils.showCoinDp(coin0, liquidity1DenomLabel, liquidity1AmountLabel, ChainType.OSMOSIS_MAIN)
        WUtils.showCoinDp(coin1, liquidity2DenomLabel, liquidity2AmountLabel, ChainType.OSMOSIS_MAIN)
        
    }
    
}
