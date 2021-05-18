//
//  TokenDetailBnbCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailBnbCell: TokenDetailCell {
    
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var frozenAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        frozenAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindTokens(_ account: Account) {
        let available = BaseData.instance.availableAmount(BNB_MAIN_DENOM)
        let locked = BaseData.instance.lockedAmount(BNB_MAIN_DENOM)
        let frozen = BaseData.instance.frozenAmount(BNB_MAIN_DENOM)
        let total = available.adding(locked).adding(frozen)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 0, 8)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 0, 8)
        lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, lockedAmount.font, 0, 8)
        frozenAmount.attributedText = WUtils.displayAmount2(frozen.stringValue, lockedAmount.font, 0, 8)
        totalValue.attributedText = WUtils.dpUserCurrencyValue(BNB_MAIN_DENOM, total, 0, totalValue.font)
        BaseData.instance.updateLastTotal(account, total.multiplying(byPowerOf10: -6).stringValue)
    }
    
}
