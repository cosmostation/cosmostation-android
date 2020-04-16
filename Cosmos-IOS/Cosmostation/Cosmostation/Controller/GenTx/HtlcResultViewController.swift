//
//  HtlcResultViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HtlcResultViewController: BaseViewController {

    @IBOutlet weak var htlcResultTableView: UITableView!
    @IBOutlet weak var bottomControlLayer: UIStackView!
    @IBOutlet weak var btnSentWallet: UIButton!
    @IBOutlet weak var btnReceievedWallet: UIButton!
    
    @IBOutlet weak var errorCard: CardView!
    @IBOutlet weak var errorMsgLabel: UILabel!
    @IBOutlet weak var errorCodeLabel: UILabel!
    
    @IBOutlet weak var loadingLayer: UIView!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    var mHtlcToSendAmount = Array<Coin>()
    var mHtlcToChain: ChainType?
    var mHtlcToAccount: Account?
    var mHtlcSendFee: Fee?
    var mHtlcClaimFee: Fee?
    
    var mSendTxInfo: TxInfo?
    var mClaimTxInfo: TxInfo?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
        //TODO requset 2 tx in orderly
    }
    
    @IBAction func onClickSentWallet(_ sender: UIButton) {
        
    }
    
    @IBAction func onClickReceivedWallet(_ sender: UIButton) {
    }
    
}
