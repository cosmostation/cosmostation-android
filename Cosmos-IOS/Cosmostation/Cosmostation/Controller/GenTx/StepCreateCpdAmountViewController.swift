//
//  StepCreateCpdAmountViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class StepCreateCpdAmountViewController: BaseViewController, UITextFieldDelegate{
    
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
    var cDenom: String = ""
    var pDenom: String = ""
    var cDpDecimal:Int16 = 6
    var pDpDecimal:Int16 = 6
    var mMarketID: String = ""
    var cdpParam:CdpParam?
    var cParam: CdpParam.CollateralParam?
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
        cDenom = pageHolderVC.cDenom!
        mMarketID = pageHolderVC.mMarketID!
        
        self.loadingImg.onStartAnimation()
        self.onFetchCdpData()
        
        cAmountInput.delegate = self
        pAmountInput.delegate = self
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
            toCAmount = WUtils.stringToDecimal(cAmountInput.text?.trimmingCharacters(in: .whitespaces)).multiplying(byPowerOf10: cDpDecimal)
            print("toCAmount ", toCAmount)
            let toCValue = toCAmount.multiplying(byPowerOf10: -cDpDecimal).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
            cDepositValue.attributedText = WUtils.getDPRawDollor(toCValue.stringValue, 2, cDepositValue.font)
            pMaxAmount = toCAmount.multiplying(byPowerOf10: pDpDecimal - cDpDecimal).multiplying(by: NSDecimalNumber.init(string: "0.9524")).multiplying(by: currentPrice).dividing(by: cParam!.getLiquidationRatio(), withBehavior: WUtils.handler0Down)
            print("pMaxAmount ", pMaxAmount)
        
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
        let userInput = WUtils.stringToDecimal(text)
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
    
    @IBAction func onClickCAmountClear(_ sender: UIButton) {
        cAmountInput.text = ""
        pAmountInput.text = ""
        isPrincipal = false
        pMaxAmount = NSDecimalNumber.zero
        onUpdateView();
    }
    
    @IBAction func onClickCMin(_ sender: UIButton) {
        let calValue = cMinAmount.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.DecimalToLocalString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickC1_4(_ sender: UIButton) {
        var calValue = cMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.25"))
        if (calValue.compare(cMinAmount).rawValue < 0) {
            calValue = cMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.DecimalToLocalString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickCHalf(_ sender: UIButton) {
        var calValue = cMaxAmount.dividing(by: NSDecimalNumber(2))
        if (calValue.compare(cMinAmount).rawValue < 0) {
            calValue = cMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.DecimalToLocalString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickC3_4(_ sender: UIButton) {
        var calValue = cMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.75"))
        if (calValue.compare(cMinAmount).rawValue < 0) {
            calValue = cMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_deposit", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.DecimalToLocalString(calValue, cDpDecimal)
    }
    
    @IBAction func onClickCMax(_ sender: UIButton) {
        let maxValue = cMaxAmount.multiplying(byPowerOf10: -cDpDecimal, withBehavior: WUtils.getDivideHandler(cDpDecimal))
        cAmountInput.text = WUtils.DecimalToLocalString(maxValue, cDpDecimal)
    }
    
    

    @IBAction func onClickPAmountClear(_ sender: UIButton) {
        pAmountInput.text = ""
    }
    
    @IBAction func onClickPMin(_ sender: UIButton) {
        let calValue = pMinAmount.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        onUpdateNextBtn()
    }
    
    @IBAction func onClickP20(_ sender: UIButton) {
        var calValue = pMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.2"))
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        onUpdateNextBtn()
    }
    
    @IBAction func onClickP50(_ sender: UIButton) {
        var calValue = pMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.5"))
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        onUpdateNextBtn()
        
    }
    
    @IBAction func onClickP70(_ sender: UIButton) {
        var calValue = pMaxAmount.multiplying(by: NSDecimalNumber.init(string: "0.7"))
        if (calValue.compare(pMinAmount).rawValue < 0) {
            calValue = pMinAmount
            self.onShowToast(NSLocalizedString("error_less_than_min_principal", comment: ""))
        }
        calValue = calValue.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(calValue, pDpDecimal)
        onUpdateNextBtn()
        
    }
    
    @IBAction func onClickPMax(_ sender: UIButton) {
        let maxValue = pMaxAmount.multiplying(byPowerOf10: -pDpDecimal, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        pAmountInput.text = WUtils.DecimalToLocalString(maxValue, pDpDecimal)
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
                let cCoin = Coin.init(cDenom, toCAmount.stringValue)
                var cCoins = Array<Coin>()
                cCoins.append(cCoin)
                self.pageHolderVC.mCollateral = cCoins
                
                let pCoin = Coin.init(pDenom, toPAmount.stringValue)
                var pCoins = Array<Coin>()
                pCoins.append(pCoin)
                self.pageHolderVC.mPrincipal = pCoins
                
                self.pageHolderVC.currentPrice = currentPrice
                self.pageHolderVC.liquidationPrice = liquidationPrice
                self.pageHolderVC.riskRate = riskRate
                self.pageHolderVC.pDenom = pDenom
                
                self.btnCancel.isUserInteractionEnabled = false
                self.btnNext.isUserInteractionEnabled = false
                pageHolderVC.onNextPage()
                
            } else {
                self.onShowToast(NSLocalizedString("error_amount", comment: ""))
            }
        }
        
    }
    
    func isValiadCAmount() -> Bool {
        let text = cAmountInput.text?.trimmingCharacters(in: .whitespaces)
        if (text == nil || text!.count == 0) { return false }
        let userInput = WUtils.stringToDecimal(text!)
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
        let userInput = WUtils.stringToDecimal(text!)
        if (userInput == NSDecimalNumber.zero) { return false }
        if (userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMaxAmount).rawValue > 0 ||
            userInput.multiplying(byPowerOf10: pDpDecimal).compare(pMinAmount).rawValue < 0) {
            return false
        }
        toPAmount = userInput.multiplying(byPowerOf10: pDpDecimal)
        print("toPAmount ", toPAmount)
        
        let collateralAmount = toCAmount.multiplying(byPowerOf10: -cDpDecimal)
        let rawDebtAmount = toPAmount.multiplying(by: cParam!.getLiquidationRatio()).multiplying(byPowerOf10: -pDpDecimal)
        liquidationPrice = rawDebtAmount.dividing(by: collateralAmount, withBehavior: WUtils.getDivideHandler(pDpDecimal))
        riskRate = NSDecimalNumber.init(string: "100").subtracting(currentPrice.subtracting(liquidationPrice).multiplying(byPowerOf10: 2).dividing(by: currentPrice, withBehavior: WUtils.handler2Down))
        
        print("collateralAmount ", collateralAmount)
        print("rawDebtAmount ", rawDebtAmount)
        print("liquidationPrice ", liquidationPrice)
        print("riskRate ", riskRate)
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
                if (riskRate.doubleValue < 50) {
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
        self.mFetchCnt = 2
        onFetchCdpParam()
        onFetchKavaPrice(self.mMarketID)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if (cParam == nil || mPrice == nil) {
                print("ERROR");
                return
            }
            pDenom = cParam!.getpDenom()
            
            cDpDecimal = WUtils.getKavaCoinDecimal(cDenom)
            pDpDecimal = WUtils.getKavaCoinDecimal(pDenom)
            
            pMinAmount = NSDecimalNumber.init(string: cdpParam!.result.debt_params[0].debt_floor)
            currentPrice = NSDecimalNumber.init(string: mPrice?.result.price)
            cMaxAmount = account!.getTokenBalance(cDenom)
            cMinAmount = pMinAmount.multiplying(byPowerOf10: cDpDecimal - pDpDecimal).multiplying(by: NSDecimalNumber.init(string: "1.05")).multiplying(by: cParam!.getLiquidationRatio()).dividing(by: currentPrice, withBehavior: WUtils.handler0Down)
            print("cMinAmount ", cMinAmount)
                
            
            cAvailabeMinLabel.attributedText = WUtils.displayAmount2(cMinAmount.stringValue, cAvailabeMinLabel.font!, cDpDecimal, cDpDecimal)
            cAvailabeMaxLabel.attributedText = WUtils.displayAmount2(cMaxAmount.stringValue, cAvailabeMaxLabel.font!, cDpDecimal, cDpDecimal)
            
            cDenomLabel.text = cDenom.uppercased()
            cAvailableDenom.text = cDenom.uppercased()
            pDenomLabel.text = pDenom.uppercased()
            pAvailableDenom.text = pDenom.uppercased()
            Alamofire.request(KAVA_COIN_IMG_URL + cDenom + ".png", method: .get).responseImage { response  in
                guard let image = response.result.value else { return }
                self.cDenomImg.image = image
            }
            Alamofire.request(KAVA_COIN_IMG_URL + pDenom + ".png", method: .get).responseImage { response  in
                guard let image = response.result.value else { return }
                self.pDenomImg.image = image
            }
            
            onUpdateView()
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
        }
    }
    
    func onFetchCdpParam() {
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
