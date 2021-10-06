//
//  TxSifIncentiveCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifIncentiveCell: UITableViewCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var claimAddress: UILabel!
    @IBOutlet weak var claimType: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg, _ tx: TxInfo, _ position: Int) {
    }
    
}
