//
//  TxRewardCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRewardCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var delegatorLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        amountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, amountDenomLabel)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        setDenomType(chain)
        let decimal = WUtils.mainDivideDecimal(chain)
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Cosmos_Distribution_V1beta1_MsgWithdrawDelegatorReward.init(serializedData: response.tx.body.messages[position].value)
        delegatorLabel.text = msg.delegatorAddress
        validatorLabel.text = msg.validatorAddress
        if let validator = BaseData.instance.mAllValidators_gRPC.filter({ $0.operatorAddress == msg.validatorAddress}).first {
            monikerLabel.text = "(" + validator.description_p.moniker + ")"
        }
        amountLabel.attributedText = WUtils.displayAmount2(WUtils.onParseStakeRewardGrpc(response, msg.validatorAddress, position).stringValue, amountLabel.font!, decimal, decimal)
    }
}
