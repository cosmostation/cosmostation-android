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
    var starnameResources: Array<StarNameResource> = Array<StarNameResource>()
    var allStarnameResources: Array<StarNameResource> = Array<StarNameResource>()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.allStarnameResources = WUtils.getStarNameAllResources()
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
        cell?.chainImg.image = WUtils.getStarNameChainImg(resource)
        cell?.chainName.text = WUtils.getStarNameChainName(resource)
        if starnameResources.filter({ $0.uri == resource.uri}).first != nil {
            cell?.cardRoot.backgroundColor = UIColor.init(hexString: "f5f5f5")
        } else {
            cell?.cardRoot.backgroundColor = .white
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let resource = allStarnameResources[indexPath.row]
        if starnameResources.filter({ $0.uri == resource.uri}).first != nil {
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
