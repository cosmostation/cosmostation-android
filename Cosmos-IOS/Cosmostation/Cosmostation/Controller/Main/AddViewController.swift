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
        
        // Do any additional setup after loading the view.
        view.backgroundColor = UIColor.clear
    }
    
    @IBAction func onClickImportMnemonic(_ sender: Any) {
        print("onClickImportMnemonic")
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: 2)
        popupViewController?.close()
//        let value:[String: String] = ["import": "importMnemonic"]
//        NotificationCenter.default.post(name: Notification.Name("startImport"), object: nil, userInfo: value)
//        self .resultDelegate?.importResponse(result: 2)
        
    }
    
    
    @IBAction func onClickImportAddress(_ sender: Any) {
        print("onClickImportAddress")
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: 3)
        popupViewController?.close()
//        let value:[String: String] = ["import": "importAddress"]
//        NotificationCenter.default.post(name: Notification.Name("startImport"), object: nil, userInfo: value)
//        self.resultDelegate?.importResponse(result: 3)
    }
    
    @IBAction func onClickCreate(_ sender: Any) {
        print("onClickCreate")
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: 1)
        popupViewController?.close()
//        let value:[String: String] = ["import": "create"]
//        NotificationCenter.default.post(name: Notification.Name("startImport"), object: nil, userInfo: value)
        
//        self.onStartCreate()
//        self.resultDelegate?.importResponse(result: 1)
    }
    
    weak var popupViewController: SBCardPopupViewController?
    
    let allowsTapToDismissPopupCard = true
    let allowsSwipeToDismissPopupCard = false
}


