//
//  TxTransferCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxTransferCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTitleLabel: UILabel!
    @IBOutlet weak var fromLabel: UILabel!
    @IBOutlet weak var toLabel: UILabel!
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountDenomLabel: UILabel!

    @IBOutlet weak var multiAmountStack: UIStackView!
    @IBOutlet weak var multiAmountLayer0: UIView!
    @IBOutlet weak var multiAmountDenom0: UILabel!
    @IBOutlet weak var multiAmount0: UILabel!
    @IBOutlet weak var multiAmountLayer1: UIView!
    @IBOutlet weak var multiAmountDenom1: UILabel!
    @IBOutlet weak var multiAmount1: UILabel!
    @IBOutlet weak var multiAmountLayer2: UIView!
    @IBOutlet weak var multiAmountDenom2: UILabel!
    @IBOutlet weak var multiAmount2: UILabel!
    @IBOutlet weak var multiAmountLayer3: UIView!
    @IBOutlet weak var multiAmountDenom3: UILabel!
    @IBOutlet weak var multiAmount3: UILabel!
    @IBOutlet weak var multiAmountLayer4: UIView!
    @IBOutlet weak var multiAmountDenom4: UILabel!
    @IBOutlet weak var multiAmount4: UILabel!
    
    @IBOutlet weak var singleAmountConstraint: NSLayoutConstraint!
    @IBOutlet weak var multiAmountConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        amountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        
        multiAmount0.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount1.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount2.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount3.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount4.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, amountDenomLabel)
    }
    
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.multiAmountStack.isHidden = true
        self.multiAmountLayer0.isHidden = true
        self.multiAmountLayer1.isHidden = true
        self.multiAmountLayer2.isHidden = true
        self.multiAmountLayer3.isHidden = true
        self.multiAmountLayer4.isHidden = true
        
    }
}
