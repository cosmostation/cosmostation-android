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
    @IBOutlet weak var depositValue: UILabel!
    @IBOutlet weak var borrowedAmount: UILabel!
    @IBOutlet weak var borrowedValue: UILabel!
    @IBOutlet weak var borroweableAmount: UILabel!
    @IBOutlet weak var borroweableValue: UILabel!
    @IBOutlet weak var depositBtn: UIButton!
    @IBOutlet weak var withdrawBtn: UIButton!
    @IBOutlet weak var borrowBtn: UIButton!
    @IBOutlet weak var repayBtn: UIButton!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        depositAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        borrowedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        borroweableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    var actionDepoist: (() -> Void)? = nil
    var actionWithdraw: (() -> Void)? = nil
    var actionBorrow: (() -> Void)? = nil
    var actionRepay: (() -> Void)? = nil
    
    @IBAction func onClickDeposit(_ sender: UIButton) {
        actionDepoist?()
    }
    
    @IBAction func onClickWithdraw(_ sender: UIButton) {
        actionWithdraw?()
    }
    
    @IBAction func onClickBorrow(_ sender: UIButton) {
        actionBorrow?()
    }
    
    @IBAction func onClickRepay(_ sender: UIButton) {
        actionRepay?()
    }
    
    
}
