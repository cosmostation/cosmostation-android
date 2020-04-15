//
//  StepHtlcSend3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend3ViewController: BaseViewController {
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    @IBOutlet weak var sendImg: UIImageView!
    @IBOutlet weak var sendAmountLabel: UILabel!
    @IBOutlet weak var sendAmountDenom: UILabel!
    @IBOutlet weak var sendFeeLabel: UILabel!
    @IBOutlet weak var sendFeeDenom: UILabel!
    @IBOutlet weak var recipientChainLabel: UILabel!
    @IBOutlet weak var recipientAddressLabel: UILabel!
    
    @IBOutlet weak var claimImg: UIImageView!
    @IBOutlet weak var claimFeeLabel: UILabel!
    @IBOutlet weak var claimFeeDenom: UILabel!
    @IBOutlet weak var claimAddress: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        
    }
    
}
