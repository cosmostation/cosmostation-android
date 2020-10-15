//
//  StepHarvestReward0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHarvestReward0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var hRewardAmount: UILabel!
    @IBOutlet weak var hRewardDenom: UILabel!
    @IBOutlet weak var lockup: UILabel!
    @IBOutlet weak var receivableAmount: UILabel!
    @IBOutlet weak var receivableDenom: UILabel!
    
    @IBOutlet weak var option1Btn: UIButton!
    @IBOutlet weak var option2Btn: UIButton!
    @IBOutlet weak var option3Btn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mHarvestDepositDenom: String?
    var mHarvestDepositType: String?
    var mHavestParam: KavaHavestParam?
    var mDistributionSchedule: KavaHavestParam.DistributionSchedule?  //lps or stake schedules
    var mAllRewardAmount = NSDecimalNumber.zero
    var mKavaClaimMultiplier = Array<KavaClaimMultiplier>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.mHarvestDepositDenom = pageHolderVC.mHarvestDepositDenom
        self.mHarvestDepositType = pageHolderVC.mHarvestDepositType
        self.mHavestParam = BaseData.instance.mHavestParam
        
        let myHavestReward = BaseData.instance.mHavestRewards.filter({ $0.deposit_denom == mHarvestDepositDenom && $0.type == mHarvestDepositType}).first
//        print("mHarvestDepositDenom ", mHarvestDepositDenom)
//        print("mHarvestDepositType ", mHarvestDepositType)
//        print("myHavestReward ", myHavestReward?.amount.amount)
        
        if let hRewardAmount =  BaseData.instance.mHavestRewards.filter({ $0.deposit_denom == mHarvestDepositDenom && $0.type == mHarvestDepositType}).first?.amount.amount {
            mAllRewardAmount = NSDecimalNumber.init(string: hRewardAmount)
        }
        WUtils.showCoinDp(KAVA_HARD_DENOM, mAllRewardAmount.stringValue, hRewardDenom, hRewardAmount, chainType!)
        
        if (mHarvestDepositType == "stake") {
            mKavaClaimMultiplier = (mHavestParam?.getKavaStakerSchedule()?.distribution_schedule.claim_multipliers)!
        } else {
            mKavaClaimMultiplier = mHavestParam!.result.liquidity_provider_schedules.filter({ $0.deposit_denom == mHarvestDepositDenom}).first!.claim_multipliers
        }
        if (mKavaClaimMultiplier.count > 0) {
            option1Btn.isHidden = false
            option1Btn.setTitle(mKavaClaimMultiplier[0].name.uppercased(), for: .normal)
        }
        if (mKavaClaimMultiplier.count > 1) {
            option2Btn.isHidden = false
            option2Btn.setTitle(mKavaClaimMultiplier[1].name.uppercased(), for: .normal)
        }
        if (mKavaClaimMultiplier.count > 2) {
            option3Btn.isHidden = false
            option3Btn.setTitle(mKavaClaimMultiplier[2].name.uppercased(), for: .normal)
        }
        
    }
    
    override func enableUserInteraction() {
        initBtns()
        pageHolderVC.mIncentiveMultiplier = nil
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickOption1(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        lockup.text = mKavaClaimMultiplier[0].months_lockup + " month"
        let receiveAmount = mAllRewardAmount.multiplying(by: NSDecimalNumber.init(string: mKavaClaimMultiplier[0].factor), withBehavior: WUtils.handler0)
        WUtils.showCoinDp(KAVA_HARD_DENOM, receiveAmount.stringValue, receivableDenom, receivableAmount, chainType!)
        pageHolderVC.mIncentiveMultiplier = mKavaClaimMultiplier[0]
        pageHolderVC.mIncentiveReceivable = receiveAmount
    }
    
    @IBAction func onClickOption2(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        lockup.text = mKavaClaimMultiplier[1].months_lockup + " month"
        let receiveAmount = mAllRewardAmount.multiplying(by: NSDecimalNumber.init(string: mKavaClaimMultiplier[1].factor), withBehavior: WUtils.handler0)
        WUtils.showCoinDp(KAVA_HARD_DENOM, receiveAmount.stringValue, receivableDenom, receivableAmount, chainType!)
        pageHolderVC.mIncentiveMultiplier = mKavaClaimMultiplier[1]
        pageHolderVC.mIncentiveReceivable = receiveAmount
    }
    
    @IBAction func onClickOption3(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        lockup.text = mKavaClaimMultiplier[2].months_lockup + " month"
        let receiveAmount = mAllRewardAmount.multiplying(by: NSDecimalNumber.init(string: mKavaClaimMultiplier[2].factor), withBehavior: WUtils.handler0)
        WUtils.showCoinDp(KAVA_HARD_DENOM, receiveAmount.stringValue, receivableDenom, receivableAmount, chainType!)
        pageHolderVC.mIncentiveMultiplier = mKavaClaimMultiplier[2]
        pageHolderVC.mIncentiveReceivable = receiveAmount
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (pageHolderVC.mIncentiveMultiplier != nil) {
            self.btnCancel.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_no_opinion", comment: ""))
            return
        }
    }
    
    
    func initBtns() {
        option1Btn.borderColor = UIColor(hexString: "#4b4f54")
        option2Btn.borderColor = UIColor(hexString: "#4b4f54")
        option3Btn.borderColor = UIColor(hexString: "#4b4f54")
    }
}
