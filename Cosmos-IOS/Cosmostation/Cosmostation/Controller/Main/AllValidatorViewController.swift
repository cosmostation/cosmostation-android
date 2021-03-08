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
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            self.onSorting()
            self.refresher.endRefreshing()
        } else {
            self.mInflation = BaseData.instance.mInflation
            self.mProvision = BaseData.instance.mProvision
            self.mStakingPool = BaseData.instance.mStakingPool
            self.mIrisStakePool = BaseData.instance.mIrisStakePool
            self.mBandOracleStatus = BaseData.instance.mBandOracleStatus
            self.onSorting()
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        print("onPriceFetchDone")
    }
    
    @objc func onSorting() {
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            self.allValidatorCnt.text = String(BaseData.instance.mBondedValidators_V1.count)
        } else {
            self.allValidatorCnt.text = String(self.mainTabVC.mTopValidators.count)
        }
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
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            return BaseData.instance.mBondedValidators_V1.count
        } else {
            return self.mainTabVC.mTopValidators.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:AllValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"AllValidatorCell") as? AllValidatorCell
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            if (BaseData.instance.mBondedValidators_V1.count > 0) {
                cell?.updateView(BaseData.instance.mBondedValidators_V1[indexPath.row], self.chainType)
            }
            
        } else {
            guard self.mainTabVC.mTopValidators.count > 0 else { return cell! }
            if let validator = self.mainTabVC.mTopValidators[indexPath.row] as? Validator {
                self.onSetValidatorItem(cell!, validator, indexPath)
            }
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
            validatorDetailVC.mValidator_V1 = BaseData.instance.mBondedValidators_V1[indexPath.row]
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            
        } else {
            if let validator = self.mainTabVC.mTopValidators[indexPath.row] as? Validator {
                let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
                validatorDetailVC.mValidator = validator
                validatorDetailVC.mIsTop100 = mainTabVC.mTopValidators.contains(where: {$0.operator_address == validator.operator_address})
                validatorDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            }
        }
    }
    
    func onSetValidatorItem(_ cell: AllValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
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
            if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_KAVA
            } else if (chainType == ChainType.BAND_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_BAND
            } else if (chainType == ChainType.SECRET_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_SECRET
            } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_IOV
            } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_CERTIK
            }
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
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            BaseData.instance.mBondedValidators_V1.sort{
                if ($0.description?.moniker == "Cosmostation") { return true }
                if ($1.description?.moniker == "Cosmostation") { return false }
                if ($0.jailed! && !$1.jailed!) { return false }
                if (!$0.jailed! && $1.jailed!) { return true }
                return $0.description!.moniker! < $1.description!.moniker!
            }
        } else {
            mainTabVC.mTopValidators.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return $0.description.moniker < $1.description.moniker
            }
        }
    }
    
    func sortByPower() {
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            BaseData.instance.mBondedValidators_V1.sort{
                if ($0.description?.moniker == "Cosmostation") { return true }
                if ($1.description?.moniker == "Cosmostation") { return false }
                if ($0.jailed! && !$1.jailed!) { return false }
                if (!$0.jailed! && $1.jailed!) { return true }
                return Double($0.tokens!)! > Double($1.tokens!)!
            }
        } else {
            mainTabVC.mTopValidators.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double($0.tokens)! > Double($1.tokens)!
            }
        }
    }
    
    func sortByCommission() {
        if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.IRIS_MAIN || self.chainType == ChainType.AKASH_MAIN ||
                self.chainType == ChainType.COSMOS_TEST || self.chainType == ChainType.IRIS_TEST) {
            BaseData.instance.mBondedValidators_V1.sort{
                if ($0.description?.moniker == "Cosmostation") { return true }
                if ($1.description?.moniker == "Cosmostation") { return false }
                if ($0.jailed! && !$1.jailed!) { return false }
                if (!$0.jailed! && $1.jailed!) { return true }
                return Double(truncating: $0.getCommission()) < Double(truncating: $1.getCommission())
            }
        } else {
            mainTabVC.mTopValidators.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                
                if (chainType == ChainType.IRIS_MAIN) { return Double($0.commission.rate)! < Double($1.commission.rate)! }
                else { return Double(truncating: $0.getCommission()) < Double(truncating: $1.getCommission()) }
            }
        }
    }

}
