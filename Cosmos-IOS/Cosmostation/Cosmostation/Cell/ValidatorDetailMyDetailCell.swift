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
    
    func updateView(_ validator: Cosmos_Staking_V1beta1_Validator?, _ selfDelegation: Cosmos_Staking_V1beta1_DelegationResponse?, _ chainType: ChainType?) {
        monikerName.text = validator?.description_p.moniker
        monikerName.adjustsFontSizeToFitWidth = true
        if (validator?.jailed == true) {
            jailedImg.isHidden = false
            validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            jailedImg.isHidden = true
            validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        freeEventImg.isHidden = true
        website.text = validator?.description_p.website
        descriptionMsg.text = validator?.description_p.details
        
        totalBondedAmount.attributedText = WUtils.displayAmount2(validator?.tokens, totalBondedAmount.font!, WUtils.mainDivideDecimal(chainType), WUtils.mainDivideDecimal(chainType))
        selfBondedRate.attributedText = WUtils.displaySelfBondRate(selfDelegation?.balance.amount, validator?.tokens, selfBondedRate.font)
        commissionRate.attributedText = WUtils.displayCommission(NSDecimalNumber.init(string: validator?.commission.commissionRates.rate).multiplying(byPowerOf10: -18).stringValue, font: commissionRate.font)
        if (validator?.status == Cosmos_Staking_V1beta1_BondStatus.bonded) {
            avergaeYield.attributedText = WUtils.getDpEstAprCommission(avergaeYield.font, NSDecimalNumber.init(string: validator?.commission.commissionRates.rate).multiplying(byPowerOf10: -18), chainType!)
        } else {
            avergaeYield.attributedText = WUtils.displayCommission(NSDecimalNumber.zero.stringValue, font: avergaeYield.font)
            avergaeYield.textColor = UIColor.init(hexString: "f31963")
        }
        validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType, validator!.operatorAddress))!)
        
//        //display for band oracle status
//        if (chainType == ChainType.BAND_MAIN) {
//            if (!BaseData.instance.mBandOracle_gRPC.contains { $0.address == validator?.operatorAddress}) {
//                bandOracleImg.image = UIImage(named: "bandoracleoffl")
//                avergaeYield.textColor = UIColor.init(hexString: "f31963")
//            } else {
//                bandOracleImg.image = UIImage(named: "bandoracleonl")
//            }
//            bandOracleImg.isHidden = false
//        }
        
        
        //temp hide apr for no mint param chain
        if (chainType == ChainType.SIF_MAIN || chainType == ChainType.ALTHEA_TEST) {
            avergaeYield.text = "--"
        }
    }
    
}
