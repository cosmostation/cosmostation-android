//
//  HarvestDetailMyActionCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestDetailMyActionCell: UITableViewCell {
    
    @IBOutlet weak var depositImg: UIImageView!
    @IBOutlet weak var depositSymbol: UILabel!
    @IBOutlet weak var depositAmount: UILabel!
    @IBOutlet weak var depositDenom: UILabel!
    @IBOutlet weak var depositValue: UILabel!
    @IBOutlet weak var rewardImg: UIImageView!
    @IBOutlet weak var rewardAmount: UILabel!
    @IBOutlet weak var rewardDenom: UILabel!
    @IBOutlet weak var rewardValue: UILabel!
    
    @IBOutlet weak var depositBtn: UIButton!
    @IBOutlet weak var withdrawBtn: UIButton!
    @IBOutlet weak var rewardBtn: UIButton!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        depositAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        rewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionDepoist: (() -> Void)? = nil
    var actionWithdraw: (() -> Void)? = nil
    var actionClaim: (() -> Void)? = nil
    
    @IBAction func onClickDeposit(_ sender: UIButton) {
        actionDepoist?()
    }
    
    @IBAction func onClickWithdraw(_ sender: UIButton) {
        actionWithdraw?()
    }
    
    @IBAction func onClickClaim(_ sender: UIButton) {
        actionClaim?()
    }
    
}
