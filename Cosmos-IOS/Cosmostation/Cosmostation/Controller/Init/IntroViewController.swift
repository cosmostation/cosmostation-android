//
//  IntroViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit

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

    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if (BaseData.instance.getUsingAppLock() == true && !lockPasses) {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_INTRO_LOCK
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
            
        } else {
            self.onStartInitJob()
            
//            let encoder = JSONEncoder()
//            encoder.outputFormatting = .sortedKeys
//
//
//            print("hihi")
//            var amino: Amino?
//            amino = Amino.init()
//            amino?.name = "yong"
//            amino?.id = 45
//            let data = try? encoder.encode(amino)
//            let rawResult = String(data:data!, encoding:.utf8)
//            print("rawResult ", rawResult)
//
//            do {
//                let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
//                print("params ", params)
//             } catch {
//                print(error)
//            }
//
//
//            print("hoho")
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
            DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
                self.onStartMainTab()
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
    
}
