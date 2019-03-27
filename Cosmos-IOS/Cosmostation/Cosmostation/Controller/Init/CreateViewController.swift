//
//  CreateViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit

class CreateViewController: BaseViewController, PasswordViewDelegate{
    
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
    
    var mnemonicLabels: [UILabel] = [UILabel]()
    var mnemonicWords: [String]?
    var createdKey: HDPrivateKey?
    var checkedPassword: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        let blurEffect = UIBlurEffect(style: .dark)
//        let blurEffectView = UIVisualEffectView(effect: blurEffect)
//        blurEffectView.alpha = 0.8
//        blurEffectView.frame = self.mnemonicView.bounds
//        self.mnemonicView.addSubview(blurEffectView)
        
//        print("viewDidLoad hasPassword : ", BaseData.instance.hasPassword())
        
        self.mnemonicLabels = [self.mnemonic0, self.mnemonic1, self.mnemonic2, self.mnemonic3,
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
        mnemonicWords = words
        createdKey = WKey.getCosmosKeyFromWords(mnemonic: mnemonicWords!, path: 0)
        onUpdateView()
    }
    
    func onUpdateView() {
        print("onUpdateView ", mnemonicWords)
        self.addressLabel.text = WKey.getCosmosDpAddress(key: createdKey!)
        for i in 0 ... mnemonicWords!.count - 1{
            if(checkedPassword) {
                self.mnemonicLabels[i].text = mnemonicWords?[i]
            } else {
                self.mnemonicLabels[i].text = mnemonicWords?[i].replacingOccurrences(of: "\\S", with: "?", options: .regularExpression)
            }
        }
        if(checkedPassword) {
            self.warningMsgLabel.text = NSLocalizedString("password_msg2", comment: "")
            self.nextBtn.setTitle(NSLocalizedString("create_wallet", comment: ""), for: .normal)
        } else {
            self.warningMsgLabel.text = NSLocalizedString("password_msg1", comment: "")
            self.nextBtn.setTitle(NSLocalizedString("show_mnemonics", comment: ""), for: .normal)
        }
        
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if(checkedPassword) {
            //TODO check wallet address list
            
        } else {
            if(!BaseData.instance.hasPassword()) {
                let transition:CATransition = CATransition()
                transition.duration = 0.3
                transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
                transition.type = CATransitionType.moveIn
                transition.subtype = CATransitionSubtype.fromTop
                
                let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
                self.navigationItem.title = ""
                self.navigationController!.view.layer.add(transition, forKey: kCATransition)
                passwordVC.mTarget = PASSWORD_ACTION_INIT
                passwordVC.resultDelegate = self
                self.navigationController?.pushViewController(passwordVC, animated: false)
            } else  {
                let transition:CATransition = CATransition()
                transition.duration = 0.3
                transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
                transition.type = CATransitionType.moveIn
                transition.subtype = CATransitionSubtype.fromTop
                
                let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
                self.navigationItem.title = ""
                self.navigationController!.view.layer.add(transition, forKey: kCATransition)
                passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
                passwordVC.resultDelegate = self
                self.navigationController?.pushViewController(passwordVC, animated: false)
            }
        }
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            print("PASSWORD_RESUKT_OK")
            checkedPassword = true
            onUpdateView()
            
        } else if (result == PASSWORD_RESUKT_CANCEL) {
            print("PASSWORD_RESUKT_CANCEL")
            
        } else if (result == PASSWORD_RESUKT_FAIL) {
            print("PASSWORD_RESUKT_FAIL")
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    func test() {
        let showAlert = UIAlertController(title: "", message: nil, preferredStyle: .alert)
        showAlert.view.subviews.first?.subviews.first?.subviews.first?.backgroundColor = UIColor.gray
        let imageView = UIImageView(frame: CGRect(x: 10, y: 10, width: 80, height: 80))
        imageView.image = UIImage(named: "loading_3")
        imageView.backgroundColor = UIColor.clear
        showAlert.view.addSubview(imageView)
        let height = NSLayoutConstraint(item: showAlert.view, attribute: .height, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1, constant: 100)
        let width = NSLayoutConstraint(item: showAlert.view, attribute: .width, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1, constant: 100)
        showAlert.view.addConstraint(height)
        showAlert.view.addConstraint(width)
        
//        let myimgArr = ["loading_1","loading_2","loading_3","loading_4","loading_5","loading_6","loading_22"]
//        let animation = UIImage.an   .animatedImage(with: myimgArr, duration: 1)
//        var images = [UIImage]()
//        for i in 0..<myimgArr.count
//        {
//            images.append(UIImage(named: myimgArr[i])!)
//        }
//        imageView.animationImages = images
//        imageView.animationDuration = 0.1
//        imageView.animationRepeatCount = 100
//        imageView.startAnimating()
        
        self.present(showAlert, animated: true, completion: nil)
    }
    
}
