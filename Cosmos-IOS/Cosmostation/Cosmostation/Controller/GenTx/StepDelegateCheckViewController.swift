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
    
    var pageHolderVC: StepDelegateViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
//        self.view.isUserInteractionEnabled = false
    }

    @IBAction func onClickConfirm(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
    }
    
    
    @IBAction func onClickBack(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
}
