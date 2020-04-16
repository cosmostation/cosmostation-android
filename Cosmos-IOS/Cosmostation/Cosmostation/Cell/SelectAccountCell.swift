//
//  SelectAccountCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class SelectAccountCell: UITableViewCell {

    @IBOutlet weak var keyStatusImg: UIImageView!
    @IBOutlet weak var accountName: UILabel!
    @IBOutlet weak var accountAddress: UILabel!
    @IBOutlet weak var accountBalance: UILabel!
    @IBOutlet weak var accountDenom: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
}
