//
//  MyFarmCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class MyFarmCell: UITableViewCell {
    
    @IBOutlet weak var poolIDLabel: UILabel!
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var poolArpLabel: UILabel!
    @IBOutlet weak var farmingAmountLabel: UILabel!
    @IBOutlet weak var farmingDenomLabel: UILabel!
    @IBOutlet weak var unbondingAmountLabel: UILabel!
    @IBOutlet weak var unbondingDenomLabel: UILabel!
    @IBOutlet weak var unbondedAmountLabel: UILabel!
    @IBOutlet weak var unbondedDenomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        farmingAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondingAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unbondedAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
}
