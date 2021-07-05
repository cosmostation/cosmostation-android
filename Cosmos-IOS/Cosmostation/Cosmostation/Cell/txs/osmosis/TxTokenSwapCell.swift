//
//  TxTokenSwapCell.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxTokenSwapCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTypeLabel: UILabel!
    @IBOutlet weak var txSenderLabel: UILabel!
    @IBOutlet weak var txPoolIdLabel: UILabel!
    @IBOutlet weak var txSwapInAmountLabel: UILabel!
    @IBOutlet weak var txSwapInDenomLabel: UILabel!
    @IBOutlet weak var txSwapOutAmountLabel: UILabel!
    @IBOutlet weak var txSwapOutDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txSwapInAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txSwapOutAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        if let msg = try? Osmosis_Gamm_V1beta1_MsgSwapExactAmountIn.init(serializedData: response.tx.body.messages[position].value) {
            txTypeLabel.text = String(Osmosis_Gamm_V1beta1_MsgSwapExactAmountIn.protoMessageName.split(separator: ".").last!)
            
            txSenderLabel.text = msg.sender
            txSenderLabel.adjustsFontSizeToFitWidth = true
            
            txPoolIdLabel.text = String(msg.routes[0].poolID)
            
            let coinIn = Coin.init(msg.tokenIn.denom, msg.tokenIn.amount)
            let coinOut = Coin.init(msg.routes[0].tokenOutDenom, msg.tokenOutMinAmount)
            
            WUtils.showCoinDp(coinIn, txSwapInDenomLabel, txSwapInAmountLabel, chain)
            WUtils.showCoinDp(coinOut, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
        }
        
        if let msg = try? Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut.init(serializedData: response.tx.body.messages[position].value) {
            txTypeLabel.text = String(Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut.protoMessageName.split(separator: ".").last!)
            
            txSenderLabel.text = msg.sender
            txSenderLabel.adjustsFontSizeToFitWidth = true
            
            txPoolIdLabel.text = String(msg.routes[0].poolID)
            
            let coinIn = Coin.init(msg.routes[0].tokenInDenom, msg.tokenInMaxAmount)
            let coinOut = Coin.init(msg.tokenOut.denom, msg.tokenOut.amount)
            
            WUtils.showCoinDp(coinIn, txSwapInDenomLabel, txSwapInAmountLabel, chain)
            WUtils.showCoinDp(coinOut, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
        }
    }
}
