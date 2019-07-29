//
//  ReInvestCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/06/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class ReInvestCheckViewController: BaseViewController, PasswordViewDelegate {
    
    
    @IBOutlet weak var rewardLabel: UILabel!
    @IBOutlet weak var feeLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var currentDelegateAmount: UILabel!
    @IBOutlet weak var expectedDelegateAmount: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    func onUpdateView() {
        rewardLabel.attributedText = WUtils.displayAmout(pageHolderVC.mReinvestReward!.amount, rewardLabel.font, 6)
        feeLabel.attributedText = WUtils.displayAmout((pageHolderVC.mFee?.amount[0].amount)!, feeLabel.font, 6)
        validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
        memoLabel.text = pageHolderVC.mMemo
        
        if let bonding = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address) {
            currentDelegateAmount.attributedText = WUtils.displayAmout(bonding.getBondingAtom(pageHolderVC.mTargetValidator!).stringValue, currentDelegateAmount.font, 6)
            
            let expected = (NSDecimalNumber.init(string: pageHolderVC.mReinvestReward!.amount)).adding(bonding.getBondingAtom(pageHolderVC.mTargetValidator!))
            expectedDelegateAmount.attributedText = WUtils.displayAmout(expected.stringValue, expectedDelegateAmount.font, 6)
        }
        
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.backBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }
    

    @IBAction func onClickBack(_ sender: UIButton) {
        self.backBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    func checkIsWasteFee() -> Bool {
        let rewardSum = NSDecimalNumber.init(string: pageHolderVC.mReinvestReward!.amount)
        if(NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
            return true
        }
        return false
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        if(checkIsWasteFee()) {
            let disableAlert = UIAlertController(title: NSLocalizedString("fee_over_title", comment: ""), message: NSLocalizedString("fee_over_msg", comment: ""), preferredStyle: .alert)
            disableAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { [weak disableAlert] (_) in
                self.dismiss(animated: true, completion: nil)
            }))
            self.present(disableAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                disableAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
            return
        }
        
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
    
    @objc func dismissAlertController(){
        self.dismiss(animated: true, completion: nil)
    }
    
    
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onGenReinvest()
        }
    }
    
    
    func onGenReinvest() {
        print("onGenReinvest")
        
        DispatchQueue.global().async {
            var stakeStdTx:StakeStdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(mnemonic: words, path: UInt32(self.pageHolderVC.mAccount!.account_path)!)
                
                let rewardMsg = MsgGenerator.genGetRewardMsgReInvest(self.pageHolderVC.mAccount!.account_address,
                                                                     self.pageHolderVC.mTargetValidator!.operator_address)
                
                let delegatemsg = MsgGenerator.genDelegateMsg(self.pageHolderVC.mAccount!.account_address,
                                                      self.pageHolderVC.mTargetValidator!.operator_address,
                                                      self.pageHolderVC.mReinvestReward!)
                
                var msgList = Array<StakeMsg>()
                msgList.append(rewardMsg)
                msgList.append(delegatemsg)
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
                var rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
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
                
                stakeStdTx = MsgGenerator.genSignedTx(msgList,
                                                      self.pageHolderVC.mFee!,
                                                      self.pageHolderVC.mMemo!,
                                                      signatures)
                print("stakeStdTx", stakeStdTx)
            } catch {
                print(error)
                
            }
            
            
            DispatchQueue.main.async(execute: {
                let postTx = StakePostTx.init("sync", stakeStdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    print("params ", params)
                    let request = Alamofire.request(CSS_LCD_URL_BORAD_TX, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("ReInvest ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("ReInvest error ", error)
                            }
                        }
                        self.hideWaittingAlert()
                        txResult["type"] = COSMOS_MULTI_MSG_TYPE_REINVEST
                        self.onStartTxResult(txResult)
                    }
                    
                }catch {
                    print(error)
                }
                
            });
        }
    }
}
