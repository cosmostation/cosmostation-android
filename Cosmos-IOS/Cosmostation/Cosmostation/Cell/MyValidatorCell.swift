//
//  MyValidatorCell.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MyValidatorCell: UITableViewCell {
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var validatorImg: UIImageView!
    @IBOutlet weak var freeEventImg: UIImageView!
    @IBOutlet weak var revokedImg: UIImageView!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var myDelegatedAmoutLabel: UILabel!
    @IBOutlet weak var myUndelegatingAmountLabel: UILabel!
    @IBOutlet weak var rewardAmoutLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        validatorImg.layer.borderWidth = 1
        validatorImg.layer.masksToBounds = false
        validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        validatorImg.layer.cornerRadius = validatorImg.frame.height/2
        validatorImg.clipsToBounds = true
        
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override func prepareForReuse() {
        self.validatorImg.image = UIImage(named: "validatorNoneImg")
        self.myDelegatedAmoutLabel.text = "-"
        self.myUndelegatingAmountLabel.text = "-"
        self.rewardAmoutLabel.text = "-"
    }
    
}
