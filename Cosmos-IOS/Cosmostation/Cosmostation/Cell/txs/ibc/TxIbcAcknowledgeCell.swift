//
//  TxIbcAcknowledgeCell.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/05.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxIbcAcknowledgeCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txSingerLabel: UILabel!
    @IBOutlet weak var txAcknowledgementLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Ibc_Core_Channel_V1_MsgAcknowledgement.init(serializedData: response.tx.body.messages[position].value)
        txSingerLabel.text = msg.signer
        txAcknowledgementLabel.text = msg.acknowledgement.toHexString()
    }
}
