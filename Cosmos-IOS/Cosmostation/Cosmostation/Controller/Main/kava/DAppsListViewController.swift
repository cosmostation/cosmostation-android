//
//  DAppsViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class DAppsListViewController: BaseViewController {
    
    @IBOutlet weak var dAppsSegment: UISegmentedControl!
    @IBOutlet weak var cdpView: UIView!
    @IBOutlet weak var havestView: UIView!
    @IBOutlet weak var auctionView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cdpView.alpha = 1
        havestView.alpha = 0
        auctionView.alpha = 0
    
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        if #available(iOS 13.0, *) {
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            dAppsSegment.selectedSegmentTintColor = TRANS_BG_COLOR_KAVA2
            
        } else {
            dAppsSegment.tintColor = COLOR_KAVA
        }
    }
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            cdpView.alpha = 1
            havestView.alpha = 0
            auctionView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            cdpView.alpha = 0
            havestView.alpha = 1
            auctionView.alpha = 0
        } else if sender.selectedSegmentIndex == 2 {
            cdpView.alpha = 0
            havestView.alpha = 0
            auctionView.alpha = 1
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_dapp_market", comment: "");
        self.navigationItem.title = NSLocalizedString("title_dapp_market", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

}
