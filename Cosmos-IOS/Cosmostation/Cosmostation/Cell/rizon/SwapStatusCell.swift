//
//  SwapStatusCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/02.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class SwapStatusCell: UITableViewCell {

    @IBOutlet weak var totalResultLabel: UILabel!
    @IBOutlet weak var requestTimeLabel: UILabel!
    @IBOutlet weak var requestIdLabel: UILabel!
    @IBOutlet weak var burnInfoCard: CardView!
    @IBOutlet weak var burnStatusImg: UIImageView!
    @IBOutlet weak var burnStatusTxtLabel: UILabel!
    @IBOutlet weak var burnAddressLabel: UILabel!
    @IBOutlet weak var burnHashLabel: UILabel!
    @IBOutlet weak var burnAmountLabel: UILabel!
    @IBOutlet weak var mintInfoCard: CardView!
    @IBOutlet weak var mintStatusImg: UIImageView!
    @IBOutlet weak var mintStatusTxtLabel: UILabel!
    @IBOutlet weak var mintAddressLabel: UILabel!
    @IBOutlet weak var mintHashLabel: UILabel!
    @IBOutlet weak var mintAmountLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        burnAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        mintAmountLabel.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        
        let tapHdac = UITapGestureRecognizer(target: self, action: #selector(self.onTapHdac))
        self.burnInfoCard.addGestureRecognizer(tapHdac)
        
        let tapRizon = UITapGestureRecognizer(target: self, action: #selector(self.onTapRizon))
        self.mintInfoCard.addGestureRecognizer(tapRizon)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        totalResultLabel.text = "--"
        requestTimeLabel.text = "--"
        requestIdLabel.text = "--"
        burnStatusImg.isHidden = true
        burnStatusTxtLabel.text = "--"
        burnAddressLabel.text = "--"
        burnHashLabel.text = "--"
        burnAmountLabel.text = "--"
        mintStatusImg.isHidden = true
        mintStatusTxtLabel.text = "--"
        mintAddressLabel.text = "--"
        mintHashLabel.text = "--"
        mintAmountLabel.text = "--"
        
    }
    
    func onBindSwapStatus(_ chainType: ChainType?, _ swapInfo: RizonSwapStatus?) {
        if (swapInfo == nil) { return }
        
        totalResultLabel.text = swapInfo?.status?.uppercased()
        requestIdLabel.text = swapInfo?._id
        if let time = swapInfo?.hdacTx?.time {
            requestTimeLabel.text = WUtils.longTimetoString(input: Int64(time) * 1000)
        }
        
        burnAddressLabel.text = swapInfo?.from
        mintAddressLabel.text = swapInfo?.to
        
        if (swapInfo?.hdacTx != nil) {
            burnHashLabel.text = swapInfo?.hdacTxId
            burnAmountLabel.attributedText = WUtils.displayAmount2(String(swapInfo!.amount!), burnAmountLabel.font!, 0, 8)
            
            let request = Alamofire.request(BaseNetWork.hdacTxDetail(chainType, swapInfo!.hdacTxId!), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    if let rawTxDetail = res as? NSDictionary {
                        let txDetail = HdacTx.init(rawTxDetail)
                        if (chainType == ChainType.RIZON_TEST && txDetail.confirmations ?? 0 >= 1) {
                            self.burnStatusImg.isHidden = true
                            self.burnStatusTxtLabel.text = "Success"
                            
                        } else {
                            self.burnStatusImg.isHidden = false
                            self.burnStatusTxtLabel.text = "pending"
                        }
                    }
                
                case .failure(let error):
                    print("hdacTxDetail ", error)
                }
            }
        }
        
        if (swapInfo?.rizonTxId?.isEmpty == false) {
            mintStatusImg.isHidden = true
            mintStatusTxtLabel.text = "Success"
            mintHashLabel.text = swapInfo?.rizonTxId
            mintAmountLabel.attributedText = WUtils.displayAmount2(String(swapInfo!.amount!), mintAmountLabel.font!, 0, 6)
            
        } else {
            mintStatusImg.isHidden = false
            mintStatusTxtLabel.text = "pending"
            mintHashLabel.text = "--"
            mintAmountLabel.text = "--"
        
        }
        
    }
    
    
    
    
    var actionTapHdac: (() -> Void)? = nil
    var actionTapRizon: (() -> Void)? = nil
    @objc func onTapHdac(sender : UITapGestureRecognizer) {
        actionTapHdac?()
    }
    @objc func onTapRizon(sender : UITapGestureRecognizer) {
        actionTapRizon?()
    }
    
}
