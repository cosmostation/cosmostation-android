//
//  CdpWarnPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpWarnPopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func onClickCancel(_ sender: UIButton) {
        popupViewController?.close()
    }
    
    @IBAction func onClickContinue(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: 1)
        popupViewController?.close()
    }
    
}
