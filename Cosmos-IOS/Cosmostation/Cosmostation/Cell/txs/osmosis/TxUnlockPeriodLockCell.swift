//
//  TxUnlockPeriodLockCell.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/05.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxUnlockPeriodLockCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txOwenerLabel: UILabel!
    @IBOutlet weak var txIdLabel: UILabel!
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
        
        let msg = try! Osmosis_Lockup_MsgUnlockPeriodLock.init(serializedData: response.tx.body.messages[position].value)
        
        txOwenerLabel.text = msg.owner
        txOwenerLabel.adjustsFontSizeToFitWidth = true
        
        txIdLabel.text = String(msg.id)
        
        var coin: Coin?
        if response.txResponse.logs.count > position {
            response.txResponse.logs[position].events.forEach { event in
                if (event.type == "transfer") {
                    event.attributes.forEach { attribute in
                        if (attribute.key == "amount") {
                            if let range = attribute.value.range(of: "[0-9]*", options: .regularExpression){
                                let amount = String(attribute.value[range])
                                coin = Coin.init(attribute.value.replacingOccurrences(of: amount, with: ""), amount)
                            }
                        }
                    }
                }
            }
        }
        if (coin != nil) {
            WUtils.showCoinDp(coin!, txUnlockDenomLabel, txUnlockAmountLabel, chain)
        }
        
    }
}
