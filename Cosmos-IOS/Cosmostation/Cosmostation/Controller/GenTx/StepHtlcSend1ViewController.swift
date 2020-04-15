//
//  StepHtlcSend1ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend1ViewController: BaseViewController {
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var toAddressCard: CardView!
    @IBOutlet weak var toAddressImg: UIImageView!
    @IBOutlet weak var toAddressTxt: UILabel!
    @IBOutlet weak var warnMsg: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.toAddressCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToAddress)))

    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }

    @IBAction func onClickBack(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        
    }
    
    @objc func onClickToAddress (_ sender: UITapGestureRecognizer) {
        print("onClickToAddress")
    }
}
