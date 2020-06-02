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
    @IBOutlet weak var myRewardAmount: UILabel!
    @IBOutlet weak var myRewardDenom: UILabel!
    @IBOutlet weak var incentiveAddress: UILabel!
    @IBOutlet weak var incentiveDenom: UILabel!
    @IBOutlet weak var incentiveLockTime: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mIncentiveReward: KavaIncentiveParam.IncentiveReward!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        mIncentiveReward = BaseData.instance.mIncentiveParam.rewards[0]
        
        var mAmount = NSDecimalNumber.zero
        for reward in BaseData.instance.mUnClaimedIncentiveRewards {
            mAmount = mAmount.adding(NSDecimalNumber.init(string: reward.reward.amount))
        }
        
        WUtils.setDenomTitle(self.chainType!, myRewardDenom)
        
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
