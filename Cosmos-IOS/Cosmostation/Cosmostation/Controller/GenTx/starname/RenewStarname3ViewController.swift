//
//  RenewStarname3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class RenewStarname3ViewController: BaseViewController, PasswordViewDelegate {

    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var starnameFeeAmount: UILabel!
    @IBOutlet weak var starnameFeeDenom: UILabel!
    @IBOutlet weak var starnameLabel: UILabel!
    @IBOutlet weak var expireDate: UILabel!
    @IBOutlet weak var renewExpireDate: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeAmountDenom)
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        feeAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, 6)
        var extendTime: Int64 = 0
        var starnameFee = NSDecimalNumber.zero
        if (pageHolderVC.mType == IOV_MSG_TYPE_RENEW_DOMAIN) {
            starnameLabel.text = "*" + pageHolderVC.mStarnameDomain!
            extendTime = WUtils.getRenewPeriod(IOV_MSG_TYPE_RENEW_DOMAIN)
            starnameFee = WUtils.getStarNameRenewDomainFee(pageHolderVC.mStarnameDomain!, pageHolderVC!.mStarnameDomainType!)
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            starnameLabel.text = pageHolderVC.mStarnameAccount! + "*" + pageHolderVC.mStarnameDomain!
            extendTime = WUtils.getRenewPeriod(IOV_MSG_TYPE_RENEW_ACCOUNT)
            starnameFee = WUtils.getStarNameRenewAccountFee(pageHolderVC!.mStarnameDomainType!)
        }
        expireDate.text = WUtils.longTimetoString(input: pageHolderVC.mStarnameTime! * 1000)
        renewExpireDate.text = WUtils.longTimetoString(input: (pageHolderVC.mStarnameTime! * 1000) + extendTime)
        starnameFeeAmount.attributedText = WUtils.displayAmount2(starnameFee.stringValue, starnameFeeAmount.font, 6, 6)
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
            
            var reqTx: Cosmos_Tx_V1beta1_BroadcastTxRequest = Cosmos_Tx_V1beta1_BroadcastTxRequest.init()
            if (self.pageHolderVC.mType == IOV_MSG_TYPE_RENEW_DOMAIN) {
                reqTx = Signer.genSignedRenewDomainMsgTxgRPC (auth!,
                                                               self.pageHolderVC.mStarnameDomain!,
                                                               self.pageHolderVC.mAccount!.account_address,
                                                               self.pageHolderVC.mFee!,
                                                               self.pageHolderVC.mMemo!,
                                                               privateKey, publicKey,
                                                               BaseData.instance.getChainId_gRPC())
                
            } else if (self.pageHolderVC.mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                reqTx = Signer.genSignedRenewAccountMsgTxgRPC (auth!,
                                                               self.pageHolderVC.mStarnameDomain!,
                                                               self.pageHolderVC.mStarnameAccount!,
                                                               self.pageHolderVC.mAccount!.account_address,
                                                               self.pageHolderVC.mFee!,
                                                               self.pageHolderVC.mMemo!,
                                                               privateKey, publicKey,
                                                               BaseData.instance.getChainId_gRPC())
            }
            
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
