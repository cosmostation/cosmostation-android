//
//  CdpDetailMyTopCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpDetailMyTopCell: UITableViewCell {
    
    @IBOutlet weak var marketImg: UIImageView!
    @IBOutlet weak var marketTitle: UILabel!
    @IBOutlet weak var riskRateImg: UIImageView!
    @IBOutlet weak var riskScore: UILabel!
    @IBOutlet weak var debtValueTitle: UILabel!
    @IBOutlet weak var debtValue: UILabel!
    @IBOutlet weak var collateralValueTitle: UILabel!
    @IBOutlet weak var collateralValue: UILabel!
    
    @IBOutlet weak var minCollateralRate: UILabel!
    @IBOutlet weak var stabilityFee: UILabel!
    @IBOutlet weak var liquidationPenalty: UILabel!
    @IBOutlet weak var currentPriceTitle: UILabel!
    @IBOutlet weak var currentPrice: UILabel!
    @IBOutlet weak var liquidationPriceTitle: UILabel!
    @IBOutlet weak var liquidationPrice: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        
        debtValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        collateralValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        minCollateralRate.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        stabilityFee.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidationPenalty.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        currentPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidationPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var helpCollateralRate: (() -> Void)? = nil
    var helpStabilityFee: (() -> Void)? = nil
    var helpLiquidationPenalty: (() -> Void)? = nil
    
    @IBAction func onClickCollateralRate(_ sender: UIButton) {
        helpCollateralRate?()
    }
    
    @IBAction func onClickStabilityFee(_ sender: UIButton) {
        helpStabilityFee?()
    }
    
    @IBAction func onClickLiquidationPenalty(_ sender: UIButton) {
        helpStabilityFee?()
    }
}
