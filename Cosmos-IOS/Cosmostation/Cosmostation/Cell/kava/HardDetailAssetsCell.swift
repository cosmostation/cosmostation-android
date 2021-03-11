//
//  HardDetailAssetsCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class HardDetailAssetsCell: UITableViewCell {

    @IBOutlet weak var marketLayer: UIView!
    @IBOutlet weak var kavaLayer: UIView!
    @IBOutlet weak var marketImg: UIImageView!
    @IBOutlet weak var marketDenom: UILabel!
    @IBOutlet weak var marketAmountLabel: UILabel!
    @IBOutlet weak var marketValueLabel: UILabel!
    @IBOutlet weak var kavaAmountLabel: UILabel!
    @IBOutlet weak var kavaValueLable: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
