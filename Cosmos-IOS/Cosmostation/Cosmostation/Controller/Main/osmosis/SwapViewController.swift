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
    @IBOutlet weak var inputCoinLayer: CardView!
    @IBOutlet weak var inputCoinImg: UIImageView!
    @IBOutlet weak var inputCoinName: UILabel!
    @IBOutlet weak var outputCoinLayer: CardView!
    @IBOutlet weak var outputCoinImg: UIImageView!
    @IBOutlet weak var outputCoinName: UILabel!
    @IBOutlet weak var poolIdLabel: UILabel!
    @IBOutlet weak var swapFeeLabel: UILabel!
    @IBOutlet weak var inputCoinRateAmount: UILabel!
    @IBOutlet weak var inputCoinRateDenom: UILabel!
    @IBOutlet weak var outputCoinRateAmount: UILabel!
    @IBOutlet weak var outputCoinRateDenom: UILabel!
    
    
    var mPoolList: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    var mAllDenoms: Array<String> = Array<String>()
    var mSelectedPool: Osmosis_Gamm_V1beta1_Pool?
    var mInputCoinDenom: String?
    var mOutputCoinDenom: String?
    

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        //init for pool pair osmos/atom
        
        self.inputCoinLayer.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickInput (_:))))
        self.outputCoinLayer.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickOutput (_:))))
        self.onFetchSwapData()
    }
    
    
    func updateView() {
        self.poolIdLabel.text = String(mSelectedPool!.id)
        self.swapFeeLabel.attributedText = WUtils.displayPercent(NSDecimalNumber.init(string: mSelectedPool?.poolParams.swapFee).multiplying(byPowerOf10: -16), swapFeeLabel.font)
        
        var inputAssetAmount = NSDecimalNumber.zero
        var inputAssetWeight = NSDecimalNumber.zero
        var outputAssetAmount = NSDecimalNumber.zero
        var outputAssetWeight = NSDecimalNumber.zero
        mSelectedPool!.poolAssets.forEach { poolAsset in
            if (poolAsset.token.denom == mInputCoinDenom) {
                inputAssetAmount = NSDecimalNumber.init(string: poolAsset.token.amount)
                inputAssetWeight = NSDecimalNumber.init(string: poolAsset.weight)
            }
            if (poolAsset.token.denom == mOutputCoinDenom) {
                outputAssetAmount = NSDecimalNumber.init(string: poolAsset.token.amount)
                outputAssetWeight = NSDecimalNumber.init(string: poolAsset.weight)
            }
        }
        let swapRate = outputAssetAmount.multiplying(by: outputAssetWeight).dividing(by: inputAssetAmount, withBehavior: WUtils.handler18).dividing(by: inputAssetWeight, withBehavior: WUtils.handler6)
        print("swapRate ", swapRate)
        
        inputCoinRateAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.one.stringValue, inputCoinRateAmount.font, 0, 6)
        WUtils.DpOsmosisTokenName(inputCoinRateDenom, mInputCoinDenom!)
        outputCoinRateAmount.attributedText = WUtils.displayAmount2(swapRate.stringValue, outputCoinRateAmount.font, 0, 6)
        WUtils.DpOsmosisTokenName(outputCoinRateDenom, mOutputCoinDenom!)
        
        
        WUtils.DpOsmosisTokenImg(inputCoinImg, mInputCoinDenom!)
        WUtils.DpOsmosisTokenName(inputCoinName, mInputCoinDenom!)
        WUtils.DpOsmosisTokenImg(outputCoinImg, mOutputCoinDenom!)
        WUtils.DpOsmosisTokenName(outputCoinName, mOutputCoinDenom!)
        
    }
    
    
    @objc func onClickInput (_ sender: UITapGestureRecognizer) {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        mAllDenoms.forEach { denom in
            let action = UIAlertAction(title: WUtils.getOsmosisTokenName(denom), style: .default, handler: { _ in
                outerLoop: for pool in self.mPoolList {
                    for asset in pool.poolAssets {
                        if (asset.token.denom == denom) {
                            self.mSelectedPool = pool
                            break outerLoop
                        }
                    }
                }
//                print("mSelectedPool ", self.mSelectedPool?.id, "   ",  self.mSelectedPool)
                
                self.mInputCoinDenom = denom
                for asset in self.mSelectedPool!.poolAssets {
                    if (asset.token.denom != self.mInputCoinDenom) {
                        self.mOutputCoinDenom = asset.token.denom
                        break
                    }
                }
//                print("mOutputCoinDenom ", self.mOutputCoinDenom)
                self.updateView()
            })
            showAlert.addAction(action)
        }
        self.present(showAlert, animated: true, completion: nil)
        
    }
    
    @objc func onClickOutput (_ sender: UITapGestureRecognizer) {
        print("onClickOutput ",self.mInputCoinDenom, "  ", self.mOutputCoinDenom)
        var swapablePools: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
        var swapableDenoms: Array<String> = Array<String>()
        
        self.mPoolList.forEach { pool in
//            if (WUtils.isAssetHasDenom(pool.poolAssets, self.mInputCoinDenom) && !WUtils.isAssetHasDenom(pool.poolAssets, self.mOutputCoinDenom)) {
            if (WUtils.isAssetHasDenom(pool.poolAssets, self.mInputCoinDenom)) {
                swapablePools.append(pool)
            }
        }
        
        swapablePools.forEach { swapablePool in
            swapablePool.poolAssets.forEach { poolAsset in
                if (poolAsset.token.denom != self.mInputCoinDenom) {
                    swapableDenoms.append(poolAsset.token.denom)
                }
            }
        }
        
        print("swapablePools ", swapablePools.count)
        print("swapablePools ", swapablePools)
        print("swapableDenoms ", swapableDenoms.count)
        print("swapableDenoms ", swapableDenoms)
    }
    
    
    @IBAction func onClickSwap(_ sender: UIButton) {
        print("onClickSwap")
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
                
                //filter only 2 asset pool
                response.pools.forEach { pool in
                    let rawPool = try! Osmosis_Gamm_V1beta1_Pool.init(serializedData: pool.value)
                    if (rawPool.poolAssets.count == 2) {
                        self.mPoolList.append(rawPool)
                    }
                }
                print("mPoolList ", self.mPoolList.count)
                self.mPoolList.forEach { pool in
                    pool.poolAssets.forEach { poolAsset in
                        if (!self.mAllDenoms.contains(poolAsset.token.denom)) {
                            self.mAllDenoms.append(poolAsset.token.denom)
                        }
                    }
                }
                print("mAllDenoms ", self.mAllDenoms.count)
                
                self.mSelectedPool = self.mPoolList[0]
                self.mInputCoinDenom = "uosmo"
                self.mOutputCoinDenom = "ibc/27394FB092D2ECCD56123C74F36E4C1F926001CEADA9CA97EA622B25F41E5EB2"
                
            } catch {
                print("onFetchGammPools failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
}
