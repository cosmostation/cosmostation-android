//
//  ReplaceResource0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/29.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class ReplaceResource0ViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, ReplaceSelectChainDelegate, ReplaceEditAddressDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var replaceResourceTableView: UITableView!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.onInitData()
        
        self.replaceResourceTableView.delegate = self
        self.replaceResourceTableView.dataSource = self
        self.replaceResourceTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.replaceResourceTableView.register(UINib(nibName: "ResourceEditCell", bundle: nil), forCellReuseIdentifier: "ResourceEditCell")
        self.replaceResourceTableView.register(UINib(nibName: "ResourceAddCell", bundle: nil), forCellReuseIdentifier: "ResourceAddCell")
        
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func onInitData() {
//        print("pageHolderVC.mStarnameResources_gRPC ", pageHolderVC.mStarnameResources_gRPC.count)
        if (pageHolderVC.mStarnameResources_gRPC.count == 0) {
            let initResource = Starnamed_X_Starname_V1beta1_Resource.with { $0.uri = STARNAME; $0.resource = self.account!.account_address }
            pageHolderVC.mStarnameResources_gRPC.append(initResource)
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (pageHolderVC.mStarnameResources_gRPC.count >= 14) {
            return pageHolderVC.mStarnameResources_gRPC.count
        } else {
            return pageHolderVC.mStarnameResources_gRPC.count + 1
            
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (pageHolderVC.mStarnameResources_gRPC.count >= 14) {
            return onBindResource(tableView, indexPath.row)
            
        } else {
            if (indexPath.row == pageHolderVC.mStarnameResources_gRPC.count) {
                let cell:ResourceAddCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceAddCell") as? ResourceAddCell
                return cell!
            } else {
                return onBindResource(tableView, indexPath.row)
            }
        }
    }
    
    func onBindResource(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:ResourceEditCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceEditCell") as? ResourceEditCell
        let resource = pageHolderVC.mStarnameResources_gRPC[position]
        cell?.chainImg.af_setImage(withURL: getStarNameChainImgUrl(resource.uri))
        cell?.chainName.text = getStarNameChainName(resource.uri)
        cell?.chainAddress.text = resource.resource
        if (pageHolderVC.mStarnameResources_gRPC.count == 1) {
            cell?.btnRemove.isHidden = true
        }
        cell?.actionRemove = {
            self.pageHolderVC.mStarnameResources_gRPC.remove(at: position)
            self.replaceResourceTableView.reloadData()
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (pageHolderVC.mStarnameResources_gRPC.count < 14 && indexPath.row == pageHolderVC.mStarnameResources_gRPC.count) {
            let replaceSelectVC = ReplaceSelectChainViewController(nibName: "ReplaceSelectChainViewController", bundle: nil)
            replaceSelectVC.modalPresentationStyle = .popover
            replaceSelectVC.resultDelegate = self
            replaceSelectVC.starnameResources_gRPC = pageHolderVC.mStarnameResources_gRPC
            present(replaceSelectVC, animated: true, completion: nil)
            
        } else {
            let resource = pageHolderVC.mStarnameResources_gRPC[indexPath.row]
            let replaceEditVC = ReplaceEditAddressViewController(nibName: "ReplaceEditAddressViewController", bundle: nil)
            replaceEditVC.modalPresentationStyle = .popover
            replaceEditVC.chainNameResource = resource.uri
            replaceEditVC.addressResource = resource.resource
            replaceEditVC.resultDelegate = self
            present(replaceEditVC, animated: true, completion: nil)
        }
    }
    
    func chainSelectedCallback(_ chain: String) {
//        print("chainSelected ", chain)
        let replaceEditVC = ReplaceEditAddressViewController(nibName: "ReplaceEditAddressViewController", bundle: nil)
        replaceEditVC.modalPresentationStyle = .popover
        replaceEditVC.chainNameResource = chain
        replaceEditVC.resultDelegate = self
        present(replaceEditVC, animated: true, completion: nil)
    }
    
    func addressEditedCallback(_ chain: String, _ address: String) {
//        print("addressEdited ", chain, "  ", address)
        var already = -1
        for i in 0..<self.pageHolderVC.mStarnameResources_gRPC.count {
            if (self.pageHolderVC.mStarnameResources_gRPC[i].uri == chain) {
                already = i;
                break
            }
        }
        
        let newResource = Starnamed_X_Starname_V1beta1_Resource.with { $0.uri = chain; $0.resource = address }
        if (already >= 0) {
            self.pageHolderVC.mStarnameResources_gRPC[already] = newResource
        } else {
            self.pageHolderVC.mStarnameResources_gRPC.append(newResource)
        }
        self.replaceResourceTableView.reloadData()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (self.pageHolderVC.mStarnameResources_gRPC.count <= 0) {
            self.onShowToast(NSLocalizedString("error_no_address_added", comment: ""))
            return
        } else {
            self.btnCancel.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
}
