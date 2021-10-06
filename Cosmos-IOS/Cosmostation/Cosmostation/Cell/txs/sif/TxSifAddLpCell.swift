//
//  TxSifAddLpCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifAddLpCell: UITableViewCell {
    
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
    
    func onBind(_ chaintype: ChainType, _ msg: Msg, _ tx: TxInfo, _ position: Int) {
    }
    
}
