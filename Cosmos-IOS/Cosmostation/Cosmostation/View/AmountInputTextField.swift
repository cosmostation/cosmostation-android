//
//  AmountInputTextField.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AmountInputTextField: UITextField {
    let border = CALayer()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.layer.borderWidth = 1.0
        self.layer.borderColor = UIColor.white.cgColor
        self.backgroundColor = UIColor.clear
        self.setLeftPaddingPoints(60)
        self.setRightPaddingPoints(12)
        
//        self.attributedPlaceholder = NSAttributedString(string: NSLocalizedString("add_address_hint", comment: ""), attributes: [NSAttributedString.Key.foregroundColor: UIColor.gray])
    }

}
