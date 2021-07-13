//
//  WalletOsmoCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/22.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class WalletOsmoCell: UITableViewCell {
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
    
    var actionDelegate: (() -> Void)? = nil
    var actionVote: (() -> Void)? = nil
    var actionLab: (() -> Void)? = nil
    
    @IBAction func onClickDelegate(_ sender: Any) {
        actionDelegate?()
    }
    @IBAction func onClickVote(_ sender: Any) {
        actionVote?()
    }
    @IBAction func onClickLab(_ sender: Any) {
        actionLab?()
    }
    
    override func prepareForReuse() {
        vestingLayer.isHidden = true
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        let totalToken = WUtils.getAllMainAsset(OSMOSIS_MAIN_DENOM)
        totalAmount.attributedText = WUtils.displayAmount2(totalToken.stringValue, totalAmount.font!, 6, 6)
        totalValue.attributedText = WUtils.dpUserCurrencyValue(OSMOSIS_MAIN_DENOM, totalToken, 6, totalValue.font)
        availableAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getAvailable_gRPC(OSMOSIS_MAIN_DENOM), availableAmount.font!, 6, 6)
        delegatedAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getDelegatedSum_gRPC(), delegatedAmount.font!, 6, 6)
        unbondingAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getUnbondingSum_gRPC(), unbondingAmount.font, 6, 6)
        rewardAmount.attributedText = WUtils.displayAmount2(BaseData.instance.getRewardSum_gRPC(OSMOSIS_MAIN_DENOM), rewardAmount.font, 6, 6)
        BaseData.instance.updateLastTotal(account, totalToken.multiplying(byPowerOf10: -6).stringValue)
    }
    
}
