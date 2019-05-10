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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let appVersion = Bundle.main.infoDictionary!["CFBundleShortVersionString"] as? String {
            self.versionLabel.text = "v " + appVersion
        }
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
                onShowToast(NSLocalizedString("only_USD", comment: ""))
                
            } else if(indexPath.row == 1) {
                onShowToast(NSLocalizedString("only_cmc", comment: ""))
            }
            
        } else if (indexPath.section == 2) {
            if(indexPath.row == 0) {
                let guideVC = GuideViewController(nibName: "GuideViewController", bundle: nil)
                guideVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(guideVC, animated: true)
                
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
    
    
    func onShowToast(_ text:String) {
        var style = ToastStyle()
        style.backgroundColor = UIColor.gray
        self.parent?.view.makeToast(text, duration: 2.0, position: .bottom, style: style)
    }
}
