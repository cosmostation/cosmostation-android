//
//  StakingViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StakingViewController: UIViewController {

    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var stepView: UIView!
    @IBOutlet weak var stepImg: UIImageView!
    @IBOutlet weak var stepDescription: UILabel!
    
    var mAccount: Account?
    var userChain: ChainType?
    var mTargetValidator: Validator?
    var mType: String?
    var mRewardTargetValidators = Array<Validator>()
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
//         print("StakingViewController")
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        userChain = WUtils.getChainType(mAccount!.account_base_chain)
        if (userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            chainBg.image = UIImage(named: "bg_cosmos")
        } else if (userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            chainBg.image = UIImage(named: "bg_iris")
        }
        
        if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
            stepDescription.text = NSLocalizedString("delegate_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_delegate", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
            stepDescription.text = NSLocalizedString("undelegate_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_undelegate", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
            stepDescription.text = NSLocalizedString("redelegate_step_1", comment: "")
            stepImg.image = UIImage.init(named: "step1Img")
            self.titleLabel.text =  NSLocalizedString("title_redelegate", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
            stepDescription.text = NSLocalizedString("send_step_1", comment: "")
            stepImg.image = UIImage.init(named: "step1Img")
            self.titleLabel.text =  NSLocalizedString("title_send", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
            stepDescription.text = NSLocalizedString("withdraw_single_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reward", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
            stepDescription.text = NSLocalizedString("reward_address_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reword_address_change", comment: "")
            
        } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
            stepDescription.text = NSLocalizedString("reinvest_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reinvest", comment: "")
        }
        
        
        self.titleLabel.adjustsFontSizeToFitWidth = true
        self.titleView.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard (_:))))
        self.stepView.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard (_:))))
    }
    
    @objc func dismissKeyboard (_ sender: UITapGestureRecognizer) {
        self.view.endEditing(true)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
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
            let StepVc = segue.destination as! StepGenTxViewController
            StepVc.topVC = self
            StepVc.mType = self.mType
            StepVc.mTargetValidator = self.mTargetValidator
            StepVc.mRewardTargetValidators = self.mRewardTargetValidators
            StepVc.mProvision = self.mProvision
            StepVc.mStakingPool = self.mStakingPool
        }
    }
    
    
    @objc func stepChanged(_ notification: NSNotification) {
        if let step = notification.userInfo?["step"] as? Int {
            if (step == 0) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("delegate_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("undelegate_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                    stepImg.image = UIImage.init(named: "step1Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step1Img")
                    stepDescription.text = NSLocalizedString("send_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("withdraw_single_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("reward_address_step_1", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("reinvest_step_1", comment: "")
                }
                
                
            } else if (step == 1) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                    stepImg.image = UIImage.init(named: "step2Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step2Img")
                    stepDescription.text = NSLocalizedString("send_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                }
                
                
            } else if (step == 2) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                    stepImg.image = UIImage.init(named: "step3Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step3Img")
                    stepDescription.text = NSLocalizedString("send_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                }
                
                
            } else if (step == 3) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("delegate_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("undelegate_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                    stepImg.image = UIImage.init(named: "step4Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step4Img")
                    stepDescription.text = NSLocalizedString("send_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("withdraw_single_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("reward_address_step_4", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("reinvest_step_4", comment: "")
                    
                }
                
            } else if (step == 4) {
                if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step5Img")
                    stepDescription.text = NSLocalizedString("send_step_5", comment: "")
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                    stepImg.image = UIImage.init(named: "step5Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_5", comment: "")
                }
                
            }
        }
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
}
