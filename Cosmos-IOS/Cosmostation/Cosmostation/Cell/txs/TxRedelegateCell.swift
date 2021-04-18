//
//  TxRedelegateCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRedelegateCell: TxCell {

    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var redelegatorLabel: UILabel!
    @IBOutlet weak var fromValidatorLabel: UILabel!
    @IBOutlet weak var fromMonikerLabel: UILabel!
    @IBOutlet weak var toValidatorLabel: UILabel!
    @IBOutlet weak var toMonikerLabel: UILabel!
    @IBOutlet weak var redelegateAmountLabel: UILabel!
    @IBOutlet weak var redelegateDenomLabel: UILabel!
    @IBOutlet weak var autoRewardLayer: UIView!
    @IBOutlet weak var autoRewardAmountLabel: UILabel!
    @IBOutlet weak var autoRewardDenomLabel: UILabel!

    @IBOutlet weak var autoRewardBottomConstraint: NSLayoutConstraint!
    @IBOutlet weak var feeBottomConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        redelegateAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        autoRewardAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, redelegateDenomLabel)
        WUtils.setDenomTitle(chainType, autoRewardDenomLabel)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        setDenomType(chain)
        let decimal = WUtils.mainDivideDecimal(chain)
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Cosmos_Staking_V1beta1_MsgBeginRedelegate.init(serializedData: response.tx.body.messages[position].value)
        
        redelegatorLabel.text = msg.delegatorAddress
        fromValidatorLabel.text = msg.validatorSrcAddress
        if let fValidator = BaseData.instance.mAllValidators_gRPC.filter({ $0.operatorAddress == msg.validatorSrcAddress}).first {
            fromMonikerLabel.text = "(" + fValidator.description_p.moniker + ")"
        }
        toValidatorLabel.text = msg.validatorDstAddress
        if let dValidator = BaseData.instance.mAllValidators_gRPC.filter({ $0.operatorAddress == msg.validatorDstAddress}).first {
            toMonikerLabel.text = "(" + dValidator.description_p.moniker + ")"
        }
        redelegateAmountLabel.attributedText = WUtils.displayAmount2(msg.amount.amount, redelegateAmountLabel.font!, decimal, decimal)
        autoRewardAmountLabel.attributedText = WUtils.displayAmount2(WUtils.onParseAutoRewardGrpc(response, msg.delegatorAddress, position).stringValue, autoRewardAmountLabel.font!, decimal, decimal)
    }
}
