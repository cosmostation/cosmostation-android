//
//  HardPoolDeposit0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class HardPoolDeposit0ViewController: BaseViewController, UITextFieldDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var btn01: UIButton!
    
    @IBOutlet weak var mCoinImg: UIImageView!
    @IBOutlet weak var mCoinLabel: UILabel!
    @IBOutlet weak var mUserInput: AmountInputTextField!
    @IBOutlet weak var mAvailabeLabel: UILabel!
    @IBOutlet weak var mAvailabeDenom: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var hardPoolDenom: String = ""
    var availableMax = NSDecimalNumber.zero
    var dpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        
        pageHolderVC = self.parent as? StepGenTxViewController
        hardPoolDenom = pageHolderVC.mHardPoolDenom!
        dpDecimal = WUtils.getKavaCoinDecimal(hardPoolDenom)
        availableMax = account!.getTokenBalance(hardPoolDenom)
        
        if (hardPoolDenom == KAVA_MAIN_DENOM) {
            WUtils.setDenomTitle(chainType!, mCoinLabel)
        } else if (hardPoolDenom == KAVA_HARD_DENOM) {
            self.mCoinLabel.textColor = COLOR_HARD
            self.mCoinLabel.text = hardPoolDenom.uppercased()
        } else {
            self.mCoinLabel.textColor = .white
            self.mCoinLabel.text = hardPoolDenom.uppercased()
        }
        WUtils.showCoinDp(hardPoolDenom, availableMax.stringValue, mAvailabeDenom, mAvailabeLabel, chainType!)
        self.mCoinImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + hardPoolDenom + ".png")!)
        self.loadingImg.isHidden = true
        
        let dp = "+ " + WUtils.decimalNumberToLocaleString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
        mUserInput.delegate = self
        mUserInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let text = textField.text else { return true }
        if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ".")) { return false }
        if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ",")) { return false }
        if let index = text.range(of: ".")?.upperBound {
            if(text.substring(from: index).count > (dpDecimal - 1) && range.length == 0) { return false }
        }
        if let index = text.range(of: ",")?.upperBound {
            if(text.substring(from: index).count > (dpDecimal - 1) && range.length == 0) { return false }
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == mUserInput) {
            self.onUIupdate()
        }
    }
    
    func onUIupdate() {
        guard let text = mUserInput.text?.trimmingCharacters(in: .whitespaces) else {
            self.mUserInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if(text.count == 0) {
            self.mUserInput.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.mUserInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: dpDecimal).compare(availableMax).rawValue > 0) {
            self.mUserInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.mUserInput.layer.borderColor = UIColor.white.cgColor
    }
    
    @IBAction func onClickAmountClear(_ sender: UIButton) {
        self.mUserInput.text = ""
        self.onUIupdate()
    }
    
    @IBAction func onClickOne(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if (mUserInput.text!.count > 0) {
            exist = NSDecimalNumber(string: mUserInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        mUserInput.text = WUtils.decimalNumberToLocaleString(added, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick1_4(_ sender: UIButton) {
        let calValue = availableMax.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        mUserInput.text = WUtils.decimalNumberToLocaleString(calValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let calValue = availableMax.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        mUserInput.text = WUtils.decimalNumberToLocaleString(calValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick3_4(_ sender: UIButton) {
        let calValue = availableMax.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        mUserInput.text = WUtils.decimalNumberToLocaleString(calValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = availableMax.multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        mUserInput.text = WUtils.decimalNumberToLocaleString(maxValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((mUserInput.text?.trimmingCharacters(in: .whitespaces))!)
            let resultCoin = Coin.init(hardPoolDenom, userInput.multiplying(byPowerOf10: dpDecimal).stringValue)
            var resultCoins = Array<Coin>()
            resultCoins.append(resultCoin)
            pageHolderVC.mHardPoolCoins = resultCoins
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = mUserInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: dpDecimal).compare(availableMax).rawValue > 0) {
            return false
        }
        return true
    }
}
