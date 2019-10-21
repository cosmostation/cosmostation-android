//
//  AccountPopupCell.swift
//  Cosmostation
//
//  Created by yongjoo on 02/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AccountPopupCell: UITableViewCell {
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
//        super.setSelected(selected, animated: animated)
    }
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var keyImg: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var address: UILabel!
    @IBOutlet weak var amount: UILabel!
    @IBOutlet weak var amountDenom: UILabel!
}
