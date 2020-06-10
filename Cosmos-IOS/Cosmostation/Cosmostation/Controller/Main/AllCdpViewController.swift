//
//  AllCdpViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class AllCdpViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var allCdpCntLabel: UILabel!
    @IBOutlet weak var allCdpTableView: UITableView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    var mAllCdp: Array<CdpParam.CollateralParam> = Array<CdpParam.CollateralParam>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.allCdpTableView.delegate = self
        self.allCdpTableView.dataSource = self
        self.allCdpTableView.register(UINib(nibName: "CdpListAllCell", bundle: nil), forCellReuseIdentifier: "CdpListAllCell")
        self.allCdpTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.allCdpTableView.rowHeight = UITableView.automaticDimension
        self.allCdpTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.allCdpTableView.addSubview(refresher)
        
        mAllCdp = BaseData.instance.mCdpParam.result.collateral_params
        allCdpCntLabel.text = String(mAllCdp.count)
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
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        mAllCdp = BaseData.instance.mCdpParam.result.collateral_params
        allCdpCntLabel.text = String(mAllCdp.count)
        self.allCdpTableView.reloadData()
        self.refresher.endRefreshing()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mAllCdp.count
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpListAllCell? = tableView.dequeueReusableCell(withIdentifier:"CdpListAllCell") as? CdpListAllCell
        let cParam = mAllCdp[indexPath.row]
        cell?.marketTitle.text = cParam.getDpMarketId()
        cell?.minCollateralRate.attributedText = WUtils.displayPercent(cParam.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
        cell?.stabilityFee.attributedText = WUtils.displayPercent(cParam.getDpStabilityFee(), font: cell!.stabilityFee.font)
        cell?.liquidationPenalty.attributedText = WUtils.displayPercent(cParam.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
        let url = KAVA_CDP_MARKET_IMG_URL + cParam.getMarketImgPath() + ".png"
        Alamofire.request(url, method: .get).responseImage { response  in
            guard let image = response.result.value else {
                return
            }
            cell?.marketImg.image = image
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cParam = mAllCdp[indexPath.row]
        let cdpDetailVC = CdpDetailViewController(nibName: "CdpDetailViewController", bundle: nil)
        cdpDetailVC.hidesBottomBarWhenPushed = true
        cdpDetailVC.cDenom = cParam.denom
        cdpDetailVC.mMarketID = cParam.spot_market_id
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(cdpDetailVC, animated: true)
    }
}
