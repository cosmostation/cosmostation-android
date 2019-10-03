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
        
        (NSClassFromString("UICalloutBarButton")! as! UIButton.Type).appearance().backgroundColor = UIColor.white
        (NSClassFromString("UICalloutBarButton")! as! UIButton.Type).appearance().setTitleColor(UIColor.black, for: .normal)
        
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            memoCntLabel.text = "0/255 byte"
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            memoCntLabel.text = "0/100 byte"
        }
    }

    @IBAction func onClickBack(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if(isValiadMemoSize()) {
            if(memoInputTextView.text != nil && memoInputTextView.text.count > 0) {
                pageHolderVC.mMemo = memoInputTextView.text.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
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
        let byteArray = [UInt8](textView.text.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines).utf8)
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            memoCntLabel.text = String(byteArray.count) + "/255 byte"
            if (byteArray.count > 255) {
                self.memoInputTextView.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            } else {
                self.memoInputTextView.layer.borderColor = UIColor.white.cgColor
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            memoCntLabel.text = String(byteArray.count) + "/100 byte"
            if (byteArray.count > 100) {
                self.memoInputTextView.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            } else {
                self.memoInputTextView.layer.borderColor = UIColor.white.cgColor
            }
        }
    }
    
    func isValiadMemoSize() -> Bool {
        let byteArray = [UInt8](memoInputTextView.text.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines).utf8)
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (byteArray.count > 255) {
                return false
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (byteArray.count > 100) {
                return false
            }
        }
        
        return true
    }
}
