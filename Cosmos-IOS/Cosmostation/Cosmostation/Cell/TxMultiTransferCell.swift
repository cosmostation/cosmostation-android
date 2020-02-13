//
//  TxMultiTransferCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/02/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class TxMultiTransferCell: UITableViewCell {
    
    @IBOutlet weak var txIcon: UIImageView!
    @IBOutlet weak var txTitleLabel: UILabel!
    
    @IBOutlet weak var sendLayer0: UIView!
    @IBOutlet weak var sendAddress0: UILabel!
    @IBOutlet weak var sendAmount0: UILabel!
    @IBOutlet weak var sendDenom0: UILabel!
    @IBOutlet weak var sendLayer1: UIView!
    @IBOutlet weak var sendAddress1: UILabel!
    @IBOutlet weak var sendAmount1: UILabel!
    @IBOutlet weak var sendDenom1: UILabel!
    @IBOutlet weak var sendLayer2: UIView!
    @IBOutlet weak var sendAddress2: UILabel!
    @IBOutlet weak var sendAmount2: UILabel!
    @IBOutlet weak var sendDenom2: UILabel!
    @IBOutlet weak var sendLayer3: UIView!
    @IBOutlet weak var sendAddress3: UILabel!
    @IBOutlet weak var sendAmount3: UILabel!
    @IBOutlet weak var sendDenom3: UILabel!
    @IBOutlet weak var sendLayer4: UIView!
    @IBOutlet weak var sendAddress4: UILabel!
    @IBOutlet weak var sendAmount4: UILabel!
    @IBOutlet weak var sendDenom4: UILabel!
    
    @IBOutlet weak var receiveLayer0: UIView!
    @IBOutlet weak var receiveAddress0: UILabel!
    @IBOutlet weak var receiveAmount0: UILabel!
    @IBOutlet weak var receiveDenom0: UILabel!
    @IBOutlet weak var receiveLayer1: UIView!
    @IBOutlet weak var receiveAddress1: UILabel!
    @IBOutlet weak var receiveAmount1: UILabel!
    @IBOutlet weak var receiveDenom1: UILabel!
    @IBOutlet weak var receiveLayer2: UIView!
    @IBOutlet weak var receiveAddress2: UILabel!
    @IBOutlet weak var receiveAmount2: UILabel!
    @IBOutlet weak var receiveDenom2: UILabel!
    @IBOutlet weak var receiveLayer3: UIView!
    @IBOutlet weak var receiveAddress3: UILabel!
    @IBOutlet weak var receiveAmount3: UILabel!
    @IBOutlet weak var receiveDenom3: UILabel!
    @IBOutlet weak var receiveLayer4: UIView!
    @IBOutlet weak var receiveAddress4: UILabel!
    @IBOutlet weak var receiveAmount4: UILabel!
    @IBOutlet weak var receiveDenom4: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        sendAmount0.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        sendAmount1.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        sendAmount2.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        sendAmount3.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        sendAmount4.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        receiveAmount0.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        receiveAmount1.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        receiveAmount2.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        receiveAmount3.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
        receiveAmount4.font = UIFontMetrics(forTextStyle: .caption2).scaledFont(for: Font_12_caption1)
    }
    
}
