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
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        
        multiAmount0.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount1.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount2.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount3.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        multiAmount4.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.multiAmountStack.isHidden = false
        self.multiAmountLayer0.isHidden = true
        self.multiAmountLayer1.isHidden = true
        self.multiAmountLayer2.isHidden = true
        self.multiAmountLayer3.isHidden = true
        self.multiAmountLayer4.isHidden = true
        
    }
    
    func onBindMsg(_ chain: ChainType, _ response: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int, _ myAddress: String) {
        txIcon.image = txIcon.image?.withRenderingMode(.alwaysTemplate)
        txIcon.tintColor = WUtils.getChainColor(chain)
        
        let msg = try! Cosmos_Bank_V1beta1_MsgSend.init(serializedData: response.tx.body.messages[position].value)
        fromLabel.text = msg.fromAddress
        toLabel.text = msg.toAddress
        fromLabel.adjustsFontSizeToFitWidth = true
        toLabel.adjustsFontSizeToFitWidth = true
        
        if (myAddress == msg.fromAddress) {
            txTitleLabel.text = NSLocalizedString("tx_send", comment: "")
        }
        if (myAddress == msg.toAddress) {
            txTitleLabel.text = NSLocalizedString("tx_receive", comment: "")
        }
        var coins = Array<Coin>()
        for coin in msg.amount {
            coins.append(Coin.init(coin.denom, coin.amount))
        }
//        print("coins size", coins.count)
        multiAmountLayer0.isHidden = false
        WUtils.showCoinDp(coins[0], multiAmountDenom0, multiAmount0, chain)
        if (coins.count > 1) {
            multiAmountLayer1.isHidden = false
            WUtils.showCoinDp(coins[1], multiAmountDenom1, multiAmount1, chain)
        }
        if (coins.count > 2) {
            multiAmountLayer2.isHidden = false
            WUtils.showCoinDp(coins[2], multiAmountDenom2, multiAmount2, chain)
        }
        if (coins.count > 3) {
            multiAmountLayer3.isHidden = false
            WUtils.showCoinDp(coins[3], multiAmountDenom3, multiAmount3, chain)
        }
        if (coins.count > 4) {
            multiAmountLayer4.isHidden = false
            WUtils.showCoinDp(coins[4], multiAmountDenom4, multiAmount4, chain)
        }
        
    }
}
