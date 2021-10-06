//
//  TxSifRemoveLpCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifRemoveLpCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txSignerLabel: UILabel!
    @IBOutlet weak var txWithdraw1AmountLabel: UILabel!
    @IBOutlet weak var txWithdraw1DenomLabel: UILabel!
    @IBOutlet weak var txWithdraw2AmountLabel: UILabel!
    @IBOutlet weak var txWithdraw2DenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txWithdraw1AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txWithdraw2AmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg, _ tx: TxInfo, _ position: Int) {
    }
    
}
