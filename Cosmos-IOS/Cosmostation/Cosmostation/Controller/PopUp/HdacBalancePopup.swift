//
//  HdacBalancePopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/03.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class HdacBalancePopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false
    
    @IBOutlet weak var hdacAddressLabel: UILabel!
    @IBOutlet weak var hdacBalanceLabel: UILabel!
    
    var address: String = ""
    var amount: NSDecimalNumber = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        hdacAddressLabel.text = address
        hdacBalanceLabel.attributedText = WUtils.displayAmount2(amount.stringValue, hdacBalanceLabel.font!, 0, 8)
    }

    @IBAction func onClickCancel(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: -1)
        popupViewController?.close()
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        if (amount.compare(NSDecimalNumber.init(string: "0.1")).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
            return
            
        } else {
            popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 1)
            popupViewController?.close()
        }
    }
}
