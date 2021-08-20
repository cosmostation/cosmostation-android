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
    @IBOutlet weak var btnIcbSend: UIButton!
    @IBOutlet weak var btnBep3Send: UIButton!
    
    var hasVesting = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.tokenDetailTableView.delegate = self
        self.tokenDetailTableView.dataSource = self
        self.tokenDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailCosmosCell", bundle: nil), forCellReuseIdentifier: "TokenDetailCosmosCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailIrisCell", bundle: nil), forCellReuseIdentifier: "TokenDetailIrisCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailAkashCell", bundle: nil), forCellReuseIdentifier: "TokenDetailAkashCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailPersistenceCell", bundle: nil), forCellReuseIdentifier: "TokenDetailPersistenceCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailKavaCell", bundle: nil), forCellReuseIdentifier: "TokenDetailKavaCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailCrytoCell", bundle: nil), forCellReuseIdentifier: "TokenDetailCrytoCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailSifCell", bundle: nil), forCellReuseIdentifier: "TokenDetailSifCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailExCell", bundle: nil), forCellReuseIdentifier: "TokenDetailExCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailBnbCell", bundle: nil), forCellReuseIdentifier: "TokenDetailBnbCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailSentinelCell", bundle: nil), forCellReuseIdentifier: "TokenDetailSentinelCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailRizonCell", bundle: nil), forCellReuseIdentifier: "TokenDetailRizonCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailAltheaCell", bundle: nil), forCellReuseIdentifier: "TokenDetailAltheaCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailOsmoCell", bundle: nil), forCellReuseIdentifier: "TokenDetailOsmoCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailBandCell", bundle: nil), forCellReuseIdentifier: "TokenDetailBandCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailIovCell", bundle: nil), forCellReuseIdentifier: "TokenDetailIovCell")
        self.tokenDetailTableView.register(UINib(nibName: "TokenDetailMediCell", bundle: nil), forCellReuseIdentifier: "TokenDetailMediCell")
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
            if (BaseData.instance.onParseRemainVestingsByDenom_gRPC(WUtils.getMainDenom(chainType)).count > 0) { hasVesting = true }
            btnIcbSend.isHidden = false
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (BaseData.instance.mKavaAccountResult.getCalcurateVestingCntByDenom(KAVA_MAIN_DENOM) > 0) { hasVesting = true }
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            btnBep3Send.isHidden = false
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
//        self.navigationController?.setNavigationBarHidden(false, animated: animated)
//        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_token_detail", comment: "");
//        self.navigationItem.title = NSLocalizedString("title_token_detail", comment: "");
//        self.navigationItem.title.co
//        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
//        self.navigationController?.navigationBar.shadowImage = UIImage()
        
//        let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 20, height: 20))
//        imageView.contentMode = .scaleAspectFit
//        let image = UIImage(named: "testnetMedibloc")
//        imageView.image = image
//        navigationItem.titleView = imageView
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
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailCosmosCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.IRIS_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailIrisCell") as? TokenDetailCell
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
                
            } else if (chainType == ChainType.CRYPTO_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailCrytoCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.SENTINEL_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailSentinelCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.OSMOSIS_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailOsmoCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.BAND_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailBandCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.IOV_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailIovCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.SIF_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailSifCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.MEDI_MAIN) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailMediCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            }  else if (chainType == ChainType.RIZON_TEST) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailRizonCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
                
            } else if (chainType == ChainType.ALTHEA_TEST) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailAltheaCell") as? TokenDetailCell
                cell?.onBindToken()
                return cell!
            }
            
            else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailExCell") as? TokenDetailExCell
                cell?.onBindTokens(account!)
                return cell!
                
            } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailBnbCell") as? TokenDetailBnbCell
                cell?.onBindTokens(account!)
                return cell!
                
            } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ) {
                let cell = tableView.dequeueReusableCell(withIdentifier:"TokenDetailKavaCell") as? TokenDetailKavaCell
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
    
    
    
    
    @IBAction func onClickShare(_ sender: UIButton) {
        var nickName:String?
        if (account!.account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account!.account_id)
        } else {
            nickName = account!.account_nick_name
        }
        var address = account!.account_address
        if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            address = WKey.convertAddressOkexToEth(address)
        }
        self.shareAddress(address, nickName!)
    }
    
    @IBAction func onClickSend(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let mainDenom = WUtils.getMainDenom(chainType)
        if (WUtils.isGRPC(chainType!)) {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.getAvailableAmount_gRPC(mainDenom).compare(feeAmount).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
        } else {
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, COSMOS_MSG_TYPE_TRANSFER2, 0)
            if (BaseData.instance.availableAmount(mainDenom).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mToSendDenom = mainDenom
        txVC.mType = COSMOS_MSG_TYPE_TRANSFER2
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickBep3Send(_ sender: UIButton) {
        if (!SUPPORT_BEP3_SWAP || chainType == ChainType.BINANCE_TEST || chainType == ChainType.KAVA_TEST) {
            self.onShowToast(NSLocalizedString("error_bep3_swap_temporary_disable", comment: ""))
            return
        }
        
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        //no gRPC case
        let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, TASK_TYPE_HTLC_SWAP, 0)
        if (BaseData.instance.availableAmount(WUtils.getMainDenom(chainType)).compare(feeAmount).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_balance_to_send", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = TASK_TYPE_HTLC_SWAP
        txVC.mHtlcDenom = WUtils.getMainDenom(chainType)
        txVC.hidesBottomBarWhenPushed = true
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    @IBAction func onClickIbcSend(_ sender: UIButton) {
        self.onShowToast(NSLocalizedString("prepare", comment: ""))
    }
}
