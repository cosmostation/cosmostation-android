//
//  GdexWithdraw3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class GdexWithdraw3ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var txFeeAmountLabel: UILabel!
    @IBOutlet weak var txFeeDenomLabel: UILabel!
    @IBOutlet weak var lpAmountLabel: UILabel!
    @IBOutlet weak var lpDenomLabel: UILabel!
    @IBOutlet weak var withdraw0AmountLabel: UILabel!
    @IBOutlet weak var withdraw0DenomLabel: UILabel!
    @IBOutlet weak var withdraw1AmountLabel: UILabel!
    @IBOutlet weak var withdraw1DenomLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var selectedPool: Tendermint_Liquidity_V1beta1_Pool!
    var selectedPoolManager: GDexManager!
    var selectedPoolSupply: Coin!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        self.selectedPool = pageHolderVC.mGDexPool
        self.selectedPoolManager = pageHolderVC.mGDexPoolManager
        self.selectedPoolSupply = pageHolderVC.mGDexPoolSupply
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        WUtils.showCoinDp(pageHolderVC.mFee!.amount[0].denom, pageHolderVC.mFee!.amount[0].amount, txFeeDenomLabel, txFeeAmountLabel, chainType!)
        lpDenomLabel.text = "GDEX-" + String(pageHolderVC.mGDexPool!.id)
        lpAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mLPCoin!.amount, lpAmountLabel.font, 6, 6)
        
        let coin0Denom = selectedPool.reserveCoinDenoms[0]
        let coin1Denom = selectedPool.reserveCoinDenoms[1]
        let totalShare = NSDecimalNumber.init(string: selectedPoolSupply.amount)
        let myShare = NSDecimalNumber.init(string: pageHolderVC.mLPCoin!.amount)
        let depositRate = myShare.dividing(by: totalShare, withBehavior: WUtils.handler18)
        
        let coin0Amount = NSDecimalNumber.init(string: selectedPoolManager.reserve.filter { $0.denom == coin0Denom }.first?.amount)
        let coin1Amount = NSDecimalNumber.init(string: selectedPoolManager.reserve.filter { $0.denom == coin1Denom }.first?.amount)
        let expectCoin0Amount = coin0Amount.multiplying(by: depositRate, withBehavior: WUtils.handler0)
        let expectCoin1Amount = coin1Amount.multiplying(by: depositRate, withBehavior: WUtils.handler0)
        let coin0 = Coin.init(coin0Denom, expectCoin0Amount.stringValue)
        let coin1 = Coin.init(coin1Denom, expectCoin1Amount.stringValue)
        
        WUtils.showCoinDp(coin0, withdraw0DenomLabel, withdraw0AmountLabel, chainType!)
        WUtils.showCoinDp(coin1, withdraw1DenomLabel, withdraw1AmountLabel, chainType!)
        
        memoLabel.text = pageHolderVC.mMemo
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchgRPCAuth(pageHolderVC.mAccount!)
        }
    }
    
    func onFetchgRPCAuth(_ account: Account) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Auth_V1beta1_QueryAccountRequest.with {
                $0.address = account.account_address
            }
            do {
                let response = try Cosmos_Auth_V1beta1_QueryClient(channel: channel).account(req).response.wait()
                self.onBroadcastGrpcTx(response)
            } catch {
                print("onFetchgRPCAuth failed: \(error)")
            }
        }
    }

    func onBroadcastGrpcTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse?) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let privateKey = KeyFac.getPrivateRaw(words, self.pageHolderVC.mAccount!)
            let publicKey = KeyFac.getPublicRaw(words, self.pageHolderVC.mAccount!)
            
            let reqTx = Signer.genSignedWithdrawBatchMsgTxgRPC(auth!,
                                                              self.pageHolderVC.mAccount!.account_address,
                                                              String(self.pageHolderVC.mGDexPool!.id),
                                                              self.pageHolderVC.mLPCoin!,
                                                              self.pageHolderVC.mFee!,
                                                              self.pageHolderVC.mMemo!,
                                                              privateKey, publicKey,
                                                              BaseData.instance.getChainId(self.chainType))
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }

            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }

            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).broadcastTx(reqTx).response.wait()
//                print("response ", response.txResponse.txhash)
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetailgRPC(response)
                        })
                    }
                });
            } catch {
                print("onBroadcastGrpcTx failed: \(error)")
            }
        }
    }
}
