//
//  StepHtlcSend0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend0ViewController: BaseViewController {
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var fromChainImg: UIImageView!
    @IBOutlet weak var fromChainTxt: UILabel!
    @IBOutlet weak var toChainCard: CardView!
    @IBOutlet weak var toChainImg: UIImageView!
    @IBOutlet weak var toChainText: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.toChainCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToChain (_:))))
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        
    }
    
    @objc func onClickToChain (_ sender: UITapGestureRecognizer) {
        print("onClickToChain")
    }
    
}
