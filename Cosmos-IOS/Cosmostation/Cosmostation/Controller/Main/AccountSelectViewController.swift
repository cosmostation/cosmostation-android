//
//  AccountSelectViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 21/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AccountSelectViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    var resultDelegate: AccountSelectDelegate?
    
    @IBOutlet weak var chainTableView: UITableView!
    @IBOutlet weak var accountTableView: UITableView!
    
    var mAccounts = Array<Account>()
    var mSelectedChain = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mSelectedChain = BaseData.instance.getRecentChain()
        
        self.accountTableView.delegate = self
        self.accountTableView.dataSource = self
        self.accountTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.accountTableView.register(UINib(nibName: "AccountPopupCell", bundle: nil), forCellReuseIdentifier: "AccountPopupCell")
        self.accountTableView.register(UINib(nibName: "ManageAccountAddCell", bundle: nil), forCellReuseIdentifier: "ManageAccountAddCell")
        
        self.chainTableView.delegate = self
        self.chainTableView.dataSource = self
        self.chainTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.chainTableView.register(UINib(nibName: "ManageChainCell", bundle: nil), forCellReuseIdentifier: "ManageChainCell")
        onRefechUserInfo()
        
        let dismissTap1 = UITapGestureRecognizer(target: self, action: #selector(tableTapped))
        let dismissTap2 = UITapGestureRecognizer(target: self, action: #selector(tableTapped))
        self.accountTableView.backgroundView = UIView()
        self.chainTableView.backgroundView = UIView()
        self.accountTableView.backgroundView?.addGestureRecognizer(dismissTap1)
        self.chainTableView.backgroundView?.addGestureRecognizer(dismissTap2)
        
    }
    
    
    @objc public func tableTapped() {
//        self.dismiss(animated: false, completion: nil)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.chainTableView.selectRow(at: IndexPath.init(item: mSelectedChain, section: 0), animated: false, scrollPosition: .middle)
    }

    @IBAction func OnClose(_ sender: UIButton) {
        self.dismiss(animated: false, completion: nil)
    }
    
    
    func onRefechUserInfo() {
        if (mSelectedChain == 0) {
            self.mAccounts = BaseData.instance.selectAllAccounts()
        } else {
            let selectedChain = ChainType.SUPPRT_CHAIN()[mSelectedChain - 1]
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(selectedChain)
        }
        
        self.mAccounts.sort{
            return $0.account_sort_order < $1.account_sort_order
        }
        self.accountTableView.reloadData()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (tableView == chainTableView) {
            return ChainType.SUPPRT_CHAIN().count + 1
            
        } else {
            if (mSelectedChain == 0) {
                return mAccounts.count
            } else {
                if (mAccounts.count < 5) {
                    return mAccounts.count + 1
                } else {
                    return mAccounts.count
                }
            }
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (tableView == chainTableView) {
            let cell:ManageChainCell? = tableView.dequeueReusableCell(withIdentifier:"ManageChainCell") as? ManageChainCell
            if (indexPath.row == 0) {
                cell?.chainImg.isHidden = true
                cell?.chainName.isHidden = true
                cell?.chainAll.isHidden = false
                
            } else {
                let selectedChain = ChainType.SUPPRT_CHAIN()[indexPath.row - 1]
                cell?.chainImg.isHidden = false
                cell?.chainName.isHidden = false
                cell?.chainAll.isHidden = true
                cell?.chainImg.image = WUtils.getChainImg(selectedChain)
                cell?.chainName.text = WUtils.getChainTitle2(selectedChain)
                
            }
            return cell!
            
        } else {
            if (mAccounts.count <= indexPath.row) {
                let cell:ManageAccountAddCell? = tableView.dequeueReusableCell(withIdentifier:"ManageAccountAddCell") as? ManageAccountAddCell
                return cell!
            }
            
            let account = mAccounts[indexPath.row]
            let cell:AccountPopupCell? = tableView.dequeueReusableCell(withIdentifier:"AccountPopupCell") as? AccountPopupCell
            let userChain = WUtils.getChainType(account.account_base_chain)
            
            if (account.account_has_private) {
                cell?.keyImg.image = cell?.keyImg.image!.withRenderingMode(.alwaysTemplate)
                cell?.keyImg.tintColor = WUtils.getChainColor(userChain)
            } else {
                cell?.keyImg.tintColor = COLOR_DARK_GRAY
            }
            cell?.nameLabel.text = WUtils.getWalletName(account)
            
            var address = account.account_address
            if (userChain == ChainType.OKEX_MAIN || userChain == ChainType.OKEX_TEST) {
                address = WKey.convertAddressOkexToEth(address)
            }
            cell?.address.text = address
            cell?.amount.attributedText = WUtils.displayAmount2(account.account_last_total, cell!.amount.font, 0, 6)
            WUtils.setDenomTitle(userChain, cell!.amountDenom)
            
            cell?.cardView.borderColor = UIColor.init(hexString: "#9ca2ac")
            if (self.account?.account_id == account.account_id) {
                cell?.cardView.borderWidth = 1.0
            } else {
                cell?.cardView.borderWidth = 0.0
            }
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (tableView == chainTableView) {
            if (mSelectedChain != indexPath.row) {
                mSelectedChain = indexPath.row
                BaseData.instance.setRecentChain(mSelectedChain)
                self.onRefechUserInfo()
            }
        } else if (mAccounts.count <= indexPath.row) {
            let toAddChain:ChainType = ChainType.SUPPRT_CHAIN()[mSelectedChain - 1]
            self.resultDelegate?.addAccount(toAddChain)
            self.dismiss(animated: false, completion: nil)
            
        } else {
            self.resultDelegate?.accountSelected(Int(mAccounts[indexPath.row].account_id))
            self.dismiss(animated: false, completion: nil)
        }
    }
}

protocol AccountSelectDelegate{
    func accountSelected (_ id:Int)
    func addAccount(_ chain:ChainType)
}
