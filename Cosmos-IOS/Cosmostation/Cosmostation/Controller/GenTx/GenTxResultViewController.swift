//
//  GenTxResultViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class GenTxResultViewController: BaseViewController {
    
    var response:[String:Any]?

    @IBOutlet weak var txResultTitleLabel: UILabel!
    @IBOutlet weak var actionLayer: UIStackView!
    @IBOutlet weak var dismissBtn: UIButton!
    
    @IBOutlet weak var mainCardView: CardView!
    @IBOutlet weak var txTypeLabel: UILabel!
    @IBOutlet weak var txResultLabel: UILabel!
    @IBOutlet weak var txHashLabel: UILabel!
    @IBOutlet weak var blockHeightLabel: UILabel!
    @IBOutlet weak var blockTimeLabel: UILabel!
    
    @IBOutlet weak var txAmountTitleLabel: UILabel!
    @IBOutlet weak var txAmountAtomLabel: UILabel!
    @IBOutlet weak var txAmountLabel: UILabel!
    @IBOutlet weak var txFeeLabel: UILabel!
    
    @IBOutlet weak var txSecondTitleLabel: UILabel!
    @IBOutlet weak var txSecondContentLabel: UILabel!
    
    @IBOutlet weak var txMemoLabel: UILabel!
    
    @IBOutlet weak var errorCardView: CardView!
    @IBOutlet weak var errorCode: UILabel!
    
    @IBOutlet weak var loadingImgs: LoadingImageView!
    
    var mTxType: String?
    var mTxHash: String?
    
    var mTxInfo: TxInfo?
    var mStakTxInfo: StakeTxInfo?
    var mBlockInfo: BlockInfo?
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        print("GenTxResultViewController viewDidLoad ", response)
    
        guard let txType = response?["type"] as? String, let txHash = response?["txhash"] as? String  else {
            self.onStartMainTab()
            return
        }
        mTxType = txType
        mTxHash = txHash
        
        if let code = response?["code"] as? Int {
//            print("code " , code)
            onShowErrorView(code)
            return
        }
        
        self.loadingImgs.onStartAnimation()
        self.onFetchTx(mTxHash!)
        
    }
    
    func onShowErrorView(_ code: Int) {
//        print("onShowErrorView")
        self.txResultTitleLabel.text = "Transaction Failed"
        self.txResultTitleLabel.textColor = UIColor.init(hexString: "f31963")
        self.errorCode.text =  "error code : " + String(code) + "\n" + ((response?["raw_log"] as? String)!)
        self.loadingImgs.isHidden = true
        self.errorCardView.isHidden = false
    }
    
    
    func onTxDetailView() {
//        print("onTxDetailView")
        if (mTxType == COSMOS_MSG_TYPE_DELEGATE) {
            txTypeLabel.text = "Delegate"
            txAmountLabel.attributedText = WUtils.displayAmout((mStakTxInfo?.tx.value.msg[0].value.amount?.amount)!, txAmountLabel.font, 6)
            txSecondTitleLabel.text = "Validator address"
            txSecondContentLabel.text = mStakTxInfo?.tx.value.msg[0].value.validator_address
            txSecondContentLabel.adjustsFontSizeToFitWidth = true
            
            txHashLabel.text = mStakTxInfo?.txhash
            txHashLabel.adjustsFontSizeToFitWidth = true
            blockHeightLabel.text = mBlockInfo?.block_meta.header.height
            blockTimeLabel.text = WUtils.nodeTimetoString(input: (mBlockInfo?.block_meta.header.time)!)
            
            txFeeLabel.attributedText = WUtils.displayAmout((mStakTxInfo?.tx.value.fee.amount[0].amount)!, txMemoLabel.font, 6)
            txMemoLabel.text = mStakTxInfo?.tx.value.memo
            
            
        } else if (mTxType == COSMOS_MSG_TYPE_UNDELEGATE2) {
            txTypeLabel.text = "UnDelegate"
            txAmountLabel.attributedText = WUtils.displayAmout((mStakTxInfo?.tx.value.msg[0].value.amount?.amount)!, txAmountLabel.font, 6)
            txSecondTitleLabel.text = "Validator address"
            txSecondContentLabel.text = mStakTxInfo?.tx.value.msg[0].value.validator_address
            txSecondContentLabel.adjustsFontSizeToFitWidth = true
            
            txHashLabel.text = mTxInfo?.txhash
            txHashLabel.adjustsFontSizeToFitWidth = true
            blockHeightLabel.text = mBlockInfo?.block_meta.header.height
            blockTimeLabel.text = WUtils.nodeTimetoString(input: (mBlockInfo?.block_meta.header.time)!)
            
            txFeeLabel.attributedText = WUtils.displayAmout((mStakTxInfo?.tx.value.fee.amount[0].amount)!, txMemoLabel.font, 6)
            txMemoLabel.text = mStakTxInfo?.tx.value.memo
            
            
        } else if (mTxType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
            txTypeLabel.text = "Get Reward"
            txAmountTitleLabel.isHidden = true
            txAmountAtomLabel.isHidden = true
            txAmountLabel.isHidden = true
            
            txHashLabel.text = mTxInfo?.txhash
            txHashLabel.adjustsFontSizeToFitWidth = true
            blockHeightLabel.text = mBlockInfo?.block_meta.header.height
            blockTimeLabel.text = WUtils.nodeTimetoString(input: (mBlockInfo?.block_meta.header.time)!)
            
            txFeeLabel.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, txMemoLabel.font, 6)
            txMemoLabel.text = mTxInfo?.tx.value.memo
            
        } else if (mTxType == COSMOS_MSG_TYPE_TRANSFER || mTxType == COSMOS_MSG_TYPE_TRANSFER) {
            txSecondTitleLabel.text = "Validator address"
            txSecondContentLabel.text = mTxInfo?.tx.value.msg[0].value.validator_address
            txSecondContentLabel.adjustsFontSizeToFitWidth = true
            
            txHashLabel.text = mTxInfo?.txhash
            txHashLabel.adjustsFontSizeToFitWidth = true
            blockHeightLabel.text = mBlockInfo?.block_meta.header.height
            blockTimeLabel.text = WUtils.nodeTimetoString(input: (mBlockInfo?.block_meta.header.time)!)
            
            txFeeLabel.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, txMemoLabel.font, 6)
            txMemoLabel.text = mTxInfo?.tx.value.memo
            
        } else {
            
        }
        

        
        
        self.loadingImgs.isHidden = true
        self.mainCardView.isHidden = false
        
        self.dismissBtn.isHidden = true
        self.actionLayer.isHidden = false
    }

    
    
    @IBAction func onClickDismiss(_ sender: UIButton) {
        self.onStartMainTab()
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        guard let url = URL(string: "https://www.mintscan.io/txs/" + mTxInfo!.txhash) else { return }
        let safariViewController = SFSafariViewController(url: url)
        present(safariViewController, animated: true, completion: nil)
    }
    
    @IBAction func onClickShare(_ sender: UIButton) {
        let text = "https://www.mintscan.io/txs/" + mTxInfo!.txhash
        let textToShare = [ text ]
        let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
        activityViewController.popoverPresentationController?.sourceView = self.view 
        self.present(activityViewController, animated: true, completion: nil)
    }
    
    @IBAction func onClickOK(_ sender: UIButton) {
        self.onStartMainTab()
    }
    
    
    var fetchCnt = 5
    func onFetchTx(_ txHash: String) {
        let url = CSS_LCD_URL_TX + txHash
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("onFetchTx ", res)
                guard let info = res as? [String : Any], info["error"] == nil else {
                    self.fetchCnt = self.fetchCnt - 1
                    if(self.fetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(5000), execute: {
                            self.onFetchTx(txHash)
                        })
                    }
                    return
                }
                if (self.mTxType == COSMOS_MSG_TYPE_DELEGATE || self.mTxType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                    self.mStakTxInfo = StakeTxInfo.init(info)
                    self.onFetchBlock(self.mStakTxInfo!.height)
                } else {
                    self.mTxInfo = TxInfo.init(info)
                    self.onFetchBlock(self.mTxInfo!.height)
                }
                
                
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchTx ", error)
                }
            }
        }
        
    }
    
    func onFetchBlock(_ height: String) {
        let url = CSS_LCD_URL_BLOCK + height
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchBlock ", res)
                guard let info = res as? [String : Any] else {
//                    print("onFetchTx error")
                    return
                }
                self.mBlockInfo = BlockInfo.init(info)
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchBlock ", error)
                }
                
            }
            self.onTxDetailView()
        }
    }
}
