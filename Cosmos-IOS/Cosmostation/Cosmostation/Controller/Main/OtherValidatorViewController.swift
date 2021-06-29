//
//  OtherValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 20/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class OtherValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var otherValidatorTableView: UITableView!
    @IBOutlet weak var otherValidatorCnt: UILabel!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.otherValidatorTableView.delegate = self
        self.otherValidatorTableView.dataSource = self
        self.otherValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.otherValidatorTableView.register(UINib(nibName: "OtherValidatorCell", bundle: nil), forCellReuseIdentifier: "OtherValidatorCell")
        self.otherValidatorTableView.rowHeight = UITableView.automaticDimension
        self.otherValidatorTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.otherValidatorTableView.addSubview(refresher)
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
        self.onSorting()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onSorting), name: Notification.Name("onSorting"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onSorting"), object: nil)
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.onSorting()
        self.refresher.endRefreshing()
    }
    
    @objc func onSorting() {
        if (WUtils.isGRPC(chainType!)) {
            self.otherValidatorCnt.text = String(BaseData.instance.mUnbondValidators_gRPC.count)
        } else {
            self.otherValidatorCnt.text = String(BaseData.instance.mOtherValidator.count)
        }
        
        self.sortByPower()
        self.otherValidatorTableView.reloadData()
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (WUtils.isGRPC(chainType!)) {
            return BaseData.instance.mUnbondValidators_gRPC.count
        } else {
            return BaseData.instance.mOtherValidator.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:OtherValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"OtherValidatorCell") as? OtherValidatorCell
        if (WUtils.isGRPC(chainType!)) {
            if (BaseData.instance.mUnbondValidators_gRPC.count > 0) {
                cell?.updateView(BaseData.instance.mUnbondValidators_gRPC[indexPath.row], self.chainType)
            }
            
        } else {
            if (BaseData.instance.mOtherValidator.count > 0) {
                self.onSetValidatorItem(cell!, BaseData.instance.mOtherValidator[indexPath.row])
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
            validatorDetailVC.mValidator_gRPC = BaseData.instance.mUnbondValidators_gRPC[indexPath.row]
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
            
        } else {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
            validatorDetailVC.mValidator = BaseData.instance.mOtherValidator[indexPath.row]
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
        }
    }
    
    func onSetValidatorItem(_ cell: OtherValidatorCell, _ validator: Validator) {
        cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, WUtils.mainDivideDecimal(chainType), 6)
        cell.commissionLabel.attributedText = WUtils.getDpEstAprCommission(cell.commissionLabel.font, NSDecimalNumber.one, chainType!)
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
        
        if (chainType == ChainType.BAND_MAIN) {
            cell.bandOracleOffImg.isHidden = false
        }
        
        //temp hide apr for no mint param chain
        if (chainType == ChainType.SIF_MAIN || chainType == ChainType.OSMOSIS_MAIN || chainType == ChainType.ALTHEA_TEST) {
            cell.commissionLabel.text = "--"
        }
    }
    
    func sortByPower() {
        if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mUnbondValidators_gRPC.sort{
                if ($0.description_p.moniker == "Cosmostation") { return true }
                if ($1.description_p.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double($0.tokens)! > Double($1.tokens)!
            }
        } else {
            BaseData.instance.mOtherValidator.sort{
                if ($0.description.moniker == "Cosmostation") { return true }
                if ($1.description.moniker == "Cosmostation") { return false }
                if ($0.jailed && !$1.jailed) { return false }
                if (!$0.jailed && $1.jailed) { return true }
                return Double($0.tokens)! > Double($1.tokens)!
            }
        }
    }
}
