//
//  StepFeeGrpcViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/26.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class StepFeeGrpcViewController: BaseViewController {

    @IBOutlet weak var feeTotalCard: CardView!
    @IBOutlet weak var feeTotalAmount: UILabel!
    @IBOutlet weak var feeTotalDenom: UILabel!
    @IBOutlet weak var feeTotalValue: UILabel!
    
    @IBOutlet weak var gasAmountLabel: UILabel!
    @IBOutlet weak var gasRateLabel: UILabel!
    @IBOutlet weak var gasFeeLabel: UILabel!
    @IBOutlet weak var gasSelectSegments: UISegmentedControl!
    
    @IBOutlet weak var speedImg: UIImageView!
    @IBOutlet weak var speedTxt: UILabel!
    
    @IBOutlet weak var btnGasCheck: UIButton!
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mSelectedGasPosition = 1
    var mSelectedGasRate = NSDecimalNumber.zero
    var mEstimateGasAmount = NSDecimalNumber.zero
    var mFee = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        feeTotalCard.backgroundColor = WUtils.getChainBg(pageHolderVC.chainType!)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeTotalDenom)
        if #available(iOS 13.0, *) {
            gasSelectSegments.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            gasSelectSegments.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            gasSelectSegments.selectedSegmentTintColor = WUtils.getChainColor(pageHolderVC.chainType!)
        } else {
            gasSelectSegments.tintColor = WUtils.getChainColor(pageHolderVC.chainType!)
        }
        mEstimateGasAmount = WUtils.getEstimateGasAmount(pageHolderVC.chainType!, pageHolderVC.mType!, pageHolderVC.mRewardTargetValidators_gRPC.count)
        onUpdateView()
    }
    
    func onCalculateFees() {
        mSelectedGasRate = WUtils.getGasRate(pageHolderVC.chainType!, mSelectedGasPosition)
        mFee = mSelectedGasRate.multiplying(by: mEstimateGasAmount, withBehavior: WUtils.handler0Up)
    }
    
    func onUpdateView() {
        onCalculateFees()
        
        feeTotalAmount.attributedText = WUtils.displayAmount2(mFee.stringValue, feeTotalAmount.font!, 6, 6)
        feeTotalValue.attributedText = WUtils.dpTokenValue(mFee, BaseData.instance.getLastPrice(), 6, feeTotalValue.font)
        
        gasRateLabel.attributedText = WUtils.displayGasRate2(mSelectedGasRate, font: gasRateLabel.font)
        gasAmountLabel.text = mEstimateGasAmount.stringValue
        gasFeeLabel.text = mFee.stringValue
        
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
        
    }

    @IBAction func onSwitchGasRate(_ sender: UISegmentedControl) {
        mSelectedGasPosition = sender.selectedSegmentIndex
        onUpdateView()
    }
    
    override func enableUserInteraction() {
        btnBefore.isUserInteractionEnabled = true
        btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCheckGas(_ sender: UIButton) {
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
        return true
    }
}
