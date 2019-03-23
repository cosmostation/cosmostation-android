//
//  ClaimRewardAllCell.swift
//  Cosmostation
//
//  Created by yongjoo on 23/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ClaimRewardAllCell: UITableViewCell {
    
    @IBOutlet weak var claimAllBtn: UIButton!
    @IBOutlet weak var totalRewardLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        self.selectionStyle = .none
        claimAllBtn.addTarget(self, action: #selector(startHighlight), for: .touchDown)
        claimAllBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpInside)
        claimAllBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpOutside)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    @objc func startHighlight(sender: UIButton) {
        claimAllBtn.layer.borderColor = UIColor.gray.cgColor
    }
    @objc func stopHighlight(sender: UIButton) {
        claimAllBtn.layer.borderColor = UIColor.white.cgColor
    }
    
}
