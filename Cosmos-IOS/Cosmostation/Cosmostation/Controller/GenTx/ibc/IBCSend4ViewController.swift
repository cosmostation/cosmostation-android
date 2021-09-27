//
//  IBCSend4ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/24.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class IBCSend4ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    @IBOutlet weak var txFeeAmountLabel: UILabel!
    @IBOutlet weak var txFeeDenomLabel: UILabel!
    @IBOutlet weak var toSendAmountLabel: UILabel!
    @IBOutlet weak var toSendDenomLabel: UILabel!
    @IBOutlet weak var destinationChainLabel: UILabel!
    @IBOutlet weak var destinationAddressLabel: UILabel!
    
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
        WUtils.showCoinDp(pageHolderVC.mIBCSendDenom!, pageHolderVC.mIBCSendAmount!, toSendDenomLabel, toSendAmountLabel, chainType!)
        
        let toChain = WUtils.getChainTypeByChainId(pageHolderVC.mIBCSendRelayer?.chain_id)
        self.destinationChainLabel.text = WUtils.getChainTitle(toChain)
        self.destinationAddressLabel.text = pageHolderVC.mIBCRecipient
        self.destinationAddressLabel.adjustsFontSizeToFitWidth = true
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
        print("onFetchgRPCAuth", account.account_address)
        self.showWaittingAlert()
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(self.chainType!, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let req = Cosmos_Auth_V1beta1_QueryAccountRequest.with { $0.address = account.account_address }
                if let response = try? Cosmos_Auth_V1beta1_QueryClient(channel: channel).account(req).response.wait() {
                    self.onFetchIbcClientState(response)
                }
                try channel.close().wait()
            } catch {
                print("onFetchgRPCAuth failed: \(error)")
            }
        }
    }
    
    func onFetchIbcClientState(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse) {
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(self.chainType!, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let req = Ibc_Core_Channel_V1_QueryChannelClientStateRequest.with {
                    $0.channelID = self.pageHolderVC.mIBCSendPath!.channel_id!
                    $0.portID = self.pageHolderVC.mIBCSendPath!.port_id!
                }
                if let response = try? Ibc_Core_Channel_V1_QueryClient(channel: channel).channelClientState(req).response.wait() {
                    let clientState = try! Ibc_Lightclients_Tendermint_V1_ClientState.init(serializedData: response.identifiedClientState.clientState.value)
                    self.onBroadcastGrpcTx(auth, clientState.latestHeight)
                }
                try channel.close().wait()
            } catch {
                print("onFetchIbcClientState failed: \(error)")
            }
        }
    }
    
    
    func onBroadcastGrpcTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ height: Ibc_Core_Client_V1_Height) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let privateKey = KeyFac.getPrivateRaw(words, self.pageHolderVC.mAccount!)
            let publicKey = KeyFac.getPublicRaw(words, self.pageHolderVC.mAccount!)
            
            let reqTx = Signer.genSignedIbcTransferMsgTxgRPC(auth,
                                                             self.pageHolderVC.mAccount!.account_address,
                                                             self.pageHolderVC.mIBCRecipient!,
                                                             self.pageHolderVC.mIBCSendDenom!,
                                                             self.pageHolderVC.mIBCSendAmount!,
                                                             self.pageHolderVC.mIBCSendPath!,
                                                             height,
                                                             self.pageHolderVC.mFee!,
                                                             IBC_TRANSFER_MEMO,
                                                             privateKey, publicKey,
                                                             BaseData.instance.getChainId(self.chainType))
            
            do {
                let channel = BaseNetWork.getConnection(self.chainType!, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).broadcastTx(reqTx).response.wait()
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetailgRPC(response)
                        })
                    }
                });
                try channel.close().wait()
            } catch {
                print("onBroadcastGrpcTx failed: \(error)")
            }
        }
    }
}
