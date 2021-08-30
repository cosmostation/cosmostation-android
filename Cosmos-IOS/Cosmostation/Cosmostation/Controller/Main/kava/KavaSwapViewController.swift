//
//  KavaSwapViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/27.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class KavaSwapViewController: BaseViewController, SBCardPopupDelegate{
    
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
    
    var mSwapParam: SwapParam!
    var mSwapPools: Array<SwapPool> = Array<SwapPool>()
    var mAllDenoms: Array<String> = Array<String>()
    var mSwapablePools: Array<SwapPool> = Array<SwapPool>()
    var mSwapableDenoms: Array<String> = Array<String>()
    var mSelectedPool: SwapPool!
    var mInputCoinDenom: String!
    var mOutputCoinDenom: String!
    var mInputCoinAmount = NSDecimalNumber.zero
    var mOutputCoinAmount = NSDecimalNumber.zero

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.inputCoinLayer.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickInput (_:))))
        self.outputCoinLayer.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickOutput (_:))))
        
        self.onFetchSwapPoolData()
    }
    
    func updateView() {
        let inputCoinDecimal = WUtils.getKavaCoinDecimal(mInputCoinDenom)
        let outputCoinDecimal = WUtils.getKavaCoinDecimal(mOutputCoinDenom)
        
        if (mSelectedPool.coins[0].denom == self.mInputCoinDenom) {
            mInputCoinAmount = NSDecimalNumber.init(string: self.mSelectedPool.coins[0].amount)
            mOutputCoinAmount = NSDecimalNumber.init(string: self.mSelectedPool.coins[1].amount)
        } else {
            mInputCoinAmount = NSDecimalNumber.init(string: self.mSelectedPool.coins[1].amount)
            mOutputCoinAmount = NSDecimalNumber.init(string: self.mSelectedPool.coins[0].amount)
        }
        let swapRate = mOutputCoinAmount.dividing(by: mInputCoinAmount, withBehavior: WUtils.handler6).multiplying(byPowerOf10: (inputCoinDecimal - outputCoinDecimal))
        print("swapRate ", swapRate)
        
        WUtils.DpKavaTokenName(inputCoinName, mInputCoinDenom)
        WUtils.DpKavaTokenName(outputCoinName, mOutputCoinDenom)
        inputCoinImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mInputCoinDenom + ".png")!)
        outputCoinImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mOutputCoinDenom + ".png")!)
        
        
        poolIdLabel.text = mSelectedPool.name?.uppercased()
        swapFeeLabel.attributedText = WUtils.displayPercent(mSwapParam.swap_fee, swapFeeLabel.font)
        
        inputCoinRateAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.one.stringValue, inputCoinRateAmount.font, 0, inputCoinDecimal)
        WUtils.DpKavaTokenName(inputCoinRateDenom, mInputCoinDenom)
        outputCoinRateAmount.attributedText = WUtils.displayAmount2(swapRate.stringValue, outputCoinRateAmount.font, 0, outputCoinDecimal)
        WUtils.DpKavaTokenName(outputCoinRateDenom, mOutputCoinDenom)
    }

    
    @objc func onClickInput (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_KAVA_SWAP_IN
        popupVC.toCoinList = mAllDenoms
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    @objc func onClickOutput (_ sender: UITapGestureRecognizer) {
        self.mSwapablePools.removeAll()
        self.mSwapableDenoms.removeAll()
        for pool in self.mSwapPools {
            if (pool.name?.contains(self.mInputCoinDenom) == true) {
                mSwapablePools.append(pool)
            }
        }
        self.mSwapablePools.forEach { swapablePool in
            if (swapablePool.coins[0].denom == self.mInputCoinDenom) {
                mSwapableDenoms.append(swapablePool.coins[1].denom)
            } else {
                mSwapableDenoms.append(swapablePool.coins[0].denom)
            }
        }

        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_KAVA_SWAP_OUT
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
        txVC.mType = KAVA_MSG_TYPE_SWAP_TOKEN
        txVC.mKavaPool = mSelectedPool
        txVC.mSwapInDenom = mInputCoinDenom
        txVC.mSwapOutDenom = mOutputCoinDenom
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    func SBCardPopupResponse(type: Int, result: Int) {
        if (type == SELECT_POPUP_KAVA_SWAP_IN) {
            self.mInputCoinDenom = self.mAllDenoms[result]
            for pool in self.mSwapPools {
                if (pool.name?.contains(self.mInputCoinDenom) == true) {
                    self.mSelectedPool = pool
                    break
                }
            }
            if (self.mSelectedPool.coins[0].denom == self.mInputCoinDenom) {
                self.mOutputCoinDenom = self.mSelectedPool.coins[1].denom
            } else {
                self.mOutputCoinDenom = self.mSelectedPool.coins[0].denom
            }
            self.updateView()
            
        } else if (type == SELECT_POPUP_KAVA_SWAP_OUT) {
            self.mOutputCoinDenom = self.mSwapableDenoms[result]
            for pool in self.mSwapPools {
                if (pool.name?.contains(self.mInputCoinDenom) == true && pool.name?.contains(self.mOutputCoinDenom) == true) {
                    self.mSelectedPool = pool
                    break
                }
            }
            self.updateView()
        }
    }
    
    var mFetchCnt = 0
    @objc func onFetchSwapPoolData() {
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mSwapPools.removeAll()
        self.mAllDenoms.removeAll()
        self.mFetchCnt = 2
        
        self.onFetchSwapPoolParam()
        self.onFetchSwapPoolList()
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.loadingImg.stopAnimating()
            self.loadingImg.isHidden = true
            self.updateView()
        }
    }
    
    func onFetchSwapPoolParam() {
        let request = Alamofire.request(BaseNetWork.paramSwapPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSwapPoolParam ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                self.mSwapParam = KavaSwapParam.init(responseData).result
                BaseData.instance.mKavaSwapParam = self.mSwapParam
            
                var market_ids = Array<String>()
                BaseData.instance.mKavaPriceMarkets.forEach { priceMarket in
                    if (!priceMarket.market_id.contains(":30")) {
                        market_ids.append(priceMarket.market_id)
                    }
                }
                self.mFetchCnt = self.mFetchCnt + market_ids.count
                market_ids.forEach { market_id in
                    self.onFetchPriceFeedPrice(market_id)
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSwapPoolParam ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchPriceFeedPrice(_ market: String) {
        let request = Alamofire.request(BaseNetWork.priceFeedUrl(chainType, market), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchPriceFeedPrice ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let priceParam = KavaPriceFeedPrice.init(responseData)
                BaseData.instance.mKavaPrice[priceParam.result.market_id] = priceParam
                
            case .failure(let error):
                print("onFetchKavaPrice ", market , " ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSwapPoolList() {
        let request = Alamofire.request(BaseNetWork.listSwapPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSwapPoolList ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String  else {
                    self.onFetchFinished()
                    return
                }
                self.mSwapPools = KavaSwapPool.init(responseData).result
                print("mSwapPools ", self.mSwapPools.count)
                self.mSwapPools.forEach { pool in
                    if (!self.mAllDenoms.contains(pool.coins[0].denom)) {
                        self.mAllDenoms.append(pool.coins[0].denom)
                    }
                    if (!self.mAllDenoms.contains(pool.coins[1].denom)) {
                        self.mAllDenoms.append(pool.coins[1].denom)
                    }
                }
                print("mAllDenoms ", self.mAllDenoms.count)
                
                self.mSwapPools.forEach { pool in
                    if (pool.name?.contains("ukava") == true && pool.name?.contains("usdx") == true) {
                        self.mSelectedPool = pool
                        self.mInputCoinDenom = "ukava"
                        self.mOutputCoinDenom = "usdx"
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSwapPoolList ", error) }
            }
            self.onFetchFinished()
        }
    }
}
