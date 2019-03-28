//
//  BottomLineTextField.swift
//  Cosmostation
//
//  Created by yongjoo on 28/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
@IBDesignable
class BottomLineTextField: UITextField {
    
//    override var tintColor: UIColor! {
//
//        didSet {
//            setNeedsDisplay()
//        }
//    }
//
//    override func draw(_ rect: CGRect) {
//
//        let startingPoint   = CGPoint(x: rect.minX, y: rect.maxY)
//        let endingPoint     = CGPoint(x: rect.maxX, y: rect.maxY)
//
//        let path = UIBezierPath()
//
//        path.move(to: startingPoint)
//        path.addLine(to: endingPoint)
//        path.lineWidth = 2.0
//
//        if self.isFocused {
//            UIColor.gray.setStroke()
//        } else {
//            UIColor.clear.setStroke()
//        }
//
//
//
//        path.stroke()
//    }
    var bottomBorder = UIView()
    
    override func awakeFromNib() {
        
        // Setup Bottom-Border
//        self.layer.backgroundColor = UIColor.red.cgColor
        
        self.translatesAutoresizingMaskIntoConstraints = false
        
        bottomBorder = UIView.init(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
//        bottomBorder.backgroundColor = UIColor(hexString: "#7A7F88")
        bottomBorder.backgroundColor = UIColor.clear
        bottomBorder.translatesAutoresizingMaskIntoConstraints = false
        
        addSubview(bottomBorder)
        
        bottomBorder.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
        bottomBorder.leftAnchor.constraint(equalTo: leftAnchor).isActive = true
        bottomBorder.rightAnchor.constraint(equalTo: rightAnchor).isActive = true
        bottomBorder.heightAnchor.constraint(equalToConstant: 1).isActive = true // Set Border-Strength
        
    }
    
    @IBInspectable var hasFocused: Bool = true {
        didSet {
            if (hasFocused) {
                bottomBorder.backgroundColor = UIColor(hexString: "#7A7F88")
                
            } else {
                bottomBorder.backgroundColor = UIColor.clear
                
            }
            
        }
    }
}
