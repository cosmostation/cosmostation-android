//
//  MainTabSettingViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MainTabSettingViewController: BaseViewController {
    
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    var mainTabVC: MainTabViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.updateTitle()
    }
    

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") { titleWalletName.text = "Wallet " + String(mainTabVC.mAccount.account_id)
        } else { titleWalletName.text = mainTabVC.mAccount.account_nick_name }
        
        if(mainTabVC.mAccount.account_base_chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN.rawValue) {
            titleChainName.text = "(Cosmos Hub)"
        } else {
            titleChainName.text = ""
        }
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }
}
