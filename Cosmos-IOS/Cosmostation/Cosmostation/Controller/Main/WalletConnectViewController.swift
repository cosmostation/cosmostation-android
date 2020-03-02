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
import AlamofireImage
import BinanceChain
import SwiftKeychainWrapper
import BitcoinKit

class WalletConnectViewController: BaseViewController {

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
            if let bnbOrder = order as? WCBinanceTradeOrder {
                let price =  NSDecimalNumber.init(value: bnbOrder.msgs[0].price).dividing(by: NSDecimalNumber.init(string: "100000000"), withBehavior: WUtils.handler8)
                let quantity =  NSDecimalNumber.init(value: bnbOrder.msgs[0].quantity).dividing(by: NSDecimalNumber.init(string: "100000000"), withBehavior: WUtils.handler8)
                
                var msg = NSLocalizedString("wc_request_sign_msg", comment: "")
                msg = msg + "\n\n Symbol : " + bnbOrder.msgs[0].symbol + "\n" +
                "Price : " + price.stringValue + "\n" +
                "Quantity : " + quantity.stringValue
                
                let alert = UIAlertController(title: NSLocalizedString("wc_request_sign_title", comment: ""), message: msg, preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .destructive, handler: nil))
                alert.addAction(UIAlertAction(title: NSLocalizedString("sign", comment: ""), style: .default, handler: { [weak self] _ in
                    self?.signBnbOrder(id: id, order: order)
                }))
                self?.present(alert, animated: true, completion: nil)
                
            }
            
            if let bnbOrder = order as? WCBinanceCancelOrder {
                var msg = NSLocalizedString("wc_request_cancel_msg", comment: "")
                msg = msg + "Symbol : " + bnbOrder.msgs[0].symbol + "\n"
                let alert = UIAlertController(title: NSLocalizedString("wc_request_sign_title", comment: ""), message: msg, preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .destructive, handler: nil))
                alert.addAction(UIAlertAction(title: NSLocalizedString("sign", comment: ""), style: .default, handler: { [weak self] _ in
                    self?.signBnbOrder(id: id, order: order)
                }))
                self?.present(alert, animated: true, completion: nil)
            }
            
        }
    }
    
    
    func onViewUpdate(_ peer: WCPeerMeta) {
        Alamofire.request(peer.icons[0], method: .get).responseImage { response  in
            guard let image = response.result.value else {
                return
            }
            self.wcImg.image = image
        }
        
        self.wcTitle.text = peer.name
        self.wcUrl.text = peer.url
        self.wcAddress.text = account?.account_address
        self.wcCardView.isHidden = false
        self.wcLoading.isHidden = false
        self.wcBtnDisconnect.isHidden = false
        self.wcWaitting.isHidden = true
        self.wcLoading.onStartAnimation()
        
    }
    
    func signBnbOrder(id: Int64, order: WCBinanceOrder) {
        guard let words = KeychainWrapper.standard.string(forKey: account!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
            return
        }
        let pKey = WKey.getHDKeyFromWords(words, account!)
        let extendPKey = PrivateKey.init(data: pKey.privateKey().raw, network: .testnet, isPublicKeyCompressed: false)
        let pubKeyString = extendPKey.publicKey().raw.dataToHexString()
        let bnbWallet = Wallet(privateKey: pKey.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.mainnet)

        bnbWallet.synchronise(){ (error) in
            if let _ = error {
                return
            }
            
            let signature = bnbWallet.sign(message: order.encoded)
            let signed = WCBinanceOrderSignature(
                signature: signature.dataToHexString(),
                publicKey: pubKeyString
            )
            self.interactor?.approveBnbOrder(id: id, signed: signed).done({ confirm in
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
        }
    }
    

    @IBAction func onClickDisconnect(_ sender: UIButton) {
        self.interactor?.killSession().done {[weak self] in
            self?.navigationController?.popViewController(animated: false)
        }.cauterize()
    }
}
