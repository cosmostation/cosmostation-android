//
//  KavaIncentiveClaim0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/29.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class KavaIncentiveClaim0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var kavaIncentiveAmountLabel: UILabel!
    @IBOutlet weak var hardIncentiveAmountLabel: UILabel!
    @IBOutlet weak var swpIncentiveAmountLabel: UILabel!
    @IBOutlet weak var usdxIncentiveAmountLabel: UILabel!
    @IBOutlet weak var lockupLabel: UILabel!
    @IBOutlet weak var option1Btn: UIButton!
    @IBOutlet weak var option2Btn: UIButton!
    @IBOutlet weak var option3Btn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    
    
    
    @IBAction func onClickOption1(_ sender: UIButton) {
    }
    
    @IBAction func onClickOption2(_ sender: UIButton) {
    }
    
    @IBAction func onClickOption3(_ sender: UIButton) {
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
    }

}
