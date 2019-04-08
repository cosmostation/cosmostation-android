//
//  StepDelegateAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepDelegateAmountViewController: BaseViewController, UITextFieldDelegate{

    @IBOutlet weak var toDelegateAmountInput: AmountInputTextField!
    @IBOutlet weak var availableAmountLabel: UILabel!
    
    var pageHolderVC: StepDelegateViewController!
    var userBalance = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
        for balance in pageHolderVC.mBalances {
            if(balance.balance_denom == "uatom") {
                userBalance = userBalance.adding(WUtils.stringToDecimal(balance.balance_amount))
            }
        }
        availableAmountLabel.attributedText = WUtils.displayAmout(userBalance.stringValue, availableAmountLabel.font, 6)
        toDelegateAmountInput.delegate = self
    }
    
    

    

    @IBAction func onClickCancel(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
}
