//
//  StepChangeCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class StepChangeCheckViewController: BaseViewController, PasswordViewDelegate {
    

    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    @IBOutlet weak var rewardAddressChangeFee: UILabel!
    @IBOutlet weak var rewardAddressChangeDenom: UILabel!
    @IBOutlet weak var currentRewardAddress: UILabel!
    @IBOutlet weak var newRewardAddress: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, rewardAddressChangeDenom)
    }
    
    func onUpdateView() {
        let feeAmout = WUtils.stringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            rewardAddressChangeFee.attributedText = WUtils.displayAmount(feeAmout.stringValue, rewardAddressChangeFee.font, 6, pageHolderVC.chainType!)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            rewardAddressChangeFee.attributedText = WUtils.displayAmount(feeAmout.stringValue, rewardAddressChangeFee.font, 18, pageHolderVC.chainType!)
        }
        currentRewardAddress.text = pageHolderVC.mCurrentRewardAddress
        newRewardAddress.text = pageHolderVC.mToChangeRewardAddress
        currentRewardAddress.adjustsFontSizeToFitWidth = true
        newRewardAddress.adjustsFontSizeToFitWidth = true
        memoLabel.text = pageHolderVC.mMemo
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBefore(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        let noticeAlert = UIAlertController(title: NSLocalizedString("reward_address_warnning_title", comment: ""), message: NSLocalizedString("reward_address_notice_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .destructive, handler: { _ in
            self.onShowPasswordCheck()
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onShowPasswordCheck() {
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
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
                    self.onGenModifyRewardAddressTx()
                case .failure( _):
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                }
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let request = Alamofire.request(IRIS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    self.onGenModifyRewardAddressTx()
                case .failure( _):
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                }
            }
        }
    }
    
    func onGenModifyRewardAddressTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                
                let msg = MsgGenerator.genGetModifyRewardAddressMsg(self.pageHolderVC.mAccount!.account_address,
                                                                    self.pageHolderVC.mToChangeRewardAddress!,
                                                                    self.pageHolderVC.chainType!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                
                let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainName(self.pageHolderVC.mAccount!.account_base_chain),
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
                    var url = "";
                    if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                        url = CSS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                        url = IRIS_LCD_URL_BORAD_TX
                    }
                    let request = Alamofire.request(url, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("AddressChange ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("AddressChange error ", error)
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
                    
                    
                }catch {
                    print(error)
                }
            });
        
        }
    }
}
