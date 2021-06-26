//
//  DomainDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class DomainDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var myDomainLabel: UILabel!
    @IBOutlet weak var myDomainType: UILabel!
    @IBOutlet weak var myDomainAddressCntLael: UILabel!
    @IBOutlet weak var myDomainExpireTimeLabel: UILabel!
    @IBOutlet weak var myDomainResourceTableView: UITableView!
    @IBOutlet weak var myDomainEmptyView: UIView!
    
    var mMyDomain: String?
    
//    var mMyDomainInfo: IovStarNameDomainInfo?
//    var mMyDomainResolve: IovStarNameResolve?
    
    var mMyDomainInfo_gRPC: Starnamed_X_Starname_V1beta1_Domain?
    var mMyDomainResolve_gRPC: Starnamed_X_Starname_V1beta1_QueryStarnameResponse?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        
        self.myDomainResourceTableView.delegate = self
        self.myDomainResourceTableView.dataSource = self
        self.myDomainResourceTableView.register(UINib(nibName: "ResourceCell", bundle: nil), forCellReuseIdentifier: "ResourceCell")
        self.myDomainResourceTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myDomainResourceTableView.rowHeight = UITableView.automaticDimension
        self.myDomainResourceTableView.estimatedRowHeight = UITableView.automaticDimension
        self.myDomainEmptyView.isHidden = true
        
        myDomainLabel.text = "*" + mMyDomain!
        self.onFetchData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_starname_domain_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_starname_domain_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mMyDomainResolve_gRPC?.account.resources.count ?? 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ResourceCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceCell") as? ResourceCell
        let resource = mMyDomainResolve_gRPC?.account.resources[indexPath.row]
        cell?.chainImg.image = WUtils.getStarNameChainImg2(resource)
        cell?.chainName.text = WUtils.getStarNameChainName2(resource)
        cell?.chainAddress.text = resource?.resource
        return cell!
    }

    @IBAction func onClickDelete(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (mMyDomainInfo_gRPC?.type == "open") {
            self.onShowToast(NSLocalizedString("error_cannot_delete_open_domain", comment: ""))
            return
        }
        
        let userAvailable = BaseData.instance.getAvailableAmount_gRPC(IOV_MAIN_DENOM)
        let txFee = WUtils.getEstimateGasFeeAmount(chainType!, IOV_MSG_TYPE_DELETE_DOMAIN, 0)
        if (userAvailable.compare(txFee).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = IOV_MSG_TYPE_DELETE_DOMAIN
        txVC.mStarnameDomain = mMyDomain
        txVC.mStarnameTime = mMyDomainInfo_gRPC?.validUntil
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickRenew(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let userAvailable = BaseData.instance.getAvailableAmount_gRPC(IOV_MAIN_DENOM)
        let txFee = WUtils.getEstimateGasFeeAmount(chainType!, IOV_MSG_TYPE_RENEW_DOMAIN, 0)
        let starnameFee = WUtils.getStarNameRenewDomainFee(mMyDomain!, mMyDomainInfo_gRPC!.type)
        print("type ", mMyDomainInfo_gRPC!.type)
        print("userAvailable ", userAvailable)
        print("txFee ", txFee)
        print("starnameFee ", starnameFee)
        if (userAvailable.compare(txFee.adding(starnameFee)).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = IOV_MSG_TYPE_RENEW_DOMAIN
        txVC.mStarnameDomain = mMyDomain
        txVC.mStarnameTime = mMyDomainInfo_gRPC?.validUntil
        txVC.mStarnameDomainType = mMyDomainInfo_gRPC?.type
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
//        let needFee = BaseData.instance.mStarNameFee!.getDomainRenewFee(mMyDomainInfo!.result.domain!.type).adding(NSDecimalNumber.init(string: "300000"))
//        if (chainType == ChainType.IOV_MAIN) {
//            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(needFee).rawValue < 0) {
//                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
//                return
//            }
//        } else if (chainType == ChainType.IOV_TEST) {
//            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(needFee).rawValue < 0) {
//                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
//                return
//            }
//        } else {
//            self.onShowToast(NSLocalizedString("error_disable", comment: ""))
//            return
//        }
//
//        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
//        txVC.mType = IOV_MSG_TYPE_RENEW_DOMAIN
//        txVC.mStarnameDomain = mMyDomain
//        txVC.mStarnameTime = mMyDomainInfo!.result.domain!.valid_until
//        txVC.mStarnameDomainType = mMyDomainInfo?.result.domain?.type
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickReplace(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
//        let needFee = BaseData.instance.mStarNameFee!.getReplaceFee().adding(NSDecimalNumber.init(string: "300000"))
//        if (chainType == ChainType.IOV_MAIN) {
//            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(needFee).rawValue < 0) {
//                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
//                return
//            }
//        } else if (chainType == ChainType.IOV_TEST) {
//            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(needFee).rawValue < 0) {
//                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
//                return
//            }
//        } else {
//            self.onShowToast(NSLocalizedString("error_disable", comment: ""))
//            return
//        }
//
//        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
//        txVC.mType = IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE
//        txVC.mStarnameDomain = mMyDomain
//        txVC.mStarnameTime = mMyDomainInfo!.result.domain!.valid_until
//        txVC.mStarnameDomainType = mMyDomainInfo?.result.domain?.type
//        txVC.mStarnameResources = mMyDomainResolve?.result.account.resources ?? Array<StarNameResource>()
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickProfile(_ sender: UIButton) {
        guard let url = URL(string: "https://starname.me/" + "*" + mMyDomain!) else { return }
        self.onShowSafariWeb(url)
    }
    
    var mFetchCnt = 0
    @objc func onFetchData() {
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 2
        self.onFetchgRPCDomainInfo(mMyDomain!)
        self.onFetchgRPCResolve(mMyDomain!)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if (mMyDomainResolve_gRPC?.hasAccount ?? false &&  mMyDomainResolve_gRPC?.account.resources.count ?? 0 > 0) {
                self.myDomainResourceTableView.reloadData()
                self.myDomainEmptyView.isHidden = true
                self.myDomainAddressCntLael.text = String(mMyDomainResolve_gRPC!.account.resources.count)
            } else {
                self.myDomainResourceTableView.isHidden = true
                self.myDomainEmptyView.isHidden = false
                self.myDomainAddressCntLael.text = "0"
            }
            myDomainExpireTimeLabel.text = WUtils.longTimetoString(input: mMyDomainInfo_gRPC!.validUntil * 1000)
            myDomainType.text = mMyDomainInfo_gRPC?.type.uppercased()
            if (mMyDomainInfo_gRPC?.type == "open") {
                myDomainType.textColor = COLOR_IOV
            } else {
                myDomainType.textColor = .white
            }
        }
    }
    
    func onFetchgRPCDomainInfo(_ domain: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Starnamed_X_Starname_V1beta1_QueryDomainRequest.with {
                    $0.name = domain
                }
                let response = try Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).domain(req, callOptions:BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchDomainInfo_gRPC ", domain, " ", response)
                self.mMyDomainInfo_gRPC = response.domain
                
            } catch {
                print("onFetchDomainInfo_gRPC failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCResolve(_ starname: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Starnamed_X_Starname_V1beta1_QueryStarnameRequest.with {
                    $0.starname = "*" + starname
                }
                let response = try Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).starname(req, callOptions:BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCResolve ", starname, " ", response)
                self.mMyDomainResolve_gRPC = response
                
            } catch {
                print("onFetchgRPCResolve failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
}
