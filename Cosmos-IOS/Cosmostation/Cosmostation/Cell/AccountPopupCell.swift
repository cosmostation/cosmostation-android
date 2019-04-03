//
//  AccountPopupCell.swift
//  Cosmostation
//
//  Created by yongjoo on 02/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import DropDown

class AccountPopupCell: DropDownCell {
    
    @IBOutlet weak var topPadding: UIView!
//    @IBOutlet weak var selectedAccount: UIView!
    @IBOutlet weak var accountView: UIView!
    @IBOutlet weak var newAccount: UIView!
    
    
//    @IBOutlet weak var selectedChainImg: UIImageView!
//    @IBOutlet weak var slectedKeystate: UIImageView!
//    @IBOutlet weak var selectedName: UILabel!
//    @IBOutlet weak var selectedChainName: UILabel!
//    @IBOutlet weak var selectedAddress: UILabel!
    
    @IBOutlet weak var cardview: CardView!
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var keystate: UIImageView!
    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var chainName: UILabel!
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var addImg: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        addImg.image = addImg.image?.withRenderingMode(.alwaysTemplate)
        addImg.tintColor = UIColor.init(hexString: "#05D2DD")
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
//        super.setSelected(selected, animated: animated)
    }
    
}
