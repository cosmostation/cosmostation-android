//
//  HarvestListRewardCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestListRewardCell: UITableViewCell {

    @IBOutlet weak var rewardSumAmount: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    
    var actionClaim: (() -> Void)? = nil
    @IBAction func onClickReward(_ sender: UIButton) {
        actionClaim?()
    }
}
