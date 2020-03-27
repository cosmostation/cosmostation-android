//
//  CdpDetailMyActionCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpDetailMyActionCell: UITableViewCell {
    
    @IBOutlet weak var collateralImg: UIImageView!
    @IBOutlet weak var collateralDenom: UILabel!
    @IBOutlet weak var collateralSelfAmount: UILabel!
    @IBOutlet weak var collateralSelfValue: UILabel!
    @IBOutlet weak var collateralTotalAmount: UILabel!
    @IBOutlet weak var collateralTotalValue: UILabel!
    @IBOutlet weak var collateralWithdrawableTitle: UILabel!
    @IBOutlet weak var collateralWithdrawableAmount: UILabel!
    @IBOutlet weak var collateralWithdrawableValue: UILabel!
    @IBOutlet weak var depositBtn: UIButton!
    @IBOutlet weak var withdrawBtn: UIButton!

    @IBOutlet weak var principalImg: UIImageView!
    @IBOutlet weak var principalDenom: UILabel!
    @IBOutlet weak var principalAmount: UILabel!
    @IBOutlet weak var principalValue: UILabel!
    @IBOutlet weak var interestAmount: UILabel!
    @IBOutlet weak var interestValue: UILabel!
    @IBOutlet weak var remainingAmount: UILabel!
    @IBOutlet weak var remainingValue: UILabel!
    @IBOutlet weak var darwdebtBtn: UIButton!
    @IBOutlet weak var repayBtn: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        collateralSelfAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        collateralTotalAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        collateralWithdrawableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        collateralSelfValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        collateralTotalValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        collateralWithdrawableValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        
        principalAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        interestAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        remainingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        principalValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        interestValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
        remainingValue.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_11_caption2)
    }
    
    
    var actionDeposit: (() -> Void)? = nil
    var actionWithdraw: (() -> Void)? = nil
    var actionDrawDebt: (() -> Void)? = nil
    var actionRepay: (() -> Void)? = nil
    
    
    @IBAction func onClickDeposit(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickWithdraw(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickDrawDebt(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickRepay(_ sender: UIButton) {
        
    }
    
}
