//
//  TxUndelegateCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxUndelegateCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var undelegatorLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var undelegateAmountLabel: UILabel!
    @IBOutlet weak var undelegateDenomLabel: UILabel!
    @IBOutlet weak var autoRewardLayer: UIView!
    @IBOutlet weak var autoRewardAmountLabel: UILabel!
    @IBOutlet weak var autoRewardDenomLabel: UILabel!
    
    @IBOutlet weak var autoRewardBottomConstraint: NSLayoutConstraint!
    @IBOutlet weak var feeBottomConstraint: NSLayoutConstraint!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        undelegateAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        autoRewardAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, undelegateDenomLabel)
        WUtils.setDenomTitle(chainType, autoRewardDenomLabel)
    }
    
}
