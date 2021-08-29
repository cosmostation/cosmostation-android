//
//  TransactionViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class TransactionViewController: UIViewController {

    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var stepView: UIView!
    @IBOutlet weak var stepImg: UIImageView!
    @IBOutlet weak var stepDescription: UILabel!
    
    var mAccount: Account?
    var mUserChain: ChainType?
    var mTargetValidator: Validator?
    var mTargetValidator_gRPC: Cosmos_Staking_V1beta1_Validator?
    var mType: String?
    var mRewardTargetValidators = Array<Validator>()
    var mRewardTargetValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    
    var mProposeId: String?
    var mProposalTitle: String?
    var mProposer: String?
    
    var mCollateralParamType: String?
    var mCDenom: String?
    var mMarketID: String?
    var mHardPoolDenom: String?
    var mKavaPool: SwapPool?
    var mKavaDeposit: SwapDeposit?
    
    var mHtlcDenom: String = BNB_MAIN_DENOM     //now only support bnb bep3
    var mHtlcRefundSwapId: String?
    
    var mStarnameDomain: String?
    var mStarnameAccount: String?
    var mStarnameTime: Int64?
    var mStarnameDomainType: String?
    var mStarnameResources_gRPC: Array<Starnamed_X_Starname_V1beta1_Resource> = Array<Starnamed_X_Starname_V1beta1_Resource>()
    
    var mToSendDenom: String?
    
    var mPoolId: String?
    var mSwapInDenom: String?
    var mSwapOutDenom: String?
    var mPool: Osmosis_Gamm_V1beta1_Pool?
    var mLockupDuration: Int64?
    var mLockups: Array<Osmosis_Lockup_PeriodLock>?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mUserChain = WUtils.getChainType(mAccount!.account_base_chain)
        
        if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
            stepDescription.text = NSLocalizedString("delegate_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_delegate", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2 || mType == IRIS_MSG_TYPE_UNDELEGATE) {
            stepDescription.text = NSLocalizedString("undelegate_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_undelegate", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
            stepDescription.text = NSLocalizedString("redelegate_step_1", comment: "")
            stepImg.image = UIImage.init(named: "step1Img")
            self.titleLabel.text =  NSLocalizedString("title_redelegate", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
            stepDescription.text = NSLocalizedString("send_step_1", comment: "")
            stepImg.image = UIImage.init(named: "step1Img")
            self.titleLabel.text =  NSLocalizedString("title_send", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL || mType == IRIS_MSG_TYPE_WITHDRAW || mType == IRIS_MSG_TYPE_WITHDRAW_ALL) {
            stepDescription.text = NSLocalizedString("withdraw_single_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reward", comment: "")
            
        } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
            stepDescription.text = NSLocalizedString("reward_address_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reword_address_change", comment: "")
            
        } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
            stepDescription.text = NSLocalizedString("reinvest_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reinvest", comment: "")
            
        } else if (mType == IRIS_MSG_TYPE_VOTE) {
            stepDescription.text = NSLocalizedString("vote_step_1", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_vote", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_CREATE_CDP) {
            stepDescription.text = NSLocalizedString("creat_cdp_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_create_cdp", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_DEPOSIT_CDP) {
            stepDescription.text = NSLocalizedString("deposit_cdp_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_deposit_cdp", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_WITHDRAW_CDP) {
            stepDescription.text = NSLocalizedString("withdraw_cdp_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_withdraw_cdp", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
            stepDescription.text = NSLocalizedString("drawdebt_cdp_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_drawdebt_cdp", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
            stepDescription.text = NSLocalizedString("repay_cdp_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_repay_cdp", comment: "")
            
        } else if (mType == TASK_TYPE_HTLC_SWAP) {
            stepDescription.text = NSLocalizedString("htlc_swap_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_interchain_swap", comment: "")
            
        } else if (mType == TASK_TYPE_HTLC_REFUND) {
            stepDescription.text = NSLocalizedString("htlc_refund_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_interchain_refund", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
            stepDescription.text = NSLocalizedString("incentive_participate_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_participate_incentive", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HAVEST) {
            stepDescription.text = NSLocalizedString("deposit_harvest_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_deposit_harvest", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HAVEST) {
            stepDescription.text = NSLocalizedString("withdraw_harvest_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_withdraw_harvest", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
            stepDescription.text = NSLocalizedString("reward_harvest_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_reward_harvest", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
            stepDescription.text = NSLocalizedString("reward_harvest_vv_step_0", comment: "")
            stepImg.image = UIImage.init(named: "step1Img")
            self.titleLabel.text =  NSLocalizedString("title_reward_harvest", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HARD) {
            stepDescription.text = NSLocalizedString("deposit_hardpool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_deposit_hardpool", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HARD) {
            stepDescription.text = NSLocalizedString("withdraw_hardpool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_withdraw_hardpool", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_BORROW_HARD) {
            stepDescription.text = NSLocalizedString("borrow_hardpool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_borrow_hardpool", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_REPAY_HARD) {
            stepDescription.text = NSLocalizedString("repay_hardpool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_repay_hardpool", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_SWAP_TOKEN) {
            stepDescription.text = NSLocalizedString("str_swap_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_swap_token", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_SWAP_DEPOSIT) {
            stepDescription.text = NSLocalizedString("str_join_pool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_pool_join", comment: "")
            
        } else if (mType == KAVA_MSG_TYPE_SWAP_WITHDRAW) {
            stepDescription.text = NSLocalizedString("str_exit_pool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_pool_exit", comment: "")
            
        }  else if (mType == OK_MSG_TYPE_DEPOSIT) {
            stepDescription.text = NSLocalizedString("str_ok_stake_deposit_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_ok_deposit", comment: "")
            
        } else if (mType == OK_MSG_TYPE_WITHDRAW) {
            stepDescription.text = NSLocalizedString("str_ok_stake_withdraw_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_ok_withdraw", comment: "")
            
        } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
            stepDescription.text = NSLocalizedString("str_ok_direct_vote_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_ok_vote", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
            stepDescription.text = NSLocalizedString("str_starname_register_domain_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_starname_registe_domain", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
            stepDescription.text = NSLocalizedString("str_starname_register_account_step_0", comment: "")
            stepImg.image = UIImage.init(named: "step1Img")
            self.titleLabel.text =  NSLocalizedString("title_starname_registe_account", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_DELETE_DOMAIN) {
            stepDescription.text = NSLocalizedString("str_starname_delete_starname_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_starname_delete_domain", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
            stepDescription.text = NSLocalizedString("str_starname_delete_starname_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_starname_delete_account", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_RENEW_DOMAIN) {
            stepDescription.text = NSLocalizedString("str_starname_renew_starname_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_starname_renew_domain", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            stepDescription.text = NSLocalizedString("str_starname_renew_starname_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_starname_renew_account", comment: "")
            
        } else if (mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
            stepDescription.text = NSLocalizedString("str_starname_replace_starname_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_starname_update_resource", comment: "")
            
        } else if (mType == OSMOSIS_MSG_TYPE_SWAP) {
            stepDescription.text = NSLocalizedString("str_swap_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_swap_token", comment: "")
            
        } else if (mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
            stepDescription.text = NSLocalizedString("str_join_pool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_pool_join", comment: "")
            
        } else if (mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
            stepDescription.text = NSLocalizedString("str_exit_pool_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_pool_exit", comment: "")
            
        } else if (mType == OSMOSIS_MSG_TYPE_LOCK) {
            stepDescription.text = NSLocalizedString("str_osmosis_lock_token_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_lock_token_osmosis", comment: "")
            
        } else if (mType == OSMOSIS_MSG_TYPE_BEGIN_UNLCOK) {
            stepDescription.text = NSLocalizedString("str_osmosis_begin_unbonding_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_begin_unbonding_osmosis", comment: "")
            
        } else if (mType == OSMOSIS_MSG_TYPE_PERIOD_UNLOCK) {
            stepDescription.text = NSLocalizedString("str_osmosis_unlock_token_step_0", comment: "")
            stepImg.image = UIImage.init(named: "4StepImg1")
            self.titleLabel.text =  NSLocalizedString("title_unlock_osmosis", comment: "")
            
        }
        
        else if (mType == TASK_RIZON_EVENT_HORIZON) {
            stepDescription.text = NSLocalizedString("str_rizon_swap_step_0", comment: "")
            stepImg.image = UIImage.init(named: "2step1")
            self.titleLabel.text =  NSLocalizedString("title_rizon_swap", comment: "")
            
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
            StepVc.mTargetValidator_gRPC = self.mTargetValidator_gRPC
            StepVc.mRewardTargetValidators = self.mRewardTargetValidators
            StepVc.mRewardTargetValidators_gRPC = self.mRewardTargetValidators_gRPC
            StepVc.mProposeId = self.mProposeId
            StepVc.mProposalTitle = self.mProposalTitle
            StepVc.mProposer = self.mProposer
            StepVc.mToSendDenom = self.mToSendDenom
            
            StepVc.mCDenom = self.mCDenom
            StepVc.mMarketID = self.mMarketID
            StepVc.mHtlcDenom = self.mHtlcDenom
            StepVc.mHtlcRefundSwapId = self.mHtlcRefundSwapId
            StepVc.mHardPoolDenom = self.mHardPoolDenom
            StepVc.mCollateralParamType = self.mCollateralParamType
            StepVc.mKavaPool = self.mKavaPool
            StepVc.mKavaDeposit = self.mKavaDeposit
            
            StepVc.mStarnameDomain = self.mStarnameDomain
            StepVc.mStarnameAccount = self.mStarnameAccount
            StepVc.mStarnameTime = self.mStarnameTime
            StepVc.mStarnameDomainType = self.mStarnameDomainType
            StepVc.mStarnameResources_gRPC = self.mStarnameResources_gRPC
            
            StepVc.mPoolId = self.mPoolId
            StepVc.mSwapInDenom = self.mSwapInDenom
            StepVc.mSwapOutDenom = self.mSwapOutDenom
            StepVc.mPool = self.mPool
            StepVc.mLockupDuration = self.mLockupDuration
            StepVc.mLockups = self.mLockups
        }
    }
    
    
    @objc func stepChanged(_ notification: NSNotification) {
        if let step = notification.userInfo?["step"] as? Int {
            if (step == 0) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("delegate_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2 || mType == IRIS_MSG_TYPE_UNDELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("undelegate_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
                    stepImg.image = UIImage.init(named: "step1Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step1Img")
                    stepDescription.text = NSLocalizedString("send_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL || mType == IRIS_MSG_TYPE_WITHDRAW || mType == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("withdraw_single_step_1", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("reward_address_step_1", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("reinvest_step_1", comment: "")
                    
                } else if (mType == TASK_TYPE_VOTE) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("vote_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_CREATE_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("creat_cdp_step_0", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("deposit_cdp_step_0", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("withdraw_cdp_step_0", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("drawdebt_cdp_step_0", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("repay_cdp_step_0", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HAVEST) {
                    stepDescription.text = NSLocalizedString("deposit_harvest_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HAVEST) {
                    stepDescription.text = NSLocalizedString("withdraw_harvest_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
                    stepDescription.text = NSLocalizedString("reward_harvest_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
                    stepDescription.text = NSLocalizedString("reward_harvest_vv_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "step1Img")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HARD) {
                    stepDescription.text = NSLocalizedString("deposit_hardpool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HARD) {
                    stepDescription.text = NSLocalizedString("withdraw_hardpool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_BORROW_HARD) {
                    stepDescription.text = NSLocalizedString("borrow_hardpool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAY_HARD) {
                    stepDescription.text = NSLocalizedString("repay_hardpool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_TOKEN) {
                    stepDescription.text = NSLocalizedString("str_swap_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == TASK_TYPE_HTLC_SWAP) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("htlc_swap_step_0", comment: "")
                    
                } else if (mType == TASK_TYPE_HTLC_REFUND) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("htlc_refund_step_0", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    stepDescription.text = NSLocalizedString("incentive_participate_step_0", comment: "")
                    
                } else if (mType == OK_MSG_TYPE_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_deposit_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OK_MSG_TYPE_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_withdraw_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
                    stepDescription.text = NSLocalizedString("str_ok_direct_vote_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
                    stepDescription.text = NSLocalizedString("str_ok_direct_vote_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                    stepImg.image = UIImage.init(named: "step1Img")
                    stepDescription.text = NSLocalizedString("str_starname_register_account_step_0", comment: "")
                    
                } else if (mType == IOV_MSG_TYPE_DELETE_DOMAIN || mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_delete_starname_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == IOV_MSG_TYPE_RENEW_DOMAIN || mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_renew_starname_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
                    stepDescription.text = NSLocalizedString("str_starname_replace_starname_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_SWAP) {
                    stepDescription.text = NSLocalizedString("str_swap_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_LOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_lock_token_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_BEGIN_UNLCOK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_begin_unbonding_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_PERIOD_UNLOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_unlock_token_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg1")
                    
                }
                
                else if (mType == TASK_RIZON_EVENT_HORIZON) {
                    stepDescription.text = NSLocalizedString("str_rizon_swap_step_0", comment: "")
                    stepImg.image = UIImage.init(named: "2step1")
                    
                }
                
                
            } else if (step == 1) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2 || mType == IRIS_MSG_TYPE_UNDELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
                    stepImg.image = UIImage.init(named: "step2Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step2Img")
                    stepDescription.text = NSLocalizedString("send_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL || mType == IRIS_MSG_TYPE_WITHDRAW || mType == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                    
                } else if (mType == TASK_TYPE_VOTE) {
                   stepImg.image = UIImage.init(named: "4StepImg2")
                   stepDescription.text = NSLocalizedString("delegate_step_2", comment: "")
                   
                } else if (mType == KAVA_MSG_TYPE_CREATE_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("creat_cdp_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("deposit_cdp_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("withdraw_cdp_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("drawdebt_cdp_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("repay_cdp_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HAVEST) {
                    stepDescription.text = NSLocalizedString("deposit_harvest_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HAVEST) {
                    stepDescription.text = NSLocalizedString("withdraw_harvest_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
                    stepDescription.text = NSLocalizedString("reward_harvest_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
                    stepDescription.text = NSLocalizedString("reward_harvest_vv_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "step2Img")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HARD) {
                    stepDescription.text = NSLocalizedString("deposit_hardpool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HARD) {
                    stepDescription.text = NSLocalizedString("withdraw_hardpool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_BORROW_HARD) {
                    stepDescription.text = NSLocalizedString("borrow_hardpool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAY_HARD) {
                    stepDescription.text = NSLocalizedString("repay_hardpool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_TOKEN) {
                    stepDescription.text = NSLocalizedString("str_swap_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == TASK_TYPE_HTLC_SWAP) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("htlc_swap_step_1", comment: "")
                    
                } else if (mType == TASK_TYPE_HTLC_REFUND) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("htlc_refund_step_1", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    stepDescription.text = NSLocalizedString("incentive_participate_step_1", comment: "")
                    
                } else if (mType == OK_MSG_TYPE_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_deposit_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OK_MSG_TYPE_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_withdraw_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
                    stepDescription.text = NSLocalizedString("str_ok_direct_vote_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
                    stepDescription.text = NSLocalizedString("str_starname_register_domain_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                    stepImg.image = UIImage.init(named: "step2Img")
                    stepDescription.text = NSLocalizedString("str_starname_register_account_step_1", comment: "")
                    
                } else if (mType == IOV_MSG_TYPE_DELETE_DOMAIN || mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_delete_starname_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == IOV_MSG_TYPE_RENEW_DOMAIN || mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_renew_starname_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
                    stepDescription.text = NSLocalizedString("str_starname_replace_starname_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_SWAP) {
                    stepDescription.text = NSLocalizedString("str_swap_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_LOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_lock_token_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_BEGIN_UNLCOK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_begin_unbonding_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_PERIOD_UNLOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_unlock_token_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg2")
                    
                }
                
                else if (mType == TASK_RIZON_EVENT_HORIZON) {
                    stepDescription.text = NSLocalizedString("str_rizon_swap_step_1", comment: "")
                    stepImg.image = UIImage.init(named: "2step2")
                    
                }
                
                
            } else if (step == 2) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2 || mType == IRIS_MSG_TYPE_UNDELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
                    stepImg.image = UIImage.init(named: "step3Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step3Img")
                    stepDescription.text = NSLocalizedString("send_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL || mType == IRIS_MSG_TYPE_WITHDRAW || mType == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                    
                } else if (mType == TASK_TYPE_VOTE) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("delegate_step_3", comment: "")
                  
                } else if (mType == KAVA_MSG_TYPE_CREATE_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("creat_cdp_step_2", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("deposit_cdp_step_2", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("withdraw_cdp_step_2", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("drawdebt_cdp_step_2", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("repay_cdp_step_2", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HAVEST) {
                    stepDescription.text = NSLocalizedString("deposit_harvest_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HAVEST) {
                    stepDescription.text = NSLocalizedString("withdraw_harvest_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
                    stepDescription.text = NSLocalizedString("reward_harvest_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
                    stepDescription.text = NSLocalizedString("reward_harvest_vv_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "step3Img")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HARD) {
                    stepDescription.text = NSLocalizedString("deposit_hardpool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HARD) {
                    stepDescription.text = NSLocalizedString("withdraw_hardpool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_BORROW_HARD) {
                    stepDescription.text = NSLocalizedString("borrow_hardpool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAY_HARD) {
                    stepDescription.text = NSLocalizedString("repay_hardpool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_TOKEN) {
                    stepDescription.text = NSLocalizedString("str_swap_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == TASK_TYPE_HTLC_SWAP) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("htlc_swap_step_2", comment: "")
                    
                } else if (mType == TASK_TYPE_HTLC_REFUND) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("htlc_refund_step_2", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    stepDescription.text = NSLocalizedString("incentive_participate_step_2", comment: "")
                    
                } else if (mType == OK_MSG_TYPE_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_deposit_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OK_MSG_TYPE_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_withdraw_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
                    stepDescription.text = NSLocalizedString("str_ok_direct_vote_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
                    stepDescription.text = NSLocalizedString("str_starname_register_domain_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                    stepImg.image = UIImage.init(named: "step3Img")
                    stepDescription.text = NSLocalizedString("str_starname_register_account_step_2", comment: "")
                    
                } else if (mType == IOV_MSG_TYPE_DELETE_DOMAIN || mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_delete_starname_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == IOV_MSG_TYPE_RENEW_DOMAIN || mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_renew_starname_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
                    stepDescription.text = NSLocalizedString("str_starname_replace_starname_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_SWAP) {
                    stepDescription.text = NSLocalizedString("str_swap_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_LOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_lock_token_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_BEGIN_UNLCOK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_begin_unbonding_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_PERIOD_UNLOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_unlock_token_step_2", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg3")
                    
                }
                
                
            } else if (step == 3) {
                if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("delegate_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2 || mType == IRIS_MSG_TYPE_UNDELEGATE) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("undelegate_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
                    stepImg.image = UIImage.init(named: "step4Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step4Img")
                    stepDescription.text = NSLocalizedString("send_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_DEL || mType == IRIS_MSG_TYPE_WITHDRAW || mType == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("withdraw_single_step_4", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("reward_address_step_4", comment: "")
                    
                } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("reinvest_step_4", comment: "")
                    
                } else if (mType == TASK_TYPE_VOTE) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("reinvest_step_4", comment: "")
                 
                } else if (mType == KAVA_MSG_TYPE_CREATE_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("creat_cdp_step_3", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("deposit_cdp_step_3", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("withdraw_cdp_step_3", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("drawdebt_cdp_step_3", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("repay_cdp_step_3", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HAVEST) {
                    stepDescription.text = NSLocalizedString("deposit_harvest_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HAVEST) {
                    stepDescription.text = NSLocalizedString("withdraw_harvest_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
                    stepDescription.text = NSLocalizedString("reward_harvest_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
                    stepDescription.text = NSLocalizedString("reward_harvest_vv_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "step4Img")
                    
                } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HARD) {
                    stepDescription.text = NSLocalizedString("deposit_hardpool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HARD) {
                    stepDescription.text = NSLocalizedString("withdraw_hardpool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_BORROW_HARD) {
                    stepDescription.text = NSLocalizedString("borrow_hardpool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_REPAY_HARD) {
                    stepDescription.text = NSLocalizedString("repay_hardpool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_TOKEN) {
                    stepDescription.text = NSLocalizedString("str_swap_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == KAVA_MSG_TYPE_SWAP_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == TASK_TYPE_HTLC_SWAP) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("htlc_swap_step_3", comment: "")
                    
                } else if (mType == TASK_TYPE_HTLC_REFUND) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("htlc_refund_step_3", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    stepDescription.text = NSLocalizedString("incentive_participate_step_3", comment: "")
                    
                } else if (mType == OK_MSG_TYPE_DEPOSIT) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_deposit_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OK_MSG_TYPE_WITHDRAW) {
                    stepDescription.text = NSLocalizedString("str_ok_stake_withdraw_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
                    stepDescription.text = NSLocalizedString("str_ok_direct_vote_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
                    stepDescription.text = NSLocalizedString("str_starname_register_domain_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                    stepImg.image = UIImage.init(named: "step4Img")
                    stepDescription.text = NSLocalizedString("str_starname_register_account_step_3", comment: "")
                    
                } else if (mType == IOV_MSG_TYPE_DELETE_DOMAIN || mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_delete_starname_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == IOV_MSG_TYPE_RENEW_DOMAIN || mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                    stepDescription.text = NSLocalizedString("str_starname_renew_starname_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
                    stepDescription.text = NSLocalizedString("str_starname_replace_starname_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_SWAP) {
                    stepDescription.text = NSLocalizedString("str_swap_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
                    stepDescription.text = NSLocalizedString("str_join_pool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
                    stepDescription.text = NSLocalizedString("str_exit_pool_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_LOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_lock_token_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_BEGIN_UNLCOK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_begin_unbonding_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                } else if (mType == OSMOSIS_MSG_TYPE_PERIOD_UNLOCK) {
                    stepDescription.text = NSLocalizedString("str_osmosis_unlock_token_step_3", comment: "")
                    stepImg.image = UIImage.init(named: "4StepImg4")
                    
                }
                
            } else if (step == 4) {
                if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
                    stepImg.image = UIImage.init(named: "step5Img")
                    stepDescription.text = NSLocalizedString("send_step_5", comment: "")
                    
                } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
                    stepImg.image = UIImage.init(named: "step5Img")
                    stepDescription.text = NSLocalizedString("redelegate_step_5", comment: "")
                    
                } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                    stepImg.image = UIImage.init(named: "step5Img")
                    stepDescription.text = NSLocalizedString("str_starname_register_account_step_4", comment: "")
                    
                } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
                    stepImg.image = UIImage.init(named: "step5Img")
                    stepDescription.text = NSLocalizedString("reward_harvest_vv_step_4", comment: "")
                    
                }
                
            }
        }
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
}
