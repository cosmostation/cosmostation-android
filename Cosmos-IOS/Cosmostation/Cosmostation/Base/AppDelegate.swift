//
//  AppDelegate.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        if UserDefaults.standard.object(forKey: "FirstInstall") == nil {
            KeychainWrapper.standard.removeAllKeys()
            UserDefaults.standard.set(false, forKey: "FirstInstall")
            UserDefaults.standard.synchronize()
        }
        
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        print("applicationDidEnterBackground")
        if( application.topViewController!.isKind(of: IntroViewController.self) ||
            application.topViewController!.isKind(of: PasswordViewController.self)) {
            
        } else {
            if (BaseData.instance.getUsingAppLock()) {
                let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
                passwordVC.mTarget = PASSWORD_ACTION_APP_LOCK
                application.topViewController!.present(passwordVC, animated: true, completion: nil)
            }
        }
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        print("applicationDidBecomeActive")
        if (application.topViewController!.isKind(of: PasswordViewController.self)) {
            NotificationCenter.default.post(name: Notification.Name("ForeGround"), object: nil, userInfo: nil)
        }
    }

    func applicationWillTerminate(_ application: UIApplication) {
    }


}
extension UIApplication{
    var topViewController: UIViewController?{
        if keyWindow?.rootViewController == nil{
            return keyWindow?.rootViewController
        }
        
        var pointedViewController = keyWindow?.rootViewController
        
        while  pointedViewController?.presentedViewController != nil {
            switch pointedViewController?.presentedViewController {
            case let navagationController as UINavigationController:
                pointedViewController = navagationController.viewControllers.last
            case let tabBarController as UITabBarController:
                pointedViewController = tabBarController.selectedViewController
            default:
                pointedViewController = pointedViewController?.presentedViewController
            }
        }
        return pointedViewController
        
    }
}

