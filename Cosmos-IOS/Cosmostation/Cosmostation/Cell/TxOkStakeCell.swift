//
//  TxOkStakeCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxOkStakeCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txLabel: UILabel!
    @IBOutlet weak var delegatorLabel: UILabel!
    @IBOutlet weak var stakeAmount: UILabel!
    @IBOutlet weak var stakeDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        stakeAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
}
