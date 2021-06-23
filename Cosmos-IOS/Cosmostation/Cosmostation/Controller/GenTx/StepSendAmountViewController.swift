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
    @IBOutlet weak var denomTitleLabel: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    @IBOutlet weak var btn01: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var maxAvailable = NSDecimalNumber.zero
    
//    var mDpDecimal:Int16 = 6
    
    var mDivideDecimal:Int16 = 6
    var mDisplayDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, denomTitleLabel)
        
        let mainDenom = WUtils.getMainDenom(pageHolderVC.chainType!)
        let feeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            mDivideDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
            mDisplayDecimal = WUtils.mainDisplayDecimal(pageHolderVC.chainType)
            if (pageHolderVC.mToSendDenom == mainDenom) {
                maxAvailable = BaseData.instance.getAvailableAmount_gRPC(pageHolderVC.mToSendDenom!).subtracting(feeAmount)
                
            } else {
                maxAvailable = BaseData.instance.getAvailableAmount_gRPC(pageHolderVC.mToSendDenom!)
            }
            
        } else {
            if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
                mDivideDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
                mDisplayDecimal = WUtils.mainDisplayDecimal(pageHolderVC.chainType)
                
            } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                mDivideDecimal = WUtils.getKavaCoinDecimal(pageHolderVC.mToSendDenom)
                mDisplayDecimal = WUtils.getKavaCoinDecimal(pageHolderVC.mToSendDenom)
                
            } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                mDivideDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
                mDisplayDecimal = WUtils.mainDisplayDecimal(pageHolderVC.chainType)
                
            } else if (pageHolderVC.chainType! == ChainType.SIF_MAIN) {
                mDivideDecimal = WUtils.getSifCoinDecimal(pageHolderVC.mToSendDenom)
                mDisplayDecimal = WUtils.getSifCoinDecimal(pageHolderVC.mToSendDenom)
                
            } else {
                mDivideDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
                mDisplayDecimal = WUtils.mainDisplayDecimal(pageHolderVC.chainType)
            }
            
            if (pageHolderVC.mToSendDenom == mainDenom) {
                maxAvailable = BaseData.instance.availableAmount(pageHolderVC.mToSendDenom!).subtracting(feeAmount)
            } else {
                maxAvailable = BaseData.instance.availableAmount(pageHolderVC.mToSendDenom!)
            }
        }
        WUtils.showCoinDp(self.pageHolderVC.mToSendDenom!, maxAvailable.stringValue, denomTitleLabel, mAvailableAmountLabel, pageHolderVC.chainType!)
        
        mTargetAmountTextField.delegate = self
        mTargetAmountTextField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        let dp = "+ " + WUtils.decimalNumberToLocaleString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == mTargetAmountTextField) {
            guard let text = textField.text else { return true }
            if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ".")) { return false }
            
            if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
            
            if (text.count == 0 && string.starts(with: ",")) { return false }
            
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (mDisplayDecimal - 1) && range.length == 0) {
                    return false
                }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (mDisplayDecimal - 1) && range.length == 0) {
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
        
        let userInput = WUtils.localeStringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if (userInput.multiplying(byPowerOf10: mDivideDecimal).compare(maxAvailable).rawValue > 0) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (pageHolderVC.mBnbToken?.type == BNB_TOKEN_TYPE_MINI) {
                if ((userInput.compare(NSDecimalNumber.one).rawValue < 0) && (userInput.compare(maxAvailable).rawValue != 0)) {
                    self.onShowToast(NSLocalizedString("error_bnb_mini_amount", comment: ""))
                    return false
                }
            }
            
        }
        
        if (userInput.multiplying(byPowerOf10: mDivideDecimal).compare(maxAvailable).rawValue > 0) {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
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
        if (isValiadAmount()) {
            if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                let userInput = WUtils.localeStringToDecimal((mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces))!)
                let toSendCoin = Coin.init(pageHolderVC.mToSendDenom!, WUtils.getFormattedNumber(userInput, mDisplayDecimal))
                var tempList = Array<Coin>()
                tempList.append(toSendCoin)
                self.pageHolderVC.mToSendAmount = tempList
                
                self.backBtn.isUserInteractionEnabled = false
                self.nextBtn.isUserInteractionEnabled = false
                pageHolderVC.onNextPage()
                
            } else {
                let userInput = WUtils.localeStringToDecimal((mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces))!)
                let toSendCoin = Coin.init(pageHolderVC.mToSendDenom!, userInput.multiplying(byPowerOf10: mDivideDecimal).stringValue)
                var tempList = Array<Coin>()
                tempList.append(toSendCoin)
                self.pageHolderVC.mToSendAmount = tempList
                
                self.backBtn.isUserInteractionEnabled = false
                self.nextBtn.isUserInteractionEnabled = false
                pageHolderVC.onNextPage()
            }
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
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDisplayDecimal)
        self.onUIupdate()
        
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if (mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDisplayDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDisplayDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDisplayDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDivideDecimal, withBehavior: WUtils.getDivideHandler(mDisplayDecimal))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDisplayDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = maxAvailable.multiplying(byPowerOf10: -mDivideDecimal, withBehavior: WUtils.getDivideHandler(mDisplayDecimal))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDisplayDecimal)
        if (pageHolderVC.mToSendDenom == WUtils.getMainDenom(pageHolderVC.chainType!)) {
            self.showMaxWarnning()
        }
        self.onUIupdate()
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
}
