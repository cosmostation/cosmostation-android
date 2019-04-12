//
//  StepDelegateAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepAmountViewController: BaseViewController, UITextFieldDelegate{

    @IBOutlet weak var toDelegateAmountInput: AmountInputTextField!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepDelegateViewController!
    var userBalance = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
        for balance in pageHolderVC.mBalances {
            if(balance.balance_denom == "uatom") {
                userBalance = userBalance.adding(WUtils.stringToDecimal(balance.balance_amount))
            }
        }
        availableAmountLabel.attributedText = WUtils.displayAmout(userBalance.stringValue, availableAmountLabel.font, 6)
        toDelegateAmountInput.delegate = self
        toDelegateAmountInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        
    }
    
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == toDelegateAmountInput) {
            guard let text = textField.text else { return true }
            if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ".")) { return false }
            
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > 5 && range.length == 0) {
                    return false
                }
            }
            
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == toDelegateAmountInput) {
            guard let text = textField.text?.trimmingCharacters(in: .whitespaces) else {
                self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
            if(text.count == 0) {
                self.toDelegateAmountInput.layer.borderColor = UIColor.white.cgColor
                return
            }
            
            let userInput = WUtils.stringToDecimal(text)
            
            if (text.count > 1 && userInput == NSDecimalNumber.zero) {
                self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) {
                self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            self.toDelegateAmountInput.layer.borderColor = UIColor.white.cgColor
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) { return false}
        return true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if(isValiadAmount()) {
            let userInput = WUtils.stringToDecimal((toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces))!)
            let coin = Coin.init("uatom", userInput.multiplying(by: 1000000).stringValue)
            pageHolderVC.mToDelegateAmount = coin
            
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    

    override func enableUserInteraction() {
        self.cancelBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    
}
