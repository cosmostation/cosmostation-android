//
//  MainTabTokenViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 26/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import UserNotifications
import SafariServices

class MainTabTokenViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    let ORDER_BY_NAME = 0
    let ORDER_BY_AMOUNT = 1
    let ORDER_BY_VALUE = 2
    
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleAlarmBtn: UIButton!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var totalCard: CardView!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var totalBtcValue: UILabel!
    @IBOutlet weak var tokenCnt: UILabel!
    @IBOutlet weak var btnSort: UIView!
    @IBOutlet weak var sortType: UILabel!
    
    @IBOutlet weak var tokenTableView: UITableView!
    var refresher: UIRefreshControl!
    var mainTabVC: MainTabViewController!
    var mBnbTics = [String : NSMutableDictionary]()
    var mOrder:Int?
    
    
    var mBalances = Array<Balance>()
    var mBalances_gRPC = Array<Coin>()

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
        
        self.tokenTableView.rowHeight = UITableView.automaticDimension
        self.tokenTableView.estimatedRowHeight = UITableView.automaticDimension
        
        mOrder = ORDER_BY_NAME
        let tap = UITapGestureRecognizer(target: self, action: #selector(onStartSort))
        self.btnSort.addGestureRecognizer(tap)
        
        self.mBalances = BaseData.instance.mBalances
        self.mBalances_gRPC = BaseData.instance.mMyBalances_gRPC
        self.updateView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceUpdated(_:)), name: Notification.Name("priceUpdate"), object: nil)
        self.updateTitle()
        self.updateView()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("priceUpdate"), object: nil)
    }
    
    func updateTitle() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        
        totalCard.backgroundColor = WUtils.getChainBg(chainType)
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
        }  else if (chainType! == ChainType.BAND_MAIN) {
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
    
    func updateView() {
        if (mOrder == ORDER_BY_NAME) {
            sortByName()
            self.sortType.text = NSLocalizedString("name", comment: "")
        } else if (mOrder == ORDER_BY_AMOUNT) {
            sortByAmount()
            self.sortType.text = NSLocalizedString("amount", comment: "")
        } else if (mOrder == ORDER_BY_VALUE) {
            sortByValue()
            self.sortType.text = NSLocalizedString("value", comment: "")
        }
        self.onUpdateTotalCard();
        self.tokenTableView.reloadData()
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.mBalances = BaseData.instance.mBalances
        self.mBalances_gRPC = BaseData.instance.mMyBalances_gRPC
        
        self.updateView()
        self.refresher.endRefreshing()
    }
    
    @objc func onPriceUpdated(_ notification: NSNotification) {
        self.updateView()
    }
    
    @objc func onStartSort() {
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: UIAlertAction.Style.cancel, handler: nil))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_name", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            self.mOrder = self.ORDER_BY_NAME
            self.updateView()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_amount", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            self.mOrder = self.ORDER_BY_AMOUNT
            self.updateView()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_value", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
            self.mOrder = self.ORDER_BY_VALUE
            self.updateView()
        }))
        self.present(alert, animated: true, completion: nil)
    }
    
    func onUpdateTotalCard() {
        self.tokenCnt.text = WUtils.tokenCnt(chainType)
        totalBtcValue.attributedText = WUtils.dpAllAssetValueBtc(chainType, totalBtcValue.font)
        totalValue.attributedText = WUtils.dpAllAssetValueUserCurrency(chainType, totalValue.font)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (WUtils.isGRPC(chainType!)) {
            return mBalances_gRPC.count
        } else {
            return mBalances.count;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType! == ChainType.COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath)
        } else if (chainType! == ChainType.IRIS_MAIN) {
            return onSetIrisItems(tableView, indexPath)
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            return onSetBnbItems(tableView, indexPath)
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            return onSetKavaItems(tableView, indexPath)
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            return onSetIovItems(tableView, indexPath)
        } else if (chainType! == ChainType.BAND_MAIN) {
            return onSetBandItems(tableView, indexPath)
        } else if (chainType! == ChainType.SECRET_MAIN) {
            return onSetSecretItems(tableView, indexPath)
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            return onSetOkItems(tableView, indexPath)
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            return onSetCertikItems(tableView, indexPath)
        } else if (chainType! == ChainType.AKASH_MAIN) {
            return onSetAkashItems(tableView, indexPath)
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            return onSetPersisItems(tableView, indexPath)
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            return onSetSentinelItems(tableView, indexPath)
        } else if (chainType! == ChainType.FETCH_MAIN) {
            return onSetFetchItems(tableView, indexPath)
        } else if (chainType! == ChainType.CRYPTO_MAIN) {
            return onSetCrytoItems(tableView, indexPath)
        } else if (chainType! == ChainType.SIF_MAIN) {
            return onSetSifItems(tableView, indexPath)
        } else if (chainType! == ChainType.KI_MAIN) {
            return onSetKiItems(tableView, indexPath)
        } else if (chainType! == ChainType.COSMOS_TEST) {
            return onSetCosmosTestItems(tableView, indexPath)
        } else if (chainType! == ChainType.IRIS_TEST) {
            return onSetIrisTestItems(tableView, indexPath)
        } else if (chainType! == ChainType.RIZON_TEST) {
            return onSetRizonItems(tableView, indexPath)
        } else if (chainType == ChainType.MEDI_MAIN || chainType == ChainType.MEDI_TEST) {
            return onSetMediItems(tableView, indexPath)
        } else if (chainType! == ChainType.ALTHEA_TEST) {
            return onSetAltheaItems(tableView, indexPath)
        } else if (chainType! == ChainType.OSMOSIS_MAIN) {
            return onSetOsmoItems(tableView, indexPath)
        }
        return tableView.dequeueReusableCell(withIdentifier:"TokenCell") as! TokenCell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (WUtils.isGRPC(chainType!)) {
            let balance = mBalances_gRPC[indexPath.row]
            if (balance.denom == WUtils.getMainDenom(chainType)) {
                let sTokenDetailVC = StakingTokenDetailViewController(nibName: "StakingTokenDetailViewController", bundle: nil)
                sTokenDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(sTokenDetailVC, animated: true)
                return
            } else {
                //TODO not this yet
            }
            
        } else {
            //TODO update for all chains
            let balance = mBalances[indexPath.row]
            if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
                if (balance.balance_denom == WUtils.getMainDenom(chainType)) {
                    let sTokenDetailVC = StakingTokenDetailViewController(nibName: "StakingTokenDetailViewController", bundle: nil)
                    sTokenDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(sTokenDetailVC, animated: true)
                    
                } else {
                    let nTokenDetailVC = NativeTokenDetailViewController(nibName: "NativeTokenDetailViewController", bundle: nil)
                    nTokenDetailVC.hidesBottomBarWhenPushed = true
                    nTokenDetailVC.denom = mBalances[indexPath.row].balance_denom
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(nTokenDetailVC, animated: true)
                }
                
            } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
                if (balance.balance_denom == WUtils.getMainDenom(chainType)) {
                    let sTokenDetailVC = StakingTokenDetailViewController(nibName: "StakingTokenDetailViewController", bundle: nil)
                    sTokenDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(sTokenDetailVC, animated: true)
                    
                } else {
                    let nTokenDetailVC = NativeTokenDetailViewController(nibName: "NativeTokenDetailViewController", bundle: nil)
                    nTokenDetailVC.hidesBottomBarWhenPushed = true
                    nTokenDetailVC.denom = mBalances[indexPath.row].balance_denom
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(nTokenDetailVC, animated: true)
                }
                
            } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
                if (balance.balance_denom == WUtils.getMainDenom(chainType)) {
                    let sTokenDetailVC = StakingTokenDetailViewController(nibName: "StakingTokenDetailViewController", bundle: nil)
                    sTokenDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(sTokenDetailVC, animated: true)
                    
                } else {
                    let nTokenDetailVC = NativeTokenDetailViewController(nibName: "NativeTokenDetailViewController", bundle: nil)
                    nTokenDetailVC.hidesBottomBarWhenPushed = true
                    nTokenDetailVC.denom = mBalances[indexPath.row].balance_denom
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(nTokenDetailVC, animated: true)
                }
                
                
            } else if (chainType! == ChainType.SIF_MAIN) {
                if (balance.balance_denom == WUtils.getMainDenom(chainType)) {
                    let sTokenDetailVC = StakingTokenDetailViewController(nibName: "StakingTokenDetailViewController", bundle: nil)
                    sTokenDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(sTokenDetailVC, animated: true)
                    
                } else {
                    let nTokenDetailVC = NativeTokenDetailViewController(nibName: "NativeTokenDetailViewController", bundle: nil)
                    nTokenDetailVC.hidesBottomBarWhenPushed = true
                    nTokenDetailVC.denom = mBalances[indexPath.row].balance_denom
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(nTokenDetailVC, animated: true)
                }
            }
        }
    }
    
    func onSetBnbItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let denom = mBalances[indexPath.row].balance_denom
        let amount = WUtils.getAllBnbToken(denom)
        let bnbToken = WUtils.getBnbToken(denom)
        if (bnbToken != nil) {
            cell?.tokenSymbol.text = bnbToken!.original_symbol.uppercased()
            cell?.tokenTitle.text = "(" + bnbToken!.symbol + ")"
            cell?.tokenDescription.text = bnbToken!.name
            if (denom == BNB_MAIN_DENOM) {
                cell?.tokenSymbol.textColor = COLOR_BNB
                cell?.tokenImg.image = UIImage(named: "bnbTokenImg")
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(amount.stringValue, cell!.tokenAmount.font, 0, 6)
                cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(BNB_MAIN_DENOM, amount, 0, cell!.tokenValue.font)
                
            } else {
                cell?.tokenSymbol.textColor = UIColor.white
                cell?.tokenImg.af_setImage(withURL: URL(string: TOKEN_IMG_URL + bnbToken!.original_symbol + ".png")!)
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(amount.stringValue, cell!.tokenAmount.font, 0, 6)
                let convertAmount = WUtils.getBnbConvertAmount(denom, amount)
                cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(BNB_MAIN_DENOM, convertAmount, 0, cell!.tokenValue.font)
            }
        }
        return cell!
    }
    
    func onSetKavaItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == KAVA_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "kavaTokenImg")
            cell?.tokenSymbol.text = "KAVA"
            cell?.tokenSymbol.textColor = COLOR_KAVA
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Kava Staking Token"
            
            let totalKava = WUtils.getAllMainAssetOld(KAVA_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.tokenAmount.font!, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(KAVA_MAIN_DENOM, totalKava, 6, cell!.tokenValue.font)
            
        } else if (balance.balance_denom == KAVA_HARD_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenhard")
            cell?.tokenSymbol.text = "HARD"
            cell?.tokenSymbol.textColor = COLOR_HARD
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "HardPool Gov. Token"
            
            let totalTokenAmount = WUtils.getKavaTokenAll(balance.balance_denom, mBalances)
            let convertedKavaAmount = WUtils.convertTokenToKava(balance.balance_denom)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalTokenAmount.stringValue, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(KAVA_MAIN_DENOM, convertedKavaAmount, 6, cell!.tokenValue.font)
            
        } else {
            cell?.tokenImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + balance.balance_denom + ".png")!)
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            if (balance.balance_denom == "usdx") { cell?.tokenDescription.text = "USD Stable Asset" }
            else if (balance.balance_denom == "hard") { cell?.tokenDescription.text = "HardPool Gov. Token" }
            else { cell?.tokenDescription.text = balance.balance_denom.uppercased() + " on Kava Chain" }
            
            let totalTokenAmount = WUtils.getKavaTokenAll(balance.balance_denom, mBalances)
            let convertedKavaAmount = WUtils.convertTokenToKava(balance.balance_denom)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalTokenAmount.stringValue, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(KAVA_MAIN_DENOM, convertedKavaAmount, 6, cell!.tokenValue.font)
        }
        return cell!
    }
    
    func onSetOkItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        let okToken = WUtils.getOkToken(balance.balance_denom)
        if (okToken != nil) {
            cell?.tokenSymbol.text = okToken?.original_symbol?.uppercased()
            cell?.tokenTitle.text = "(" + okToken!.symbol! + ")"
            cell?.tokenDescription.text = okToken?.description
            if (balance.balance_denom == OKEX_MAIN_DENOM) {
                cell?.tokenSymbol.textColor = COLOR_OK
                cell?.tokenImg.image = UIImage(named: "okexTokenImg")
                
                let tokenAmount = WUtils.getAllExToken(balance.balance_denom)
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(tokenAmount.stringValue, cell!.tokenAmount.font, 0, 6)
                cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(OKEX_MAIN_DENOM, tokenAmount, 0, cell!.tokenValue.font)
                
            } else {
                cell?.tokenSymbol.textColor = UIColor.white
                cell?.tokenImg.af_setImage(withURL: URL(string: OKEX_COIN_IMG_URL + okToken!.original_symbol! + ".png")!)
                
                let tokenAmount = WUtils.getAllExToken(balance.balance_denom)
                let convertedAmount = WUtils.convertTokenToOkt(balance.balance_denom)
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(tokenAmount.stringValue, cell!.tokenAmount.font, 0, 6)
                cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(OKEX_MAIN_DENOM, convertedAmount, 0, cell!.tokenValue.font)
            }
        }
        return cell!
    }
    
    func onSetSecretItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == SECRET_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "secretTokenImg")
            cell?.tokenSymbol.text = "SCRT"
            cell?.tokenSymbol.textColor = COLOR_SECRET
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Secret Staking Token"
            
            let allSecret = WUtils.getAllMainAssetOld(SECRET_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allSecret.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(SECRET_MAIN_DENOM, allSecret, 6, cell!.tokenValue.font)

        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetCertikItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == CERTIK_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "certikTokenImg")
            cell?.tokenSymbol.text = "CTK"
            cell?.tokenSymbol.textColor = COLOR_CERTIK
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Certik Staking Token"
            
            let allCtk = WUtils.getAllMainAssetOld(CERTIK_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allCtk.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(CERTIK_MAIN_DENOM, allCtk, 6, cell!.tokenValue.font)
            
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetFetchItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == FETCH_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenfetchai")
            cell?.tokenSymbol.text = "FET"
            cell?.tokenSymbol.textColor = COLOR_FETCH
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Fetch.ai Staking Token"
            
            let allFet = WUtils.getAllMainAssetOld(FETCH_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allFet.stringValue, cell!.tokenAmount.font, 18, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(FETCH_MAIN_DENOM, allFet, 18, cell!.tokenValue.font)
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetSifItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == SIF_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokensifchain")
            cell?.tokenSymbol.text = "ROWAN"
            cell?.tokenSymbol.textColor = COLOR_SIF
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Sifchain Staking Token"
            
            let allSif = WUtils.getAllMainAssetOld(SIF_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allSif.stringValue, cell!.tokenAmount.font, 18, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(SIF_MAIN_DENOM, allSif, 18, cell!.tokenValue.font)
            
        } else {
            cell?.tokenImg.af_setImage(withURL: URL(string: SIF_COIN_IMG_URL + balance.balance_denom + ".png")!)
            cell?.tokenSymbol.text = balance.balance_denom.substring(from: 1).uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = balance.balance_denom.substring(from: 1).uppercased() + " on Sifchain"
            
            let available = BaseData.instance.availableAmount(balance.balance_denom)
            let decimal = WUtils.getSifCoinDecimal(balance.balance_denom)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(available.stringValue, cell!.tokenAmount.font!, decimal, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(balance.balance_denom.substring(from: 1), available, decimal, cell!.tokenValue.font)
            
        }
        return cell!
    }
    
    func onSetKiItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == KI_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenKifoundation")
            cell?.tokenSymbol.text = "XKI"
            cell?.tokenSymbol.textColor = COLOR_KI
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "KiChain Staking Token"
            
            let allKi = WUtils.getAllMainAssetOld(KI_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allKi.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(KI_MAIN_DENOM, allKi, 6, cell!.tokenValue.font)
            
        }
        return cell!
    }
    
    func onSetMediItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == MEDI_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenmedibloc")
            cell?.tokenSymbol.text = "MED"
            cell?.tokenSymbol.textColor = COLOR_MEDI
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Mediblock Staking Token"
            
            let allMed = WUtils.getAllMainAssetOld(MEDI_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allMed.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(MEDI_MAIN_DENOM, allMed, 6, cell!.tokenValue.font)
            
        }
        return cell!
    }
    
    
    
    
    //with gRPC
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == COSMOS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "atom_ic")
            cell?.tokenSymbol.text = "ATOM"
            cell?.tokenSymbol.textColor = COLOR_ATOM
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Cosmos Staking Token"
            let allAtom = WUtils.getAllMainAsset(COSMOS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(COSMOS_MAIN_DENOM, allAtom, 6, cell!.tokenValue.font)

        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetIrisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == IRIS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "irisTokenImg")
            cell?.tokenSymbol.text = "IRIS"
            cell?.tokenSymbol.textColor = COLOR_IRIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Iris Staking Token"
            let allIris = WUtils.getAllMainAsset(IRIS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(IRIS_MAIN_DENOM, allIris, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetAkashItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == AKASH_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "akashTokenImg")
            cell?.tokenSymbol.text = "AKT"
            cell?.tokenSymbol.textColor = COLOR_AKASH
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Akash Staking Token"
            
            let allAkt = WUtils.getAllMainAsset(AKASH_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAkt.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(AKASH_MAIN_DENOM, allAkt, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetPersisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == PERSIS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenpersistence")
            cell?.tokenSymbol.text = "XPRT"
            cell?.tokenSymbol.textColor = COLOR_PERSIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Persistence Staking Token"
            
            let allPersis = WUtils.getAllMainAsset(PERSIS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allPersis.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(PERSIS_MAIN_DENOM, allPersis, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetCrytoItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == CRYPTO_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokencrypto")
            cell?.tokenSymbol.text = "CRO"
            cell?.tokenSymbol.textColor = COLOR_CRYPTO
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Crypto.org Staking Token"
            
            let allCro = WUtils.getAllMainAsset(CRYPTO_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allCro.stringValue, cell!.tokenAmount.font, 8, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(CRYPTO_MAIN_DENOM, allCro, 8, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetSentinelItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == SENTINEL_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokensentinel")
            cell?.tokenSymbol.text = "DVPN"
            cell?.tokenSymbol.textColor = COLOR_SENTINEL
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Sentinel Staking Token"
            let allAtom = WUtils.getAllMainAsset(SENTINEL_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(SENTINEL_MAIN_DENOM, allAtom, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetRizonItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == RIZON_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenRizon")
            cell?.tokenSymbol.text = "ATOLO"
            cell?.tokenSymbol.textColor = COLOR_RIZON
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Rizon Staking Token"
            
            let allCro = WUtils.getAllMainAsset(RIZON_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allCro.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(RIZON_MAIN_DENOM, allCro, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            
        }
        return cell!
    }
    
    func onSetAltheaItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == ALTHEA_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenAlthea")
            cell?.tokenSymbol.text = "ALTG"
            cell?.tokenSymbol.textColor = COLOR_ALTHEA
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Althea Staking Token"
            
            let allAlthea = WUtils.getAllMainAsset(ALTHEA_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAlthea.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(ALTHEA_MAIN_DENOM, allAlthea, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenSymbol.text = balance.denom.substring(from: 1).uppercased()
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            
        }
        return cell!
    }
    
    func onSetOsmoItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == OSMOSIS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenOsmosis")
            cell?.tokenSymbol.text = "OSMO"
            cell?.tokenSymbol.textColor = COLOR_OSMOSIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Osmosis Staking Token"
            
            let allOsmos = WUtils.getAllMainAsset(OSMOSIS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allOsmos.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(OSMOSIS_MAIN_DENOM, allOsmos, 6, cell!.tokenValue.font)
            
        } else if (balance.denom == OSMOSIS_ION_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenIon")
            cell?.tokenSymbol.text = "ION"
            cell?.tokenSymbol.textColor = COLOR_ION
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Ion Token"
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(balance.amount, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(OSMOSIS_ION_DENOM, BaseData.instance.getAvailableAmount_gRPC(OSMOSIS_ION_DENOM), 6, cell!.tokenValue.font)
            
        } else if (balance.isOsmosisAmm()) {
            cell?.tokenImg.image = UIImage(named: "tokenPool")
            cell?.tokenSymbol.text = balance.isOsmosisAmmDpDenom()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = ""
            cell?.tokenDescription.text = balance.denom
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(balance.amount, cell!.tokenAmount.font, 18, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(balance.denom, NSDecimalNumber.init(string: balance.amount), 18, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenSymbol.text = balance.denom.uppercased()
            cell?.tokenTitle.text = ""
            
        }
        return cell!
    }
    
    func onSetBandItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances[indexPath.row]
        if (balance.balance_denom == BAND_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenBand")
            cell?.tokenSymbol.text = "BAND"
            cell?.tokenSymbol.textColor = COLOR_BAND
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Band Staking Token"
            
            let allBand = WUtils.getAllMainAssetOld(BAND_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allBand.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(BAND_MAIN_DENOM, allBand, 6, cell!.tokenValue.font)
        }
        return cell!

//        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
//        let balance = mBalances_gRPC[indexPath.row]
//        if (balance.denom == BAND_MAIN_DENOM) {
//            cell?.tokenImg.image = UIImage(named: "tokenBand")
//            cell?.tokenSymbol.text = "BAND"
//            cell?.tokenSymbol.textColor = COLOR_BAND
//            cell?.tokenTitle.text = "(" + balance.denom + ")"
//            cell?.tokenDescription.text = "Band Staking Token"
//
//            let allBand = WUtils.getAllMainAsset(BAND_MAIN_DENOM)
//            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allBand.stringValue, cell!.tokenAmount.font, 6, 6)
//            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(BAND_MAIN_DENOM, allBand, 6, cell!.tokenValue.font)
//
//        } else if (balance.isIbc()) {
//            onBindIbcToken(cell, balance)
//
//        } else {
//            cell?.tokenImg.image = UIImage(named: "tokenIc")
//            cell?.tokenSymbol.textColor = UIColor.white
//            cell?.tokenSymbol.text = balance.denom.substring(from: 1).uppercased()
//            cell?.tokenTitle.text = "(" + balance.denom + ")"
//
//        }
//        return cell!
    }
    
    func onSetIovItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == IOV_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "iovTokenImg")
            cell?.tokenSymbol.text = "IOV"
            cell?.tokenSymbol.textColor = COLOR_IOV
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Starname Staking Token"
            
            let allIov = WUtils.getAllMainAsset(IOV_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIov.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(IOV_MAIN_DENOM, allIov, 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            onBindIbcToken(cell, balance)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenSymbol.text = balance.denom.substring(from: 1).uppercased()
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            
        }
        return cell!
    }
    
    func onSetCosmosTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == COSMOS_TEST_DENOM) {
            cell?.tokenImg.image = UIImage(named: "atom_ic")
            cell?.tokenSymbol.text = "MUON"
            cell?.tokenSymbol.textColor = COLOR_ATOM
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Stargate Staking Token"
            let allAtom = WUtils.getAllMainAsset(COSMOS_TEST_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(COSMOS_TEST_DENOM, allAtom, 6, cell!.tokenValue.font)
            
        }
        return cell!
    }
    
    func onSetIrisTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mBalances_gRPC[indexPath.row]
        if (balance.denom == IRIS_TEST_DENOM) {
            cell?.tokenImg.image = UIImage(named: "irisTokenImg")
            cell?.tokenSymbol.text = "BIF"
            cell?.tokenSymbol.textColor = COLOR_IRIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Bifrost Staking Token"
            let allIris = WUtils.getAllMainAsset(IRIS_TEST_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(IRIS_TEST_DENOM, allIris, 6, cell!.tokenValue.font)
            
        }
        return cell!
    }
    
    //bind ibc tokens
    func onBindIbcToken(_ cell: TokenCell?, _ coin: Coin) {
        cell?.tokenSymbol.textColor = UIColor.white
        guard let ibcToken = BaseData.instance.getIbcToken(coin.getIbcHash()) else {
            cell?.tokenImg.image = UIImage(named: "tokenDefaultIbc")
            cell?.tokenSymbol.text = "UnKnown"
            cell?.tokenTitle.text = ""
            cell?.tokenDescription.text = coin.denom
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(coin.amount, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(coin.denom, NSDecimalNumber.init(string: coin.amount), 6, cell!.tokenValue.font)
            return
        }
        if (ibcToken.auth == true) {
            cell?.tokenImg.af_setImage(withURL: URL(string: ibcToken.moniker!)!)
            cell?.tokenSymbol.text = ibcToken.display_denom?.uppercased()
            cell?.tokenTitle.text = "(" + ibcToken.base_denom! + ")"
            cell?.tokenDescription.text = coin.denom
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(coin.amount, cell!.tokenAmount.font, ibcToken.decimal!, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(ibcToken.base_denom!, NSDecimalNumber.init(string: coin.amount), ibcToken.decimal!, cell!.tokenValue.font)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenDefaultIbc")
            cell?.tokenSymbol.text = "UnKnown"
            cell?.tokenTitle.text = "(" + ibcToken.base_denom! + ")"
            cell?.tokenDescription.text = coin.denom
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(coin.amount, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpUserCurrencyValue(coin.denom, NSDecimalNumber.init(string: coin.amount), 6, cell!.tokenValue.font)
            
        }
    }
    
    
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.onShowAccountSwicth()
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        let link = WUtils.getAccountExplorer(chainType!, mainTabVC.mAccount.account_address)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
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
    
    func sortByName() {
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) { return true }
                if ($1.balance_denom == BNB_MAIN_DENOM) { return false }
                return $0.balance_denom < $1.balance_denom
            }
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            mBalances.sort{
                if ($0.balance_denom == KAVA_MAIN_DENOM) { return true }
                if ($1.balance_denom == KAVA_MAIN_DENOM) { return false }
                if ($0.balance_denom == KAVA_HARD_DENOM) { return true }
                if ($1.balance_denom == KAVA_HARD_DENOM) { return false }
                return $0.balance_denom < $1.balance_denom
            }
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            mBalances.sort{
                if ($0.balance_denom == OKEX_MAIN_DENOM) { return true }
                if ($1.balance_denom == OKEX_MAIN_DENOM) { return false }
                if ($0.balance_denom == OKEX_MAIN_OKB) { return true }
                if ($1.balance_denom == OKEX_MAIN_OKB){ return false }
                if ($0.balance_denom == "okb-c4d") { return true }
                if ($1.balance_denom == "okb-c4d"){ return false }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            mBalances.sort{
                if ($0.balance_denom == SIF_MAIN_DENOM) { return true }
                if ($1.balance_denom == SIF_MAIN_DENOM) { return false }
                return $0.balance_denom < $1.balance_denom
            }
        }
        else if (WUtils.isGRPC(chainType!)) {
            mBalances_gRPC.sort {
                if ($0.denom == WUtils.getMainDenom(chainType)) { return true }
                if ($1.denom == WUtils.getMainDenom(chainType)) { return false }
                if ($0.denom == OSMOSIS_ION_DENOM) { return true }
                if ($1.denom == OSMOSIS_ION_DENOM) { return false }
                return $0.denom < $1.denom
            }
        }
    }
    
    func sortByAmount() {
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) { return true }
                if ($1.balance_denom == BNB_MAIN_DENOM) { return false }
                let totalTokenAmount0 = WUtils.getAllBnbToken($0.balance_denom)
                let totalTokenAmount1 = WUtils.getAllBnbToken($1.balance_denom)
                return totalTokenAmount0.compare(totalTokenAmount1).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            mBalances.sort{
                if ($0.balance_denom == KAVA_MAIN_DENOM) { return true }
                if ($1.balance_denom == KAVA_MAIN_DENOM) { return false }
                if ($0.balance_denom == KAVA_HARD_DENOM) { return true }
                if ($1.balance_denom == KAVA_HARD_DENOM) { return false }
                return WUtils.localeStringToDecimal($0.balance_amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal($0.balance_denom)).compare(WUtils.localeStringToDecimal($1.balance_amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal($1.balance_denom))).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            mBalances.sort{
                if ($0.balance_denom == OKEX_MAIN_DENOM) { return true }
                if ($1.balance_denom == OKEX_MAIN_DENOM) { return false }
                if ($0.balance_denom == OKEX_MAIN_OKB) { return true }
                if ($1.balance_denom == OKEX_MAIN_OKB) { return false }
                if ($0.balance_denom == "okb-c4d") { return true }
                if ($1.balance_denom == "okb-c4d") { return false }
                return WUtils.localeStringToDecimal($0.balance_amount).adding(WUtils.localeStringToDecimal($0.balance_locked)).stringValue > WUtils.localeStringToDecimal($1.balance_amount).adding(WUtils.localeStringToDecimal($1.balance_locked)).stringValue
            }
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            mBalances.sort{
                if ($0.balance_denom == SIF_MAIN_DENOM) { return true }
                if ($1.balance_denom == SIF_MAIN_DENOM) { return false }
                return false
            }
        }
        else if (WUtils.isGRPC(chainType!)) {
            mBalances_gRPC.sort {
                if ($0.denom == WUtils.getMainDenom(chainType)) { return true }
                if ($1.denom == WUtils.getMainDenom(chainType)) { return false }
                if ($0.denom == OSMOSIS_ION_DENOM) { return true }
                if ($1.denom == OSMOSIS_ION_DENOM) { return false }
                return WUtils.localeStringToDecimal($0.amount).compare(WUtils.localeStringToDecimal($1.amount)).rawValue > 0 ? true : false
            }
        }
    }
    
    func sortByValue() {
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) { return true }
                if ($1.balance_denom == BNB_MAIN_DENOM) { return false }
                let totalTokenAmount0 = WUtils.getAllBnbToken($0.balance_denom)
                let totalTokenAmount1 = WUtils.getAllBnbToken($1.balance_denom)
                let totalTokenValue0 = WUtils.getBnbConvertAmount($0.balance_denom, totalTokenAmount0)
                let totalTokenValue1 = WUtils.getBnbConvertAmount($1.balance_denom, totalTokenAmount1)
                return totalTokenValue0.compare(totalTokenValue1).rawValue > 0 ? true : false
            }
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            let balances = mBalances
            mBalances.sort {
                if ($0.balance_denom == KAVA_MAIN_DENOM) { return true }
                if ($1.balance_denom == KAVA_MAIN_DENOM){ return false }
                if ($0.balance_denom == KAVA_HARD_DENOM) { return true }
                if ($1.balance_denom == KAVA_HARD_DENOM) { return false }
                let totalTokenAmount0 = WUtils.getKavaTokenAll($0.balance_denom, balances)
                let totalTokenAmount1 = WUtils.getKavaTokenAll($1.balance_denom, balances)
                let totalTokenValue0 = WUtils.getKavaTokenDollorValue($0.balance_denom, totalTokenAmount0)
                let totalTokenValue1 = WUtils.getKavaTokenDollorValue($1.balance_denom, totalTokenAmount1)
                return totalTokenValue0.compare(totalTokenValue1).rawValue > 0 ? true : false
            }
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            mBalances.sort{
                if ($0.balance_denom == OKEX_MAIN_DENOM) { return true }
                if ($1.balance_denom == OKEX_MAIN_DENOM){ return false }
                if ($0.balance_denom == OKEX_MAIN_OKB) { return true }
                if ($1.balance_denom == OKEX_MAIN_OKB){ return false }
                if ($0.balance_denom == "okb-c4d") { return true }
                if ($1.balance_denom == "okb-c4d"){ return false }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            mBalances.sort{
                if ($0.balance_denom == SIF_MAIN_DENOM) { return true }
                if ($1.balance_denom == SIF_MAIN_DENOM) { return false }
                return false
            }
        }
        
        else if (WUtils.isGRPC(chainType!)) {
            mBalances_gRPC.sort {
                if ($0.denom == WUtils.getMainDenom(chainType)) { return true }
                if ($1.denom == WUtils.getMainDenom(chainType)) { return false }
                if ($0.denom == OSMOSIS_ION_DENOM) { return true }
                if ($1.denom == OSMOSIS_ION_DENOM) { return false }
                return false
            }
        }
        
    }
}
