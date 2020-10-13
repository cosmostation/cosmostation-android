//
//  TxIncentiveTableViewCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/22.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxIncentiveTableViewCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var rewardAmountDenom: UILabel!
    @IBOutlet weak var senderLabel: UILabel!
    @IBOutlet weak var coinTypeLabel: UILabel!
    @IBOutlet weak var multiplierLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
