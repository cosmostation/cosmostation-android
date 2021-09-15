//
//  KavaIncentiveClaim0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/29.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class KavaIncentiveClaim0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var kavaIncentiveAmountLabel: UILabel!
    @IBOutlet weak var hardIncentiveAmountLabel: UILabel!
    @IBOutlet weak var swpIncentiveAmountLabel: UILabel!
    @IBOutlet weak var lockupLabel: UILabel!
    @IBOutlet weak var option1Btn: UIButton!
    @IBOutlet weak var option2Btn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mIncentiveParam: IncentiveParam!
    var mIncentiveRewards: IncentiveReward!
    var kavaIncentiveAmount = NSDecimalNumber.zero
    var hardIncentiveAmount = NSDecimalNumber.zero
    var swpIncentiveAmount = NSDecimalNumber.zero

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        mIncentiveParam = BaseData.instance.mIncentiveParam
        mIncentiveRewards = BaseData.instance.mIncentiveRewards
        
        kavaIncentiveAmount = mIncentiveRewards.getIncentiveAmount(KAVA_MAIN_DENOM)
        hardIncentiveAmount = mIncentiveRewards.getIncentiveAmount(KAVA_HARD_DENOM)
        swpIncentiveAmount = mIncentiveRewards.getIncentiveAmount(KAVA_SWAP_DENOM)
        
        kavaIncentiveAmountLabel.attributedText = WUtils.displayAmount2(kavaIncentiveAmount.stringValue, kavaIncentiveAmountLabel.font!, 6, 6)
        hardIncentiveAmountLabel.attributedText = WUtils.displayAmount2(hardIncentiveAmount.stringValue, hardIncentiveAmountLabel.font!, 6, 6)
        swpIncentiveAmountLabel.attributedText = WUtils.displayAmount2(swpIncentiveAmount.stringValue, swpIncentiveAmountLabel.font!, 6, 6)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    
    
    
    @IBAction func onClickOption1(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        let kavaIncentiveCal = kavaIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_MAIN_DENOM, 0), withBehavior: WUtils.handler0)
        let hardIncentiveCal = hardIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_HARD_DENOM, 0), withBehavior: WUtils.handler0)
        let swpIncentiveCal = swpIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_SWAP_DENOM, 0), withBehavior: WUtils.handler0)
        
        kavaIncentiveAmountLabel.attributedText = WUtils.displayAmount2(kavaIncentiveCal.stringValue, kavaIncentiveAmountLabel.font!, 6, 6)
        hardIncentiveAmountLabel.attributedText = WUtils.displayAmount2(hardIncentiveCal.stringValue, hardIncentiveAmountLabel.font!, 6, 6)
        swpIncentiveAmountLabel.attributedText = WUtils.displayAmount2(swpIncentiveCal.stringValue, swpIncentiveAmountLabel.font!, 6, 6)
        
        lockupLabel.text = "1 Month"
        pageHolderVC.mIncentiveMultiplier = "small"
    }
    
    @IBAction func onClickOption2(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        let kavaIncentiveCal = kavaIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_MAIN_DENOM, 1), withBehavior: WUtils.handler0)
        let hardIncentiveCal = hardIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_HARD_DENOM, 1), withBehavior: WUtils.handler0)
        let swpIncentiveCal = swpIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_SWAP_DENOM, 1), withBehavior: WUtils.handler0)
        
        kavaIncentiveAmountLabel.attributedText = WUtils.displayAmount2(kavaIncentiveCal.stringValue, kavaIncentiveAmountLabel.font!, 6, 6)
        hardIncentiveAmountLabel.attributedText = WUtils.displayAmount2(hardIncentiveCal.stringValue, hardIncentiveAmountLabel.font!, 6, 6)
        swpIncentiveAmountLabel.attributedText = WUtils.displayAmount2(swpIncentiveCal.stringValue, swpIncentiveAmountLabel.font!, 6, 6)
        
        lockupLabel.text = "12 Month"
        pageHolderVC.mIncentiveMultiplier = "large"
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
    }

}
