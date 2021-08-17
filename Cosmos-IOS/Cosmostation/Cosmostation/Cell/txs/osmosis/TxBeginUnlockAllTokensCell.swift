//
//  TxUnlockAllTokensCell.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/05.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class TxBeginUnlockAllTokensCell: TxCell {
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txOwenerLabel: UILabel!
    @IBOutlet weak var txUnlockCntLabel: UILabel!

    @IBOutlet weak var txUnlockLayer0: UIView!
    @IBOutlet weak var txId0Label: UILabel!
    @IBOutlet weak var txComplete0Label: UILabel!
    @IBOutlet weak var txUnlockDenom0Label: UILabel!
    @IBOutlet weak var txUnlockAmount0Label: UILabel!
    
    @IBOutlet weak var txUnlockLayer1: UIView!
    @IBOutlet weak var txId1Label: UILabel!
    @IBOutlet weak var txComplete1Label: UILabel!
    @IBOutlet weak var txUnlockDenom1Label: UILabel!
    @IBOutlet weak var txUnlockAmount1Label: UILabel!
    
    @IBOutlet weak var txUnlockLayer2: UIView!
    @IBOutlet weak var txId2Label: UILabel!
    @IBOutlet weak var txComplete2Label: UILabel!
    @IBOutlet weak var txUnlockDenom2Label: UILabel!
    @IBOutlet weak var txUnlockAmount2Label: UILabel!
    
    @IBOutlet weak var txUnlockLayer3: UIView!
    @IBOutlet weak var txId3Label: UILabel!
    @IBOutlet weak var txComplete3Label: UILabel!
    @IBOutlet weak var txUnlockDenom3Label: UILabel!
    @IBOutlet weak var txUnlockAmount3Label: UILabel!
    
    @IBOutlet weak var txUnlockLayer4: UIView!
    @IBOutlet weak var txId4Label: UILabel!
    @IBOutlet weak var txComplete4Label: UILabel!
    @IBOutlet weak var txUnlockDenom4Label: UILabel!
    @IBOutlet weak var txUnlockAmount4Label: UILabel!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        txUnlockAmount0Label.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txUnlockAmount1Label.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txUnlockAmount2Label.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txUnlockAmount3Label.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        txUnlockAmount4Label.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        
        txUnlockLayer0.isHidden = true
    }
    
    override func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Osmosis_Lockup_MsgBeginUnlockingAll.init(serializedData: response.tx.body.messages[position].value)
        
        txOwenerLabel.text = msg.owner
        txOwenerLabel.adjustsFontSizeToFitWidth = true
        
        var unlockCnt: Int = 0
        if response.txResponse.logs.count > position {
            response.txResponse.logs[position].events.forEach { event in
                if (event.type == "begin_unlock" && event.attributes.count > 0) {
                    unlockCnt = event.attributes.count / 4
                }
            }
        }
        txUnlockCntLabel.text = String(unlockCnt)
    }
}
