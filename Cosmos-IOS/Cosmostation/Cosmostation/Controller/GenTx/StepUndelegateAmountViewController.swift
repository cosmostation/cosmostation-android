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
    @IBOutlet weak var denomTitleLabel: UILabel!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    @IBOutlet weak var btn01: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var userDelegated = NSDecimalNumber.zero
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, denomTitleLabel)
        
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            userDelegated = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address)!.getBondingAmount(pageHolderVC.mTargetValidator!)
            mDpDecimal = 6
            availableAmountLabel.attributedText = WUtils.displayAmount2(userDelegated.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            userDelegated = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address)!.getBondingAmount(pageHolderVC.mTargetValidator!)
            mDpDecimal = 18
            availableAmountLabel.attributedText = WUtils.displayAmount2(userDelegated.stringValue, availableAmountLabel.font, 18, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            userDelegated = BaseData.instance.getDelegated(self.pageHolderVC.mTargetValidator_V1?.operator_address)
            mDpDecimal = 6
            availableAmountLabel.attributedText = WUtils.displayAmount2(userDelegated.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        }
        toUndelegateAmountInput.delegate = self
        toUndelegateAmountInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        let dp = "+ " + WUtils.decimalNumberToLocaleString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
    }

    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == toUndelegateAmountInput) {
            guard let text = textField.text else { return true }
            if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ".")) { return false }
            
            if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ",")) { return false }
            
            if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                    pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                    pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                    pageHolderVC.chainType! == ChainType.AKASH_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
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
                
            } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
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
        
        let userInput = WUtils.localeStringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.toUndelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                pageHolderVC.chainType! == ChainType.AKASH_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            if (userInput.multiplying(by: 1000000).compare(userDelegated).rawValue > 0) {
                self.toUndelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            if (userInput.multiplying(by: 1000000000000000000).compare(userDelegated).rawValue > 0) {
                self.toUndelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        }
        self.toUndelegateAmountInput.layer.borderColor = UIColor.white.cgColor
    }
    
    
    func isValiadAmount() -> Bool {
        let text = toUndelegateAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN  || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                pageHolderVC.chainType! == ChainType.AKASH_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            if (userInput.multiplying(by: 1000000).compare(userDelegated).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            if (userInput.multiplying(by: 1000000000000000000).compare(userDelegated).rawValue > 0) {
                return false
            }
        }
        return true
    }
    
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((toUndelegateAmountInput.text?.trimmingCharacters(in: .whitespaces))!)
            var coin:Coin?
            if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
                coin = Coin.init(COSMOS_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                coin = Coin.init(IRIS_MAIN_DENOM, userInput.multiplying(by: 1000000000000000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                coin = Coin.init(KAVA_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                coin = Coin.init(BAND_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                coin = Coin.init(SECRET_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
                coin = Coin.init(IOV_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
                coin = Coin.init(IOV_TEST_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                coin = Coin.init(CERTIK_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                coin = Coin.init(AKASH_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
                coin = Coin.init(COSMOS_TEST_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
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
            exist = NSDecimalNumber(string: toUndelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
        
    }
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toUndelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toUndelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toUndelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toUndelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickHalf(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                pageHolderVC.chainType! == ChainType.AKASH_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            let halfValue = userDelegated.dividing(by: NSDecimalNumber(string: "2000000", locale: Locale.current), withBehavior: WUtils.handler6)
            toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            let halfValue = userDelegated.dividing(by: NSDecimalNumber(string: "2000000000000000000", locale: Locale.current), withBehavior: WUtils.handler18)
            toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
        }
        self.onUIupdate()
    }
    @IBAction func onClickMax(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                pageHolderVC.chainType! == ChainType.AKASH_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            let maxValue = userDelegated.dividing(by: NSDecimalNumber(string: "1000000", locale: Locale.current), withBehavior: WUtils.handler6)
            toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            let maxValue = userDelegated.dividing(by: NSDecimalNumber(string: "1000000000000000000", locale: Locale.current), withBehavior: WUtils.handler18)
            toUndelegateAmountInput.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
        }
        self.onUIupdate()
    }
}
