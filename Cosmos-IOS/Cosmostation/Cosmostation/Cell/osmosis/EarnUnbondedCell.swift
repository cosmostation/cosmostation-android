//
//  EarnUnbondedCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/15.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class EarnUnbondedCell: UITableViewCell {
    
    @IBOutlet weak var lockIdLabel: UILabel!
    @IBOutlet weak var lcokStatusLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountValueLabel: UILabel!
    @IBOutlet weak var nextRewardAmountLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    var actionUnlock: (() -> Void)? = nil
    @IBAction func onClickUnlock(_ sender: UIButton) {
        actionUnlock?()
    }
    
    func onBindView() {
        
    }
}
