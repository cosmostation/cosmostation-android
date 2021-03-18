//
//  TxHardRepayCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/18.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxHardRepayCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var sender: UILabel!
    @IBOutlet weak var owener: UILabel!
    @IBOutlet weak var repayAmount: UILabel!
    @IBOutlet weak var repayDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        repayAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        sender.text = msg.value.sender
        owener.text = msg.value.owner
        if let repayamount = msg.value.getAmounts() {
            WUtils.showCoinDp(repayamount[0], repayDenom, repayAmount, chaintype)
        }
    }
    
}
