//
//  StepDelegateAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepDelegateAmountViewController: BaseViewController, UITextFieldDelegate{

    @IBOutlet weak var toDelegateAmountInput: AmountInputTextField!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var denomTitleLabel: UILabel!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    @IBOutlet weak var btn01: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var userBalance = NSDecimalNumber.zero
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, denomTitleLabel)
        
        userBalance = NSDecimalNumber.zero
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, COSMOS_MAIN_DENOM).subtracting(NSDecimalNumber.one)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mDpDecimal = 18
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, IRIS_MAIN_DENOM).subtracting(NSDecimalNumber(string: "400000000000000000"))
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 18, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, KAVA_MAIN_DENOM).subtracting(NSDecimalNumber.one)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
        }
        print("userBalance ", userBalance)
        toDelegateAmountInput.delegate = self
        toDelegateAmountInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        let dp = "+ " + WUtils.DecimalToLocalString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(500), execute: {
            if ((self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) &&
                WUtils.stringToDecimal(self.pageHolderVC.mBalances[0].balance_locked) != NSDecimalNumber.zero) {
                self.showVestingWarnning()
            }
        })
    }
    
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == toDelegateAmountInput) {
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
        if (textField == toDelegateAmountInput) {
            self.onUIupdate()
        }
    }
    
    func onUIupdate() {
        guard let text = toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces) else {
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
        
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) {
                self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (userInput.multiplying(by: 1000000000000000000).compare(userBalance).rawValue > 0) {
                self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        }
        self.toDelegateAmountInput.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if (userInput.multiplying(by: 1000000).compare(userBalance).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (userInput.multiplying(by: 1000000000000000000).compare(userBalance).rawValue > 0) {
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
        if(isValiadAmount()) {
            let userInput = WUtils.stringToDecimal((toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces))!)
            var coin:Coin?
            if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                coin = Coin.init(COSMOS_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                coin = Coin.init(IRIS_MAIN_DENOM, userInput.multiplying(by: 1000000000000000000).stringValue)
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                coin = Coin.init(KAVA_MAIN_DENOM, userInput.multiplying(by: 1000000).stringValue)
            }
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
    
    @IBAction func onClickClear(_ sender: UIButton) {
        toDelegateAmountInput.text = ""
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd01(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        toDelegateAmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        toDelegateAmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        toDelegateAmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        toDelegateAmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickHalf(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let halfValue = userBalance.dividing(by: NSDecimalNumber(string: "2000000", locale: Locale.current), withBehavior: WUtils.handler6)
            toDelegateAmountInput.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let halfValue = userBalance.dividing(by: NSDecimalNumber(string: "2000000000000000000", locale: Locale.current), withBehavior: WUtils.handler18)
            toDelegateAmountInput.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
        }
        self.onUIupdate()
    }
    @IBAction func onClickMax(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let maxValue = userBalance.dividing(by: NSDecimalNumber(string: "1000000", locale: Locale.current), withBehavior: WUtils.handler6)
            toDelegateAmountInput.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let maxValue = userBalance.dividing(by: NSDecimalNumber(string: "1000000000000000000", locale: Locale.current), withBehavior: WUtils.handler18)
            toDelegateAmountInput.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
        }
        self.onUIupdate()
        self.showMaxWarnning()
    }
    
    func showMaxWarnning() {
        let noticeAlert = UIAlertController(title: NSLocalizedString("max_spend_title", comment: ""), message: NSLocalizedString("max_spend_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func showVestingWarnning() {
        let noticeAlert = UIAlertController(title: NSLocalizedString("vesting_account", comment: ""), message: NSLocalizedString("vesting_account_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .destructive, handler: { _ in
            self.navigationController?.popViewController(animated: true)
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
        
    }
}
