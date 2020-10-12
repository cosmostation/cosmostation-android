//
//  StarNameListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/10.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StarNameListViewController: BaseViewController {
    
    @IBOutlet weak var myStarNameSegment: UISegmentedControl!
    @IBOutlet weak var myDomainView: UIView!
    @IBOutlet weak var myAccountView: UIView!
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            myDomainView.alpha = 1
            myAccountView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            myDomainView.alpha = 0
            myAccountView.alpha = 1
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        myDomainView.alpha = 1
        myAccountView.alpha = 0
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        if #available(iOS 13.0, *) {
            myStarNameSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            myStarNameSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            myStarNameSegment.selectedSegmentTintColor = TRANS_BG_COLOR_IOV2
            
        } else {
            myStarNameSegment.tintColor = COLOR_IOV
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_starname_list", comment: "");
        self.navigationItem.title = NSLocalizedString("title_starname_list", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}
