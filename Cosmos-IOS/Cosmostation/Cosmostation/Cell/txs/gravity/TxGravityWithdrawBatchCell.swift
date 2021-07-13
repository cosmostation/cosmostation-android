//
//  TxGravityWithdrawBatchCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/13.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxGravityWithdrawBatchCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txWithdrawerLabel: UILabel!
    @IBOutlet weak var txPoolIDLabel: UILabel!
    @IBOutlet weak var txWithdrawAmountLabel: UILabel!
    @IBOutlet weak var txWithdrawDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txWithdrawAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Tendermint_Liquidity_V1beta1_MsgWithdrawWithinBatch.init(serializedData: response.tx.body.messages[position].value)
        txWithdrawerLabel.text = msg.withdrawerAddress
        txWithdrawerLabel.adjustsFontSizeToFitWidth = true
        
        txPoolIDLabel.text = String(msg.poolID)
        
        txWithdrawAmountLabel.text = ""
        txWithdrawDenomLabel.text = ""
        
    }
    
}
