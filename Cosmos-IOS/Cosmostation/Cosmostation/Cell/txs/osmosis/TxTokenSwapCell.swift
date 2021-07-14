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
            print("Osmosis_Gamm_V1beta1_MsgSwapExactAmountIn")
            txTypeLabel.text = String(Osmosis_Gamm_V1beta1_MsgSwapExactAmountIn.protoMessageName.split(separator: ".").last!)
            
            txSenderLabel.text = msg.sender
            txSenderLabel.adjustsFontSizeToFitWidth = true
            
            txPoolIdLabel.text = String(msg.routes[0].poolID)
            
            var inCoin: Coin?
            if response.txResponse.logs.count > position {
                response.txResponse.logs[position].events.forEach { event in
                    if (event.type == "transfer") {
                        if (event.attributes.count >= 6) {
                            let coin = String(event.attributes[2].value)
                            if let range = coin.range(of: "[0-9]*", options: .regularExpression){
                                let amount = String(coin[range])
                                inCoin = Coin.init(coin.replacingOccurrences(of: amount, with: ""), amount)
                            }
                        }
                    }
                }
            }
            if (inCoin != nil) {
                WUtils.showCoinDp(inCoin!, txSwapInDenomLabel, txSwapInAmountLabel, chain)
            } else {
                txSwapInAmountLabel.text = ""
                txSwapInDenomLabel.text = ""
            }
            print("inCoin ", inCoin)
            
            var outCoin: Coin?
            if response.txResponse.logs.count > position {
                response.txResponse.logs[position].events.forEach { event in
                    if (event.type == "transfer") {
                        event.attributes.forEach { attribute in
                            if (event.attributes.count >= 6) {
                                let coin = String(event.attributes[event.attributes.count - 1].value)
                                if let range = coin.range(of: "[0-9]*", options: .regularExpression){
                                    let amount = String(coin[range])
                                    outCoin = Coin.init(coin.replacingOccurrences(of: amount, with: ""), amount)
                                }
                            }
                        }
                    }
                }
            }
            print("outCoin ", outCoin)
            if (outCoin != nil) {
                WUtils.showCoinDp(outCoin!, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
            } else {
                txSwapOutAmountLabel.text = ""
                txSwapOutDenomLabel.text = ""
            }
            return
        }
        
        if let msg = try? Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut.init(serializedData: response.tx.body.messages[position].value) {
            print("Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut")
            txTypeLabel.text = String(Osmosis_Gamm_V1beta1_MsgSwapExactAmountOut.protoMessageName.split(separator: ".").last!)
            
            txSenderLabel.text = msg.sender
            txSenderLabel.adjustsFontSizeToFitWidth = true
            
            txPoolIdLabel.text = String(msg.routes[0].poolID)
            
            var inCoin: Coin?
            if response.txResponse.logs.count > position {
                response.txResponse.logs[position].events.forEach { event in
                    if (event.type == "transfer") {
                        if (event.attributes.count >= 6) {
                            let coin = String(event.attributes[2].value)
                            if let range = coin.range(of: "[0-9]*", options: .regularExpression){
                                let amount = String(coin[range])
                                inCoin = Coin.init(coin.replacingOccurrences(of: amount, with: ""), amount)
                            }
                        }
                    }
                }
            }
            if (inCoin != nil) {
                WUtils.showCoinDp(inCoin!, txSwapInDenomLabel, txSwapInAmountLabel, chain)
            } else {
                txSwapInAmountLabel.text = ""
                txSwapInDenomLabel.text = ""
            }
            print("inCoin ", inCoin)
            
            var outCoin: Coin?
            if response.txResponse.logs.count > position {
                response.txResponse.logs[position].events.forEach { event in
                    if (event.type == "transfer") {
                        event.attributes.forEach { attribute in
                            if (event.attributes.count >= 6) {
                                let coin = String(event.attributes[event.attributes.count - 1].value)
                                if let range = coin.range(of: "[0-9]*", options: .regularExpression){
                                    let amount = String(coin[range])
                                    outCoin = Coin.init(coin.replacingOccurrences(of: amount, with: ""), amount)
                                }
                            }
                        }
                    }
                }
            }
            print("outCoin ", outCoin)
            if (outCoin != nil) {
                WUtils.showCoinDp(outCoin!, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
            } else {
                txSwapOutAmountLabel.text = ""
                txSwapOutDenomLabel.text = ""
            }
        }
    }
}
