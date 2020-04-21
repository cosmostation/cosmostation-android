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
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, denomTitleLabel)
        
        maxAvailable = NSDecimalNumber.zero
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            mDpDecimal = 6
            maxAvailable = pageHolderVC.mAccount!.getAtomBalance().subtracting(NSDecimalNumber.one)
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mDpDecimal = 18
            if (pageHolderVC.mIrisToken?.base_token?.id == IRIS_DP_DENOM) {
                maxAvailable = maxAvailable.adding(self.pageHolderVC.mAccount!.getIrisBalance()).subtracting(NSDecimalNumber(string: "200000000000000000"))
                mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 18, mDpDecimal)
                
            } else {
                
            }
        
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            mDpDecimal = 8
            self.denomTitleLabel.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            if (pageHolderVC.mBnbToken?.symbol == BNB_MAIN_DENOM) {
                maxAvailable = pageHolderVC.mAccount!.getBnbBalance().subtracting(NSDecimalNumber.init(string: "0.000375"))
                mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 0, mDpDecimal)
                
            } else {
                self.denomTitleLabel.textColor = UIColor.white
                maxAvailable = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mBnbToken!.symbol)
                mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 0, mDpDecimal)
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            mDpDecimal = WUtils.getKavaCoinDecimal(self.pageHolderVC.mKavaSendDenom!)
            maxAvailable = self.pageHolderVC.mAccount!.getTokenBalance(self.pageHolderVC.mKavaSendDenom!)
            if (self.pageHolderVC.mKavaSendDenom == KAVA_MAIN_DENOM) {
                maxAvailable = maxAvailable.subtracting(NSDecimalNumber.one)
            }
            WUtils.showCoinDp(self.pageHolderVC.mKavaSendDenom!, maxAvailable.stringValue, denomTitleLabel, mAvailableAmountLabel, pageHolderVC.chainType!)
        
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            mDpDecimal = 9
            maxAvailable = pageHolderVC.mAccount!.getIovBalance().subtracting(NSDecimalNumber.init(string: "0.5"))
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 0, mDpDecimal)
        }
        
        mTargetAmountTextField.delegate = self
        mTargetAmountTextField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        let dp = "+ " + WUtils.DecimalToLocalString(NSDecimalNumber(string: "0.1"), 1)
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
                if(text.substring(from: index).count > (mDpDecimal - 1) && range.length == 0) {
                    return false
                }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (mDpDecimal - 1) && range.length == 0) {
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
        
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        }
        self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                return false
            }
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
            var toSendCoin:Coin?
            if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                toSendCoin = Coin.init(COSMOS_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                toSendCoin = Coin.init(IRIS_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mBnbToken!.symbol, userInput.stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mKavaSendDenom!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
                toSendCoin = Coin.init(pageHolderVC.mIovSendDenom!, userInput.stringValue)
            }
            
            var tempList = Array<Coin>()
            tempList.append(toSendCoin!)
            self.pageHolderVC.mToSendAmount = tempList
            
            self.backBtn.isUserInteractionEnabled = false
            self.nextBtn.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()

        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
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
        mTargetAmountTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
        
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if (mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        mTargetAmountTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        mTargetAmountTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        mTargetAmountTextField.text = WUtils.DecimalToLocalString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2), withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2), withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(halfValue, mDpDecimal)
        }
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
            self.showMaxWarnning()
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
            self.showMaxWarnning()
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(maxAvailable, mDpDecimal)
            if (pageHolderVC.mBnbToken?.symbol == BNB_MAIN_DENOM) {
                self.showMaxWarnning()
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(maxValue, mDpDecimal)
            if (self.pageHolderVC.mKavaSendDenom == KAVA_MAIN_DENOM) {
                self.showMaxWarnning()
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            mTargetAmountTextField.text = WUtils.DecimalToLocalString(maxAvailable, mDpDecimal)
            if (pageHolderVC.mIovSendDenom == IOV_MAIN_DENOM) {
                self.showMaxWarnning()
            }
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
