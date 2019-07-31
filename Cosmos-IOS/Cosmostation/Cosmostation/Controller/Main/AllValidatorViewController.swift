//
//  AllValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class AllValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var allValidatorTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var userChain: ChainType?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.allValidatorTableView.delegate = self
        self.allValidatorTableView.dataSource = self
        self.allValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.allValidatorTableView.register(UINib(nibName: "AllValidatorCell", bundle: nil), forCellReuseIdentifier: "AllValidatorCell")
    
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.allValidatorTableView.addSubview(refresher)
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
        if (BaseData.instance.getAllValidatorSort() == 0) {
            sortByPower()
        } else if (BaseData.instance.getAllValidatorSort() == 1) {
            sortByName()
        } else {
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
        return self.mainTabVC.mTopValidators.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:AllValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"AllValidatorCell") as? AllValidatorCell
        guard self.mainTabVC.mTopValidators.count > 0 else {
            return cell!
        }
        if let validator = self.mainTabVC.mTopValidators[indexPath.row] as? Validator {
            self.onSetValidatorItem(cell!, validator, indexPath)
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let validator = self.mainTabVC.mTopValidators[indexPath.row] as? Validator {
            let validatorDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VaidatorDetailViewController") as! VaidatorDetailViewController
            validatorDetailVC.mValidator = validator
            validatorDetailVC.mInflation = mainTabVC.mInflation
            validatorDetailVC.mProvision = mainTabVC.mProvision
            validatorDetailVC.mStakingPool = mainTabVC.mStakingPool
            validatorDetailVC.mIsTop100 = mainTabVC.mTopValidators.contains(where: {$0.operator_address == validator.operator_address})
            validatorDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(validatorDetailVC, animated: true)
        }
    }
    
    
    
    func onSetValidatorItem(_ cell: AllValidatorCell, _ validator: Validator, _ indexPath: IndexPath) {
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(validator.tokens, cell.powerLabel.font, 6, userChain!)
            if (mainTabVC!.mStakingPool != nil && mainTabVC!.mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mainTabVC.mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mainTabVC.mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell.commissionLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: validator.commission.rate), font: cell.commissionLabel.font)
            } else {
                cell.commissionLabel.text = "-"
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            cell.powerLabel.attributedText =  WUtils.displayAmount(NSDecimalNumber.init(string: validator.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell.powerLabel.font, 6, userChain!)
            if (mainTabVC!.mIrisStakePool != nil) {
                let provisions = NSDecimalNumber.init(string: mainTabVC.mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                let bonded_tokens = NSDecimalNumber.init(string: mainTabVC.mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                cell.commissionLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: validator.commission.rate), font: cell.commissionLabel.font)
            } else {
                cell.commissionLabel.text = "-"
            }
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
//                    print("res : ", res)
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
    
    
    func sortByName() {
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
    }
    
    func sortByPower() {
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
    }
    
    func sortByCommission() {
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
            return Double($0.commission.rate)! < Double($1.commission.rate)!
        }
    }

}
