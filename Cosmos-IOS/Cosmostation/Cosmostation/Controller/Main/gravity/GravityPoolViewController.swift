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
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 0) {
            let noticeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
            noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("title_pool_join", comment: ""), style: .default, handler: { _ in
                self.onCheckPoolJoin(self.mMyPoolList[indexPath.row])
            }))
            noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("title_pool_exit", comment: ""), style: .default, handler: { _ in
                self.onCheckExitJoin(self.mMyPoolList[indexPath.row])
            }))
            self.present(noticeAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
            
        } else if (indexPath.section == 1) {
            self.onCheckPoolJoin(self.mOtherPoolList[indexPath.row])
            
        }
    }
    
    func onCheckPoolJoin(_ pool: Tendermint_Liquidity_V1beta1_Pool) {
        print("onCheckPoolJoin")
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(chainType!, GAS_FEE_AMOUNT_COSMOS_JOIN_POOL, 0)
        let coin0Denom = pool.reserveCoinDenoms[0]
        let coin1Denom = pool.reserveCoinDenoms[1]
        var coin0Available = BaseData.instance.getAvailableAmount_gRPC(coin0Denom)
        var coin1Available = BaseData.instance.getAvailableAmount_gRPC(coin1Denom)
        if (coin0Denom == COSMOS_MAIN_DENOM) { coin0Available = coin0Available.subtracting(txFeeAmount) }
        if (coin1Denom == COSMOS_MAIN_DENOM) { coin1Available = coin1Available.subtracting(txFeeAmount) }
        
        if (coin0Available.compare(NSDecimalNumber.zero).rawValue <= 0 || coin1Available.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_to_deposit", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = GAS_FEE_AMOUNT_COSMOS_JOIN_POOL
        txVC.mPoolId = String(pool.id)
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onCheckExitJoin(_ pool: Tendermint_Liquidity_V1beta1_Pool) {
        print("onCheckExitJoin")
        let mainBalance = BaseData.instance.getAvailableAmount_gRPC(COSMOS_MAIN_DENOM)
        let txFeeAmount = WUtils.getEstimateGasFeeAmount(chainType!, GAS_FEE_AMOUNT_COSMOS_EXIT_POOL, 0)
        
        if (mainBalance.compare(txFeeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = GAS_FEE_AMOUNT_COSMOS_EXIT_POOL
        txVC.mPoolId = String(pool.id)
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
}
