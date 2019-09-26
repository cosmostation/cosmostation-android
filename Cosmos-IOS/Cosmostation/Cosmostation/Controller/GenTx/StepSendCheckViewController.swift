//
//  StepSendCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class StepSendCheckViewController: BaseViewController, PasswordViewDelegate{

    @IBOutlet weak var mToSendAmountLabel: UILabel!
    @IBOutlet weak var mFeeAmountLabel: UILabel!
    @IBOutlet weak var mTotalSpendLabel: UILabel!
    @IBOutlet weak var mTotalSpendPrice: UILabel!
    
    @IBOutlet weak var mCurrentAvailable: UILabel!
    @IBOutlet weak var mReminaingAvailable: UILabel!
    @IBOutlet weak var mReminaingPrice: UILabel!
    
    @IBOutlet weak var mToAddressLabel: UILabel!
    @IBOutlet weak var mMemoLabel: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    @IBOutlet weak var mToSendDenomLabel: UILabel!
    @IBOutlet weak var mFeeDenomTitle: UILabel!
    @IBOutlet weak var mToSpendDenomTitle: UILabel!
    @IBOutlet weak var mCurrentBalanceDenomTitle: UILabel!
    @IBOutlet weak var mRemainBalanceTitle: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.userChain!, mToSendDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.userChain!, mFeeDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.userChain!, mToSpendDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.userChain!, mCurrentBalanceDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.userChain!, mRemainBalanceTitle)
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
        let toSendAmount = WUtils.stringToDecimal(pageHolderVC.mToSendAmount[0].amount)
        let feeAmout = WUtils.stringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        var currentAva = NSDecimalNumber.zero
        var priceTic:NSDictionary?
        if (BaseData.instance.getMarket() == 0) {
            priceTic = BaseData.instance.getPriceTicCgc()
        } else {
            priceTic = BaseData.instance.getPriceTicCmc()
        }
        
        
        if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            for balance in pageHolderVC.mBalances {
                if(balance.balance_denom == COSMOS_MAIN_DENOM) {
                    currentAva = currentAva.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            }
            mToSendAmountLabel.attributedText = WUtils.displayAmount(toSendAmount.stringValue, mToSendAmountLabel.font, 6, pageHolderVC.userChain!)
            mFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmout.stringValue, mFeeAmountLabel.font, 6, pageHolderVC.userChain!)
            mTotalSpendLabel.attributedText = WUtils.displayAmount(feeAmout.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, pageHolderVC.userChain!)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount(currentAva.stringValue, mCurrentAvailable.font, 6, pageHolderVC.userChain!)
            mReminaingAvailable.attributedText = WUtils.displayAmount(currentAva.subtracting(feeAmout).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, pageHolderVC.userChain!)
            
            if let price = priceTic?.value(forKeyPath: getPricePath()) as? Double {
                let priceValue = NSDecimalNumber(value: price)
                var totalSpendDpPrice = NSDecimalNumber.zero
                var remindDpPrice = NSDecimalNumber.zero
                if(BaseData.instance.getCurrency() == 5) {
                    totalSpendDpPrice = (feeAmout.adding(toSendAmount)).dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler8)
                    remindDpPrice = (currentAva.subtracting(feeAmout).subtracting(toSendAmount)).dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler8)
                } else {
                    totalSpendDpPrice = (feeAmout.adding(toSendAmount)).dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2)
                    remindDpPrice = (currentAva.subtracting(feeAmout).subtracting(toSendAmount)).dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2)
                }
                mTotalSpendPrice.attributedText = WUtils.displayPrice(totalSpendDpPrice, BaseData.instance.getCurrency(), BaseData.instance.getCurrencySymbol(), font: mTotalSpendPrice.font);
                mReminaingPrice.attributedText = WUtils.displayPrice(remindDpPrice, BaseData.instance.getCurrency(), BaseData.instance.getCurrencySymbol(), font: mReminaingPrice.font);
            }
            
        } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            for balance in pageHolderVC.mBalances {
                if(balance.balance_denom == IRIS_MAIN_DENOM) {
                    currentAva = currentAva.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            }
            mToSendAmountLabel.attributedText = WUtils.displayAmount(toSendAmount.stringValue, mToSendAmountLabel.font, 18, pageHolderVC.userChain!)
            mFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmout.stringValue, mFeeAmountLabel.font, 18, pageHolderVC.userChain!)
            mTotalSpendLabel.attributedText = WUtils.displayAmount(feeAmout.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 18, pageHolderVC.userChain!)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount(currentAva.stringValue, mCurrentAvailable.font, 18, pageHolderVC.userChain!)
            mReminaingAvailable.attributedText = WUtils.displayAmount(currentAva.subtracting(feeAmout).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 18, pageHolderVC.userChain!)
            
            if let price = priceTic?.value(forKeyPath: getPricePath()) as? Double {
                let priceValue = NSDecimalNumber(value: price)
                var totalSpendDpPrice = NSDecimalNumber.zero
                var remindDpPrice = NSDecimalNumber.zero
                if(BaseData.instance.getCurrency() == 5) {
                    totalSpendDpPrice = (feeAmout.adding(toSendAmount)).dividing(by: 1000000000000000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler8)
                    remindDpPrice = (currentAva.subtracting(feeAmout).subtracting(toSendAmount)).dividing(by: 1000000000000000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler8)
                } else {
                    totalSpendDpPrice = (feeAmout.adding(toSendAmount)).dividing(by: 1000000000000000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2)
                    remindDpPrice = (currentAva.subtracting(feeAmout).subtracting(toSendAmount)).dividing(by: 1000000000000000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2)
                }
                mTotalSpendPrice.attributedText = WUtils.displayPrice(totalSpendDpPrice, BaseData.instance.getCurrency(), BaseData.instance.getCurrencySymbol(), font: mTotalSpendPrice.font);
                mReminaingPrice.attributedText = WUtils.displayPrice(remindDpPrice, BaseData.instance.getCurrency(), BaseData.instance.getCurrencySymbol(), font: mReminaingPrice.font);
            }
        }
        print("currentAva ", currentAva)
        
        mToAddressLabel.text = pageHolderVC.mToSendRecipientAddress
        mToAddressLabel.adjustsFontSizeToFitWidth = true
        mMemoLabel.text = pageHolderVC.mMemo
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }
    
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let request = Alamofire.request(CSS_LCD_URL_ACCOUNT_INFO + account.account_address, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    guard let responseData = res as? NSDictionary,
//                        let info = responseData.object(forKey: "result") as? [String : Any] else {
//                            _ = BaseData.instance.deleteBalance(account: account)
//                            self.hideWaittingAlert()
//                            self.onShowToast(NSLocalizedString("error_network", comment: ""))
//                            return
//                    }
                    //TODO rollback cosmos-hub2
                    guard let info = res as? [String : Any] else {
                            _ = BaseData.instance.deleteBalance(account: account)
                            self.hideWaittingAlert()
                            self.onShowToast(NSLocalizedString("error_network", comment: ""))
                            return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    self.onGenSendTx()
                case .failure( _):
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                }
            }
        } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
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
                    self.onGenSendTx()
                case .failure( _):
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                }
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
                let pKey = WKey.getHDKeyFromWords(mnemonic: words, path: UInt32(self.pageHolderVC.mAccount!.account_path)!)
                
                let msg = MsgGenerator.genGetSendMsg(self.pageHolderVC.mAccount!.account_address,
                                                     self.pageHolderVC.mToSendRecipientAddress!,
                                                     self.pageHolderVC.mToSendAmount,
                                                     self.pageHolderVC.userChain!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                
                if (self.pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
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
                    
                } else if (self.pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    let irisStdMsg = MsgGenerator.getIrisToSignMsg(WUtils.getChainName(self.pageHolderVC.mAccount!.account_base_chain),
                                                                   String(self.pageHolderVC.mAccount!.account_account_numner),
                                                                   String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                                   msgList,
                                                                   self.pageHolderVC.mFee!,
                                                                   self.pageHolderVC.mMemo!)
                    
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
                    var url = "";
                    if (self.pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                        url = CSS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                        url = IRIS_LCD_URL_BORAD_TX
                    }
                    let request = Alamofire.request(url, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Send ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("send error ", error)
                            }
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                if (self.pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                                    txResult["type"] = COSMOS_MSG_TYPE_TRANSFER2
                                } else if (self.pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                                    txResult["type"] = IRIS_MSG_TYPE_TRANSFER
                                }
                                self.onStartTxResult(txResult)
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
