//
//  TxRewardCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRewardCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var delegatorLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        amountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, amountDenomLabel)
    }
}
