//
//  OkValidatorListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class OkValidatorListViewController: BaseViewController {
    
    @IBOutlet weak var validatorSegment: UISegmentedControl!
    @IBOutlet weak var okMyValidatorView: UIView!
    @IBOutlet weak var okTopValidatorView: UIView!
    @IBOutlet weak var okOtherValidatorView: UIView!
    
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            okMyValidatorView.alpha = 1
            okTopValidatorView.alpha = 0
            okOtherValidatorView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            okMyValidatorView.alpha = 0
            okTopValidatorView.alpha = 1
            okOtherValidatorView.alpha = 0
        } else {
            okMyValidatorView.alpha = 0
            okTopValidatorView.alpha = 0
            okOtherValidatorView.alpha = 1
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        okMyValidatorView.alpha = 1
        okTopValidatorView.alpha = 0
        okOtherValidatorView.alpha = 0
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        if #available(iOS 13.0, *) {
            validatorSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            validatorSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
                validatorSegment.selectedSegmentTintColor = TRANS_BG_COLOR_OK2
            }
            
        } else {
            if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
                validatorSegment.tintColor = COLOR_OK
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_validator_list", comment: "");
        self.navigationItem.title = NSLocalizedString("title_validator_list", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}
