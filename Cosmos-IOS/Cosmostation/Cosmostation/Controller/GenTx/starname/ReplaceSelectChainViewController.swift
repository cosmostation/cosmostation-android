//
//  ReplaceSelectChainViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ReplaceSelectChainViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var selectChainTableView: UITableView!
    
    var resultDelegate: ReplaceSelectChainDelegate?
    var starnameResources_gRPC: Array<Starnamed_X_Starname_V1beta1_Resource> = Array<Starnamed_X_Starname_V1beta1_Resource>()
    var allStarnameResources = Array<StarnameAsset>()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.allStarnameResources = getStarnameAssets()
        self.selectChainTableView.delegate = self
        self.selectChainTableView.dataSource = self
        self.selectChainTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.selectChainTableView.register(UINib(nibName: "ResourceChainCell", bundle: nil), forCellReuseIdentifier: "ResourceChainCell")
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return allStarnameResources.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ResourceChainCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceChainCell") as? ResourceChainCell
        let resource = allStarnameResources[indexPath.row]
        cell?.chainImg.af_setImage(withURL: getStarNameChainImgUrl(resource.uri))
        cell?.chainName.text = getStarNameChainName(resource.uri)
        if starnameResources_gRPC.filter({ $0.uri == resource.uri}).first != nil {
            cell?.cardRoot.backgroundColor = UIColor.init(hexString: "f5f5f5")
        } else {
            cell?.cardRoot.backgroundColor = .white
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let resource = allStarnameResources[indexPath.row]
        if starnameResources_gRPC.filter({ $0.uri == resource.uri}).first != nil {
            self.onShowToast(NSLocalizedString("error_already_address_added_chain", comment: ""))
            return
        
        } else {
            self.dismiss(animated: true, completion: {
                self.resultDelegate?.chainSelectedCallback(resource.uri)
            })
        }
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
}

protocol ReplaceSelectChainDelegate{
    func chainSelectedCallback (_ chain: String)
}
