//
//  StepSendAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepSendAmountViewController: BaseViewController, UITextFieldDelegate{

    @IBOutlet weak var mTargetAmountTextField: AmountInputTextField!
    @IBOutlet weak var mAvailableAmountLabel: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var userBalance = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        userBalance = NSDecimalNumber.zero
        for balance in pageHolderVC.mBalances {
            if(TESTNET) {
                if(balance.balance_denom == "muon") {
                    userBalance = userBalance.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            } else {
                if(balance.balance_denom == "uatom") {
                    userBalance = userBalance.adding(WUtils.stringToDecimal(balance.balance_amount)).subtracting(NSDecimalNumber(string: "500"))
                }
            }
        }
        mAvailableAmountLabel.attributedText = WUtils.displayAmout(userBalance.stringValue, mAvailableAmountLabel.font, 6)
        mTargetAmountTextField.delegate = self
        mTargetAmountTextField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == mTargetAmountTextField) {
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
        if (textField == mTargetAmountTextField) {
            onUIupdate()
        }
    }
    
    func onUIupdate() {
        guard let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces) else {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if(text.count == 0) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.stringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            return false
            
        }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            return false
            
        }
        if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            return false
            
        }
        
        if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) {
            self.onShowToast(NSLocalizedString("error_no_fee", comment: ""))
            return false
            
        }
        return true
    }
    

    @IBAction func onClickBack(_ sender: Any) {
        self.backBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    @IBAction func onClickNext(_ sender: Any) {
        if(isValiadAmount()) {
            let userInput = WUtils.stringToDecimal((mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces))!)
            var coin:Coin
            if(TESTNET) {
                coin = Coin.init("muon", userInput.multiplying(by: 1000000).stringValue)
            } else {
                coin = Coin.init("uatom", userInput.multiplying(by: 1000000).stringValue)
            }
            
            var tempList = Array<Coin>()
            tempList.append(coin)
            self.pageHolderVC.mToSendAmount = tempList
            
            self.backBtn.isUserInteractionEnabled = false
            self.nextBtn.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()

        }
    }
    
    
    override func enableUserInteraction() {
        self.backBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    
    @IBAction func onClickClear(_ sender: UIButton) {
        mTargetAmountTextField.text = ""
        self.onUIupdate()
    }
    @IBAction func onClickAdd01(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = WUtils.stringToDecimal(mTargetAmountTextField.text!)
        }
        mTargetAmountTextField.text = exist.adding(NSDecimalNumber(string: "0.1")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = WUtils.stringToDecimal(mTargetAmountTextField.text!)
        }
        mTargetAmountTextField.text = exist.adding(NSDecimalNumber(string: "1")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = WUtils.stringToDecimal(mTargetAmountTextField.text!)
        }
        mTargetAmountTextField.text = exist.adding(NSDecimalNumber(string: "10")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = WUtils.stringToDecimal(mTargetAmountTextField.text!)
        }
        mTargetAmountTextField.text = exist.adding(NSDecimalNumber(string: "100")).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickHalf(_ sender: UIButton) {
        mTargetAmountTextField.text = userBalance.dividing(by: NSDecimalNumber(string: "2000000"), withBehavior: WUtils.handler6).stringValue
        self.onUIupdate()
    }
    @IBAction func onClickMax(_ sender: UIButton) {
        mTargetAmountTextField.text = userBalance.dividing(by: NSDecimalNumber(string: "1000000"), withBehavior: WUtils.handler6).stringValue
        self.onUIupdate()
    }
}
