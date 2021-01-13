//
//  OkTopValidatorViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class OkTopValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var okTopValidatorCnt: UILabel!
    @IBOutlet weak var okTopValidatorTableView: UITableView!

    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var mTopValidator = Array<Validator>()
    var mMyValidator = Array<Validator>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.mTopValidator = BaseData.instance.mTopValidator
        self.mMyValidator = BaseData.instance.mMyValidator
        self.sortOkValidator()
        
        self.okTopValidatorTableView.delegate = self
        self.okTopValidatorTableView.dataSource = self
        self.okTopValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.okTopValidatorTableView.register(UINib(nibName: "OtherValidatorCell", bundle: nil), forCellReuseIdentifier: "OtherValidatorCell")
        self.okTopValidatorTableView.rowHeight = UITableView.automaticDimension
        self.okTopValidatorTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.okTopValidatorCnt.text = String.init(self.mTopValidator.count)

        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.okTopValidatorTableView.addSubview(refresher)
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
        self.mTopValidator = BaseData.instance.mTopValidator
        self.mMyValidator = BaseData.instance.mMyValidator
        self.sortOkValidator()
        self.okTopValidatorTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mTopValidator.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:OtherValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"OtherValidatorCell") as? OtherValidatorCell
        let validator = mTopValidator[indexPath.row]
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
        cell?.powerLabel.attributedText =  WUtils.displayAmount2(validator.delegator_shares, cell!.powerLabel.font, 0, 0)
        cell?.commissionLabel.attributedText = WUtils.displayCommission("0", font: cell!.commissionLabel.font)
        if (self.mMyValidator.contains(where: {$0.operator_address == validator.operator_address})) {
            cell?.cardView.backgroundColor = TRANS_BG_COLOR_OK
        } else {
            cell?.cardView.backgroundColor = COLOR_BG_GRAY
        }
        
        if (validator.description.identity.starts(with: "logo|||")) {
            let url = validator.description.identity.replacingOccurrences(of: "logo|||", with: "").trimmingCharacters(in: .whitespaces)
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("didSelectRowAt")
    }
    
    func sortOkValidator() {
        self.mTopValidator.sort{
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
