//
//  MainTabSettingViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import UserNotifications

class MainTabSettingViewController: BaseViewController {
    
    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleAlarmBtn: UIButton!
    @IBOutlet weak var titleChainName: UILabel!
    
    var mainTabVC: MainTabViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        if (BaseData.instance.getNeedRefresh()) {
            BaseData.instance.setNeedRefresh(false)
            mainTabVC.onUpdateAccountDB()
        }
        self.updateTitle()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        if (chainType! == ChainType.COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Mainnet)"
        } else if (chainType! == ChainType.IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType == ChainType.BINANCE_MAIN) {
            titleChainImg.image = UIImage(named: "binanceChImg")
            titleChainName.text = "(Binance Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType == ChainType.KAVA_MAIN) {
            titleChainImg.image = UIImage(named: "kavaImg")
            titleChainName.text = "(Kava Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType == ChainType.IOV_MAIN) {
            titleChainImg.image = UIImage(named: "iovChainImg")
            titleChainName.text = "(Starname Mainnet)"
            titleAlarmBtn.isHidden = true
        }  else if (chainType! == ChainType.BAND_MAIN) {
            titleChainImg.image = UIImage(named: "bandChainImg")
            titleChainName.text = "(Band Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SECRET_MAIN) {
            titleChainImg.image = UIImage(named: "secretChainImg")
            titleChainName.text = "(Secret Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CERTIK_MAIN) {
            titleChainImg.image = UIImage(named: "certikChainImg")
            titleChainName.text = "(Certik Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.AKASH_MAIN) {
            titleChainImg.image = UIImage(named: "akashChainImg")
            titleChainName.text = "(Akash Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.OKEX_MAIN) {
            titleChainImg.image = UIImage(named: "okexChainImg")
            titleChainName.text = "(ExChain Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            titleChainImg.image = UIImage(named: "chainpersistence")
            titleChainName.text = "(Persistence Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            titleChainImg.image = UIImage(named: "chainsentinel")
            titleChainName.text = "(Sentinel Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.FETCH_MAIN) {
            titleChainImg.image = UIImage(named: "chainfetchai")
            titleChainName.text = "(Fetch.ai Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            titleChainImg.image = UIImage(named: "chaincrypto")
            titleChainName.text = "(Crypto.org Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SIF_MAIN) {
            titleChainImg.image = UIImage(named: "chainsifchain")
            titleChainName.text = "(SifChain Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.KI_MAIN) {
            titleChainImg.image = UIImage(named: "chainKifoundation")
            titleChainName.text = "(KiChain Mainnet)"
            titleAlarmBtn.isHidden = true
        }
        
        else if (chainType! == ChainType.COSMOS_TEST) {
            titleChainImg.image = UIImage(named: "cosmosTestChainImg")
            titleChainName.text = "(StarGate Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.IRIS_TEST) {
            titleChainImg.image = UIImage(named: "irisTestChainImg")
            titleChainName.text = "(Bifrost Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.BINANCE_TEST) {
            titleChainImg.image = UIImage(named: "binancetestnet")
            titleChainName.text = "(Binance Testnet)"
            titleAlarmBtn.isHidden = true
        }  else if (chainType! == ChainType.KAVA_TEST) {
            titleChainImg.image = UIImage(named: "kavaTestImg")
            titleChainName.text = "(Kava Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.IOV_TEST) {
            titleChainImg.image = UIImage(named: "iovTestnetImg")
            titleChainName.text = "(Starname Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.OKEX_TEST) {
            titleChainImg.image = UIImage(named: "okexTestnetImg")
            titleChainName.text = "(ExChain Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CERTIK_TEST) {
            titleChainImg.image = UIImage(named: "certikTestnetImg")
            titleChainName.text = "(Certik Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.RIZON_TEST) {
            titleChainImg.image = UIImage(named: "testnetRizon")
            titleChainName.text = "(Rizon Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.MEDI_TEST) {
            titleChainImg.image = UIImage(named: "testnetMedibloc")
            titleChainName.text = "(Medi Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.ALTHEA_TEST) {
            titleChainImg.image = UIImage(named: "testnetAlthea")
            titleChainName.text = "(Althea Testnet)"
            titleAlarmBtn.isHidden = true
        }
        UNUserNotificationCenter.current().getNotificationSettings { (settings) in
            if settings.authorizationStatus == .authorized {
                DispatchQueue.main.async {
                    if (self.mainTabVC.mAccount.account_push_alarm) {
                        self.titleAlarmBtn.setImage(UIImage(named: "notificationsIc"), for: .normal)
                    } else {
                        self.titleAlarmBtn.setImage(UIImage(named: "notificationsIcOff"), for: .normal)
                    }
                }
            } else {
                DispatchQueue.main.async {
                    self.titleAlarmBtn.setImage(UIImage(named: "notificationsIcOff"), for: .normal)
                }
            }
        }
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.onShowAccountSwicth()
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        let link = WUtils.getAccountExplorer(chainType!, mainTabVC.mAccount.account_address)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    @IBAction func onClickAlaram(_ sender: UIButton) {
        if (sender.imageView?.image == UIImage(named: "notificationsIcOff")) {
            UNUserNotificationCenter.current().getNotificationSettings { (settings) in
                if settings.authorizationStatus == .authorized {
                    DispatchQueue.main.async {
                        self.showWaittingAlert()
                        self.onToggleAlarm(self.mainTabVC.mAccount!) { (success) in
                            self.mainTabVC.onUpdateAccountDB()
                            self.updateTitle()
                            self.dismissAlertController()
                        }
                    }
                    
                } else {
                    let alertController = UIAlertController(title: NSLocalizedString("permission_push_title", comment: ""), message: NSLocalizedString("permission_push_msg", comment: ""), preferredStyle: .alert)
                    let settingsAction = UIAlertAction(title: NSLocalizedString("settings", comment: ""), style: .default) { (_) -> Void in
                        guard let settingsUrl = URL(string: UIApplication.openSettingsURLString) else {
                            return
                        }
                        if UIApplication.shared.canOpenURL(settingsUrl) {
                            UIApplication.shared.open(settingsUrl, completionHandler: { (success) in
                            })
                        }
                    }
                    let cancelAction = UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: nil)
                    alertController.addAction(cancelAction)
                    alertController.addAction(settingsAction)
                    DispatchQueue.main.async {
                        self.present(alertController, animated: true, completion: nil)
                    }
                }
            }
        } else {
            DispatchQueue.main.async {
                self.showWaittingAlert()
                self.onToggleAlarm(self.mainTabVC.mAccount!) { (success) in
                    self.mainTabVC.onUpdateAccountDB()
                    self.updateTitle()
                    self.dismissAlertController()
                }
            }
        }
    }
}
