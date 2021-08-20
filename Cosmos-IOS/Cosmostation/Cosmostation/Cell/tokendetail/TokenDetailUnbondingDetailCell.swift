//
//  WalletUnbondingInfoCellTableViewCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/02.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TokenDetailUnbondingDetailCell: UITableViewCell {
    
    @IBOutlet weak var unBondingCard: CardView!
    @IBOutlet weak var unBondingCnt: UILabel!
    
    @IBOutlet weak var unBondingLayer0: UIView!
    @IBOutlet weak var unBondingTime0: UILabel!
    @IBOutlet weak var unBondingMoniker0: UILabel!
    @IBOutlet weak var unBondingAmount0: UILabel!
    
    @IBOutlet weak var unBondingLayer1: UIView!
    @IBOutlet weak var unBondingTime1: UILabel!
    @IBOutlet weak var unBondingMoniker1: UILabel!
    @IBOutlet weak var unBondingAmount1: UILabel!
    
    @IBOutlet weak var unBondingLayer2: UIView!
    @IBOutlet weak var unBondingTime2: UILabel!
    @IBOutlet weak var unBondingMoniker2: UILabel!
    @IBOutlet weak var unBondingAmount2: UILabel!
    
    @IBOutlet weak var unBondingLayer3: UIView!
    @IBOutlet weak var unBondingTime3: UILabel!
    @IBOutlet weak var unBondingMoniker3: UILabel!
    @IBOutlet weak var unBondingAmount3: UILabel!
    
    @IBOutlet weak var unBondingLayer4: UIView!
    @IBOutlet weak var unBondingTime4: UILabel!
    @IBOutlet weak var unBondingMoniker4: UILabel!
    @IBOutlet weak var unBondingAmount4: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        unBondingAmount0.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount1.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount2.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount3.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount4.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.unBondingLayer1.isHidden = true
        self.unBondingLayer2.isHidden = true
        self.unBondingLayer3.isHidden = true
        self.unBondingLayer4.isHidden = true
    }
    
    func onBindUnbondingToken(_ chainType: ChainType) {
        unBondingCard.backgroundColor = WUtils.getChainBg(chainType)
        if (WUtils.isGRPC(chainType)) {
            onBindVesting_gRPC(chainType)
        } else {
            
        }
    }
    
    func onBindVesting_gRPC(_ chainType: ChainType) {
        let stakingDivideDecimal = WUtils.mainDivideDecimal(chainType)
        let stakingDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
        let unbondingEntries = BaseData.instance.getUnbondingEntrie_gRPC()
        unBondingCnt.text = String(unbondingEntries.count)
        
        unBondingTime0.text = WUtils.longTimetoString3(unbondingEntries[0].completionTime.seconds * 1000)
        unBondingMoniker0.text = WUtils.getUnbondingTimeleft(unbondingEntries[0].completionTime.seconds * 1000)
        unBondingAmount0.attributedText = WUtils.displayAmount2(unbondingEntries[0].balance, unBondingAmount0.font!, stakingDivideDecimal, stakingDisplayDecimal)
        
        if (unbondingEntries.count > 1) {
            unBondingLayer1.isHidden = false
            unBondingTime1.text = WUtils.longTimetoString3(unbondingEntries[1].completionTime.seconds * 1000)
            unBondingMoniker1.text = WUtils.getUnbondingTimeleft(unbondingEntries[1].completionTime.seconds * 1000)
            unBondingAmount1.attributedText = WUtils.displayAmount2(unbondingEntries[1].balance, unBondingAmount1.font!, stakingDivideDecimal, stakingDisplayDecimal)
        }
        if (unbondingEntries.count > 2) {
            unBondingLayer2.isHidden = false
            unBondingTime2.text = WUtils.longTimetoString3(unbondingEntries[2].completionTime.seconds * 1000)
            unBondingMoniker2.text = WUtils.getUnbondingTimeleft(unbondingEntries[2].completionTime.seconds * 1000)
            unBondingAmount2.attributedText = WUtils.displayAmount2(unbondingEntries[2].balance, unBondingAmount2.font!, stakingDivideDecimal, stakingDisplayDecimal)
        }
        if (unbondingEntries.count > 3) {
            unBondingLayer3.isHidden = false
            unBondingTime3.text = WUtils.longTimetoString3(unbondingEntries[3].completionTime.seconds * 1000)
            unBondingMoniker3.text = WUtils.getUnbondingTimeleft(unbondingEntries[3].completionTime.seconds * 1000)
            unBondingAmount3.attributedText = WUtils.displayAmount2(unbondingEntries[3].balance, unBondingAmount3.font!, stakingDivideDecimal, stakingDisplayDecimal)
        }
        if (unbondingEntries.count > 4) {
            unBondingLayer4.isHidden = false
            unBondingTime4.text = WUtils.longTimetoString3(unbondingEntries[4].completionTime.seconds * 1000)
            unBondingMoniker4.text = WUtils.getUnbondingTimeleft(unbondingEntries[4].completionTime.seconds * 1000)
            unBondingAmount4.attributedText = WUtils.displayAmount2(unbondingEntries[4].balance, unBondingAmount4.font!, stakingDivideDecimal, stakingDisplayDecimal)
        }
    }
}
