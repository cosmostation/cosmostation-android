//
//  TokenDetailStakingCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/19.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailStakingCell: UITableViewCell {
    @IBOutlet weak var cardRoot: CardView!
    @IBOutlet weak var totalAmount: UILabel!
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
    
    func onBindStakingToken(_ chainType: ChainType) {
        let stakingDenom = WUtils.getMainDenom(chainType)
        let stakingDivideDecimal = WUtils.mainDivideDecimal(chainType)
        let stakingDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
        let totalToken = WUtils.getAllMainAsset(stakingDenom)
        totalAmount.attributedText = WUtils.displayAmount2(totalToken.stringValue, totalAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
        availableAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getAvailable_gRPC(stakingDenom), availableAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
        delegatedAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getDelegatedSum_gRPC(), delegatedAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
        unbondingAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getUnbondingSum_gRPC(), unbondingAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
        rewardAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getRewardSum_gRPC(stakingDenom), rewardAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
        
        let vesting = BaseData.instance.getVestingAmount_gRPC(stakingDenom)
        if (vesting.compare(NSDecimalNumber.zero).rawValue > 0) {
            vestingLayer.isHidden = false
            vestingAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getVesting_gRPC(stakingDenom), availableAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
        }
        cardRoot.backgroundColor = WUtils.getChainBg(chainType)
    }
    
}
