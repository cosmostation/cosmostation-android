//
//  StepEventHorizon1ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/02.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepEventHorizon1ViewController: BaseViewController, PasswordViewDelegate {
    
    @IBOutlet weak var hdacTxtLabel: UILabel!
    @IBOutlet weak var hdacAddressLabel: UILabel!
    @IBOutlet weak var hdacBurnAmountLabel: UILabel!
    @IBOutlet weak var hdacTxFeeLabel: UILabel!
    
    @IBOutlet weak var rizonTxLabel: UILabel!
    @IBOutlet weak var rizonAddressLabel: UILabel!
    @IBOutlet weak var rizonMintAmountLabel: UILabel!
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnConfirm.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        if (chainType == ChainType.RIZON_TEST) {
            hdacTxtLabel.text = "Hdac Testnet"
            rizonTxLabel.text = "Rizon Testnet"
        } else {
            hdacTxtLabel.text = "Hdac Mainnet"
            rizonTxLabel.text = "Rizon Mainnet"
        }
        
        hdacAddressLabel.text = pageHolderVC.mHdacAddress
        hdacBurnAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mHdacBalance?.subtracting(NSDecimalNumber.init(string: "0.1")).stringValue, hdacBurnAmountLabel.font, 0, 8)
        hdacTxFeeLabel.attributedText = WUtils.displayAmount2(NSDecimalNumber.init(string: "0.1").stringValue, hdacTxFeeLabel.font, 0, 8)
        
        rizonAddressLabel.text = self.account?.account_address
        rizonMintAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mHdacBalance?.subtracting(NSDecimalNumber.init(string: "0.1")).stringValue, hdacBurnAmountLabel.font, 0, 6)
        
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
    
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onBroadCastBurnTx()
        }
    }
    
    func onBroadCastBurnTx() {
        self.showWaittingAlert()
        DispatchQueue.global().async {
            let signedTxHex = try! HdacUtil.createSwapTx(self.chainType, self.pageHolderVC!.mHdacUTXOs!, self.account!.account_address, self.pageHolderVC!.mHdacKey!)
            print("signedTxHex ", signedTxHex)
            
            DispatchQueue.main.async(execute: {
                let request = Alamofire.request(BaseNetWork.hdacBroadcast(self.chainType), method: .get, parameters: ["rawtx":signedTxHex], encoding: URLEncoding.default, headers: [:])
                request.responseJSON { (response) in
                    switch response.result {
                    case .success(let res):
                        print("res ", res)
                        
                    case .failure(let error):
                        print("onBroadCastBurnTx ", error)
                    }
                }
            });
        }
    }
}
