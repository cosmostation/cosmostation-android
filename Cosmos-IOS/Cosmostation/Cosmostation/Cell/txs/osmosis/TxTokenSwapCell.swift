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
            WUtils.showCoinDp(coinIn, txSwapInDenomLabel, txSwapInAmountLabel, chain)
            
            var coinOut: Coin?
            if response.txResponse.logs.count > position {
                response.txResponse.logs[position].events.forEach { event in
                    if (event.type == "transfer") {
                        let attributesCnt = event.attributes.count
                        if (event.attributes[attributesCnt - 1].key == "amount") {
                            let value = event.attributes[attributesCnt - 1].value
                            if let range = value.range(of: "[0-9]*", options: .regularExpression){
                                let amount = String(value[range])
                                coinOut = Coin.init(value.replacingOccurrences(of: amount, with: ""), amount)
                            }
                        }
                    }
                }
            }
            if (coinOut != nil) {
                WUtils.showCoinDp(coinOut!, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
            } else {
                txSwapOutDenomLabel.text = ""
                txSwapOutAmountLabel.text = ""
            }
        }
        
        if let msg = try? Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut.init(serializedData: response.tx.body.messages[position].value) {
            txTypeLabel.text = String(Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut.protoMessageName.split(separator: ".").last!)
            
            txSenderLabel.text = msg.sender
            txSenderLabel.adjustsFontSizeToFitWidth = true
            
            txPoolIdLabel.text = String(msg.routes[0].poolID)
            
            
            let coinOut = Coin.init(msg.tokenOut.denom, msg.tokenOut.amount)
            WUtils.showCoinDp(coinOut, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
            
            var coinIn: Coin?
            if response.txResponse.logs.count > position {
                response.txResponse.logs[position].events.forEach { event in
                    if (event.type == "transfer") {
                        if (event.attributes.count >= 2 && event.attributes[2].key == "amount") {
                            let value = event.attributes[2].value
                            if let range = value.range(of: "[0-9]*", options: .regularExpression){
                                let amount = String(value[range])
                                coinIn = Coin.init(value.replacingOccurrences(of: amount, with: ""), amount)
                            }
                        }
                    }
                }
            }
            if (coinIn != nil) {
                WUtils.showCoinDp(coinIn!, txSwapInDenomLabel, txSwapInAmountLabel, chain)
            } else {
                txSwapInDenomLabel.text = ""
                txSwapInAmountLabel.text = ""
            }
        }
    }
}
