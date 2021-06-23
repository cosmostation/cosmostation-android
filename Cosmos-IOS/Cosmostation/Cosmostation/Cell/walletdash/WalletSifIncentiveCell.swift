//
//  WalletSifIncentiveCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/22.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class WalletSifIncentiveCell: UITableViewCell {
    @IBOutlet weak var currentVsAmount: UILabel!
    @IBOutlet weak var currentLmAmount: UILabel!
    @IBOutlet weak var maxVsAmount: UILabel!
    @IBOutlet weak var maxLmAmount: UILabel!
    @IBOutlet weak var maxDate: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        currentVsAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        currentLmAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        maxVsAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        maxLmAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        maxDate.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
        currentVsAmount.attributedText = WUtils.displayAmount2("0", currentVsAmount.font, 0, 6)
        currentLmAmount.attributedText = WUtils.displayAmount2("0", currentLmAmount.font, 0, 6)
        maxVsAmount.attributedText = WUtils.displayAmount2("0", maxVsAmount.font, 0, 6)
        maxLmAmount.attributedText = WUtils.displayAmount2("0", maxLmAmount.font, 0, 6)
        maxDate.text = "--"
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        let baseData = BaseData.instance
        if let vsCurrentAmount = baseData.mSifVsIncentive?.user?.totalClaimableCommissionsAndClaimableRewards {
            currentVsAmount.attributedText = WUtils.displayAmount2(String(vsCurrentAmount), currentVsAmount.font, 0, 6)
        }
        if let lmCurrentAmount = baseData.mSifLmIncentive?.user?.totalClaimableCommissionsAndClaimableRewards {
            currentLmAmount.attributedText = WUtils.displayAmount2(String(lmCurrentAmount), currentLmAmount.font, 0, 6)
        }
        
        if let vsMaxAmount = baseData.mSifVsIncentive?.user?.totalRewardsOnDepositedAssetsAtMaturity {
            maxVsAmount.attributedText = WUtils.displayAmount2(String(vsMaxAmount), maxVsAmount.font, 0, 6)
        }
        if let lmMaxAmount = baseData.mSifLmIncentive?.user?.totalRewardsOnDepositedAssetsAtMaturity {
            maxLmAmount.attributedText = WUtils.displayAmount2(String(lmMaxAmount), maxVsAmount.font, 0, 6)
        }
        
        if let finishDate = baseData.mSifVsIncentive?.user?.maturityDateISO {
            maxDate.text = WUtils.sifNodeTimeToString(finishDate)
        }
        if let finishDate = baseData.mSifLmIncentive?.user?.maturityDateISO {
            maxDate.text = WUtils.sifNodeTimeToString(finishDate)
        }
    }
}
