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
    @IBOutlet weak var htlcRefundBtn: UIButton!
    @IBOutlet weak var dismissBtn: UIButton!
    @IBOutlet weak var controlLayer: UIStackView!
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
        self.txTableView.register(UINib(nibName: "TxCreateCdpCell", bundle: nil), forCellReuseIdentifier: "TxCreateCdpCell")
        self.txTableView.register(UINib(nibName: "TxDepositCdpCell", bundle: nil), forCellReuseIdentifier: "TxDepositCdpCell")
        self.txTableView.register(UINib(nibName: "TxWithDrawCdpCell", bundle: nil), forCellReuseIdentifier: "TxWithDrawCdpCell")
        self.txTableView.register(UINib(nibName: "TxdrawDebtCdpCell", bundle: nil), forCellReuseIdentifier: "TxdrawDebtCdpCell")
        self.txTableView.register(UINib(nibName: "TxRepayCdpCell", bundle: nil), forCellReuseIdentifier: "TxRepayCdpCell")
        self.txTableView.register(UINib(nibName: "TxHtlcCreateCell", bundle: nil), forCellReuseIdentifier: "TxHtlcCreateCell")
        self.txTableView.register(UINib(nibName: "TxHtlcClaimCell", bundle: nil), forCellReuseIdentifier: "TxHtlcClaimCell")
        self.txTableView.register(UINib(nibName: "TxHtlcRefundCell", bundle: nil), forCellReuseIdentifier: "TxHtlcRefundCell")
        self.txTableView.register(UINib(nibName: "TxIncentiveTableViewCell", bundle: nil), forCellReuseIdentifier: "TxIncentiveTableViewCell")
        
        self.txTableView.register(UINib(nibName: "TxOkStakeCell", bundle: nil), forCellReuseIdentifier: "TxOkStakeCell")
        self.txTableView.register(UINib(nibName: "TxOkDirectVoteCell", bundle: nil), forCellReuseIdentifier: "TxOkDirectVoteCell")
        
        
        self.txTableView.register(UINib(nibName: "TxUnknownCell", bundle: nil), forCellReuseIdentifier: "TxUnknownCell")
        self.txTableView.rowHeight = UITableView.automaticDimension
        self.txTableView.estimatedRowHeight = UITableView.automaticDimension
        
        if (mIsGen) {
            self.loadingMsg.isHidden = false
            self.loadingImg.onStartAnimation()
            if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
                chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN  || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.OKEX_TEST || chainType == ChainType.CERTIK_TEST) {
                guard let txHash = mBroadCaseResult?["txhash"] as? String  else {
                    self.onStartMainTab()
                    return
                }
                mTxHash = txHash
                if let code = mBroadCaseResult?["code"] as? Int {
                    onShowErrorView(code)
                    return
                }

            } else if (chainType == ChainType.IRIS_MAIN) {
                if let net_error = mBroadCaseResult?["net_error"] as? Int {
                    onShowErrorView(net_error)
                    return
                }
                guard let txHash = mBroadCaseResult?["hash"] as? String  else {
                    self.onStartMainTab()
                    return
                }
                mTxHash = txHash
                if let check_tx = mBroadCaseResult?["check_tx"] as? [String:Any], let code = check_tx["code"] as? Int{
                    onShowErrorView(code)
                    return
                }
                
            } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
                guard let txHash = mBroadCaseResult?["hash"] as? String  else {
                    self.onStartMainTab()
                    return
                }
                mTxHash = txHash
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
                return onBindCreateCdp(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_DEPOSIT_CDP) {
                return onBindDepositCdp(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_WITHDRAW_CDP) {
                return onBindWithdrawCdp(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
                return onBindDrawDebtCdp(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                return onBindRepayDebtCdp(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_CREATE_SWAP || msg?.type == BNB_MSG_TYPE_HTLC) {
                return onBindHtlcCreate(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_CLAIM_SWAP || msg?.type == BNB_MSG_TYPE_HTLC_CLIAM) {
                return onBindHtlcClaim(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_REFUND_SWAP || msg?.type == BNB_MSG_TYPE_HTLC_REFUND) {
                return onBindHtlcRefund(tableView, indexPath.row)
                
            } else if (msg?.type == KAVA_MSG_TYPE_INCENTIVE_REWARD) {
                return onBindIncentive(tableView, indexPath.row)
                
            } else if (msg?.type == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                return onBindGetRewardAll(tableView, indexPath.row)
                
            } else if (msg?.type == OK_MSG_TYPE_DEPOSIT || msg?.type == OK_MSG_TYPE_WITHDRAW) {
                return onBindOkStake(tableView, indexPath.row)
                
            } else if (msg?.type == OK_MSG_TYPE_DIRECT_VOTE) {
                return onBindOkDirectVote(tableView, indexPath.row)
                
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
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
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
            cell?.feeAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleFee().stringValue, cell!.feeAmountLabel.font!, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.feeLayer.isHidden = true
            cell?.usedFeeLayer.isHidden = false
            cell?.limitFeeLayer.isHidden = false
            if (mTxInfo!.isSuccessIris()) {
                cell?.statusImg.image = UIImage(named: "successIc")
                cell?.statusLabel.text = NSLocalizedString("tx_success", comment: "")
                cell?.errorMsg.isHidden = true
                cell?.errorConstraint.priority = .defaultLow
                cell?.successConstraint.priority = .defaultHigh
            } else {
                cell?.statusImg.image = UIImage(named: "failIc")
                cell?.statusLabel.text = NSLocalizedString("tx_fail", comment: "")
                cell?.errorMsg.text = mTxInfo?.failMsgIris()
                cell?.errorMsg.isHidden = false
                cell?.errorConstraint.priority = .defaultHigh
                cell?.successConstraint.priority = .defaultLow
            }
            cell?.heightLabel.text = mTxInfo!.height
            cell?.msgCntLabel.text = String(mTxInfo!.getMsgs().count)
            cell?.gasAmountLabel.text = mTxInfo!.result!.GasUsed! + " / " + mTxInfo!.result!.GasWanted!
            cell?.timeLabel.text = WUtils.txTimetoString(input: mTxInfo!.timestamp!)
            cell?.timeGapLabel.text = WUtils.txTimeGap(input: mTxInfo!.timestamp!)
            cell?.hashLabel.text = mTxInfo!.hash
            cell?.memoLabel.text = mTxInfo!.tx?.value.memo
            cell?.usedFeeAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleUsedFeeIris().stringValue, cell!.usedFeeAmountLabel.font!, 18, 18)
            cell?.limitFeeAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleFee().stringValue, cell!.limitFeeAmountLabel.font!, 18, 18)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
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
            
        } else if (chainType == ChainType.OKEX_TEST) {
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
        }
        cell?.actionHashCheck = {
            self.onClickExplorer()
        }
        
        return cell!
    }
    
    func onBindDelegate(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxDelegateCell? = tableView.dequeueReusableCell(withIdentifier:"TxDelegateCell") as? TxDelegateCell
        let msg = mTxInfo!.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            cell?.delegatorLabel.text = msg?.value.delegator_address
            cell?.validatorLabel.text = msg?.value.validator_address
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
            cell?.delegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.getAmount()?.amount, cell!.delegateAmountLabel.font!, 6, 6)
            cell?.autoRewardAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleAutoReward(self.account!.account_address, position - 1).stringValue, cell!.autoRewardAmountLabel.font!, 6, 6)
            if (mTxInfo?.getMsgs().count == 1) {
                cell?.autoRewardLayer.isHidden = false
                cell?.autoRewardBottomConstraint.priority = .defaultHigh
                cell?.feeBottomConstraint.priority = .defaultLow
            } else {
                cell?.autoRewardLayer.isHidden = true
                cell?.autoRewardBottomConstraint.priority = .defaultLow
                cell?.feeBottomConstraint.priority = .defaultHigh
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.delegatorLabel.text = msg?.value.delegator_addr
            cell?.validatorLabel.text = msg?.value.validator_addr
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_addr!, true)
            cell?.delegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.delegation?.amount, cell!.delegateAmountLabel.font!, 18, 18)
            cell?.autoRewardLayer.isHidden = true
            cell?.autoRewardBottomConstraint.priority = .defaultLow
            cell?.feeBottomConstraint.priority = .defaultHigh
            
        }
        return cell!
    }
    
    func onBindUndelegate(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxUndelegateCell? = tableView.dequeueReusableCell(withIdentifier:"TxUndelegateCell") as? TxUndelegateCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            cell?.undelegatorLabel.text = msg?.value.delegator_address
            cell?.validatorLabel.text = msg?.value.validator_address
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
            cell?.undelegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.getAmount()?.amount, cell!.undelegateAmountLabel.font!, 6, 6)
            cell?.autoRewardAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleAutoReward(self.account!.account_address, position - 1).stringValue, cell!.autoRewardAmountLabel.font!, 6, 6)
            if (mTxInfo?.getMsgs().count == 1) {
                cell?.autoRewardLayer.isHidden = false
                cell?.autoRewardBottomConstraint.priority = .defaultHigh
                cell?.feeBottomConstraint.priority = .defaultLow
            } else {
                cell?.autoRewardLayer.isHidden = true
                cell?.autoRewardBottomConstraint.priority = .defaultLow
                cell?.feeBottomConstraint.priority = .defaultHigh
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.undelegatorLabel.text = msg?.value.delegator_addr
            cell?.validatorLabel.text = msg?.value.validator_addr
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_addr!, true)
            cell?.undelegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.shares_amount, cell!.undelegateAmountLabel.font!, 18, 18)
            cell?.autoRewardLayer.isHidden = true
            cell?.autoRewardBottomConstraint.priority = .defaultLow
            cell?.feeBottomConstraint.priority = .defaultHigh

        }
        return cell!
    }
    
    func onBindRedelegate(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxRedelegateCell? = tableView.dequeueReusableCell(withIdentifier:"TxRedelegateCell") as? TxRedelegateCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            cell?.redelegatorLabel.text = msg?.value.delegator_address
            cell?.fromValidatorLabel.text = msg?.value.validator_src_address
            cell?.fromMonikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_src_address!, true)
            cell?.toValidatorLabel.text = msg?.value.validator_dst_address
            cell?.toMonikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_dst_address!, true)
            cell?.redelegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.getAmount()?.amount, cell!.redelegateAmountLabel.font!, 6, 6)
            cell?.autoRewardAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleAutoReward(self.account!.account_address, position - 1).stringValue, cell!.autoRewardAmountLabel.font!, 6, 6)
            if (mTxInfo?.getMsgs().count == 1) {
                cell?.autoRewardLayer.isHidden = false
                cell?.autoRewardBottomConstraint.priority = .defaultHigh
                cell?.feeBottomConstraint.priority = .defaultLow
            } else {
                cell?.autoRewardLayer.isHidden = true
                cell?.autoRewardBottomConstraint.priority = .defaultLow
                cell?.feeBottomConstraint.priority = .defaultHigh
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.redelegatorLabel.text = msg?.value.delegator_addr
            cell?.fromValidatorLabel.text = msg?.value.validator_src_addr
            cell?.fromMonikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_src_addr!, true)
            cell?.toValidatorLabel.text = msg?.value.validator_dst_addr
            cell?.toMonikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_dst_addr!, true)
            cell?.redelegateAmountLabel.attributedText = WUtils.displayAmount2(msg?.value.shares_amount, cell!.redelegateAmountLabel.font!, 18, 18)
            cell?.autoRewardLayer.isHidden = true
            cell?.autoRewardBottomConstraint.priority = .defaultLow
            cell?.feeBottomConstraint.priority = .defaultHigh

        }
        return cell!
    }
    
    func onBindTransfer(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxTransferCell? = tableView.dequeueReusableCell(withIdentifier:"TxTransferCell") as? TxTransferCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.OKEX_TEST || chainType == ChainType.CERTIK_TEST) {
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
            if (coins!.count <= 1) {
                cell?.multiAmountStack.isHidden = true
                cell?.amountLabel.isHidden = false
                cell?.amountDenomLabel.isHidden = false
                cell?.multiAmountConstraint.priority = .defaultLow
                cell?.singleAmountConstraint.priority = .defaultHigh
                WUtils.showCoinDp(coins![0], cell!.amountDenomLabel, cell!.amountLabel, chainType!)
            } else {
                cell?.multiAmountStack.isHidden = false
                cell?.amountLabel.isHidden = true
                cell?.amountDenomLabel.isHidden = true
                cell?.multiAmountConstraint.priority = .defaultHigh
                cell?.singleAmountConstraint.priority = .defaultLow
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
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.fromLabel.text = msg?.value.inputs![0].address
            cell?.toLabel.text = msg?.value.outputs![0].address
            if (self.account?.account_address == msg?.value.inputs![0].address) {
                cell?.txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
            } else if (self.account?.account_address == msg?.value.outputs![0].address) {
                cell?.txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
            }
            var coins = msg?.value.inputs?[0].coins
            coins = sortCoins(coins!, chainType!)
            if (coins!.count <= 1) {
                cell?.multiAmountStack.isHidden = true
                cell?.amountLabel.isHidden = false
                cell?.amountDenomLabel.isHidden = false
                cell?.multiAmountConstraint.priority = .defaultLow
                cell?.singleAmountConstraint.priority = .defaultHigh
                WUtils.showCoinDp(coins![0], cell!.amountDenomLabel, cell!.amountLabel, chainType!)
            } else {
                cell?.multiAmountStack.isHidden = false
                cell?.amountLabel.isHidden = true
                cell?.amountDenomLabel.isHidden = true
                cell?.multiAmountConstraint.priority = .defaultHigh
                cell?.singleAmountConstraint.priority = .defaultLow
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
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            cell?.fromLabel.text = msg?.value.inputs![0].address
            cell?.toLabel.text = msg?.value.outputs![0].address
            if (self.account?.account_address == msg?.value.inputs![0].address) {
                cell?.txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
            } else if (self.account?.account_address == msg?.value.outputs![0].address) {
                cell?.txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
            }
            let coins = msg?.value.inputs?[0].coins
            cell?.multiAmountStack.isHidden = true
            cell?.amountLabel.isHidden = false
            cell?.amountDenomLabel.isHidden = false
            cell?.multiAmountConstraint.priority = .defaultLow
            cell?.singleAmountConstraint.priority = .defaultHigh
            WUtils.showCoinDp(coins![0], cell!.amountDenomLabel, cell!.amountLabel, chainType!)
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
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            cell?.delegatorLabel.text = msg?.value.delegator_address
            cell?.validatorLabel.text = msg?.value.validator_address
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
            cell?.amountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleReward(msg!.value.validator_address!, position - 1).stringValue, cell!.amountLabel.font!, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.delegatorLabel.text = msg?.value.delegator_addr
            cell?.validatorLabel.text = msg?.value.validator_addr
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_addr!, true)
            cell?.amountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleRewardIris().stringValue, cell!.amountLabel.font!, 18, 18)
            
        }
        return cell!
    }
    
    func onBindGetRewardAll(_ tableView: UITableView,  _ position:Int) -> UITableViewCell {
        let cell:TxRewardAllCell? = tableView.dequeueReusableCell(withIdentifier:"TxRewardAllCell") as? TxRewardAllCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.IRIS_MAIN) {
            cell?.delegatorLabel.text = msg?.value.delegator_addr
            cell?.validatorCnt.text = "(" + String(mTxInfo!.rewardValidatorsIris().count) + ")"
            
            cell?.validatorLabel0.text = mTxInfo!.rewardValidatorIris(0)
            cell?.monikerLabel0.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(0), true)
            if (mTxInfo!.rewardValidatorsIris().count > 1) {
                cell?.validatorLayer1.isHidden = false
                cell?.validatorLabel1.text = mTxInfo!.rewardValidatorIris(1)
                cell?.monikerLabel1.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(1), true)
            }
            if (mTxInfo!.rewardValidatorsIris().count > 2) {
                cell?.validatorLayer2.isHidden = false
                cell?.validatorLabel2.text = mTxInfo!.rewardValidatorIris(2)
                cell?.monikerLabel2.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(2), true)
            }
            if (mTxInfo!.rewardValidatorsIris().count > 3) {
                cell?.validatorLayer3.isHidden = false
                cell?.validatorLabel3.text = mTxInfo!.rewardValidatorIris(3)
                cell?.monikerLabel3.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(3), true)
            }
            if (mTxInfo!.rewardValidatorsIris().count > 4) {
                cell?.validatorLayer4.isHidden = false
                cell?.validatorLabel4.text = mTxInfo!.rewardValidatorIris(4)
                cell?.monikerLabel4.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(4), true)
            }
            if (mTxInfo!.rewardValidatorsIris().count > 5) {
                cell?.validatorLayer5.isHidden = false
                cell?.validatorLabel5.text = mTxInfo!.rewardValidatorIris(5)
                cell?.monikerLabel5.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(5), true)
            }
            if (mTxInfo!.rewardValidatorsIris().count > 6) {
                cell?.validatorLayer6.isHidden = false
                cell?.validatorLabel6.text = mTxInfo!.rewardValidatorIris(6)
                cell?.monikerLabel6.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(6), true)
            }
            if (mTxInfo!.rewardValidatorsIris().count > 7) {
                cell?.validatorLayer7.isHidden = false
                cell?.validatorLabel7.text = mTxInfo!.rewardValidatorIris(7)
                cell?.monikerLabel7.text = WUtils.getMonikerName(mAllValidator, mTxInfo!.rewardValidatorIris(7), true)
            }
            cell?.amountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleRewardIris().stringValue, cell!.amountLabel.font!, 18, 18)
            
        }
        return cell!
    }
    
    func onBindEditRewardAddress(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxEditRewardAddressCell? = tableView.dequeueReusableCell(withIdentifier:"TxEditRewardAddressCell") as? TxEditRewardAddressCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell?.delegatorLabel.text = msg?.value.delegator_address
            cell?.widthrawAddressLabel.text = msg?.value.withdraw_address
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.delegatorLabel.text = msg?.value.delegator_addr
            cell?.widthrawAddressLabel.text = msg?.value.withdraw_addr
            
        }
        return cell!
    }
    
    func onBindVote(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxVoteCell? = tableView.dequeueReusableCell(withIdentifier:"TxVoteCell") as? TxVoteCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell?.voterLabel.text = msg?.value.voter
            cell?.proposalIdLabel.text = msg?.value.proposal_id
            cell?.opinionLabel.text = msg?.value.option
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.voterLabel.text = msg?.value.voter
            cell?.proposalIdLabel.text = msg?.value.proposal_id
            cell?.opinionLabel.text = msg?.value.option
            
        }
        return cell!
    }
    
    func onBindCommission(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxCommissionCell? = tableView.dequeueReusableCell(withIdentifier:"TxCommissionCell") as? TxCommissionCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.setDenomType(chainType!)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell?.validatorLabel.text = msg?.value.validator_address
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_address!, true)
            cell?.commissionAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleCommission(position - 1).stringValue, cell!.commissionAmountLabel.font!, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell?.validatorLabel.text = msg?.value.validator_addr
            cell?.monikerLabel.text = WUtils.getMonikerName(mAllValidator, msg!.value.validator_addr!, true)
            cell?.commissionAmountLabel.attributedText = WUtils.displayAmount2(mTxInfo?.simpleCommissionIris().stringValue, cell!.commissionAmountLabel.font!, 18, 18)
            
        }
        return cell!
    }
    
    func onBindPostPrice(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxPostPriceCell? = tableView.dequeueReusableCell(withIdentifier:"TxPostPriceCell") as? TxPostPriceCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell?.pricePoster.text = msg?.value.from
            cell?.marketId.text = msg?.value.market_id
            cell?.postPrice.text = msg?.value.price
            cell?.validityTime.text = WUtils.txTimetoString(input: msg!.value.expiry!)
        }
        return cell!
    }
    
    func onBindCreateCdp(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxCreateCdpCell? = tableView.dequeueReusableCell(withIdentifier:"TxCreateCdpCell") as? TxCreateCdpCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let cDenom = msg?.value.collateral!.denom
            let pDenom = msg?.value.principal!.denom
            cell?.senderLabel.text = msg?.value.sender
            cell?.collateralAmount.attributedText = WUtils.displayAmount2(msg?.value.collateral!.amount, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom!), WUtils.getKavaCoinDecimal(cDenom!))
            cell?.principalAmount.attributedText = WUtils.displayAmount2(msg?.value.principal!.amount, cell!.principalAmount.font!, WUtils.getKavaCoinDecimal(pDenom!), WUtils.getKavaCoinDecimal(pDenom!))
            cell?.collateralDenom.text = cDenom!.uppercased()
            cell?.principalDenom.text = pDenom!.uppercased()
        }
        return cell!
    }
    
    func onBindDepositCdp(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxDepositCdpCell? = tableView.dequeueReusableCell(withIdentifier:"TxDepositCdpCell") as? TxDepositCdpCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let cDenom = msg?.value.collateral!.denom
            cell?.owerLabel.text = msg?.value.owner
            cell?.depositorLabel.text = msg?.value.depositor
            cell?.collateralAmount.attributedText = WUtils.displayAmount2(msg?.value.collateral!.amount, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom!), WUtils.getKavaCoinDecimal(cDenom!))
            cell?.collateralDenom.text = cDenom!.uppercased()
        }
        return cell!
    }
    
    func onBindWithdrawCdp(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:TxWithDrawCdpCell? = tableView.dequeueReusableCell(withIdentifier:"TxWithDrawCdpCell") as? TxWithDrawCdpCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let cDenom = msg?.value.collateral!.denom
            cell?.ownerLabel.text = msg?.value.owner
            cell?.depositorLabel.text = msg?.value.depositor
            cell?.collateralAmount.attributedText = WUtils.displayAmount2(msg?.value.collateral!.amount, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom!), WUtils.getKavaCoinDecimal(cDenom!))
            cell?.collateralDenom.text = cDenom!.uppercased()
        }
        return cell!
    }
    
    func onBindDrawDebtCdp(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxdrawDebtCdpCell? = tableView.dequeueReusableCell(withIdentifier:"TxdrawDebtCdpCell") as? TxdrawDebtCdpCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN) {
            cell?.coinTypeLabel.text = msg?.value.cdp_denom?.uppercased()
        } else if (chainType == ChainType.KAVA_TEST) {
            cell?.coinTypeLabel.text = msg?.value.collateral_type?.uppercased()
        }
        let pDenom = msg?.value.principal!.denom
        cell?.senderLabel.text = msg?.value.sender
        cell?.principalAmount.attributedText = WUtils.displayAmount2(msg?.value.principal!.amount, cell!.principalAmount.font!, WUtils.getKavaCoinDecimal(pDenom!), WUtils.getKavaCoinDecimal(pDenom!))
        cell?.principalDenom.text = pDenom!.uppercased()
        return cell!
    }
    
    func onBindRepayDebtCdp(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxRepayCdpCell? = tableView.dequeueReusableCell(withIdentifier:"TxRepayCdpCell") as? TxRepayCdpCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN) {
            cell?.coinTypeLabel.text = msg?.value.cdp_denom?.uppercased()
        } else if (chainType == ChainType.KAVA_TEST) {
            cell?.coinTypeLabel.text = msg?.value.collateral_type?.uppercased()
        }
        let pDenom = msg?.value.payment!.denom
        cell?.senderLabel.text = msg?.value.sender
        cell?.paymentAmount.attributedText = WUtils.displayAmount2(msg?.value.payment!.amount, cell!.paymentAmount.font!, WUtils.getKavaCoinDecimal(pDenom!), WUtils.getKavaCoinDecimal(pDenom!))
        cell?.paymentDenom.text = pDenom!.uppercased()
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
    
    func onBindIncentive(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxIncentiveTableViewCell? = tableView.dequeueReusableCell(withIdentifier:"TxIncentiveTableViewCell") as? TxIncentiveTableViewCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell?.senderLabel.text = msg?.value.sender
            cell?.coinTypeLabel.text = msg?.value.denom?.uppercased()
            
            let incentiveCoin = mTxInfo!.simpleIncentive()
            if (incentiveCoin != nil && !incentiveCoin!.denom.isEmpty) {
                WUtils.showCoinDp(incentiveCoin!, cell!.rewardAmountDenom, cell!.rewardAmount, chainType!)
            } else {
                cell!.rewardAmountDenom.text = ""
                cell!.rewardAmount.text = ""
            }
        }
        return cell!
    }
    
    func onBindOkStake(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxOkStakeCell? = tableView.dequeueReusableCell(withIdentifier:"TxOkStakeCell") as? TxOkStakeCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        if (msg?.type == OK_MSG_TYPE_DEPOSIT) {
            cell?.txIcon.image = UIImage(named: "txDepositCdp")
            cell?.txLabel.text = NSLocalizedString("title_ok_deposit", comment: "")
        } else {
            cell?.txIcon.image = UIImage(named: "txWithdrawCdp")
            cell?.txLabel.text = NSLocalizedString("title_ok_withdraw", comment: "")
        }
        cell?.delegatorLabel.text = msg?.value.delegator_address
        WUtils.showCoinDp(msg!.value.quantity!, cell!.stakeDenom, cell!.stakeAmount, chainType!)
        return cell!
    }
    
    func onBindOkDirectVote(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
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
    
    
    func onBindUnknown(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:TxUnknownCell? = tableView.dequeueReusableCell(withIdentifier:"TxUnknownCell") as? TxUnknownCell
        let msg = mTxInfo?.getMsg(position - 1)
        cell?.txIcon.image = cell?.txIcon.image?.withRenderingMode(.alwaysTemplate)
        cell?.txIcon.tintColor = WUtils.getChainColor(chainType!)
        return cell!
    }
    
    
    @IBAction func onClickShare(_ sender: UIButton) {
        if (self.chainType! == ChainType.COSMOS_MAIN) {
            let text = EXPLORER_COSMOS_MAIN + "txs/" + mTxInfo!.txhash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.IRIS_MAIN) {
            let text = EXPLORER_IRIS_MAIN + "txs/" + mTxInfo!.hash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.BINANCE_MAIN) {
            let text = EXPLORER_BINANCE_MAIN + "txs/" + mTxInfo!.hash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.KAVA_MAIN) {
            let text = EXPLORER_KAVA_MAIN + "txs/" + mTxInfo!.txhash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.BAND_MAIN) {
            let text = EXPLORER_BAND_MAIN + "tx/" + mTxInfo!.txhash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.IOV_MAIN) {
            let text = EXPLORER_IOV_MAIN + "txs/" + mTxInfo!.txhash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.OKEX_TEST) {
            let text = EXPLORER_OKEX_TEST + "tx/" + mTxInfo!.txhash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.BINANCE_TEST) {
            let text = EXPLORER_BINANCE_TEST + "tx/" + mTxInfo!.hash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.KAVA_TEST) {
            let text = EXPLORER_KAVA_TEST + "txs/" + mTxInfo!.txhash!
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.chainType! == ChainType.CERTIK_TEST) {
            let text = EXPLORER_CERTIK_TEST + "Transactions/" + mTxInfo!.txhash!  + "?net=" + WUtils.getChainId(self.chainType!)
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
        }
        
    }
    
    func onClickExplorer() {
        if (self.chainType! == ChainType.COSMOS_MAIN) {
            guard let url = URL(string: EXPLORER_COSMOS_MAIN + "txs/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.IRIS_MAIN) {
            guard let url = URL(string: EXPLORER_IRIS_MAIN + "txs/" + mTxInfo!.hash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: EXPLORER_BINANCE_MAIN + "txs/" + mTxInfo!.hash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.KAVA_MAIN) {
            guard let url = URL(string: EXPLORER_KAVA_MAIN + "txs/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: EXPLORER_BAND_MAIN + "tx/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.SECRET_MAIN) {
            guard let url = URL(string: EXPLORER_SECRET_MAIN + "transactions/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.IOV_MAIN) {
            guard let url = URL(string: EXPLORER_IOV_MAIN + "txs/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.BINANCE_TEST) {
            guard let url = URL(string: EXPLORER_BINANCE_TEST + "tx/" + mTxInfo!.hash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: EXPLORER_OKEX_TEST + "tx/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: EXPLORER_KAVA_TEST + "txs/" + mTxInfo!.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (self.chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: EXPLORER_CERTIK_TEST + "Transactions/" + mTxInfo!.txhash!  + "?net=" + WUtils.getChainId(self.chainType!)) else { return }
            self.onShowSafariWeb(url)
        }
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
        var url = ""
        var request:DataRequest?
        if (self.chainType! == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.BINANCE_MAIN) {
            url = BNB_URL_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.BAND_MAIN) {
            url = BAND_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.IOV_MAIN) {
            url = IOV_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.OKEX_TEST) {
            url = OKEX_TEST_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            
        } else if (self.chainType! == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_TX + txHash
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        }
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
//        print("onFetchHtlcStatus ", swapId)
        if (swapId == nil) {self.onUpdateView()}
        var url = ""
        if (self.chainType! == ChainType.BINANCE_MAIN) {
            url = BNB_URL_CHECK_SWAPID + swapId!
            
        } else if (self.chainType! == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_CHECK_SWAPID + swapId!
            
        } else if (self.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_CHECK_SWAPID + swapId!
            
        } else if (self.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CHECK_SWAPID + swapId!
        }
        print("swapId url ", url)
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
        var url = ""
        if (self.chainType! == ChainType.BINANCE_MAIN) {
            url = BNB_URL_NODE_INFO
            
        } else if (self.chainType! == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_NODE_INFO
            
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
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
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            return coins.sorted(by: {
                if ($0.denom == OKEX_TEST_DENOM) {
                    return true
                }
                if ($1.denom == OKEX_TEST_DENOM) {
                    return false
                }
                return false
            })
        }
        return coins
    }
}
