//
//  KavaPoolViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/27.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class PoolListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var swapPoolTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mSwapParam: SwapParam?
    var mSwapPools: Array<SwapPool> = Array<SwapPool>()
    var mMySwapPoolDeposits: Array<SwapDeposit> = Array<SwapDeposit>()
    var mMySwapPools: Array<SwapPool> = Array<SwapPool>()
    var mOtherSwapPools: Array<SwapPool> = Array<SwapPool>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.swapPoolTableView.delegate = self
        self.swapPoolTableView.dataSource = self
        self.swapPoolTableView.register(UINib(nibName: "SwapPoolListMyCell", bundle: nil), forCellReuseIdentifier: "SwapPoolListMyCell")
        self.swapPoolTableView.register(UINib(nibName: "SwapPoolListOtherCell", bundle: nil), forCellReuseIdentifier: "SwapPoolListOtherCell")
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchSwapPoolData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.swapPoolTableView.addSubview(refresher)
        
        self.onFetchSwapPoolData()
    }
    
    var mFetchCnt = 0
    @objc func onFetchSwapPoolData() {
        if (self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mSwapPools.removeAll()
        self.mMySwapPools.removeAll()
        self.mOtherSwapPools.removeAll()
        self.mFetchCnt = 3
        
        self.onFetchSwapPoolParam()
        self.onFetchSwapPoolList()
        self.onFetchSwapPoolDeposit(account!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            mSwapPools.forEach { pool in
                var myPool = false
                if (mMySwapPoolDeposits.filter { $0.pool_id == pool.name }.first != nil) {
                    myPool = true
                }
                
                if (myPool) { mMySwapPools.append(pool) }
                else { mOtherSwapPools.append(pool) }
            }
            self.swapPoolTableView.reloadData()
            self.refresher.endRefreshing()
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return mMySwapPools.count
        } else {
            return mOtherSwapPools.count
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"SwapPoolListMyCell") as? SwapPoolListMyCell
            let pool = mMySwapPools[indexPath.row]
            cell?.onBindView(pool, mMySwapPoolDeposits)
            return cell!
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"SwapPoolListOtherCell") as? SwapPoolListOtherCell
            let pool = mOtherSwapPools[indexPath.row]
            cell?.onBindView(pool)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 0) {
            let noticeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
            noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("title_pool_join", comment: ""), style: .default, handler: { _ in
                self.onCheckPoolJoin(self.mMySwapPools[indexPath.row])
            }))
            noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("title_pool_exit", comment: ""), style: .default, handler: { _ in
                self.onCheckExitJoin(self.mMySwapPools[indexPath.row])
            }))
            self.present(noticeAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
            
        } else {
            self.onCheckPoolJoin(self.mOtherSwapPools[indexPath.row])
        }
    }
    
    func onCheckPoolJoin(_ pool: SwapPool) {
        print("onCheckPoolJoin ", pool.name)
        
    }
    
    func onCheckExitJoin(_ pool: SwapPool) {
        print("onCheckExitJoin ", pool.name)
    }
    
    
    
    func onFetchSwapPoolParam() {
        let request = Alamofire.request(BaseNetWork.paramSwapPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSwapPoolParam ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                self.mSwapParam = KavaSwapParam.init(responseData).result
            
                var market_ids = Array<String>()
                BaseData.instance.mKavaPriceMarkets.forEach { priceMarket in
                    if (!priceMarket.market_id.contains(":30")) {
                        market_ids.append(priceMarket.market_id)
                    }
                }
                self.mFetchCnt = self.mFetchCnt + market_ids.count
                market_ids.forEach { market_id in
                    self.onFetchPriceFeedPrice(market_id)
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSwapPoolParam ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchPriceFeedPrice(_ market: String) {
        let request = Alamofire.request(BaseNetWork.priceFeedUrl(chainType, market), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchPriceFeedPrice ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let priceParam = KavaPriceFeedPrice.init(responseData)
                BaseData.instance.mKavaPrice[priceParam.result.market_id] = priceParam
                
            case .failure(let error):
                print("onFetchKavaPrice ", market , " ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSwapPoolList() {
        let request = Alamofire.request(BaseNetWork.listSwapPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSwapPoolList ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String  else {
                    self.onFetchFinished()
                    return
                }
                self.mSwapPools = KavaSwapPool.init(responseData).result
//                print("mSwapPools ", self.mSwapPools.count)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSwapPoolParam ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSwapPoolDeposit(_ address: String) {
        let request = Alamofire.request(BaseNetWork.depositSwapPoolUrl(chainType), method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSwapPoolDeposit ", res)
                guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String  else {
                    self.onFetchFinished()
                    return
                }
                self.mMySwapPoolDeposits = KavaSwapDeposit.init(responseData).result
//                print("mMySwapPoolDeposits ", self.mMySwapPoolDeposits.count)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSwapPoolDeposit ", error) }
            }
            self.onFetchFinished()
        }
    }

}
