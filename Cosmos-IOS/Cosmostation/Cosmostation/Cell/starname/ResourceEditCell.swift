//
//  ResourceEditCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ResourceEditCell: UITableViewCell {
    
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var chainName: UILabel!
    @IBOutlet weak var chainAddress: UILabel!
    @IBOutlet weak var btnRemove: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionRemove: (() -> Void)? = nil
    @IBAction func onClickRemove(_ sender: Any) {
        actionRemove?()
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.btnRemove.isHidden = false
    }
}
