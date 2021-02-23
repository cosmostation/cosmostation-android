//
//  StepRewardCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class StepRewardCheckViewController: BaseViewController, PasswordViewDelegate{
    
    @IBOutlet weak var rewardAmoutLaebl: UILabel!
    @IBOutlet weak var rewardDenomLabel: UILabel!
    @IBOutlet weak var feeAmountLabel: UILabel!
    @IBOutlet weak var feeDenomLabel: UILabel!
    
    @IBOutlet weak var fromValidatorLabel: UILabel!
    @IBOutlet weak var recipientTitleLabel: UILabel!
    @IBOutlet weak var recipientLabel: UILabel!
    @IBOutlet weak var memoLabel: UILabel!
    
    @IBOutlet weak var expectedSeparator: UIView!
    @IBOutlet weak var expectedAmountTitle: UILabel!
    @IBOutlet weak var expectedAmountLabel: UILabel!
    @IBOutlet weak var expectedDenomLabel: UILabel!
    
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!

    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, rewardDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.chainType!, expectedDenomLabel)
        
    }
    
    @IBAction func onClickConfirm(_ sender: Any) {
        if (checkIsWasteFee()) {
            let disableAlert = UIAlertController(title: NSLocalizedString("fee_over_title", comment: ""), message: NSLocalizedString("fee_over_msg", comment: ""), preferredStyle: .alert)
            disableAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { [weak disableAlert] (_) in
                self.dismiss(animated: true, completion: nil)
            }))
            self.present(disableAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                disableAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
            return
        }

        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    @IBAction func onClickBack(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.beforeBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }

    func checkIsWasteFee() -> Bool {
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, KAVA_MAIN_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, BAND_MAIN_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, SECRET_MAIN_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, IOV_MAIN_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, IOV_TEST_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, CERTIK_MAIN_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, AKASH_MAIN_DENOM)
            if (NSDecimalNumber.init(string: pageHolderVC.mFee!.amount[0].amount).compare(rewardSum).rawValue > 0 ) {
                return true
            }
            
        }
        
        else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST ||
                    pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            var selectedRewardSum = NSDecimalNumber.zero
            for validator in pageHolderVC.mRewardTargetValidators_V1 {
                if let reward = BaseData.instance.mMyReward_V1.filter({ $0.validator_address == validator.operator_address}).first {
                    selectedRewardSum = selectedRewardSum.adding(reward.getRewardByDenom(WUtils.getMainDenom(pageHolderVC.chainType)))
                }
            }
            if (NSDecimalNumber.init(string: pageHolderVC.mFee?.amount[0].amount).compare(selectedRewardSum).rawValue > 0 ) {
                return true
            }

        }
        return false
    }
    
    func onUpdateView() {
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST ||
                pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators_V1 {
                if(monikers.count > 0) {
                    monikers = monikers + ",   " + (validator.description?.moniker)!
                } else {
                    monikers = (validator.description?.moniker)!
                }
            }
            fromValidatorLabel.text = monikers
            memoLabel.text = pageHolderVC.mMemo
            recipientLabel.text = pageHolderVC.mRewardAddress
            recipientLabel.adjustsFontSizeToFitWidth = true
            
            var selectedRewardSum = NSDecimalNumber.zero
            for validator in pageHolderVC.mRewardTargetValidators_V1 {
                if let reward = BaseData.instance.mMyReward_V1.filter({ $0.validator_address == validator.operator_address}).first {
                    selectedRewardSum = selectedRewardSum.adding(reward.getRewardByDenom(WUtils.getMainDenom(pageHolderVC.chainType)))
                }
            }
            
            rewardAmoutLaebl.attributedText = WUtils.displayAmount2(selectedRewardSum.stringValue, rewardAmoutLaebl.font, 6, 6)
            feeAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mFee?.amount[0].amount, feeAmountLabel.font, 6, 6)
            
            let userBalance: NSDecimalNumber = BaseData.instance.getAvailable(WUtils.getMainDenom(pageHolderVC.chainType))
            let expectedAmount = userBalance.adding(selectedRewardSum).subtracting(WUtils.plainStringToDecimal(pageHolderVC.mFee?.amount[0].amount))
            expectedAmountLabel.attributedText = WUtils.displayAmount2(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, 6)
            
            if (pageHolderVC.mAccount?.account_address == pageHolderVC.mRewardAddress) {
                recipientTitleLabel.isHidden = true
                recipientLabel.isHidden = true
                
                expectedSeparator.isHidden = false
                expectedAmountTitle.isHidden = false
                expectedAmountLabel.isHidden = false
                expectedDenomLabel.isHidden = false
                
            } else {
                recipientTitleLabel.isHidden = false
                recipientLabel.isHidden = false
                
                expectedSeparator.isHidden = true
                expectedAmountTitle.isHidden = true
                expectedAmountLabel.isHidden = true
                expectedDenomLabel.isHidden = true
            }
            
        } else {
            if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, KAVA_MAIN_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == KAVA_MAIN_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, BAND_MAIN_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == BAND_MAIN_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, SECRET_MAIN_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == SECRET_MAIN_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, IOV_MAIN_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == IOV_MAIN_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, IOV_TEST_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == IOV_TEST_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, CERTIK_MAIN_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == CERTIK_MAIN_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                let rewardSum = WUtils.getAllRewardByDenom(pageHolderVC.mRewardList, AKASH_MAIN_DENOM)
                rewardAmoutLaebl.attributedText = WUtils.displayAmount(rewardSum.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
                feeAmountLabel.attributedText = WUtils.displayAmount((pageHolderVC.mFee?.amount[0].amount)!, feeAmountLabel.font, 6, pageHolderVC.chainType!)
                
                var userBalance = NSDecimalNumber.zero
                for balance in pageHolderVC.mBalances {
                    if(balance.balance_denom == AKASH_MAIN_DENOM) {
                        userBalance = userBalance.adding(WUtils.localeStringToDecimal(balance.balance_amount))
                    }
                }
                
                let expectedAmount = userBalance.adding(rewardSum).subtracting(WUtils.localeStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!))
                expectedAmountLabel.attributedText = WUtils.displayAmount(expectedAmount.stringValue, rewardAmoutLaebl.font, 6, pageHolderVC.chainType!)
            }
            
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators {
                if(monikers.count > 0) {
                    monikers = monikers + ",   " + validator.description.moniker
                } else {
                    monikers = validator.description.moniker
                }
            }
            fromValidatorLabel.text = monikers
            memoLabel.text = pageHolderVC.mMemo
            
            recipientLabel.text = pageHolderVC.mRewardAddress
            recipientLabel.adjustsFontSizeToFitWidth = true
            
            if (pageHolderVC.mAccount?.account_address == pageHolderVC.mRewardAddress) {
                recipientTitleLabel.isHidden = true
                recipientLabel.isHidden = true
        
                expectedSeparator.isHidden = false
                expectedAmountTitle.isHidden = false
                expectedAmountLabel.isHidden = false
                expectedDenomLabel.isHidden = false
                
            } else {
                recipientTitleLabel.isHidden = false
                recipientLabel.isHidden = false
                
                expectedSeparator.isHidden = true
                expectedAmountTitle.isHidden = true
                expectedAmountLabel.isHidden = true
                expectedDenomLabel.isHidden = true
            }
            
        }
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST ||
                    pageHolderVC.chainType! == ChainType.IRIS_MAIN  || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
                self.onFetchAuth(pageHolderVC.mAccount!)
            } else {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
            }
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        var url: String?
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            url = AKASH_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST || self.pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenGetRewardTx()
                    
                } else if (self.pageHolderVC.chainType! == ChainType.BAND_MAIN || self.pageHolderVC.chainType! == ChainType.SECRET_MAIN || self.pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                            self.pageHolderVC.chainType! == ChainType.IOV_TEST || self.pageHolderVC.chainType! == ChainType.CERTIK_MAIN || self.pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                    guard let responseData = res as? NSDictionary,
                        let info = responseData.object(forKey: "result") as? [String : Any] else {
                            _ = BaseData.instance.deleteBalance(account: account)
                            self.hideWaittingAlert()
                            self.onShowToast(NSLocalizedString("error_network", comment: ""))
                            return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    self.onGenGetRewardTx()
                }
                
            case .failure( _):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onFetchAuth(_ account: Account) {
        self.showWaittingAlert()
        let url = BaseNetWork.authUrl(self.pageHolderVC.chainType!, account.account_address)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("res ", res)
                guard let responseData = res as? NSDictionary, let account = responseData.object(forKey: "account") as? NSDictionary else {
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let auth = Auth_V1.init(account)
                self.onBroadcastTxV1(auth)
                
            case .failure(let error):
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAuth ", error) }
            }
        }
        
    }
    
    func onGenGetRewardTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                var msgList = Array<Msg>()
                for val in self.pageHolderVC.mRewardTargetValidators {
                    let msg = MsgGenerator.genGetRewardMsg(self.pageHolderVC.mAccount!.account_address, val.operator_address, self.pageHolderVC.chainType!)
                    msgList.append(msg)
                }
                let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainId(self.pageHolderVC.mAccount!.account_base_chain),
                                                       String(self.pageHolderVC.mAccount!.account_account_numner),
                                                       String(self.pageHolderVC.mAccount!.account_sequence_number),
                                                       msgList,
                                                       self.pageHolderVC.mFee!,
                                                       self.pageHolderVC.mMemo!)
                
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(stdMsg)
                let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                let rawData: Data? = rawResult!.data(using: .utf8)
                let hash = Crypto.sha256(rawData!)
                
                let signedData: Data? = try Crypto.sign(hash, privateKey: pKey.privateKey())
                
                var genedSignature = Signature.init()
                var genPubkey =  PublicKey.init()
                genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
                genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
                genedSignature.pub_key = genPubkey
                genedSignature.signature = WKey.convertSignature(signedData!)
                genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                
                var signatures: Array<Signature> = Array<Signature>()
                signatures.append(genedSignature)
                
                stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                
            } catch {
                if (SHOW_LOG) { print(error) }
                
            }
            
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    var url: String?
                    if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                        url = KAVA_TEST_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                        url = BAND_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                        url = SECRET_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.IOV_MAIN) {
                        url = IOV_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.IOV_TEST) {
                        url = IOV_TEST_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
                        url = CERTIK_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                        url = CERTIK_TEST_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                        url = AKASH_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("getReward ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) {
                                print("getRewarderror ", error)
                            }
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }
                        
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                self.onStartTxDetail(txResult)
                            })
                        }
                    }
                } catch {
                    if (SHOW_LOG) { print(error) }
                }
            });
        }
    }
    
    func onBroadcastTxV1(_ auth: Auth_V1) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let stdTx = Signer.genSignedRewardTxV1(auth.getAddress(), auth.getAccountNumber(), auth.getSequenceNumber(),
                                                   self.pageHolderVC.mRewardTargetValidators_V1, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!,
                                                     WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!), self.pageHolderVC.chainType!)
            
            DispatchQueue.main.async(execute: {
                let url = BaseNetWork.postTxUrl(self.pageHolderVC.chainType!)
                let params = Signer.getBroadCastParam(stdTx)
                let request = Alamofire.request(url, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                request.responseJSON { response in
                    var txResult = [String:Any]()
                    switch response.result {
                    case .success(let res):
                        if(SHOW_LOG) { print("Claim Reward ", res) }
                        if let result = res as? [String : Any]  {
                            txResult = result
                        }
                    case .failure(let error):
                        if(SHOW_LOG) { print("Claim Reward error ", error) }
                        if (response.response?.statusCode == 500) {
                            txResult["net_error"] = 500
                        }
                    }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetail(txResult)
                        })
                    }
                }
            });
        }
        
    }
}
