//
//  StepUndelegateAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepUndelegateAmountViewController: BaseViewController, UITextFieldDelegate{
    
    @IBOutlet weak var toUndelegateAmountInput: AmountInputTextField!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!

    var pageHolderVC: StepGenTxViewController!
    var userDelegated = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        userDelegated = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address)!.getBondingAtom(pageHolderVC.mTargetValidator!)
        availableAmountLabel.attributedText = WUtils.displayAmout(userDelegated.stringValue, availableAmountLabel.font, 6)
        toUndelegateAmountInput.delegate = self
        toUndelegateAmountInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
    }

    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == toUndelegateAmountInput) {
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
        if (textField == toUndelegateAmountInput) {
            self.onUIupdate()
        }
    }
    
    
    func onUIupdate() {
        guard let text = toUndelegateAmountInput.text?.trimmingCharacters(in: .whitespaces) else {
            self.toUndelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if(text.count == 0) {
            self.toUndelegateAmountInput.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.stringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.toUndelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(by: 1000000).compare(userDelegated).rawValue > 0) {
            self.toUndelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.toUndelegateAmountInput.layer.borderColor = UIColor.white.cgColor
    }
    
    
    func isValiadAmount() -> Bool {
        let text = toUndelegateAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(by: 1000000).compare(userDelegated).rawValue > 0) { return false}
        return true
    }
    
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if(isValiadAmount()) {
            let userInput = WUtils.stringToDecimal((toUndelegateAmountInput.text?.trimmingCharacters(in: .whitespaces))!)
            var coin:Coin
            if(TESTNET) {
                coin = Coin.init("muon", userInput.multiplying(by: 1000000).stringValue)
            } else {
                coin = Coin.init("uatom", userInput.multiplying(by: 1000000).stringValue)
                
            }
            pageHolderVC.mToUndelegateAmount = coin
            
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
    
    
    @IBAction func onClickClear(_ sender: UIButton) {
        toUndelegateAmountInput.text = "";
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd01(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = WUtils.stringToDecimal(toUndelegateAmountInput.text!)
        }
        toUndelegateAmountInput.text = exist.adding(NSDecimalNumber(string: "0.1")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = WUtils.stringToDecimal(toUndelegateAmountInput.text!)
        }
        toUndelegateAmountInput.text = exist.adding(NSDecimalNumber(string: "1")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = WUtils.stringToDecimal(toUndelegateAmountInput.text!)
        }
        toUndelegateAmountInput.text = exist.adding(NSDecimalNumber(string: "10")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = WUtils.stringToDecimal(toUndelegateAmountInput.text!)
        }
        toUndelegateAmountInput.text = exist.adding(NSDecimalNumber(string: "100")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickHalf(_ sender: UIButton) {
        toUndelegateAmountInput.text = userDelegated.dividing(by: NSDecimalNumber(string: "2000000"), withBehavior: WUtils.handler6).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickMax(_ sender: UIButton) {
        toUndelegateAmountInput.text = userDelegated.dividing(by: NSDecimalNumber(string: "1000000"), withBehavior: WUtils.handler6).stringValue
        self.onUIupdate()
    }
}
