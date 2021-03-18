//
//  HarvestDetailTopCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestDetailTopCell: UITableViewCell {
    
    @IBOutlet weak var harvestImg: UIImageView!
    @IBOutlet weak var harvestTitle: UILabel!
    @IBOutlet weak var supplyAPILabel: UILabel!
    @IBOutlet weak var borrowAPILabel: UILabel!
    @IBOutlet weak var systemSuppliedAmount: UILabel!
    @IBOutlet weak var systemSuppliedDenom: UILabel!
    @IBOutlet weak var systemSuppliedValue: UILabel!
    @IBOutlet weak var systemBorrowedAmount: UILabel!
    @IBOutlet weak var systemBorrowedDenom: UILabel!
    @IBOutlet weak var systemBorrowedValue: UILabel!
    @IBOutlet weak var systemRemainBorrowableAmount: UILabel!
    @IBOutlet weak var systemRemainBorrowableDenom: UILabel!
    @IBOutlet weak var systemRemainBorrowableValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
