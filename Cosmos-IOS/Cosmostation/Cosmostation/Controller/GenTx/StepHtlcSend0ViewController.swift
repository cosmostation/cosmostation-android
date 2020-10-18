//
//  StepHtlcSend0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepHtlcSend0ViewController: BaseViewController, SBCardPopupDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var fromChainImg: UIImageView!
    @IBOutlet weak var fromChainTxt: UILabel!
    @IBOutlet weak var toChainCard: CardView!
    @IBOutlet weak var toChainImg: UIImageView!
    @IBOutlet weak var toChainText: UILabel!
    
    @IBOutlet weak var sendCoinCard: CardView!
    @IBOutlet weak var sendCoinImg: UIImageView!
    @IBOutlet weak var sendCoinTxt: UILabel!
    @IBOutlet weak var sendCoinDenom: UILabel!
    @IBOutlet weak var sendCoinAvailable: UILabel!
    
    @IBOutlet weak var RelayerMaxLayer: UIView!
    @IBOutlet weak var RelayerReaminLayer: UIView!
    @IBOutlet weak var oneTimeLimitAmount: UILabel!
    @IBOutlet weak var oneTimeLimitDenom: UILabel!
    @IBOutlet weak var systemMaxAmount: UILabel!
    @IBOutlet weak var systemMaxDenom: UILabel!
    @IBOutlet weak var systemReaminAmount: UILabel!
    @IBOutlet weak var systemReaminDenom: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var toChainList = Array<ChainType>()
    var toChain: ChainType?
    var toSwapableCoinList = Array<String>()
    var toSwapDenom: String?
    
    var kavaSwapParam2: KavaSwapParam2?
    var kavaSwapSupply2: KavaSwapSupply2?
    
    var supplyLimit = NSDecimalNumber.zero
    var supplyRemain = NSDecimalNumber.zero
    var onetimeMax = NSDecimalNumber.zero
    var availableAmount = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        self.toChainCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToChain (_:))))
        self.sendCoinCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToSendCoin (_:))))
        self.toChainList = ChainType.getHtlcSendable(pageHolderVC.chainType!)
        if (self.toChainList.count <= 0) { pageHolderVC.onBeforePage() }
        self.toChain = self.toChainList[0]
        
        self.toSwapableCoinList = ChainType.getHtlcSwappableCoin(pageHolderVC.chainType!)
        if (self.toSwapableCoinList.count <= 0) { pageHolderVC.onBeforePage() }
        self.toSwapDenom = pageHolderVC.mHtlcDenom;
        print("toSwapDenom ", toSwapDenom)
        
        self.onCheckSwapParam()
        self.updateView()
    }
    
    func updateView() {
        WUtils.onDpSwapChain(pageHolderVC.chainType!, fromChainImg, fromChainTxt)
        WUtils.onDpSwapChain(toChain!, toChainImg, toChainText)
        sendCoinDenom.text = "(" + toSwapDenom! + ")"
        if (pageHolderVC.chainType == ChainType.BINANCE_MAIN && kavaSwapParam2 != nil && kavaSwapSupply2 != nil) {
            RelayerMaxLayer.isHidden = false
            RelayerReaminLayer.isHidden = false
            if (toSwapDenom == TOKEN_HTLC_BINANCE_BNB) {
                sendCoinImg.image = UIImage(named: "bnbTokenImg")
                self.onSetDpDenom("BNB")
            }
            availableAmount = pageHolderVC.mAccount!.getTokenBalance(toSwapDenom!)
            supplyLimit = kavaSwapParam2!.getSupportedSwapAssetLimit(toSwapDenom!)
            supplyRemain = kavaSwapSupply2!.getRemainCap(toSwapDenom!, supplyLimit)
            onetimeMax = kavaSwapParam2!.getSupportedSwapAssetMaxOnce(toSwapDenom!)
            sendCoinAvailable.attributedText = WUtils.displayAmount2(availableAmount.stringValue, sendCoinAvailable.font, 0, 8)
            
        } else if (pageHolderVC.chainType == ChainType.BINANCE_TEST && kavaSwapParam2 != nil && kavaSwapSupply2 != nil) {
            RelayerMaxLayer.isHidden = false
            RelayerReaminLayer.isHidden = false
            if (toSwapDenom == TOKEN_HTLC_BINANCE_TEST_BNB) {
                sendCoinImg.image = UIImage(named: "bnbTokenImg")
                self.onSetDpDenom("BNB")
            } else if (toSwapDenom == TOKEN_HTLC_BINANCE_TEST_BTC) {
                sendCoinImg.image = UIImage(named: "btcTokenImg")
                self.onSetDpDenom("BTC")
            }
            availableAmount = pageHolderVC.mAccount!.getTokenBalance(toSwapDenom!)
            supplyLimit = kavaSwapParam2!.getSupportedSwapAssetLimit(toSwapDenom!)
            supplyRemain = kavaSwapSupply2!.getRemainCap(toSwapDenom!, supplyLimit)
            onetimeMax = kavaSwapParam2!.getSupportedSwapAssetMaxOnce(toSwapDenom!)
            sendCoinAvailable.attributedText = WUtils.displayAmount2(availableAmount.stringValue, sendCoinAvailable.font, 0, 8)
            
        } else if (pageHolderVC.chainType == ChainType.KAVA_MAIN && kavaSwapParam2 != nil && kavaSwapSupply2 != nil) {
            RelayerMaxLayer.isHidden = true
            RelayerReaminLayer.isHidden = true
            if (toSwapDenom == TOKEN_HTLC_KAVA_BNB) {
                sendCoinImg.image = UIImage(named: "bnbonKavaImg")
                self.onSetDpDenom("BNB")
            }
            availableAmount = pageHolderVC.mAccount!.getTokenBalance(toSwapDenom!)
            supplyLimit = kavaSwapParam2!.getSupportedSwapAssetLimit(toSwapDenom!)
            supplyRemain = kavaSwapSupply2!.getRemainCap(toSwapDenom!, supplyLimit)
            onetimeMax = kavaSwapParam2!.getSupportedSwapAssetMaxOnce(toSwapDenom!)
            sendCoinAvailable.attributedText = WUtils.displayAmount2(availableAmount.stringValue, sendCoinAvailable.font, 8, 8)
            
        } else if (pageHolderVC.chainType == ChainType.KAVA_TEST && kavaSwapParam2 != nil && kavaSwapSupply2 != nil) {
            RelayerMaxLayer.isHidden = true
            RelayerReaminLayer.isHidden = true
            if (toSwapDenom == TOKEN_HTLC_KAVA_TEST_BNB) {
                sendCoinImg.image = UIImage(named: "bnbonKavaImg")
                self.onSetDpDenom("BNB")
            } else if (toSwapDenom == TOKEN_HTLC_KAVA_TEST_BTC) {
                sendCoinImg.image = UIImage(named: "btconKavaImg")
                self.onSetDpDenom("BTC")
            }
            availableAmount = pageHolderVC.mAccount!.getTokenBalance(toSwapDenom!)
            supplyLimit = kavaSwapParam2!.getSupportedSwapAssetLimit(toSwapDenom!)
            supplyRemain = kavaSwapSupply2!.getRemainCap(toSwapDenom!, supplyLimit)
            onetimeMax = kavaSwapParam2!.getSupportedSwapAssetMaxOnce(toSwapDenom!)
            sendCoinAvailable.attributedText = WUtils.displayAmount2(availableAmount.stringValue, sendCoinAvailable.font, 8, 8)
            
        }
//        print("availableAmount ", availableAmount)
//        print("supplyLimit ", supplyLimit)
//        print("supplyRemain ", supplyRemain)
//        print("onetimeMax ", onetimeMax)
        
        oneTimeLimitAmount.attributedText = WUtils.displayAmount2(onetimeMax.stringValue, oneTimeLimitAmount.font, 8, 8)
        systemMaxAmount.attributedText = WUtils.displayAmount2(supplyLimit.stringValue, systemMaxAmount.font, 8, 8)
        systemReaminAmount.attributedText = WUtils.displayAmount2(supplyRemain.stringValue, systemReaminAmount.font, 8, 8)
    }
    
    func onSetDpDenom(_ denom: String) {
        sendCoinTxt.text = denom
        oneTimeLimitDenom.text = denom
        systemMaxDenom.text = denom
        systemReaminDenom.text = denom
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (supplyRemain.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_bep3_supply_full", comment: ""))
            
        } else if (!onCheckMinMinBalance()) {
            self.onShowToast(NSLocalizedString("error_bep3_under_min_amount", comment: ""))
            
        } else {
            self.btnCancel.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            self.pageHolderVC.mHtlcDenom = self.toSwapDenom
            self.pageHolderVC.mHtlcToChain = self.toChain
            self.pageHolderVC.mSwapRemainCap = self.supplyRemain
            self.pageHolderVC.mSwapMaxOnce = self.onetimeMax
            pageHolderVC.onNextPage()
        }
    }
    
    @objc func onClickToChain (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_HTLC_TO_CHAIN
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
        
    }
    
    @objc func onClickToSendCoin (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_HTLC_TO_COIN
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        if (type == SELECT_POPUP_HTLC_TO_CHAIN) {
            self.toChain = self.toChainList[result]
            self.updateView()
            
        } else if (type == SELECT_POPUP_HTLC_TO_COIN) {
            self.toSwapDenom = self.toSwapableCoinList[result]
            self.updateView()
        }
    }
    
    func onCheckSwapParam() {
        var url: String?
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_CHECK_SWAP_PARAM
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_TEST || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CHECK_SWAP_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let info = res as? [String : Any] else {
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    if (self.pageHolderVC.chainType! == ChainType.BINANCE_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                        self.kavaSwapParam2 = KavaSwapParam2.init(info)
                        self.onCheckSwapSupply()
                        
                    }  else if (self.pageHolderVC.chainType! == ChainType.BINANCE_TEST || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                        self.kavaSwapParam2 = KavaSwapParam2.init(info)
                        self.onCheckSwapSupply()
                    }

                case .failure(let error):
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
        }
        
    }
    
    func onCheckSwapSupply() {
        var url: String?
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_CHECK_SWAP_SUPPLY
        } else if (pageHolderVC.chainType! == ChainType.BINANCE_TEST || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CHECK_SWAP_SUPPLY
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let info = res as? [String : Any] else {
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    if (self.pageHolderVC.chainType! == ChainType.BINANCE_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                        self.kavaSwapSupply2 = KavaSwapSupply2.init(info)
                        
                    }  else if (self.pageHolderVC.chainType! == ChainType.BINANCE_TEST || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                        self.kavaSwapSupply2 = KavaSwapSupply2.init(info)
                    }
                    self.updateView()
                    
                case .failure(let error):
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
        }
        
    }
    
    func onCheckMinMinBalance() -> Bool {
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            if (availableAmount.compare(NSDecimalNumber.init(string: FEE_BEP3_RELAY_FEE)).rawValue > 0) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            if (availableAmount.compare(NSDecimalNumber.init(string: FEE_BEP3_RELAY_FEE).multiplying(byPowerOf10: -8) ).rawValue > 0) {
                return true
            }
        }
        return false
    }
    
}
