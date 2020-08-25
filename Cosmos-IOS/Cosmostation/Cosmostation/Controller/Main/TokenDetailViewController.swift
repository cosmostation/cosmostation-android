//
//  TokenDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
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
    var mApiHistories = Array<ApiHistory.HistoryData>()
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
        self.tokenDetailTableView.rowHeight = UITableView.automaticDimension
        self.tokenDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.tokenDetailTableView.addSubview(refresher)
        
        self.updateAccountCard()
        self.onRequestFetch()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationItem.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func updateAccountCard() {
        dpAddress.text = account?.account_address
        dpAddress.adjustsFontSizeToFitWidth = true
        if (account?.account_has_private == true) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            if (chainType == ChainType.COSMOS_MAIN) {
                keyState.tintColor = COLOR_ATOM
            } else if (chainType == ChainType.IRIS_MAIN) {
                keyState.tintColor = COLOR_IRIS
            } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
                keyState.tintColor = COLOR_BNB
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                keyState.tintColor = COLOR_KAVA
            }
        }
    }
    
    @objc func onRequestFetch() {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.IRIS_MAIN) {
            onFetchHistory(account!.account_address, balance!.balance_denom);
            
        } else if (chainType == ChainType.BINANCE_MAIN ||
            chainType == ChainType.BINANCE_TEST) {
            onFetchBnbHistory(account!.account_address, bnbToken!.symbol);
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            onFetchApiHistory(account!.account_address, balance!.balance_denom)
        }
    }

    @IBAction func onClickWebLink(_ sender: UIButton) {
        if (chainType! == ChainType.COSMOS_MAIN) {
            guard let url = URL(string: "https://www.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            guard let url = URL(string: "https://irishub.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: "https://binance.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.KAVA_MAIN) {
            guard let url = URL(string: "https://kava.mintscan.io/account/" + account!.account_address) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType! == ChainType.BINANCE_TEST) {
            guard let url = URL(string: "https://testnet-explorer.binance.org/address/" + account!.account_address) else { return }
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
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.IRIS_MAIN) {
            return mHistories.count + 1
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return mBnbHistories.count + 1
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            return mApiHistories.count + 1
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            if (chainType == ChainType.COSMOS_MAIN && balance?.balance_denom == COSMOS_MAIN_DENOM) {
                return onSetCosmosItems(tableView, indexPath);
                
            } else if (chainType == ChainType.IRIS_MAIN && balance?.balance_denom == IRIS_MAIN_DENOM) {
                return onSetIrisItem(tableView, indexPath);
                
            } else if ((chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) &&
                balance?.balance_denom == BNB_MAIN_DENOM) {
                return onSetBnbItem(tableView, indexPath);
                
            } else if (chainType == ChainType.KAVA_MAIN && balance?.balance_denom == KAVA_MAIN_DENOM) {
                return onSetKavaItem(tableView, indexPath);
                
            }  else if (chainType == ChainType.KAVA_TEST && balance?.balance_denom == KAVA_MAIN_DENOM) {
                return onSetKavaTestItem(tableView, indexPath);
                
            } else {
                return onSetCustomTokenItem(tableView, indexPath);
            }
            
        } else {
            if (chainType == ChainType.COSMOS_MAIN) {
                return onSetCosmosHistoryItems(tableView, indexPath);
                
            } else if (chainType == ChainType.IRIS_MAIN) {
                return onSetIrisHistoryItem(tableView, indexPath);
                
            } else if (chainType == ChainType.KAVA_MAIN) {
                return onSetKavaHistoryItem(tableView, indexPath);
                
            } else if (chainType == ChainType.KAVA_TEST) {
                return onSetKavaHistoryItem(tableView, indexPath);
                
            } else {
                return onSetBnbHistoryItem(tableView, indexPath);
            }
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.row > 0) {
            if (chainType == ChainType.COSMOS_MAIN) {
                let history = mHistories[indexPath.row - 1]
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = history._source.hash
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            } else if (chainType == ChainType.IRIS_MAIN) {
                let history = mHistories[indexPath.row - 1]
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = history._source.hash
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            } else if (chainType == ChainType.BINANCE_MAIN) {
                let bnbHistory = mBnbHistories[indexPath.row - 1]
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
                    present(safariViewController, animated: true, completion: nil)
                }
                           
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                let history = mApiHistories[indexPath.row - 1]
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = history.tx_hash
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            } else if (chainType == ChainType.BINANCE_TEST) {
                let bnbHistory = mBnbHistories[indexPath.row - 1]
                guard let url = URL(string: "https://testnet-explorer.binance.org/tx/" + bnbHistory.txHash) else { return }
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
        cell?.actionReceive = {
            self.onRecieveToken()
        }
        cell?.actionBuy = {
            self.onBuyCoin()
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
            let totalAmount = WUtils.getAllBnb(balance)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 0, 6)
            cell?.totalValue.attributedText = WUtils.dpBnbValue(totalAmount, BaseData.instance.getLastPrice(), cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance.balance_amount, cell!.availableAmount.font, 0, 6)
            cell?.lockedAmount.attributedText = WUtils.displayAmount2(balance.balance_locked, cell!.lockedAmount.font, 0, 6)
            cell?.actionSend  = {
                self.onSendToken()
            }
            cell?.actionRecieve = {
                self.onRecieveToken()
            }
            cell?.BtnSendBep3.isHidden = false;
            cell?.actionSendBep3 = {
                self.onClickBep3Send(BNB_MAIN_DENOM)
            }
            if (chainType == ChainType.BINANCE_MAIN) {
                cell?.BtnBuyBnb.isHidden = false
                cell?.actionBuy = {
                    self.onBuyCoin()
                }
            } else {
                cell?.BtnBuyBnb.isHidden = true
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
        cell?.actionRecieve = {
            self.onRecieveToken()
        }
        cell?.butBtn.isHidden = false
        cell?.showBuyConstraint.priority = .defaultHigh
        cell?.hideBuyConstraint.priority = .defaultLow
        cell?.actionBuy = {
            self.onBuyCoin()
        }
        return cell!
    }
    
    func onSetKavaTestItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
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
        cell?.actionRecieve = {
            self.onRecieveToken()
        }
        cell?.butBtn.isHidden = true
        cell?.showBuyConstraint.priority = .defaultLow
        cell?.hideBuyConstraint.priority = .defaultHigh
        return cell!
    }
    
    
    func onSetCustomTokenItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderCustomCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderCustomCell") as? TokenDetailHeaderCustomCell
        if (chainType == ChainType.COSMOS_MAIN) {
            //TODO not this case yet!

        } else if (chainType == ChainType.IRIS_MAIN && irisToken != nil) {
            cell?.tokenInfoBtn.isHidden = true
            cell?.tokenSymbol.text = irisToken?.base_token?.symbol.uppercased()
            cell?.tokenName.text = balance?.balance_denom
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.totalAmount.font, irisToken!.base_token!.decimal, irisToken!.base_token!.decimal)
            cell?.totalAmount.adjustsFontSizeToFitWidth = true
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.availableAmount.font, irisToken!.base_token!.decimal, irisToken!.base_token!.decimal)
            cell?.actionSend  = {
                self.onShowToast(NSLocalizedString("error_iris_token_yet", comment: ""))
            }

        } else if ((chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) && bnbToken != nil) {
            cell?.tokenInfoBtn.isHidden = false
            cell?.tokenSymbol.text = bnbToken?.original_symbol.uppercased()
            cell?.tokenName.text = bnbToken?.symbol
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance!.getAllAmountBnbToken().stringValue, cell!.totalAmount.font, 0, 8)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.availableAmount.font, 0, 8)
            cell?.totalValue.attributedText = WUtils.dpBnbValue(balance!.exchangeBnbValue(bnbTic), BaseData.instance.getLastPrice(), cell!.totalValue.font)
            let url = TOKEN_IMG_URL + bnbToken!.original_symbol + ".png"
            cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
            cell?.actionTokenInfo = {
                if (self.chainType == ChainType.BINANCE_MAIN) {
                    guard let url = URL(string: "https://binance.mintscan.io/assets/" + self.bnbToken!.symbol) else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    self.present(safariViewController, animated: true, completion: nil)
                } else {
                    guard let url = URL(string: "https://testnet-explorer.binance.org/asset/" + self.bnbToken!.symbol) else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    self.present(safariViewController, animated: true, completion: nil)
                }
            }
            cell?.actionSend  = {
                self.onSendToken()
            }
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell?.tokenInfoBtn.isHidden = true
            cell?.tokenSymbol.text = balance!.balance_denom.uppercased()
            cell?.tokenName.text = balance?.balance_denom
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance?.balance_amount, cell!.totalAmount.font, WUtils.getKavaCoinDecimal(balance!.balance_denom), WUtils.getKavaCoinDecimal(balance!.balance_denom))
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance?.balance_amount, cell!.availableAmount.font, WUtils.getKavaCoinDecimal(balance!.balance_denom), WUtils.getKavaCoinDecimal(balance!.balance_denom))
            
            let tokenTotalValue = balance!.kavaTokenDollorValue(BaseData.instance.mKavaPrice)
            let convertedKavaAmount = tokenTotalValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)))
            cell?.totalValue.attributedText = WUtils.dpAtomValue(convertedKavaAmount.multiplying(byPowerOf10: WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)), BaseData.instance.getLastPrice(), cell!.totalValue.font)
            let url = KAVA_COIN_IMG_URL + balance!.balance_denom + ".png"
            cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
            cell?.actionSend  = {
                self.onSendToken()
            }
            if (balance?.balance_denom.uppercased() == BNB_MAIN_DENOM) {
                cell?.btnBep3Send.isHidden = false
                cell?.actionBep3Send  = {
                    self.onClickBep3Send(BNB_MAIN_DENOM)
                }
                
            } else if (balance?.balance_denom.uppercased() == "USDX") {
                cell?.tokenInfoBtn.isHidden = false
                cell?.actionTokenInfo  = {
                    guard let url = URL(string: "https://www.kava.io/registration/") else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    self.present(safariViewController, animated: true, completion: nil)
                }
                
            } else {
                cell?.btnBep3Send.isHidden = true
            }
        }
        cell?.actionReceive = {
            self.onRecieveToken()
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
        let history = mApiHistories[indexPath.row - 1]
        cell?.txTimeLabel.text = WUtils.txTimetoString(input: history.time)
        cell?.txTimeGapLabel.text = WUtils.txTimeGap(input: history.time)
        cell?.txBlockLabel.text = String(history.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history.msg, account!.account_address)
        if (history.isSuccess) {
            cell?.txResultLabel.isHidden = true
        } else {
            cell?.txResultLabel.isHidden = false
        }
        return cell!
    }
    
    func onFetchHistory(_ address:String, _ symbol:String) {
        var query = ""
        var url = ""
        if (chainType == ChainType.COSMOS_MAIN) {
            query = "{\"from\" : 0,\"query\" : {\"bool\" : {\"must\" : [ {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.from_address\", \"tx.value.msg.value.to_address\", \"tx.value.msg.value.inputs.address\", \"tx.value.msg.value.outputs.address\" ],\"query\" : \"" + address + "\"}}, {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.amount.denom\", \"tx.value.msg.value.inputs.coins.denom\", \"tx.value.msg.value.outputs.coins.denom\" ],\"query\" : \"" + symbol + "\"}} ]}},\"size\" : 100}"
            print("query ", query)
            url = CSS_ES_PROXY_COSMOS
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            query = "{\"from\" : 0,\"query\" : {\"bool\" : {\"must\" : [ {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.inputs.address\", \"tx.value.msg.value.outputs.address\" ],\"query\" : \"" + address + "\"}}, {\"multi_match\" : {\"fields\" : [ \"tx.value.msg.value.inputs.coins.denom\", \"tx.value.msg.value.outputs.coins.denom\" ],\"query\" : \"" + symbol + "\"}} ]}},\"size\" : 100}"
            print("query ", query)
            url = IRIS_ES_PROXY_IRIS
        }
        let data = query.data(using: .utf8)
        do {
            let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
            let request = Alamofire.request(url, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
            request.validate().responseJSON { response in
                switch response.result {
                case .success(let res):
                    self.mHistories.removeAll()
                    guard let history = res as? [String : Any] else {
                        print("no history!!")
                        return;
                    }
                    
                    let rawHistory = History.init(history)
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
        var url = ""
        if (chainType == ChainType.BINANCE_MAIN) {
            url = BNB_URL_HISTORY
        } else if (chainType == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_HISTORY
        }
        let request = Alamofire.request(url, method: .get, parameters: ["address":address, "startTime":Date().Stringmilli3MonthAgo, "endTime":Date().millisecondsSince1970, "txAsset":symbol], encoding: URLEncoding.default, headers: [:])
//        let request = Alamofire.request(url, method: .get, parameters: ["address":address, "startTime":Date().Stringmilli3MonthAgo, "endTime":Date().millisecondsSince1970, "txAsset":symbol, "txType":"CLAIM_HTL"], encoding: URLEncoding.default, headers: [:])
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
    
    func onFetchApiHistory(_ address:String, _ symbol:String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_API_TRANS_HISTORY + address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_API_TEST_TRANS_HISTORY + address
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["denom":balance!.balance_denom], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("res ", res)
                if (self.chainType == ChainType.KAVA_TEST || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.BAND_MAIN) {
                    self.mApiHistories.removeAll()
                    guard let histories = res as? Array<NSDictionary> else {
                        print("no history!!")
                        return;
                    }
                    for rawHistory in histories {
                        self.mApiHistories.append(ApiHistory.HistoryData.init(rawHistory))
                    }
                    if (self.mApiHistories.count > 0) {
                        self.tokenDetailTableView.reloadData()
                    }
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchApiHistory ", error) }
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
        if (chainType! == ChainType.COSMOS_MAIN) {
            if (WUtils.getTokenAmount(balances, COSMOS_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "200000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mIrisToken = self.irisToken
            txVC.mType = IRIS_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: "0.000375")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mBnbToken = self.bnbToken
            txVC.mType = BNB_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            if (WUtils.getTokenAmount(balances, KAVA_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mKavaSendDenom = self.balance?.balance_denom
            txVC.mType = KAVA_MSG_TYPE_TRANSFER
        }
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onRecieveToken() {
        var nickName:String?
        if (account!.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account!.account_nick_name
        }
        self.shareAddress(account!.account_address, nickName!)
    }
    
    func onBuyCoin() {
        if (account!.account_has_private) {
            self.onShowBuySelectFiat()
        } else {
            self.onShowBuyWarnNoKey()
        }
    }
    
    func onClickBep3Send(_ denom: String) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: FEE_BEP3_SEND_CHECK)).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            print("", WUtils.getTokenAmount(balances, BNB_MAIN_DENOM))
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).multiplying(byPowerOf10: -8).compare(NSDecimalNumber.init(string: FEE_BEP3_SEND_MIN)).rawValue <= 0) {
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
            query = query + "&currencyCode=atom";
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            query = query + "&currencyCode=bnb";
        } else if (chainType! == ChainType.KAVA_MAIN) {
            query = query + "&currencyCode=kava";
        }
        query = query + "&walletAddress=" + account!.account_address + "&baseCurrencyCode=" + fiat;
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
}
