//
//  NativeTokenDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class NativeTokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var keyState: UIImageView!
    @IBOutlet weak var dpAddress: UILabel!
    @IBOutlet weak var tokenDetailTableView: UITableView!
    @IBOutlet weak var btnIbcSend: UIButton!
    @IBOutlet weak var bntBep3Send: UIButton!
    
    var denom: String?
    var hasVesting = false

    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailNativeCell", bundle: nil), forCellReuseIdentifier: "TokenDetailNativeCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHardCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHardCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailVestingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailVestingDetailCell")
        self.tokenDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        self.tokenDetailTableView.rowHeight = UITableView.automaticDimension
        self.tokenDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        dpAddress.text = address
        dpAddress.adjustsFontSizeToFitWidth = true
        
        if (account?.account_has_private == true) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            keyState.tintColor = WUtils.getChainColor(chainType)
        }
        
        if (WUtils.isGRPC(chainType!)) {
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            if (ChainType.isHtlcSwappableCoin(chainType, denom)) { bntBep3Send.isHidden = false }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(denom!) > 0) { hasVesting = true }
            if (ChainType.isHtlcSwappableCoin(chainType, denom)) { bntBep3Send.isHidden = false }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationItem.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (hasVesting == true) {
            return 2
        }
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            if ((chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) && self.denom == KAVA_HARD_DENOM) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHardCell") as? TokenDetailHardCell
                cell?.onBindHardToken()
                return cell!
            }
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailNativeCell") as? TokenDetailNativeCell
            cell?.onBindNativeToken(chainType, denom)
            return cell!
            
        } else if (indexPath.row == 1 && hasVesting) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailVestingDetailCell") as? TokenDetailVestingDetailCell
            cell?.onBindVesting(chainType!, denom!)
            return cell!
            
        }
        let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        return cell!
    }
    
    
    @IBAction func onClickShare(_ sender: UIButton) {
        var nickName:String?
        if (account!.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account!.account_nick_name
        }
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        self.shareAddress(address, nickName!)
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        let mainDenom = WUtils.getMainDenom(chainType)
        if (WUtils.isGRPC(chainType!)) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.getAvailableAmount(mainDenom).compare(feeAmount).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = self.denom
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
        }
        
        else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (BaseData.instance.availableAmount(mainDenom).compare(NSDecimalNumber.init(string: FEE_BNB_TRANSFER)).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = self.denom
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            txVC.mToSendDenom = self.denom
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            if (BaseData.instance.availableAmount(mainDenom).compare(NSDecimalNumber.init(string: "0.002")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = self.denom
            txVC.mType = OK_MSG_TYPE_TRANSFER
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.availableAmount(mainDenom).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = self.denom
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
    
        } else {
            return
        }
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickBep3Send(_ sender: UIButton) {
        if (!SUPPORT_BEP3_SWAP || chainType == ChainType.BINANCE_TEST || chainType == ChainType.KAVA_TEST) {
            self.onShowToast(NSLocalizedString("error_bep3_swap_temporary_disable", comment: ""))
            return
        }
        
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            if (BaseData.instance.availableAmount(BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: FEE_BNB_TRANSFER)).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_SWAP
        txVC.mHtlcDenom = denom!
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickIbcSend(_ sender: UIButton) {
        self.onShowToast(NSLocalizedString("prepare", comment: ""))
    }
}
