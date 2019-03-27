//
//  CreateViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit

class CreateViewController: BaseViewController {

    @IBOutlet weak var nextBtn: UIButton!
    @IBOutlet weak var warningMsgLabel: UILabel!
    
    @IBOutlet weak var addressLabel: UILabel!
    
    @IBOutlet weak var mnemonicView: CardView!
    
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
    
    var mMnemonicLabels: [UILabel] = [UILabel]()
    var mMnemonicWords: [String]?
    var createdKey: HDPrivateKey?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        let blurEffect = UIBlurEffect(style: .dark)
//        let blurEffectView = UIVisualEffectView(effect: blurEffect)
//        blurEffectView.alpha = 0.8
//        blurEffectView.frame = self.mnemonicView.bounds
//        self.mnemonicView.addSubview(blurEffectView)
        
        print("viewDidLoad hasPassword : ", BaseData.instance.hasPassword())
        
        self.mMnemonicLabels = [self.mnemonic0, self.mnemonic1, self.mnemonic2, self.mnemonic3,
                          self.mnemonic4, self.mnemonic5, self.mnemonic6, self.mnemonic7,
                          self.mnemonic8, self.mnemonic9, self.mnemonic10, self.mnemonic11,
                          self.mnemonic12, self.mnemonic13, self.mnemonic14, self.mnemonic15,
                          self.mnemonic16, self.mnemonic17, self.mnemonic18, self.mnemonic19,
                          self.mnemonic20, self.mnemonic21, self.mnemonic22, self.mnemonic23]
    
        self.onGenNewKey()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = "CREATE";
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    func onGenNewKey() {
        guard let words = try? Mnemonic.generate(strength: .veryHigh, language: .english) else {
            return
        }
        mMnemonicWords = words
        createdKey = WKey.getCosmosKeyFromWords(mnemonic: mMnemonicWords!, path: 0)
        onUpdateView()
    }
    
    func onUpdateView() {
        print("onUpdateView ", mMnemonicWords)
        self.addressLabel.text = WKey.getCosmosDpAddress(key: createdKey!)
        for i in 0 ... mMnemonicWords!.count - 1{
            self.mMnemonicLabels[i].text = mMnemonicWords?[i]
        }
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if(!BaseData.instance.hasPassword()) {
            let transition:CATransition = CATransition()
            transition.duration = 0.3
            transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
            transition.type = CATransitionType.moveIn
            transition.subtype = CATransitionSubtype.fromTop
            
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(transition, forKey: kCATransition)
            self.navigationController?.pushViewController(passwordVC, animated: false)
        } else  {
            
        }
    }
    
}
