//
//  MainTabWalletViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import Floaty
import SafariServices

class MainTabWalletViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, FloatyDelegate, QrScannerDelegate, PasswordViewDelegate {

    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleAlarmBtn: UIButton!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var walletTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mainTabVC: MainTabViewController!
    var wcURL:String?
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?
    var mIrisStakePool: NSDictionary?

    override func viewDidLoad() {
        super.viewDidLoad()
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        self.mInflation = BaseData.instance.mInflation
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        self.mIrisStakePool = BaseData.instance.mIrisStakePool
        
        self.walletTableView.delegate = self
        self.walletTableView.dataSource = self
        self.walletTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.walletTableView.register(UINib(nibName: "WalletAddressCell", bundle: nil), forCellReuseIdentifier: "WalletAddressCell")
        self.walletTableView.register(UINib(nibName: "WalletCosmosCell", bundle: nil), forCellReuseIdentifier: "WalletCosmosCell")
        self.walletTableView.register(UINib(nibName: "WalletIrisCell", bundle: nil), forCellReuseIdentifier: "WalletIrisCell")
        self.walletTableView.register(UINib(nibName: "WalletBnbCell", bundle: nil), forCellReuseIdentifier: "WalletBnbCell")
        self.walletTableView.register(UINib(nibName: "WalletKavaCell", bundle: nil), forCellReuseIdentifier: "WalletKavaCell")
        self.walletTableView.register(UINib(nibName: "WalletKavaIncentiveCell", bundle: nil), forCellReuseIdentifier: "WalletKavaIncentiveCell")
        self.walletTableView.register(UINib(nibName: "WalletIovCell", bundle: nil), forCellReuseIdentifier: "WalletIovCell")
        self.walletTableView.register(UINib(nibName: "WalletBandCell", bundle: nil), forCellReuseIdentifier: "WalletBandCell")
        self.walletTableView.register(UINib(nibName: "WalletOkCell", bundle: nil), forCellReuseIdentifier: "WalletOkCell")
        self.walletTableView.register(UINib(nibName: "WalletCertikCell", bundle: nil), forCellReuseIdentifier: "WalletCertikCell")
        self.walletTableView.register(UINib(nibName: "WalletUnbondingInfoCellTableViewCell", bundle: nil), forCellReuseIdentifier: "WalletUnbondingInfoCellTableViewCell")
        self.walletTableView.register(UINib(nibName: "WalletVestingDetailCell", bundle: nil), forCellReuseIdentifier: "WalletVestingDetailCell")
        self.walletTableView.register(UINib(nibName: "WalletPriceCell", bundle: nil), forCellReuseIdentifier: "WalletPriceCell")
        self.walletTableView.register(UINib(nibName: "WalletInflationCell", bundle: nil), forCellReuseIdentifier: "WalletInflationCell")
        self.walletTableView.register(UINib(nibName: "WalletGuideCell", bundle: nil), forCellReuseIdentifier: "WalletGuideCell")
        
        self.walletTableView.rowHeight = UITableView.automaticDimension
        self.walletTableView.estimatedRowHeight = UITableView.automaticDimension
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        refresher.tintColor = UIColor.white
        walletTableView.addSubview(refresher)
        
//        print("aa", WUtils.localeStringToDecimal(FEE_ATOM_TINY))
    
        self.updateFloaty()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceFetchDone(_:)), name: Notification.Name("onPriceFetchDone"), object: nil)
        self.updateTitle()
        self.walletTableView.reloadData()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    
    func updateTitle() {
        titleChainName.textColor = WUtils.getChainColor(chainType!)
        if (mainTabVC.mAccount.account_nick_name == "") {
            titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            titleWalletName.text = mainTabVC.mAccount.account_nick_name
        }
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
        } else if (chainType! == ChainType.BAND_MAIN) {
            titleChainImg.image = UIImage(named: "bandChainImg")
            titleChainName.text = "(Band Mainnet)"
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
    
    func updateFloaty() {
        let floaty = Floaty()
        floaty.buttonImage = UIImage.init(named: "sendImg")
        floaty.buttonColor =  WUtils.getChainColor(chainType)
        floaty.fabDelegate = self
        self.view.addSubview(floaty)
    }

    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.mInflation = BaseData.instance.mInflation
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        self.mIrisStakePool = BaseData.instance.mIrisStakePool
        self.walletTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        self.walletTableView.reloadData()
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        self.onClickMainSend()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.COSMOS_MAIN) {
            return 6;
        } else if  (chainType == ChainType.IRIS_MAIN) {
            return 5;
        } else if  (chainType == ChainType.KAVA_MAIN) {
            return 7
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return 4;
        } else if (chainType == ChainType.KAVA_TEST) {
            return 7
        } else if (chainType == ChainType.BAND_MAIN) {
            return 5;
        } else if (chainType == ChainType.IOV_MAIN  || chainType == ChainType.IOV_TEST ) {
            return 5;
        } else if (chainType == ChainType.OKEX_TEST) {
            return 4;
        } else if (chainType == ChainType.CERTIK_TEST) {
            return 5;
        } else {
            return 0;
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType == ChainType.COSMOS_MAIN) {
            return onSetCosmosItems(tableView, indexPath);
        } else if (chainType == ChainType.IRIS_MAIN) {
            return onSetIrisItem(tableView, indexPath);
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return onSetBnbItem(tableView, indexPath);
        } else if (chainType == ChainType.KAVA_MAIN) {
            return onSetKavaItem(tableView, indexPath);
        } else if (chainType == ChainType.BAND_MAIN) {
            return onSetBandItem(tableView, indexPath);
        } else if (chainType == ChainType.KAVA_TEST) {
            return onSetKavaTestItem(tableView, indexPath);
        } else if (chainType == ChainType.IOV_MAIN) {
            return onSetIovItems(tableView, indexPath);
        } else if (chainType == ChainType.IOV_TEST) {
            return onSetIovTestItems(tableView, indexPath);
        } else if (chainType == ChainType.OKEX_TEST) {
            return onSetOkTestItems(tableView, indexPath);
        } else if (chainType == ChainType.CERTIK_TEST) {
            return onSetCertikTestItems(tableView, indexPath);
        } else {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if (chainType == ChainType.COSMOS_MAIN) {
            if (indexPath.row == 2) {
                if (mainTabVC.mUnbondingList.count > 0) {
                    return UITableView.automaticDimension;
                } else {
                    return 0;
                }
            }
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            //TODO
//            if (BaseData.instance.mUnClaimedIncentiveRewards.count == 0 && indexPath.row == 3) {
//                return 0;
//            }
//            if ((BaseData.instance.mKavaAccountResult.type == COSMOS_AUTH_TYPE_ACCOUNT || BaseData.instance.mKavaAccountResult.getCVestingCnt() == 0) &&
//                indexPath.row == 2) {
//                return 0;
//            }
            if (indexPath.row == 2 || indexPath.row == 3) {
                return 0;
            }
        }
        return UITableView.automaticDimension;
    }
    
    func onSetCosmosItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_ATOM
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletCosmosCell? = tableView.dequeueReusableCell(withIdentifier:"WalletCosmosCell") as? WalletCosmosCell
            let totalAtom = WUtils.getAllAtom(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAtom.stringValue, cell!.totalAmount.font!, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAtom, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(mainTabVC.mBalances, cell!.availableAmount.font, 6, COSMOS_MAIN_DENOM, chainType!)
            cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(mainTabVC.mBondingList, mainTabVC.mAllValidator, cell!.delegatedAmount.font, 6, chainType!)
            cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(mainTabVC.mUnbondingList, cell!.unbondingAmount.font, 6, chainType!)
            cell?.rewardAmount.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell!.rewardAmount.font, 6, COSMOS_MAIN_DENOM, chainType!)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onClickVoteList()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalAtom.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletUnbondingInfoCellTableViewCell? = tableView.dequeueReusableCell(withIdentifier:"WalletUnbondingInfoCellTableViewCell") as? WalletUnbondingInfoCellTableViewCell
            let unBondings = mainTabVC!.mUnbondingList.sorted(by: {$0.unbonding_complete_time < $1.unbonding_complete_time})
            cell?.unBondingCnt.text = String(mainTabVC!.mUnbondingList.count)
            if (unBondings.count > 0) {
                cell?.unBondingMoniker0.text = WUtils.getMonikerName(mainTabVC!.mAllValidator, unBondings[0].unbonding_v_address, false)
                cell?.unBondingAmount0.attributedText = WUtils.displayAmount2(unBondings[0].unbonding_balance, cell!.unBondingAmount0.font!, 6, 6)
                cell?.unBondingTime0.text = WUtils.getUnbondingTimeleft(unBondings[0].unbonding_complete_time)
            }
            if (unBondings.count > 1) {
                cell?.unBondingLayer1.isHidden = false
                cell?.unBondingMoniker1.text = WUtils.getMonikerName(mainTabVC!.mAllValidator, unBondings[1].unbonding_v_address, false)
                cell?.unBondingAmount1.attributedText = WUtils.displayAmount2(unBondings[1].unbonding_balance, cell!.unBondingAmount1.font!, 6, 6)
                cell?.unBondingTime1.text = WUtils.getUnbondingTimeleft(unBondings[1].unbonding_complete_time)
            }
            if (unBondings.count > 2) {
                cell?.unBondingLayer2.isHidden = false
                cell?.unBondingMoniker2.text = WUtils.getMonikerName(mainTabVC!.mAllValidator, unBondings[2].unbonding_v_address, false)
                cell?.unBondingAmount2.attributedText = WUtils.displayAmount2(unBondings[2].unbonding_balance, cell!.unBondingAmount2.font!, 6, 6)
                cell?.unBondingTime2.text = WUtils.getUnbondingTimeleft(unBondings[2].unbonding_complete_time)
            }
            if (unBondings.count > 3) {
                cell?.unBondingLayer3.isHidden = false
                cell?.unBondingMoniker3.text = WUtils.getMonikerName(mainTabVC!.mAllValidator, unBondings[3].unbonding_v_address, false)
                cell?.unBondingAmount3.attributedText = WUtils.displayAmount2(unBondings[3].unbonding_balance, cell!.unBondingAmount3.font!, 6, 6)
                cell?.unBondingTime3.text = WUtils.getUnbondingTimeleft(unBondings[3].unbonding_complete_time)
            }
            if (unBondings.count > 4) {
                cell?.unBondingLayer4.isHidden = false
                cell?.unBondingMoniker4.text = WUtils.getMonikerName(mainTabVC!.mAllValidator, unBondings[4].unbonding_v_address, false)
                cell?.unBondingAmount4.attributedText = WUtils.displayAmount2(unBondings[4].unbonding_balance, cell!.unBondingAmount4.font!, 6, 6)
                cell?.unBondingTime4.text = WUtils.getUnbondingTimeleft(unBondings[4].unbonding_complete_time)
            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.text = ""
            }
            cell?.buySeparator.isHidden = false
            cell?.buyBtn.isHidden = false
            cell?.buyBtn.setTitle(NSLocalizedString("buy_atom", comment: ""), for: .normal)
            cell?.buyConstraint.priority = .defaultHigh
            cell?.noBuyConstraint.priority = .defaultLow
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            cell?.actionBuy = {
                self.onClickBuyCoin()
            }
            return cell!
            
        } else if (indexPath.row == 4) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "guideImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_cosmos", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_cosmos", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_cosmos", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_cosmos", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
    }
    
    func onSetIrisItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_IRIS
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletIrisCell? = tableView.dequeueReusableCell(withIdentifier:"WalletIrisCell") as? WalletIrisCell
            let totalIris = WUtils.getAllIris(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mIrisRewards, mainTabVC.mAllValidator)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalIris.stringValue, cell!.totalAmount.font!, 18, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalIris, BaseData.instance.getLastPrice(), 18, cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(mainTabVC.mBalances, cell!.availableAmount.font, 6, IRIS_MAIN_DENOM, chainType!)
            cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(mainTabVC.mBondingList, mainTabVC.mAllValidator, cell!.delegatedAmount.font, 6, chainType!)
            cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(mainTabVC.mUnbondingList, cell!.unbondingAmount.font, 6, chainType!)
            cell?.rewardAmount.attributedText = WUtils.dpIrisRewards(mainTabVC.mIrisRewards, cell!.rewardAmount.font, 6, chainType!)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onClickVoteList()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalIris.multiplying(byPowerOf10: -18).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.text = ""
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: "0.04"), font: cell!.infaltionLabel.font)
            if (self.mIrisStakePool != nil) {
                let provisions = NSDecimalNumber.init(string: self.mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                let bonded_tokens = NSDecimalNumber.init(string: self.mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                cell?.yieldLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "irisnetImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_iris", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_iris", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_iris", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_iris", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
        
    }
    
    func onSetBnbItem(_ tableView: UITableView, _ indexPath: IndexPath)  -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_BNB
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletBnbCell? = tableView.dequeueReusableCell(withIdentifier:"WalletBnbCell") as? WalletBnbCell
            cell?.bnbCard.backgroundColor = WUtils.getChainBg(chainType!)
            cell?.btnBep3.isHidden = false
            
            let availableAmount = WUtils.availableAmount(mainTabVC.mBalances, BNB_MAIN_DENOM)
            let lockedAmount = WUtils.lockedAmount(mainTabVC.mBalances, BNB_MAIN_DENOM)
            let totalAmount = availableAmount.adding(lockedAmount)
            
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 0, 6)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 0, 6)
            cell?.lockedAmount.attributedText = WUtils.displayAmount2(lockedAmount.stringValue, cell!.lockedAmount.font, 0, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 0, cell!.totalValue.font)
            
            cell?.actionWC = {
                self.onClickWalletConect()
            }
            cell?.actionBep3 = {
                self.onClickBep3Send(BNB_MAIN_DENOM)
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalAmount.stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.text = ""
            }
            
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            if (chainType == ChainType.BINANCE_MAIN) {
                cell?.buySeparator.isHidden = false
                cell?.buyBtn.isHidden = false
                cell?.buyBtn.setTitle(NSLocalizedString("buy_bnb", comment: ""), for: .normal)
                cell?.buyConstraint.priority = .defaultHigh
                cell?.noBuyConstraint.priority = .defaultLow
                cell?.actionBuy = {
                    self.onClickBuyCoin()
                }
            } else {
                cell?.buySeparator.isHidden = true
                cell?.buyBtn.isHidden = true
                cell?.buyConstraint.priority = .defaultLow
                cell?.noBuyConstraint.priority = .defaultHigh
                cell?.actionBuy = { }
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "binanceImg")
            
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_bnb", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_bnb", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_bnb", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_bnb", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
        
    }
    
    func onSetKavaItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_KAVA
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletKavaCell? = tableView.dequeueReusableCell(withIdentifier:"WalletKavaCell") as? WalletKavaCell
            let totalKava = WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.cardKava.backgroundColor = WUtils.getChainBg(chainType!)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.totalAmount.font!, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalKava, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(mainTabVC.mBalances, cell!.availableAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
            cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(mainTabVC.mBondingList, mainTabVC.mAllValidator, cell!.delegatedAmount.font, 6, chainType!)
            cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(mainTabVC.mUnbondingList, cell!.unbondingAmount.font, 6, chainType!)
            cell?.rewardAmount.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell!.rewardAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
            cell?.vestingAmount.attributedText = WUtils.dpVestingCoin(mainTabVC.mBalances, cell!.vestingAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onClickVoteList()
            }
            cell?.cdpBtn.isHidden = false
            cell?.nonCdpConstarint.priority = .defaultLow
            cell?.cdpConstraint.priority = .defaultHigh
            cell?.actionCdp = {
                self.onClickCdp()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalKava.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletVestingDetailCell? = tableView.dequeueReusableCell(withIdentifier:"WalletVestingDetailCell") as? WalletVestingDetailCell
//            let mKavaAccount = BaseData.instance.mKavaAccountResult
//            cell?.rootCardView.backgroundColor = TRANS_BG_COLOR_KAVA
//            cell?.vestingCntLabel.text = "(" + String(mKavaAccount.getCVestingCnt()) + ")"
//            cell?.vestingTotalAmount.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingSum().stringValue, cell!.vestingTotalAmount.font!, 6, 6)
//            if (mKavaAccount.getCVestingCnt() > 0) {
//                cell?.vestingTime0.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(0))
//                cell?.vestingGap0.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(0))
//                cell?.vestingAmount0.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(0).stringValue, cell!.vestingAmount0.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 1) {
//                cell?.vestingLayer1.isHidden = false
//                cell?.vestingTime1.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(1))
//                cell?.vestingGap1.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(1))
//                cell?.vestingAmount1.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(1).stringValue, cell!.vestingAmount1.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 2) {
//                cell?.vestingLayer2.isHidden = false
//                cell?.vestingTime2.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(2))
//                cell?.vestingGap2.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(2))
//                cell?.vestingAmount2.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(2).stringValue, cell!.vestingAmount2.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 3) {
//                cell?.vestingLayer3.isHidden = false
//                cell?.vestingTime3.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(3))
//                cell?.vestingGap3.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(3))
//                cell?.vestingAmount3.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(3).stringValue, cell!.vestingAmount3.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 4) {
//                cell?.vestingLayer4.isHidden = false
//                cell?.vestingTime4.text = WUtils.longTimetoString(input: mKavaAccount.getCVestingUnLockTime(4))
//                cell?.vestingGap4.text = WUtils.getUnbondingTimeleft(mKavaAccount.getCVestingUnLockTime(4))
//                cell?.vestingAmount4.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(4).stringValue, cell!.vestingAmount4.font!, 6, 6)
//            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletKavaIncentiveCell? = tableView.dequeueReusableCell(withIdentifier:"WalletKavaIncentiveCell") as? WalletKavaIncentiveCell
//            cell?.rootCard.backgroundColor = TRANS_BG_COLOR_KAVA
//            cell?.actionParticipate = {
//                self.onClickIncentive()
//            }
            return cell!
            
        } else if (indexPath.row == 4) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.text = ""
            }
            cell?.buySeparator.isHidden = false
            cell?.buyBtn.isHidden = false
            cell?.buyBtn.setTitle(NSLocalizedString("buy_kava", comment: ""), for: .normal)
            cell?.buyConstraint.priority = .defaultHigh
            cell?.noBuyConstraint.priority = .defaultLow
            cell?.actionBuy = {
                self.onClickBuyCoin()
            }
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else if (indexPath.row == 5) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "kavamainImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_kava", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_kava", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_kava", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_kava", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
    }
    
    func onSetKavaTestItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_KAVA
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletKavaCell? = tableView.dequeueReusableCell(withIdentifier:"WalletKavaCell") as? WalletKavaCell
            let totalKava = WUtils.getAllKava(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.cardKava.backgroundColor = WUtils.getChainBg(chainType!)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalKava.stringValue, cell!.totalAmount.font!, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalKava, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(mainTabVC.mBalances, cell!.availableAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
            cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(mainTabVC.mBondingList, mainTabVC.mAllValidator, cell!.delegatedAmount.font, 6, chainType!)
            cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(mainTabVC.mUnbondingList, cell!.unbondingAmount.font, 6, chainType!)
            cell?.rewardAmount.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell!.rewardAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
            cell?.vestingAmount.attributedText = WUtils.dpVestingCoin(mainTabVC.mBalances, cell!.vestingAmount.font, 6, KAVA_MAIN_DENOM, chainType!)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onClickVoteList()
            }
            cell?.cdpBtn.isHidden = false
            cell?.nonCdpConstarint.priority = .defaultLow
            cell?.cdpConstraint.priority = .defaultHigh
            cell?.actionCdp = {
                self.onClickCdp()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalKava.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletVestingDetailCell? = tableView.dequeueReusableCell(withIdentifier:"WalletVestingDetailCell") as? WalletVestingDetailCell
//            let mKavaAccount = BaseData.instance.mKavaAccountResult
//            cell?.rootCardView.backgroundColor = COLOR_BG_GRAY
//            cell?.vestingCntLabel.text = "(" + String(mKavaAccount.getCVestingCnt()) + ")"
//            cell?.vestingTotalAmount.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingSum().stringValue, cell!.vestingTotalAmount.font!, 6, 6)
//            if (mKavaAccount.getCVestingCnt() > 0) {
//                cell?.vestingTime0.text = WUtils.longTimetoString(input: mKavaAccount.getUnLockTime(0))
//                cell?.vestingGap0.text = WUtils.getUnbondingTimeleft(mKavaAccount.getUnLockTime(0))
//                cell?.vestingAmount0.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(0).stringValue, cell!.vestingAmount0.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 1) {
//                cell?.vestingLayer1.isHidden = false
//                cell?.vestingTime1.text = WUtils.longTimetoString(input: mKavaAccount.getUnLockTime(1))
//                cell?.vestingGap1.text = WUtils.getUnbondingTimeleft(mKavaAccount.getUnLockTime(1))
//                cell?.vestingAmount1.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(1).stringValue, cell!.vestingAmount1.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 2) {
//                cell?.vestingLayer2.isHidden = false
//                cell?.vestingTime2.text = WUtils.longTimetoString(input: mKavaAccount.getUnLockTime(2))
//                cell?.vestingGap2.text = WUtils.getUnbondingTimeleft(mKavaAccount.getUnLockTime(2))
//                cell?.vestingAmount2.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(2).stringValue, cell!.vestingAmount2.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 3) {
//                cell?.vestingLayer3.isHidden = false
//                cell?.vestingTime3.text = WUtils.longTimetoString(input: mKavaAccount.getUnLockTime(3))
//                cell?.vestingGap3.text = WUtils.getUnbondingTimeleft(mKavaAccount.getUnLockTime(3))
//                cell?.vestingAmount3.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(3).stringValue, cell!.vestingAmount3.font!, 6, 6)
//            }
//            if (mKavaAccount.getCVestingCnt() > 4) {
//                cell?.vestingLayer4.isHidden = false
//                cell?.vestingTime4.text = WUtils.longTimetoString(input: mKavaAccount.getUnLockTime(4))
//                cell?.vestingGap4.text = WUtils.getUnbondingTimeleft(mKavaAccount.getUnLockTime(4))
//                cell?.vestingAmount4.attributedText = WUtils.displayAmount2(mKavaAccount.getCVestingPeriodAmount(4).stringValue, cell!.vestingAmount4.font!, 6, 6)
//            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletKavaIncentiveCell? = tableView.dequeueReusableCell(withIdentifier:"WalletKavaIncentiveCell") as? WalletKavaIncentiveCell
//            cell?.rootCard.backgroundColor = COLOR_BG_GRAY
//            //TODO check show or hide btn
////            cell?.btnParticipate.isHidden = true
////            cell?.participateDone.isHidden = false
////            cell?.BtnConstraint?.isActive = false
////            cell?.LabelConstraint?.isActive = true
//
//            cell?.actionParticipate = {
//                self.onClickIncentive()
//            }
            return cell!
            
        } else if (indexPath.row == 4) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.text = ""
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh
            cell?.actionBuy = { }
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else if (indexPath.row == 5) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "kavamainImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_kava", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_kava", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_kava", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_kava", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
            
        }
        
    }
    
    func onSetIovItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_IOV
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletIovCell? = tableView.dequeueReusableCell(withIdentifier:"WalletIovCell") as? WalletIovCell
            let totalAmount = WUtils.getAllIov(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            let availableAmount = WUtils.availableAmount(mainTabVC.mBalances, IOV_MAIN_DENOM)
            let delegatedAmount = WUtils.deleagtedAmount(mainTabVC.mBondingList, mainTabVC.mAllValidator, chainType!)
            let unbondingAmount = WUtils.unbondingAmount(mainTabVC.mUnbondingList, chainType!)
            let rewardAmount = WUtils.rewardAmount(mainTabVC.mRewardList, IOV_MAIN_DENOM, chainType!)

            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 6, 6)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 6, 6)
            cell?.delegatedAmount.attributedText = WUtils.displayAmount2(delegatedAmount.stringValue, cell!.delegatedAmount.font, 6, 6)
            cell?.unbondingAmount.attributedText = WUtils.displayAmount2(unbondingAmount.stringValue, cell!.unbondingAmount.font, 6, 6)
            cell?.rewardAmount.attributedText = WUtils.displayAmount2(rewardAmount.stringValue, cell!.rewardAmount.font, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onShowToast(NSLocalizedString("error_not_yet", comment: ""))
            }
            cell?.actionNameService = {
                self.onClickIovNameservice()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalAmount.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(NSDecimalNumber.zero, font: cell!.updownPercent.font)
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "iovImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_iov", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_iov", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_iov", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_iov", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
    }
    
    func onSetBandItem(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_BAND
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletBandCell? = tableView.dequeueReusableCell(withIdentifier:"WalletBandCell") as? WalletBandCell
            let totalAmount = WUtils.getAllBand(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            let availableAmount = WUtils.availableAmount(mainTabVC.mBalances, BAND_MAIN_DENOM)   //mainTabVC.mAccount.getBandBalance()
            let delegatedAmount = WUtils.deleagtedAmount(mainTabVC.mBondingList, mainTabVC.mAllValidator, chainType!)
            let unbondingAmount = WUtils.unbondingAmount(mainTabVC.mUnbondingList, chainType!)
            let rewardAmount = WUtils.rewardAmount(mainTabVC.mRewardList, BAND_MAIN_DENOM, chainType!)
            
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 6, 6)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 6, 6)
            cell?.delegatedAmount.attributedText = WUtils.displayAmount2(delegatedAmount.stringValue, cell!.delegatedAmount.font, 6, 6)
            cell?.unbondingAmount.attributedText = WUtils.displayAmount2(unbondingAmount.stringValue, cell!.unbondingAmount.font, 6, 6)
            cell?.rewardAmount.attributedText = WUtils.displayAmount2(rewardAmount.stringValue, cell!.rewardAmount.font, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalAmount.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.text = ""
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh

//            cell?.buySeparator.isHidden = false
//            cell?.buyBtn.isHidden = false
//            cell?.buyBtn.setTitle(NSLocalizedString("buy_band", comment: ""), for: .normal)
//            cell?.buyConstraint.priority = .defaultHigh
//            cell?.noBuyConstraint.priority = .defaultLow
//            cell?.actionTapPricel = {
//                self.onClickMarketInfo()
//            }
//            cell?.actionBuy = {
//                self.onClickBuyCoin()
//            }
            
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "bandprotocolImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_band", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_band", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_band", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_band", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
    }
    
    func onSetIovTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_IOV
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletIovCell? = tableView.dequeueReusableCell(withIdentifier:"WalletIovCell") as? WalletIovCell
            cell?.rootCardView.backgroundColor = COLOR_BG_GRAY
            let totalAmount = WUtils.getAllIov(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            let availableAmount = WUtils.availableAmount(mainTabVC.mBalances, IOV_TEST_DENOM)
            let delegatedAmount = WUtils.deleagtedAmount(mainTabVC.mBondingList, mainTabVC.mAllValidator, chainType!)
            let unbondingAmount = WUtils.unbondingAmount(mainTabVC.mUnbondingList, chainType!)
            let rewardAmount = WUtils.rewardAmount(mainTabVC.mRewardList, IOV_TEST_DENOM, chainType!)

            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 6, 6)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 6, 6)
            cell?.delegatedAmount.attributedText = WUtils.displayAmount2(delegatedAmount.stringValue, cell!.delegatedAmount.font, 6, 6)
            cell?.unbondingAmount.attributedText = WUtils.displayAmount2(unbondingAmount.stringValue, cell!.unbondingAmount.font, 6, 6)
            cell?.rewardAmount.attributedText = WUtils.displayAmount2(rewardAmount.stringValue, cell!.rewardAmount.font, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onShowToast(NSLocalizedString("error_not_yet", comment: ""))
            }
            cell?.actionNameService = {
                self.onClickIovNameservice()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalAmount.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(NSDecimalNumber.zero, font: cell!.updownPercent.font)
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "iovImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_iov", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_iov", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_iov", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_iov", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
    }
    
    func onSetOkTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_OK
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletOkCell? = tableView.dequeueReusableCell(withIdentifier:"WalletOkCell") as? WalletOkCell
            cell?.rootCardView.backgroundColor = COLOR_BG_GRAY
            let totalAmount = WUtils.getAllOkt(mainTabVC.mBalances, BaseData.instance.mOkDeposit, BaseData.instance.mOkWithdraw)
            let availableAmount = WUtils.availableAmount(mainTabVC.mBalances, OKEX_TEST_DENOM)
            let lockedAmount = WUtils.lockedAmount(mainTabVC.mBalances, OKEX_TEST_DENOM)
            let depositAmount = WUtils.okDepositAmount(BaseData.instance.mOkDeposit)
            let withdrawAmount = WUtils.okWithdrawAmount(BaseData.instance.mOkWithdraw)
            
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalAmount.stringValue, cell!.totalAmount.font, 0, 8)
            cell?.availableAmount.attributedText = WUtils.displayAmount2(availableAmount.stringValue, cell!.availableAmount.font, 0, 8)
            cell?.lockedAmount.attributedText = WUtils.displayAmount2(lockedAmount.stringValue, cell!.lockedAmount.font, 0, 8)
            cell?.depositAmount.attributedText = WUtils.displayAmount2(depositAmount.stringValue, cell!.depositAmount.font, 0, 8)
            cell?.withdrawAmount.attributedText = WUtils.displayAmount2(withdrawAmount.stringValue, cell!.withdrawAmount.font, 0, 8)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalAmount, BaseData.instance.getLastPrice(), 0, cell!.totalValue.font)
            
            cell?.actionDeposit = {
                self.onClickOkDeposit()
            }
            cell?.actionWithdraw = {
                self.onClickOkWithdraw()
            }
            cell?.actionVote = {
                self.onClickOkVote()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalAmount.stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(NSDecimalNumber.zero, font: cell!.updownPercent.font)
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "okexImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_ok", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_ok", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_ok", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_ok", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
        }
    }
    
    func onSetCertikTestItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:WalletAddressCell? = tableView.dequeueReusableCell(withIdentifier:"WalletAddressCell") as? WalletAddressCell
            if (mainTabVC.mAccount.account_has_private) {
                cell?.keyState.image = cell?.keyState.image?.withRenderingMode(.alwaysTemplate)
                cell?.keyState.tintColor = COLOR_CERTIK
            }
            cell?.dpAddress.text = mainTabVC.mAccount.account_address
            cell?.dpAddress.adjustsFontSizeToFitWidth = true
            cell?.actionShare = {
                self.onClickActionShare()
            }
            cell?.actionWebLink = {
                self.onClickActionLink()
            }
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:WalletCertikCell? = tableView.dequeueReusableCell(withIdentifier:"WalletCertikCell") as? WalletCertikCell
            cell?.rootCardView.backgroundColor = COLOR_BG_GRAY
            let totalCtk = WUtils.getAllCertik(mainTabVC.mBalances, mainTabVC.mBondingList, mainTabVC.mUnbondingList, mainTabVC.mRewardList, mainTabVC.mAllValidator)
            cell?.totalAmount.attributedText = WUtils.displayAmount2(totalCtk.stringValue, cell!.totalAmount.font!, 6, 6)
            cell?.totalValue.attributedText = WUtils.dpTokenValue(totalCtk, BaseData.instance.getLastPrice(), 6, cell!.totalValue.font)
            cell?.availableAmount.attributedText = WUtils.dpTokenAvailable(mainTabVC.mBalances, cell!.availableAmount.font, 6, CERTIK_TEST_DENOM, chainType!)
            cell?.delegatedAmount.attributedText = WUtils.dpDeleagted(mainTabVC.mBondingList, mainTabVC.mAllValidator, cell!.delegatedAmount.font, 6, chainType!)
            cell?.unbondingAmount.attributedText = WUtils.dpUnbondings(mainTabVC.mUnbondingList, cell!.unbondingAmount.font, 6, chainType!)
            cell?.rewardAmount.attributedText = WUtils.dpRewards(mainTabVC.mRewardList, cell!.rewardAmount.font, 6, CERTIK_TEST_DENOM, chainType!)
            cell?.actionDelegate = {
                self.onClickValidatorList()
            }
            cell?.actionVote = {
                self.onClickVoteList()
            }
            BaseData.instance.updateLastTotal(mainTabVC!.mAccount, totalCtk.multiplying(byPowerOf10: -6).stringValue)
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:WalletPriceCell? = tableView.dequeueReusableCell(withIdentifier:"WalletPriceCell") as? WalletPriceCell
            cell?.sourceSite.text = "("+BaseData.instance.getMarketString()+")"
            cell?.perPrice.attributedText = WUtils.dpPricePerUnit(BaseData.instance.getLastPrice(), cell!.perPrice.font)
            let changeValue = WUtils.priceChanges(BaseData.instance.get24hPrice())
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                cell?.updownImg.image = UIImage(named: "priceUp")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                cell?.updownImg.image = UIImage(named: "priceDown")
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(changeValue, font: cell!.updownPercent.font)
            } else {
                cell?.updownImg.image = nil
                cell?.updownPercent.attributedText = WUtils.displayPriceUPdown(NSDecimalNumber.zero, font: cell!.updownPercent.font)
            }
            cell?.buySeparator.isHidden = true
            cell?.buyBtn.isHidden = true
            cell?.buyConstraint.priority = .defaultLow
            cell?.noBuyConstraint.priority = .defaultHigh
            cell?.actionTapPricel = {
                self.onClickMarketInfo()
            }
            return cell!
            
        } else if (indexPath.row == 3) {
            let cell:WalletInflationCell? = tableView.dequeueReusableCell(withIdentifier:"WalletInflationCell") as? WalletInflationCell
            if (self.mInflation != nil) {
                cell?.infaltionLabel.attributedText = WUtils.displayInflation(NSDecimalNumber.init(string: self.mInflation), font: cell!.infaltionLabel.font)
            }
            if (self.mStakingPool != nil && self.mProvision != nil) {
                cell?.yieldLabel.attributedText = WUtils.displayYield(NSDecimalNumber.init(string: self.mStakingPool?.object(forKey: "bonded_tokens") as? String), NSDecimalNumber.init(string: self.mProvision), NSDecimalNumber.zero, font: cell!.yieldLabel.font)
            }
            return cell!
            
        } else {
            let cell:WalletGuideCell? = tableView.dequeueReusableCell(withIdentifier:"WalletGuideCell") as? WalletGuideCell
            cell?.guideImg.image = UIImage(named: "certikImg")
            cell?.guideTitle.text = NSLocalizedString("send_guide_title_certik", comment: "")
            cell?.guideMsg.text = NSLocalizedString("send_guide_msg_certik", comment: "")
            cell?.btn1Label.setTitle(NSLocalizedString("send_guide_btn1_certik", comment: ""), for: .normal)
            cell?.btn2Label.setTitle(NSLocalizedString("send_guide_btn2_certik", comment: ""), for: .normal)
            cell?.actionGuide1 = {
                self.onClickGuide1()
            }
            cell?.actionGuide2 = {
                self.onClickGuide2()
            }
            return cell!
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
    
    
    func onClickActionShare() {
        var nickName:String?
        if (mainTabVC.mAccount.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else {
            nickName = mainTabVC.mAccount.account_nick_name
        }
        self.shareAddress(mainTabVC.mAccount.account_address, nickName!)
    }
    
    func onClickActionLink() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            guard let url = URL(string: EXPLORER_COSMOS_MAIN + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            guard let url = URL(string: EXPLORER_IRIS_MAIN + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: EXPLORER_BINANCE_MAIN + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            guard let url = URL(string: EXPLORER_IOV_MAIN + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_MAIN) {
            guard let url = URL(string: EXPLORER_KAVA_MAIN + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: EXPLORER_BAND_MAIN + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_TEST) {
            guard let url = URL(string: EXPLORER_BINANCE_TEST + "address/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: EXPLORER_OKEX_TEST + "address/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: EXPLORER_KAVA_TEST + "account/" + mainTabVC.mAccount.account_address) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: EXPLORER_CERTIK_TEST + "accounts/" + mainTabVC.mAccount.account_address + "?net=" + WUtils.getChainId(chainType!)) else { return }
            self.onShowSafariWeb(url)
        }
    }
    
    func onClickValidatorList() {
        let validatorListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "ValidatorListViewController") as! ValidatorListViewController
        validatorListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(validatorListVC, animated: true)
        
    }
    
    func onClickVoteList() {
        if (chainType! == ChainType.KAVA_TEST) {
            return
        }
        let voteListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteListViewController") as! VoteListViewController
        voteListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(voteListVC, animated: true)
        
    }
    
    func onClickWalletConect() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        self.onStartQrCode()
    }
    
    func onClickBep3Send(_ denom: String) {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }

        let balances = BaseData.instance.selectBalanceById(accountId: self.mainTabVC.mAccount.account_id)
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: GAS_FEE_BNB_TRANSFER)).rawValue <= 0) {
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
    
    func onClickIovNameservice() {
        self.onShowToast(NSLocalizedString("error_not_yet", comment: ""))
    }
    
    func onClickCdp() {
        if (BaseData.instance.mCdpParam == nil) {
            return
        }
        let cdpListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "CdpListViewController") as! CdpListViewController
        cdpListVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(cdpListVC, animated: true)
    }
    
    func onClickIncentive() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_INCENTIVE_REWARD
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickOkDeposit() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(mainTabVC.mBalances, OKEX_TEST_DENOM).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_to_deposit", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OK_MSG_TYPE_DEPOSIT
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickOkWithdraw() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(mainTabVC.mBalances, OKEX_TEST_DENOM).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        if (WUtils.okDepositAmount(BaseData.instance.mOkDeposit).compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_to_withdraw", comment: ""))
            return
            
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = OK_MSG_TYPE_WITHDRAW
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    func onClickOkVote() {
        let okVoteTypeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        okVoteTypeAlert.addAction(UIAlertAction(title: NSLocalizedString("str_vote_direct", comment: ""), style: .default, handler: { _ in
            let okValidatorListVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "OkValidatorListViewController") as! OkValidatorListViewController
            okValidatorListVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(okValidatorListVC, animated: true)
        }))
        okVoteTypeAlert.addAction(UIAlertAction(title: NSLocalizedString("str_vote_agent", comment: ""), style: .default, handler: { _ in
            self.onShowToast(NSLocalizedString("prepare", comment: ""))
        }))
        self.present(okVoteTypeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            okVoteTypeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
        
    }
    
    func onClickGuide1() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            if(Locale.current.languageCode == "ko") {
                guard let url = URL(string: "https://www.cosmostation.io/files/guide_KO.pdf") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://www.cosmostation.io/files/guide_EN.pdf") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            guard let url = URL(string: "https://www.irisnet.org") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: "https://www.binance.org") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: "https://www.kava.io/registration/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: "https://bandprotocol.com/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            guard let url = URL(string: "https://iov.one/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: "https://www.okex.com/") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: "https://www.certik.foundation/") else { return }
            self.onShowSafariWeb(url)
        }
        
    }
    
    func onClickGuide2() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            if(Locale.current.languageCode == "ko") {
                guard let url = URL(string: "https://guide.cosmostation.io/app_wallet_ko.html") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://guide.cosmostation.io/app_wallet_en.html") else { return }
                self.onShowSafariWeb(url)
            }

        } else if (chainType! == ChainType.IRIS_MAIN) {
            guard let url = URL(string: "https://medium.com/irisnet-blog") else { return }
            self.onShowSafariWeb(url)

        } else if (chainType! == ChainType.BINANCE_MAIN) {
            guard let url = URL(string: "https://medium.com/@binance") else { return }
            self.onShowSafariWeb(url)

        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            guard let url = URL(string: "https://medium.com/kava-labs") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: "https://medium.com/bandprotocol") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            guard let url = URL(string: "https://medium.com/iov-internet-of-values") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            guard let url = URL(string: "https://www.okex.com/community") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.CERTIK_TEST) {
            guard let url = URL(string: "https://www.certik.foundation/blog") else { return }
            self.onShowSafariWeb(url)
        }
    }
    
    func onClickMarketInfo() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/cosmos") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/cosmos/") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.IRIS_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/irisnet") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/irisnet") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/binancecoin") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/binance-coin") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                guard let url = URL(string: "https://www.coingecko.com/en/coins/kava") else { return }
                self.onShowSafariWeb(url)
            } else {
                guard let url = URL(string: "https://coinmarketcap.com/currencies/kava") else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/band-protocol") else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType! == ChainType.IOV_MAIN || chainType! == ChainType.IOV_TEST) {
            guard let url = URL(string: "https://www.coingecko.com/en/coins/starname") else { return }
            self.onShowSafariWeb(url)
        }
    }
    
    func onClickBuyCoin() {
        if (mainTabVC.mAccount.account_has_private) {
            self.onShowBuySelectFiat()
        } else {
            self.onShowBuyWarnNoKey()
        }
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
            query = query + "&currencyCode=atom"
        } else if (chainType! == ChainType.BINANCE_MAIN) {
            query = query + "&currencyCode=bnb"
        } else if (chainType! == ChainType.KAVA_MAIN) {
            query = query + "&currencyCode=kava"
        } else if (chainType! == ChainType.BAND_MAIN) {
            query = query + "&currencyCode=band"
        }
        query = query + "&walletAddress=" + mainTabVC.mAccount.account_address + "&baseCurrencyCode=" + fiat;
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
    
    func onClickMainSend() {
        if (!mainTabVC.mAccount.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        let balances = BaseData.instance.selectBalanceById(accountId: self.mainTabVC.mAccount.account_id)
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
            txVC.mIrisToken = WUtils.getIrisMainToken(self.mainTabVC.mIrisTokenList)
            txVC.mType = IRIS_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (WUtils.getTokenAmount(balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: "0.000375")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mBnbToken = WUtils.getBnbMainToken(BaseData.instance.mBnbTokenList)
            txVC.mType = BNB_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            if (WUtils.getTokenAmount(balances, KAVA_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mKavaSendDenom = KAVA_MAIN_DENOM
            txVC.mType = KAVA_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "1000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mIovSendDenom = IOV_MAIN_DENOM
            txVC.mType = IOV_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.BAND_MAIN) {
            if (WUtils.getTokenAmount(balances, BAND_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mType = BAND_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "1000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mIovSendDenom = IOV_TEST_DENOM
            txVC.mType = IOV_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.OKEX_TEST) {
            if (WUtils.getTokenAmount(balances, OKEX_TEST_DENOM).compare(NSDecimalNumber.init(string: "0.02")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mOkSendDenom = OKEX_TEST_DENOM
            txVC.mType = OK_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.CERTIK_TEST) {
            if (WUtils.getTokenAmount(balances, CERTIK_TEST_DENOM).compare(NSDecimalNumber.init(string: "10000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mCertikSendDenom = CERTIK_TEST_DENOM
            txVC.mType = CERTIK_MSG_TYPE_TRANSFER
        } else {
            return
        }
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onStartQrCode() {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        self.navigationController?.pushViewController(qrScanVC, animated: false)
    }
    
    
    func scannedAddress(result: String) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
            if (result.contains("wallet-bridge.binance.org")) {
                self.wcURL = result
                let wcAlert = UIAlertController(title: NSLocalizedString("wc_alert_title", comment: ""), message: NSLocalizedString("wc_alert_msg", comment: ""), preferredStyle: .alert)
                wcAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .destructive, handler: nil))
                wcAlert.addAction(UIAlertAction(title: NSLocalizedString("ok", comment: ""), style: .default, handler: { _ in
                    let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
                    self.navigationItem.title = ""
                    self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
                    passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
                    passwordVC.resultDelegate = self
                    passwordVC.hidesBottomBarWhenPushed = true
                    self.navigationController?.pushViewController(passwordVC, animated: false)
                }))
                self.present(wcAlert, animated: true) {
                    let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                    wcAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
                }
            }
        })
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
                let wcVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "WalletConnectViewController") as! WalletConnectViewController
                wcVC.hidesBottomBarWhenPushed = true
                wcVC.wcURL = self.wcURL!
                self.navigationItem.title = ""
                self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false;
                self.navigationController?.pushViewController(wcVC, animated: true)
            })
        }
    }
}
