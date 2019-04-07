//
//  ValidatorDetailCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ValidatorDetailCell: UITableViewCell {
    
    @IBOutlet weak var validatorImg: UIImageView!
    @IBOutlet weak var freeEventImg: UIImageView!
    @IBOutlet weak var jailedImg: UIImageView!
    @IBOutlet weak var monikerName: UILabel!
    @IBOutlet weak var operatorAddress: UILabel!
    @IBOutlet weak var website: UILabel!
    @IBOutlet weak var descriptionMsg: UILabel!
    @IBOutlet weak var totalBondedAmount: UILabel!
    @IBOutlet weak var selfBondedRate: UILabel!
    @IBOutlet weak var commissionRate: UILabel!
    @IBOutlet weak var delegateBtn: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    var actionDelegate: (() -> Void)? = nil
    @IBAction func onClickDelegate(_ sender: Any) {
        actionDelegate?()
    }
}
