//
//  TxDepositHavestCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxDepositHavestCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var depositor: UILabel!
    @IBOutlet weak var depositType: UILabel!
    @IBOutlet weak var depositAmount: UILabel!
    @IBOutlet weak var depositDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        depositAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }

    
}
