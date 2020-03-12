//
//  StepRedelegateAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepRedelegateAmountViewController: BaseViewController, UITextFieldDelegate{

    @IBOutlet weak var redelegateInputTextField: AmountInputTextField!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var availableDenomLabel: UILabel!
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var btnAdd01: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var userDelegated = NSDecimalNumber.zero
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, availableDenomLabel)
        
        userDelegated = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address)!.getBondingAmount(pageHolderVC.mTargetValidator!)
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                mDpDecimal = 6
            availableAmountLabel.attributedText = WUtils.displayAmount2(userDelegated.stringValue, availableAmountLabel.font, 6, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mDpDecimal = 18
            availableAmountLabel.attributedText = WUtils.displayAmount2(userDelegated.stringValue, availableAmountLabel.font, 18, mDpDecimal)
        }
        redelegateInputTextField.delegate = self
        redelegateInputTextField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    
        let dp = "+ " + WUtils.DecimalToLocalString(NSDecimalNumber(string: "0.1"), 1)
        btnAdd01.setTitle(dp, for: .normal)
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == redelegateInputTextField) {
            guard let text = textField.text else { return true }
            if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ".")) { return false }
            
            if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ",")) { return false }
            
            
            if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
                pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
                pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                if let index = text.range(of: ".")?.upperBound {
                    if(text.substring(from: index).count > 5 && range.length == 0) {
                        return false
                    }
                }
                
                if let index = text.range(of: ",")?.upperBound {
                    if(text.substring(from: index).count > 5 && range.length == 0) {
                        return false
                    }
                }
                
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                if let index = text.range(of: ".")?.upperBound {
                    if(text.substring(from: index).count > 17 && range.length == 0) {
                        return false
                    }
                }
                
                if let index = text.range(of: ",")?.upperBound {
                    if(text.substring(from: index).count > 17 && range.length == 0) {
                        return false
                    }
                }
            }
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == redelegateInputTextField) {
            self.onUIupdate()
        }
    }
    
    func onUIupdate() {
        guard let text = redelegateInputTextField.text?.trimmingCharacters(in: .whitespaces) else {
            self.redelegateInputTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if(text.count == 0) {
            self.redelegateInputTextField.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.stringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.redelegateInputTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(by: 1000000).compare(userDelegated).rawValue > 0) {
            self.redelegateInputTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.redelegateInputTextField.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = redelegateInputTextField.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if (userInput.multiplying(by: 1000000).compare(userDelegated).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (userInput.multiplying(by: 1000000000000000000).compare(userDelegated).rawValue > 0) {
                return false
            }
        }
        return true
    }
    
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if(isValiadAmount()) {
            let userInput = WUtils.stringToDecimal((redelegateInputTextField.text?.trimmingCharacters(in: .whitespaces))!)
            var coin:Coin?
            if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                coin = Coin.init(COSMOS_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                coin = Coin.init(IRIS_MAIN_DENOM, userInput.multiplying(by: 1000000000000000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                coin = Coin.init(KAVA_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            }
            pageHolderVC.mToReDelegateAmount = coin
            self.btnCancel.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            
        }
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickClear(_ sender: UIButton) {
        redelegateInputTextField.text = "";
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd01(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(redelegateInputTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: redelegateInputTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        redelegateInputTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(redelegateInputTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: redelegateInputTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        redelegateInputTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(redelegateInputTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: redelegateInputTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        redelegateInputTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(redelegateInputTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: redelegateInputTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        redelegateInputTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let halfValue = userDelegated.dividing(by: NSDecimalNumber(string: "2000000", locale: Locale.current), withBehavior: WUtils.handler6)
            redelegateInputTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let halfValue = userDelegated.dividing(by: NSDecimalNumber(string: "2000000000000000000", locale: Locale.current), withBehavior: WUtils.handler18)
            redelegateInputTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
        }
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let maxValue = userDelegated.dividing(by: NSDecimalNumber(string: "1000000", locale: Locale.current), withBehavior: WUtils.handler6)
            redelegateInputTextField.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let maxValue = userDelegated.dividing(by: NSDecimalNumber(string: "1000000000000000000", locale: Locale.current), withBehavior: WUtils.handler18)
            redelegateInputTextField.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
        }
        self.onUIupdate()
    }
    
}
