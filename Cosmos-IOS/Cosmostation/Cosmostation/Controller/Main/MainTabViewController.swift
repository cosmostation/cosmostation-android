//
//  MainTabViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MainTabViewController: UITabBarController {
    
    var mAccount:Account!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        if(mAccount == nil) {
            print("NO ACCOUNT ERROR!!!!")
        }
        
        
    }
}
