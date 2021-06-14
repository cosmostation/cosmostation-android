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
        guard let param = BaseData.instance.mParam else {
            return
        }
        infaltionLabel.attributedText = WUtils.displayPercent(param.getDpInflation(chainType), infaltionLabel.font)
        yieldLabel.attributedText = WUtils.displayPercent(param.getDpApr(chainType), yieldLabel.font)
        
    }
    
}
