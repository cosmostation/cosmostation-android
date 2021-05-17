//
//  TokenDetailCrytoCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/04/15.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailCrytoCell: TokenDetailCell {
    @IBOutlet weak var cardRoot: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var delegatedAmount: UILabel!
    @IBOutlet weak var unbondingAmount: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var vestingAmount: UILabel!
    @IBOutlet weak var vestingLayer: UIView!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        delegatedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        rewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    override func prepareForReuse() {
        vestingLayer.isHidden = true
    }
    
    override func onBindToken() {
        let totalToken = WUtils.getAllMainAsset(CRYPTO_MAIN_DENOM)
        totalAmount.attributedText = WUtils.displayAmount2(totalToken.stringValue, totalAmount.font!, 8, 8)
        totalValue.attributedText = WUtils.dpAmountValue(CRYPTO_MAIN_DENOM, totalToken, 8, totalValue.font)
        availableAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getAvailable(CRYPTO_MAIN_DENOM), availableAmount.font!, 8, 8)
        delegatedAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getDelegatedSum(), delegatedAmount.font!, 8, 8)
        unbondingAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getUnbondingSum(), unbondingAmount.font, 8, 8)
        rewardAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getRewardSum(CRYPTO_MAIN_DENOM), rewardAmount.font, 8, 8)
        let vesting = BaseData.instance.getVestingAmount(CRYPTO_MAIN_DENOM)
        if (vesting.compare(NSDecimalNumber.zero).rawValue > 0) {
            vestingLayer.isHidden = false
            vestingAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getVesting(PERSIS_MAIN_DENOM), availableAmount.font!, 8, 8)
        }
    }
    
}
