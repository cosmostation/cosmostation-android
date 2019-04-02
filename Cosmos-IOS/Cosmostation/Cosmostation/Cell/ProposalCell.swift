//
//  ProposalCell.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ProposalCell: UITableViewCell {

    @IBOutlet weak var rootCard: CardView!
    @IBOutlet weak var proposalIdLabel: UILabel!
    @IBOutlet weak var proposalTitleLabel: UILabel!
    @IBOutlet weak var proposalStateLabel: UILabel!
    @IBOutlet weak var proposalMsgLabel: UILabel!
    @IBOutlet weak var proposalSubmitTime: UILabel!
    @IBOutlet weak var proposalStartTime: UILabel!
    @IBOutlet weak var proposalEndTime: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
