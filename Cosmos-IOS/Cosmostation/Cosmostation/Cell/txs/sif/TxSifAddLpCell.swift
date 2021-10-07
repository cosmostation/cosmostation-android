//
//  TxSifAddLpCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifAddLpCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txSingerLabel: UILabel!
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
        
        let msg = try! Sifnode_Clp_V1_MsgAddLiquidity.init(serializedData: response.tx.body.messages[position].value)
        txSingerLabel.text = msg.signer
        txSingerLabel.adjustsFontSizeToFitWidth = true
        
        let depositRowan = Coin.init(SIF_MAIN_DENOM, msg.nativeAssetAmount)
        let depositOther = Coin.init(msg.externalAsset.symbol, msg.externalAssetAmount)
        WUtils.showCoinDp(depositRowan, txDeposit1DenomLabel, txDeposit1AmountLabel, chain)
        WUtils.showCoinDp(depositOther, txDeposit2DenomLabel, txDeposit2AmountLabel, chain)
        
    }
    
}
