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
//            extendTime = BaseData.instance.mStarNameConfig!.getRenewPeriod(IOV_MSG_TYPE_RENEW_DOMAIN)
//            starnameFee = BaseData.instance.mStarNameFee!.getDomainRenewFee(pageHolderVC!.mStarnameDomainType!)
            extendTime = WUtils.getRenewPeriod(IOV_MSG_TYPE_RENEW_DOMAIN)
            starnameFee = WUtils.getStarNameRenewDomainFee(pageHolderVC.mStarnameDomain!, pageHolderVC!.mStarnameDomainType!)
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            starnameLabel.text = pageHolderVC.mStarnameAccount! + "*" + pageHolderVC.mStarnameDomain!
//            extendTime = BaseData.instance.mStarNameConfig!.getRenewPeriod(IOV_MSG_TYPE_RENEW_ACCOUNT)
//            starnameFee = BaseData.instance.mStarNameFee!.getAccountRenewFee(pageHolderVC!.mStarnameDomainType!)
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
            
            var reqTx: Cosmos_Tx_V1beta1_BroadcastTxRequest = Cosmos_Tx_V1beta1_BroadcastTxRequest.init()
            if (self.pageHolderVC.mType == IOV_MSG_TYPE_RENEW_DOMAIN) {
                reqTx = Signer.genSignedRenewDomainMsgTxgRPC (auth!,
                                                               self.pageHolderVC.mStarnameDomain!,
                                                               self.pageHolderVC.mAccount!.account_address,
                                                               self.pageHolderVC.mFee!,
                                                               self.pageHolderVC.mMemo!,
                                                               WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!),
                                                               BaseData.instance.getChainId_gRPC())
                
            } else if (self.pageHolderVC.mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                reqTx = Signer.genSignedRenewAccountMsgTxgRPC (auth!,
                                                               self.pageHolderVC.mStarnameDomain!,
                                                               self.pageHolderVC.mStarnameAccount!,
                                                               self.pageHolderVC.mAccount!.account_address,
                                                               self.pageHolderVC.mFee!,
                                                               self.pageHolderVC.mMemo!,
                                                               WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!),
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
    
//    func onFetchAccountInfo(_ account: Account) {
//        self.showWaittingAlert()
//        let request = Alamofire.request(BaseNetWork.accountInfoUrl(pageHolderVC.chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
//        request.responseJSON { (response) in
//            switch response.result {
//            case .success(let res):
//                guard let responseData = res as? NSDictionary,
//                    let info = responseData.object(forKey: "result") as? [String : Any] else {
//                        _ = BaseData.instance.deleteBalance(account: account)
//                        self.hideWaittingAlert()
//                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
//                        return
//                }
//                let accountInfo = AccountInfo.init(info)
//                _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
//                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
//                self.onGenRenewStarnameTx()
//
//            case .failure( _):
//                self.hideWaittingAlert()
//                self.onShowToast(NSLocalizedString("error_network", comment: ""))
//            }
//        }
//    }
//
//    func onGenRenewStarnameTx() {
//        DispatchQueue.global().async {
//            var stdTx:StdTx!
//            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
//                return
//            }
//
//            do {
//                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
//                let msg = MsgGenerator.genRenewStarnameMsg(self.pageHolderVC.mType!,
//                                                            self.pageHolderVC.mStarnameDomain!,
//                                                            self.pageHolderVC.mStarnameAccount,
//                                                            self.pageHolderVC.mAccount!.account_address,
//                                                            self.pageHolderVC.chainType!)
//
//                var msgList = Array<Msg>()
//                msgList.append(msg)
//
//                let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(),
//                                                       String(self.pageHolderVC.mAccount!.account_account_numner),
//                                                       String(self.pageHolderVC.mAccount!.account_sequence_number),
//                                                       msgList,
//                                                       self.pageHolderVC.mFee!,
//                                                       self.pageHolderVC.mMemo!)
//
//                let encoder = JSONEncoder()
//                encoder.outputFormatting = .sortedKeys
//                let data = try? encoder.encode(stdMsg)
//                let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
//                let rawData: Data? = rawResult!.data(using: .utf8)
//                let hash = rawData!.sha256()
//                let signedData = try! ECDSA.compactsign(hash, privateKey: pKey.raw)
//
//                var genedSignature = Signature.init()
//                var genPubkey =  PublicKey.init()
//                genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
//                genPubkey.value = pKey.publicKey.data.base64EncodedString()
//                genedSignature.pub_key = genPubkey
//                genedSignature.signature = signedData.base64EncodedString()
//                genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
//                genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
//
//                var signatures: Array<Signature> = Array<Signature>()
//                signatures.append(genedSignature)
//
//                stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
//
//            } catch {
//                if (SHOW_LOG) { print(error) }
//            }
//
//            DispatchQueue.main.async(execute: {
//                let postTx = PostTx.init("sync", stdTx.value)
//                let encoder = JSONEncoder()
//                encoder.outputFormatting = .sortedKeys
//                let data = try? encoder.encode(postTx)
//                do {
//                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
//                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.pageHolderVC.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
//                    request.validate().responseJSON { response in
//                        var txResult = [String:Any]()
//                        switch response.result {
//                        case .success(let res):
//                            if(SHOW_LOG) { print("onGenRenewStarnameTx ", res) }
//                            if let result = res as? [String : Any]  {
//                                txResult = result
//                            }
//                        case .failure(let error):
//                            if(SHOW_LOG) {
//                                print("onGenRenewStarnameTx error ", error)
//                            }
//                            if (response.response?.statusCode == 500) {
//                                txResult["net_error"] = 500
//                            }
//                        }
//
//                        if (self.waitAlert != nil) {
//                            self.waitAlert?.dismiss(animated: true, completion: {
//                                self.onStartTxDetail(txResult)
//                            })
//                        }
//                    }
//
//                } catch {
//                    if (SHOW_LOG) { print(error) }
//                }
//            });
//        }
//    }
}
