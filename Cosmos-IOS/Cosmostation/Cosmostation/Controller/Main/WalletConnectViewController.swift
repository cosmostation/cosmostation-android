//
//  WalletConnectViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletConnectViewController: UIViewController {

    @IBOutlet weak var wcImg: UIImageView!
    @IBOutlet weak var wcTitle: UILabel!
    @IBOutlet weak var wcUrl: UILabel!
    @IBOutlet weak var wcAddress: UILabel!
    @IBOutlet weak var wcLoading: WalletConnectImageView!
    @IBOutlet weak var wcWaitting: LoadingImageView!
    @IBOutlet weak var wcBtnDisconnect: UIButton!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.wcLoading.onStartAnimation()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_wallet_connect", comment: "");
        self.navigationItem.title = NSLocalizedString("title_wallet_connect", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

    @IBAction func onClickDisconnect(_ sender: UIButton) {
    }
}
