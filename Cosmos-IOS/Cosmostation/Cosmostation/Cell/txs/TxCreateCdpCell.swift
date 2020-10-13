//
//  TxCreateCdpCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxCreateCdpCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var senderLabel: UILabel!
    @IBOutlet weak var collateralAmount: UILabel!
    @IBOutlet weak var collateralDenom: UILabel!
    @IBOutlet weak var principalAmount: UILabel!
    @IBOutlet weak var principalDenom: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        collateralAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        principalAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
