//
//  StepHtlcRefund3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import BinanceChain
import SwiftKeychainWrapper


class StepHtlcRefund3ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var swapIdLabel: UILabel!
    @IBOutlet weak var refundToLabel: UILabel!
    @IBOutlet weak var refundAmountLabel: UILabel!
    @IBOutlet weak var refundAmountDenom: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
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
        let feeAmount = NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeAmountDenom)
        memoLabel.text = pageHolderVC.mMemo
        
        if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
            feeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, feeAmountLabel.font, 0, 8)
            swapIdLabel.text = pageHolderVC.mHtlcRefundSwapId!
            refundToLabel.text = pageHolderVC.mBnbSwapInfo?.fromAddr
            let coin = pageHolderVC.mBnbSwapInfo?.getSendCoin()
            WUtils.showCoinDp(coin!, refundAmountDenom, refundAmountLabel, chainType!)
            
        } else if (self.chainType! == ChainType.KAVA_MAIN || self.chainType! == ChainType.KAVA_TEST) {
            feeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, feeAmountLabel.font, 6, 6)
            swapIdLabel.text = pageHolderVC.mHtlcRefundSwapId!
            refundToLabel.text = pageHolderVC.mKavaSwapInfo?.result.sender
            let coin = pageHolderVC.mKavaSwapInfo?.result.amount[0]
            WUtils.showCoinDp(coin!, refundAmountDenom, refundAmountLabel, chainType!)
            
        }
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
            if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
            } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
                self.onGenBnbRefund()
            }
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        var url: String?
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let accountInfo = KavaAccountInfo.init(info)
                _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                self.onGenKavaRefund()
                
            case .failure(let error):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
        }
    }
    
    func onGenKavaRefund() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                let msg = MsgGenerator.genRefundAtomicSwap(self.pageHolderVC.mAccount!.account_address,
                                                           self.pageHolderVC.mHtlcRefundSwapId!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                    let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainId(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner), String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(stdMsg)
                    let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                    let rawData: Data? = rawResult!.data(using: .utf8)
                    let hash = Crypto.sha256(rawData!)
                    let signedData: Data? = try Crypto.sign(hash, privateKey: pKey.privateKey())
                    
                    var genedSignature = Signature.init()
                    var genPubkey =  PublicKey.init()
                    genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
                    genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
                    genedSignature.pub_key = genPubkey
                    genedSignature.signature = WKey.convertSignature(signedData!)
                    genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                    genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                    
                    var signatures: Array<Signature> = Array<Signature>()
                    signatures.append(genedSignature)
                    
                    stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
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
                    var url: String?
                    if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                        url = KAVA_TEST_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Refund ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) { print("Refund error ", error) }
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN ||
                                    self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                                    txResult["type"] = TASK_TYPE_HTLC_REFUND
                                    self.onStartTxDetail(txResult)
                                }
                            })
                        }
                    }

                } catch {
                    if(SHOW_LOG) { print(error) }
                }
            });
        }
    }
    
    func onGenBnbRefund() {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            var binance: BinanceChain?
            var pKey: HDPrivateKey?
            var wallet = Wallet()
            var txResult = [String:Any]()
            
            if (self.pageHolderVC.chainType! == ChainType.BINANCE_MAIN) {
                //For Binance main-net refund
                binance = BinanceChain(endpoint: BinanceChain.Endpoint.mainnet)
                pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                wallet = Wallet(privateKey: pKey!.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.mainnet)
                
            } else {
                //For Binance test-net refund
                binance = BinanceChain(endpoint: BinanceChain.Endpoint.testnet)
                pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                wallet = Wallet(privateKey: pKey!.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.testnet)
            }
            
            wallet.synchronise(){ (error) in
                if let error = error {
                    if(SHOW_LOG) { print(error) }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            txResult["type"] = TASK_TYPE_HTLC_REFUND
                            self.onStartTxDetail(txResult)
                        })
                    }
                }
                
                let bnbRefundMsg = Message.refundHtlc(swapId: self.pageHolderVC.mHtlcRefundSwapId!,
                                                      memo: self.pageHolderVC.mMemo!,
                                                      wallet: wallet)
                
                binance!.broadcast(message: bnbRefundMsg, sync: true) { (response) in
                    if let error = response.error {
                        if(SHOW_LOG) { print(error.localizedDescription) }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                txResult["type"] = TASK_TYPE_HTLC_REFUND
                                self.onStartTxDetail(txResult)
                            })
                        }
                    }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            txResult["type"] = TASK_TYPE_HTLC_REFUND
                            txResult["hash"] = response.broadcast[0].hash
                            self.onStartTxDetail(txResult)
                        })
                    }
                    print(response.broadcast)
                }
            }
        }
    }
}
