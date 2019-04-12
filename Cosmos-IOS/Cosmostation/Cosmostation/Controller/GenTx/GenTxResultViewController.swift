//
//  GenTxResultViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

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
    
    @IBOutlet weak var txAmountLabel: UILabel!
    @IBOutlet weak var txFeeLabel: UILabel!
    
    @IBOutlet weak var txSecondTitleLabel: UILabel!
    @IBOutlet weak var txSecondContentLabel: UILabel!
    
    @IBOutlet weak var txMemoLabel: UILabel!
    
    @IBOutlet weak var errorCardView: CardView!
    @IBOutlet weak var errorCode: UILabel!
    
    @IBOutlet weak var loadingImg: UIImageView!
    
    
    var mTxType: String?
    var mTxHash: String?
    
    var mTxInfo: TxInfo?
    var mBlockInfo: BlockInfo?
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("GenTxResultViewController viewDidLoad ", response)
    
        guard let txType = response?["type"] as? String, let txHash = response?["txhash"] as? String  else {
            print("BIG ERROR1")
            self.onStartMainTab()
            return
        }
        mTxType = txType
        mTxHash = txHash
        
        if let code = response?["code"] as? Int {
            print("code " , code)
            onShowErrorView(code)
            return
        }
        
        self.onFetchTx(mTxHash!)
        
        
    }
    
    func onShowErrorView(_ code: Int) {
        print("onShowErrorView")
        self.txResultTitleLabel.text = "Transaction Failed"
        self.txResultTitleLabel.textColor = UIColor.init(hexString: "f31963")
        self.errorCode.text =  "error code : " + String(code)
        self.loadingImg.isHidden = true
        self.errorCardView.isHidden = false
    }
    
    
    func onTxDetailView() {
        print("onTxDetailView")
        if (mTxType == COSMOS_MSG_TYPE_DELEGATE) {
            txTypeLabel.text = "Delegate"
            txAmountLabel.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.msg[0].value.value?.amount)!, txAmountLabel.font, 6)
            txSecondTitleLabel.text = "Validator address"
            txSecondContentLabel.text = mTxInfo?.tx.value.msg[0].value.validator_address
            txSecondContentLabel.adjustsFontSizeToFitWidth = true
            
        } else if (mTxType == COSMOS_MSG_TYPE_TRANSFER || mTxType == COSMOS_MSG_TYPE_TRANSFER) {
            
        } else {
            
        }
        
        txHashLabel.text = mTxInfo?.txhash
        blockHeightLabel.text = mBlockInfo?.block_meta.header.height
        blockTimeLabel.text = WUtils.nodeTimetoString(input: (mBlockInfo?.block_meta.header.time)!)
        
        txFeeLabel.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, txMemoLabel.font, 6)
        txMemoLabel.text = mTxInfo?.tx.value.memo
        
        
        self.loadingImg.isHidden = true
        self.mainCardView.isHidden = false
        
        self.dismissBtn.isHidden = true
        self.actionLayer.isHidden = false
    }

    
    
    @IBAction func onClickDismiss(_ sender: UIButton) {
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
    }
    
    @IBAction func onClickShare(_ sender: UIButton) {
    }
    
    @IBAction func onClickOK(_ sender: UIButton) {
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
                    print("onFetchTx guard in")
                    self.fetchCnt = self.fetchCnt - 1
                    print("fetchCnt ", self.fetchCnt)
                    if(self.fetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(5000), execute: {
                            self.onFetchTx(txHash)
                        })
                    } else {
                        print("onFetchTx error")
                    }
                    return
                }
                print("onFetchTx guard out")
                self.mTxInfo = TxInfo.init(info)
                self.onFetchBlock(self.mTxInfo!.height)
                
            case .failure(let error):
                print("onFetchTx ", error)
            }
        }
        
    }
    
    func onFetchBlock(_ height: String) {
        let url = CSS_LCD_URL_BLOCK + height
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("onFetchBlock ", res)
                guard let info = res as? [String : Any] else {
                    print("onFetchTx error")
                    return
                }
                self.mBlockInfo = BlockInfo.init(info)
                
            case .failure(let error):
                print("onFetchBlock ", error)
            }
            self.onTxDetailView()
        }
    }
}
