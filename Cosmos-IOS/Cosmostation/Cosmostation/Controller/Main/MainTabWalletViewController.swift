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
    
    @IBOutlet weak var totalCard: CardView!
    @IBOutlet weak var totalKeyState: UIImageView!
    @IBOutlet weak var totalDpAddress: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    
    @IBOutlet weak var walletTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mainTabVC: MainTabViewController!
    var wcURL:String?

    override func viewDidLoad() {
        super.viewDidLoad()
        self.mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.walletTableView.delegate = self
        self.walletTableView.dataSource = self
        self.walletTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.walletTableView.register(UINib(nibName: "WalletAddressCell", bundle: nil), forCellReuseIdentifier: "WalletAddressCell")
        self.walletTableView.register(UINib(nibName: "WalletOkAddressCell", bundle: nil), forCellReuseIdentifier: "WalletOkAddressCell")
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
        self.walletTableView.register(UINib(nibName: "WalletSifCell", bundle: nil), forCellReuseIdentifier: "WalletSifCell")
        self.walletTableView.register(UINib(nibName: "WalletSifIncentiveCell", bundle: nil), forCellReuseIdentifier: "WalletSifIncentiveCell")
        self.walletTableView.register(UINib(nibName: "WalletKiCell", bundle: nil), forCellReuseIdentifier: "WalletKiCell")
        self.walletTableView.register(UINib(nibName: "WalletRizonCell", bundle: nil), forCellReuseIdentifier: "WalletRizonCell")
        self.walletTableView.register(UINib(nibName: "WalletMediCell", bundle: nil), forCellReuseIdentifier: "WalletMediCell")
        self.walletTableView.register(UINib(nibName: "WalletAltheaCell", bundle: nil), forCellReuseIdentifier: "WalletAltheaCell")
        self.walletTableView.register(UINib(nibName: "WalletOsmoCell", bundle: nil), forCellReuseIdentifier: "WalletOsmoCell")
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
        
        let tapTotalCard = UITapGestureRecognizer(target: self, action: #selector(self.onClickActionShare))
        self.totalCard.addGestureRecognizer(tapTotalCard)
        
        self.updateFloaty()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchPrice(_:)), name: Notification.Name("onFetchPrice"), object: nil)
        self.updateTitle()
        self.walletTableView.reloadData()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchPrice"), object: nil)
    }
    
    
    func updateTitle() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        if (account?.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            titleWalletName.text = account?.account_nick_name
        }
        
        self.totalCard.backgroundColor = WUtils.getChainBg(chainType)
        if (account?.account_has_private == true) {
            self.totalKeyState.image = totalKeyState.image?.withRenderingMode(.alwaysTemplate)
            self.totalKeyState.tintColor = WUtils.getChainColor(chainType)
        }
        self.totalDpAddress.text = account?.account_address
        self.totalDpAddress.adjustsFontSizeToFitWidth = true
        self.totalValue.attributedText = WUtils.dpAllAssetValueUserCurrency(chainType, totalValue.font)
        
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
            titleChainImg.image = UIImage(named: "chainBandprotocal")
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
            titleChainName.text = "(ExChain Mainnet)"
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
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            titleChainImg.image = UIImage(named: "chaincrypto")
            titleChainName.text = "(Crypto.org Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SIF_MAIN) {
            titleChainImg.image = UIImage(named: "chainsifchain")
            titleChainName.text = "(SifChain Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.KI_MAIN) {
            titleChainImg.image = UIImage(named: "chainKifoundation")
            titleChainName.text = "(KiChain Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.OSMOSIS_MAIN) {
            titleChainImg.image = UIImage(named: "chainOsmosis")
            titleChainName.text = "(OSMOSIS Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.MEDI_MAIN) {
            titleChainImg.image = UIImage(named: "chainMedibloc")
            titleChainName.text = "(Medibloc Mainnet)"
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
            titleChainName.text = "(ExChain Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.CERTIK_TEST) {
            titleChainImg.image = UIImage(named: "certikTestnetImg")
            titleChainName.text = "(Certik Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.RIZON_TEST) {
            titleChainImg.image = UIImage(named: "testnetRizon")
            titleChainName.text = "(Rizon Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.MEDI_TEST) {
            titleChainImg.image = UIImage(named: "testnetMedibloc")
            titleChainName.text = "(Medi Testnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.ALTHEA_TEST) {
            titleChainImg.image = UIImage(named: "testnetAlthea")
            titleChainName.text = "(Althea Testnet)"
            titleAlarmBtn.isHidden = true
        }
        UNUserNotificationCenter.current().getNotificationSettings { (settings) in
            if settings.authorizationStatus == .authorized {
                DispatchQueue.main.async {
                    if (self.account!.account_push_alarm) {
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
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            floaty.buttonImage = UIImage.init(named: "sendImg")
            floaty.buttonColor = COLOR_CRYPTO_DARK
        } else if (chainType! == ChainType.ALTHEA_TEST) {
            floaty.buttonImage = UIImage.init(named: "btnSendAlthea")
            floaty.buttonColor = COLOR_ALTHEA
        } else if (chainType == ChainType.MEDI_MAIN || chainType == ChainType.MEDI_TEST) {
            floaty.buttonImage = UIImage.init(named: "btnSendMedi")
            floaty.buttonColor = .white
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
        self.totalValue.attributedText = WUtils.dpAllAssetValueUserCurrency(chainType, totalValue.font)
        self.walletTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    @objc func onFetchPrice(_ notification: NSNotification) {
        self.totalValue.attributedText = WUtils.dpAllAssetValueUserCurrency(chainType, totalValue.font)
        self.walletTableView.reloadData()
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        self.onClickMainSend()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return 3;
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return 3;
        }
        return 4;
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
        } else if (chainType == ChainType.CRYPTO_MAIN) {
            return onSetCrytoItems(tableView, indexPath);
        } else if (chainType == ChainType.SIF_MAIN) {
            return onSetSifItems(tableView, indexPath);
        } else if (chainType == ChainType.KI_MAIN) {
            return onSetkiItems(tableView, indexPath);
        } else if (chainType == ChainType.OSMOSIS_MAIN) {
            return onSetOsmoItems(tableView, indexPath);
        } else if (chainType == ChainType.MEDI_MAIN || chainType == ChainType.MEDI_TEST) {
            return onSetMediItems(tableView, indexPath);
        }
        
        else if (chainType == ChainType.COSMOS_TEST) {
            return onSetCosmosTestItems(tableView, indexPath);
        } else if (chainType == ChainType.IRIS_TEST) {
            return onSetIrisTestItems(tableView, indexPath);
        } else if (chainType == ChainType.RIZON_TEST) {
            return onSetRizonItems(tableView, indexPath);
        } else if (chainType == ChainType.ALTHEA_TEST) {
            return onSetAltheaItems(tableView, indexPath);
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
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCosmosCell") as? WalletCosmosCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionGravity = {
                self.onShowToast(NSLocalizedString("prepare", comment: ""))
//                self.onClickGravityDex()
                
            }
            cell?.actionWalletConnect = {
                self.onShowToast(NSLocalizedString("prepare", comment: ""))
//                self.onClickWalletConect()
                
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetIrisItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletIrisCell") as? WalletIrisCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetBnbItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletBnbCell") as? WalletBnbCell
            cell?.updateView(account, chainType)
            cell?.actionWC = { self.onClickWalletConect() }
            cell?.actionBep3 = { self.onClickBep3Send(BNB_MAIN_DENOM) }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetKavaItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletKavaCell") as? WalletKavaCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionCdp = { self.onClickCdp() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetIovItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletIovCell") as? WalletIovCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionNameService = { self.onClickStarName() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetBandItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletBandCell") as? WalletBandCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetSecretItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletSecretCell") as? WalletSecretCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetOKexItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletOkCell") as? WalletOkCell
            cell?.updateView(account, chainType)
            cell?.actionDeposit = { self.onClickOkDeposit() }
            cell?.actionWithdraw = { self.onClickOkWithdraw() }
            cell?.actionVoteforVal = { self.onClickOkVoteVal() }
            cell?.actionVote = { self.onShowToast(NSLocalizedString("error_not_yet", comment: "")) }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetCertikItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCertikCell") as? WalletCertikCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetAkashItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAkashCell") as? WalletAkashCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetPersisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPersisCell") as? WalletPersisCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetSentinelItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletSentinelCell") as? WalletSentinelCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetFetchItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletFetchCell") as? WalletFetchCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetCrytoItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCrytoCell") as? WalletCrytoCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetSifItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletSifCell") as? WalletSifCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletSifIncentiveCell") as? WalletSifIncentiveCell
            cell?.updateView(account, chainType)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            cell?.actionBuy = { self.onClickBuyCoin() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
            
        }
    }
    
    func onSetkiItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletKiCell") as? WalletKiCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetRizonItems(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletRizonCell") as? WalletRizonCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionSwap = { self.onClickRizonSwap() }
            return cell!

        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!

        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!

        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetMediItems(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletMediCell") as? WalletMediCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = {
                self.onShowToast(NSLocalizedString("prepare", comment: ""))
//                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onShowToast(NSLocalizedString("prepare", comment: ""))
//                self.onClickVoteList()
            }
            return cell!

        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!

        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!

        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetAltheaItems(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletAltheaCell") as? WalletAltheaCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!

        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!

        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!

        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    func onSetOsmoItems(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletOsmoCell") as? WalletOsmoCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            cell?.actionLab = { self.onClickOsmosisLab() }
            return cell!

        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!

        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!

        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
        
    }
    
    
    
    func onSetCosmosTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletCosmosCell") as? WalletCosmosCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    func onSetIrisTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletIrisCell") as? WalletIrisCell
            cell?.updateView(account, chainType)
            cell?.actionDelegate = { self.onClickValidatorList() }
            cell?.actionVote = { self.onClickVoteList() }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.updateView(account, chainType)
            cell?.actionTapPricel = { self.onClickMarketInfo() }
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.updateView(account, chainType)
            cell?.actionTapApr = { self.onClickAprHelp() }
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.updateView(account, chainType)
            cell?.actionGuide1 = { self.onClickGuide1() }
            cell?.actionGuide2 = { self.onClickGuide2() }
            return cell!
        }
    }
    
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.onShowAccountSwicth()
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        let link = WUtils.getAccountExplorer(chainType!, account!.account_address)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    
    @IBAction func onClickAlaram(_ sender: UIButton) {
        if (sender.imageView?.image == UIImage(named: "notificationsIcOff")) {
            UNUserNotificationCenter.current().getNotificationSettings { (settings) in
                if settings.authorizationStatus == .authorized {
                    DispatchQueue.main.async {
                        self.showWaittingAlert()
                        self.onToggleAlarm(self.account!) { (success) in
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
                self.onToggleAlarm(self.account!) { (success) in
                    self.mainTabVC.onUpdateAccountDB()
                    self.updateTitle()
                    self.dismissAlertController()
                }
            }
        }
    }
    
    
    @objc func onClickActionShare() {
        var nickName:String?
        if (account?.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account?.account_nick_name
        }
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        self.shareAddress(address, nickName!)
    }
    
    func onClickValidatorList() {
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
        if (account?.account_has_private == false) {
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
        
        if (account?.account_has_private == false) {
            self.onShowAddMenomicDialog()
            return
        }

        let balances = BaseData.instance.selectBalanceById(accountId: self.account!.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: FEE_BNB_TRANSFER)).rawValue <= 0) {
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
        if (account?.account_has_private == false) {
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
        if (account?.account_has_private == false) {
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
    
    func onClickRizonSwap() {
        if (account?.account_has_private == false) {
            self.onShowAddMenomicDialog()
            return
        }
        let rizonSwapAlert = UIAlertController(title: "Event Horizon", message: NSLocalizedString("str_rizon_swap_alert_msg", comment: ""), preferredStyle: .alert)
        rizonSwapAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default))
        rizonSwapAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default, handler: { _ in
            self.onCheckSwapStatus(self.account!.account_address)
        }))
        self.present(rizonSwapAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            rizonSwapAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onCheckSwapStatus(_ address: String) {
        print("onCheckSwapStatus ", BaseNetWork.rizonSwapStatus(chainType, address))
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.rizonSwapStatus(chainType, address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                var swapInfos = Array<RizonSwapStatus>()
                if let rawSwapInfos = res as? Array<NSDictionary> {
                    rawSwapInfos.forEach { rawSwapInfo in
                        swapInfos.append(RizonSwapStatus.init(rawSwapInfo))
                    }
                }
                print("swapInfos ", swapInfos.count)
                if (swapInfos.count > 0) {
                    self.onShowToast(NSLocalizedString("error_alreay_rizon_swap", comment: ""))
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(500)) {
                        let swapStatusVC = RizonSwapStatusViewController(nibName: "RizonSwapStatusViewController", bundle: nil)
                        swapStatusVC.hidesBottomBarWhenPushed = true
                        self.navigationItem.title = ""
                        self.navigationController?.pushViewController(swapStatusVC, animated: true)
                    }
                } else {
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(500)) {
                        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
                        txVC.mType = TASK_RIZON_EVENT_HORIZON
                        txVC.hidesBottomBarWhenPushed = true
                        self.navigationItem.title = ""
                        self.navigationController?.pushViewController(txVC, animated: true)
                    }
                }

            case .failure(let error):
                print("onCheckSwapStatus ", error)
            }
            self.hideWaittingAlert()
        }
    }
    
    func onClickOsmosisLab() {
        let osmosisDappVC = UIStoryboard(name: "Osmosis", bundle: nil).instantiateViewController(withIdentifier: "OsmosisDAppViewController") as! OsmosisDAppViewController
        osmosisDappVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(osmosisDappVC, animated: true)
    }
    
    func onClickGravityDex() {
        print("onClickGravityDex")
        self.onShowToast(NSLocalizedString("prepare", comment: ""))
    }
    
    func onClickAprHelp() {
        guard let param = BaseData.instance.mParam else { return }
        let msg1 = NSLocalizedString("str_apr_help_onchain_msg", comment: "") + "\n"
        let msg2 = param.getDpApr(chainType).stringValue + "%\n\n"
        let msg3 = NSLocalizedString("str_apr_help_real_msg", comment: "") + "\n"
        var msg4 = ""
        if (param.getDpRealApr(chainType) == NSDecimalNumber.zero) {
            msg4 = "-"
        } else {
            msg4 = param.getDpRealApr(chainType).stringValue + "%"
        }
        
        let helpAlert = UIAlertController(title: "", message: msg1 + msg2 + msg3 + msg4, preferredStyle: .alert)
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
            if (Locale.current.languageCode == "ko") {
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
            
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            guard let url = URL(string: "https://crypto.org/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            guard let url = URL(string: "https://sifchain.finance/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KI_MAIN) {
            guard let url = URL(string: "https://foundation.ki/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OSMOSIS_MAIN) {
            guard let url = URL(string: "https://osmosis.zone/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.MEDI_MAIN || chainType! == ChainType.MEDI_TEST) {
            if (Locale.current.languageCode == "ko") {
                guard let url = URL(string: "https://medibloc.com") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://medibloc.com/en/") else { return }
                self.onShowSafariWeb(url)
            }
        }
        
    }
    
    func onClickGuide2() {
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.COSMOS_TEST) {
            if (Locale.current.languageCode == "ko") {
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
            
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            guard let url = URL(string: "https://crypto.org/community") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            guard let url = URL(string: "https://medium.com/sifchain-finance") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KI_MAIN) {
            guard let url = URL(string: "https://medium.com/ki-foundation") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OSMOSIS_MAIN) {
            guard let url = URL(string: "https://medium.com/osmosis") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.MEDI_MAIN || chainType! == ChainType.MEDI_TEST) {
            if (Locale.current.languageCode == "ko") {
                guard let url = URL(string: "https://blog.medibloc.org/") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://medium.com/medibloc/") else { return }
                self.onShowSafariWeb(url)
            }
        }
        
    }
    
    func onClickMarketInfo() {
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.COSMOS_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/cosmos") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IRIS_MAIN || chainType! == ChainType.IRIS_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/irisnet") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/binancecoin") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/kava") else { return }
            self.onShowSafariWeb(url)
            
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
            guard let url = URL(string: "https://www.coingecko.com/en/coins/sentinel") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/persistence") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.FETCH_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/fetch-ai") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/crypto-com-chain") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/sifchain") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KI_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/ki") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OSMOSIS_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/osmosis") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.MEDI_MAIN || chainType == ChainType.MEDI_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/medibloc") else { return }
            self.onShowSafariWeb(url)
        }
        
        
        
    }
    
    func onClickBuyCoin() {
        if (self.account?.account_has_private == true) {
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
        query = query + "&walletAddress=" + self.account!.account_address + "&baseCurrencyCode=" + fiat;
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
        if (account?.account_has_private == false) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let mainDenom = WUtils.getMainDenom(chainType)
        if (WUtils.isGRPC(chainType!)) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.getAvailableAmount_gRPC(mainDenom).compare(feeAmount).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            
        } else {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.availableAmount(mainDenom).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mToSendDenom = mainDenom
        txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
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
            if (self.chainType == ChainType.BINANCE_MAIN || self.chainType == ChainType.BINANCE_TEST) {
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
                
            } else {
                print("chainType ", self.chainType, "  url ",  result)
                
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
