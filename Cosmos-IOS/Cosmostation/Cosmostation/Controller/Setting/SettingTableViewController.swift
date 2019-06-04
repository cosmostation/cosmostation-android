//
//  SettingTableViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import SafariServices
import Toast_Swift

class SettingTableViewController: UITableViewController {

    @IBOutlet weak var versionLabel: UILabel!
    @IBOutlet weak var currecyLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let appVersion = Bundle.main.infoDictionary!["CFBundleShortVersionString"] as? String {
            self.versionLabel.text = "v " + appVersion
        }
        self.onUpdateCurrency()
        
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 0) {
            if(indexPath.row == 0) {
                let accoutManageVC = WalletManageViewController(nibName: "WalletManageViewController", bundle: nil)
                accoutManageVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(accoutManageVC, animated: true)
            }
            
        } else if (indexPath.section == 1) {
            if(indexPath.row == 0) {
//                onShowToast(NSLocalizedString("only_USD", comment: ""))
                self.onShowCurrenyDialog()
                
            } else if(indexPath.row == 1) {
                onShowToast(NSLocalizedString("only_cmc", comment: ""))
            }
            
        } else if (indexPath.section == 2) {
            if(indexPath.row == 0) {
                if(Locale.current.languageCode == "ko") {
                    guard let url = URL(string: "https://guide.cosmostation.io/app_wallet_ko.html") else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    present(safariViewController, animated: true, completion: nil)
                } else {
                    guard let url = URL(string: "https://guide.cosmostation.io/app_wallet_en.html") else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    present(safariViewController, animated: true, completion: nil)
                }
                
            } else if(indexPath.row == 1) {
                let url = URL(string: "tg://resolve?domain=cosmostation")
                if(UIApplication.shared.canOpenURL(url!))
                {
                    UIApplication.shared.open(url!, options: [:], completionHandler: nil)
                }else
                {
                    let alert = UIAlertController(title: NSLocalizedString("warnning", comment: ""), message: NSLocalizedString("error_no_telegram", comment: ""), preferredStyle: .alert)
                    let action = UIAlertAction(title: "Download And Install", style: .default, handler: { (UIAlertAction) in
                        let urlAppStore = URL(string: "itms-apps://itunes.apple.com/app/id686449807")
                        if(UIApplication.shared.canOpenURL(urlAppStore!))
                        {
                            UIApplication.shared.open(urlAppStore!, options: [:], completionHandler: nil)
                        }
                        
                    })
                    let actionCancel = UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .cancel, handler: nil)
                    alert.addAction(action)
                    alert.addAction(actionCancel)
                    self.present(alert, animated: true, completion: nil)
                }
                
            } else if(indexPath.row == 2) {
                guard let url = URL(string: "https://www.mintscan.io") else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
                
            } else if(indexPath.row == 3) {
                guard let url = URL(string: "https://www.cosmostation.io") else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
            }
            
        } else if (indexPath.section == 3) {
            if(indexPath.row == 0) {
                if(Locale.current.languageCode == "ko") {
                    guard let url = URL(string: "https://www.cosmostation.io/service_ko.html") else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    present(safariViewController, animated: true, completion: nil)
                } else {
                    guard let url = URL(string: "https://www.cosmostation.io/service_en.html") else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    present(safariViewController, animated: true, completion: nil)
                }
                
                
            } else if(indexPath.row == 1) {
                 onShowToast(NSLocalizedString("prepare", comment: ""))
                
            } else if(indexPath.row == 2) {
                onShowToast(self.versionLabel.text!)
            }
        }
    }
    
    func onUpdateCurrency() {
        currecyLabel.text = BaseData.instance.getCurrencyString()
    }
    
    
    func onShowToast(_ text:String) {
        var style = ToastStyle()
        style.backgroundColor = UIColor.gray
        self.parent?.view.makeToast(text, duration: 2.0, position: .bottom, style: style)
    }
    
    func onShowCurrenyDialog() {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let usdAction = UIAlertAction(title: NSLocalizedString("currency_usd", comment: ""), style: .default, handler: { _ in
            self.onSetCurrency(0)
        })
        usdAction.setValue(UIColor.black, forKey: "titleTextColor")
        
        let eurAction = UIAlertAction(title: NSLocalizedString("currency_eur", comment: ""), style: .default, handler: { _ in
            self.onSetCurrency(1)
        })
        eurAction.setValue(UIColor.black, forKey: "titleTextColor")
        
        let krwAction = UIAlertAction(title: NSLocalizedString("currency_krw", comment: ""), style: .default, handler: { _ in
            self.onSetCurrency(2)
        })
        krwAction.setValue(UIColor.black, forKey: "titleTextColor")
        
        let jpyAction = UIAlertAction(title: NSLocalizedString("currency_jpy", comment: ""), style: .default, handler: { _ in
            self.onSetCurrency(3)
        })
        jpyAction.setValue(UIColor.black, forKey: "titleTextColor")
        
        let cnyAction = UIAlertAction(title: NSLocalizedString("currency_cny", comment: ""), style: .default, handler: { _ in
            self.onSetCurrency(4)
        })
        cnyAction.setValue(UIColor.black, forKey: "titleTextColor")
        
        let btcAction = UIAlertAction(title: NSLocalizedString("currency_btc", comment: ""), style: .default, handler: { _ in
            self.onSetCurrency(5)
        })
        btcAction.setValue(UIColor.black, forKey: "titleTextColor")
        
        showAlert.addAction(usdAction)
        showAlert.addAction(eurAction)
        showAlert.addAction(krwAction)
        showAlert.addAction(jpyAction)
        showAlert.addAction(cnyAction)
        showAlert.addAction(btcAction)
        
        self.present(showAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            showAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onSetCurrency(_ value:Int) {
        if(BaseData.instance.getCurrency() != value) {
            BaseData.instance.setCurrency(value)
            self.onUpdateCurrency()
            NotificationCenter.default.post(name: Notification.Name("refreshCurrency"), object: nil, userInfo: nil)
        }
        
    }
    
    @objc func dismissAlertController(){
        self.dismiss(animated: true, completion: nil)
    }
}
