//
//  MyAccountViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/10.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MyAccountViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var myAccountTableView: UITableView!
    @IBOutlet weak var myAccountCnt: UILabel!
    
    var refresher: UIRefreshControl!
    var myAccounts: Array<StarNameAccount> = Array<StarNameAccount>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.myAccountTableView.delegate = self
        self.myAccountTableView.dataSource = self
        self.myAccountTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myAccountTableView.register(UINib(nibName: "AccountPromotionCell", bundle: nil), forCellReuseIdentifier: "AccountPromotionCell")
        self.myAccountTableView.register(UINib(nibName: "AccountCell", bundle: nil), forCellReuseIdentifier: "AccountCell")
        self.myAccountTableView.rowHeight = UITableView.automaticDimension
        self.myAccountTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.myAccountTableView.addSubview(refresher)
        
        self.showWaittingAlert()
        self.onFetchMyAccount(self.account!)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (self.myAccounts.count <= 0) {
            return 1
        } else {
            return self.myAccounts.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (self.myAccounts.count <= 0) {
            let cell:AccountPromotionCell? = tableView.dequeueReusableCell(withIdentifier:"AccountPromotionCell") as? AccountPromotionCell
            return cell!
            
        } else {
            let cell:AccountCell? = tableView.dequeueReusableCell(withIdentifier:"AccountCell") as? AccountCell
            let starnameAccount = myAccounts[indexPath.row]
            cell?.starNameLabel.text = starnameAccount.name + "*" + starnameAccount.domain
            cell?.accountConnectedAddressLabel.text = String(starnameAccount.resources.count)
            cell?.accountExpireTime.text = WUtils.longTimetoString(input: starnameAccount.valid_until * 1000)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        if (self.myAccounts.count > 0) {
//            let starnameAccount = myDomains[indexPath.row]
//            print("start detail ", indexPath.row)
//        }
    }
    
    func onFetchFinished() {
        self.myAccountCnt.text = String(myAccounts.count)
        self.myAccountTableView.reloadData()
        self.refresher.endRefreshing()
        self.hideWaittingAlert()
    }

    @IBAction func onClickBuy(_ sender: UIButton) {
        self.onShowToast(NSLocalizedString("error_not_yet", comment: ""))
    }
    
    @objc func onRequestFetch() {
        self.myAccounts.removeAll()
        self.onFetchMyAccount(self.account!)
    }
    
    var mAccountOffset = 1
    func onFetchMyAccount(_ account:Account) {
        var url: String?
        if (chainType == ChainType.IOV_MAIN) {
            url = IOV_CHECK_MY_ACCOUNT;
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_CHECK_MY_ACCOUNT;
        }
        let param = ["owner":account.account_address, "results_per_page": 100, "offset":mAccountOffset] as [String : Any]
        let request = Alamofire.request(url!, method: .post, parameters: param, encoding: JSONEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                let iovStarNameAccount = IovStarNameAccount.init(info)
                for account in iovStarNameAccount.result.accounts {
                    if (!account.name.isEmpty) {
                        self.myAccounts.append(account)
                    }
                }
                if (iovStarNameAccount.result.accounts.count == 100) {
                    self.mAccountOffset = self.mAccountOffset + 1
                    self.onFetchMyAccount(self.account!)
                } else {
                    self.onFetchFinished()
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyAccount ", error) }
            }
            self.onFetchFinished()
        }
    }
}
