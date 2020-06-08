//
//  MainTabRewardViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class ValidatorListViewController: BaseViewController {
    
    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var validatorSegment: UISegmentedControl!
    @IBOutlet weak var myValidatorView: UIView!
    @IBOutlet weak var allValidatorView: UIView!
    @IBOutlet weak var otherValidatorView: UIView!
    
    var mainTabVC: MainTabViewController!
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            myValidatorView.alpha = 1
            allValidatorView.alpha = 0
            otherValidatorView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            myValidatorView.alpha = 0
            allValidatorView.alpha = 1
            otherValidatorView.alpha = 0
        } else {
            myValidatorView.alpha = 0
            allValidatorView.alpha = 0
            otherValidatorView.alpha = 1
        }
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        myValidatorView.alpha = 1
        allValidatorView.alpha = 0
        otherValidatorView.alpha = 0
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        
        if #available(iOS 13.0, *) {
            validatorSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            validatorSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                validatorSegment.selectedSegmentTintColor = TRANS_BG_COLOR_COSMOS2
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                validatorSegment.selectedSegmentTintColor = TRANS_BG_COLOR_IRIS2
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                validatorSegment.selectedSegmentTintColor = TRANS_BG_COLOR_KAVA2
            } else if (chainType == ChainType.SUPPORT_CHAIN_BAND_MAIN) {
                validatorSegment.selectedSegmentTintColor = TRANS_BG_COLOR_BAND2
            }
            
        } else {
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                validatorSegment.tintColor = COLOR_ATOM
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                validatorSegment.tintColor = COLOR_IRIS
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                validatorSegment.tintColor = COLOR_KAVA
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                validatorSegment.tintColor = COLOR_BAND
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
