//
//  StepFeeOldViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/22.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class StepFeeOldViewController: BaseViewController {
    
    @IBOutlet weak var feeTotalCard: CardView!
    @IBOutlet weak var feeTotalAmount: UILabel!
    @IBOutlet weak var feeTotalDenom: UILabel!
    @IBOutlet weak var feeTotalValue: UILabel!
    
    @IBOutlet weak var gasSetCard: CardView!
    @IBOutlet weak var gasAmountLabel: UILabel!
    @IBOutlet weak var gasRateLabel: UILabel!
    @IBOutlet weak var gasFeeLabel: UILabel!
    @IBOutlet weak var gasSelectSegments: UISegmentedControl!
    
    @IBOutlet weak var speedImg: UIImageView!
    @IBOutlet weak var speedTxt: UILabel!
    
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mSelectedGasPosition = 1
    var mSelectedGasRate = NSDecimalNumber.zero
    var mEstimateGasAmount = NSDecimalNumber.zero
    var mFee = NSDecimalNumber.zero
    
    var mDivideDecimal:Int16 = 6
    var mDisplayDecimal:Int16 = 6

    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        feeTotalCard.backgroundColor = WUtils.getChainBg(pageHolderVC.chainType!)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeTotalDenom)
        mDivideDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
        mDisplayDecimal = WUtils.mainDisplayDecimal(pageHolderVC.chainType)
        if #available(iOS 13.0, *) {
            gasSelectSegments.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            gasSelectSegments.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            gasSelectSegments.selectedSegmentTintColor = WUtils.getChainColor(pageHolderVC.chainType!)
        } else {
            gasSelectSegments.tintColor = WUtils.getChainColor(pageHolderVC.chainType!)
        }
        mEstimateGasAmount = WUtils.getEstimateGasAmount(pageHolderVC.chainType!, pageHolderVC.mType!, pageHolderVC.mRewardTargetValidators.count)
        onUpdateView()
    }
    
    func onCalculateFees() {
        mSelectedGasRate = WUtils.getGasRate(pageHolderVC.chainType!, mSelectedGasPosition)
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            mFee = NSDecimalNumber.init(string: FEE_BNB_TRANSFER)
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            mFee = mSelectedGasRate.multiplying(by: mEstimateGasAmount, withBehavior: WUtils.handler18)
        } else {
            mFee = mSelectedGasRate.multiplying(by: mEstimateGasAmount, withBehavior: WUtils.handler0Up)
        }
    }
    
    func onUpdateView() {
        onCalculateFees()
        
        feeTotalAmount.attributedText = WUtils.displayAmount2(mFee.stringValue, feeTotalAmount.font!, mDivideDecimal, mDisplayDecimal)
        feeTotalValue.attributedText = WUtils.dpUserCurrencyValue(WUtils.getMainDenom(pageHolderVC.chainType), mFee, mDivideDecimal, feeTotalValue.font)
        
        gasRateLabel.attributedText = WUtils.displayGasRate(mSelectedGasRate.rounding(accordingToBehavior: WUtils.handler6), font: gasRateLabel.font, 4)
        gasAmountLabel.text = mEstimateGasAmount.stringValue
        gasFeeLabel.text = mFee.stringValue
        
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            self.gasSetCard.isHidden = false
            if (mSelectedGasPosition == 0) {
                self.speedImg.image = UIImage.init(named: "bycicle")
                self.speedTxt.text = NSLocalizedString("fee_speed_title_0", comment: "")
            } else if (mSelectedGasPosition == 1) {
                self.speedImg.image = UIImage.init(named: "car")
                self.speedTxt.text = NSLocalizedString("fee_speed_title_1", comment: "")
            } else {
                self.speedImg.image = UIImage.init(named: "roket")
                self.speedTxt.text = NSLocalizedString("fee_speed_title_2", comment: "")
            }
            
        } else {
            self.gasSetCard.isHidden = true
            self.speedImg.image = UIImage.init(named: "roket")
            self.speedTxt.text = NSLocalizedString("fee_speed_title_2", comment: "")
            
        }
    }
    
    @IBAction func onSwitchGasRate(_ sender: UISegmentedControl) {
        mSelectedGasPosition = sender.selectedSegmentIndex
        onUpdateView()
    }
    
    override func enableUserInteraction() {
        btnBefore.isUserInteractionEnabled = true
        btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        btnBefore.isUserInteractionEnabled = false
        btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (onCheckValidate()) {
            onSetFee()
            btnBefore.isUserInteractionEnabled = false
            btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
    
    func onSetFee() {
        let gasCoin = Coin.init(WUtils.getMainDenom(pageHolderVC.chainType), mFee.stringValue)
        var amount: Array<Coin> = Array<Coin>()
        amount.append(gasCoin)
        
        var fee = Fee.init()
        fee.amount = amount
        fee.gas = mEstimateGasAmount.stringValue
        
        pageHolderVC.mFee = fee
    }
    
    func onCheckValidate() -> Bool {
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let mainDenom = WUtils.getMainDenom(pageHolderVC.chainType!)
            if (pageHolderVC.mType == COSMOS_MSG_TYPE_TRANSFER2) {
                if (pageHolderVC.mToSendDenom == mainDenom) {
                    let spend = NSDecimalNumber.init(string: pageHolderVC.mToSendAmount[0].amount).adding(mFee)
                    let available = BaseData.instance.availableAmount(mainDenom)
                    if (available.compare(spend).rawValue < 0) {
                        self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                        return false
                    }
                }
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_DELEGATE) {
                let spend = NSDecimalNumber.init(string: pageHolderVC.mToDelegateAmount?.amount).adding(mFee)
                let available = BaseData.instance.delegatableAmount(mainDenom)
                if (available.compare(spend).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                    return false
                }
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                let available = BaseData.instance.availableAmount(mainDenom)
                var selectedRewardSum = NSDecimalNumber.zero
                pageHolderVC.mRewards.forEach { coin in
                    if (coin.denom == mainDenom) {
                        selectedRewardSum = selectedRewardSum.adding(WUtils.plainStringToDecimal(coin.amount))
                    }
                }
                if (available.compare(mFee).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                    return false
                }
                if (selectedRewardSum.compare(mFee).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return false
                    
                }
                
            } else if (pageHolderVC.mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                let available = BaseData.instance.availableAmount(mainDenom)
                let reInvestReward = NSDecimalNumber.init(string: pageHolderVC.mReinvestReward?.amount)
                if (available.compare(mFee).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                    return false
                }
                if (reInvestReward.compare(mFee).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return false
                }
            }
        }
        return true
    }
}
