//
//  RedelegateCell.swift
//  Cosmostation
//
//  Created by yongjoo on 24/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class RedelegateCell: UITableViewCell {

    @IBOutlet weak var rootCard: CardView!
    @IBOutlet weak var valImg: UIImageView!
    @IBOutlet weak var valjailedImg: UIImageView!
    @IBOutlet weak var valCheckedImg: UIImageView!
    @IBOutlet weak var valMonikerLabel: UILabel!
    @IBOutlet weak var valPowerLabel: UILabel!
    @IBOutlet weak var valCommissionLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        valImg.layer.borderWidth = 1
        valImg.layer.masksToBounds = false
        valImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        valImg.layer.cornerRadius = valImg.frame.height/2
        valImg.clipsToBounds = true
        
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.valImg.image = UIImage(named: "validatorNoneImg")
    }
    
}
