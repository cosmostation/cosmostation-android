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
        print("IntroViewController viewDidLoad")
        print("password ", BaseData.instance.hasPassword())
        
        //TEST
//        testFunc()
                
        let accouts = BaseData.instance.selectAllAccounts()
        if(accouts.count <= 0) {
            print("No accounts");
            UIView.animate(withDuration: 0.3, delay: 0.3, options: .curveEaseOut, animations: {
                self.bottomLogoView.alpha = 0.0
            }, completion: { (finished) -> Void in
                UIView.animate(withDuration: 0.3, delay: 0.0, options: .curveEaseIn, animations: {
                    self.bottomControlView.alpha = 1.0
                }, completion: nil)

            })

        } else {
            print("accounts size : ", accouts.count);

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
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    @IBAction func onClickCreate(_ sender: Any) {
        print("onClickCreate");
        let createVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "CreateViewController") as! CreateViewController
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(createVC, animated: true)
    }
    
    @IBAction func onClickImport(_ sender: Any) {
        print("onClickImport");
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
        print("onClickImportMnemonic");
        let restoreVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestoreViewController") as! RestoreViewController
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(restoreVC, animated: true)
    }
    
    @IBAction func onClickImportAddress(_ sender: Any) {
        print("onClickImportAddress");
        let addAddressVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "AddAddressViewController") as! AddAddressViewController
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(addAddressVC, animated: true)
    }
    
    @objc func startHighlight(sender: UIButton) {
        sender.layer.borderColor = UIColor.gray.cgColor
    }
    @objc func stopHighlight(sender: UIButton) {
        sender.layer.borderColor = UIColor.white.cgColor
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    let PRE_PUB_KEY = "eb5ae98721";
    let PRE_PRI_KEY = "e1b0f79b20";
    
    
    func testFunc() {
        do {
            guard let mnemonic = try? Mnemonic.generate(strength: .veryHigh, language: .english) else {
                print("mnemonic error")
                return
            }
            print("mnemonic", mnemonic)
            
            
            let seed = Mnemonic.seed(mnemonic: ["iron", "breeze", "tongue", "voice", "stomach", "nut", "manage", "advance", "rather", "mad", "hurry", "neutral", "identify", "armed", "unusual", "crunch", "hammer", "scan", "riot", "mom", "surface", "horn", "stamp", "thank"])
            
//            let seed = Mnemonic.seed(mnemonic: ["slice", "fever", "fluid", "nose", "spread", "engine", "review", "subway", "vote", "say", "wide", "away", "hamster", "jazz", "biology", "position", "upgrade", "pyramid", "practice", "aim", "assist", "roof", "harbor", "keep"])
            
            print("seedHD", seed.hexEncodedString())
            
            let masterKey = HDPrivateKey(seed: seed, network: .testnet)
            print("master privateKey ", masterKey.privateKey())
            print("master publicKey ", masterKey.privateKey().publicKey())
            print("")
            
            
            //path for "m/44'/118'/0'/0/0"
            let derivedKey = try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: 0)
            print("derived privateKey ", derivedKey.privateKey())
            print("derived publicKey", derivedKey.privateKey().publicKey())
            print("derived publicKey", derivedKey.privateKey().publicKey().raw.hexEncodedString())
            //            print("derived extended", derivedKey.extended())
            //            print("derived childIndex", derivedKey.childIndex)
            //            print("derived depth", derivedKey.depth)
            print("")
            
            /** for DP publickey **/
            let cosmosPublic = PRE_PUB_KEY + derivedKey.privateKey().publicKey().raw.hexEncodedString();
            print("cosmosPublic hex", cosmosPublic)
            print("cosmosPublic ", dataWithHexString(hex: cosmosPublic))
            print(" ", dataWithHexString(hex: cosmosPublic).hexEncodedString())
            print("")
            let cosmosPublicDp = try SegwitAddrCoder.shared.encode2(hrp: "cosmospub", program: dataWithHexString(hex: cosmosPublic))
            print("cosmosPublic dpStyle ", cosmosPublicDp)
            
            /** for DP address **/
            print("")
            let sha256 = Crypto.sha256(derivedKey.privateKey().publicKey().raw)
            print("sha256 ", sha256.hexEncodedString())
            let ripemd160 = Crypto.ripemd160(sha256)
            print("ripemd160 ", ripemd160.hexEncodedString())
            let cosmosAddrDp = try SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
            print("cosmosAdd dpStyle ", cosmosAddrDp)
            
            
            /** simple sign **/
            let target = "aaaaabbbbb"
            let targetByte: Data? = target.data(using: .utf8)
            print("targetByte ", targetByte?.hexEncodedString())
            let hash = Crypto.sha256(targetByte!)
            print("hash ", hash.hexEncodedString())
            let signedData: Data? = try Crypto.sign(hash, privateKey: derivedKey.privateKey())
            print("signedData ", signedData?.hexEncodedString())
            print("signedData ", signedData)
            
            let rangeR = Range(5..<37)
            var subDataR = signedData?.subdata(in: rangeR)
            print("subDataR ", subDataR?.hexEncodedString())
            
            let rangeL = Range(39..<71)
            let subDataL = signedData?.subdata(in: rangeL)
            print("subDataL ", subDataL?.hexEncodedString())
            
            subDataR?.append(subDataL!)
            print("signature ", subDataR?.hexEncodedString())
            
            
            //            let rangeR = Range(0..<36)
            //            let subDataR = signedData?.subdata(in: rangeR)
            //            print("subDataR ", subDataR?.hexEncodedString())
            //
            //            let rangeL = Range(37..<71)
            //            let subDataL = signedData?.subdata(in: rangeL)
            //            print("subDataL ", subDataL?.hexEncodedString())
            
            
            //            Signature signature = Signature.getInstance("ECDSA", "SC");
            
            //            let r = BigInte
            
            
            //            3045022100b5e690db1762e3c7ab7e808e3be437066a23168395007113deba74519968dff2022003479823c9b72e3193607efd9776a982eb25962252c1bc347face1eb26aea4fc
            
            
        } catch {
            print("encode error")
        }
        
    }
}
