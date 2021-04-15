//
//  MainTabWalletViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import Floaty
import SafariServices

class MainTabWalletViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, FloatyDelegate, QrScannerDelegate, PasswordViewDelegate {

    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleAlarmBtn: UIButton!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var walletTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mainTabVC: MainTabViewController!
    var wcURL:String?
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?

    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.mInflation = BaseData.instance.mInflation
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        
        self.walletTableView.delegate = self
        self.walletTableView.dataSource = self
        self.walletTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.walletTableView.register(UINib(nibName: "WalletAddressCell", bundle: nil), forCellReuseIdentifier: "WalletAddressCell")
        self.walletTableView.register(UINib(nibName: "WalletCosmosCell", bundle: nil), forCellReuseIdentifier: "WalletCosmosCell")
        self.walletTableView.register(UINib(nibName: "WalletIrisCell", bundle: nil), forCellReuseIdentifier: "WalletIrisCell")
        self.walletTableView.register(UINib(nibName: "WalletBnbCell", bundle: nil), forCellReuseIdentifier: "WalletBnbCell")
        self.walletTableView.register(UINib(nibName: "WalletKavaCell", bundle: nil), forCellReuseIdentifier: "WalletKavaCell")
        self.walletTableView.register(UINib(nibName: "WalletIovCell", bundle: nil), forCellReuseIdentifier: "WalletIovCell")
        self.walletTableView.register(UINib(nibName: "WalletBandCell", bundle: nil), forCellReuseIdentifier: "WalletBandCell")
        self.walletTableView.register(UINib(nibName: "WalletSecretCell", bundle: nil), forCellReuseIdentifier: "WalletSecretCell")
        self.walletTableView.register(UINib(nibName: "WalletOkCell", bundle: nil), forCellReuseIdentifier: "WalletOkCell")
        self.walletTableView.register(UINib(nibName: "WalletCertikCell", bundle: nil), forCellReuseIdentifier: "WalletCertikCell")
        self.walletTableView.register(UINib(nibName: "WalletAkashCell", bundle: nil), forCellReuseIdentifier: "WalletAkashCell")
        self.walletTableView.register(UINib(nibName: "WalletPersisCell", bundle: nil), forCellReuseIdentifier: "WalletPersisCell")
        self.walletTableView.register(UINib(nibName: "WalletSentinelCell", bundle: nil), forCellReuseIdentifier: "WalletSentinelCell")
        self.walletTableView.register(UINib(nibName: "WalletFetchCell", bundle: nil), forCellReuseIdentifier: "WalletFetchCell")
        self.walletTableView.register(UINib(nibName: "WalletCrytoCell", bundle: nil), forCellReuseIdentifier: "WalletCrytoCell")
        self.walletTableView.register(UINib(nibName: "WalletUnbondingInfoCellTableViewCell", bundle: nil), forCellReuseIdentifier: "WalletUnbondingInfoCellTableViewCell")
        self.walletTableView.register(UINib(nibName: "WalletPriceCell", bundle: nil), forCellReuseIdentifier: "WalletPriceCell")
        self.walletTableView.register(UINib(nibName: "WalletInflationCell", bundle: nil), forCellReuseIdentifier: "WalletInflationCell")
        self.walletTableView.register(UINib(nibName: "WalletGuideCell", bundle: nil), forCellReuseIdentifier: "WalletGuideCell")
        self.walletTableView.register(UINib(nibName: "EventStakeDropCell", bundle: nil), forCellReuseIdentifier: "EventStakeDropCell")
        
        self.walletTableView.rowHeight = UITableView.automaticDimension
        self.walletTableView.estimatedRowHeight = UITableView.automaticDimension
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        refresher.tintColor = UIColor.white
        walletTableView.addSubview(refresher)
    
        self.updateFloaty()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceFetchDone(_:)), name: Notification.Name("onPriceFetchDone"), object: nil)
        self.updateTitle()
        self.walletTableView.reloadData()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    
    func updateTitle() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        if (chainType! == ChainType.COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Mainnet)"
        } else if (chainType! == ChainType.IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            titleChainImg.image = UIImage(named: "binanceChImg")
            titleChainName.text = "(Binance Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.KAVA_MAIN) {
            titleChainImg.image = UIImage(named: "kavaImg")
            titleChainName.text = "(Kava Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.IOV_MAIN) {
            titleChainImg.image = UIImage(named: "iovChainImg")
            titleChainName.text = "(Starname Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.BAND_MAIN) {
            titleChainImg.image = UIImage(named: "bandChainImg")
            titleChainName.text = "(Band Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SECRET_MAIN) {
            titleChainImg.image = UIImage(named: "secretChainImg")
            titleChainName.text = "(Secret Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CERTIK_MAIN) {
            titleChainImg.image = UIImage(named: "certikChainImg")
            titleChainName.text = "(Certik Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.AKASH_MAIN) {
            titleChainImg.image = UIImage(named: "akashChainImg")
            titleChainName.text = "(Akash Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.OKEX_MAIN) {
            titleChainImg.image = UIImage(named: "okexChainImg")
            titleChainName.text = "(OKex Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            titleChainImg.image = UIImage(named: "chainpersistence")
            titleChainName.text = "(Persistence Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            titleChainImg.image = UIImage(named: "chainsentinel")
            titleChainName.text = "(Sentinel Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.FETCH_MAIN) {
            titleChainImg.image = UIImage(named: "chainfetchai")
            titleChainName.text = "(Fetch.ai Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CRYTO_MAIN) {
            titleChainImg.image = UIImage(named: "chaincrypto")
            titleChainName.text = "(Cryto.org Mainnet)"
            titleAlarmBtn.isHidden = true
        }
        
        else if (chainType! == ChainType.COSMOS_TEST) {
            titleChainImg.image = UIImage(named: "cosmosTestChainImg")
            titleChainName.text = "(StarGate Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.IRIS_TEST) {
            titleChainImg.image = UIImage(named: "irisTestChainImg")
            titleChainName.text = "(Bifrost Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.BINANCE_TEST) {
            titleChainImg.image = UIImage(named: "binancetestnet")
            titleChainName.text = "(Binance Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.KAVA_TEST) {
            titleChainImg.image = UIImage(named: "kavaTestImg")
            titleChainName.text = "(Kava Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.IOV_TEST) {
            titleChainImg.image = UIImage(named: "iovTestnetImg")
            titleChainName.text = "(Starname Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.OKEX_TEST) {
            titleChainImg.image = UIImage(named: "okexTestnetImg")
            titleChainName.text = "(Okex Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CERTIK_TEST) {
            titleChainImg.image = UIImage(named: "certikTestnetImg")
            titleChainName.text = "(Certik Testnet)"
            titleAlarmBtn.isHidden = true
        }
        UNUserNotificationCenter.current().getNotificationSettings { (settings) in
            if settings.authorizationStatus == .authorized {
                DispatchQueue.main.async {
                    if (self.mainTabVC.mAccount.account_push_alarm) {
                        self.titleAlarmBtn.setImage(UIImage(named: "notificationsIc"), for: .normal)
                    } else {
                        self.titleAlarmBtn.setImage(UIImage(named: "notificationsIcOff"), for: .normal)
                    }
                }
            } else {
                DispatchQueue.main.async {
                    self.titleAlarmBtn.setImage(UIImage(named: "notificationsIcOff"), for: .normal)
                }
            }
        }
        
    }
    
    func updateFloaty() {
        let floaty = Floaty()
        if (chainType! == ChainType.PERSIS_MAIN) {
            floaty.buttonImage = UIImage.init(named: "btnSendPersistence")
            floaty.buttonColor = .black
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            floaty.buttonImage = UIImage.init(named: "sendImg")
            floaty.buttonColor = COLOR_SENTINEL_DARK2
        } else {
            floaty.buttonImage = UIImage.init(named: "sendImg")
            floaty.buttonColor = WUtils.getChainColor(chainType)
        }
        floaty.fabDelegate = self
        self.view.addSubview(floaty)
    }

    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.mInflation = BaseData.instance.mInflation
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        self.walletTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        self.walletTableView.reloadData()
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        self.onClickMainSend()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.COSMOS_MAIN) {
            return 5;
        } else if  (chainType == ChainType.IRIS_MAIN) {
            return 5;
        } else if  (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            return 5;
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return 4;
        } else if (chainType == ChainType.BAND_MAIN) {
            return 5;
        } else if (chainType == ChainType.SECRET_MAIN) {
            return 5;
        } else if (chainType == ChainType.IOV_MAIN  || chainType == ChainType.IOV_TEST ) {
            return 5;
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return 4;
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            return 5;
        } else if (chainType == ChainType.AKASH_MAIN) {
            return 5;
        } else if (chainType == ChainType.PERSIS_MAIN) {
            return 5;
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            return 5;
        } else if (chainType == ChainType.FETCH_MAIN) {
            return 5;
        } else if (chainType == ChainType.CRYTO_MAIN) {
            return 5;
        } else if (chainType == ChainType.COSMOS_TEST || chainType == ChainType.IRIS_TEST) {
            return 5;
        } else {
            return 0;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType == ChainType.COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath);
        } else if (chainType == ChainType.IRIS_MAIN) {
            return onSetIrisItem(tableView, indexPath);
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return onSetBnbItem(tableView, indexPath);
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            return onSetKavaItem(tableView, indexPath);
        } else if (chainType == ChainType.BAND_MAIN) {
            return onSetBandItem(tableView, indexPath);
        } else if (chainType == ChainType.SECRET_MAIN) {
            return onSetSecretItem(tableView, indexPath);
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            return onSetIovItems(tableView, indexPath);
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return onSetOKexItems(tableView, indexPath);
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            return onSetCertikItems(tableView, indexPath);
        } else if (chainType == ChainType.AKASH_MAIN) {
            return onSetAkashItems(tableView, indexPath);
        } else if (chainType == ChainType.PERSIS_MAIN) {
            return onSetPersisItems(tableView, indexPath);
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            return onSetSentinelItems(tableView, indexPath);
        } else if (chainType == ChainType.FETCH_MAIN) {
            return onSetFetchItems(tableView, indexPath);
        } else if (chainType == ChainType.CRYTO_MAIN) {
            return onSetCrytoItems(tableView, indexPath);
        } else if (chainType == ChainType.COSMOS_TEST) {
            return onSetCosmosTestItems(tableView, indexPath);
        } else if (chainType == ChainType.IRIS_TEST) {
            return onSetIrisTestItems(tableView, indexPath);
        } else {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCosmosCell") as? WalletCosmosCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetIrisItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletIrisCell") as? WalletIrisCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetBnbItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletBnbCell") as? WalletBnbCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionWC = { self.onClickWalletConect() }
            cell?.actionBep3 = { self.onClickBep3Send(BNB_MAIN_DENOM) }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetKavaItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletKavaCell") as? WalletKavaCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionCdp = { self.onClickCdp() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetIovItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletIovCell") as? WalletIovCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionNameService = { self.onClickStarName() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetBandItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletBandCell") as? WalletBandCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetSecretItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletSecretCell") as? WalletSecretCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetOKexItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletOkCell") as? WalletOkCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDeposit = { self.onClickOkDeposit() }
            cell?.actionWithdraw = { self.onClickOkWithdraw() }
            cell?.actionVoteforVal = { self.onClickOkVoteVal() }
            cell?.actionVote = { self.onShowToast(NSLocalizedString("error_not_yet", comment: "")) }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetCertikItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCertikCell") as? WalletCertikCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetAkashItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAkashCell") as? WalletAkashCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetPersisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPersisCell") as? WalletPersisCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetSentinelItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletSentinelCell") as? WalletSentinelCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetFetchItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletFetchCell") as? WalletFetchCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetCrytoItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCrytoCell") as? WalletCrytoCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetCosmosTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCosmosCell") as? WalletCosmosCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetIrisTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionShare = { self.onClickActionShare() }
            cell?.actionWebLink = { self.onClickActionLink() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletIrisCell") as? WalletIrisCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(mainTabVC.mAccount, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.onShowAccountSwicth()
    }
    
    
    @IBAction func onClickAlaram(_ sender: UIButton) {
        if (sender.imageView?.image == UIImage(named: "notificationsIcOff")) {
            UNUserNotificationCenter.current().getNotificationSettings { (settings) in
                if settings.authorizationStatus == .authorized {
                    DispatchQueue.main.async {
                        self.showWaittingAlert()
                        self.onToggleAlarm(self.mainTabVC.mAccount!) { (success) in
                            self.mainTabVC.onUpdateAccountDB()
                            self.updateTitle()
                            self.dismissAlertController()
                        }
                    }
                    
                } else {
                    let alertController = UIAlertController(title: NSLocalizedString("permission_push_title", comment: ""), message: NSLocalizedString("permission_push_msg", comment: ""), preferredStyle: .alert)
                    let settingsAction = UIAlertAction(title: NSLocalizedString("settings", comment: ""), style: .default) { (_) -> Void in
                        guard let settingsUrl = URL(string: UIApplication.openSettingsURLString) else {
                            return
                        }
                        if UIApplication.shared.canOpenURL(settingsUrl) {
                            UIApplication.shared.open(settingsUrl, completionHandler: { (success) in
                            })
                        }
                    }
                    let cancelAction = UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: nil)
                    alertController.addAction(cancelAction)
                    alertController.addAction(settingsAction)
                    DispatchQueue.main.async {
                        self.present(alertController, animated: true, completion: nil)
                    }
                }
            }
        } else {
            DispatchQueue.main.async {
                self.showWaittingAlert()
                self.onToggleAlarm(self.mainTabVC.mAccount!) { (success) in
                    self.mainTabVC.onUpdateAccountDB()
                    self.updateTitle()
                    self.dismissAlertController()
                }
            }
        }
    }
    
    
    func onClickActionShare() {
        var nickName:String?
        if (mainTabVC.mAccount.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            nickName = mainTabVC.mAccount.account_nick_name
        }
        self.shareAddress(mainTabVC.mAccount.account_address, nickName!)
    }
    
    func onClickActionLink() {
        let link = WUtils.getAccountExplorer(chainType!, mainTabVC.mAccount.account_address)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    func onClickValidatorList() {
        print("onClickValidatorList")
        let validatorListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "ValidatorListViewController") as! ValidatorListViewController
        validatorListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(validatorListVC, animated: true)
    }
    
    func onClickVoteList() {
        if (chainType! == ChainType.KAVA_TEST) {
            return
        }
        let voteListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteListViewController") as! VoteListViewController
        voteListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(voteListVC, animated: true)
    }
    
    func onClickWalletConect() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        self.onStartQrCode()
    }
    
    func onClickBep3Send(_ denom: String) {
        if (!SUPPORT_BEP3_SWAP) {
            self.onShowToast(NSLocalizedString("error_bep3_swap_temporary_disable", comment: ""))
            return
        }
        
        if (chainType! == ChainType.BINANCE_TEST || chainType! == ChainType.KAVA_TEST) {
            self.onShowToast(NSLocalizedString("error_bep3_swap_temporary_disable", comment: ""))
            return
        }
        
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }

        let balances = BaseData.instance.selectBalanceById(accountId: self.mainTabVC.mAccount.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: GAS_FEE_BNB_TRANSFER)).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
        }

        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_SWAP
        txVC.mHtlcDenom = denom
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickStarName() {
        let starnameListVC = UIStoryboard(name: "StarName", bundle: nil).instantiateViewController(withIdentifier: "StarNameListViewController") as! StarNameListViewController
        starnameListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(starnameListVC, animated: true)
    }
    
    func onClickCdp() {
        let dAppVC = UIStoryboard(name: "Kava", bundle: nil).instantiateViewController(withIdentifier: "DAppsListViewController") as! DAppsListViewController
        dAppVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(dAppVC, animated: true)
    }
    
    func onClickOkDeposit() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(mainTabVC.mBalances, OKEX_MAIN_DENOM).compare(NSDecimalNumber(string: "0.1")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_to_deposit", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OK_MSG_TYPE_DEPOSIT
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickOkWithdraw() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(mainTabVC.mBalances, OKEX_MAIN_DENOM).compare(NSDecimalNumber(string: "0.1")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        if (WUtils.okDepositAmount(BaseData.instance.mOkStaking).compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_to_withdraw", comment: ""))
            return
            
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OK_MSG_TYPE_WITHDRAW
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    //no need yet
    func onClickOkVoteValMode() {
        let okVoteTypeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        okVoteTypeAlert.addAction(UIAlertAction(title: NSLocalizedString("str_vote_direct", comment: ""), style: .default, handler: { _ in
            self.onClickOkVoteVal()
        }))
        okVoteTypeAlert.addAction(UIAlertAction(title: NSLocalizedString("str_vote_agent", comment: ""), style: .default, handler: { _ in
            self.onShowToast(NSLocalizedString("prepare", comment: ""))
        }))
        self.present(okVoteTypeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            okVoteTypeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onClickOkVoteVal() {
        let okValidatorListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "OkValidatorListViewController") as! OkValidatorListViewController
        okValidatorListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(okValidatorListVC, animated: true)
    }
    
    func onClickAprHelp() {
        let helpAlert = UIAlertController(title: NSLocalizedString("str_apr_help_title", comment: ""), message: NSLocalizedString("str_apr_help_msg", comment: ""), preferredStyle: .alert)
        helpAlert.addAction(UIAlertAction(title: NSLocalizedString("ok", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(helpAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            helpAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onClickGuide1() {
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.COSMOS_TEST) {
            if(Locale.current.languageCode == "ko") {
                guard let url = URL(string: "https://www.cosmostation.io/files/guide_KO.pdf") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://www.cosmostation.io/files/guide_EN.pdf") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.IRIS_MAIN || chainType! == ChainType.IRIS_TEST) {
            guard let url = URL(string: "https://www.irisnet.org") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: "https://www.binance.org") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: "https://www.kava.io/registration/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: "https://bandprotocol.com/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SECRET_MAIN) {
            guard let url = URL(string: "https://scrt.network") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            guard let url = URL(string: "https://iov.one/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: "https://www.okex.com/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: "https://www.certik.foundation/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.AKASH_MAIN) {
            guard let url = URL(string: "https://akash.network/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            guard let url = URL(string: "https://persistence.one/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            guard let url = URL(string: "https://sentinel.co/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.FETCH_MAIN) {
            guard let url = URL(string: "https://fetch.ai/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CRYTO_MAIN) {
            guard let url = URL(string: "https://crypto.org/") else { return }
            self.onShowSafariWeb(url)
        }
        
    }
    
    func onClickGuide2() {
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.COSMOS_TEST) {
            if(Locale.current.languageCode == "ko") {
                guard let url = URL(string: "https://guide.cosmostation.io/app_wallet_ko.html") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://guide.cosmostation.io/app_wallet_en.html") else { return }
                self.onShowSafariWeb(url)
            }

        } else if (chainType! == ChainType.IRIS_MAIN || chainType! == ChainType.IRIS_TEST) {
            guard let url = URL(string: "https://medium.com/irisnet-blog") else { return }
            self.onShowSafariWeb(url)

        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: "https://medium.com/@binance") else { return }
            self.onShowSafariWeb(url)

        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: "https://medium.com/kava-labs") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: "https://medium.com/bandprotocol") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SECRET_MAIN) {
            guard let url = URL(string: "https://blog.scrt.network") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            guard let url = URL(string: "https://medium.com/iov-internet-of-values") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: "https://www.okex.com/community") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: "https://www.certik.foundation/blog") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.AKASH_MAIN) {
            guard let url = URL(string: "https://akash.network/blog/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            guard let url = URL(string: "https://medium.com/persistence-blog") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            guard let url = URL(string: "https://medium.com/sentinel") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.FETCH_MAIN) {
            guard let url = URL(string: "https://fetch.ai/blog/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CRYTO_MAIN) {
            guard let url = URL(string: "https://crypto.org/community") else { return }
            self.onShowSafariWeb(url)
        }
    }
    
    func onClickMarketInfo() {
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.COSMOS_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/cosmos") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/cosmos/") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.IRIS_MAIN || chainType! == ChainType.IRIS_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/irisnet") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/irisnet") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/binancecoin") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/binance-coin") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/kava") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/kava") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/band-protocol") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SECRET_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/secret") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/starname") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/certik") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.AKASH_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/akash-network") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/sentinel-group") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/persistence") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.FETCH_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/fetch-ai") else { return }
            self.onShowSafariWeb(url)
        }
        
        
    }
    
    func onClickBuyCoin() {
        if (mainTabVC.mAccount.account_has_private) {
            self.onShowBuySelectFiat()
        } else {
            self.onShowBuyWarnNoKey()
        }
    }
    
    func onShowBuyWarnNoKey() {
        let noKeyAlert = UIAlertController(title: NSLocalizedString("buy_without_key_title", comment: ""), message: NSLocalizedString("buy_without_key_msg", comment: ""), preferredStyle: .alert)
        noKeyAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: {_ in
            self.dismiss(animated: true, completion: nil)
        }))
        noKeyAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .destructive, handler: {_ in
            self.onShowBuySelectFiat()
        }))
        self.present(noKeyAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noKeyAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onShowBuySelectFiat() {
        let selectFiatAlert = UIAlertController(title: NSLocalizedString("buy_select_fiat_title", comment: ""), message: NSLocalizedString("buy_select_fiat_msg", comment: ""), preferredStyle: .alert)
        let usdAction = UIAlertAction(title: "USD", style: .default, handler: { _ in
            self.onStartMoonpaySignature("usd")
        })
        let eurAction = UIAlertAction(title: "EUR", style: .default, handler: { _ in
            self.onStartMoonpaySignature("eur")
        })
        let gbpAction = UIAlertAction(title: "GBP", style: .default, handler: { _ in
            self.onStartMoonpaySignature("gbp")
        })
        selectFiatAlert.addAction(usdAction)
        selectFiatAlert.addAction(eurAction)
        selectFiatAlert.addAction(gbpAction)
        self.present(selectFiatAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            selectFiatAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onStartMoonpaySignature(_ fiat:String) {
        var query = "?apiKey=" + MOON_PAY_PUBLICK
        if (chainType! == ChainType.COSMOS_MAIN) {
            query = query + "&currencyCode=atom"
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            query = query + "&currencyCode=bnb"
        } else if (chainType! == ChainType.KAVA_MAIN) {
            query = query + "&currencyCode=kava"
        } else if (chainType! == ChainType.BAND_MAIN) {
            query = query + "&currencyCode=band"
        }
        query = query + "&walletAddress=" + mainTabVC.mAccount.account_address + "&baseCurrencyCode=" + fiat;
        let param = ["api_key":query] as [String : Any]
        let request = Alamofire.request(CSS_MOON_PAY, method: .post, parameters: param, encoding: JSONEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary else {
                    self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
                    return
                }
                let result = responseData.object(forKey: "signature") as? String ?? ""
                let signauture = result.addingPercentEncoding(withAllowedCharacters: .alphanumerics)
                self.onStartMoonPay(MOON_PAY_URL + query + "&signature=" + signauture!)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onStartMoonpaySignature ", error) }
                self.onShowToast(NSLocalizedString("error_network_msg", comment: ""))
            }
        }
    }
    
    func onStartMoonPay(_ url:String) {
        guard let url = URL(string: url) else { return }
        let safariViewController = SFSafariViewController(url: url)
        present(safariViewController, animated: true, completion: nil)
    }
    
    func onClickMainSend() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        let balances = BaseData.instance.selectBalanceById(accountId: self.mainTabVC.mAccount.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: "0.000375")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mBnbToken = WUtils.getBnbMainToken(BaseData.instance.mBnbTokenList)
            txVC.mType = BNB_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            if (WUtils.getTokenAmount(balances, KAVA_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mKavaSendDenom = KAVA_MAIN_DENOM
            txVC.mType = KAVA_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "1000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mIovSendDenom = IOV_MAIN_DENOM
            txVC.mType = IOV_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            if (WUtils.getTokenAmount(balances, BAND_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mType = BAND_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.SECRET_MAIN) {
            if (WUtils.getTokenAmount(balances, SECRET_MAIN_DENOM).compare(NSDecimalNumber.init(string: "20000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mSecretSendDenom = SECRET_MAIN_DENOM
            txVC.mType = SECRET_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "1000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mIovSendDenom = IOV_TEST_DENOM
            txVC.mType = IOV_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(balances, OKEX_MAIN_DENOM).compare(NSDecimalNumber.init(string: "0.002")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mOkSendDenom = OKEX_MAIN_DENOM
            txVC.mType = OK_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            if (WUtils.getTokenAmount(balances, CERTIK_MAIN_DENOM).compare(NSDecimalNumber.init(string: "5000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mCertikSendDenom = CERTIK_MAIN_DENOM
            txVC.mType = CERTIK_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (WUtils.getTokenAmount(balances, SENTINEL_MAIN_DENOM).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = WUtils.getMainDenom(chainType)
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else if (chainType! == ChainType.FETCH_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (WUtils.getTokenAmount(balances, FETCH_MAIN_DENOM).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = WUtils.getMainDenom(chainType)
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        }
        
        else if (WUtils.isGRPC(chainType!)) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.getAvailableAmount(WUtils.getMainDenom(chainType)).compare(feeAmount).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = WUtils.getMainDenom(chainType)
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else {
            return
            
        }
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onStartQrCode() {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        self.navigationController?.pushViewController(qrScanVC, animated: false)
    }
    
    func scannedAddress(result: String) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
            if (result.contains("wallet-bridge.binance.org")) {
                self.wcURL = result
                let wcAlert = UIAlertController(title: NSLocalizedString("wc_alert_title", comment: ""), message: NSLocalizedString("wc_alert_msg", comment: ""), preferredStyle: .alert)
                wcAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .destructive, handler: nil))
                wcAlert.addAction(UIAlertAction(title: NSLocalizedString("ok", comment: ""), style: .default, handler: { _ in
                    let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
                    self.navigationItem.title = ""
                    self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
                    passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
                    passwordVC.resultDelegate = self
                    passwordVC.hidesBottomBarWhenPushed = true
                    self.navigationController?.pushViewController(passwordVC, animated: false)
                }))
                self.present(wcAlert, animated: true) {
                    let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                    wcAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
                }
            }
        })
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
                let wcVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "WalletConnectViewController") as! WalletConnectViewController
                wcVC.hidesBottomBarWhenPushed = true
                wcVC.wcURL = self.wcURL!
                self.navigationItem.title = ""
                self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false;
                self.navigationController?.pushViewController(wcVC, animated: true)
            })
        }
    }
}
