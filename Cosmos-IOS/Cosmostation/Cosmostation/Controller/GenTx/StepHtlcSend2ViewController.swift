//
//  StepHtlcSend2ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend2ViewController: BaseViewController, UITextFieldDelegate {
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var AmountInput: AmountInputTextField!
    @IBOutlet weak var minAvailableAmount: UILabel!
    @IBOutlet weak var maxAvailableAmount: UILabel!
    @IBOutlet weak var availableDenom: UILabel!
    @IBOutlet weak var btnAdd01: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var minAvailable = NSDecimalNumber.zero
    var maxAvailable = NSDecimalNumber.zero
    var mDpDecimal:Int16 = 8
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            mDpDecimal = 8;
            minAvailable = NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN)
            minAvailableAmount.attributedText = WUtils.displayAmount2(minAvailable.stringValue, minAvailableAmount.font, 0, mDpDecimal)
            maxAvailable = WUtils.getTokenAmount(self.pageHolderVC.mAccount?.account_balances, self.pageHolderVC.mHtlcDenom!).subtracting(NSDecimalNumber.init(string: "0.000375"))
            if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN) {
                if (maxAvailable.compare(pageHolderVC.mSwapRemainCap).rawValue > 0) {
                    maxAvailable = pageHolderVC.mSwapRemainCap
                }
            }
            if (maxAvailable.compare(pageHolderVC.mSwapMaxOnce.multiplying(byPowerOf10: -mDpDecimal)).rawValue > 0) {
                maxAvailable = pageHolderVC.mSwapMaxOnce.multiplying(byPowerOf10: -mDpDecimal)
            }
            maxAvailableAmount.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, maxAvailableAmount.font, 0, mDpDecimal)
            availableDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            availableDenom.textColor = COLOR_BNB
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            mDpDecimal = WUtils.getKavaCoinDecimal(self.pageHolderVC.mHtlcDenom!)
            minAvailable = NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN).multiplying(byPowerOf10: mDpDecimal)
            minAvailableAmount.attributedText = WUtils.displayAmount2(minAvailable.stringValue, minAvailableAmount.font, mDpDecimal, mDpDecimal)
            maxAvailable = WUtils.getTokenAmount(self.pageHolderVC.mAccount?.account_balances, self.pageHolderVC.mHtlcDenom!)
            if (maxAvailable.compare(NSDecimalNumber.init(string: "1000000000000")).rawValue > 0) {
                maxAvailable = NSDecimalNumber.init(string: "1000000000000")
            }
            maxAvailableAmount.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, maxAvailableAmount.font, mDpDecimal, mDpDecimal)
            availableDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            availableDenom.textColor = .white
            
        }
        
        AmountInput.delegate = self
        AmountInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        let dp = "+ " + WUtils.DecimalToLocalString(NSDecimalNumber(string: "0.1"), 1)
        btnAdd01.setTitle(dp, for: .normal)
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let text = textField.text else { return true }
        if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ".")) { return false }
        if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ",")) { return false }
        if let index = text.range(of: ".")?.upperBound {
            if(text.substring(from: index).count > (mDpDecimal - 1) && range.length == 0) { return false }
        }
        if let index = text.range(of: ",")?.upperBound {
            if(text.substring(from: index).count > (mDpDecimal - 1) && range.length == 0) { return false }
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        guard let text = textField.text?.trimmingCharacters(in: .whitespaces) else {
            textField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (text.count == 0) {
            textField.layer.borderColor = UIColor.white.cgColor
            return
        }
        let userInput = WUtils.stringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            textField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                textField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            if (userInput.compare(NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN)).rawValue < 0) {
                textField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                textField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            if (userInput.compare(NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN)).rawValue < 0) {
                textField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        }
        textField.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = AmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) { return false }
            if (userInput.compare(NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN)).rawValue < 0) {return false}
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) { return false }
            if (userInput.compare(NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN)).rawValue < 0) {return false}
        }
        return true
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if(isValiadAmount()) {
            let userInput = WUtils.stringToDecimal((AmountInput.text?.trimmingCharacters(in: .whitespaces))!)
            var toSendCoin:Coin?
            if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN ||
                pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mHtlcDenom!, userInput.stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN ||
                pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mHtlcDenom!.lowercased(), userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            }
            
            var tempList = Array<Coin>()
            tempList.append(toSendCoin!)
            self.pageHolderVC.mToSendAmount = tempList

            self.btnBack.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            
        }
    }
    
    @IBAction func onClickClear(_ sender: UIButton) {
        AmountInput.text = ""
        textFieldDidChange(AmountInput)
    }
    
    @IBAction func onClickAdd01(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(AmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: AmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        AmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        textFieldDidChange(AmountInput)
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(AmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: AmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        AmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        textFieldDidChange(AmountInput)
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(AmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: AmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        AmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        textFieldDidChange(AmountInput)
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(AmountInput.text!.count > 0) {
            exist = NSDecimalNumber(string: AmountInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        AmountInput.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        textFieldDidChange(AmountInput)
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN ||
            pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2), withBehavior: WUtils.getDivideHandler(mDpDecimal))
            AmountInput.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            AmountInput.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
        }
        textFieldDidChange(AmountInput)
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN ||
            pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            AmountInput.text = WUtils.DecimalToLocalString(maxAvailable, mDpDecimal)
            if (pageHolderVC.mBnbToken?.symbol == BNB_MAIN_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            AmountInput.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
        }
        textFieldDidChange(AmountInput)
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
