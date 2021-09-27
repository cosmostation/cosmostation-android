//
//  IBCSend2ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/24.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class IBCSend2ViewController: BaseViewController, UITextFieldDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var mTargetAmountTextField: AmountInputTextField!
    @IBOutlet weak var mAvailableAmountLabel: UILabel!
    @IBOutlet weak var denomTitleLabel: UILabel!
    @IBOutlet weak var btn01: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var ibcSendDenom: String!
    var maxAvailable = NSDecimalNumber.zero
    var decimal:Int16 = 6

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.ibcSendDenom = self.pageHolderVC.mIBCSendDenom
        self.decimal = WUtils.tokenDivideDecimal(chainType, ibcSendDenom)
        
        let mainDenom = WUtils.getMainDenom(chainType!)
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, TASK_IBC_TRANSFER, 0)
        
        maxAvailable = BaseData.instance.getAvailableAmount_gRPC(ibcSendDenom)
        if (ibcSendDenom == mainDenom) {
            maxAvailable = maxAvailable.subtracting(feeAmount)
        }
        WUtils.showCoinDp(ibcSendDenom, maxAvailable.stringValue, denomTitleLabel, mAvailableAmountLabel, chainType!)
        
        mTargetAmountTextField.delegate = self
        mTargetAmountTextField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        let dp = "+ " + WUtils.decimalNumberToLocaleString(NSDecimalNumber(string: "0.1"), 1)
        btn01.setTitle(dp, for: .normal)
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (textField == mTargetAmountTextField) {
            guard let text = textField.text else { return true }
            if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
            if (text.count == 0 && string.starts(with: ".")) { return false }
            if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
            if (text.count == 0 && string.starts(with: ",")) { return false }
            if let index = text.range(of: ".")?.upperBound {
                if (String(text[index...]).count > (decimal - 1) && range.length == 0) {
                    return false
                }
            }
            if let index = text.range(of: ",")?.upperBound {
                if (String(text[index...]).count > (decimal - 1) && range.length == 0) {
                    return false
                }
            }
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == mTargetAmountTextField) {
            onUpdateView()
        }
    }
    
    func onUpdateView() {
        guard let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces) else {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        
        if (text.count == 0) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: decimal).compare(maxAvailable).rawValue > 0) {
            self.mTargetAmountTextField.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        self.mTargetAmountTextField.layer.borderColor = UIColor.white.cgColor
    }
    
    @IBAction func onClickClear(_ sender: UIButton) {
        mTargetAmountTextField.text = ""
        self.onUpdateView()
    }
    
    @IBAction func onClickAdd01(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "0.1"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, decimal)
        self.onUpdateView()
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if (mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "1"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, decimal)
        self.onUpdateView()
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "10"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, decimal)
        self.onUpdateView()
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
        var exist = NSDecimalNumber.zero
        if(mTargetAmountTextField.text!.count > 0) {
            exist = NSDecimalNumber(string: mTargetAmountTextField.text!, locale: Locale.current)
        }
        let added = exist.adding(NSDecimalNumber(string: "100"))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(added, decimal)
        self.onUpdateView()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let halfValue = maxAvailable.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -decimal, withBehavior: WUtils.getDivideHandler(decimal))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(halfValue, decimal)
        self.onUpdateView()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = maxAvailable.multiplying(byPowerOf10: -decimal, withBehavior: WUtils.getDivideHandler(decimal))
        mTargetAmountTextField.text = WUtils.decimalNumberToLocaleString(maxValue, decimal)
        if (ibcSendDenom == WUtils.getMainDenom(chainType!)) {
            self.showMaxWarnning()
        }
        self.onUpdateView()
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces))!)
            pageHolderVC.mIBCSendAmount = userInput.multiplying(byPowerOf10: decimal).stringValue
            
            self.btnBack.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = mTargetAmountTextField.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: decimal).compare(maxAvailable).rawValue > 0) { return false }
        return true
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
