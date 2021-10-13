//
//  MyDomainViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/10.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import GRPC
import NIO
import SwiftProtobuf

class MyDomainViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var myDomainTableView: UITableView!
    @IBOutlet weak var myDomainCnt: UILabel!
    
    var refresher: UIRefreshControl!
    var mFetchCnt = 0
    
    var myDomains_gRPC: Array<Starnamed_X_Starname_V1beta1_Domain> = Array<Starnamed_X_Starname_V1beta1_Domain>()
    var myDomainResolves_gRPC: Array<Starnamed_X_Starname_V1beta1_Account> = Array<Starnamed_X_Starname_V1beta1_Account>()
    
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
        self.onFetchgRPCMyDomain(self.account!)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (self.myDomains_gRPC.count <= 0) {
            return 1
        } else {
            return self.myDomains_gRPC.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (self.myDomains_gRPC.count <= 0) {
            let cell:DomainPromotionCell? = tableView.dequeueReusableCell(withIdentifier:"DomainPromotionCell") as? DomainPromotionCell
            return cell!
            
        } else {
            let cell:DomainCell? = tableView.dequeueReusableCell(withIdentifier:"DomainCell") as? DomainCell
            let starnameAccount = myDomains_gRPC[indexPath.row]
            cell?.starNameLabel.text = "*" + starnameAccount.name
            cell?.domainTypeLabel.text = starnameAccount.type.uppercased()
            if (starnameAccount.type == "open") {
                cell?.domainTypeLabel.textColor = COLOR_IOV
            } else {
                cell?.domainTypeLabel.textColor = .white
            }
            cell?.domainExpireTime.text = WUtils.longTimetoString(starnameAccount.validUntil * 1000)
            let resourceCnt = myDomainResolves_gRPC.filter({ $0.domain == starnameAccount.name}).first?.resources.count ?? 0
            cell?.domainResourcesLabel.text = String(resourceCnt)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let starnameAccount = myDomains_gRPC[indexPath.row]
        let domainDetailVC = DomainDetailViewController(nibName: "DomainDetailViewController", bundle: nil)
        domainDetailVC.mMyDomain = starnameAccount.name
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(domainDetailVC, animated: true)
    }
    
    func onFetchFinished() {
//        print("onFetchFinished ", self.mFetchCnt)
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.myDomainCnt.text = String(myDomains_gRPC.count)
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
        self.myDomains_gRPC.removeAll()
        self.myDomainResolves_gRPC.removeAll()
        self.mFetchCnt = 1
        self.onFetchgRPCMyDomain(self.account!)
    }
    
    func onFetchgRPCMyDomain(_ account: Account) {
//        print("onFetchgRPCMyDomain ", account.account_address)
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(self.chainType!, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let page = Cosmos_Base_Query_V1beta1_PageRequest.with { $0.limit = 500 }
                let req = Starnamed_X_Starname_V1beta1_QueryOwnerDomainsRequest.with { $0.owner = account.account_address; $0.pagination = page }
                if let response = try? Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).ownerDomains(req, callOptions:BaseNetWork.getCallOptions()).response.wait() {
//                    print("onFetchgRPCMyDomain myDomains_gRPC.count ", self.myDomains_gRPC.count)
                    response.domains.forEach { domain in
                        self.myDomains_gRPC.append(domain)
                    }
                }
                try channel.close().wait()
                
            } catch {
                print("onFetchgRPCMyDomain failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.mFetchCnt = self.mFetchCnt + self.myDomains_gRPC.count
                self.myDomains_gRPC.forEach { domain in
                    self.onFetchgRPCResolve(domain.name)
                }
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCResolve(_ domain: String) {
//        print("onFetchgRPCResolve ", domain)
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(self.chainType!, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let req = Starnamed_X_Starname_V1beta1_QueryStarnameRequest.with { $0.starname = "*" + domain }
                if let response = try? Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).starname(req, callOptions:BaseNetWork.getCallOptions()).response.wait() {
                    self.myDomainResolves_gRPC.append(response.account)
                }
                try channel.close().wait()
                
            } catch {
                print("onFetchgRPCResolve failed: \(error)")
            }
            DispatchQueue.main.async(execute: { self.onFetchFinished() });
        }
        
    }
 
}
