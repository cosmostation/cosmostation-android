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
        } else if (chainType! == ChainType.BINANCE_TEST) {
            titleChainImg.image = UIImage(named: "binancetestnet")
            titleChainName.text = "(Binance Testnet)"
            kavaOracle.isHidden = true
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = COLOR_BG_GRAY
        }  else if (chainType! == ChainType.KAVA_TEST) {
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
        if (chainType! == ChainType.COSMOS_MAIN) {
            onFetchCosmosTokenPrice()
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
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
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            onFetchIovTokenPrice()
            
        } else if (chainType! == ChainType.IOV_TEST) {
            onFetchIovTokenPrice()
            updateFloaty()
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            onFetchOkTokenPrice()
            updateFloaty()
            
        }
        
    }
    
    func updateFloaty() {
        if (chainType! == ChainType.KAVA_MAIN) {
            if (mainTabVC.mAccount.account_account_numner <= 0 && mainTabVC.mAccount.account_has_private) {
                let floaty = Floaty()
                floaty.buttonImage = UIImage.init(named: "airdropBtn")
                floaty.buttonColor = COLOR_KAVA
                floaty.fabDelegate = self
                self.view.addSubview(floaty)
            }
            
        } else if (chainType! == ChainType.KAVA_TEST) {
            let floaty = Floaty()
            floaty.buttonImage = UIImage.init(named: "faucetBtn")
            floaty.buttonColor = COLOR_KAVA
            floaty.fabDelegate = self
            self.view.addSubview(floaty)
            
        } else if (chainType! == ChainType.BINANCE_TEST) {
            let floaty = Floaty()
            floaty.buttonImage = UIImage.init(named: "faucetBtn")
            floaty.buttonColor = COLOR_BNB
            floaty.fabDelegate = self
            self.view.addSubview(floaty)
            
        } else if (chainType! == ChainType.IOV_TEST) {
            let floaty = Floaty()
            floaty.buttonImage = UIImage.init(named: "faucetBtn")
            floaty.buttonColor = COLOR_IOV
            floaty.fabDelegate = self
            self.view.addSubview(floaty)
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            let floaty = Floaty()
            floaty.buttonImage = UIImage.init(named: "faucetBtn")
            floaty.buttonColor = COLOR_OK
            floaty.fabDelegate = self
            self.view.addSubview(floaty)
        }
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        floaty.fabDelegate = nil
        self.onRequestFaucet()
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
        self.tokenCnt.text = String(mainTabVC.mBalances.count)
        if (chainType! == ChainType.COSMOS_MAIN) {
            let allAtom = WUtils.getAllAtom(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allAtom, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            let allIris = WUtils.getAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.dpAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator, totalAmount.font, 6, chainType!)
            totalValue.attributedText = WUtils.dpIrisValue(allIris, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
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
            var allKava = NSDecimalNumber.zero
            for balance in mainTabVC.mBalances {
                if (balance.balance_denom == KAVA_MAIN_DENOM) {
                    allKava = allKava.adding(WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator))
                } else {
                    let tokenTotalValue = balance.kavaTokenDollorValue(BaseData.instance.mKavaPrice, BaseData.instance.mCdpParam!)
                    let convertedKavaAmount = tokenTotalValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(6))
                    allKava = allKava.adding(convertedKavaAmount.multiplying(byPowerOf10: 6))
                }
            }
            totalAmount.attributedText = WUtils.displayAmount2(allKava.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allKava, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            let allBand = WUtils.getAllBand(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allBand.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allBand, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            let allIov = WUtils.getAllIov(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allIov.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allIov, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            let allOk = WUtils.getAllOkt(mainTabVC.mBalances, BaseData.instance.mOkDeposit, BaseData.instance.mOkWithdraw)
            totalAmount.attributedText = WUtils.displayAmount2(allOk.stringValue, totalAmount.font, 0, 6)
            totalValue.attributedText = WUtils.dpTokenValue(allOk, BaseData.instance.getLastPrice(), 0, totalValue.font)
            
            
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mainTabVC.mBalances.count;
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
        } else if (chainType! == ChainType.OKEX_TEST) {
            return onSetOkItems(tableView, indexPath)
        }
        return onSetCosmosItems(tableView, indexPath)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let tokenDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "TokenDetailViewController") as! TokenDetailViewController
        tokenDetailVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        if (chainType! == ChainType.COSMOS_MAIN || chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
            tokenDetailVC.allValidator = mainTabVC.mAllValidator
            tokenDetailVC.allRewards = mainTabVC.mRewardList
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
            tokenDetailVC.allValidator = mainTabVC.mAllValidator
            tokenDetailVC.irisToken = WUtils.getIrisToken(mainTabVC.mIrisTokenList, mainTabVC.mBalances[indexPath.row])
            tokenDetailVC.irisRewards = mainTabVC.mIrisRewards
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
            tokenDetailVC.bnbToken = WUtils.getBnbToken(BaseData.instance.mBnbTokenList, mainTabVC.mBalances[indexPath.row])
            tokenDetailVC.bnbTic = WUtils.getTicData(WUtils.getBnbTicSymbol(mainTabVC.mBalances[indexPath.row].balance_denom), mBnbTics)
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            //TODO IOV toekn details
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            //TODO IOV toekn details
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            tokenDetailVC.okDenom = mainTabVC.mBalances[indexPath.row].balance_denom
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
        }
        
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        if (balance.balance_denom == COSMOS_MAIN_DENOM) {
            cell?.tokenImg.image = UIImage(named: "atom_ic")
            cell?.tokenSymbol.text = "ATOM"
            cell?.tokenSymbol.textColor = COLOR_ATOM
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            cell?.tokenDescription.text = "Cosmos Hub Staking Token"
            let allAtom = WUtils.getAllAtom(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, cell!.tokenAmount.font, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(allAtom, BaseData.instance.getLastPrice(), cell!.tokenValue.font)

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
                cell?.tokenAmount.attributedText = WUtils.displayAmount2(allIris.stringValue, cell!.tokenAmount.font, 18, 6)
                cell?.tokenValue.attributedText = WUtils.dpIrisValue(allIris, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
                
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
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            if (balance.balance_denom == "usdx") {
                cell?.tokenDescription.text = "USD Stable Asset"
            } else {
                cell?.tokenDescription.text = balance.balance_denom.uppercased() + " on Kava Chain"
            }
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(balance.balance_amount, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            
            let tokenTotalValue = balance.kavaTokenDollorValue(BaseData.instance.mKavaPrice, BaseData.instance.mCdpParam!)
            let convertedKavaAmount = tokenTotalValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)))
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
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = "(" + balance.balance_denom + ")"
            if (balance.balance_denom == "usdx") {
                cell?.tokenDescription.text = "USD Stable Asset"
            } else {
                cell?.tokenDescription.text = balance.balance_denom.uppercased() + " on Kava Chain"
            }
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(balance.balance_amount, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            
            let tokenTotalValue = balance.kavaTokenDollorValue(BaseData.instance.mKavaPrice, BaseData.instance.mCdpParam!)
            let convertedKavaAmount = tokenTotalValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)))
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
            cell?.tokenDescription.text = "Starname Chain Native Token"
                        
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
    
    func onSetOkItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenCell? = tableView.dequeueReusableCell(withIdentifier:"TokenCell") as? TokenCell
        let balance = mainTabVC.mBalances[indexPath.row]
        let okToken = WUtils.getOkToken(BaseData.instance.mOkTokenList, balance.balance_denom)
        if (balance.balance_denom == OKEX_TEST_DENOM) {
            cell?.tokenImg.image = UIImage(named: "okexTokenImg")
            cell?.tokenSymbol.textColor = COLOR_OK
            let tokenAmount = WUtils.getAllOkt(mainTabVC.mBalances, BaseData.instance.mOkDeposit, BaseData.instance.mOkWithdraw)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(tokenAmount.stringValue, cell!.tokenAmount.font, 0, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(tokenAmount, BaseData.instance.getLastPrice(), 0, cell!.tokenValue.font)
            
        } else {
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.textColor = UIColor.white
            let availableAmount = WUtils.availableAmount(mainTabVC.mBalances, balance.balance_denom)
            let lockedAmount = WUtils.lockedAmount(mainTabVC.mBalances, balance.balance_denom)
            let tokenAmount = availableAmount.adding(lockedAmount)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(tokenAmount.stringValue, cell!.tokenAmount.font, 0, 6)
            cell?.tokenValue.attributedText = WUtils.dpTokenValue(tokenAmount, BaseData.instance.getLastPrice(), 0, cell!.tokenValue.font)
            
        }
        if (okToken != nil) {
            cell?.tokenSymbol.text = okToken?.original_symbol.uppercased()
            cell?.tokenDescription.text = okToken?.description
            cell?.tokenTitle.text = "(" + okToken!.symbol + ")"
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
        self.onUpdateTotalCard()
    }
    
    func onFetchBandTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchIovTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    func onFetchOkTokenPrice() {
        self.onUpdateTotalCard()
    }
    
    
    func onRequestFaucet() {
        if (chainType! == ChainType.BINANCE_TEST) {
            if (mainTabVC.mAccount.getBnbBalance().compare(NSDecimalNumber.init(value: 2)).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_no_more_faucet", comment: ""))
                return
            }
            self.showWaittingAlert()
            let request = Alamofire.request(BNB_TEST_FAUCET +  mainTabVC.mAccount.account_address , method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(2000), execute: {
                        self.onRequestFetch()
                        self.hideWaittingAlert()
                    })

                case .failure(let error):
                    self.onShowToast(error.localizedDescription)
                    self.hideWaittingAlert()
                }
            }
            
        } else if (chainType! == ChainType.KAVA_TEST) {
            if (mainTabVC.mAccount.getKavaBalance().compare(NSDecimalNumber.init(value: 5000000)).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_no_more_faucet", comment: ""))
                return
            }
            self.showWaittingAlert()
            let request = Alamofire.request(KAVA_TEST_FAUCET +  mainTabVC.mAccount.account_address , method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                        self.onRequestFetch()
                        self.hideWaittingAlert()
                    })

                case .failure(let error):
                    self.onShowToast(error.localizedDescription)
                    self.hideWaittingAlert()
                }
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN) {
            if (mainTabVC.mAccount.account_account_numner > 0) {
                self.onShowToast(NSLocalizedString("error_no_more_faucet", comment: ""))
                return
            }
            self.showWaittingAlert()
            let request = Alamofire.request(KAVA_FAUCET +  mainTabVC.mAccount.account_address , method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(10000), execute: {
                        self.onRequestFetch()
                        self.hideWaittingAlert()
                    })

                case .failure(let error):
                    self.onShowToast(error.localizedDescription)
                    self.hideWaittingAlert()
                }
            }
            
        } else if (chainType! == ChainType.IOV_TEST) {
            if (mainTabVC.mAccount.getIovBalance().compare(NSDecimalNumber.init(value: 1000000000)).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_no_more_faucet", comment: ""))
                return
            }
            self.showWaittingAlert()
            let request = Alamofire.request(IOV_TEST_FAUCET +  mainTabVC.mAccount.account_address , method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                        self.onRequestFetch()
                        self.hideWaittingAlert()
                    })

                case .failure(let error):
                    self.onShowToast(error.localizedDescription)
                    self.hideWaittingAlert()
                }
            }
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: "https://www.okex.com/drawdex") else { return }
            let safariViewController = SFSafariViewController(url: url)
            safariViewController.modalPresentationStyle = .popover
            present(safariViewController, animated: true, completion: nil)
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
    
    func sortByName() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == COSMOS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == COSMOS_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == IRIS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == IRIS_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
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
        } else if (chainType! == ChainType.OKEX_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == OKEX_TEST_DENOM) {
                    return true
                }
                if ($1.balance_denom == OKEX_TEST_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
        }
        
//        else if (chainType! == ChainType.BAND_MAIN) {
//
//        } else if (chainType! == ChainType.IOV_MAIN) {
//
//        } else if (chainType! == ChainType.IOV_TEST) {
//
//        }
    }
    
    func sortByAmount() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == COSMOS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == COSMOS_MAIN_DENOM){
                    return false
                }
                return WUtils.localeStringToDecimal($0.balance_amount).compare(WUtils.localeStringToDecimal($1.balance_amount)).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == IRIS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == IRIS_MAIN_DENOM){
                    return false
                }
                return WUtils.localeStringToDecimal($0.balance_amount).compare(WUtils.localeStringToDecimal($1.balance_amount)).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
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
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == OKEX_TEST_DENOM) {
                    return true
                }
                if ($1.balance_denom == OKEX_TEST_DENOM){
                    return false
                }
                return WUtils.localeStringToDecimal($0.balance_amount).adding(WUtils.localeStringToDecimal($0.balance_locked)).stringValue > WUtils.localeStringToDecimal($1.balance_amount).adding(WUtils.localeStringToDecimal($1.balance_locked)).stringValue
            }
        }
        
//        else if (chainType! == ChainType.BAND_MAIN) {
//
//        } else if (chainType! == ChainType.IOV_MAIN) {
//
//        } else if (chainType! == ChainType.IOV_TEST) {
//
//        }
    }
    
    func sortByValue() {
        if (chainType! == ChainType.COSMOS_MAIN) {
        } else if (chainType! == ChainType.IRIS_MAIN) {
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
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
            mainTabVC.mBalances.sort {
                if ($0.balance_denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == KAVA_MAIN_DENOM){
                    return false
                }
                return $0.kavaTokenDollorValue(BaseData.instance.mKavaPrice, BaseData.instance.mCdpParam!).compare($1.kavaTokenDollorValue(BaseData.instance.mKavaPrice, BaseData.instance.mCdpParam!)).rawValue > 0 ? true : false
            }
        } else if (chainType! == ChainType.OKEX_TEST) {
            
        }
        
//        else if (chainType! == ChainType.BAND_MAIN) {
//
//        } else if (chainType! == ChainType.IOV_MAIN) {
//
//        } else if (chainType! == ChainType.IOV_TEST) {
//
//        }
        
    }
}
