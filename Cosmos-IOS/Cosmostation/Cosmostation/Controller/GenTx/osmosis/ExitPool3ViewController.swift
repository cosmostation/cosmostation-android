//
//  ExitPool3ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/19.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class ExitPool3ViewController: BaseViewController {
    
    @IBOutlet weak var txFeeAmountLabel: UILabel!
    @IBOutlet weak var txFeeDenomLabel: UILabel!
    @IBOutlet weak var lpAmountLabel: UILabel!
    @IBOutlet weak var lpDenomLabel: UILabel!
    @IBOutlet weak var withdraw0AmountLabel: UILabel!
    @IBOutlet weak var withdraw0DenomLabel: UILabel!
    @IBOutlet weak var withdraw1AmountLabel: UILabel!
    @IBOutlet weak var withdraw1DenomLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    
    
    
    @IBAction func onClickBack(_ sender: UIButton) {
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
    }
    

}
