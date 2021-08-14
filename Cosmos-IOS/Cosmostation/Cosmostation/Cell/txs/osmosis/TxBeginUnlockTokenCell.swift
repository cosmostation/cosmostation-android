//
//  TxUnlockTokenCell.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/05.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import SwiftProtobuf

class TxBeginUnlockTokenCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txOwenerLabel: UILabel!
    @IBOutlet weak var txIdLabel: UILabel!
    @IBOutlet weak var txCompleteLabel: UILabel!
    @IBOutlet weak var txUnlockDenomLabel: UILabel!
    @IBOutlet weak var txUnlockAmountLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txUnlockAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Osmosis_Lockup_MsgBeginUnlocking.init(serializedData: response.tx.body.messages[position].value)
        
        txOwenerLabel.text = msg.owner
        txOwenerLabel.adjustsFontSizeToFitWidth = true
        
        txIdLabel.text = String(msg.id)
        
        var duration: Int64?
        if response.txResponse.logs.count > position {
            response.txResponse.logs[position].events.forEach { event in
                if (event.type == "begin_unlock") {
                    event.attributes.forEach { attribute in
                        if (attribute.key == "duration") {
                            let stringHour = attribute.value.components(separatedBy: "h")[0]
                            if let intHour = Int64(stringHour) {
                                duration = intHour * 60 * 60 * 1000
                            }
                        }
                    }
                }
            }
        }
        
        if duration != nil, let timeStamp = WUtils.newApiTimeToInt64(response.txResponse.timestamp)?.millisecondsSince1970 {
            let completeTime = timeStamp + duration!
            let localFormatter = DateFormatter()
            localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
            txCompleteLabel.text = localFormatter.string(from: Date.init(milliseconds: Int(completeTime)))
        } else {
            txCompleteLabel.text = "--"
        }
    }
}
