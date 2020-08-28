//
//  DelegateWarnPopup.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class DelegateWarnPopup: BaseViewController, SBCardPopupContent {
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false
    var warnImgType:Int?
    @IBOutlet weak var warnImg: UIImageView!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        if (warnImgType == 14) {
            self.warnImg.image = UIImage(named: "imgDelegate14Warning")
        }
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
