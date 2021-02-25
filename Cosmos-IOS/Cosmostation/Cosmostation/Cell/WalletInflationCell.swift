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
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST) {
            infaltionLabel.attributedText = WUtils.displayInflation(BaseData.instance.mInflation_V1?.inflation, font: infaltionLabel.font)
            yieldLabel.attributedText = WUtils.getDpEstApr(yieldLabel.font, chainType!)
        } else if (chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            infaltionLabel.attributedText = WUtils.displayInflation(BaseData.instance.mMintParam_V1?.inflation, font: infaltionLabel.font)
            yieldLabel.attributedText = WUtils.getDpEstApr(yieldLabel.font, chainType!)
            
        }
    }
    
}
