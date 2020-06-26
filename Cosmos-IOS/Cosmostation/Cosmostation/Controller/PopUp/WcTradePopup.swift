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
    var bnbOrder :WCBinanceOrder?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        if let order = bnbOrder as? WCBinanceTradeOrder {
//            tradeConstraint.priority = .defaultHigh
//            cancelConstraint.priority = .defaultLow
//            
//        }
//        
//        if let order = bnbOrder as? WCBinanceCancelOrder {
//            tradeConstraint.priority = .defaultLow
//            cancelConstraint.priority = .defaultHigh
//            
//        }
        
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
