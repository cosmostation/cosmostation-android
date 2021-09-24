//
//  NativeTokenGrpcViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/20.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class NativeTokenGrpcViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, UIGestureRecognizerDelegate {
    
    @IBOutlet weak var naviTokenImg: UIImageView!
    @IBOutlet weak var naviTokenSymbol: UILabel!
    @IBOutlet weak var naviPerPrice: UILabel!
    @IBOutlet weak var naviUpdownPercent: UILabel!
    @IBOutlet weak var naviUpdownImg: UIImageView!
    
    @IBOutlet weak var topCard: CardView!
    @IBOutlet weak var topKeyState: UIImageView!
    @IBOutlet weak var topDpAddress: UILabel!
    @IBOutlet weak var topValue: UILabel!
    
    @IBOutlet weak var tokenTableView: UITableView!
    @IBOutlet weak var btnIbcSend: UIButton!
    @IBOutlet weak var btnSend: UIButton!

    var nativeDenom = ""
    var nativeDivideDecimal: Int16 = 6
    var nativeDisplayDecimal: Int16 = 6
    var totalAmount = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenTableView.delegate = self
        self.tokenTableView.dataSource = self
        self.tokenTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenTableView.register(UINib(nibName: "TokenDetailNativeCell", bundle: nil), forCellReuseIdentifier: "TokenDetailNativeCell")
        self.tokenTableView.register(UINib(nibName: "NewHistoryCell", bundle: nil), forCellReuseIdentifier: "NewHistoryCell")
        
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
        if (chainType == ChainType.OSMOSIS_MAIN) {
            WUtils.DpOsmosisTokenName(naviTokenSymbol, nativeDenom)
            WUtils.DpOsmosisTokenImg(naviTokenImg, nativeDenom)
            if (nativeDenom == OSMOSIS_ION_DENOM) {
                nativeDivideDecimal = 6
                nativeDisplayDecimal = 6
                totalAmount = BaseData.instance.getAvailableAmount_gRPC(nativeDenom)
            }
            
        } else if (chainType == ChainType.EMONEY_MAIN) {
            naviTokenSymbol.text = nativeDenom.substring(from: 1).uppercased()
            naviTokenImg.af_setImage(withURL: URL(string: EMONEY_COIN_IMG_URL + nativeDenom + ".png")!)
            nativeDivideDecimal = 6
            nativeDisplayDecimal = 6
            totalAmount = BaseData.instance.getAvailableAmount_gRPC(nativeDenom)
        }
        
        self.naviPerPrice.attributedText = WUtils.dpPerUserCurrencyValue(nativeDenom, naviPerPrice.font)
        self.naviUpdownPercent.attributedText = WUtils.dpValueChange(nativeDenom, font: naviUpdownPercent.font)
        let changeValue = WUtils.valueChange(nativeDenom)
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
        self.topValue.attributedText = WUtils.dpUserCurrencyValue(nativeDenom, totalAmount, nativeDivideDecimal, topValue.font)
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return 1
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailNativeCell") as? TokenDetailNativeCell
            cell?.onBindNativeToken(chainType, nativeDenom)
            return cell!
            
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier:"NewHistoryCell") as? NewHistoryCell
            return cell!
        }
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
    
    @IBAction func onClickIbcSend(_ sender: UIButton) {
        self.onShowToast(NSLocalizedString("prepare", comment: ""))
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let stakingDenom = WUtils.getMainDenom(chainType)
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
        if (BaseData.instance.getAvailableAmount_gRPC(stakingDenom).compare(feeAmount).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mToSendDenom = nativeDenom
        txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false;
        self.navigationController?.pushViewController(txVC, animated: true)
    }
}
