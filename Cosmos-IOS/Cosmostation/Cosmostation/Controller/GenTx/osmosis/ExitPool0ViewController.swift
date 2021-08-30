//
//  ExitPool0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/19.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class ExitPool0ViewController: BaseViewController, UITextFieldDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var lpCoinImg: UIImageView!
    @IBOutlet weak var lpCoinName: UILabel!
    @IBOutlet weak var lpAvailableLabel: UILabel!
    @IBOutlet weak var lpAvailableDenomLabel: UILabel!
    @IBOutlet weak var inputTextFiled: AmountInputTextField!
    
    var pageHolderVC: StepGenTxViewController!
    var availableMaxAmount = NSDecimalNumber.zero
    var coinDecimal:Int16 = 18

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
        availableMaxAmount = BaseData.instance.getAvailableAmount_gRPC(pageHolderVC.mPool!.totalShares.denom)
        WUtils.showCoinDp(pageHolderVC.mPool!.totalShares.denom, availableMaxAmount.stringValue, lpAvailableDenomLabel, lpAvailableLabel, chainType!)
        WUtils.DpOsmosisTokenImg(lpCoinImg, pageHolderVC.mPool!.totalShares.denom)
        WUtils.DpOsmosisTokenName(lpCoinName, pageHolderVC.mPool!.totalShares.denom)
        
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let text = textField.text else { return true }
        if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ".")) { return false }
        if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ",")) { return false }
        if (textField == inputTextFiled) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (coinDecimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (coinDecimal - 1) && range.length == 0) { return false }
            }
            
        } else if (textField == inputTextFiled) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (coinDecimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (coinDecimal - 1) && range.length == 0) { return false }
            }
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
        if (userInput.compare(NSDecimalNumber.zero).rawValue <= 0) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: coinDecimal).compare(availableMaxAmount).rawValue > 0) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        inputTextFiled.layer.borderColor = UIColor.white.cgColor
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickClear(_ sender: UIButton) {
        self.inputTextFiled.text = ""
        self.onUIupdate()
    }
    
    @IBAction func onClick1_4(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -coinDecimal, withBehavior: WUtils.getDivideHandler(coinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.5")).multiplying(byPowerOf10: -coinDecimal, withBehavior: WUtils.getDivideHandler(coinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick3_4(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -coinDecimal, withBehavior: WUtils.getDivideHandler(coinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = availableMaxAmount.multiplying(byPowerOf10: -coinDecimal, withBehavior: WUtils.getDivideHandler(coinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(maxValue, coinDecimal)
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
            let paybacklpAmount = userInput.multiplying(byPowerOf10: coinDecimal)
            pageHolderVC.mLPCoin = Coin.init(pageHolderVC.mPool!.totalShares.denom, paybacklpAmount.stringValue)
            
            //Expected receiveing pool tokens!!
            let coin0Denom = pageHolderVC.mPool!.poolAssets[0].token.denom
            let coin1Denom = pageHolderVC.mPool!.poolAssets[1].token.denom
            let coin0Amount = NSDecimalNumber.init(string: pageHolderVC.mPool!.poolAssets[0].token.amount)
            let coin1Amount = NSDecimalNumber.init(string: pageHolderVC.mPool!.poolAssets[1].token.amount)
            let poolTotalShare = NSDecimalNumber.init(string: pageHolderVC.mPool!.totalShares.amount)
            
            let coin0PaybackAmount = coin0Amount.multiplying(by: paybacklpAmount).dividing(by: poolTotalShare, withBehavior: WUtils.handler0).multiplying(by: NSDecimalNumber.init(string: "0.975"), withBehavior: WUtils.handler0)
            let coin1PaybackAmount = coin1Amount.multiplying(by: paybacklpAmount).dividing(by: poolTotalShare, withBehavior: WUtils.handler0).multiplying(by: NSDecimalNumber.init(string: "0.975"), withBehavior: WUtils.handler0)
            
            pageHolderVC.mPoolCoin0 = Coin.init(coin0Denom, coin0PaybackAmount.stringValue)
            pageHolderVC.mPoolCoin1 = Coin.init(coin1Denom, coin1PaybackAmount.stringValue)
            
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    
    func isValiadAmount() -> Bool {
        let text = inputTextFiled.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.compare(NSDecimalNumber.zero).rawValue < 0) { return false }
        if (userInput.multiplying(byPowerOf10: coinDecimal).compare(availableMaxAmount).rawValue > 0) { return false }
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
