//
//  StepDelegateCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepDelegateCheckViewController: BaseViewController {

    @IBOutlet weak var toDelegateAmoutLaebl: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var targetValidatorLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepDelegateViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
    }

    @IBAction func onClickConfirm(_ sender: Any) {
    }
    
    
    @IBAction func onClickBack(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.beforeBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        toDelegateAmoutLaebl.attributedText = WUtils.displayAmout((pageHolderVC.mToDelegateAmount?.amount)!, toDelegateAmoutLaebl.font, 6)
        feeAmountLabel.attributedText = WUtils.displayAmout((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6)
        targetValidatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
        memoLabel.text = pageHolderVC.mMemo
    }
}
