//
//  Swap3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class Swap3ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var txFeeAmountLabel: UILabel!
    @IBOutlet weak var txFeeDenomLabel: UILabel!
    @IBOutlet weak var swapFeeLabel: UILabel!
    @IBOutlet weak var swapInAmountLabel: UILabel!
    @IBOutlet weak var swapInDenomLabel: UILabel!
    @IBOutlet weak var swapOutAmountLabel: UILabel!
    @IBOutlet weak var swapOutDenomLabel: UILabel!
    @IBOutlet weak var mMemoLabel: UILabel!
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mPool: Osmosis_Gamm_V1beta1_Pool!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.mPool = pageHolderVC!.mPool
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        WUtils.showCoinDp(pageHolderVC.mFee!.amount[0].denom, pageHolderVC.mFee!.amount[0].amount, txFeeDenomLabel, txFeeAmountLabel, chainType!)
        WUtils.showCoinDp(pageHolderVC.mSwapInDenom!, pageHolderVC.mSwapInAmount!.stringValue, swapInDenomLabel, swapInAmountLabel, chainType!)
        WUtils.showCoinDp(pageHolderVC.mSwapOutDenom!, pageHolderVC.mSwapOutAmount!.stringValue, swapOutDenomLabel, swapOutAmountLabel, chainType!)
        swapFeeLabel.attributedText = WUtils.displayPercent(NSDecimalNumber.init(string: mPool.poolParams.swapFee).multiplying(byPowerOf10: -16), swapFeeLabel.font)
        mMemoLabel.text = pageHolderVC.mMemo
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
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
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
            
            var swapRoutes = Array<Osmosis_Gamm_V1beta1_SwapAmountInRoute>()
            let swapRoute = Osmosis_Gamm_V1beta1_SwapAmountInRoute.with {
                $0.poolID = self.pageHolderVC.mPool!.id
                $0.tokenOutDenom = self.pageHolderVC.mSwapOutDenom!
            }
            swapRoutes.append(swapRoute)
            
            let reqTx = Signer.genSignedSwapInMsgTxgRPC(auth!,
                                                        swapRoutes,
                                                        self.pageHolderVC.mSwapInDenom!,
                                                        self.pageHolderVC.mSwapInAmount!.stringValue,
                                                        self.pageHolderVC.mSwapOutAmount!.stringValue,
                                                        self.pageHolderVC.mFee!,
                                                        self.pageHolderVC.mMemo!,
                                                        privateKey, publicKey,
                                                        BaseData.instance.getChainId_gRPC())
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
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
