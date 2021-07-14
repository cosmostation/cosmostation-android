//
//  TxJoinPoolCell.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxJoinPoolCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txSenderLabel: UILabel!
    @IBOutlet weak var txPoolIdLabel: UILabel!
    @IBOutlet weak var txPoolShareOutAmountLabel: UILabel!
    @IBOutlet weak var txPoolAsset1AmountLabel: UILabel!
    @IBOutlet weak var txPoolAsset1DenomLabel: UILabel!
    @IBOutlet weak var txPoolAsset2AmountLabel: UILabel!
    @IBOutlet weak var txPoolAsset2DenomLabel: UILabel!
    @IBOutlet weak var txPoolOutAmountLabel: UILabel!
    @IBOutlet weak var txPoolOutDenomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txPoolAsset1AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txPoolAsset2AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Osmosis_Gamm_V1beta1_MsgJoinPool.init(serializedData: response.tx.body.messages[position].value)
        txSenderLabel.text = msg.sender
        txSenderLabel.adjustsFontSizeToFitWidth = true
        
        txPoolIdLabel.text = String(msg.poolID)
        
        txPoolShareOutAmountLabel.text = NSDecimalNumber.init(string: msg.shareOutAmount).multiplying(byPowerOf10: -18).rounding(accordingToBehavior: WUtils.handler18).stringValue
        
        var inCoins: Array<Coin> = Array<Coin>()
        if response.txResponse.logs.count > position {
            response.txResponse.logs[position].events.forEach { event in
                if (event.type == "transfer") {
                    if (event.attributes.count >= 6) {
                        for rawInCoin in event.attributes[2].value.split(separator: ",") {
                            let inCoin = String(rawInCoin)
                            if let range = inCoin.range(of: "[0-9]*", options: .regularExpression) {
                                let amount = String(inCoin[range])
                                inCoins.append(Coin.init(inCoin.replacingOccurrences(of: amount, with: ""), amount))
                            }
                        }
                    }
                }
            }
        }
        print("inCoins ", inCoins)
        if (inCoins.count == 2) {
            WUtils.showCoinDp(inCoins[0], txPoolAsset1DenomLabel, txPoolAsset1AmountLabel, chain)
            WUtils.showCoinDp(inCoins[1], txPoolAsset2DenomLabel, txPoolAsset2AmountLabel, chain)
            
        } else {
            txPoolAsset1AmountLabel.text = ""
            txPoolAsset1DenomLabel.text = ""
            txPoolAsset2AmountLabel.text = ""
            txPoolAsset2DenomLabel.text = ""
        }
        
        
        var outCoin: Coin?
        if response.txResponse.logs.count > position {
            response.txResponse.logs[position].events.forEach { event in
                if (event.type == "transfer") {
                    if (event.attributes.count >= 6) {
                        for rawCoin in event.attributes[5].value.split(separator: ",") {
                            let coin = String(rawCoin)
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
            WUtils.showCoinDp(outCoin!, txPoolOutDenomLabel, txPoolOutAmountLabel, chain)
            
        } else {
            txPoolOutAmountLabel.text = ""
            txPoolOutDenomLabel.text = ""
        }
    }
    
}
