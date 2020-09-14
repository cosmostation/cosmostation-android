//
//  RiskCheckPopupViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/04.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class RiskCheckPopupViewController: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false
    let RISK_POPUP_CHECK = 0
    let RISK_POPUP_CREATE = 1
    let RISK_POPUP_CHANGE = 2
    
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var btnOk: UIButton!
    
    @IBOutlet weak var riskCard: CardView!
    @IBOutlet weak var riskScore: UILabel!
    @IBOutlet weak var riskText: UILabel!
    
    @IBOutlet weak var beforeRiskCard: CardView!
    @IBOutlet weak var beforeRiskScore: UILabel!
    @IBOutlet weak var beforeRiskText: UILabel!
    @IBOutlet weak var afterRiskCard: CardView!
    @IBOutlet weak var afterRiskScore: UILabel!
    @IBOutlet weak var afterRiskText: UILabel!
    @IBOutlet weak var arrowRisk: UIImageView!
    
    @IBOutlet weak var currentPriceTitle: UILabel!
    @IBOutlet weak var currentPrice: UILabel!
    @IBOutlet weak var liquidationPriceTitle: UILabel!
    @IBOutlet weak var liquidationPrice: UILabel!
    
    @IBOutlet weak var afterLiquidationLayer: UIView!
    @IBOutlet weak var afterLiquidationPriceTitle: UILabel!
    @IBOutlet weak var afterLiquidationPrice: UILabel!
    
    var type: Int?
    var cDenom: String?
    var DNcurrentPrice: NSDecimalNumber?
    var DNliquidationPrice: NSDecimalNumber?
    var DNriskRate: NSDecimalNumber?
    var DNbeforeLiquidationPrice: NSDecimalNumber?
    var DNafterLiquidationPrice: NSDecimalNumber?
    var DNbeforeRiskRate: NSDecimalNumber?
    var DNafterRiskRate: NSDecimalNumber?

    override func viewDidLoad() {
        super.viewDidLoad()
        if (type == RISK_POPUP_CHECK) {
            controlLayer.isHidden = true
            btnOk.isHidden = false
            beforeRiskCard.isHidden = true
            afterRiskCard.isHidden = true
            arrowRisk.isHidden = true
            riskCard.isHidden = false
            afterLiquidationLayer.isHidden = true
            
            WUtils.showRiskRate3(DNriskRate!, riskScore, riskText, riskCard)
            
            currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), cDenom!.uppercased())
            currentPrice.attributedText = WUtils.getDPRawDollor(DNcurrentPrice!.stringValue, 4, currentPrice.font)
            liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), cDenom!.uppercased())
            liquidationPrice.attributedText = WUtils.getDPRawDollor(DNliquidationPrice!.stringValue, 4, liquidationPrice.font)
            
        } else if (type == RISK_POPUP_CREATE) {
            controlLayer.isHidden = false
            btnOk.isHidden = true
            beforeRiskCard.isHidden = true
            afterRiskCard.isHidden = true
            arrowRisk.isHidden = true
            riskCard.isHidden = false
            afterLiquidationLayer.isHidden = true
            
            WUtils.showRiskRate3(DNriskRate!, riskScore, riskText, riskCard)
            currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), cDenom!.uppercased())
            currentPrice.attributedText = WUtils.getDPRawDollor(DNcurrentPrice!.stringValue, 4, currentPrice.font)
            liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), cDenom!.uppercased())
            liquidationPrice.attributedText = WUtils.getDPRawDollor(DNliquidationPrice!.stringValue, 4, liquidationPrice.font)
            
        } else if (type == RISK_POPUP_CHANGE) {
            controlLayer.isHidden = false
            btnOk.isHidden = true
            beforeRiskCard.isHidden = false
            afterRiskCard.isHidden = false
            arrowRisk.isHidden = false
            riskCard.isHidden = true
            afterLiquidationLayer.isHidden = false
            
            WUtils.showRiskRate3(DNbeforeRiskRate!, beforeRiskScore, beforeRiskText, beforeRiskCard)
            WUtils.showRiskRate3(DNafterRiskRate!, afterRiskScore, afterRiskText, afterRiskCard)
            currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), cDenom!.uppercased())
            currentPrice.attributedText = WUtils.getDPRawDollor(DNcurrentPrice!.stringValue, 4, currentPrice.font)
            liquidationPriceTitle.text = String(format: NSLocalizedString("before_liquidation_price_format", comment: ""), cDenom!.uppercased())
            liquidationPrice.attributedText = WUtils.getDPRawDollor(DNbeforeLiquidationPrice!.stringValue, 4, liquidationPrice.font)
            afterLiquidationPriceTitle.text = String(format: NSLocalizedString("after_liquidation_price_format", comment: ""), cDenom!.uppercased())
            afterLiquidationPrice.attributedText = WUtils.getDPRawDollor(DNafterLiquidationPrice!.stringValue, 4, liquidationPrice.font)
            
        }
    }

    @IBAction func onClickCancel(_ sender: UIButton) {
        popupViewController?.close()
        
    }
    
    @IBAction func onClickContinue(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 10)
        popupViewController?.close()
    }
    
    @IBAction func onClickOk(_ sender: UIButton) {
        popupViewController?.close()
    }
    
    
}
