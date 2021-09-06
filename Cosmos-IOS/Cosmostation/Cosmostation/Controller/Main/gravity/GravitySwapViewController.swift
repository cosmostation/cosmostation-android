//
//  GravitySwapViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/31.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class GravitySwapViewController: BaseViewController, SBCardPopupDelegate {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var inputCoinLayer: CardView!
    @IBOutlet weak var inputCoinImg: UIImageView!
    @IBOutlet weak var inputCoinName: UILabel!
    @IBOutlet weak var inputCoinAvailableAmountLabel: UILabel!
    
    @IBOutlet weak var toggleBtn: UIButton!
    @IBOutlet weak var swapFeeLabel: UILabel!
    @IBOutlet weak var slippageLabel: UILabel!
    
    @IBOutlet weak var outputCoinLayer: CardView!
    @IBOutlet weak var outputCoinImg: UIImageView!
    @IBOutlet weak var outputCoinName: UILabel!
    
    @IBOutlet weak var inputCoinRateAmount: UILabel!
    @IBOutlet weak var inputCoinRateDenom: UILabel!
    @IBOutlet weak var outputCoinRateAmount: UILabel!
    @IBOutlet weak var outputCoinRateDenom: UILabel!
    @IBOutlet weak var inputCoinExRateAmount: UILabel!
    @IBOutlet weak var inputCoinExRateDenom: UILabel!
    @IBOutlet weak var outputCoinExRateAmount: UILabel!
    @IBOutlet weak var outputCoinExRateDenom: UILabel!
    
    
    var mAllDenoms: Array<String> = Array<String>()
    var mSwapablePools: Array<Tendermint_Liquidity_V1beta1_Pool> = Array<Tendermint_Liquidity_V1beta1_Pool>()
    var mSwapableDenoms: Array<String> = Array<String>()
    var mSelectedPool: Tendermint_Liquidity_V1beta1_Pool?
    var mInputCoinDenom: String?
    var mOutputCoinDenom: String?
    var mInPutDecimal:Int16 = 6
    var mOutPutDecimal:Int16 = 6
    var mAvailableMaxAmount = NSDecimalNumber.zero

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.inputCoinLayer.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickInput (_:))))
        self.outputCoinLayer.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickOutput (_:))))
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onGdexFetchDone(_:)), name: Notification.Name("GdexFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("GdexFetchDone"), object: nil)
    }
    
    func updateView() {
        mInPutDecimal = WUtils.getCosmosCoinDecimal(mInputCoinDenom!)
        mOutPutDecimal = WUtils.getCosmosCoinDecimal(mOutputCoinDenom!)
        mAvailableMaxAmount = BaseData.instance.getAvailableAmount_gRPC(mInputCoinDenom!)
        
        self.swapFeeLabel.attributedText = WUtils.displayPercent(NSDecimalNumber.init(string: BaseData.instance.mGravityParam_gRPC?.swapFeeRate).multiplying(byPowerOf10: -16), swapFeeLabel.font)
        self.slippageLabel.attributedText = WUtils.displayPercent(NSDecimalNumber.init(string: "3"), swapFeeLabel.font)
        self.inputCoinAvailableAmountLabel.attributedText = WUtils.displayAmount2(mAvailableMaxAmount.stringValue, inputCoinAvailableAmountLabel.font!, mInPutDecimal, mInPutDecimal)
        
        WUtils.DpCosmosTokenImg(inputCoinImg, mInputCoinDenom!)
        WUtils.DpCosmosTokenName(inputCoinName, mInputCoinDenom!)
        WUtils.DpCosmosTokenName(inputCoinRateDenom, mInputCoinDenom!)
        WUtils.DpCosmosTokenName(inputCoinExRateDenom, mInputCoinDenom!)
        WUtils.DpCosmosTokenImg(outputCoinImg, mOutputCoinDenom!)
        WUtils.DpCosmosTokenName(outputCoinName, mOutputCoinDenom!)
        WUtils.DpCosmosTokenName(outputCoinRateDenom, mOutputCoinDenom!)
        WUtils.DpCosmosTokenName(outputCoinExRateDenom, mOutputCoinDenom!)
        
        self.inputCoinRateAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.one.stringValue, inputCoinRateAmount.font, 0, mInPutDecimal)
        self.inputCoinExRateAmount.attributedText = WUtils.displayAmount2(NSDecimalNumber.one.stringValue, inputCoinExRateAmount.font, 0, mInPutDecimal)
        
        //display swap rate with this pool
        let lpInputAmount = WUtils.getLpCoinAmount(mSelectedPool?.reserveAccountAddress, mInputCoinDenom)
        let lpOutputAmount = WUtils.getLpCoinAmount(mSelectedPool?.reserveAccountAddress, mOutputCoinDenom)
        let poolSwapRate = lpOutputAmount.dividing(by: lpInputAmount, withBehavior: WUtils.handler6).multiplying(byPowerOf10: (mInPutDecimal - mOutPutDecimal))
        self.outputCoinRateAmount.attributedText = WUtils.displayAmount2(poolSwapRate.stringValue, outputCoinRateAmount.font, 0, mOutPutDecimal)
        
        //display swap rate with market price
        let priceInput = WUtils.perUsdValue(BaseData.instance.getBaseDenom(mInputCoinDenom!)) ?? NSDecimalNumber.zero
        let priceOutput = WUtils.perUsdValue(BaseData.instance.getBaseDenom(mOutputCoinDenom!)) ?? NSDecimalNumber.zero
        if (priceInput == NSDecimalNumber.zero || priceOutput == NSDecimalNumber.zero) {
            self.outputCoinExRateAmount.text = "?.??????"
        } else {
            let priceRate = priceInput.dividing(by: priceOutput, withBehavior: WUtils.handler6)
            self.outputCoinExRateAmount.attributedText = WUtils.displayAmount2(priceRate.stringValue, outputCoinExRateAmount.font, 0, mOutPutDecimal)
        }
        
        self.loadingImg.onStopAnimation()
        self.loadingImg.isHidden = true
    }
    
    
    @objc func onGdexFetchDone(_ notification: NSNotification) {
        if (BaseData.instance.mGravityPools_gRPC.count <= 0 ) {
            self.onShowToast(NSLocalizedString("error_network", comment: ""))
            self.navigationController?.popViewController(animated: true)
        }
        mAllDenoms.append(COSMOS_MAIN_DENOM)
        BaseData.instance.mGravityPools_gRPC.forEach { pool in
            pool.reserveCoinDenoms.forEach { lpCoin in
                if (!mAllDenoms.contains(lpCoin)) {
                    mAllDenoms.append(lpCoin)
                }
            }
        }
        if (mSelectedPool == nil || mInputCoinDenom == nil || mOutputCoinDenom == nil) {
            mSelectedPool = BaseData.instance.mGravityPools_gRPC[0]
            mInputCoinDenom = mSelectedPool?.reserveCoinDenoms[1]
            mOutputCoinDenom = mSelectedPool?.reserveCoinDenoms[0]
        }
        self.updateView()
    }
    
    @IBAction func onClickToggle(_ sender: UIButton) {
        let temp = mInputCoinDenom
        mInputCoinDenom = mOutputCoinDenom
        mOutputCoinDenom = temp
        self.updateView()
    }
    
    @objc func onClickInput (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_GRAVITY_SWAP_IN
        popupVC.toCoinList = mAllDenoms
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    @IBAction func onClickOutput(_ sender: UIButton) {
        self.mSwapableDenoms.removeAll()
        self.mSwapablePools.removeAll()
        BaseData.instance.mGravityPools_gRPC .forEach { pool in
            if (pool.reserveCoinDenoms.contains(self.mInputCoinDenom!)) {
                mSwapablePools.append(pool)
            }
        }
        self.mSwapablePools.forEach { swapablePool in
            swapablePool.reserveCoinDenoms.forEach { denom in
                if (denom != self.mInputCoinDenom!) {
                    mSwapableDenoms.append(denom)
                }
            }
        }
        
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_GRAVITY_SWAP_OUT
        popupVC.toCoinList = mSwapableDenoms
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    func SBCardPopupResponse(type: Int, result: Int) {
        if (type == SELECT_POPUP_GRAVITY_SWAP_IN) {
            self.mInputCoinDenom = self.mAllDenoms[result]
            outerLoop: for pool in BaseData.instance.mGravityPools_gRPC {
                for asset in pool.reserveCoinDenoms {
                    if (asset == self.mInputCoinDenom) {
                        self.mSelectedPool = pool
                        break outerLoop
                    }
                }
            }
            for asset in self.mSelectedPool!.reserveCoinDenoms {
                if (asset != self.mInputCoinDenom) {
                    self.mOutputCoinDenom = asset
                    break
                }
            }
            self.updateView()
            
        } else if (type == SELECT_POPUP_GRAVITY_SWAP_OUT) {
            self.mOutputCoinDenom = self.mSwapableDenoms[result]
            outerLoop: for pool in self.mSwapablePools {
                for asset in pool.reserveCoinDenoms {
                    if (asset == self.mOutputCoinDenom) {
                        self.mSelectedPool = pool
                        break outerLoop
                    }
                }
            }
            self.updateView()
        }
    }
    
    
    @IBAction func onClickStarSwap(_ sender: UIButton) {
        print("onClickStarSwap")
    }
    
}
