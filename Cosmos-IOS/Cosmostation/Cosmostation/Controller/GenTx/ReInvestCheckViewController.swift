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
    @IBOutlet weak var rewardDenomLabel: UILabel!
    @IBOutlet weak var feeLabel: UILabel!
    @IBOutlet weak var feeDenomLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var currentDelegateAmount: UILabel!
    @IBOutlet weak var currentDenom: UILabel!
    @IBOutlet weak var expectedDelegateAmount: UILabel!
    @IBOutlet weak var expectedDenom: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, rewardDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.chainType!, currentDenom)
        WUtils.setDenomTitle(pageHolderVC.chainType!, expectedDenom)
    }
    
    func onUpdateView() {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            rewardLabel.attributedText = WUtils.displayAmount(pageHolderVC.mReinvestReward!.amount, rewardLabel.font, 6, pageHolderVC.chainType!)
            feeLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeLabel.font, 6, pageHolderVC.chainType!)
            
            if let bonding = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address) {
                currentDelegateAmount.attributedText = WUtils.displayAmount(bonding.getBondingAmount(pageHolderVC.mTargetValidator!).stringValue, currentDelegateAmount.font, 6, pageHolderVC.chainType!)
                let expected = (NSDecimalNumber.init(string: pageHolderVC.mReinvestReward!.amount)).adding(bonding.getBondingAmount(pageHolderVC.mTargetValidator!))
                expectedDelegateAmount.attributedText = WUtils.displayAmount(expected.stringValue, expectedDelegateAmount.font, 6, pageHolderVC.chainType!)
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            rewardLabel.attributedText = WUtils.displayAmount(pageHolderVC.mReinvestReward!.amount, rewardLabel.font, 18, pageHolderVC.chainType!)
            feeLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeLabel.font, 18, pageHolderVC.chainType!)
            
            if let bonding = BaseData.instance.selectBondingWithValAdd(pageHolderVC.mAccount!.account_id, pageHolderVC.mTargetValidator!.operator_address) {
                currentDelegateAmount.attributedText = WUtils.displayAmount(bonding.getBondingAmount(pageHolderVC.mTargetValidator!).stringValue, currentDelegateAmount.font, 18, pageHolderVC.chainType!)
                let expected = (NSDecimalNumber.init(string: pageHolderVC.mReinvestReward!.amount)).adding(bonding.getBondingAmount(pageHolderVC.mTargetValidator!))
                expectedDelegateAmount.attributedText = WUtils.displayAmount(expected.stringValue, expectedDelegateAmount.font, 18, pageHolderVC.chainType!)
            }
        }
        validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
        memoLabel.text = pageHolderVC.mMemo
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
        var url: String?
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
             url = CSS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
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
                    self.onGenReinvest()
                    
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
                    self.onGenReinvest()
                    
                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenReinvest()
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenReinvest() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                var msgList = Array<Msg>()
                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
                    self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
                    self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    let rewardMsg = MsgGenerator.genGetRewardMsg(self.pageHolderVC.mAccount!.account_address,
                                                                 self.pageHolderVC.mTargetValidator!.operator_address,
                                                                 self.pageHolderVC.chainType!)
                    
                    let delegatemsg = MsgGenerator.genDelegateMsg(self.pageHolderVC.mAccount!.account_address,
                                                                  self.pageHolderVC.mTargetValidator!.operator_address,
                                                                  self.pageHolderVC.mReinvestReward!,
                                                                  self.pageHolderVC.chainType!)
                    msgList.append(rewardMsg)
                    msgList.append(delegatemsg)
                    
                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    let rewardMsg = MsgGenerator.genGetRewardMsg(self.pageHolderVC.mAccount!.account_address,
                                                                 self.pageHolderVC.mTargetValidator!.operator_address,
                                                                 self.pageHolderVC.chainType!)
                    
                    let delegatemsg = MsgGenerator.genDelegateMsg(self.pageHolderVC.mAccount!.account_address,
                                                                  self.pageHolderVC.mTargetValidator!.operator_address,
                                                                  self.pageHolderVC.mReinvestReward!,
                                                                  self.pageHolderVC.chainType!)
                    msgList.append(rewardMsg)
                    msgList.append(delegatemsg)
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
                genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                
                var signatures: Array<Signature> = Array<Signature>()
                signatures.append(genedSignature)
                
                stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                print("stdTx", stdTx)
            } catch {
                print(error)
                
            }
            
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
//                    print("params ", params)
                    var url: String?
                    if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                        url = CSS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                        url = IRIS_LCD_URL_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                        url = KAVA_TEST_BORAD_TX
                    }
            
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.validate()
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
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }
                        
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN
                                    || self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN
                                    || self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                                    txResult["type"] = COSMOS_MULTI_MSG_TYPE_REINVEST
                                    self.onStartTxDetail(txResult)
                                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                                    txResult["type"] = COSMOS_MULTI_MSG_TYPE_REINVEST
                                    self.onStartTxResult(txResult)
                                }
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
