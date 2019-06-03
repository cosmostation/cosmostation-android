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
    
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
    }

    @IBAction func onClickBack(_ sender: Any) {
        self.backBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickConfirm(_ sender: Any) {
        let transition:CATransition = CATransition()
        transition.duration = 0.3
        transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
        transition.type = CATransitionType.moveIn
        transition.subtype = CATransitionSubtype.fromTop
        
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(transition, forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
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
        for balance in pageHolderVC.mBalances {
            if(TESTNET) {
                if(balance.balance_denom == "muon") {
                    currentAva = currentAva.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            } else {
                if(balance.balance_denom == "uatom") {
                    currentAva = currentAva.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            }
        }
        mToSendAmountLabel.attributedText = WUtils.displayAmout(toSendAmount.stringValue, mToSendAmountLabel.font, 6)
        mFeeAmountLabel.attributedText = WUtils.displayAmout(feeAmout.stringValue, mFeeAmountLabel.font, 6)
        mTotalSpendLabel.attributedText = WUtils.displayAmout(feeAmout.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6)
        
        mCurrentAvailable.attributedText = WUtils.displayAmout(currentAva.stringValue, mCurrentAvailable.font, 6)
        mReminaingAvailable.attributedText = WUtils.displayAmout(currentAva.subtracting(feeAmout).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6)
        
        mToAddressLabel.text = pageHolderVC.mToSendRecipientAddress
        mToAddressLabel.adjustsFontSizeToFitWidth = true
        
        mMemoLabel.text = pageHolderVC.mMemo
        
        guard let tic = BaseData.instance.getAtomTicCmc() else {
            return
        }
        if let price = tic.value(forKeyPath: "data.quotes.USD.price") as? Double {
            let priceValue = NSDecimalNumber(value: price)
            mTotalSpendPrice.attributedText = WUtils.displayUSD((feeAmout.adding(toSendAmount)).dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2), font: mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.displayUSD((currentAva.subtracting(feeAmout).subtracting(toSendAmount)).dividing(by: 1000000, withBehavior: WUtils.handler6).multiplying(by: priceValue).rounding(accordingToBehavior: WUtils.handler2), font: mReminaingPrice.font)
        }
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onGenSendTx()
        }
    }
    
    func onGenSendTx() {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getCosmosKeyFromWords(mnemonic: words, path: UInt32(self.pageHolderVC.mAccount!.account_path)!)
                
                let msg = MsgGenerator.genGetSendMsg(self.pageHolderVC.mAccount!.account_address,
                                                     self.pageHolderVC.mToSendRecipientAddress!,
                                                     self.pageHolderVC.mToSendAmount)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                if(FEE_FREE) {
                    self.pageHolderVC.mFee?.amount[0].amount = "1"
                }
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
                
                var signatures: Array<Signature> = Array<Signature>()
                signatures.append(genedSignature)
                
                stdTx = MsgGenerator.genSignedTx(msgList,
                                                 self.pageHolderVC.mFee!,
                                                 self.pageHolderVC.mMemo!,
                                                 signatures)
                
                
            } catch {
                if(SHOW_LOG) {
                    print(error)
                }
            }
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    let request = Alamofire.request(CSS_LCD_URL_BORAD_TX, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("send ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("send error ", error)
                            }
                            //
                        }
                        self.hideWaittingAlert()
                        txResult["type"] = COSMOS_MSG_TYPE_TRANSFER2
                        self.onStartTxResult(txResult)
                    }
                    
                    
                }catch {
                    print(error)
                }
            });
        }
    }
}
