//
//  Tally_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct Tally_V1 {
    var yes: String?
    var abstain: String?
    var no: String?
    var no_with_veto: String?
    
    init(_ dictionary: NSDictionary?) {
        self.yes = dictionary?["yes"] as? String
        self.abstain = dictionary?["abstain"] as? String
        self.no = dictionary?["no"] as? String
        self.no_with_veto = dictionary?["no_with_veto"] as? String
    }
    
    public func getSum() ->NSDecimalNumber {
        var sum = NSDecimalNumber.zero
        sum = sum.adding(NSDecimalNumber.init(string: yes))
        sum = sum.adding(NSDecimalNumber.init(string: no))
        sum = sum.adding(NSDecimalNumber.init(string: no_with_veto))
        sum = sum.adding(NSDecimalNumber.init(string: abstain))
        return sum
    }
    
    public func getYes() -> NSDecimalNumber {
        if (getSum() == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: yes).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getNo() -> NSDecimalNumber {
        if (getSum() == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: no).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getVeto() -> NSDecimalNumber {
        if (getSum() == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: no_with_veto).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getAbstain() -> NSDecimalNumber {
        if (getSum() == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: abstain).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getTurnout() -> NSDecimalNumber {
        if let bonded = BaseData.instance.mStakingPool_V1?.getBondedTokens() {
            return getSum().multiplying(byPowerOf10: 2).dividing(by: bonded, withBehavior: WUtils.handler2)
        }
        return NSDecimalNumber.zero
    }
}
