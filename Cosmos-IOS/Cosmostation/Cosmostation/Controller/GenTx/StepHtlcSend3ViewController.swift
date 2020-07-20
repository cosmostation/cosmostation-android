//
//  StepHtlcSend3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend3ViewController: BaseViewController, PasswordViewDelegate, SBCardPopupDelegate {
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    @IBOutlet weak var sendImg: UIImageView!
    @IBOutlet weak var sendAmountLabel: UILabel!
    @IBOutlet weak var sendAmountDenom: UILabel!
    @IBOutlet weak var sendFeeLabel: UILabel!
    @IBOutlet weak var sendFeeDenom: UILabel!
    @IBOutlet weak var recipientChainLabel: UILabel!
    @IBOutlet weak var recipientAddressLabel: UILabel!
    
    @IBOutlet weak var claimImg: UIImageView!
    @IBOutlet weak var receiveAmountLabel: UILabel!
    @IBOutlet weak var receiveAmountDenom: UILabel!
    @IBOutlet weak var relayFeeLabel: UILabel!
    @IBOutlet weak var relayFeeDenom: UILabel!
    @IBOutlet weak var claimFeeLabel: UILabel!
    @IBOutlet weak var claimFeeDenom: UILabel!
    @IBOutlet weak var claimAddress: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    var relayerFee: NSDecimalNumber = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
        self.onUpdateView()
    }
    
    func onUpdateView() {
        onInitSendFee()
        onInitClaimFee()
        
        let toSendAmount = WUtils.stringToDecimalNoLocale(pageHolderVC.mToSendAmount[0].amount)
        let sendFeeAmount = WUtils.stringToDecimalNoLocale((pageHolderVC.mHtlcSendFee?.amount[0].amount)!)
        let claimFeeAmount = WUtils.stringToDecimalNoLocale((pageHolderVC.mHtlcClaimFee?.amount[0].amount)!)
        
        //set Send layer's data
        sendImg.image = sendImg.image?.withRenderingMode(.alwaysTemplate)
        sendImg.tintColor = WUtils.getChainColor(pageHolderVC.chainType!)
        WUtils.setDenomTitle(pageHolderVC.chainType!, sendFeeDenom)
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            sendAmountDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            sendAmountDenom.textColor = COLOR_BNB
            
            sendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, sendAmountLabel.font, 0, 8)
            sendFeeLabel.attributedText = WUtils.displayAmount2(sendFeeAmount.stringValue, sendFeeLabel.font, 0, 8)
            
            recipientChainLabel.text = WUtils.getChainId(pageHolderVC.mHtlcToChain!)
            recipientAddressLabel.text = pageHolderVC.mHtlcToAccount?.account_address
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            sendAmountDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            sendAmountDenom.textColor = .white
            
            sendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, sendAmountLabel.font, WUtils.getKavaCoinDecimal(pageHolderVC.mToSendAmount[0].denom), WUtils.getKavaCoinDecimal(pageHolderVC.mToSendAmount[0].denom))
            sendFeeLabel.attributedText = WUtils.displayAmount2(sendFeeAmount.stringValue, sendFeeLabel.font, 6, 6)
            
            recipientChainLabel.text = WUtils.getChainId(pageHolderVC.mHtlcToChain!)
            claimAddress.text = pageHolderVC.mHtlcToAccount?.account_address
            
        }
        
        //set Claim layer's data
        claimImg.image = claimImg.image?.withRenderingMode(.alwaysTemplate)
        claimImg.tintColor = WUtils.getChainColor(pageHolderVC.mHtlcToChain!)
        WUtils.setDenomTitle(pageHolderVC.mHtlcToChain!, claimFeeDenom)
        if (pageHolderVC.mHtlcToChain == ChainType.BINANCE_MAIN || pageHolderVC.mHtlcToChain == ChainType.BINANCE_TEST) {
            claimFeeLabel.attributedText = WUtils.displayAmount2(claimFeeAmount.stringValue, claimFeeLabel.font, 0, 8)
            recipientAddressLabel.text = pageHolderVC.mHtlcToAccount?.account_address
            
            relayFeeDenom.textColor = COLOR_BNB
            receiveAmountDenom.textColor = COLOR_BNB
            relayFeeDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            receiveAmountDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            relayerFee = NSDecimalNumber.init(string: FEE_BEP3_RELAY_FEE).multiplying(byPowerOf10: 8)
            relayFeeLabel.attributedText = WUtils.displayAmount2(relayerFee.stringValue, relayFeeLabel.font, 8, 8)
            receiveAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.subtracting(relayerFee).stringValue , receiveAmountLabel.font, 8, 8)
            
            
        } else if (pageHolderVC.mHtlcToChain == ChainType.KAVA_MAIN || pageHolderVC.mHtlcToChain == ChainType.KAVA_TEST) {
            claimFeeLabel.attributedText = WUtils.displayAmount2(claimFeeAmount.stringValue, claimFeeLabel.font, 6, 6)
            claimAddress.text = pageHolderVC.mHtlcToAccount?.account_address
            
            relayFeeDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            receiveAmountDenom.text = self.pageHolderVC.mHtlcDenom!.uppercased()
            relayerFee = NSDecimalNumber.init(string: FEE_BEP3_RELAY_FEE)
            relayFeeLabel.attributedText = WUtils.displayAmount2(relayerFee.stringValue, relayFeeLabel.font, 0, 8)
            receiveAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.subtracting(relayerFee).stringValue , receiveAmountLabel.font, 0, 8)
        }
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        let popupVC = Bep3WarnPopup(nibName: "Bep3WarnPopup", bundle: nil)
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    func SBCardPopupResponse(result: Int) {
        if (result == 1) {
            self.btnBack.isUserInteractionEnabled = false
            self.btnConfirm.isUserInteractionEnabled = false
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
        }
    }
    
    func onInitSendFee() {
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN ||
            pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            let feeCoin = Coin.init(BNB_MAIN_DENOM, "0.000375")
            var tempList = Array<Coin>()
            tempList.append(feeCoin)
            pageHolderVC.mHtlcSendFee = Fee.init("", tempList)
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let feeCoin = Coin.init(KAVA_MAIN_DENOM, "0")
            var tempList = Array<Coin>()
            tempList.append(feeCoin)
            pageHolderVC.mHtlcSendFee = Fee.init(KAVA_GAS_FEE_AMOUNT_BEP3, tempList)
            
        }
    }
    
    func onInitClaimFee() {
        if (pageHolderVC.mHtlcToChain == ChainType.BINANCE_MAIN ||
            pageHolderVC.mHtlcToChain == ChainType.BINANCE_TEST) {
            let feeCoin = Coin.init(BNB_MAIN_DENOM, "0.000375")
            var tempList = Array<Coin>()
            tempList.append(feeCoin)
            pageHolderVC.mHtlcClaimFee = Fee.init("", tempList)
            
        } else if (pageHolderVC.mHtlcToChain == ChainType.KAVA_MAIN ||
            pageHolderVC.mHtlcToChain == ChainType.KAVA_TEST) {
            let feeCoin = Coin.init(KAVA_MAIN_DENOM, "0")
            var tempList = Array<Coin>()
            tempList.append(feeCoin)
            pageHolderVC.mHtlcClaimFee = Fee.init(KAVA_GAS_FEE_AMOUNT_BEP3, tempList)
            
        }
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
                let htlcDetailVC = HtlcResultViewController(nibName: "HtlcResultViewController", bundle: nil)
                self.navigationItem.title = ""
                htlcDetailVC.mHtlcDenom = self.pageHolderVC.mHtlcDenom
                htlcDetailVC.mHtlcToSendAmount = self.pageHolderVC.mToSendAmount
                htlcDetailVC.mHtlcToChain = self.pageHolderVC.mHtlcToChain
                htlcDetailVC.mHtlcToAccount = self.pageHolderVC.mHtlcToAccount
                htlcDetailVC.mHtlcSendFee = self.pageHolderVC.mHtlcSendFee
                htlcDetailVC.mHtlcClaimFee = self.pageHolderVC.mHtlcClaimFee
                self.navigationController?.pushViewController(htlcDetailVC, animated: true)
            })
        } else {
            self.btnBack.isUserInteractionEnabled = true
            self.btnConfirm.isUserInteractionEnabled = true
        }
    }
}
