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
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
                
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
        self.onSorting()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        if (WUtils.isGRPC(chainType!)) {
            self.onSorting()
            self.refresher.endRefreshing()
        } else {
            self.onSorting()
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onSorting() {
        if (WUtils.isGRPC(chainType!)) {
            self.allValidatorCnt.text = String(BaseData.instance.mBondedValidators_gRPC.count)
        } else {
            self.allValidatorCnt.text = String(BaseData.instance.mTopValidator.count)
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
        if (WUtils.isGRPC(chainType!)) {
            return BaseData.instance.mBondedValidators_gRPC.count
        } else {
            return BaseData.instance.mTopValidator.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:AllValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"AllValidatorCell") as? AllValidatorCell
        if (WUtils.isGRPC(chainType!)) {
            if (BaseData.instance.mBondedValidators_gRPC.count > 0) {
                cell?.updateView(BaseData.instance.mBondedValidators_gRPC[indexPath.row], self.chainType)
            }
            
        } else {
            if (BaseData.instance.mTopValidator.count > 0) {
                self.onSetValidatorItem(cell!, BaseData.instance.mTopValidator[indexPath.row])
            }
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (WUtils.isGRPC(chainType!)) {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
            validatorDetailVC.mValidator_gRPC = BaseData.instance.mBondedValidators_gRPC[indexPath.row]
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            
        } else {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
            validatorDetailVC.mValidator = BaseData.instance.mTopValidator[indexPath.row]
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
        }
    }
    
    func onSetValidatorItem(_ cell: AllValidatorCell, _ validator: Validator) {
        cell.powerLabel.attributedText =  WUtils.displayAmount2(validator.tokens, cell.powerLabel.font, WUtils.mainDivideDecimal(chainType), 6)
        cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, validator.getCommission(), chainType!)
        cell.validatorImg.af_setImage(withURL: URL(string: WUtils.getMonikerImgUrl(chainType!, validator.operator_address))!)
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
        
        if BaseData.instance.mMyValidator.first(where: {$0.operator_address == validator.operator_address}) != nil {
            cell.cardView.backgroundColor = WUtils.getChainBg(chainType)
        } else {
            cell.cardView.backgroundColor = COLOR_BG_GRAY
        }
        
        //temp hide apr for no mint param chain
        if (chainType == ChainType.SIF_MAIN || chainType == ChainType.ALTHEA_TEST) {
            cell.commissionLabel.text = "--"
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
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mBondedValidators_gRPC.sort{
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return $0.description_p.moniker < $1.description_p.moniker
            }
        } else {
            BaseData.instance.mTopValidator.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return $0.description.moniker < $1.description.moniker
            }
        }
    }
    
    func sortByPower() {
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mBondedValidators_gRPC.sort{
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double($0.tokens)! > Double($1.tokens)!
            }
        } else {
            BaseData.instance.mTopValidator.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double($0.tokens)! > Double($1.tokens)!
            }
        }
    }
    
    func sortByCommission() {
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mBondedValidators_gRPC.sort{
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double($0.commission.commissionRates.rate)! < Double($1.commission.commissionRates.rate)!
            }
        } else {
            BaseData.instance.mTopValidator.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double(truncating: $0.getCommission()) < Double(truncating: $1.getCommission())
            }
        }
    }

}
