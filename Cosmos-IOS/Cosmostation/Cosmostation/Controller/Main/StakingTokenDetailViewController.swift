//
//  StakingTokenDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class StakingTokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var keyState: UIImageView!
    @IBOutlet weak var dpAddress: UILabel!
    @IBOutlet weak var tokenDetailTableView: UITableView!
    
    var hasVesting = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderCosmosCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderCosmosCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderIrisCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderIrisCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailAkashCell", bundle: nil), forCellReuseIdentifier: "TokenDetailAkashCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailPersistenceCell", bundle: nil), forCellReuseIdentifier: "TokenDetailPersistenceCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHeaderKavaCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHeaderKavaCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailVestingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailVestingDetailCell")
        self.tokenDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        self.tokenDetailTableView.rowHeight = UITableView.automaticDimension
        self.tokenDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        dpAddress.text = account?.account_address
        dpAddress.adjustsFontSizeToFitWidth = true
        if (account?.account_has_private == true) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            keyState.tintColor = WUtils.getChainColor(chainType)
        }
        
        if (WUtils.isGRPC(chainType!)) {
            if (BaseData.instance.onParseRemainVestingsByDenom(WUtils.getMainDenom(chainType)).count > 0) { hasVesting = true }
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(KAVA_MAIN_DENOM) > 0) { hasVesting = true }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationItem.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }

    
    @IBAction func onClickWebLink(_ sender: UIButton) {
        let link = WUtils.getAccountExplorer(chainType!, account!.account_address)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    @IBAction func onClickShare(_ sender: UIButton) {
        var nickName:String?
        if (account!.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account!.account_nick_name
        }
        self.shareAddress(account!.account_address, nickName!)
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
        if (account!.account_has_private == false) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        if (WUtils.isGRPC(chainType!)) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.getAvailableAmount(WUtils.getMainDenom(chainType)).compare(feeAmount).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
            txVC.mToSendDenom = WUtils.getMainDenom(chainType)
            txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
            
        } else {
            return
            
        }
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (hasVesting == true) {
            return 2
        }
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            if (chainType == ChainType.COSMOS_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderCosmosCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.IRIS_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderIrisCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.AKASH_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailAkashCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.PERSIS_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailPersistenceCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
            }
            
            else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHeaderKavaCell") as? TokenDetailHeaderKavaCell
                cell?.onBindTokens(account!)
                return cell!
                
            }
            
        } else if (indexPath.row == 1 && hasVesting) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailVestingDetailCell") as? TokenDetailVestingDetailCell
            cell?.onBindVesting(chainType!, WUtils.getMainDenom(chainType))
            return cell!
        }
        
        let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        return cell!
    }
}
