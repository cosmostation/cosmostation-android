//
//  StepDelegateFeeViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepFeeViewController: BaseViewController {

    @IBOutlet weak var feeTypeCardView: CardView!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feePriceLabel: UILabel!
    @IBOutlet weak var feeSlider: UISlider!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var atomFees: Array<NSDecimalNumber>!
    var feeAmount   = NSDecimalNumber.zero
    var feeCoin:Coin!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        atomFees = WUtils.getAtomFees()
        feeAmountLabel.attributedText = WUtils.displayAmout(atomFees[1].stringValue, feeAmountLabel.font, 6)
        
        
        let gesture = UITapGestureRecognizer(target: self, action:  #selector(self.tapFeeType(sender:)))
        self.feeTypeCardView.addGestureRecognizer(gesture)
        
        _ = updateView(0)
    }

    @IBAction func onSlideChanged(_ sender: UISlider) {
    }
    
    @IBAction func onSlideEnd(_ sender: UISlider) {
        if(sender.value < 0.5) {
            sender.value = 0.0
            _ = updateView(0)
        } else if (sender.value < 1.5) {
            sender.value = 1.0
            _ = updateView(1)
        } else if (sender.value < 2.5) {
            sender.value = 2.0
            _ = updateView(2)
        } else {
            sender.value = 3.0
            _ = updateView(3)
        }
            
    }
    
    @objc func tapFeeType(sender : UITapGestureRecognizer) {
        self.onShowToast(NSLocalizedString("error_only_fee_with_atom", comment: ""))
    }
    
    @IBAction func onClickBefore(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    func updateView(_ position: Int) -> Bool {
        var available   = NSDecimalNumber.zero
        var toSpend     = NSDecimalNumber.zero
        feeAmount       = NSDecimalNumber.zero
        for balance in pageHolderVC.mBalances {
            if(TESTNET) {
                if(balance.balance_denom == "muon") {
                    available = available.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            } else {
                if(balance.balance_denom == "uatom") {
                    available = available.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            }
        }
        
        if(pageHolderVC.mType == COSMOS_MSG_TYPE_DELEGATE) {
            toSpend = WUtils.stringToDecimal(pageHolderVC.mToDelegateAmount!.amount)
            feeAmount = atomFees[position]
            
        } else if(pageHolderVC.mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
            feeAmount = atomFees[position]
            
        } else if(pageHolderVC.mType == COSMOS_MSG_TYPE_TRANSFER2) {
            toSpend = WUtils.stringToDecimal(pageHolderVC.mToSendAmount[0].amount)
            feeAmount = atomFees[position]
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
            
        }
        
        
        if(toSpend.adding(feeAmount).compare(available).rawValue > 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
            feeSlider.value = 0
            feeAmount = atomFees[0]
            feeAmountLabel.attributedText = WUtils.displayAmout(feeAmount.stringValue, feeAmountLabel.font, 6)
            updateFeePrice()
            return false;
        }
        print("available : ",available)
        print("toSpend   : ",toSpend)
        print("feeAmount : ",feeAmount)
        feeAmountLabel.attributedText = WUtils.displayAmout(feeAmount.stringValue, feeAmountLabel.font, 6)
        updateFeePrice()
        
        
        
        return true;
    }
    
    
    func updateFeePrice() {
        guard let tic = BaseData.instance.getAtomTicCmc() else {
            return
        }
        if let price = tic.value(forKeyPath: "data.quotes.USD.price") as? Double {
            let priceValue = NSDecimalNumber(value: price)
            feePriceLabel.attributedText = WUtils.displayUSD(feeAmount.dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2), font: feePriceLabel.font)
        }
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
            
        } else {
            if(self.updateView(Int(feeSlider!.value))) {
                if(TESTNET) {
                    feeCoin = Coin.init("muon", feeAmount.stringValue)
                } else {
                    feeCoin = Coin.init("uatom", feeAmount.stringValue)
                }
                var fee = Fee.init()
                var amount: Array<Coin> = Array<Coin>()
                amount.append(feeCoin)
                let gas = "200000"
                fee.amount = amount
                fee.gas = gas
                pageHolderVC.mFee = fee
                
                self.beforeBtn.isUserInteractionEnabled = false
                self.nextBtn.isUserInteractionEnabled = false
                pageHolderVC.onNextPage()
                
            }
        }
        
        
    }
    
    override func enableUserInteraction() {
        self.beforeBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
}
