//
//  StakingViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StakingViewController: UIViewController {

    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleLabel: UILabel!
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
        
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard (_:)))
        self.view.addGestureRecognizer(tapGesture)
        
//        self.navigationItem.hidesBackButton = true
//        let newBackButton = UIBarButtonItem(image: UIImage.init(named: "backBtn"), style: UIBarButtonItem.Style.plain, target: self, action: #selector(StakingViewController.back(sender:)))
//        self.navigationItem.leftBarButtonItem = newBackButton
//        self.titleLabel.text = "Delegate"
    }
    
//    @objc func back(sender: UIBarButtonItem) {
//        print("StakingViewController BACK")
////        // Perform your custom actions
////        // ...
////        // Go back to the previous ViewController
//        _ = navigationController?.popViewController(animated: true)
//    }
    
    @objc func dismissKeyboard (_ sender: UITapGestureRecognizer) {
        self.view.endEditing(true)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.titleLabel.text = "Delegate to " + String((mTargetValidator?.description.moniker)!)
        
//        self.navigationController?.navigationBar.layoutMargins.left = 100
//        self.navigationController?.navigationBar.topItem?.title = "Delegate";
//        self.navigationItem.title = "Delegate";
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.stepChanged(_:)),
                                               name: Notification.Name("stepChanged"),
                                               object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("stepChanged"), object: nil)
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
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
}
