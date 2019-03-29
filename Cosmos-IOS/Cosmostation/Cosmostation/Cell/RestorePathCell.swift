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
    @IBOutlet weak var atomLayer: UIStackView!
    @IBOutlet weak var atomTitle: UILabel!
    @IBOutlet weak var atomAmount: UILabel!
    @IBOutlet weak var photonLayer: UIStackView!
    @IBOutlet weak var photonTitle: UILabel!
    @IBOutlet weak var photonAmount: UILabel!
    
}
