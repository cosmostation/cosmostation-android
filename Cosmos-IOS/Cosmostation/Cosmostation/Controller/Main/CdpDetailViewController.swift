//
//  CdpDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class CdpDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var cdpDetailTableView: UITableView!
    @IBOutlet weak var createCdpBtn: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    var refresher: UIRefreshControl!
    @IBOutlet weak var emptyConstraint: NSLayoutConstraint!
    @IBOutlet weak var owenConstraint: NSLayoutConstraint!
    
    var cDenom: String = ""
    var pDenom: String = ""
    var cDpDecimal:Int16 = 6
    var pDpDecimal:Int16 = 6
    var kDpDecimal:Int16 = 6
    var mMarketID: String = ""
    var cAvailable: NSDecimalNumber = NSDecimalNumber.zero
    var pAvailable: NSDecimalNumber = NSDecimalNumber.zero
    var kAvailable: NSDecimalNumber = NSDecimalNumber.zero
    var currentPrice: NSDecimalNumber = NSDecimalNumber.zero
    var liquidationPrice: NSDecimalNumber = NSDecimalNumber.zero
    var riskRate: NSDecimalNumber = NSDecimalNumber.zero
    
    var cdpParam:CdpParam?
    var cParam: CdpParam.CollateralParam?
    var mMyCdps: CdpOwen?
    var mMyCdpDeposit: CdpDeposits?
    var mPrice: KavaTokenPrice = KavaTokenPrice.init()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.cdpDetailTableView.delegate = self
        self.cdpDetailTableView.dataSource = self
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailTopCell", bundle: nil), forCellReuseIdentifier: "CdpDetailTopCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailMyTopCell", bundle: nil), forCellReuseIdentifier: "CdpDetailMyTopCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "CdpDetailMyActionCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailAssetsCell", bundle: nil), forCellReuseIdentifier: "CdpDetailAssetsCell")
        self.cdpDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.cdpDetailTableView.rowHeight = UITableView.automaticDimension
        self.cdpDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchCdpData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.cdpDetailTableView.addSubview(refresher)
        
        print("cDenom ", cDenom)
        print("mMarketID ", mMarketID)
        
        self.loadingImg.onStartAnimation()
        self.onFetchCdpData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_cdp_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_cdp_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    

    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if ((mMyCdps) != nil) {
            return 3
        } else {
            return 2
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (mMyCdps != nil && mMyCdpDeposit != nil) {
            if (indexPath.row == 0) {
                return self.onSetMyTopItems(tableView, indexPath)
            } else if (indexPath.row == 1) {
                return self.onSetMyActionItems(tableView, indexPath)
            } else {
                return self.onSetAssetsItems(tableView, indexPath)
            }
            
        } else {
            if (indexPath.row == 0) {
                return self.onSetTopItems(tableView, indexPath)
            } else {
                return self.onSetAssetsItems(tableView, indexPath)
            }
        }
    }
    
    func onSetTopItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpDetailTopCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailTopCell") as? CdpDetailTopCell
        if (cParam != nil) {
            cell?.marketTitle.text = cParam!.getDpMarketId()
            cell?.minCollateralRate.attributedText = WUtils.displayPercent(cParam!.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
            cell?.stabilityFee.attributedText = WUtils.displayPercent(cParam!.getDpStabilityFee(), font: cell!.stabilityFee.font)
            cell?.liquidationPenalty.attributedText = WUtils.displayPercent(cParam!.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
            cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)
            let url = KAVA_CDP_MARKET_IMG_URL + cParam!.getMarketImgPath() + ".png"
            Alamofire.request(url, method: .get).responseImage { response  in
                guard let image = response.result.value else { return }
                cell?.marketImg.image = image
            }
            cell?.helpCollateralRate = {
                self.onShowSimpleHelp(NSLocalizedString("help_collateral_rate_title", comment: ""), NSLocalizedString("help_collateral_rate_msg", comment: ""))
            }
            cell?.helpStabilityFee = {
                self.onShowSimpleHelp(NSLocalizedString("help_stability_fee_title", comment: ""), NSLocalizedString("help_stability_fee_msg", comment: ""))
            }
            cell?.helpLiquidationPenalty = {
                self.onShowSimpleHelp(NSLocalizedString("help_liquidation_penalty_title", comment: ""), NSLocalizedString("help_liquidation_penalty_msg", comment: ""))
            }
        }
        return cell!
        
    }
    
    func onSetMyTopItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpDetailMyTopCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyTopCell") as? CdpDetailMyTopCell
        
        liquidationPrice = mMyCdps!.result.getLiquidationPrice(cDenom, pDenom, cParam!)
        riskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(liquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))
        print("currentPrice ", currentPrice)
        print("liquidationPrice ", liquidationPrice)
        print("riskRate ", riskRate)
        
        cell?.marketTitle.text = cParam!.getDpMarketId()
        WUtils.showRiskRate(riskRate, cell!.riskScore, _rateIamg: cell!.riskRateImg)
        
        cell?.debtValueTitle.text = String(format: NSLocalizedString("debt_value_format", comment: ""), pDenom.uppercased())
        cell?.debtValue.attributedText = WUtils.getDPRawDollor(mMyCdps!.result.getDpEstimatedTotalDebtValue(pDenom, cParam!).stringValue, 2, cell!.debtValue.font)
        
        cell?.collateralValueTitle.text = String(format: NSLocalizedString("collateral_value_format", comment: ""), pDenom.uppercased())
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(mMyCdps!.result.getDpCollateralValue(pDenom).stringValue, 2, cell!.collateralValue.font)
        
        cell?.minCollateralRate.attributedText = WUtils.displayPercent(cParam!.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
        cell?.stabilityFee.attributedText = WUtils.displayPercent(cParam!.getDpStabilityFee(), font: cell!.stabilityFee.font)
        cell?.liquidationPenalty.attributedText = WUtils.displayPercent(cParam!.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
        
        cell?.currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), cDenom.uppercased())
        cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)
        
        cell?.liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), cDenom.uppercased())
        cell?.liquidationPrice.attributedText = WUtils.getDPRawDollor(liquidationPrice.stringValue, 4, cell!.liquidationPrice.font)
        
        let url = KAVA_CDP_MARKET_IMG_URL + cParam!.getMarketImgPath() + ".png"
        Alamofire.request(url, method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.marketImg.image = image
        }
        cell?.helpCollateralRate = {
            self.onShowSimpleHelp(NSLocalizedString("help_collateral_rate_title", comment: ""), NSLocalizedString("help_collateral_rate_msg", comment: ""))
        }
        cell?.helpStabilityFee = {
            self.onShowSimpleHelp(NSLocalizedString("help_stability_fee_title", comment: ""), NSLocalizedString("help_stability_fee_msg", comment: ""))
        }
        cell?.helpLiquidationPenalty = {
            self.onShowSimpleHelp(NSLocalizedString("help_liquidation_penalty_title", comment: ""), NSLocalizedString("help_liquidation_penalty_msg", comment: ""))
        }
        return cell!
    }
    
    func onSetMyActionItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell  {
        let cell:CdpDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyActionCell") as? CdpDetailMyActionCell
        
        cell?.collateralDenom.text = cDenom.uppercased()
        let selfDepositAmount = mMyCdpDeposit!.getSelfDeposit(account!.account_address)
        let selfDepositValue = selfDepositAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralSelfAmount.attributedText = WUtils.displayAmount2(selfDepositAmount.stringValue, cell!.collateralSelfAmount.font!, cDpDecimal, cDpDecimal)
        cell?.collateralSelfValue.attributedText = WUtils.getDPRawDollor(selfDepositValue.stringValue, 2, cell!.collateralSelfValue.font)
        
        let totalDepositAmount = mMyCdps!.result.getTotalCollateralAmount()
        let totalDepositValue = totalDepositAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralTotalAmount.attributedText = WUtils.displayAmount2(totalDepositAmount.stringValue, cell!.collateralTotalAmount.font!, cDpDecimal, cDpDecimal)
        cell?.collateralTotalValue.attributedText = WUtils.getDPRawDollor(totalDepositValue.stringValue, 2, cell!.collateralTotalValue.font)
        
        cell?.collateralWithdrawableTitle.text = String(format: NSLocalizedString("withdrawable_format", comment: ""), cDenom.uppercased())
        let maxWithdrawableAmount = mMyCdps!.result.getWithdrawableAmount(cDenom, pDenom, cParam!, currentPrice, selfDepositAmount)
        let maxWithdrawableValue = maxWithdrawableAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
//        print("maxWithdrawableAmount ", maxWithdrawableAmount)
//        print("maxWithdrawableValue ", maxWithdrawableValue)
        
        cell?.collateralWithdrawableAmount.attributedText = WUtils.displayAmount2(maxWithdrawableAmount.stringValue, cell!.collateralWithdrawableAmount.font!, cDpDecimal, cDpDecimal)
        cell?.collateralWithdrawableValue.attributedText = WUtils.getDPRawDollor(maxWithdrawableValue.stringValue, 2, cell!.collateralWithdrawableValue.font)
        
        cell?.depositBtn.setTitle(String(format: NSLocalizedString("str_deposit", comment: ""), self.cDenom.uppercased()), for: .normal)
        cell?.withdrawBtn.setTitle(String(format: NSLocalizedString("str_withdraw", comment: ""), self.cDenom.uppercased()), for: .normal)
        
        cell?.helpCollateralSelf = {
            self.onShowSimpleHelp(NSLocalizedString("help_self_deposited_title", comment: ""), String(format: NSLocalizedString("help_self_deposited_msg", comment: ""), self.cDenom.uppercased()))
        }
        cell?.helpCollateralTotal = {
            self.onShowSimpleHelp(NSLocalizedString("help_total_deposited_title", comment: ""), String(format: NSLocalizedString("help_total_deposited_msg", comment: ""), self.cDenom.uppercased()))
        }
        cell?.helpCollateralWithdrawable = {
            self.onShowSimpleHelp(NSLocalizedString("help_withdrawable_title", comment: ""), NSLocalizedString("help_withdrawable_msg", comment: ""))
        }
        cell?.actionDeposit = {
            self.onClickDeposit()
        }
        cell?.actionWithdraw = {
            self.onClickWithdraw()
        }
        
        
        cell?.principalDenom.text = pDenom.uppercased()
        let rawPricipalAmount = mMyCdps!.result.cdp.getRawPrincipalAmount()
        cell?.principalAmount.attributedText = WUtils.displayAmount2(rawPricipalAmount.stringValue, cell!.principalAmount.font!, pDpDecimal, pDpDecimal)
        cell?.principalValue.attributedText = WUtils.getDPRawDollor(rawPricipalAmount.multiplying(byPowerOf10: -pDpDecimal).stringValue, 2, cell!.principalValue.font)
        
        let totalFeeAmount = mMyCdps!.result.cdp.getEstimatedTotalFee(cParam!)
        cell?.interestAmount.attributedText = WUtils.displayAmount2(totalFeeAmount.stringValue, cell!.interestAmount.font!, pDpDecimal, pDpDecimal)
        cell?.interestValue.attributedText = WUtils.getDPRawDollor(totalFeeAmount.multiplying(byPowerOf10: -pDpDecimal).stringValue, 2, cell!.principalValue.font)
        
        let moreDebtAmount = mMyCdps!.result.getMoreLoanableAmount(cParam!)
        cell?.remainingAmount.attributedText = WUtils.displayAmount2(moreDebtAmount.stringValue, cell!.remainingAmount.font!, pDpDecimal, pDpDecimal)
        cell?.remainingValue.attributedText = WUtils.getDPRawDollor(moreDebtAmount.multiplying(byPowerOf10: -pDpDecimal).stringValue, 2, cell!.remainingValue.font)
        
        cell?.helpPrincipal = {
            self.onShowSimpleHelp(NSLocalizedString("help_loaned_amount_title", comment: ""), NSLocalizedString("help_loaned_amount_msg", comment: ""))
        }
        cell?.helpInterest = {
            self.onShowSimpleHelp(NSLocalizedString("help_total_fee_title", comment: ""), NSLocalizedString("help_total_fee_msg", comment: ""))
        }
        cell?.helpRemaining = {
            self.onShowSimpleHelp(NSLocalizedString("help_remaining_loan_title", comment: ""), NSLocalizedString("help_remaining_loan_msg", comment: ""))
        }
        cell?.actionDrawDebt = {
            self.onClickDrawDebt()
        }
        cell?.actionRepay = {
            self.onClickRepay()
        }
        
        
        Alamofire.request(KAVA_COIN_IMG_URL + cDenom + ".png", method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.collateralImg.image = image
        }
        Alamofire.request(KAVA_COIN_IMG_URL + pDenom + ".png", method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.principalImg.image = image
        }
        return cell!
    }
    
    func onSetAssetsItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpDetailAssetsCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailAssetsCell") as? CdpDetailAssetsCell
        
        cell?.collateralDenom.text = cDenom.uppercased()
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(cAvailable.stringValue, cell!.collateralAmount.font!, cDpDecimal, cDpDecimal)
        let collateralValue = cAvailable.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(collateralValue.stringValue, 2, cell!.collateralValue.font)
        
        cell?.principalDenom.text = pDenom.uppercased()
        cell?.principalAmount.attributedText = WUtils.displayAmount2(pAvailable.stringValue, cell!.principalAmount.font!, pDpDecimal, pDpDecimal)
        let principalValue = pAvailable.multiplying(byPowerOf10: -pDpDecimal)
        cell?.principalValue.attributedText = WUtils.getDPRawDollor(principalValue.stringValue, 2, cell!.principalValue.font)
        
        cell?.kavaAmount.attributedText = WUtils.displayAmount2(kAvailable.stringValue, cell!.kavaAmount.font!, kDpDecimal, kDpDecimal)
        let kavaValue = kAvailable.multiplying(byPowerOf10: -kDpDecimal).multiplying(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler2Down)
        cell?.kavaValue.attributedText = WUtils.getDPRawDollor(kavaValue.stringValue, 2, cell!.kavaValue.font)
        
        Alamofire.request(KAVA_COIN_IMG_URL + cDenom + ".png", method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.collateralImg.image = image
        }
        Alamofire.request(KAVA_COIN_IMG_URL + pDenom + ".png", method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.principalImg.image = image
        }
        return cell!
    }

    @IBAction func onClickCreateCdp(_ sender: UIButton) {
        if (!onCommonCheck()) { return }
        let debtFloor = NSDecimalNumber.init(string: cdpParam!.result.debt_params[0].debt_floor)
        let cMinAmount = debtFloor.multiplying(byPowerOf10: cDpDecimal - pDpDecimal).multiplying(by: NSDecimalNumber.init(string: "1.05")).multiplying(by: cParam!.getLiquidationRatio()).dividing(by: currentPrice, withBehavior: WUtils.handler0Down)
        if (cAvailable.compare(cMinAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_CREATE_CDP
        txVC.cDenom = cDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickDeposit() {
        if (!onCommonCheck()) { return }
        if (cAvailable.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enought_deposit_asset", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_DEPOSIT_CDP
        txVC.cDenom = cDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickWithdraw() {
        if (!onCommonCheck()) { return }
        let selfDepositAmount = mMyCdpDeposit!.getSelfDeposit(account!.account_address)
        let maxWithdrawableAmount = mMyCdps!.result.getWithdrawableAmount(cDenom, pDenom, cParam!, currentPrice, selfDepositAmount)
        if (maxWithdrawableAmount.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enought_withdraw_asset", comment: ""))
            return
        }
         
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_WITHDRAW_CDP
        txVC.cDenom = cDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickDrawDebt() {
        if (!onCommonCheck()) { return }
        if (mMyCdps!.result.getMoreLoanableAmount(cParam!).compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_can_not_draw_debt", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_DRAWDEBT_CDP
        txVC.cDenom = cDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickRepay() {
        if (!onCommonCheck()) { return }
        if (pAvailable.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enought_principal_asset", comment: ""))
            return
        }
        var repayAll = true
        var repayPart = true
        let debtFloor = NSDecimalNumber.init(string: cdpParam!.result.debt_params[0].debt_floor)
        let rawDebt = mMyCdps!.result.cdp.getRawPrincipalAmount()
        let totalDebt = mMyCdps!.result.cdp.getEstimatedTotalDebt(cParam!)
        if (totalDebt.compare(pAvailable).rawValue > 0) { repayAll = false }
        if (rawDebt.compare(debtFloor).rawValue <= 0) { repayPart = false }
        if (!repayAll && !repayPart) {
            self.onShowToast(NSLocalizedString("error_can_not_repay", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_REPAYDEBT_CDP
        txVC.cDenom = cDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    func onCommonCheck() -> Bool {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return false
        }
        if (self.account!.getKavaBalance().compare(NSDecimalNumber.one).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
            return false
        }
        if (cdpParam!.result.circuit_breaker) {
            self.onShowToast(NSLocalizedString("error_circuit_breaker", comment: ""))
            return false
        }
        return true
    }
    
    
    var mFetchCnt = 0
    @objc func onFetchCdpData() {
        if(self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 4
        onFetchCdpParam(account!)
        onFetchOwenCdp(account!, self.cDenom)
        onFetchCdpDeposit(account!, self.cDenom)
        onFetchKavaPrice(self.mMarketID)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if ((cParam) == nil) {
                print("ERROR");
                return
            }
            pDenom = cParam!.getpDenom()
            cDpDecimal = WUtils.getKavaCoinDecimal(cDenom)
            pDpDecimal = WUtils.getKavaCoinDecimal(pDenom)
            kDpDecimal = WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)
            cAvailable = account!.getTokenBalance(cDenom)
            pAvailable = account!.getTokenBalance(pDenom)
            kAvailable = account!.getTokenBalance(KAVA_MAIN_DENOM)
            currentPrice = NSDecimalNumber.init(string: mPrice.result.price)
//            print("cAvailable ", cAvailable)
//            print("pAvailable ", pAvailable)
//            print("kAvailable ", kAvailable)
            
            if ((mMyCdps) != nil) {
                emptyConstraint.priority = .defaultLow
                owenConstraint.priority = .defaultHigh
                createCdpBtn.isHidden = true
            } else {
                emptyConstraint.priority = .defaultHigh
                owenConstraint.priority = .defaultLow
                createCdpBtn.isHidden = false
            }
            self.cdpDetailTableView.reloadData()
            self.cdpDetailTableView.isHidden = false
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.refresher.endRefreshing()
        }
    }
    
    func onShowSimpleHelp(_ title:String, _ msg:String) {
        let helpAlert = UIAlertController(title: title, message: msg, preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let attributedMessage: NSMutableAttributedString = NSMutableAttributedString(
            string: msg,
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.footnote)
            ]
        )
        helpAlert.setValue(attributedMessage, forKey: "attributedMessage")
        helpAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(helpAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            helpAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onFetchCdpParam(_ account:Account) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_CDP_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String else {
                            self.onFetchFinished()
                            return
                    }
                    self.cdpParam = CdpParam.init(responseData)
                    self.cParam = self.cdpParam!.result.getcParam(self.cDenom)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchCdpParam ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchOwenCdp(_ account:Account, _ denom:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_CDP_OWEN + account.account_address + "/" + denom
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String,
                        responseData.object(forKey: "result") != nil else {
                            self.onFetchFinished()
                            return
                    }
                    self.mMyCdps = CdpOwen.init(responseData)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchOwenCdp ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchCdpDeposit(_ account:Account, _ denom:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_CDP_DEPOSIT + account.account_address + "/" + denom
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String,
                        responseData.object(forKey: "result") != nil else {
                            self.onFetchFinished()
                            return
                    }
                    self.mMyCdpDeposit = CdpDeposits.init(responseData)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchCdpDeposit ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchKavaPrice(_ market:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_TOKEN_PRICE + market
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String else {
                            self.onFetchFinished()
                            return
                    }
                    self.mPrice = KavaTokenPrice.init(responseData)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchKavaPrice ", market , " ", error) }
                }
            self.onFetchFinished()
        }
    }
}
