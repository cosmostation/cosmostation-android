//
//  MainTabWalletViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MainTabWalletViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var walletTableView: UITableView!
    
    var mainTabVC: MainTabViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        
        self.walletTableView.delegate = self
        self.walletTableView.dataSource = self
        self.walletTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.walletTableView.register(UINib(nibName: "WalletAddressCell", bundle: nil), forCellReuseIdentifier: "WalletAddressCell")
        self.walletTableView.register(UINib(nibName: "WalletCosmosCell", bundle: nil), forCellReuseIdentifier: "WalletCosmosCell")
        self.walletTableView.register(UINib(nibName: "WalletIrisCell", bundle: nil), forCellReuseIdentifier: "WalletIrisCell")
        self.walletTableView.register(UINib(nibName: "WalletBnbCell", bundle: nil), forCellReuseIdentifier: "WalletBnbCell")
        self.walletTableView.register(UINib(nibName: "WalletPriceCell", bundle: nil), forCellReuseIdentifier: "WalletPriceCell")
        self.walletTableView.register(UINib(nibName: "WalletInflationCell", bundle: nil), forCellReuseIdentifier: "WalletInflationCell")
        self.walletTableView.register(UINib(nibName: "WalletGuideCell", bundle: nil), forCellReuseIdentifier: "WalletGuideCell")
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }

    
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return 5;
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return 4;
        } else {
            return 0;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return onSetIrisItem(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return onSetBnbItem(tableView, indexPath);
        }
        let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (indexPath.row == 0) {
                return 78;
                
            } else if (indexPath.row == 1) {
                return 258;
                
            } else if (indexPath.row == 2) {
                return 68;
                
            } else if (indexPath.row == 3) {
                return 68;
                
            } else if (indexPath.row == 4) {
                return 153;
            }
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            if (indexPath.row == 0) {
                return 78;
                
            } else if (indexPath.row == 1) {
                return 258;
                
            } else if (indexPath.row == 2) {
                return 68;
                
            } else if (indexPath.row == 3) {
                return 153;
            }
        }
        return 0;
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletCosmosCell? = tableView.dequeueReusableCell(withIdentifier:"WalletCosmosCell") as? WalletCosmosCell
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            return cell!
            
        } else if (indexPath.row == 4) {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            return cell!
        }
        let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
        return cell!
        
    }
    
    func onSetIrisItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletIrisCell? = tableView.dequeueReusableCell(withIdentifier:"WalletIrisCell") as? WalletIrisCell
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            return cell!
            
        } else if (indexPath.row == 4) {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            return cell!
        }
        let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
        return cell!
        
    }
    
    func onSetBnbItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletBnbCell? = tableView.dequeueReusableCell(withIdentifier:"WalletBnbCell") as? WalletBnbCell
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            return cell!
        }
        let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
        return cell!
        
    }
    
    
    
    
    
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }
    
    
    
    
    
}
