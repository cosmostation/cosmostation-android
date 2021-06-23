//
//  TxDetailgRPCViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO

class TxDetailgRPCViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var txTableView: UITableView!
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var errorLayer: CardView!
    @IBOutlet weak var errorCode: UILabel!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    var mIsGen: Bool = false
    var mTxHash: String?
    var mBroadCaseResult: Cosmos_Tx_V1beta1_BroadcastTxResponse?
    var mFetchCnt = 10
    var mTxRespose: Cosmos_Tx_V1beta1_GetTxResponse?
    

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        self.txTableView.delegate = self
        self.txTableView.dataSource = self
        self.txTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.txTableView.rowHeight = UITableView.automaticDimension
        self.txTableView.estimatedRowHeight = UITableView.automaticDimension
        self.txTableView.register(UINib(nibName: "TxCommonCell", bundle: nil), forCellReuseIdentifier: "TxCommonCell")
        self.txTableView.register(UINib(nibName: "TxTransferCell", bundle: nil), forCellReuseIdentifier: "TxTransferCell")
        self.txTableView.register(UINib(nibName: "TxDelegateCell", bundle: nil), forCellReuseIdentifier: "TxDelegateCell")
        self.txTableView.register(UINib(nibName: "TxUndelegateCell", bundle: nil), forCellReuseIdentifier: "TxUndelegateCell")
        self.txTableView.register(UINib(nibName: "TxRedelegateCell", bundle: nil), forCellReuseIdentifier: "TxRedelegateCell")
        self.txTableView.register(UINib(nibName: "TxRewardCell", bundle: nil), forCellReuseIdentifier: "TxRewardCell")
        self.txTableView.register(UINib(nibName: "TxCommissionCell", bundle: nil), forCellReuseIdentifier: "TxCommissionCell")
        self.txTableView.register(UINib(nibName: "TxEditRewardAddressCell", bundle: nil), forCellReuseIdentifier: "TxEditRewardAddressCell")
        self.txTableView.register(UINib(nibName: "TxVoteCell", bundle: nil), forCellReuseIdentifier: "TxVoteCell")
        self.txTableView.register(UINib(nibName: "TxIbcSendCell", bundle: nil), forCellReuseIdentifier: "TxIbcSendCell")
        self.txTableView.register(UINib(nibName: "TxUnknownCell", bundle: nil), forCellReuseIdentifier: "TxUnknownCell")
        
        if (mIsGen) {
            if (mBroadCaseResult?.txResponse.code != 0 || mBroadCaseResult?.txResponse.txhash == nil) {
                self.onShowError(mBroadCaseResult?.txResponse.code, mBroadCaseResult?.txResponse.rawLog)
            } else {
                mTxHash = mBroadCaseResult?.txResponse.txhash
            }
        }
        self.onFetchgRPCTx(mTxHash!)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    
    func onUpdateView() {
        self.loadingImg.isHidden = true
        self.controlLayer.isHidden = false
        self.txTableView.isHidden = false
        self.txTableView.reloadData()
    }
    
    func onShowError(_ code: UInt32?, _ msg: String?) {
        let dpCode = code ?? 8000
        let dpMsg = msg ?? "unKnown"
        
        self.loadingImg.isHidden = true
        self.errorCode.text = "error code : " + String(dpCode) + "\n" + dpMsg
        self.errorLayer.isHidden = false
        self.controlLayer.isHidden = false
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mTxRespose == nil || mTxRespose!.tx.body.messages.count <= 0) {
            return 0
        }
        return mTxRespose!.tx.body.messages.count + 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            return onBindTxCommon(tableView)
        } else {
            let msg = mTxRespose!.tx.body.messages[indexPath.row - 1]
            if (msg.typeURL.contains(Cosmos_Bank_V1beta1_MsgSend.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxTransferCell") as? TxTransferCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1, account!.account_address)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Staking_V1beta1_MsgDelegate.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxDelegateCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Staking_V1beta1_MsgUndelegate.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxUndelegateCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Staking_V1beta1_MsgBeginRedelegate.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxRedelegateCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Distribution_V1beta1_MsgWithdrawDelegatorReward.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxRewardCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Distribution_V1beta1_MsgWithdrawValidatorCommission.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxCommissionCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Distribution_V1beta1_MsgSetWithdrawAddress.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxEditRewardAddressCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Cosmos_Gov_V1beta1_MsgVote.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxVoteCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            } else if (msg.typeURL.contains(Ibc_Applications_Transfer_V1_MsgTransfer.protoMessageName)) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TxIbcSendCell") as? TxCell
                cell?.onBindMsg(chainType!, mTxRespose!, indexPath.row - 1)
                return cell!
                
            }
            return onBindUnknown(tableView)
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }

    
    // bind tx and msg
    func onBindTxCommon(_ tableView: UITableView) -> UITableViewCell {
        let cell:TxCommonCell? = tableView.dequeueReusableCell(withIdentifier:"TxCommonCell") as? TxCommonCell
        cell?.onBind(chainType!, mTxRespose!)
        return cell!
    }
    
    func onBindUnknown(_ tableView: UITableView) -> UITableViewCell  {
        let cell:TxUnknownCell? = tableView.dequeueReusableCell(withIdentifier:"TxUnknownCell") as? TxUnknownCell
        cell?.onBind(chainType!, mTxRespose!)
        return cell!
    }
    
    
    func onFetchgRPCTx(_ txHash: String) {
        print("onFetchgRPCTx ", txHash)
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Tx_V1beta1_GetTxRequest.with {
                $0.hash = txHash
            }
            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).getTx(req).response.wait()
                self.mTxRespose = response
                DispatchQueue.main.async(execute: { self.onUpdateView() });
                
            } catch {
                print("onFetchgRPCAuth failed: \(error)")
                if (self.mIsGen) {
                    self.mFetchCnt = self.mFetchCnt - 1
                    if (self.mFetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                            self.onFetchgRPCTx(self.mTxHash!)
                        })
                    } else {
                        DispatchQueue.main.async(execute: { self.onShowMoreWait() });
                    }
                } else {
                    DispatchQueue.main.async(execute: { self.onShowError(nil, nil) });
                }
            }
        }
        
    }
    
    func onShowMoreWait() {
        let noticeAlert = UIAlertController(title: NSLocalizedString("more_wait_title", comment: ""), message: NSLocalizedString("more_wait_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
            self.onStartMainTab()
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("wait", comment: ""), style: .default, handler: { _ in
            self.mFetchCnt = 10
            self.onFetchgRPCTx(self.mTxHash!)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    @IBAction func onClickShare(_ sender: UIButton) {
        if (self.errorLayer.isHidden) {
            let link = WUtils.getTxExplorer(self.chainType!, self.mTxHash!)
            let textToShare = [ link ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
        }
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        let link = WUtils.getTxExplorer(self.chainType!, self.mTxHash!)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    @IBAction func onClickDismiss(_ sender: UIButton) {
        self.mFetchCnt = -1
        if (mIsGen){
            self.onStartMainTab()
        } else {
            self.navigationController?.popViewController(animated: true)
        }
    }

}
