//
//  NativeTokenDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class NativeTokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, UIGestureRecognizerDelegate {
    
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
        
        if (ChainType.isHtlcSwappableCoin(chainType, denom)) {
            self.bntBep3Send.isHidden = false
        }
        
        let tapTotalCard = UITapGestureRecognizer(target: self, action: #selector(self.onClickActionShare))
        self.topCard.addGestureRecognizer(tapTotalCard)
        
        self.onInitView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: true)
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = true
        self.navigationController?.interactivePopGestureRecognizer?.delegate = self
    }
    
    @objc func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return true
    }
    
    func onInitView() {
        self.topCard.backgroundColor = WUtils.getChainBg(chainType)
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            let baseDenom = WUtils.getKavaBaseDenom(denom)
            let decimal = WUtils.getKavaCoinDecimal(denom)
            if (denom == KAVA_HARD_DENOM) {
                naviTokenSymbol.textColor = COLOR_HARD
                topCard.backgroundColor = COLOR_BG_COLOR_HARD
            } else if (denom == KAVA_USDX_DENOM) {
                naviTokenSymbol.textColor = COLOR_USDX
                topCard.backgroundColor = COLOR_BG_COLOR_USDX
            } else if (denom == KAVA_SWAP_DENOM) {
                naviTokenSymbol.textColor = COLOR_SWP
                topCard.backgroundColor = COLOR_BG_COLOR_SWP
            }
            naviTokenImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + denom + ".png")!)
            naviTokenSymbol.text = denom.uppercased()
            
            let totalTokenAmount = WUtils.getKavaTokenAll(denom)
            topValue.attributedText = WUtils.dpUserCurrencyValue(baseDenom, totalTokenAmount, decimal, topValue.font)
            
            self.naviPerPrice.attributedText = WUtils.dpPerUserCurrencyValue(baseDenom, naviPerPrice.font)
            self.naviUpdownPercent.attributedText = WUtils.dpValueChange(baseDenom, font: naviUpdownPercent.font)
            let changeValue = WUtils.valueChange(baseDenom)
            if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) { naviUpdownImg.image = UIImage(named: "priceUp") }
            else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) { naviUpdownImg.image = UIImage(named: "priceDown") }
            else { naviUpdownImg.image = nil }
            

        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            guard let bnbToken = WUtils.getBnbToken(denom) else {
                self.navigationController?.popViewController(animated: true)
                return
            }
            naviTokenImg.af_setImage(withURL: URL(string: BINANCE_TOKEN_IMG_URL + bnbToken.original_symbol + ".png")!)
            naviTokenSymbol.text = bnbToken.original_symbol.uppercased()
            
            let convertedBnbAmount = WUtils.getBnbConvertAmount(denom!)
            topValue.attributedText = WUtils.dpUserCurrencyValue(BNB_MAIN_DENOM, convertedBnbAmount, 0, topValue.font)
            
            self.naviPerPrice.attributedText = WUtils.dpBnbTokenUserCurrencyPrice(denom, naviPerPrice.font)
            self.naviUpdownPercent.text = ""
            self.naviUpdownImg.image = nil

        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            guard let okToken = WUtils.getOkToken(denom) else {
                self.navigationController?.popViewController(animated: true)
                return
            }
            naviTokenImg.af_setImage(withURL: URL(string: OKEX_COIN_IMG_URL + okToken.original_symbol! + ".png")!)
            naviTokenSymbol.text = okToken.original_symbol!.uppercased()
            
            let convertedOktAmount = WUtils.convertTokenToOkt(denom!)
            topValue.attributedText = WUtils.dpUserCurrencyValue(OKEX_MAIN_DENOM, convertedOktAmount, 0, topValue.font)
            
            if (okToken.original_symbol == "okb") {
                self.naviPerPrice.attributedText = WUtils.dpPerUserCurrencyValue("okb", naviPerPrice.font)
                self.naviUpdownPercent.attributedText = WUtils.dpValueChange("okb", font: naviUpdownPercent.font)
                let changeValue = WUtils.valueChange("okb")
                if (changeValue.compare(NSDecimalNumber.zero).rawValue > 0) { naviUpdownImg.image = UIImage(named: "priceUp") }
                else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) { naviUpdownImg.image = UIImage(named: "priceDown") }
                else { naviUpdownImg.image = nil }
                
            } else {
                self.naviPerPrice.text = ""
                self.naviUpdownPercent.text = ""
                self.naviUpdownImg.image = nil
            }
            
        }
        
        if (account?.account_has_private == true) {
            self.topKeyState.image = topKeyState.image?.withRenderingMode(.alwaysTemplate)
            self.topKeyState.tintColor = WUtils.getChainColor(chainType)
        }
        self.topDpAddress.text = account?.dpAddress(chainType)
        self.topDpAddress.adjustsFontSizeToFitWidth = true
        
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
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        self.shareAddress(address, WUtils.getWalletName(account))
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
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false;
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let stakingDenom = WUtils.getMainDenom(chainType)
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
        if (BaseData.instance.availableAmount(stakingDenom).compare(feeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mToSendDenom = denom
        txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false;
        self.navigationController?.pushViewController(txVC, animated: true)
    }
}
