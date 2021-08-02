//
//  HarvestListAllCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestListAllCell: UITableViewCell {
    
    @IBOutlet weak var harvestImg: UIImageView!
    @IBOutlet weak var harvestTitle: UILabel!
    @IBOutlet weak var supplyAPILabel: UILabel!
    @IBOutlet weak var borrowAPILabel: UILabel!
    @IBOutlet weak var mySuppliedAmount: UILabel!
    @IBOutlet weak var mySuppliedDenom: UILabel!
    @IBOutlet weak var mySuppliedValue: UILabel!
    @IBOutlet weak var myBorrowedAmount: UILabel!
    @IBOutlet weak var myBorrowedDenom: UILabel!
    @IBOutlet weak var myBorrowedValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        mySuppliedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        myBorrowedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
}
