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
    @IBOutlet weak var bandOracleOffImg: UIImageView!
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
        
        myDelegatedAmoutLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        myUndelegatingAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        rewardAmoutLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override func prepareForReuse() {
        self.validatorImg.image = UIImage(named: "validatorNoneImg")
        self.myDelegatedAmoutLabel.text = "-"
        self.myUndelegatingAmountLabel.text = "-"
        self.rewardAmoutLabel.text = "-"
        self.bandOracleOffImg.isHidden = true
        super.prepareForReuse()
    }
    
    func updateView(_ validator: Validator_V1, _ chainType: ChainType?) {
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
        
        let myDelegation = BaseData.instance.mMyDelegations_V1.filter { $0.delegation?.validator_address == validator.operator_address }.first
        myDelegatedAmoutLabel.attributedText = WUtils.displayAmount2(myDelegation?.balance?.amount, myDelegatedAmoutLabel.font, 6, 6)
        
        let myUnbonding = BaseData.instance.mMyUnbondings_V1.filter { $0.validator_address == validator.operator_address }.first
        myUndelegatingAmountLabel.attributedText = WUtils.displayAmount2(myUnbonding?.getAllUnbondingBalance().stringValue, myUndelegatingAmountLabel.font, 6, 6)
        
        let myReward = BaseData.instance.mMyReward_V1.filter { $0.validator_address == validator.operator_address }.first
        rewardAmoutLabel.attributedText = WUtils.displayAmount2(myReward?.getRewardByDenom(WUtils.getMainDenom(chainType)).stringValue, rewardAmoutLabel.font, 6, 6)
        
        cardView.backgroundColor = WUtils.getChainBg(chainType)
        validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType, validator.operator_address!))!)
    }
    
}
