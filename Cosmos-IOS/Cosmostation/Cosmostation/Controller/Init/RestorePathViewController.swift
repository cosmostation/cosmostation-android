//
//  RestorePathViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 28/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class RestorePathViewController: BaseViewController {
    
    var userInputWords: [String]?

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = "SELECT WALLET TO RESTORE";
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        
        print("RestorePathViewController userInputWords ", userInputWords)
    }

}
