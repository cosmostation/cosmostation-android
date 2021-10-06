//
//  StepCreateCpdCheckViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import HDWalletKit
import SwiftKeychainWrapper

class StepCreateCpdCheckViewController: BaseViewController, PasswordViewDelegate, SBCardPopupDelegate {

    @IBOutlet weak var cAmountLabel: UILabel!
    @IBOutlet weak var cDenomLabel: UILabel!
    @IBOutlet weak var pAmountLabel: UILabel!
    @IBOutlet weak var pDenomLabel: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var riskScoreLabel: UILabel!
    @IBOutlet weak var currentPriceTitle: UILabel!
    @IBOutlet weak var currentPrice: UILabel!
    @IBOutlet weak var liquidationPriceTitle: UILabel!
    @IBOutlet weak var liquidationPrice: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnConfirm.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }

    @IBAction func onClickConfirm(_ sender: UIButton) {
        let popupVC = CdpWarnPopup(nibName: "CdpWarnPopup", bundle: nil)
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        let cDenom = pageHolderVC.mCDenom
        let pDenom = pageHolderVC.pDenom
        let cDpDecimal = WUtils.getKavaCoinDecimal(cDenom!)
        let pDpDecimal = WUtils.getKavaCoinDecimal(pDenom!)

        let cAmount = NSDecimalNumber.init(string: pageHolderVC.mCollateral.amount)
        let pAmount = NSDecimalNumber.init(string: pageHolderVC.mPrincipal.amount)
        let fAmount = NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount)

        cDenomLabel.text = cDenom?.uppercased()
        cAmountLabel.attributedText = WUtils.displayAmount2(cAmount.stringValue, cAmountLabel.font!, cDpDecimal, cDpDecimal)

        pDenomLabel.text = pDenom?.uppercased()
        pAmountLabel.attributedText = WUtils.displayAmount2(pAmount.stringValue, pAmountLabel.font!, pDpDecimal, pDpDecimal)

        feeAmountLabel.attributedText = WUtils.displayAmount2(fAmount.stringValue, feeAmountLabel.font!, 6, 6)

        WUtils.showRiskRate(pageHolderVC.riskRate!, riskScoreLabel, _rateIamg: nil)
        
        currentPriceTitle.text = String(format: NSLocalizedString("current_price_format", comment: ""), cDenom!.uppercased())
        currentPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.currentPrice!.stringValue, 4, currentPrice.font)
        
        liquidationPriceTitle.text = String(format: NSLocalizedString("liquidation_price_format", comment: ""), cDenom!.uppercased())
        liquidationPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.liquidationPrice!.stringValue, 4, liquidationPrice.font)
        
        memoLabel.text = pageHolderVC.mMemo
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        if (result == 1) {
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
        }
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(pageHolderVC.mAccount!)
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let accountInfo = KavaAccountInfo.init(info)
                _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                self.onGenCreateCdpTx()
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    
    func onGenCreateCdpTx() {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let msg = MsgGenerator.genGetCreatCdpMsg(self.chainType!,
                                                     self.pageHolderVC.mAccount!.account_address,
                                                     self.pageHolderVC.mCollateral,
                                                     self.pageHolderVC.mPrincipal,
                                                     self.pageHolderVC.mCollateralParam?.type)
            
            var msgList = Array<Msg>()
            msgList.append(msg)
            
            let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(self.chainType),
                                                   String(self.pageHolderVC.mAccount!.account_account_numner),
                                                   String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                   msgList,
                                                   self.pageHolderVC.mFee!,
                                                   self.pageHolderVC.mMemo!)
            
            let stdTx = KeyFac.getStdTx(words, msgList, stdMsg,
                                        self.pageHolderVC.mAccount!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
 
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("CreateCdp ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            print("CreateCdp error ", error)
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }

                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                if (self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST) {
                                    txResult["type"] = COSMOS_MSG_TYPE_DELEGATE
                                    self.onStartTxDetail(txResult)
                                }
                            })
                        }
                    }

                } catch {
                    print(error)
                }
            });
        }
    }
}
