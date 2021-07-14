//
//  TxGravityCreatePoolCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/13.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxGravityCreatePoolCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txOwenerLabel: UILabel!
    @IBOutlet weak var txPoolIDLabel: UILabel!
    @IBOutlet weak var txDeposit1AmountLabel: UILabel!
    @IBOutlet weak var txDeposit1DenomLabel: UILabel!
    @IBOutlet weak var txDeposit2AmountLabel: UILabel!
    @IBOutlet weak var txDeposit2DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txDeposit1AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txDeposit2AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Tendermint_Liquidity_V1beta1_MsgCreatePool.init(serializedData: response.tx.body.messages[position].value)
        txOwenerLabel.text = msg.poolCreatorAddress
        txOwenerLabel.adjustsFontSizeToFitWidth = true
        
        txPoolIDLabel.text = String(msg.poolTypeID)
        
        txDeposit1AmountLabel.text = ""
        txDeposit1DenomLabel.text = ""
        txDeposit2AmountLabel.text = ""
        txDeposit2DenomLabel.text = ""
        
    }
}
