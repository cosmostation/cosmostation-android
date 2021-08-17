//
//  StartUnbonding3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class StartUnbonding3ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var txFeeAmountLabel: UILabel!
    @IBOutlet weak var txFeeDenomLabel: UILabel!
    @IBOutlet weak var toUnbondingIdsLabel: UILabel!
    @IBOutlet weak var toUnbondingAmountLabel: UILabel!
    @IBOutlet weak var toUnbondingDenomLabel: UILabel!
    @IBOutlet weak var toUnbondingDurationLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        WUtils.showCoinDp(pageHolderVC.mFee!.amount[0].denom, pageHolderVC.mFee!.amount[0].amount, txFeeDenomLabel, txFeeAmountLabel, chainType!)
        
        let toUnbondingDuration  = pageHolderVC.mLockups![0].duration.seconds
        let toUnbondingDenom = String(pageHolderVC.mLockups![0].coins[0].denom)
        var ids = ""
        var toUnbondingAmount = NSDecimalNumber.zero
        for lockUp in pageHolderVC.mLockups! {
            ids = ids + "# " + String(lockUp.id) + "  "
            toUnbondingAmount = toUnbondingAmount.adding(NSDecimalNumber.init(string: lockUp.coins[0].amount))
        }
        
        toUnbondingIdsLabel.text = ids
        if (toUnbondingDuration == 86400) {
            toUnbondingDurationLabel.text = "1 Day"
        } else if (toUnbondingDuration == 604800) {
            toUnbondingDurationLabel.text = "7 Days"
        } else if (toUnbondingDuration == 1209600) {
            toUnbondingDurationLabel.text = "14 Days"
        }
        WUtils.showCoinDp(toUnbondingDenom, toUnbondingAmount.stringValue, toUnbondingDenomLabel, toUnbondingAmountLabel, chainType!)
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
            
            var ids = Array<UInt64>()
            for lockup in self.pageHolderVC.mLockups! {
                ids.append(lockup.id)
            }
            let reqTx = Signer.genSignedBeginUnlockingsMsgTxgRPC(auth!,
                                                                 ids,
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
