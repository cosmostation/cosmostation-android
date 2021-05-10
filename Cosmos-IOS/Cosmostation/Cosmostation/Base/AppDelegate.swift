//
//  AppDelegate.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper
import Firebase
import UserNotifications

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?
    let gcmMessageIDKey = "gcm.message_id"
    var userInfo:[AnyHashable : Any]?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        FirebaseApp.configure()
        BaseData.instance.copySalt = UUID().uuidString
        if UserDefaults.standard.object(forKey: "FirstInstall") == nil {
            KeychainWrapper.standard.removeAllKeys()
            UserDefaults.standard.set(false, forKey: "FirstInstall")
            UserDefaults.standard.synchronize()
        }
        
        if #available(iOS 10.0, *) {
            UNUserNotificationCenter.current().delegate = self
            let authOptions: UNAuthorizationOptions = [.alert, .badge, .sound]
            UNUserNotificationCenter.current().requestAuthorization(options: authOptions, completionHandler: {_, _ in })
        } else {
            let settings: UIUserNotificationSettings = UIUserNotificationSettings(types: [.alert, .badge, .sound], categories: nil)
            application.registerUserNotificationSettings(settings)
        }

        application.registerForRemoteNotifications()
        return true
    }
    
    func applicationWillResignActive(_ application: UIApplication) {
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        if(application.topViewController!.isKind(of: IntroViewController.self) ||
            application.topViewController!.isKind(of: PasswordViewController.self)) {
            
        } else {
            if (BaseData.instance.getUsingAppLock() && BaseData.instance.hasPassword()) {
                let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
                passwordVC.mTarget = PASSWORD_ACTION_APP_LOCK
                if #available(iOS 13.0, *) {
                    passwordVC.isModalInPresentation = true
                }
                application.topViewController!.present(passwordVC, animated: true, completion: nil)
            }
        }
        
        if (KeychainWrapper.standard.hasValue(forKey: BaseData.instance.copySalt!)) {
            KeychainWrapper.standard.removeObject(forKey: BaseData.instance.copySalt!)
        }
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        if (application.topViewController!.isKind(of: PasswordViewController.self)) {
            NotificationCenter.default.post(name: Notification.Name("ForeGround"), object: nil, userInfo: nil)
        } else {
            if let notifyto = userInfo?["notifyto"] as? String {
                userInfo = nil
                let notiAccount = BaseData.instance.selectAccountByAddress(address: notifyto)
                if (notiAccount != nil) {
                    BaseData.instance.setRecentAccountId(notiAccount!.account_id)
                    BaseData.instance.setLastTab(2)
                    DispatchQueue.main.async(execute: {
                        let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
                        let rootVC = self.window?.rootViewController!
                        self.window?.rootViewController = mainTabVC
                        rootVC?.present(mainTabVC, animated: true, completion: nil)
                    })
                }
            }
        }
    }

    func applicationWillTerminate(_ application: UIApplication) {
        if (KeychainWrapper.standard.hasValue(forKey: BaseData.instance.copySalt!)) {
            KeychainWrapper.standard.removeObject(forKey: BaseData.instance.copySalt!)
        }
    }
    
    
    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
//        let token = deviceToken.reduce("") { $0 + String(format: "%02.2hhx", $1) }
//        print("token ", token)
    }
    
    func application(_ application: UIApplication, didFailToRegisterForRemoteNotificationsWithError error: Error) {
    }
    
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable: Any]) {
        if let messageID = userInfo[gcmMessageIDKey] {
            print("Message ID: \(messageID)")
        }
    }
    
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable: Any], fetchCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void) {
        if let messageID = userInfo[gcmMessageIDKey] {
            print(" Message ID: \(messageID)")
        }
        print("fetch: ",userInfo)
        completionHandler(UIBackgroundFetchResult.newData)
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

// [START ios_10_message_handling]
@available(iOS 10, *)
extension AppDelegate : UNUserNotificationCenterDelegate {
    // Receive displayed notifications for iOS 10 devices.
    func userNotificationCenter(_ center: UNUserNotificationCenter, willPresent notification: UNNotification, withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
        let userInfo = notification.request.content.userInfo
        NotificationCenter.default.post(name: Notification.Name("pushNoti"), object: nil, userInfo: userInfo)
        completionHandler([])
    }
    
    func userNotificationCenter(_ center: UNUserNotificationCenter, didReceive response: UNNotificationResponse, withCompletionHandler completionHandler: @escaping () -> Void) {
        let userInfo = response.notification.request.content.userInfo
        if let notifyto = userInfo["notifyto"] as? String {
            self.userInfo = userInfo
        }
        completionHandler()
    }
}
// [END ios_10_message_handling]

extension AppDelegate : MessagingDelegate {
//    func messaging(_ messaging: Messaging, didReceive remoteMessage: MessagingRemoteMessage) {
//        print("msg didReceive \(remoteMessage.appData)")
//    }
//    
//    func messaging(_ messaging: Messaging, didReceiveRegistrationToken fcmToken: String) {
//        print("msg didReceiveRegistrationToken : ", fcmToken)
//    }
    
}

