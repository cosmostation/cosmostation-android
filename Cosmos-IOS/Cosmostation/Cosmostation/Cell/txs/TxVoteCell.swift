//
//  TxVoteCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxVoteCell: TxCell {

    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var voterLabel: UILabel!
    @IBOutlet weak var proposalIdLabel: UILabel!
    @IBOutlet weak var opinionLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Cosmos_Gov_V1beta1_MsgVote.init(serializedData: response.tx.body.messages[position].value)
        voterLabel.text = msg.voter
        proposalIdLabel.text = String(msg.proposalID)
        if (msg.option == Cosmos_Gov_V1beta1_VoteOption.yes) {
            opinionLabel.text = "Yes"
            
        } else if (msg.option == Cosmos_Gov_V1beta1_VoteOption.no) {
            opinionLabel.text = "No"
            
        } else if (msg.option == Cosmos_Gov_V1beta1_VoteOption.noWithVeto) {
            opinionLabel.text = "No With Veto"
            
        } else if (msg.option == Cosmos_Gov_V1beta1_VoteOption.abstain) {
            opinionLabel.text = "Abstain"
            
        }
    }
    
}
