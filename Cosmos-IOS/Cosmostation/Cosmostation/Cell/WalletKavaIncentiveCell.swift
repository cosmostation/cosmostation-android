//
//  WalletKavaIncentiveCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/22.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class WalletKavaIncentiveCell: UITableViewCell {

    @IBOutlet weak var btnParticipate: UIButton!
    @IBOutlet weak var participateDone: UILabel!
    @IBOutlet weak var BtnConstraint: NSLayoutConstraint!
    @IBOutlet weak var LabelConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionParticipate: (() -> Void)? = nil
    @IBAction func onParticipate(_ sender: UIButton) {
        actionParticipate?()
    }
}
