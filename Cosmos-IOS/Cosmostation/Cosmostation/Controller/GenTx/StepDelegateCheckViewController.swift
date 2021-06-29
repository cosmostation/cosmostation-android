//
//  StepDelegateCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class StepDelegateCheckViewController: BaseViewController, PasswordViewDelegate, SBCardPopupDelegate {
    
    @IBOutlet weak var toDelegateAmountLabel: UILabel!
    @IBOutlet weak var toDelegateAmountDenom: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var targetValidatorLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, toDelegateAmountDenom)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeAmountDenom)
    }

    @IBAction func onClickConfirm(_ sender: Any) {
        let popupVC = DelegateWarnPopup(nibName: "DelegateWarnPopup", bundle: nil)
        if (pageHolderVC.chainType! == ChainType.SENTINEL_MAIN || pageHolderVC.chainType! == ChainType.CRYPTO_MAIN) {
            popupVC.warnImgType = 28
        } else {
            popupVC.warnImgType = 21
        }
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    
    @IBAction func onClickBack(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.beforeBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        mDpDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            toDelegateAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mToDelegateAmount?.amount, toDelegateAmountLabel.font, mDpDecimal, mDpDecimal)
            feeAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mFee?.amount[0].amount, feeAmountLabel.font, mDpDecimal, mDpDecimal)
            targetValidatorLabel.text = pageHolderVC.mTargetValidator_gRPC?.description_p.moniker
            
        } else {
            toDelegateAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mToDelegateAmount?.amount)!, toDelegateAmountLabel.font, mDpDecimal, mDpDecimal)
            feeAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, mDpDecimal, mDpDecimal)
            targetValidatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
        }
        memoLabel.text = pageHolderVC.mMemo
    }
    
    
    func SBCardPopupResponse(type:Int, result: Int) {
        if (result == 1) {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
        }
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            if (WUtils.isGRPC(pageHolderVC.chainType!)) {
                self.onFetchgRPCAuth(pageHolderVC.mAccount!)
            } else {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
            }
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(pageHolderVC.chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenDelegateTx()
                    
                } else {
                    guard let responseData = res as? NSDictionary,
                        let info = responseData.object(forKey: "result") as? [String : Any] else {
                            _ = BaseData.instance.deleteBalance(account: account)
                            self.hideWaittingAlert()
                            self.onShowToast(NSLocalizedString("error_network", comment: ""))
                            return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    self.onGenDelegateTx()
                    
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenDelegateTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                let msg = MsgGenerator.genDelegateMsg(self.pageHolderVC.mAccount!.account_address,
                                                      self.pageHolderVC.mTargetValidator!.operator_address,
                                                      self.pageHolderVC.mToDelegateAmount!,
                                                      self.pageHolderVC.chainType!)
                
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(),
                                                       String(self.pageHolderVC.mAccount!.account_account_numner),
                                                       String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                       msgList,
                                                       self.pageHolderVC.mFee!,
                                                       self.pageHolderVC.mMemo!)
                
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(stdMsg)
                let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                let rawData: Data? = rawResult!.data(using: .utf8)
                let hash = rawData!.sha256()
                let signedData = try! ECDSA.compactsign(hash, privateKey: pKey.raw)
                
                var genedSignature = Signature.init()
                var genPubkey =  PublicKey.init()
                genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
                genPubkey.value = pKey.publicKey.data.base64EncodedString()
                genedSignature.pub_key = genPubkey
                genedSignature.signature = signedData.base64EncodedString()
                genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                
                var signatures: Array<Signature> = Array<Signature>()
                signatures.append(genedSignature)
                
                stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.pageHolderVC.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.validate().responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Delegate ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("Delegate error ", error)
                            }
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }

                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                self.onStartTxDetail(txResult)
                            })
                        }
                    }

                } catch {
                    if (SHOW_LOG) { print(error) }
                }
            });
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
            let reqTx = Signer.genSignedDelegateTxgRPC(auth!, self.pageHolderVC.mTargetValidator_gRPC!.operatorAddress, self.pageHolderVC.mToDelegateAmount!,
                                                       self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!),
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
