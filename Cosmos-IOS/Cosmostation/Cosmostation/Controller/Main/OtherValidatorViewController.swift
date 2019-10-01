//
//  OtherValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 20/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class OtherValidatorViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var otherValidatorTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var userChain: ChainType?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.otherValidatorTableView.delegate = self
        self.otherValidatorTableView.dataSource = self
        self.otherValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.otherValidatorTableView.register(UINib(nibName: "OtherValidatorCell", bundle: nil), forCellReuseIdentifier: "OtherValidatorCell")
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.otherValidatorTableView.addSubview(refresher)
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
        self.userChain = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
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
        self.sortByPower()
        self.otherValidatorTableView.reloadData()
    }
    
    
    @objc func onRequestFetch() {
        if(!mainTabVC.onFetchAccountData()) {
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
        return 80;
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let validator = self.mainTabVC.mOtherValidators[indexPath.row] as? Validator {
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
    
    
    func onSetValidatorItem(_ cell: OtherValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmout(validator.tokens, cell.powerLabel.font, 6)
            //TODO rollback cosmos-hub2
//            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.commission_rates.rate, font: cell.commissionLabel.font)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.rate, font: cell.commissionLabel.font)
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(NSDecimalNumber.init(string: validator.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell.powerLabel.font, 6, userChain!)
            cell.commissionLabel.attributedText = WUtils.displayCommission(validator.commission.rate, font: cell.commissionLabel.font)
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
            if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_COSMOS
            } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                cell.cardView.backgroundColor = TRANS_BG_COLOR_IRIS
            }
        } else {
            cell.cardView.backgroundColor = COLOR_BG_GRAY
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
