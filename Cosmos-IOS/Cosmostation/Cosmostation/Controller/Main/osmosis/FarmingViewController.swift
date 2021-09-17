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
    var mMyIncentivePoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    var mOtherIncentivePoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    
    var mIncentivizedPool: Array<Osmosis_Poolincentives_V1beta1_IncentivizedPool> = Array<Osmosis_Poolincentives_V1beta1_IncentivizedPool>()
    var mActiveGauges: Array<Osmosis_Incentives_Gauge> = Array<Osmosis_Incentives_Gauge>()
    var mPeriodLockUps = Array<Osmosis_Lockup_PeriodLock>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.farmListTableView.delegate = self
        self.farmListTableView.dataSource = self
        self.farmListTableView.register(UINib(nibName: "FarmDashCell", bundle: nil), forCellReuseIdentifier: "FarmDashCell")
        self.farmListTableView.register(UINib(nibName: "MyFarmCell", bundle: nil), forCellReuseIdentifier: "MyFarmCell")
        self.farmListTableView.register(UINib(nibName: "FarmCell", bundle: nil), forCellReuseIdentifier: "FarmCell")
        
        self.onFetchFarmData()
    }
    
    func getPoolwithID(_ id: UInt64) -> Osmosis_Gamm_V1beta1_Pool? {
        return self.mPoolList.filter { $0.id == id }.first
    }
    
    func updateView() {
        self.farmListTableView.reloadData()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
//            return mMyIncentivePoolList.count > 0 ? 1 : 0
            return 0
        } else if (section == 1) {
            return mMyIncentivePoolList.count
        } else if (section == 2) {
            return mOtherIncentivePoolList.count
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"FarmDashCell") as? FarmDashCell
            cell?.onBindView(mMyIncentivePoolList, mPeriodLockUps)
            return cell!
            
        } else if (indexPath.section == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"MyFarmCell") as? MyFarmCell
            let pool = mMyIncentivePoolList[indexPath.row]
            let gauges = WUtils.getGaugesByPoolId(pool.id, mIncentivizedPool, mActiveGauges)
            cell?.onBindView(pool, mPeriodLockUps, gauges)
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"FarmCell") as? FarmCell
            let pool = mOtherIncentivePoolList[indexPath.row]
            let gauges = WUtils.getGaugesByPoolId(pool.id, mIncentivizedPool, mActiveGauges)
            cell?.onBindView(pool, gauges)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 1) {
            let farmDetailVC = FarmingDetailViewController(nibName: "FarmingDetailViewController", bundle: nil)
            let pool = mMyIncentivePoolList[indexPath.row]
            farmDetailVC.mPool = pool
            farmDetailVC.mPoolGauges = WUtils.getGaugesByPoolId(pool.id, mIncentivizedPool, mActiveGauges)
            farmDetailVC.mLockUps = WUtils.getLockupByPoolId(pool.id, mPeriodLockUps)
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(farmDetailVC, animated: true)
            
        } else if (indexPath.section == 2) {
            let farmDetailVC = FarmingDetailViewController(nibName: "FarmingDetailViewController", bundle: nil)
            let pool = mOtherIncentivePoolList[indexPath.row]
            farmDetailVC.mPool = pool
            farmDetailVC.mPoolGauges = WUtils.getGaugesByPoolId(pool.id, mIncentivizedPool, mActiveGauges)
            farmDetailVC.mLockUps = WUtils.getLockupByPoolId(pool.id, mPeriodLockUps)
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(farmDetailVC, animated: true)
            
        }
    }
    
    var mFetchCnt = 0
    @objc func onFetchFarmData() {
        print("onFetchFarmData")
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 4
        self.mPoolList.removeAll()
        self.mIncentivizedPool.removeAll()
        self.mPeriodLockUps.removeAll()
        
        self.onFetchGammPools()
        self.onFetchIncentivizedPools()
        self.onFetchActiveGauges()
        self.onFetchLockupStatus(account!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            
            var incentivePoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
            self.mIncentivizedPool.forEach { incentivizedPool in
                if (incentivePoolList.filter { $0.id == incentivizedPool.poolID }.first == nil ? true : false) {
                    incentivePoolList.append(getPoolwithID(incentivizedPool.poolID)!)
                }
            }
//            print("incentivePoolList ", incentivePoolList.count)
            
            incentivePoolList.forEach { pool in
                var isMine = false
                self.mPeriodLockUps.forEach { lockup in
                    let tempPoolId = lockup.coins[0].denom.replacingOccurrences(of: "gamm/pool/", with: "")
                    if let poolId = Int(tempPoolId) {
                        if (poolId == pool.id) { isMine = true }
                    }
                }
                if (isMine) { mMyIncentivePoolList.append(pool) }
                else { mOtherIncentivePoolList.append(pool) }
            }
//            print("mMyIncentivePoolList ", mMyIncentivePoolList.count)
//            print("mOtherIncentivePoolList ", mOtherIncentivePoolList.count)
            
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
                        self.mPoolList.append(rawPool)
                    }
                }
//                print("mPoolList ", self.mPoolList.count)
                try? channel.close().wait()
                
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
//                print("mIncentivizedPool ", self.mIncentivizedPool.count)
                
            } catch {
                print("onFetchIncentivizedPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchActiveGauges() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let page = Cosmos_Base_Query_V1beta1_PageRequest.with { $0.limit = 1000 }
                let req = Osmosis_Incentives_ActiveGaugesRequest.with { $0.pagination = page }
                let response = try Osmosis_Incentives_QueryClient(channel: channel).activeGauges(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                self.mActiveGauges = response.data
                print("mActiveGauges ", self.mActiveGauges.count)
                
            } catch {
                print("onFetchActiveGauges failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchLockupStatus(_ address: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Osmosis_Lockup_AccountLockedPastTimeRequest.with { $0.owner = address }
                let response = try Osmosis_Lockup_QueryClient(channel: channel).accountLockedPastTime(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                self.mPeriodLockUps = response.locks
                print("mPeriodLockUps ", self.mPeriodLockUps.count)
                
            } catch {
                print("onFetchUnLockingCoins failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    
    
    
}
