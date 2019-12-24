//
//  NotificationView.swift
//  Cosmostation
//
//  Created by yongjoo on 24/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class NotificationView: UIView {

    @IBOutlet var contentView: UIView!
    @IBOutlet weak var notiType: UIImageView!
    @IBOutlet weak var notiTitle: UILabel!
    @IBOutlet weak var notiMsg: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    private func commonInit() {
        Bundle.main.loadNibNamed("NotificationView", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }

    var actionDismiss: (() -> Void)? = nil
    var actionBody: (() -> Void)? = nil
    @IBAction func onTouchDismiss(_ sender: UIButton) {
        actionDismiss?()
    }
    @IBAction func onTouchBody(_ sender: UIButton) {
        actionBody?()
    }
}
