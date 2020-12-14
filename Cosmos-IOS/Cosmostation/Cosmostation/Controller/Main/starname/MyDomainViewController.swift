//
//  MyDomainViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/10.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MyDomainViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var myDomainTableView: UITableView!
    @IBOutlet weak var myDomainCnt: UILabel!
    
    var refresher: UIRefreshControl!
    var mFetchCnt = 0
    var myDomains: Array<StarNameDomain> = Array<StarNameDomain>()
    var myDomainResolves: Array<IovStarNameResolve.NameAccount> = Array<IovStarNameResolve.NameAccount>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        
        self.myDomainTableView.delegate = self
        self.myDomainTableView.dataSource = self
        self.myDomainTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myDomainTableView.register(UINib(nibName: "DomainPromotionCell", bundle: nil), forCellReuseIdentifier: "DomainPromotionCell")
        self.myDomainTableView.register(UINib(nibName: "DomainCell", bundle: nil), forCellReuseIdentifier: "DomainCell")
        self.myDomainTableView.rowHeight = UITableView.automaticDimension
        self.myDomainTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.myDomainTableView.addSubview(refresher)
        self.myDomainTableView.isHidden = true
        
        self.showWaittingAlert()
        self.onFetchMyDomain(self.account!)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (self.myDomains.count <= 0) {
            return 1
        } else {
            return self.myDomains.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (self.myDomains.count <= 0) {
            let cell:DomainPromotionCell? = tableView.dequeueReusableCell(withIdentifier:"DomainPromotionCell") as? DomainPromotionCell
            return cell!
            
        } else {
            let cell:DomainCell? = tableView.dequeueReusableCell(withIdentifier:"DomainCell") as? DomainCell
            let starnameAccount = myDomains[indexPath.row]
            cell?.starNameLabel.text = "*" + starnameAccount.name
            cell?.domainTypeLabel.text = starnameAccount.type.uppercased()
            if (starnameAccount.type == "open") {
                cell?.domainTypeLabel.textColor = COLOR_IOV
            } else {
                cell?.domainTypeLabel.textColor = .white
            }
            cell?.domainExpireTime.text = WUtils.longTimetoString(input: starnameAccount.valid_until * 1000)
            let resourceCnt = myDomainResolves.filter({ $0.domain == starnameAccount.name}).first?.resources.count ?? 0
            cell?.domainResourcesLabel.text = String(resourceCnt)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let starnameAccount = myDomains[indexPath.row]
        let domainDetailVC = DomainDetailViewController(nibName: "DomainDetailViewController", bundle: nil)
        domainDetailVC.mMyDomain = starnameAccount.name
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(domainDetailVC, animated: true)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.myDomainCnt.text = String(myDomains.count)
            self.myDomainTableView.reloadData()
            self.refresher.endRefreshing()
            self.hideWaittingAlert()
            self.myDomainTableView.isHidden = false
        }
    }
    
    @IBAction func onClickBuy(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = IOV_MSG_TYPE_REGISTER_DOMAIN
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    @objc func onRequestFetch() {
        if (mFetchCnt > 0) { return }
        self.myDomains.removeAll()
        self.onFetchMyDomain(self.account!)
    }
    
    var domainOffset = 1
    func onFetchMyDomain(_ account:Account) {
        var url: String?
        if (chainType == ChainType.IOV_MAIN) {
            url = IOV_CHECK_MY_DOMAIN;
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_CHECK_MY_DOMAIN;
        }
        let param = ["owner":account.account_address, "results_per_page": 100, "offset":domainOffset] as [String : Any]
        let request = Alamofire.request(url!, method: .post, parameters: param, encoding: JSONEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                let iovStarNameDomain = IovStarNameDomain.init(info)
                self.myDomains.append(contentsOf: iovStarNameDomain.result.Domains)
                
                if (iovStarNameDomain.result.Domains.count == 100) {
                    self.domainOffset = self.domainOffset + 1
                    self.onFetchMyDomain(self.account!)
                } else {
                    self.mFetchCnt = self.mFetchCnt + self.myDomains.count
                    self.myDomainResolves.removeAll()
                    for domain in self.myDomains {
                        self.onFetchResolve(domain.name)
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyDomain ", error) }
                self.onFetchFinished()
            }
        }
    }
    
    func onFetchResolve(_ starname: String) {
        var url: String?
        if (chainType == ChainType.IOV_MAIN) {
            url = IOV_CHECK_WITH_STARNAME;
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_CHECK_WITH_STARNAME;
        }
        let request = Alamofire.request(url!, method: .post, parameters: ["starname" : "*" + starname], encoding: JSONEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any], info["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                let nameResolve = IovStarNameResolve.init(info)
                self.myDomainResolves.append(nameResolve.result.account)
                self.onFetchFinished()
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchResolve ", error) }
                self.onFetchFinished()
            }
        }
    }
}
