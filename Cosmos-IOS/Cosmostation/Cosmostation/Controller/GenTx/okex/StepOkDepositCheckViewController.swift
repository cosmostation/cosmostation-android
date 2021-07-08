//
//  StepOkDepositCheckViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SwiftKeychainWrapper
import HDWalletKit

class StepOkDepositCheckViewController: BaseViewController, PasswordViewDelegate, SBCardPopupDelegate {
    @IBOutlet weak var toDepositAmountLabel: UILabel!
    @IBOutlet weak var toDepositAmountDenom: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, toDepositAmountDenom)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeAmountDenom)
    }
    
    @IBAction func onClickConfirm(_ sender: Any) {
        let popupVC = DelegateWarnPopup(nibName: "DelegateWarnPopup", bundle: nil)
        popupVC.warnImgType = 14
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
        if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            toDepositAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mOkToStaking.amount, toDepositAmountLabel.font, 0, 18)
            feeAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 0, 18)
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
                self.onGenOkDepositTx()
                
            case .failure(let error):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
        }
    }
    
    func onGenOkDepositTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let privateKey = KeyFac.getPrivateRaw(words, self.pageHolderVC.mAccount!)
            let publicKey = KeyFac.getPublicRaw(words, self.pageHolderVC.mAccount!)
            
            do {
                let msg = MsgGenerator.genOkDepositMsg(self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mOkToStaking)
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
                            print("Deposit ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            print("Deposit error ", error)
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
