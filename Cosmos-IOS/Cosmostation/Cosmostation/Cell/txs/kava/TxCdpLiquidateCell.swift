//
//  TxCdpLiquidateCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/18.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxCdpLiquidateCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var keeper: UILabel!
    @IBOutlet weak var owener: UILabel!
    @IBOutlet weak var type: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        keeper.text = msg.value.keeper
        owener.text = msg.value.borrower
        type.text = msg.value.collateral_type?.uppercased()
    }
    
}
