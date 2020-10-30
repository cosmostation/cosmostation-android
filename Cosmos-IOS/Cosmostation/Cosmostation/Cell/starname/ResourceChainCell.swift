//
//  ResourceChainCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ResourceChainCell: UITableViewCell {

    @IBOutlet weak var cardRoot: CardView!
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var chainName: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
