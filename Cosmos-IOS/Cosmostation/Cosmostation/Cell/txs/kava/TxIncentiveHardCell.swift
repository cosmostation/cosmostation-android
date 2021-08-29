//
//  TxIncentiveHardCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/18.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxIncentiveHardCell: UITableViewCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var sender: UILabel!
    @IBOutlet weak var multiplier: UILabel!
    @IBOutlet weak var kavaAmount: UILabel!
    @IBOutlet weak var kavaDenom: UILabel!
    @IBOutlet weak var hardAmount: UILabel!
    @IBOutlet weak var hardDenom: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        kavaAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        hardAmount.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func onBind(_ chaintype: ChainType, _ msg: Msg, _ tx: TxInfo) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        
        sender.text = msg.value.sender
        multiplier.text = msg.value.multiplier_name
        
        if let kavacoin = tx.simpleHardReward(KAVA_MAIN_DENOM) {
            WUtils.showCoinDp(kavacoin, kavaDenom, kavaAmount, chaintype)
        }
        
        if let hardcoin = tx.simpleHardReward(KAVA_HARD_DENOM) {
            WUtils.showCoinDp(hardcoin, hardDenom, hardAmount, chaintype)
        }
    }
}
