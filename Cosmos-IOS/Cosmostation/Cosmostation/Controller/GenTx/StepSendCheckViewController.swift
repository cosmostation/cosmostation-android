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
import BinanceChain
import SwiftKeychainWrapper

class StepSendCheckViewController: BaseViewController, PasswordViewDelegate{

    @IBOutlet weak var mToSendAmountLabel: UILabel!
    @IBOutlet weak var mFeeAmountLabel: UILabel!
    @IBOutlet weak var mTotalSpendTitle: UILabel!
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
        WUtils.setDenomTitle(pageHolderVC.chainType!, mToSendDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mFeeDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mToSpendDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mCurrentBalanceDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mRemainBalanceTitle)
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
        let feeAmount = WUtils.stringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        var currentAva = NSDecimalNumber.zero
        
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            currentAva = pageHolderVC.mAccount!.getAtomBalance()
            mToSendAmountLabel.attributedText = WUtils.displayAmount(toSendAmount.stringValue, mToSendAmountLabel.font, 6, pageHolderVC.chainType!)
            mFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, mFeeAmountLabel.font, 6, pageHolderVC.chainType!)
            mTotalSpendLabel.attributedText = WUtils.displayAmount(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, pageHolderVC.chainType!)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount(currentAva.stringValue, mCurrentAvailable.font, 6, pageHolderVC.chainType!)
            mReminaingAvailable.attributedText = WUtils.displayAmount(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, pageHolderVC.chainType!)
            
            mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            currentAva = pageHolderVC.mAccount!.getIrisBalance()
            mToSendAmountLabel.attributedText = WUtils.displayAmount(toSendAmount.stringValue, mToSendAmountLabel.font, 18, pageHolderVC.chainType!)
            mFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, mFeeAmountLabel.font, 18, pageHolderVC.chainType!)
            mTotalSpendLabel.attributedText = WUtils.displayAmount(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 18, pageHolderVC.chainType!)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount(currentAva.stringValue, mCurrentAvailable.font, 18, pageHolderVC.chainType!)
            mReminaingAvailable.attributedText = WUtils.displayAmount(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 18, pageHolderVC.chainType!)
            
            mTotalSpendPrice.attributedText = WUtils.dpIrisValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.dpIrisValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            mToSendDenomLabel.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            mCurrentBalanceDenomTitle.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            mRemainBalanceTitle.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            
            if (pageHolderVC.mBnbToken?.symbol == BNB_MAIN_DENOM) {
                currentAva = pageHolderVC.mAccount!.getBnbBalance()
                mToSendAmountLabel.attributedText = WUtils.displayAmount(toSendAmount.stringValue, mToSendAmountLabel.font, 8, pageHolderVC.chainType!)
                mFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, mFeeAmountLabel.font, 8, pageHolderVC.chainType!)
                mTotalSpendLabel.attributedText = WUtils.displayAmount(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 8, pageHolderVC.chainType!)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount(currentAva.stringValue, mCurrentAvailable.font, 8, pageHolderVC.chainType!)
                mReminaingAvailable.attributedText = WUtils.displayAmount(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 8, pageHolderVC.chainType!)
                
                mTotalSpendPrice.attributedText = WUtils.dpBnbValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpBnbValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                
            }  else {
                mToSendDenomLabel.textColor = UIColor.white
                mCurrentBalanceDenomTitle.textColor = UIColor.white
                mRemainBalanceTitle.textColor = UIColor.white
                
                mTotalSpendTitle.isHidden = true
                mTotalSpendLabel.isHidden = true
                mToSpendDenomTitle.isHidden = true
                mTotalSpendPrice.isHidden = true
                mReminaingPrice.isHidden = true
                
                currentAva = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mBnbToken!.symbol)
                mToSendAmountLabel.attributedText = WUtils.displayAmount(toSendAmount.stringValue, mToSendAmountLabel.font, 8, pageHolderVC.chainType!)
                mFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, mFeeAmountLabel.font, 8, pageHolderVC.chainType!)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount(currentAva.stringValue, mCurrentAvailable.font, 8, pageHolderVC.chainType!)
                mReminaingAvailable.attributedText = WUtils.displayAmount(currentAva.subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 8, pageHolderVC.chainType!)
                
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            currentAva = pageHolderVC.mAccount!.getKavaBalance()
            mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, 6)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, 6)
            mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, 6)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, 6)
            mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, 6)
            
            mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            
        }
        
        mToAddressLabel.text = pageHolderVC.mToSendRecipientAddress
        mToAddressLabel.adjustsFontSizeToFitWidth = true
        mMemoLabel.text = pageHolderVC.mMemo
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
                pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN ||
                pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
            } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                self.onGenBnbSendTx()
            }
        }
    }
    
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        var url: String?
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
             url = CSS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
//                    guard let responseData = res as? NSDictionary,
//                        let info = responseData.object(forKey: "result") as? [String : Any] else {
//                        _ = BaseData.instance.deleteBalance(account: account)
//                        self.hideWaittingAlert()
//                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
//                        return
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
                    
                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
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
                    
                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
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
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
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
                let pKey = WKey.getHDKeyFromWords(mnemonic: words, path: UInt32(self.pageHolderVC.mAccount!.account_path)!, chain: self.pageHolderVC.chainType!)
                let msg = MsgGenerator.genGetSendMsg(self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mToSendRecipientAddress!, self.pageHolderVC.mToSendAmount, self.pageHolderVC.chainType!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainName(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner), String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
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
                    
                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    let irisStdMsg = MsgGenerator.getIrisToSignMsg(WUtils.getChainName(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner),  String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
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
                    if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                        url = CSS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                        url = IRIS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
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
                                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                                    txResult["type"] = COSMOS_MSG_TYPE_TRANSFER2
                                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
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
    
    
    
    func onGenBnbSendTx() {
        print("onGenBnbSendTx")
        self.showWaittingAlert()
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            let binance = BinanceChain(endpoint: BinanceChain.Endpoint.mainnet)
            let pKey = WKey.getHDKeyFromWords(mnemonic: words, path: UInt32(self.pageHolderVC.mAccount!.account_path)!, chain: self.pageHolderVC.chainType!)
            let wallet = Wallet(privateKey: pKey.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.mainnet)
            var txResult = [String:Any]()
            
            wallet.synchronise(){ (error) in
                if let error = error {
                    if(SHOW_LOG) { print(error) }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            txResult["type"] = BNB_MSG_TYPE_TRANSFER
                            self.onStartTxResult(txResult)
                        })
                    }
                }
                let bnbMsg = Message.transfer2(symbol: self.pageHolderVC.mToSendAmount[0].denom,
                                              amount: (self.pageHolderVC.mToSendAmount[0].amount as NSString).doubleValue,
                                              to: self.pageHolderVC.mToSendRecipientAddress!,
                                              memo: self.pageHolderVC.mMemo!,
                                              wallet: wallet)
                
                
                
                binance.broadcast(message: bnbMsg, sync: true) { (response) in
                    if let error = response.error {
                        if(SHOW_LOG) { print(error.localizedDescription) }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                txResult["type"] = BNB_MSG_TYPE_TRANSFER
                                self.onStartTxResult(txResult)
                            })
                        }
                    }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            txResult["type"] = BNB_MSG_TYPE_TRANSFER
                            txResult["hash"] = response.broadcast[0].hash
                            self.onStartTxResult(txResult)
                        })
                    }
                    print(response.broadcast)
                }

            }
        }
        
    }
}
