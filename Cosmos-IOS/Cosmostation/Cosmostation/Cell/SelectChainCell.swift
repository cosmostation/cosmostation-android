//
//  SelectChainCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class SelectChainCell: UITableViewCell {

    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var chainTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
}
