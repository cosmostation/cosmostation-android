//
//  AccountCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class AccountCell: UITableViewCell {
    @IBOutlet weak var starNameLabel: UILabel!
    @IBOutlet weak var accountConnectedAddressLabel: UILabel!
    @IBOutlet weak var accountExpireTime: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
