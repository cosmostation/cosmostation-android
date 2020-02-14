//
//  TxRedelegateCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRedelegateCell: UITableViewCell {

    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var redelegatorLabel: UILabel!
    @IBOutlet weak var fromValidatorLabel: UILabel!
    @IBOutlet weak var fromMonikerLabel: UILabel!
    @IBOutlet weak var toValidatorLabel: UILabel!
    @IBOutlet weak var toMonikerLabel: UILabel!
    @IBOutlet weak var redelegateAmountLabel: UILabel!
    @IBOutlet weak var redelegateDenomLabel: UILabel!
    @IBOutlet weak var autoRewardLayer: UIView!
    @IBOutlet weak var autoRewardAmountLabel: UILabel!
    @IBOutlet weak var autoRewardDenomLabel: UILabel!

    @IBOutlet weak var autoRewardBottomConstraint: NSLayoutConstraint!
    @IBOutlet weak var feeBottomConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        redelegateAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        autoRewardAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, redelegateDenomLabel)
        WUtils.setDenomTitle(chainType, autoRewardDenomLabel)
    }
    
    
}
