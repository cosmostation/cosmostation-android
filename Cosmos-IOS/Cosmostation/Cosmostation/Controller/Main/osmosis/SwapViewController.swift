//
//  SwapViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class SwapViewController: BaseViewController {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
        
    var mPoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.onFetchSwapData()
    }

    var mFetchCnt = 0
    @objc func onFetchSwapData() {
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 1
        self.mPoolList.removeAll()
        
        self.onFetchGammPools()
    }
    
    func onFetchFinished() {
        self.loadingImg.stopAnimating()
        self.loadingImg.isHidden = true
        
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
                
                //filter only 2 asset pool
                response.pools.forEach { pool in
                    let rawPool = try! Osmosis_Gamm_V1beta1_Pool.init(serializedData: pool.value)
                    if (rawPool.poolAssets.count == 2) {
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
