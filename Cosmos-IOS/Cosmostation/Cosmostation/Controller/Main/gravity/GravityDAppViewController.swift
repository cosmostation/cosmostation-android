//
//  GravityDAppViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/31.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class GravityDAppViewController: BaseViewController {
    
    @IBOutlet weak var dAppsSegment: UISegmentedControl!
    @IBOutlet weak var gravitySwapView: UIView!
    @IBOutlet weak var gravityPoolView: UIView!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        gravitySwapView.alpha = 1
        gravityPoolView.alpha = 0
        
        if #available(iOS 13.0, *) {
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            dAppsSegment.selectedSegmentTintColor = TRANS_BG_COLOR_COSMOS2
        } else {
            dAppsSegment.tintColor = COLOR_ATOM
        }
    }
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            gravitySwapView.alpha = 1
            gravityPoolView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            gravitySwapView.alpha = 0
            gravityPoolView.alpha = 1
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_dapp_gravity", comment: "");
        self.navigationItem.title = NSLocalizedString("title_dapp_gravity", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

}
