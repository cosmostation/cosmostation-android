//
//  StepDelegateFeeViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepDelegateFeeViewController: BaseViewController {

    @IBOutlet weak var feeTypeCardView: CardView!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeSlider: UISlider!
    
    var pageHolderVC: StepDelegateViewController!
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
//        self.view.isUserInteractionEnabled = false
    }

    @IBAction func onSlideChanged(_ sender: UISlider) {
        
    }
    
    @IBAction func onClickBefore(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
}
