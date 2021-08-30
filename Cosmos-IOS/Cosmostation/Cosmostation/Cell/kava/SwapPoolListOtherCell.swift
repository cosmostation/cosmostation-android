//
//  SwapPoolListOtherCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/28.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class SwapPoolListOtherCell: UITableViewCell {
    
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var totalShareAmountLabel: UILabel!
    @IBOutlet weak var totalShareValueLabel: UILabel!
    @IBOutlet weak var liquidity1AmountLabel: UILabel!
    @IBOutlet weak var liquidity1DenomLabel: UILabel!
    @IBOutlet weak var liquidity2AmountLabel: UILabel!
    @IBOutlet weak var liquidity2DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        totalShareAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity2AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ pool: SwapPool) {
        let nf = WUtils.getNumberFormatter(2)
        let coin0 = pool.coins[0]
        let coin1 = pool.coins[1]
        let coin0Decimal = WUtils.getKavaCoinDecimal(coin0.denom)
        let coin1Decimal = WUtils.getKavaCoinDecimal(coin1.denom)
        let coin0price = WUtils.getKavaPriceFeed(coin0.denom)
        let coin1price = WUtils.getKavaPriceFeed(coin1.denom)
        let coin0Value = NSDecimalNumber.init(string: coin0.amount).multiplying(by: coin0price).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.handler2)
        let coin1Value = NSDecimalNumber.init(string: coin1.amount).multiplying(by: coin1price).multiplying(byPowerOf10: -coin1Decimal, withBehavior: WUtils.handler2)

        poolPairLabel.text = pool.name?.uppercased()

        let poolValue = coin0Value.adding(coin1Value)
        let poolValueFormatted = "$ " + nf.string(from: poolValue)!
        totalShareAmountLabel.attributedText = WUtils.displayAmount2(pool.total_shares.stringValue, totalShareAmountLabel.font, 6, 6)
        totalShareValueLabel.attributedText = WUtils.getDpAttributedString(poolValueFormatted, 2, totalShareValueLabel.font)

        WUtils.DpKavaTokenName(liquidity1DenomLabel, coin0.denom)
        WUtils.DpKavaTokenName(liquidity2DenomLabel, coin1.denom)
        liquidity1AmountLabel.attributedText = WUtils.displayAmount2(coin0.amount, liquidity1AmountLabel.font, coin0Decimal, 6)
        liquidity2AmountLabel.attributedText = WUtils.displayAmount2(coin1.amount, liquidity2AmountLabel.font, coin1Decimal, 6)
        
    }
    
}
