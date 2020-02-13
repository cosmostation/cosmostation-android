//
//  TxVoteCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxVoteCell: UITableViewCell {

    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var voterLabel: UILabel!
    @IBOutlet weak var proposalIdLabel: UILabel!
    @IBOutlet weak var opinionLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
