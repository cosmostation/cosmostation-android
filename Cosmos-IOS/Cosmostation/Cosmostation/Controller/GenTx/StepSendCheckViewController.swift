//
//  StepSendCheckViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import BinanceChain
import SwiftKeychainWrapper
import HDWalletKit
import GRPC
import NIO

class StepSendCheckViewController: BaseViewController, PasswordViewDelegate{

    @IBOutlet weak var mToSendAmountLabel: UILabel!
    @IBOutlet weak var mFeeAmountLabel: UILabel!
    @IBOutlet weak var mTotalSpendTitle: UILabel!
    @IBOutlet weak var mTotalSpendLabel: UILabel!
    @IBOutlet weak var mTotalSpendPrice: UILabel!
    
    @IBOutlet weak var mCurrentAvailable: UILabel!
    @IBOutlet weak var mReminaingAvailable: UILabel!
    @IBOutlet weak var mReminaingPrice: UILabel!
    
    @IBOutlet weak var mToAddressLabel: UILabel!
    @IBOutlet weak var mMemoLabel: UILabel!
    @IBOutlet weak var backBtn: UIButton!
    @IBOutlet weak var confirmBtn: UIButton!
    
    @IBOutlet weak var mToSendDenomLabel: UILabel!
    @IBOutlet weak var mFeeDenomTitle: UILabel!
    @IBOutlet weak var mTotalSpendDenomTitle: UILabel!
    @IBOutlet weak var mCurrentBalanceDenomTitle: UILabel!
    @IBOutlet weak var mRemainBalanceTitle: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, mToSendDenomLabel)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mFeeDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mTotalSpendDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mCurrentBalanceDenomTitle)
        WUtils.setDenomTitle(pageHolderVC.chainType!, mRemainBalanceTitle)
    }
    
    @IBAction func onClickConfirm(_ sender: Any) {
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
        passwordVC.resultDelegate = self
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    @IBAction func onClickBack(_ sender: Any) {
        self.backBtn.isUserInteractionEnabled = false
        self.confirmBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.backBtn.isUserInteractionEnabled = true
        self.confirmBtn.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        let toSendAmount = WUtils.plainStringToDecimal(pageHolderVC.mToSendAmount[0].amount)
        let toSendDenom = pageHolderVC.mToSendAmount[0].denom
        let feeAmount = WUtils.plainStringToDecimal((pageHolderVC.mFee?.amount[0].amount)!)
        var currentAva = NSDecimalNumber.zero
        
        if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
            mDpDecimal = 8
            mToSendDenomLabel.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            mCurrentBalanceDenomTitle.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            mRemainBalanceTitle.text = pageHolderVC.mBnbToken?.original_symbol.uppercased()
            
            if (pageHolderVC.mBnbToken?.symbol == BNB_MAIN_DENOM) {
                currentAva = pageHolderVC.mAccount!.getBnbBalance()
                mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 0, 8)
                mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 0, 8)
                mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 0, 8)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 0, 8)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 0, 8)
                
                mTotalSpendPrice.attributedText = WUtils.dpBnbValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpBnbValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                
            }  else {
                mToSendDenomLabel.textColor = UIColor.white
                mCurrentBalanceDenomTitle.textColor = UIColor.white
                mRemainBalanceTitle.textColor = UIColor.white
                
                mTotalSpendTitle.isHidden = true
                mTotalSpendLabel.isHidden = true
                mTotalSpendDenomTitle.isHidden = true
                mTotalSpendPrice.isHidden = true
                mReminaingPrice.isHidden = true
                
                currentAva = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mBnbToken!.symbol)
                mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 0, 8)
                mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 0, 8)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 0, 8)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 0, 8)
                
            }
            
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            mDpDecimal = WUtils.getKavaCoinDecimal(self.pageHolderVC.mKavaSendDenom!)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, 6)
            if (toSendDenom == KAVA_MAIN_DENOM) {
                currentAva = pageHolderVC.mAccount!.getKavaBalance()
                mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, 6)
                mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, 6)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, 6)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, 6)
                
                mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                
            } else {
                mTotalSpendTitle.isHidden = true
                mTotalSpendLabel.isHidden = true
                mTotalSpendDenomTitle.isHidden = true
                mTotalSpendPrice.isHidden = true
                mReminaingPrice.isHidden = true
                
                currentAva = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mKavaSendDenom!)
                WUtils.showCoinDp(toSendDenom, toSendAmount.stringValue, mToSendDenomLabel, mToSendAmountLabel, pageHolderVC.chainType!)
                WUtils.showCoinDp(toSendDenom, currentAva.stringValue, mCurrentBalanceDenomTitle, mCurrentAvailable, pageHolderVC.chainType!)
                WUtils.showCoinDp(toSendDenom, currentAva.subtracting(toSendAmount).stringValue, mRemainBalanceTitle, mReminaingAvailable, pageHolderVC.chainType!)
                
            }
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
            mDpDecimal = 6
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, mDpDecimal)
            if (toSendDenom == IOV_MAIN_DENOM || toSendDenom == IOV_TEST_DENOM ) {
                currentAva = pageHolderVC.mAccount!.getIovBalance()
                mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, mDpDecimal)
                mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, mDpDecimal)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, mDpDecimal)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, mDpDecimal)
                
                mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
                
            } else {}
            
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            mDpDecimal = 6
            currentAva = pageHolderVC.mAccount!.getBandBalance()
            mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, 6)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, 6)
            mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, 6)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, 6)
            mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, 6)
            
            mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN || pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            mDpDecimal = 18
            mToSendDenomLabel.text = pageHolderVC.mOkSendDenom!.uppercased()
            mCurrentBalanceDenomTitle.text = pageHolderVC.mOkSendDenom!.uppercased()
            mRemainBalanceTitle.text = pageHolderVC.mOkSendDenom!.uppercased()
            currentAva = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mOkSendDenom!)
            
            if (pageHolderVC.mOkSendDenom == OKEX_MAIN_DENOM) {
                mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 0, mDpDecimal)
                mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 0, mDpDecimal)
                mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 0, mDpDecimal)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 0, mDpDecimal)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 0, mDpDecimal)
                
                mTotalSpendPrice.attributedText = WUtils.dpTokenValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), 0, mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpTokenValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), 0, mReminaingPrice.font)
                
            } else {
                mToSendDenomLabel.textColor = UIColor.white
                mCurrentBalanceDenomTitle.textColor = UIColor.white
                mRemainBalanceTitle.textColor = UIColor.white
                
                mTotalSpendTitle.isHidden = true
                mTotalSpendLabel.isHidden = true
                mTotalSpendDenomTitle.isHidden = true
                mTotalSpendPrice.isHidden = true
                mReminaingPrice.isHidden = true
                
                mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 0, mDpDecimal)
                mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 0, mDpDecimal)
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 0, mDpDecimal)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 0, mDpDecimal)
                
            }
            
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            mDpDecimal = 6
            currentAva = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mCertikSendDenom!)
            mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, 6)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, 6)
            mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, 6)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, 6)
            mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, 6)
            
            mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            mDpDecimal = 6
            currentAva = pageHolderVC.mAccount!.getTokenBalance(pageHolderVC.mSecretSendDenom!)
            mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, 6)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, 6)
            mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, 6)
            
            mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, 6)
            mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, 6)
            
            mTotalSpendPrice.attributedText = WUtils.dpAtomValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            mReminaingPrice.attributedText = WUtils.dpAtomValue(currentAva.subtracting(feeAmount).subtracting(toSendAmount), BaseData.instance.getLastPrice(), mTotalSpendPrice.font)
            
        }
        
        
        else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.AKASH_MAIN || pageHolderVC.chainType! == ChainType.COSMOS_TEST) {
            mDpDecimal = 6
            currentAva = BaseData.instance.getAvailable(pageHolderVC.mToSendDenom!)
            mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, mDpDecimal)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, mDpDecimal)
            
            if (pageHolderVC.mToSendDenom == WUtils.getMainDenom(pageHolderVC.chainType!)) {
                mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, mDpDecimal)
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, mDpDecimal)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6,mDpDecimal)

                mTotalSpendPrice.attributedText = WUtils.dpTokenValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), 6, mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpTokenValue(currentAva.subtracting(feeAmount), BaseData.instance.getLastPrice(), 6, mReminaingPrice.font)
                
            } else {
                //TODO need real test
                mTotalSpendTitle.isHidden = true
                mTotalSpendLabel.isHidden = true
                mTotalSpendDenomTitle.isHidden = true
                mTotalSpendPrice.isHidden = true
                mReminaingPrice.isHidden = true
                
                mToSendDenomLabel.textColor = UIColor.white
                mCurrentBalanceDenomTitle.textColor = UIColor.white
                mRemainBalanceTitle.textColor = UIColor.white
                mToSendDenomLabel.text = pageHolderVC.mToSendDenom!.uppercased()
                mCurrentBalanceDenomTitle.text = pageHolderVC.mToSendDenom!.uppercased()
                mRemainBalanceTitle.text = pageHolderVC.mToSendDenom!.uppercased()
                
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, mDpDecimal)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, mDpDecimal)
            }
            
        } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            mDpDecimal = 6
            currentAva = BaseData.instance.getAvailable(pageHolderVC.mToSendDenom!)
            mToSendAmountLabel.attributedText = WUtils.displayAmount2(toSendAmount.stringValue, mToSendAmountLabel.font, 6, mDpDecimal)
            mFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, mFeeAmountLabel.font, 6, 6)
            
            if (pageHolderVC.mToSendDenom == WUtils.getMainDenom(pageHolderVC.chainType!)) {
                mTotalSpendLabel.attributedText = WUtils.displayAmount2(feeAmount.adding(toSendAmount).stringValue, mTotalSpendLabel.font, 6, mDpDecimal)
                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, mDpDecimal)
                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(feeAmount).subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, mDpDecimal)
                
                mTotalSpendPrice.attributedText = WUtils.dpTokenValue(feeAmount.adding(toSendAmount), BaseData.instance.getLastPrice(), 6, mTotalSpendPrice.font)
                mReminaingPrice.attributedText = WUtils.dpTokenValue(currentAva.subtracting(feeAmount), BaseData.instance.getLastPrice(), 6, mReminaingPrice.font)
                
            } else {
                //TODO need real test
//                mTotalSpendTitle.isHidden = true
//                mTotalSpendLabel.isHidden = true
//                mTotalSpendDenomTitle.isHidden = true
//                mTotalSpendPrice.isHidden = true
//                mReminaingPrice.isHidden = true
//                
//                mToSendDenomLabel.textColor = UIColor.white
//                mCurrentBalanceDenomTitle.textColor = UIColor.white
//                mRemainBalanceTitle.textColor = UIColor.white
//                mToSendDenomLabel.text = pageHolderVC.mIrisTokenV1!.symbol?.uppercased()
//                mCurrentBalanceDenomTitle.text = pageHolderVC.mIrisTokenV1!.symbol?.uppercased()
//                mRemainBalanceTitle.text = pageHolderVC.mIrisTokenV1!.symbol?.uppercased()
//                
//                mCurrentAvailable.attributedText = WUtils.displayAmount2(currentAva.stringValue, mCurrentAvailable.font, 6, mDpDecimal)
//                mReminaingAvailable.attributedText = WUtils.displayAmount2(currentAva.subtracting(toSendAmount).stringValue, mReminaingAvailable.font, 6, mDpDecimal)
            }
        }
        
        mToAddressLabel.text = pageHolderVC.mToSendRecipientAddress
        mToAddressLabel.adjustsFontSizeToFitWidth = true
        mMemoLabel.text = pageHolderVC.mMemo
        
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                    pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST ||
                    pageHolderVC.chainType! == ChainType.OKEX_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST ||
                    pageHolderVC.chainType! == ChainType.OKEX_MAIN) {
                self.onFetchAccountInfo(pageHolderVC.mAccount!)
                
            } else if (pageHolderVC.chainType! == ChainType.BINANCE_MAIN || pageHolderVC.chainType! == ChainType.BINANCE_TEST) {
                self.onGenBnbSendTx()
                
            } else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.AKASH_MAIN ||
                        pageHolderVC.chainType! == ChainType.COSMOS_TEST || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
                self.onFetchgRPCAuth(pageHolderVC.mAccount!)
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
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.OKEX_MAIN) {
            url = OKEX_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.OKEX_TEST) {
            url = OKEX_TEST_ACCOUNT_INFO + account.account_address
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                    guard  let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenSendTx()
                    
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
                    self.onGenSendTx()
                    
                } else if (self.pageHolderVC.chainType! == ChainType.OKEX_MAIN || self.pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                    guard let info = res as? NSDictionary else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let okAccountInfo = OkAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithOkAccountInfo(account, okAccountInfo))
                    BaseData.instance.mOkAccountInfo = okAccountInfo
                    self.onGenSendTx()
                    
                }
                
            case .failure(let error):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
        }
    }
    
    func onGenSendTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                let msg = MsgGenerator.genGetSendMsg(self.pageHolderVC.mAccount!.account_address,
                                                     self.pageHolderVC.mToSendRecipientAddress!,
                                                     self.pageHolderVC.mToSendAmount,
                                                     self.pageHolderVC.chainType!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                if (self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                        self.pageHolderVC.chainType! == ChainType.BAND_MAIN || self.pageHolderVC.chainType! == ChainType.SECRET_MAIN  || self.pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                        self.pageHolderVC.chainType! == ChainType.IOV_TEST || self.pageHolderVC.chainType! == ChainType.CERTIK_MAIN || self.pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                        self.pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                    
                    let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainId(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner), String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
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
                    
                } else if (self.pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                    let irisStdMsg = MsgGenerator.getIrisToSignMsg(WUtils.getChainId(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner),  String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(irisStdMsg)
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
                    
                } else if (self.pageHolderVC.chainType! == ChainType.OKEX_MAIN || self.pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                    let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainId(self.pageHolderVC.mAccount!.account_base_chain), String(self.pageHolderVC.mAccount!.account_account_numner), String(self.pageHolderVC.mAccount!.account_sequence_number), msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!)
                    
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(stdMsg)
                    let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                    let rawData: Data? = rawResult!.data(using: .utf8)
                    
                    if (self.pageHolderVC.mAccount!.account_new_bip44) {
                        let hash = HDWalletKit.Crypto.sha3keccak256(data: rawData!)
                        let signedData: Data? = try ECDSA.compactsign(hash, privateKey: pKey.privateKey().raw)
                        
                        var genedSignature = Signature.init()
                        var genPubkey =  PublicKey.init()
                        genPubkey.type = ETHERMINT_KEY_TYPE_PUBLIC
                        genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
                        genedSignature.pub_key = genPubkey
                        genedSignature.signature = signedData!.base64EncodedString()
                        genedSignature.account_number = String(self.pageHolderVC.mAccount!.account_account_numner)
                        genedSignature.sequence = String(self.pageHolderVC.mAccount!.account_sequence_number)
                        
                        var signatures: Array<Signature> = Array<Signature>()
                        signatures.append(genedSignature)
                        
                        stdTx = MsgGenerator.genSignedTx(msgList, self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, signatures)
                        
                    } else {
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
                    }
                    
                }
                
                
            } catch {
                if(SHOW_LOG) { print(error) }
            }
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    print("params ", params)
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
                    } else if (self.pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
                        url = CERTIK_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.IOV_TEST) {
                        url = IOV_TEST_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.OKEX_MAIN) {
                        url = OKEX_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.OKEX_TEST) {
                        url = OKEX_TEST_BORAD_TX
                    } else if (self.pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                        url = CERTIK_TEST_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Send ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) { print("send error ", error) }
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
                    if(SHOW_LOG) { print(error) }
                }
            });
        }
    }
    
    func onGenBnbSendTx() {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            
            var binance: BinanceChain?
            var pKey: HDPrivateKey?
            var wallet = Wallet()
            var txResult = [String:Any]()
            
            if (self.pageHolderVC.chainType! == ChainType.BINANCE_MAIN) {
                //For Binance maninet send
                binance = BinanceChain(endpoint: BinanceChain.Endpoint.mainnet)
                pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                wallet = Wallet(privateKey: pKey!.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.mainnet)
                
            } else {
                //For Binance testent send
                binance = BinanceChain(endpoint: BinanceChain.Endpoint.testnet)
                pKey = WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!)
                wallet = Wallet(privateKey: pKey!.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.testnet)
            }
            
            wallet.synchronise(){ (error) in
                if let error = error {
                    if(SHOW_LOG) { print(error) }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetail(txResult)
                        })
                    }
                }
                
                let bnbMsg = Message.transfer(symbol: self.pageHolderVC.mToSendAmount[0].denom,
                                              amount: (self.pageHolderVC.mToSendAmount[0].amount as NSString).doubleValue,
                                              to: self.pageHolderVC.mToSendRecipientAddress!,
                                              memo: self.pageHolderVC.mMemo!,
                                              wallet: wallet)
                
                binance!.broadcast(message: bnbMsg, sync: true) { (response) in
                    if let error = response.error {
                        if(SHOW_LOG) { print(error.localizedDescription) }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                self.onStartTxDetail(txResult)
                            })
                        }
                    }
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            txResult["hash"] = response.broadcast[0].hash
                            self.onStartTxDetail(txResult)
                        })
                    }
                    print(response.broadcast)
                }
            }
        }
    }
    
    
    //gRPC
    func onFetchgRPCAuth(_ account: Account) {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Auth_V1beta1_QueryAccountRequest.with {
                $0.address = account.account_address
            }
            do {
                let response = try Cosmos_Auth_V1beta1_QueryClient(channel: channel).account(req).response.wait()
                self.onBroadcastGrpcTx(response)
            } catch {
                print("onFetchgRPCAuth failed: \(error)")
            }
        }
    }
    
    func onBroadcastGrpcTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse?) {
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.pageHolderVC.mAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let reqTx = Signer.genSignedSendTxgRPC(auth!, self.pageHolderVC.mToSendRecipientAddress!, self.pageHolderVC.mToSendAmount,
                                                   self.pageHolderVC.mFee!, self.pageHolderVC.mMemo!, WKey.getHDKeyFromWords(words, self.pageHolderVC.mAccount!),
                                                   BaseData.instance.getChainId_gRPC())
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            do {
                let response = try Cosmos_Tx_V1beta1_ServiceClient(channel: channel).broadcastTx(reqTx).response.wait()
//                print("response ", response.txResponse.txhash)
                DispatchQueue.main.async(execute: {
                    if (self.waitAlert != nil) {
                        self.waitAlert?.dismiss(animated: true, completion: {
                            self.onStartTxDetailgRPC(response)
                        })
                    }
                });
            } catch {
                print("onBroadcastGrpcTx failed: \(error)")
            }
        }
    }
}
