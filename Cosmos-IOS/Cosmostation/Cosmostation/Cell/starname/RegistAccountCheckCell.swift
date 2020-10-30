//
//  RegistAccountCheckCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class RegistAccountCheckCell: UITableViewCell {
    
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var starnameFeeAmount: UILabel!
    @IBOutlet weak var starnameFeeDenom: UILabel!
    @IBOutlet weak var starnameLabel: UILabel!
    @IBOutlet weak var expireDate: UILabel!
    @IBOutlet weak var connectedAddressesLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
