//
//  RegisterAccount1ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/30.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class RegisterAccount1ViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, ReplaceSelectChainDelegate, ReplaceEditAddressDelegate {
    
    @IBOutlet weak var btnBack: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    @IBOutlet weak var resigter1Tableview: UITableView!
    
    var pageHolderVC: StepGenTxViewController!
    var mStarnameResources: Array<StarNameResource> = Array<StarNameResource>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.onInitData()
        
        self.resigter1Tableview.delegate = self
        self.resigter1Tableview.dataSource = self
        self.resigter1Tableview.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.resigter1Tableview.register(UINib(nibName: "ResourceEditCell", bundle: nil), forCellReuseIdentifier: "ResourceEditCell")
        self.resigter1Tableview.register(UINib(nibName: "ResourceAddCell", bundle: nil), forCellReuseIdentifier: "ResourceAddCell")
    }
    
    override func enableUserInteraction() {
        self.btnBack.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func onInitData() {
        let initResource = StarNameResource.init(STARNAME, self.account!.account_address)
        mStarnameResources.append(initResource)
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mStarnameResources.count >= 14) {
            return mStarnameResources.count
        }
        return mStarnameResources.count + 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (mStarnameResources.count >= 14) {
            return onBindResource(tableView, indexPath.row)
            
        } else {
            if (indexPath.row == mStarnameResources.count) {
                let cell:ResourceAddCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceAddCell") as? ResourceAddCell
                return cell!
            } else {
                return onBindResource(tableView, indexPath.row)
            }
        }
    }
    
    func onBindResource(_ tableView: UITableView,  _ position:Int) -> UITableViewCell  {
        let cell:ResourceEditCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceEditCell") as? ResourceEditCell
        let resource = self.mStarnameResources[position]
        cell?.chainImg.image = WUtils.getStarNameChainImg(resource)
        cell?.chainName.text = WUtils.getStarNameChainName(resource)
        cell?.chainAddress.text = resource.resource
        if (self.mStarnameResources.count == 1) {
            cell?.btnRemove.isHidden = true
        }
        cell?.actionRemove = {
            self.mStarnameResources.remove(at: position)
            self.resigter1Tableview.reloadData()
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (self.mStarnameResources.count < 14 && indexPath.row == self.mStarnameResources.count) {
            let replaceSelectVC = ReplaceSelectChainViewController(nibName: "ReplaceSelectChainViewController", bundle: nil)
            replaceSelectVC.modalPresentationStyle = .popover
            replaceSelectVC.resultDelegate = self
            replaceSelectVC.starnameResources = self.mStarnameResources
            present(replaceSelectVC, animated: true, completion: nil)
            
        } else {
            let resource = self.mStarnameResources[indexPath.row]
            let replaceEditVC = ReplaceEditAddressViewController(nibName: "ReplaceEditAddressViewController", bundle: nil)
            replaceEditVC.modalPresentationStyle = .popover
            replaceEditVC.chainNameResource = resource.uri
            replaceEditVC.addressResource = resource.resource
            replaceEditVC.resultDelegate = self
            present(replaceEditVC, animated: true, completion: nil)
        }
    }
    
    func chainSelectedCallback(_ chain: String) {
        let replaceEditVC = ReplaceEditAddressViewController(nibName: "ReplaceEditAddressViewController", bundle: nil)
        replaceEditVC.modalPresentationStyle = .popover
        replaceEditVC.chainNameResource = chain
        replaceEditVC.resultDelegate = self
        present(replaceEditVC, animated: true, completion: nil)
    }
    
    func addressEditedCallback(_ chain: String, _ address: String) {
        var already = -1
        for i in 0..<self.mStarnameResources.count {
            if (self.mStarnameResources[i].uri == chain) {
                already = i;
                break
            }
        }
        
        let newResource = StarNameResource.init(chain, address)
        if (already >= 0) {
            self.mStarnameResources[already] = newResource
        } else {
            self.mStarnameResources.append(newResource)
        }
        self.resigter1Tableview.reloadData()
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBack.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (self.mStarnameResources.count <= 0) {
            self.onShowToast(NSLocalizedString("error_no_address_added", comment: ""))
            return
        } else {
            self.btnBack.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            pageHolderVC.mStarnameResources = self.mStarnameResources
            pageHolderVC.onNextPage()
        }
    }
}
