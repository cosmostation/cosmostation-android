//
//  TokenDetailHeaderCustomCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class TokenDetailHeaderCustomCell: UITableViewCell {
    
    @IBOutlet weak var tokenImg: UIImageView!
    @IBOutlet weak var tokenSymbol: UILabel!
    @IBOutlet weak var tokenInfoBtn: UIButton!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    
    @IBOutlet weak var availableLayer: UIView!
    @IBOutlet weak var lockedLayer: UIView!
    @IBOutlet weak var frozenLayer: UIView!
    @IBOutlet weak var vestingLayer: UIView!
    @IBOutlet weak var havestDepsoitLayer: UIView!
    @IBOutlet weak var havsetRewardLayer: UIView!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var fronzenAmount: UILabel!
    @IBOutlet weak var vestingAmount: UILabel!
    @IBOutlet weak var havestDepositAmount: UILabel!
    @IBOutlet weak var havestRewardAmount: UILabel!
    
    @IBOutlet weak var btnBep3Send: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        fronzenAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        havestDepositAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        havestRewardAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionSend: (() -> Void)? = nil
    var actionReceive: (() -> Void)? = nil
    var actionTokenInfo: (() -> Void)? = nil
    var actionBep3Send: (() -> Void)? = nil
    
    @IBAction func onClickSend(_ sender: Any) {
        actionSend?()
    }
    
    @IBAction func onClickReceive(_ sender: UIButton) {
        actionReceive?()
    }
    
    
    @IBAction func onClickTokenInfo(_ sender: Any) {
        actionTokenInfo?()
    }
    
    @IBAction func onClickBep3Send(_ sender: UIButton) {
        actionBep3Send?()
    }
    
    override func prepareForReuse() {
        self.tokenImg.image = UIImage(named: "tokenIc")
        self.tokenSymbol.text = "-"
        self.totalAmount.text = "-"
        self.totalValue.text = "-"
        self.availableAmount.text = "-"
        super.prepareForReuse()
    }
    
}
