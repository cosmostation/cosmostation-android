//
//  ManageAccountAddCell.swift
//  Cosmostation
//
//  Created by yongjoo on 21/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class ManageAccountAddCell: UITableViewCell {

    @IBOutlet weak var addImg: UIImageView!
    @IBOutlet weak var dashView: DashedView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        addImg.image = addImg.image?.withRenderingMode(.alwaysTemplate)
        addImg.tintColor = COLOR_PHOTON
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
