//
//  AccountDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class AccountDetailViewController: BaseViewController {
    @IBOutlet weak var myAccountNameLabel: UILabel!
    @IBOutlet weak var myAccountAddressCntLabel: UILabel!
    @IBOutlet weak var myAccountExpireTimeLabel: UILabel!
    @IBOutlet weak var myAccountResourceTableView: UITableView!
    @IBOutlet weak var myAccountEmptyView: UIView!
    
    var mMyDomain: String?
    var mMyAccount: String?

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_starname_account_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_starname_account_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    @IBAction func onClickDelete(_ sender: UIButton) {
    }
    @IBAction func onClickRenew(_ sender: UIButton) {
    }
    @IBAction func onClickReplace(_ sender: UIButton) {
    }
}
