//
//  StepCreateCpdAmountViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepCreateCpdAmountViewController: BaseViewController, UITextFieldDelegate, SBCardPopupDelegate{
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var cDenomImg: UIImageView!
    @IBOutlet weak var cDenomLabel: UILabel!
    @IBOutlet weak var cAmountInput: AmountInputTextField!
    @IBOutlet weak var cAvailabeMaxLabel: UILabel!
    @IBOutlet weak var cAvailabeDashLabel: UILabel!
    @IBOutlet weak var cAvailabeMinLabel: UILabel!
    @IBOutlet weak var cAvailableDenom: UILabel!
    @IBOutlet weak var cDepositValueTitle: UILabel!
    @IBOutlet weak var cDepositValue: UILabel!
    @IBOutlet weak var cControlStackView: UIStackView!
    
    @IBOutlet weak var pDenomImg: UIImageView!
    @IBOutlet weak var pDenomLabel: UILabel!
    @IBOutlet weak var pAmountInput: AmountInputTextField!
    @IBOutlet weak var btnPAmountClear: UIButton!
    @IBOutlet weak var pAvailabeMaxLabel: UILabel!
    @IBOutlet weak var pAvailabeDashLabel: UILabel!
    @IBOutlet weak var pAvailabeMinLabel: UILabel!
    @IBOutlet weak var pAvailableDenom: UILabel!
    @IBOutlet weak var pControlStackView: UIStackView!
    
    
    var pageHolderVC: StepGenTxViewController!
    
    var isPrincipal:Bool = false
    var mCDenom: String = ""
    var mPDenom: String = ""
    var cDpDecimal:Int16 = 6
    var pDpDecimal:Int16 = 6
    var mMarketID: String = ""
    var mCdpParam:CdpParam?
    var mCollateralParam: CdpParam.CollateralParam?
    var mMyCdpStatus: CdpOwen?
    var mPrice: KavaTokenPrice?
    
    var currentPrice: NSDecimalNumber = NSDecimalNumber.zero
    var liquidationPrice: NSDecimalNumber = NSDecimalNumber.zero
    var riskRate: NSDecimalNumber = NSDecimalNumber.zero
    
    var cMaxAmount: NSDecimalNumber = NSDecimalNumber.zero
    var cMinAmount: NSDecimalNumber = NSDecimalNumber.zero
    var pMaxAmount: NSDecimalNumber = NSDecimalNumber.zero
    var pMinAmount: NSDecimalNumber = NSDecimalNumber.zero
    
    var toCAmount: NSDecimalNumber = NSDecimalNumber.zero
    var toPAmount: NSDecimalNumber = NSDecimalNumber.zero
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        pageHolderVC = self.parent as? StepGenTxViewController
        mCDenom = pageHolderVC.mCDenom!
        mMarketID = pageHolderVC.mMarketID!
        mCdpParam = BaseData.instance.mCdpParam
        mCollateralParam = mCdpParam?.result.getcParam(mCDenom)
        
        self.loadingImg.onStartAnimation()
        self.onFetchCdpData()
        
        cAmountInput.delegate = self
        pAmountInput.delegate = self
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        if (!isPrincipal) {
            cAvailabeMaxLabel.isHidden = false
            cAvailabeDashLabel.isHidden = false
            cAvailabeMinLabel.isHidden = false
            cAvailableDenom.isHidden = false
            cDepositValueTitle.isHidden = true
            cDepositValue.isHidden = true
            cControlStackView.isHidden = false
            
            pDenomImg.isHidden = true
            pDenomLabel.isHidden = true
            pAmountInput.isHidden = true
            btnPAmountClear.isHidden = true
            pAvailabeMaxLabel.isHidden = true
            pAvailabeDashLabel.isHidden = true
            pAvailabeMinLabel.isHidden = true
            
            pAvailableDenom.isHidden = true
            pControlStackView.isHidden = true
            
        } else {
            toCAmount = WUtils.localeStringToDecimal(cAmountInput.text?.trimmingCharacters(in: .whitespaces)).multiplying(byPowerOf10: cDpDecimal)
            let toCValue = toCAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
            cDepositValue.attributedText = WUtils.getDPRawDollor(toCValue.stringValue, 2, cDepositValue.font)
            pMaxAmount = toCAmount.multiplying(byPowerOf10: pDpDecimal - cDpDecimal).multiplying(by: NSDecimalNumber.init(string: "0.95")).multiplying(by: currentPrice).dividing(by: mCollateralParam!.getLiquidationRatio(), withBehavior: WUtils.handler0Down)
        
            pAvailabeMinLabel.attributedText = WUtils.displayAmount2(pMinAmount.stringValue, pAvailabeMinLabel.font!, pDpDecimal, pDpDecimal)
            pAvailabeMaxLabel.attributedText = WUtils.displayAmount2(pMaxAmount.stringValue, pAvailabeMinLabel.font!, pDpDecimal, pDpDecimal)
            
            cAvailabeMaxLabel.isHidden = true
            cAvailabeDashLabel.isHidden = true
            cAvailabeMinLabel.isHidden = true
            cAvailableDenom.isHidden = true
            cDepositValueTitle.isHidden = false
            cDepositValue.isHidden = false
            cControlStackView.isHidden = true
            
            pDenomImg.isHidden = false
            pDenomLabel.isHidden = false
            pAmountInput.isHidden = false
            btnPAmountClear.isHidden = false
            pAvailabeMaxLabel.isHidden = false
            pAvailabeDashLabel.isHidden = false
            pAvailabeMinLabel.isHidden = false
            pAvailableDenom.isHidden = false
            pControlStackView.isHidden = false
            
        }
        onUpdateNextBtn()
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if (textField == cAmountInput) {
            isPrincipal = false
            pAmountInput.text = ""
            pMaxAmount = NSDecimalNumber.zero
            onUpdateView();
        }
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let text = textField.text else { return true }
        if (text.contains(".") && string.contains(".") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ".")) { return false }
        if (text.contains(",") && string.contains(",") && range.length == 0) { return false }
        if (text.count == 0 && string.starts(with: ",")) { return false }
        if (textField == cAmountInput) {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (cDpDecimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (cDpDecimal - 1) && range.length == 0) { return false }
            }
        } else {
            if let index = text.range(of: ".")?.upperBound {
                if(text.substring(from: index).count > (pDpDecimal - 1) && range.length == 0) { return false }
            }
            if let index = text.range(of: ",")?.upperBound {
                if(text.substring(from: index).count > (pDpDecimal - 1) && range.length == 0) { return false }
            }
        }
        return true
    }
    
    
    @IBAction func AmountChangedC(_ sender: AmountInputTextField) {
        guard let text = sender.text?.trimmingCharacters(in: .whitespaces) else {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (text.count == 0) {
            sender.layer.borderColor = UIColor.white.cgColor
            return
        }
        let userInput = WUtils.localeStringToDecimal(text)
        if (text.count > 1 && userInput == NSDecimalNumber.zero) {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: cDpDecimal).compare(cMaxAmount).rawValue > 0) {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        if (userInput.multiplying(byPowerOf10: cDpDecimal).compare(cMinAmount).rawValue < 0) {
            sender.layer.borderColor = UIColor.init(hexString: "f31963").cgColor
            return
        }
        sender.layer.borderColor = UIColor.white.cgColor
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
        let userInput = WUtils.localeStringToDecimal(text)
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
    
    @IBAction func onClickCAmountClear(_ sender: UIButton) {
        cAmountInput.text = ""
        pAmountInput.text = ""
        isPrincipal = false
        pMaxAmount = NSDecimalNumber.zero
        onUpdateView();
    }
    
    @IBAction func onClickCMin(_ sender: UIButton) {
        let calValue = cMinAmount.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickC1_4(_ sender: UIButton) {
        var calValue = cMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25"))
        if (calValue.compare(cMinAmount).rawValue < 0) {
            calValue = cMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickCHalf(_ sender: UIButton) {
        var calValue = cMaxAmount.dividing(by: NSDecimalNumber(2))
        if (calValue.compare(cMinAmount).rawValue < 0) {
            calValue = cMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickC3_4(_ sender: UIButton) {
        var calValue = cMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75"))
        if (calValue.compare(cMinAmount).rawValue < 0) {
            calValue = cMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickCMax(_ sender: UIButton) {
        let maxValue = cMaxAmount.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.decimalNumberToLocaleString(maxValue, cDpDecimal)
    }
    
    

    @IBAction func onClickPAmountClear(_ sender: UIButton) {
        pAmountInput.text = ""
    }
    
    @IBAction func onClickPMin(_ sender: UIButton) {
        let calValue = pMinAmount.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, pDpDecimal)
        onUpdateNextBtn()
    }
    
    @IBAction func onClickP20(_ sender: UIButton) {
        var calValue = toCAmount.multiplying(byPowerOf10: pDpDecimal - cDpDecimal).multiplying(by: NSDecimalNumber.init(string: "0.2")).multiplying(by: currentPrice).dividing(by: mCollateralParam!.getLiquidationRatio(), withBehavior: WUtils.handler0Down)
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, pDpDecimal)
        onUpdateNextBtn()
    }
    
    @IBAction func onClickP50(_ sender: UIButton) {
        var calValue = toCAmount.multiplying(byPowerOf10: pDpDecimal - cDpDecimal).multiplying(by: NSDecimalNumber.init(string: "0.5")).multiplying(by: currentPrice).dividing(by: mCollateralParam!.getLiquidationRatio(), withBehavior: WUtils.handler0Down)
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, pDpDecimal)
        onUpdateNextBtn()
        
    }
    
    @IBAction func onClickP70(_ sender: UIButton) {
        var calValue = toCAmount.multiplying(byPowerOf10: pDpDecimal - cDpDecimal).multiplying(by: NSDecimalNumber.init(string: "0.7")).multiplying(by: currentPrice).dividing(by: mCollateralParam!.getLiquidationRatio(), withBehavior: WUtils.handler0Down)
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.decimalNumberToLocaleString(calValue, pDpDecimal)
        onUpdateNextBtn()
        
    }
    
    @IBAction func onClickPMax(_ sender: UIButton) {
        let maxValue = pMaxAmount.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.decimalNumberToLocaleString(maxValue, pDpDecimal)
        onUpdateNextBtn()
    }
    

    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (!isPrincipal) {
            if (isValiadCAmount()) {
                isPrincipal = true
                onUpdateView();
                pAmountInput.becomeFirstResponder()
            } else {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            }
            
        } else {
            if (isValiadPAmount()) {
                view.endEditing(true)
                let popupVC = RiskCheckPopupViewController(nibName: "RiskCheckPopupViewController", bundle: nil)
                popupVC.type = popupVC.RISK_POPUP_CREATE
                popupVC.cDenom = self.mCDenom
                popupVC.DNcurrentPrice = self.currentPrice
                popupVC.DNliquidationPrice = self.liquidationPrice
                popupVC.DNriskRate = self.riskRate
                let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
                cardPopup.resultDelegate = self
                cardPopup.show(onViewController: self)
                
            } else {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            }
        }
        
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(300), execute: {
            if(result == 10) {
                let cCoin = Coin.init(self.mCDenom, self.toCAmount.stringValue)
                self.pageHolderVC.mCollateral = cCoin

                let pCoin = Coin.init(self.mPDenom, self.toPAmount.stringValue)
                self.pageHolderVC.mPrincipal = pCoin

                self.pageHolderVC.currentPrice = self.currentPrice
                self.pageHolderVC.liquidationPrice = self.liquidationPrice
                self.pageHolderVC.riskRate = self.riskRate
                self.pageHolderVC.pDenom = self.mPDenom
                self.pageHolderVC.mCollateralParam = self.mCollateralParam

                self.btnCancel.isUserInteractionEnabled = false
                self.btnNext.isUserInteractionEnabled = false
                self.pageHolderVC.onNextPage()
            }
        })
    }
    
    func isValiadCAmount() -> Bool {
        let text = cAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: cDpDecimal).compare(cMaxAmount).rawValue > 0 ||
            userInput.multiplying(byPowerOf10: cDpDecimal).compare(cMinAmount).rawValue < 0) {
            return false
        }
        return true
    }
    
    func isValiadPAmount() -> Bool {
        let text = pAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.localeStringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMaxAmount).rawValue > 0 ||
            userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMinAmount).rawValue < 0) {
            return false
        }
        toPAmount = userInput.multiplying(byPowerOf10: pDpDecimal)
        
        let collateralAmount = toCAmount.multiplying(byPowerOf10: -cDpDecimal)
        let rawDebtAmount = toPAmount.multiplying(by: mCollateralParam!.getLiquidationRatio()).multiplying(byPowerOf10: -pDpDecimal)
        liquidationPrice = rawDebtAmount.dividing(by: collateralAmount, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        riskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(liquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))
        
        if (SHOW_LOG) {
            print("toPAmount ", toPAmount)
            print("collateralAmount ", collateralAmount)
            print("rawDebtAmount ", rawDebtAmount)
            print("liquidationPrice ", liquidationPrice)
            print("riskRate ", riskRate)
        }
        return true
    }
    
    func onUpdateNextBtn() {
        if (!isPrincipal) {
            btnNext.backgroundColor = UIColor.clear
            btnNext.setTitle(NSLocalizedString("tx_next", comment: ""), for: .normal)
            btnNext.setTitleColor(COLOR_PHOTON, for: .normal)
            btnNext.layer.borderWidth = 1.0
            
        } else {
            if (!isValiadPAmount()) {
                btnNext.backgroundColor = UIColor.clear
                btnNext.setTitle(NSLocalizedString("tx_next", comment: ""), for: .normal)
                btnNext.setTitleColor(COLOR_PHOTON, for: .normal)
                btnNext.layer.borderWidth = 1.0
                
            } else {
                btnNext.setTitleColor(UIColor.black, for: .normal)
                btnNext.layer.borderWidth = 0.0
                if (riskRate.doubleValue <= 50) {
                    btnNext.backgroundColor = COLOR_CDP_SAFE
                    btnNext.setTitle(riskRate.stringValue + " SAFE", for: .normal)
                    
                } else if (riskRate.doubleValue < 80) {
                    btnNext.backgroundColor = COLOR_CDP_STABLE
                    btnNext.setTitle(riskRate.stringValue + " STABLE", for: .normal)
                    
                } else {
                    btnNext.backgroundColor = COLOR_CDP_DANGER
                    btnNext.setTitle(riskRate.stringValue + " DANGER", for: .normal)
                }
            }
        }
    }
    
    var mFetchCnt = 0
    func onFetchCdpData() {
        self.mFetchCnt = 1
        onFetchKavaPrice(self.mMarketID)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if (mCollateralParam == nil || mPrice == nil) {
                print("ERROR");
                return
            }
            mPDenom = mCollateralParam!.getpDenom()
            
            cDpDecimal = WUtils.getKavaCoinDecimal(mCDenom)
            pDpDecimal = WUtils.getKavaCoinDecimal(mPDenom)
            
            pMinAmount = NSDecimalNumber.init(string: mCdpParam!.result.debt_param?.debt_floor)
            currentPrice = NSDecimalNumber.init(string: mPrice?.result.price)
            cMaxAmount = account!.getTokenBalance(mCDenom)
            cMinAmount = pMinAmount.multiplying(byPowerOf10: cDpDecimal - pDpDecimal).multiplying(by: NSDecimalNumber.init(string: "1.05263157895")).multiplying(by: mCollateralParam!.getLiquidationRatio()).dividing(by: currentPrice, withBehavior: WUtils.handler0Up)
            print("currentPrice ", currentPrice)
            print("pMinAmount ", pMinAmount)
            print("cMinAmount ", cMinAmount)
            print("cMaxAmount ", cMaxAmount)
            
            cAvailabeMinLabel.attributedText = WUtils.displayAmount2(cMinAmount.stringValue, cAvailabeMinLabel.font!, cDpDecimal, cDpDecimal)
            cAvailabeMaxLabel.attributedText = WUtils.displayAmount2(cMaxAmount.stringValue, cAvailabeMaxLabel.font!, cDpDecimal, cDpDecimal)
            
            cDenomLabel.text = mCDenom.uppercased()
            cAvailableDenom.text = mCDenom.uppercased()
            pDenomLabel.text = mPDenom.uppercased()
            pAvailableDenom.text = mPDenom.uppercased()
            let cUrl = KAVA_COIN_IMG_URL + mCDenom + ".png"
            self.cDenomImg.af_setImage(withURL: URL(string: cUrl)!)
            let pUrl = KAVA_COIN_IMG_URL + mPDenom + ".png"
            self.pDenomImg.af_setImage(withURL: URL(string: pUrl)!)
            
            onUpdateView()
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
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
    
}
