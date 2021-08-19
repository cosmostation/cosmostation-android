//
//  DeleteStarname0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class DeleteStarname0ViewController: BaseViewController {

    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var toDeleteStarname: UILabel!
    @IBOutlet weak var expireDate: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        if (pageHolderVC.mType == IOV_MSG_TYPE_DELETE_DOMAIN) {
            toDeleteStarname.text = "*" + pageHolderVC.mStarnameDomain!
        } else if (pageHolderVC.mType == IOV_MSG_TYPE_DELETE_ACCOUNT) {
            toDeleteStarname.text = pageHolderVC.mStarnameAccount! + "*" + pageHolderVC.mStarnameDomain!
        }
        expireDate.text = WUtils.longTimetoString(pageHolderVC.mStarnameTime! * 1000)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
        
    }
}
