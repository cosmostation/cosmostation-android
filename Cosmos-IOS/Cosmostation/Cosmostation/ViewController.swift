//
//  ViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
//        testFunc()
        onStartMainTab()
        self.test()
    }
    
    
    
    func onStartMainTab() {
        let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.window?.rootViewController = mainTabVC
        self.present(mainTabVC, animated: true, completion: nil)
    }
    
    
    
    func test() {
        DispatchQueue.global().async {
            
            var amino = Amino.init()
            amino.name = "hihi"
            amino.id = 10
            //            amino.data = String(NSNull())
            //            amino.data = noNull
            
            var inner = Amino.Inner.init()
            inner.innerName = "yoyo"
            
            amino.innder = inner
            
            let encoder = JSONEncoder()
            //            encoder.outputFormatting = .prettyPrinted
            let data = try? encoder.encode(amino)
            print("data ", data)
            print("data ", data?.toHexString())
            print("data string ", String(data:data!, encoding:.utf8)!)
            
            
            let json = try? JSONSerialization.data(withJSONObject: amino.toDic2(), options: [])
            print("json ", json)
            print("json hex ", json!.toHexString())
            print("json string ", String(data:json!, encoding:.utf8)!)
            
            
            
            //            amino.encode(to: encoder)
            
            DispatchQueue.main.async(execute: {
                print("finish")
            });
        }
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

