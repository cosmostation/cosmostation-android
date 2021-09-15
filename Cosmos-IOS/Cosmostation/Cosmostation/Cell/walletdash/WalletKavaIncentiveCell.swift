//
//  WalletKavaIncentiveCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/27.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class WalletKavaIncentiveCell: UITableViewCell {

    @IBOutlet weak var kavaAmountLabel: UILabel!
    @IBOutlet weak var hardAmountLabel: UILabel!
    @IBOutlet weak var swpAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        kavaAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        hardAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        swpAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionGetIncentive: (() -> Void)? = nil
    @IBAction func onClickCdp(_ sender: Any) {
        actionGetIncentive?()
    }
    
    func updateView() {
        let kavaAmount = BaseData.instance.mIncentiveRewards?.getIncentiveAmount(KAVA_MAIN_DENOM) ?? NSDecimalNumber.zero
        let hardAmount = BaseData.instance.mIncentiveRewards?.getIncentiveAmount(KAVA_HARD_DENOM) ?? NSDecimalNumber.zero
        let swpAmount = BaseData.instance.mIncentiveRewards?.getIncentiveAmount(KAVA_SWAP_DENOM) ?? NSDecimalNumber.zero
        
        kavaAmountLabel.attributedText = WUtils.displayAmount2(kavaAmount.stringValue, kavaAmountLabel.font, 6, 6)
        hardAmountLabel.attributedText = WUtils.displayAmount2(hardAmount.stringValue, hardAmountLabel.font, 6, 6)
        swpAmountLabel.attributedText = WUtils.displayAmount2(swpAmount.stringValue, swpAmountLabel.font, 6, 6)
    }
}
