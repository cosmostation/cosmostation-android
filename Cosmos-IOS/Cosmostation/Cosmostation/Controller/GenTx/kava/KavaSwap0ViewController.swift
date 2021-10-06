//
//  KavaSwap0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/29.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class KavaSwap0ViewController: BaseViewController, UITextFieldDelegate{
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var inputCoinImg: UIImageView!
    @IBOutlet weak var inputCoinName: UILabel!
    @IBOutlet weak var inputCoinAvailableLabel: UILabel!
    @IBOutlet weak var inputCoinAvailableDenomLabel: UILabel!
    @IBOutlet weak var inputTextFiled: AmountInputTextField!
    @IBOutlet weak var outputCoinImg: UIImageView!
    @IBOutlet weak var outputCoinName: UILabel!
    @IBOutlet weak var outputCoinAmountLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mPool: SwapPool!
    var availableMaxAmount = NSDecimalNumber.zero
    var dpInPutDecimal:Int16 = 6
    var dpOutPutDecimal:Int16 = 6
    var mInputCoinAmount = NSDecimalNumber.zero
    var mOutputCoinAmount = NSDecimalNumber.zero
    var swapRate = NSDecimalNumber.one


    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        inputTextFiled.delegate = self
        inputTextFiled.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        loadingImg.startAnimating()
        onFetchSwapPool(pageHolderVC.mKavaPool!.name!)
    }
    
    func onInitView() {
        self.loadingImg.stopAnimating()
        self.loadingImg.isHidden = true
        
        let inputCoinDenom = pageHolderVC.mSwapInDenom!
        let outputCoinDenom = pageHolderVC.mSwapOutDenom!
        
        if (mPool.coins[0].denom == inputCoinDenom) {
            mInputCoinAmount = NSDecimalNumber.init(string: mPool.coins[0].amount)
            mOutputCoinAmount = NSDecimalNumber.init(string: mPool.coins[1].amount)
        } else {
            mInputCoinAmount = NSDecimalNumber.init(string: mPool.coins[1].amount)
            mOutputCoinAmount = NSDecimalNumber.init(string: mPool.coins[0].amount)
        }
        
        dpInPutDecimal = WUtils.getKavaCoinDecimal(inputCoinDenom)
        dpOutPutDecimal = WUtils.getKavaCoinDecimal(outputCoinDenom)
        
        availableMaxAmount = BaseData.instance.availableAmount(inputCoinDenom)
        WUtils.showCoinDp(inputCoinDenom, availableMaxAmount.stringValue, inputCoinAvailableDenomLabel, inputCoinAvailableLabel, chainType!)
        
        swapRate = mOutputCoinAmount.dividing(by: mInputCoinAmount, withBehavior: WUtils.handler18)
        print("swapRate ", swapRate)
        
        WUtils.DpKavaTokenName(inputCoinName, inputCoinDenom)
        WUtils.DpKavaTokenName(outputCoinName, outputCoinDenom)
        inputCoinImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + inputCoinDenom + ".png")!)
        outputCoinImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + outputCoinDenom + ".png")!)
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
            if(text.substring(from: index).count > (dpInPutDecimal - 1) && range.length == 0) { return false }
        }
        if let index = text.range(of: ",")?.upperBound {
            if(text.substring(from: index).count > (dpInPutDecimal - 1) && range.length == 0) { return false }
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == inputTextFiled) {
            self.onUIupdate()
        }
    }
    func onUIupdate() {
        guard let text = inputTextFiled.text?.trimmingCharacters(in: .whitespaces) else {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (text.count == 0) {
            inputTextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.compare(NSDecimalNumber.init(string: "0.01")).rawValue < 0) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: dpInPutDecimal).compare(availableMaxAmount).rawValue > 0) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        inputTextFiled.layer.borderColor = UIColor.white.cgColor
        
        let padding = (NSDecimalNumber.one).subtracting(BaseData.instance.mKavaSwapParam.swap_fee).subtracting(NSDecimalNumber.init(string: "0.03"))
        let outputAmount = userInput.multiplying(byPowerOf10: dpInPutDecimal).multiplying(by: padding).multiplying(by: swapRate, withBehavior: WUtils.handler0)
        outputCoinAmountLabel.text = outputAmount.multiplying(byPowerOf10: -dpOutPutDecimal).stringValue
     
    }
    
    @IBAction func onClickClear(_ sender: UIButton) {
        self.inputTextFiled.text = ""
        self.onUIupdate()
    }
    
    @IBAction func onClick1_4(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -dpInPutDecimal, withBehavior: WUtils.getDivideHandler(dpInPutDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, dpInPutDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let calValue = availableMaxAmount.dividing(by: NSDecimalNumber(2)).multiplying(byPowerOf10: -dpInPutDecimal, withBehavior: WUtils.getDivideHandler(dpInPutDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, dpInPutDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick3_4(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -dpInPutDecimal, withBehavior: WUtils.getDivideHandler(dpInPutDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, dpInPutDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = availableMaxAmount.multiplying(byPowerOf10: -dpInPutDecimal, withBehavior: WUtils.getDivideHandler(dpInPutDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(maxValue, dpInPutDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((inputTextFiled.text?.trimmingCharacters(in: .whitespaces))!)
            pageHolderVC.mSwapInAmount = userInput.multiplying(byPowerOf10: dpInPutDecimal)
            let userOutput = WUtils.localeStringToDecimal((outputCoinAmountLabel.text?.trimmingCharacters(in: .whitespaces))!)
            pageHolderVC.mSwapOutAmount = userOutput.multiplying(byPowerOf10: dpOutPutDecimal)
            pageHolderVC.mKavaPool = self.mPool
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = inputTextFiled.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: dpInPutDecimal).compare(availableMaxAmount).rawValue > 0) {
            return false
        }
        return true
    }

    
    func onFetchSwapPool(_ poolId: String) {
        let request = Alamofire.request(BaseNetWork.swapPoolUrl(chainType), method: .get, parameters: ["pool":poolId], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSwapPool ", res)
                guard let responseData = res as? NSDictionary,
                      let responseResult = responseData.object(forKey: "result") as? NSDictionary,
                      let _ = responseData.object(forKey: "height") as? String  else {
                    self.pageHolderVC.onBeforePage()
                    return
                }
                self.mPool = SwapPool.init(responseResult)
                self.onInitView()
                
            case .failure(let error):
                print("onFetchSwapPool ", error)
            }
        }
    }
}
