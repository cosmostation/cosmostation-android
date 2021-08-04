//
//  TxRizonEventHorizonCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxRizonEventHorizonCell: TxCell {
    
    @IBOutlet weak var legacyChainHashLabel: UILabel!
    @IBOutlet weak var recipientLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        amountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        
        let msg = try! Rizonworld_Rizon_Tokenswap_MsgCreateTokenswapRequest.init(serializedData: response.tx.body.messages[position].value)
        
        legacyChainHashLabel.text = msg.txHash
        recipientLabel.text = msg.receiver
        let receiveAmount = NSDecimalNumber.init(string: msg.amount).multiplying(byPowerOf10: -18)
        amountLabel.attributedText = WUtils.displayAmount2(receiveAmount.stringValue, amountLabel.font!, 6, 6)
        
    }
}
