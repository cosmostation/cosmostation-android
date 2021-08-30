//
//  TxSwapTokenCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSwapTokenCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTypeLabel: UILabel!
    @IBOutlet weak var txSenderLabel: UILabel!
    @IBOutlet weak var txPoolInAmountLabel: UILabel!
    @IBOutlet weak var txPoolInDenomLabel: UILabel!
    @IBOutlet weak var txPoolOutAmountLabel: UILabel!
    @IBOutlet weak var txPoolOutDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg, _ tx: TxInfo) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        
        txTypeLabel.text = msg.value.type
        txSenderLabel.text = msg.value.requester
        
        if let inCoin = tx.simpleSwapInCoin() {
            WUtils.showCoinDp(inCoin, txPoolInDenomLabel, txPoolInAmountLabel, chaintype)
        }
        if let outCoin = tx.simpleSwapOutCoin() {
            WUtils.showCoinDp(outCoin, txPoolOutDenomLabel, txPoolOutAmountLabel, chaintype)
        }
    }
    
}
