//
//  RegisterAccount4ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import HDWalletKit
import SwiftKeychainWrapper
import GRPC
import NIO

class RegisterAccount4ViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, PasswordViewDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    @IBOutlet weak var resigter4Tableview: UITableView!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
        self.resigter4Tableview.delegate = self
        self.resigter4Tableview.dataSource = self
        self.resigter4Tableview.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.resigter4Tableview.register(UINib(nibName: "RegistAccountCheckCell", bundle: nil), forCellReuseIdentifier: "RegistAccountCheckCell")
    }
    
    override func enableUserInteraction() {
        self.resigter4Tableview.reloadData()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:RegistAccountCheckCell? = tableView.dequeueReusableCell(withIdentifier:"RegistAccountCheckCell") as? RegistAccountCheckCell
        
        let starnameFee = WUtils.getStarNameRegisterAccountFee("open")
        cell?.feeAmountLabel.attributedText = WUtils.displayAmount2((pageHolderVC.mFee?.amount[0].amount)!, cell!.feeAmountLabel.font, 6, 6)
        cell?.starnameFeeAmount.attributedText = WUtils.displayAmount2(starnameFee.stringValue, cell!.starnameFeeAmount.font, 6, 6)
        cell?.starnameLabel.text = pageHolderVC.mStarnameAccount! + "*iov"
        
        let extendTime = WUtils.getStarNameRegisterDomainExpireTime()
        cell?.expireDate.text = WUtils.longTimetoString(input: Date().millisecondsSince1970 + extendTime)
        cell?.memoLabel.text = pageHolderVC.mMemo
        
        let resources = pageHolderVC.mStarnameResources_gRPC
        if (resources.count == 0) {
            cell?.connectedAddressesLabel.text = ""
        } else {
            var resourceString = ""
            for resource in resources {
                resourceString.append(resource.uri + "\n" + resource.resource + "\n\n")
            }
            cell?.connectedAddressesLabel.text = resourceString
        }
        return cell!
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchgRPCAuth(pageHolderVC.mAccount!)
        }
    }
    
    
    func onFetchgRPCAuth(_ account: Account) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Auth_V1beta1_QueryAccountRequest.with {
                $0.address = account.account_address
            }
            do {
                let response = try Cosmos_Auth_V1beta1_QueryClient(channel: channel).account(req).response.wait()
                self.onBroadcastGrpcTx(response)
            } catch {
                print("onFetchgRPCAuth failed: \(error)")
            }
        }
    }
    
    func onBroadcastGrpcTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse?) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let reqTx = Signer.genSignedRegisterAccountMsgTxgRPC(auth!,
                                                                 self.pageHolderVC.mStarnameDomain!,
                                                                 self.pageHolderVC.mStarnameAccount!,
                                                                 self.pageHolderVC.mAccount!.account_address,
                                                                 self.pageHolderVC.mAccount!.account_address,
                                                                 self.pageHolderVC.mStarnameResources_gRPC,
                                                                 self.pageHolderVC.mFee!,
                                                                 self.pageHolderVC.mMemo!,
                                                                 WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!),
                                                                 BaseData.instance.getChainId_gRPC())
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).broadcastTx(reqTx).response.wait()
//                print("response ", response.txResponse.txhash)
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetailgRPC(response)
                        })
                    }
                });
            } catch {
                print("onBroadcastGrpcTx failed: \(error)")
            }
        }
    }
}
