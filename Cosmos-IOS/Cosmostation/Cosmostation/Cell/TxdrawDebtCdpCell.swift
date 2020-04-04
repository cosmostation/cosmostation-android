//
//  TxdrawDebtCdpCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxdrawDebtCdpCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var senderLabel: UILabel!
    @IBOutlet weak var coinTypeLabel: UILabel!
    @IBOutlet weak var principalAmount: UILabel!
    @IBOutlet weak var principalDenom: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        principalAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
