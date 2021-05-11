//
//  TxDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class TxDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var txTableView: UITableView!
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var htlcRefundBtn: UIButton!
    @IBOutlet weak var errorLayer: CardView!
    @IBOutlet weak var errorCode: UILabel!
    @IBOutlet weak var loadingLayer: UIView!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var loadingMsg: UILabel!
    
    var mIsGen: Bool = false
    var mTxHash: String?
    var mTxInfo: TxInfo?
    var mBnbTime: String?
    var mBroadCaseResult: [String:Any]?
    var mFetchCnt = 10
    var mAllValidator = Array<Validator>()
    
    var mBnbNodeInfo: BnbNodeInfo?
    var mBnbSwapInfo: BnbSwapInfo?
    var mKavaSwapInfo: KavaSwapInfo?
    var mSwapId: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.mAllValidator = BaseData.instance.getAllValidators()
        
        self.txTableView.delegate = self
        self.txTableView.dataSource = self
        self.txTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.txTableView.register(UINib(nibName: "TxCommonCell", bundle: nil), forCellReuseIdentifier: "TxCommonCell")
        self.txTableView.register(UINib(nibName: "TxCommissionCell", bundle: nil), forCellReuseIdentifier: "TxCommissionCell")
        self.txTableView.register(UINib(nibName: "TxDelegateCell", bundle: nil), forCellReuseIdentifier: "TxDelegateCell")
        self.txTableView.register(UINib(nibName: "TxUndelegateCell", bundle: nil), forCellReuseIdentifier: "TxUndelegateCell")
        self.txTableView.register(UINib(nibName: "TxRedelegateCell", bundle: nil), forCellReuseIdentifier: "TxRedelegateCell")
        self.txTableView.register(UINib(nibName: "TxTransferCell", bundle: nil), forCellReuseIdentifier: "TxTransferCell")
        self.txTableView.register(UINib(nibName: "TxMultiTransferCell", bundle: nil), forCellReuseIdentifier: "TxMultiTransferCell")
        self.txTableView.register(UINib(nibName: "TxRewardCell", bundle: nil), forCellReuseIdentifier: "TxRewardCell")
        self.txTableView.register(UINib(nibName: "TxRewardAllCell", bundle: nil), forCellReuseIdentifier: "TxRewardAllCell")
        self.txTableView.register(UINib(nibName: "TxEditRewardAddressCell", bundle: nil), forCellReuseIdentifier: "TxEditRewardAddressCell")
        self.txTableView.register(UINib(nibName: "TxVoteCell", bundle: nil), forCellReuseIdentifier: "TxVoteCell")
        
        self.txTableView.register(UINib(nibName: "TxPostPriceCell", bundle: nil), forCellReuseIdentifier: "TxPostPriceCell")
        self.txTableView.register(UINib(nibName: "TxCdpCreateCell", bundle: nil), forCellReuseIdentifier: "TxCdpCreateCell")
        self.txTableView.register(UINib(nibName: "TxCdpDepositCell", bundle: nil), forCellReuseIdentifier: "TxCdpDepositCell")
        self.txTableView.register(UINib(nibName: "TxCdpWithdrawCell", bundle: nil), forCellReuseIdentifier: "TxCdpWithdrawCell")
        self.txTableView.register(UINib(nibName: "TxCdpBorrowCell", bundle: nil), forCellReuseIdentifier: "TxCdpBorrowCell")
        self.txTableView.register(UINib(nibName: "TxCdpRepayCell", bundle: nil), forCellReuseIdentifier: "TxCdpRepayCell")
        self.txTableView.register(UINib(nibName: "TxCdpLiquidateCell", bundle: nil), forCellReuseIdentifier: "TxCdpLiquidateCell")
        self.txTableView.register(UINib(nibName: "TxHardDepositCell", bundle: nil), forCellReuseIdentifier: "TxHardDepositCell")
        self.txTableView.register(UINib(nibName: "TxHardWithdrawCell", bundle: nil), forCellReuseIdentifier: "TxHardWithdrawCell")
        self.txTableView.register(UINib(nibName: "TxHardBorrowCell", bundle: nil), forCellReuseIdentifier: "TxHardBorrowCell")
        self.txTableView.register(UINib(nibName: "TxHardRepayCell", bundle: nil), forCellReuseIdentifier: "TxHardRepayCell")
        self.txTableView.register(UINib(nibName: "TxHardLiquidateCell", bundle: nil), forCellReuseIdentifier: "TxHardLiquidateCell")
        self.txTableView.register(UINib(nibName: "TxIncentiveMintingCell", bundle: nil), forCellReuseIdentifier: "TxIncentiveMintingCell")
        self.txTableView.register(UINib(nibName: "TxIncentiveHardCell", bundle: nil), forCellReuseIdentifier: "TxIncentiveHardCell")
        self.txTableView.register(UINib(nibName: "TxHtlcCreateCell", bundle: nil), forCellReuseIdentifier: "TxHtlcCreateCell")
        self.txTableView.register(UINib(nibName: "TxHtlcClaimCell", bundle: nil), forCellReuseIdentifier: "TxHtlcClaimCell")
        self.txTableView.register(UINib(nibName: "TxHtlcRefundCell", bundle: nil), forCellReuseIdentifier: "TxHtlcRefundCell")
        
        self.txTableView.register(UINib(nibName: "TxOkStakeCell", bundle: nil), forCellReuseIdentifier: "TxOkStakeCell")
        self.txTableView.register(UINib(nibName: "TxOkDirectVoteCell", bundle: nil), forCellReuseIdentifier: "TxOkDirectVoteCell")
        
        self.txTableView.register(UINib(nibName: "TxRegisterDomainCell", bundle: nil), forCellReuseIdentifier: "TxRegisterDomainCell")
        self.txTableView.register(UINib(nibName: "TxRegisterAccountCell", bundle: nil), forCellReuseIdentifier: "TxRegisterAccountCell")
        self.txTableView.register(UINib(nibName: "TxDeleteDomainCell", bundle: nil), forCellReuseIdentifier: "TxDeleteDomainCell")
        self.txTableView.register(UINib(nibName: "TxDeleteAccountCell", bundle: nil), forCellReuseIdentifier: "TxDeleteAccountCell")
        self.txTableView.register(UINib(nibName: "TxReplaceResourceCell", bundle: nil), forCellReuseIdentifier: "TxReplaceResourceCell")
        self.txTableView.register(UINib(nibName: "TxRenewStarnameCell", bundle: nil), forCellReuseIdentifier: "TxRenewStarnameCell")
        
        self.txTableView.register(UINib(nibName: "TxUnknownCell", bundle: nil), forCellReuseIdentifier: "TxUnknownCell")
        self.txTableView.rowHeight = UITableView.automaticDimension
        self.txTableView.estimatedRowHeight = UITableView.automaticDimension
        
        if (mIsGen) {
            self.loadingMsg.isHidden = false
            self.loadingImg.onStartAnimation()
            if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
                guard let txHash = mBroadCaseResult?["hash"] as? String  else {
                    self.onStartMainTab()
                    return
                }
                mTxHash = txHash
            } else {
                guard let txHash = mBroadCaseResult?["txhash"] as? String  else {
                    self.onStartMainTab()
                    return
                }
                mTxHash = txHash
                if let code = mBroadCaseResult?["code"] as? Int {
                    onShowErrorView(code)
                    return
                }
            }
            self.onFetchTx(mTxHash!)

        } else {
            //TODO TEST HASH for KAVA 
//            mTxHash = "94C476304818D6B1C47DF0FE9D0ABF2462191912DAA11C847483CC58D4A1452D"
            self.loadingMsg.isHidden = true
            self.loadingImg.onStartAnimation()
            self.onFetchTx(mTxHash!)
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    
    func onShowErrorView(_ code: Int) {
        print("onShowErrorView")
        var logMsg = ""
        if let errorMsg = mBroadCaseResult?["raw_log"] as? String {
            logMsg = errorMsg;
        }
        if let check_tx = mBroadCaseResult?["check_tx"] as? [String : Any], let errorMsg = check_tx["log"] as? String {
            logMsg = errorMsg;
        }
        self.errorCode.text =  "error code : " + String(code) + "\n" + logMsg
        self.loadingLayer.isHidden = true
        self.errorLayer.isHidden = false
    }
    
    func onUpdateView() {
        self.loadingLayer.isHidden = true
        self.controlLayer.isHidden = false
        self.txTableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mTxInfo != nil) {
            return mTxInfo!.getMsgs().count  + 1
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            return onBindTxCommon(tableView)
        } else {
            let msg = mTxInfo?.getMsg(indexPath.row - 1)
            if (msg?.type == COSMOS_MSG_TYPE_DELEGATE || msg?.type == IRIS_MSG_TYPE_DELEGATE) {
                return onBindDelegate(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_UNDELEGATE || msg?.type == COSMOS_MSG_TYPE_UNDELEGATE2 || msg?.type == IRIS_MSG_TYPE_UNDELEGATE) {
                return onBindUndelegate(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_REDELEGATE || msg?.type == COSMOS_MSG_TYPE_REDELEGATE2 || msg?.type == IRIS_MSG_TYPE_REDELEGATE) {
                return onBindRedelegate(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_WITHDRAW_DEL || msg?.type == IRIS_MSG_TYPE_WITHDRAW) {
                return onBindGetReward(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || msg?.type == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                return onBindEditRewardAddress(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_VOTE || msg?.type == IRIS_MSG_TYPE_VOTE) {
                return onBindVote(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_WITHDRAW_VAL || msg?.type == IRIS_MSG_TYPE_COMMISSION) {
                return onBindCommission(tableView, indexPath.row)
                
            } else if (msg?.type == COSMOS_MSG_TYPE_TRANSFER || msg?.type == COSMOS_MSG_TYPE_TRANSFER2 || msg?.type == COSMOS_MSG_TYPE_TRANSFER3 || msg?.type == IRIS_MSG_TYPE_TRANSFER || msg?.type == OK_MSG_TYPE_TRANSFER || msg?.type == OK_MSG_TYPE_MULTI_TRANSFER || msg?.type == CERTIK_MSG_TYPE_TRANSFER) {
                if ((msg?.value.inputs != nil && (msg?.value.inputs!.count)! > 1) ||  (msg?.value.outputs != nil && (msg?.value.outputs!.count)! > 1)) {
                    //No case yet!
                    return onBindMultiTransfer(tableView, indexPath.row)
                } else {
                    return onBindTransfer(tableView, indexPath.row)
                }
            } else if (msg?.type == KAVA_MSG_TYPE_POST_PRICE) {
                return onBindPostPrice(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_CREATE_CDP) {
                return onBindCdpCreate(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_DEPOSIT_CDP) {
                return onBindCdpDeposit(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_WITHDRAW_CDP) {
                return onBindCdpWithdraw(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
                return onBindCdpBorrow(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                return onBindCdpRepay(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_LIQUIDATE_CDP) {
                return onBindCdpLiquidate(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_CREATE_SWAP || msg?.type == BNB_MSG_TYPE_HTLC) {
                return onBindHtlcCreate(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_CLAIM_SWAP || msg?.type == BNB_MSG_TYPE_HTLC_CLIAM) {
                return onBindHtlcClaim(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_REFUND_SWAP || msg?.type == BNB_MSG_TYPE_HTLC_REFUND) {
                return onBindHtlcRefund(tableView, indexPath.row)
                
            }else if (msg?.type == KAVA_MSG_TYPE_DEPOSIT_HAVEST || msg?.type == KAVA_MSG_TYPE_DEPOSIT_HARD) {
                return onBindHardDeposit(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_WITHDRAW_HAVEST || msg?.type == KAVA_MSG_TYPE_WITHDRAW_HARD) {
                return onBindHardWithdraw(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_BORROW_HARD) {
                return onBindHardBorrow(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_REPAY_HARD) {
                return onBindHardRepay(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_LIQUIDATE_HARD) {
                return onBindHardLiquidate(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_INCENTIVE_REWARD || msg?.type == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
                return onBindIncentiveMinting(tableView, indexPath.row)
                
            }  else if (msg?.type == KAVA_MSG_TYPE_CLAIM_HAVEST || msg?.type == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
                return onBindIncentiveHard(tableView, indexPath.row)
                
            } else if (msg?.type == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                return onBindGetRewardAll(tableView, indexPath.row)
                
            } else if (msg?.type == OK_MSG_TYPE_DEPOSIT || msg?.type == OK_MSG_TYPE_WITHDRAW) {
                return onBindOkStake(tableView, indexPath.row)
                
            } else if (msg?.type == OK_MSG_TYPE_DIRECT_VOTE) {
                return onBindOkDirectVote(tableView, indexPath.row)
                
            } else if (msg?.type == IOV_MSG_TYPE_REGISTER_DOMAIN) {
                return onBindRegisterDomain(tableView, indexPath.row)
                
            } else if (msg?.type == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                return onBindRegisterAccount(tableView, indexPath.row)
                
            } else if (msg?.type == IOV_MSG_TYPE_DELETE_DOMAIN) {
                return onBindDeleteDomain(tableView, indexPath.row)
                
            } else if (msg?.type == IOV_MSG_TYPE_DELETE_ACCOUNT) {
                return onBindDeleteAccount(tableView, indexPath.row)
                
            } else if (msg?.type == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
                return onBindReplaceResource(tableView, indexPath.row)
                
            } else if (msg?.type == IOV_MSG_TYPE_RENEW_DOMAIN || msg?.type == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                return onBindRenewStarname(tableView, indexPath.row)
                
            } else {
                return onBindUnknown(tableView, indexPath.row)
            }
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func onBindTxCommon(_ tableView: UITableView) -> UITableViewCell {
        let cell:TxCommonCell? = tableView.dequeueReusableCell(withIdentifier:"TxCommonCell") as? TxCommonCell
        cell?.setDenomType(chainType!)
        if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            cell?.feeLayer.isHidden = false
            cell?.usedFeeLayer.isHidden = true
            cell?.limitFeeLayer.isHidden = true
            cell?.statusImg.image = UIImage(named: "successIc")
            cell?.statusLabel.text = NSLocalizedString("tx_success", comment: "")
            cell?.errorMsg.isHidden = true
            cell?.errorConstraint.priority = .defaultLow
            cell?.successConstraint.priority = .defaultHigh
            
            cell?.heightLabel.text = mTxInfo!.height
            cell?.msgCntLabel.text = String(mTxInfo!.getMsgs().count)
            cell?.gasAmountLabel.text = "-"
            cell?.timeLabel.text = WUtils.nodeTimetoString(input: mBnbTime)
            cell?.timeGapLabel.text = WUtils.timeGap(input: mBnbTime)
            cell?.hashLabel.text = mTxInfo!.hash
            cell?.memoLabel.text = mTxInfo!.tx?.value.memo
            cell?.feeAmountLabel.attributedText = WUtils.displayAmount2("0.000375", cell!.feeAmountLabel.font!, 0, 8)
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            cell?.feeLayer.isHidden = false
            cell?.usedFeeLayer.isHidden = true
            cell?.limitFeeLayer.isHidden = true
            if (mTxInfo!.isSuccess()) {
                cell?.statusImg.image = UIImage(named: "successIc")
                cell?.statusLabel.text = NSLocalizedString("tx_success", comment: "")
                cell?.errorMsg.isHidden = true
                cell?.errorConstraint.priority = .defaultLow
                cell?.successConstraint.priority = .defaultHigh
            } else {
                cell?.statusImg.image = UIImage(named: "failIc")
                cell?.statusLabel.text = NSLocalizedString("tx_fail", comment: "")
                cell?.errorMsg.text = mTxInfo?.failMsg()
                cell?.errorMsg.isHidden = false
                cell?.errorConstraint.priority = .defaultHigh
                cell?.successConstraint.priority = .defaultLow
            }
            cell?.heightLabel.text = mTxInfo!.height
            cell?.msgCntLabel.text = String(mTxInfo!.getMsgs().count)
            cell?.gasAmountLabel.text = mTxInfo!.gas_used! + " / " + mTxInfo!.gas_wanted!
            cell?.timeLabel.text = WUtils.txTimetoString(input: mTxInfo!.timestamp!)
            cell?.timeGapLabel.text = WUtils.txTimeGap(input: mTxInfo!.timestamp!)
            cell?.hashLabel.text = mTxInfo!.txhash
            cell?.memoLabel.text = mTxInfo!.tx?.value.memo
            cell?.feeAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleFee().stringValue, cell!.feeAmountLabel.font!, 0, 8)
            
        } else {
            cell?.feeLayer.isHidden = false
            cell?.usedFeeLayer.isHidden = true
            cell?.limitFeeLayer.isHidden = true
            let decimal = WUtils.mainDivideDecimal(chainType)
            if (mTxInfo!.isSuccess()) {
                cell?.statusImg.image = UIImage(named: "successIc")
                cell?.statusLabel.text = NSLocalizedString("tx_success", comment: "")
                cell?.errorMsg.isHidden = true
                cell?.errorConstraint.priority = .defaultLow
                cell?.successConstraint.priority = .defaultHigh
            } else {
                cell?.statusImg.image = UIImage(named: "failIc")
                cell?.statusLabel.text = NSLocalizedString("tx_fail", comment: "")
                if let bool = mTxInfo?.failMsg().starts(with: "atomic swap not found"), bool {
                    cell?.errorMsg.text = "atomic swap not found"
                } else {
                    cell?.errorMsg.text = mTxInfo?.failMsg()
                }
                cell?.errorMsg.isHidden = false
                cell?.errorConstraint.priority = .defaultHigh
                cell?.successConstraint.priority = .defaultLow
            }
            cell?.heightLabel.text = mTxInfo!.height
            cell?.msgCntLabel.text = String(mTxInfo!.getMsgs().count)
            cell?.gasAmountLabel.text = mTxInfo!.gas_used! + " / " + mTxInfo!.gas_wanted!
            cell?.timeLabel.text = WUtils.txTimetoString(input: mTxInfo!.timestamp!)
            cell?.timeGapLabel.text = WUtils.txTimeGap(input: mTxInfo!.timestamp!)
            cell?.hashLabel.text = mTxInfo!.txhash
            cell?.memoLabel.text = mTxInfo!.tx?.value.memo
            cell?.feeAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleFee().stringValue, cell!.feeAmountLabel.font!, decimal, decimal)
            
        }
//        cell?.actionHashCheck = {
//            self.onClickExplorer()
//        }
        
        return cell!
    }
    
    func onBindDelegate(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxDelegateCell? = tableView.dequeueReusableCell(withIdentifier:"TxDelegateCell") as? TxDelegateCell
        let msg = mTxInfo!.getMsg(position - 1)
        let decimal = WUtils.mainDivideDecimal(chainType)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.delegatorLabel.text = msg?.value.delegator_address
        cell?.validatorLabel.text = msg?.value.validator_address
        cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
        cell?.delegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.getAmount()?.amount, cell!.delegateAmountLabel.font!, decimal, decimal)
        cell?.autoRewardAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleAutoReward(self.account!.account_address, position - 1).stringValue, cell!.autoRewardAmountLabel.font!, decimal, decimal)
        if (mTxInfo?.getMsgs().count == 1) {
            cell?.autoRewardLayer.isHidden = false
            cell?.autoRewardBottomConstraint.priority = .defaultHigh
            cell?.feeBottomConstraint.priority = .defaultLow
        } else {
            cell?.autoRewardLayer.isHidden = true
            cell?.autoRewardBottomConstraint.priority = .defaultLow
            cell?.feeBottomConstraint.priority = .defaultHigh
        }
        return cell!
    }
    
    func onBindUndelegate(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxUndelegateCell? = tableView.dequeueReusableCell(withIdentifier:"TxUndelegateCell") as? TxUndelegateCell
        let msg = mTxInfo?.getMsg(position - 1)
        let decimal = WUtils.mainDivideDecimal(chainType)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.undelegatorLabel.text = msg?.value.delegator_address
        cell?.validatorLabel.text = msg?.value.validator_address
        cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
        cell?.undelegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.getAmount()?.amount, cell!.undelegateAmountLabel.font!, decimal, decimal)
        cell?.autoRewardAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleAutoReward(self.account!.account_address, position - 1).stringValue, cell!.autoRewardAmountLabel.font!, decimal, decimal)
        if (mTxInfo?.getMsgs().count == 1) {
            cell?.autoRewardLayer.isHidden = false
            cell?.autoRewardBottomConstraint.priority = .defaultHigh
            cell?.feeBottomConstraint.priority = .defaultLow
        } else {
            cell?.autoRewardLayer.isHidden = true
            cell?.autoRewardBottomConstraint.priority = .defaultLow
            cell?.feeBottomConstraint.priority = .defaultHigh
        }
        return cell!
    }
    
    func onBindRedelegate(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxRedelegateCell? = tableView.dequeueReusableCell(withIdentifier:"TxRedelegateCell") as? TxRedelegateCell
        let msg = mTxInfo?.getMsg(position - 1)
        let decimal = WUtils.mainDivideDecimal(chainType)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.redelegatorLabel.text = msg?.value.delegator_address
        cell?.fromValidatorLabel.text = msg?.value.validator_src_address
        cell?.fromMonikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_src_address!, true)
        cell?.toValidatorLabel.text = msg?.value.validator_dst_address
        cell?.toMonikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_dst_address!, true)
        cell?.redelegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.getAmount()?.amount, cell!.redelegateAmountLabel.font!, decimal, decimal)
        cell?.autoRewardAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleAutoReward(self.account!.account_address, position - 1).stringValue, cell!.autoRewardAmountLabel.font!, decimal, decimal)
        if (mTxInfo?.getMsgs().count == 1) {
            cell?.autoRewardLayer.isHidden = false
            cell?.autoRewardBottomConstraint.priority = .defaultHigh
            cell?.feeBottomConstraint.priority = .defaultLow
        } else {
            cell?.autoRewardLayer.isHidden = true
            cell?.autoRewardBottomConstraint.priority = .defaultLow
            cell?.feeBottomConstraint.priority = .defaultHigh
        }
        return cell!
    }
    
    func onBindTransfer(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxTransferCell? = tableView.dequeueReusableCell(withIdentifier:"TxTransferCell") as? TxTransferCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            cell?.fromLabel.text = msg?.value.inputs![0].address
            cell?.toLabel.text = msg?.value.outputs![0].address
            if (self.account?.account_address == msg?.value.inputs![0].address) {
                cell?.txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
            } else if (self.account?.account_address == msg?.value.outputs![0].address) {
                cell?.txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
            }
            let coins = msg?.value.inputs?[0].coins
            cell?.multiAmountStack.isHidden = false
            WUtils.showCoinDp(coins![0], cell!.multiAmountDenom0, cell!.multiAmount0, chainType!)
        } else {
            var coins :[Coin]?
            if (msg?.type == COSMOS_MSG_TYPE_TRANSFER3) {
                cell?.fromLabel.text = msg?.value.inputs![0].address
                cell?.toLabel.text = msg?.value.outputs![0].address
                if (self.account?.account_address == msg?.value.inputs![0].address) {
                    cell?.txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
                }
                if (self.account?.account_address == msg?.value.outputs![0].address) {
                    cell?.txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
                }
                coins = msg?.value.inputs?[0].coins
                
            } else if (msg?.type == OK_MSG_TYPE_MULTI_TRANSFER) {
                cell?.fromLabel.text = msg?.value.from
                cell?.toLabel.text = msg?.value.transfers?[0].to
                if (self.account?.account_address == msg?.value.from) {
                    cell?.txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
                }
                if (self.account?.account_address == msg?.value.transfers?[0].to) {
                    cell?.txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
                }
                coins = msg?.value.transfers?[0].coins
                
            } else {
                cell?.fromLabel.text = msg?.value.from_address
                cell?.toLabel.text = msg?.value.to_address
                if (self.account?.account_address == msg?.value.from_address) {
                    cell?.txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
                }
                if (self.account?.account_address == msg?.value.to_address) {
                    cell?.txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
                }
                coins = msg?.value.getAmounts()
                
            }
            coins = sortCoins(coins!, chainType!)
            cell?.multiAmountStack.isHidden = false
            cell?.multiAmountLayer0.isHidden = false
            WUtils.showCoinDp(coins![0], cell!.multiAmountDenom0, cell!.multiAmount0, chainType!)
            if (coins!.count > 1) {
                cell?.multiAmountLayer1.isHidden = false
                WUtils.showCoinDp(coins![1], cell!.multiAmountDenom1, cell!.multiAmount1, chainType!)
            }
            if (coins!.count > 2) {
                cell?.multiAmountLayer2.isHidden = false
                WUtils.showCoinDp(coins![2], cell!.multiAmountDenom2, cell!.multiAmount2, chainType!)
            }
            if (coins!.count > 3) {
                cell?.multiAmountLayer3.isHidden = false
                WUtils.showCoinDp(coins![3], cell!.multiAmountDenom3, cell!.multiAmount3, chainType!)
            }
            if (coins!.count > 4) {
                cell?.multiAmountLayer4.isHidden = false
                WUtils.showCoinDp(coins![4], cell!.multiAmountDenom4, cell!.multiAmount4, chainType!)
            }
        }
        return cell!
    }
    
    func onBindMultiTransfer(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxMultiTransferCell? = tableView.dequeueReusableCell(withIdentifier:"TxMultiTransferCell") as? TxMultiTransferCell
        let msg = mTxInfo?.getMsg(position - 1)
        return cell!
    }
    
    func onBindGetReward(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxRewardCell? = tableView.dequeueReusableCell(withIdentifier:"TxRewardCell") as? TxRewardCell
        let msg = mTxInfo?.getMsg(position - 1)
        let decimal = WUtils.mainDivideDecimal(chainType)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.delegatorLabel.text = msg?.value.delegator_address
        cell?.validatorLabel.text = msg?.value.validator_address
        cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
        cell?.amountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleReward(msg!.value.validator_address!, position - 1).stringValue, cell!.amountLabel.font!, decimal, decimal)
        return cell!
    }
    
    func onBindGetRewardAll(_ tableView: UITableView,  _ position:Int) -> UITableViewCell {
        let cell:TxRewardAllCell? = tableView.dequeueReusableCell(withIdentifier:"TxRewardAllCell") as? TxRewardAllCell
        return cell!
    }
    
    func onBindEditRewardAddress(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxEditRewardAddressCell? = tableView.dequeueReusableCell(withIdentifier:"TxEditRewardAddressCell") as? TxEditRewardAddressCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.delegatorLabel.text = msg?.value.delegator_address
        cell?.widthrawAddressLabel.text = msg?.value.withdraw_address
        return cell!
    }
    
    func onBindVote(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxVoteCell? = tableView.dequeueReusableCell(withIdentifier:"TxVoteCell") as? TxVoteCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.voterLabel.text = msg?.value.voter
        cell?.proposalIdLabel.text = msg?.value.proposal_id
        cell?.opinionLabel.text = msg?.value.getOption()
        return cell!
    }
    
    func onBindCommission(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxCommissionCell? = tableView.dequeueReusableCell(withIdentifier:"TxCommissionCell") as? TxCommissionCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.validatorLabel.text = msg?.value.validator_addr
        cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_addr!, true)
        cell?.commissionAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleCommissionIris().stringValue, cell!.commissionAmountLabel.font!, 18, 18)
        return cell!
    }
    
    func onBindPostPrice(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxPostPriceCell? = tableView.dequeueReusableCell(withIdentifier:"TxPostPriceCell") as? TxPostPriceCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.pricePoster.text = msg?.value.from
        cell?.marketId.text = msg?.value.market_id
        cell?.postPrice.text = msg?.value.price
        cell?.validityTime.text = WUtils.txTimetoString(input: msg!.value.expiry!)
        return cell!
    }
    
    func onBindCdpCreate(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxCdpCreateCell") as? TxCdpCreateCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        let cDenom = msg?.value.collateral!.denom
        let pDenom = msg?.value.principal!.denom
        cell?.senderLabel.text = msg?.value.sender
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(msg?.value.collateral!.amount, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom!), WUtils.getKavaCoinDecimal(cDenom!))
        cell?.principalAmount.attributedText = WUtils.displayAmount2(msg?.value.principal!.amount, cell!.principalAmount.font!, WUtils.getKavaCoinDecimal(pDenom!), WUtils.getKavaCoinDecimal(pDenom!))
        cell?.collateralDenom.text = cDenom!.uppercased()
        cell?.principalDenom.text = pDenom!.uppercased()
        return cell!
    }
    
    func onBindCdpDeposit(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxCdpDepositCell") as? TxCdpDepositCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        let cDenom = msg?.value.collateral!.denom
        cell?.owerLabel.text = msg?.value.owner
        cell?.depositorLabel.text = msg?.value.depositor
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(msg?.value.collateral!.amount, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom!), WUtils.getKavaCoinDecimal(cDenom!))
        cell?.collateralDenom.text = cDenom!.uppercased()
        return cell!
    }
    
    func onBindCdpWithdraw(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxCdpWithdrawCell") as? TxCdpWithdrawCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        let cDenom = msg?.value.collateral!.denom
        cell?.ownerLabel.text = msg?.value.owner
        cell?.depositorLabel.text = msg?.value.depositor
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(msg?.value.collateral!.amount, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom!), WUtils.getKavaCoinDecimal(cDenom!))
        cell?.collateralDenom.text = cDenom!.uppercased()
        return cell!
    }
    
    func onBindCdpBorrow(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxCdpBorrowCell") as? TxCdpBorrowCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.coinTypeLabel.text = msg?.value.collateral_type?.uppercased()
        let pDenom = msg?.value.principal!.denom
        cell?.senderLabel.text = msg?.value.sender
        cell?.principalAmount.attributedText = WUtils.displayAmount2(msg?.value.principal!.amount, cell!.principalAmount.font!, WUtils.getKavaCoinDecimal(pDenom!), WUtils.getKavaCoinDecimal(pDenom!))
        cell?.principalDenom.text = pDenom!.uppercased()
        return cell!
    }
    
    func onBindCdpRepay(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxCdpRepayCell") as? TxCdpRepayCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.coinTypeLabel.text = msg?.value.collateral_type?.uppercased()
        let pDenom = msg?.value.payment!.denom
        cell?.senderLabel.text = msg?.value.sender
        cell?.paymentAmount.attributedText = WUtils.displayAmount2(msg?.value.payment!.amount, cell!.paymentAmount.font!, WUtils.getKavaCoinDecimal(pDenom!), WUtils.getKavaCoinDecimal(pDenom!))
        cell?.paymentDenom.text = pDenom!.uppercased()
        return cell!
    }
    
    func onBindCdpLiquidate(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxCdpLiquidateCell") as? TxCdpLiquidateCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg)
        }
        return cell!
    }
    
    func onBindHtlcCreate(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxHtlcCreateCell? = tableView.dequeueReusableCell(withIdentifier:"TxHtlcCreateCell") as? TxHtlcCreateCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            WUtils.showCoinDp((msg?.value.getAmounts()![0])!, cell!.sendDenom, cell!.sendAmount, chainType!)
            cell?.senderLabel.text = msg?.value.from
            cell?.recipientLabel.text = msg?.value.recipient_other_chain
            cell?.randomHashLabel.text = msg?.value.random_number_hash
            cell?.expectedAmountLabel.text = msg?.value.expected_income
            cell?.statusLabel.text = WUtils.getKavaHtlcStatus(self.mTxInfo!, mKavaSwapInfo)
            
            if (mKavaSwapInfo != nil && mKavaSwapInfo?.result.status == KavaSwapInfo.STATUS_EXPIRED) {
                self.htlcRefundBtn.isHidden = false
                self.mSwapId = self.mTxInfo?.simpleKavaSwapId()
            }
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            WUtils.showCoinDp((msg?.value.getAmounts()![0])!, cell!.sendDenom, cell!.sendAmount, chainType!)
            if (self.account?.account_address == msg?.value.from) {
                cell!.txTitle.text = NSLocalizedString("tx_send_htlc2", comment: "")
                cell?.senderLabel.text = msg?.value.from
                cell?.recipientLabel.text = msg?.value.recipient_other_chain
            } else if (self.account?.account_address == msg?.value.to) {
                cell!.txTitle.text = NSLocalizedString("tx_receive_htlc2", comment: "")
                cell?.senderLabel.text = msg?.value.sender_other_chain
                cell?.recipientLabel.text = msg?.value.to
            } else {
                cell!.txTitle.text = NSLocalizedString("tx_create_htlc2", comment: "")
                cell?.senderLabel.text = msg?.value.from
                cell?.recipientLabel.text = msg?.value.to
            }
            cell?.randomHashLabel.text = msg?.value.random_number_hash
            cell?.expectedAmountLabel.text = msg?.value.expected_income
            cell?.statusLabel.text = WUtils.getBnbHtlcStatus(mBnbSwapInfo, mBnbNodeInfo)
            
            if (mBnbSwapInfo != nil && mBnbNodeInfo != nil &&
                mBnbSwapInfo?.status == BnbSwapInfo.BNB_STATUS_OPEN &&
                mBnbSwapInfo!.expireHeight < mBnbNodeInfo!.getCHeight()) {
                self.htlcRefundBtn.isHidden = false
                self.mSwapId = self.mTxInfo?.simpleBnbSwapId()
            }
            
        }
        return cell!
    }
    
    func onBindHtlcClaim(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxHtlcClaimCell? = tableView.dequeueReusableCell(withIdentifier:"TxHtlcClaimCell") as? TxHtlcClaimCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let receiveCoin = mTxInfo!.simpleSwapCoin()
            if (receiveCoin != nil && !receiveCoin!.denom.isEmpty) {
                cell?.claimAmount.attributedText = WUtils.displayAmount2(receiveCoin!.amount, cell!.claimAmount.font!, WUtils.getKavaCoinDecimal(receiveCoin!.denom), WUtils.getKavaCoinDecimal(receiveCoin!.denom))
                cell?.claimDenom.text = receiveCoin!.denom.uppercased()
            }
            cell?.claimerAddress.text = msg?.value.from
            cell?.randomNumberLabel.text = msg?.value.random_number
            cell?.swapIdLabel.text = msg?.value.swap_id
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            cell?.claimAmount.text = ""
            cell?.claimDenom.text = "-"
            cell?.claimerAddress.text = msg?.value.from
            cell?.randomNumberLabel.text = msg?.value.random_number
            cell?.swapIdLabel.text = msg?.value.swap_id
            
        }
        return cell!
    }
    
    func onBindHtlcRefund(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxHtlcRefundCell? = tableView.dequeueReusableCell(withIdentifier:"TxHtlcRefundCell") as? TxHtlcRefundCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell?.fromAddress.text = msg?.value.from
            cell?.swapIdLabel.text = msg?.value.swap_id
            let refundCoin = mTxInfo?.simpleRefund()
            if (refundCoin != nil) {
                cell?.refundAmount.attributedText = WUtils.displayAmount2(refundCoin!.amount, cell!.refundAmount.font!, WUtils.getKavaCoinDecimal(refundCoin!.denom), WUtils.getKavaCoinDecimal(refundCoin!.denom))
                cell?.refundDenom.text = refundCoin!.denom.uppercased()
            }
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            cell?.fromAddress.text = msg?.value.from
            cell?.swapIdLabel.text = msg?.value.swap_id
            
        }
        return cell!
    }
    
    func onBindHardDeposit(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxHardDepositCell") as? TxHardDepositCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg)
        }
        return cell!
    }
    
    func onBindHardWithdraw(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxHardWithdrawCell") as? TxHardWithdrawCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg)
        }
        return cell!
    }
    
    func onBindHardBorrow(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxHardBorrowCell") as? TxHardBorrowCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg)
        }
        return cell!
    }
    
    func onBindHardRepay(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxHardRepayCell") as? TxHardRepayCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg)
        }
        return cell!
    }
    
    func onBindHardLiquidate(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxHardLiquidateCell") as? TxHardLiquidateCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg)
        }
        return cell!
    }
    
    func onBindIncentiveMinting(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxIncentiveMintingCell") as? TxIncentiveMintingCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg, mTxInfo!)
        }
        return cell!
    }
    
    func onBindIncentiveHard(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell = tableView.dequeueReusableCell(withIdentifier:"TxIncentiveHardCell") as? TxIncentiveHardCell
        if let msg = mTxInfo?.getMsg(position - 1) {
            cell?.onBind(chainType!, msg, mTxInfo!)
        }
        return cell!
    }
    
    
    func onBindOkStake(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxOkStakeCell? = tableView.dequeueReusableCell(withIdentifier:"TxOkStakeCell") as? TxOkStakeCell
        let msg = mTxInfo?.getMsg(position - 1)
        if (msg?.type == OK_MSG_TYPE_DEPOSIT) {
            cell?.txIcon.image = UIImage(named: "txDepositCdp")
            cell?.txLabel.text = NSLocalizedString("title_ok_deposit", comment: "")
        } else {
            cell?.txIcon.image = UIImage(named: "txWithdrawCdp")
            cell?.txLabel.text = NSLocalizedString("title_ok_withdraw", comment: "")
        }
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.delegatorLabel.text = msg?.value.delegator_address
        WUtils.showCoinDp(msg!.value.quantity!, cell!.stakeDenom, cell!.stakeAmount, chainType!)
        return cell!
    }
    
    func onBindOkDirectVote(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxOkDirectVoteCell? = tableView.dequeueReusableCell(withIdentifier:"TxOkDirectVoteCell") as? TxOkDirectVoteCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.voterLabel.text = msg?.value.delegator_address
        
        var monikers = ""
        let validators = msg?.value.validator_addresses
        for validator in validators! {
            for allVal in BaseData.instance.mAllValidator {
                if (allVal.operator_address == validator) {
                    monikers = monikers + allVal.description.moniker + ", "
                }
            }
        }
        cell?.validatorList.text = monikers
        return cell!
    }
    
    func onBindRegisterDomain(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxRegisterDomainCell? = tableView.dequeueReusableCell(withIdentifier:"TxRegisterDomainCell") as? TxRegisterDomainCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.domainLabel.text = "*" + msg!.value.domain!
        cell?.adminLabel.text = msg?.value.admin
        cell?.domainTypeLabel.text = msg?.value.type
        
        WUtils.showCoinDp(IOV_MAIN_DENOM, "0", cell!.starnameFeeDenomLabel, cell!.starnameFeeAmountLabel, chainType!)
        if let starnameFee = BaseData.instance.mStarNameFee?.getDomainFee(msg!.value.domain!, msg!.value.type!) {
            WUtils.showCoinDp(IOV_MAIN_DENOM, starnameFee.stringValue, cell!.starnameFeeDenomLabel, cell!.starnameFeeAmountLabel, chainType!)
        }
        return cell!
    }
    
    func onBindRegisterAccount(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxRegisterAccountCell? = tableView.dequeueReusableCell(withIdentifier:"TxRegisterAccountCell") as? TxRegisterAccountCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.accountLabel.text = msg!.value.name! + "*" + msg!.value.domain!
        cell?.owenerLabel.text = msg?.value.owner
        cell?.registerLabel.text = msg?.value.registerer
        
        WUtils.showCoinDp(IOV_MAIN_DENOM, "0", cell!.starnameFeeDenomLabel, cell!.starnameFeeAmountLabel, chainType!)
        if let starnameFee = BaseData.instance.mStarNameFee?.getAccountFee("open") {
            WUtils.showCoinDp(IOV_MAIN_DENOM, starnameFee.stringValue, cell!.starnameFeeDenomLabel, cell!.starnameFeeAmountLabel, chainType!)
        }
        return cell!
    }
    
    func onBindDeleteDomain(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxDeleteDomainCell? = tableView.dequeueReusableCell(withIdentifier:"TxDeleteDomainCell") as? TxDeleteDomainCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.domainLabel.text = "*" + msg!.value.domain!
        cell?.owenerLabel.text = msg?.value.owner
        return cell!
    }
    
    func onBindDeleteAccount(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxDeleteAccountCell? = tableView.dequeueReusableCell(withIdentifier:"TxDeleteAccountCell") as? TxDeleteAccountCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        cell?.accountLabel.text = msg!.value.name! + "*" + msg!.value.domain!
        cell?.owenerLabel.text = msg?.value.owner
        return cell!
    }
    
    func onBindReplaceResource(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxReplaceResourceCell? = tableView.dequeueReusableCell(withIdentifier:"TxReplaceResourceCell") as? TxReplaceResourceCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if let dpName = msg?.value.name {
            cell?.starnameLabel.text = dpName + "*" + msg!.value.domain!
        } else {
            cell?.starnameLabel.text = "*" + msg!.value.domain!
        }
        
        WUtils.showCoinDp(IOV_MAIN_DENOM, "0", cell!.starnameFeeDenomLabel, cell!.starnameFeeAmountLabel, chainType!)
        if let starnameFee = BaseData.instance.mStarNameFee?.getReplaceFee() {
            WUtils.showCoinDp(IOV_MAIN_DENOM, starnameFee.stringValue, cell!.starnameFeeDenomLabel, cell!.starnameFeeAmountLabel, chainType!)
        }
        
        let resources = msg?.value.new_resources
        if (resources == nil || resources?.count == 0) {
            cell?.resourceCntLabel.text = "0"
            cell?.resourceLabel.text = ""
        } else {
            cell?.resourceCntLabel.text = String(resources!.count)
            var resourceString = ""
            for resource in resources! {
                resourceString.append(resource.uri + "\n" + resource.resource + "\n")
            }
            cell?.resourceLabel.text = resourceString
        }
        return cell!
    }
    
    func onBindRenewStarname(_ tableView: UITableView, _ position:Int) -> UITableViewCell {
        let cell:TxRenewStarnameCell? = tableView.dequeueReusableCell(withIdentifier:"TxRenewStarnameCell") as? TxRenewStarnameCell
        let msg = mTxInfo?.getMsg(position - 1)
        if (msg?.type == IOV_MSG_TYPE_RENEW_DOMAIN) {
            cell?.txIcon.image = UIImage.init(named: "renewdomainic28")
            cell?.txTitleLabel.text = "Renew Domain"
        } else if (msg?.type == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            cell?.txIcon.image = UIImage.init(named: "renewaccountic28")
            cell?.txTitleLabel.text = "Renew Account"
        }
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if let dpName = msg?.value.name {
            cell?.starnameLabel.text = dpName + "*" + msg!.value.domain!
        } else {
            cell?.starnameLabel.text = "*" + msg!.value.domain!
        }
        cell?.signerLabel.text = msg?.value.signer
        return cell!
    }
    
    
    
    func onBindUnknown(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxUnknownCell? = tableView.dequeueReusableCell(withIdentifier:"TxUnknownCell") as? TxUnknownCell
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        return cell!
    }
    
    
    @IBAction func onClickShare(_ sender: UIButton) {
        var hash = ""
        if (mTxInfo?.hash != nil) {
            hash = mTxInfo!.hash!
        } else if (mTxInfo?.txhash != nil) {
            hash = mTxInfo!.txhash!
        }
        let link = WUtils.getTxExplorer(self.chainType!, hash)
        let textToShare = [ link ]
        let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
        activityViewController.popoverPresentationController?.sourceView = self.view
        self.present(activityViewController, animated: true, completion: nil)
        
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        var hash = ""
        if (mTxInfo?.hash != nil) {
            hash = mTxInfo!.hash!
        } else if (mTxInfo?.txhash != nil) {
            hash = mTxInfo!.txhash!
        }
        let link = WUtils.getTxExplorer(self.chainType!, hash)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    @IBAction func onClickDismiss(_ sender: UIButton) {
        self.mFetchCnt = -1
        if (mIsGen){
            self.onStartMainTab()
        } else {
            self.navigationController?.popViewController(animated: true)
        }
    }
    
    @IBAction func onClickHtlcRefund(_ sender: UIButton) {
//        print("onClickHtlcRefund")
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: self.account!.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: "0.000375")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_REFUND
        txVC.mHtlcRefundSwapId = self.mSwapId
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    func onShowMoreWait() {
        let noticeAlert = UIAlertController(title: NSLocalizedString("more_wait_title", comment: ""), message: NSLocalizedString("more_wait_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
            self.onStartMainTab()
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("wait", comment: ""), style: .default, handler: { _ in
            self.mFetchCnt = 10
            self.onFetchTx(self.mTxHash!)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    
    func onFetchTx(_ txHash: String) {
        var url = BaseNetWork.txUrl(chainType, txHash)
        var request:DataRequest?
        if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
        } else {
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        }
        print("url ", url)
        request!.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                if(SHOW_LOG) { print("onFetchTx OK", self.mIsGen, " ", res) }
                guard let info = res as? [String : Any], info["error"] == nil else {
                    if (self.mIsGen) {
                        self.mFetchCnt = self.mFetchCnt - 1
                        if (self.mFetchCnt > 0) {
                            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                                self.onFetchTx(txHash)
                            })
                        } else {
                            self.onShowMoreWait()
                        }
                    } else {
                        self.onUpdateView()
                    }
                    return
                }
                
                self.mTxInfo = TxInfo.init(info)
                
                //Check swap status if Send HTLC Tx
                if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
                    if (self.mTxInfo?.getMsgs()[0].type == BNB_MSG_TYPE_HTLC && self.account?.account_address == self.mTxInfo?.getMsgs()[0].value.from) {
                        self.onFetchHtlcStatus(self.mTxInfo?.simpleBnbSwapId())
                    } else {
                        self.onUpdateView()
                    }
                    
                } else if (self.chainType! == ChainType.KAVA_MAIN || self.chainType! == ChainType.KAVA_TEST) {
                    if (self.mTxInfo?.getMsgs()[0].type == KAVA_MSG_TYPE_CREATE_SWAP) {
                        print("simpleKavaSwapId " , self.mTxInfo?.simpleKavaSwapId())
                        self.onFetchHtlcStatus(self.mTxInfo?.simpleKavaSwapId())
                    } else {
                        self.onUpdateView()
                    }
                    
                } else {
                    self.onUpdateView()
                }
                
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchTx failure", error) }
                if (self.chainType! == ChainType.IRIS_MAIN) {
                    self.mFetchCnt = self.mFetchCnt - 1
                    if(self.mFetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                            self.onFetchTx(txHash)
                        })
                    } else {
                        self.onShowMoreWait()
                    }
                } else if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
                    if (self.mIsGen) {
                        self.mFetchCnt = self.mFetchCnt - 1
                        if (self.mFetchCnt > 0) {
                            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(1500), execute: {
                                self.onFetchTx(txHash)
                            })
                        } else {
                            self.onShowMoreWait()
                        }
                        
                    } else {
                        self.onUpdateView()
                    }
                }
                return
            }
            
        }
        
    }
    
    func onFetchHtlcStatus(_ swapId: String?) {
        print("onFetchHtlcStatus ", swapId)
        if (swapId == nil) {
            self.onUpdateView()
            return
        }
        let url = BaseNetWork.swapIdBep3Url(self.chainType, swapId!)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                if(SHOW_LOG) { print("onFetchSwapId ", res) }
                if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
                    if let info = res as? [String : Any] {
                        self.mBnbSwapInfo = BnbSwapInfo.init(info)
                    }
                    self.onFetchBnbNodeInfo()
                    
                } else if (self.chainType! == ChainType.KAVA_MAIN || self.chainType! == ChainType.KAVA_TEST) {
                    if let info = res as? [String : Any], info["error"] == nil  {
                        self.mKavaSwapInfo = KavaSwapInfo.init(info)
                    }
                    self.onUpdateView()
                    
                }
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchSwapId", error) }
                self.onUpdateView()
                return
            }
        }
    }
    
    func onFetchBnbNodeInfo() {
        let request = Alamofire.request(BaseNetWork.nodeInfoUrl(self.chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                if(SHOW_LOG) { print("onFetchBnbNodeInfo ", res) }
                if let info = res as? [String : Any] {
                    self.mBnbNodeInfo = BnbNodeInfo.init(info)
                }
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchBnbNodeInfo", error) }
                return
            }
            self.onUpdateView()
        }
    }
    
    
    
    
    
    func sortCoins(_ coins:Array<Coin>, _ chain:ChainType) -> Array<Coin> {
        if (chainType! == ChainType.COSMOS_MAIN) {
            return coins.sorted(by: {
                if ($0.denom == COSMOS_MAIN_DENOM) {
                    return true
                }
                if ($1.denom == COSMOS_MAIN_DENOM) {
                    return false
                }
                return false
            })
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            return coins.sorted(by: {
                if ($0.denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.denom == KAVA_MAIN_DENOM) {
                    return false
                }
                return false
            })
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            return coins.sorted(by: {
                if ($0.denom == IRIS_MAIN_DENOM) {
                    return true
                }
                if ($1.denom == IRIS_MAIN_DENOM) {
                    return false
                }
                return false
            })
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            return coins.sorted(by: {
                if ($0.denom == OKEX_MAIN_DENOM) {
                    return true
                }
                if ($1.denom == OKEX_MAIN_DENOM) {
                    return false
                }
                if ($0.denom == OKEX_MAIN_OKB) {
                    return true
                }
                if ($1.denom == OKEX_MAIN_OKB) {
                    return false
                }
                return false
            })
        }
        return coins
    }
}
