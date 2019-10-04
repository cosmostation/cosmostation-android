//
//  TokenDetailHeaderCosmosCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class TokenDetailHeaderCosmosCell: UITableViewCell {
    
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var delegatedAmount: UILabel!
    @IBOutlet weak var unbondingAmount: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    var actionSend: (() -> Void)? = nil
    
    @IBAction func onClickSend(_ sender: Any) {
        actionSend?()
    }
}
