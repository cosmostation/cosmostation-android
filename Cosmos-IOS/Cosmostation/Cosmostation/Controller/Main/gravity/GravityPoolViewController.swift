//
//  GravityPoolViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/31.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class GravityPoolViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var poolListTableView: UITableView!
    
    var mMyPoolList: Array<Tendermint_Liquidity_V1beta1_Pool> = Array<Tendermint_Liquidity_V1beta1_Pool>()
    var mOtherPoolList: Array<Tendermint_Liquidity_V1beta1_Pool> = Array<Tendermint_Liquidity_V1beta1_Pool>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.poolListTableView.delegate = self
        self.poolListTableView.dataSource = self
        self.poolListTableView.register(UINib(nibName: "CommonPoolCell", bundle: nil), forCellReuseIdentifier: "CommonPoolCell")
        self.poolListTableView.register(UINib(nibName: "CommonMyPoolCell", bundle: nil), forCellReuseIdentifier: "CommonMyPoolCell")
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onGdexFetchDone(_:)), name: Notification.Name("GdexFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("GdexFetchDone"), object: nil)
    }
    
    func updateView() {
        self.poolListTableView.reloadData()
        self.loadingImg.onStopAnimation()
        self.loadingImg.isHidden = true
    }

    @objc func onGdexFetchDone(_ notification: NSNotification) {
        BaseData.instance.mGravityPools_gRPC.forEach { pool in
            if (BaseData.instance.getAvailableAmount_gRPC(pool.poolCoinDenom).compare(NSDecimalNumber.zero).rawValue > 0) {
                mMyPoolList.append(pool)
            } else {
                mOtherPoolList.append(pool)
            }
        }
        self.updateView()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return mMyPoolList.count
        } else {
            return mOtherPoolList.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"CommonMyPoolCell") as? CommonMyPoolCell
            let pool = mMyPoolList[indexPath.row]
            cell?.onBindGdexPoolView(pool)
            return cell!
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"CommonPoolCell") as? CommonPoolCell
            let pool = mOtherPoolList[indexPath.row]
            cell?.onBindGdexPoolView(pool)
            return cell!
        }
    }
}
