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
    var okDenom:String?
    var okToken:OkToken?
    
    var refresher: UIRefreshControl!
    var mApiHistories = Array<ApiHistory.HistoryData>()
    var mBnbHistories = Array<BnbHistory>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderCosmosCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderCosmosCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderIrisCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderIrisCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderBnbCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderBnbCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderKavaCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderKavaCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderOkCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderOkCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderCustomCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderCustomCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailVestingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailVestingDetailCell")
        
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
            } else if (chainType == ChainType.OKEX_TEST) {
                keyState.tintColor = COLOR_OK
            }
        }
    }
    
    @objc func onRequestFetch() {
        if (chainType == ChainType.COSMOS_MAIN) {
            onFetchApiHistory(account!.account_address, balance!.balance_denom)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            onFetchApiHistory(account!.account_address, balance!.balance_denom)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            onFetchBnbHistory(account!.account_address, bnbToken!.symbol);
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            onFetchApiHistory(account!.account_address, balance!.balance_denom)
            
        } else if (chainType == ChainType.OKEX_TEST) {
            self.refresher.endRefreshing()
            
        }
    }

    @IBAction func onClickWebLink(_ sender: UIButton) {
        if (chainType! == ChainType.COSMOS_MAIN) {
            guard let url = URL(string: EXPLORER_COSMOS_MAIN + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            guard let url = URL(string: EXPLORER_IRIS_MAIN + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: EXPLORER_BINANCE_MAIN + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            guard let url = URL(string: EXPLORER_IOV_MAIN + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_MAIN) {
            guard let url = URL(string: EXPLORER_KAVA_MAIN + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: EXPLORER_BAND_MAIN + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_TEST) {
            guard let url = URL(string: EXPLORER_BINANCE_TEST + "address/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: EXPLORER_OKEX_TEST + "address/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: EXPLORER_KAVA_TEST + "account/" + account!.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: EXPLORER_CERTIK_TEST + "accounts/" + account!.account_address + "?net=" + WUtils.getChainId(chainType!)) else { return }
            self.onShowSafariWeb(url)
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
        if (section == 0) {
            if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                if (balance?.balance_denom == KAVA_MAIN_DENOM && BaseData.instance.mKavaAccountResult.getCVestingCnt(KAVA_MAIN_DENOM) > 0) {
                    return 2
                } else if (balance?.balance_denom == KAVA_HARD_DENOM && BaseData.instance.mKavaAccountResult.getCVestingCnt(KAVA_HARD_DENOM) > 0) {
                    return 2
                }
            }
            return 1
            
        } else {
            return mApiHistories.count
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
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
                    
                } else if (chainType == ChainType.KAVA_TEST && balance?.balance_denom == KAVA_MAIN_DENOM) {
                    return onSetKavaTestItem(tableView, indexPath);
                    
                } else if (chainType == ChainType.OKEX_TEST && self.okDenom == OKEX_TEST_DENOM) {
                    return onSetOkItem(tableView, indexPath);
                    
                } else {
                    return onSetCustomTokenItem(tableView, indexPath);
                }
                
            } else {
                return onSetKavaVestingItems(tableView, indexPath);
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
            if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.IRIS_MAIN) {
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
                           
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                let history = mApiHistories[indexPath.row]
                let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
                txDetailVC.mIsGen = false
                txDetailVC.mTxHash = history.tx_hash
                txDetailVC.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(txDetailVC, animated: true)
                
            } else if (chainType == ChainType.BINANCE_TEST) {
                let bnbHistory = mBnbHistories[indexPath.row]
                guard let url = URL(string: "https://testnet-explorer.binance.org/tx/" + bnbHistory.txHash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                safariViewController.modalPresentationStyle = .popover
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
        let totalAmount = WUtils.getAllBnb(balance)
        cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 0, 6)
        cell?.totalValue.attributedText = WUtils.dpBnbValue(totalAmount, BaseData.instance.getLastPrice(), cell!.totalValue.font)
        cell?.availableAmount.attributedText = WUtils.displayAmount2(balance?.balance_amount, cell!.availableAmount.font, 0, 6)
        cell?.lockedAmount.attributedText = WUtils.displayAmount2(balance?.balance_locked, cell!.lockedAmount.font, 0, 6)
        cell?.actionSend  = {
            self.onSendToken()
        }
        cell?.actionRecieve = {
            self.onRecieveToken()
        }
        cell?.BtnSendBep3.isHidden = false;
        cell?.actionSendBep3 = {
            self.onClickBep3Send(self.balance?.balance_denom)
        }
        if (chainType == ChainType.BINANCE_MAIN) {
            cell?.BtnBuyBnb.isHidden = false
            cell?.actionBuy = {
                self.onBuyCoin()
            }
        } else {
            cell?.BtnBuyBnb.isHidden = true
        }
        return cell!
    }
    
    func onSetKavaItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderKavaCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderKavaCell") as? TokenDetailHeaderKavaCell
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let unbondingList = BaseData.instance.selectUnbondingById(accountId: account!.account_id)
        
        let totalAmount = WUtils.getAllKava(balances, bondingList, unbondingList, allRewards, allValidator)
        let availableAmount = WUtils.availableAmount(balances, KAVA_MAIN_DENOM)
        let delegatedAmount = WUtils.deleagtedAmount(bondingList, allValidator, chainType!)
        let unbondingAmount = WUtils.unbondingAmount(unbondingList, chainType!)
        let rewardAmount = WUtils.rewardAmount(allRewards, KAVA_MAIN_DENOM, chainType!)
        let vestingAmount = WUtils.lockedAmount(balances, KAVA_MAIN_DENOM)
        let havestDepositAmount = WUtils.havestDepositAmount(KAVA_MAIN_DENOM)
        let unclaimedIncentiveAmount = WUtils.unclaimedIncentiveAmount(KAVA_MAIN_DENOM)
        
        cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 6, 6)
        cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
        cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 6, 6)
        cell?.delegatedAmount.attributedText = WUtils.displayAmount2(delegatedAmount.stringValue, cell!.delegatedAmount.font, 6, 6)
        cell?.unbondingAmount.attributedText = WUtils.displayAmount2(unbondingAmount.stringValue, cell!.unbondingAmount.font, 6, 6)
        cell?.rewardAmount.attributedText = WUtils.displayAmount2(rewardAmount.stringValue, cell!.rewardAmount.font, 6, 6)
        cell?.vestingAmount.attributedText = WUtils.displayAmount2(vestingAmount.stringValue, cell!.vestingAmount.font, 6, 6)
        cell?.havestDepositedAmount.attributedText = WUtils.displayAmount2(havestDepositAmount.stringValue, cell!.havestDepositedAmount.font, 6, 6)
        cell?.unClaimedIncentiveAmount.attributedText = WUtils.displayAmount2(unclaimedIncentiveAmount.stringValue, cell!.unClaimedIncentiveAmount.font, 6, 6)
        if (vestingAmount != NSDecimalNumber.zero) {
            cell?.vestingLayer.isHidden = false
        }
        if (havestDepositAmount != NSDecimalNumber.zero) {
            cell?.havestDepositLayer.isHidden = false
        }
        if (unclaimedIncentiveAmount != NSDecimalNumber.zero) {
            cell?.unClaimedIncentiveLayer.isHidden = false
        }
        cell?.actionSend  = {
            self.onSendToken()
        }
        cell?.actionRecieve = {
            self.onRecieveToken()
        }
        return cell!
    }
    
    func onSetKavaTestItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderKavaCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderKavaCell") as? TokenDetailHeaderKavaCell
        cell?.cardRoot.backgroundColor = WUtils.getChainBg(chainType!)
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let unbondingList = BaseData.instance.selectUnbondingById(accountId: account!.account_id)
        
        let totalAmount = WUtils.getAllKava(balances, bondingList, unbondingList, allRewards, allValidator)
        let availableAmount = WUtils.availableAmount(balances, KAVA_MAIN_DENOM)
        let delegatedAmount = WUtils.deleagtedAmount(bondingList, allValidator, chainType!)
        let unbondingAmount = WUtils.unbondingAmount(unbondingList, chainType!)
        let rewardAmount = WUtils.rewardAmount(allRewards, KAVA_MAIN_DENOM, chainType!)
        let vestingAmount = WUtils.lockedAmount(balances, KAVA_MAIN_DENOM)
        let havestDepositAmount = WUtils.havestDepositAmount(KAVA_MAIN_DENOM)
        let unclaimedIncentiveAmount = WUtils.unclaimedIncentiveAmount(KAVA_MAIN_DENOM)
        
        cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 6, 6)
        cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
        cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 6, 6)
        cell?.delegatedAmount.attributedText = WUtils.displayAmount2(delegatedAmount.stringValue, cell!.delegatedAmount.font, 6, 6)
        cell?.unbondingAmount.attributedText = WUtils.displayAmount2(unbondingAmount.stringValue, cell!.unbondingAmount.font, 6, 6)
        cell?.rewardAmount.attributedText = WUtils.displayAmount2(rewardAmount.stringValue, cell!.rewardAmount.font, 6, 6)
        cell?.vestingAmount.attributedText = WUtils.displayAmount2(vestingAmount.stringValue, cell!.vestingAmount.font, 6, 6)
        cell?.havestDepositedAmount.attributedText = WUtils.displayAmount2(havestDepositAmount.stringValue, cell!.havestDepositedAmount.font, 6, 6)
        cell?.unClaimedIncentiveAmount.attributedText = WUtils.displayAmount2(unclaimedIncentiveAmount.stringValue, cell!.unClaimedIncentiveAmount.font, 6, 6)
        if (vestingAmount != NSDecimalNumber.zero) {
            cell?.vestingLayer.isHidden = false
        }
        if (havestDepositAmount != NSDecimalNumber.zero) {
            cell?.havestDepositLayer.isHidden = false
        }
        if (unclaimedIncentiveAmount != NSDecimalNumber.zero) {
            cell?.unClaimedIncentiveLayer.isHidden = false
        }
        cell?.actionSend  = {
            self.onSendToken()
        }
        cell?.actionRecieve = {
            self.onRecieveToken()
        }
        return cell!
    }
    
    func onSetOkItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderOkCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderOkCell") as? TokenDetailHeaderOkCell
        cell?.rootCardView.backgroundColor = COLOR_BG_GRAY
        let totalAmount = WUtils.getAllOkt(balances, BaseData.instance.mOkDeposit, BaseData.instance.mOkWithdraw)
        let availableAmount = WUtils.availableAmount(balances, OKEX_TEST_DENOM)
        let lockedAmount = WUtils.lockedAmount(balances, OKEX_TEST_DENOM)
        let depositAmount = WUtils.okDepositAmount(BaseData.instance.mOkDeposit)
        let withdrawAmount = WUtils.okWithdrawAmount(BaseData.instance.mOkWithdraw)
        
        cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 0, 8)
        cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 0, 8)
        cell?.lockedAmount.attributedText = WUtils.displayAmount2(lockedAmount.stringValue, cell!.lockedAmount.font, 0, 8)
        cell?.depositAmount.attributedText = WUtils.displayAmount2(depositAmount.stringValue, cell!.depositAmount.font, 0, 8)
        cell?.withdrawAmount.attributedText = WUtils.displayAmount2(withdrawAmount.stringValue, cell!.withdrawAmount.font, 0, 8)
        cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 0, cell!.totalValue.font)
        
        cell?.actionSend  = {
            self.onSendToken()
        }
        cell?.actionReceive = {
            self.onRecieveToken()
        }
        
        return cell!
    }
    
    func onSetCustomTokenItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:TokenDetailHeaderCustomCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderCustomCell") as? TokenDetailHeaderCustomCell
        if (chainType == ChainType.COSMOS_MAIN) {
            //TODO not this case yet!

        } else if (chainType == ChainType.IRIS_MAIN && irisToken != nil) {
            cell?.tokenInfoBtn.isHidden = true
            cell?.tokenSymbol.text = irisToken?.base_token?.symbol.uppercased()
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.totalAmount.font, irisToken!.base_token!.decimal, irisToken!.base_token!.decimal)
            cell?.totalAmount.adjustsFontSizeToFitWidth = true
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.availableAmount.font, irisToken!.base_token!.decimal, irisToken!.base_token!.decimal)
            cell?.actionSend  = {
                self.onShowToast(NSLocalizedString("error_iris_token_yet", comment: ""))
            }

        } else if ((chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) && bnbToken != nil) {
            cell?.tokenInfoBtn.isHidden = false
            cell?.tokenSymbol.text = bnbToken?.original_symbol.uppercased()
            cell?.totalAmount.attributedText = WUtils.displayAmount2(balance!.getAllAmountBnbToken().stringValue, cell!.totalAmount.font, 0, 8)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(balance!.balance_amount, cell!.availableAmount.font, 0, 8)
            cell?.totalValue.attributedText = WUtils.dpBnbValue(balance!.exchangeBnbValue(bnbTic), BaseData.instance.getLastPrice(), cell!.totalValue.font)
            let url = TOKEN_IMG_URL + bnbToken!.original_symbol + ".png"
            cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
            cell?.actionTokenInfo = {
                if (self.chainType == ChainType.BINANCE_MAIN) {
                    guard let url = URL(string: EXPLORER_BINANCE_MAIN + "assets/" + self.bnbToken!.symbol) else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    safariViewController.modalPresentationStyle = .popover
                    self.present(safariViewController, animated: true, completion: nil)
                } else {
                    guard let url = URL(string: EXPLORER_BINANCE_TEST + "asset/" + self.bnbToken!.symbol) else { return }
                    let safariViewController = SFSafariViewController(url: url)
                    safariViewController.modalPresentationStyle = .popover
                    self.present(safariViewController, animated: true, completion: nil)
                }
            }
            cell?.actionSend  = {
                self.onSendToken()
            }
            if (balance?.balance_denom == TOKEN_HTLC_BINANCE_TEST_BTC) {
                cell?.btnBep3Send.isHidden = false
                cell?.actionBep3Send  = {
                    self.onClickBep3Send(self.balance?.balance_denom)
                }
            } else {
                cell?.btnBep3Send.isHidden = true
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let dpDecimal = WUtils.getKavaCoinDecimal(balance!.balance_denom)
            let totalTokenAmount = WUtils.getKavaTokenAll(balance!.balance_denom, balances)
            let availableTokenAmount = WUtils.getKavaTokenAvailable(balance!.balance_denom, balances)
            let vestingTokenAmount = WUtils.getKavaTokenVesting(balance!.balance_denom, balances)
            let havestDepositTokenAmount = WUtils.getKavaTokenHavestDeposited(balance!.balance_denom, balances)
            let havestRewardTokenAmount = WUtils.getKavaTokenHavestReward(balance!.balance_denom, balances)
            let totalTokenValue = WUtils.getKavaTokenDollorValue(balance!.balance_denom, totalTokenAmount)
            let convertedKavaAmount = totalTokenValue.dividing(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)))
            
            print("availableTokenAmount ", availableTokenAmount)
            
            cell?.tokenInfoBtn.isHidden = true
            cell?.tokenSymbol.text = balance!.balance_denom.uppercased()
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalTokenAmount.stringValue, cell!.totalAmount.font, dpDecimal, dpDecimal)
            cell?.totalValue.attributedText = WUtils.dpAtomValue(convertedKavaAmount.multiplying(byPowerOf10: WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)), BaseData.instance.getLastPrice(), cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(availableTokenAmount.stringValue, cell!.availableAmount.font, dpDecimal, dpDecimal)
            cell?.vestingAmount.attributedText = WUtils.displayAmount2(vestingTokenAmount.stringValue, cell!.vestingAmount.font, dpDecimal, dpDecimal)
            cell?.havestDepositAmount.attributedText = WUtils.displayAmount2(havestDepositTokenAmount.stringValue, cell!.havestDepositAmount.font, dpDecimal, dpDecimal)
            cell?.havestRewardAmount.attributedText = WUtils.displayAmount2(havestRewardTokenAmount.stringValue, cell!.havestRewardAmount.font, dpDecimal, dpDecimal)
            
            if (vestingTokenAmount != NSDecimalNumber.zero) {
                cell?.vestingLayer.isHidden = false
            }
            cell?.havestDepsoitLayer.isHidden = false
            if (havestRewardTokenAmount != NSDecimalNumber.zero) {
                cell?.havsetRewardLayer.isHidden = false
            }
            
            let url = KAVA_COIN_IMG_URL + balance!.balance_denom + ".png"
            cell?.tokenImg.af_setImage(withURL: URL(string: url)!)
            cell?.actionSend  = {
                self.onSendToken()
            }
            if (balance?.balance_denom == TOKEN_HTLC_KAVA_TEST_BNB || balance?.balance_denom == TOKEN_HTLC_KAVA_TEST_BTC) {
                cell?.btnBep3Send.isHidden = false
                cell?.actionBep3Send  = {
                    self.onClickBep3Send(self.balance?.balance_denom)
                }
            } else {
                cell?.btnBep3Send.isHidden = true
            }
            
        } else if (chainType == ChainType.OKEX_TEST && okDenom != nil) {
            cell?.tokenInfoBtn.isHidden = false
            okToken = WUtils.getOkToken(BaseData.instance.mOkTokenList, okDenom!)
            cell?.tokenSymbol.text = okToken?.original_symbol.uppercased()
            
            let available = WUtils.availableAmount(balances, okToken!.original_symbol)
            let locked = WUtils.lockedAmount(balances, okToken!.original_symbol)
            let total = available.adding(locked)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(total.stringValue, cell!.totalAmount.font, 0, 8)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(available.stringValue, cell!.availableAmount.font, 0, 8)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(total, BaseData.instance.getLastPrice(), 0, cell!.totalValue.font)
            
            cell?.actionTokenInfo = {
                guard let url = URL(string: EXPLORER_OKEX_TEST + "token/" + self.okToken!.symbol) else { return }
                let safariViewController = SFSafariViewController(url: url)
                safariViewController.modalPresentationStyle = .popover
                self.present(safariViewController, animated: true, completion: nil)
            }
            cell?.actionSend  = {
                self.onSendToken()
            }
        }
        cell?.actionReceive = {
            self.onRecieveToken()
        }
        return cell!
    }
    
    func onSetCosmosHistoryItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mApiHistories[indexPath.row]
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
    
    func onSetIrisHistoryItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mApiHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.txTimetoString(input: history.time)
        cell?.txTimeGapLabel.text = WUtils.txTimeGap(input: history.time)
        cell?.txBlockLabel.text = String(history.height) + " block"
        cell?.txTypeLabel.text = WUtils.historyTitle(history.msg, account!.account_address)
        if (history.result.code > 0) {
            cell?.txResultLabel.isHidden = false
        } else {
            cell?.txResultLabel.isHidden = true
        }
        return cell!
    }
    
    func onSetBnbHistoryItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mBnbHistories[indexPath.row]
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history.timeStamp)
        cell?.txTimeGapLabel.text = WUtils.timeGap(input: history.timeStamp)
        cell?.txBlockLabel.text = String(history.blockHeight) + " block"
        cell?.txTypeLabel.text = WUtils.bnbHistoryTitle(history, account!.account_address)
        cell?.txResultLabel.isHidden = true
        return cell!
    }
    
    func onSetKavaHistoryItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mApiHistories[indexPath.row]
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
    
    func onSetKavaVestingItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:TokenDetailVestingDetailCell? = tableView.dequeueReusableCell(withIdentifier:"TokenDetailVestingDetailCell") as? TokenDetailVestingDetailCell
        let denom = balance!.balance_denom
        let mKavaAccount = BaseData.instance.mKavaAccountResult
        cell?.rootCardView.backgroundColor = WUtils.getChainBg(chainType!)
        cell?.vestingCntLabel.text = "(" + String(mKavaAccount.getCVestingCnt(denom)) + ")"
        cell?.vestingTotalAmount.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingSum(denom).stringValue, cell!.vestingTotalAmount.font!, 6, 6)
        if (mKavaAccount.getCVestingCnt(denom) > 0) {
            cell?.vestingTime0.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(0, denom))
            cell?.vestingGap0.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(0, denom))
            cell?.vestingAmount0.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(0, denom).stringValue, cell!.vestingAmount0.font!, 6, 6)
        }
        if (mKavaAccount.getCVestingCnt(denom) > 1) {
            cell?.vestingLayer1.isHidden = false
            cell?.vestingTime1.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(1, denom))
            cell?.vestingGap1.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(1, denom))
            cell?.vestingAmount1.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(1, denom).stringValue, cell!.vestingAmount1.font!, 6, 6)
        }
        if (mKavaAccount.getCVestingCnt(denom) > 2) {
            cell?.vestingLayer2.isHidden = false
            cell?.vestingTime2.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(2, denom))
            cell?.vestingGap2.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(2, denom))
            cell?.vestingAmount2.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(2, denom).stringValue, cell!.vestingAmount2.font!, 6, 6)
        }
        if (mKavaAccount.getCVestingCnt(denom) > 3) {
            cell?.vestingLayer3.isHidden = false
            cell?.vestingTime3.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(3, denom))
            cell?.vestingGap3.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(3, denom))
            cell?.vestingAmount3.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(3, denom).stringValue, cell!.vestingAmount3.font!, 6, 6)
        }
        if (mKavaAccount.getCVestingCnt(denom) > 4) {
            cell?.vestingLayer4.isHidden = false
            cell?.vestingTime4.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(4, denom))
            cell?.vestingGap4.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(4, denom))
            cell?.vestingAmount4.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(4, denom).stringValue, cell!.vestingAmount4.font!, 6, 6)
        }
        return cell!
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
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_API_TRANS_HISTORY + address
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_API_TRANS_HISTORY + address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_API_TRANS_HISTORY + address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_API_TRANS_HISTORY + address
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["denom":balance!.balance_denom], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
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
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(balances, OKEX_TEST_DENOM).compare(NSDecimalNumber.init(string: "0.02")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mOkSendDenom = okDenom
            txVC.mType = OK_MSG_TYPE_TRANSFER
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
    
    func onClickBep3Send(_ denom: String?) {
        if (denom == nil) { return }
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: GAS_FEE_BNB_TRANSFER)).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_SWAP
        txVC.mHtlcDenom = denom!
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
