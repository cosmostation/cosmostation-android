//
//  ManageChainCell.swift
//  Cosmostation
//
//  Created by yongjoo on 18/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ManageChainCell: UITableViewCell {

    @IBOutlet weak var chainCard: CardView!
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var chainName: UILabel!
    @IBOutlet weak var chainAll: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        if (selected) {
            chainCard.borderColor = UIColor.init(hexString: "#9ca2ac", alpha: 1.0)
            chainName.textColor = UIColor.white
            chainAll.textColor = UIColor.white
            chainImg.alpha = 1.0
        } else {
            chainCard.borderColor = UIColor.init(hexString: "#9ca2ac", alpha: 0.0)
            chainName.textColor = UIColor.init(hexString: "#4b4f54")
            chainAll.textColor = UIColor.init(hexString: "#4b4f54")
            chainImg.alpha = 0.1
        }
        
    }
    
}
