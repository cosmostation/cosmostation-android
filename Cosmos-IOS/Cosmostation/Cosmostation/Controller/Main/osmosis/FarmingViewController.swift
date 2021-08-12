//
//  FarmingViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class FarmingViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var farmListTableView: UITableView!
    
    var mPoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    var mIncentivizedPool: Array<Osmosis_Poolincentives_V1beta1_IncentivizedPool> = Array<Osmosis_Poolincentives_V1beta1_IncentivizedPool>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
//        self.loadingImg.onStartAnimation()
        
        self.farmListTableView.delegate = self
        self.farmListTableView.dataSource = self
        self.farmListTableView.register(UINib(nibName: "FarmDashCell", bundle: nil), forCellReuseIdentifier: "FarmDashCell")
        self.farmListTableView.register(UINib(nibName: "MyFarmCell", bundle: nil), forCellReuseIdentifier: "MyFarmCell")
        self.farmListTableView.register(UINib(nibName: "FarmCell", bundle: nil), forCellReuseIdentifier: "FarmCell")
        
        
        self.loadingImg.isHidden = true
        self.farmListTableView.isHidden = true
//        self.onFetchFarmData()
    }
    
    func updateView() {
        self.farmListTableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"FarmDashCell") as? FarmDashCell
        return cell!
    }
    
    var mFetchCnt = 0
    @objc func onFetchFarmData() {
        print("onFetchFarmData")
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 2
        self.mPoolList.removeAll()
        self.mIncentivizedPool.removeAll()
        
        self.onFetchGammPools()
        self.onFetchIncentivizedPools()
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
                let page = Cosmos_Base_Query_V1beta1_PageRequest.with { $0.limit = 300 }
                let req = Osmosis_Gamm_V1beta1_QueryPoolsRequest.with { $0.pagination = page }
                let response = try Osmosis_Gamm_V1beta1_QueryClient(channel: channel).pools(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                
                //filter pool
                response.pools.forEach { pool in
                    let rawPool = try! Osmosis_Gamm_V1beta1_Pool.init(serializedData: pool.value)
                    if (BaseData.instance.mParam?.isPoolEnabled(Int(rawPool.id)) == true) {
                        self.mPoolList.append(rawPool)
                    }
                }
                print("mPoolList ", self.mPoolList.count)
                
            } catch {
                print("onFetchGammPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchIncentivizedPools() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Osmosis_Poolincentives_V1beta1_QueryIncentivizedPoolsRequest.init()
                let response = try Osmosis_Poolincentives_V1beta1_QueryClient(channel: channel).incentivizedPools(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                response.incentivizedPools.forEach { pool in
                    self.mIncentivizedPool.append(pool)
                }
                print("mIncentivizedPool ", self.mIncentivizedPool.count)
                
            } catch {
                print("onFetchIncentivizedPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    
    
}
