//
//  WalletCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper

class WalletCheckViewController: BaseViewController {

    var accountId: Int64?
    
    @IBOutlet weak var cardView: CardView!
    
    @IBOutlet weak var mneminicLayer0: UIView!
    @IBOutlet weak var mneminicLayer1: UIView!
    @IBOutlet weak var mneminicLayer2: UIView!
    @IBOutlet weak var mneminicLayer3: UIView!
    @IBOutlet weak var mneminicLayer4: UIView!
    @IBOutlet weak var mneminicLayer5: UIView!
    @IBOutlet weak var mneminicLayer6: UIView!
    @IBOutlet weak var mneminicLayer7: UIView!
    @IBOutlet weak var mneminicLayer8: UIView!
    @IBOutlet weak var mneminicLayer9: UIView!
    @IBOutlet weak var mneminicLayer10: UIView!
    @IBOutlet weak var mneminicLayer11: UIView!
    @IBOutlet weak var mneminicLayer12: UIView!
    @IBOutlet weak var mneminicLayer13: UIView!
    @IBOutlet weak var mneminicLayer14: UIView!
    @IBOutlet weak var mneminicLayer15: UIView!
    @IBOutlet weak var mneminicLayer16: UIView!
    @IBOutlet weak var mneminicLayer17: UIView!
    @IBOutlet weak var mneminicLayer18: UIView!
    @IBOutlet weak var mneminicLayer19: UIView!
    @IBOutlet weak var mneminicLayer20: UIView!
    @IBOutlet weak var mneminicLayer21: UIView!
    @IBOutlet weak var mneminicLayer22: UIView!
    @IBOutlet weak var mneminicLayer23: UIView!
    
    
    @IBOutlet weak var mnemonic0: UILabel!
    @IBOutlet weak var mnemonic1: UILabel!
    @IBOutlet weak var mnemonic2: UILabel!
    @IBOutlet weak var mnemonic3: UILabel!
    @IBOutlet weak var mnemonic4: UILabel!
    @IBOutlet weak var mnemonic5: UILabel!
    @IBOutlet weak var mnemonic6: UILabel!
    @IBOutlet weak var mnemonic7: UILabel!
    @IBOutlet weak var mnemonic8: UILabel!
    @IBOutlet weak var mnemonic9: UILabel!
    @IBOutlet weak var mnemonic10: UILabel!
    @IBOutlet weak var mnemonic11: UILabel!
    @IBOutlet weak var mnemonic12: UILabel!
    @IBOutlet weak var mnemonic13: UILabel!
    @IBOutlet weak var mnemonic14: UILabel!
    @IBOutlet weak var mnemonic15: UILabel!
    @IBOutlet weak var mnemonic16: UILabel!
    @IBOutlet weak var mnemonic17: UILabel!
    @IBOutlet weak var mnemonic18: UILabel!
    @IBOutlet weak var mnemonic19: UILabel!
    @IBOutlet weak var mnemonic20: UILabel!
    @IBOutlet weak var mnemonic21: UILabel!
    @IBOutlet weak var mnemonic22: UILabel!
    @IBOutlet weak var mnemonic23: UILabel!
    
    var mnemonicLayers: [UIView] = [UIView]()
    var mnemonicLabels: [UILabel] = [UILabel]()
    var mnemonicWords: [String]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mnemonicLayers = [self.mneminicLayer0, self.mneminicLayer1, self.mneminicLayer2, self.mneminicLayer3,
                               self.mneminicLayer4, self.mneminicLayer5, self.mneminicLayer6, self.mneminicLayer7,
                               self.mneminicLayer8, self.mneminicLayer9, self.mneminicLayer10, self.mneminicLayer11,
                               self.mneminicLayer12, self.mneminicLayer13, self.mneminicLayer14, self.mneminicLayer15,
                               self.mneminicLayer16, self.mneminicLayer17, self.mneminicLayer18, self.mneminicLayer19,
                               self.mneminicLayer20, self.mneminicLayer21, self.mneminicLayer22, self.mneminicLayer23]
        self.mnemonicLabels = [self.mnemonic0, self.mnemonic1, self.mnemonic2, self.mnemonic3,
                               self.mnemonic4, self.mnemonic5, self.mnemonic6, self.mnemonic7,
                               self.mnemonic8, self.mnemonic9, self.mnemonic10, self.mnemonic11,
                               self.mnemonic12, self.mnemonic13, self.mnemonic14, self.mnemonic15,
                               self.mnemonic16, self.mnemonic17, self.mnemonic18, self.mnemonic19,
                               self.mnemonic20, self.mnemonic21, self.mnemonic22, self.mnemonic23]
        
        account = BaseData.instance.selectAccountById(id: accountId!)
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        cardView.backgroundColor = WUtils.getChainBg(chainType!)
        onRetriveKey()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_check_mnemonics", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func updateView() {
        for i in 0 ..< self.mnemonicLabels.count {
            if(self.mnemonicWords!.count > i) {
                self.mnemonicLabels[i].text = self.mnemonicWords![i].replacingOccurrences(of: ",", with: "").replacingOccurrences(of: " ", with: "")
                self.mnemonicLabels[i].adjustsFontSizeToFitWidth = true
                self.mnemonicLayers[i].layer.borderWidth = 1
                self.mnemonicLayers[i].layer.cornerRadius = 4
                self.mnemonicLayers[i].layer.borderColor = WUtils.getChainDarkColor(chainType!).cgColor
            }
        }
    }
    @IBAction func onClickCopy(_ sender: Any) {
        self.onCopyAlert()
    }
    
    @IBAction func onClickOK(_ sender: Any) {
        self.onStartMainTab()
    }
    
    func onRetriveKey() {
        DispatchQueue.global().async {
            if let words: String = KeychainWrapper.standard.string(forKey: self.account!.account_uuid.sha1()) {
                self.mnemonicWords = words.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ")
            }
            
            DispatchQueue.main.async(execute: {
                self.updateView()
            });
        }
    }
    
    func onCopyAlert() {
        let copyAlert = UIAlertController(title: NSLocalizedString("str_safe_copy_title", comment: ""), message: NSLocalizedString("str_safe_copy_msg", comment: ""), preferredStyle: .alert)
        copyAlert.addAction(UIAlertAction(title: NSLocalizedString("str_raw_copy", comment: ""), style: .destructive, handler: { _ in
            var resource: String = ""
            for word in self.mnemonicWords! {
                resource = resource + " " + word
            }
            UIPasteboard.general.string = resource.trimmingCharacters(in: .whitespacesAndNewlines)
            self.onShowToast(NSLocalizedString("mnemonic_copied", comment: ""))
        }))
        copyAlert.addAction(UIAlertAction(title: NSLocalizedString("str_safe_copy", comment: ""), style: .default, handler: { _ in
            var resource: String = ""
            for word in self.mnemonicWords! {
                resource = resource + " " + word
            }
            KeychainWrapper.standard.set(resource, forKey: BaseData.instance.copySalt!, withAccessibility: .afterFirstUnlockThisDeviceOnly)
            self.onShowToast(NSLocalizedString("mnemonic_safe_copied", comment: ""))
            
        }))
        self.present(copyAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            copyAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
}
