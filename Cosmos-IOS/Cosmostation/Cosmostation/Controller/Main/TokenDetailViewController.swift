//
//  TokenDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage
import SafariServices

class TokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var keyState: UIImageView!
    @IBOutlet weak var dpAddress: UILabel!
    @IBOutlet weak var tokenDetailTableView: UITableView!
    
    var balance:Balance?
    var allValidator = Array<Validator>()
    var allRewards = Array<Reward>()
    var irisToken:IrisToken?
    var irisRewards:IrisRewards?
    var bnbToken:BnbToken?
    var bnbTic:NSMutableDictionary?
    
    var refresher: UIRefreshControl!
    var mHistories = Array<History.InnerHits>()
    var mBnbHistories = Array<BnbHistory>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderCosmosCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderCosmosCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderIrisCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderIrisCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderBnbCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderBnbCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderKavaCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderKavaCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderCustomCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderCustomCell")
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.tokenDetailTableView.addSubview(refresher)
        
        self.updateAccoutCard()
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN ||
            chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            onFetchHistory(account!.account_address, balance!.balance_denom);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            onFetchBnbHistory(account!.account_address, bnbToken!.symbol);
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationItem.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    func updateAccoutCard() {
        dpAddress.text = account?.account_address
        dpAddress.adjustsFontSizeToFitWidth = true
        if (account?.account_has_private == true) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                keyState.tintColor = COLOR_ATOM
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                keyState.tintColor = COLOR_IRIS
            } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                keyState.tintColor = COLOR_BNB
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                keyState.tintColor = COLOR_KAVA
            }
        }
    }
    
    @objc func onRequestFetch() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN ||
            chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            onFetchHistory(account!.account_address, balance!.balance_denom);
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            onFetchBnbHistory(account!.account_address, bnbToken!.symbol);
        }
    }

    @IBAction func onClickWebLink(_ sender: UIButton) {
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            guard let url = URL(string: "https://www.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            guard let url = URL(string: "https://irishub.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            guard let url = URL(string: "https://explorer.binance.org/address/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            guard let url = URL(string: "https://kava.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        }
    }
    
    @IBAction func onClickShare(_ sender: UIButton) {
        var nickName:String?
        if (account!.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account!.account_nick_name
        }
        self.shareAddress(account!.account_address, nickName!)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN ||
            chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return mHistories.count + 1
        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return mBnbHistories.count + 1
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN && balance?.balance_denom == COSMOS_MAIN_DENOM) {
                return onSetCosmosItems(tableView, indexPath);
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN && balance?.balance_denom == IRIS_MAIN_DENOM) {
                return onSetIrisItem(tableView, indexPath);
            } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN && balance?.balance_denom == BNB_MAIN_DENOM) {
                return onSetBnbItem(tableView, indexPath);
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN && balance?.balance_denom == KAVA_MAIN_DENOM) {
                return onSetKavaItem(tableView, indexPath);
            } else {
                return onSetCustomTokenItem(tableView, indexPath);
            }
            
        } else {
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                return onSetCosmosHistoryItems(tableView, indexPath);
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                return onSetIrisHistoryItem(tableView, indexPath);
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                return onSetKavaHistoryItem(tableView, indexPath);
            } else {
                return onSetBnbHistoryItem(tableView, indexPath);
            }
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if (indexPath.row == 0) {
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN && balance?.balance_denom == COSMOS_MAIN_DENOM) {
                return 258;
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN && balance?.balance_denom == IRIS_MAIN_DENOM) {
                return 258;
            } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN && balance?.balance_denom == BNB_MAIN_DENOM) {
                return 208;
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN && balance?.balance_denom == KAVA_MAIN_DENOM) {
                return 276;
            } else {
                return 198;
            }
        } else {
            return 80;
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.row > 0) {
            if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                let history = mHistories[indexPath.row - 1]
                guard let url = URL(string: "https://www.mintscan.io/txs/" + history._source.hash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
                
            } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                let history = mHistories[indexPath.row - 1]
                guard let url = URL(string: "https://irishub.mintscan.io/txs/" + history._source.hash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
                
            } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                let bnbHistory = mBnbHistories[indexPath.row - 1]
                guard let url = URL(string: "https://explorer.binance.org/tx/" + bnbHistory.txHash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
                           
            } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                let history = mHistories[indexPath.row - 1]
                guard let url = URL(string: "https://kava.mintscan.io/txs/" + history._source.hash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
            }
        }
    }
    
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenDetailHeaderCosmosCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderCosmosCell") as? TokenDetailHeaderCosmosCell
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let unbondingList = BaseData.instance.selectUnbondingById(accountId: account!.account_id)
        
        cell?.totalAmount.attributedText = WUtils.dpAllAtom(balances, bondingList, unbondingList, allRewards, allValidator, cell!.totalAmount.font, 6, chainType!)
        cell?.totalValue.attributedText = WUtils.dpAllAtomValue(balances, bondingList, unbondingList, allRewards, allValidator, BaseData.instance.getLastPrice(), cell!.totalAmount.font)
        cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(balances, cell!.availableAmount.font, 6, COSMOS_MAIN_DENOM, chainType!)
        cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(bondingList, allValidator, cell!.delegatedAmount.font, 6, chainType!)
        cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(unbondingList, cell!.unbondingAmount.font, 6, chainType!)
        cell?.rewardAmount.attributedText = WUtils.dpRewards(allRewards, cell!.rewardAmount.font, 6, COSMOS_MAIN_DENOM, chainType!)
        cell?.actionSend  = {
            self.onSendToken()
        }
        return cell!
    }
    
    func onSetIrisItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderIrisCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderIrisCell") as? TokenDetailHeaderIrisCell
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let unbondingList = BaseData.instance.selectUnbondingById(accountId: account!.account_id)
        
        cell?.totalAmount.attributedText = WUtils.dpAllIris(balances, bondingList, unbondingList, irisRewards, allValidator, cell!.totalAmount.font, 18, chainType!)
        cell?.totalValue.attributedText = WUtils.dpAllIrisValue(balances, bondingList, unbondingList, irisRewards, allValidator, BaseData.instance.getLastPrice(), cell!.totalAmount.font)
        cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(balances, cell!.availableAmount.font, 18, IRIS_MAIN_DENOM, chainType!)
        cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(bondingList, allValidator, cell!.delegatedAmount.font, 18, chainType!)
        cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(unbondingList, cell!.unbondingAmount.font, 18, chainType!)
        cell?.rewardAmount.attributedText = WUtils.dpIrisRewards(irisRewards, cell!.rewardAmount.font, 18, chainType!)
        cell?.actionSend  = {
            self.onSendToken()
        }
        return cell!
    }
    
    func onSetBnbItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderBnbCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderBnbCell") as? TokenDetailHeaderBnbCell
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if let balance = WUtils.getTokenBalace(balances, BNB_MAIN_DENOM) {
            let totalAmount = WUtils.stringToDecimal(balance.balance_amount).adding(WUtils.stringToDecimal(balance.balance_locked))
            cell?.totalAmount.attributedText = WUtils.displayAmount(totalAmount.stringValue, cell!.totalAmount.font, 8, chainType!)
            cell?.totalValue.attributedText = WUtils.dpBnbValue(totalAmount, BaseData.instance.getLastPrice(), cell!.totalAmount.font)
            cell?.availableAmount.attributedText = WUtils.displayAmount(balance.balance_amount, cell!.availableAmount.font, 8, chainType!)
            cell?.lockedAmount.attributedText = WUtils.displayAmount(balance.balance_locked, cell!.lockedAmount.font, 8, chainType!)
            cell?.actionSend  = {
                self.onSendToken()
            }
        }
        return cell!
    }
    
    func onSetKavaItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderKavaCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderKavaCell") as? TokenDetailHeaderKavaCell
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let unbondingList = BaseData.instance.selectUnbondingById(accountId: account!.account_id)
        let totalKava = WUtils.getAllKava(balances, bondingList, unbondingList, allRewards, allValidator)
        
        cell?.totalAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.totalAmount.font!, 6, 6)
        cell?.totalValue.attributedText = WUtils.dpAtomValue(totalKava, BaseData.instance.getLastPrice(), cell!.totalValue.font)
        cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(balances, cell!.availableAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
        cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(bondingList, allValidator, cell!.delegatedAmount.font, 6, chainType!)
        cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(unbondingList, cell!.unbondingAmount.font, 6, chainType!)
        cell?.rewardAmount.attributedText = WUtils.dpRewards(allRewards, cell!.rewardAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
        cell?.vestingAmount.attributedText = WUtils.dpVestingCoin(balances, cell!.vestingAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
        cell?.actionSend  = {
            self.onSendToken()
        }
        return cell!
    }
    
    func onSetCustomTokenItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderCustomCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderCustomCell") as? TokenDetailHeaderCustomCell
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            //TODO not this case yet!

        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN && irisToken != nil) {
            cell?.tokenInfoBtn.isHidden = true
            cell?.tokenSymbol.text = irisToken?.base_token?.symbol.uppercased()
            cell?.tokenName.text = balance?.balance_denom
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.totalAmount.font, irisToken!.base_token!.decimal, irisToken!.base_token!.decimal)
            cell?.totalAmount.adjustsFontSizeToFitWidth = true
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.availableAmount.font, irisToken!.base_token!.decimal, irisToken!.base_token!.decimal)
            cell?.actionSend  = {
                self.onShowToast(NSLocalizedString("error_iris_token_yet", comment: ""))
            }

        } else if (chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN && bnbToken != nil) {
            cell?.tokenInfoBtn.isHidden = false
            cell?.tokenSymbol.text = bnbToken?.original_symbol.uppercased()
            cell?.tokenName.text = bnbToken?.symbol
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance!.getAllAmountBnbToken().stringValue, cell!.totalAmount.font, 0, 8)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.availableAmount.font, 0, 8)
            cell?.totalValue.attributedText = WUtils.dpBnbValue(balance!.exchangeBnbValue(bnbTic), BaseData.instance.getLastPrice(), cell!.totalValue.font)
            let url = TOKEN_IMG_URL + bnbToken!.original_symbol + ".png"
            Alamofire.request(url, method: .get).responseImage { response  in
                guard let image = response.result.value else {
                    return
                }
                cell?.tokenImg.image = image
            }
            cell?.actionTokenInfo = {
                guard let url = URL(string: "https://explorer.binance.org/asset/" + self.bnbToken!.symbol) else { return }
                let safariViewController = SFSafariViewController(url: url)
                self.present(safariViewController, animated: true, completion: nil)
            }
            cell?.actionSend  = {
                self.onSendToken()
            }
        }
        return cell!
    }
    
    
    func onSetCosmosHistoryItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mHistories[indexPath.row - 1]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.timestamp)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history._source.timestamp)
        cell?.txBlockLabel.text = String(history._source.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg, account!.account_address)
        if(history._source.result.allResult) {
            cell?.txResultLabel.isHidden = true
        } else {
            cell?.txResultLabel.isHidden = false
        }
        return cell!
    }
    
    func onSetIrisHistoryItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mHistories[indexPath.row - 1]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.time)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history._source.time)
        cell?.txBlockLabel.text = String(history._source.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg, account!.account_address)
        if(history._source.result.code > 0) {
            cell?.txResultLabel.isHidden = false
        } else {
            cell?.txResultLabel.isHidden = true
        }
        return cell!
    }
    
    func onSetBnbHistoryItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mBnbHistories[indexPath.row - 1]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history.timeStamp)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history.timeStamp)
        cell?.txBlockLabel.text = String(history.blockHeight) + " block"
        cell?.txTypeLabel.text = WUtils.bnbHistoryTitle(history, account!.account_address)
        cell?.txResultLabel.isHidden = true
        return cell!
    }
    
    func onSetKavaHistoryItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mHistories[indexPath.row - 1]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.timestamp)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history._source.timestamp)
        cell?.txBlockLabel.text = String(history._source.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg, account!.account_address)
        if(history._source.allResult) {
            cell?.txResultLabel.isHidden = true
        } else {
            cell?.txResultLabel.isHidden = false
        }
        return cell!
    }
    
    
    func onFetchHistory(_ address:String, _ symbol:String) {
        var query = ""
        var url = ""
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            query = "{\"from\" : 0,\"query\" : {\"bool\" : {\"must\" : [ {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.from_address\", \"tx.value.msg.value.to_address\" ],\"query\" : \"" + address + "\"}}, {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.amount.denom\", \"tx.value.msg.value.inputs.coins.denom\", \"tx.value.msg.value.outputs.coins.denom\" ],\"query\" : \"" + symbol + "\"}} ]}},\"size\" : 100}"
            print("query ", query)
            url = CSS_ES_PROXY_COSMOS
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            query = "{\"from\" : 0,\"query\" : {\"bool\" : {\"must\" : [ {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.inputs.address\", \"tx.value.msg.value.outputs.address\" ],\"query\" : \"" + address + "\"}}, {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.inputs.coins.denom\", \"tx.value.msg.value.outputs.coins.denom\" ],\"query\" : \"" + symbol + "\"}} ]}},\"size\" : 100}"
            print("query ", query)
            url = IRIS_ES_PROXY_IRIS
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            query = "{\"from\" : 0,\"query\" : {\"bool\" : {\"must\" : [ {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.from_address\", \"tx.value.msg.value.to_address\" ],\"query\" : \"" + address + "\"}}, {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.amount.denom\", \"tx.value.msg.value.inputs.coins.denom\", \"tx.value.msg.value.outputs.coins.denom\" ],\"query\" : \"" + symbol + "\"}} ]}},\"size\" : 100}"
            print("query ", query)
            url = KAVA_ES_PROXY_IRIS
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
                        return;
                    }
                    let rawHistory = History.init(history)

                    self.mHistories.removeAll()
                    self.mHistories = rawHistory.hits.hits

                    if (self.mHistories.count > 0) {
                        self.tokenDetailTableView.reloadData()
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
    
    func onFetchBnbHistory(_ address:String, _ symbol:String) {
        let request = Alamofire.request(BNB_URL_HISTORY, method: .get, parameters: ["address":address, "startTime":Date().Stringmilli3MonthAgo, "endTime":Date().millisecondsSince1970, "txAsset":symbol], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { response in
            switch response.result {
            case .success(let res):
                if let data = res as? NSDictionary, let rawHistory = data.object(forKey: "tx") as? Array<NSDictionary> {
                    self.mBnbHistories.removeAll()
                    for raw in rawHistory {
                        self.mBnbHistories.append(BnbHistory.init(raw as! [String : Any]))
                    }
                    if(self.mBnbHistories.count > 0) {
                        self.tokenDetailTableView.reloadData()
                    }
                    
                }
                
            case .failure(let error):
                print("error ", error)
            }
        }
        self.refresher.endRefreshing()
    }
    
    func onSendToken() {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (WUtils.getTokenAmount(balances, COSMOS_MAIN_DENOM).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "200000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mIrisToken = self.irisToken
            txVC.mType = IRIS_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: "0.000375")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mBnbToken = self.bnbToken
            txVC.mType = BNB_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            if (WUtils.getTokenAmount(balances, KAVA_MAIN_DENOM).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mType = KAVA_MSG_TYPE_TRANSFER
        }
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
}
