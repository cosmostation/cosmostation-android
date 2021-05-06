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
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            mDpDecimal = 6
            userBalance = WUtils.getDelegableAmount(pageHolderVC.mBalances, KAVA_MAIN_DENOM)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, BAND_MAIN_DENOM)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, SECRET_MAIN_DENOM).subtracting(NSDecimalNumber(string: "50000"))
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, IOV_MAIN_DENOM).subtracting(NSDecimalNumber(string: "200000"))
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, IOV_TEST_DENOM).subtracting(NSDecimalNumber(string: "200000"))
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            mDpDecimal = 6
            userBalance = WUtils.getTokenAmount(pageHolderVC.mBalances, CERTIK_MAIN_DENOM).subtracting(NSDecimalNumber(string: "20000"))
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SENTINEL_MAIN) {
            mDpDecimal = 6
            let feeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, COSMOS_MSG_TYPE_DELEGATE, 0)
            userBalance = WUtils.getDelegableAmount(pageHolderVC.mBalances, SENTINEL_MAIN_DENOM).subtracting(feeAmount)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.FETCH_MAIN) {
            mDpDecimal = 18
            let feeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, COSMOS_MSG_TYPE_DELEGATE, 0)
            userBalance = WUtils.getDelegableAmount(pageHolderVC.mBalances, WUtils.getMainDenom(pageHolderVC.chainType)).subtracting(feeAmount)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, WUtils.mainDivideDecimal(pageHolderVC.chainType), mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SIF_MAIN) {
            mDpDecimal = 18
            let feeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, COSMOS_MSG_TYPE_DELEGATE, 0)
            userBalance = WUtils.getDelegableAmount(pageHolderVC.mBalances, WUtils.getMainDenom(pageHolderVC.chainType)).subtracting(feeAmount)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, WUtils.mainDivideDecimal(pageHolderVC.chainType), mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.KI_MAIN) {
            mDpDecimal = 6
            let feeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, COSMOS_MSG_TYPE_DELEGATE, 0)
            userBalance = WUtils.getDelegableAmount(pageHolderVC.mBalances, WUtils.getMainDenom(pageHolderVC.chainType)).subtracting(feeAmount)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, WUtils.mainDivideDecimal(pageHolderVC.chainType), mDpDecimal)
            
        }
        
        else if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            mDpDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
            let feeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, COSMOS_MSG_TYPE_DELEGATE, 0)
            userBalance = BaseData.instance.getDelegatable(WUtils.getMainDenom(pageHolderVC.chainType)).subtracting(feeAmount)
            availableAmountLabel.attributedText = WUtils.displayAmount2(userBalance.stringValue, availableAmountLabel.font, mDpDecimal, mDpDecimal)
        }
        
        toDelegateAmountInput.delegate = self
        toDelegateAmountInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        let dp = "+ " + WUtils.decimalNumberToLocaleString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
        
    }
    
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == toDelegateAmountInput) {
            guard let text = textField.text else { return true }
            if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ".")) { return false }
            
            if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ",")) { return false }
            
            if let index = text.range(of: ".")?.upperBound {
                if (text.substring(from: index).count > (mDpDecimal - 1) && range.length == 0) {
                    return false
                }
            }
            
            if let index = text.range(of: ",")?.upperBound {
                if (text.substring(from: index).count > (mDpDecimal - 1) && range.length == 0) {
                    return false
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
        
        let userInput = WUtils.localeStringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(userBalance).rawValue > 0) {
            self.toDelegateAmountInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.toDelegateAmountInput.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(userBalance).rawValue > 0) { return false }
        return true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((toDelegateAmountInput.text?.trimmingCharacters(in: .whitespaces))!)
            let coin = Coin.init(WUtils.getMainDenom(pageHolderVC.chainType), userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
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
        toDelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        toDelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        toDelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(toDelegateAmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toDelegateAmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        toDelegateAmountInput.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickHalf(_ sender: UIButton) {
        let halfValue = userBalance.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
        toDelegateAmountInput.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
        self.onUIupdate()
    }
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = userBalance.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
        toDelegateAmountInput.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
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
