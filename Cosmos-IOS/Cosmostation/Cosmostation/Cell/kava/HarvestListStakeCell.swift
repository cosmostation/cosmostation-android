//
//  HarvestListStakeCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestListStakeCell: UITableViewCell {
    
    @IBOutlet weak var harvestImg: UIImageView!
    @IBOutlet weak var harvestTitle: UILabel!
    @IBOutlet weak var rewardForSecond: UILabel!
    @IBOutlet weak var eventTime: UILabel!
    @IBOutlet weak var stakeAmount: UILabel!
    @IBOutlet weak var stakeDenom: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var rewardDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionClaim: (() -> Void)? = nil
    @IBAction func onClickClaim(_ sender: Any) {
        actionClaim?()
    }
}
