//
//  IntroViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit

class IntroViewController: BaseViewController {

    @IBOutlet weak var bottomLogoView: UIView!
    @IBOutlet weak var bottomControlView: UIView!
    @IBOutlet weak var importBtn: UIButton!
    @IBOutlet weak var importView: UIView!
    @IBOutlet weak var importMnemonicMsg: UIStackView!
    @IBOutlet weak var importMnemonicBtn: UIButton!
    @IBOutlet weak var importAddressMsg: UIStackView!
    @IBOutlet weak var importAddressBtn: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        print("IntroViewController viewDidLoad")
//        print("password ", BaseData.instance.hasPassword())
        
        let accouts = BaseData.instance.selectAllAccounts()
        if(accouts.count <= 0) {
//            print("No accounts");
            UIView.animate(withDuration: 0.3, delay: 0.3, options: .curveEaseOut, animations: {
                self.bottomLogoView.alpha = 0.0
            }, completion: { (finished) -> Void in
                UIView.animate(withDuration: 0.3, delay: 0.0, options: .curveEaseIn, animations: {
                    self.bottomControlView.alpha = 1.0
                }, completion: nil)

            })

        } else {
//            print("accounts size : ", accouts.count);
            self.onStartMainTab()

        }

        importMnemonicBtn.addTarget(self, action: #selector(startHighlight), for: .touchDown)
        importMnemonicBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpInside)
        importMnemonicBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpOutside)

        importAddressBtn.addTarget(self, action: #selector(startHighlight), for: .touchDown)
        importAddressBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpInside)
        importAddressBtn.addTarget(self, action: #selector(stopHighlight), for: .touchUpOutside)
        
//        let createVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(createVC, animated: true)
        
        
//        WKey.getCosmosAddressFromPubKey("cosmospub1addwnpepq2djnv5m0aqqeqjlkul9yza2g4fdfyupa9gde66x7d4m80tm4evpyxll4ss")
        
//        let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(stakingVC, animated: true)
        
        
//        print("amount18 ", WUtils.unDelegateFormat("10.45"))

    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    @IBAction func onClickCreate(_ sender: Any) {
//        print("onClickCreate");
        self.onStartCreate()
        
    }
    
    @IBAction func onClickImport(_ sender: Any) {
//        print("onClickImport");
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
//        print("onClickImportMnemonic");
        self.onStartImportMnemonic()
    }
    
    @IBAction func onClickImportAddress(_ sender: Any) {
//        print("onClickImportAddress");
        self.onStartImportAddress()
        
    }
    
    @objc func startHighlight(sender: UIButton) {
        sender.layer.borderColor = UIColor.gray.cgColor
    }
    @objc func stopHighlight(sender: UIButton) {
        sender.layer.borderColor = UIColor.white.cgColor
    }
}
