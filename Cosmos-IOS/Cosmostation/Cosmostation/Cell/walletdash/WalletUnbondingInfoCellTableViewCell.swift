//
//  WalletUnbondingInfoCellTableViewCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/02.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class WalletUnbondingInfoCellTableViewCell: UITableViewCell {
    
    @IBOutlet weak var unBondingCard: CardView!
    @IBOutlet weak var unBondingCnt: UILabel!
    
    @IBOutlet weak var unBondingLayer0: UIView!
    @IBOutlet weak var unBondingMoniker0: UILabel!
    @IBOutlet weak var unBondingAmount0: UILabel!
    @IBOutlet weak var unBondingTime0: UILabel!
    
    @IBOutlet weak var unBondingLayer1: UIView!
    @IBOutlet weak var unBondingMoniker1: UILabel!
    @IBOutlet weak var unBondingAmount1: UILabel!
    @IBOutlet weak var unBondingTime1: UILabel!
    
    @IBOutlet weak var unBondingLayer2: UIView!
    @IBOutlet weak var unBondingMoniker2: UILabel!
    @IBOutlet weak var unBondingAmount2: UILabel!
    @IBOutlet weak var unBondingTime2: UILabel!
    
    @IBOutlet weak var unBondingLayer3: UIView!
    @IBOutlet weak var unBondingMoniker3: UILabel!
    @IBOutlet weak var unBondingAmount3: UILabel!
    @IBOutlet weak var unBondingTime3: UILabel!
    
    @IBOutlet weak var unBondingLayer4: UIView!
    @IBOutlet weak var unBondingMoniker4: UILabel!
    @IBOutlet weak var unBondingAmount4: UILabel!
    @IBOutlet weak var unBondingTime4: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        unBondingAmount0.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount1.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount2.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount3.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        unBondingAmount4.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.unBondingLayer1.isHidden = true
        self.unBondingLayer2.isHidden = true
        self.unBondingLayer3.isHidden = true
        self.unBondingLayer4.isHidden = true
    }
}
