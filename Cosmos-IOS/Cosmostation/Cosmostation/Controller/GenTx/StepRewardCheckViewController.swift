//
//  StepRewardCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepRewardCheckViewController: BaseViewController {
    
    @IBOutlet weak var rewardAmoutLaebl: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var fromValidatorLabel: UILabel!
    @IBOutlet weak var recipientLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    
    @IBAction func onClickConfirm(_ sender: Any) {
//        let transition:CATransition = CATransition()
//        transition.duration = 0.3
//        transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
//        transition.type = CATransitionType.moveIn
//        transition.subtype = CATransitionSubtype.fromTop
//
//        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
//        self.navigationItem.title = ""
//        self.navigationController!.view.layer.add(transition, forKey: kCATransition)
//        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
//        passwordVC.resultDelegate = self
//        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    
    @IBAction func onClickBack(_ sender: Any) {
//        self.beforeBtn.isUserInteractionEnabled = false
//        self.confirmBtn.isUserInteractionEnabled = false
//        pageHolderVC.onBeforePage()
    }
    
    override func enableUserInteraction() {
//        self.onUpdateView()
//        self.beforeBtn.isUserInteractionEnabled = true
//        self.confirmBtn.isUserInteractionEnabled = true
    }

}
