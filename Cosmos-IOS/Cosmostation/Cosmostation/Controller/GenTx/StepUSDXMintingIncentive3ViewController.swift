//
//  StepIncentive3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import HDWalletKit
import SwiftKeychainWrapper

class StepUSDXMintingIncentive3ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    @IBOutlet weak var feeAmount: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    
    
    @IBOutlet weak var receivableAmount: UILabel!
    @IBOutlet weak var receivableDenom: UILabel!
    @IBOutlet weak var lockupTime: UILabel!
    @IBOutlet weak var type: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
//        let fAmount = NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount)
//        feeAmount.attributedText = WUtils.displayAmount2(fAmount.stringValue, feeAmount.font, 6, 6)
//        WUtils.showCoinDp(KAVA_MAIN_DENOM, pageHolderVC.mIncentiveKavaReceivable.stringValue, receivableDenom, receivableAmount, chainType!)
//
//        lockupTime.text = pageHolderVC.mIncentiveMultiplier!.months_lockup! + " Month"
//        type.text = pageHolderVC.mIncentiveMultiplier!.name!.uppercased()
//        memoLabel.text = pageHolderVC.mMemo
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
                self.onGenIncentiveRewardTx()
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenIncentiveRewardTx() {
//        DispatchQueue.global().async {
//            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
//                return
//            }
//            let msg = MsgGenerator.genClaimUSDXMintingReward(self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mIncentiveMultiplier!.name!)
//            var msgList = Array<Msg>()
//            msgList.append(msg)
//            
//            let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(self.chainType),
//                                                   String(self.pageHolderVC.mAccount!.account_account_numner),
//                                                   String(self.pageHolderVC.mAccount!.account_sequence_number),
//                                                   msgList,
//                                                   self.pageHolderVC.mFee!,
//                                                   self.pageHolderVC.mMemo!)
//            let stdTx = KeyFac.getStdTx(words, msgList, stdMsg, self.pageHolderVC.mAccount!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
//            
//            DispatchQueue.main.async(execute: {
//                let postTx = PostTx.init("sync", stdTx.value)
//                let encoder = JSONEncoder()
//                encoder.outputFormatting = .sortedKeys
//                let data = try? encoder.encode(postTx)
//                do {
//                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
//                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
//                    request.validate().responseJSON { response in
//                        var txResult = [String:Any]()
//                        switch response.result {
//                        case .success(let res):
//                            print("IncentiveReward ", res)
//                            if let result = res as? [String : Any]  {
//                                txResult = result
//                            }
//                        case .failure(let error):
//                            print("IncentiveReward error ", error)
//                            if (response.response?.statusCode == 500) {
//                                txResult["net_error"] = 500
//                            }
//                        }
//
//                        if (self.waitAlert != nil) {
//                            self.waitAlert?.dismiss(animated: true, completion: {
//                                txResult["type"] = COSMOS_MSG_TYPE_DELEGATE
//                                self.onStartTxDetail(txResult)
//                            })
//                        }
//                    }
//
//                } catch {
//                    if (SHOW_LOG) { print(error) }
//                }
//            });
//        }
    }

}
