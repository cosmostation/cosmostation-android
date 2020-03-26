//
//  MyCdpViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class MyCdpViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    

    @IBOutlet weak var myCdpCntLabel: UILabel!
    @IBOutlet weak var myCdpTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.myCdpTableView.delegate = self
        self.myCdpTableView.dataSource = self
        self.myCdpTableView.register(UINib(nibName: "CdpListPromotionCell", bundle: nil), forCellReuseIdentifier: "CdpListPromotionCell")
        self.myCdpTableView.register(UINib(nibName: "CdpLisyMyCell", bundle: nil), forCellReuseIdentifier: "CdpLisyMyCell")
        self.myCdpTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myCdpTableView.rowHeight = UITableView.automaticDimension
        self.myCdpTableView.estimatedRowHeight = UITableView.automaticDimension
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell:CdpListPromotionCell? = tableView.dequeueReusableCell(withIdentifier:"CdpListPromotionCell") as? CdpListPromotionCell
        let cell:CdpLisyMyCell? = tableView.dequeueReusableCell(withIdentifier:"CdpLisyMyCell") as? CdpLisyMyCell
        
        let url = KAVA_CDP_MARKET_IMG_URL + "xrpusd" + ".png"
        Alamofire.request(url, method: .get).responseImage { response  in
            guard let image = response.result.value else {
                return
            }
            cell?.marketImg.image = image
        }
        
        return cell!
    }
    
    

}
