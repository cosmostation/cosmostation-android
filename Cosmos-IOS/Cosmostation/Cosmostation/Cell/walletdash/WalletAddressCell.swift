//
//  WalletAddressCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletAddressCell: UITableViewCell {

    @IBOutlet weak var keyState: UIImageView!
    @IBOutlet weak var dpAddress: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    
    var actionWebLink: (() -> Void)? = nil
    var actionShare: (() -> Void)? = nil
    
    @IBAction func onClickWebLink(_ sender: Any) {
        actionWebLink?()
    }
    @IBAction func onClickShare(_ sender: Any) {
        actionShare?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        if (account!.account_has_private) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            keyState.tintColor = WUtils.getChainColor(chainType)
        }
        dpAddress.text = account?.account_address
        dpAddress.adjustsFontSizeToFitWidth = true
    }
}
