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
        self.chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.onSorting()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceFetchDone(_:)), name: Notification.Name("onPriceFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onSorting), name: Notification.Name("onSorting"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onPriceFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onSorting"), object: nil)
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.onSorting()
        self.refresher.endRefreshing()
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        print("onPriceFetchDone")
    }
    
    @objc func onSorting() {
        self.otherValidatorCnt.text = String(self.mainTabVC.mOtherValidators.count)
        self.sortByPower()
        self.otherValidatorTableView.reloadData()
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mainTabVC.mOtherValidators.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:OtherValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"OtherValidatorCell") as? OtherValidatorCell
        guard self.mainTabVC.mTopValidators.count > 0 else {
            return cell!
        }
        if let validator = self.mainTabVC.mOtherValidators[indexPath.row] as? Validator {
            self.onSetValidatorItem(cell!, validator, indexPath)
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let validator = self.mainTabVC.mOtherValidators[indexPath.row] as? Validator {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaildatorDetailViewController") as! VaildatorDetailViewController
            validatorDetailVC.mValidator = validator
            validatorDetailVC.mIsTop100 = mainTabVC.mTopValidators.contains(where: {$0.operator_address == validator.operator_address})
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
        }
    }
    
    
    func onSetValidatorItem(_ cell: OtherValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        if (chainType == ChainType.COSMOS_MAIN) {
            cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, 6, 6)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell.commissionLabel.font)
            let url = COSMOS_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, 0, 6)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.rate, font: cell.commissionLabel.font)
            let url = IRIS_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, 6, 6)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell.commissionLabel.font)
            let url = KAVA_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, 6, 6)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell.commissionLabel.font)
            let url = BAND_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, 6, 6)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell.commissionLabel.font)
            let url = IOV_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            cell.powerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell.powerLabel.font!, 6, 6)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell.commissionLabel.font)
            let url = CERTIK_VAL_URL + validator.operator_address + ".png"
            cell.validatorImg.af_setImage(withURL: URL(string: url)!)
            
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
            } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_IOV
            } else if (chainType == ChainType.CERTIK_TEST) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_CERTIK
            }
        } else {
            cell.cardView.backgroundColor = COLOR_BG_GRAY
        }
    }
    
    func sortByPower() {
        mainTabVC.mOtherValidators.sort{
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
    }
}
