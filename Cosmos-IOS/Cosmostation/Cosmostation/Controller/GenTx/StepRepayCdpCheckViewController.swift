//
//  StepRepayCdpCheckViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/01.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import HDWalletKit
import SwiftKeychainWrapper

class StepRepayCdpCheckViewController: BaseViewController, PasswordViewDelegate {

    @IBOutlet weak var pAmountLabel: UILabel!
    @IBOutlet weak var pDenomLabel: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var beforeRiskRate: UILabel!
    @IBOutlet weak var afterRiskRate: UILabel!
    @IBOutlet weak var adjuestedcAmount: UILabel!
    @IBOutlet weak var adjuestedcAmountDenom: UILabel!
    @IBOutlet weak var beforeLiquidationPriceTitle: UILabel!
    @IBOutlet weak var beforeLiquidationPrice: UILabel!
    @IBOutlet weak var afterLiquidationPriceTitle: UILabel!
    @IBOutlet weak var afterLiquidationPrice: UILabel!
    @IBOutlet weak var memo: UILabel!
    @IBOutlet weak var warnImg: UIImageView!
    @IBOutlet weak var warnMsg: UILabel!
    
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
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        print("onUpdateView")
        let cDenom = pageHolderVC.mCDenom
        let pDenom = pageHolderVC.pDenom
        let pDpDecimal = WUtils.getKavaCoinDecimal(pDenom!)
        
        let pAmount = NSDecimalNumber.init(string: pageHolderVC.mPayment.amount)
        let fAmount = NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount)
        
        pDenomLabel.text = pDenom?.uppercased()
        pAmountLabel.attributedText = WUtils.displayAmount2(pAmount.stringValue, pAmountLabel.font!, pDpDecimal, pDpDecimal)
        
        feeAmountLabel.attributedText = WUtils.displayAmount2(fAmount.stringValue, feeAmountLabel.font!, 6, 6)
        
        WUtils.showRiskRate(pageHolderVC.beforeRiskRate!, beforeRiskRate, _rateIamg: nil)
        WUtils.showRiskRate(pageHolderVC.afterRiskRate!, afterRiskRate, _rateIamg: nil)
        
        adjuestedcAmount.attributedText = WUtils.displayAmount2(pageHolderVC.totalLoanAmount!.stringValue, adjuestedcAmount.font!, pDpDecimal, pDpDecimal)
        adjuestedcAmountDenom.text = pDenom?.uppercased()
        
        beforeLiquidationPriceTitle.text = String(format: NSLocalizedString("before_liquidation_price_format", comment: ""), cDenom!.uppercased())
        beforeLiquidationPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.beforeLiquidationPrice!.stringValue, 4, beforeLiquidationPrice.font)
        
        afterLiquidationPriceTitle.text = String(format: NSLocalizedString("after_liquidation_price_format", comment: ""), cDenom!.uppercased())
        if (pageHolderVC.totalLoanAmount! != NSDecimalNumber.zero) {
            afterLiquidationPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.afterLiquidationPrice!.stringValue, 4, afterLiquidationPrice.font)
        } else {
            afterLiquidationPrice.attributedText = WUtils.getDPRawDollor(pageHolderVC.afterLiquidationPrice!.stringValue, 4, afterLiquidationPrice.font)
            warnImg.isHidden = false
            warnMsg.isHidden = false
            
        }
        memo.text = pageHolderVC.mMemo
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
                print("res : ", res)
                guard let info = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let accountInfo = KavaAccountInfo.init(info)
                _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                self.onGenRepayCdpTx()
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onGenRepayCdpTx() {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let msg = MsgGenerator.genGetRepayCdpMsg(self.chainType!,
                                                     self.pageHolderVC.mAccount!.account_address,
                                                     self.pageHolderVC.mCDenom!,
                                                     self.pageHolderVC.mPayment,
                                                     self.pageHolderVC.mCollateralParam?.type)
            
            var msgList = Array<Msg>()
            msgList.append(msg)
            
            let stdMsg = MsgGenerator.getToSignMsg(BaseData.instance.getChainId(self.chainType),
                                                   String(self.pageHolderVC.mAccount!.account_account_numner),
                                                   String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                   msgList,
                                                   self.pageHolderVC.mFee!,
                                                   self.pageHolderVC.mMemo!)
            
            let stdTx = KeyFac.getStdTx(words, msgList, stdMsg, self.pageHolderVC.mAccount!, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    let request = Alamofire.request(BaseNetWork.broadcastUrl(self.chainType), method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.validate().responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            print("DrawDebt ", res)
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            print("DrawDebt error ", error)
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }

                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                txResult["type"] = COSMOS_MSG_TYPE_DELEGATE
                                self.onStartTxDetail(txResult)
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
