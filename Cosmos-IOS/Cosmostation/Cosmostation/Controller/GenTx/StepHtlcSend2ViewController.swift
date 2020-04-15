//
//  StepHtlcSend2ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend2ViewController: BaseViewController {
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var AmountInput: AmountInputTextField!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var availableDenom: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
    }
    
    @IBAction func onClickClear(_ sender: UIButton) {
    }
    
    @IBAction func onClickAdd01(_ sender: UIButton) {
    }
    
    @IBAction func onClickAdd1(_ sender: UIButton) {
    }
    
    @IBAction func onClickAdd10(_ sender: UIButton) {
    }
    
    @IBAction func onClickAdd100(_ sender: UIButton) {
    }
    
    @IBAction func onClickHalf(_ sender: UIButton) {
    }
    
    @IBAction func onClickMax(_ sender: UIButton) {
    }
}
