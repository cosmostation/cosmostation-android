//
//  MemoMnemonicPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/04/27.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class MemoMnemonicPopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false
    

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    

    @IBAction func onClickCancel(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: -1)
        popupViewController?.close()
    }
    @IBAction func onClickConfirm(_ sender: UIButton) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 1)
        popupViewController?.close()
    }
}
