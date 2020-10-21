//
//  HarvestDetailTopCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestDetailTopCell: UITableViewCell {
    
    @IBOutlet weak var harvestImg: UIImageView!
    @IBOutlet weak var harvestTitle: UILabel!
    @IBOutlet weak var rewardForSecond: UILabel!
    @IBOutlet weak var eventTime: UILabel!
    @IBOutlet weak var totolDepositedAmount: UILabel!
    @IBOutlet weak var totolDepositedDenom: UILabel!
    @IBOutlet weak var totalDepositedValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
