//
//  StepDelegateFeeViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepDelegateFeeViewController: BaseViewController {

    @IBOutlet weak var feeTypeCardView: CardView!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeSlider: UISlider!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepDelegateViewController!
    var atomFees: Array<NSDecimalNumber>!
    var feeCoin:Coin!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
        
        atomFees = WUtils.getAtomFees()
        feeAmountLabel.attributedText = WUtils.displayAmout(atomFees[1].stringValue, feeAmountLabel.font, 6)
        
        
        let gesture = UITapGestureRecognizer(target: self, action:  #selector(self.tapFeeType(sender:)))
        self.feeTypeCardView.addGestureRecognizer(gesture)
    }

    @IBAction func onSlideChanged(_ sender: UISlider) {
//        print("sender : ", sender.value)
    }
    
    @IBAction func onSlideEnd(_ sender: UISlider) {
        print("onSlideEnd : ", sender.value)
        if(sender.value > 0.33 && sender.value < 0.66) {
            sender.value = 0.5
            feeAmountLabel.attributedText = WUtils.displayAmout(atomFees[1].stringValue, feeAmountLabel.font, 6)
        } else if (sender.value <= 0.33) {
            sender.value = 0.0
            feeAmountLabel.attributedText = WUtils.displayAmout(atomFees[0].stringValue, feeAmountLabel.font, 6)
        } else {
            sender.value = 1.0
            feeAmountLabel.attributedText = WUtils.displayAmout(atomFees[2].stringValue, feeAmountLabel.font, 6)
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
    
    @IBAction func onClickNext(_ sender: Any) {
        var gasAmount = atomFees[1].stringValue
        if (feeSlider.value <= 0.33) {
            gasAmount = atomFees[0].stringValue
        } else if (feeSlider.value > 0.66) {
            gasAmount = atomFees[2].stringValue
        }
        feeCoin = Coin.init("uatom", gasAmount)
        
        let fee = Fee.init()
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
    
    override func enableUserInteraction() {
        self.beforeBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
}
