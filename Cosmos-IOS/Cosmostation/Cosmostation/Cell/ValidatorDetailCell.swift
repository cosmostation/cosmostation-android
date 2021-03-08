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
    @IBOutlet weak var bandOracleImg: UIImageView!
    @IBOutlet weak var operatorAddress: UILabel!
    @IBOutlet weak var website: UILabel!
    @IBOutlet weak var descriptionMsg: UILabel!
    @IBOutlet weak var totalBondedAmount: UILabel!
    @IBOutlet weak var selfBondedRate: UILabel!
    @IBOutlet weak var avergaeYield: UILabel!
    @IBOutlet weak var commissionRate: UILabel!
    @IBOutlet weak var delegateBtn: UIButton!
    
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
    
    var actionDelegate: (() -> Void)? = nil
    var actionTapUrl: (() -> Void)? = nil
    
    @IBAction func onClickDelegate(_ sender: Any) {
        actionDelegate?()
    }
    
    @objc func onTapUrl(sender:UITapGestureRecognizer) {
        actionTapUrl?()
    }
    
    
    
    func updateView(_ validator: Validator_V1?, _ selfDelegationInfo_V1: DelegationInfo_V1?, _ chainType: ChainType?) {
        monikerName.text = validator?.description?.moniker
        monikerName.adjustsFontSizeToFitWidth = true
        if (validator?.jailed == true) {
            jailedImg.isHidden = false
            validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            jailedImg.isHidden = true
            validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        freeEventImg.isHidden = true
        website.text = validator?.description?.website
        descriptionMsg.text = validator?.description?.details
        
        totalBondedAmount.attributedText = WUtils.displayAmount2(validator?.tokens, totalBondedAmount.font!, 6, 6)
        selfBondedRate.attributedText = WUtils.displaySelfBondRate(selfDelegationInfo_V1?.balance?.amount, validator?.tokens, selfBondedRate.font)
        commissionRate.attributedText = WUtils.displayCommission(validator?.commission?.commission_rates?.rate, font: commissionRate.font)
        if (validator?.status == BONDED_V1) {
            avergaeYield.attributedText = WUtils.getDpEstAprCommission(avergaeYield.font, validator!.getCommission(), chainType!)
        } else {
            avergaeYield.attributedText = WUtils.displayCommission(NSDecimalNumber.zero.stringValue, font: avergaeYield.font)
            avergaeYield.textColor = UIColor.init(hexString: "f31963")
        }
        validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType, validator!.operator_address!))!)
        
    }
}
