//
//  TxHardBorrowCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/18.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxHardBorrowCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var borrower: UILabel!
    @IBOutlet weak var borrowAmount: UILabel!
    @IBOutlet weak var borrowDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        borrowAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        borrower.text = msg.value.borrower
        if let borrowamount = msg.value.getAmounts() {
            WUtils.showCoinDp(borrowamount[0], borrowDenom, borrowAmount, chaintype)
        }
    }
}
