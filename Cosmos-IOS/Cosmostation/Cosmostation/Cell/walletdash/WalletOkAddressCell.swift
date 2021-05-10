//
//  WalletOkAddressCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/10.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class WalletOkAddressCell: UITableViewCell {
    
    @IBOutlet weak var keyState: UIImageView!
    @IBOutlet weak var ethAddress: UILabel!
    @IBOutlet weak var dpAddress: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionShare: (() -> Void)? = nil
    @IBAction func onClickShare(_ sender: Any) {
        actionShare?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        if (account!.account_has_private) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            keyState.tintColor = WUtils.getChainColor(chainType)
        }
        ethAddress.text = WKey.convertAddressOkexToEth(account!.account_address)
        ethAddress.adjustsFontSizeToFitWidth = true
        
        dpAddress.text = account?.account_address
        dpAddress.adjustsFontSizeToFitWidth = true
    }
}
