//
//  WalletCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit
import SwiftKeychainWrapper

class WalletCheckViewController: BaseViewController {

    var accountId: Int64?
    var mAccount: Account!
    var userChain: ChainType?
    
    @IBOutlet weak var cardView: CardView!
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
    
    var mnemonicLabels: [UILabel] = [UILabel]()
    var mnemonicWords: [String]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mnemonicLabels = [self.mnemonic0, self.mnemonic1, self.mnemonic2, self.mnemonic3,
                               self.mnemonic4, self.mnemonic5, self.mnemonic6, self.mnemonic7,
                               self.mnemonic8, self.mnemonic9, self.mnemonic10, self.mnemonic11,
                               self.mnemonic12, self.mnemonic13, self.mnemonic14, self.mnemonic15,
                               self.mnemonic16, self.mnemonic17, self.mnemonic18, self.mnemonic19,
                               self.mnemonic20, self.mnemonic21, self.mnemonic22, self.mnemonic23]
        
        mAccount = BaseData.instance.selectAccountById(id: accountId!)
        userChain = WUtils.getChainType(mAccount!.account_base_chain)
        
        cardView.backgroundColor = WUtils.getChainBg(userChain!)
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
            }
        }
    }
    @IBAction func onClickCopy(_ sender: Any) {
        var resource: String = ""
        for word in self.mnemonicWords! {
            resource = resource + " " + word
        }
        UIPasteboard.general.string = resource.trimmingCharacters(in: .whitespacesAndNewlines)
        onShowToast(NSLocalizedString("mnemonic_copied", comment: ""))
    }
    
    @IBAction func onClickOK(_ sender: Any) {
        self.onStartMainTab()
    }
    
    func onRetriveKey() {
        DispatchQueue.global().async {
            if let words: String = KeychainWrapper.standard.string(forKey: self.mAccount.account_uuid.sha1()) {
                self.mnemonicWords = words.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ")
            }
            
            DispatchQueue.main.async(execute: {
                self.updateView()
            });
        }
    }
}
