//
//  TxMediCreateDidCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/05.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxMediCreateDidCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txDidLabel: UILabel!
    @IBOutlet weak var txVertificationMethodIdLabel: UILabel!
    @IBOutlet weak var txSignatureLabel: UILabel!
    @IBOutlet weak var txFromAddressLabel: UILabel!
    @IBOutlet weak var txContextLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Panacea_Did_V2_MsgCreateDID.init(serializedData: response.tx.body.messages[position].value)
        
        txDidLabel.text = msg.did
        txVertificationMethodIdLabel.text = msg.verificationMethodID
        txSignatureLabel.text = msg.signature.dataToHexString()
        txFromAddressLabel.text = msg.fromAddress
        txFromAddressLabel.text = msg.document.contexts.values[0]
    }
    
}
