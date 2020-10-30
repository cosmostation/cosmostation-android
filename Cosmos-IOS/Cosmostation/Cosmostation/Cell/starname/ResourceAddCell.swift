//
//  ResourceAddCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ResourceAddCell: UITableViewCell {

    @IBOutlet weak var addImg: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        addImg.image = addImg.image?.withRenderingMode(.alwaysTemplate)
        addImg.tintColor = COLOR_PHOTON
    }
    
}
