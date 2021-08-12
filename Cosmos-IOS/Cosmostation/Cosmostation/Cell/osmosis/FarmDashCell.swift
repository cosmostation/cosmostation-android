//
//  FarmDashCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class FarmDashCell: UITableViewCell {

    @IBOutlet weak var totalFarmValueLabel: UILabel!
    @IBOutlet weak var totalUnbondingValueLabe: UILabel!
    @IBOutlet weak var totalUnbondedValueLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
}
