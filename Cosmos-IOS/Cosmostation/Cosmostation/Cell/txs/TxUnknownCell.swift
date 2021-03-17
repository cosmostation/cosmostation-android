//
//  TxUnknownCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxUnknownCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    override func onBind(_ chain: ChainType, _ tx: Cosmos_Tx_V1beta1_GetTxResponse) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
    }
    
}
