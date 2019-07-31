//
//  PromotionCell.swift
//  Cosmostation
//
//  Created by yongjoo on 23/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class PromotionCell: UITableViewCell {

    @IBOutlet weak var cardView: CardView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
