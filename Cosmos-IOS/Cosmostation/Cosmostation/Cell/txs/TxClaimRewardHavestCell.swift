//
//  TxClaimRewardHavestCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxClaimRewardHavestCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var sender: UILabel!
    @IBOutlet weak var receiver: UILabel!
    @IBOutlet weak var coinType: UILabel!
    @IBOutlet weak var multiplier: UILabel!
    @IBOutlet weak var depositType: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var rewardDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        rewardAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
