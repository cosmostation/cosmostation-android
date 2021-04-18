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
    var mBnbHistories = Array<BnbHistory>()
    var mOkHistories = Array<OkHistory.DataDetail>()
    var mApiHistories = Array<ApiHistory.HistoryData>()
    var mApiCustomHistories = Array<ApiHistoryCustom>()
    
    
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
        
        if (WUtils.isGRPC(chainType!)) {
            onFetchApiHistoryCustom(mainTabVC.mAccount.account_address)
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            onFetchBnbHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.BAND_MAIN) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SECRET_MAIN) {
            self.comingLabel.isHidden = false
            self.historyTableView.isHidden = true
            self.comingLabel.text = "Check with Explorer"
        } else if (chainType == ChainType.IOV_MAIN ) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.IOV_TEST ) {
            self.comingLabel.isHidden = false
            self.historyTableView.isHidden = true
            self.comingLabel.text = "Coming Soon!!"
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            onFetchOkHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.FETCH_MAIN) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        }
        
        self.comingLabel.isUserInteractionEnabled = true
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(testClick(tapGestureRecognizer:)))
        self.comingLabel.addGestureRecognizer(tapGesture)
    }

    @objc func testClick(tapGestureRecognizer: UITapGestureRecognizer) {
//        let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
//        txDetailVC.mIsGen = false
//        txDetailVC.mTxHash = "316D5C4492149900549FFEA037B8C2CC51E2F903BBB2A4B805A236016BAD9278"
//        txDetailVC.hidesBottomBarWhenPushed = true
//        self.navigationItem.title = ""
//        self.navigationController?.pushViewController(txDetailVC, animated: true)
//        if (chainType == ChainType.SECRET_MAIN) {
//            guard let url = URL(string: EXPLORER_SECRET_MAIN + "accounts/" + mainTabVC.mAccount.account_address) else { return }
//            self.onShowSafariWeb(url)
//        }
        
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
        if (chainType == ChainType.COSMOS_MAIN) {
            titleChainImg.image = UIImage(named: "cosmosWhMain")
            titleChainName.text = "(Cosmos Mainnet)"
        } else if (chainType == ChainType.IRIS_MAIN) {
            titleChainImg.image = UIImage(named: "irisWh")
            titleChainName.text = "(Iris Mainnet)"
            titleAlarmBtn.isHidden = true
        } else if (chainType == ChainType.BINANCE_MAIN) {
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
        }  else if (chainType! == ChainType.KAVA_TEST) {
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
    
    @objc func onRequestFetch() {
        if (WUtils.isGRPC(chainType!)) {
            onFetchApiHistoryCustom(mainTabVC.mAccount.account_address)
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            onFetchBnbHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.BAND_MAIN) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SECRET_MAIN) {
            self.comingLabel.isHidden = false
        } else if (chainType == ChainType.IOV_MAIN ) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.IOV_TEST ) {
            self.comingLabel.isHidden = false
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            onFetchOkHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.SENTINEL_MAIN ) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        } else if (chainType == ChainType.FETCH_MAIN) {
            onFetchApiHistory(mainTabVC.mAccount.account_address);
        }
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return self.mBnbHistories.count
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return self.mOkHistories.count
        } else if (WUtils.isGRPC(chainType!)) {
            return self.mApiCustomHistories.count
        } else {
            return self.mApiHistories.count
        }
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (WUtils.isGRPC(chainType!)) {
            return onSetCustomHistoryItems(tableView, indexPath);
        } else if (chainType == ChainType.KAVA_MAIN) {
            return onSetKavaItem(tableView, indexPath);
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return onSetBnbItem(tableView, indexPath);
        } else if (chainType == ChainType.IOV_MAIN) {
            return onSetIovItem(tableView, indexPath);
        } else if (chainType == ChainType.KAVA_TEST) {
            return onSetKavaItem(tableView, indexPath);
        } else if (chainType == ChainType.BAND_MAIN) {
            return onSetBandItem(tableView, indexPath);
        } else if (chainType == ChainType.SECRET_MAIN) {
            return onSetSecretItem(tableView, indexPath);
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            return onSetCertikItem(tableView, indexPath);
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return onSetOkItem(tableView, indexPath);
        } else if (chainType == ChainType.SENTINEL_MAIN || chainType == ChainType.FETCH_MAIN) {
            return onSetDefaultItem(tableView, indexPath);
        }
        return onSetEmptyItem(tableView, indexPath);
    }
    
    func onSetCustomHistoryItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mApiCustomHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.txTimetoString(input: history.timestamp)
        cell?.txTimeGapLabel.text = WUtils.txTimeGap(input: history.timestamp)
        cell?.txBlockLabel.text = String(history.height!) + " block"
        cell?.txTypeLabel.text = history.getMsgType(mainTabVC.mAccount.account_address)
        if (history.isSuccess()) { cell?.txResultLabel.isHidden = true }
        else { cell?.txResultLabel.isHidden = false }
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
        if (history.msg[0].type == COSMOS_MSG_TYPE_TRANSFER2) {
            if (history.height > PERSISTENCE_KAVA_EVENT_START && history.height < PERSISTENCE_KAVA_EVENT_END) {
                if (history.msg[0].value.to_address == PERSISTENCE_KAVA_EVENT_ADDRESS && history.msg[0].value.from_address == mainTabVC.mAccount.account_address) {
                    cell?.txRootCard.backgroundColor = COLOR_STAKE_DROP_BG
                    cell?.txTypeLabel.textColor = COLOR_STAKE_DROP
                    cell?.txTypeLabel.text = "Persistence\nStake Drop"
                }
            }
        }
        return cell!
    }
    
    func onSetBandItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
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
    
    func onSetSecretItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
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
    
    func onSetCertikItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
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
    
    func onSetAkashItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
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
    
    func onSetDefaultItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
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
    
    func onSetOkItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let okHistory = mOkHistories[indexPath.row]
        cell?.txTypeLabel.text = WUtils.okHistoryTitle(okHistory)
        cell?.txTimeLabel.text = WUtils.longTimetoString(input: okHistory.timestamp! * 1000)
        cell?.txTimeGapLabel.text = WUtils.timeGap2(input: okHistory.timestamp! * 1000)
        cell?.txBlockLabel.text = okHistory.txhash
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
        if (chainType == ChainType.BINANCE_MAIN) {
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
            
        } else if (WUtils.isGRPC(chainType!)) {
            let history = mApiCustomHistories[indexPath.row]
            let txDetailVC = TxDetailgRPCViewController(nibName: "TxDetailgRPCViewController", bundle: nil)
            txDetailVC.mIsGen = false
            txDetailVC.mTxHash = history.tx_hash
            txDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txDetailVC, animated: true)
            
        } else {
            let history = mApiHistories[indexPath.row]
            if (history.chain_id.count > 0 && (BaseData.instance.getChainId() != history.chain_id)) {
                let link = WUtils.getTxExplorer(self.chainType!, history.tx_hash)
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)
                
            } else {
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = history.tx_hash
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            }
        }
    }
    
    func onFetchBnbHistory(_ address:String) {
        var url = ""
        if (chainType == ChainType.BINANCE_MAIN) {
            url = BNB_URL_HISTORY
        } else if (chainType == ChainType.BINANCE_TEST) {
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
    
    func onFetchOkHistory(_ address:String) {
        var url = ""
        if (chainType == ChainType.OKEX_MAIN) {
            url = OKEX_HISTORY
        } else if (chainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_HISTORY
        }
        let request = Alamofire.request(url, method: .get, parameters: ["address":address], encoding: URLEncoding.default, headers: [:])
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
    
    func onFetchApiHistory(_ address:String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_API_HISTORY + address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_API_HISTORY + address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_API_HISTORY + address
        } else if (chainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_API_HISTORY + address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_API_HISTORY + address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_API_HISTORY + address
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_API_HISTORY + address
        } else if (chainType == ChainType.FETCH_MAIN) {
            url = FETCH_API_HISTORY + address
        }
        print("url ", url)
        let request = Alamofire.request(url!, method: .get, parameters: ["limit":"50"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
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
                
            case .failure(let error):
                self.emptyLabel.isHidden = false
                if (SHOW_LOG) { print("onFetchApiHistory ", error) }
            }
        }
        self.refresher.endRefreshing()
    }
    
    func onFetchApiHistoryCustom(_ address:String) {
        let url = BaseNetWork.accountHistory(chainType!, address)
        print("url ", url)
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"50"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.mApiCustomHistories.removeAll()
                guard let responseDatas = res as? Array<NSDictionary> else {
                    if (SHOW_LOG) { print("no history!!") }
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
                if (SHOW_LOG) { print("onFetchApiHistoryCustom ", error) }
            }
            self.refresher.endRefreshing()
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
    
}
