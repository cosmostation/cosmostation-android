//
//  StepWithdrawHarvestAmountViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepWithdrawHarvestAmountViewController: BaseViewController, UITextFieldDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var btn01: UIButton!
    
    @IBOutlet weak var withdrawCoinImg: UIImageView!
    @IBOutlet weak var withdrawCoinLabel: UILabel!
    @IBOutlet weak var toWithdrawInput: AmountInputTextField!
    @IBOutlet weak var withdrawAvailabeLabel: UILabel!
    @IBOutlet weak var withdrawAvailabeDenom: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mHarvestDepositDenom: String = ""
    var availabeMax = NSDecimalNumber.zero
    var dpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        
        pageHolderVC = self.parent as? StepGenTxViewController
        mHarvestDepositDenom = pageHolderVC.mHarvestDepositDenom!
        dpDecimal = WUtils.getKavaCoinDecimal(mHarvestDepositDenom)
        
        let myHavestDeposit = BaseData.instance.mHavestDeposits.filter({ $0.amount.denom == mHarvestDepositDenom}).first
        if let depositedCoin = myHavestDeposit?.amount {
            availabeMax = NSDecimalNumber.init(string: depositedCoin.amount)
        }
        //TODO check or not for availabeMax zero
        
        if (mHarvestDepositDenom == KAVA_MAIN_DENOM) {
            WUtils.setDenomTitle(chainType!, self.withdrawCoinLabel)
        } else if (mHarvestDepositDenom == KAVA_HARD_DENOM) {
            self.withdrawCoinLabel.textColor = COLOR_HARD
            self.withdrawCoinLabel.text = mHarvestDepositDenom.uppercased()
        } else {
            self.withdrawCoinLabel.textColor = .white
            self.withdrawCoinLabel.text = mHarvestDepositDenom.uppercased()
        }
        WUtils.showCoinDp(mHarvestDepositDenom, availabeMax.stringValue, withdrawAvailabeDenom, withdrawAvailabeLabel, chainType!)
        self.withdrawCoinImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mHarvestDepositDenom + ".png")!)
        self.loadingImg.isHidden = true
        
        let dp = "+ " + WUtils.decimalNumberToLocaleString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
        toWithdrawInput.delegate = self
        toWithdrawInput.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
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
        if (textField == toWithdrawInput) {
            self.onUIupdate()
        }
    }
    
    func onUIupdate() {
        guard let text = toWithdrawInput.text?.trimmingCharacters(in: .whitespaces) else {
            self.toWithdrawInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if(text.count == 0) {
            self.toWithdrawInput.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.toWithdrawInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: dpDecimal).compare(availabeMax).rawValue > 0) {
            self.toWithdrawInput.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.toWithdrawInput.layer.borderColor = UIColor.white.cgColor
    }
    
    @IBAction func onClickAmountClear(_ sender: UIButton) {
        self.toWithdrawInput.text = ""
        self.onUIupdate()
    }
    
    @IBAction func onClickOne(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if (toWithdrawInput.text!.count > 0) {
            exist = NSDecimalNumber(string: toWithdrawInput.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        toWithdrawInput.text = WUtils.decimalNumberToLocaleString(added, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick1_4(_ sender: UIButton) {
        let calValue = availabeMax.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        toWithdrawInput.text = WUtils.decimalNumberToLocaleString(calValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let calValue = availabeMax.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        toWithdrawInput.text = WUtils.decimalNumberToLocaleString(calValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick3_4(_ sender: UIButton) {
        let calValue = availabeMax.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        toWithdrawInput.text = WUtils.decimalNumberToLocaleString(calValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = availabeMax.multiplying(byPowerOf10: -dpDecimal, withBehavior: WUtils.getDivideHandler(dpDecimal))
        toWithdrawInput.text = WUtils.decimalNumberToLocaleString(maxValue, dpDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((toWithdrawInput.text?.trimmingCharacters(in: .whitespaces))!)
            let depositCoin = Coin.init(mHarvestDepositDenom, userInput.multiplying(byPowerOf10: dpDecimal).stringValue)
            pageHolderVC.mHarvestCoin = depositCoin
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = toWithdrawInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: dpDecimal).compare(availabeMax).rawValue > 0) {
            return false
        }
        return true
    }

}
