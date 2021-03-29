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
        } else if (warnImgType == 3) {
            self.warnImg.image = UIImage(named: "imgDelegate3Warning")
        } else if (warnImgType == 28) {
            self.warnImg.image = UIImage(named: "imgDelegate28Warning")
        } else {
            self.warnImg.image = UIImage(named: "imgDelegateWarning")
        }
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
