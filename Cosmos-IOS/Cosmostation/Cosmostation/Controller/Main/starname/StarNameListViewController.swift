//
//  StarNameListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/10.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StarNameListViewController: BaseViewController {
    
    @IBOutlet weak var myStarNameSegment: UISegmentedControl!
    @IBOutlet weak var myDomainView: UIView!
    @IBOutlet weak var myAccountView: UIView!
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            myDomainView.alpha = 1
            myAccountView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            myDomainView.alpha = 0
            myAccountView.alpha = 1
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        myDomainView.alpha = 1
        myAccountView.alpha = 0
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        if #available(iOS 13.0, *) {
            myStarNameSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            myStarNameSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            myStarNameSegment.selectedSegmentTintColor = TRANS_BG_COLOR_IOV2
            
        } else {
            myStarNameSegment.tintColor = COLOR_IOV
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_starname_list", comment: "");
        self.navigationItem.title = NSLocalizedString("title_starname_list", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}


extension WUtils {
    static func isStarnameValidStarName(_ starname: String) -> Bool {
        let starNameRegEx = "[0-9a-z.-]{0,64}+\\*[a-z0-9.-]{3,16}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static func isStarnameValidDomain(_ starname: String) -> Bool {
        let starNameRegEx = "[a-z0-9]{4,32}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static func isStarnameValidAccount(_ starname: String) -> Bool {
        let starNameRegEx = "[0-9a-z.-]{1,63}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static public func getStarNameRegisterDomainFee(_ domain: String, _ type: String) -> NSDecimalNumber {
        let starNameFee = BaseData.instance.mStarNameFee_gRPC
        if (starNameFee == nil) { return NSDecimalNumber.zero }
        
        var feeResult = NSDecimalNumber.zero
        if (domain.isEmpty || domain.count <= 3) {
            return feeResult
        } else if (domain.count == 4) {
            feeResult = NSDecimalNumber.init(string: starNameFee?.registerDomain4).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        } else if (domain.count == 5) {
            feeResult = NSDecimalNumber.init(string: starNameFee?.registerDomain5).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        } else {
            feeResult = NSDecimalNumber.init(string: starNameFee?.registerDomainDefault).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        }

        if (type == "open") {
            feeResult = feeResult.multiplying(by: NSDecimalNumber.init(string: starNameFee?.registerOpenDomainMultiplier).multiplying(byPowerOf10: -18))
        }
        return feeResult
    }
    
    static public func getStarNameRegisterAccountFee(_ type: String) -> NSDecimalNumber {
        let starNameFee = BaseData.instance.mStarNameFee_gRPC
        if (starNameFee == nil) { return NSDecimalNumber.zero }
        if (type == "open") {
            return NSDecimalNumber.init(string: starNameFee?.registerAccountOpen).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        } else {
            return NSDecimalNumber.init(string: starNameFee?.registerAccountClosed).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        }
    }
    
    static public func getStarNameRenewDomainFee(_ domain: String, _ type: String) -> NSDecimalNumber {
        let starNameFee = BaseData.instance.mStarNameFee_gRPC
        if (starNameFee == nil) { return NSDecimalNumber.zero }
        if (type == "open") {
            return NSDecimalNumber.init(string: starNameFee?.renewDomainOpen).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        } else {
            let registerFee = getStarNameRegisterDomainFee(domain, "closed")
            let addtionalFee = NSDecimalNumber.init(string: starNameFee?.registerAccountClosed).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
            return registerFee.adding(addtionalFee)
        }
    }
    
    static public func getStarNameRenewAccountFee(_ type: String) -> NSDecimalNumber {
        let starNameFee = BaseData.instance.mStarNameFee_gRPC
        if (starNameFee == nil) { return NSDecimalNumber.zero }
        if (type == "open") {
            return NSDecimalNumber.init(string: starNameFee?.registerAccountOpen).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        } else {
            return NSDecimalNumber.init(string: starNameFee?.registerAccountClosed).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
        }
    }
    
    static public func getReplaceFee() -> NSDecimalNumber {
        let starNameFee = BaseData.instance.mStarNameFee_gRPC
        if (starNameFee == nil) { return NSDecimalNumber.zero }
        return NSDecimalNumber.init(string: starNameFee?.replaceAccountResources).dividing(by: NSDecimalNumber.init(string: starNameFee?.feeCoinPrice), withBehavior: WUtils.handler0Down)
    }
    
    static public func getRenewPeriod(_ type: String) -> Int64 {
        let starNameConfig = BaseData.instance.mStarNameConfig_gRPC
        if (type == IOV_MSG_TYPE_RENEW_DOMAIN) {
            if let seconds = starNameConfig?.domainRenewalPeriod.seconds { return seconds * 1000 }
        } else if (type == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            if let seconds = starNameConfig?.accountRenewalPeriod.seconds { return seconds * 1000 }
        }
        return 0
    }
    
    static func getStarNameRegisterDomainExpireTime() -> Int64 {
        let starNameConfig = BaseData.instance.mStarNameConfig_gRPC
        if let seconds = starNameConfig?.domainRenewalPeriod.seconds {
            return seconds * 1000
        }
        return 0
    }
    
    static func checkStarnameWithResource(_ chainType: ChainType, _ response: Starnamed_X_Starname_V1beta1_QueryStarnameResponse) -> String? {
        for resource in response.account.resources {
            if (isValidChainAddress(chainType, resource.resource)) {
                return resource.resource
            }
        }
        return nil
    }
    
    static func getExportResource(_ accounts: Array<Account>) -> ExportStarname {
        var result = ExportStarname.init()
        result.type = "starname"
        accounts.forEach { (account) in
            var resource = ExportStarname.ExportResource.init()
            if (WUtils.getChainType(account.account_base_chain) == ChainType.COSMOS_MAIN) {
                resource.ticker = "atom"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.IRIS_MAIN) {
                resource.ticker = "iris"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.KAVA_MAIN) {
                resource.ticker = "kava"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.BINANCE_MAIN) {
                resource.ticker = "bnb"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.IOV_MAIN) {
                resource.ticker = "iov"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.BAND_MAIN) {
                resource.ticker = "band"
                resource.address = account.account_address
                result.addresses.append(resource)
            }
        }
        return result;
        
    }
}
