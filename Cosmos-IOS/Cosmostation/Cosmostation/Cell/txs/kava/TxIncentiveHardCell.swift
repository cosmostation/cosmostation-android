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
    
    func onBind(_ chaintype: ChainType, _ msg: Msg, _ tx: TxInfo, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chaintype)
        
        sender.text = msg.value.sender
        multiplier.text = msg.value.denoms_to_claim?[0].multiplier_name
        
        let incentiveCoins = tx.simpleIncentives(position)
        if let kavaToken = incentiveCoins.filter({ $0.denom == KAVA_MAIN_DENOM }).first {
            kavaAmount.attributedText = WUtils.displayAmount2(kavaToken.amount, kavaAmount.font!, 6, 6)
        } else {
            kavaAmount.attributedText = WUtils.displayAmount2("0", kavaAmount.font!, 6, 6)
        }
        if let hardToken = incentiveCoins.filter({ $0.denom == KAVA_HARD_DENOM }).first {
            hardAmount.attributedText = WUtils.displayAmount2(hardToken.amount, hardAmount.font!, 6, 6)
        } else {
            hardAmount.attributedText = WUtils.displayAmount2("0", hardAmount.font!, 6, 6)
        }
    }
}
