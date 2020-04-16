//
//  HtlcResultClaimCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HtlcResultClaimCell: UITableViewCell {
    
    @IBOutlet weak var claimImg: UIImageView!
    @IBOutlet weak var resultImg: UIImageView!
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var errorMsg: EdgeInsetLabel!
    @IBOutlet weak var blockHeightLabel: UILabel!
    @IBOutlet weak var txHashLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    @IBOutlet weak var receivedAmountLabel: UILabel!
    @IBOutlet weak var receivedDenom: UILabel!
    @IBOutlet weak var feeLabel: UILabel!
    @IBOutlet weak var feeDenomLabel: UILabel!
    @IBOutlet weak var claimerAddress: UILabel!
    @IBOutlet weak var randomNumberLabel: UILabel!
    @IBOutlet weak var swapIdLabel: UILabel!
    
    @IBOutlet weak var failConstraint: NSLayoutConstraint!
    @IBOutlet weak var successConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        receivedAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        feeLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
