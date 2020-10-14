//
//  HarvestListMyCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestListMyCell: UITableViewCell {
    
    @IBOutlet weak var harvestImg: UIImageView!
    @IBOutlet weak var harvestTitle: UILabel!
    @IBOutlet weak var rewardForSecond: UILabel!
    @IBOutlet weak var starTime: UILabel!
    @IBOutlet weak var endTime: UILabel!
    @IBOutlet weak var depositedAmount: UILabel!
    @IBOutlet weak var depositedDenom: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var rewardDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
