//
//  TokenDetailHardCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/12.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailHardCell: TokenDetailCell {
    
    @IBOutlet weak var cardRoot: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var vestingAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    
    func onBindHardToken() {
        let available = BaseData.instance.availableAmount(KAVA_HARD_DENOM)
        let vesting = BaseData.instance.lockedAmount(KAVA_HARD_DENOM)
        let total = available.adding(vesting)
        let convertedDollorValue = WUtils.getKavaTokenDollorValue(KAVA_HARD_DENOM, total)
        let convertedKavaAmount = convertedDollorValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler6)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 6, 6)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 6, 6)
        vestingAmount.attributedText = WUtils.displayAmount2(vesting.stringValue, vestingAmount.font, 6, 6)
        totalValue.attributedText = WUtils.dpTokenValue(convertedKavaAmount.multiplying(byPowerOf10: 6), BaseData.instance.getLastPrice(), 6, totalValue.font)
    }
    
}
