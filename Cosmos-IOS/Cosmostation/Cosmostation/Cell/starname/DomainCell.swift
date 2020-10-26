//
//  DomainCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class DomainCell: UITableViewCell {
    
    @IBOutlet weak var starNameLabel: UILabel!
    @IBOutlet weak var domainTypeLabel: UILabel!
    @IBOutlet weak var domainResourcesLabel: UILabel!
    @IBOutlet weak var domainExpireTime: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        domainTypeLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        domainResourcesLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        domainExpireTime.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }
    
}
