//
//  BaseViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import MBProgressHUD

class BaseViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    public func showWaittingAlert() {
        let loadingNotification = MBProgressHUD.showAdded(to: self.view, animated: true)
        loadingNotification.bezelView.color = UIColor(hexString: "#6D6D6D")
        loadingNotification.bezelView.style = .blur
        loadingNotification.contentColor = UIColor(hexString: "#2359B8")
        loadingNotification.mode = MBProgressHUDMode.indeterminate
        loadingNotification.label.text = "Loading"
        
    }
    
    public func hideWaittingAlert() {
        MBProgressHUD.hideAllHUDs(for: self.view, animated: true)
    }
}
