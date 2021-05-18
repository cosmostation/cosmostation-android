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
    @IBOutlet weak var frozenAmount: UILabel!
    @IBOutlet weak var btnBep3: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        frozenAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionWC: (() -> Void)? = nil
    var actionBep3: (() -> Void)? = nil
    
    @IBAction func onClickWC(_ sender: Any) {
        actionWC?()
    }
    
    @IBAction func onClickBep3(_ sender: UIButton) {
        actionBep3?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        let available = BaseData.instance.availableAmount(BNB_MAIN_DENOM)
        let locked = BaseData.instance.lockedAmount(BNB_MAIN_DENOM)
        let frozen = BaseData.instance.frozenAmount(BNB_MAIN_DENOM)
        let total = available.adding(locked).adding(frozen)
        
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 0, 6)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 0, 6)
        lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, lockedAmount.font, 0, 6)
        frozenAmount.attributedText = WUtils.displayAmount2(frozen.stringValue, frozenAmount.font, 0, 6)
        totalValue.attributedText = WUtils.dpUserCurrencyValue(BNB_MAIN_DENOM, total, 0, totalValue.font)
        BaseData.instance.updateLastTotal(account, total.stringValue)
    }
    
}
