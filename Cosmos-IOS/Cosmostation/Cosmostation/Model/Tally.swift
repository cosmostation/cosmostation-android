//
//  Tally.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/18.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class Tally {
    
    var yes: String = ""
    var abstain: String = ""
    var no: String = ""
    var no_with_veto: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.yes = dictionary["yes"] as? String ?? "0"
        self.abstain = dictionary["abstain"] as? String ?? "0"
        self.no = dictionary["no"] as? String ?? "0"
        self.no_with_veto = dictionary["no_with_veto"] as? String ?? "0"
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
        guard let param = BaseData.instance.mParam else {
            return NSDecimalNumber.zero
        }
        return getSum().multiplying(byPowerOf10: 2).dividing(by: param.getBondedAmount(), withBehavior: WUtils.handler2)
    }
}
