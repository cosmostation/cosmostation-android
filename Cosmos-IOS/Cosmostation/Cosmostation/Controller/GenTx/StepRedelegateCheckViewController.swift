//
//  StepRedelegateCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepRedelegateCheckViewController: BaseViewController {

    @IBOutlet weak var redelegateAmountLabel: UILabel!
    @IBOutlet weak var redelegateFeeLabel: UILabel!
    @IBOutlet weak var redelegateFromValLabel: UILabel!
    @IBOutlet weak var redelegateToValLabel: UILabel!
    @IBOutlet weak var redelegateMemoLabel: UILabel!
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    
    var pageHolderVC: StepGenTxViewController!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    func onUpdateView() {
        let toRedelegateAmount = WUtils.stringToDecimal(pageHolderVC.mToReDelegateAmount!.amount)
        let feeAmout = WUtils.stringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        
        redelegateAmountLabel.attributedText = WUtils.displayAmout(toRedelegateAmount.stringValue, redelegateAmountLabel.font, 6)
        redelegateFeeLabel.attributedText = WUtils.displayAmout(feeAmout.stringValue, redelegateFeeLabel.font, 6)
        redelegateFromValLabel.text = pageHolderVC.mTargetValidator?.description.moniker
        redelegateToValLabel.text = pageHolderVC.mToReDelegateValidator?.description.moniker
        redelegateMemoLabel.text = pageHolderVC.mMemo
        
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBefore.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBefore(_ sender: UIButton) {
        self.btnBefore.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        
    }
    

}
