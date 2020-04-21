//
//  TxHtlcRefundCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxHtlcRefundCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var fromAddress: UILabel!
    @IBOutlet weak var swapIdLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
