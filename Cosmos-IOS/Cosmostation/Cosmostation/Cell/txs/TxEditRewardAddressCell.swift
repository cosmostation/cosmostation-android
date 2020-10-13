//
//  TxEditRewardAddressCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxEditRewardAddressCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var delegatorLabel: UILabel!
    @IBOutlet weak var widthrawAddressLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
}
