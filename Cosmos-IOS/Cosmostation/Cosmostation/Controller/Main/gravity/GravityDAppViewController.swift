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

extension WUtils {
    static func getCosmosTokenName(_ denom: String) -> String {
        if (denom == COSMOS_MAIN_DENOM) {
            return "ATOM"
            
        } else if (denom.starts(with: "pool")) {
            if let poolInfo = BaseData.instance.getGravityPoolByDenom(denom)  {
                return "GDEX-" + String(poolInfo.id)
            } else {
                return"UnKnown"
            }
            
        } else if (denom.starts(with: "ibc/")) {
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")), let dpDenom = ibcToken.display_denom {
                return dpDenom.uppercased()
            } else {
                return"UnKnown"
            }
        }
        return denom
    }
    
    static func getCosmosTokenImg(_ denom: String) -> UIImage? {
        if (denom == COSMOS_MAIN_DENOM) {
            return UIImage(named: "atom_ic")
            
        } else if (denom.starts(with: "pool")) {
            if let poolInfo = BaseData.instance.getGravityPoolByDenom(denom)  {
                return UIImage(named: "tokenGravitydex")
            }
            
        } else if (denom.starts(with: "ibc/")) {
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")), let url = URL(string: ibcToken.moniker ?? ""), let data = try? Data(contentsOf: url) {
                return UIImage(data: data)?.resized(to: CGSize(width: 20, height: 20))
            } else {
                return UIImage(named: "tokenDefaultIbc")
            }
        }
        return UIImage(named: "tokenIc")
    }
    
    
    static func getCosmosCoinDecimal(_ denom: String) -> Int16 {
        if (denom == COSMOS_MAIN_DENOM) {
            return 6;
            
        } else if (denom.starts(with: "pool")) {
            if let poolInfo = BaseData.instance.getGravityPoolByDenom(denom)  {
                return 6;
            }
            
        } else if (denom.starts(with: "ibc/")) {
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")), let decimal = ibcToken.decimal  {
                return decimal
            }
        }
        return 6;
    }
    
    static func DpCosmosTokenName(_ label: UILabel, _ denom: String) {
        if (denom == COSMOS_MAIN_DENOM) {
            label.textColor = COLOR_ATOM
            label.text = "ATOM"
            
        } else if (denom.starts(with: "pool")) {
            label.textColor = .white
            if let poolInfo = BaseData.instance.getGravityPoolByDenom(denom)  {
                label.text = "GDEX-" + String(poolInfo.id)
            } else {
                label.text = "UnKnown"
            }
            
        } else if (denom.starts(with: "ibc/")) {
            label.textColor = .white
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")), let dpDenom = ibcToken.display_denom {
                label.text = dpDenom.uppercased()
            } else {
                label.text = "UnKnown"
            }
            
        } else {
            label.textColor = .white
            label.text = "UnKnown"
        }
    }
    
    static func DpCosmosTokenImg(_ imgView: UIImageView, _ denom: String) {
        if (denom == COSMOS_MAIN_DENOM) {
            imgView.image = UIImage(named: "atom_ic")
            
        } else if (denom.starts(with: "pool")) {
            if let poolInfo = BaseData.instance.getGravityPoolByDenom(denom)  {
                imgView.image = UIImage(named: "tokenGravitydex")
            }
            
        } else if (denom.starts(with: "ibc/")) {
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")), let url = URL(string: ibcToken.moniker ?? "") {
                imgView.af_setImage(withURL: url)
            } else {
                imgView.image = UIImage(named: "tokenDefaultIbc")
            }
            
        } else {
            imgView.image = UIImage(named: "tokenIc")
            
        }
    }
    
    static func getGDexManager(_ address: String?) -> GDexManager? {
        return BaseData.instance.mGravityManager_gRPC.filter {$0.address == address }.first
    }
    
    static func getLpCoinAmount(_ address: String?, _ denom: String?) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if let gDexManager = getGDexManager(address) {
            return NSDecimalNumber.init(string: gDexManager.reserve.filter{ $0.denom == denom }.first?.amount)
        }
        return result
    }
    
}
