//
//  TokenHeader.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class CommonHeader: UIView {
    private let xibName = "CommonHeader"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.commonInit()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.commonInit()
    }
    
    override func prepareForInterfaceBuilder() {
        super.prepareForInterfaceBuilder()
        self.commonInit()
    }
    
    private func commonInit(){
        let view = Bundle.main.loadNibNamed(xibName, owner: self, options: nil)?.first as! UIView
        view.frame = self.bounds
        self.addSubview(view)
    }

    @IBOutlet weak var tokenHeaderTitle: UILabel!
}
