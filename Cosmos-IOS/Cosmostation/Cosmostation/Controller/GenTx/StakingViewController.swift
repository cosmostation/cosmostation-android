//
//  StakingViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StakingViewController: UIViewController {

    @IBOutlet weak var stepImg: UIImageView!
    @IBOutlet weak var stepDescription: UILabel!
    @IBOutlet weak var loadingImg: UIImageView!
    
    var mTargetValidator: Validator?
    
    override func viewDidLoad() {
        super.viewDidLoad()
//         print("StakingViewController")
        
        
        stepImg.image = UIImage.init(named: "4StepImg1")
        stepDescription.text = NSLocalizedString("delegate_step_1", comment: "")
//        loadingImg.image = UIImage.gifImageWithName("cosmostation_loading_wh")
//        loadingImg.image = UIImage.init(named: "freeIc")
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.navigationBar.topItem?.title = "Delegate";
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.stepChanged(_:)),
                                               name: Notification.Name("stepChanged"),
                                               object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if(self.isMovingFromParent) {
            print("StakingViewController BACK")
        }
        NotificationCenter.default.removeObserver(self, name: Notification.Name("pageChanged"), object: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if(segue.identifier == "containerTxPage") {
            let StepVc = segue.destination as! StepDelegateViewController
            StepVc.topVC = self
            StepVc.mTargetValidator = self.mTargetValidator
        }
    }
    
    
    @objc func stepChanged(_ notification: NSNotification) {
        if let step = notification.userInfo?["step"] as? Int {
            print("step ", step)
            if (step == 0) {
                stepImg.image = UIImage.init(named: "4StepImg1")
                stepDescription.text = NSLocalizedString("delegate_step_1", comment: "")
                
            } else if (step == 1) {
                stepImg.image = UIImage.init(named: "4StepImg2")
                stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                
            } else if (step == 2) {
                stepImg.image = UIImage.init(named: "4StepImg3")
                stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                
            } else if (step == 3) {
                stepImg.image = UIImage.init(named: "4StepImg4")
                stepDescription.text = NSLocalizedString("delegate_step_4", comment: "")
                
            }
        }
    }
    
//    func exitDelegate() {
//        self.navigationController?.popViewController(animated: true)
//    }

}
