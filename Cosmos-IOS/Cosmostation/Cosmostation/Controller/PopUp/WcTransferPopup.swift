//
//  WcTransferPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import WalletConnect

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
    
    var bnbOrderId :Int64?
    var bnbOrder :WCBinanceTransferOrder?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let firstMsg = bnbOrder?.msgs[0]
        let dpDenom = firstMsg?.outputs[0].coins[0].denom.split(separator: "-")[0]
        let dpAmount = NSDecimalNumber.init(value: firstMsg?.outputs[0].coins[0].amount ?? 0).multiplying(byPowerOf10: -8, withBehavior: WUtils.handler8)
        let url = TOKEN_IMG_URL + String(dpDenom!) + ".png"
        WcSendCoinIcon.af_setImage(withURL: URL(string: url)!)
        
        WcRecipientAddress.text = firstMsg?.outputs[0].address
        WcRecipientAddress.adjustsFontSizeToFitWidth = true
        WcSendCoinSymbol.text = String(dpDenom!)
        WcSendCoinAmount.attributedText = WUtils.displayAmount2(dpAmount.stringValue, WcSendCoinAmount.font, 0, 8)
        WcMemo.text = bnbOrder?.memo
        
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        popupViewController?.close()
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: -1)
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        popupViewController?.close()
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 1)
    }
}
