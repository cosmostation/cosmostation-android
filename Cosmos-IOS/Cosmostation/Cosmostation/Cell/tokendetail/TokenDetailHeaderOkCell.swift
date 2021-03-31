//
//  TokenDetailHeaderOkCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TokenDetailHeaderOkCell: UITableViewCell {
    
    @IBOutlet weak var rootCardView: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var depositAmount: UILabel!
    @IBOutlet weak var withdrawAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        depositAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        withdrawAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    
    var actionSend: (() -> Void)? = nil
    var actionReceive: (() -> Void)? = nil
    
    @IBAction func onClickSend(_ sender: UIButton) {
        actionSend?()
    }
    @IBAction func onClickReceive(_ sender: UIButton) {
        actionReceive?()
    }
}
