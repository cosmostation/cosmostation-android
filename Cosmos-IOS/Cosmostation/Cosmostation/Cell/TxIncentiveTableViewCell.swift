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
    @IBOutlet weak var senderLabel: UILabel!
    @IBOutlet weak var coinTypeLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
