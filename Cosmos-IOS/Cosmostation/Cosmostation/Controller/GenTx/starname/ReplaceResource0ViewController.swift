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
    var mStarnameResources: Array<StarNameResource> = Array<StarNameResource>()
    
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
        print("pageHolderVC.mStarnameResources ", pageHolderVC.mStarnameResources.count)
        if (pageHolderVC.mStarnameResources.count == 0) {
            let initResource = StarNameResource.init(STARNAME, self.account!.account_address)
            pageHolderVC.mStarnameResources.append(initResource)
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (pageHolderVC.mStarnameResources.count >= 14) {
            return pageHolderVC.mStarnameResources.count
        } else {
            return pageHolderVC.mStarnameResources.count + 1
            
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (pageHolderVC.mStarnameResources.count >= 14) {
            return onBindResource(tableView, indexPath.row)
            
        } else {
            if (indexPath.row == pageHolderVC.mStarnameResources.count) {
                let cell:ResourceAddCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceAddCell") as? ResourceAddCell
                return cell!
            } else {
                return onBindResource(tableView, indexPath.row)
            }
        }
    }
    
    func onBindResource(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:ResourceEditCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceEditCell") as? ResourceEditCell
        let resource = pageHolderVC.mStarnameResources[position]
        cell?.chainImg.image = WUtils.getStarNameChainImg(resource)
        cell?.chainName.text = WUtils.getStarNameChainName(resource)
        cell?.chainAddress.text = resource.resource
        if (pageHolderVC.mStarnameResources.count == 1) {
            cell?.btnRemove.isHidden = true
        }
        cell?.actionRemove = {
            self.pageHolderVC.mStarnameResources.remove(at: position)
            self.replaceResourceTableView.reloadData()
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (pageHolderVC.mStarnameResources.count < 14 && indexPath.row == pageHolderVC.mStarnameResources.count) {
            let replaceSelectVC = ReplaceSelectChainViewController(nibName: "ReplaceSelectChainViewController", bundle: nil)
            replaceSelectVC.modalPresentationStyle = .popover
            replaceSelectVC.resultDelegate = self
            replaceSelectVC.starnameResources = pageHolderVC.mStarnameResources
            present(replaceSelectVC, animated: true, completion: nil)
            
        } else {
            let resource = pageHolderVC.mStarnameResources[indexPath.row]
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
        for i in 0..<self.pageHolderVC.mStarnameResources.count {
            if (self.pageHolderVC.mStarnameResources[i].uri == chain) {
                already = i;
                break
            }
        }
        
        let newResource = StarNameResource.init(chain, address)
        if (already >= 0) {
            self.pageHolderVC.mStarnameResources[already] = newResource
        } else {
            self.pageHolderVC.mStarnameResources.append(newResource)
        }
        self.replaceResourceTableView.reloadData()
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (self.pageHolderVC.mStarnameResources.count <= 0) {
            self.onShowToast(NSLocalizedString("error_no_address_added", comment: ""))
            return
        } else {
            self.btnCancel.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
        }
    }
}
