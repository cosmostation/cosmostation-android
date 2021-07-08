//
//  WalletConnectViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import WalletConnect
import Alamofire
import SwiftKeychainWrapper
import HDWalletKit

class WalletConnectViewController: BaseViewController, SBCardPopupDelegate {

    @IBOutlet weak var wcCardView: CardView!
    @IBOutlet weak var wcImg: UIImageView!
    @IBOutlet weak var wcTitle: UILabel!
    @IBOutlet weak var wcUrl: UILabel!
    @IBOutlet weak var wcAddress: UILabel!
    @IBOutlet weak var wcLoading: WalletConnectImageView!
    @IBOutlet weak var wcWaitting: LoadingImageView!
    @IBOutlet weak var wcBtnDisconnect: UIButton!
    
    var wcURL:String?
    var interactor: WCInteractor?
    let clientMeta = WCPeerMeta(name: "", url: "")
    var defaultChainId: Int = 82
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.wcWaitting.onStartAnimation()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_wallet_connect", comment: "");
        self.navigationItem.title = NSLocalizedString("title_wallet_connect", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        UIApplication.shared.isIdleTimerDisabled = true
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        UIApplication.shared.isIdleTimerDisabled = false
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        guard let session = WCSession.from(string: wcURL!) else {
            self.navigationController?.popViewController(animated: false)
            return
        }
        self.onConnectSession(session)
    }
    
    override func willMove(toParent parent: UIViewController?) {
        super.willMove(toParent: parent)
        if parent == nil {
            self.interactor?.killSession().cauterize()
        }
    }
    
    
    func onConnectSession(_ session: WCSession) {
        let interactor = WCInteractor(session: session, meta: clientMeta)
        configure(interactor: interactor)
        interactor.connect().cauterize()
        self.interactor = interactor
    }
    
    var wcPopup:SBCardPopupViewController?
    var cOrder:WCBinanceOrder?
    var cId:Int64?
    
    func configure(interactor: WCInteractor) {
        let accounts = [account!.account_address]
        let chainId = defaultChainId

        interactor.onSessionRequest = { [weak self] (id, peer) in
            self?.interactor?.approveSession(accounts: accounts, chainId: chainId).done { _ in
                self?.onViewUpdate(peer)
            }.cauterize()
            
        }

        interactor.onDisconnect = { [weak self] (error) in
            self?.navigationController?.popViewController(animated: false)
        }

        interactor.onBnbSign = { [weak self] (id, order) in
            if (self?.wcPopup?.viewIfLoaded?.window != nil) {
                self?.wcPopup?.dismiss(animated: true, completion: {
                    self?.onShowPopupForRequest(id: id, order: order)
                })
            } else {
                self?.onShowPopupForRequest(id: id, order: order)
            }
        }
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(300), execute: {
            if(result == 1) {
                self.onFetchAccountInfo(self.account!)
            }
        })
    }
    
    func onViewUpdate(_ peer: WCPeerMeta) {
        wcImg.af_setImage(withURL: URL(string: peer.icons[0])!)
        
        self.wcTitle.text = peer.name
        self.wcUrl.text = peer.url
        self.wcAddress.text = account?.account_address
        self.wcCardView.isHidden = false
        self.wcLoading.isHidden = false
        self.wcBtnDisconnect.isHidden = false
        self.wcWaitting.isHidden = true
        self.wcLoading.onStartAnimation()
        
    }
    
    func onShowPopupForRequest(id: Int64, order: WCBinanceOrder) {
        self.cId = id
        self.cOrder = order
        if let bnbOrder = order as? WCBinanceTradeOrder {
            let popupVC = WcTradePopup(nibName: "WcTradePopup", bundle: nil)
            popupVC.bnbOrderId = id
            popupVC.bnbOrder = bnbOrder
            self.wcPopup = SBCardPopupViewController(contentViewController: popupVC)
            self.wcPopup?.resultDelegate = self
            self.wcPopup?.show(onViewController: self)
            return
        }

        if let bnbOrder = order as? WCBinanceCancelOrder {
            let popupVC = WcCancelPopup(nibName: "WcCancelPopup", bundle: nil)
            popupVC.bnbOrderId = id
            popupVC.bnbOrder = bnbOrder
            self.wcPopup = SBCardPopupViewController(contentViewController: popupVC)
            self.wcPopup!.resultDelegate = self
            self.wcPopup!.show(onViewController: self)
            return
        }
        
        if let bnbOrder = order as? WCBinanceTransferOrder {
            let popupVC = WcTransferPopup(nibName: "WcTransferPopup", bundle: nil)
            popupVC.bnbOrderId = id
            popupVC.bnbOrder = bnbOrder
            self.wcPopup = SBCardPopupViewController(contentViewController: popupVC)
            self.wcPopup!.resultDelegate = self
            self.wcPopup!.show(onViewController: self)
            return
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        let request = Alamofire.request(BaseNetWork.accountInfoUrl(chainType, account.account_address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.hideWaittingAlert()
                    self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    return
                }
                let bnbAccountInfo = BnbAccountInfo.init(info)
                _ = BaseData.instance.updateAccount(WUtils.getAccountWithBnbAccountInfo(account, bnbAccountInfo))
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithBnbAccountInfo(account, bnbAccountInfo))
                self.signBnbOrder()
                
            case .failure(let error):
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                print("onFetchAccountInfo ", error)
            }
        }
    }
    
    func signBnbOrder() {
        do {
            guard let words = KeychainWrapper.standard.string(forKey: account!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            let pKey = WKey.getHDKeyFromWords(words, account!)
            let pubKeyString = pKey.publicKey.uncompressedPublicKey.dataToHexString()
            let signature = try ECDSA.compactsign(self.cOrder!.encoded.sha256(), privateKey: pKey.raw)
            let signed = WCBinanceOrderSignature(
                signature: signature.dataToHexString(),
                publicKey: pubKeyString
            )
            self.interactor?.approveBnbOrder(id: self.cId!, signed: signed).done({ confirm in
                if (confirm.ok) {
                    self.onShowToast(NSLocalizedString("wc_request_success", comment: ""))
                } else {
                    if let errorMsg = confirm.errorMsg {
                        self.onShowToast(NSLocalizedString("wc_request_fail", comment: "") + "  " + errorMsg)
                    } else {
                        self.onShowToast(NSLocalizedString("wc_request_fail", comment: ""))
                    }
                }
            }).cauterize()
            
        } catch {
            self.onShowToast(NSLocalizedString("error_network", comment: ""))
        }
    }
    

    @IBAction func onClickDisconnect(_ sender: UIButton) {
        self.interactor?.killSession().done {[weak self] in
            self?.navigationController?.popViewController(animated: false)
        }.cauterize()
    }
}
