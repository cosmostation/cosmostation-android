//
//  TxRegisterDomainCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRegisterDomainCell: TxCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTitleLabel: UILabel!
    @IBOutlet weak var domainLabel: UILabel!
    @IBOutlet weak var adminLabel: UILabel!
    @IBOutlet weak var domainTypeLabel: UILabel!
    @IBOutlet weak var starnameFeeAmountLabel: UILabel!
    @IBOutlet weak var starnameFeeDenomLabel: UILabel!
    

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        starnameFeeAmountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        if let msg = try? Starnamed_X_Starname_V1beta1_MsgRegisterDomain.init(serializedData: response.tx.body.messages[position].value) {
            domainLabel.text = "*" + msg.name
            adminLabel.text = msg.admin
            domainTypeLabel.text = msg.domainType
            
            let starnameFee = WUtils.getStarNameRegisterDomainFee(msg.name, msg.domainType)
            WUtils.showCoinDp(IOV_MAIN_DENOM, starnameFee.stringValue, starnameFeeDenomLabel, starnameFeeAmountLabel, chain)
        }
    }
}
