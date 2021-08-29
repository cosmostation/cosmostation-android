//
//  StepGenTxViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import GRPC
import NIO
import HDWalletKit

class StepGenTxViewController: UIPageViewController, UIPageViewControllerDelegate, UIPageViewControllerDataSource, UIScrollViewDelegate {
    
    fileprivate var currentIndex = 0
    var disableBounce = false
    
    var topVC: TransactionViewController!
    var mType: String?
    
    var mAccount: Account?
    var chainType: ChainType?
    var mBalances = Array<Balance>()
    
    var mRewards = Array<Coin>()
    var mRewardAddress: String?
    
    var mTargetValidator: Validator?
    var mTargetValidator_gRPC: Cosmos_Staking_V1beta1_Validator?
    var mToDelegateAmount: Coin?
    var mToUndelegateAmount:Coin?
    var mRewardTargetValidators = Array<Validator>()
    var mRewardTargetValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    
    var mToSendRecipientAddress:String?
    var mToSendAmount = Array<Coin>()
    
    var mToReDelegateAmount: Coin?
    var mToReDelegateValidator: Validator?
    var mToReDelegateValidator_gRPC: Cosmos_Staking_V1beta1_Validator?
    var mToReDelegateValidators = Array<Validator>()
    var mToReDelegateValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    
    var mCurrentRewardAddress: String?
    var mToChangeRewardAddress: String?
    
    var mReinvestReward: Coin?
    
    var mMemo: String?
    var mFee: Fee?
    
    var mCollateral = Coin.init()
    var mPrincipal = Coin.init()
    var mPayment = Coin.init()
    var mSender: String?
    
    var currentPrice: NSDecimalNumber?
    var liquidationPrice: NSDecimalNumber?
    var riskRate: NSDecimalNumber?
    var beforeLiquidationPrice: NSDecimalNumber?
    var afterLiquidationPrice: NSDecimalNumber?
    var beforeRiskRate: NSDecimalNumber?
    var afterRiskRate: NSDecimalNumber?
    var totalDepositAmount: NSDecimalNumber?
    var totalLoanAmount: NSDecimalNumber?
    
    var mBnbToken: BnbToken?
    
    var mProposeId: String?
    var mProposalTitle: String?
    var mProposer: String?
    var mVoteOpinion: String?
    
    var mCollateralParamType: String?
    var mCDenom: String?
    var pDenom: String?
    var mMarketID: String?
    var mCollateralParam: CollateralParam?
    var mIncentiveKavaReceivable = NSDecimalNumber.zero
    var mIncentiveHardReceivable = NSDecimalNumber.zero
    var mHardPoolDenom: String?
    var mHardPoolCoins: Array<Coin>?
    var mIncentiveMultiplier: String?
    var mHardPoolCoin = Coin.init()
    var mKavaPool: SwapPool?
    var mKavaDeposit: SwapDeposit?
    var mKavaShareAmount = NSDecimalNumber.zero
    
    
    var mHtlcDenom: String?
    var mHtlcToChain: ChainType?
    var mHtlcToAccount: Account?
    var mHtlcSendFee: Fee?
    var mHtlcClaimFee: Fee?
    var mKavaSwapParam2: KavaSwapParam2?
    var mKavaSwapSupply2: KavaSwapSupply2?
    
    
    var mHtlcRefundSwapId: String?
    var mBnbSwapInfo: BnbSwapInfo?
    var mKavaSwapInfo: KavaSwapInfo?
    var mSwapRemainCap: NSDecimalNumber = NSDecimalNumber.zero
    var mSwapMaxOnce: NSDecimalNumber = NSDecimalNumber.zero
    
    var mOkToStaking = Coin.init()
    var mOkToWithdraw = Coin.init()
    var mOkVoteValidators: Array<String> = Array<String>()
    
    var mStarnameDomain: String?
    var mStarnameAccount: String?
    var mStarnameTime: Int64?
    var mStarnameDomainType: String?
    var mStarnameResources_gRPC: Array<Starnamed_X_Starname_V1beta1_Resource> = Array<Starnamed_X_Starname_V1beta1_Resource>()
    
    var mToSendDenom: String?
    
    var mPoolId: String?
    var mSwapInDenom: String?
    var mSwapOutDenom: String?
    var mSwapInAmount: NSDecimalNumber?
    var mSwapOutAmount: NSDecimalNumber?
    var mPool: Osmosis_Gamm_V1beta1_Pool?
    var mPoolCoin0: Coin?
    var mPoolCoin1: Coin?
    var mLPCoin: Coin?
    var mLockupDuration: Int64?
    var mLockups: Array<Osmosis_Lockup_PeriodLock>?
    
    
    var mHdacKey: HDWalletKit.PrivateKey?
    var mHdacAddress: String?
    var mHdacBalance: NSDecimalNumber?
    var mHdacUTXOs: Array<HdacUtxo>?
    
    lazy var orderedViewControllers: [UIViewController] = {
        if (mType == COSMOS_MSG_TYPE_DELEGATE || mType == IRIS_MSG_TYPE_DELEGATE) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "StepDelegateAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "StepDelegateCheckViewController")]
            } else {
                return [self.newVc(viewController: "StepDelegateAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "StepDelegateCheckViewController")]
            }
            
        } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2 || mType == IRIS_MSG_TYPE_UNDELEGATE) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "StepUndelegateAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "StepUndelegateCheckViewController")]
            } else {
                return [self.newVc(viewController: "StepUndelegateAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "StepUndelegateCheckViewController")]
            }
            
        } else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "StepSendAddressViewController"),
                        self.newVc(viewController: "StepSendAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "StepSendCheckViewController")]
            } else {
                return [self.newVc(viewController: "StepSendAddressViewController"),
                        self.newVc(viewController: "StepSendAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "StepSendCheckViewController")]
            }
            
        } else if (mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "StepRedelegateAmountViewController"),
                        self.newVc(viewController: "StepRedelegateToViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "StepRedelegateCheckViewController")]
            } else {
                return [self.newVc(viewController: "StepRedelegateAmountViewController"),
                        self.newVc(viewController: "StepRedelegateToViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "StepRedelegateCheckViewController")]
            }
            
        } else if (mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "StepChangeAddressViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "StepChangeCheckViewController")]
            } else {
                return [self.newVc(viewController: "StepChangeAddressViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "StepChangeCheckViewController")]
            }
            
        } else if (mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "ReInvestAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "ReInvestCheckViewController")]
            } else {
                return [self.newVc(viewController: "ReInvestAmountViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "ReInvestCheckViewController")]
            }
            
        } else if (mType == TASK_TYPE_VOTE) {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "VoteSelectViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "VoteCheckViewController")]
            } else {
                return [self.newVc(viewController: "VoteSelectViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "VoteCheckViewController")]
            }
           
        } else if (mType == KAVA_MSG_TYPE_CREATE_CDP) {
            return [self.newVc(viewController: "StepCreateCpdAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepCreateCpdCheckViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_DEPOSIT_CDP) {
            return [self.newVc(viewController: "StepDepositCdpAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepDepositCdpCheckViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_WITHDRAW_CDP) {
            return [self.newVc(viewController: "StepWithdrawCdpAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepWithdrawCdpCheckViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
            return [self.newVc(viewController: "StepDrawDebtCdpAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepDrawDebtCdpCheckViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
            return [self.newVc(viewController: "StepRepayCdpAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepRepayCdpCheckViewController")]
            
        } else if (mType == TASK_TYPE_HTLC_SWAP) {
            return [self.newVc(viewController: "StepHtlcSend0ViewController"),
                    self.newVc(viewController: "StepHtlcSend1ViewController"),
                    self.newVc(viewController: "StepHtlcSend2ViewController"),
                    self.newVc(viewController: "StepHtlcSend3ViewController")]
            
        } else if (mType == TASK_TYPE_HTLC_REFUND) {
            return [self.newVc(viewController: "StepHtlcRefund0ViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepHtlcRefund3ViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
            return [self.newVc(viewController: "StepIncentive0ViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepIncentive3ViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
            return [self.newVc(viewController: "StepHarvestReward0ViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepHarvestReward3ViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV) {
            return [self.newVc(viewController: "StepSendAddressViewController"),
                    self.newVc(viewController: "StepHarvestReward0ViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepHarvestReward3ViewController")]
            
        } else if (mType == KAVA_MSG_TYPE_DEPOSIT_HARD) {
            return [HardPoolDeposit0ViewController(nibName: "HardPoolDeposit0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    HardPoolDeposit3ViewController(nibName: "HardPoolDeposit3ViewController", bundle: nil)]
            
        } else if (mType == KAVA_MSG_TYPE_WITHDRAW_HARD) {
            return [HardPoolWithdraw0ViewController(nibName: "HardPoolWithdraw0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    HardPoolWithdraw3ViewController(nibName: "HardPoolWithdraw3ViewController", bundle: nil)]
            
        } else if (mType == KAVA_MSG_TYPE_BORROW_HARD) {
            return [HardPoolBorrow0ViewController(nibName: "HardPoolBorrow0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    HardPoolBorrow3ViewController(nibName: "HardPoolBorrow3ViewController", bundle: nil)]
            
        } else if (mType == KAVA_MSG_TYPE_REPAY_HARD) {
            return [HardPoolRepay0ViewController(nibName: "HardPoolRepay0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    HardPoolRepay3ViewController(nibName: "HardPoolRepay3ViewController", bundle: nil)]
            
        } else if (mType == KAVA_MSG_TYPE_SWAP_TOKEN) {
            return [KavaSwap0ViewController(nibName: "KavaSwap0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    KavaSwap3ViewController(nibName: "KavaSwap3ViewController", bundle: nil)]
            
        } else if (mType == KAVA_MSG_TYPE_SWAP_DEPOSIT) {
            return [KavaSwapJoin0ViewController(nibName: "KavaSwapJoin0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    KavaSwapJoin3ViewController(nibName: "KavaSwapJoin3ViewController", bundle: nil)]

        } else if (mType == KAVA_MSG_TYPE_SWAP_WITHDRAW) {
            return [KavaSwapExit0ViewController(nibName: "KavaSwapExit0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    KavaSwapExit3ViewController(nibName: "KavaSwapExit3ViewController", bundle: nil)]

        } else if (mType == KAVA_MSG_TYPE_INCENTIVE_ALL) {
            return [KavaIncentiveClaim0ViewController(nibName: "KavaIncentiveClaim0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    KavaIncentiveClaim3ViewController(nibName: "KavaIncentiveClaim3ViewController", bundle: nil)]
        }
        
        else if (mType == OK_MSG_TYPE_DEPOSIT) {
            return [self.newVc(viewController: "StepOkDepositAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepOkDepositCheckViewController")]
            
        } else if (mType == OK_MSG_TYPE_WITHDRAW) {
            return [self.newVc(viewController: "StepOkWithdrawAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepOkWithdrawCheckViewController")]
            
        } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
            return [self.newVc(viewController: "StepOkVoteToViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                    self.newVc(viewController: "StepOkVoteCheckViewController")]

        } else if (mType == IOV_MSG_TYPE_REGISTER_DOMAIN) {
            return [RegisterDomain0ViewController(nibName: "RegisterDomain0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    RegisterDomain3ViewController(nibName: "RegisterDomain3ViewController", bundle: nil)]
            
        } else if (mType == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
            return [RegisterAccount0ViewController(nibName: "RegisterAccount0ViewController", bundle: nil),
                    RegisterAccount1ViewController(nibName: "RegisterAccount1ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    RegisterAccount4ViewController(nibName: "RegisterAccount4ViewController", bundle: nil)]

        } else if (mType == IOV_MSG_TYPE_DELETE_DOMAIN || mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
            return [DeleteStarname0ViewController(nibName: "DeleteStarname0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    DeleteStarname3ViewController(nibName: "DeleteStarname3ViewController", bundle: nil)]

        } else if (mType == IOV_MSG_TYPE_RENEW_DOMAIN || mType == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            return [RenewStarname0ViewController(nibName: "RenewStarname0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    RenewStarname3ViewController(nibName: "RenewStarname3ViewController", bundle: nil)]

        } else if (mType == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
            return [ReplaceResource0ViewController(nibName: "ReplaceResource0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    ReplaceResource3ViewController(nibName: "ReplaceResource3ViewController", bundle: nil)]

        }
        
        else if (mType == OSMOSIS_MSG_TYPE_SWAP) {
            return [Swap0ViewController(nibName: "Swap0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    Swap3ViewController(nibName: "Swap3ViewController", bundle: nil)]
            
        } else if (mType == OSMOSIS_MSG_TYPE_JOIN_POOL) {
            return [JoinPool0ViewController(nibName: "JoinPool0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    JoinPool3ViewController(nibName: "JoinPool3ViewController", bundle: nil)]
            
        } else if (mType == OSMOSIS_MSG_TYPE_EXIT_POOL) {
            return [ExitPool0ViewController(nibName: "ExitPool0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    ExitPool3ViewController(nibName: "ExitPool3ViewController", bundle: nil)]
            
        } else if (mType == OSMOSIS_MSG_TYPE_LOCK) {
            return [StartLock0ViewController(nibName: "StartLock0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    StartLock3ViewController(nibName: "StartLock3ViewController", bundle: nil)]
            
        } else if (mType == OSMOSIS_MSG_TYPE_BEGIN_UNLCOK) {
            return [StartUnbonding0ViewController(nibName: "StartUnbonding0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    StartUnbonding3ViewController(nibName: "StartUnbonding3ViewController", bundle: nil)]
            
        } else if (mType == OSMOSIS_MSG_TYPE_PERIOD_UNLOCK) {
            return [StartUnlock0ViewController(nibName: "StartUnlock0ViewController", bundle: nil),
                    self.newVc(viewController: "StepMemoViewController"),
                    StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                    StartUnlock3ViewController(nibName: "StartUnlock3ViewController", bundle: nil)]
        }
        
        else if (mType == TASK_RIZON_EVENT_HORIZON) {
            return [StepEventHorizon0ViewController(nibName: "StepEventHorizon0ViewController", bundle: nil),
                    StepEventHorizon1ViewController(nibName: "StepEventHorizon1ViewController", bundle: nil)]
        }
        
        
        else {
            if (WUtils.isGRPC(chainType!)) {
                return [self.newVc(viewController: "StepRewardViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeGrpcViewController(nibName: "StepFeeGrpcViewController", bundle: nil),
                        self.newVc(viewController: "StepRewardCheckViewController")]
            } else {
                return [self.newVc(viewController: "StepRewardViewController"),
                        self.newVc(viewController: "StepMemoViewController"),
                        StepFeeOldViewController(nibName: "StepFeeOldViewController", bundle: nil),
                        self.newVc(viewController: "StepRewardCheckViewController")]
            }
            
        }
    }()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(transitionStyle: .scroll, navigationOrientation: .horizontal, options: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mAccount        = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mBalances       = mAccount!.account_balances
        chainType       = WUtils.getChainType(mAccount!.account_base_chain)
        mBnbToken       = WUtils.getBnbToken(mToSendDenom)
        
        if (mType == COSMOS_MSG_TYPE_REDELEGATE2) {
            if (WUtils.isGRPC(chainType!)) {
                onFetchBondedValidators(0)
            } else {
                onFetchTopValidatorsInfo()
            }
            
        } else if (mType == OK_MSG_TYPE_DIRECT_VOTE) {
            if let votedVals = BaseData.instance.mOkStaking?.validator_address {
                self.mOkVoteValidators = votedVals
            } else {
                self.mOkVoteValidators = Array<String>()
            }
        }
            
        self.dataSource = self
        self.delegate = self
        if let firstViewController = orderedViewControllers.first {
            setViewControllers([firstViewController], direction: .forward, animated: true, completion: nil)
        }
        
        for view in self.view.subviews {
            if let subView = view as? UIScrollView {
                subView.delegate = self
                subView.isScrollEnabled = false
                subView.bouncesZoom = false

            }
        }
        disableBounce = true
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }
    
    func newVc(viewController: String) ->UIViewController {
        return UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: viewController)
    }

    
    func onBeforePage() {
        disableBounce = false
        if (currentIndex == 0) {
            self.navigationController?.popViewController(animated: true)
        } else {
            setViewControllers([orderedViewControllers[currentIndex - 1]], direction: .reverse, animated: true, completion: { (finished) -> Void in
                self.currentIndex = self.currentIndex - 1
                let value:[String: Int] = ["step": self.currentIndex ]
                NotificationCenter.default.post(name: Notification.Name("stepChanged"), object: nil, userInfo: value)
                let currentVC = self.orderedViewControllers[self.currentIndex] as! BaseViewController
                currentVC.enableUserInteraction()
                self.disableBounce = true
            })
        }
        
    }
    
    func onNextPage() {
        disableBounce = false
        if ((currentIndex <= 3 && (mType == COSMOS_MSG_TYPE_TRANSFER2 || mType == COSMOS_MSG_TYPE_REDELEGATE2 || mType == IRIS_MSG_TYPE_REDELEGATE || mType == IOV_MSG_TYPE_REGISTER_ACCOUNT || mType == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE_VV)) || currentIndex <= 2) {
            setViewControllers([orderedViewControllers[currentIndex + 1]], direction: .forward, animated: true, completion: { (finished) -> Void in
                self.currentIndex = self.currentIndex + 1
                let value:[String: Int] = ["step": self.currentIndex ]
                NotificationCenter.default.post(name: Notification.Name("stepChanged"), object: nil, userInfo: value)
                let currentVC = self.orderedViewControllers[self.currentIndex] as! BaseViewController
                currentVC.enableUserInteraction()
                self.disableBounce = true
            })
        }
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        return nil
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        return nil
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, didFinishAnimating finished: Bool, previousViewControllers: [UIViewController], transitionCompleted completed: Bool) {
        if(!completed) {
            
        } else {
            if let currentViewController = pageViewController.viewControllers?.first,
                let index = orderedViewControllers.index(of: currentViewController) {
                currentIndex = index
            }
            let value:[String: Int] = ["step": currentIndex]
            NotificationCenter.default.post(name: Notification.Name("stepChanged"), object: nil, userInfo: value)
        }
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if(disableBounce) {
            scrollView.contentOffset = CGPoint(x: scrollView.bounds.size.width, y: 0);
        }
    }
    
    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        if(disableBounce) {
            targetContentOffset.pointee = CGPoint(x: scrollView.bounds.size.width, y: 0);
        }
    }
    
    func onFetchTopValidatorsInfo() {
        let request = Alamofire.request(BaseNetWork.validatorsUrl(chainType), method: .get, parameters: ["status":"bonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                         print("no validators!!")
                        return
                }
                self.mToReDelegateValidators.removeAll()
                for validator in validators {
                    let tempVal = Validator(validator as! [String : Any])
                    if(tempVal.operator_address != self.mTargetValidator?.operator_address) {
                        self.mToReDelegateValidators.append(tempVal)
                    }
                }
                self.sortByPower()
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTopValidatorsInfo ", error) }
            }
        }
    }
    
    func onFetchBondedValidators(_ offset:Int) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let page = Cosmos_Base_Query_V1beta1_PageRequest.with {
                $0.limit = 125
            }
            let req = Cosmos_Staking_V1beta1_QueryValidatorsRequest.with {
                $0.pagination = page
                $0.status = "BOND_STATUS_BONDED"
            }
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).validators(req).response.wait()
                response.validators.forEach { validator in
                    if (validator.operatorAddress != self.mTargetValidator_gRPC?.operatorAddress) {
                        self.mToReDelegateValidators_gRPC.append(validator)
                    }
                }
            } catch {
                print("onFetchgRPCBondedValidators failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.sortByPower_gRPC()
            });
        }
        
    }
    
    func sortByPower() {
        mToReDelegateValidators.sort{
            if ($0.description.moniker == "Cosmostation") { return true }
            if ($1.description.moniker == "Cosmostation") {  return false }
            if ($0.jailed && !$1.jailed) { return false }
            if (!$0.jailed && $1.jailed) { return true }
            return Double($0.tokens)! > Double($1.tokens)!
        }
    }
    
    func sortByPower_gRPC() {
        mToReDelegateValidators_gRPC.sort{
            if ($0.description_p.moniker == "Cosmostation") { return true }
            if ($1.description_p.moniker == "Cosmostation") { return false }
            if ($0.jailed && !$1.jailed) { return false }
            if (!$0.jailed && $1.jailed) { return true }
            return Double($0.tokens)! > Double($1.tokens)!
        }
    }
}
