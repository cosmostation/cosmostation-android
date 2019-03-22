//
//  MyValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MyValidatorViewController: UIViewController {
    func rewardViewUpdate() {
        print("MyValidatorViewController rewardViewUpdate")
    }
    

    @IBOutlet weak var myValidatorTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        

        // Do any additional setup after loading the view.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        let rewardTabVC = self.parent as! MainTabRewardViewController
        
        print("MyValidatorViewController ", rewardTabVC.mAllValidators.count)
        
    }
}
