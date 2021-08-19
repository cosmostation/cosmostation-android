//
//  StartUnbonding0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class StartUnbonding0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var toUnbondingIdsLabel: UILabel!
    @IBOutlet weak var toUnbondingAmountLabel: UILabel!
    @IBOutlet weak var toUnbondingDenomLabel: UILabel!
    @IBOutlet weak var toUnbondingDurationLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        let toUnbondingDuration  = pageHolderVC.mLockups![0].duration.seconds
        let toUnbondingDenom = String(pageHolderVC.mLockups![0].coins[0].denom)
        var ids = ""
        var toUnbondingAmount = NSDecimalNumber.zero
        for lockUp in pageHolderVC.mLockups! {
            ids = ids + "# " + String(lockUp.id) + "  "
            toUnbondingAmount = toUnbondingAmount.adding(NSDecimalNumber.init(string: lockUp.coins[0].amount))
        }
        
        toUnbondingIdsLabel.text = ids
        if (toUnbondingDuration == 86400) {
            toUnbondingDurationLabel.text = "1 Day"
        } else if (toUnbondingDuration == 604800) {
            toUnbondingDurationLabel.text = "7 Days"
        } else if (toUnbondingDuration == 1209600) {
            toUnbondingDurationLabel.text = "14 Days"
        }
        WUtils.showCoinDp(toUnbondingDenom, toUnbondingAmount.stringValue, toUnbondingDenomLabel, toUnbondingAmountLabel, chainType!)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }

}
