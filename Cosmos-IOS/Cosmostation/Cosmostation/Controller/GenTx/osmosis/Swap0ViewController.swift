//
//  Swap0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/12.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class Swap0ViewController: BaseViewController, UITextFieldDelegate {
    
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
    var availableMaxAmount = NSDecimalNumber.zero
    var dpInPutDecimal:Int16 = 6
    var dpOutPutDecimal:Int16 = 6
    var swapRate = NSDecimalNumber.one
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        pageHolderVC = self.parent as? StepGenTxViewController
        inputTextFiled.delegate = self
        inputTextFiled.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        loadingImg.startAnimating()
        onFetchGammPool(pageHolderVC.mPoolId!)
    }
    
    func onInitView() {
        availableMaxAmount = BaseData.instance.getAvailableAmount_gRPC(pageHolderVC.mSwapInDenom!)
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, OSMOSIS_MSG_TYPE_SWAP, 0)
        print("availableMaxAmount ", availableMaxAmount)
        print("txFeeAmount ", txFeeAmount)
        if (pageHolderVC.mSwapInDenom == OSMOSIS_MAIN_DENOM) {
            availableMaxAmount = availableMaxAmount.subtracting(txFeeAmount)
        }
        WUtils.showCoinDp(pageHolderVC.mSwapInDenom!, availableMaxAmount.stringValue, inputCoinAvailableDenomLabel, inputCoinAvailableLabel, chainType!)
        WUtils.DpOsmosisTokenImg(inputCoinImg, pageHolderVC.mSwapInDenom!)
        WUtils.DpOsmosisTokenName(inputCoinName, pageHolderVC.mSwapInDenom!)
        WUtils.DpOsmosisTokenImg(outputCoinImg, pageHolderVC.mSwapOutDenom!)
        WUtils.DpOsmosisTokenName(outputCoinName, pageHolderVC.mSwapOutDenom!)
        
        dpInPutDecimal = WUtils.getOsmosisCoinDecimal(pageHolderVC.mSwapInDenom)
        dpOutPutDecimal = WUtils.getOsmosisCoinDecimal(pageHolderVC.mSwapOutDenom)
        
        var inputAssetAmount = NSDecimalNumber.zero
        var inputAssetWeight = NSDecimalNumber.zero
        var outputAssetAmount = NSDecimalNumber.zero
        var outputAssetWeight = NSDecimalNumber.zero
        pageHolderVC.mPool!.poolAssets.forEach { poolAsset in
            if (poolAsset.token.denom == pageHolderVC.mSwapInDenom) {
                inputAssetAmount = NSDecimalNumber.init(string: poolAsset.token.amount)
                inputAssetWeight = NSDecimalNumber.init(string: poolAsset.weight)
            }
            if (poolAsset.token.denom == pageHolderVC.mSwapOutDenom) {
                outputAssetAmount = NSDecimalNumber.init(string: poolAsset.token.amount)
                outputAssetWeight = NSDecimalNumber.init(string: poolAsset.weight)
            }
        }
        swapRate = outputAssetAmount.multiplying(by: inputAssetWeight).dividing(by: inputAssetAmount, withBehavior: WUtils.handler18).dividing(by: outputAssetWeight, withBehavior: WUtils.handler18)
        print("swapRate ", swapRate)
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
        
        let padding = NSDecimalNumber(string: "0.97")
        let outputAmount = userInput.multiplying(byPowerOf10: dpInPutDecimal).multiplying(by: padding).multiplying(by: swapRate, withBehavior: WUtils.handler0)
        print("outputAmount ", outputAmount)
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
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = inputTextFiled.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.compare(NSDecimalNumber.init(string: "0.01")).rawValue < 0) {
            self.onShowToast("Please enter 0.01 or higher")
            return false
        }
        if (userInput.multiplying(byPowerOf10: dpInPutDecimal).compare(availableMaxAmount).rawValue > 0) {
            return false
        }
        return true
    }
    
    
    
    func onFetchGammPool(_ poolId: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Osmosis_Gamm_V1beta1_QueryPoolRequest.with {
                    $0.poolID = UInt64(poolId)!
                }
                let response = try Osmosis_Gamm_V1beta1_QueryClient(channel: channel).pool(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                self.pageHolderVC.mPool = try! Osmosis_Gamm_V1beta1_Pool.init(serializedData: response.pool.value)
                
                
            } catch {
                print("onFetchGammPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.loadingImg.stopAnimating()
                self.loadingImg.isHidden = true
                self.onInitView()
            });
        }
    }
}
