//
//  WalletKavaIncentiveCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/22.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpIncentiveCell: UITableViewCell {

    @IBOutlet weak var rootCard: CardView!
    @IBOutlet weak var incentiveSumAmount: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    
}
