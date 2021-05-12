//
//  TokenDetailNativeCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TokenDetailNativeCell: TokenDetailCell {
    
    @IBOutlet weak var tokenImg: UIImageView!
    @IBOutlet weak var tokenSymbol: UILabel!
    @IBOutlet weak var tokenDenom: UILabel!
    
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var lockedAmount: UILabel!
    @IBOutlet weak var fronzenAmount: UILabel!
    @IBOutlet weak var vestingAmount: UILabel!
    
    @IBOutlet weak var lockedLayer: UIView!
    @IBOutlet weak var frozenLayer: UIView!
    @IBOutlet weak var vestingLayer: UIView!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        availableAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        lockedAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        fronzenAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        vestingAmount.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    override func prepareForReuse() {
        lockedLayer.isHidden = true
        frozenLayer.isHidden = true
        vestingLayer.isHidden = true
    }
    
    func onBindNativeToken(_ chainType: ChainType?, _ denom: String?) {
        if (WUtils.isGRPC(chainType)) {
            return
        }
        
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            onBindBNBTokens(denom)
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            onBindKavaTokens(denom)
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            onBindOKTokens(denom)
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            onBindSifTokens(denom)
            
        }
    }
    
    func onBindBNBTokens(_ denom: String?) {
        let balance = BaseData.instance.getBalance(denom)
        let bnbToken = WUtils.getBnbToken(denom)
        if (balance != nil && bnbToken != nil) {
            frozenLayer.isHidden = false
            lockedLayer.isHidden = false
            tokenImg.af_setImage(withURL: URL(string: TOKEN_IMG_URL + bnbToken!.original_symbol + ".png")!)
            tokenSymbol.text = bnbToken!.original_symbol.uppercased()
            tokenDenom.text = "(" + denom! + ")"
            
            let available = BaseData.instance.availableAmount(denom!)
            let locked = BaseData.instance.lockedAmount(denom!)
            let frozen = BaseData.instance.frozenAmount(denom!)
            let total = available.adding(locked).adding(frozen)
            let convertAmount = WUtils.getBnbConvertAmount(denom, total)
            
            totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 0, 8)
            availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 0, 8)
            lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, availableAmount.font, 0, 8)
            fronzenAmount.attributedText = WUtils.displayAmount2(frozen.stringValue, availableAmount.font, 0, 8)
            totalValue.attributedText = WUtils.dpTokenValue(convertAmount, BaseData.instance.getLastPrice(), 0, totalValue.font)
        }
    }
    
    func onBindKavaTokens(_ denom: String?) {
        tokenImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + denom! + ".png")!)
        tokenSymbol.text = denom!.uppercased()
        tokenDenom.text = "(" + denom! + ")"
        
        let dpDecimal = WUtils.getKavaCoinDecimal(denom!)
        let available = BaseData.instance.availableAmount(denom!)
        let convertedDollorValue = WUtils.getKavaTokenDollorValue(denom!, available)
        let convertedKavaAmount = convertedDollorValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler6)
        
        totalAmount.attributedText = WUtils.displayAmount2(available.stringValue, totalAmount.font, dpDecimal, dpDecimal)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, dpDecimal, dpDecimal)
        totalValue.attributedText = WUtils.dpTokenValue(convertedKavaAmount.multiplying(byPowerOf10: 6), BaseData.instance.getLastPrice(), 6, totalValue.font)
    }
    
    func onBindOKTokens(_ denom: String?) {
        let balance = BaseData.instance.getBalance(denom)
        let okToken = WUtils.getOkToken(denom)
        if (balance != nil && okToken != nil) {
            lockedLayer.isHidden = false
            tokenImg.af_setImage(withURL: URL(string: OKEX_COIN_IMG_URL + okToken!.original_symbol! + ".png")!)
            tokenSymbol.text = okToken!.original_symbol!.uppercased()
            tokenDenom.text = "(" + denom! + ")"
            
            let available = BaseData.instance.availableAmount(denom!)
            let locked = BaseData.instance.lockedAmount(denom!)
            let total = available.adding(locked)
            let totalTokenValue = WUtils.getOkexTokenDollorValue(okToken, total)
            let convertedOKTAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler6)
            
            totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, totalAmount.font, 0, 18)
            availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, 0, 18)
            lockedAmount.attributedText = WUtils.displayAmount2(locked.stringValue, availableAmount.font, 0, 18)
            totalValue.attributedText = WUtils.dpTokenValue(convertedOKTAmount, BaseData.instance.getLastPrice(), 0, totalValue.font)
            
        }
    }
    
    func onBindSifTokens(_ denom: String?) {
        tokenImg.af_setImage(withURL: URL(string: SIF_COIN_IMG_URL + denom! + ".png")!)
        tokenSymbol.text = denom!.substring(from: 1).uppercased()
        tokenDenom.text = "(" + denom! + ")"
        
        let dpDecimal = WUtils.getSifCoinDecimal(denom!)
        let available = BaseData.instance.availableAmount(denom!)
        totalAmount.attributedText = WUtils.displayAmount2(available.stringValue, totalAmount.font, dpDecimal, dpDecimal)
        availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, availableAmount.font, dpDecimal, dpDecimal)
        totalValue.attributedText = WUtils.dpTokenValue(NSDecimalNumber.zero, BaseData.instance.getLastPrice(), dpDecimal, totalValue.font)
    }
}
