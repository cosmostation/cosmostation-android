//
//  LoadingImageView.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class LoadingImageView: UIImageView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    func setup() {
        var imagesName = ["loading_1", "loading_2", "loading_3", "loading_4", "loading_5",
                          "loading_6", "loading_7", "loading_8", "loading_9", "loading_10",
                          "loading_11", "loading_12", "loading_13", "loading_14", "loading_15",
                          "loading_16", "loading_17", "loading_18", "loading_19", "loading_20",
                          "loading_21", "loading_22", "loading_23", "loading_24", "loading_25",
                          "loading_26"]
        var images = [UIImage]()
        for i in 0..<imagesName.count {
            images.append(UIImage(named: imagesName[i])!)
        }
        self.animationImages = images
//        self.animationDuration = 0.5
        self.animationDuration = 1
    }
    
    func onStartAnimation() {
        self.startAnimating()
    }
    
    func onStopAnimation() {
        self.stopAnimating()
    }
}
