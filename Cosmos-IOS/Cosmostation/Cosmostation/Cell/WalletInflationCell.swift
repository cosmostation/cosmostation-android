//
//  WalletInflationCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletInflationCell: UITableViewCell {
    
    @IBOutlet weak var infaltionLabel: UILabel!
    @IBOutlet weak var yieldLabel: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        infaltionLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        yieldLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
}
