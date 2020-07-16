//
//  SelectPopupViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class SelectPopupViewController: BaseViewController, SBCardPopupContent, UITableViewDelegate, UITableViewDataSource {
    let SELECT_POPUP_HTLC_CHAIN = 0
    let SELECT_POPUP_HTLC_ACCOUNT = 1
    
    var popupViewController: SBCardPopupViewController?
    let allowsTapToDismissPopupCard =  true
    let allowsSwipeToDismissPopupCard =  false
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var popupTitle: UILabel!
    @IBOutlet weak var popupTableview: UITableView!
    
    var type: Int?
    var toChainList = Array<ChainType>()
    var toChain: ChainType?
    var toAccountList = Array<Account>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.popupTableview.delegate = self
        self.popupTableview.dataSource = self
        self.popupTableview.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.popupTableview.register(UINib(nibName: "SelectChainCell", bundle: nil), forCellReuseIdentifier: "SelectChainCell")
        self.popupTableview.register(UINib(nibName: "SelectAccountCell", bundle: nil), forCellReuseIdentifier: "SelectAccountCell")
        self.popupTableview.rowHeight = UITableView.automaticDimension
        self.popupTableview.estimatedRowHeight = UITableView.automaticDimension
        
        if (type == SELECT_POPUP_HTLC_CHAIN) {
            self.popupTitle.text = NSLocalizedString("select_destination_chain", comment: "")
            self.toChainList = ChainType.getHtlcSendable(chainType!)
            
        } else if (type == SELECT_POPUP_HTLC_ACCOUNT) {
            self.popupTitle.text = NSLocalizedString("select_account", comment: "")
            self.toAccountList = BaseData.instance.selectAllAccountsByHtlcClaim(toChain)
        }
        
    }
    
    override func viewDidLayoutSubviews() {
        var esHeight: CGFloat = 250
        if (type == SELECT_POPUP_HTLC_CHAIN) {
            esHeight = (CGFloat)((toChainList.count * 55) + 55)
        } else if (type == SELECT_POPUP_HTLC_ACCOUNT) {
            esHeight = (CGFloat)((toAccountList.count * 55) + 55)
        }
        esHeight = (esHeight > 250) ? 250 : esHeight
        cardView.frame = CGRect(x: cardView.frame.origin.x, y: cardView.frame.origin.y, width: cardView.frame.size.width, height: esHeight)
        cardView.layoutIfNeeded()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (type == SELECT_POPUP_HTLC_CHAIN) {
            return toChainList.count;
        } else if (type == SELECT_POPUP_HTLC_ACCOUNT) {
            return toAccountList.count
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (type == SELECT_POPUP_HTLC_CHAIN) {
            let cell:SelectChainCell? = tableView.dequeueReusableCell(withIdentifier:"SelectChainCell") as? SelectChainCell
            let chain = toChainList[indexPath.row]
            WUtils.dpChainInfo(chain, cell!.chainImg, cell!.chainTitle)
            return cell!
            
        } else {
            let cell:SelectAccountCell? = tableView.dequeueReusableCell(withIdentifier:"SelectAccountCell") as? SelectAccountCell
            let account = toAccountList[indexPath.row]
            cell?.keyStatusImg.image = cell?.keyStatusImg.image?.withRenderingMode(.alwaysTemplate)
            cell?.accountAddress.text = account.account_address
            WUtils.setDenomTitle(toChain!, cell!.accountDenom)
            if (toChain == ChainType.BINANCE_MAIN || toChain == ChainType.BINANCE_TEST) {
                cell?.keyStatusImg.tintColor = COLOR_BNB
                cell!.accountBalance.attributedText = WUtils.displayAmount2(WUtils.getTokenAmount(account.account_balances, BNB_MAIN_DENOM).stringValue, cell!.accountBalance.font, 0, 8)
                
            } else if (toChain == ChainType.KAVA_MAIN || toChain == ChainType.KAVA_TEST) {
                cell?.keyStatusImg.tintColor = COLOR_KAVA
                cell!.accountBalance.attributedText = WUtils.displayAmount2(WUtils.getTokenAmount(account.account_balances, KAVA_MAIN_DENOM).stringValue, cell!.accountBalance.font, 6, 6)
            }
            return cell!
            
        }
    }
    
     func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        popupViewController?.resultDelegate?.SBCardPopupResponse(result: indexPath.row)
        popupViewController?.close()
    }

}
