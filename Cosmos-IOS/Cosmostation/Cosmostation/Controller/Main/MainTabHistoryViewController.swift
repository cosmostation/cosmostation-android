//
//  MainTabHistoryViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices
import UserNotifications

class MainTabHistoryViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleAlarmBtn: UIButton!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var totalCard: CardView!
    @IBOutlet weak var totalKeyState: UIImageView!
    @IBOutlet weak var totalDpAddress: UILabel!
    @IBOutlet weak var totalValue: UILabel!

    @IBOutlet weak var historyTableView: UITableView!
    @IBOutlet weak var emptyLabel: UILabel!
    @IBOutlet weak var comingLabel: UILabel!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var mBnbHistories = Array<BnbHistory>()
    var mOkHistories = Array<OkHistory.DataDetail>()
    var mApiHistories = Array<ApiHistory.HistoryData>()
    var mApiCustomHistories = Array<ApiHistoryCustom>()
    var mApiCustomNewHistories = Array<ApiHistoryNewCustom>()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.historyTableView.delegate = self
        self.historyTableView.dataSource = self
        self.historyTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.historyTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        self.historyTableView.register(UINib(nibName: "NewHistoryCell", bundle: nil), forCellReuseIdentifier: "NewHistoryCell")
        
        self.historyTableView.rowHeight = UITableView.automaticDimension
        self.historyTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.historyTableView.addSubview(refresher)
        
        self.onRequestFetch()
        
        let tapTotalCard = UITapGestureRecognizer(target: self, action: #selector(self.onClickActionShare))
        self.totalCard.addGestureRecognizer(tapTotalCard)
        
        self.emptyLabel.isUserInteractionEnabled = true
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(testClick(tapGestureRecognizer:)))
        self.emptyLabel.addGestureRecognizer(tapGesture)
    }
    
    @objc func testClick(tapGestureRecognizer: UITapGestureRecognizer) {
//        let txDetailVC = TxDetailgRPCViewController(nibName: "TxDetailgRPCViewController", bundle: nil)
//        txDetailVC.mIsGen = false
//        txDetailVC.mTxHash = "19EFC831F2DCC2B919094B4F32936F01B6F8953980227482FBE4753B10840244"
//        txDetailVC.hidesBottomBarWhenPushed = true
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(txDetailVC, animated: true)
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchPrice(_:)), name: Notification.Name("onFetchPrice"), object: nil)
        self.updateTitle()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchPrice"), object: nil)
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.historyTableView.reloadData()
    }
    
    @objc func onFetchPrice(_ notification: NSNotification) {
        self.totalValue.attributedText = WUtils.dpAllAssetValueUserCurrency(chainType, totalValue.font)
    }
    
    func updateTitle() {
        self.titleChainImg.image = WUtils.getChainImg(chainType)
        self.titleChainName.text = WUtils.getChainTitle(chainType)
        self.titleChainName.textColor = WUtils.getChainColor(chainType!)
        self.titleWalletName.text = WUtils.getWalletName(account)
        self.titleAlarmBtn.isHidden = (chainType! == ChainType.COSMOS_MAIN) ? false : true
        
        self.totalCard.backgroundColor = WUtils.getChainBg(chainType)
        if (account?.account_has_private == true) {
            self.totalKeyState.image = totalKeyState.image?.withRenderingMode(.alwaysTemplate)
            self.totalKeyState.tintColor = WUtils.getChainColor(chainType)
        }
        self.totalDpAddress.text = account?.dpAddress(chainType)
        self.totalDpAddress.adjustsFontSizeToFitWidth = true
        self.totalValue.attributedText = WUtils.dpAllAssetValueUserCurrency(chainType, totalValue.font)
        
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
    
    @objc func onRequestFetch() {
        if (chainType == ChainType.CRYPTO_MAIN) {
            onFetchApiHistoryCustom(account!.account_address)
        } else if (chainType == ChainType.KI_MAIN) {
            onFetchLegacyOldApiHistory(account!.account_address)
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            onFetchBnbHistory(account!.account_address)
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            onFetchOkHistory(account!.account_address)
        } else if (chainType == ChainType.SECRET_MAIN) {
            self.comingLabel.text = "Check with Explorer"
            self.comingLabel.isHidden = false
        } else if (chainType == ChainType.MEDI_TEST) {
            self.comingLabel.text = "Support Soon"
            self.comingLabel.isHidden = false
        } else {
            onFetchNewApiHistoryCustom(account!.account_address)
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 30
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = CommonHeader(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view.headerTitleLabel.text = "Recently Histories"
        var cntString = "0"
        if (chainType == ChainType.CRYPTO_MAIN) {
            cntString = String(self.mApiCustomHistories.count)
        } else if (chainType == ChainType.KI_MAIN) {
            cntString = String(self.mApiHistories.count)
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            cntString = String(self.mBnbHistories.count)
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            cntString = String(self.mOkHistories.count)
        } else {
            cntString = String(self.mApiCustomNewHistories.count)
        }
        view.headerCntLabel.text = cntString
        return view
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.CRYPTO_MAIN) {
            return self.mApiCustomHistories.count
        } else if (chainType == ChainType.KI_MAIN) {
            return self.mApiHistories.count
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return self.mBnbHistories.count
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return self.mOkHistories.count
        } else {
            return self.mApiCustomNewHistories.count
        }
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType == ChainType.CRYPTO_MAIN) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
            cell?.bindHistoryCustomView(mApiCustomHistories[indexPath.row], account!.account_address)
            return cell!
            
        } else if (chainType == ChainType.KI_MAIN) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
            cell?.bindHistoryLegacyView(mApiHistories[indexPath.row], account!.account_address)
            return cell!
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
            cell?.bindHistoryBnbView(mBnbHistories[indexPath.row], account!.account_address)
            return cell!
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
            cell?.bindHistoryOkView(mOkHistories[indexPath.row], account!.account_address)
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"NewHistoryCell") as? NewHistoryCell
            cell?.bindHistoryView(chainType!, mApiCustomNewHistories[indexPath.row], account!.account_address)
            return cell!
        }
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (chainType == ChainType.CRYPTO_MAIN) {
            let history = mApiCustomHistories[indexPath.row]
            if (history.chain_id?.isEmpty == false && (BaseData.instance.getChainId(self.chainType) != history.chain_id)) {
                let link = WUtils.getTxExplorer(self.chainType!, history.tx_hash!)
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)

            } else {
                let txDetailVC = TxDetailgRPCViewController(nibName: "TxDetailgRPCViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = history.tx_hash
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
            }
            
        } else if (chainType == ChainType.KI_MAIN) {
            let history = mApiHistories[indexPath.row]
            let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
            txDetailVC.mIsGen = false
            txDetailVC.mTxHash = history.tx_hash
            txDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txDetailVC, animated: true)
            
        } else if (chainType == ChainType.BINANCE_MAIN) {
            let bnbHistory = mBnbHistories[indexPath.row]
            if (bnbHistory.txType == "HTL_TRANSFER" || bnbHistory.txType == "CLAIM_HTL" || bnbHistory.txType == "REFUND_HTL" || bnbHistory.txType == "TRANSFER") {
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = bnbHistory.txHash
                txDetailVC.mBnbTime = bnbHistory.timeStamp
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            } else {
                guard let url = URL(string: "https://binance.mintscan.io/txs/" + bnbHistory.txHash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                safariViewController.modalPresentationStyle = .popover
                present(safariViewController, animated: true, completion: nil)
            }
            
        } else if (chainType == ChainType.BINANCE_TEST) {
            let bnbHistory = mBnbHistories[indexPath.row]
            if (bnbHistory.txType == "HTL_TRANSFER" || bnbHistory.txType == "CLAIM_HTL" || bnbHistory.txType == "REFUND_HTL" || bnbHistory.txType == "TRANSFER") {
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = bnbHistory.txHash
                txDetailVC.mBnbTime = bnbHistory.timeStamp
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
            } else {
                guard let url = URL(string: "https://testnet-explorer.binance.org/tx/" + bnbHistory.txHash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                safariViewController.modalPresentationStyle = .popover
                present(safariViewController, animated: true, completion: nil)
            }
            
        } else if (chainType == ChainType.OKEX_MAIN) {
            let okHistory = mOkHistories[indexPath.row]
            guard let url = URL(string: EXPLORER_OKEX_MAIN + "tx/" + okHistory.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.OKEX_TEST) {
            let okHistory = mOkHistories[indexPath.row]
            guard let url = URL(string: EXPLORER_OKEX_TEST + "tx/" + okHistory.txhash!) else { return }
            self.onShowSafariWeb(url)
            
        } else {
            let history = mApiCustomNewHistories[indexPath.row]
            if (history.header?.chain_id != BaseData.instance.getChainId(self.chainType)) {
                let link = WUtils.getTxExplorer(self.chainType!, history.data!.txhash!)
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)

            } else {
                if (WUtils.isGRPC(self.chainType)) {
                    let txDetailVC = TxDetailgRPCViewController(nibName: "TxDetailgRPCViewController", bundle: nil)
                    txDetailVC.mIsGen = false
                    txDetailVC.mTxHash = history.data!.txhash!
                    txDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(txDetailVC, animated: true)
                    
                } else {
                    let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                    txDetailVC.mIsGen = false
                    txDetailVC.mTxHash = history.data!.txhash!
                    txDetailVC.hidesBottomBarWhenPushed = true
                    self.navigationItem.title = ""
                    self.navigationController?.pushViewController(txDetailVC, animated: true)
                }
            }
        }
    }
    
    func onFetchBnbHistory(_ address:String) {
        let request = Alamofire.request(BaseNetWork.bnbHistoryUrl(chainType), method: .get, parameters: ["address":address, "startTime":Date().Stringmilli3MonthAgo, "endTime":Date().millisecondsSince1970], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { response in
            switch response.result {
            case .success(let res):
                if let data = res as? NSDictionary, let rawHistory = data.object(forKey: "tx") as? Array<NSDictionary> {
                    self.mBnbHistories.removeAll()
                    for raw in rawHistory {
                        self.mBnbHistories.append(BnbHistory.init(raw as! [String : Any]))
                    }
                    if(self.mBnbHistories.count > 0) {
                        self.historyTableView.reloadData()
                        self.emptyLabel.isHidden = true
                    } else {
                        self.emptyLabel.isHidden = false
                    }
                    
                } else {
                    self.emptyLabel.isHidden = false
                }
                
            case .failure(let error):
                print("error ", error)
            }
        }
        self.refresher.endRefreshing()
    }
    
    func onFetchOkHistory(_ address:String) {
        let request = Alamofire.request(BaseNetWork.historyOkUrl(chainType), method: .get, parameters: ["address":address], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { response in
            switch response.result {
            case .success(let res):
                self.mOkHistories.removeAll()
                if let historyInfo = res as? NSDictionary {
                    let okHistory = OkHistory.init(historyInfo)
                    if let okHistoryDetails = okHistory.data?.dataDetails {
                        self.mOkHistories = okHistoryDetails
                    }
                }
                if (self.mOkHistories.count > 0) {
                    self.historyTableView.reloadData()
                    self.emptyLabel.isHidden = true
                } else {
                    self.emptyLabel.isHidden = false
                }

            case .failure(let error):
                print("error ", error)
            }
        }
        self.refresher.endRefreshing()
    }
    
    func onFetchLegacyOldApiHistory(_ address:String) {
        let url = BaseNetWork.accountHistory(chainType!, address)
        print("onFetchLegacyOldApiHistory url ", url)
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"50"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.mApiHistories.removeAll()
                guard let histories = res as? Array<NSDictionary> else {
                    print("no history!!")
                    self.emptyLabel.isHidden = false
                    return;
                }
                for rawHistory in histories {
                    self.mApiHistories.append(ApiHistory.HistoryData.init(rawHistory))
                }
                print("onFetchLegacyOldApiHistory ", self.mApiHistories.count)
                if (self.mApiHistories.count > 0) {
                    self.historyTableView.reloadData()
                    self.emptyLabel.isHidden = true
                } else {
                    self.emptyLabel.isHidden = false
                }
                
            case .failure(let error):
                self.emptyLabel.isHidden = false
                print("onFetchLegacyOldApiHistory ", error)
            }
        }
        self.refresher.endRefreshing()
    }
    
    func onFetchNewApiHistoryCustom(_ address:String) {
        let url = BaseNetWork.accountHistory(chainType!, address)
        print("onFetchNewApiHistoryCustom url ", url)
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"50"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.mApiCustomNewHistories.removeAll()
                guard let histories = res as? Array<NSDictionary> else {
                    self.emptyLabel.isHidden = false
                    return;
                }
                for rawHistory in histories {
                    self.mApiCustomNewHistories.append(ApiHistoryNewCustom.init(rawHistory))
                }
                print("onFetchNewApiHistoryCustom ", self.mApiCustomNewHistories.count)
                if (self.mApiCustomNewHistories.count > 0) {
                    self.historyTableView.reloadData()
                    self.emptyLabel.isHidden = true
                } else {
                    self.emptyLabel.isHidden = false
                }

            case .failure(let error):
                self.emptyLabel.isHidden = false
                print("onFetchNewApiHistoryCustom ", error)
            }
            self.refresher.endRefreshing()
        }
    }
    
    func onFetchApiHistoryCustom(_ address:String) {
        let url = BaseNetWork.accountHistory(chainType!, address)
        print("onFetchApiHistoryCustom url ", url)
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"50"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.mApiCustomHistories.removeAll()
                guard let responseDatas = res as? Array<NSDictionary> else {
                    print("no history!!")
                    self.emptyLabel.isHidden = false
                    return;
                }
                for responseData in responseDatas {
                    self.mApiCustomHistories.append(ApiHistoryCustom(responseData))
                }
                if (self.mApiCustomHistories.count > 0) {
                    self.historyTableView.reloadData()
                    self.emptyLabel.isHidden = true
                } else {
                    self.emptyLabel.isHidden = false
                }

            case .failure(let error):
                self.emptyLabel.isHidden = false
                print("onFetchApiHistoryCustom ", error)
            }
            self.refresher.endRefreshing()
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
    
    @objc func onClickActionShare() {
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        self.shareAddress(address, WUtils.getWalletName(account))
    }
    
}
