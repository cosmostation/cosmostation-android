//
//  TxRegisterAccountCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRegisterAccountCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTitleLabel: UILabel!
    @IBOutlet weak var accountLabel: UILabel!
    @IBOutlet weak var owenerLabel: UILabel!
    @IBOutlet weak var registerLabel: UILabel!
    @IBOutlet weak var starnameFeeAmountLabel: UILabel!
    @IBOutlet weak var starnameFeeDenomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        starnameFeeAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        if let msg = try? Starnamed_X_Starname_V1beta1_MsgRegisterAccount.init(serializedData: response.tx.body.messages[position].value) {
            accountLabel.text = msg.name + "*" + msg.domain
            owenerLabel.text = msg.owner
            registerLabel.text = msg.registerer
            
            let starnameFee = WUtils.getStarNameRegisterAccountFee("open")
            WUtils.showCoinDp(IOV_MAIN_DENOM, starnameFee.stringValue, starnameFeeDenomLabel, starnameFeeAmountLabel, chain)
        }
        
    }
    
}
