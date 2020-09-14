//
//  AddViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AddViewController: BaseViewController, SBCardPopupContent {
    static func create() -> UIViewController {
        let storyboard = UIStoryboard(name: "MainStoryboard", bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: "AddViewController") as! AddViewController
        return viewController
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = UIColor.clear
    }
    
    @IBAction func onClickImportMnemonic(_ sender: Any) {
//        print("onClickImportMnemonic")
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 2)
        popupViewController?.close()
        
    }
    
    
    @IBAction func onClickImportAddress(_ sender: Any) {
//        print("onClickImportAddress")
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 3)
        popupViewController?.close()
    }
    
    @IBAction func onClickCreate(_ sender: Any) {
//        print("onClickCreate")
        popupViewController?.resultDelegate?.SBCardPopupResponse(type: 0, result: 1)
        popupViewController?.close()
    }
    
    weak var popupViewController: SBCardPopupViewController?
    
    let allowsTapToDismissPopupCard = true
    let allowsSwipeToDismissPopupCard = false
}


