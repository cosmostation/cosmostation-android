//
//  WalletPriceCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletPriceCell: UITableViewCell {
    
    @IBOutlet weak var priceLayer: UIView!
    @IBOutlet weak var perPrice: UILabel!
    @IBOutlet weak var sourceSite: UILabel!
    @IBOutlet weak var updownPercent: UILabel!
    @IBOutlet weak var updownImg: UIImageView!
    @IBOutlet weak var buySeparator: UIView!
    @IBOutlet weak var buyBtn: UIButton!
    
    @IBOutlet weak var noBuyConstraint: NSLayoutConstraint!
    @IBOutlet weak var buyConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        perPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        updownPercent.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_11_caption2)
        
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onTapPrice))
        self.contentView.isUserInteractionEnabled = true
        self.priceLayer.addGestureRecognizer(tap)
        
    }
    
    var actionTapPricel: (() -> Void)? = nil
    var actionBuy: (() -> Void)? = nil
    
    @objc func onTapPrice(sender:UITapGestureRecognizer) {
        actionTapPricel?()
    }
    
    @IBAction func onBuyCoin(_ sender: UIButton) {
        actionBuy?()
    }
    
    override func prepareForReuse() {
        noBuyConstraint.priority = .defaultHigh
        buyConstraint.priority = .defaultLow
        buySeparator.isHidden = true
        buyBtn.isHidden = true
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        sourceSite.text = "(CoinGecko)"
        perPrice.attributedText = WUtils.dpPerUserCurrencyValue(WUtils.getMainDenom(chainType), perPrice.font)
        updownPercent.attributedText = WUtils.dpValueChange(WUtils.getMainDenom(chainType), font: updownPercent.font)
        let changeValue = WUtils.valueChange(WUtils.getMainDenom(chainType))
        if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) { updownImg.image = UIImage(named: "priceUp") }
        else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) { updownImg.image = UIImage(named: "priceDown") }
        else { updownImg.image = nil }
        
        if (chainType == ChainType.COSMOS_MAIN) {
            buyBtn.setTitle(NSLocalizedString("buy_atom", comment: ""), for: .normal)
            buySeparator.isHidden = false
            buyBtn.isHidden = false
            buyConstraint.priority = .defaultHigh
            noBuyConstraint.priority = .defaultLow
            
        } else if (chainType == ChainType.BINANCE_MAIN) {
            buyBtn.setTitle(NSLocalizedString("buy_bnb", comment: ""), for: .normal)
            buySeparator.isHidden = false
            buyBtn.isHidden = false
            buyConstraint.priority = .defaultHigh
            noBuyConstraint.priority = .defaultLow
            
        } else if (chainType == ChainType.KAVA_MAIN) {
            buyBtn.setTitle(NSLocalizedString("buy_kava", comment: ""), for: .normal)
            buySeparator.isHidden = false
            buyBtn.isHidden = false
            buyConstraint.priority = .defaultHigh
            noBuyConstraint.priority = .defaultLow
            
        } else {
            buySeparator.isHidden = true
            buyBtn.isHidden = true
            buyConstraint.priority = .defaultLow
            noBuyConstraint.priority = .defaultHigh
        }
    }
}
