//
//  OkMyValidatorViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class OkMyValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var okMyValidatorCnt: UILabel!
    @IBOutlet weak var okMyValidatorTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var mMyValidator = Array<Validator>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.mMyValidator = BaseData.instance.mMyValidator
        self.sortOkValidator()
        
        self.okMyValidatorTableView.delegate = self
        self.okMyValidatorTableView.dataSource = self
        self.okMyValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.okMyValidatorTableView.register(UINib(nibName: "OtherValidatorCell", bundle: nil), forCellReuseIdentifier: "OtherValidatorCell")
        self.okMyValidatorTableView.rowHeight = UITableView.automaticDimension
        self.okMyValidatorTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.okMyValidatorCnt.text = String.init(self.mMyValidator.count)

        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.okMyValidatorTableView.addSubview(refresher)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
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
        self.mMyValidator = BaseData.instance.mMyValidator
        self.sortOkValidator()
        self.okMyValidatorTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @IBAction func onClickVote(_ sender: UIButton) {
        print("onClickVote")
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (chainType! == ChainType.OK_TEST) {
            if (WUtils.getTokenAmount(mainTabVC.mBalances, OK_TEST_DENOM).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        if (WUtils.okDepositAmount(BaseData.instance.mOkDeposit).compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_only_deposit_can_vote", comment: ""))
            return
            
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OK_MSG_TYPE_DIRECT_VOTE
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mMyValidator.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:OtherValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"OtherValidatorCell") as? OtherValidatorCell
        let validator = mMyValidator[indexPath.row]
        cell?.monikerLabel.text = validator.description.moniker
        cell?.monikerLabel.adjustsFontSizeToFitWidth = true
        cell?.freeEventImg.isHidden = true
        if (validator.jailed) {
            cell?.revokedImg.isHidden = false
            cell?.validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            cell?.revokedImg.isHidden = true
            cell?.validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        cell?.powerLabel.attributedText =  WUtils.displayAmount2(validator.delegator_shares, cell!.powerLabel.font, 0, 8)
        cell?.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell!.commissionLabel.font)
        cell?.cardView.backgroundColor = TRANS_BG_COLOR_OK
        
        if (validator.description.identity.starts(with: "logo|||")) {
            let url = validator.description.identity.replacingOccurrences(of: "logo|||", with: "")
            Alamofire.request(url, method: .get).responseImage { response  in
                guard let image = response.result.value else {
                    return
                }
                cell?.validatorImg.image = image
            }
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("didSelectRowAt")
    }
    
    func sortOkValidator() {
        self.mMyValidator.sort{
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
            return Double($0.delegator_shares)! > Double($1.delegator_shares)!
        }
    }

}
