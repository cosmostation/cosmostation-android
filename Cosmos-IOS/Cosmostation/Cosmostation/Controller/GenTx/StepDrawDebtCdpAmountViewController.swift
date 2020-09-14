//
//  StepDrawDebtCdpAmountViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/01.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepDrawDebtCdpAmountViewController: BaseViewController, UITextFieldDelegate, SBCardPopupDelegate{
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var pDenomImg: UIImageView!
    @IBOutlet weak var pDenomLabel: UILabel!
    @IBOutlet weak var pAmountInput: AmountInputTextField!
    @IBOutlet weak var btnPAmountClear: UIButton!
    @IBOutlet weak var pAvailabeMinLabel: UILabel!
    @IBOutlet weak var pAvailabeMaxLabel: UILabel!
    @IBOutlet weak var pAvailableDenom: UILabel!

    @IBOutlet weak var beforeSafeTxt: UILabel!
    @IBOutlet weak var beforeSafeRate: UILabel!
    @IBOutlet weak var afterSafeTxt: UILabel!
    @IBOutlet weak var afterSafeRate: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    
    var cDenom: String = ""
    var pDenom: String = ""
    var cDpDecimal:Int16 = 6
    var pDpDecimal:Int16 = 6
    var mMarketID: String = ""
    
    var cdpParam:CdpParam?
    var cParam: CdpParam.CollateralParam?
    var mMyCdps: CdpOwen?
    var mMyCdpDeposit: CdpDeposits?
    var mPrice: KavaTokenPrice?
    
    var currentPrice: NSDecimalNumber = NSDecimalNumber.zero
    var beforeLiquidationPrice: NSDecimalNumber = NSDecimalNumber.zero
    var afterLiquidationPrice: NSDecimalNumber = NSDecimalNumber.zero
    var beforeRiskRate: NSDecimalNumber = NSDecimalNumber.zero
    var afterRiskRate: NSDecimalNumber = NSDecimalNumber.zero
    
    var pMaxAmount: NSDecimalNumber = NSDecimalNumber.zero
    var pMinAmount: NSDecimalNumber = NSDecimalNumber.zero
    var toPAmount: NSDecimalNumber = NSDecimalNumber.zero
    var sumPAmount: NSDecimalNumber = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        pageHolderVC = self.parent as? StepGenTxViewController
        cDenom = pageHolderVC.cDenom!
        mMarketID = pageHolderVC.mMarketID!
        
        self.loadingImg.onStartAnimation()
        self.onFetchCdpData()
        
        pAmountInput.delegate = self
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }

    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let text = textField.text else { return true }
        if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ".")) { return false }
        if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ",")) { return false }
        if let index = text.range(of: ".")?.upperBound {
            if(text.substring(from: index).count > (pDpDecimal - 1) && range.length == 0) { return false }
        }
        if let index = text.range(of: ",")?.upperBound {
            if(text.substring(from: index).count > (pDpDecimal - 1) && range.length == 0) { return false }
        }
        return true
    }
    
    @IBAction func AmountChangedP(_ sender: AmountInputTextField) {
        guard let text = sender.text?.trimmingCharacters(in: .whitespaces) else {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (text.count == 0) {
            sender.layer.borderColor = UIColor.white.cgColor
            return
        }
        let userInput = WUtils.stringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMaxAmount).rawValue > 0) {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMinAmount).rawValue < 0) {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        sender.layer.borderColor = UIColor.white.cgColor
        onUpdateNextBtn()
    }
    
    @IBAction func onClickPAmountClear(_ sender: UIButton) {
        pAmountInput.text = ""
        onUpdateNextBtn()
    }
    
    @IBAction func onClickPMin(_ sender: UIButton) {
        let calValue = pMinAmount.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        AmountChangedP(pAmountInput)
    }
    
    @IBAction func onClickP1_4(_ sender: UIButton) {
        var calValue = pMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25"))
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        AmountChangedP(pAmountInput)
    }
    
    @IBAction func onClickPHalf(_ sender: UIButton) {
        var calValue = pMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.5"))
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        AmountChangedP(pAmountInput)
    }
    
    @IBAction func onClickPAdd3_4(_ sender: UIButton) {
        var calValue = pMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75"))
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        AmountChangedP(pAmountInput)
    }
    
    @IBAction func onClickPMax(_ sender: UIButton) {
        let maxValue = pMaxAmount.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(maxValue, pDpDecimal)
        AmountChangedP(pAmountInput)
    }
    

    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (isValiadPAmount()) {
            view.endEditing(true)
            let popupVC = RiskCheckPopupViewController(nibName: "RiskCheckPopupViewController", bundle: nil)
            popupVC.type = popupVC.RISK_POPUP_CHANGE
            popupVC.cDenom = self.cDenom
            popupVC.DNcurrentPrice = self.currentPrice
            popupVC.DNbeforeLiquidationPrice = self.beforeLiquidationPrice
            popupVC.DNbeforeRiskRate = self.beforeRiskRate
            popupVC.DNafterLiquidationPrice = self.afterLiquidationPrice
            popupVC.DNafterRiskRate = self.afterRiskRate
            
            let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
            cardPopup.resultDelegate = self
            cardPopup.show(onViewController: self)

        } else {
            self.onShowToast(NSLocalizedString("error_amount", comment: ""))
        }
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(300), execute: {
            if(result == 10) {
                let pCoin = Coin.init(self.pDenom, self.toPAmount.stringValue)
                self.pageHolderVC.mPrincipal = pCoin

                self.pageHolderVC.currentPrice = self.currentPrice
                self.pageHolderVC.beforeLiquidationPrice = self.beforeLiquidationPrice
                self.pageHolderVC.afterLiquidationPrice = self.afterLiquidationPrice
                self.pageHolderVC.beforeRiskRate = self.beforeRiskRate
                self.pageHolderVC.afterRiskRate = self.afterRiskRate
                self.pageHolderVC.pDenom = self.pDenom
                self.pageHolderVC.totalLoanAmount = self.sumPAmount

                self.btnCancel.isUserInteractionEnabled = false
                self.btnNext.isUserInteractionEnabled = false
                self.pageHolderVC.onNextPage()
            }
        })
    }
    
    func isValiadPAmount() -> Bool {
        let text = pAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMaxAmount).rawValue > 0 ||
            userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMinAmount).rawValue < 0) {
            return false
        }
        
        toPAmount = userInput.multiplying(byPowerOf10: pDpDecimal)
        sumPAmount = mMyCdps!.result.cdp.getEstimatedTotalDebt(cParam!).adding(toPAmount)
        let collateralAmount = mMyCdps!.result.getTotalCollateralAmount().multiplying(byPowerOf10: -cDpDecimal)
        let rawDebtAmount = sumPAmount.multiplying(by: cParam!.getLiquidationRatio()).multiplying(byPowerOf10: -pDpDecimal)
        afterLiquidationPrice = rawDebtAmount.dividing(by: collateralAmount, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        afterRiskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(afterLiquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))
        
        if (SHOW_LOG) {
            print("currentPrice ", currentPrice)
            print("afterLiquidationPrice ", afterLiquidationPrice)
            print("afterRiskRate ", afterRiskRate)
        }
        return true
    }
    
    
    func onUpdateNextBtn() {
        if (!isValiadPAmount()) {
            btnNext.backgroundColor = UIColor.clear
            btnNext.setTitle(NSLocalizedString("tx_next", comment: ""), for: .normal)
            btnNext.setTitleColor(COLOR_PHOTON, for: .normal)
            btnNext.layer.borderWidth = 1.0
            afterSafeRate.isHidden = true
            afterSafeTxt.isHidden = true
        } else {
            btnNext.setTitleColor(UIColor.black, for: .normal)
            btnNext.layer.borderWidth = 0.0
            if (afterRiskRate.doubleValue <= 50) {
                btnNext.backgroundColor = COLOR_CDP_SAFE
//                btnNext.setTitle(afterRiskRate.stringValue + " SAFE", for: .normal)
                btnNext.setTitle("SAFE", for: .normal)
                
            } else if (afterRiskRate.doubleValue < 80) {
                btnNext.backgroundColor = COLOR_CDP_STABLE
//                btnNext.setTitle(afterRiskRate.stringValue + " STABLE", for: .normal)
                btnNext.setTitle("STABLE", for: .normal)
                
            } else {
                btnNext.backgroundColor = COLOR_CDP_DANGER
//                btnNext.setTitle(afterRiskRate.stringValue + " DANGER", for: .normal)
                btnNext.setTitle("DANGER", for: .normal)
            }
            WUtils.showRiskRate2(afterRiskRate, afterSafeRate, afterSafeTxt)
            afterSafeRate.isHidden = false
            afterSafeTxt.isHidden = false
        }
    }
    
    
    
    
    
    
    
    var mFetchCnt = 0
    func onFetchCdpData() {
        self.mFetchCnt = 4
        onFetchCdpParam()
        onFetchKavaPrice(self.mMarketID)
        onFetchOwenCdp(account!, self.cDenom)
        onFetchCdpDeposit(account!, self.cDenom)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if (cParam == nil || mPrice == nil || mMyCdps == nil) {
                print("ERROR");
                return
            }
            pDenom = cParam!.getpDenom()
            
            cDpDecimal = WUtils.getKavaCoinDecimal(cDenom)
            pDpDecimal = WUtils.getKavaCoinDecimal(pDenom)
            
            currentPrice = NSDecimalNumber.init(string: mPrice?.result.price)
            
            //calculate min debtable amount from current state.(if current debt under debt_floor.)
            let currentPAmount = mMyCdps?.result.cdp.getRawPrincipalAmount()
            let debtFloor = NSDecimalNumber.init(string: cdpParam!.result.debt_param?.debt_floor)
            
            if (currentPAmount!.compare(debtFloor).rawValue > 0) {
                pMinAmount = NSDecimalNumber.one
            } else {
                pMinAmount = debtFloor.subtracting(currentPAmount!)
            }
            
            //calculate max debtable amount from current state.
            pMaxAmount = mMyCdps!.result.getMoreLoanableAmount(cParam!)
            
            pAvailabeMinLabel.attributedText = WUtils.displayAmount2(pMinAmount.stringValue, pAvailabeMinLabel.font!, pDpDecimal, pDpDecimal)
            pAvailabeMaxLabel.attributedText = WUtils.displayAmount2(pMaxAmount.stringValue, pAvailabeMaxLabel.font!, pDpDecimal, pDpDecimal)
            
            beforeLiquidationPrice = mMyCdps!.result.getLiquidationPrice(cDenom, pDenom, cParam!)
            beforeRiskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(beforeLiquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))
            WUtils.showRiskRate2(beforeRiskRate, beforeSafeRate, beforeSafeTxt)
            
            if (SHOW_LOG) {
                print("currentPrice ", currentPrice)
                print("beforeLiquidationPrice ", beforeLiquidationPrice)
                print("beforeRiskRate ", beforeRiskRate)
            }
            
            pDenomLabel.text = pDenom.uppercased()
            pAvailableDenom.text = pDenom.uppercased()
            Alamofire.request(KAVA_COIN_IMG_URL + pDenom + ".png", method: .get).responseImage { response  in
                guard let image = response.result.value else { return }
                self.pDenomImg.image = image
            }
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
        }
    }
    
    
    func onFetchCdpParam() {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_PARAM
        } else if (chainType == ChainType.KAVA_TEST) {
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
    
    func onFetchOwenCdp(_ account:Account, _ denom:String) {
        var url: String?
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_OWEN + account.account_address + "/" + denom
        } else if (chainType == ChainType.KAVA_TEST) {
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
        if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_CDP_DEPOSIT + account.account_address + "/" + denom
        } else if (chainType == ChainType.KAVA_TEST) {
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

}
