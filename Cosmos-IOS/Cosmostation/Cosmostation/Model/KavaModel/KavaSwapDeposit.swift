//
//  KavaSwapDeposit.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/28.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaSwapDeposit {
    var height: String?
    var result: Array<SwapDeposit> = Array<SwapDeposit>()
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String
        if let rawSwapDeposits = dictionary["result"] as? Array<NSDictionary> {
            rawSwapDeposits.forEach { rawSwapDeposit in
                self.result.append(SwapDeposit.init(rawSwapDeposit))
            }
        }
    }
}

public struct SwapDeposit {
    var depositor: String?
    var pool_id: String?
    var shares_owned = NSDecimalNumber.zero
    var shares_value: Array<Coin> = Array<Coin>()
    
    init(_ dictionary: NSDictionary?) {
        self.depositor = dictionary?["depositor"] as? String
        self.pool_id = dictionary?["pool_id"] as? String
        if let rawSharesOwned = dictionary?["shares_owned"] as? String {
            self.shares_owned = NSDecimalNumber.init(string: rawSharesOwned)
        }
        if let rawSharesValues = dictionary?["shares_value"] as? Array<NSDictionary> {
            for rawSharesValue in rawSharesValues {
                self.shares_value.append(Coin.init(rawSharesValue))
            }
        }
    }
    
}
