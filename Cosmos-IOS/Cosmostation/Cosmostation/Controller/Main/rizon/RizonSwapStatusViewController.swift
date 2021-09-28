//
//  RizonSwapStatusViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/02.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class RizonSwapStatusViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var swapStatusTableView: UITableView!
    @IBOutlet weak var doneBtn: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    var refresher: UIRefreshControl!
    
    var swapInfos = Array<RizonSwapStatus>()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.swapStatusTableView.delegate = self
        self.swapStatusTableView.dataSource = self
        self.swapStatusTableView.register(UINib(nibName: "SwapStatusCell", bundle: nil), forCellReuseIdentifier: "SwapStatusCell")
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchSwapData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.swapStatusTableView.addSubview(refresher)
        
        self.loadingImg.onStartAnimation()
        self.onFetchSwapData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_rizon_swap_status", comment: "")
        self.navigationItem.title = NSLocalizedString("title_rizon_swap_status", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

    @IBAction func onClickDone(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return swapInfos.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier:"SwapStatusCell") as? SwapStatusCell
        let swapInfo = swapInfos[indexPath.row]
        cell?.onBindSwapStatus(chainType, swapInfo)
        cell?.actionTapHdac = { self.onExplorerHdac(swapInfo) }
        cell?.actionTapRizon = { self.onExplorerRizon(swapInfo) }
        return cell!
    }
    
    var mFetchCnt = 0
    @objc func onFetchSwapData() {
        if (self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 1
        self.swapInfos.removeAll()
        self.onCheckSwapStatus(self.account!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            self.swapStatusTableView.reloadData()
            self.swapStatusTableView.isHidden = false
            self.doneBtn.isHidden = false
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.refresher.endRefreshing()
        }
    }
    
    func onCheckSwapStatus(_ address: String) {
        print("onCheckSwapStatus ", BaseNetWork.rizonSwapStatus(chainType, address))
        let request = Alamofire.request(BaseNetWork.rizonSwapStatus(chainType, address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let rawSwapInfos = res as? Array<NSDictionary> {
                    rawSwapInfos.forEach { rawSwapInfo in
                        self.swapInfos.append(RizonSwapStatus.init(rawSwapInfo))
                    }
                }
                print("swapInfos ", self.swapInfos.count)
            
            case .failure(let error):
                print("onCheckSwapStatus ", error)
            }
            self.onFetchFinished()
        }
    }
    
    
    func onExplorerHdac(_ swapInfo: RizonSwapStatus) {
        if let hash = swapInfo.hdacTxId {
            if (chainType == ChainType.RIZON_MAIN) {
                let link = EXPLORER_HDAC_MAIN + "tx/" + hash
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)
                
            } else if (chainType == ChainType.RIZON_TEST) {
                let link = EXPLORER_HDAC_TEST + "tx/" + hash
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)
            }
        }
    }
    
    func onExplorerRizon(_ swapInfo: RizonSwapStatus) {
        if let hash = swapInfo.rizonTxId {
            if (chainType == ChainType.RIZON_MAIN) {
                let link = EXPLORER_RIZON + "txs/" + hash
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)
                
            } else if (chainType == ChainType.RIZON_TEST) {
                let link = EXPLORER_RIZON_TEST + "txs/" + hash
                guard let url = URL(string: link) else { return }
                self.onShowSafariWeb(url)
            }
        }
    }
}
