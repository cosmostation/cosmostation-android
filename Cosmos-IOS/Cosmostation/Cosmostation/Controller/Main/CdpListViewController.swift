//
//  CdpListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpListViewController: BaseViewController {

    @IBOutlet weak var cdpSegmentControl: UISegmentedControl!
    @IBOutlet weak var myCdpUIView: UIView!
    @IBOutlet weak var allCdpUIView: UIView!
    @IBOutlet weak var incentiveCdpUIView: UIView!
    
    var mainTabVC: MainTabViewController!
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            myCdpUIView.alpha = 1
            allCdpUIView.alpha = 0
            incentiveCdpUIView.alpha = 0
            
        } else if sender.selectedSegmentIndex == 1 {
            myCdpUIView.alpha = 0
            allCdpUIView.alpha = 1
            incentiveCdpUIView.alpha = 0
            
        } else {
            myCdpUIView.alpha = 0
            allCdpUIView.alpha = 0
            incentiveCdpUIView.alpha = 1
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        myCdpUIView.alpha = 1
        allCdpUIView.alpha = 0
        incentiveCdpUIView.alpha = 0
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        
        if #available(iOS 13.0, *) {
            cdpSegmentControl.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            cdpSegmentControl.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            cdpSegmentControl.selectedSegmentTintColor = TRANS_BG_COLOR_KAVA2
            
        } else {
            cdpSegmentControl.tintColor = COLOR_KAVA
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_cdp_list", comment: "");
        self.navigationItem.title = NSLocalizedString("title_cdp_list", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}
