//
//  StepWithdrawCdpCheckViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/31.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper


class StepWithdrawCdpCheckViewController: BaseViewController, PasswordViewDelegate {

    @IBOutlet weak var cAmountLabel: UILabel!
    @IBOutlet weak var cDenomLabel: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var beforeRiskRate: UILabel!
    @IBOutlet weak var afterRiskRate: UILabel!
    @IBOutlet weak var adjuestedcAmount: UILabel!
    @IBOutlet weak var adjuestedcAmountDenom: UILabel!
    @IBOutlet weak var beforeLiquidationPriceTitle: UILabel!
    @IBOutlet weak var beforeLiquidationPrice: UILabel!
    @IBOutlet weak var afterLiquidationPriceTitle: UILabel!
    @IBOutlet weak var afterLiquidationPrice: UILabel!
    @IBOutlet weak var memo: UILabel!
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
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
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        let cDenom = pageHolderVC.mCDenom
        let cDpDecimal = WUtils.getKavaCoinDecimal(cDenom!)

        let cAmount = NSDecimalNumber.init(string: pageHolderVC.mCollateral.amount)
        let fAmount = NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount)
        
        cDenomLabel.text = cDenom?.uppercased()
        cAmountLabel.attributedText = WUtils.displayAmount2(cAmount.stringValue, cAmountLabel.font!, cDpDecimal, cDpDecimal)
        
        feeAmountLabel.attributedText = WUtils.displayAmount2(fAmount.stringValue, feeAmountLabel.font!, 6, 6)

        WUtils.showRiskRate(pageHolderVC.beforeRiskRate!, beforeRiskRate, _rateIamg: nil)
        WUtils.showRiskRate(pageHolderVC.afterRiskRate!, afterRiskRate, _rateIamg: nil)
        
        adjuestedcAmount.attributedText = WUtils.displayAmount2(pageHolderVC.totalDepositAmount!.stringValue, adjuestedcAmount.font!, cDpDecimal, cDpDecimal)
        adjuestedcAmountDenom.text = cDenom?.uppercased()
        
        beforeLiquidationPriceTitle.text = String(format: NSLocalizedString("before_liquidation_price_format", comment: ""), cDenom!.uppercased())
        beforeLiquidationPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.beforeLiquidationPrice!.stringValue, 4, beforeLiquidationPrice.font)
        
        afterLiquidationPriceTitle.text = String(format: NSLocalizedString("after_liquidation_price_format", comment: ""), cDenom!.uppercased())
        afterLiquidationPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.afterLiquidationPrice!.stringValue, 4, afterLiquidationPrice.font)
        
        memo.text = pageHolderVC.mMemo
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        var url: String?
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("res : ", res)
                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenWithdrawCdpTx()
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenWithdrawCdpTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                let msg = MsgGenerator.genGetWithdrawCdpMsg(self.pageHolderVC.chainType!,
                                                            self.pageHolderVC.mAccount!.account_address,
                                                            self.pageHolderVC.mAccount!.account_address,
                                                            self.pageHolderVC.mCollateral,
                                                            self.pageHolderVC.mCollateralParam?.type)
                
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
                if (SHOW_LOG) { print(error) }
            }
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    var url: String?
                    if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                        url = KAVA_TEST_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.validate().responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("WithdrawCdp ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("WithdrawCdp error ", error)
                            }
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }

                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                                    txResult["type"] = COSMOS_MSG_TYPE_DELEGATE
                                    self.onStartTxDetail(txResult)
                                }
                            })
                        }
                    }

                } catch {
                    if (SHOW_LOG) { print(error) }
                }
            });
        }
    }
    
    
}
