//
//  ResourceCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ResourceCell: UITableViewCell {
    
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var chainName: UILabel!
    @IBOutlet weak var chainAddress: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
}
