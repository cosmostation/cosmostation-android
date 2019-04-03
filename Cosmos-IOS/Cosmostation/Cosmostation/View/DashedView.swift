//
//  DashedView.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit


class DashedView: UIView {
    
    var dashedLine: CAShapeLayer!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    func setup() {
        dashedLine             = CAShapeLayer()
        dashedLine.fillColor   = UIColor.clear.cgColor
        dashedLine.strokeColor = UIColor.white.cgColor
        dashedLine.lineWidth   = 1
        dashedLine.lineDashPattern = [2,2]
        layer.addSublayer(dashedLine)
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        layer.cornerRadius = 8
        dashedLine.path = UIBezierPath(roundedRect: self.bounds, cornerRadius:8).cgPath
        dashedLine.frame = self.bounds
    }
    
}
