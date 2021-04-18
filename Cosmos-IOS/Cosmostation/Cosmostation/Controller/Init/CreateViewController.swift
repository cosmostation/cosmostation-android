//
//  CreateViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import BitcoinKit
import SwiftKeychainWrapper

class CreateViewController: BaseViewController, PasswordViewDelegate{
    
    @IBOutlet weak var nextBtn: UIButton!
    @IBOutlet weak var warningMsgLabel: UILabel!
    
    @IBOutlet weak var addressView: CardView!
    @IBOutlet weak var addressLabel: UILabel!
    
    @IBOutlet weak var mnemonicView: CardView!
    
    
    @IBOutlet weak var mneminicLayer0: UIView!
    @IBOutlet weak var mneminicLayer1: UIView!
    @IBOutlet weak var mneminicLayer2: UIView!
    @IBOutlet weak var mneminicLayer3: UIView!
    @IBOutlet weak var mneminicLayer4: UIView!
    @IBOutlet weak var mneminicLayer5: UIView!
    @IBOutlet weak var mneminicLayer6: UIView!
    @IBOutlet weak var mneminicLayer7: UIView!
    @IBOutlet weak var mneminicLayer8: UIView!
    @IBOutlet weak var mneminicLayer9: UIView!
    @IBOutlet weak var mneminicLayer10: UIView!
    @IBOutlet weak var mneminicLayer11: UIView!
    @IBOutlet weak var mneminicLayer12: UIView!
    @IBOutlet weak var mneminicLayer13: UIView!
    @IBOutlet weak var mneminicLayer14: UIView!
    @IBOutlet weak var mneminicLayer15: UIView!
    @IBOutlet weak var mneminicLayer16: UIView!
    @IBOutlet weak var mneminicLayer17: UIView!
    @IBOutlet weak var mneminicLayer18: UIView!
    @IBOutlet weak var mneminicLayer19: UIView!
    @IBOutlet weak var mneminicLayer20: UIView!
    @IBOutlet weak var mneminicLayer21: UIView!
    @IBOutlet weak var mneminicLayer22: UIView!
    @IBOutlet weak var mneminicLayer23: UIView!
    
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
    
    @IBOutlet weak var warningView: UIView!
    
    var mnemonicLayers: [UIView] = [UIView]()
    var mnemonicLabels: [UILabel] = [UILabel]()
    var mnemonicWords: [String]?
    var checkedPassword: Bool = false
    var dpAddress: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mnemonicLayers = [self.mneminicLayer0, self.mneminicLayer1, self.mneminicLayer2, self.mneminicLayer3,
                          self.mneminicLayer4, self.mneminicLayer5, self.mneminicLayer6, self.mneminicLayer7,
                          self.mneminicLayer8, self.mneminicLayer9, self.mneminicLayer10, self.mneminicLayer11,
                          self.mneminicLayer12, self.mneminicLayer13, self.mneminicLayer14, self.mneminicLayer15,
                          self.mneminicLayer16, self.mneminicLayer17, self.mneminicLayer18, self.mneminicLayer19,
                          self.mneminicLayer20, self.mneminicLayer21, self.mneminicLayer22, self.mneminicLayer23]
        
        self.mnemonicLabels = [self.mnemonic0, self.mnemonic1, self.mnemonic2, self.mnemonic3,
                          self.mnemonic4, self.mnemonic5, self.mnemonic6, self.mnemonic7,
                          self.mnemonic8, self.mnemonic9, self.mnemonic10, self.mnemonic11,
                          self.mnemonic12, self.mnemonic13, self.mnemonic14, self.mnemonic15,
                          self.mnemonic16, self.mnemonic17, self.mnemonic18, self.mnemonic19,
                          self.mnemonic20, self.mnemonic21, self.mnemonic22, self.mnemonic23]
    
        self.addressView.isHidden = true
        self.mnemonicView.isHidden = true
        self.warningView.isHidden = true
        self.nextBtn.isHidden = true
        
        if (chainType == nil) {
            self.onShowChainType()
        } else {
            self.onGenNewKey()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_create", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func onShowChainType() {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let cosmosAction = UIAlertAction(title: NSLocalizedString("chain_title_cosmos", comment: ""), style: .default, handler: { _ in
            self.chainType = ChainType.COSMOS_MAIN
            self.onGenNewKey()
        })
        cosmosAction.setValue(UIImage(named: "cosmosWhMain")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let irisAction = UIAlertAction(title: NSLocalizedString("chain_title_iris", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IRIS_MAIN
            self.onGenNewKey()
        })
        irisAction.setValue(UIImage(named: "irisWh")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bnbAction = UIAlertAction(title: NSLocalizedString("chain_title_bnb", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.BINANCE_MAIN
            self.onGenNewKey()
        })
        bnbAction.setValue(UIImage(named: "binanceChImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let okexAction = UIAlertAction(title: NSLocalizedString("chain_title_okex", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.OKEX_MAIN
            self.onGenNewKey()
        })
        okexAction.setValue(UIImage(named: "okexChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let crytoAction = UIAlertAction(title: NSLocalizedString("chain_title_cryto", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.CRYTO_MAIN
            self.onGenNewKey()
        })
        crytoAction.setValue(UIImage(named: "chaincrypto")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let iovAction = UIAlertAction(title: NSLocalizedString("chain_title_iov", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IOV_MAIN
            self.onGenNewKey()
        })
        iovAction.setValue(UIImage(named: "iovChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let kavaAction = UIAlertAction(title: NSLocalizedString("chain_title_kava", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.KAVA_MAIN
            self.onGenNewKey()
        })
        kavaAction.setValue(UIImage(named: "kavaImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bandAction = UIAlertAction(title: NSLocalizedString("chain_title_band", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.BAND_MAIN
            self.onGenNewKey()
        })
        bandAction.setValue(UIImage(named: "bandChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let persisAction = UIAlertAction(title: NSLocalizedString("chain_title_persis", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.PERSIS_MAIN
            self.onGenNewKey()
        })
        persisAction.setValue(UIImage(named: "chainpersistence")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let certikAction = UIAlertAction(title: NSLocalizedString("chain_title_certik", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.CERTIK_MAIN
            self.onGenNewKey()
        })
        certikAction.setValue(UIImage(named: "certikChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let akashAction = UIAlertAction(title: NSLocalizedString("chain_title_akash", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.AKASH_MAIN
            self.onGenNewKey()
        })
        akashAction.setValue(UIImage(named: "akashChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let sentinelAction = UIAlertAction(title: NSLocalizedString("chain_title_sentinel", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.SENTINEL_MAIN
            self.onGenNewKey()
        })
        sentinelAction.setValue(UIImage(named: "chainsentinel")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let fetchAction = UIAlertAction(title: NSLocalizedString("chain_title_fetch", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.FETCH_MAIN
            self.onGenNewKey()
        })
        fetchAction.setValue(UIImage(named: "chainfetchai")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let secretAction = UIAlertAction(title: NSLocalizedString("chain_title_secret", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.SECRET_MAIN
            self.onGenNewKey()
        })
        secretAction.setValue(UIImage(named: "secretChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let cosmosTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_cosmos", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.COSMOS_TEST
            self.onGenNewKey()
        })
        cosmosTestAction.setValue(UIImage(named: "cosmosTestChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let irisTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_iris", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IRIS_TEST
            self.onGenNewKey()
        })
        irisTestAction.setValue(UIImage(named: "irisTestChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bnbTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_bnb", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.BINANCE_TEST
            self.onGenNewKey()
        })
        bnbTestAction.setValue(UIImage(named: "binancetestnet")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let kavaTestAction = UIAlertAction(title: NSLocalizedString("chain_title_kava_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.KAVA_TEST
            self.onGenNewKey()
        })
        kavaTestAction.setValue(UIImage(named: "kavaTestImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let iovTestAction = UIAlertAction(title: NSLocalizedString("chain_title_iov_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IOV_TEST
            self.onGenNewKey()
        })
        iovTestAction.setValue(UIImage(named: "iovTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let okTestAction = UIAlertAction(title: NSLocalizedString("chain_title_okex_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.OKEX_TEST
            self.onGenNewKey()
        })
        okTestAction.setValue(UIImage(named: "okexTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let certikTestAction = UIAlertAction(title: NSLocalizedString("chain_title_certik_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.CERTIK_TEST
            self.onGenNewKey()
        })
        certikTestAction.setValue(UIImage(named: "certikTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(cosmosAction)
        showAlert.addAction(irisAction)
        showAlert.addAction(bnbAction)
        showAlert.addAction(okexAction)
        showAlert.addAction(kavaAction)
        showAlert.addAction(bandAction)
        showAlert.addAction(persisAction)
        showAlert.addAction(iovAction)
        showAlert.addAction(certikAction)
        showAlert.addAction(akashAction)
        showAlert.addAction(sentinelAction)
        showAlert.addAction(fetchAction)
        showAlert.addAction(crytoAction)
        showAlert.addAction(secretAction)
        
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.COSMOS_TEST)) {
            showAlert.addAction(cosmosTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.IRIS_TEST)) {
            showAlert.addAction(irisTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.BINANCE_TEST)) {
            showAlert.addAction(bnbTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.KAVA_TEST)) {
            showAlert.addAction(kavaTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.IOV_TEST)) {
            showAlert.addAction(iovTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.OKEX_TEST)) {
            showAlert.addAction(okTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.CERTIK_TEST)) {
            showAlert.addAction(certikTestAction)
        }
        self.present(showAlert, animated: true, completion: nil)
    }
    
    
    func onGenNewKey() {
        guard let words = try? Mnemonic.generate(strength: .veryHigh, language: .english) else {
            return
        }
        self.mnemonicWords = words
        self.onUpdateView()
    }
    
    func onUpdateView() {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            //secret toggle!!!
            if (self.chainType! == ChainType.SECRET_MAIN) {
                self.dpAddress = WKey.getDpAddressPath(self.mnemonicWords!, 0, self.chainType!, false)
            } else {
                self.dpAddress = WKey.getDpAddressPath(self.mnemonicWords!, 0, self.chainType!, true)
            }
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
                self.hideWaittingAlert()
                self.addressLabel.text = self.dpAddress
                self.mnemonicView.backgroundColor = WUtils.getChainBg(self.chainType!)
                for i in 0 ... self.mnemonicLabels.count - 1{
                    self.mnemonicLayers[i].layer.borderWidth = 1
                    self.mnemonicLayers[i].layer.cornerRadius = 4
                    self.mnemonicLayers[i].layer.borderColor = WUtils.getChainDarkColor(self.chainType!).cgColor
                }
                self.addressView.isHidden = false
                self.mnemonicView.isHidden = false
                self.warningView.isHidden = false
                self.nextBtn.isHidden = false

                for i in 0 ... self.mnemonicWords!.count - 1{
                    if(self.checkedPassword) {
                        self.mnemonicLabels[i].text = self.mnemonicWords?[i]
                        self.mnemonicLabels[i].adjustsFontSizeToFitWidth = true
                    } else {
                        self.mnemonicLabels[i].text = ""
                    }
                }

                if (self.checkedPassword) {
                    self.warningMsgLabel.text = NSLocalizedString("password_msg2", comment: "")
                    self.nextBtn.setTitle(NSLocalizedString("create_wallet", comment: ""), for: .normal)
                } else {
                    self.warningMsgLabel.text = NSLocalizedString("password_msg1", comment: "")
                    self.nextBtn.setTitle(NSLocalizedString("show_mnemonics", comment: ""), for: .normal)
                }
            }
        }
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if (checkedPassword) {
            onGenAccount(chainType!)

        } else {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.resultDelegate = self
            if(!BaseData.instance.hasPassword()) {
                passwordVC.mTarget = PASSWORD_ACTION_INIT
            } else  {
                passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
            }
            self.navigationController?.pushViewController(passwordVC, animated: false)
        }
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(310), execute: {
                self.checkedPassword = true
                self.onUpdateView()
            })
            
        } else if (result == PASSWORD_RESUKT_CANCEL) {
            
        } else if (result == PASSWORD_RESUKT_FAIL) {
            
        }
    }
    
    func onGenAccount(_ chain:ChainType) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var resource: String = ""
            for word in self.mnemonicWords! {
                resource = resource + " " + word
            }
            
            let newAccount = Account.init(isNew: true)
            let keyResult = KeychainWrapper.standard.set(resource, forKey: newAccount.account_uuid.sha1(), withAccessibility: .afterFirstUnlockThisDeviceOnly)
            var insertResult :Int64 = -1
            if(keyResult) {
                newAccount.account_address = self.dpAddress!
                newAccount.account_base_chain = WUtils.getChainDBName(chain)
                newAccount.account_has_private = true
                newAccount.account_from_mnemonic = true
                newAccount.account_path = "0"
                newAccount.account_m_size = 24
                newAccount.account_import_time = Date().millisecondsSince1970
                if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST || chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
                    newAccount.account_new_bip44 = true
                }
                newAccount.account_sort_order = 9999
                insertResult = BaseData.instance.insertAccount(newAccount)
                
                if (insertResult < 0) {
                    KeychainWrapper.standard.removeObject(forKey: newAccount.account_uuid.sha1())
                }
            }
            
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                if(keyResult && insertResult > 0) {
                    BaseData.instance.setRecentAccountId(insertResult)
                    self.onStartMainTab()
                } else {
                    //TODO Error control
                    print("ERROR!!")
                }
            });
        }
    }

}
