//
//  EarnUnbondingCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/15.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class EarnUnbondingCell: UITableViewCell {
    
    @IBOutlet weak var lockIdLabel: UILabel!
    @IBOutlet weak var lcokStatusLabel: UILabel!
    @IBOutlet weak var unBondingCompleteTimeLabel: UILabel!
    @IBOutlet weak var unBondingCompleteGapLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountValueLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        lockIdLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lcokStatusLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingCompleteTimeLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        amountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        nextRewardAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView() {
        
    }
    
}
