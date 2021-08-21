//
//  NativeTokenDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class NativeTokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
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
    @IBOutlet weak var bntBep3Send: UIButton!
    
    var denom: String!
    var baseDenom = ""
    var divideDecimal: Int16 = 6
    var displayDecimal: Int16 = 6
    var totalAmount = NSDecimalNumber.zero

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailNativeCell", bundle: nil), forCellReuseIdentifier: "TokenDetailNativeCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailVestingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailVestingDetailCell")
        self.tokenDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
//        var address = account!.account_address
//        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
//            address = WKey.convertAddressOkexToEth(address)
//        }
//        dpAddress.text = address
//        dpAddress.adjustsFontSizeToFitWidth = true
//
//        if (account?.account_has_private == true) {
//            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
//            keyState.tintColor = WUtils.getChainColor(chainType)
//        }
//
//        if (WUtils.isGRPC(chainType!)) {
//
//        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
//            if (ChainType.isHtlcSwappableCoin(chainType, denom)) { bntBep3Send.isHidden = false }
//
//        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
//            if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(denom!) > 0) { hasVesting = true }
//            if (ChainType.isHtlcSwappableCoin(chainType, denom)) { bntBep3Send.isHidden = false }
//        }
//
//        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
//
//        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
//
//        }
        
        if (ChainType.isHtlcSwappableCoin(chainType, denom)) {
            self.bntBep3Send.isHidden = false
        }
        
        
        let tapTotalCard = UITapGestureRecognizer(target: self, action: #selector(self.onClickActionShare))
        self.topCard.addGestureRecognizer(tapTotalCard)
        
        self.onInitView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    
    func onInitView() {
        self.topCard.backgroundColor = WUtils.getChainBg(chainType)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            baseDenom = WUtils.getKavaBaseDenom(denom)
            if (denom == KAVA_HARD_DENOM) {
                naviTokenSymbol.textColor = COLOR_HARD
                topCard.backgroundColor = COLOR_BG_COLOR_HARD
            }
            naviTokenImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + denom + ".png")!)
            naviTokenSymbol.text = denom.uppercased()

        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {

        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            
        }
        
        self.naviPerPrice.attributedText = WUtils.dpPerUserCurrencyValue(baseDenom, naviPerPrice.font)
        self.naviUpdownPercent.attributedText = WUtils.dpValueChange(baseDenom, font: naviUpdownPercent.font)
        let changeValue = WUtils.valueChange(baseDenom)
        if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) { naviUpdownImg.image = UIImage(named: "priceUp") }
        else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) { naviUpdownImg.image = UIImage(named: "priceDown") }
        else { naviUpdownImg.image = nil }
        
        
        if (account?.account_has_private == true) {
            self.topKeyState.image = topKeyState.image?.withRenderingMode(.alwaysTemplate)
            self.topKeyState.tintColor = WUtils.getChainColor(chainType)
        }
        self.topDpAddress.text = account?.dpAddress(chainType)
        self.topDpAddress.adjustsFontSizeToFitWidth = true
        
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let convertedKavaAmount = WUtils.convertTokenToKava(denom!)
            topValue.attributedText = WUtils.dpUserCurrencyValue(KAVA_MAIN_DENOM, convertedKavaAmount, 6, topValue.font)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            
        }
        
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return 1
        } else if (section == 1) {
            if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
                if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(denom) > 0) { return 1 }
            }
            return 0
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailNativeCell") as? TokenDetailNativeCell
            cell?.onBindNativeToken(chainType, denom)
            return cell!
            
        } else if (indexPath.section == 1) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailVestingDetailCell") as? TokenDetailVestingDetailCell
            cell?.onBindVestingToken(chainType!, denom!)
            return cell!
            
        }
        let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        return cell!
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
    
    @IBAction func onClickBep3Send(_ sender: UIButton) {
        if (!SUPPORT_BEP3_SWAP || chainType == ChainType.BINANCE_TEST || chainType == ChainType.KAVA_TEST) {
            self.onShowToast(NSLocalizedString("error_bep3_swap_temporary_disable", comment: ""))
            return
        }
        
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        //no gRPC case
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, TASK_TYPE_HTLC_SWAP, 0)
        if (BaseData.instance.availableAmount(WUtils.getMainDenom(chainType)).compare(feeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_SWAP
        txVC.mHtlcDenom = denom!
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
    }
}
