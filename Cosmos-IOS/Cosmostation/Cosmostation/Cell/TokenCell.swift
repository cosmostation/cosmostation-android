//
//  TokenCell.swift
//  Cosmostation
//
//  Created by yongjoo on 30/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class TokenCell: UITableViewCell {
    
    @IBOutlet weak var tokenImg: UIImageView!
    @IBOutlet weak var tokenSymbol: UILabel!
    @IBOutlet weak var tokenTitle: UILabel!
    @IBOutlet weak var tokenDescription: UILabel!
    @IBOutlet weak var tokenAmount: UILabel!
    @IBOutlet weak var tokenValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        tokenAmount.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: Font_13_footnote)
        tokenValue.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_13_footnote)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.tokenImg.image = UIImage(named: "tokenIc")
        self.tokenSymbol.text = "-"
        self.tokenTitle.text = "-"
        self.tokenDescription.text = "-"
        self.tokenAmount.text = "-"
        self.tokenValue.text = "-"
    }
    
}
