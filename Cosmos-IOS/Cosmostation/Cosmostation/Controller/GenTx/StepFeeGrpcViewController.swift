//
//  StepFeeGrpcViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/26.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper
import HDWalletKit
import GRPC
import NIO

class StepFeeGrpcViewController: BaseViewController, PasswordViewDelegate {

    @IBOutlet weak var feeTotalCard: CardView!
    @IBOutlet weak var feeTotalAmount: UILabel!
    @IBOutlet weak var feeTotalDenom: UILabel!
    @IBOutlet weak var feeTotalValue: UILabel!
    
    @IBOutlet weak var gasAmountLabel: UILabel!
    @IBOutlet weak var gasRateLabel: UILabel!
    @IBOutlet weak var gasFeeLabel: UILabel!
    @IBOutlet weak var gasSelectSegments: UISegmentedControl!
    
    @IBOutlet weak var speedImg: UIImageView!
    @IBOutlet weak var speedTxt: UILabel!
    
    @IBOutlet weak var btnGasCheck: UIButton!
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var mSelectedGasPosition = 1
    var mSelectedGasRate = NSDecimalNumber.zero
    var mEstimateGasAmount = NSDecimalNumber.zero
    var mFee = NSDecimalNumber.zero
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        feeTotalCard.backgroundColor = WUtils.getChainBg(chainType)
        WUtils.setDenomTitle(chainType, feeTotalDenom)
        mDpDecimal = WUtils.mainDivideDecimal(chainType)
        if #available(iOS 13.0, *) {
            gasSelectSegments.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            gasSelectSegments.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            gasSelectSegments.selectedSegmentTintColor = WUtils.getChainColor(chainType)
        } else {
            gasSelectSegments.tintColor = WUtils.getChainColor(chainType!)
        }
        mEstimateGasAmount = WUtils.getEstimateGasAmount(chainType!, pageHolderVC.mType!, pageHolderVC.mRewardTargetValidators_gRPC.count)
        onUpdateView()
    }
    
    func onCalculateFees() {
        mSelectedGasRate = WUtils.getGasRate(chainType!, mSelectedGasPosition)
        mFee = mSelectedGasRate.multiplying(by: mEstimateGasAmount, withBehavior: WUtils.handler0Up)
    }
    
    func onUpdateView() {
        onCalculateFees()
        
        feeTotalAmount.attributedText = WUtils.displayAmount2(mFee.stringValue, feeTotalAmount.font!, mDpDecimal, mDpDecimal)
        feeTotalValue.attributedText = WUtils.dpUserCurrencyValue(WUtils.getMainDenom(chainType), mFee, 6, feeTotalValue.font)
        
        gasRateLabel.attributedText = WUtils.displayGasRate(mSelectedGasRate.rounding(accordingToBehavior: WUtils.handler6), font: gasRateLabel.font, 5)
        gasAmountLabel.text = mEstimateGasAmount.stringValue
        gasFeeLabel.text = mFee.stringValue
        
        if (mSelectedGasPosition == 0) {
            self.speedImg.image = UIImage.init(named: "bycicle")
            self.speedTxt.text = NSLocalizedString("fee_speed_title_0", comment: "")
        } else if (mSelectedGasPosition == 1) {
            self.speedImg.image = UIImage.init(named: "car")
            self.speedTxt.text = NSLocalizedString("fee_speed_title_1", comment: "")
        } else {
            self.speedImg.image = UIImage.init(named: "roket")
            self.speedTxt.text = NSLocalizedString("fee_speed_title_2", comment: "")
        }
        
    }

    @IBAction func onSwitchGasRate(_ sender: UISegmentedControl) {
        mSelectedGasPosition = sender.selectedSegmentIndex
        onUpdateView()
    }
    
    override func enableUserInteraction() {
        btnBefore.isUserInteractionEnabled = true
        btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCheckGas(_ sender: UIButton) {
        onSetFee()
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        btnBefore.isUserInteractionEnabled = false
        btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (onCheckValidate()) {
            onSetFee()
            btnBefore.isUserInteractionEnabled = false
            btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
    
    func onSetFee() {
        let gasCoin = Coin.init(WUtils.getMainDenom(chainType), mFee.stringValue)
        var amount: Array<Coin> = Array<Coin>()
        amount.append(gasCoin)
        
        var fee = Fee.init()
        fee.amount = amount
        fee.gas = mEstimateGasAmount.stringValue
        
        pageHolderVC.mFee = fee
    }
    
    func onCheckValidate() -> Bool {
        return true
    }
    
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(310), execute: {
                self.onFetchgRPCAuth(self.pageHolderVC.mAccount!)
            })
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
                self.onSimulateGrpcTx(response)
            } catch {
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                print("onFetchgRPCAuth failed: \(error)")
            }
        }
    }
    
    func onSimulateGrpcTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse?) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let privateKey = KeyFac.getPrivateRaw(words, self.pageHolderVC.mAccount!)
            let publicKey = KeyFac.getPublicRaw(words, self.pageHolderVC.mAccount!)
            let simulateReq = self.genSimulateReq(auth!, privateKey, publicKey)
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).simulate(simulateReq!).response.wait()
//                print("response ", response.gasInfo)
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.mEstimateGasAmount = NSDecimalNumber.init(value: response.gasInfo.gasUsed).multiplying(by: NSDecimalNumber.init(value: 1.1), withBehavior: WUtils.handler0Up)
                            self.onUpdateView()
                        })
                    }
                });
            } catch {
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        })
                    }
                    print("onSimulateGrpcTx failed: \(error)")
                });
            }
        }
    }
    
    func genSimulateReq(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ privateKey: Data, _ publicKey: Data)  -> Cosmos_Tx_V1beta1_SimulateRequest? {
        if (pageHolderVC.mType == COSMOS_MSG_TYPE_TRANSFER2) {
            return Signer.genSimulateSendTxgRPC(auth, self.pageHolderVC.mToSendRecipientAddress!, self.pageHolderVC.mToSendAmount,
                                                self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_DELEGATE) {
            return Signer.genSimulateDelegateTxgRPC(auth, self.pageHolderVC.mTargetValidator_gRPC!.operatorAddress, self.pageHolderVC.mToDelegateAmount!,
                                                    self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                    BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
            return Signer.genSimulateUnDelegateTxgRPC(auth, self.pageHolderVC.mTargetValidator_gRPC!.operatorAddress, self.pageHolderVC.mToUndelegateAmount!,
                                                      self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                      BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_REDELEGATE2) {
            return Signer.genSimulateReDelegateTxgRPC(auth, self.pageHolderVC.mTargetValidator_gRPC!.operatorAddress, self.pageHolderVC.mToReDelegateValidator_gRPC!.operatorAddress,
                                                      self.pageHolderVC.mToReDelegateAmount!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                      BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
            return Signer.genSimulateClaimRewardsTxgRPC(auth, self.pageHolderVC.mRewardTargetValidators_gRPC, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                        BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
            return Signer.genSimulateReInvestTxgRPC(auth, self.pageHolderVC.mTargetValidator_gRPC!.operatorAddress, self.pageHolderVC.mReinvestReward!,
                                                    self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                    BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
            return Signer.genSimulateetRewardAddressTxgRPC(auth, self.pageHolderVC.mToChangeRewardAddress!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!,
                                                           privateKey,publicKey,BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == TASK_TYPE_VOTE) {
            return Signer.genSimulateVoteTxgRPC(auth, self.pageHolderVC.mProposeId!, self.pageHolderVC.mVoteOpinion!, self.pageHolderVC.mFee!,
                                                self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        }
        
        //for starname custom msg
        else if (pageHolderVC.mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
            return Signer.genSimulateRegisterDomainMsgTxgRPC(auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mAccount!.account_address,
                                                             self.pageHolderVC.mStarnameDomainType!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
            return Signer.genSimulateRegisterAccountMsgTxgRPC(auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mStarnameAccount!, self.pageHolderVC.mAccount!.account_address,
                                                              self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mStarnameResources_gRPC, self.pageHolderVC.mFee!,
                                                              self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_DELETE_DOMAIN) {
            return Signer.genSimulateDeleteDomainMsgTxgRPC (auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mFee!,
                                                            self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
            return Signer.genSimulateDeleteAccountMsgTxgRPC (auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mStarnameAccount!, self.pageHolderVC.mAccount!.account_address,
                                                             self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_RENEW_DOMAIN) {
            return Signer.genSimulateRenewDomainMsgTxgRPC (auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mAccount!.account_address, self.pageHolderVC.mFee!,
                                                           self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            return Signer.genSimulateRenewAccountMsgTxgRPC (auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mStarnameAccount!, self.pageHolderVC.mAccount!.account_address,
                                                            self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
            return Signer.genSimulateReplaceResourceMsgTxgRPC(auth, self.pageHolderVC.mStarnameDomain!, self.pageHolderVC.mStarnameAccount, self.pageHolderVC.mAccount!.account_address,
                                                              self.pageHolderVC.mStarnameResources_gRPC, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey, BaseData.instance.getChainId(self.chainType))
        }
        
        //for osmosis custom msg
        else if (pageHolderVC.mType == OSMOSIS_MSG_TYPE_SWAP) {
            var swapRoutes = Array<Osmosis_Gamm_V1beta1_SwapAmountInRoute>()
            let swapRoute = Osmosis_Gamm_V1beta1_SwapAmountInRoute.with {
                $0.poolID = self.pageHolderVC.mPool!.id
                $0.tokenOutDenom = self.pageHolderVC.mSwapOutDenom!
            }
            swapRoutes.append(swapRoute)
            return Signer.genSimulateSwapInMsgTxgRPC(auth, swapRoutes,
                                                     self.pageHolderVC.mSwapInDenom!,
                                                     self.pageHolderVC.mSwapInAmount!.stringValue,
                                                     self.pageHolderVC.mSwapOutAmount!.stringValue,
                                                     self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, privateKey, publicKey,
                                                     BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
            return Signer.genSimulateDepositPoolMsgTxgRPC(auth,
                                                          self.pageHolderVC.mPoolId!, self.pageHolderVC.mPoolCoin0!, self.pageHolderVC.mPoolCoin1!,
                                                          self.pageHolderVC.mLPCoin!.amount, self.pageHolderVC.mFee!,
                                                          self.pageHolderVC.mMemo!,
                                                          privateKey, publicKey,
                                                          BaseData.instance.getChainId(self.chainType))
            
        } else if (pageHolderVC.mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
            return Signer.genSimulateWithdrawPoolMsgTxgRPC(auth,
                                                           self.pageHolderVC.mPoolId!, self.pageHolderVC.mPoolCoin0!, self.pageHolderVC.mPoolCoin1!,
                                                           self.pageHolderVC.mLPCoin!.amount, self.pageHolderVC.mFee!,
                                                           self.pageHolderVC.mMemo!,
                                                           privateKey, publicKey,
                                                           BaseData.instance.getChainId(self.chainType))
            
        }
        return nil
    }
}
