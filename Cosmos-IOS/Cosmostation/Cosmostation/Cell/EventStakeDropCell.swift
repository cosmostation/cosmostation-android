//
//  EventStakeDropCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/23.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class EventStakeDropCell: UITableViewCell {
    
    @IBOutlet weak var rootCard: CardView!
    @IBOutlet weak var eventImg: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    var actionClose: (() -> Void)? = nil
    var actionEvent: (() -> Void)? = nil
    @IBAction func onClickClose(_ sender: UIButton) {
        actionClose?()
    }
    @IBAction func onClickEvent(_ sender: UIButton) {
        actionEvent?()
    }
}
