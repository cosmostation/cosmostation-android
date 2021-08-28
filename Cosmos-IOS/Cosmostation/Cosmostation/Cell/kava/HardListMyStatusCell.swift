//
//  HardListMyStatusCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class HardListMyStatusCell: UITableViewCell {
    @IBOutlet weak var totalDepositedValue: UILabel!
    @IBOutlet weak var maxBorrowableValue: UILabel!
    @IBOutlet weak var totalBorrowedValue: UILabel!
    @IBOutlet weak var remainingBorrowableValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        
        totalDepositedValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        maxBorrowableValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        totalBorrowedValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
        remainingBorrowableValue.font = UIFontMetrics(forTextStyle: .footnote).scaledFont(for: Font_13_footnote)
    }
    
    func onBindView(_ hardParam: HardParam?, _ myDeposit: Array<HardMyDeposit>?, _ myBorrow: Array<HardMyBorrow>?) {
        var totalDepositValue = NSDecimalNumber.zero
        var totalLTVValue = NSDecimalNumber.zero
        if let myDposits = myDeposit, let coins = myDposits[0].amount {
            for coin in coins {
                let decimal         = WUtils.getKavaCoinDecimal(coin.denom)
                let LTV             = hardParam!.getLTV(coin.denom)
                var depositValue    = NSDecimalNumber.zero
                var ltvValue        = NSDecimalNumber.zero
                if let price = BaseData.instance.mKavaPrice[hardParam!.getSpotMarketId(coin.denom)!] {
                    depositValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -decimal).multiplying(by: NSDecimalNumber.init(string: price.result.price), withBehavior: WUtils.handler12Down)
                }
                ltvValue = depositValue.multiplying(by: LTV)
                totalLTVValue = totalLTVValue.adding(ltvValue)
                totalDepositValue = totalDepositValue.adding(depositValue)
            }
        }
        totalDepositedValue.attributedText = WUtils.getDPRawDollor(totalDepositValue.stringValue, 2, totalDepositedValue.font)
        maxBorrowableValue.attributedText = WUtils.getDPRawDollor(totalLTVValue.stringValue, 2, maxBorrowableValue.font)
        
        var totalBorroweValue = NSDecimalNumber.zero
        if let myBorrows = myBorrow, let coins = myBorrows[0].amount {
            for coin in coins {
                let decimal         = WUtils.getKavaCoinDecimal(coin.denom)
                var borrowValue     = NSDecimalNumber.zero
                if let price = BaseData.instance.mKavaPrice[hardParam!.getSpotMarketId(coin.denom)!] {
                    borrowValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -decimal).multiplying(by: NSDecimalNumber.init(string: price.result.price), withBehavior: WUtils.handler12Down)
                }
                totalBorroweValue = totalBorroweValue.adding(borrowValue)
            }
        }
        totalBorrowedValue.attributedText = WUtils.getDPRawDollor(totalBorroweValue.stringValue, 2, totalBorrowedValue.font)
        let remainBorrowable = (totalLTVValue.subtracting(totalBorroweValue).compare(NSDecimalNumber.zero).rawValue > 0) ? totalLTVValue.subtracting(totalBorroweValue) : NSDecimalNumber.zero
        remainingBorrowableValue.attributedText = WUtils.getDPRawDollor(remainBorrowable.stringValue, 2, remainingBorrowableValue.font)
    }
    
}
