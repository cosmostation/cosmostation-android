//
//  TxIncentiveSwapCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxIncentiveSwapCell: UITableViewCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var sender: UILabel!
    @IBOutlet weak var multiplier: UILabel!
    @IBOutlet weak var kavaAmountLabel: UILabel!
    @IBOutlet weak var hardAmountLabel: UILabel!
    @IBOutlet weak var swpAmountLabel: UILabel!
    @IBOutlet weak var usdxAmountLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    func onBind(_ chaintype: ChainType, _ msg: Msg) {
//        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
//        txIcon.tintColor = WUtils.getChainColor(chaintype)
//        keeper.text = msg.value.keeper
//        owener.text = msg.value.borrower
    }
    
}
