//
//  WcTransferPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class WcTransferPopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false

    @IBOutlet weak var WcTitleLabel: UILabel!
    @IBOutlet weak var WcMsgLabel: UILabel!
    
    @IBOutlet weak var WcRecipientAddress: UILabel!
    @IBOutlet weak var WcSendCoinIcon: UIImageView!
    @IBOutlet weak var WcSendCoinSymbol: UILabel!
    @IBOutlet weak var WcSendCoinAmount: UILabel!
    @IBOutlet weak var WcMemo: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
