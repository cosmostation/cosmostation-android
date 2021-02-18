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
        if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            mDpDecimal = 18
            if (pageHolderVC.mIrisToken?.base_token?.id == IRIS_DP_DENOM) {
                maxAvailable = maxAvailable.adding(self.pageHolderVC.mAccount!.getIrisBalance()).subtracting(NSDecimalNumber(string: "200000000000000000"))
                mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 18, mDpDecimal)
                
            } else {
                
            }
        
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
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
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            mDpDecimal = WUtils.getKavaCoinDecimal(self.pageHolderVC.mKavaSendDenom!)
            maxAvailable = self.pageHolderVC.mAccount!.getTokenBalance(self.pageHolderVC.mKavaSendDenom!)
            WUtils.showCoinDp(self.pageHolderVC.mKavaSendDenom!, maxAvailable.stringValue, denomTitleLabel, mAvailableAmountLabel, pageHolderVC.chainType!)
        
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            mDpDecimal = 6
            maxAvailable = pageHolderVC.mAccount!.getIovBalance().subtracting(NSDecimalNumber.init(string: "100000"))
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            mDpDecimal = 6
            maxAvailable = pageHolderVC.mAccount!.getBandBalance()
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            mDpDecimal = 6
            maxAvailable = pageHolderVC.mAccount!.getTokenBalance(SECRET_MAIN_DENOM).subtracting(NSDecimalNumber.init(string: "20000"))
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            mDpDecimal = 18
            self.denomTitleLabel.text = pageHolderVC.mOkSendDenom?.uppercased()
            if (pageHolderVC.mOkSendDenom == OKEX_MAIN_DENOM) {
                maxAvailable = pageHolderVC.mAccount!.getTokenBalance(OKEX_MAIN_DENOM).subtracting(NSDecimalNumber.init(string: "0.002"))
                mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 0, mDpDecimal)
                
            } else {
                self.denomTitleLabel.textColor = UIColor.white
                maxAvailable = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mOkSendDenom!)
                mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 0, mDpDecimal)
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            mDpDecimal = 6
            maxAvailable = pageHolderVC.mAccount!.getTokenBalance(CERTIK_MAIN_DENOM).subtracting(NSDecimalNumber.init(string: "5000"))
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            mDpDecimal = 6
            maxAvailable = pageHolderVC.mAccount!.getTokenBalance(AKASH_MAIN_DENOM).subtracting(NSDecimalNumber.init(string: "2500"))
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        }
        
        
        else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
            mDpDecimal = 6
            if (pageHolderVC.mCosmosSendDenom == COSMOS_MAIN_DENOM) {
                maxAvailable = BaseData.instance.getAvailable(pageHolderVC.mCosmosSendDenom!).subtracting(NSDecimalNumber.init(string: "2500"))
            } else {
                maxAvailable = BaseData.instance.getAvailable(pageHolderVC.mCosmosSendDenom!)
            }
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            mDpDecimal = 6
            if (pageHolderVC.mCosmosSendDenom == COSMOS_TEST_DENOM) {
                maxAvailable = BaseData.instance.getAvailable(pageHolderVC.mCosmosSendDenom!).subtracting(NSDecimalNumber.init(string: "2500"))
            } else {
                maxAvailable = BaseData.instance.getAvailable(pageHolderVC.mCosmosSendDenom!)
            }
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            mDpDecimal = pageHolderVC.mIrisTokenV1!.scale!
            if (pageHolderVC.mIrisTokenV1?.min_unit == IRIS_TEST_DENOM) {
                maxAvailable = BaseData.instance.getAvailable(pageHolderVC.mIrisTokenV1!.min_unit!).subtracting(NSDecimalNumber.init(string: "2500"))
            } else {
                maxAvailable = BaseData.instance.getAvailable(pageHolderVC.mIrisTokenV1!.min_unit!)
            }
            mAvailableAmountLabel.attributedText = WUtils.displayAmount2(maxAvailable.stringValue, mAvailableAmountLabel.font, 6, mDpDecimal)
            
        }
        
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
        
        let userInput = WUtils.localeStringToDecimal(text)
        
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        } else if (pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
                return
            }
        }
        self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
    }
    
    func isValiadAmount() -> Bool {
        let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (pageHolderVC.mBnbToken?.type == BNB_TOKEN_TYPE_MINI) {
                if ((userInput.compare(NSDecimalNumber.one).rawValue < 0) && (userInput.compare(maxAvailable).rawValue != 0)) {
                    self.onShowToast(NSLocalizedString("error_bnb_mini_amount", comment: ""))
                    return false
                }
            }
            if (userInput.compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            if (userInput.compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            if (userInput.multiplying(byPowerOf10: mDpDecimal).compare(maxAvailable).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
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
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces))!)
            var toSendCoin:Coin?
            if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                toSendCoin = Coin.init(IRIS_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mBnbToken!.symbol, userInput.stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mKavaSendDenom!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mIovSendDenom!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                toSendCoin = Coin.init(BAND_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                toSendCoin = Coin.init(SECRET_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mOkSendDenom!, WUtils.getFormattedNumber(userInput, mDpDecimal))
                
            } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                toSendCoin = Coin.init(CERTIK_MAIN_DENOM, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                toSendCoin = Coin.init(pageHolderVC.mAkashSendDenom!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            }
            
            
            else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
                toSendCoin = Coin.init(pageHolderVC.mCosmosSendDenom!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mCosmosSendDenom!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            } else if (pageHolderVC.chainType! == ChainType.IRIS_TEST) {
                toSendCoin = Coin.init(pageHolderVC.mIrisTokenV1!.min_unit!, userInput.multiplying(byPowerOf10: mDpDecimal).stringValue)
                
            }
            
            var tempList = Array<Coin>()
            tempList.append(toSendCoin!)
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
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
        
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if (mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, mDpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2), withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2), withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, mDpDecimal)
            
        }
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            if (pageHolderVC.mCosmosSendDenom == COSMOS_MAIN_DENOM || pageHolderVC.mCosmosSendDenom == COSMOS_TEST_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            self.showMaxWarnning()
            
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxAvailable, mDpDecimal)
            if (pageHolderVC.mBnbToken?.symbol == BNB_MAIN_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            if (pageHolderVC.mIovSendDenom == IOV_MAIN_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            if (pageHolderVC.mSecretSendDenom == SECRET_MAIN_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxAvailable, mDpDecimal)
            if (pageHolderVC.mOkSendDenom == OKEX_MAIN_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            if (pageHolderVC.mCertikSendDenom == CERTIK_MAIN_DENOM) {
                self.showMaxWarnning()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            if (pageHolderVC.mAkashSendDenom == AKASH_MAIN_DENOM) {
                self.showMaxWarnning()
            }
        } else if (pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            let maxValue = maxAvailable.multiplying(byPowerOf10: -mDpDecimal, withBehavior: WUtils.getDivideHandler(mDpDecimal))
            mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, mDpDecimal)
            if (pageHolderVC.mIrisTokenV1!.min_unit! == IRIS_TEST_DENOM) {
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
