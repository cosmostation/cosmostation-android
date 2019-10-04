//
//  WalletConnectImageView.swift
//  Cosmostation
//
//  Created by yongjoo on 05/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletConnectImageView: UIImageView {

    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    func setup() {
        var imagesName = ["connectImg1", "connectImg2", "connectImg3", "connectImg4", "connectImg5",
                          "connectImg6", "connectImg7", "connectImg8"]
        var images = [UIImage]()
        for i in 0..<imagesName.count {
            images.append(UIImage(named: imagesName[i])!)
        }
        self.animationImages = images
        self.animationDuration = 1
    }
    
    func onStartAnimation() {
        self.startAnimating()
    }
    
    func onStopAnimation() {
        self.stopAnimating()
    }

}
