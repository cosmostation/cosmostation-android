//
//  HtlcResultSentCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HtlcResultSentCell: UITableViewCell {
    
    @IBOutlet weak var sendImg: UIImageView!
    @IBOutlet weak var resultImg: UIImageView!
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var errorMsg: EdgeInsetLabel!
    @IBOutlet weak var blockHeightLabel: UILabel!
    @IBOutlet weak var txHashLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var sentAmountLabel: UILabel!
    @IBOutlet weak var sentDenom: UILabel!
    @IBOutlet weak var feeLabel: UILabel!
    @IBOutlet weak var senderLabel: UILabel!
    @IBOutlet weak var relayRecipientLabel: UILabel!
    @IBOutlet weak var relaySenderLabel: UILabel!
    @IBOutlet weak var recipientLabel: UILabel!
    @IBOutlet weak var randomHashLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        sentAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        feeLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
