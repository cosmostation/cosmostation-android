//
//  SwapPoolListMyCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/28.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class SwapPoolListMyCell: UITableViewCell {
    
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var totalShareAmountLabel: UILabel!
    @IBOutlet weak var totalShareValueLabel: UILabel!
    @IBOutlet weak var liquidity1AmountLabel: UILabel!
    @IBOutlet weak var liquidity1DenomLabel: UILabel!
    @IBOutlet weak var liquidity2AmountLabel: UILabel!
    @IBOutlet weak var liquidity2DenomLabel: UILabel!
    
    @IBOutlet weak var myShareAmountLabel: UILabel!
    @IBOutlet weak var myShareValueLabel: UILabel!
    @IBOutlet weak var myDepositCoin0AmountLabel: UILabel!
    @IBOutlet weak var myDepositCoin0DenomLabel: UILabel!
    @IBOutlet weak var myDepositCoin1AmountLabel: UILabel!
    @IBOutlet weak var myDepositCoin1DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        totalShareAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidity2AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myShareAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myDepositCoin0AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myDepositCoin1AmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ pool: SwapPool, _ myDeposit: SwapDeposit) {
        let nf = WUtils.getNumberFormatter(2)
        let coin0 = pool.coins[0]
        let coin1 = pool.coins[1]
        let coin0Decimal = WUtils.getKavaCoinDecimal(coin0.denom)
        let coin1Decimal = WUtils.getKavaCoinDecimal(coin1.denom)
        let coin0price = WUtils.getKavaPriceFeed(coin0.denom)
        let coin1price = WUtils.getKavaPriceFeed(coin1.denom)
        let coin0Value = NSDecimalNumber.init(string: coin0.amount).multiplying(by: coin0price).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.handler2)
        let coin1Value = NSDecimalNumber.init(string: coin1.amount).multiplying(by: coin1price).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.handler2)
        
        poolPairLabel.text = pool.name?.uppercased()
        
        let poolValue = coin0Value.adding(coin1Value)
        let poolValueFormatted = "$ " + nf.string(from: poolValue)!
        totalShareAmountLabel.attributedText = WUtils.displayAmount2(pool.total_shares.stringValue, totalShareAmountLabel.font, 6, 6)
        totalShareValueLabel.attributedText = WUtils.getDpAttributedString(poolValueFormatted, 2, totalShareValueLabel.font)
        
        WUtils.DpKavaTokenName(liquidity1DenomLabel, coin0.denom)
        WUtils.DpKavaTokenName(liquidity2DenomLabel, coin1.denom)
        liquidity1AmountLabel.attributedText = WUtils.displayAmount2(coin0.amount, liquidity1AmountLabel.font, coin0Decimal, 6)
        liquidity2AmountLabel.attributedText = WUtils.displayAmount2(coin1.amount, liquidity2AmountLabel.font, coin1Decimal, 6)
                
        let my0 = myDeposit.shares_value[0]
        let my1 = myDeposit.shares_value[1]
        let my0Value = NSDecimalNumber.init(string: my0.amount).multiplying(by: coin0price).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.handler2)
        let my1Value = NSDecimalNumber.init(string: my1.amount).multiplying(by: coin1price).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.handler2)
        
        let myShareValue = my0Value.adding(my1Value)
        let myShareValueFormatted = "$ " + nf.string(from: myShareValue)!
        myShareAmountLabel.attributedText = WUtils.displayAmount2(myDeposit.shares_owned.stringValue, myShareAmountLabel.font, 6, 6)
        myShareValueLabel.attributedText = WUtils.getDpAttributedString(myShareValueFormatted, 2, myShareValueLabel.font)
        
        WUtils.DpKavaTokenName(myDepositCoin0DenomLabel, my0.denom)
        WUtils.DpKavaTokenName(myDepositCoin1DenomLabel, my1.denom)
        myDepositCoin0AmountLabel.attributedText = WUtils.displayAmount2(my0.amount, myDepositCoin0AmountLabel.font, coin0Decimal, 6)
        myDepositCoin1AmountLabel.attributedText = WUtils.displayAmount2(my1.amount, myDepositCoin1AmountLabel.font, coin1Decimal, 6)
        
    }
}
