//
//  PasswordViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 26/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import CryptoSwift
import SwiftKeychainWrapper
import Toast_Swift

class PasswordViewController: BaseViewController {

    @IBOutlet weak var passwordTitleLable: UILabel!
    @IBOutlet weak var passwordMsgLabel: UILabel!
    @IBOutlet weak var pin0Img: UIImageView!
    @IBOutlet weak var pin1Img: UIImageView!
    @IBOutlet weak var pin2Img: UIImageView!
    @IBOutlet weak var pin3Img: UIImageView!
    @IBOutlet weak var pin4Img: UIImageView!
    
    var pinImgs: [UIImageView] = [UIImageView]()
    
    var mTarget: String?
    var mIsConfirmSequence: Bool = false
    
    var mUserInsert: String = "" {
        willSet(newVal){
            self.onUpdatePinImage(count: newVal.count)
        }
    }
    var mUserConfirm: String  = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.pinImgs = [self.pin0Img, self.pin1Img, self.pin2Img, self.pin3Img, self.pin4Img]
        
        //TEST
        mTarget = PASSWORD_ACTION_INIT
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: false)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.onUserInsert(_:)),
                                               name: Notification.Name("KeyboardClick"),
                                               object: nil)
        
        self.initView()
    }
    
    
    func initView() {
        passwordMsgLabel.text = NSLocalizedString("password_init_warnning", comment: "")
        if (mTarget == PASSWORD_ACTION_INIT) {
            passwordMsgLabel.isHidden = false
            passwordTitleLable.text = NSLocalizedString("password_init1", comment: "")
            
            
        } else {
            passwordMsgLabel.isHidden = true
        }
        passwordTitleLable.adjustsFontSizeToFitWidth = true
        
        mIsConfirmSequence = false
        mUserInsert = ""
        mUserConfirm = ""
        
        let value:[String: Int] = ["Page": 0]
        NotificationCenter.default.post(name: Notification.Name("KeyBoardPage"), object: nil, userInfo: value)
    }
    
    func initConfirmView() {
        passwordTitleLable.text = NSLocalizedString("password_init2", comment: "")
        passwordTitleLable.adjustsFontSizeToFitWidth = true
        
        self.mIsConfirmSequence = true
        self.mUserConfirm = mUserInsert
        self.mUserInsert = ""
        
        NotificationCenter.default.post(name: Notification.Name("KeyboardShuffle"), object: nil, userInfo: nil)
        let value:[String: Int] = ["Page": 0]
        NotificationCenter.default.post(name: Notification.Name("KeyBoardPage"), object: nil, userInfo: value)
        
    }
    
    
    @objc func onUserInsert(_ notification: NSNotification) {
        if let string = notification.userInfo?["Keyboard"] as? String {
            if(string == "delete") {
                if(mUserInsert.count == 4) {
                    let subString = mUserInsert.prefix(mUserInsert.count - 1)
                    mUserInsert = String(subString)
                    
                    NotificationCenter.default.post(name: Notification.Name("lockBtns"), object: nil, userInfo: nil)
                    let value:[String: Int] = ["Page": 0]
                    NotificationCenter.default.post(name: Notification.Name("KeyBoardPage"), object: nil, userInfo: value)
                    
                } else if (mUserInsert.count > 0) {
                    let subString = mUserInsert.prefix(mUserInsert.count - 1)
                    mUserInsert = String(subString)
                    
                } else {
                    print("back to stack")
                    let transition:CATransition = CATransition()
                    transition.duration = 0.3
                    transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
                    transition.type = CATransitionType.reveal
                    transition.subtype = CATransitionSubtype.fromBottom
                    self.navigationController!.view.layer.add(transition, forKey: kCATransition)
                    self.navigationController?.popViewController(animated: false)
                    
                }
                
            } else {
                mUserInsert.append(string)
                if(mUserInsert.count == 4) {
                    NotificationCenter.default.post(name: Notification.Name("lockBtns"), object: nil, userInfo: nil)
                    let value:[String: Int] = ["Page": 1]
                    NotificationCenter.default.post(name: Notification.Name("KeyBoardPage"), object: nil, userInfo: value)
                    
                } else if(mUserInsert.count == 5) {
                    self.onUserInsertFinish()
                }
            }
        }
    }
    
    func onUpdatePinImage(count:Int) {
        for i in 0 ..< 5 {
            if(i < count) {
                pinImgs[i].image = UIImage(named: "passp");
            } else {
                pinImgs[i].image = UIImage(named: "passUp");
            }
        }
    }
    
    func onUserInsertFinish() {
        if (mTarget == PASSWORD_ACTION_INIT) {
            if(mIsConfirmSequence == true) {
                if(mUserConfirm == mUserInsert) {
//                    self.onStartInsertUser()
                    
                } else {
                    var style = ToastStyle()
                    style.backgroundColor = UIColor.gray
                    self.view.makeToast(NSLocalizedString("error_password_fail", comment: ""), duration: 2.0, position: .bottom, style: style)
                    NotificationCenter.default.post(name: Notification.Name("KeyboardShuffle"), object: nil, userInfo: nil)
                    self.initView()
                }
                
            } else {
                self.initConfirmView()
            }
            
        }
    }
}
