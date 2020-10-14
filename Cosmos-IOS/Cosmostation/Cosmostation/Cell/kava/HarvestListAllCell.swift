//
//  HarvestListAllCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestListAllCell: UITableViewCell {
    
    @IBOutlet weak var harvestImg: UIImageView!
    @IBOutlet weak var harvestTitle: UILabel!
    @IBOutlet weak var rewardForSecond: UILabel!
    @IBOutlet weak var starTime: UILabel!
    @IBOutlet weak var endTime: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
