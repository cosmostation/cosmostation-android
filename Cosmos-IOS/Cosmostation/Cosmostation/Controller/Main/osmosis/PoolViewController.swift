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

class PoolViewController: BaseViewController {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var poolListTableView: UITableView!
    
    var mPoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.onFetchPoolData()
    }
    
    func updateView() {
    }

    
    var mFetchCnt = 0
    @objc func onFetchPoolData() {
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 1
        self.mPoolList.removeAll()
        
        self.onFetchGammPools()
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.loadingImg.stopAnimating()
            self.loadingImg.isHidden = true
//            self.updateView()
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
}
