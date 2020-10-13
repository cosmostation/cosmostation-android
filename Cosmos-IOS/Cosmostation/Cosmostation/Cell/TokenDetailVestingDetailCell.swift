//
//  WalletVestingDetailCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/04.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TokenDetailVestingDetailCell: UITableViewCell {

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
}
