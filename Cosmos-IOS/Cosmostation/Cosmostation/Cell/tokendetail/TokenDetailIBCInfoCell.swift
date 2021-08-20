//
//  TokenDetailIBCInfoCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/20.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailIBCInfoCell: TokenDetailCell {

    @IBOutlet weak var statusImg: UIImageView!
    @IBOutlet weak var counterChainIDLabel: UILabel!
    @IBOutlet weak var channelLabel: UILabel!
    @IBOutlet weak var counterChannelLabel: UILabel!
    @IBOutlet weak var denomLabel: UILabel!
    @IBOutlet weak var counterDenomLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    func onBindIBCTokenInfo(_ chainType: ChainType, _ ibcDenom: String) {
        let ibcHash = ibcDenom.replacingOccurrences(of: "ibc/", with: "")
        if let ibcToken = BaseData.instance.getIbcToken(ibcHash) {
            if (ibcToken.auth == true) {
                statusImg.image = UIImage(named: "ibcauthed")
            } else {
                statusImg.image = UIImage(named: "ibcunknown")
            }
            counterChainIDLabel.text = ibcToken.counter_party?.chain_id
            channelLabel.text = ibcToken.channel_id
            counterChannelLabel.text = ibcToken.counter_party?.channel_id
            denomLabel.text = ibcDenom
            counterDenomLabel.text = ibcToken.base_denom
            
            
        } else {
            statusImg.image = UIImage(named: "ibcunknown")
            counterChainIDLabel.text = "unknown"
            channelLabel.text = "unknown"
            counterChannelLabel.text = "unknown"
            denomLabel.text = "unknown"
            counterDenomLabel.text = "unknown"
        }
    }
}
