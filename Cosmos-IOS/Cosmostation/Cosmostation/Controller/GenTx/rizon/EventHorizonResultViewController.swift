//
//  EventHorizonResultViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class EventHorizonResultViewController: BaseViewController {
    
    @IBOutlet weak var txResultImg: UIImageView!
    @IBOutlet weak var txResultLabel: UILabel!
    @IBOutlet weak var blockHeightLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var blockHashLabel: UILabel!
    @IBOutlet weak var txHashLabel: UILabel!
    @IBOutlet weak var burnAmountLabel: UILabel!
    @IBOutlet weak var rizonAddressLabel: UILabel!
    
    @IBOutlet weak var btnExplorer: UIButton!
    @IBOutlet weak var btnDone: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    var txResult: NSDictionary?
    var txDetail: HdacTx?

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        
        guard let txId = txResult?.object(forKey: "txid") as? String else {
            txResultImg.image = UIImage(named: "failIc")
            txResultLabel.text = NSLocalizedString("tx_fail", comment: "")
            return
        }
        self.onCheckTxDetail(txId)
    }
    
    func onUpdateView() {
        blockHeightLabel.text = String(txDetail!.blockheight!)
        timeLabel.text = WUtils.longTimetoString(input: Int64(txDetail!.time!) * 1000)
        blockHashLabel.text = txDetail!.blockhash
        txHashLabel.text = txDetail!.txid
        burnAmountLabel.attributedText = WUtils.displayAmount2(String(txDetail!.valueOut!), burnAmountLabel.font!, 0, 8)
        rizonAddressLabel.text = txDetail!.data![0].hexToString()
        self.loadingImg.onStopAnimation()
        self.loadingImg.isHidden = true
    }

    @IBAction func onClickExplorer(_ sender: UIButton) {
        if (txDetail?.txid == nil) { return }
        var urlLink = ""
        if (chainType == ChainType.RIZON_TEST) {
            urlLink = EXPLORER_HDAC_TEST + "tx/" + txDetail!.txid!
        } else {
            urlLink = EXPLORER_HDAC_MAIN + "tx/" + txDetail!.txid!
        }
        guard let url = URL(string: urlLink) else { return }
        self.onShowSafariWeb(url)
    }
    
    @IBAction func onClickDone(_ sender: UIButton) {
        self.onStartMainTab()
    }
    
    var mFetchCnt = 5
    func onCheckTxDetail(_ txHash: String) {
        let request = Alamofire.request(BaseNetWork.hdacTxDetail(chainType, txHash), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let rawTxDetail = res as? NSDictionary {
                    self.txDetail = HdacTx.init(rawTxDetail)
                }
                if (self.txDetail == nil && self.mFetchCnt > 0) {
                    self.mFetchCnt = self.mFetchCnt - 1
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                        self.onCheckTxDetail(txHash)
                    })
                } else {
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(500), execute: {
                        self.onUpdateView()
                    })
                }
            
            case .failure(let error):
                print("hdacTxDetail ", error)
                if (self.mFetchCnt > 0) {
                    self.mFetchCnt = self.mFetchCnt - 1
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                        self.onCheckTxDetail(txHash)
                    })
                } else {
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(500), execute: {
                        self.onUpdateView()
                    })
                }
            }
        }
    }
}
