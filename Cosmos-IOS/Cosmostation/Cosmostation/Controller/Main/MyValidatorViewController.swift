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
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
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
        self.balances = BaseData.instance.selectBalanceById(accountId: self.account!.account_id)
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
    }
    
    @objc func onSortingMy() {
        if (WUtils.isGRPC(chainType!)) {
            self.myValidatorCnt.text = String(BaseData.instance.mMyValidators_gRPC.count)
        } else {
            self.myValidatorCnt.text = String(BaseData.instance.mMyValidator.count)
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
            if (BaseData.instance.mMyValidator.count < 1) { return 1; }
            else if (BaseData.instance.mMyValidator.count == 1) { return 1; }
            else { return BaseData.instance.mMyValidator.count + 1; }
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
            if (BaseData.instance.mMyValidator.count < 1) {
                let cell:PromotionCell? = tableView.dequeueReusableCell(withIdentifier:"PromotionCell") as? PromotionCell
                cell?.cardView.backgroundColor = WUtils.getChainBg(chainType)
                return cell!
                
            } else if (BaseData.instance.mMyValidator.count == 1) {
                let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                let validator = BaseData.instance.mMyValidator[indexPath.row]
                self.onSetValidatorItem(cell!, validator, indexPath)
                return cell!
                
            } else {
                if (indexPath.row == BaseData.instance.mMyValidator.count) {
                    let cell:ClaimRewardAllCell? = tableView.dequeueReusableCell(withIdentifier:"ClaimRewardAllCell") as? ClaimRewardAllCell
                    self.onSetClaimAllItem(cell!)
                    return cell!
                } else {
                    let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
                    let validator = BaseData.instance.mMyValidator[indexPath.row]
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
            if (BaseData.instance.mMyValidator.count > 0 && indexPath.row != BaseData.instance.mMyValidator.count) {
                let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
                validatorDetailVC.mValidator = BaseData.instance.mMyValidator[indexPath.row]
                validatorDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            }
        }
    }
    
    func onSetValidatorItem(_ cell: MyValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        cell.cardView.backgroundColor = WUtils.getChainBg(chainType)
        cell.monikerLabel.text = validator.description.moniker
        cell.monikerLabel.adjustsFontSizeToFitWidth = true
        cell.freeEventImg.isHidden = true
        if (validator.jailed) {
            cell.revokedImg.isHidden = false
            cell.validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            cell.revokedImg.isHidden = true
            cell.validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        cell.validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType!, validator.operator_address))!)
        
        let delegated = BaseData.instance.delegatedAmountByValidator(validator.operator_address)
        cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmount2(delegated.stringValue, cell.myDelegatedAmoutLabel.font, WUtils.mainDivideDecimal(chainType), 6)
        
        let unbonding = BaseData.instance.unbondingAmountByValidator(validator.operator_address)
        cell.myUndelegatingAmountLabel.attributedText =  WUtils.displayAmount2(unbonding.stringValue, cell.myUndelegatingAmountLabel.font, WUtils.mainDivideDecimal(chainType), 6)
        
        let reward = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address)
        cell.rewardAmoutLabel.attributedText = WUtils.displayAmount2(reward.stringValue, cell.rewardAmoutLabel.font, WUtils.mainDivideDecimal(chainType), 6)

        if (chainType == ChainType.BAND_MAIN) {
            if let oracle = mBandOracleStatus?.isEnable(validator.operator_address) {
                if (!oracle) { cell.bandOracleOffImg.isHidden = false }
            }
        }
    }
    
    func onSetClaimAllItem(_ cell: ClaimRewardAllCell) {
        WUtils.setDenomTitle(chainType!, cell.denomLabel)
        if (WUtils.isGRPC(chainType!)) {
            cell.totalRewardLabel.attributedText = WUtils.displayAmount2(BaseData.instance.getRewardSum(WUtils.getMainDenom(chainType)), cell.totalRewardLabel.font, WUtils.mainDivideDecimal(chainType), 6)
            cell.delegate = self
            
        } else {
            cell.totalRewardLabel.attributedText = WUtils.displayAmount2(BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).stringValue, cell.totalRewardLabel.font, WUtils.mainDivideDecimal(chainType), 6)
            cell.delegate = self
        }
    }
    
    func didTapClaimAll(_ sender: UIButton) {
        if(!self.account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        var toClaimValidator        = Array<Validator>()
        var toClaimValidator_gRPC   = Array<Cosmos_Staking_V1beta1_Validator>()
        
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(NSDecimalNumber.one).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(NSDecimalNumber.one).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(NSDecimalNumber.init(string: "37500")).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(NSDecimalNumber.init(string: "150000")).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(NSDecimalNumber.init(string: "150000")).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(NSDecimalNumber.init(string: "7500")).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, 1)
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(feeAmount).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            let available = WUtils.getTokenAmount(balances, SENTINEL_MAIN_DENOM)
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.FETCH_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, 1)
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(feeAmount).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: FETCH_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler18)
            let available = WUtils.getTokenAmount(balances, WUtils.getMainDenom(chainType))
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.SIF_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, 1)
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(feeAmount).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: SIF_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler18)
            let available = WUtils.getTokenAmount(balances, WUtils.getMainDenom(chainType))
            if (available.compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.KI_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, 1)
            if (BaseData.instance.rewardAmount(WUtils.getMainDenom(chainType)).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            BaseData.instance.mAllValidator.forEach { validator in
                if (BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), validator.operator_address).compare(feeAmount).rawValue > 0) {
                    myBondedValidator.append(validator)
                }
            }
            myBondedValidator.sort {
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
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
            let estimatedFeeAmount = estimatedGasAmount.multiplying(by: NSDecimalNumber.init(string: KI_GAS_FEE_RATE_AVERAGE), withBehavior: WUtils.handler18)
            let available = WUtils.getTokenAmount(balances, WUtils.getMainDenom(chainType))
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
            
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_WITHDRAW_DEL, toClaimValidator_gRPC.count)
            if (BaseData.instance.getAvailableAmount(WUtils.getMainDenom(chainType)).compare(feeAmount).rawValue < 0) {
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
            BaseData.instance.mMyValidator.sort{
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
            BaseData.instance.mMyValidator.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                let firstVal = BaseData.instance.delegatedAmountByValidator($0.operator_address)
                let seconVal = BaseData.instance.delegatedAmountByValidator($1.operator_address)
                return firstVal.compare(seconVal).rawValue > 0 ? true : false
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
            BaseData.instance.mMyValidator.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                let reward0 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $0.operator_address)
                let reward1 = BaseData.instance.rewardAmountByValidator(WUtils.getMainDenom(chainType), $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
        }
        
    }
}
