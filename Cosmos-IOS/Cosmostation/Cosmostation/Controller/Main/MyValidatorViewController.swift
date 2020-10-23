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
        self.myValidatorCnt.text = String(self.mainTabVC.mMyValidators.count)
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
        if (self.mainTabVC.mMyValidators.count < 1) {
            return 1;
        } else if (self.mainTabVC.mMyValidators.count == 1) {
            return 1;
        } else {
            return self.mainTabVC.mMyValidators.count  + 1;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (mainTabVC.mMyValidators.count < 1) {
            let cell:PromotionCell? = tableView.dequeueReusableCell(withIdentifier:"PromotionCell") as? PromotionCell
            if (chainType == ChainType.COSMOS_MAIN) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            } else if (chainType == ChainType.IRIS_MAIN) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_IRIS
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_KAVA
            } else if (chainType == ChainType.BAND_MAIN) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_BAND
            } else if (chainType == ChainType.SECRET_MAIN) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_SECRET
            } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_IOV
            } else if (chainType == ChainType.CERTIK_TEST) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_CERTIK
            }
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
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
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

        if (chainType == ChainType.COSMOS_MAIN) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            let url = COSMOS_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_IRIS
            if (mainTabVC.mIrisRewards != nil) {
                cell.rewardAmoutLabel.attributedText = WUtils.displayAmount((mainTabVC.mIrisRewards?.getPerValReward(valOp: validator.operator_address).stringValue)!, cell.rewardAmoutLabel.font, 6, chainType!)
            } else {
                cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            }
            let url = IRIS_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_KAVA
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            let url = KAVA_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_BAND
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            if let oracle = mBandOracleStatus?.isEnable(validator.operator_address) {
                if (!oracle) { cell.bandOracleOffImg.isHidden = false }
            }
            let url = BAND_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_SECRET
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            let url = SECRET_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_IOV
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            let url = IOV_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_CERTIK
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, chainType!)
            let url = CERTIK_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
        }
    }
    
    func onSetClaimAllItem(_ cell: ClaimRewardAllCell) {
        WUtils.setDenomTitle(chainType!, cell.denomLabel)
        cell.totalRewardLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.totalRewardLabel.font, 6, chainType!)
        if (chainType == ChainType.COSMOS_MAIN) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, COSMOS_MAIN_DENOM, chainType!)
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (mainTabVC.mIrisRewards != nil) {
                cell.totalRewardLabel.attributedText = WUtils.displayAmount((mainTabVC.mIrisRewards?.getSimpleIrisReward().stringValue)!, cell.totalRewardLabel.font, 6, chainType!)
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, KAVA_MAIN_DENOM, chainType!)
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, BAND_MAIN_DENOM, chainType!)
            }
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, SECRET_MAIN_DENOM, chainType!)
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, IOV_MAIN_DENOM, chainType!)
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, IOV_TEST_DENOM, chainType!)
            }
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6, CERTIK_MAIN_DENOM, chainType!)
            }
        }
        cell.delegate = self
    }
    
    
    func didTapClaimAll(_ sender: UIButton) {
        if(!self.mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        var toClaimValidator = Array<Validator>()
        
        if (chainType == ChainType.IRIS_MAIN) {
            if (self.mainTabVC.mIrisRewards != nil && (self.mainTabVC.mIrisRewards?.delegations.count)! > 0) {
                for validator in self.mainTabVC.mAllValidator {
                    for delegation in self.mainTabVC.mIrisRewards!.delegations {
                        if (validator.operator_address == delegation.validator) {
                            toClaimValidator.append(validator)
                        }
                    }
                }
            }
            if (toClaimValidator.count <= 0 ) {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            let estimatedGasAmount = (NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_MUX).multiplying(by: NSDecimalNumber.init(value: toClaimValidator.count))).adding(NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_BASE))
            let estimatedFeeAmount = estimatedGasAmount.multiplying(byPowerOf10: 18).multiplying(by: WUtils.plainStringToDecimal(GAS_FEE_RATE_IRIS_AVERAGE), withBehavior: WUtils.handler0)
            if(balances.count <= 0 || WUtils.localeStringToDecimal(balances[0].balance_amount).compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
            if ((mainTabVC.mIrisRewards?.getSimpleIrisReward().compare(estimatedFeeAmount).rawValue)! <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
            txVC.mRewardTargetValidators = toClaimValidator
            txVC.mType = IRIS_MSG_TYPE_WITHDRAW
            txVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txVC, animated: true)
            return
        }
        
        if (chainType == ChainType.COSMOS_MAIN) {
            if(WUtils.getAllRewardByDenom(mainTabVC.mRewardList, COSMOS_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.one).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, KAVA_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.one).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
            if (myBondedValidator.count > 16) {
                toClaimValidator = Array(myBondedValidator[0..<16])
            } else {
                toClaimValidator = myBondedValidator
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (WUtils.getAllRewardByDenom(mainTabVC.mRewardList, BAND_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.one).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
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
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "37500")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
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
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "1500000")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
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
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber.init(string: "1500000")).rawValue > 0) {
                        myBondedValidator.append(validator)
                        break;
                    }
                }
            }
            myBondedValidator.sort {
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
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
                   
        } else if (chainType == ChainType.CERTIK_TEST) {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))//TODO
            return
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))//TODO
            return
            
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mRewardTargetValidators = toClaimValidator
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
        mainTabVC.mMyValidators.sort{
            if ($0.description.moniker == "Cosmostation") {
                return true
            }
            if ($1.description.moniker == "Cosmostation"){
                return false
            }
            if ($0.jailed && !$1.jailed) {
                return false
            }
            if (!$0.jailed && $1.jailed) {
                return true
            }
            return $0.description.moniker < $1.description.moniker
        }
    }
    
    func sortByDelegated() {
        mainTabVC.mMyValidators.sort{
            if ($0.description.moniker == "Cosmostation") {
                return true
            }
            if ($1.description.moniker == "Cosmostation"){
                return false
            }
            if ($0.jailed && !$1.jailed) {
                return false
            }
            if (!$0.jailed && $1.jailed) {
                return true
            }
            var bonding0:Double = 0
            var bonding1:Double = 0
            if(BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $0.operator_address) != nil) {
                bonding0 = Double(BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $0.operator_address)!.getBondingAmount($0)) as! Double
            }
            if(BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $1.operator_address) != nil) {
                bonding1 = Double(BaseData.instance.selectBondingWithValAdd(mainTabVC.mAccount.account_id, $1.operator_address)!.getBondingAmount($1)) as! Double
            }
            return bonding0 > bonding1
        }
    }
    
    func sortByReward() {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            mainTabVC.mMyValidators.sort{
                if ($0.description.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description.moniker == "Cosmostation"){
                    return false
                }
                if ($0.jailed && !$1.jailed) {
                    return false
                }
                if (!$0.jailed && $1.jailed) {
                    return true
                }
                let reward0 = WUtils.getValidatorReward(mainTabVC.mRewardList, $0.operator_address)
                let reward1 = WUtils.getValidatorReward(mainTabVC.mRewardList, $1.operator_address)
                return reward0.compare(reward1).rawValue > 0 ? true : false
            }
        } else if (chainType == ChainType.IRIS_MAIN) {
            mainTabVC.mMyValidators.sort{
                if ($0.description.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description.moniker == "Cosmostation"){
                    return false
                }
                if ($0.jailed && !$1.jailed) {
                    return false
                }
                if (!$0.jailed && $1.jailed) {
                    return true
                }
                let reward0 = mainTabVC.mIrisRewards?.getPerValReward(valOp: $0.operator_address)
                let reward1 = mainTabVC.mIrisRewards?.getPerValReward(valOp: $1.operator_address)
                return reward0!.compare(reward1!).rawValue > 0 ? true : false
            }
        }
        
    }
}
