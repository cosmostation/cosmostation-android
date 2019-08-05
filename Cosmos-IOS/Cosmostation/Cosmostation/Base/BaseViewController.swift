//
//  BaseViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Toast_Swift

class BaseViewController: UIViewController {
    var waitAlert: UIAlertController?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.startAvoidingKeyboard()
    }
    
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.stopAvoidingKeyboard()
    }
    

    
    public func showWaittingAlert() {
        waitAlert = UIAlertController(title: "", message: "\n\n\n\n", preferredStyle: .alert)
        let image = LoadingImageView(frame: CGRect(x: 0, y: 0, width: 58, height: 58))
        waitAlert!.view.addSubview(image)
        image.translatesAutoresizingMaskIntoConstraints = false
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerX, relatedBy: .equal, toItem: waitAlert!.view, attribute: .centerX, multiplier: 1, constant: 0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerY, relatedBy: .equal, toItem: waitAlert!.view, attribute: .centerY, multiplier: 1, constant: 0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .width, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 58.0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .height, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 58.0))
        self.clearBackgroundColor(of: waitAlert!.view)
        self.present(waitAlert!, animated: true, completion: nil)
        image.onStartAnimation()
        
    }
    
    public func hideWaittingAlert(){
        if (waitAlert != nil) {
            waitAlert?.dismiss(animated: true, completion: nil)
        }
    }
    
    func clearBackgroundColor(of view: UIView) {
        if let effectsView = view as? UIVisualEffectView {
            effectsView.removeFromSuperview()
            return
        }
        
        view.backgroundColor = .clear
        view.subviews.forEach { (subview) in
            self.clearBackgroundColor(of: subview)
        }
    }
    
    
    func onStartMainTab() {
//        print("onStartMainTab")
        let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.window?.rootViewController = mainTabVC
        self.present(mainTabVC, animated: true, completion: nil)
    }
    
    
    func onStartImportMnemonic() {
//        print("onStartImportMnemonic")
        let restoreVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestoreViewController") as! RestoreViewController
        restoreVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(restoreVC, animated: true)
    }
    
    func onStartImportAddress() {
//        print("onStartImportAddress")
        let addAddressVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "AddAddressViewController") as! AddAddressViewController
        addAddressVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(addAddressVC, animated: true)
    }
    
    func onStartCreate() {
//        print("onStartCreate")
        let createVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "CreateViewController") as! CreateViewController
        createVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(createVC, animated: true)
    }
    
    func onStartTxResult(_ response:[String:Any]) {
        print("onStartTxResult")
        let resultVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "GenTxResultViewController") as! GenTxResultViewController
        resultVC.response = response
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(resultVC, animated: true)
    }
    
    func onDeleteWallet(_ account:Account) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            BaseData.instance.deleteAccount(account: account)
            BaseData.instance.deleteBalance(account: account)
            BaseData.instance.deleteBonding(account: account)
            BaseData.instance.deleteUnbonding(account: account)
            
            if (BaseData.instance.selectAllAccounts().count <= 0) {
                //TODO delete password
            } else {
                BaseData.instance.setRecentAccountId(BaseData.instance.selectAllAccounts()[0].account_id)
            }
            DispatchQueue.main.async(execute: {
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("wallet_delete_complete", comment: ""))
                
                let introVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "StartNavigation")
                let appDelegate = UIApplication.shared.delegate as! AppDelegate
                appDelegate.window?.rootViewController = introVC
                self.present(introVC, animated: true, completion: nil)
            });
        }
    }
    
    func onShowToast(_ text:String) {
        var style = ToastStyle()
        style.backgroundColor = UIColor.gray
        self.view.makeToast(text, duration: 2.0, position: .bottom, style: style)
    }
    
    func getPricePath() -> String {
        if (BaseData.instance.getMarket() == 0) {
            return "market_data.current_price." + BaseData.instance.getCurrencyString().lowercased()
        } else {
            return "data.quotes." + BaseData.instance.getCurrencyString() + ".price"
        }
    }
    
    func getPrice24hPath() -> String {
        if (BaseData.instance.getMarket() == 0) {
            return "market_data.price_change_percentage_24h_in_currency." + BaseData.instance.getCurrencyString().lowercased()
        } else {
            return "data.quotes." + BaseData.instance.getCurrencyString() + ".percent_change_24h"
        }
    }
    
}
extension BaseViewController {
    
    func startAvoidingKeyboard() {
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow), name: UIWindow.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.keyboardWillHide), name: UIWindow.keyboardWillHideNotification, object: nil)
    }
    
    func stopAvoidingKeyboard() {
        NotificationCenter.default.removeObserver(self, name: UIWindow.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIWindow.keyboardWillHideNotification, object: nil)
        
    }
    
    @objc func keyboardWillShow(notification: NSNotification) {
        self._onKeyboardFrameWillChangeNotificationReceived(notification as Notification)
    }
    
    @objc func keyboardWillHide(notification: NSNotification){
        self._onKeyboardFrameWillChangeNotificationReceived(notification as Notification)
    }
    
    @objc private func _onKeyboardFrameWillChangeNotificationReceived(_ notification: Notification) {
        guard let userInfo = notification.userInfo,
            let keyboardFrame = (userInfo[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue else {
                return
        }

        let keyboardFrameInView = view.convert(keyboardFrame, from: nil)
        let safeAreaFrame = view.safeAreaLayoutGuide.layoutFrame.insetBy(dx: 0, dy: -additionalSafeAreaInsets.bottom)
        let intersection = safeAreaFrame.intersection(keyboardFrameInView)

        let animationDuration: TimeInterval = (notification.userInfo?[UIResponder.keyboardAnimationDurationUserInfoKey] as? NSNumber)?.doubleValue ?? 0
        let animationCurveRawNSN = notification.userInfo?[UIResponder.keyboardAnimationCurveUserInfoKey] as? NSNumber
        let animationCurveRaw = animationCurveRawNSN?.uintValue ?? UIView.AnimationOptions.curveEaseInOut.rawValue
        let animationCurve = UIView.AnimationOptions(rawValue: animationCurveRaw)

        UIView.animate(withDuration: animationDuration, delay: 0, options: animationCurve, animations: {
            self.additionalSafeAreaInsets.bottom = intersection.height
            self.view.layoutIfNeeded()
        }, completion: nil)
    }
    
    
    @objc func enableUserInteraction() {
    }
    
    @objc func disableUserInteraction() {
    }
}
