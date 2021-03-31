//
//  WalletBnbCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletBnbCell: UITableViewCell {
    
    @IBOutlet weak var bnbCard: CardView!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var btnBep3: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionWC: (() -> Void)? = nil
    var actionBep3: (() -> Void)? = nil
    
    @IBAction func onClickWC(_ sender: Any) {
        actionWC?()
    }
    
    @IBAction func onClickBep3(_ sender: UIButton) {
        actionBep3?()
    }
    
}
