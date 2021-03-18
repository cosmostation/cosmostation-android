//
//  IncentiveReward.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IncentiveReward {
    var hard_claims: Array<HardClaim>?
    var usdx_minting_claims: Array<UsdxMintingClaim>?
    
    init(_ dictionary: NSDictionary?) {
        if let rawHardClaims = dictionary?["hard_claims"] as? Array<NSDictionary>  {
            self.hard_claims = Array<HardClaim>()
            for rawHardClaim in rawHardClaims {
                self.hard_claims!.append(HardClaim(rawHardClaim))
            }
        }
        if let rawUsdxMintingClaims = dictionary?["usdx_minting_claims"] as? Array<NSDictionary>  {
            self.usdx_minting_claims = Array<UsdxMintingClaim>()
            for rawUsdxMintingClaim in rawUsdxMintingClaims {
                self.usdx_minting_claims!.append(UsdxMintingClaim(rawUsdxMintingClaim))
            }
        }
    }
    
    public func getHardPoolRewardCnt() -> Int {
        var result = 0;
        if (hard_claims != nil) {
            result = hard_claims!.count
        }
        return result;
    }
    
    public func getHardPoolHardRewardAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero;
        if let hardClaims = hard_claims {
            for reward in hardClaims {
                if let claimRewards = reward.base_claim?.reward {
                    for coin in claimRewards {
                        if (coin.denom == KAVA_HARD_DENOM) {
                            result = result.adding(NSDecimalNumber.init(string: coin.amount))
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public func getHardPoolKavaRewardAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero;
        if let hardClaims = hard_claims {
            for reward in hardClaims {
                if let claimRewards = reward.base_claim?.reward {
                    for coin in claimRewards {
                        if (coin.denom == KAVA_MAIN_DENOM) {
                            result = result.adding(NSDecimalNumber.init(string: coin.amount))
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public func getMintingRewardCnt() -> Int {
        var result = 0;
        if (usdx_minting_claims != nil) {
            result = usdx_minting_claims!.count
        }
        return result;
    }
    
    public func getMintingRewardAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero;
        if let usdxMintingClaims = usdx_minting_claims {
            for reward in usdxMintingClaims {
                if let coin = reward.base_claim?.reward {
                    result = result.adding(NSDecimalNumber.init(string: coin.amount))
                }
            }
        }
        return result;
    }
    
    
    
    
    
    
    public struct HardClaim {
        var base_claim: HardBaseClaim?
        var supply_reward_indexes: Array<SupplyRewardIndex>?
        var borrow_reward_indexes: Array<BorrowRewardIndex>?
        
        init(_ dictionary: NSDictionary?) {
            if let rawBaseClaim = dictionary?["base_claim"] as? NSDictionary {
                self.base_claim = HardBaseClaim.init(rawBaseClaim)
            }
            if let rawSupplyRewardIndexes = dictionary?["supply_reward_indexes"] as? Array<NSDictionary>  {
                self.supply_reward_indexes = Array<SupplyRewardIndex>()
                for rawSupplyRewardIndex in rawSupplyRewardIndexes {
                    self.supply_reward_indexes!.append(SupplyRewardIndex(rawSupplyRewardIndex))
                }
            }
            if let rawBorrowRewardIndexes = dictionary?["borrow_reward_indexes"] as? Array<NSDictionary>  {
                self.borrow_reward_indexes = Array<BorrowRewardIndex>()
                for rawBorrowRewardIndex in rawBorrowRewardIndexes {
                    self.borrow_reward_indexes!.append(BorrowRewardIndex(rawBorrowRewardIndex))
                }
            }
        }
    }
    
    
    public struct HardBaseClaim {
        var owner: String?
        var reward: Array<Coin>?
        
        init(_ dictionary: NSDictionary?) {
            self.owner = dictionary?["owner"] as? String
            if let rawCoins = dictionary?["reward"] as? Array<NSDictionary>  {
                self.reward = Array<Coin>()
                for rawCoin in rawCoins {
                    self.reward!.append(Coin(rawCoin))
                }
            }
        }
    }
    
    
    public struct SupplyRewardIndex {
        var collateral_type: String?
        var reward_indexes: Array<RewardIndex>?
        
        init(_ dictionary: NSDictionary?) {
            self.collateral_type = dictionary?["collateral_type"] as? String
            if let rawRewardIndexes = dictionary?["reward_indexes"] as? Array<NSDictionary>  {
                self.reward_indexes = Array<RewardIndex>()
                for rawRewardIndexe in rawRewardIndexes {
                    self.reward_indexes!.append(RewardIndex(rawRewardIndexe))
                }
            }
        }
    }
    
    public struct BorrowRewardIndex {
        var collateral_type: String?
        var reward_indexes: Array<RewardIndex>?
        
        init(_ dictionary: NSDictionary?) {
            self.collateral_type = dictionary?["collateral_type"] as? String
            if let rawRewardIndexes = dictionary?["reward_indexes"] as? Array<NSDictionary>  {
                self.reward_indexes = Array<RewardIndex>()
                for rawRewardIndexe in rawRewardIndexes {
                    self.reward_indexes!.append(RewardIndex(rawRewardIndexe))
                }
            }
        }
    }
    
    public struct RewardIndex {
        var collateral_type: String?
        var reward_factor: String?
        
        init(_ dictionary: NSDictionary?) {
            self.collateral_type = dictionary?["collateral_type"] as? String
            self.reward_factor = dictionary?["reward_factor"] as? String
        }
    }
    
    public struct UsdxMintingClaim {
        var base_claim: UsdxBaseClaim?
        var reward_factor: String?
        
        init(_ dictionary: NSDictionary?) {
            if let rawBaseClaim = dictionary?["base_claim"] as? NSDictionary {
                self.base_claim = UsdxBaseClaim.init(rawBaseClaim)
            }
            self.reward_factor = dictionary?["reward_factor"] as? String
        }
    }
    
    
    public struct UsdxBaseClaim {
        var owner: String?
        var reward: Coin?
        
        init(_ dictionary: NSDictionary?) {
            self.owner = dictionary?["owner"] as? String
            if let rawReward = dictionary?["reward"] as? NSDictionary {
                self.reward = Coin.init(rawReward)
            }
        }
    }
}
