//
//  TxHtlcCreateCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxHtlcCreateCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var sendAmount: UILabel!
    @IBOutlet weak var sendDenom: UILabel!
    @IBOutlet weak var senderLabel: UILabel!
    @IBOutlet weak var recipientLabel: UILabel!
    @IBOutlet weak var randomHashLabel: UILabel!
    @IBOutlet weak var expectedAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        sendAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)

    }
    
}
