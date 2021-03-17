//
//  TxCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func onBind(_ chain: ChainType, _ tx: Cosmos_Tx_V1beta1_GetTxResponse) {
    }
    func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
    }
}
