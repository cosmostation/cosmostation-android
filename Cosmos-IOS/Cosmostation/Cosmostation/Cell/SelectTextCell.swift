//
//  SelectTextCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/13.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class SelectTextCell: UITableViewCell {

    @IBOutlet weak var selectTextLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
}
