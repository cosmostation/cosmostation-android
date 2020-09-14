//
//  WcTradePopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import WalletConnect

class WcTradePopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false

    @IBOutlet weak var WcTitleLabel: UILabel!
    @IBOutlet weak var WcMsgLabel: UILabel!
    @IBOutlet weak var WcSideLabel: UILabel!
    @IBOutlet weak var WcSymbolLabel: UILabel!
    @IBOutlet weak var WcPriceLayer: UIView!
    @IBOutlet weak var WcPriceLabel: UILabel!
    @IBOutlet weak var WcPriceDenom: UILabel!
    
    @IBOutlet weak var WcFromCard: CardView!
    @IBOutlet weak var WcFromIcon: UIImageView!
    @IBOutlet weak var WcFromSymbol: UILabel!
    @IBOutlet weak var WcFromAmount: UILabel!
    
    @IBOutlet weak var WcArrowImg: UIImageView!
    
    @IBOutlet weak var WcToCard: CardView!
    @IBOutlet weak var WcToIcon: UIImageView!
    @IBOutlet weak var WcToSymbol: UILabel!
    @IBOutlet weak var WcToAmount: UILabel!
    
    var bnbOrderId :Int64?
    var bnbOrder :WCBinanceTradeOrder?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let firstMsg = bnbOrder?.msgs[0]
        let pair_denom = firstMsg!.symbol.split(separator: "_")
        WcSymbolLabel.text = String(pair_denom[0].split(separator: "-")[0])
        WcPriceDenom.text = String(pair_denom[1].split(separator: "-")[0])
        
        let dpPrice = NSDecimalNumber.init(value: firstMsg!.price).multiplying(byPowerOf10: -8, withBehavior: WUtils.handler8)
        let dpAmount = NSDecimalNumber.init(value: firstMsg!.quantity).multiplying(byPowerOf10: -8, withBehavior: WUtils.handler8)
        WcPriceLabel.attributedText = WUtils.displayAmount2(dpPrice.stringValue, WcPriceLabel.font, 0, 8)
        
        if (firstMsg!.side == 1) {
            WcSideLabel.text = "BUY"
            WcSideLabel.textColor = COLOR_WC_TRADE_BUY
            
            let fromUrl = TOKEN_IMG_URL + pair_denom[1] + ".png"
            WcFromIcon.af_setImage(withURL: URL(string: fromUrl)!)
            
            let toUrl = TOKEN_IMG_URL + pair_denom[0] + ".png"
            WcToIcon.af_setImage(withURL: URL(string: toUrl)!)
            
            WcFromSymbol.text = String(pair_denom[1].split(separator: "-")[0])
            WcToSymbol.text = String(pair_denom[0].split(separator: "-")[0])
            
            WcFromAmount.attributedText = WUtils.displayAmount2(dpPrice.multiplying(by: dpAmount, withBehavior: WUtils.handler8).stringValue, WcPriceLabel.font, 0, 8)
            WcToAmount.attributedText = WUtils.displayAmount2(dpAmount.stringValue, WcPriceLabel.font, 0, 8)
            
        } else {
            WcSideLabel.text = "SELL"
            WcSideLabel.textColor = COLOR_WC_TRADE_SELL
            
            let fromUrl = TOKEN_IMG_URL + pair_denom[0] + ".png"
            WcFromIcon.af_setImage(withURL: URL(string: fromUrl)!)
            
            let toUrl = TOKEN_IMG_URL + pair_denom[1] + ".png"
            WcToIcon.af_setImage(withURL: URL(string: toUrl)!)
            
            WcFromSymbol.text = String(pair_denom[0].split(separator: "-")[0])
            WcToSymbol.text = String(pair_denom[1].split(separator: "-")[0])
            
            WcFromAmount.attributedText = WUtils.displayAmount2(dpAmount.stringValue, WcPriceLabel.font, 0, 8)
            WcToAmount.attributedText = WUtils.displayAmount2(dpPrice.multiplying(by: dpAmount, withBehavior: WUtils.handler8).stringValue, WcPriceLabel.font, 0, 8)
            
        }
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
