//
//  GravitySwapViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/31.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class GravitySwapViewController: BaseViewController {
    
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var poolIdLabel: UILabel!
    @IBOutlet weak var swapFeeLabel: UILabel!
    @IBOutlet weak var inputCoinRateAmount: UILabel!
    @IBOutlet weak var inputCoinRateDenom: UILabel!
    @IBOutlet weak var outputCoinRateAmount: UILabel!
    @IBOutlet weak var outputCoinRateDenom: UILabel!
    
    @IBOutlet weak var inputCoinLayer: CardView!
    @IBOutlet weak var inputCoinImg: UIImageView!
    @IBOutlet weak var inputCoinName: UILabel!
    @IBOutlet weak var inputCoinAvailableAmountLabel: UILabel!
    @IBOutlet weak var inputCoinAvailableDenomLabel: UILabel!
    
    @IBOutlet weak var outputCoinLayer: CardView!
    @IBOutlet weak var outputCoinImg: UIImageView!
    @IBOutlet weak var outputCoinName: UILabel!
    @IBOutlet weak var outputCoinAvailableAmountLabel: UILabel!
    @IBOutlet weak var outputCoinAvailableDenomLabel: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onGdexFetchDone(_:)), name: Notification.Name("GdexFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("GdexFetchDone"), object: nil)
    }
    
    @objc func onGdexFetchDone(_ notification: NSNotification) {
        print("GravitySwapViewController onGdexFetchDone")
        print("mGravityPools_gRPC ", BaseData.instance.mGravityPools_gRPC.count)
        print("mGravityManager_gRPC ", BaseData.instance.mGravityManager_gRPC.count)
    }

}
