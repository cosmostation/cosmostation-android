//
//  GdexWithdraw0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class GdexWithdraw0ViewController: BaseViewController, UITextFieldDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var lpAvailableLabel: UILabel!
    @IBOutlet weak var lpAvailableDenomLabel: UILabel!
    @IBOutlet weak var inputTextFiled: AmountInputTextField!
    
    var channel: ClientConnection!
    var pageHolderVC: StepGenTxViewController!
    
    var selectedPool: Tendermint_Liquidity_V1beta1_Pool!
    var selectedPoolManager: GDexManager!
    var selectedPoolSupply: Coin!
    var lpCoinDecimal:Int16 = 6
    var availableMaxAmount = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        self.selectedPool = BaseData.instance.getGravityPoolById(pageHolderVC.mPoolId!)
        
        loadingImg.startAnimating()
        onFetchDexData()
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func onInitView() {
        availableMaxAmount = BaseData.instance.getAvailableAmount_gRPC(selectedPool.poolCoinDenom)
        lpAvailableDenomLabel.text = "GDEX-" + String(selectedPool.id)
        lpAvailableLabel.attributedText = WUtils.displayAmount2(availableMaxAmount.stringValue, lpAvailableLabel.font!, lpCoinDecimal, lpCoinDecimal)
        
        inputTextFiled.delegate = self
        inputTextFiled.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        self.loadingImg.stopAnimating()
        self.loadingImg.isHidden = true
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let text = textField.text else { return true }
        if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ".")) { return false }
        if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ",")) { return false }
        if (textField == inputTextFiled) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (lpCoinDecimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (lpCoinDecimal - 1) && range.length == 0) { return false }
            }
            
        } else if (textField == inputTextFiled) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (lpCoinDecimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (lpCoinDecimal - 1) && range.length == 0) { return false }
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
        if (userInput.multiplying(byPowerOf10: lpCoinDecimal).compare(availableMaxAmount).rawValue > 0) {
            inputTextFiled.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        inputTextFiled.layer.borderColor = UIColor.white.cgColor
    }

    @IBAction func onClickClear(_ sender: UIButton) {
        self.inputTextFiled.text = ""
        self.onUIupdate()
    }
    
    @IBAction func onClick1_4(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25")).multiplying(byPowerOf10: -lpCoinDecimal, withBehavior: WUtils.getDivideHandler(lpCoinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, lpCoinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.5")).multiplying(byPowerOf10: -lpCoinDecimal, withBehavior: WUtils.getDivideHandler(lpCoinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, lpCoinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClick3_4(_ sender: UIButton) {
        let calValue = availableMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75")).multiplying(byPowerOf10: -lpCoinDecimal, withBehavior: WUtils.getDivideHandler(lpCoinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(calValue, lpCoinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
        let maxValue = availableMaxAmount.multiplying(byPowerOf10: -lpCoinDecimal, withBehavior: WUtils.getDivideHandler(lpCoinDecimal))
        inputTextFiled.text = WUtils.decimalNumberToLocaleString(maxValue, lpCoinDecimal)
        self.onUIupdate()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadAmount()) {
            let userInput = WUtils.localeStringToDecimal((inputTextFiled.text?.trimmingCharacters(in: .whitespaces))!).multiplying(byPowerOf10: lpCoinDecimal)
            pageHolderVC.mLPCoin = Coin.init(selectedPool.poolCoinDenom, userInput.stringValue)
            
            pageHolderVC.mGDexPool = selectedPool
            pageHolderVC.mGDexPoolManager = selectedPoolManager
            pageHolderVC.mGDexPoolSupply = selectedPoolSupply
            
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
        if (userInput.multiplying(byPowerOf10: lpCoinDecimal).compare(availableMaxAmount).rawValue > 0) { return false }
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
