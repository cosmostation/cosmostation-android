//
//  StepIncentive1ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepIncentive0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var totalRewardAmount: UILabel!
    @IBOutlet weak var totalRewardAmountDenom: UILabel!
    @IBOutlet weak var myRewardAmount: UILabel!
    @IBOutlet weak var myRewardDenom: UILabel!
    @IBOutlet weak var incentiveAddress: UILabel!
    @IBOutlet weak var incentiveDenom: UILabel!
    @IBOutlet weak var incentiveLockTime: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mIncentiveReward: KavaIncentiveParam.IncentiveReward!
    var mUnClaimedIncentiveReward: KavaIncentiveReward.UnClaimedIncentiveReward!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        mIncentiveReward = BaseData.instance.mIncentiveParam.rewards[0]
        mUnClaimedIncentiveReward = BaseData.instance.mUnClaimedIncentiveRewards.last
        let tAmount = NSDecimalNumber.init(string: mIncentiveReward.available_rewards.amount)
        let mAmount = NSDecimalNumber.init(string: mUnClaimedIncentiveReward.reward.amount)
        
        WUtils.setDenomTitle(self.chainType!, totalRewardAmountDenom)
        WUtils.setDenomTitle(self.chainType!, myRewardDenom)
        
        totalRewardAmount.attributedText = WUtils.displayAmount2(tAmount.stringValue, totalRewardAmount.font, 6, 6)
        myRewardAmount.attributedText = WUtils.displayAmount2(mAmount.stringValue, myRewardAmount.font, 6, 6)
        incentiveAddress.text = self.account?.account_address
        incentiveDenom.text = mIncentiveReward.denom.uppercased()
        incentiveLockTime.text = "365 Days"
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        self.pageHolderVC.onNextPage()
    }
}
