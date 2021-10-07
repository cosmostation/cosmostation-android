//
//  TxSifSwapCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifSwapCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txSingerLabel: UILabel!
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
        
        let msg = try! Sifnode_Clp_V1_MsgSwap.init(serializedData: response.tx.body.messages[position].value)
        txSingerLabel.text = msg.signer
        txSingerLabel.adjustsFontSizeToFitWidth = true
        
        let inCoin = Coin.init(msg.sentAsset.symbol, msg.sentAmount)
        let outCoin = Coin.init(msg.receivedAsset.symbol, msg.minReceivingAmount)
        WUtils.showCoinDp(inCoin, txSwapInDenomLabel, txSwapInAmountLabel, chain)
        WUtils.showCoinDp(outCoin, txSwapOutDenomLabel, txSwapOutAmountLabel, chain)
    }
    
}
