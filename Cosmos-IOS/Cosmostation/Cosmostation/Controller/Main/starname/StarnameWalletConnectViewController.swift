//
//  StarnameWalletConnectViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/11/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import WalletConnect

class StarnameWalletConnectViewController: BaseViewController {
    
    @IBOutlet weak var chainImg: UIImageView!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var connectLabel: UILabel!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    let clientMeta = WCPeerMeta(name: "", url: "")
    var wcURL:String?
    var interactor: WCInteractor?
    var cId:Int64?
    var exportAddress: ExportStarname?

    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        self.loadingImg.onStartAnimation()
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
        interactor.onSessionRequest = { [weak self] (id, peer) in
            self?.onViewUpdate(peer)
        }

        interactor.onDisconnect = { [weak self] (error) in
            self?.navigationController?.popViewController(animated: false)
        }
    }
    
    
    
    func onViewUpdate(_ peer: WCPeerMeta) {
        self.loadingImg.stopAnimating()
        self.loadingImg.isHidden = true
        
        self.chainImg.af_setImage(withURL: URL(string: peer.icons[0])!)
        self.descriptionLabel.text = peer.name
        self.connectLabel.text = peer.url
        
        let allAccount = BaseData.instance.selectAllAccounts()
        exportAddress = WUtils.getExportResource(allAccount)
        
        let encoder = JSONEncoder()
        let jsonData = try? encoder.encode(exportAddress)
        let json = String(data: jsonData!, encoding: .utf8)
        
        if (peer.url != "https://app.starname.me") {
            self.dismiss(animated: true, completion: nil)
        } else if (allAccount.count < 1) {
            self.onShowToast(NSLocalizedString("error_no_address_export", comment: ""))
        } else {
            self.onShowConfirmDialog(allAccount.count, json!)
        }
        
    }
    
    func onShowConfirmDialog(_ count: Int, _ json: String) {
        let confirmMsg = String(format: NSLocalizedString("str_starname_walletconnect_alert_msg2", comment: ""), String(count))
        let starnameWCAlert = UIAlertController(title: NSLocalizedString("str_starname_walletconnect_alert_title", comment: ""), message: confirmMsg, preferredStyle: .alert)
        starnameWCAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        starnameWCAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default, handler: { _ in
            self.onStartExportAddress(json)
        }))
        self.present(starnameWCAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            starnameWCAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onStartExportAddress(_ jsonData: String) {
        self.interactor?.approveSession(accounts: [jsonData], chainId: -1).done { _ in
            self.onShowToast(NSLocalizedString("str_starname_walletconnect_complete", comment: ""))
        }.cauterize()
    }
    
    @IBAction func onClickDisconnect(_ sender: UIButton) {
        self.interactor?.killSession().done {[weak self] in
            self?.navigationController?.popViewController(animated: false)
        }.cauterize()
    }
    
}
