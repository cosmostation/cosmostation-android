//
//  WcCancelPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import WalletConnect

class WcCancelPopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false

    @IBOutlet weak var WcTitleLabel: UILabel!
    @IBOutlet weak var WcMsgLabel: UILabel!
    @IBOutlet weak var WcSymbolLabel: UILabel!
    
    var bnbOrderId :Int64?
    var bnbOrder :WCBinanceCancelOrder?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let firstMsg = bnbOrder?.msgs[0]
        WcSymbolLabel.text = firstMsg?.symbol
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: -1)
        popupViewController?.close()
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: 1)
        popupViewController?.close()
    }
}
