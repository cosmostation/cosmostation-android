//
//  RestorePathCell.swift
//  Cosmostation
//
//  Created by yongjoo on 29/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class RestorePathCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    @IBOutlet weak var rootCardView: CardView!
    @IBOutlet weak var pathLabel: UILabel!
    @IBOutlet weak var stateLabel: UILabel!
    @IBOutlet weak var addressLabel: UILabel!
    @IBOutlet weak var denomLayer: UIStackView!
    @IBOutlet weak var denomTitle: UILabel!
    @IBOutlet weak var denomAmount: UILabel!
    
}
