//
//  TxSifRemoveLpCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifRemoveLpCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txSignerLabel: UILabel!
    @IBOutlet weak var txWithdraw1AmountLabel: UILabel!
    @IBOutlet weak var txWithdraw1DenomLabel: UILabel!
    @IBOutlet weak var txWithdraw2AmountLabel: UILabel!
    @IBOutlet weak var txWithdraw2DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txWithdraw1AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txWithdraw2AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Sifnode_Clp_V1_MsgRemoveLiquidity.init(serializedData: response.tx.body.messages[position].value)
        txSignerLabel.text = msg.signer
        txSignerLabel.adjustsFontSizeToFitWidth = true
        
        var removeCoins = Array<Coin>()
        if response.txResponse.logs.count > position {
            response.txResponse.logs[position].events.forEach { event in
                if (event.type == "transfer") {
                    event.attributes.forEach { attribute in
                        if (attribute.key == "amount") {
                            let rawCoins = attribute.value.split(separator: ",")
                            rawCoins.forEach { rawCoin in
                                if let range = rawCoin.range(of: "[0-9]*", options: .regularExpression) {
                                    let amount = String(rawCoin[range])
                                    removeCoins.append(Coin.init(rawCoin.replacingOccurrences(of: amount, with: ""), amount))
                                }
                            }
                        }
                    }
                }
            }
        }
        print("removeCoins ", removeCoins)
        
        let removeRowan = removeCoins.filter { $0.denom == SIF_MAIN_DENOM }.first
        if (removeRowan != nil) {
            WUtils.showCoinDp(removeRowan!, txWithdraw1DenomLabel, txWithdraw1AmountLabel, chain)
        } else {
            WUtils.showCoinDp(SIF_MAIN_DENOM, "0", txWithdraw1DenomLabel, txWithdraw1AmountLabel, chain)
        }
        
        let removeOther = removeCoins.filter { $0.denom != SIF_MAIN_DENOM }.first
        if (removeOther != nil) {
            WUtils.showCoinDp(removeOther!, txWithdraw2DenomLabel, txWithdraw2AmountLabel, chain)
        } else {
            txWithdraw2DenomLabel.isHidden = true
            txWithdraw2AmountLabel.isHidden = true
        }
    }
    
}
