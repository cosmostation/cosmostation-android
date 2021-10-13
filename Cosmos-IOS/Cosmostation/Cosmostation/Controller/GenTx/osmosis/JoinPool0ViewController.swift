//
//  JoinPool0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/19.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class JoinPool0ViewController: BaseViewController, UITextFieldDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var inputCoin0Img: UIImageView!
    @IBOutlet weak var inputCoin0Name: UILabel!
    @IBOutlet weak var inputCoin0AvailableLabel: UILabel!
    @IBOutlet weak var inputCoin0AvailableDenomLabel: UILabel!
    @IBOutlet weak var input0TextFiled: AmountInputTextField!
    @IBOutlet weak var inputCoin1Img: UIImageView!
    @IBOutlet weak var inputCoin1Name: UILabel!
    @IBOutlet weak var inputCoin1AvailableLabel: UILabel!
    @IBOutlet weak var inputCoin1AvailableDenomLabel: UILabel!
    @IBOutlet weak var input1TextFiled: AmountInputTextField!
    
    var pageHolderVC: StepGenTxViewController!
    var available0MaxAmount = NSDecimalNumber.zero
    var available1MaxAmount = NSDecimalNumber.zero
    var coin0Decimal:Int16 = 6
    var coin1Decimal:Int16 = 6
    var depositRate = NSDecimalNumber.one

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        pageHolderVC = self.parent as? StepGenTxViewController
        input0TextFiled.delegate = self
        input0TextFiled.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        input1TextFiled.delegate = self
        input1TextFiled.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        loadingImg.startAnimating()
        onFetchGammPool(pageHolderVC.mPoolId!)
    }
    
    func onInitView() {
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(chainType!, OSMOSIS_MSG_TYPE_JOIN_POOL, 0)
        let coin0Denom = pageHolderVC.mPool!.poolAssets[0].token.denom
        let coin1Denom = pageHolderVC.mPool!.poolAssets[1].token.denom
        
        available0MaxAmount = BaseData.instance.getAvailableAmount_gRPC(coin0Denom)
        if (coin0Denom == OSMOSIS_MAIN_DENOM) {
            available0MaxAmount = available0MaxAmount.subtracting(txFeeAmount)
        }
        available1MaxAmount = BaseData.instance.getAvailableAmount_gRPC(coin1Denom)
        if (coin1Denom == OSMOSIS_MAIN_DENOM) {
            available1MaxAmount = available1MaxAmount.subtracting(txFeeAmount)
        }
        coin0Decimal = WUtils.getOsmosisCoinDecimal(coin0Denom)
        coin1Decimal = WUtils.getOsmosisCoinDecimal(coin1Denom)
        
        WUtils.DpOsmosisTokenImg(inputCoin0Img, coin0Denom)
        WUtils.DpOsmosisTokenName(inputCoin0Name, coin0Denom)
        WUtils.DpOsmosisTokenImg(inputCoin1Img, coin1Denom)
        WUtils.DpOsmosisTokenName(inputCoin1Name, coin1Denom)
        WUtils.showCoinDp(coin0Denom, available0MaxAmount.stringValue, inputCoin0AvailableDenomLabel, inputCoin0AvailableLabel, chainType!)
        WUtils.showCoinDp(coin1Denom, available1MaxAmount.stringValue, inputCoin1AvailableDenomLabel, inputCoin1AvailableLabel, chainType!)
        
        
        let coin0Amount = NSDecimalNumber.init(string: pageHolderVC.mPool!.poolAssets[0].token.amount)
        let coin1Amount = NSDecimalNumber.init(string: pageHolderVC.mPool!.poolAssets[1].token.amount)
        depositRate = coin1Amount.dividing(by: coin0Amount, withBehavior: WUtils.handler18)
        print("depositRate ", depositRate)
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
        if (textField == input0TextFiled) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (coin0Decimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (coin0Decimal - 1) && range.length == 0) { return false }
            }
            
        } else if (textField == input1TextFiled) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (coin1Decimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (coin1Decimal - 1) && range.length == 0) { return false }
            }
        }
        return true
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        if (textField == input0TextFiled) {
            self.onUIupdate0()
        } else if (textField == input1TextFiled) {
            self.onUIupdate1()
        }
    }
    
    func onUIupdate0() {
        guard let text = input0TextFiled.text?.trimmingCharacters(in: .whitespaces) else {
            input0TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input1TextFiled.text = ""
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        if (text.count == 0) {
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
            input1TextFiled.text = ""
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            input0TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input1TextFiled.text = ""
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        if (userInput.compare(NSDecimalNumber.zero).rawValue <= 0) {
            input0TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input1TextFiled.text = ""
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: coin0Decimal).compare(available0MaxAmount).rawValue > 0) {
            input0TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input1TextFiled.text = ""
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        input0TextFiled.layer.borderColor = UIColor.white.cgColor
        
        let outputAmount = userInput.multiplying(byPowerOf10: coin0Decimal - coin1Decimal).multiplying(by: depositRate, withBehavior: WUtils.handler18)
        input1TextFiled.text = WUtils.decimalNumberToLocaleString(outputAmount, coin1Decimal)
        if ((outputAmount.multiplying(byPowerOf10: coin1Decimal)).compare(available1MaxAmount).rawValue > 0) {
            input1TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
        } else {
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
        }
    }
    
    func onUIupdate1() {
        guard let text = input1TextFiled.text?.trimmingCharacters(in: .whitespaces) else {
            input1TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input0TextFiled.text = ""
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        if (text.count == 0) {
            input1TextFiled.layer.borderColor = UIColor.white.cgColor
            input0TextFiled.text = ""
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            input1TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input0TextFiled.text = ""
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        if (userInput.compare(NSDecimalNumber.zero).rawValue <= 0) {
            input1TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input0TextFiled.text = ""
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: coin1Decimal).compare(available1MaxAmount).rawValue > 0) {
            input1TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            input0TextFiled.text = ""
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
            return
        }
        input1TextFiled.layer.borderColor = UIColor.white.cgColor
        
        let outputAmount = userInput.multiplying(byPowerOf10: coin1Decimal - coin0Decimal).dividing(by: depositRate, withBehavior: WUtils.handler18)
        input0TextFiled.text = WUtils.decimalNumberToLocaleString(outputAmount, coin0Decimal)
        if ((outputAmount.multiplying(byPowerOf10: coin0Decimal)).compare(available0MaxAmount).rawValue > 0) {
            input0TextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
        } else {
            input0TextFiled.layer.borderColor = UIColor.white.cgColor
        }
    }
    
    
    
    @IBAction func onClick0Clear(_ sender: UIButton) {
        self.input0TextFiled.text = ""
        onUIupdate0()
    }
    
    @IBAction func onClick01_4(_ sender: UIButton) {
        let calValue = available0MaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.getDivideHandler(coin0Decimal))
        input0TextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coin0Decimal)
        onUIupdate0()
    }
    
    @IBAction func onClick0Half(_ sender: UIButton) {
        let calValue = available0MaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.5")).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.getDivideHandler(coin0Decimal))
        input0TextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coin0Decimal)
        onUIupdate0()
    }
    
    @IBAction func onClick03_4(_ sender: UIButton) {
        let calValue = available0MaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.getDivideHandler(coin0Decimal))
        input0TextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coin0Decimal)
        onUIupdate0()
    }
    
    @IBAction func onClick0Max(_ sender: UIButton) {
        let maxValue = available0MaxAmount.multiplying(byPowerOf10: -coin0Decimal, withBehavior: WUtils.getDivideHandler(coin0Decimal))
        input0TextFiled.text = WUtils.decimalNumberToLocaleString(maxValue, coin0Decimal)
        onUIupdate0()
    }
    
    @IBAction func onClick1Clear(_ sender: UIButton) {
        self.input1TextFiled.text = ""
        onUIupdate1()
    }
    
    @IBAction func onClick11_4(_ sender: UIButton) {
        let calValue = available1MaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -coin1Decimal, withBehavior: WUtils.getDivideHandler(coin1Decimal))
        input1TextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coin1Decimal)
        onUIupdate1()
    }
    
    @IBAction func onClick1Half(_ sender: UIButton) {
        let calValue = available1MaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.5")).multiplying(byPowerOf10: -coin1Decimal, withBehavior: WUtils.getDivideHandler(coin1Decimal))
        input1TextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coin1Decimal)
        onUIupdate1()
    }
    
    @IBAction func onClick13_4(_ sender: UIButton) {
        let calValue = available1MaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -coin1Decimal, withBehavior: WUtils.getDivideHandler(coin1Decimal))
        input1TextFiled.text = WUtils.decimalNumberToLocaleString(calValue, coin1Decimal)
        onUIupdate1()
    }
    
    @IBAction func onClick1Max(_ sender: UIButton) {
        let maxValue = available1MaxAmount.multiplying(byPowerOf10: -coin1Decimal, withBehavior: WUtils.getDivideHandler(coin1Decimal))
        input1TextFiled.text = WUtils.decimalNumberToLocaleString(maxValue, coin1Decimal)
        onUIupdate1()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            //To Deposit Tokens
            let pool0Amount = WUtils.localeStringToDecimal((input0TextFiled.text?.trimmingCharacters(in: .whitespaces))!).multiplying(byPowerOf10: coin0Decimal)
            let pool1Amount = WUtils.localeStringToDecimal((input1TextFiled.text?.trimmingCharacters(in: .whitespaces))!).multiplying(byPowerOf10: coin1Decimal)
            pageHolderVC.mPoolCoin0 = Coin.init(pageHolderVC.mPool!.poolAssets[0].token.denom, pool0Amount.stringValue)
            pageHolderVC.mPoolCoin1 = Coin.init(pageHolderVC.mPool!.poolAssets[1].token.denom, pool1Amount.stringValue)
            
            
            //Expected receiveing lp Token
            let originAmount = NSDecimalNumber.init(string: pageHolderVC.mPool!.poolAssets[0].token.amount)
            let addedAmount = originAmount.adding(pool0Amount)
            let poolTotalShare = NSDecimalNumber.init(string: pageHolderVC.mPool!.totalShares.amount)
            let expectedLpTokenAmount = addedAmount.multiplying(by: poolTotalShare).dividing(by: originAmount, withBehavior: WUtils.handler0)
            let willReceieveLpTokenAmount = (expectedLpTokenAmount.subtracting(poolTotalShare)).multiplying(by: NSDecimalNumber.init(string: "0.975"), withBehavior: WUtils.handler0)
            pageHolderVC.mLPCoin = Coin.init(pageHolderVC.mPool!.totalShares.denom, willReceieveLpTokenAmount.stringValue)
            
            sender.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    
    
    func isValiadAmount() -> Bool {
        let text0 = input0TextFiled.text?.trimmingCharacters(in: .whitespaces)
        if (text0 == nil || text0!.count == 0) { return false }
        let userInput0 = WUtils.localeStringToDecimal(text0!)
        if (userInput0.compare(NSDecimalNumber.zero).rawValue <= 0) { return false }
        if (userInput0.multiplying(byPowerOf10: coin0Decimal).compare(available0MaxAmount).rawValue > 0) { return false }
        
        let text1 = input1TextFiled.text?.trimmingCharacters(in: .whitespaces)
        if (text1 == nil || text1!.count == 0) { return false }
        let userInput1 = WUtils.localeStringToDecimal(text1!)
        if (userInput1.compare(NSDecimalNumber.zero).rawValue <= 0) { return false }
        if (userInput1.multiplying(byPowerOf10: coin1Decimal).compare(available1MaxAmount).rawValue > 0) { return false }
        
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
