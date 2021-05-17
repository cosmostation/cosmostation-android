//
//  WalletCertikCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/09/22.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class WalletCertikCell: UITableViewCell {
    
    @IBOutlet weak var rootCardView: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var delegatedAmount: UILabel!
    @IBOutlet weak var unbondingAmount: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        delegatedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        rewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionDelegate: (() -> Void)? = nil
    var actionVote: (() -> Void)? = nil
    
    @IBAction func onClickDelegate(_ sender: Any) {
        actionDelegate?()
    }
    @IBAction func onClickVote(_ sender: Any) {
        actionVote?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        let available = BaseData.instance.availableAmount(CERTIK_MAIN_DENOM)
        let delegated = BaseData.instance.delegatedSumAmount()
        let unbonding = BaseData.instance.unbondingSumAmount()
        let reward = BaseData.instance.rewardAmount(CERTIK_MAIN_DENOM)
        let total = available.adding(delegated).adding(unbonding).adding(reward)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 6, 6)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 6, 6)
        delegatedAmount.attributedText = WUtils.displayAmount2(delegated.stringValue, delegatedAmount.font, 6, 6)
        unbondingAmount.attributedText = WUtils.displayAmount2(unbonding.stringValue, unbondingAmount.font, 6, 6)
        rewardAmount.attributedText = WUtils.displayAmount2(reward.stringValue, rewardAmount.font, 6, 6)
        totalValue.attributedText = WUtils.dpAmountValue(CERTIK_MAIN_DENOM, total, 6, totalValue.font)
        BaseData.instance.updateLastTotal(account, total.multiplying(byPowerOf10: -6).stringValue)
    }
}
