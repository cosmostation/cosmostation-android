//
//  CdpDetailAssetsCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpDetailAssetsCell: UITableViewCell {

    @IBOutlet weak var collateralImg: UIImageView!
    @IBOutlet weak var collateralDenom: UILabel!
    @IBOutlet weak var collateralAmount: UILabel!
    @IBOutlet weak var collateralValue: UILabel!
    @IBOutlet weak var principalImg: UIImageView!
    @IBOutlet weak var principalDenom: UILabel!
    @IBOutlet weak var principalAmount: UILabel!
    @IBOutlet weak var principalValue: UILabel!
    @IBOutlet weak var kavaAmount: UILabel!
    @IBOutlet weak var kavaValue: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        collateralAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        principalAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        kavaAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
        collateralValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        principalValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        kavaValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
    }
    
}
