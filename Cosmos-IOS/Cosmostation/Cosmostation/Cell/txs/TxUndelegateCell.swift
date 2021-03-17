//
//  TxUndelegateCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxUndelegateCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var undelegatorLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var undelegateAmountLabel: UILabel!
    @IBOutlet weak var undelegateDenomLabel: UILabel!
    @IBOutlet weak var autoRewardLayer: UIView!
    @IBOutlet weak var autoRewardAmountLabel: UILabel!
    @IBOutlet weak var autoRewardDenomLabel: UILabel!
    
    @IBOutlet weak var autoRewardBottomConstraint: NSLayoutConstraint!
    @IBOutlet weak var feeBottomConstraint: NSLayoutConstraint!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        undelegateAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        autoRewardAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, undelegateDenomLabel)
        WUtils.setDenomTitle(chainType, autoRewardDenomLabel)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        setDenomType(chain)
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Cosmos_Staking_V1beta1_MsgUndelegate.init(serializedData: response.tx.body.messages[position].value)
        undelegatorLabel.text = msg.delegatorAddress
        validatorLabel.text = msg.validatorAddress
        if let validator = BaseData.instance.mAllValidators_gRPC.filter({ $0.operatorAddress == msg.validatorAddress}).first {
            monikerLabel.text = "(" + validator.description_p.moniker + ")"
        }
        undelegateAmountLabel.attributedText = WUtils.displayAmount2(msg.amount.amount, undelegateAmountLabel.font!, 6, 6)
        autoRewardAmountLabel.attributedText = WUtils.displayAmount2(WUtils.onParseAutoRewardGrpc(response, msg.delegatorAddress, position).stringValue, autoRewardAmountLabel.font!, 6, 6)
    }
}
