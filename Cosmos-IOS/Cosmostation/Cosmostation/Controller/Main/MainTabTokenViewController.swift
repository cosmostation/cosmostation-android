//
//  MainTabTokenViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 26/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage
import UserNotifications

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
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalDenom: UILabel!
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
        self.updateTitle()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
    }
    
    func updateTitle() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        WUtils.setDenomTitle(chainType!, totalDenom)
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Hub)"
            totalCard.backgroundColor = TRANS_BG_COLOR_COSMOS
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Hub)"
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_IRIS
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            titleChainImg.image = UIImage(named: "binanceChImg")
            titleChainName.text = "(Binance Chain)"
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_BNB
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            titleChainImg.image = UIImage(named: "kavaImg")
            titleChainName.text = "(KAVA Chain)"
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_KAVA
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            titleChainImg.image = UIImage(named: "iovImg")
            titleChainName.text = "(IOV Chain)"
            titleAlarmBtn.isHidden = true
            totalCard.backgroundColor = TRANS_BG_COLOR_IOV
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            titleChainImg.image = UIImage(named: "kavaTestImg")
            titleChainName.text = "(KAVA Test)"
            titleAlarmBtn.isHidden = true
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
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            onFetchCosmosTokenPrice()
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            onFetchIrisTokenPrice()
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            onFetchBnbTokenPrice()
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            onFetchKavaTokenPrice()
        }
    }
    
    @objc func onRequestFetch() {
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.updateView()
        self.refresher.endRefreshing()
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
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let allAtom = WUtils.getAllAtom(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(allAtom.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(allAtom, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let allIris = WUtils.getAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.dpAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator, totalAmount.font, 6, chainType!)
            totalValue.attributedText = WUtils.dpIrisValue(allIris, BaseData.instance.getLastPrice(), totalValue.font)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
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
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let totalKava = WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            totalAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, totalAmount.font, 6, 6)
            totalValue.attributedText = WUtils.dpAtomValue(totalKava, BaseData.instance.getLastPrice(), totalValue.font)
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
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return onSetBnbItems(tableView, indexPath)
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            return onSetKavaItems(tableView, indexPath)
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
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
            tokenDetailVC.allValidator = mainTabVC.mAllValidator
            tokenDetailVC.allRewards = mainTabVC.mRewardList
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
            tokenDetailVC.allValidator = mainTabVC.mAllValidator
            tokenDetailVC.irisToken = WUtils.getIrisToken(mainTabVC.mIrisTokenList, mainTabVC.mBalances[indexPath.row])
            tokenDetailVC.irisRewards = mainTabVC.mIrisRewards
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
            tokenDetailVC.bnbToken = WUtils.getBnbToken(mainTabVC.mBnbTokenList, mainTabVC.mBalances[indexPath.row])
            tokenDetailVC.bnbTic = WUtils.getTicData(WUtils.getBnbTicSymbol(mainTabVC.mBalances[indexPath.row].balance_denom), mBnbTics)
            self.navigationController?.pushViewController(tokenDetailVC, animated: true)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            tokenDetailVC.balance = mainTabVC.mBalances[indexPath.row]
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
            cell?.tokenTitle.text = ""
            cell?.tokenDescription.text = balance.balance_denom
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
        if let bnbToken = WUtils.getBnbToken(mainTabVC.mBnbTokenList, balance) {
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
                Alamofire.request(url, method: .get).responseImage { response  in
                    guard let image = response.result.value else {
                        return
                    }
                    cell?.tokenImg.image = image
                }
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
            cell?.tokenTitle.text = ""
            cell?.tokenDescription.text = balance.balance_denom
            let totalKava = WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.tokenAmount.font!, 6, 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(totalKava, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            
        } else {
            // TODO no this case yet!
            cell?.tokenImg.image = UIImage(named: "tokenIc")
            cell?.tokenSymbol.text = balance.balance_denom.uppercased()
            cell?.tokenSymbol.textColor = UIColor.white
            cell?.tokenTitle.text = ""
            cell?.tokenDescription.text = balance.balance_denom + " guaranteed by Kava-chain"
            cell?.tokenAmount.attributedText = WUtils.displayAmount2(balance.balance_amount, cell!.tokenAmount.font!, WUtils.getKavaCoinDecimal(balance.balance_denom), 6)
            cell?.tokenValue.attributedText = WUtils.dpAtomValue(NSDecimalNumber.zero, BaseData.instance.getLastPrice(), cell!.tokenValue.font)
            let url = KAVA_COIN_IMG_URL + balance.balance_denom + ".png"
            Alamofire.request(url, method: .get).responseImage { response  in
                guard let image = response.result.value else {
                    return
                }
                cell?.tokenImg.image = image
            }
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
                                self.tokenTableView.reloadRows(at: [[0,i] as IndexPath], with: .fade)
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
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == COSMOS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == COSMOS_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == IRIS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == IRIS_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == BNB_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == KAVA_MAIN_DENOM){
                    return false
                }
                return $0.balance_denom < $1.balance_denom
            }
        }
    }
    
    func sortByAmount() {
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == COSMOS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == COSMOS_MAIN_DENOM){
                    return false
                }
                return WUtils.stringToDecimal($0.balance_amount).compare(WUtils.stringToDecimal($1.balance_amount)).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == IRIS_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == IRIS_MAIN_DENOM){
                    return false
                }
                return WUtils.stringToDecimal($0.balance_amount).compare(WUtils.stringToDecimal($1.balance_amount)).rawValue > 0 ? true : false
            }
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == BNB_MAIN_DENOM){
                    return false
                }
                return $0.getAllAmountBnbToken().compare($1.getAllAmountBnbToken()).rawValue > 0 ? true : false
            }
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == KAVA_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == KAVA_MAIN_DENOM){
                    return false
                }
                return WUtils.stringToDecimal($0.balance_amount).compare(WUtils.stringToDecimal($1.balance_amount)).rawValue > 0 ? true : false
            }
        }
    }
    
    func sortByValue() {
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            mainTabVC.mBalances.sort{
                if ($0.balance_denom == BNB_MAIN_DENOM) {
                    return true
                }
                if ($1.balance_denom == BNB_MAIN_DENOM){
                    return false
                }
                return $0.exchangeBnbValue(WUtils.getTicData(WUtils.getBnbTicSymbol($0.balance_denom), mBnbTics)).compare($1.exchangeBnbValue(WUtils.getTicData(WUtils.getBnbTicSymbol($1.balance_denom), mBnbTics))).rawValue > 0 ? true : false
            }
        }
        
    }
}
