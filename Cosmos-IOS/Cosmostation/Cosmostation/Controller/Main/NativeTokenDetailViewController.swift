//
//  NativeTokenDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class NativeTokenDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var keyState: UIImageView!
    @IBOutlet weak var dpAddress: UILabel!
    @IBOutlet weak var tokenDetailTableView: UITableView!
    
    var denom: String?
    var hasVesting = false

    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailNativeCell", bundle: nil), forCellReuseIdentifier: "TokenDetailNativeCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailHardCell", bundle: nil), forCellReuseIdentifier: "TokenDetailHardCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailVestingDetailCell", bundle: nil), forCellReuseIdentifier: "TokenDetailVestingDetailCell")
        self.tokenDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        self.tokenDetailTableView.rowHeight = UITableView.automaticDimension
        self.tokenDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        dpAddress.text = address
        dpAddress.adjustsFontSizeToFitWidth = true
        
        if (account?.account_has_private == true) {
            keyState.image = keyState.image?.withRenderingMode(.alwaysTemplate)
            keyState.tintColor = WUtils.getChainColor(chainType)
        }
        
        if (WUtils.isGRPC(chainType!)) {
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(denom!) > 0) { hasVesting = true }
        }
        
//        print("mBnbTokenList ", BaseData.instance.mBnbTokenList.count)
//        print("mBnbTokenTicker ", BaseData.instance.mBnbTokenTicker.count)
//        print("KAVA_HARD_DENOM ", hasVesting)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationItem.title = NSLocalizedString("title_token_detail", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (hasVesting == true) {
            return 2
        }
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            if ((chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) && self.denom == KAVA_HARD_DENOM) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailHardCell") as? TokenDetailHardCell
                cell?.onBindHardToken()
                return cell!
            }
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailNativeCell") as? TokenDetailNativeCell
            cell?.onBindNativeToken(chainType, denom)
            return cell!
            
        } else if (indexPath.row == 1 && hasVesting) {
            let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailVestingDetailCell") as? TokenDetailVestingDetailCell
            cell?.onBindVesting(chainType!, denom!)
            return cell!
            
        }
        let cell = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        return cell!
    }
    
    
    @IBAction func onClickShare(_ sender: UIButton) {
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
    }
    
    @IBAction func onClickBep3Send(_ sender: UIButton) {
    }
    
    @IBAction func onClickIbcSend(_ sender: UIButton) {
    }
}
