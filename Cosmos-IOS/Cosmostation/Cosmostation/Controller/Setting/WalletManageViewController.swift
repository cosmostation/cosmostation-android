//
//  WalletManageViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 03/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class WalletManageViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, UITableViewDragDelegate, UITableViewDropDelegate, SBCardPopupDelegate {
    
    @IBOutlet weak var chainTableView: UITableView!
    @IBOutlet weak var accountTableView: UITableView!
    
    var mAccounts = Array<Account>()
    var mSelectedChain = 0;
    var isEditMode = false;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.accountTableView.delegate = self
        self.accountTableView.dataSource = self
        self.accountTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.accountTableView.register(UINib(nibName: "ManageAccountCell", bundle: nil), forCellReuseIdentifier: "ManageAccountCell")
        self.accountTableView.register(UINib(nibName: "ManageAccountAddCell", bundle: nil), forCellReuseIdentifier: "ManageAccountAddCell")
        self.accountTableView.dragDelegate = self
        self.accountTableView.dropDelegate = self
        
        self.chainTableView.delegate = self
        self.chainTableView.dataSource = self
        self.chainTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.chainTableView.register(UINib(nibName: "ManageChainCell", bundle: nil), forCellReuseIdentifier: "ManageChainCell")
        self.chainTableView.selectRow(at: IndexPath.init(item: 0, section: 0), animated: false, scrollPosition: .top)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_wallet_manage", comment: "");
        self.navigationItem.title = NSLocalizedString("title_wallet_manage", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        self.onRefechUserInfo()
    }
    
    func updateOptionBtn() {
        if (mSelectedChain == 0 && isEditMode) {
            let rightBarButtonItem = UIBarButtonItem.init(image: UIImage(named: "finishBtn"), style: .done, target: self, action: #selector(onStopEdit))
            self.navigationItem.rightBarButtonItem = rightBarButtonItem
        } else if (mSelectedChain == 0 && !isEditMode) {
            let rightBarButtonItem = UIBarButtonItem.init(image: UIImage(named: "sortingBtn"), style: .done, target: self, action: #selector(onStartEdit))
            self.navigationItem.rightBarButtonItem = rightBarButtonItem
        } else {
            self.navigationItem.rightBarButtonItem = nil
        }
    }
    
    @objc public func onStartEdit() {
        self.isEditMode = true
        self.accountTableView.reloadData()
        self.updateOptionBtn()
        self.accountTableView.dragInteractionEnabled = true
    }
    
    @objc public func onStopEdit() {
        self.isEditMode = false
        self.accountTableView.reloadData()
        self.updateOptionBtn()
        self.accountTableView.dragInteractionEnabled = false
    }
    
    func onRefechUserInfo() {
        if (mSelectedChain == 0) {
            self.mAccounts = BaseData.instance.selectAllAccounts()
        } else {
            let selectedChain = ChainType.SUPPRT_CHAIN()[mSelectedChain - 1]
            self.mAccounts = BaseData.instance.selectAllAccountsByChain(selectedChain)
        }
        self.sortWallet()
        self.accountTableView.reloadData()
        self.updateOptionBtn()
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
                if (selectedChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    cell?.chainImg.isHidden = false
                    cell?.chainName.isHidden = false
                    cell?.chainAll.isHidden = true
                    cell?.chainImg.image = UIImage(named: "cosmosWhMain")
                    cell?.chainName.text = "COSMOS"
                    
                } else if (selectedChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    cell?.chainImg.isHidden = false
                    cell?.chainName.isHidden = false
                    cell?.chainAll.isHidden = true
                    cell?.chainImg.image = UIImage(named: "irisWh")
                    cell?.chainName.text = "IRIS"
                    
                } else if (selectedChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                    cell?.chainImg.isHidden = false
                    cell?.chainName.isHidden = false
                    cell?.chainAll.isHidden = true
                    cell?.chainImg.image = UIImage(named: "binanceChImg")
                    cell?.chainName.text = "BINANCE"
                    
                } else if (selectedChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    cell?.chainImg.isHidden = false
                    cell?.chainName.isHidden = false
                    cell?.chainAll.isHidden = true
                    cell?.chainImg.image = UIImage(named: "kavaImg")
                    cell?.chainName.text = "KAVA"
                    
                } else if (selectedChain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
                    cell?.chainImg.isHidden = false
                    cell?.chainName.isHidden = false
                    cell?.chainAll.isHidden = true
                    cell?.chainImg.image = UIImage(named: "iovImg")
                    cell?.chainName.text = "IOV"
                    
                } else if (selectedChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    cell?.chainImg.isHidden = false
                    cell?.chainName.isHidden = false
                    cell?.chainAll.isHidden = true
                    cell?.chainImg.image = UIImage(named: "kavaTestImg")
                    cell?.chainName.text = "KAVA TEST"
                }
            }
            return cell!
            
        } else {
            if (mAccounts.count <= indexPath.row) {
                let cell:ManageAccountAddCell? = tableView.dequeueReusableCell(withIdentifier:"ManageAccountAddCell") as? ManageAccountAddCell
                return cell!
            }
            
            let account = mAccounts[indexPath.row]
            let cell:ManageAccountCell? = tableView.dequeueReusableCell(withIdentifier:"ManageAccountCell") as? ManageAccountCell
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
            if (isEditMode) {
                cell?.arrowImg.image = UIImage(named: "changeIc")
                cell?.arrowConstraint.constant = 10
                cell?.arrowConstraint2.constant = 10
                cell?.arrowConstraint3.constant = 10
            } else {
                cell?.arrowImg.image = UIImage(named: "arrowNextGr")
                cell?.arrowConstraint.constant = 4
                cell?.arrowConstraint2.constant = 4
                cell?.arrowConstraint3.constant = 4
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
                self.isEditMode = false
                self.onRefechUserInfo()
            }
        } else if (!isEditMode) {
            if (mAccounts.count <= indexPath.row) {
                let popupContent = AddViewController.create()
                let cardPopup = SBCardPopupViewController(contentViewController: popupContent)
                cardPopup.resultDelegate = self
                cardPopup.show(onViewController: self)
                
            } else {
                let walletDetailVC = WalletDetailViewController(nibName: "WalletDetailViewController", bundle: nil)
                walletDetailVC.hidesBottomBarWhenPushed = true
                walletDetailVC.accountId = self.mAccounts[indexPath.row].account_id
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(walletDetailVC, animated: true)
            }
        }
    }
    
    func tableView(_ tableView: UITableView, dragPreviewParametersForRowAt indexPath: IndexPath) -> UIDragPreviewParameters? {
        let param = UIDragPreviewParameters()
        param.backgroundColor = .clear
        return param
    }
    
    func tableView(_ tableView: UITableView, itemsForBeginning session: UIDragSession, at indexPath: IndexPath) -> [UIDragItem] {
        let itemProvider = NSItemProvider(object: self.mAccounts[indexPath.row])
        let dragItem = UIDragItem(itemProvider: itemProvider)
        return [dragItem]
    }
    
    func tableView(_ tableView: UITableView, performDropWith coordinator: UITableViewDropCoordinator) {
        guard let destinationIndexPath = coordinator.destinationIndexPath,
            let sourceIndexPath = coordinator.items[0].sourceIndexPath else{
            return
        }
        let sourceItem = self.mAccounts[sourceIndexPath.row]
        self.mAccounts.remove(at: sourceIndexPath.row)
        self.mAccounts.insert(sourceItem, at: destinationIndexPath.row)
        DispatchQueue.main.async {
            for i in 0 ... (self.mAccounts.count - 1) {
                self.mAccounts[i].account_sort_order = Int64(i)
            }
            BaseData.instance.updateSortOrder(self.mAccounts)
            self.accountTableView.reloadData()
        }
    }
    
    func tableView(_ tableView: UITableView, canHandle session: UIDropSession) -> Bool {
        return session.canLoadObjects(ofClass: Account.self)
    }

    func tableView(_ tableView: UITableView, dropSessionDidUpdate session: UIDropSession, withDestinationIndexPath destinationIndexPath: IndexPath?) -> UITableViewDropProposal {
        return UITableViewDropProposal(operation: .move, intent: .insertAtDestinationIndexPath)
    }
    
    func sortWallet() {
        self.mAccounts.sort{
            return $0.account_sort_order < $1.account_sort_order
        }
    }
    
    func SBCardPopupResponse(result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(490), execute: {
            var tagetVC:BaseViewController?
            if(result == 1) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "CreateViewController") as! CreateViewController
                tagetVC?.chainType = ChainType.SUPPRT_CHAIN()[self.mSelectedChain - 1]
                
            } else if(result == 2) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestoreViewController") as! RestoreViewController
                tagetVC?.chainType = ChainType.SUPPRT_CHAIN()[self.mSelectedChain - 1]
                
            } else if(result == 3) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "AddAddressViewController") as! AddAddressViewController
                
            }
            if(tagetVC != nil) {
                tagetVC?.hidesBottomBarWhenPushed = true
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(tagetVC!, animated: true)
            }
        })
    }
}
