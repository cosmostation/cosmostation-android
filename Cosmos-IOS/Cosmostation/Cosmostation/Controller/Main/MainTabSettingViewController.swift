//
//  MainTabSettingViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MainTabSettingViewController: BaseViewController {
    
    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    var mainTabVC: MainTabViewController!
    var userChain: ChainType?

    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.userChain = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.updateTitle()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        if(BaseData.instance.getNeedRefresh()) {
            BaseData.instance.setNeedRefresh(false)
            mainTabVC.onUpdateAccountDB()
            self.updateTitle()
        }
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.onRefreshPrice(_:)),
                                               name: Notification.Name("refreshPrice"),
                                               object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("refreshPrice"), object: nil)
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        
        titleChainName.textColor = WUtils.getChainColor(userChain!)
        if (userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Hub)"
        } else if (userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Hub)"
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            titleChainImg.image = UIImage(named: "binanceChImg")
            titleChainName.text = "(Binance Chain)"
        }
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.onShowAccountSwicth()
    }
    
    @objc func onRefreshPrice(_ notification: NSNotification) {
        mainTabVC.onFetchPriceTic(false)
    }
}
