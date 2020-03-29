//
//  CdpDetailTopCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpDetailTopCell: UITableViewCell {
    
    @IBOutlet weak var marketImg: UIImageView!
    @IBOutlet weak var marketTitle: UILabel!
    @IBOutlet weak var minCollateralRate: UILabel!
    @IBOutlet weak var stabilityFee: UILabel!
    @IBOutlet weak var liquidationPenalty: UILabel!
    @IBOutlet weak var currentPriceTitle: UILabel!
    @IBOutlet weak var currentPrice: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        stabilityFee.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidationPenalty.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        currentPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
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
        helpLiquidationPenalty?()
    }
}
