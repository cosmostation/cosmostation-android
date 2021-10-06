//
//  StepRewardCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class StepRewardCheckViewController: BaseViewController, PasswordViewDelegate{
    
    @IBOutlet weak var rewardAmoutLaebl: UILabel!
    @IBOutlet weak var rewardDenomLabel: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeDenomLabel: UILabel!
    
    @IBOutlet weak var fromValidatorLabel: UILabel!
    @IBOutlet weak var recipientTitleLabel: UILabel!
    @IBOutlet weak var recipientLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    @IBOutlet weak var expectedSeparator: UIView!
    @IBOutlet weak var expectedAmountTitle: UILabel!
    @IBOutlet weak var expectedAmountLabel: UILabel!
    @IBOutlet weak var expectedDenomLabel: UILabel!
    
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!

    var pageHolderVC: StepGenTxViewController!
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(chainType!, rewardDenomLabel)
        WUtils.setDenomTitle(chainType!, feeDenomLabel)
        WUtils.setDenomTitle(chainType!, expectedDenomLabel)
        
    }
    
    @IBAction func onClickConfirm(_ sender: Any) {
        if (checkIsWasteFee()) {
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
    
    @IBAction func onClickBack(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.beforeBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }

    func checkIsWasteFee() -> Bool {
        if (WUtils.isGRPC(chainType)) {
            var selectedRewardSum = NSDecimalNumber.zero
            for validator in pageHolderVC.mRewardTargetValidators_gRPC {
                let amount = BaseData.instance.getReward_gRPC(WUtils.getMainDenom(chainType), validator.operatorAddress)
                selectedRewardSum = selectedRewardSum.adding(amount)
            }
            if (NSDecimalNumber.init(string: pageHolderVC.mFee?.amount[0].amount).compare(selectedRewardSum).rawValue > 0 ) {
                return true
            }

        } else {
            var selectedRewardSum = NSDecimalNumber.zero
            pageHolderVC.mRewards.forEach { coin in
                if (coin.denom == WUtils.getMainDenom(chainType)) {
                    selectedRewardSum = selectedRewardSum.adding(WUtils.plainStringToDecimal(coin.amount))
                }
            }
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(selectedRewardSum).rawValue > 0 ) {
                return true
            }
        }
        return false
    }
    
    func onUpdateView() {
        mDpDecimal = WUtils.mainDivideDecimal(chainType)
        
        if (WUtils.isGRPC(chainType)) {
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators_gRPC {
                if(monikers.count > 0) {
                    monikers = monikers + ",   " + validator.description_p.moniker
                } else {
                    monikers = validator.description_p.moniker
                }
            }
            fromValidatorLabel.text = monikers
            memoLabel.text = pageHolderVC.mMemo
            recipientLabel.text = pageHolderVC.mRewardAddress
            recipientLabel.adjustsFontSizeToFitWidth = true
            
            var selectedRewardSum = NSDecimalNumber.zero
            for validator in pageHolderVC.mRewardTargetValidators_gRPC {
                let amount = BaseData.instance.getReward_gRPC(WUtils.getMainDenom(chainType), validator.operatorAddress)
                selectedRewardSum = selectedRewardSum.adding(amount)
            }
            
            rewardAmoutLaebl.attributedText = WUtils.displayAmount2(selectedRewardSum.stringValue, rewardAmoutLaebl.font, mDpDecimal, mDpDecimal)
            feeAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mFee?.amount[0].amount, feeAmountLabel.font, mDpDecimal, mDpDecimal)
            
            let userBalance: NSDecimalNumber = BaseData.instance.getAvailableAmount_gRPC(WUtils.getMainDenom(chainType))
            let expectedAmount = userBalance.adding(selectedRewardSum).subtracting(WUtils.plainStringToDecimal(pageHolderVC.mFee?.amount[0].amount))
            expectedAmountLabel.attributedText = WUtils.displayAmount2(expectedAmount.stringValue, rewardAmoutLaebl.font, mDpDecimal, mDpDecimal)
            
            if (pageHolderVC.mAccount?.account_address == pageHolderVC.mRewardAddress) {
                recipientTitleLabel.isHidden = true
                recipientLabel.isHidden = true
                
                expectedSeparator.isHidden = false
                expectedAmountTitle.isHidden = false
                expectedAmountLabel.isHidden = false
                expectedDenomLabel.isHidden = false
                
            } else {
                recipientTitleLabel.isHidden = false
                recipientLabel.isHidden = false
                
                expectedSeparator.isHidden = true
                expectedAmountTitle.isHidden = true
                expectedAmountLabel.isHidden = true
                expectedDenomLabel.isHidden = true
            }
            
        } else {
            var selectedRewardSum = NSDecimalNumber.zero
            pageHolderVC.mRewards.forEach { coin in
                if (coin.denom == WUtils.getMainDenom(chainType)) {
                    selectedRewardSum = selectedRewardSum.adding(WUtils.plainStringToDecimal(coin.amount))
                }
            }
            rewardAmoutLaebl.attributedText = WUtils.displayAmount2(selectedRewardSum.stringValue, rewardAmoutLaebl.font, mDpDecimal, mDpDecimal)
            feeAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, mDpDecimal, mDpDecimal)
            
            var userBalance = NSDecimalNumber.zero
            for balance in pageHolderVC.mBalances {
                if (balance.balance_denom == WUtils.getMainDenom(chainType)) {
                    userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                }
            }
            
            let expectedAmount = userBalance.adding(selectedRewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
            expectedAmountLabel.attributedText = WUtils.displayAmount2(expectedAmount.stringValue, rewardAmoutLaebl.font, mDpDecimal, mDpDecimal)
            
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators {
                if(monikers.count > 0) {
                    monikers = monikers + ",   " + validator.description.moniker
                } else {
                    monikers = validator.description.moniker
                }
            }
            fromValidatorLabel.text = monikers
            memoLabel.text = pageHolderVC.mMemo
            
            recipientLabel.text = pageHolderVC.mRewardAddress
            recipientLabel.adjustsFontSizeToFitWidth = true
            
            if (pageHolderVC.mAccount?.account_address == pageHolderVC.mRewardAddress) {
                recipientTitleLabel.isHidden = true
                recipientLabel.isHidden = true
        
                expectedSeparator.isHidden = false
                expectedAmountTitle.isHidden = false
                expectedAmountLabel.isHidden = false
                expectedDenomLabel.isHidden = false
                
            } else {
                recipientTitleLabel.isHidden = false
                recipientLabel.isHidden = false
                
                expectedSeparator.isHidden = true
                expectedAmountTitle.isHidden = true
                expectedAmountLabel.isHidden = true
                expectedDenomLabel.isHidden = true
            }
            
        }
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            if (WUtils.isGRPC(chainType)) {
                self.onFetchgRPCAuth(pageHolderVC.mAccount!)
            } else {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
            }
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenGetRewardTx()
                    
                } else {
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
                    self.onGenGetRewardTx()
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenGetRewardTx() {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            var msgList = Array<Msg>()
            for val in self.pageHolderVC.mRewardTargetValidators {
                let msg = MsgGenerator.genGetRewardMsg(self.pageHolderVC.mAccount!.account_address, val.operator_address, self.chainType!)
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
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("getReward ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            print("getRewarderror ", error)
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
                } catch {
                    print(error)
                }
            });
        }
    }
    
    
    func onFetchgRPCAuth(_ account: Account) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Auth_V1beta1_QueryAccountRequest.with {
                $0.address = account.account_address
            }
            do {
                let response = try Cosmos_Auth_V1beta1_QueryClient(channel: channel).account(req).response.wait()
                self.onBroadcastGrpcTx(response)
            } catch {
                print("onFetchgRPCAuth failed: \(error)")
            }
        }
    }
    
    func onBroadcastGrpcTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse?) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let privateKey = KeyFac.getPrivateRaw(words, self.pageHolderVC.mAccount!)
            let publicKey = KeyFac.getPublicRaw(words, self.pageHolderVC.mAccount!)
            let reqTx = Signer.genSignedClaimRewardsTxgRPC(auth!, self.pageHolderVC.mRewardTargetValidators_gRPC, self.pageHolderVC.mFee!,
                                                           self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).broadcastTx(reqTx).response.wait()
//                print("response ", response.txResponse.txhash)
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetailgRPC(response)
                        })
                    }
                });
            } catch {
                print("onBroadcastGrpcTx failed: \(error)")
            }
        }
    }
}
