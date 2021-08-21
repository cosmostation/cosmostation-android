//
//  StakingTokenDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class StakingTokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var naviTokenImg: UIImageView!
    @IBOutlet weak var naviTokenSymbol: UILabel!
    @IBOutlet weak var naviPerPrice: UILabel!
    @IBOutlet weak var naviUpdownPercent: UILabel!
    @IBOutlet weak var naviUpdownImg: UIImageView!
    
    @IBOutlet weak var topCard: CardView!
    @IBOutlet weak var topKeyState: UIImageView!
    @IBOutlet weak var topDpAddress: UILabel!
    @IBOutlet weak var topValue: UILabel!
    
    @IBOutlet weak var tokenDetailTableView: UITableView!
    @IBOutlet weak var btnBep3Send: UIButton!
    
    var stakingDenom = ""
    var stakingDivideDecimal: Int16 = 6
    var stakingDisplayDecimal: Int16 = 6
    var totalAmount = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.stakingDenom = WUtils.getMainDenom(chainType)
        self.stakingDivideDecimal = WUtils.mainDivideDecimal(chainType)
        self.stakingDisplayDecimal = WUtils.mainDisplayDecimal(chainType)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "TokenStakingOldCell", bundle: nil), forCellReuseIdentifier: "TokenStakingOldCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailVestingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailVestingDetailCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailUnbondingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailUnbondingDetailCell")
        self.tokenDetailTableView.register(UINib(nibName: "NewHistoryCell", bundle: nil), forCellReuseIdentifier: "NewHistoryCell")
        
        let tapTotalCard = UITapGestureRecognizer(target: self, action: #selector(self.onClickActionShare))
        self.topCard.addGestureRecognizer(tapTotalCard)
        
        self.onInitView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    
    func onInitView() {
        WUtils.setDenomTitle(chainType, naviTokenSymbol)
        self.naviTokenImg.image = WUtils.getStakingTokenImg(chainType!)
        self.naviPerPrice.attributedText = WUtils.dpPerUserCurrencyValue(WUtils.getMainDenom(chainType), naviPerPrice.font)
        self.naviUpdownPercent.attributedText = WUtils.dpValueChange(WUtils.getMainDenom(chainType), font: naviUpdownPercent.font)
        let changeValue = WUtils.valueChange(WUtils.getMainDenom(chainType))
        if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) { naviUpdownImg.image = UIImage(named: "priceUp") }
        else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) { naviUpdownImg.image = UIImage(named: "priceDown") }
        else { naviUpdownImg.image = nil }
        
        self.topCard.backgroundColor = WUtils.getChainBg(chainType)
        if (account?.account_has_private == true) {
            self.topKeyState.image = topKeyState.image?.withRenderingMode(.alwaysTemplate)
            self.topKeyState.tintColor = WUtils.getChainColor(chainType)
        }
        self.topDpAddress.text = account?.dpAddress(chainType)
        self.topDpAddress.adjustsFontSizeToFitWidth = true
        
        if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            totalAmount = WUtils.getAllBnbToken(stakingDenom)
            btnBep3Send.isHidden = false
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            totalAmount = WUtils.getAllExToken(stakingDenom)
        } else {
            totalAmount = WUtils.getAllMainAssetOld(stakingDenom)
        }
        self.topValue.attributedText = WUtils.dpUserCurrencyValue(stakingDenom, totalAmount, stakingDivideDecimal, topValue.font)
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 4
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return 1
            
        } else if (section == 1) {
            if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(stakingDenom) > 0) { return 1 }
            }
            return 0
            
        } else if (section == 2) {
            if (BaseData.instance.unbondingSumAmount().compare(NSDecimalNumber.zero).rawValue > 0) { return 1 }
            return 0
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenStakingOldCell") as? TokenStakingOldCell
            cell?.onBindStakingToken(chainType!)
            return cell!
            
        } else if (indexPath.section == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailVestingDetailCell") as? TokenDetailVestingDetailCell
            cell?.onBindVestingToken(chainType!, stakingDenom)
            return cell!
            
        } else if (indexPath.section == 2) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailUnbondingDetailCell") as? TokenDetailUnbondingDetailCell
            cell?.onBindUnbondingToken(chainType!)
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"NewHistoryCell") as? NewHistoryCell
            return cell!
        }
    }
    
    
    @objc func onClickActionShare() {
        var nickName:String?
        if (account?.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account?.account_nick_name
        }
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        self.shareAddress(address, nickName!)
    }
    
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
        if (BaseData.instance.availableAmount(stakingDenom).compare(feeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mToSendDenom = stakingDenom
        txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickBep3Send(_ sender: UIButton) {
        if (!SUPPORT_BEP3_SWAP || chainType == ChainType.BINANCE_TEST) {
            self.onShowToast(NSLocalizedString("error_bep3_swap_temporary_disable", comment: ""))
            return
        }
        
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, TASK_TYPE_HTLC_SWAP, 0)
        if (BaseData.instance.availableAmount(WUtils.getMainDenom(chainType)).compare(feeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_SWAP
        txVC.mHtlcDenom = stakingDenom
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
}
