//
//  TokenStakingOldCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/20.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenStakingOldCell: UITableViewCell {
    
    @IBOutlet weak var cardRoot: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var vestingLayer: UIView!
    @IBOutlet weak var vestingAmount: UILabel!
    @IBOutlet weak var delegatedLayer: UIView!
    @IBOutlet weak var delegatedAmount: UILabel!
    @IBOutlet weak var unbondingLayer: UIView!
    @IBOutlet weak var unbondingAmount: UILabel!
    @IBOutlet weak var rewardLayer: UIView!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var lockedLayer: UIView!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var frozenLayer: UIView!
    @IBOutlet weak var frozenAmount: UILabel!
    @IBOutlet weak var okStakingLayer: UIView!
    @IBOutlet weak var okStakingAmount: UILabel!
    @IBOutlet weak var okUnbondingLayer: UIView!
    @IBOutlet weak var okUnbondingAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        delegatedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        rewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        frozenAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        okStakingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        okUnbondingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
    }
    
    func onBindStakingToken(_ chainType: ChainType) {
        let stakingDenom = WUtils.getMainDenom(chainType)
        let stakingDivideDecimal = WUtils.mainDivideDecimal(chainType)
        let stakingDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
        
        if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            vestingLayer.isHidden = true
            delegatedLayer.isHidden = true
            unbondingLayer.isHidden = true
            rewardLayer.isHidden = true
            lockedLayer.isHidden = false
            frozenLayer.isHidden = false
            okStakingLayer.isHidden = true
            okUnbondingLayer.isHidden = true
            
            let total = WUtils.getAllBnbToken(stakingDenom)
            let available = BaseData.instance.availableAmount(stakingDenom)
            let locked = BaseData.instance.lockedAmount(stakingDenom)
            let frozen = BaseData.instance.frozenAmount(stakingDenom)
            totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
            availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, lockedAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            frozenAmount.attributedText = WUtils.displayAmount2(frozen.stringValue, frozenAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            vestingLayer.isHidden = true
            delegatedLayer.isHidden = true
            unbondingLayer.isHidden = true
            rewardLayer.isHidden = true
            lockedLayer.isHidden = true
            frozenLayer.isHidden = true
            okStakingLayer.isHidden = false
            okUnbondingLayer.isHidden = false
            
            let total = WUtils.getAllExToken(stakingDenom)
            let available = BaseData.instance.availableAmount(stakingDenom)
            let locked = BaseData.instance.lockedAmount(stakingDenom)
            let deposit = BaseData.instance.okDepositAmount()
            let withdraw = BaseData.instance.okWithdrawAmount()
            totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
            availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, lockedAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            okStakingAmount.attributedText = WUtils.displayAmount2(deposit.stringValue, okStakingAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            okUnbondingAmount.attributedText = WUtils.displayAmount2(withdraw.stringValue, okUnbondingAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            
        } else {
            delegatedLayer.isHidden = false
            unbondingLayer.isHidden = false
            rewardLayer.isHidden = false
            lockedLayer.isHidden = true
            frozenLayer.isHidden = true
            okStakingLayer.isHidden = true
            okUnbondingLayer.isHidden = true
            
            let total = WUtils.getAllMainAssetOld(stakingDenom)
            let available = BaseData.instance.availableAmount(stakingDenom)
            let delegated = BaseData.instance.delegatedSumAmount()
            let unbonding = BaseData.instance.unbondingSumAmount()
            let reward = BaseData.instance.rewardAmount(stakingDenom)
            totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
            availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            delegatedAmount.attributedText = WUtils.displayAmount2(delegated.stringValue, delegatedAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            unbondingAmount.attributedText = WUtils.displayAmount2(unbonding.stringValue, unbondingAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            rewardAmount.attributedText = WUtils.displayAmount2(reward.stringValue, rewardAmount.font, stakingDivideDecimal, stakingDisplayDecimal)
            
            let vesting = BaseData.instance.lockedAmount(stakingDenom)
            if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                if (vesting.compare(NSDecimalNumber.zero).rawValue > 0) {
                    vestingLayer.isHidden = false
                    vestingAmount.attributedText = WUtils.displayAmount2(vesting.stringValue, vestingAmount.font!, stakingDivideDecimal, stakingDisplayDecimal)
                }
            }
            
        }
        cardRoot.backgroundColor = WUtils.getChainBg(chainType)
    }
}
