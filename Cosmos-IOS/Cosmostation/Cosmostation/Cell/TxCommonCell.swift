//
//  TxCommonCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxCommonCell: UITableViewCell {

    @IBOutlet weak var statusImg: UIImageView!
    @IBOutlet weak var statusLabel: UILabel!
    @IBOutlet weak var heightLabel: UILabel!
    @IBOutlet weak var msgCntLabel: UILabel!
    @IBOutlet weak var gasAmountLabel: UILabel!
    
    @IBOutlet weak var feeLayer: UIView!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeDenomLabel: UILabel!
    @IBOutlet weak var usedFeeLayer: UIView!
    @IBOutlet weak var usedFeeAmountLabel: UILabel!
    @IBOutlet weak var usedFeeDenomLabel: UILabel!
    @IBOutlet weak var limitFeeLayer: UIView!
    @IBOutlet weak var limitFeeAmountLabel: UILabel!
    @IBOutlet weak var limitFeeDenomLabel: UILabel!
    
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var timeGapLabel: UILabel!
    @IBOutlet weak var hashLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        statusLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        heightLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        msgCntLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        gasAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        feeAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        usedFeeAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        limitFeeAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        timeLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        timeGapLabel.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, feeDenomLabel)
        WUtils.setDenomTitle(chainType, usedFeeDenomLabel)
        WUtils.setDenomTitle(chainType, limitFeeDenomLabel)
    }
    
}
