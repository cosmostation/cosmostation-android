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
import Floaty
import SafariServices

class MainTabTokenViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, FloatyDelegate {
    
    let ORDER_BY_NAME = 0
    let ORDER_BY_AMOUNT = 1
    let ORDER_BY_VALUE = 2
    
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleAlarmBtn: UIButton!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var totalCard: CardView!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalDenom: UILabel!
    @IBOutlet weak var kavaOracle: UILabel!
    @IBOutlet weak var tokenCnt: UILabel!
    @IBOutlet weak var btnSort: UIView!
    @IBOutlet weak var sortType: UILabel!
    
    @IBOutlet weak var tokenTableView: UITableView!
    var refresher: UIRefreshControl!
    var mainTabVC: MainTabViewController!
    var mBnbTics = [String : NSMutableDictionary]()
    var mOrder:Int?

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
        
        self.updateView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceFetchDone(_:)), name: Notification.Name("onPriceFetchDone"), object: nil)
        self.updateTitle()
        self.updateView()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    func updateTitle() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        WUtils.setDenomTitle(chainType!, totalDenom)
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        if (chainType! == ChainType.COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Mainnet)"
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_COSMOS
        } else if (chainType! == ChainType.IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_IRIS
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            titleChainImg.image = UIImage(named: "binanceChImg")
            titleChainName.text = "(Binance Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_BNB
        } else if (chainType! == ChainType.KAVA_MAIN) {
            titleChainImg.image = UIImage(named: "kavaImg")
            titleChainName.text = "(Kava Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = false
            totalCard.backgroundColor = TRANS_BG_COLOR_KAVA
        } else if (chainType! == ChainType.IOV_MAIN) {
            titleChainImg.image = UIImage(named: "iovChainImg")
            titleChainName.text = "(Starname Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_IOV
        }  else if (chainType! == ChainType.BAND_MAIN) {
            titleChainImg.image = UIImage(named: "bandChainImg")
            titleChainName.text = "(Band Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_BAND
        } else if (chainType! == ChainType.SECRET_MAIN) {
            titleChainImg.image = UIImage(named: "secretChainImg")
            titleChainName.text = "(Secret Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_SECRET
        } else if (chainType! == ChainType.CERTIK_MAIN) {
            titleChainImg.image = UIImage(named: "certikChainImg")
            titleChainName.text = "(Certik Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_CERTIK
        } else if (chainType! == ChainType.AKASH_MAIN) {
            titleChainImg.image = UIImage(named: "akashChainImg")
            titleChainName.text = "(Akash Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_AKASH
        } else if (chainType! == ChainType.OKEX_MAIN) {
            titleChainImg.image = UIImage(named: "okexChainImg")
            titleChainName.text = "(OKex Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_OK
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            titleChainImg.image = UIImage(named: "chainpersistence")
            titleChainName.text = "(Perisistence Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_PERSIS
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            titleChainImg.image = UIImage(named: "chainsentinel")
            titleChainName.text = "(Sentinel Mainnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_SENTINEL
        }
        
        else if (chainType! == ChainType.COSMOS_TEST) {
            titleChainImg.image = UIImage(named: "cosmosTestChainImg")
            titleChainName.text = "(StarGate Testnet)"
            kavaOracle.isHidden = true
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
        } else if (chainType! == ChainType.IRIS_TEST) {
            titleChainImg.image = UIImage(named: "irisTestChainImg")
            titleChainName.text = "(Bifrost Testnet)"
            kavaOracle.isHidden = true
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
        } else if (chainType! == ChainType.BINANCE_TEST) {
            titleChainImg.image = UIImage(named: "binancetestnet")
            titleChainName.text = "(Binance Testnet)"
            kavaOracle.isHidden = true
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
        } else if (chainType! == ChainType.KAVA_TEST) {
            titleChainImg.image = UIImage(named: "kavaTestImg")
            titleChainName.text = "(Kava Testnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = false
            totalCard.backgroundColor = COLOR_BG_GRAY
        } else if (chainType! == ChainType.IOV_TEST) {
            titleChainImg.image = UIImage(named: "iovTestnetImg")
            titleChainName.text = "(Starname Testnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
        } else if (chainType! == ChainType.OKEX_TEST) {
            titleChainImg.image = UIImage(named: "okexTestnetImg")
            titleChainName.text = "(Okex Testnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
        } else if (chainType! == ChainType.CERTIK_TEST) {
            titleChainImg.image = UIImage(named: "certikTestnetImg")
            titleChainName.text = "(Certik Testnet)"
            titleAlarmBtn.isHidden = true
            kavaOracle.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
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
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.COSMOS_TEST) {
            onFetchCosmosTokenPrice()
            
        } else if (chainType! == ChainType.IRIS_MAIN || chainType! == ChainType.IRIS_TEST) {
            onFetchIrisTokenPrice()
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            onFetchBnbTokenPrice()
            
        } else if (chainType! == ChainType.KAVA_MAIN) {
            onFetchKavaTokenPrice()
            updateFloaty()
            
        } else if (chainType! == ChainType.BINANCE_TEST) {
            updateFloaty()
            
        } else if (chainType! == ChainType.KAVA_TEST) {
            onFetchKavaTokenPrice()
            updateFloaty()
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            onFetchBandTokenPrice()
            
        } else if (chainType! == ChainType.SECRET_MAIN) {
            onFetchSecretTokenPrice()
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            onFetchIovTokenPrice()
            
        } else if (chainType! == ChainType.IOV_TEST) {
            onFetchIovTokenPrice()
            updateFloaty()
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            onFetchOkTokenPrice()
            updateFloaty()
            
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            onFetchCertikTokenPrice()
            
        } else if (chainType! == ChainType.AKASH_MAIN) {
            onFetchAkashTokenPrice()
            
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            onFetchPersisTokenPrice()
            
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            onFetchSentinelTokenPrice()
            
        }
        
    }
    
    func updateFloaty() {
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        floaty.fabDelegate = nil
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.updateView()
        self.refresher.endRefreshing()
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
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
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            var allBnb = NSDecimalNumber.zero
            for balance in mainTabVC.mBalances {
                if (balance.balance_denom == BNB_MAIN_DENOM) {
                    allBnb = allBnb.adding(WUtils.getAllBnb(balance))
                } else {
                    allBnb = allBnb.adding(balance.exchangeBnbValue(WUtils.getTicData(WUtils.getBnbTicSymbol(balance.balance_denom), mBnbTics)))
                }
            }
            totalAmount.attributedText = WUtils.displayAmount2(allBnb.stringValue, totalAmount.font, 0, 6)
            totalValue.attributedText = WUtils.dpBnbValue(allBnb, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            var allKava = NSDecimalNumber.zero
            for balance in mainTabVC.mBalances {
                if (balance.balance_denom == KAVA_MAIN_DENOM) {
                    allKava = allKava.adding(WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator))
                } else {
                    let totalTokenAmount = WUtils.getKavaTokenAll(balance.balance_denom, mainTabVC.mBalances)
                    let totalTokenValue = WUtils.getKavaTokenDollorValue(balance.balance_denom, totalTokenAmount)
                    let convertedKavaAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(6))
                    allKava = allKava.adding(convertedKavaAmount.multiplying(byPowerOf10: 6))
                }
            }
            totalAmount.attributedText = WUtils.displayAmount2(allKava.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allKava, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            let allBand = WUtils.getAllBand(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allBand.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allBand, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.SECRET_MAIN) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            let allSecret = WUtils.getAllSecret(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allSecret.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allSecret, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            let allIov = WUtils.getAllIov(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allIov.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allIov, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            var allOKT = NSDecimalNumber.zero
            for balance in mainTabVC.mBalances {
                if (balance.balance_denom == OKEX_MAIN_DENOM) {
                    allOKT = allOKT.adding(WUtils.getAllOkt(mainTabVC.mBalances, BaseData.instance.mOkStaking, BaseData.instance.mOkUnbonding))
                } else {
                    let okToken = WUtils.getOkToken(BaseData.instance.mOkTokenList, balance.balance_denom)
                    let totalTokenAmount = balance.getAllAmountOKToken()
                    let totalTokenValue = WUtils.getOkexTokenDollorValue(okToken, totalTokenAmount)
                    let convertedOKTAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler6)
                    allOKT = allOKT.adding(convertedOKTAmount)
                }
            }
            totalAmount.attributedText = WUtils.displayAmount2(allOKT.stringValue, totalAmount.font, 0, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allOKT, BaseData.instance.getLastPrice(), 0, totalValue.font)
            
        } else if (chainType! == ChainType.CERTIK_MAIN || chainType! == ChainType.CERTIK_TEST) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            let allCtk = WUtils.getAllCertik(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allCtk.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allCtk, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.SENTINEL_MAIN) {
            self.tokenCnt.text = String(mainTabVC.mBalances.count)
            let allDvpn = WUtils.getAllSentinel(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allDvpn.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allDvpn, BaseData.instance.getLastPrice(), totalValue.font)
        }
        
        else if (chainType! == ChainType.COSMOS_MAIN) {
            self.tokenCnt.text = String(BaseData.instance.mMyBalances_gRPC.count)
            let allAtom = WUtils.getAllMainAsset(COSMOS_MAIN_DENOM)
            totalAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allAtom, BaseData.instance.getLastPrice(), 6, totalValue.font)
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            self.tokenCnt.text = String(BaseData.instance.mMyBalances_gRPC.count)
            let allIris = WUtils.getAllMainAsset(IRIS_MAIN_DENOM)
            totalAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allIris, BaseData.instance.getLastPrice(), 6, totalValue.font)
            
        } else if (chainType! == ChainType.AKASH_MAIN) {
            self.tokenCnt.text = String(BaseData.instance.mMyBalances_gRPC.count)
            let allAkt = WUtils.getAllMainAsset(AKASH_MAIN_DENOM)
            totalAmount.attributedText = WUtils.displayAmount2(allAkt.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allAkt, BaseData.instance.getLastPrice(), 6, totalValue.font)
            
        } else if (chainType! == ChainType.PERSIS_MAIN) {
            self.tokenCnt.text = String(BaseData.instance.mMyBalances_gRPC.count)
            let allXprt = WUtils.getAllMainAsset(PERSIS_MAIN_DENOM)
            totalAmount.attributedText = WUtils.displayAmount2(allXprt.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allXprt, BaseData.instance.getLastPrice(), 6, totalValue.font)
            
        }
        
        
        else if (chainType! == ChainType.COSMOS_TEST) {
            self.tokenCnt.text = String(BaseData.instance.mMyBalances_gRPC.count)
            let allAtom = WUtils.getAllMainAsset(COSMOS_TEST_DENOM)
            totalAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allAtom, BaseData.instance.getLastPrice(), 6, totalValue.font)
            
        } else if (chainType! == ChainType.IRIS_TEST) {
            self.tokenCnt.text = String(BaseData.instance.mMyBalances_gRPC.count)
            let allIris = WUtils.getAllMainAsset(IRIS_TEST_DENOM)
            totalAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allIris, BaseData.instance.getLastPrice(), 6, totalValue.font)
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (WUtils.isGRPC(chainType!)) {
            return BaseData.instance.mMyBalances_gRPC.count
        } else {
            return mainTabVC.mBalances.count;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType! == ChainType.COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath)
        } else if (chainType! == ChainType.IRIS_MAIN) {
            return onSetIrisItems(tableView, indexPath)
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            return onSetBnbItems(tableView, indexPath)
        } else if (chainType! == ChainType.KAVA_MAIN) {
            return onSetKavaItems(tableView, indexPath)
        } else if (chainType! == ChainType.KAVA_TEST) {
            return onSetKavaTestItems(tableView, indexPath)
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
        } else if (chainType! == ChainType.COSMOS_TEST) {
            return onSetCosmosTestItems(tableView, indexPath)
        } else if (chainType! == ChainType.IRIS_TEST) {
            return onSetIrisTestItems(tableView, indexPath)
        }
        return onSetCosmosItems(tableView, indexPath)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (WUtils.isGRPC(chainType!)) {
            let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
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
            let balance = mainTabVC.mBalances[indexPath.row]
            if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
                if (balance.balance_denom == WUtils.getMainDenom(chainType)) {
                    let sTokenDetailVC = StakingTokenDetailViewController(nibName: "StakingTokenDetailViewController", bundle: nil)
                    sTokenDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(sTokenDetailVC, animated: true)
                    
                } else {
                    let tokenDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "TokenDetailViewController") as! TokenDetailViewController
                    tokenDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    tokenDetailVC.balance = balance
                    tokenDetailVC.allValidator = mainTabVC.mAllValidator
                    tokenDetailVC.allRewards = mainTabVC.mRewardList
                    self.navigationController?.pushViewController(tokenDetailVC, animated: true)
                }
                
            } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
                let tokenDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "TokenDetailViewController") as! TokenDetailViewController
                tokenDetailVC.hidesBottomBarWhenPushed = true
                tokenDetailVC.balance = balance
                tokenDetailVC.bnbToken = WUtils.getBnbToken(BaseData.instance.mBnbTokenList, mainTabVC.mBalances[indexPath.row])
                tokenDetailVC.bnbTic = WUtils.getTicData(WUtils.getBnbTicSymbol(mainTabVC.mBalances[indexPath.row].balance_denom), mBnbTics)
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(tokenDetailVC, animated: true)
                
            } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
                let tokenDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "TokenDetailViewController") as! TokenDetailViewController
                tokenDetailVC.hidesBottomBarWhenPushed = true
                tokenDetailVC.okDenom = balance.balance_denom
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            }
        }
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
        if (balance.denom == COSMOS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "atom_ic")
            cell?.tokenSymbol.text = "ATOM"
            cell?.tokenSymbol.textColor = COLOR_ATOM
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Cosmos Staking Token"
            let allAtom = WUtils.getAllMainAsset(COSMOS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(allAtom, BaseData.instance.getLastPrice(), 6, cell!.tokenValue.font)

        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetCosmosTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
        if (balance.denom == COSMOS_TEST_DENOM) {
            cell?.tokenImg.image = UIImage(named: "atom_ic")
            cell?.tokenSymbol.text = "MUON"
            cell?.tokenSymbol.textColor = COLOR_ATOM
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Stargate Staking Token"
            let allAtom = WUtils.getAllMainAsset(COSMOS_TEST_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(allAtom, BaseData.instance.getLastPrice(), 6, cell!.tokenValue.font)
        }
        return cell!
    }
    
    func onSetIrisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
        if (balance.denom == IRIS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "irisTokenImg")
            cell?.tokenSymbol.text = "IRIS"
            cell?.tokenSymbol.textColor = COLOR_IRIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Iris Staking Token"
            let allIris = WUtils.getAllMainAsset(IRIS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(allIris, BaseData.instance.getLastPrice(), 6, cell!.tokenValue.font)
            
        } else {
            
        }
        return cell!
    }
    
    func onSetIrisTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
        if (balance.denom == IRIS_TEST_DENOM) {
            cell?.tokenImg.image = UIImage(named: "irisTokenImg")
            cell?.tokenSymbol.text = "BIF"
            cell?.tokenSymbol.textColor = COLOR_IRIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Bifrost Staking Token"
            let allIris = WUtils.getAllMainAsset(IRIS_TEST_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(allIris, BaseData.instance.getLastPrice(), 6, cell!.tokenValue.font)
            
        } else if (balance.isIbc()) {
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            
        } else {
            
        }
        return cell!
    }
    
    func onSetBnbItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if let bnbToken = WUtils.getBnbToken(BaseData.instance.mBnbTokenList, balance) {
            cell?.tokenSymbol.text = bnbToken.original_symbol.uppercased()
            cell?.tokenTitle.text = "(" + bnbToken.symbol + ")"
            cell?.tokenDescription.text = bnbToken.name
            let totalAmount = WUtils.getAllBnb(balance)
            if (balance.balance_denom == BNB_MAIN_DENOM) {
                cell?.tokenSymbol.textColor = COLOR_BNB
                cell?.tokenImg.image = UIImage(named: "bnbTokenImg")
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.tokenAmount.font, 0, 6)
                cell?.tokenValue.attributedText = WUtils.dpBnbValue(totalAmount, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
                
            } else {
                cell?.tokenSymbol.textColor = UIColor.white
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.tokenAmount.font, 0, 6)
                let convertAmount = balance.exchangeBnbValue(WUtils.getTicData(WUtils.getBnbTicSymbol(balance.balance_denom), mBnbTics))
                cell?.tokenValue.attributedText = WUtils.dpBnbValue(convertAmount, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
                
                let url = TOKEN_IMG_URL + bnbToken.original_symbol + ".png"
                cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
            }
        }
        return cell!
    }
    
    func onSetKavaItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == KAVA_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "kavaTokenImg")
            cell?.tokenSymbol.text = "KAVA"
            cell?.tokenSymbol.textColor = COLOR_KAVA
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Kava Chain Native Token"
            
            let totalKava = WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.tokenAmount.font!, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(totalKava, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            
        } else {
            let totalTokenAmount = WUtils.getKavaTokenAll(balance.balance_denom, mainTabVC.mBalances)
            let totalTokenValue = WUtils.getKavaTokenDollorValue(balance.balance_denom, totalTokenAmount)
            let convertedKavaAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)))
            
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            if (balance.balance_denom == "usdx") {
                cell?.tokenDescription.text = "USD Stable Asset"
            } else if (balance.balance_denom == KAVA_HARD_DENOM) {
                cell?.tokenDescription.text = "Harvest Gov. Token"
            } else {
                cell?.tokenDescription.text = balance.balance_denom.uppercased() + " on Kava Chain"
            }
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalTokenAmount.stringValue, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(convertedKavaAmount.multiplying(byPowerOf10: WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)), BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            let url = KAVA_COIN_IMG_URL + balance.balance_denom + ".png"
            cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
        }
        return cell!
    }
    
    func onSetKavaTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == KAVA_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "kavaTokenImg")
            cell?.tokenSymbol.text = "KAVA"
            cell?.tokenSymbol.textColor = COLOR_KAVA
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Kava Chain Native Token"
            
            let totalKava = WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.tokenAmount.font!, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(totalKava, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            
        } else {
            let totalTokenAmount = WUtils.getKavaTokenAll(balance.balance_denom, mainTabVC.mBalances)
            let totalTokenValue = WUtils.getKavaTokenDollorValue(balance.balance_denom, totalTokenAmount)
            let convertedKavaAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)))
            
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            if (balance.balance_denom == "usdx") {
                cell?.tokenDescription.text = "USD Stable Asset"
            } else if (balance.balance_denom == "hard") {
                cell?.tokenDescription.text = "Harvest Gov. Token"
            } else {
                cell?.tokenDescription.text = balance.balance_denom.uppercased() + " on Kava Chain"
            }
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalTokenAmount.stringValue, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(convertedKavaAmount.multiplying(byPowerOf10: WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)), BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            let url = KAVA_COIN_IMG_URL + balance.balance_denom + ".png"
            cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
        }
        return cell!
    }
    
    func onSetIovItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == IOV_MAIN_DENOM || balance.balance_denom == IOV_TEST_DENOM) {
            cell?.tokenImg.image = UIImage(named: "iovTokenImg")
            cell?.tokenSymbol.text = "IOV"
            cell?.tokenSymbol.textColor = COLOR_IOV
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Starname Native Token"
                        
            let allIov = WUtils.getAllIov(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIov.stringValue, cell!.tokenAmount.font!, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allIov, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            
        } else { }
        return cell!
    }
    
    func onSetBandItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == BAND_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "bandTokenImg")
            cell?.tokenSymbol.text = "BAND"
            cell?.tokenSymbol.textColor = COLOR_BAND
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Band Chain Native Token"
            let allBand = WUtils.getAllBand(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allBand.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allBand, BaseData.instance.getLastPrice(), cell!.tokenValue.font)

        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetSecretItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == SECRET_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "secretTokenImg")
            cell?.tokenSymbol.text = "SCRT"
            cell?.tokenSymbol.textColor = COLOR_SECRET
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Secret Native Token"
            let allSecret = WUtils.getAllSecret(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allSecret.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allSecret, BaseData.instance.getLastPrice(), cell!.tokenValue.font)

        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetOkItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        let okToken = WUtils.getOkToken(BaseData.instance.mOkTokenList, balance.balance_denom)
        if (okToken == nil) {
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(balance.balance_amount, cell!.tokenAmount.font, 0, 6)
            cell?.tokenDescription.text = ""
            return cell!
            
        }
        cell?.tokenSymbol.text = okToken?.original_symbol?.uppercased()
        cell?.tokenTitle.text = "(" + okToken!.symbol! + ")"
        cell?.tokenDescription.text = okToken?.description
        
        if (balance.balance_denom == OKEX_MAIN_DENOM) {
            cell?.tokenSymbol.textColor = COLOR_OK
            cell?.tokenImg.image = UIImage(named: "okexTokenImg")
            
            let tokenAmount = WUtils.getAllOkt(mainTabVC.mBalances, BaseData.instance.mOkStaking, BaseData.instance.mOkUnbonding)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(tokenAmount.stringValue, cell!.tokenAmount.font, 0, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(tokenAmount, BaseData.instance.getLastPrice(), 0, cell!.tokenValue.font)
            
        } else {
            let totalTokenAmount = balance.getAllAmountOKToken()
            let totalTokenValue = WUtils.getOkexTokenDollorValue(okToken, totalTokenAmount)
            let convertedOKTAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler6)
            
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenImg.af_setImage(withURL: URL(string: OKEX_COIN_IMG_URL + okToken!.original_symbol! + ".png")!)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalTokenAmount.stringValue, cell!.tokenAmount.font, 0, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(convertedOKTAmount, BaseData.instance.getLastPrice(), 0, cell!.tokenValue.font)
        }
        return cell!
    }
    
    func onSetCertikItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == CERTIK_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "certikTokenImg")
            cell?.tokenSymbol.text = "CTK"
            cell?.tokenSymbol.textColor = COLOR_CERTIK
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Certik Staking Token"
            let allCtk = WUtils.getAllCertik(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allCtk.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allCtk, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetAkashItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
        if (balance.denom == AKASH_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "akashTokenImg")
            cell?.tokenSymbol.text = "AKT"
            cell?.tokenSymbol.textColor = COLOR_AKASH
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Akash Staking Token"
            let allAkt = WUtils.getAllMainAsset(AKASH_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAkt.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(allAkt, BaseData.instance.getLastPrice(), 6, cell!.tokenValue.font)
            
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetPersisItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = BaseData.instance.mMyBalances_gRPC[indexPath.row]
        if (balance.denom == PERSIS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokenpersistence")
            cell?.tokenSymbol.text = "XPRT"
            cell?.tokenSymbol.textColor = COLOR_PERSIS
            cell?.tokenTitle.text = "(" + balance.denom + ")"
            cell?.tokenDescription.text = "Persistence Staking Token"
            let allAkt = WUtils.getAllMainAsset(PERSIS_MAIN_DENOM)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAkt.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(allAkt, BaseData.instance.getLastPrice(), 6, cell!.tokenValue.font)
            
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    func onSetSentinelItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == SENTINEL_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "tokensentinel")
            cell?.tokenSymbol.text = "DVPN"
            cell?.tokenSymbol.textColor = COLOR_SENTINEL
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Sentinel Staking Token"
            let allDvpn = WUtils.getAllSentinel(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allDvpn.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allDvpn, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
        }
        return cell!
    }
    
    
    
    func onFetchCosmosTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchIrisTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchBnbTokenPrice() {
        for i in 0..<mainTabVC.mBalances.count {
            if (!(mainTabVC.mBalances[i].balance_denom == BNB_MAIN_DENOM)) {
                let ticSymbol = WUtils.getBnbTicSymbol(mainTabVC.mBalances[i].balance_denom)
                let request = Alamofire.request(BNB_URL_TIC, method: .get, parameters: ["symbol":ticSymbol], encoding: URLEncoding.default, headers: [:])
                request.responseJSON { (response) in
                    switch response.result {
                    case .success(let res):
                        if let tics = res as? Array<NSDictionary> {
                            if (tics.count > 0) {
                                self.mBnbTics[ticSymbol] = tics[0].mutableCopy() as? NSMutableDictionary
                                self.tokenTableView.reloadRows(at: [[0,i] as IndexPath], with: .none)
                            }
                        }
                        self.onUpdateTotalCard()

                    case .failure(let error):
                        if (SHOW_LOG) { print("onFetchBnbTokenPrice ", ticSymbol, " ", error) }
                    }
                }
            }
        }
    }
    
    func onFetchKavaTokenPrice() {
    }
    
    func onFetchBandTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchSecretTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchIovTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchOkTokenPrice() {
        for i in 0..<mainTabVC.mBalances.count {
            if ((mainTabVC.mBalances[i].balance_denom == "okb-c4d")) {
//            if ((mainTabVC.mBalances[i].balance_denom == OKEX_MAIN_OKB)) {
                let url = CGC_PRICE_TIC + "okb"
                let request = Alamofire.request(url, method: .get, parameters: ["localization":"false", "tickers":"false", "community_data":"false", "developer_data":"false", "sparkline":"false"], encoding: URLEncoding.default, headers: [:]);
                request.responseJSON { (response) in
                    switch response.result {
                    case .success(let res):
                        if let tics = res as? NSDictionary, let priceUsd = tics.value(forKeyPath: "market_data.current_price.usd") as? Double {
                            BaseData.instance.mOKBPrice = NSDecimalNumber.init(value: priceUsd)
                            self.tokenTableView.reloadRows(at: [[0,i] as IndexPath], with: .none)
                        }
                        self.onUpdateTotalCard()

                    case .failure(let error):
                        if (SHOW_LOG) { print("onFetchKavaTokenPrice ", error) }
                    }
                }
            }
        }
    }
    
    func onFetchCertikTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchAkashTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchPersisTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchSentinelTokenPrice() {
        self.onUpdateTotalCard()
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
    
    func sortByName() {
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == BNB_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == KAVA_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == OKEX_MAIN_DENOM) { return true }
                if ($1.balance_denom == OKEX_MAIN_DENOM) { return false }
                if ($0.balance_denom == OKEX_MAIN_OKB) { return true }
                if ($1.balance_denom == OKEX_MAIN_OKB){ return false }
                if ($0.balance_denom == "okb-c4d") { return true }
                if ($1.balance_denom == "okb-c4d"){ return false }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mMyBalances_gRPC.sort {
                if ($0.denom == WUtils.getMainDenom(chainType)) { return true }
                if ($1.denom == WUtils.getMainDenom(chainType)) { return false }
                return $0.denom < $1.denom
            }
        }
    }
    
    func sortByAmount() {
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == BNB_MAIN_DENOM){
                    return false
                }
                return $0.getAllAmountBnbToken().compare($1.getAllAmountBnbToken()).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == KAVA_MAIN_DENOM){
                    return false
                }
                return WUtils.localeStringToDecimal($0.balance_amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal($0.balance_denom)).compare(WUtils.localeStringToDecimal($1.balance_amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal($1.balance_denom))).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == OKEX_MAIN_DENOM) { return true }
                if ($1.balance_denom == OKEX_MAIN_DENOM) { return false }
                if ($0.balance_denom == OKEX_MAIN_OKB) { return true }
                if ($1.balance_denom == OKEX_MAIN_OKB) { return false }
                if ($0.balance_denom == "okb-c4d") { return true }
                if ($1.balance_denom == "okb-c4d") { return false }
                return WUtils.localeStringToDecimal($0.balance_amount).adding(WUtils.localeStringToDecimal($0.balance_locked)).stringValue > WUtils.localeStringToDecimal($1.balance_amount).adding(WUtils.localeStringToDecimal($1.balance_locked)).stringValue
            }
            
        } else if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mMyBalances_gRPC.sort {
                if ($0.denom == WUtils.getMainDenom(chainType)) { return true }
                if ($1.denom == WUtils.getMainDenom(chainType)) { return false }
                return WUtils.localeStringToDecimal($0.amount).compare(WUtils.localeStringToDecimal($1.amount)).rawValue > 0 ? true : false
            }
        }
    }
    
    func sortByValue() {
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == BNB_MAIN_DENOM){
                    return false
                }
                return $0.exchangeBnbValue(WUtils.getTicData(WUtils.getBnbTicSymbol($0.balance_denom), mBnbTics)).compare($1.exchangeBnbValue(WUtils.getTicData(WUtils.getBnbTicSymbol($1.balance_denom), mBnbTics))).rawValue > 0 ? true : false
            }
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            let balances = mainTabVC.mBalances
            mainTabVC.mBalances.sort {
                if ($0.balance_denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == KAVA_MAIN_DENOM){
                    return false
                }
                let totalTokenAmount0 = WUtils.getKavaTokenAll($0.balance_denom, balances)
                let totalTokenAmount1 = WUtils.getKavaTokenAll($1.balance_denom, balances)
                let totalTokenValue0 = WUtils.getKavaTokenDollorValue($0.balance_denom, totalTokenAmount0)
                let totalTokenValue1 = WUtils.getKavaTokenDollorValue($1.balance_denom, totalTokenAmount1)
                return totalTokenValue0.compare(totalTokenValue1).rawValue > 0 ? true : false
            }
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == OKEX_MAIN_DENOM) { return true }
                if ($1.balance_denom == OKEX_MAIN_DENOM){ return false }
                if ($0.balance_denom == OKEX_MAIN_OKB) { return true }
                if ($1.balance_denom == OKEX_MAIN_OKB){ return false }
                if ($0.balance_denom == "okb-c4d") { return true }
                if ($1.balance_denom == "okb-c4d"){ return false }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (WUtils.isGRPC(chainType!)) {
            BaseData.instance.mMyBalances_gRPC.sort {
                if ($0.denom == WUtils.getMainDenom(chainType)) { return true }
                if ($1.denom == WUtils.getMainDenom(chainType)) { return false }
                return false
            }
        }
        
    }
}
