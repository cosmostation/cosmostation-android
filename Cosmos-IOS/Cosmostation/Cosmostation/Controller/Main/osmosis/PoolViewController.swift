//
//  PoolViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class PoolViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var poolListTableView: UITableView!
    
    var mMyPoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    var mOtherPoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.poolListTableView.delegate = self
        self.poolListTableView.dataSource = self
        self.poolListTableView.register(UINib(nibName: "CommonPoolCell", bundle: nil), forCellReuseIdentifier: "CommonPoolCell")
        self.poolListTableView.register(UINib(nibName: "CommonMyPoolCell", bundle: nil), forCellReuseIdentifier: "CommonMyPoolCell")
        
        self.onFetchPoolData()
    }
    
    func updateView() {
        self.poolListTableView.reloadData()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return mMyPoolList.count
        } else {
            return mOtherPoolList.count
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"CommonMyPoolCell") as? CommonMyPoolCell
            cell?.onBindOsmoPoolView(mMyPoolList[indexPath.row])
            return cell!
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"CommonPoolCell") as? CommonPoolCell
            cell?.onBindOsmoPoolView(mOtherPoolList[indexPath.row])
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 0) {
            let noticeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
            noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("title_pool_join", comment: ""), style: .default, handler: { _ in
                self.onCheckPoolJoin(self.mMyPoolList[indexPath.row])
            }))
            noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("title_pool_exit", comment: ""), style: .default, handler: { _ in
                self.onCheckExitJoin(self.mMyPoolList[indexPath.row])
            }))
            self.present(noticeAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
            
            
        } else if (indexPath.section == 1) {
            self.onCheckPoolJoin(self.mOtherPoolList[indexPath.row])
        }
    }
    
    func onCheckPoolJoin(_ pool: Osmosis_Gamm_V1beta1_Pool) {
        print("onCheckPoolJoin")
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(chainType!, OSMOSIS_MSG_TYPE_JOIN_POOL, 0)
        let coin0Denom = pool.poolAssets[0].token.denom
        let coin1Denom = pool.poolAssets[1].token.denom
        var coin0Available = BaseData.instance.getAvailableAmount_gRPC(coin0Denom)
        var coin1Available = BaseData.instance.getAvailableAmount_gRPC(coin1Denom)
        if (coin0Denom == OSMOSIS_MAIN_DENOM) {
            coin0Available = coin0Available.subtracting(txFeeAmount)
        }
        if (coin1Denom == OSMOSIS_MAIN_DENOM) {
            coin1Available = coin1Available.subtracting(txFeeAmount)
        }
        
        if (coin0Available.compare(NSDecimalNumber.zero).rawValue <= 0 || coin1Available.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_to_deposit", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OSMOSIS_MSG_TYPE_JOIN_POOL
        txVC.mPoolId = String(pool.id)
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    func onCheckExitJoin(_ pool: Osmosis_Gamm_V1beta1_Pool) {
        print("onCheckExitJoin")
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        let mainBalance = BaseData.instance.getAvailableAmount_gRPC(OSMOSIS_MAIN_DENOM)
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(chainType!, OSMOSIS_MSG_TYPE_EXIT_POOL, 0)
        if (mainBalance.compare(txFeeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OSMOSIS_MSG_TYPE_EXIT_POOL
        txVC.mPoolId = String(pool.id)
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }

    
    var mFetchCnt = 0
    @objc func onFetchPoolData() {
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 1
        self.mMyPoolList.removeAll()
        self.mOtherPoolList.removeAll()
        
        self.onFetchGammPools()
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.loadingImg.stopAnimating()
            self.loadingImg.isHidden = true
            self.updateView()
        }
    }
    
    func onFetchGammPools() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let page = Cosmos_Base_Query_V1beta1_PageRequest.with { $0.limit = 1000 }
                let req = Osmosis_Gamm_V1beta1_QueryPoolsRequest.with { $0.pagination = page }
                let response = try Osmosis_Gamm_V1beta1_QueryClient(channel: channel).pools(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                
                //filter pool
                response.pools.forEach { pool in
                    let rawPool = try! Osmosis_Gamm_V1beta1_Pool.init(serializedData: pool.value)
                    if (BaseData.instance.mParam?.isPoolEnabled(Int(rawPool.id)) == true) {
                        if (BaseData.instance.getAvailableAmount_gRPC("gamm/pool/" + String(rawPool.id)) != NSDecimalNumber.zero) {
                            self.mMyPoolList.append(rawPool)
                        } else {
                            self.mOtherPoolList.append(rawPool)
                        }
                    }
                }
//                print("mMyPoolList ", self.mMyPoolList.count)
//                print("mOtherPoolList ", self.mOtherPoolList.count)
                try? channel.close().wait()
                
            } catch {
                print("onFetchGammPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
}
