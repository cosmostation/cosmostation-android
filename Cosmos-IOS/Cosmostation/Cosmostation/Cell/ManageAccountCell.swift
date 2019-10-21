//
//  ManageAccountCell.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ManageAccountCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var keyImg: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var address: UILabel!
    @IBOutlet weak var amount: UILabel!
    @IBOutlet weak var amountDenom: UILabel!
    @IBOutlet weak var arrowImg: UIImageView!
    @IBOutlet weak var arrowConstraint: NSLayoutConstraint!
    @IBOutlet weak var arrowConstraint2: NSLayoutConstraint!
    @IBOutlet weak var arrowConstraint3: NSLayoutConstraint!
    
}
