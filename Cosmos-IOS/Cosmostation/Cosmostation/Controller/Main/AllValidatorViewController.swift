//
//  AllValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class AllValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var allValidatorTableView: UITableView!
    @IBOutlet weak var allValidatorCnt: UILabel!
    @IBOutlet weak var btnSort: UIView!
    @IBOutlet weak var sortType: UILabel!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?
    var mIrisStakePool: NSDictionary?
    var mBandOracleStatus: BandOracleStatus?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mInflation = BaseData.instance.mInflation
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        self.mIrisStakePool = BaseData.instance.mIrisStakePool
        self.mBandOracleStatus = BaseData.instance.mBandOracleStatus
        
        self.allValidatorTableView.delegate = self
        self.allValidatorTableView.dataSource = self
        self.allValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.allValidatorTableView.register(UINib(nibName: "AllValidatorCell", bundle: nil), forCellReuseIdentifier: "AllValidatorCell")
        self.allValidatorTableView.rowHeight = UITableView.automaticDimension
        self.allValidatorTableView.estimatedRowHeight = UITableView.automaticDimension
    
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.allValidatorTableView.addSubview(refresher)
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onStartSort))
        self.btnSort.addGestureRecognizer(tap)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
        self.chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.onSorting()
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
        if (self.chainType != ChainType.COSMOS_TEST) {
            self.mInflation = BaseData.instance.mInflation
            self.mProvision = BaseData.instance.mProvision
            self.mStakingPool = BaseData.instance.mStakingPool
            self.mIrisStakePool = BaseData.instance.mIrisStakePool
            self.mBandOracleStatus = BaseData.instance.mBandOracleStatus
            self.onSorting()
            self.refresher.endRefreshing()
            
        } else {
            self.onSorting()
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        print("onPriceFetchDone")
    }
    
    @objc func onSorting() {
        self.allValidatorCnt.text = String(self.mainTabVC.mTopValidators.count)
        if (BaseData.instance.getAllValidatorSort() == 0) {
            self.sortType.text = NSLocalizedString("sort_by_power", comment: "")
            sortByPower()
        } else if (BaseData.instance.getAllValidatorSort() == 1) {
            self.sortType.text = NSLocalizedString("sort_by_name", comment: "")
            sortByName()
        } else {
            self.sortType.text = NSLocalizedString("sort_by_yield", comment: "")
            sortByCommission()
        }
        self.allValidatorTableView.reloadData()
    }
    
    @objc func onRequestFetch() {
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (self.chainType != ChainType.COSMOS_TEST) {
            return self.mainTabVC.mTopValidators.count
        } else {
            return BaseData.instance.mBondedValidators_V1.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:AllValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"AllValidatorCell") as? AllValidatorCell
        if (self.chainType != ChainType.COSMOS_TEST) {
            guard self.mainTabVC.mTopValidators.count > 0 else { return cell! }
            if let validator = self.mainTabVC.mTopValidators[indexPath.row] as? Validator {
                self.onSetValidatorItem(cell!, validator, indexPath)
            }
            
        } else {
            self.onSetValidatorItemV1(cell!, BaseData.instance.mBondedValidators_V1[indexPath.row], indexPath)
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (self.chainType != ChainType.COSMOS_TEST) {
            if let validator = self.mainTabVC.mTopValidators[indexPath.row] as? Validator {
                let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
                validatorDetailVC.mValidator = validator
                validatorDetailVC.mIsTop100 = mainTabVC.mTopValidators.contains(where: {$0.operator_address == validator.operator_address})
                validatorDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            }
            
        } else {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
            validatorDetailVC.mValidator_V1 = BaseData.instance.mBondedValidators_V1[indexPath.row]
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
        }
    }
    
    
    
    func onSetValidatorItem(_ cell: AllValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        if (chainType == ChainType.COSMOS_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: COSMOS_VAL_URL + validator.operator_address + ".png")!)

        } else if (chainType == ChainType.IRIS_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(NSDecimalNumber.init(string: validator.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell.powerLabel.font, 6, chainType!)
            if (self.mIrisStakePool != nil) {
                let provisions = NSDecimalNumber.init(string: self.mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                let bonded_tokens = NSDecimalNumber.init(string: self.mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                cell.commissionLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: validator.commission.rate), font: cell.commissionLabel.font)
            } else {
                cell.commissionLabel.text = "-"
            }
            cell.validatorImg.af_setImage(withURL: URL(string: IRIS_VAL_URL + validator.operator_address + ".png")!)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: KAVA_VAL_URL + validator.operator_address + ".png")!)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: BAND_VAL_URL + validator.operator_address + ".png")!)
            if let oracle = mBandOracleStatus?.isEnable(validator.operator_address) {
                if (!oracle) {
                    cell.bandOracleOffImg.isHidden = false
                    cell.commissionLabel.textColor = UIColor.init(hexString: "f31963")
                }
            }
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: SECRET_VAL_URL + validator.operator_address + ".png")!)
            
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: IOV_VAL_URL + validator.operator_address + ".png")!)
            
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: CERTIK_VAL_URL + validator.operator_address + ".png")!)
            
        } else if (chainType == ChainType.AKASH_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, chainType!)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: AKASH_VAL_URL + validator.operator_address + ".png")!)
            
        }
        
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

        if mainTabVC.mMyValidators.first(where: {$0.operator_address == validator.operator_address}) != nil {
            if (chainType == ChainType.COSMOS_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            } else if (chainType == ChainType.IRIS_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_IRIS
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_KAVA
            } else if (chainType == ChainType.BAND_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_BAND
            } else if (chainType == ChainType.SECRET_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_SECRET
            } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_IOV
            } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_CERTIK
            } else if (chainType == ChainType.AKASH_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_AKASH
            }
        } else {
            cell.cardView.backgroundColor = COLOR_BG_GRAY
        }
    }
    
    func onSetValidatorItemV1(_ cell: AllValidatorCell, _ validator: Validator_V1, _ indexPath: IndexPath) {
        if (chainType == ChainType.COSMOS_TEST) {
            cell.powerLabel.attributedText =  WUtils.displayAmount2(validator.tokens, cell.powerLabel.font, 6, 6)
            cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
            cell.validatorImg.af_setImage(withURL: URL(string: COSMOS_VAL_URL + validator.operator_address! + ".png")!)
        }
        cell.monikerLabel.text = validator.description?.moniker
        cell.monikerLabel.adjustsFontSizeToFitWidth = true
        cell.freeEventImg.isHidden = true
        if (validator.jailed == true) {
            cell.revokedImg.isHidden = false
            cell.validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            cell.revokedImg.isHidden = true
            cell.validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        if BaseData.instance.mMyValidators_V1.first(where: {$0.operator_address == validator.operator_address}) != nil {
            cell.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
        } else {
            cell.cardView.backgroundColor = COLOR_BG_GRAY
        }
    }
    
    
    @objc func onStartSort() {
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: UIAlertAction.Style.cancel, handler: nil))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_name", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            BaseData.instance.setAllValidatorSort(1)
            self.onSorting()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_power", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            BaseData.instance.setAllValidatorSort(0)
            self.onSorting()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_yield", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            BaseData.instance.setAllValidatorSort(2)
            self.onSorting()
        }))
        self.present(alert, animated: true, completion: nil)
    }
    
    func sortByName() {
        if (chainType != ChainType.COSMOS_TEST) {
            mainTabVC.mTopValidators.sort{
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
            
        } else {
            BaseData.instance.mBondedValidators_V1.sort{
                if ($0.description?.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description?.moniker == "Cosmostation"){
                    return false
                }
                if ($0.jailed! && !$1.jailed!) {
                    return false
                }
                if (!$0.jailed! && $1.jailed!) {
                    return true
                }
                return $0.description!.moniker! < $1.description!.moniker!
            }
            
        }
    }
    
    func sortByPower() {
        if (chainType != ChainType.COSMOS_TEST) {
            mainTabVC.mTopValidators.sort{
                if ($0.description.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description.moniker == "Cosmostation") {
                    return false
                }
                if ($0.jailed && !$1.jailed) {
                    return false
                }
                if (!$0.jailed && $1.jailed) {
                    return true
                }
                return Double($0.tokens)! > Double($1.tokens)!
            }
            
        } else {
            BaseData.instance.mBondedValidators_V1.sort{
                if ($0.description?.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description?.moniker == "Cosmostation") {
                    return false
                }
                if ($0.jailed! && !$1.jailed!) {
                    return false
                }
                if (!$0.jailed! && $1.jailed!) {
                    return true
                }
                return Double($0.tokens!)! > Double($1.tokens!)!
            }
            
        }
    }
    
    func sortByCommission() {
        if (chainType != ChainType.COSMOS_TEST) {
            mainTabVC.mTopValidators.sort{
                if ($0.description.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description.moniker == "Cosmostation") {
                    return false
                }
                if ($0.jailed && !$1.jailed) {
                    return false
                }
                if (!$0.jailed && $1.jailed) {
                    return true
                }
                if (chainType == ChainType.IRIS_MAIN) {
                    return Double($0.commission.rate)! < Double($1.commission.rate)!
                } else {
                    return Double(truncating: $0.getCommission()) < Double(truncating: $1.getCommission())
                }
            }
            
        } else {
            BaseData.instance.mBondedValidators_V1.sort{
                if ($0.description?.moniker == "Cosmostation") {
                    return true
                }
                if ($1.description?.moniker == "Cosmostation") {
                    return false
                }
                if ($0.jailed! && !$1.jailed!) {
                    return false
                }
                if (!$0.jailed! && $1.jailed!) {
                    return true
                }
                return Double(truncating: $0.getCommission()) < Double(truncating: $1.getCommission())
            }
            
        }
    }

}
