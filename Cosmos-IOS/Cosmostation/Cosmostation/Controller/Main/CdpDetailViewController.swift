//
//  CdpDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class CdpDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    

    @IBOutlet weak var cdpDetailTableView: UITableView!
    @IBOutlet weak var createCdpBtn: UIButton!
    
    var cdpCollateralDenom: String = ""
    var cdpMarketID: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.cdpDetailTableView.delegate = self
        self.cdpDetailTableView.dataSource = self
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailTopCell", bundle: nil), forCellReuseIdentifier: "CdpDetailTopCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailMyTopCell", bundle: nil), forCellReuseIdentifier: "CdpDetailMyTopCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "CdpDetailMyActionCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailAssetsCell", bundle: nil), forCellReuseIdentifier: "CdpDetailAssetsCell")
        self.cdpDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.cdpDetailTableView.rowHeight = UITableView.automaticDimension
        self.cdpDetailTableView.estimatedRowHeight = UITableView.automaticDimension
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_cdp_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_cdp_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 4
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            let cell:CdpDetailTopCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailTopCell") as? CdpDetailTopCell
            return cell!
            
        } else if (indexPath.row == 1) {
            let cell:CdpDetailMyTopCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyTopCell") as? CdpDetailMyTopCell
            return cell!
            
        } else if (indexPath.row == 2) {
            let cell:CdpDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyActionCell") as? CdpDetailMyActionCell
            return cell!
            
        } else {
            let cell:CdpDetailAssetsCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailAssetsCell") as? CdpDetailAssetsCell
            return cell!
            
        }
    }

    
    @IBAction func onClickCreateCdp(_ sender: UIButton) {
        
    }
}
