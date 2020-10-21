//
//  CdpLisyMyCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpLisyMyCell: UITableViewCell {

    @IBOutlet weak var marketImg: UIImageView!
    @IBOutlet weak var marketType: UILabel!
    @IBOutlet weak var marketTitle: UILabel!
    @IBOutlet weak var riskRateImg: UIImageView!
    @IBOutlet weak var riskScore: UILabel!
    @IBOutlet weak var debtValueTitle: UILabel!
    @IBOutlet weak var debtValue: UILabel!
    @IBOutlet weak var collateralValueTitle: UILabel!
    @IBOutlet weak var collateralValue: UILabel!
    @IBOutlet weak var currentPriceTitle: UILabel!
    @IBOutlet weak var currentPrice: UILabel!
    @IBOutlet weak var liquidationPriceTitle: UILabel!
    @IBOutlet weak var liquidationPrice: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        debtValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        collateralValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        currentPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        liquidationPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
}
