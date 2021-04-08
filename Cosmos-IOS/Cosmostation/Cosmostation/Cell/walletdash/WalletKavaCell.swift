//
//  WalletKavaCell.swift
//  Cosmostation
//
//  Created by yongjoo on 11/11/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletKavaCell: UITableViewCell {
    
    @IBOutlet weak var cardKava: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var delegatedAmount: UILabel!
    @IBOutlet weak var unbondingAmount: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var vestingAmount: UILabel!
    @IBOutlet weak var havestDepositedAmount: UILabel!
    @IBOutlet weak var unclaimedIncentiveAmount: UILabel!
    @IBOutlet weak var vestingLayer: UIView!
    @IBOutlet weak var havestDepositLayer: UIView!
    @IBOutlet weak var unClaimedIncentiveLayer: UIView!
    @IBOutlet weak var cdpBtn: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        delegatedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        rewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        havestDepositedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unclaimedIncentiveAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionDelegate: (() -> Void)? = nil
    var actionVote: (() -> Void)? = nil
    var actionCdp: (() -> Void)? = nil
    
    @IBAction func onClickDelegate(_ sender: Any) {
        actionDelegate?()
    }
    @IBAction func onClickVote(_ sender: Any) {
        actionVote?()
    }
    @IBAction func onClickCdp(_ sender: Any) {
        actionCdp?()
    }
    
    override func prepareForReuse() {
        vestingLayer.isHidden = true
        havestDepositLayer.isHidden = true
        unClaimedIncentiveLayer.isHidden = true
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        let available = BaseData.instance.availableAmount(KAVA_MAIN_DENOM)
        let vesting = BaseData.instance.lockedAmount(KAVA_MAIN_DENOM)
        let delegated = BaseData.instance.deleagtedSumAmount()
        let unbonding = BaseData.instance.unbondingSumAmount()
        let reward = BaseData.instance.rewardAmount(KAVA_MAIN_DENOM)
        let total = available.adding(vesting).adding(delegated).adding(unbonding).adding(reward)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 6, 6)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 6, 6)
        delegatedAmount.attributedText = WUtils.displayAmount2(delegated.stringValue, delegatedAmount.font, 6, 6)
        unbondingAmount.attributedText = WUtils.displayAmount2(unbonding.stringValue, unbondingAmount.font, 6, 6)
        rewardAmount.attributedText = WUtils.displayAmount2(reward.stringValue, rewardAmount.font, 6, 6)
        totalValue.attributedText = WUtils.dpTokenValue(total, BaseData.instance.getLastPrice(), 6, totalValue.font)
        
        if (vesting.compare(NSDecimalNumber.zero).rawValue > 0) {
            vestingLayer.isHidden = false
            vestingAmount.attributedText = WUtils.displayAmount2(vesting.stringValue, vestingAmount.font!, 6, 6)
        }
        BaseData.instance.updateLastTotal(account, total.multiplying(byPowerOf10: -6).stringValue)
    }
    
}
