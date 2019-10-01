//
//  MyValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class MyValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, ClaimRewardAllDelegate {
    
    @IBOutlet weak var myValidatorTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var userChain: ChainType?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.myValidatorTableView.delegate = self
        self.myValidatorTableView.dataSource = self
        self.myValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myValidatorTableView.register(UINib(nibName: "MyValidatorCell", bundle: nil), forCellReuseIdentifier: "MyValidatorCell")
        self.myValidatorTableView.register(UINib(nibName: "ClaimRewardAllCell", bundle: nil), forCellReuseIdentifier: "ClaimRewardAllCell")
        self.myValidatorTableView.register(UINib(nibName: "PromotionCell", bundle: nil), forCellReuseIdentifier: "PromotionCell")

        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.myValidatorTableView.addSubview(refresher)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
        self.userChain = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.onSortingMy()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onSortingMy), name: Notification.Name("onSortingMy"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onSortingMy"), object: nil)
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.onSortingMy()
        self.refresher.endRefreshing()
    }
    
    @objc func onSortingMy() {
        if (BaseData.instance.getMyValidatorSort() == 0) {
            sortByDelegated()
        } else if (BaseData.instance.getMyValidatorSort() == 1) {
            sortByName()
        } else {
            sortByReward()
        }
        self.myValidatorTableView.reloadData()
    }
    
    @objc func onRequestFetch() {
        if(!mainTabVC.onFetchAccountData()) {
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
            if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                cell?.cardView.backgroundColor = TRANS_BG_COLOR_IRIS
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
        if (mainTabVC.mMyValidators.count < 1) {
            return 168;
        } else {
            if (indexPath.row == mainTabVC.mMyValidators.count) {
                return 70;
            } else {
                return 95;
            }
            
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("My didSelectRowAt")
        if (mainTabVC.mMyValidators.count > 0 && indexPath.row != mainTabVC.mMyValidators.count) {
            if let validator = self.mainTabVC.mMyValidators[indexPath.row] as? Validator {
                let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
                validatorDetailVC.mValidator = validator
                validatorDetailVC.mInflation = mainTabVC.mInflation
                validatorDetailVC.mProvision = mainTabVC.mProvision
                validatorDetailVC.mStakingPool = mainTabVC.mStakingPool
                validatorDetailVC.mIrisStakePool = mainTabVC.mIrisStakePool
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
        if(bonding != nil) {
            cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmount(bonding!.getBondingAmount(validator).stringValue, cell.myDelegatedAmoutLabel.font, 6, userChain!)
        } else {
            cell.myDelegatedAmoutLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.myDelegatedAmoutLabel.font, 6, userChain!)
        }
        
        let unbonding = BaseData.instance.selectUnBondingWithValAdd(mainTabVC.mAccount.account_id, validator.operator_address)
        var unbondSum = NSDecimalNumber.zero
        for unbond in unbonding {
            unbondSum = unbondSum.adding(WUtils.stringToDecimal(unbond.unbonding_balance))
        }
        cell.myUndelegatingAmountLabel.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell.myUndelegatingAmountLabel.font, 6, userChain!)
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(WUtils.getValidatorReward(mainTabVC.mRewardList, validator.operator_address).stringValue, cell.rewardAmoutLabel.font, 6, userChain!)
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_IRIS
            if (mainTabVC.mIrisRewards != nil) {
                cell.rewardAmoutLabel.attributedText = WUtils.displayAmount((mainTabVC.mIrisRewards?.getPerValReward(valOp: validator.operator_address).stringValue)!, cell.rewardAmoutLabel.font, 6, userChain!)
            } else {
                cell.rewardAmoutLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.rewardAmoutLabel.font, 6, userChain!)
            }
        }
        
        cell.validatorImg.tag = indexPath.row
        cell.validatorImg.image = UIImage.init(named: "validatorNoneImg")
        if (validator.description.identity != "") {
            let parameters: Parameters = ["fields": "pictures", "key_suffix": validator.description.identity]
            let request = Alamofire.request(KEY_BASE_URL_USER_INFO,
                                            method: .get,
                                            parameters: parameters,
                                            encoding: URLEncoding.default,
                                            headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let keybaseInfo = res as? NSDictionary,
                        let thems = keybaseInfo.value(forKey: "them") as? Array<NSDictionary>,
                        thems.count > 0,
                        let url = thems[0].value(forKeyPath: "pictures.primary.url") as? String else {
                        return
                    }

                    Alamofire.request(url, method: .get).responseImage { response  in
                        guard let image = response.result.value else {
                            return
                        }
                        if(indexPath.row == cell.validatorImg.tag) {
                            cell.validatorImg.image = image
                        }
                    }

                case .failure(let error):
                    print("onSetValidatorItem error : ", error)
                }
            }
        }
    }
    
    func onSetClaimAllItem(_ cell: ClaimRewardAllCell) {
        WUtils.setDenomTitle(userChain!, cell.denomLabel)
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if(mainTabVC.mRewardList.count > 0) {
                cell.totalRewardLabel.attributedText = WUtils.displayAllAtomReward(mainTabVC.mRewardList, cell.totalRewardLabel.font, 6)
            } else {
                cell.totalRewardLabel.attributedText = WUtils.displayAmount("0", cell.totalRewardLabel.font, 6, userChain!)
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (mainTabVC.mIrisRewards != nil) {
                cell.totalRewardLabel.attributedText = WUtils.displayAmount((mainTabVC.mIrisRewards?.getSimpleIrisReward().stringValue)!, cell.totalRewardLabel.font, 6, userChain!)
            } else {
                cell.totalRewardLabel.attributedText = WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell.totalRewardLabel.font, 6, userChain!)
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
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if(WUtils.getAllAtomReward(mainTabVC.mRewardList).compare(NSDecimalNumber.zero).rawValue <= 0 ){
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
            var myBondedValidator = Array<Validator>()
            for validator in self.mainTabVC.mAllValidator {
                for bonding in self.mainTabVC.mBondingList {
                    if(bonding.bonding_v_address == validator.operator_address &&
                        WUtils.getValidatorReward(mainTabVC.mRewardList, bonding.bonding_v_address).compare(NSDecimalNumber(string: "1")).rawValue > 0) {
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
            
            var available = NSDecimalNumber.zero
            for balance in self.mainTabVC.mBalances {
                if (balance.balance_denom == COSMOS_MAIN_DENOM) {
                    available = available.adding(WUtils.stringToDecimal(balance.balance_amount))
                }
            }
            
            if (available.compare(NSDecimalNumber(string: "1")).rawValue < 0 ) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
            if (WUtils.getAllAtomReward(mainTabVC.mRewardList).compare(NSDecimalNumber.one).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
            stakingVC.mRewardTargetValidators = toClaimValidator
            stakingVC.mType = COSMOS_MSG_TYPE_WITHDRAW_DEL
            stakingVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(stakingVC, animated: true)
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (self.mainTabVC.mIrisRewards != nil && (self.mainTabVC.mIrisRewards?.delegations.count)! > 0) {
                for validator in self.mainTabVC.mAllValidator {
                    for delegation in self.mainTabVC.mIrisRewards!.delegations {
                        if (validator.operator_address == delegation.validator) {
                            toClaimValidator.append(validator)
                        }
                    }
                }
            }
            print("toClaimValidator ", toClaimValidator.count)
            if (toClaimValidator.count <= 0 ) {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            let estimatedGasAmount = (NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_MUX).multiplying(by: NSDecimalNumber.init(value: toClaimValidator.count))).adding(NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_BASE))
            let estimatedFeeAmount = estimatedGasAmount.multiplying(byPowerOf10: 18).multiplying(by: NSDecimalNumber.init(string: GAS_FEE_RATE_IRIS_AVERAGE), withBehavior: WUtils.handler0)
            print("estimatedGasAmount ", estimatedGasAmount)
            print("estimatedFeeAmount ", estimatedFeeAmount)
            
            var balances = BaseData.instance.selectBalanceById(accountId: mainTabVC.mAccount!.account_id)
            if(balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(estimatedFeeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
            if ((mainTabVC.mIrisRewards?.getSimpleIrisReward().compare(estimatedFeeAmount).rawValue)! <= 0) {
                self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                return
            }
            
            if (toClaimValidator.count > 1 ) {
                let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
                stakingVC.mRewardTargetValidators = toClaimValidator
                stakingVC.mType = IRIS_MSG_TYPE_WITHDRAW
                stakingVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(stakingVC, animated: true)
            } else {
                let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
                stakingVC.mRewardTargetValidators = toClaimValidator
                stakingVC.mType = IRIS_MSG_TYPE_WITHDRAW_ALL
                stakingVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(stakingVC, animated: true)
            }
            
            
        }
        
        
        
        
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
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            mainTabVC.mMyValidators.sort{
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
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mainTabVC.mMyValidators.sort{
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
    
    func onShowAddMenomicDialog() {
        let alert = UIAlertController(title: NSLocalizedString("alert_title_no_private_key", comment: ""), message: NSLocalizedString("alert_msg_no_private_key", comment: ""), preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("add_mnemonic", comment: ""), style: .default, handler: { [weak alert] (_) in
            self.onStartImportMnemonic()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}
