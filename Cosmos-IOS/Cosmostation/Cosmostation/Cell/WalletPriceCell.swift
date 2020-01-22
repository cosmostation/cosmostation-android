//
//  WalletPriceCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletPriceCell: UITableViewCell {
    
    @IBOutlet weak var perPrice: UILabel!
    @IBOutlet weak var sourceSite: UILabel!
    @IBOutlet weak var updownPercent: UILabel!
    @IBOutlet weak var updownImg: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        perPrice.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        updownPercent.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_11_caption2)
        
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onTapPrice))
        self.contentView.isUserInteractionEnabled = true
        self.contentView.addGestureRecognizer(tap)
        
    }
    
    var actionTapPricel: (() -> Void)? = nil
    @objc func onTapPrice(sender:UITapGestureRecognizer) {
        actionTapPricel?()
    }
}
