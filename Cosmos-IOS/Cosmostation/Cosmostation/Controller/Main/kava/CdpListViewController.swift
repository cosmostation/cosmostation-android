//
//  CdpListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class CdpListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var cdpTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var cdpParam: CdpParam?
    var myCdps: Array<MyCdp>?
    var otherCdps: Array<CollateralParam>?
    var incentiveRewards : IncentiveReward?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.cdpTableView.delegate = self
        self.cdpTableView.dataSource = self
        self.cdpTableView.register(UINib(nibName: "CdpIncentiveCell", bundle: nil), forCellReuseIdentifier: "CdpIncentiveCell")
        self.cdpTableView.register(UINib(nibName: "CdpListAllCell", bundle: nil), forCellReuseIdentifier: "CdpListAllCell")
        self.cdpTableView.register(UINib(nibName: "CdpLisyMyCell", bundle: nil), forCellReuseIdentifier: "CdpLisyMyCell")
        self.cdpTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.cdpTableView.rowHeight = UITableView.automaticDimension
        self.cdpTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchCdpData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.cdpTableView.addSubview(refresher)
        
        self.onFetchCdpData()
    }
    
    var mFetchCnt = 0
    @objc func onFetchCdpData() {
        if (self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 3
        self.myCdps?.removeAll()
        self.otherCdps?.removeAll()
        
        self.onFetchCdpParam()
        self.onFetchIncentiveReward(account!.account_address)
        self.onFetchOwenCdp(account!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.cdpParam = BaseData.instance.mCdpParam
            self.incentiveRewards = BaseData.instance.mIncentiveRewards
            
            otherCdps = Array<CollateralParam>()
            if let collateralparams = cdpParam?.collateral_params  {
                for collateralparam in collateralparams {
                    var has = false
                    if let mycdps = myCdps {
                        for mycdp in mycdps {
                            if (mycdp.cdp?.type == collateralparam.type) {
                                has = true
                            }
                        }
                    }
                    if (!has) {
                        self.otherCdps!.append(collateralparam)
                    }
                }
            }
//            print("myCdps ", myCdps?.count)
//            print("otherCdps ", otherCdps?.count)
            self.cdpTableView.reloadData()
            self.refresher.endRefreshing()
        }
        
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            if let rewardAmount = incentiveRewards?.getMintingRewardAmount(), rewardAmount.compare(NSDecimalNumber.zero).rawValue > 0 {
                return 1
            }
            return 0
            
        } else if (section == 1) {
            return myCdps?.count ?? 0
            
        } else {
            return otherCdps?.count ?? 0
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
        var incentiveAmount = NSDecimalNumber.zero
        if let incentiveSum = incentiveRewards?.getMintingRewardAmount() {
            incentiveAmount = incentiveSum
        }
        cell?.incentiveSumAmount.attributedText = WUtils.displayAmount2(incentiveAmount.stringValue, cell!.incentiveSumAmount.font, 6, 6)
        cell?.actionClaim = {
            self.onMintingIncentiveClaim()
        }
        return cell!
    }
    
    func onBindMyCdp(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:CdpLisyMyCell? = tableView.dequeueReusableCell(withIdentifier:"CdpLisyMyCell") as? CdpLisyMyCell
        
        let myCdp = myCdps![position]
        let mCollateralParam = cdpParam!.getcParamByType(myCdp.cdp!.type!)
        let mCDenom = myCdp.cdp!.getcDenom()
        let mPDenom = myCdp.cdp!.getpDenom()
        let mPrice = BaseData.instance.mKavaPrice[mCollateralParam!.liquidation_market_id!]

        if (SHOW_LOG) {
            print("getEstimatedTotalDebt ", myCdp.cdp!.getEstimatedTotalDebt(mCollateralParam!))
        }

        let currentPrice = NSDecimalNumber.init(string: mPrice?.result.price)
        let liquidationPrice = myCdp.getLiquidationPrice(mCDenom, mPDenom, mCollateralParam!)
        let riskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(liquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))

        if (SHOW_LOG) {
            print("currentPrice ", currentPrice)
            print("liquidationPrice ", liquidationPrice)
            print("riskRate ", riskRate)
        }

        cell?.marketType.text = mCollateralParam!.type!.uppercased()
        cell?.marketTitle.text = mCollateralParam!.getDpMarketId()
        WUtils.showRiskRate(riskRate, cell!.riskScore, _rateIamg: cell!.riskRateImg)

        cell?.debtValueTitle.text = String(format: NSLocalizedString("debt_value_format", comment: ""), mPDenom.uppercased())
        cell?.debtValue.attributedText = WUtils.getDPRawDollor(myCdp.getDpEstimatedTotalDebtValue(mPDenom, mCollateralParam!).stringValue, 2, cell!.debtValue.font)

        cell?.collateralValueTitle.text = String(format: NSLocalizedString("collateral_value_format", comment: ""), mCDenom.uppercased())
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(myCdp.getDpCollateralValue(mPDenom).stringValue, 2, cell!.collateralValue.font)

        cell?.currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), mCDenom.uppercased())
        cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)

        cell?.liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), mCDenom.uppercased())
        cell?.liquidationPrice.attributedText = WUtils.getDPRawDollor(liquidationPrice.stringValue, 4, cell!.liquidationPrice.font)
        cell?.liquidationPrice.textColor = WUtils.getRiskColor(riskRate)

        let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam!.getMarketImgPath()! + ".png"
        cell?.marketImg.af_setImage(withURL: URL(string: url)!)
        
        return cell!
    }
    
    func onBindOtherCdp(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
            let cell:CdpListAllCell? = tableView.dequeueReusableCell(withIdentifier:"CdpListAllCell") as? CdpListAllCell
            let mCollateralParam = otherCdps![position]
            cell?.marketType.text = mCollateralParam.type!.uppercased()
            cell?.marketTitle.text = mCollateralParam.getDpMarketId()
            cell?.minCollateralRate.attributedText = WUtils.displayPercent(mCollateralParam.getDpLiquidationRatio(), cell!.minCollateralRate.font)
            cell?.stabilityFee.attributedText = WUtils.displayPercent(mCollateralParam.getDpStabilityFee(), cell!.stabilityFee.font)
            cell?.liquidationPenalty.attributedText = WUtils.displayPercent(mCollateralParam.getDpLiquidationPenalty(), cell!.liquidationPenalty.font)
            let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam.getMarketImgPath()! + ".png"
            cell?.marketImg.af_setImage(withURL: URL(string: url)!)
            return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        if (indexPath.section == 1) {
//            let mMyCdpStatus = myCdps[indexPath.row]
//            let mCollateralParam = cdpParam!.result.getcParamByType(mMyCdpStatus.result.cdp.type!)
//            let cdpDetailVC = CdpDetailViewController(nibName: "CdpDetailViewController", bundle: nil)
//            cdpDetailVC.hidesBottomBarWhenPushed = true
//            cdpDetailVC.mCollateralParamType = mCollateralParam!.type
//            cdpDetailVC.mCDenom = mCollateralParam!.denom
//            cdpDetailVC.mMarketID = mCollateralParam!.liquidation_market_id
//            self.navigationItem.title = ""
//            self.navigationController?.pushViewController(cdpDetailVC, animated: true)
//
//        } else if (indexPath.section == 2) {
//            let mCollateralParam = otherCdps[indexPath.row]
//            let cdpDetailVC = CdpDetailViewController(nibName: "CdpDetailViewController", bundle: nil)
//            cdpDetailVC.hidesBottomBarWhenPushed = true
//            cdpDetailVC.mCollateralParamType = mCollateralParam.type
//            cdpDetailVC.mCDenom = mCollateralParam.denom
//            cdpDetailVC.mMarketID = mCollateralParam.liquidation_market_id
//            self.navigationItem.title = ""
//            self.navigationController?.pushViewController(cdpDetailVC, animated: true)
//        }
    }
    
    func onMintingIncentiveClaim() {
        print("onMintingIncentiveClaim")
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
    }
    
    
    func onFetchCdpParam() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_PARAM
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CDP_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchCdpParam res ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                }
                let kavaCdpParam = KavaCdpParam.init(responseData)
                BaseData.instance.mCdpParam = kavaCdpParam.result
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchCdpParam ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchIncentiveReward(_ address: String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_INCENTIVE_REWARD
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_INCENTIVE_REWARD
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaIncentiveReward = KavaIncentiveReward.init(responseData)
                    BaseData.instance.mIncentiveRewards = kavaIncentiveReward.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchIncentiveReward ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchOwenCdp(_ address: String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_OWEN
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CDP_OWEN
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaMyCdps = KavaMyCdps.init(responseData)
                    self.myCdps = kavaMyCdps.result
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchOwenCdp ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    

}
