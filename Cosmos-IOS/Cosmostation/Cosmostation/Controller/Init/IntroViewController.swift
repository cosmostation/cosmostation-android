//
//  IntroViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import Firebase

class IntroViewController: BaseViewController, PasswordViewDelegate {

    @IBOutlet weak var bottomLogoView: UIView!
    @IBOutlet weak var bottomControlView: UIView!
    @IBOutlet weak var importBtn: UIButton!
    @IBOutlet weak var importView: UIView!
    @IBOutlet weak var importMnemonicMsg: UIStackView!
    @IBOutlet weak var importMnemonicBtn: UIButton!
    @IBOutlet weak var importAddressMsg: UIStackView!
    @IBOutlet weak var importAddressBtn: UIButton!
    
    var accounts:Array<Account>?
    var lockPasses = false;
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.lockPasses = false;
        accounts = BaseData.instance.selectAllAccounts()
        importMnemonicBtn.addTarget(self, action: #selector(startHighlight), for: .touchDown)
        importMnemonicBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpInside)
        importMnemonicBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpOutside)

        importAddressBtn.addTarget(self, action: #selector(startHighlight), for: .touchDown)
        importAddressBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpInside)
        importAddressBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpOutside)
        
        Messaging.messaging().token { token, error in
            if let error = error {
                print("Error fetching FCM registration token: \(error)")
            } else if let token = token {
                print("FCM registration token: \(token)")
                BaseData.instance.setFCMToken(token)
            }
        }
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        //update okex chain
        BaseData.instance.upgradeAaccountAddressforOk()
        if (BaseData.instance.getUsingAppLock() == true && BaseData.instance.hasPassword() && !lockPasses) {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_INTRO_LOCK
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
            
        } else {
            self.onCheckAppVersion()
        }
    }
    
    
    func onStartInitJob() {
        if (accounts!.count <= 0) {
            UIView.animate(withDuration: 0.3, delay: 0.3, options: .curveEaseOut, animations: {
                self.bottomLogoView.alpha = 0.0
            }, completion: { (finished) -> Void in
                UIView.animate(withDuration: 0.3, delay: 0.0, options: .curveEaseIn, animations: {
                    self.bottomControlView.alpha = 1.0
                }, completion: nil)
            })
            
        } else {
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            if (appDelegate.userInfo != nil) {
                if let userInfo = appDelegate.userInfo,
                    let notifyto = userInfo["notifyto"] as? String {
                    appDelegate.userInfo = nil
                    let notiAccount = BaseData.instance.selectAccountByAddress(address: notifyto)
                    if (notiAccount != nil) {
                        BaseData.instance.setRecentAccountId(notiAccount!.account_id)
                        BaseData.instance.setLastTab(2)
                        DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
                            self.onStartMainTab()
                        }
                    }
                }
            } else {
                DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
                    self.onStartMainTab()
                }
            }
        }
    }
    
    
    @IBAction func onClickCreate(_ sender: Any) {
        self.onStartCreate()
        
    }
    
    @IBAction func onClickImport(_ sender: Any) {
        UIView.animate(withDuration: 0.2, delay: 0.0, options: .curveEaseOut, animations: {
            self.importBtn.alpha = 0.0
        }, completion: { (finished) -> Void in
            UIView.animate(withDuration: 0.2, delay: 0.0, options: .transitionCurlUp, animations: {
                self.importView.alpha = 1.0
            }, completion: nil)
            UIView.animate(withDuration: 0.1, delay: 0.1, options: .transitionCurlUp, animations: {
                self.importMnemonicMsg.transform = CGAffineTransform(translationX: 0, y: -9.6)
                self.importAddressMsg.transform = CGAffineTransform(translationX: 0, y: -9.6)
            }, completion: nil)
            
        })
    }
    
    @IBAction func onClickImportMnemonic(_ sender: Any) {
        self.onStartImportMnemonic()
    }
    
    @IBAction func onClickImportAddress(_ sender: Any) {
        self.onStartImportAddress()
        
    }
    
    @objc func startHighlight(sender: UIButton) {
        sender.layer.borderColor = UIColor.gray.cgColor
    }
    @objc func stopHighlight(sender: UIButton) {
        sender.layer.borderColor = UIColor.white.cgColor
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.lockPasses = true
        }
    }
    
    func onCheckAppVersion() {
        let request = Alamofire.request(CSS_VERSION, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary else {
                    self.onShowNetworkAlert()
                    return
                }
                
                let enable = responseData.object(forKey: "enable") as? Bool ?? false
                let latestVersion = responseData.object(forKey: "version") as? Int ?? 0
                let appVersion = Int(Bundle.main.infoDictionary!["CFBundleVersion"] as? String ?? "0") ?? 0
                
                if (!enable) {
                    self.onShowDisableAlert()
                } else {
                    if (latestVersion > appVersion) {
                        self.onShowUpdateAlert()
                    } else {
                        self.onStartInitJob()
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onCheckAppVersion ", error) }
                self.onShowNetworkAlert()
            }
        }
        
    }
    
    func onShowNetworkAlert() {
        let netAlert = UIAlertController(title: NSLocalizedString("error_network", comment: ""), message: NSLocalizedString("error_network_msg", comment: ""), preferredStyle: .alert)
        let action = UIAlertAction(title: NSLocalizedString("retry", comment: ""), style: .default, handler: { (UIAlertAction) in
            self.onCheckAppVersion()
        })
        netAlert.addAction(action)
        self.present(netAlert, animated: true, completion: nil)
    }
    
    func onShowDisableAlert() {
        let disableAlert = UIAlertController(title: NSLocalizedString("error_disable", comment: ""), message: NSLocalizedString("error_disable_msg", comment: ""), preferredStyle: .alert)
        let action = UIAlertAction(title: NSLocalizedString("confirm", comment: ""), style: .default, handler: { (UIAlertAction) in
            exit(-1)
        })
        disableAlert.addAction(action)
        self.present(disableAlert, animated: true, completion: nil)
    }
    
    func onShowUpdateAlert() {
        let updateAlert = UIAlertController(title: NSLocalizedString("update_title", comment: ""), message: NSLocalizedString("update_msg", comment: ""), preferredStyle: .alert)
        let action = UIAlertAction(title: NSLocalizedString("go_appstore", comment: ""), style: .default, handler: { (UIAlertAction) in
            let urlAppStore = URL(string: "itms-apps://itunes.apple.com/app/id1459830339")
            if(UIApplication.shared.canOpenURL(urlAppStore!)) {
                UIApplication.shared.open(urlAppStore!, options: [:], completionHandler: nil)
            }
        })
        updateAlert.addAction(action)
        self.present(updateAlert, animated: true, completion: nil)
    }
    
}
