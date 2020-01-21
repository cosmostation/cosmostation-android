//
//  ValidatorDetailMyActionCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ValidatorDetailMyActionCell: UITableViewCell {

    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var myDelegateAmount: UILabel!
    @IBOutlet weak var myUndelegateAmount: UILabel!
    @IBOutlet weak var myRewardAmount: UILabel!
    @IBOutlet weak var myDailyReturns: UILabel!
    @IBOutlet weak var myMonthlyReturns: UILabel!
    
    @IBOutlet weak var delegateBtn: UIButton!
    @IBOutlet weak var undelegateBtn: UIButton!
    @IBOutlet weak var redelegateBtn: UIButton!
    @IBOutlet weak var claimRewardBtn: UIButton!
    @IBOutlet weak var reInvestBtn: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        myDelegateAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myUndelegateAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myRewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myDailyReturns.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myMonthlyReturns.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    var actionDelegate: (() -> Void)? = nil
    var actionUndelegate: (() -> Void)? = nil
    var actionRedelegate: (() -> Void)? = nil
    var actionReward: (() -> Void)? = nil
    var actionReinvest: (() -> Void)? = nil
    
    @IBAction func onClickDelegate(_ sender: Any) {
        actionDelegate?()
    }
    @IBAction func onClickUndelegate(_ sender: Any) {
        actionUndelegate?()
    }
    @IBAction func onClickRedelegate(_ sender: Any) {
        actionRedelegate?()
    }
    @IBAction func onClickReward(_ sender: Any) {
        actionReward?()
    }
    @IBAction func onClickReInvest(_ sender: Any) {
        actionReinvest?()
    }
    
    
    
}
