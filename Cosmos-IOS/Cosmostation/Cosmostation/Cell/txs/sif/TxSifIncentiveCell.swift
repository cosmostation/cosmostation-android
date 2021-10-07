//
//  TxSifIncentiveCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxSifIncentiveCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var claimAddress: UILabel!
    @IBOutlet weak var claimType: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Sifnode_Dispensation_V1_MsgCreateUserClaim.init(serializedData: response.tx.body.messages[position].value)
        claimAddress.text = msg.userClaimAddress
        claimAddress.adjustsFontSizeToFitWidth = true
        
        if (msg.userClaimType.rawValue == Sifnode_Dispensation_V1_DistributionType.liquidityMining.rawValue) {
            claimType.text = "liquidityMining"
        } else if (msg.userClaimType.rawValue == Sifnode_Dispensation_V1_DistributionType.validatorSubsidy.rawValue) {
            claimType.text = "validatorSubsidy"
        } else if (msg.userClaimType.rawValue == Sifnode_Dispensation_V1_DistributionType.airdrop.rawValue) {
            claimType.text = "airdrop"
        } else {
            claimType.text = "unspecified"
        }
    }
    
}
