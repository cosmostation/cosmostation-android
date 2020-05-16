//
//  VoteInfoTableViewCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class VoteInfoTableViewCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
 
    @IBOutlet weak var statusImg: UIImageView!
    @IBOutlet weak var statusTitle: UILabel!
    @IBOutlet weak var proposalTitle: UILabel!
    @IBOutlet weak var proposerLabel: UILabel!
    @IBOutlet weak var proposalTypeLabel: UILabel!
    @IBOutlet weak var voteStartTime: UILabel!
    @IBOutlet weak var voteEndTime: UILabel!
    @IBOutlet weak var voteDescription: UITextView!
    @IBOutlet weak var btnToggle: UIButton!
    
    var actionLink: (() -> Void)? = nil
    var actionToggle: (() -> Void)? = nil
    var expended: Bool = false
    
    
    @IBAction func onClickLink(_ sender: UIButton) {
        actionLink?()
    }
    
    @IBAction func onClikcToggle(_ sender: UIButton) {
        if (expended) {
            btnToggle.setImage(UIImage(named: "arrowDown"), for: .normal)
        } else {
            btnToggle.setImage(UIImage(named: "arrowUp"), for: .normal)
        }
        actionToggle?()
        expended = !expended
    }
    
}
