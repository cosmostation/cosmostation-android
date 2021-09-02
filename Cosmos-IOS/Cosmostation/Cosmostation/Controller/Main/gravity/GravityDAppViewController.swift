//
//  GravityDAppViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/31.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO
import SwiftProtobuf

class GravityDAppViewController: BaseViewController {
    
    @IBOutlet weak var dAppsSegment: UISegmentedControl!
    @IBOutlet weak var gravitySwapView: UIView!
    @IBOutlet weak var gravityPoolView: UIView!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        gravitySwapView.alpha = 1
        gravityPoolView.alpha = 0
        
        if #available(iOS 13.0, *) {
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            dAppsSegment.selectedSegmentTintColor = TRANS_BG_COLOR_COSMOS2
        } else {
            dAppsSegment.tintColor = COLOR_ATOM
        }
        
        self.onFetchGdexData()
    }
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            gravitySwapView.alpha = 1
            gravityPoolView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            gravitySwapView.alpha = 0
            gravityPoolView.alpha = 1
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_dapp_gravity", comment: "");
        self.navigationItem.title = NSLocalizedString("title_dapp_gravity", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    var mFetchCnt = 0
    @objc func onFetchGdexData() -> Bool {
        if (self.mFetchCnt > 0)  {
            return false
        }
        self.mFetchCnt = 2
        BaseData.instance.mGravityPools_gRPC.removeAll()
        BaseData.instance.mGravityManager_gRPC.removeAll()
        
        self.onFetchGdexParam()
        self.onFetchGdexPools()
        return true
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt > 0) { return }
        NotificationCenter.default.post(name: Notification.Name("GdexFetchDone"), object: nil, userInfo: nil)
    }
    
    func onFetchGdexParam() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Tendermint_Liquidity_V1beta1_QueryParamsRequest.init()
                let response = try Tendermint_Liquidity_V1beta1_QueryClient(channel: channel).params(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                BaseData.instance.mGravityParam_gRPC = response.params
                
            } catch {
                print("onFetchGdexParam failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchGdexPools() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }

            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }

            do {
                let page = Cosmos_Base_Query_V1beta1_PageRequest.with { $0.limit = 1000 }
                let req = Tendermint_Liquidity_V1beta1_QueryLiquidityPoolsRequest.with { $0.pagination = page }
                let response = try Tendermint_Liquidity_V1beta1_QueryClient(channel: channel).liquidityPools(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                
                self.mFetchCnt = self.mFetchCnt + response.pools.count
                response.pools.forEach { pool in
                    self.onFetchGdexPoolManager(pool.reserveAccountAddress)
                    BaseData.instance.mGravityPools_gRPC.append(pool)
                }

            } catch {
                print("onFetchgRPCGravityPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchGdexPoolManager(_ address: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let req = Cosmos_Bank_V1beta1_QueryAllBalancesRequest.with { $0.address = address }
                let response = try Cosmos_Bank_V1beta1_QueryClient(channel: channel).allBalances(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                BaseData.instance.mGravityManager_gRPC.append(GDexManager.init(address, response.balances))
                
            } catch {
                print("onFetchGdexPoolManager failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }

}
