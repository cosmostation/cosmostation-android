//
//  KavaIncentiveClaim3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/29.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import HDWalletKit
import SwiftKeychainWrapper

class KavaIncentiveClaim3ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var txFeeAmountLabel: UILabel!
    @IBOutlet weak var txFeeDenomLabel: UILabel!
    @IBOutlet weak var kavaIncentiveAmountLabel: UILabel!
    @IBOutlet weak var hardIncentiveAmountLabel: UILabel!
    @IBOutlet weak var swpIncentiveAmountLabel: UILabel!
    @IBOutlet weak var lockupLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mIncentiveParam: IncentiveParam!
    var mIncentiveRewards: IncentiveReward!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        mIncentiveParam = BaseData.instance.mIncentiveParam
        mIncentiveRewards = BaseData.instance.mIncentiveRewards
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        WUtils.showCoinDp(pageHolderVC.mFee!.amount[0].denom, pageHolderVC.mFee!.amount[0].amount, txFeeDenomLabel, txFeeAmountLabel, chainType!)
        
        var kavaIncentiveAmount = mIncentiveRewards.getIncentiveAmount(KAVA_MAIN_DENOM)
        var hardIncentiveAmount = mIncentiveRewards.getIncentiveAmount(KAVA_HARD_DENOM)
        var swpIncentiveAmount = mIncentiveRewards.getIncentiveAmount(KAVA_SWAP_DENOM)
        
        if (pageHolderVC.mIncentiveMultiplier == "small") {
            lockupLabel.text = "1 Month"
            kavaIncentiveAmount = kavaIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_MAIN_DENOM, 0), withBehavior: WUtils.handler0)
            hardIncentiveAmount = hardIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_HARD_DENOM, 0), withBehavior: WUtils.handler0)
            swpIncentiveAmount = swpIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_SWAP_DENOM, 0), withBehavior: WUtils.handler0)
            
        } else {
            lockupLabel.text = "12 Month"
            kavaIncentiveAmount = kavaIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_MAIN_DENOM, 1), withBehavior: WUtils.handler0)
            hardIncentiveAmount = hardIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_HARD_DENOM, 1), withBehavior: WUtils.handler0)
            swpIncentiveAmount = swpIncentiveAmount.multiplying(by: mIncentiveParam.getFactor(KAVA_SWAP_DENOM, 1), withBehavior: WUtils.handler0)
        }
        
        kavaIncentiveAmountLabel.attributedText = WUtils.displayAmount2(kavaIncentiveAmount.stringValue, kavaIncentiveAmountLabel.font!, 6, 6)
        hardIncentiveAmountLabel.attributedText = WUtils.displayAmount2(hardIncentiveAmount.stringValue, hardIncentiveAmountLabel.font!, 6, 6)
        swpIncentiveAmountLabel.attributedText = WUtils.displayAmount2(swpIncentiveAmount.stringValue, swpIncentiveAmountLabel.font!, 6, 6)
        
        memoLabel.text = pageHolderVC.mMemo
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
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let accountInfo = KavaAccountInfo.init(info)
                _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                self.onGenClaimIncentiveAllTx()
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenClaimIncentiveAllTx() {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            var msgList = Array<Msg>()
            if (self.mIncentiveRewards.getMintingRewardAmount().compare(NSDecimalNumber.zero).rawValue > 0) {
                let msg = MsgGenerator.genClaimUSDXMintingRewardMsg(self.chainType!,
                                                                    self.pageHolderVC.mAccount!.account_address,
                                                                    self.pageHolderVC.mIncentiveMultiplier!)
                msgList.append(msg)
            }
            if (self.mIncentiveRewards.getHardRewardDenoms().count > 0) {
                var denoms_to_claims = Array<DenomsToClaim>()
                for denom in self.mIncentiveRewards.getHardRewardDenoms(){
                    denoms_to_claims.append(DenomsToClaim.init(denom, self.pageHolderVC.mIncentiveMultiplier!))
                }
                let msg = MsgGenerator.genClaimHardRewardMsg(self.chainType!,
                                                             self.pageHolderVC.mAccount!.account_address,
                                                             self.pageHolderVC.mIncentiveMultiplier!,
                                                             denoms_to_claims)
                msgList.append(msg)
            }
            if (self.mIncentiveRewards.getDelegatorRewardDenoms().count > 0) {
                var denoms_to_claims = Array<DenomsToClaim>()
                for denom in self.mIncentiveRewards.getDelegatorRewardDenoms(){
                    denoms_to_claims.append(DenomsToClaim.init(denom, self.pageHolderVC.mIncentiveMultiplier!))
                }
                let msg = MsgGenerator.genClaimDelegatorRewardMsg(self.chainType!,
                                                                  self.pageHolderVC.mAccount!.account_address,
                                                                  self.pageHolderVC.mIncentiveMultiplier!,
                                                                  denoms_to_claims)
                msgList.append(msg)
            }
            if (self.mIncentiveRewards.getSwapRewardDenoms().count > 0) {
                var denoms_to_claims = Array<DenomsToClaim>()
                for denom in self.mIncentiveRewards.getSwapRewardDenoms(){
                    denoms_to_claims.append(DenomsToClaim.init(denom, self.pageHolderVC.mIncentiveMultiplier!))
                }
                let msg = MsgGenerator.genClaimSwapRewardMsg(self.chainType!,
                                                             self.pageHolderVC.mAccount!.account_address,
                                                             self.pageHolderVC.mIncentiveMultiplier!,
                                                             denoms_to_claims)
                msgList.append(msg)
            }
            
            
            
            
            let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(self.chainType),
                                                   String(self.pageHolderVC.mAccount!.account_account_numner),
                                                   String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                   msgList,
                                                   self.pageHolderVC.mFee!,
                                                   self.pageHolderVC.mMemo!)
            
            let stdTx = KeyFac.getStdTx(words, msgList, stdMsg, self.pageHolderVC.mAccount!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    print("params ", params)
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("onGenWithdrawTx ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            print("onGenWithdrawTx error ", error)
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }

                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                txResult["type"] = COSMOS_MSG_TYPE_DELEGATE
                                self.onStartTxDetail(txResult)
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
