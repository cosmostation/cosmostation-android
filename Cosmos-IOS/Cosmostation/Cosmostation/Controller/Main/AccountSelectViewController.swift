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
        self.accountTableView.delegate = self
        self.accountTableView.dataSource = self
        self.accountTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.accountTableView.register(UINib(nibName: "AccountPopupCell", bundle: nil), forCellReuseIdentifier: "AccountPopupCell")
        self.accountTableView.register(UINib(nibName: "ManageAccountAddCell", bundle: nil), forCellReuseIdentifier: "ManageAccountAddCell")
        
        self.chainTableView.delegate = self
        self.chainTableView.dataSource = self
        self.chainTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.chainTableView.register(UINib(nibName: "ManageChainCell", bundle: nil), forCellReuseIdentifier: "ManageChainCell")
        self.chainTableView.selectRow(at: IndexPath.init(item: 0, section: 0), animated: false, scrollPosition: .top)
        
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
    }

    @IBAction func OnClose(_ sender: UIButton) {
        self.dismiss(animated: false, completion: nil)
    }
    
    
    func onRefechUserInfo() {
        if (mSelectedChain == 0) {
            self.mAccounts = BaseData.instance.selectAllAccounts()
        } else if (mSelectedChain == 1) {
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(ChainType.SUPPORT_CHAIN_COSMOS_MAIN)
        } else if (mSelectedChain == 2) {
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(ChainType.SUPPORT_CHAIN_IRIS_MAIN)
        } else if (mSelectedChain == 3) {
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(ChainType.SUPPORT_CHAIN_BINANCE_MAIN)
        } else if (mSelectedChain == 4) {
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(ChainType.SUPPORT_CHAIN_KAVA_MAIN)
        } else if (mSelectedChain == 5) {
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(ChainType.SUPPORT_CHAIN_IOV_MAIN)
        }
        self.mAccounts.sort{
            return $0.account_sort_order < $1.account_sort_order
        }
        self.accountTableView.reloadData()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (tableView == chainTableView) {
            return 6
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
                
            } else if (indexPath.row == 1) {
                cell?.chainImg.isHidden = false
                cell?.chainName.isHidden = false
                cell?.chainAll.isHidden = true
                cell?.chainImg.image = UIImage(named: "cosmosWhMain")
                cell?.chainName.text = "COSMOS"
                
            } else if (indexPath.row == 2) {
                cell?.chainImg.isHidden = false
                cell?.chainName.isHidden = false
                cell?.chainAll.isHidden = true
                cell?.chainImg.image = UIImage(named: "irisWh")
                cell?.chainName.text = "IRIS"
                
            } else if (indexPath.row == 3) {
                cell?.chainImg.isHidden = false
                cell?.chainName.isHidden = false
                cell?.chainAll.isHidden = true
                cell?.chainImg.image = UIImage(named: "binanceChImg")
                cell?.chainName.text = "BINANCE"
                
            } else if (indexPath.row == 4) {
                cell?.chainImg.isHidden = false
                cell?.chainName.isHidden = false
                cell?.chainAll.isHidden = true
                cell?.chainImg.image = UIImage(named: "kavaImg")
                cell?.chainName.text = "KAVA"
                
            } else if (indexPath.row == 5) {
                cell?.chainImg.isHidden = false
                cell?.chainName.isHidden = false
                cell?.chainAll.isHidden = true
                cell?.chainImg.image = UIImage(named: "iovImg")
                cell?.chainName.text = "IOV"

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
            if (account.account_nick_name == "") {
                cell?.nameLabel.text = NSLocalizedString("wallet_dash", comment: "") + String(account.account_id)
            } else {
                cell?.nameLabel.text = account.account_nick_name
            }
            cell?.address.text = account.account_address
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
                self.onRefechUserInfo()
            }
        } else if (mAccounts.count <= indexPath.row) {
            var addChain:ChainType?
            if (mSelectedChain == 1) {
                addChain = ChainType.SUPPORT_CHAIN_COSMOS_MAIN
            } else if (mSelectedChain == 2) {
                addChain = ChainType.SUPPORT_CHAIN_IRIS_MAIN
            } else if (mSelectedChain == 3) {
                addChain = ChainType.SUPPORT_CHAIN_BINANCE_MAIN
            } else if (mSelectedChain == 4) {
                addChain = ChainType.SUPPORT_CHAIN_KAVA_MAIN
            } else if (mSelectedChain == 5) {
                addChain = ChainType.SUPPORT_CHAIN_IOV_MAIN
            }
            self.resultDelegate?.addAccount(addChain!)
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
