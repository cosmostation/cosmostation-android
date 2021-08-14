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
    
    @IBOutlet weak var availableCoin0AmountLabel: UILabel!
    @IBOutlet weak var availableCoin0DenomLabel: UILabel!
    @IBOutlet weak var availableCoin1AmountLabel: UILabel!
    @IBOutlet weak var availableCoin1DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        liquidity1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity2AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        availableCoin0AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        availableCoin1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ pool: Osmosis_Gamm_V1beta1_Pool) {
        let coin0 = Coin.init(pool.poolAssets[0].token.denom, pool.poolAssets[0].token.amount)
        let coin1 = Coin.init(pool.poolAssets[1].token.denom, pool.poolAssets[1].token.amount)
        let coin0BaseDenom = BaseData.instance.getBaseDenom(coin0.denom)
        let coin1BaseDenom = BaseData.instance.getBaseDenom(coin1.denom)
        let coin0Symbol = WUtils.getOsmosisTokenName(coin0.denom)
        let coin1Symbol = WUtils.getOsmosisTokenName(coin1.denom)
        let coin0Decimal = WUtils.getOsmosisCoinDecimal(coin0.denom)
        let coin1Decimal = WUtils.getOsmosisCoinDecimal(coin1.denom)
        
        poolIDLabel.text = "POOL #" + String(pool.id)
        poolPairLabel.text = coin0Symbol + " / " + coin1Symbol
        
        let coin0Value = WUtils.usdValue(coin0BaseDenom, NSDecimalNumber.init(string: coin0.amount), coin0Decimal)
        let coin1Value = WUtils.usdValue(coin1BaseDenom, NSDecimalNumber.init(string: coin1.amount), coin1Decimal)
        let poolValue = coin0Value.adding(coin1Value)
        let nf = WUtils.getNumberFormatter(2)
        let formatted = "$ " + nf.string(from: poolValue)!
        totalLiquidityValueLabel.attributedText = WUtils.getDpAttributedString(formatted, 2, totalLiquidityValueLabel.font)
        
        liquidity1DenomLabel.text = coin0Symbol
        liquidity1DenomLabel.adjustsFontSizeToFitWidth = true
        liquidity2DenomLabel.text = coin1Symbol
        liquidity2DenomLabel.adjustsFontSizeToFitWidth = true
        liquidity1AmountLabel.attributedText = WUtils.displayAmount2(coin0.amount, liquidity1AmountLabel.font, coin0Decimal, 6)
        liquidity2AmountLabel.attributedText = WUtils.displayAmount2(coin1.amount, liquidity2AmountLabel.font, coin1Decimal, 6)
        
        
        let availableCoin0 = BaseData.instance.getAvailable_gRPC(coin0.denom)
        let availableCoin1 = BaseData.instance.getAvailable_gRPC(coin1.denom)
        
        availableCoin0DenomLabel.text = coin0Symbol
        availableCoin0DenomLabel.adjustsFontSizeToFitWidth = true
        availableCoin1DenomLabel.text = coin1Symbol
        availableCoin1DenomLabel.adjustsFontSizeToFitWidth = true
        availableCoin0AmountLabel.attributedText = WUtils.displayAmount2(availableCoin0, availableCoin0AmountLabel.font, coin0Decimal, 6)
        availableCoin1AmountLabel.attributedText = WUtils.displayAmount2(availableCoin1, availableCoin1AmountLabel.font, coin1Decimal, 6)
        
    }
    
}
