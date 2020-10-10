//
//  VoteCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 10/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class VoteCheckViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var proposalTitle: UILabel!
    @IBOutlet weak var proposer: UILabel!
    
    @IBOutlet weak var mOpinion: UILabel!
    @IBOutlet weak var mFeeAmount: UILabel!
    @IBOutlet weak var mMemo: UILabel!
    @IBOutlet weak var mBtnBack: UIButton!
    @IBOutlet weak var mBtnConfirm: UIButton!
    @IBOutlet weak var mFeeDenomTitle: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        proposalTitle.text = pageHolderVC.mProposalTitle
        proposalTitle.adjustsFontSizeToFitWidth = true
        proposer.text = pageHolderVC.mProposer
        WUtils.setDenomTitle(pageHolderVC.chainType!, mFeeDenomTitle)
    }
    
    
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.mBtnBack.isUserInteractionEnabled = false
        self.mBtnBack.isUserInteractionEnabled = false
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
    
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.mBtnBack.isUserInteractionEnabled = true
        self.mBtnBack.isUserInteractionEnabled = true
    }
    
    
    func onUpdateView() {
        let feeAmount = WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            mOpinion.text = pageHolderVC.mVoteOpinion
            mFeeAmount.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmount.font, 6, 6)
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            mOpinion.text = pageHolderVC.mVoteOpinion
            mFeeAmount.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmount.font, 18, 18)
            
        }
        mMemo.text = pageHolderVC.mMemo
    }
    
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        var url: String?
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
             url = COSMOS_URL_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
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
                    self.onGenVoteTx()
                    
                } else if (self.pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    self.onGenVoteTx()
                    
                } else if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                    guard  let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenVoteTx()
                } else if (self.pageHolderVC.chainType! == ChainType.BAND_MAIN || self.pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
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
                    self.onGenVoteTx()
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    
    func onGenVoteTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                let msg = MsgGenerator.genGetVoteMsg(self.pageHolderVC.mAccount!.account_address,
                                                    self.pageHolderVC.mProposeId!,
                                                    self.pageHolderVC.mVoteOpinion!,
                                                    self.pageHolderVC.chainType!)
                
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                if (self.pageHolderVC.chainType! == ChainType.COSMOS_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.BAND_MAIN || self.pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
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
                    
                } else if (self.pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                    let irisStdMsg = MsgGenerator.getIrisToSignMsg(WUtils.getChainId(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner),  String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(irisStdMsg)
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
                    if (self.pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
                        url = COSMOS_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                        url = IRIS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                        url = BAND_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                        url = SECRET_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Vote ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) { print("Vote error ", error) }
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
