//
//  TxHardWithdrawCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/18.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxHardWithdrawCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var depositor: UILabel!
    @IBOutlet weak var depositAmount: UILabel!
    @IBOutlet weak var depositDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        depositAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        depositor.text = msg.value.depositor
        if let depostAmounts = msg.value.getAmounts() {
            WUtils.showCoinDp(depostAmounts[0], depositDenom, depositAmount, chaintype)
        }
        //support old version
        if let depostAmount = msg.value.getAmount() {
            WUtils.showCoinDp(depostAmount, depositDenom, depositAmount, chaintype)
        }
    }
    
}
