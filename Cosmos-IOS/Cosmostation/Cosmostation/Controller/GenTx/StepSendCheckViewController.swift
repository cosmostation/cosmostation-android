//
//  StepSendCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SwiftKeychainWrapper
import HDWalletKit
import GRPC
import NIO

class StepSendCheckViewController: BaseViewController, PasswordViewDelegate{

    @IBOutlet weak var sendAmountLabel: UILabel!
    @IBOutlet weak var sendDenomLabel: UILabel!
    @IBOutlet weak var sendValueLabel: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeDenomLabel: UILabel!
    @IBOutlet weak var feeValueLabel: UILabel!
    @IBOutlet weak var availableAmountLabel: UILabel!
    @IBOutlet weak var availableDenomLabel: UILabel!
    @IBOutlet weak var availableValueLabel: UILabel!
    @IBOutlet weak var remainAmountLabel: UILabel!
    @IBOutlet weak var remainDenomLabel: UILabel!
    @IBOutlet weak var remainValueLabel: UILabel!
    @IBOutlet weak var mToAddressLabel: UILabel!
    @IBOutlet weak var mMemoLabel: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    var mDivideDecimal:Int16 = 6
    var mDisplayDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(chainType, sendDenomLabel)
        WUtils.setDenomTitle(chainType, availableDenomLabel)
        WUtils.setDenomTitle(chainType, remainDenomLabel)
    }
    
    @IBAction func onClickConfirm(_ sender: Any) {
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    @IBAction func onClickBack(_ sender: Any) {
        self.backBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.backBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        let mainDenom = WUtils.getMainDenom(chainType)
        let toSendDenom = pageHolderVC.mToSendAmount[0].denom
        let toSendAmount = WUtils.plainStringToDecimal(pageHolderVC.mToSendAmount[0].amount)
        let feeAmount = WUtils.plainStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        var currentAvailable = NSDecimalNumber.zero
        var remainAvailable = NSDecimalNumber.zero
        
        if (WUtils.isGRPC(chainType)) {
            mDivideDecimal = WUtils.mainDivideDecimal(chainType)
            mDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
            if (chainType == ChainType.SIF_MAIN) {
                mDivideDecimal = WUtils.getSifCoinDecimal(pageHolderVC.mToSendDenom)
                mDisplayDecimal = WUtils.getSifCoinDecimal(pageHolderVC.mToSendDenom)
            }
            currentAvailable = BaseData.instance.getAvailableAmount_gRPC(toSendDenom)
            
        }  else {
            if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
                mDivideDecimal = WUtils.mainDivideDecimal(chainType)
                mDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
                
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                mDivideDecimal = WUtils.getKavaCoinDecimal(pageHolderVC.mToSendDenom)
                mDisplayDecimal = WUtils.getKavaCoinDecimal(pageHolderVC.mToSendDenom)
                
            } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
                mDivideDecimal = WUtils.mainDivideDecimal(chainType)
                mDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
                
            } else {
                mDivideDecimal = WUtils.mainDivideDecimal(chainType)
                mDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
            }
            currentAvailable = BaseData.instance.availableAmount(toSendDenom)
            
        }
        if (toSendDenom == mainDenom) {
            remainAvailable = currentAvailable.subtracting(toSendAmount).subtracting(feeAmount)
            sendValueLabel.attributedText = WUtils.dpUserCurrencyValue(mainDenom, toSendAmount, mDivideDecimal, sendValueLabel.font)
            feeValueLabel.attributedText = WUtils.dpUserCurrencyValue(mainDenom, feeAmount, mDivideDecimal, feeValueLabel.font)
            availableValueLabel.attributedText = WUtils.dpUserCurrencyValue(mainDenom, currentAvailable, mDivideDecimal, availableValueLabel.font)
            remainValueLabel.attributedText = WUtils.dpUserCurrencyValue(mainDenom, remainAvailable, mDivideDecimal, remainValueLabel.font)
            
        } else {
            remainAvailable = currentAvailable.subtracting(toSendAmount)
            sendValueLabel.isHidden = true
            feeValueLabel.isHidden = true
            availableValueLabel.isHidden = true
            remainValueLabel.isHidden = true
            
        }
        
        WUtils.showCoinDp(toSendDenom, toSendAmount.stringValue, sendDenomLabel, sendAmountLabel, chainType!)
        WUtils.showCoinDp(mainDenom, feeAmount.stringValue, feeDenomLabel, feeAmountLabel, chainType!)
        WUtils.showCoinDp(toSendDenom, currentAvailable.stringValue, availableDenomLabel, availableAmountLabel, chainType!)
        WUtils.showCoinDp(toSendDenom, remainAvailable.stringValue, remainDenomLabel, remainAmountLabel, chainType!)
        
        mToAddressLabel.text = pageHolderVC.mToSendRecipientAddress
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            mToAddressLabel.text = WKey.convertAddressOkexToEth(pageHolderVC.mToSendRecipientAddress!)
        }
        mToAddressLabel.adjustsFontSizeToFitWidth = true
        mMemoLabel.text = pageHolderVC.mMemo
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            if (WUtils.isGRPC(chainType)) {
                self.onFetchgRPCAuth(pageHolderVC.mAccount!)
            } else {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
            }
        }
    }
    
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.BINANCE_MAIN || self.chainType == ChainType.BINANCE_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let bnbAccountInfo = BnbAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithBnbAccountInfo(account, bnbAccountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithBnbAccountInfo(account, bnbAccountInfo))
                    self.onGenBnbSendTx()
                    
                } else if (self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST) {
                    guard  let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenSendTx()
                    
                } else if (self.chainType == ChainType.OKEX_MAIN || self.chainType == ChainType.OKEX_TEST) {
                    guard let info = res as? NSDictionary else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let okAccountInfo = OkAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithOkAccountInfo(account, okAccountInfo))
                    BaseData.instance.mOkAccountInfo = okAccountInfo
                    self.onGenSendTx()
                    
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
                    self.onGenSendTx()
                }
                
            case .failure(let error):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
        }
    }
    
    func onGenSendTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let msg = MsgGenerator.genGetSendMsg(self.pageHolderVC.mAccount!.account_address,
                                                     self.pageHolderVC.mToSendRecipientAddress!,
                                                     self.pageHolderVC.mToSendAmount,
                                                     self.chainType!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                let privateKey = KeyFac.getPrivateRaw(words, self.pageHolderVC.mAccount!)
                let publicKey = KeyFac.getPublicRaw(words, self.pageHolderVC.mAccount!)
                if (self.chainType == ChainType.OKEX_MAIN || self.chainType == ChainType.OKEX_TEST) {
                    let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(self.chainType),
                                                           String(self.pageHolderVC.mAccount!.account_account_numner),
                                                           String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                           msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                    
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(stdMsg)
                    let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                    let rawData: Data? = rawResult!.data(using: .utf8)
                    
                    if (self.pageHolderVC.mAccount!.account_new_bip44) {
                        let hash = HDWalletKit.Crypto.sha3keccak256(data: rawData!)
                        let signedData: Data? = try ECDSA.compactsign(hash, privateKey: privateKey)
                        
                        var genedSignature = Signature.init()
                        var genPubkey =  PublicKey.init()
                        genPubkey.type = ETHERMINT_KEY_TYPE_PUBLIC
                        genPubkey.value = publicKey.base64EncodedString()
                        genedSignature.pub_key = genPubkey
                        genedSignature.signature = signedData!.base64EncodedString()
                        genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                        genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                        
                        var signatures: Array<Signature> = Array<Signature>()
                        signatures.append(genedSignature)
                        
                        stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                        
                    } else {
                        let hash = rawData!.sha256()
                        let signedData = try! ECDSA.compactsign(hash, privateKey: privateKey)
                        
                        var genedSignature = Signature.init()
                        var genPubkey =  PublicKey.init()
                        genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
                        genPubkey.value = publicKey.base64EncodedString()
                        genedSignature.pub_key = genPubkey
                        genedSignature.signature = signedData.base64EncodedString()
                        genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                        genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                        
                        var signatures: Array<Signature> = Array<Signature>()
                        signatures.append(genedSignature)
                        
                        stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                    }
                    
                } else {
                    let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(self.chainType),
                                                           String(self.pageHolderVC.mAccount!.account_account_numner),
                                                           String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                           msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                
                    stdTx = KeyFac.getStdTx(words, msgList, stdMsg, self.pageHolderVC.mAccount!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                }
                
                
            } catch {
                if(SHOW_LOG) { print(error) }
            }
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
//                    print("params ", params)
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("Send ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            print("send error ", error)
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
                    if(SHOW_LOG) { print(error) }
                }
            });
        }
    }
    
    func onGenBnbSendTx() {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
            
            let bnbMsg = BinanceMessage.transfer(symbol: self.pageHolderVC.mToSendAmount[0].denom,
                                    amount: (self.pageHolderVC.mToSendAmount[0].amount as NSString).doubleValue,
                                    toAddress: self.pageHolderVC.mToSendRecipientAddress!,
                                    memo: self.pageHolderVC.mMemo!,
                                    privateKey: pKey,
                                    signerAddress: self.pageHolderVC.mAccount!.account_address,
                                    sequence: Int(self.pageHolderVC.mAccount!.account_sequence_number),
                                    accountNumber: Int(self.pageHolderVC.mAccount!.account_account_numner),
                                    chainId: BaseData.instance.getChainId(self.chainType))
            
            DispatchQueue.main.async(execute: {
                do {
                    var encoding: ParameterEncoding = URLEncoding.default
                    encoding = HexEncoding(data: try bnbMsg.encode())
                    let param: Parameters = [ "address": self.pageHolderVC.mAccount!.account_address ]
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: param, encoding: encoding, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("res ", res)
                            if let result = res as? Array<NSDictionary> {
                                txResult["hash"] = result[0].object(forKey:"hash")
                            }
                            
                        case .failure(let error):
                            print("send error ", error)
                        }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                self.onStartTxDetail(txResult)
                            })
                        }
                    }

                } catch {
                    if(SHOW_LOG) { print(error) }
                }
            });
        }
    }
    
    
    //gRPC
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
            let reqTx = Signer.genSignedSendTxgRPC(auth!, self.pageHolderVC.mToSendRecipientAddress!, self.pageHolderVC.mToSendAmount,
                                                   self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                   BaseData.instance.getChainId(self.chainType))
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }

            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }

            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).broadcastTx(reqTx).response.wait()
                print("response ", response.txResponse.txhash)
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


