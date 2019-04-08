//
//  MemoTextView.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MemoTextView: UITextView {

    let border = CALayer()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.layer.borderWidth = 1.0
        self.layer.borderColor = UIColor.white.cgColor
        self.backgroundColor = UIColor.clear
        self.tintColor = UIColor.white
    }
}
