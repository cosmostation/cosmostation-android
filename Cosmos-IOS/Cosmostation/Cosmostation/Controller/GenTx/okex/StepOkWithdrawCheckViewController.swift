//
//  StepOkWithdrawCheckViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper
import HDWalletKit

class StepOkWithdrawCheckViewController: BaseViewController, PasswordViewDelegate {
    @IBOutlet weak var toWithdrawAmountLabel: UILabel!
    @IBOutlet weak var toWithdrawAmountDenom: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, toWithdrawAmountDenom)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeAmountDenom)
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
        if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            toWithdrawAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mOkToWithdraw.amount, toWithdrawAmountLabel.font, 0, 18)
            feeAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 0, 18)
        }
        memoLabel.text = pageHolderVC.mMemo
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }

    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(pageHolderVC.chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? NSDictionary else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let okAccountInfo = OkAccountInfo.init(info)
                _ = BaseData.instance.updateAccount(WUtils.getAccountWithOkAccountInfo(account, okAccountInfo))
                BaseData.instance.mOkAccountInfo = okAccountInfo
                self.onGenOkWithdrawTx()
                
            case .failure(let error):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
        }
    }
    
    func onGenOkWithdrawTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                let msg = MsgGenerator.genOkWithdarwMsg(self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mOkToWithdraw)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                if (self.pageHolderVC.chainType! == ChainType.OKEX_MAIN || self.pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                    let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(), String(self.pageHolderVC.mAccount!.account_account_numner),
                                                           String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                    
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(stdMsg)
                    let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                    let rawData: Data? = rawResult!.data(using: .utf8)
                    
                    if (self.pageHolderVC.mAccount!.account_new_bip44) {
                        let hash = HDWalletKit.Crypto.sha3keccak256(data: rawData!)
                        let signedData: Data? = try ECDSA.compactsign(hash, privateKey: pKey.privateKey().raw)
                        
                        var genedSignature = Signature.init()
                        var genPubkey =  PublicKey.init()
                        genPubkey.type = ETHERMINT_KEY_TYPE_PUBLIC
                        genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
                        genedSignature.pub_key = genPubkey
                        genedSignature.signature = signedData!.base64EncodedString()
                        genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                        genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                        
                        var signatures: Array<Signature> = Array<Signature>()
                        signatures.append(genedSignature)
                        
                        stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                        
                    } else {
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
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.pageHolderVC.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Send ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) { print("send error ", error) }
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

}
