//
//  AmountInputTextField.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AmountInputTextField: UITextField {
    let border = CALayer()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.layer.borderWidth = 1.0
        self.layer.borderColor = UIColor.white.cgColor
        self.backgroundColor = UIColor.clear
//        self.setLeftPaddingPoints(60)
//        self.setRightPaddingPoints(12)
        self.setLeftPaddingPoints(8)
        self.setRightPaddingPoints(60)
        
//        self.attributedPlaceholder = NSAttributedString(string: NSLocalizedString("add_address_hint", comment: ""), attributes: [NSAttributedString.Key.foregroundColor: UIColor.gray])
    }

    open override func canPerformAction(_ action: Selector, withSender sender: Any?) -> Bool {
//        return action == #selector(UIResponderStandardEditActions.cut) ||
//            action == #selector(UIResponderStandardEditActions.copy) ||
//            action == #selector(UIResponderStandardEditActions.paste) ||
//            action == #selector(UIResponderStandardEditActions.select) ||
//            action == #selector(UIResponderStandardEditActions.selectAll) ||
//            action == #selector(UIResponderStandardEditActions.delete)
        return false
    }
    
//    private func getKeyboardLanguage() -> String? {
//        return "en" // here you can choose keyboard any way you need
//    }
//    
//    override var textInputMode: UITextInputMode? {
//        if let language = getKeyboardLanguage() {
//            for tim in UITextInputMode.activeInputModes {
//                if tim.primaryLanguage!.contains(language) {
//                    return tim
//                }
//            }
//        }
//        return super.textInputMode
//    }
}
