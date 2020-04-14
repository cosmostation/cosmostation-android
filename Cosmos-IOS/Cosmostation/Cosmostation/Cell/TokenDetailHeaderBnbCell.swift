//
//  TokenDetailHeaderBnbCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class TokenDetailHeaderBnbCell: UITableViewCell {
    
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var BtnBuyBnb: UIButton!
    @IBOutlet weak var BtnSendBep3: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionSend: (() -> Void)? = nil
    var actionRecieve: (() -> Void)? = nil
    var actionBuy: (() -> Void)? = nil
    var actionSendBep3: (() -> Void)? = nil
    
    @IBAction func onClickSend(_ sender: Any) {
        actionSend?()
    }
    
    @IBAction func onClickRecieve(_ sender: UIButton) {
        actionRecieve?()
    }
    
    @IBAction func onClickBuy(_ sender: UIButton) {
        actionBuy?()
    }
    
    @IBAction func onClickSendBep3(_ sender: UIButton) {
        actionSendBep3?()
    }
    
}
