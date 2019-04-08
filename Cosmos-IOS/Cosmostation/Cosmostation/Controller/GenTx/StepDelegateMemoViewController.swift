//
//  StepDelegateMemoViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepDelegateMemoViewController: BaseViewController {

    @IBOutlet weak var memoInputTextView: MemoTextView!
    @IBOutlet weak var memoCntLabel: UILabel!
    
    var pageHolderVC: StepDelegateViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepDelegateViewController
        
        memoInputTextView.tintColor = UIColor.init(hexString: "FFFFFF")
//        self.view.isUserInteractionEnabled = false
    }

    @IBAction func onClickBack(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: Any) {
//        self.view.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
}
