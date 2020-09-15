//
//  StepHtlcSend1ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend1ViewController: BaseViewController, SBCardPopupDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var toAddressCard: CardView!
    @IBOutlet weak var toAddressImg: UIImageView!
    @IBOutlet weak var toAddressTxt: UILabel!
    @IBOutlet weak var warnMsg: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var toAccountList = Array<Account>()
    var toAccount: Account?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.toAddressCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToAddress)))

    }
    
    func updateView() {
        toAddressImg.image = toAddressImg.image?.withRenderingMode(.alwaysTemplate)
        if (pageHolderVC.mHtlcToChain == ChainType.BINANCE_MAIN || pageHolderVC.mHtlcToChain == ChainType.BINANCE_TEST) {
            self.warnMsg.text = String(format: NSLocalizedString("error_can_not_bep3_account_msg", comment: ""), WUtils.dpChainName(pageHolderVC!.mHtlcToChain!))
            toAddressImg.tintColor = COLOR_BNB
        } else if (pageHolderVC.mHtlcToChain == ChainType.KAVA_MAIN || pageHolderVC.mHtlcToChain == ChainType.KAVA_TEST) {
            self.warnMsg.text = String(format: NSLocalizedString("error_can_not_bep3_account_msg2", comment: ""), WUtils.dpChainName(pageHolderVC!.mHtlcToChain!))
            toAddressImg.tintColor = COLOR_KAVA
        }
        self.toAccountList = BaseData.instance.selectAllAccountsByHtlcClaim(pageHolderVC.mHtlcToChain)
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
        self.updateView()
    }

    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (toAccount == nil) {
            self.onShowToast(NSLocalizedString("error_no_recipient_account", comment: ""))
            return
        }
        pageHolderVC.mHtlcToAccount = self.toAccount
        self.btnBack.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    @objc func onClickToAddress (_ sender: UITapGestureRecognizer) {
        if (self.toAccountList.count > 0) {
            let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
            popupVC.type = SELECT_POPUP_HTLC_TO_ACCOUNT
            popupVC.toChain = pageHolderVC.mHtlcToChain
            let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
            cardPopup.resultDelegate = self
            cardPopup.show(onViewController: self)

        } else {
            var msg = ""
            let title = String(format: NSLocalizedString("no_account", comment: ""), WUtils.dpChainName(pageHolderVC!.mHtlcToChain!))
            if (pageHolderVC.mHtlcToChain == ChainType.BINANCE_MAIN || pageHolderVC.mHtlcToChain == ChainType.BINANCE_TEST) {
                msg = String(format: NSLocalizedString("error_can_not_bep3_account_msg", comment: ""), WUtils.dpChainName(pageHolderVC!.mHtlcToChain!))
            } else if (pageHolderVC.mHtlcToChain == ChainType.KAVA_MAIN || pageHolderVC.mHtlcToChain == ChainType.KAVA_TEST) {
                msg = String(format: NSLocalizedString("error_can_not_bep3_account_msg2", comment: ""), WUtils.dpChainName(pageHolderVC!.mHtlcToChain!))
            }
            let noAccountAlert = UIAlertController(title: title, message: msg, preferredStyle: .alert)
            noAccountAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
                self.dismiss(animated: true, completion: nil)
            }))
            self.present(noAccountAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                noAccountAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
        }
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        self.toAccount = self.toAccountList[result]
        self.toAddressImg.isHidden = false
        self.toAddressTxt.text = self.toAccount?.account_address
    }
}
