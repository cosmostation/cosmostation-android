//
//  DomainDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class DomainDetailViewController: BaseViewController {

    @IBOutlet weak var myDomainLabel: UILabel!
    @IBOutlet weak var myDomainType: UILabel!
    @IBOutlet weak var myDomainAddressCntLael: UILabel!
    @IBOutlet weak var myDomainExpireTimeLabel: UILabel!
    @IBOutlet weak var myDomainResourceTableView: UITableView!
    @IBOutlet weak var myDomainEmptyView: UIView!
    
    var mMyDomain: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_starname_domain_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_starname_domain_detail", comment: "")
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
