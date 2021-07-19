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

class SwapViewController: BaseViewController, SBCardPopupDelegate {
    
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
    var mSwapablePools: Array<Osmosis_Gamm_V1beta1_Pool> = Array<Osmosis_Gamm_V1beta1_Pool>()
    var mSwapableDenoms: Array<String> = Array<String>()
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
        inputAssetAmount = inputAssetAmount.multiplying(byPowerOf10: -WUtils.getOsmosisCoinDecimal(mInputCoinDenom))
        outputAssetAmount = outputAssetAmount.multiplying(byPowerOf10: -WUtils.getOsmosisCoinDecimal(mOutputCoinDenom))
        let swapRate = outputAssetAmount.multiplying(by: inputAssetWeight).dividing(by: inputAssetAmount, withBehavior: WUtils.handler18).dividing(by: outputAssetWeight, withBehavior: WUtils.handler6)
        print("swapRate ", swapRate)
        
        inputCoinRateAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.one.stringValue, inputCoinRateAmount.font, 0, 6)
        WUtils.DpOsmosisTokenName(inputCoinRateDenom, mInputCoinDenom!)
        outputCoinRateAmount.attributedText = WUtils.displayAmount2(swapRate.stringValue, outputCoinRateAmount.font, 0, 6)
        WUtils.DpOsmosisTokenName(outputCoinRateDenom, mOutputCoinDenom!)
        
        inputCoinImg.image = UIImage(named: "tokenDefaultIbc")
        outputCoinImg.image = UIImage(named: "tokenDefaultIbc")
        WUtils.DpOsmosisTokenImg(inputCoinImg, mInputCoinDenom!)
        WUtils.DpOsmosisTokenName(inputCoinName, mInputCoinDenom!)
        WUtils.DpOsmosisTokenImg(outputCoinImg, mOutputCoinDenom!)
        WUtils.DpOsmosisTokenName(outputCoinName, mOutputCoinDenom!)
        
    }
    
    
    @objc func onClickInput (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_OSMOSIS_COIN_IN
        popupVC.toCoinList = mAllDenoms
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
        
    }
    
    @objc func onClickOutput (_ sender: UITapGestureRecognizer) {
        self.mSwapableDenoms.removeAll()
        self.mSwapablePools.removeAll()
        self.mPoolList.forEach { pool in
            if (WUtils.isAssetHasDenom(pool.poolAssets, self.mInputCoinDenom)) {
                mSwapablePools.append(pool)
            }
        }
        self.mSwapablePools.forEach { swapablePool in
            swapablePool.poolAssets.forEach { poolAsset in
                if (poolAsset.token.denom != self.mInputCoinDenom) {
                    mSwapableDenoms.append(poolAsset.token.denom)
                }
            }
        }
        
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_OSMOSIS_COIN_OUT
        popupVC.toCoinList = mSwapableDenoms
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    
    @IBAction func onClickSwap(_ sender: UIButton) {
        print("onClickSwap")
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OSMOSIS_MSG_TYPE_SWAP
        txVC.mPoolId = String(mSelectedPool!.id)
        txVC.mSwapInDenom = mInputCoinDenom
        txVC.mSwapOutDenom = mOutputCoinDenom
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    func SBCardPopupResponse(type: Int, result: Int) {
        if (type == SELECT_POPUP_OSMOSIS_COIN_IN) {
            self.mInputCoinDenom = self.mAllDenoms[result]
            outerLoop: for pool in self.mPoolList {
                for asset in pool.poolAssets {
                    if (asset.token.denom == self.mInputCoinDenom) {
                        self.mSelectedPool = pool
                        break outerLoop
                    }
                }
            }
            for asset in self.mSelectedPool!.poolAssets {
                if (asset.token.denom != self.mInputCoinDenom) {
                    self.mOutputCoinDenom = asset.token.denom
                    break
                }
            }
            self.updateView()
            
        } else if (type == SELECT_POPUP_OSMOSIS_COIN_OUT) {
            self.mOutputCoinDenom = self.mSwapableDenoms[result]
            outerLoop: for pool in self.mSwapablePools {
                for asset in pool.poolAssets {
                    if (asset.token.denom == self.mOutputCoinDenom) {
                        self.mSelectedPool = pool
                        break outerLoop
                    }
                }
            }
            self.updateView()
        }
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
                
                //filter pool
                response.pools.forEach { pool in
                    let rawPool = try! Osmosis_Gamm_V1beta1_Pool.init(serializedData: pool.value)
                    if (BaseData.instance.mParam?.isPoolEnabled(Int(rawPool.id)) == true) {
                        self.mPoolList.append(rawPool)
                    }
                }
//                print("mPoolList ", self.mPoolList.count)
                self.mPoolList.forEach { pool in
                    pool.poolAssets.forEach { poolAsset in
                        if (!self.mAllDenoms.contains(poolAsset.token.denom)) {
                            self.mAllDenoms.append(poolAsset.token.denom)
                        }
                    }
                }
//                print("mAllDenoms ", self.mAllDenoms.count)
                
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
