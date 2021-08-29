//
//  TxRenewStarnameCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRenewStarnameCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTitleLabel: UILabel!
    @IBOutlet weak var starnameLabel: UILabel!
    @IBOutlet weak var signerLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        if let msg = try? Starnamed_X_Starname_V1beta1_MsgRenewAccount.init(serializedData: response.tx.body.messages[position].value) {
            txIcon.image = UIImage.init(named: "renewaccountic28")
            txTitleLabel.text = "Renew Account"
            txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
            txIcon.tintColor = WUtils.getChainColor(chain)

            starnameLabel.text = msg.name + "*" + msg.domain
            signerLabel.text = msg.signer
            return
        }
        if let msg = try? Starnamed_X_Starname_V1beta1_MsgRenewDomain.init(serializedData: response.tx.body.messages[position].value) {
            txIcon.image = UIImage.init(named: "renewdomainic28")
            txTitleLabel.text = "Renew Domain"
            txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
            txIcon.tintColor = WUtils.getChainColor(chain)
            
            starnameLabel.text = msg.domain
            signerLabel.text = msg.signer
        }
    }
    
}
