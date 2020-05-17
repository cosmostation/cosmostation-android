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
        self.yes = dictionary["yes"] as? String ?? ""
        self.abstain = dictionary["abstain"] as? String ?? ""
        self.no = dictionary["no"] as? String ?? ""
        self.no_with_veto = dictionary["no_with_veto"] as? String ?? ""
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
        return NSDecimalNumber.init(string: yes).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getNo() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: no).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getVeto() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: no_with_veto).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
    
    public func getAbstain() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: abstain).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
    }
}
