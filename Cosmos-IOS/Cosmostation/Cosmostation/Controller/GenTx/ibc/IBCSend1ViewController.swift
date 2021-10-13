//
//  IBCSend1ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/24.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import GRPC
import NIO

class IBCSend1ViewController: BaseViewController, QrScannerDelegate, SBCardPopupDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var detinationChainLabel: UILabel!
    @IBOutlet weak var addressInput: AddressInputTextField!
    @IBOutlet weak var btnWallet: UIButton!
    @IBOutlet weak var btnQrScan: UIButton!
    @IBOutlet weak var btnPaste: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var toChain: ChainType!
    var toReceivableAccounts = Array<Account>()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
    }
    
    override func enableUserInteraction() {
        self.onUpdateView()
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        self.toChain = WUtils.getChainTypeByChainId(pageHolderVC.mIBCSendRelayer?.chain_id)
        self.detinationChainLabel.text = WUtils.getChainTitle(toChain)
        self.detinationChainLabel.textColor = WUtils.getChainColor(toChain)
    }
    
    @IBAction func onClickWallet(_ sender: UIButton) {
        toReceivableAccounts = BaseData.instance.selectAllAccountsByChain(toChain)
        if (toReceivableAccounts.count <= 0) {
            self.onShowToast(NSLocalizedString("error_no_wallet_this_chain", comment: ""))
            return
            
        } else {
            let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
            popupVC.type = SELECT_POPUP_IBC_RECIPIENT
            popupVC.toChain = toChain
            let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
            cardPopup.resultDelegate = self
            cardPopup.show(onViewController: self)
        }
    }
    
    func SBCardPopupResponse(type: Int, result: Int) {
        self.addressInput.text = toReceivableAccounts[result].account_address
    }

    @IBAction func onClickScan(_ sender: UIButton) {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.modalPresentationStyle = .fullScreen
        qrScanVC.resultDelegate = self
        self.present(qrScanVC, animated: true, completion: nil)
    }
    
    func scannedAddress(result: String) {
        self.addressInput.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
    
    @IBAction func onClickPaste(_ sender: UIButton) {
        if let myString = UIPasteboard.general.string {
            self.addressInput.text = myString
        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))
        }
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        btnBack.isUserInteractionEnabled = false
        btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        let userInputRecipient = addressInput.text?.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        if (WUtils.isStarnameValidStarName(userInputRecipient!.lowercased())) {
            self.onCheckNameservice(userInputRecipient!.lowercased())
            return;
        }
        if (WUtils.isValidChainAddress(toChain, userInputRecipient)) {
            btnBack.isUserInteractionEnabled = true
            btnNext.isUserInteractionEnabled = true
            pageHolderVC.mIBCRecipient = userInputRecipient
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_invalid_address_or_pubkey", comment: ""))
            return;
        }
    }
    
    func onCheckNameservice(_ userInput: String) {
        DispatchQueue.global().async {
            do {
                let channel = BaseNetWork.getConnection(ChainType.IOV_MAIN, MultiThreadedEventLoopGroup(numberOfThreads: 1))!
                let req = Starnamed_X_Starname_V1beta1_QueryStarnameRequest.with { $0.starname = userInput }
                let response = try Starnamed_X_Starname_V1beta1_QueryClient(channel: channel).starname(req, callOptions:BaseNetWork.getCallOptions()).response.wait()
                try channel.close().wait()
                DispatchQueue.main.async(execute: {
                    guard let matchedAddress = WUtils.checkStarnameWithResource(self.toChain, response) else {
                        self.onShowToast(NSLocalizedString("error_no_mattched_starname", comment: ""))
                        return
                    }
                    if (self.pageHolderVC.mAccount?.account_address == matchedAddress) {
                        self.onShowToast(NSLocalizedString("error_starname_self_send", comment: ""))
                        return;
                    }
                    self.onShowMatchedStarName(userInput, matchedAddress)
                });
                
            } catch {
                print("onFetchgRPCResolve failed: \(error)")
                DispatchQueue.main.async(execute: {
                    self.onShowToast(NSLocalizedString("error_invalide_starname", comment: ""))
                    return
                });
            }
        }
    }
    
    func onShowMatchedStarName(_ starname: String, _ matchedAddress: String) {
        let msg = String(format: NSLocalizedString("str_starname_confirm_msg", comment: ""), starname, matchedAddress)
        let alertController = UIAlertController(title: NSLocalizedString("str_starname_confirm_title", comment: ""), message: msg, preferredStyle: .alert)
        let settingsAction = UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default) { (_) -> Void in
            self.btnBack.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            self.pageHolderVC.mIBCRecipient = matchedAddress
            self.pageHolderVC.onNextPage()
        }
        let cancelAction = UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: nil)
        alertController.addAction(cancelAction)
        alertController.addAction(settingsAction)
        DispatchQueue.main.async {
            self.present(alertController, animated: true, completion: nil)
        }
    }
}
