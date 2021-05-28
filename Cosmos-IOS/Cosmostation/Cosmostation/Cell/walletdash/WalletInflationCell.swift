//
//  WalletInflationCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletInflationCell: UITableViewCell {
    
    @IBOutlet weak var infaltionLabel: UILabel!
    @IBOutlet weak var yieldLabel: UILabel!
    @IBOutlet weak var aprCard: CardView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        infaltionLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        yieldLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onTapApr))
        self.contentView.isUserInteractionEnabled = true
        self.aprCard.addGestureRecognizer(tap)
    }
    
    
    var actionTapApr: (() -> Void)? = nil
    @objc func onTapApr(sender:UITapGestureRecognizer) {
        actionTapApr?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.AKASH_MAIN || chainType == ChainType.PERSIS_MAIN ||
                chainType == ChainType.CRYPTO_MAIN || chainType == ChainType.SENTINEL_MAIN || chainType == ChainType.COSMOS_TEST || chainType == ChainType.RIZON_TEST) {
            infaltionLabel.attributedText = WUtils.displayInflation(BaseData.instance.mInflation_gRPC?.stringValue, font: infaltionLabel.font)
            yieldLabel.attributedText = WUtils.getDpEstApr(yieldLabel.font, chainType!)
            
        } else if (chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            let irisInflation = NSDecimalNumber.init(string: BaseData.instance.mIrisMintParam_gRPC?.inflation).multiplying(byPowerOf10: -18, withBehavior: WUtils.handler2)
            infaltionLabel.attributedText = WUtils.displayInflation(irisInflation.stringValue , font: infaltionLabel.font)
            yieldLabel.attributedText = WUtils.getDpEstApr(yieldLabel.font, chainType!)
            
        } else {
            infaltionLabel.attributedText = WUtils.displayInflation(BaseData.instance.mInflation, font: infaltionLabel.font)
            yieldLabel.attributedText = WUtils.getDpEstApr(yieldLabel.font, chainType!)
        }
    }
    
}
