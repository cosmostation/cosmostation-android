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
    
    func onBind(_ chaintype: ChainType, _ msg: Msg) {
    }
    
}
