//
//  NewHistoryCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/23.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class NewHistoryCell: UITableViewCell {
    
    @IBOutlet weak var txTypeLabel: UILabel!
    @IBOutlet weak var txResultLabel: UILabel!
    @IBOutlet weak var txTimeLabel: UILabel!
    @IBOutlet weak var txTimeGapLabel: UILabel!
    @IBOutlet weak var txAmountLabel: UILabel!
    @IBOutlet weak var txDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        txAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func prepareForReuse() {
        txDenomLabel.isHidden = false
        txAmountLabel.isHidden = false
    }
    
    func bindView(_ chainType: ChainType, _ history: ApiHistoryNewCustom, _ address: String) {
        txTypeLabel.text = history.getMsgType(address)
        txResultLabel.isHidden = history.isSuccess()
        txTimeLabel.text = WUtils.newApiTimeToString(history.header?.timestamp)
        txTimeGapLabel.text = WUtils.newApiTimeGap(history.header?.timestamp)
        guard let dpCoin = history.getDpCoin() else {
            txDenomLabel.isHidden = true
            txAmountLabel.isHidden = true
            return
        }
        WUtils.showCoinDp(dpCoin, txDenomLabel, txAmountLabel, chainType)
    }
    
}
