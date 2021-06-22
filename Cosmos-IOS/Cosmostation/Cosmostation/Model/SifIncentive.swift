//
//  SifVsIncentive.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/22.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct SifIncentive {
    var user: SifUserIncentive?
    
    init(_ dictionary: NSDictionary?) {
        if let rawUser = dictionary?["user"] as? NSDictionary {
            self.user = SifUserIncentive.init(rawUser)
        }
    }
}

public struct SifUserIncentive {
    var totalClaimableCommissionsAndClaimableRewards: Double?
    var totalRewardsOnDepositedAssetsAtMaturity: Double?
    var maturityAPY: Double?
    var maturityDateISO: String?
    
    init(_ dictionary: NSDictionary?) {
        self.totalClaimableCommissionsAndClaimableRewards = dictionary?["totalClaimableCommissionsAndClaimableRewards"] as? Double ?? 0
        self.totalRewardsOnDepositedAssetsAtMaturity = dictionary?["totalRewardsOnDepositedAssetsAtMaturity"] as? Double ?? 0
        self.maturityAPY = dictionary?["maturityAPY"] as? Double ?? 0
        self.maturityDateISO = dictionary?["maturityDateISO"] as? String ?? ""
    }
}
