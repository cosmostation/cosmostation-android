//
//  StepDelegateMemoViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

//class StepDelegateMemoViewController: BaseViewController, UITextViewDelegate {
class StepMemoViewController: BaseViewController, UITextViewDelegate {

    @IBOutlet weak var memoInputTextView: MemoTextView!
    @IBOutlet weak var memoCntLabel: UILabel!
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        memoInputTextView.tintColor = UIColor.init(hexString: "FFFFFF")
        memoInputTextView.delegate = self
    }

    @IBAction func onClickBack(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if(isValiadAmount()) {
            if(memoInputTextView.text != nil && memoInputTextView.text.count > 0) {
                pageHolderVC.mMemo = memoInputTextView.text
            } else {
                pageHolderVC.mMemo = ""
            }
            
            self.beforeBtn.isUserInteractionEnabled = false
            self.nextBtn.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        } else {
            self.onShowToast(NSLocalizedString("error_memo", comment: ""))
        }
        
    }
    
    override func enableUserInteraction() {
        self.beforeBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    func textViewDidChange(_ textView: UITextView) {
        print("textViewDidChange ", textView.text)
        let byteArray = [UInt8](textView.text.utf8)
        memoCntLabel.text = String(byteArray.count) + "/255"
        if (byteArray.count > 255) {
            self.memoInputTextView.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
        } else {
            self.memoInputTextView.layer.borderColor = UIColor.white.cgColor
        }
    }
    
    func isValiadAmount() -> Bool {
        let byteArray = [UInt8](memoInputTextView.text.utf8)
        if (byteArray.count > 255) { return false }
        return true
    }
}
