//
//  VaidatorDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class VaidatorDetailViewController: BaseViewController{
    
    @IBOutlet weak var validatorDetailTableView: UITableView!
    
    
    var mValidator:Validator?

    override func viewDidLoad() {
        super.viewDidLoad()
        print("mValidator ", mValidator?.description.moniker)
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
//        if(myValidator != nil) {
//            self.navigationController?.navigationBar.topItem?.title = "My Validator";
//        } else {
            self.navigationController?.navigationBar.topItem?.title = "Validator";
//        }
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}
