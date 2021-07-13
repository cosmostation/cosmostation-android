//
//  OsmosisDAppViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/10.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class OsmosisDAppViewController: BaseViewController {
    
    @IBOutlet weak var dAppsSegment: UISegmentedControl!
    @IBOutlet weak var swapView: UIView!
    @IBOutlet weak var poolView: UIView!
    @IBOutlet weak var farmingView: UIView!

    override func viewDidLoad() {
        super.viewDidLoad()
        swapView.alpha = 1
        poolView.alpha = 0
        farmingView.alpha = 0
    
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        if #available(iOS 13.0, *) {
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.white], for: .selected)
            dAppsSegment.setTitleTextAttributes([.foregroundColor: UIColor.gray], for: .normal)
            dAppsSegment.selectedSegmentTintColor = TRANS_BG_COLOR_OSMOSIS2
            
        } else {
            dAppsSegment.tintColor = COLOR_OSMOSIS
        }
    }
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            swapView.alpha = 1
            poolView.alpha = 0
            farmingView.alpha = 0
        } else if sender.selectedSegmentIndex == 1 {
            swapView.alpha = 0
            poolView.alpha = 1
            farmingView.alpha = 0
        } else if sender.selectedSegmentIndex == 2 {
            swapView.alpha = 0
            poolView.alpha = 0
            farmingView.alpha = 1
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_dapp_osmosis", comment: "");
        self.navigationItem.title = NSLocalizedString("title_dapp_osmosis", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

}

extension WUtils {
    static func DpOsmosisTokenImg(_ imgView: UIImageView, _ denom: String) {
        if (denom == OSMOSIS_MAIN_DENOM) {
            imgView.image = UIImage(named: "tokenOsmosis")
            
        } else if (denom == OSMOSIS_ION_DENOM) {
            imgView.image = UIImage(named: "tokenIon")
            
        } else if (denom.starts(with: "gamm/pool/")) {
            imgView.image = UIImage(named: "tokenPool")
            
        } else if (denom.starts(with: "ibc/")) {
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")) {
                imgView.af_setImage(withURL: URL(string: ibcToken.moniker!)!)
            } else {
                imgView.image = UIImage(named: "tokenDefaultIbc")
            }
        }
    }
    
    static func DpOsmosisTokenName(_ label: UILabel, _ denom: String) {
        if (denom == OSMOSIS_MAIN_DENOM) {
            label.textColor = COLOR_OSMOSIS
            label.text = "OSMO"
            
        } else if (denom == OSMOSIS_ION_DENOM) {
            label.textColor = COLOR_ION
            label.text = "ION"
            
        } else if (denom.starts(with: "gamm/pool/")) {
            label.textColor = .white
            label.text = "GAMM-" + String(denom.split(separator: "/").last!)
            
        } else if (denom.starts(with: "ibc/")) {
            print("denom ", denom)
            label.textColor = .white
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")) {
                print("ibcToken ", ibcToken)
                label.text = ibcToken.display_denom?.uppercased()
            } else {
                label.text = "UnKnown"
            }
            
        }
    }
    
    static func getOsmosisTokenName(_ denom: String) -> String {
        if (denom == OSMOSIS_MAIN_DENOM) {
            return "OSMO"
            
        } else if (denom == OSMOSIS_ION_DENOM) {
            return "ION"
            
        } else if (denom.starts(with: "gamm/pool/")) {
            return "GAMM-" + String(denom.split(separator: "/").last!)
            
        } else if (denom.starts(with: "ibc/")) {
            if let ibcToken = BaseData.instance.getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")) {
                return  ibcToken.display_denom!.uppercased()
            } else {
                return"UnKnown"
            }
        }
        return denom
    }
    
    
    
    static func isAssetHasDenom(_ assets: [Osmosis_Gamm_V1beta1_PoolAsset], _ denom: String?) -> Bool {
        guard let token = assets.filter { $0.token.denom == denom }.first else {
            return false
        }
        return true
    }
    
}
