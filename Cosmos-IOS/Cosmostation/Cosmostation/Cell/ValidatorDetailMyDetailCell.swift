//
//  ValidatorDetailMyDetailCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ValidatorDetailMyDetailCell: UITableViewCell {
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var validatorImg: UIImageView!
    @IBOutlet weak var freeEventImg: UIImageView!
    @IBOutlet weak var jailedImg: UIImageView!
    @IBOutlet weak var monikerName: UILabel!
    @IBOutlet weak var bandOracleImg: UIImageView!
    @IBOutlet weak var operatorAddress: UILabel!
    @IBOutlet weak var website: UILabel!
    @IBOutlet weak var descriptionMsg: UILabel!
    @IBOutlet weak var totalBondedAmount: UILabel!
    @IBOutlet weak var selfBondedRate: UILabel!
    @IBOutlet weak var avergaeYield: UILabel!
    @IBOutlet weak var commissionRate: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        validatorImg.layer.borderWidth = 1
        validatorImg.layer.masksToBounds = false
        validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        validatorImg.layer.cornerRadius = validatorImg.frame.height/2
        validatorImg.clipsToBounds = true
        self.selectionStyle = .none
        
        totalBondedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        selfBondedRate.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        avergaeYield.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        commissionRate.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onTapUrl))
        website.isUserInteractionEnabled = true
        website.addGestureRecognizer(tap)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    var actionTapUrl: (() -> Void)? = nil
    @objc func onTapUrl(sender:UITapGestureRecognizer) {
        actionTapUrl?()
    }
    
}
