//
//  MyCdpViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MyCdpViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var myCdpCntLabel: UILabel!
    @IBOutlet weak var myCdpTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    var mCdpParam: KavaCdpParam = KavaCdpParam.init()
    var mMyCdps: Array<CdpOwen> = Array<CdpOwen>()
    var mKavaPrice = [String:KavaPriceFeedPrice]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.myCdpTableView.delegate = self
        self.myCdpTableView.dataSource = self
        self.myCdpTableView.register(UINib(nibName: "CdpListPromotionCell", bundle: nil), forCellReuseIdentifier: "CdpListPromotionCell")
        self.myCdpTableView.register(UINib(nibName: "CdpLisyMyCell", bundle: nil), forCellReuseIdentifier: "CdpLisyMyCell")
        self.myCdpTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myCdpTableView.rowHeight = UITableView.automaticDimension
        self.myCdpTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.myCdpTableView.addSubview(refresher)
        
        mCdpParam = BaseData.instance.mCdpParam!
        mMyCdps = BaseData.instance.mMyCdps
        sortByCdpId()
        mKavaPrice = BaseData.instance.mKavaPrice
        
        myCdpCntLabel.text = String(mMyCdps.count)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onPriceFetchDone(_:)), name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onPriceFetchDone"), object: nil)
    }
    
    @objc func onRequestFetch() {
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onPriceFetchDone(_ notification: NSNotification) {
        print("onPriceFetchDone")
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        mCdpParam = BaseData.instance.mCdpParam!
        mMyCdps = BaseData.instance.mMyCdps
        mKavaPrice = BaseData.instance.mKavaPrice
        myCdpCntLabel.text = String(mMyCdps.count)
        sortByCdpId()
        self.myCdpTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mMyCdps.count < 1) {
            return 1
        } else {
            return mMyCdps.count
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (mMyCdps.count < 1) {
            let cell:CdpListPromotionCell? = tableView.dequeueReusableCell(withIdentifier:"CdpListPromotionCell") as? CdpListPromotionCell
            return cell!
            
        } else {
            let mMyCdpStatus = mMyCdps[indexPath.row]
            let mCollateralParam = mCdpParam.result.getcParam(mMyCdpStatus.getcDenom())
            let mCDenom = mMyCdpStatus.getcDenom()
            let mPDenom = mMyCdpStatus.getpDenom()
            let mPrice = mKavaPrice[mCollateralParam!.liquidation_market_id]

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

            let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam!.getMarketImgPath() + ".png"
            cell?.marketImg.af_setImage(withURL: URL(string: url)!)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (mMyCdps.count < 1) {
            return
        } else {
            let mMyCdpStatus = mMyCdps[indexPath.row]
            let mCollateralParam = mCdpParam.result.getcParam(mMyCdpStatus.getcDenom())
            let cdpDetailVC = CdpDetailViewController(nibName: "CdpDetailViewController", bundle: nil)
            cdpDetailVC.hidesBottomBarWhenPushed = true
            cdpDetailVC.mCDenom = mCollateralParam!.denom
            cdpDetailVC.mMarketID = mCollateralParam!.liquidation_market_id
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(cdpDetailVC, animated: true)
        }
    }
    
    func sortByCdpId() {
        mMyCdps.sort {
            return $0.result.cdp.getCdpId() > $1.result.cdp.getCdpId()
        }
    }
}
