//
//  DAppsViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class DAppsListViewController: BaseViewController {
    
    @IBOutlet weak var dAppsSegment: UISegmentedControl!
    @IBOutlet weak var swapView: UIView!
    @IBOutlet weak var poolView: UIView!
    @IBOutlet weak var cdpView: UIView!
    @IBOutlet weak var havestView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        swapView.alpha = 1
        poolView.alpha = 0
        cdpView.alpha = 0
        havestView.alpha = 0
    
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        if #available(iOS 13.0, *) {
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            dAppsSegment.selectedSegmentTintColor = TRANS_BG_COLOR_KAVA2
        } else {
            dAppsSegment.tintColor = COLOR_KAVA
        }
    }
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            swapView.alpha = 1
            poolView.alpha = 0
            cdpView.alpha = 0
            havestView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            swapView.alpha = 0
            poolView.alpha = 1
            cdpView.alpha = 0
            havestView.alpha = 0
        } else if sender.selectedSegmentIndex == 2 {
            swapView.alpha = 0
            poolView.alpha = 0
            cdpView.alpha = 1
            havestView.alpha = 0
        } else if sender.selectedSegmentIndex == 3 {
            swapView.alpha = 0
            poolView.alpha = 0
            cdpView.alpha = 0
            havestView.alpha = 1
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_dapp_market", comment: "");
        self.navigationItem.title = NSLocalizedString("title_dapp_market", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

}

extension WUtils {
    static func getKavaCoinDecimal(_ denom:String?) -> Int16 {
        if (denom?.caseInsensitiveCompare(KAVA_MAIN_DENOM) == .orderedSame) {
            return 6;
        } else if (denom?.caseInsensitiveCompare("btc") == .orderedSame) {
            return 8;
        } else if (denom?.caseInsensitiveCompare("usdx") == .orderedSame) {
            return 6;
        } else if (denom?.caseInsensitiveCompare("bnb") == .orderedSame) {
            return 8;
        } else if (denom?.caseInsensitiveCompare("btcb") == .orderedSame || denom?.caseInsensitiveCompare("hbtc") == .orderedSame) {
            return 8;
        } else if (denom?.caseInsensitiveCompare("busd") == .orderedSame) {
            return 8;
        } else if (denom?.caseInsensitiveCompare("xrpb") == .orderedSame || denom?.caseInsensitiveCompare("xrbp") == .orderedSame) {
            return 8;
        } else if (denom?.caseInsensitiveCompare("hard") == .orderedSame) {
            return 6;
        } else if (denom?.caseInsensitiveCompare("swp") == .orderedSame) {
            return 6;
        }
        return 100;
    }
    
    static func getKavaTokenName(_ denom: String) -> String {
        if (denom == KAVA_MAIN_DENOM) {
            return "KAVA"
            
        } else if (denom == KAVA_HARD_DENOM) {
            return "HARD"
            
        } else if (denom == KAVA_USDX_DENOM) {
            return "USDX"
            
        } else if (denom == KAVA_SWAP_DENOM) {
            return "SWP"
            
        } else if (denom == TOKEN_HTLC_KAVA_BNB) {
            return "BNB"
            
        } else if (denom == TOKEN_HTLC_KAVA_XRPB) {
            return "XRPB"
            
        } else if (denom == TOKEN_HTLC_KAVA_BUSD) {
            return "BUSD"
            
        } else if (denom == TOKEN_HTLC_KAVA_BTCB) {
            return "BTCB"
            
        } else if (denom == "btch") {
            return "BTCH"
            
        }
        return denom.uppercased()
    }
    
    static func DpKavaTokenName(_ label: UILabel, _ denom: String) {
        label.text = getKavaTokenName(denom)
        if (denom == KAVA_MAIN_DENOM) {
            label.textColor = COLOR_KAVA
            
        } else if (denom == KAVA_HARD_DENOM) {
            label.textColor = COLOR_HARD
            
        } else if (denom == KAVA_USDX_DENOM) {
            label.textColor = COLOR_USDX
            
        } else if (denom == KAVA_SWAP_DENOM) {
            label.textColor = COLOR_SWP
            
        } else {
            label.textColor = .white
        }
    }
    
    
    static func getKavaPriceFeedSymbol(_ denom: String) -> String {
        if (denom == KAVA_MAIN_DENOM) {
            return "kava:usd"
        } else if (denom == KAVA_HARD_DENOM) {
            return "hard:usd"
        } else if (denom == KAVA_USDX_DENOM) {
            return "usdx:usd"
        } else if (denom == KAVA_SWAP_DENOM) {
            return "swp:usd"
        } else if (denom == TOKEN_HTLC_KAVA_BNB) {
            return "bnb:usd"
        } else if (denom == TOKEN_HTLC_KAVA_XRPB) {
            return "xrp:usd"
        } else if (denom == TOKEN_HTLC_KAVA_BUSD) {
            return "busd:usd"
        } else if (denom.contains("btc")) {
            return "btc:usd"
        }
        return ""
    }
    
    static func getKavaPriceFeed(_ denom: String) -> NSDecimalNumber {
        let feedSymbol = getKavaPriceFeedSymbol(denom)
        if let price = BaseData.instance.mKavaPrice[feedSymbol]?.result.price {
            return NSDecimalNumber.init(string: price)
        }
        return NSDecimalNumber.zero
    }
}
