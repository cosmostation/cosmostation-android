//
//  Bep3SupplyPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class Bep3SupplyPopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false
    
    @IBOutlet weak var supplyTitle: UILabel!
    @IBOutlet weak var supplyMsg: UILabel!
    
    @IBOutlet weak var maxAmount: UILabel!
    @IBOutlet weak var maxDenom: UILabel!
    @IBOutlet weak var currentAmount: UILabel!
    @IBOutlet weak var currentDenom: UILabel!
    @IBOutlet weak var remainAmount: UILabel!
    @IBOutlet weak var remainDenom: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        maxAmount.attributedText = WUtils.displayAmount2(swapSupply.getLimitAmount().stringValue, maxAmount.font, 8, 8)
//        currentAmount.attributedText = WUtils.displayAmount2(swapSupply.getCurrentCapAmount().stringValue, currentAmount.font, 8, 8)
//        remainAmount.attributedText = WUtils.displayAmount2(swapSupply.getRemainAmount().stringValue, remainAmount.font, 8, 8)
//        
//        if (swapSupply.getRemainAmount().compare(NSDecimalNumber.zero).rawValue <= 0) {
//            supplyTitle.text = NSLocalizedString("str_bep3_unavailable_title", comment: "")
//            supplyMsg.text = NSLocalizedString("str_bep3_bnb_cap_over", comment: "")
//            
//        } else {
//            supplyTitle.text = NSLocalizedString("str_bep3_available_title", comment: "")
//            let msg = String(format: NSLocalizedString("str_bep3_bnb_cap", comment: ""), remainAmount.text!)
//            supplyMsg.text = msg
//            
//        }
    }

    @IBAction func onClickOk(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: -1)
        popupViewController?.close()
    }
}
