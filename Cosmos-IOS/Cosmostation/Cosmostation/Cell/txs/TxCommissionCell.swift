//
//  txCommissionCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxCommissionCell: TxCell {

    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var commissionAmountLabel: UILabel!
    @IBOutlet weak var commissionDenomLabel: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        commissionAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, commissionDenomLabel)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        setDenomType(chain)
        let decimal = WUtils.mainDivideDecimal(chain)
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Cosmos_Distribution_V1beta1_MsgWithdrawValidatorCommission.init(serializedData: response.tx.body.messages[position].value)
        validatorLabel.text = msg.validatorAddress
        if let validator = BaseData.instance.mAllValidators_gRPC.filter({ $0.operatorAddress == msg.validatorAddress}).first {
            monikerLabel.text = "(" + validator.description_p.moniker + ")"
        }
        commissionAmountLabel.attributedText = WUtils.displayAmount2(WUtils.onParseCommisiondGrpc(response, position).stringValue, commissionAmountLabel.font!, decimal, decimal)
    }
}
