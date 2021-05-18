//
//  TokenDetailExCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailExCell: UITableViewCell {
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var depositAmount: UILabel!
    @IBOutlet weak var withdrawAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        depositAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        withdrawAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindTokens(_ account: Account) {
        let available = BaseData.instance.availableAmount(OKEX_MAIN_DENOM)
        let locked = BaseData.instance.lockedAmount(OKEX_MAIN_DENOM)
        let deposit = BaseData.instance.okDepositAmount()
        let withdraw = BaseData.instance.okWithdrawAmount()
        let total = available.adding(locked).adding(deposit).adding(withdraw)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 0, 18)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 0, 18)
        lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, lockedAmount.font, 0, 18)
        depositAmount.attributedText = WUtils.displayAmount2(deposit.stringValue, depositAmount.font, 0, 18)
        withdrawAmount.attributedText = WUtils.displayAmount2(withdraw.stringValue, withdrawAmount.font, 0, 18)
        totalValue.attributedText = WUtils.dpUserCurrencyValue(OKEX_MAIN_DENOM, total, 0, totalValue.font)
    }
    
}
