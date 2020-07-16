//
//  StepHtlcRefund0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepHtlcRefund0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var swapIdLabel: UILabel!
    @IBOutlet weak var refundToLabel: UILabel!
    @IBOutlet weak var refundAmountLabel: UILabel!
    @IBOutlet weak var refundAmountDenom: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mBnbSwapInfo: BnbSwapInfo?
    var mKavaSwapInfo: KavaSwapInfo?

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.loadingImg.onStartAnimation()
        self.onFetchSwapInfo(pageHolderVC.mHtlcRefundSwapId!)
    }
    
    func onUpdateView() {
        self.loadingImg.onStopAnimation()
        self.loadingImg.isHidden = true
        if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
            swapIdLabel.text = pageHolderVC.mHtlcRefundSwapId!
            refundToLabel.text = mBnbSwapInfo?.fromAddr
            let coin = mBnbSwapInfo?.getSendCoin()
            WUtils.showCoinDp(coin!, refundAmountDenom, refundAmountLabel, chainType!)
            
        } else if (self.chainType! == ChainType.KAVA_MAIN || self.chainType! == ChainType.KAVA_TEST) {
            swapIdLabel.text = pageHolderVC.mHtlcRefundSwapId!
            refundToLabel.text = mKavaSwapInfo?.result.sender
            let coin = mKavaSwapInfo?.result.amount[0]
            WUtils.showCoinDp(coin!, refundAmountDenom, refundAmountLabel, chainType!)
            
        }
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        self.pageHolderVC.onNextPage()
    }
    
    
    func onFetchSwapInfo(_ swapId: String) {
        var url = ""
        if (self.chainType! == ChainType.BINANCE_MAIN) {
            url = BNB_URL_CHECK_SWAPID + swapId
            
        } else if (self.chainType! == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_CHECK_SWAPID + swapId
            
        } else if (self.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_CHECK_SWAPID + swapId
            
        } else if (self.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_CHECK_SWAPID + swapId
        }
        
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    if (self.chainType! == ChainType.BINANCE_MAIN || self.chainType! == ChainType.BINANCE_TEST) {
                        if let info = res as? [String : Any] {
                            self.mBnbSwapInfo = BnbSwapInfo.init(info)
                            self.pageHolderVC.mBnbSwapInfo = self.mBnbSwapInfo
                        }
                        
                    } else if (self.chainType! == ChainType.KAVA_MAIN || self.chainType! == ChainType.KAVA_TEST) {
                        if let info = res as? [String : Any], info["error"] == nil  {
                            self.mKavaSwapInfo = KavaSwapInfo.init(info)
                            self.pageHolderVC.mKavaSwapInfo = self.mKavaSwapInfo
                        }
                    }

                    self.onUpdateView()
                    
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchSwapId", error) }
                    self.onUpdateView()
                    return
            }
        }
    }
}
