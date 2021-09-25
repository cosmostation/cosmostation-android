//
//  SelectRelayerCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/25.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class SelectRelayerCell: UITableViewCell {
    
    @IBOutlet weak var channelTitle: UILabel!
    @IBOutlet weak var channelMsg: UILabel!
    @IBOutlet weak var channelStausImg: UIImageView!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
}
