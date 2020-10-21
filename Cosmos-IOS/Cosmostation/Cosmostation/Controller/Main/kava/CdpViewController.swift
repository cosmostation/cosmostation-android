//
//  CdpListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var cdpTableView: UITableView!
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    var cdpParam: KavaCdpParam?
    var myCdps: Array<CdpOwen> = Array<CdpOwen>()
    var otherCdps: Array<KavaCdpParam.CollateralParam> = Array<KavaCdpParam.CollateralParam>()
    var incentiveClaimables = Array<KavaIncentiveReward2.IncentiveRewardClaimable>()
    var priceFeeds = [String:KavaPriceFeedPrice]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.cdpParam = BaseData.instance.mCdpParam
        self.myCdps = BaseData.instance.mMyCdps
        self.incentiveClaimables = BaseData.instance.mIncentiveClaimables
        self.priceFeeds = BaseData.instance.mKavaPrice
        self.onSortCdps()
        
        self.cdpTableView.delegate = self
        self.cdpTableView.dataSource = self
        self.cdpTableView.register(UINib(nibName: "CdpIncentiveCell", bundle: nil), forCellReuseIdentifier: "CdpIncentiveCell")
        self.cdpTableView.register(UINib(nibName: "CdpListAllCell", bundle: nil), forCellReuseIdentifier: "CdpListAllCell")
        self.cdpTableView.register(UINib(nibName: "CdpLisyMyCell", bundle: nil), forCellReuseIdentifier: "CdpLisyMyCell")
        self.cdpTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.cdpTableView.rowHeight = UITableView.automaticDimension
        self.cdpTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.cdpTableView.addSubview(refresher)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.cdpParam = BaseData.instance.mCdpParam
        self.myCdps = BaseData.instance.mMyCdps
        self.incentiveClaimables = BaseData.instance.mIncentiveClaimables
        self.priceFeeds = BaseData.instance.mKavaPrice
        self.onSortCdps()
        self.refresher.endRefreshing()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            if (incentiveClaimables.count > 0) {
                return 1
            }
            return 0
            
        } else if (section == 1) {
            return myCdps.count
            
        } else {
            return otherCdps.count
            
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            return onBindTop(tableView, indexPath.row)
            
        } else if (indexPath.section == 1) {
            return onBindMyCdp(tableView, indexPath.row)
            
        } else {
            return onBindOtherCdp(tableView, indexPath.row)
        }
    }
    
    func onBindTop(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:CdpIncentiveCell? = tableView.dequeueReusableCell(withIdentifier:"CdpIncentiveCell") as? CdpIncentiveCell
        var totalIncentive = NSDecimalNumber.zero
        for incentive in incentiveClaimables {
            if (incentive.claimable) {
                totalIncentive = totalIncentive.adding(NSDecimalNumber.init(string: incentive.claim.reward.amount))
            }
        }
        cell?.incentiveSumAmount.attributedText = WUtils.displayAmount2(totalIncentive.stringValue, cell!.incentiveSumAmount.font, 6, 6)
        return cell!
    }
    
    func onBindMyCdp(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let mMyCdpStatus = myCdps[position]
        let mCollateralParam = cdpParam!.result.getcParamByType(mMyCdpStatus.result.cdp.type!)
        let mCDenom = mMyCdpStatus.getcDenom()
        let mPDenom = mMyCdpStatus.getpDenom()
        let mPrice = priceFeeds[mCollateralParam!.liquidation_market_id]

        if (SHOW_LOG) {
            print("getEstimatedTotalDebt ", mMyCdpStatus.result.cdp.getEstimatedTotalDebt(mCollateralParam!))
        }

        let currentPrice = NSDecimalNumber.init(string: mPrice?.result.price)
        let liquidationPrice = mMyCdpStatus.result.getLiquidationPrice(mCDenom, mPDenom, mCollateralParam!)
        let riskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(liquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))

        if (SHOW_LOG) {
            print("currentPrice ", currentPrice)
            print("liquidationPrice ", liquidationPrice)
            print("riskRate ", riskRate)
        }

        let cell:CdpLisyMyCell? = tableView.dequeueReusableCell(withIdentifier:"CdpLisyMyCell") as? CdpLisyMyCell
        cell?.marketType.text = mCollateralParam!.type.uppercased()
        cell?.marketTitle.text = mCollateralParam!.getDpMarketId()
        WUtils.showRiskRate(riskRate, cell!.riskScore, _rateIamg: cell!.riskRateImg)

        cell?.debtValueTitle.text = String(format: NSLocalizedString("debt_value_format", comment: ""), mPDenom.uppercased())
        cell?.debtValue.attributedText = WUtils.getDPRawDollor(mMyCdpStatus.result.getDpEstimatedTotalDebtValue(mPDenom, mCollateralParam!).stringValue, 2, cell!.debtValue.font)

        cell?.collateralValueTitle.text = String(format: NSLocalizedString("collateral_value_format", comment: ""), mCDenom.uppercased())
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(mMyCdpStatus.result.getDpCollateralValue(mPDenom).stringValue, 2, cell!.collateralValue.font)

        cell?.currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), mCDenom.uppercased())
        cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)

        cell?.liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), mCDenom.uppercased())
        cell?.liquidationPrice.attributedText = WUtils.getDPRawDollor(liquidationPrice.stringValue, 4, cell!.liquidationPrice.font)
        cell?.liquidationPrice.textColor = WUtils.getRiskColor(riskRate)

        let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam!.getMarketImgPath() + ".png"
        cell?.marketImg.af_setImage(withURL: URL(string: url)!)
        
        return cell!
    }
    
    func onBindOtherCdp(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
            let cell:CdpListAllCell? = tableView.dequeueReusableCell(withIdentifier:"CdpListAllCell") as? CdpListAllCell
            let mCollateralParam = otherCdps[position]
            cell?.marketType.text = mCollateralParam.type.uppercased()
            cell?.marketTitle.text = mCollateralParam.getDpMarketId()
            cell?.minCollateralRate.attributedText = WUtils.displayPercent(mCollateralParam.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
            cell?.stabilityFee.attributedText = WUtils.displayPercent(mCollateralParam.getDpStabilityFee(), font: cell!.stabilityFee.font)
            cell?.liquidationPenalty.attributedText = WUtils.displayPercent(mCollateralParam.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
            let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam.getMarketImgPath() + ".png"
            cell?.marketImg.af_setImage(withURL: URL(string: url)!)
            return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 1) {
            let mMyCdpStatus = myCdps[indexPath.row]
            let mCollateralParam = cdpParam!.result.getcParamByType(mMyCdpStatus.result.cdp.type!)
            let cdpDetailVC = CdpDetailViewController(nibName: "CdpDetailViewController", bundle: nil)
            cdpDetailVC.hidesBottomBarWhenPushed = true
            cdpDetailVC.mCollateralParamType = mCollateralParam!.type
            cdpDetailVC.mCDenom = mCollateralParam!.denom
            cdpDetailVC.mMarketID = mCollateralParam!.liquidation_market_id
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(cdpDetailVC, animated: true)
            
        } else if (indexPath.section == 2) {
            let mCollateralParam = otherCdps[indexPath.row]
            let cdpDetailVC = CdpDetailViewController(nibName: "CdpDetailViewController", bundle: nil)
            cdpDetailVC.hidesBottomBarWhenPushed = true
            cdpDetailVC.mCollateralParamType = mCollateralParam.type
            cdpDetailVC.mCDenom = mCollateralParam.denom
            cdpDetailVC.mMarketID = mCollateralParam.liquidation_market_id
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(cdpDetailVC, animated: true)
        }
    }
    
    func onSortCdps() {
        self.otherCdps.removeAll()
        if let collateralparams = cdpParam?.result.collateral_params  {
            for collateralparam in collateralparams {
                var has = false
                for myCdp in myCdps {
                    if (myCdp.result.cdp.type == collateralparam.type) {
                        has = true
                    }
                }
                if (!has) {
                    self.otherCdps.append(collateralparam)
                }
            }
        }
    }

}
