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
import LocalAuthentication

class PasswordViewController: BaseViewController {
    
    var resultDelegate: PasswordViewDelegate?

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
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: false)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.onUserInsert(_:)),
                                               name: Notification.Name("KeyboardClick"),
                                               object: nil)
        if (mTarget == PASSWORD_ACTION_APP_LOCK) {
            NotificationCenter.default.addObserver(self,
                                                   selector: #selector(self.onBioAuth),
                                                   name: Notification.Name("ForeGround"),
                                                   object: nil)
        } else if (mTarget == PASSWORD_ACTION_INTRO_LOCK) {
            self.onBioAuth()
        }
        
        self.initView()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("KeyboardClick"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("ForeGround"), object: nil)
    }
    
    
    func initView() {
        passwordMsgLabel.text = NSLocalizedString("password_init_warnning", comment: "")
        passwordMsgLabel.isHidden = true
        if (mTarget == PASSWORD_ACTION_INIT) {
            passwordMsgLabel.isHidden = false
            passwordTitleLable.text = NSLocalizedString("password_init1", comment: "")
            
            
        } else if (mTarget == PASSWORD_ACTION_SIMPLE_CHECK) {
            passwordTitleLable.text = NSLocalizedString("password_check", comment: "")
            
        } else if (mTarget == PASSWORD_ACTION_DELETE_ACCOUNT) {
            passwordTitleLable.text = NSLocalizedString("password_delete", comment: "")
            
        } else if (mTarget == PASSWORD_ACTION_CHECK_TX) {
            passwordTitleLable.text = NSLocalizedString("password_tx", comment: "")
            
        } else if (mTarget == PASSWORD_ACTION_APP_LOCK || mTarget ==  PASSWORD_ACTION_INTRO_LOCK) {
            passwordTitleLable.text = NSLocalizedString("password_app_lock", comment: "")
            
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
                    self.sendResultAndPop(PASSWORD_RESUKT_CANCEL)
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
    
    var cancelbio = false
    @objc func onBioAuth() {
        if (self.cancelbio) {return}
        
        if(!BaseData.instance.getUsingBioAuth()) {
            return
        }
        let myContext = LAContext()
        let myLocalizedReasonString = NSLocalizedString("app_locked", comment: "")
        
        var authError: NSError?
        if #available(iOS 8.0, macOS 10.12.1, *) {
            if myContext.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &authError) {
                myContext.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: myLocalizedReasonString) { success, evaluateError in
                    DispatchQueue.main.async {
                        if success {
                            self.onUserSuccessUnlock()
                        } else {
                            self.cancelbio = true
                        }
                    }
                }
            } else {
                self.cancelbio = true
            }
        } else {
            self.cancelbio = true
        }
    }
    
    func sendResultAndPop(_ data: Int) {
        if(mTarget != PASSWORD_ACTION_APP_LOCK) {
            UIApplication.shared.endIgnoringInteractionEvents()
            let transition:CATransition = CATransition()
            transition.duration = 0.3
            transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
            transition.type = CATransitionType.reveal
            transition.subtype = CATransitionSubtype.fromBottom
            self.resultDelegate?.passwordResponse(result: data)
            self.navigationController!.view.layer.add(transition, forKey: kCATransition)
            self.navigationController!.popViewController(animated: false)
            
        } else {
            self.onShowToast(NSLocalizedString("insert_password_app_lock", comment: ""))
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
        NotificationCenter.default.post(name: Notification.Name("lockBtns"), object: nil, userInfo: nil)
        if (mTarget == PASSWORD_ACTION_INIT) {
            if(mIsConfirmSequence == true) {
                if(mUserConfirm == mUserInsert) {
                    self.onStartInitPassword(mUserInsert)
                    
                } else {
                    self.onShowToast(NSLocalizedString("error_password_fail", comment: ""))
                    NotificationCenter.default.post(name: Notification.Name("KeyboardShuffle"), object: nil, userInfo: nil)
                    self.initView()
                }
                
            } else {
                self.initConfirmView()
            }
            
        } else if (mTarget == PASSWORD_ACTION_SIMPLE_CHECK ||
                    mTarget == PASSWORD_ACTION_CHECK_TX) {
            self.onStartCheckPassword(mUserInsert)
            
        } else if (mTarget == PASSWORD_ACTION_DELETE_ACCOUNT) {
            self.onStartCheckPasswordForDelete(mUserInsert)
            
        } else if (mTarget == PASSWORD_ACTION_APP_LOCK ||
                    mTarget ==  PASSWORD_ACTION_INTRO_LOCK) {
            self.onStartCheckAppLock(mUserInsert)
        }
        
    }
    
    
    func onStartInitPassword(_ initInput: String) {
        DispatchQueue.global().async {
            var result = false
            if(!KeychainWrapper.standard.hasValue(forKey: "password")) {
                result = KeychainWrapper.standard.set(initInput, forKey: "password", withAccessibility: .afterFirstUnlockThisDeviceOnly)
            }
            DispatchQueue.main.async(execute: {
                if(result) {
                    self.sendResultAndPop(PASSWORD_RESUKT_OK)
                } else {
                    self.sendResultAndPop(PASSWORD_RESUKT_FAIL)
                }
            });
        }
        
    }
    
    func onStartCheckPassword(_ input: String) {
        DispatchQueue.global().async {
            var result = false
            if(KeychainWrapper.standard.hasValue(forKey: "password")) {
                if(KeychainWrapper.standard.string(forKey: "password") == input) {
                    result = true
                }
            }
            DispatchQueue.main.async(execute: {
                if(result) {
                    self.sendResultAndPop(PASSWORD_RESUKT_OK)
                } else {
                    self.onShowToast(NSLocalizedString("error_invalid_password", comment: ""))
                    self.initView()
                }
            });
        }
    }
    
    func onStartCheckAppLock(_ input: String) {
        DispatchQueue.global().async {
            var result = false
            if(KeychainWrapper.standard.hasValue(forKey: "password")) {
                if(KeychainWrapper.standard.string(forKey: "password") == input) {
                    result = true
                }
            }
            DispatchQueue.main.async(execute: {
                UIApplication.shared.endIgnoringInteractionEvents()
                if(result) {
                    self.onUserSuccessUnlock()
                } else {
                    self.onShowToast(NSLocalizedString("error_invalid_password", comment: ""))
                    self.initView()
                }
            });
        }
    }
    
    func onStartCheckPasswordForDelete(_ input: String) {
        DispatchQueue.global().async {
            var result = false
            if(KeychainWrapper.standard.hasValue(forKey: "password")) {
                if(KeychainWrapper.standard.string(forKey: "password") == input) {
                    result = true
                }
            }
            DispatchQueue.main.async(execute: {
                if(result) {
                    self.sendResultAndPop(PASSWORD_RESUKT_OK_FOR_DELETE)
                } else {
                    self.onShowToast(NSLocalizedString("error_invalid_password", comment: ""))
                    self.initView()
                }
            });
        }
    }
    
    func onUserSuccessUnlock() {
        if(mTarget ==  PASSWORD_ACTION_INTRO_LOCK) {
            self.sendResultAndPop(PASSWORD_RESUKT_OK)
        } else {
            self.dismiss(animated: true, completion: nil)
        }
    }
    
}

protocol PasswordViewDelegate{
    func passwordResponse(result:Int)
}
