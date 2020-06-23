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

    @IBOutlet weak var historyTableView: UITableView!
    @IBOutlet weak var emptyLabel: UILabel!
    @IBOutlet weak var comingLabel: UILabel!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    var mHistories = Array<History.InnerHits>()
    var mBnbHistories = Array<BnbHistory>()
    var mApiHistories = Array<ApiHistory.HistoryData>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        
        self.historyTableView.delegate = self
        self.historyTableView.dataSource = self
        self.historyTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.historyTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        self.historyTableView.rowHeight = UITableView.automaticDimension
        self.historyTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.historyTableView.addSubview(refresher)
        
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            onFetchHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            onFetchBnbHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BAND_MAIN) {
            comingLabel.isHidden = false
        }
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        self.updateTitle()
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
        
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Hub)"
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Hub)"
            titleAlarmBtn.isHidden = true
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            titleChainImg.image = UIImage(named: "binanceChImg")
            titleChainName.text = "(Binance Chain)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            titleChainImg.image = UIImage(named: "kavaImg")
            titleChainName.text = "(KAVA Chain)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            titleChainImg.image = UIImage(named: "iovImg")
            titleChainName.text = "(IOV Chain)"
            titleAlarmBtn.isHidden = true
        }  else if (chainType! == ChainType.SUPPORT_CHAIN_BAND_MAIN) {
            titleChainImg.image = UIImage(named: "bandChainImg")
            titleChainName.text = "(Band Chain)"
            titleAlarmBtn.isHidden = true
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            titleChainImg.image = UIImage(named: "binancetestnet")
            titleChainName.text = "(Binance Test)"
            titleAlarmBtn.isHidden = true
        }  else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            titleChainImg.image = UIImage(named: "kavaTestImg")
            titleChainName.text = "(KAVA Test)"
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
    
    @objc func onRequestFetch() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            onFetchHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            onFetchBnbHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BAND_MAIN) {
            comingLabel.isHidden = false
        }
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return self.mHistories.count
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            return self.mBnbHistories.count
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            return self.mApiHistories.count
        }
        return 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return onSetIrisItem(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return onSetKavaItem(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            return onSetBnbItem(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            return onSetIovItem(tableView, indexPath);
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            return onSetKavaItem(tableView, indexPath);
        }
        return onSetEmptyItem(tableView, indexPath);
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.timestamp)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history._source.timestamp)
        cell?.txBlockLabel.text = String(history._source.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg, mainTabVC.mAccount.account_address)
        if (history._source.allResult) {
            cell?.txResultLabel.isHidden = true
        } else {
            cell?.txResultLabel.isHidden = false
        }
        return cell!
    }
    
    func onSetIrisItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.time)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history._source.time)
        cell?.txBlockLabel.text = String(history._source.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg, mainTabVC.mAccount.account_address)
        if(history._source.result.code > 0) {
            cell?.txResultLabel.isHidden = false
        } else {
            cell?.txResultLabel.isHidden = true
        }
        return cell!
    }
    
    func onSetBnbItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mBnbHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history.timeStamp)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history.timeStamp)
        cell?.txBlockLabel.text = String(history.blockHeight) + " block"
        cell?.txTypeLabel.text = WUtils.bnbHistoryTitle(history, mainTabVC.mAccount.account_address)
        cell?.txResultLabel.isHidden = true
        return cell!
    }
    
    func onSetIovItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        return cell!
    }
    
    func onSetKavaItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mApiHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.txTimetoString(input: history.time)
        cell?.txTimeGapLabel.text = WUtils.txTimeGap(input: history.time)
        cell?.txBlockLabel.text = String(history.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history.msg, mainTabVC.mAccount.account_address)
        if (history.isSuccess) {
            cell?.txResultLabel.isHidden = true
        } else {
            cell?.txResultLabel.isHidden = false
        }
        return cell!
    }
    
    func onSetEmptyItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let history = mHistories[indexPath.row]
            let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
            txDetailVC.mIsGen = false
            txDetailVC.mTxHash = history._source.hash
            txDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txDetailVC, animated: true)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let history = mHistories[indexPath.row]
            let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
            txDetailVC.mIsGen = false
            txDetailVC.mTxHash = history._source.hash
            txDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txDetailVC, animated: true)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            let bnbHistory = mBnbHistories[indexPath.row]
            if (bnbHistory.txType == "HTL_TRANSFER" || bnbHistory.txType == "CLAIM_HTL" || bnbHistory.txType == "REFUND_HTL") {
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
                present(safariViewController, animated: true, completion: nil)
            }
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let history = mApiHistories[indexPath.row]
            let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
            txDetailVC.mIsGen = false
            txDetailVC.mTxHash = history.tx_hash
            txDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txDetailVC, animated: true)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            let bnbHistory = mBnbHistories[indexPath.row]
            if (bnbHistory.txType == "HTL_TRANSFER" || bnbHistory.txType == "CLAIM_HTL" || bnbHistory.txType == "REFUND_HTL") {
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = bnbHistory.txHash
                txDetailVC.mBnbTime = bnbHistory.timeStamp
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            } else {
                guard let url = URL(string: "https://testnet-explorer.binance.org/tx/" + bnbHistory.txHash) else { return }
                print("url ", url)
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
            }
        }
    }
    
    
    func onFetchHistory(_ address:String) {
        var query = ""
        var url = ""
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            query = "{\"from\": " + "0" + ",\"size\": " + "100" + ",\"query\": {\"multi_match\": {\"query\": \"" + address + "\",\"fields\": [\"tx.value.msg.value.delegator_address\", \"tx.value.msg.value.from_address\", \"tx.value.msg.value.to_address\", \"tx.value.msg.value.depositor\", \"tx.value.msg.value.voter\", \"tx.value.msg.value.inputs.address\", \"tx.value.msg.value.outputs.address\", \"tx.value.msg.value.proposer\"]}}}"
            url = CSS_ES_PROXY_COSMOS
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            query = "{\"from\": " + "0" + ",\"size\": " + "100" + ",\"query\": {\"multi_match\": {\"query\": \"" + address + "\",\"fields\": [\"tx.value.msg.value.address\", \"tx.value.msg.value.owner\", \"tx.value.msg.value.banker\", \"tx.value.msg.value.delegator_addr\", \"tx.value.msg.value.proposer\", \"tx.value.msg.value.dest_address\", \"tx.value.msg.value.voter\", \"tx.value.msg.value.author\", \"tx.value.msg.value.consumer\", \"tx.value.msg.value.trustee\", \"tx.value.msg.value.inputs.address\", \"tx.value.msg.value.outputs.address\"]}}}"
            url = IRIS_ES_PROXY_IRIS
            
        }
        
        let data = query.data(using: .utf8)
        do {
            let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
            let request = Alamofire.request(url, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
            request.validate().responseJSON { response in
                switch response.result {
                case .success(let res):
                    guard let history = res as? [String : Any] else {
                        print("no history!!")
                        self.emptyLabel.isHidden = false
                        return;
                    }
                    let rawHistory = History.init(history)
                    print("rawHistory " , rawHistory.hits.hits.count)

                    self.mHistories.removeAll()
                    self.mHistories = rawHistory.hits.hits

                    if(self.mHistories.count > 0) {
                        self.historyTableView.reloadData()
                        self.emptyLabel.isHidden = true
                    } else {
                        self.emptyLabel.isHidden = false
                    }

                case .failure(let error):
                    print("error ", error)
                }
            }

        } catch {
            print(error)
        }
        self.refresher.endRefreshing()
    }
    
    func onFetchBnbHistory(_ address:String) {
        var url = ""
        if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            url = BNB_URL_HISTORY
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            url = BNB_TEST_URL_HISTORY
        }
        let request = Alamofire.request(url, method: .get, parameters: ["address":address, "startTime":Date().Stringmilli3MonthAgo, "endTime":Date().millisecondsSince1970], encoding: URLEncoding.default, headers: [:])
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
    
    func onFetchApiHistory(_ address:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_API_HISTORY + address
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_API_TEST_HISTORY + address
        }
        print("url ", url)
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    self.mApiHistories.removeAll()
                    guard let histories = res as? Array<NSDictionary> else {
                        if (SHOW_LOG) { print("no history!!") }
                        self.emptyLabel.isHidden = false
                        return;
                    }
                    for rawHistory in histories {
                        self.mApiHistories.append(ApiHistory.HistoryData.init(rawHistory))
                    }
                    if (SHOW_LOG) { print("mApiHistories ", self.mApiHistories.count) }
                    if (self.mApiHistories.count > 0) {
                        self.historyTableView.reloadData()
                        self.emptyLabel.isHidden = true
                    } else {
                        self.emptyLabel.isHidden = false
                    }
                }
                
            case .failure(let error):
                self.emptyLabel.isHidden = false
                if (SHOW_LOG) { print("onFetchApiHistory ", error) }
            }
        }
        self.refresher.endRefreshing()
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
    
}
