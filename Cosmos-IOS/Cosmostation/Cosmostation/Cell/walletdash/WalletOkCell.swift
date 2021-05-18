//
//  WalletOkCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class WalletOkCell: UITableViewCell {
    
    @IBOutlet weak var rootCardView: CardView!
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
    
    var actionDeposit: (() -> Void)? = nil
    var actionWithdraw: (() -> Void)? = nil
    var actionVoteforVal: (() -> Void)? = nil
    var actionVote: (() -> Void)? = nil
    
    @IBAction func onClickDeposit(_ sender: UIButton) {
        actionDeposit?()
    }
    @IBAction func onClickWithdraw(_ sender: UIButton) {
        actionWithdraw?()
    }
    @IBAction func onClickVoteForVal(_ sender: UIButton) {
        actionVoteforVal?()
    }
    @IBAction func onClickVote(_ sender: UIButton) {
        actionVote?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        let available = BaseData.instance.availableAmount(OKEX_MAIN_DENOM)
        let locked = BaseData.instance.lockedAmount(OKEX_MAIN_DENOM)
        let deposit = BaseData.instance.okDepositAmount()
        let withdraw = BaseData.instance.okWithdrawAmount()
        let total = available.adding(locked).adding(deposit).adding(withdraw)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 0, 6)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 0, 6)
        lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, lockedAmount.font, 0, 6)
        depositAmount.attributedText = WUtils.displayAmount2(deposit.stringValue, depositAmount.font, 0, 6)
        withdrawAmount.attributedText = WUtils.displayAmount2(withdraw.stringValue, withdrawAmount.font, 0, 6)
        totalValue.attributedText = WUtils.dpUserCurrencyValue(OKEX_MAIN_DENOM, total, 0, totalValue.font)
        BaseData.instance.updateLastTotal(account, total.stringValue)
    }
}
