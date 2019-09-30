//
//  MainTabTokenViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 26/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MainTabTokenViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var totalCard: CardView!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalDenom: UILabel!
    @IBOutlet weak var tokenCnt: UILabel!
    @IBOutlet weak var btnSort: UIView!
    @IBOutlet weak var sortType: UILabel!
    
    @IBOutlet weak var tokenTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mainTabVC: MainTabViewController!
//    var mBalances = Array<Balance>()

    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        
        self.tokenTableView.delegate = self
        self.tokenTableView.dataSource = self
        self.tokenTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenTableView.register(UINib(nibName: "TokenCell", bundle: nil), forCellReuseIdentifier: "TokenCell")
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        refresher.tintColor = UIColor.white
        tokenTableView.addSubview(refresher)
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(onStartSort))
        self.btnSort.addGestureRecognizer(tap)
        
        self.updateView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
    }
    
    func updateView() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        WUtils.setDenomTitle(chainType!, totalDenom)
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        onUpdateTotalCard();
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Hub)"
            totalCard.backgroundColor = TRANS_BG_COLOR_COSMOS
            onFetchCosmosTokenPrice();
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Hub)"
            totalCard.backgroundColor = TRANS_BG_COLOR_IRIS
            onFetchIrisTokenPrice();
        }
    }
    
    @objc func onRequestFetch() {
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.onUpdateTotalCard()
        self.tokenTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    @objc func onStartSort() {
        print("onStartSort")
    }
    
    
    
    func onUpdateTotalCard() {
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let allAtom = WUtils.getAllAtom(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount(allAtom.stringValue, totalAmount.font, 6, chainType!)
            totalValue.attributedText = WUtils.dpAtomValue(allAtom, mainTabVC.mPriceTic?.value(forKeyPath: getPricePath()) as? Double, totalValue.font)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let allIris = WUtils.getAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.dpAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator, totalAmount.font, 6, chainType!)
            totalValue.attributedText = WUtils.dpIrisValue(allIris, mainTabVC.mPriceTic?.value(forKeyPath: getPricePath()) as? Double, totalValue.font)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
        }
    }
    
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mainTabVC.mBalances.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath)
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return onSetIrisItems(tableView, indexPath)
        } else {
            return onSetBnbItems(tableView, indexPath)
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == COSMOS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "atom_ic")
            cell?.tokenSymbol.text = "ATOM"
            cell?.tokenSymbol.textColor = COLOR_ATOM
            cell?.tokenTitle.text = ""
            cell?.tokenDescription.text = balance.balance_denom
            let allAtom = WUtils.getAllAtom(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount(allAtom.stringValue, cell!.tokenAmount.font, 6, chainType!)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allAtom, mainTabVC.mPriceTic?.value(forKeyPath: getPricePath()) as? Double, cell!.tokenValue.font)

        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetIrisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if let irisToken = WUtils.getIrisToken(mainTabVC.mIrisTokenList, balance) {
            cell?.tokenSymbol.text = irisToken.base_token?.symbol.uppercased()
            cell?.tokenTitle.text = "(" + irisToken.base_token!.id + ")"
            cell?.tokenDescription.text = irisToken.base_token?.name
            if (balance.balance_denom == IRIS_MAIN_DENOM) {
                cell?.tokenImg.image = UIImage(named: "irisTokenImg")
                cell?.tokenSymbol.textColor = COLOR_IRIS
                
                let allIris = WUtils.getAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator)
                cell?.tokenAmount.attributedText = WUtils.displayAmount(allIris.stringValue, cell!.tokenAmount.font, 6, chainType!)
                cell?.tokenValue.attributedText = WUtils.dpIrisValue(allIris, mainTabVC.mPriceTic?.value(forKeyPath: getPricePath()) as? Double, cell!.tokenValue.font)
                
            } else {
                cell?.tokenImg.image = UIImage(named: "tokenIc")
                cell?.tokenSymbol.textColor = UIColor.white
                
                cell?.tokenAmount.attributedText = WUtils.displayIrisToken(balance.balance_amount, cell!.tokenAmount.font, 6, irisToken.base_token!.decimal)
                cell?.tokenValue.attributedText = WUtils.dpValue(NSDecimalNumber.zero, cell!.tokenValue.font)
            }
        }
        
        return cell!
    }
    
    func onSetBnbItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        return cell!
    }
    
    func onFetchCosmosTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchIrisTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchBnbTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }
}
