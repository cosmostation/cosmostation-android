//
//  FarmCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class FarmCell: UITableViewCell {
    
    @IBOutlet weak var poolIDLabel: UILabel!
    @IBOutlet weak var poolPairLabel: UILabel!
    @IBOutlet weak var poolArpLabel: UILabel!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var availableDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        
    }
    
}
