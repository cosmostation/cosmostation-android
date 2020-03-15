//
//  TxPostPriceCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxPostPriceCell: UITableViewCell {

    @IBOutlet weak var pricePoster: UILabel!
    @IBOutlet weak var marketId: UILabel!
    @IBOutlet weak var postPrice: UILabel!
    @IBOutlet weak var validityTime: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

}
