//
//  SelectCoinCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/09/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class SelectCoinCell: UITableViewCell {

    @IBOutlet weak var coinImg: UIImageView!
    @IBOutlet weak var coinTitle: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
