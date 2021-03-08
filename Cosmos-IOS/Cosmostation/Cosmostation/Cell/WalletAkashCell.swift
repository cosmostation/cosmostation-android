//
//  WalletAkashCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/11/11.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class WalletAkashCell: UITableViewCell {
    
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
        let totalAkt = WUtils.getAllMainAsset(AKASH_MAIN_DENOM)
        totalAmount.attributedText = WUtils.displayAmount2(totalAkt.stringValue, totalAmount.font!, 6, 6)
        totalValue.attributedText = WUtils.dpTokenValue(totalAkt, BaseData.instance.getLastPrice(), 6, totalValue.font)
        availableAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getAvailable(AKASH_MAIN_DENOM), availableAmount.font!, 6, 6)
        delegatedAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getDelegatedSum(), delegatedAmount.font!, 6, 6)
        unbondingAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getUnbondingSum(), unbondingAmount.font, 6, 6)
        rewardAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getRewardSum(AKASH_MAIN_DENOM), rewardAmount.font, 6, 6)
        BaseData.instance.updateLastTotal(account, totalAkt.multiplying(byPowerOf10: -6).stringValue)
    }
}
