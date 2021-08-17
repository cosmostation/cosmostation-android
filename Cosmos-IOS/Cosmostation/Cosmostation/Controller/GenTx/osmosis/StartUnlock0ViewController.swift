//
//  StartUnlock0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class StartUnlock0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var toUnlockIdsLabel: UILabel!
    @IBOutlet weak var toUnlockAmountLabel: UILabel!
    @IBOutlet weak var toUnlockDenomLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        let toUnlockDenom = String(pageHolderVC.mLockups![0].coins[0].denom)
        var ids = ""
        var toUnlockAmount = NSDecimalNumber.zero
        for lockUp in pageHolderVC.mLockups! {
            ids = ids + "# " + String(lockUp.id) + "  "
            toUnlockAmount = toUnlockAmount.adding(NSDecimalNumber.init(string: lockUp.coins[0].amount))
        }
        toUnlockIdsLabel.text = ids
        WUtils.showCoinDp(toUnlockDenom, toUnlockAmount.stringValue, toUnlockDenomLabel, toUnlockAmountLabel, chainType!)
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
