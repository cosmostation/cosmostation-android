//
//  HarvestListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class HardListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var hardTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var hardParam: HardParam?
    var interestRates: Array<HardInterestRate>?
    var totalDeposit: Array<Coin>?
    var totalBorrow: Array<Coin>?
    var myDeposit: Array<HardMyDeposit>?
    var myBorrow: Array<HardMyBorrow>?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.hardTableView.delegate = self
        self.hardTableView.dataSource = self
        self.hardTableView.register(UINib(nibName: "HardListMyStatusCell", bundle: nil), forCellReuseIdentifier: "HardListMyStatusCell")
        self.hardTableView.register(UINib(nibName: "HardListCell", bundle: nil), forCellReuseIdentifier: "HardListCell")
        
        self.hardTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.hardTableView.rowHeight = UITableView.automaticDimension
        self.hardTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchHardData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.hardTableView.addSubview(refresher)
        
        self.onFetchHardData()
    }
    
    var mFetchCnt = 0
    @objc func onFetchHardData() {
        if (self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 6
        self.interestRates?.removeAll()
        self.totalDeposit?.removeAll()
        self.totalBorrow?.removeAll()
        self.myDeposit?.removeAll()
        self.myBorrow?.removeAll()
        
        self.onFetchHardParam()
        self.onFetchHardInterestRate()
        self.onFetchHardTotalDeposit()
        self.onFetchHardTotalBorrow()
        self.onFetchHardMyDeposit(account!.account_address)
        self.onFetchHardMyBorrow(account!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.hardTableView.reloadData()
            self.refresher.endRefreshing()
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return 1
        } else {
            return hardParam?.money_markets?.count ?? 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"HardListMyStatusCell") as? HardListMyStatusCell
            cell?.onBindView(self.hardParam, self.myDeposit, self.myBorrow)
            return cell!
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"HardListCell") as? HardListCell
            cell?.onBindView(indexPath.row, self.hardParam, self.myDeposit, self.myBorrow, self.interestRates)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 1) {
            let hardDetailVC = HardDetailViewController(nibName: "HardDetailViewController", bundle: nil)
            hardDetailVC.mHardMoneyMarketDenom = hardParam!.money_markets![indexPath.row].denom
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(hardDetailVC, animated: true)
        }
    }
    
    func onFetchHardParam() {
        let request = Alamofire.request(BaseNetWork.paramHardPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardParam = KavaHardParam.init(responseData)
                    self.hardParam = kavaHardParam.result
                    BaseData.instance.mHardParam = kavaHardParam.result
                    
                    if let moneyMarkets = kavaHardParam.result?.money_markets {
                        self.mFetchCnt = self.mFetchCnt + moneyMarkets.count
                        moneyMarkets.forEach { moneyMarket in
                            self.onFetchPriceFeedPrice(moneyMarket.spot_market_id!)
                        }
                    }
                    
                case .failure(let error):
                    print("onFetchHardInterestRate ", error)
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
    
    func onFetchHardInterestRate() {
        let request = Alamofire.request(BaseNetWork.interestRateHardPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardInterestRate = KavaHardInterestRate.init(responseData)
                    self.interestRates = kavaHardInterestRate.result
                    
                case .failure(let error):
                    print("onFetchHardInterestRate ", error)
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardTotalDeposit() {
        let request = Alamofire.request(BaseNetWork.totalDepositHardPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardTotalDeposit = KavaHardTotalDeposit.init(responseData)
                    self.totalDeposit = kavaHardTotalDeposit.result
                    
                case .failure(let error):
                    print("onFetchHardTotalDeposit ", error)
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardTotalBorrow() {
        let request = Alamofire.request(BaseNetWork.totalBorrowHardPoolUrl(chainType), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardTotalBorrow = KavaHardTotalBorrow.init(responseData)
                    self.totalBorrow = kavaHardTotalBorrow.result
                    
                case .failure(let error):
                    print("onFetchHardTotalBorrow ", error)
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardMyDeposit(_ address: String) {
        let request = Alamofire.request(BaseNetWork.depositHardPoolUrl(chainType), method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
//                    print("onFetchHardMyDeposit ", res)
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardMyDeposit = KavaHardMyDeposit.init(responseData)
                    self.myDeposit = kavaHardMyDeposit.result
                    BaseData.instance.mMyHardDeposit = kavaHardMyDeposit.result
//                    print("mMyHardDeposit ", BaseData.instance.mMyHardDeposit?.count)
                    
                case .failure(let error):
                    print("onFetchHardMyDeposit ", error)
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchHardMyBorrow(_ address: String) {
        let request = Alamofire.request(BaseNetWork.borrowHardPoolUrl(chainType), method: .get, parameters: ["owner":address], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
//                    print("onFetchHardMyBorrow ", res)
                    guard let responseData = res as? NSDictionary, let _ = responseData.object(forKey: "height") as? String else {
                        self.onFetchFinished()
                        return
                    }
                    let kavaHardMyBorrow = KavaHardMyBorrow.init(responseData)
                    self.myBorrow = kavaHardMyBorrow.result
                    BaseData.instance.mMyHardBorrow = kavaHardMyBorrow.result
//                    print("mMyHardBorrow ", BaseData.instance.mMyHardBorrow?.count)
                    
                case .failure(let error):
                    print("onFetchHardMyBorrow ", error)
                }
            self.onFetchFinished()
        }
    }
}
