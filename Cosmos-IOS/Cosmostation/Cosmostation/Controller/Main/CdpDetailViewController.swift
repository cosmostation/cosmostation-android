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
    
    var mCDenom: String = ""
    var mPDenom: String = ""
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
    
    var mCdpParam:CdpParam?
    var mCollateralParam: CdpParam.CollateralParam?
    var mMyCdpStatus: CdpOwen?
    var mMyCdpDeposit: CdpDeposits?
    var mKavaTotalSupply: KavaSupply?
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
        
        if (SHOW_LOG) {
            print("mCDenom ", mCDenom)
            print("mMarketID ", mMarketID)
        }
        
        mCdpParam = BaseData.instance.mCdpParam
        mCollateralParam = mCdpParam?.result.getcParam(mCDenom)
        
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
        if ((mMyCdpStatus) != nil) {
            return 3
        } else {
            return 2
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (mMyCdpStatus != nil && mMyCdpDeposit != nil) {
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
        if (mCollateralParam != nil) {
            cell?.marketTitle.text = mCollateralParam!.getDpMarketId()
            cell?.minCollateralRate.attributedText = WUtils.displayPercent(mCollateralParam!.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
            cell?.stabilityFee.attributedText = WUtils.displayPercent(mCollateralParam!.getDpStabilityFee(), font: cell!.stabilityFee.font)
            cell?.liquidationPenalty.attributedText = WUtils.displayPercent(mCollateralParam!.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
            
            cell?.currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), mCDenom.uppercased())
            cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)
            
            cell?.systemMax.attributedText = WUtils.displayAmount2(mCdpParam?.result.getGlobalDebtAmount().stringValue, cell!.systemMax.font, 6, 6)
            cell?.remainCap.attributedText = WUtils.displayAmount2(mCdpParam!.result.getGlobalDebtAmount().subtracting(mKavaTotalSupply?.getDebtAmount() ?? NSDecimalNumber.zero).stringValue, cell!.remainCap.font, 6, 6)
            
            let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam!.getMarketImgPath() + ".png"
            cell?.marketImg.af_setImage(withURL: URL(string: url)!)
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
        
        liquidationPrice = mMyCdpStatus!.result.getLiquidationPrice(mCDenom, mPDenom, mCollateralParam!)
        riskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(liquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))
        
        if (SHOW_LOG) {
            print("currentPrice ", currentPrice)
            print("liquidationPrice ", liquidationPrice)
            print("riskRate ", riskRate)
        }
        
        cell?.marketTitle.text = mCollateralParam!.getDpMarketId()
        WUtils.showRiskRate(riskRate, cell!.riskScore, _rateIamg: cell!.riskRateImg)
        
        cell?.debtValueTitle.text = String(format: NSLocalizedString("debt_value_format", comment: ""), mPDenom.uppercased())
        cell?.debtValue.attributedText = WUtils.getDPRawDollor(mMyCdpStatus!.result.getDpEstimatedTotalDebtValue(mPDenom, mCollateralParam!).stringValue, 2, cell!.debtValue.font)
        
        cell?.collateralValueTitle.text = String(format: NSLocalizedString("collateral_value_format", comment: ""), mPDenom.uppercased())
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(mMyCdpStatus!.result.getDpCollateralValue(mPDenom).stringValue, 2, cell!.collateralValue.font)
        
        cell?.minCollateralRate.attributedText = WUtils.displayPercent(mCollateralParam!.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
        cell?.stabilityFee.attributedText = WUtils.displayPercent(mCollateralParam!.getDpStabilityFee(), font: cell!.stabilityFee.font)
        cell?.liquidationPenalty.attributedText = WUtils.displayPercent(mCollateralParam!.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
        
        cell?.currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), mCDenom.uppercased())
        cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)
        
        cell?.liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), mCDenom.uppercased())
        cell?.liquidationPrice.attributedText = WUtils.getDPRawDollor(liquidationPrice.stringValue, 4, cell!.liquidationPrice.font)
        
        cell?.systemMax.attributedText = WUtils.displayAmount2(mCdpParam?.result.getGlobalDebtAmount().stringValue, cell!.systemMax.font, 6, 6)
        cell?.remainCap.attributedText = WUtils.displayAmount2(mCdpParam!.result.getGlobalDebtAmount().subtracting(mKavaTotalSupply?.getDebtAmount() ?? NSDecimalNumber.zero).stringValue, cell!.remainCap.font, 6, 6)
        
        let url = KAVA_CDP_MARKET_IMG_URL + mCollateralParam!.getMarketImgPath() + ".png"
        cell?.marketImg.af_setImage(withURL: URL(string: url)!)
        cell?.helpCollateralRate = {
            self.onShowSimpleHelp(NSLocalizedString("help_collateral_rate_title", comment: ""), NSLocalizedString("help_collateral_rate_msg", comment: ""))
        }
        cell?.helpStabilityFee = {
            self.onShowSimpleHelp(NSLocalizedString("help_stability_fee_title", comment: ""), NSLocalizedString("help_stability_fee_msg", comment: ""))
        }
        cell?.helpLiquidationPenalty = {
            self.onShowSimpleHelp(NSLocalizedString("help_liquidation_penalty_title", comment: ""), NSLocalizedString("help_liquidation_penalty_msg", comment: ""))
        }
        cell?.helpRiskScore = {
            let popupVC = RiskCheckPopupViewController(nibName: "RiskCheckPopupViewController", bundle: nil)
            popupVC.type = popupVC.RISK_POPUP_CHECK
            popupVC.cDenom = self.mCDenom
            popupVC.DNcurrentPrice = self.currentPrice
            popupVC.DNliquidationPrice = self.liquidationPrice
            popupVC.DNriskRate = self.riskRate
            let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
            cardPopup.show(onViewController: self)
        }
        return cell!
    }
    
    func onSetMyActionItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell  {
        let cell:CdpDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyActionCell") as? CdpDetailMyActionCell
        
        cell?.collateralDenom.text = mCDenom.uppercased()
        let selfDepositAmount = mMyCdpDeposit!.getSelfDeposit(account!.account_address)
        let selfDepositValue = selfDepositAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralSelfAmount.attributedText = WUtils.displayAmount2(selfDepositAmount.stringValue, cell!.collateralSelfAmount.font!, cDpDecimal, cDpDecimal)
        cell?.collateralSelfValue.attributedText = WUtils.getDPRawDollor(selfDepositValue.stringValue, 2, cell!.collateralSelfValue.font)
        
        let totalDepositAmount = mMyCdpStatus!.result.getTotalCollateralAmount()
        let totalDepositValue = totalDepositAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralTotalAmount.attributedText = WUtils.displayAmount2(totalDepositAmount.stringValue, cell!.collateralTotalAmount.font!, cDpDecimal, cDpDecimal)
        cell?.collateralTotalValue.attributedText = WUtils.getDPRawDollor(totalDepositValue.stringValue, 2, cell!.collateralTotalValue.font)
        
        cell?.collateralWithdrawableTitle.text = String(format: NSLocalizedString("withdrawable_format", comment: ""), mCDenom.uppercased())
        let maxWithdrawableAmount = mMyCdpStatus!.result.getWithdrawableAmount(mCDenom, mPDenom, mCollateralParam!, currentPrice, selfDepositAmount)
        let maxWithdrawableValue = maxWithdrawableAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
//        print("maxWithdrawableAmount ", maxWithdrawableAmount)
//        print("maxWithdrawableValue ", maxWithdrawableValue)
        
        cell?.collateralWithdrawableAmount.attributedText = WUtils.displayAmount2(maxWithdrawableAmount.stringValue, cell!.collateralWithdrawableAmount.font!, cDpDecimal, cDpDecimal)
        cell?.collateralWithdrawableValue.attributedText = WUtils.getDPRawDollor(maxWithdrawableValue.stringValue, 2, cell!.collateralWithdrawableValue.font)
        
        cell?.depositBtn.setTitle(String(format: NSLocalizedString("str_deposit", comment: ""), mCDenom.uppercased()), for: .normal)
        cell?.withdrawBtn.setTitle(String(format: NSLocalizedString("str_withdraw", comment: ""), mCDenom.uppercased()), for: .normal)
        
        cell?.helpCollateralSelf = {
            self.onShowSimpleHelp(NSLocalizedString("help_self_deposited_title", comment: ""), String(format: NSLocalizedString("help_self_deposited_msg", comment: ""), self.mCDenom.uppercased()))
        }
        cell?.helpCollateralTotal = {
            self.onShowSimpleHelp(NSLocalizedString("help_total_deposited_title", comment: ""), String(format: NSLocalizedString("help_total_deposited_msg", comment: ""), self.mCDenom.uppercased()))
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
        
        
        cell?.principalDenom.text = mPDenom.uppercased()
        let rawPricipalAmount = mMyCdpStatus!.result.cdp.getRawPrincipalAmount()
        cell?.principalAmount.attributedText = WUtils.displayAmount2(rawPricipalAmount.stringValue, cell!.principalAmount.font!, pDpDecimal, pDpDecimal)
        cell?.principalValue.attributedText = WUtils.getDPRawDollor(rawPricipalAmount.multiplying(byPowerOf10: -pDpDecimal).stringValue, 2, cell!.principalValue.font)
        
        let totalFeeAmount = mMyCdpStatus!.result.cdp.getEstimatedTotalFee(mCollateralParam!)
        cell?.interestAmount.attributedText = WUtils.displayAmount2(totalFeeAmount.stringValue, cell!.interestAmount.font!, pDpDecimal, pDpDecimal)
        cell?.interestValue.attributedText = WUtils.getDPRawDollor(totalFeeAmount.multiplying(byPowerOf10: -pDpDecimal).stringValue, 2, cell!.principalValue.font)
        
        let moreDebtAmount = mMyCdpStatus!.result.getMoreLoanableAmount(mCollateralParam!)
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
        
        cell?.collateralImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mCDenom + ".png")!)
        cell?.principalImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mPDenom + ".png")!)
        return cell!
    }
    
    func onSetAssetsItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpDetailAssetsCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailAssetsCell") as? CdpDetailAssetsCell
        
        cell?.collateralDenom.text = mCDenom.uppercased()
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(cAvailable.stringValue, cell!.collateralAmount.font!, cDpDecimal, cDpDecimal)
        let collateralValue = cAvailable.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(collateralValue.stringValue, 2, cell!.collateralValue.font)
        
        cell?.principalDenom.text = mPDenom.uppercased()
        cell?.principalAmount.attributedText = WUtils.displayAmount2(pAvailable.stringValue, cell!.principalAmount.font!, pDpDecimal, pDpDecimal)
        let principalValue = pAvailable.multiplying(byPowerOf10: -pDpDecimal)
        cell?.principalValue.attributedText = WUtils.getDPRawDollor(principalValue.stringValue, 2, cell!.principalValue.font)
        
        cell?.kavaAmount.attributedText = WUtils.displayAmount2(kAvailable.stringValue, cell!.kavaAmount.font!, kDpDecimal, kDpDecimal)
        let kavaValue = kAvailable.multiplying(byPowerOf10: -kDpDecimal).multiplying(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler2Down)
        cell?.kavaValue.attributedText = WUtils.getDPRawDollor(kavaValue.stringValue, 2, cell!.kavaValue.font)
        
        cell?.collateralImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mCDenom + ".png")!)
        cell?.principalImg.af_setImage(withURL: URL(string: KAVA_COIN_IMG_URL + mPDenom + ".png")!)
        return cell!
    }

    @IBAction func onClickCreateCdp(_ sender: UIButton) {
        if (!onCommonCheck()) { return }
        let debtFloor = NSDecimalNumber.init(string: mCdpParam!.result.debt_param?.debt_floor)
        let cMinAmount = debtFloor.multiplying(byPowerOf10: cDpDecimal - pDpDecimal).multiplying(by: NSDecimalNumber.init(string: "1.05263157895")).multiplying(by: mCollateralParam!.getLiquidationRatio()).dividing(by: currentPrice, withBehavior: WUtils.handler0Up)
        if (cAvailable.compare(cMinAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
            return
        }
        if (!onCheckSupply()) {
            self.onShowToast(NSLocalizedString("error_no_more_debt_kava", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_CREATE_CDP
        txVC.cDenom = self.mCDenom
        txVC.mMarketID = self.mMarketID
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
        txVC.cDenom = mCDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickWithdraw() {
        if (!onCommonCheck()) { return }
        let selfDepositAmount = mMyCdpDeposit!.getSelfDeposit(account!.account_address)
        let maxWithdrawableAmount = mMyCdpStatus!.result.getWithdrawableAmount(mCDenom, mPDenom, mCollateralParam!, currentPrice, selfDepositAmount)
        if (maxWithdrawableAmount.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_not_enought_withdraw_asset", comment: ""))
            return
        }
         
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_WITHDRAW_CDP
        txVC.cDenom = mCDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onClickDrawDebt() {
        if (!onCommonCheck()) { return }
        if (mMyCdpStatus!.result.getMoreLoanableAmount(mCollateralParam!).compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_can_not_draw_debt", comment: ""))
            return
        }
        if (!onCheckSupply()) {
            self.onShowToast(NSLocalizedString("error_no_more_debt_kava", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_DRAWDEBT_CDP
        txVC.cDenom = self.mCDenom
        txVC.mMarketID = self.mMarketID
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
        let debtFloor = NSDecimalNumber.init(string: mCdpParam!.result.debt_param?.debt_floor)
        let rawDebt = mMyCdpStatus!.result.cdp.getRawPrincipalAmount()
        let totalDebt = mMyCdpStatus!.result.cdp.getEstimatedTotalDebt(mCollateralParam!)
        if (totalDebt.compare(pAvailable).rawValue > 0) { repayAll = false }
        if (rawDebt.compare(debtFloor).rawValue <= 0) { repayPart = false }
        if (!repayAll && !repayPart) {
            self.onShowToast(NSLocalizedString("error_can_not_repay", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_REPAYDEBT_CDP
        txVC.cDenom = mCDenom
        txVC.mMarketID = mMarketID
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onCommonCheck() -> Bool {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return false
        }
        if (mCdpParam!.result.circuit_breaker) {
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
        self.mFetchCnt = 3
        onFetchOwenCdp(account!, self.mCollateralParam!)
        onFetchCdpDeposit(account!, self.mCollateralParam!)
        onFetchKavaPrice(self.mMarketID)
        onFetchTotalSupply()
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if ((mCollateralParam) == nil) {
                print("ERROR");
                return
            }
            mPDenom = mCollateralParam!.getpDenom()
            cDpDecimal = WUtils.getKavaCoinDecimal(mCDenom)
            pDpDecimal = WUtils.getKavaCoinDecimal(mPDenom)
            kDpDecimal = WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)
            cAvailable = account!.getTokenBalance(mCDenom)
            pAvailable = account!.getTokenBalance(mPDenom)
            kAvailable = account!.getTokenBalance(KAVA_MAIN_DENOM)
            currentPrice = NSDecimalNumber.init(string: mPrice.result.price)
//            print("cAvailable ", cAvailable)
//            print("pAvailable ", pAvailable)
//            print("kAvailable ", kAvailable)
            
            if ((mMyCdpStatus) != nil) {
                emptyConstraint?.isActive = false
                owenConstraint?.isActive = true
                createCdpBtn.isHidden = true
            } else {
                emptyConstraint?.isActive = true
                owenConstraint?.isActive = false
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
    
    func onFetchOwenCdp(_ account:Account, _ collateralParam: CdpParam.CollateralParam) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_OWEN + account.account_address + "/" + collateralParam.denom
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CDP_OWEN + account.account_address + "/" + collateralParam.type
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
                    self.mMyCdpStatus = CdpOwen.init(responseData)
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchOwenCdp ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchCdpDeposit(_ account:Account, _ collateralParam: CdpParam.CollateralParam) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_DEPOSIT + account.account_address + "/" + collateralParam.denom
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CDP_DEPOSIT + account.account_address + "/" + collateralParam.type
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
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_TOKEN_PRICE + market
        } else if (chainType == ChainType.KAVA_TEST) {
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
    
    func onFetchTotalSupply() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CHECK_SUPPLY
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CHECK_SUPPLY
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                self.mKavaTotalSupply = KavaSupply.init(info)
                
            case .failure(let error):
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                return
            }
            self.onFetchFinished()
        }
    }
    
    func onCheckSupply() -> Bool {
        if (mCdpParam!.result.getGlobalDebtAmount().compare(mKavaTotalSupply!.getDebtAmount()).rawValue <= 0 ) {
            return false;
        }
        return true
    }
}
