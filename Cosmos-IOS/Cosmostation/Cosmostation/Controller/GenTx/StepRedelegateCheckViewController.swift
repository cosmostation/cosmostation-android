//
//  StepRedelegateCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class StepRedelegateCheckViewController: BaseViewController, PasswordViewDelegate{
    

    @IBOutlet weak var redelegateAmountLabel: UILabel!
    @IBOutlet weak var redelegateFeeLabel: UILabel!
    @IBOutlet weak var redelegateFromValLabel: UILabel!
    @IBOutlet weak var redelegateToValLabel: UILabel!
    @IBOutlet weak var redelegateMemoLabel: UILabel!
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    
    var pageHolderVC: StepGenTxViewController!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    func onUpdateView() {
        let toRedelegateAmount = WUtils.stringToDecimal(pageHolderVC.mToReDelegateAmount!.amount)
        let feeAmout = WUtils.stringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        
        redelegateAmountLabel.attributedText = WUtils.displayAmout(toRedelegateAmount.stringValue, redelegateAmountLabel.font, 6)
        redelegateFeeLabel.attributedText = WUtils.displayAmout(feeAmout.stringValue, redelegateFeeLabel.font, 6)
        redelegateFromValLabel.text = pageHolderVC.mTargetValidator?.description.moniker
        redelegateToValLabel.text = pageHolderVC.mToReDelegateValidator?.description.moniker
        redelegateMemoLabel.text = pageHolderVC.mMemo
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBefore.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBefore(_ sender: UIButton) {
        self.btnBefore.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        self.btnBefore.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
        
    }
    
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onGenRedelegateTx()
        }
    }
    
    func onGenRedelegateTx() {
        print("onGenRedelegateTx")
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(mnemonic: words, path: UInt32(self.pageHolderVC.mAccount!.account_path)!)
                let msg = MsgGenerator.genGetRedelegateMsg(self.pageHolderVC.mAccount!.account_address,
                                                           self.pageHolderVC.mTargetValidator!.operator_address,
                                                           self.pageHolderVC.mToReDelegateValidator!.operator_address,
                                                           self.pageHolderVC.mToReDelegateAmount!,
                                                           self.pageHolderVC.userChain!)
                
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
                
                var signatures: Array<Signature> = Array<Signature>()
                signatures.append(genedSignature)
                
                stdTx = MsgGenerator.genSignedTx(msgList,
                                                 self.pageHolderVC.mFee!,
                                                 self.pageHolderVC.mMemo!,
                                                 signatures)
                
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
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("redelegate error ", error)
                            }
                            
                        }
                        self.hideWaittingAlert()
                        txResult["type"] = COSMOS_MSG_TYPE_REDELEGATE2
                        self.onStartTxResult(txResult)
                    }
                    
                    
                }catch {
                    print(error)
                }
            });
        }
        
    }
    

}
