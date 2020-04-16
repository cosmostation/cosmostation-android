//
//  TxHtlcClaimCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxHtlcClaimCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var claimAmount: UILabel!
    @IBOutlet weak var claimDenom: UILabel!
    @IBOutlet weak var claimerAddress: UILabel!
    @IBOutlet weak var randomNumberLabel: UILabel!
    @IBOutlet weak var swapIdLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        claimAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
