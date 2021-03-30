//
//  WalletVestingDetailCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/04.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TokenDetailVestingDetailCell: TokenDetailCell {

    @IBOutlet weak var rootCardView: CardView!
    @IBOutlet weak var vestingCntLabel: UILabel!
    @IBOutlet weak var vestingTotalAmount: UILabel!
    
    @IBOutlet weak var vestingLayer0: UIView!
    @IBOutlet weak var vestingTime0: UILabel!
    @IBOutlet weak var vestingGap0: UILabel!
    @IBOutlet weak var vestingAmount0: UILabel!
    
    @IBOutlet weak var vestingLayer1: UIView!
    @IBOutlet weak var vestingTime1: UILabel!
    @IBOutlet weak var vestingGap1: UILabel!
    @IBOutlet weak var vestingAmount1: UILabel!
    
    @IBOutlet weak var vestingLayer2: UIView!
    @IBOutlet weak var vestingTime2: UILabel!
    @IBOutlet weak var vestingGap2: UILabel!
    @IBOutlet weak var vestingAmount2: UILabel!
    
    @IBOutlet weak var vestingLayer3: UIView!
    @IBOutlet weak var vestingTime3: UILabel!
    @IBOutlet weak var vestingGap3: UILabel!
    @IBOutlet weak var vestingAmount3: UILabel!
    
    @IBOutlet weak var vestingLayer4: UIView!
    @IBOutlet weak var vestingTime4: UILabel!
    @IBOutlet weak var vestingGap4: UILabel!
    @IBOutlet weak var vestingAmount4: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        vestingTotalAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount0.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount1.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount2.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount3.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount4.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.vestingLayer4.isHidden = true
        self.vestingLayer3.isHidden = true
        self.vestingLayer2.isHidden = true
        self.vestingLayer1.isHidden = true
    }
    
    func onBindVesting(_ chainType: ChainType, _ denom: String) {
        rootCardView.backgroundColor = WUtils.getChainBg(chainType)
        if (WUtils.isGRPC(chainType)) {
            onBindGRPCTokenVesting(chainType, denom)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            onBindTokenVesting(chainType, denom)
        }
    }
    
    func onBindGRPCTokenVesting(_ chainType: ChainType, _ denom: String) {
        let baseData = BaseData.instance
        let vps = baseData.onParseRemainVestingsByDenom(denom)
        vestingCntLabel.text = "(" + String(vps.count) + ")"
        vestingTotalAmount.attributedText = WUtils.displayAmount2(baseData.onParseRemainVestingsAmountSumByDenom(denom).stringValue, vestingTotalAmount.font!, 6, 6)
        
        vestingTime0.text = WUtils.longTimetoString(input: vps[0].length)
        vestingGap0.text = WUtils.getUnbondingTimeleft(vps[0].length)
        vestingAmount0.attributedText = WUtils.displayAmount2(WUtils.getAmountVp(vps[0], denom).stringValue, vestingAmount0.font!, 6, 6)
        
        if (vps.count > 1) {
            vestingLayer1.isHidden = false
            vestingTime1.text = WUtils.longTimetoString(input: vps[1].length)
            vestingGap1.text = WUtils.getUnbondingTimeleft(vps[1].length)
            vestingAmount1.attributedText = WUtils.displayAmount2(WUtils.getAmountVp(vps[1], denom).stringValue, vestingAmount0.font!, 6, 6)
        }
        if (vps.count > 2) {
            vestingLayer2.isHidden = false
            vestingTime2.text = WUtils.longTimetoString(input: vps[2].length)
            vestingGap2.text = WUtils.getUnbondingTimeleft(vps[2].length)
            vestingAmount2.attributedText = WUtils.displayAmount2(WUtils.getAmountVp(vps[2], denom).stringValue, vestingAmount0.font!, 6, 6)
        }
        if (vps.count > 3) {
            vestingLayer3.isHidden = false
            vestingTime3.text = WUtils.longTimetoString(input: vps[3].length)
            vestingGap3.text = WUtils.getUnbondingTimeleft(vps[3].length)
            vestingAmount3.attributedText = WUtils.displayAmount2(WUtils.getAmountVp(vps[3], denom).stringValue, vestingAmount0.font!, 6, 6)
        }
        if (vps.count > 4) {
            vestingLayer4.isHidden = false
            vestingTime4.text = WUtils.longTimetoString(input: vps[4].length)
            vestingGap4.text = WUtils.getUnbondingTimeleft(vps[4].length)
            vestingAmount4.attributedText = WUtils.displayAmount2(WUtils.getAmountVp(vps[4], denom).stringValue, vestingAmount0.font!, 6, 6)
        }
    
    }
    
    func onBindTokenVesting(_ chainType: ChainType, _ denom: String) {
        
    }
}
