//
//  TxOkDirectVoteCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxOkDirectVoteCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txLabel: UILabel!
    @IBOutlet weak var voterLabel: UILabel!
    @IBOutlet weak var validatorList: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
