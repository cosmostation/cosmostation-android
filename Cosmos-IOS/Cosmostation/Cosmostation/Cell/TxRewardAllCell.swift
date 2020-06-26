//
//  TxRewardAllCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/23.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxRewardAllCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var delegatorLabel: UILabel!
    @IBOutlet weak var validatorCnt: UILabel!
    
    @IBOutlet weak var validatorLabel0: UILabel!
    @IBOutlet weak var monikerLabel0: UILabel!
    
    @IBOutlet weak var validatorLayer1: UIView!
    @IBOutlet weak var validatorLabel1: UILabel!
    @IBOutlet weak var monikerLabel1: UILabel!
    
    @IBOutlet weak var validatorLayer2: UIView!
    @IBOutlet weak var validatorLabel2: UILabel!
    @IBOutlet weak var monikerLabel2: UILabel!
    
    @IBOutlet weak var validatorLayer3: UIView!
    @IBOutlet weak var validatorLabel3: UILabel!
    @IBOutlet weak var monikerLabel3: UILabel!
    
    @IBOutlet weak var validatorLayer4: UIView!
    @IBOutlet weak var validatorLabel4: UILabel!
    @IBOutlet weak var monikerLabel4: UILabel!
    
    @IBOutlet weak var validatorLayer5: UIView!
    @IBOutlet weak var validatorLabel5: UILabel!
    @IBOutlet weak var monikerLabel5: UILabel!
    
    @IBOutlet weak var validatorLayer6: UIView!
    @IBOutlet weak var validatorLabel6: UILabel!
    @IBOutlet weak var monikerLabel6: UILabel!
    
    @IBOutlet weak var validatorLayer7: UIView!
    @IBOutlet weak var validatorLabel7: UILabel!
    @IBOutlet weak var monikerLabel7: UILabel!
    
    @IBOutlet weak var amountLabel: UILabel!
    @IBOutlet weak var amountDenomLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        amountLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    func setDenomType(_ chainType:ChainType) {
        WUtils.setDenomTitle(chainType, amountDenomLabel)
    }
    
}
