//
//  AccountDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class AccountDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    @IBOutlet weak var myAccountNameLabel: UILabel!
    @IBOutlet weak var myAccountAddressCntLabel: UILabel!
    @IBOutlet weak var myAccountExpireTimeLabel: UILabel!
    @IBOutlet weak var myAccountResourceTableView: UITableView!
    @IBOutlet weak var myAccountEmptyView: UIView!
    
    var mMyDomain: String?
    var mMyAccount: String?
    var mMyDomainInfo: IovStarNameDomainInfo?
    var mMyAccountResolve: IovStarNameResolve?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.balances = account!.account_balances
        
        self.myAccountResourceTableView.delegate = self
        self.myAccountResourceTableView.dataSource = self
        self.myAccountResourceTableView.register(UINib(nibName: "ResourceCell", bundle: nil), forCellReuseIdentifier: "ResourceCell")
        self.myAccountResourceTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myAccountResourceTableView.rowHeight = UITableView.automaticDimension
        self.myAccountResourceTableView.estimatedRowHeight = UITableView.automaticDimension
        self.myAccountEmptyView.isHidden = true
        
        myAccountNameLabel.text = mMyAccount! + "*" + mMyDomain!
        self.onFetchData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_starname_account_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_starname_account_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mMyAccountResolve != nil && mMyAccountResolve?.result.account.resources != nil) {
            return mMyAccountResolve!.result.account.resources.count
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ResourceCell? = tableView.dequeueReusableCell(withIdentifier:"ResourceCell") as? ResourceCell
        let resource = mMyAccountResolve?.result.account.resources[indexPath.row]
        cell?.chainImg.image = WUtils.getStarNameChainImg(resource!)
        cell?.chainName.text = WUtils.getStarNameChainName(resource!)
        cell?.chainAddress.text = resource?.resource
        return cell!
    }
    
    
    @IBAction func onClickDelete(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let needFee = NSDecimalNumber.init(string: "150000")
        if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(needFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(needFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else {
            self.onShowToast(NSLocalizedString("error_disable", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = IOV_MSG_TYPE_DELETE_ACCOUNT
        txVC.mStarnameDomain = mMyDomain
        txVC.mStarnameAccount = mMyAccount
        txVC.mStarnameTime = mMyAccountResolve?.result.account.valid_until
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickRenew(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        if (mMyDomainInfo?.result.domain?.type != "open") {
            self.onShowToast(NSLocalizedString("error_can_not_extend_close_domain", comment: ""))
            return
        }
        
        let needFee = BaseData.instance.mStarNameFee!.getAccountRenewFee(mMyDomainInfo!.result.domain!.type).adding(NSDecimalNumber.init(string: "300000"))
        if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(needFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(needFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else {
            self.onShowToast(NSLocalizedString("error_disable", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = IOV_MSG_TYPE_RENEW_ACCOUNT
        txVC.mStarnameDomain = mMyDomain
        txVC.mStarnameAccount = mMyAccount
        txVC.mStarnameTime = mMyAccountResolve?.result.account.valid_until
        txVC.mStarnameDomainType = mMyDomainInfo?.result.domain?.type
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    @IBAction func onClickReplace(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let needFee = BaseData.instance.mStarNameFee!.getReplaceFee().adding(NSDecimalNumber.init(string: "300000"))
        if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(needFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(needFee).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_starname_fee", comment: ""))
                return
            }
        } else {
            self.onShowToast(NSLocalizedString("error_disable", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE
        txVC.mStarnameDomain = mMyDomain
        txVC.mStarnameAccount = mMyAccount
        txVC.mStarnameTime = mMyAccountResolve?.result.account.valid_until
        txVC.mStarnameDomainType = mMyDomainInfo?.result.domain?.type
        txVC.mStarnameResources = mMyAccountResolve!.result.account.resources
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickProfile(_ sender: UIButton) {
        guard let url = URL(string: "https://starname.me/" + mMyAccount! + "*" + mMyDomain!) else { return }
        self.onShowSafariWeb(url)
    }
    
    var mFetchCnt = 0
    @objc func onFetchData() {
        if (self.mFetchCnt > 0)  {
            return
        }
        self.mFetchCnt = 2
        self.onFetchDomainInfo(mMyDomain!)
        self.onFetchResolve(mMyAccount!, mMyDomain!)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if (mMyAccountResolve != nil && mMyAccountResolve?.result.account.resources != nil &&  mMyAccountResolve!.result.account.resources.count > 0) {
                self.myAccountResourceTableView.reloadData()
                self.myAccountEmptyView.isHidden = true
                self.myAccountAddressCntLabel.text = String(mMyAccountResolve!.result.account.resources.count)
            } else {
                self.myAccountResourceTableView.isHidden = true
                self.myAccountEmptyView.isHidden = false
                self.myAccountAddressCntLabel.text = "0"
            }
            myAccountExpireTimeLabel.text = WUtils.longTimetoString(input: mMyAccountResolve!.result.account.valid_until * 1000)
        }
    }
    
    
    func onFetchDomainInfo(_ domain: String) {
        let request = Alamofire.request(BaseNetWork.domainInfoStarnameUrl(chainType), method: .post, parameters: ["name" : domain], encoding: JSONEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                self.mMyDomainInfo = IovStarNameDomainInfo.init(info)
                self.onFetchFinished()
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchDomainInfo ", error) }
                self.onFetchFinished()
            }
        }
    }
    
    
    func onFetchResolve(_ account: String, _ doamin: String) {
        let request = Alamofire.request(BaseNetWork.resolveStarnameUrl(chainType), method: .post, parameters: ["starname" : account + "*" + doamin], encoding: JSONEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any], info["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                self.mMyAccountResolve = IovStarNameResolve.init(info)
                self.onFetchFinished()
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchResolve ", error) }
                self.onFetchFinished()
            }
        }
    }
}
