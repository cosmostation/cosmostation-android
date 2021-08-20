//
//  TokenDetailIBCCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/20.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailIBCCell: TokenDetailCell {
    
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    
    var ibcDivideDecimal: Int16 = 6
    var ibcDisplayDecimal: Int16 = 6

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindIBCToken(_ chainType: ChainType, _ ibcDenom: String) {
        let ibcHash = ibcDenom.replacingOccurrences(of: "ibc/", with: "")
        if let ibcToken = BaseData.instance.getIbcToken(ibcHash) {
            ibcDivideDecimal = ibcToken.decimal ?? 6
            ibcDisplayDecimal = ibcToken.decimal ?? 6
        }
        
        let total = BaseData.instance.getAvailableAmount_gRPC(ibcDenom)
        totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, ibcDivideDecimal, ibcDisplayDecimal)
        availableAmount.attributedText = WUtils.displayAmount2(total.stringValue, availableAmount.font, ibcDivideDecimal, ibcDisplayDecimal)
    }
    
}
