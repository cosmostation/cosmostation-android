//
//  TxWithdrawHavestCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxWithdrawHavestCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var depositor: UILabel!
    @IBOutlet weak var depositType: UILabel!
    @IBOutlet weak var withdrawAmount: UILabel!
    @IBOutlet weak var withdrawDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        withdrawAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
