//
//  AllCdpViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class AllCdpViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var allCdpCntLabel: UILabel!
    @IBOutlet weak var allCdpTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.allCdpTableView.delegate = self
        self.allCdpTableView.dataSource = self
        self.allCdpTableView.register(UINib(nibName: "CdpListAllCell", bundle: nil), forCellReuseIdentifier: "CdpListAllCell")
        self.allCdpTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.allCdpTableView.rowHeight = UITableView.automaticDimension
        self.allCdpTableView.estimatedRowHeight = UITableView.automaticDimension
    }
    
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpListAllCell? = tableView.dequeueReusableCell(withIdentifier:"CdpListAllCell") as? CdpListAllCell
        
//        let url = KAVA_CDP_MARKET_IMG_URL + "xrpusd" + ".png"
//        Alamofire.request(url, method: .get).responseImage { response  in
//            guard let image = response.result.value else {
//                return
//            }
//            cell?.marketImg.image = image
//        }
        
        return cell!
    }
}
