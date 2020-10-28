//
//  TxRegisterDomainCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRegisterDomainCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTitleLabel: UILabel!
    @IBOutlet weak var domainLabel: UILabel!
    @IBOutlet weak var adminLabel: UILabel!
    @IBOutlet weak var domainTypeLabel: UILabel!
    @IBOutlet weak var starnameFeeAmountLabel: UILabel!
    @IBOutlet weak var starnameFeeDenomLabel: UILabel!
    

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        starnameFeeAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
