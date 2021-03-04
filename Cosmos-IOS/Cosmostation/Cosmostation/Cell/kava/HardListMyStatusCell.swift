//
//  HardListMyStatusCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class HardListMyStatusCell: UITableViewCell {
    @IBOutlet weak var totalDepositedValue: UILabel!
    @IBOutlet weak var maxBorrowableValue: UILabel!
    @IBOutlet weak var totalBorrowedValue: UILabel!
    @IBOutlet weak var remainingBorrowableValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        totalDepositedValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        maxBorrowableValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        totalBorrowedValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        remainingBorrowableValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
}
