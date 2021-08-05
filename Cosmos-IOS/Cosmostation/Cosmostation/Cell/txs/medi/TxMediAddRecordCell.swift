//
//  TxMediAddRecordCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/05.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxMediAddRecordCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTopicNameLabel: UILabel!
    @IBOutlet weak var txKeyLabel: UILabel!
    @IBOutlet weak var txValueLabel: UILabel!
    @IBOutlet weak var txWriterAddressLabel: UILabel!
    @IBOutlet weak var txOwnerAddressLabel: UILabel!
    @IBOutlet weak var txFeePayerAddressLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Panacea_Aol_V2_MsgAddRecord.init(serializedData: response.tx.body.messages[position].value)
        
        txTopicNameLabel.text = msg.topicName
        txKeyLabel.text = msg.key.dataToHexString()
        txValueLabel.text = msg.value.dataToHexString()
        txWriterAddressLabel.text = msg.writerAddress
        txOwnerAddressLabel.text = msg.ownerAddress
        txFeePayerAddressLabel.text = msg.feePayerAddress
    }
    
}
