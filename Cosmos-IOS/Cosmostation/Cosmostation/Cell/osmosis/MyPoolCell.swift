//
//  MyPoolCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/16.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class MyPoolCell: UITableViewCell {

    @IBOutlet weak var poolIDLabel: UILabel!
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var totalLiquidityValueLabel: UILabel!
    @IBOutlet weak var liquidity1AmountLabel: UILabel!
    @IBOutlet weak var liquidity1DenomLabel: UILabel!
    @IBOutlet weak var liquidity2AmountLabel: UILabel!
    @IBOutlet weak var liquidity2DenomLabel: UILabel!
    
    @IBOutlet weak var myLpAmountLabel: UILabel!
    @IBOutlet weak var myLpDenomLabel: UILabel!
    @IBOutlet weak var withdrawable1AmountLabel: UILabel!
    @IBOutlet weak var withdrawable1DenomLabel: UILabel!
    @IBOutlet weak var withdrawable2AmountLabel: UILabel!
    @IBOutlet weak var withdrawable2DenomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        liquidity1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity2AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myLpAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
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
        
        
        let lpCoin = BaseData.instance.mMyBalances_gRPC.filter { $0.denom == "gamm/pool/" + String(pool.id) }.first
        myLpAmountLabel.attributedText = WUtils.displayAmount2(lpCoin!.amount, myLpAmountLabel.font, 18, 6)
        myLpDenomLabel.text = WUtils.getOsmosisTokenName(lpCoin!.denom)
        
        let padding = NSDecimalNumber(string: "0.97")
        let totalShares = NSDecimalNumber.init(string: pool.totalShares.amount)
        let myShare = NSDecimalNumber.init(string: lpCoin?.amount)
        let poolAmount0 = NSDecimalNumber.init(string: pool.poolAssets[0].token.amount)
        let poolAmount1 = NSDecimalNumber.init(string: pool.poolAssets[1].token.amount)
        
        let withdrawableAmount0 = poolAmount0.multiplying(by: myShare).multiplying(by: padding).dividing(by: totalShares, withBehavior: WUtils.handler0)
        let withdrawableCoin0 = Coin.init(pool.poolAssets[0].token.denom, withdrawableAmount0.stringValue)
        let withdrawableAmount1 = poolAmount1.multiplying(by: myShare).multiplying(by: padding).dividing(by: totalShares, withBehavior: WUtils.handler0)
        let withdrawableCoin1 = Coin.init(pool.poolAssets[1].token.denom, withdrawableAmount1.stringValue)
        
        WUtils.showCoinDp(withdrawableCoin0, withdrawable1DenomLabel, withdrawable1AmountLabel, ChainType.OSMOSIS_MAIN)
        WUtils.showCoinDp(withdrawableCoin1, withdrawable2DenomLabel, withdrawable2AmountLabel, ChainType.OSMOSIS_MAIN)
    }
}
