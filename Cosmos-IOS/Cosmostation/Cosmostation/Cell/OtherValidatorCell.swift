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
    
    func updateView(_ validator: Cosmos_Staking_V1beta1_Validator, _ chainType: ChainType?) {
        powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, powerLabel.font!, WUtils.mainDivideDecimal(chainType), 6)
        commissionLabel.attributedText = WUtils.getDpEstAprCommission(commissionLabel.font, NSDecimalNumber.one, chainType!)
        validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType, validator.operatorAddress))!)
        
        monikerLabel.text = validator.description_p.moniker
        monikerLabel.adjustsFontSizeToFitWidth = true
        freeEventImg.isHidden = true
        if (validator.jailed == true) {
            revokedImg.isHidden = false
            validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            revokedImg.isHidden = true
            validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        if BaseData.instance.mMyValidators_gRPC.first(where: {$0.operatorAddress == validator.operatorAddress}) != nil {
            cardView.backgroundColor = WUtils.getChainBg(chainType)
        } else {
            cardView.backgroundColor = COLOR_BG_GRAY
        }
        
//        //display for band oracle status
//        if (chainType == ChainType.BAND_MAIN) {
//            bandOracleOffImg.isHidden = false
//        }
        
        //temp hide apr for no mint param chain
        if (chainType == ChainType.SIF_MAIN || chainType == ChainType.OSMOSIS_MAIN || chainType == ChainType.ALTHEA_TEST) {
            commissionLabel.text = "--"
        }
    }
    
}
