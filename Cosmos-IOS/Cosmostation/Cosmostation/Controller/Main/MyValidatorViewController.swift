//
//  MyValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MyValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, ClaimRewardAllDelegate {
    
    @IBOutlet weak var myValidatorCnt: UILabel!
    @IBOutlet weak var btnSort: UIView!
    @IBOutlet weak var sortType: UILabel!
    @IBOutlet weak var myValidatorTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var mBandOracleStatus: BandOracleStatus?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mBandOracleStatus = BaseData.instance.mBandOracleStatus
        self.myValidatorTableView.delegate = self
        self.myValidatorTableView.dataSource = self
        self.myValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myValidatorTableView.register(UINib(nibName: "MyValidatorCell", bundle: nil), forCellReuseIdentifier: "MyValidatorCell")
        self.myValidatorTableView.register(UINib(nibName: "ClaimRewardAllCell", bundle: nil), forCellReuseIdentifier: "ClaimRewardAllCell")
        self.myValidatorTableView.register(UINib(nibName: "PromotionCell", bundle: nil), forCellReuseIdentifier: "PromotionCell")
        self.myValidatorTableView.rowHeight = UITableView.automaticDimension
        self.myValidatorTableView.estimatedRowHeight = UITableView.automaticDimension

        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.myValidatorTableView.addSubview(refresher)
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onStartSort))
        self.btnSort.addGestureRecognizer(tap)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
        self.chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.balances = BaseData.instance.selectBalanceById(accountId: mainTabVC.mAccount!.account_id)
        self.onSortingMy()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceFetchDone(_:)), name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.mBandOracleStatus = BaseData.instance.mBandOracleStatus
        self.onSortingMy()
        self.refresher.endRefreshing()
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        print("onPriceFetchDone")
    }
    
    @objc func onSortingMy() {
        if (WUtils.isGRPC(chainType!)) {
            self.myValidatorCnt.text = String(BaseData.instance.mMyValidators_gRPC.count)
        } else {
            self.myValidatorCnt.text = String(self.mainTabVC.mMyValidators.count)
        }
        
        if (BaseData.instance.getMyValidatorSort() == 0) {
            self.sortType.text = NSLocalizedString("sort_by_my_delegate", comment: "")
            sortByDelegated()
        } else if (BaseData.instance.getMyValidatorSort() == 1) {
            self.sortType.text = NSLocalizedString("sort_by_name", comment: "")
            sortByName()
        } else {
            self.sortType.text = NSLocalizedString("sort_by_reward", comment: "")
            sortByReward()
        }
        self.myValidatorTableView.reloadData()
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (WUtils.isGRPC(chainType!)) {
            if (BaseData.instance.mMyValidators_gRPC.count < 1) { return 1; }
            else if (BaseData.instance.mMyValidators_gRPC.count == 1) { return 1; }
            else { return BaseData.instance.mMyValidators_gRPC.count + 1; }
            
        } else {
            if (self.mainTabVC.mMyValidators.count < 1) { return 1; }
            else if (self.mainTabVC.mMyValidators.count == 1) { return 1; }
            else { return self.mainTabVC.mMyValidators.count + 1; }
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (WUtils.isGRPC(chainType!)) {
            if (BaseData.instance.mMyValidators_gRPC.count < 1) {
                let cell:PromotionCell? = tableView.dequeueReusableCell(withIdentifier:"PromotionCell") as? PromotionCell
                cell?.cardView.backgroundColor = WUtils.getChainBg(chainType)
                return cell!
                
            } else if (BaseData.instance.mMyValidators_gRPC.count == 1) {
                let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                cell?.updateView(BaseData.instance.mMyValidators_gRPC[indexPath.row], self.chainType)
                return cell!
                
            } else {
                if (indexPath.row == BaseData.instance.mMyValidators_gRPC.count) {
                    let cell:ClaimRewardAllCell? = tableView.dequeueReusableCell(withIdentifier:"ClaimRewardAllCell") as? ClaimRewardAllCell
                    self.onSetClaimAllItem(cell!)
                    return cell!
                } else {
                    let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                    cell?.updateView(BaseData.instance.mMyValidators_gRPC[indexPath.row], self.chainType)
                    return cell!
                }
            }
            
        } else {
            if (mainTabVC.mMyValidators.count < 1) {
                let cell:PromotionCell? = tableView.dequeueReusableCell(withIdentifier:"PromotionCell") as? PromotionCell
                cell?.cardView.backgroundColor = WUtils.getChainBg(chainType)
                return cell!
                
            } else if (mainTabVC.mMyValidators.count == 1) {
                let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                let validator = mainTabVC.mMyValidators[indexPath.row]
                self.onSetValidatorItem(cell!, validator, indexPath)
                return cell!
                
            } else {
                if (indexPath.row == mainTabVC.mMyValidators.count) {
                    let cell:ClaimRewardAllCell? = tableView.dequeueReusableCell(withIdentifier:"ClaimRewardAllCell") as? ClaimRewardAllCell
                    self.onSetClaimAllItem(cell!)
                    return cell!
                } else {
                    let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                    let validator = mainTabVC.mMyValidators[indexPath.row]
                    self.onSetValidatorItem(cell!, validator, indexPath)
                    return cell!
                }
            }
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (WUtils.isGRPC(chainType!)) {
            if (BaseData.instance.mMyValidators_gRPC.count > 0 && indexPath.row != BaseData.instance.mMyValidators_gRPC.count) {
                let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
                validatorDetailVC.mValidator_gRPC = BaseData.instance.mMyValidators_gRPC[indexPath.row]
                validatorDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            }
            
        } else {
            if (mainTabVC.mMyValidators.count > 0 && indexPath.row != mainTabVC.mMyValidators.count) {
                if let validator = self.mainTabVC.mMyValidators[indexPath.row] as? Validator {
                    let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
                    validatorDetailVC.mValidator = validator
                    validatorDetailVC.mIsTop100 = mainTabVC.mTopValidators.contains(where: {$0.operator_address == validator.operator_address})
                    validatorDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(validatorDetailVC, animated: true)
                }
            }
        }
    }
    
    func onSetValidatorItem(_ cell: MyValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        cell.monikerLabel.text = validator.description.moniker
        cell.monikerLabel.adjustsFontSizeToFitWidth = true
        cell.freeEventImg.isHidden = true
        if(validator.jailed) {
            cell.revokedImg.isHidden = false
            cell.validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            cell.revokedImg.isHidden = true
            cell.validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }

        let bonding = BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, validator.operator_address)
        if (bonding != nil) {
            cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmount(bonding!.getBondingAmount(validator).stringValue, cell.myDelegatedAmoutLabel.font, 6, chainType!)
        } else {
            cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.myDelegatedAmoutLabel.font, 6, chainType!)
        }

        let unbonding = BaseData.instance.selectUnBondingWithValAdd(mainTabVC.mAccount.account_id, validator.operator_address)
        var unbondSum = NSDecimalNumber.zero
        for unbond in unbonding {
            unbondSum = unbondSum.adding(WUtils.localeStringToDecimal(unbond.unbonding_balance))
        }
        cell.myUndelegatingAmountLabel.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell.myUndelegatingAmountLabel.font, 6, chainType!)

        cell.cardView.backgroundColor = WUtils.getChainBg(chainType)
        cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
        cell.validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType!, validator.operator_address))!)
        
        if (chainType == ChainType.BAND_MAIN) {
            if let oracle = mBandOracleStatus?.isEnable(validator.operator_address) {
                if (!oracle) { cell.bandOracleOffImg.isHidden = false }
            }
        }
    }
    
    func onSetClaimAllItem(_ cell: ClaimRewardAllCell) {
        WUtils.setDenomTitle(chainType!, cell.denomLabel)
        if (WUtils.isGRPC(chainType!)) {
            cell.totalRewardLabel.attributedText = WUtils.displayAmount2(BaseData.instance.getRewardSum(WUtils.getMainDenom(chainType)), cell.totalRewardLabel.font, 6, 6)
            cell.delegate = self
            
        } else {
            cell.totalRewardLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.totalRewardLabel.font, 6, chainType!)
            if (mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, WUtils.getMainDenom(chainType), chainType!)
            }
            cell.delegate = self
        }
    }
    
    func didTapClaimAll(_ sender: UIButton) {
        if(!self.mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        var toClaimValidator        = Array<Validator>()
        var toClaimValidator_gRPC   = Array<Cosmos_Staking_V1beta1_Validator>()
        
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, KAVA_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.one).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            print("toClaimValidator ", toClaimValidator.count)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, BAND_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.one).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, SECRET_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if (bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "37500")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            if (toClaimValidator.count <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            let estimatedGasAmount = WUtils.getEstimateGasAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, toClaimValidator.count)
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: SECRET_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler6)
            let available = WUtils.getTokenAmount(balances, SECRET_MAIN_DENOM)
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, IOV_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "150000")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            if (toClaimValidator.count <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            let estimatedGasAmount = WUtils.getEstimateGasAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, toClaimValidator.count)
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: IOV_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler6)
            let available = WUtils.getTokenAmount(balances, IOV_MAIN_DENOM)
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, IOV_TEST_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "150000")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            if (toClaimValidator.count <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            let estimatedGasAmount = WUtils.getEstimateGasAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, toClaimValidator.count)
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: IOV_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler6)
            let available = WUtils.getTokenAmount(balances, IOV_TEST_DENOM)
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
                   
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, CERTIK_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "7500")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            if (toClaimValidator.count <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            let estimatedGasAmount = WUtils.getEstimateGasAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, toClaimValidator.count)
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: CERTIK_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler6)
            let available = WUtils.getTokenAmount(balances, CERTIK_MAIN_DENOM)
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        }
        
        else if (WUtils.isGRPC(chainType!)) {
            var claimAbleValidators = Array<Cosmos_Staking_V1beta1_Validator>()
            BaseData.instance.mMyValidators_gRPC.forEach { validator in
                if (BaseData.instance.getReward(WUtils.getMainDenom(chainType), validator.operatorAddress).compare(NSDecimalNumber.init(string: "3750")).rawValue > 0) {
                    claimAbleValidators.append(validator)
                }
            }
            if (claimAbleValidators.count == 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_reward", comment: ""))
                return;
            }
            claimAbleValidators.sort {
                let reward0 = BaseData.instance.getReward(WUtils.getMainDenom(chainType), $0.operatorAddress)
                let reward1 = BaseData.instance.getReward(WUtils.getMainDenom(chainType), $1.operatorAddress)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (claimAbleValidators.count > 16) {
                toClaimValidator_gRPC = Array(claimAbleValidators[0..<16])
            } else {
                toClaimValidator_gRPC = claimAbleValidators
            }
            
            let estimatedGasAmount = WUtils.getEstimateGasAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, toClaimValidator_gRPC.count)
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(value: GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler6)
            if (BaseData.instance.getAvailableAmount(WUtils.getMainDenom(chainType)).compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))//TODO
            return
            
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mRewardTargetValidators = toClaimValidator
        txVC.mRewardTargetValidators_gRPC = toClaimValidator_gRPC
        txVC.mType = COSMOS_MSG_TYPE_WITHDRAW_DEL
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    @objc func onStartSort() {
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: UIAlertAction.Style.cancel, handler: nil))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_name", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            BaseData.instance.setMyValidatorSort(1)
            self.onSortingMy()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_my_delegate", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            BaseData.instance.setMyValidatorSort(0)
            self.onSortingMy()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_reward", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            BaseData.instance.setMyValidatorSort(2)
            self.onSortingMy()
        }))
        self.present(alert, animated: true, completion: nil)
    }
    
    func sortByName() {
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mMyValidators_gRPC.sort{
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return $0.description_p.moniker < $1.description_p.moniker
            }
            
        } else {
            mainTabVC.mMyValidators.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return $0.description.moniker < $1.description.moniker
            }
        }
    }
    
    func sortByDelegated() {
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mMyValidators_gRPC.sort {
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                let firstVal = BaseData.instance.getDelegated($0.operatorAddress)
                let seconVal = BaseData.instance.getDelegated($1.operatorAddress)
                return firstVal.compare(seconVal).rawValue > 0 ? true : false
            }
            
        } else {
            mainTabVC.mMyValidators.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                var bonding0:Double = 0
                var bonding1:Double = 0
                if (BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $0.operator_address) != nil) {
                    bonding0 = Double(BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $0.operator_address)!.getBondingAmount($0)) as! Double
                }
                if (BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $1.operator_address) != nil) {
                    bonding1 = Double(BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $1.operator_address)!.getBondingAmount($1)) as! Double
                }
                return bonding0 > bonding1
            }
            
        }
    }
    
    func sortByReward() {
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mMyValidators_gRPC.sort {
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                let firstVal = BaseData.instance.getReward(WUtils.getMainDenom(self.chainType), $0.operatorAddress)
                let seconVal = BaseData.instance.getReward(WUtils.getMainDenom(self.chainType), $1.operatorAddress)
                return firstVal.compare(seconVal).rawValue > 0 ? true : false
            }
            
        } else {
            mainTabVC.mMyValidators.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                let reward0 = WUtils.getValidatorReward(self.chainType, mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(self.chainType, mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
        }
        
    }
}
