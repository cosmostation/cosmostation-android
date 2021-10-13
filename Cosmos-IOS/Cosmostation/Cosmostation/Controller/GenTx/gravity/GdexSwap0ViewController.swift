//
//  GdexSwap0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class GdexSwap0ViewController: BaseViewController, UITextFieldDelegate {
    
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
    
    var channel: ClientConnection!
    var pageHolderVC: StepGenTxViewController!
    
    var selectedPool: Tendermint_Liquidity_V1beta1_Pool!
    var selectedPoolManager: GDexManager!
    var selectedPoolSupply: Coin!
    var availableMaxAmount = NSDecimalNumber.zero
    var swapInDenom = ""
    var swapOutDenom = ""
    var dpInPutDecimal:Int16 = 6
    var dpOutPutDecimal:Int16 = 6
    var swapRate = NSDecimalNumber.one
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.selectedPool = BaseData.instance.getGravityPoolById(pageHolderVC.mPoolId!)
        self.swapInDenom = pageHolderVC.mSwapInDenom!
        self.swapOutDenom = pageHolderVC.mSwapOutDenom!
        self.dpInPutDecimal = WUtils.getCosmosCoinDecimal(swapInDenom)
        self.dpOutPutDecimal = WUtils.getCosmosCoinDecimal(swapOutDenom)
        
        inputTextFiled.delegate = self
        inputTextFiled.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        loadingImg.startAnimating()
        onFetchDexData()
    }
    
    func onInitView() {
        availableMaxAmount = BaseData.instance.getAvailableAmount_gRPC(pageHolderVC.mSwapInDenom!)
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(pageHolderVC.chainType!, LIQUIDITY_MSG_TYPE_SWAP, 0)
        if (swapInDenom == COSMOS_MAIN_DENOM) {
            availableMaxAmount = availableMaxAmount.subtracting(txFeeAmount)
        }
        availableMaxAmount = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.9985"), withBehavior: WUtils.handler6)
        
        WUtils.showCoinDp(swapInDenom, availableMaxAmount.stringValue, inputCoinAvailableDenomLabel, inputCoinAvailableLabel, chainType!)
        WUtils.DpCosmosTokenImg(inputCoinImg, swapInDenom)
        WUtils.DpCosmosTokenName(inputCoinName, swapInDenom)
        WUtils.DpCosmosTokenImg(outputCoinImg, swapOutDenom)
        WUtils.DpCosmosTokenName(outputCoinName, swapOutDenom)
        
        
        let coin0TotalLpAmount = NSDecimalNumber.init(string: selectedPoolManager.reserve.filter {$0.denom == swapInDenom }.first?.amount)
        let coin1TotalLpAmount = NSDecimalNumber.init(string: selectedPoolManager.reserve.filter {$0.denom == swapOutDenom }.first?.amount)
        swapRate = coin1TotalLpAmount.dividing(by: coin0TotalLpAmount, withBehavior: WUtils.handler8).multiplying(byPowerOf10: (dpInPutDecimal - dpOutPutDecimal))
        
        self.loadingImg.stopAnimating()
        self.loadingImg.isHidden = true
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
        if (userInput.multiplying(byPowerOf10: dpInPutDecimal).compare(availableMaxAmount).rawValue > 0) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        inputTextFiled.layer.borderColor = UIColor.white.cgColor
        
        let padding = NSDecimalNumber(string: "0.97")
        let outputAmount = userInput.multiplying(byPowerOf10: dpInPutDecimal - dpOutPutDecimal).multiplying(by: padding).multiplying(by: swapRate, withBehavior: WUtils.handler18)
        outputCoinAmountLabel.text = WUtils.decimalNumberToLocaleString(outputAmount, dpOutPutDecimal)
        
     
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
            pageHolderVC.mGDexPool = selectedPool
            pageHolderVC.mGDexPoolManager = selectedPoolManager
            pageHolderVC.mGDexPoolSupply = selectedPoolSupply
            let coin0Amount = NSDecimalNumber.init(string: selectedPoolManager.reserve[0].amount)
            let coin1Amount = NSDecimalNumber.init(string: selectedPoolManager.reserve[1].amount)
            var padding = NSDecimalNumber.one
            if (swapInDenom == selectedPoolManager.reserve[0].denom) {
                padding = NSDecimalNumber.init(string: "1.03")
            } else if (swapInDenom == selectedPoolManager.reserve[1].denom) {
                padding = NSDecimalNumber.init(string: "0.97")
            }
            let orderPrice = coin0Amount.multiplying(by: padding).dividing(by: coin1Amount, withBehavior: WUtils.handler18)
            print("orderPrice ", orderPrice)
            pageHolderVC.mGDexSwapOrderPrice = orderPrice.multiplying(byPowerOf10: 18)
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = inputTextFiled.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: dpInPutDecimal).compare(availableMaxAmount).rawValue > 0) { return false }
        return true
    }
    
    
    var mFetchCnt = 0
    func onFetchDexData() {
        self.mFetchCnt = 2
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 5)
            defer { try! group.syncShutdownGracefully() }
            
            self.channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! self.channel.close().wait() }
            
            self.onFetchGdexPoolManager(self.selectedPool.reserveAccountAddress)
            self.onFetchSupply(self.selectedPool.poolCoinDenom)
        }
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt > 0) { return }
        
        DispatchQueue.global().async {
            try? self.channel.close().wait()
            DispatchQueue.main.async(execute: {
                self.onInitView()
            });
        }
    }
    
    func onFetchGdexPoolManager(_ address: String) {
        let req = Cosmos_Bank_V1beta1_QueryAllBalancesRequest.with { $0.address = address }
        if let response = try? Cosmos_Bank_V1beta1_QueryClient(channel: self.channel).allBalances(req, callOptions: BaseNetWork.getCallOptions()).response.wait() {
            self.selectedPoolManager = GDexManager.init(address, response.balances)
        }
        self.onFetchFinished()
    }
    
    func onFetchSupply(_ denom: String) {
        let req = Cosmos_Bank_V1beta1_QuerySupplyOfRequest.with { $0.denom = denom }
        if let response = try? Cosmos_Bank_V1beta1_QueryClient(channel: self.channel).supplyOf(req, callOptions: BaseNetWork.getCallOptions()).response.wait() {
            self.selectedPoolSupply = Coin.init(response.amount.denom, response.amount.amount)
        }
        self.onFetchFinished()
    }
}
