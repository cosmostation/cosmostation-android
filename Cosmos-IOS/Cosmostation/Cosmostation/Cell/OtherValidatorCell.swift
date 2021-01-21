//
//  OtherValidatorCell.swift
//  Cosmostation
//
//  Created by yongjoo on 04/06/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class OtherValidatorCell: UITableViewCell {
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var validatorImg: UIImageView!
    @IBOutlet weak var freeEventImg: UIImageView!
    @IBOutlet weak var revokedImg: UIImageView!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var bandOracleOffImg: UIImageView!
    @IBOutlet weak var powerLabel: UILabel!
    @IBOutlet weak var commissionLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        validatorImg.layer.borderWidth = 1
        validatorImg.layer.masksToBounds = false
        validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        validatorImg.layer.cornerRadius = validatorImg.frame.height/2
        validatorImg.clipsToBounds = true
        
        self.selectionStyle = .none
        
        powerLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        commissionLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override func prepareForReuse() {
        self.validatorImg.image = UIImage(named: "validatorNoneImg")
        self.monikerLabel.text = "-"
        self.powerLabel.text = "-"
        self.commissionLabel.text = "-"
        self.bandOracleOffImg.isHidden = true
        super.prepareForReuse()
    }
    
    func updateView(_ validator: Validator_V1, _ chainType: ChainType?) {
        if (chainType == ChainType.COSMOS_TEST) {
            powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, powerLabel.font!, 6, 6)
            commissionLabel.attributedText = WUtils.getDpEstAprCommission(commissionLabel.font, NSDecimalNumber.one, chainType!)
            validatorImg.af_setImage(withURL: URL(string: COSMOS_VAL_URL + validator.operator_address! + ".png")!)
            
            monikerLabel.text = validator.description?.moniker
            monikerLabel.adjustsFontSizeToFitWidth = true
            freeEventImg.isHidden = true
            if (validator.jailed == true) {
                revokedImg.isHidden = false
                validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
            } else {
                revokedImg.isHidden = true
                validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
            }
            if BaseData.instance.mMyValidators_V1.first(where: {$0.operator_address == validator.operator_address}) != nil {
                cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            } else {
                cardView.backgroundColor = COLOR_BG_GRAY
            }
            
        } else if (chainType == ChainType.IRIS_TEST) {
            powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, powerLabel.font!, 6, 6)
            commissionLabel.attributedText = WUtils.getDpEstAprCommission(commissionLabel.font, NSDecimalNumber.one, chainType!)
            validatorImg.af_setImage(withURL: URL(string: IRIS_VAL_URL + validator.operator_address! + ".png")!)
            
            monikerLabel.text = validator.description?.moniker
            monikerLabel.adjustsFontSizeToFitWidth = true
            freeEventImg.isHidden = true
            if (validator.jailed == true) {
                revokedImg.isHidden = false
                validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
            } else {
                revokedImg.isHidden = true
                validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
            }
            if BaseData.instance.mMyValidators_V1.first(where: {$0.operator_address == validator.operator_address}) != nil {
                cardView.backgroundColor = TRANS_BG_COLOR_IRIS
            } else {
                cardView.backgroundColor = COLOR_BG_GRAY
            }
            
        }
        
    }
    
}
