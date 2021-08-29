//
//  IncentiveReward.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IncentiveReward {
    var hard_claims: Array<HardClaim> = Array<HardClaim>()
    var usdx_minting_claims: Array<UsdxMintingClaim> = Array<UsdxMintingClaim>()
    var delegator_claims: Array<DelegatorClaim> = Array<DelegatorClaim>()
    var swap_claims: Array<SwapClaim> = Array<SwapClaim>()
    
    init(_ dictionary: NSDictionary?) {
        if let rawHardClaims = dictionary?["hard_claims"] as? Array<NSDictionary>  {
            for rawHardClaim in rawHardClaims {
                self.hard_claims.append(HardClaim(rawHardClaim))
            }
        }
        if let rawUsdxMintingClaims = dictionary?["usdx_minting_claims"] as? Array<NSDictionary>  {
            for rawUsdxMintingClaim in rawUsdxMintingClaims {
                self.usdx_minting_claims.append(UsdxMintingClaim(rawUsdxMintingClaim))
            }
        }
        if let rawDelegatorClaims = dictionary?["delegator_claims"] as? Array<NSDictionary>  {
            for rawDelegatorClaim in rawDelegatorClaims {
                self.delegator_claims.append(DelegatorClaim(rawDelegatorClaim))
            }
        }
        if let rawSwapClaims = dictionary?["swap_claims"] as? Array<NSDictionary>  {
            for rawSwapClaim in rawSwapClaims {
                self.swap_claims.append(SwapClaim(rawSwapClaim))
            }
        }
    }
    
    public func getAllIncentives() -> Array<Coin> {
        var result = Array<Coin>()
        var kavaAmount = NSDecimalNumber.zero
        var hardAmount = NSDecimalNumber.zero
        var usdxAmount = NSDecimalNumber.zero
        var swapAmount = NSDecimalNumber.zero
        hard_claims.forEach { claim in
            claim.base_claim?.reward?.forEach({ reward in
                if (reward.denom == KAVA_MAIN_DENOM) {
                    kavaAmount = kavaAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_HARD_DENOM) {
                    hardAmount = hardAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_USDX_DENOM) {
                    usdxAmount = usdxAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_SWAP_DENOM) {
                    swapAmount = swapAmount.adding(NSDecimalNumber.init(string: reward.amount))
                }
            })
        }
        usdx_minting_claims.forEach { claim in
            kavaAmount = kavaAmount.adding(NSDecimalNumber.init(string: claim.base_claim?.reward?.amount))
        }
        delegator_claims.forEach { claim in
            claim.base_claim?.reward?.forEach({ reward in
                if (reward.denom == KAVA_MAIN_DENOM) {
                    kavaAmount = kavaAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_HARD_DENOM) {
                    hardAmount = hardAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_USDX_DENOM) {
                    usdxAmount = usdxAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_SWAP_DENOM) {
                    swapAmount = swapAmount.adding(NSDecimalNumber.init(string: reward.amount))
                }
            })
        }
        swap_claims.forEach { claim in
            claim.base_claim?.reward?.forEach({ reward in
                if (reward.denom == KAVA_MAIN_DENOM) {
                    kavaAmount = kavaAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_HARD_DENOM) {
                    hardAmount = hardAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_USDX_DENOM) {
                    usdxAmount = usdxAmount.adding(NSDecimalNumber.init(string: reward.amount))
                } else if (reward.denom == KAVA_SWAP_DENOM) {
                    swapAmount = swapAmount.adding(NSDecimalNumber.init(string: reward.amount))
                }
            })
        }
        if (kavaAmount.compare(NSDecimalNumber.zero).rawValue > 0) {
            result.append(Coin.init(KAVA_MAIN_DENOM, kavaAmount.stringValue))
        }
        if (hardAmount.compare(NSDecimalNumber.zero).rawValue > 0) {
            result.append(Coin.init(KAVA_HARD_DENOM, hardAmount.stringValue))
        }
        if (usdxAmount.compare(NSDecimalNumber.zero).rawValue > 0) {
            result.append(Coin.init(KAVA_USDX_DENOM, usdxAmount.stringValue))
        }
        if (swapAmount.compare(NSDecimalNumber.zero).rawValue > 0) {
            result.append(Coin.init(KAVA_SWAP_DENOM, swapAmount.stringValue))
        }
        return result;
    }
    
    public func getIncentiveAmount(_ denom: String) -> NSDecimalNumber {
        if let coin = getAllIncentives().filter { $0.denom == denom }.first {
            return NSDecimalNumber.init(string: coin.amount)
        }
        return NSDecimalNumber.zero
    }
    
    
    public func getMintingRewardCnt() -> Int {
        return usdx_minting_claims.count;
    }
    
    public func getHardRewardCnt() -> Int {
        return hard_claims.count;
    }
    
    public func getDelegatorRewardCnt() -> Int {
        return delegator_claims.count;
    }
    
    public func getSwapRewardCnt() -> Int {
        return swap_claims.count;
    }
    
    public func getMintingRewardAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        usdx_minting_claims.forEach { usdxMintingClaim in
            result = result.adding(NSDecimalNumber.init(string: usdxMintingClaim.base_claim?.reward?.amount))
        }
        return result
    }
    
    public func getHardRewardDenoms() -> Array<String> {
        var result = Array<String>()
        hard_claims.forEach { hardClaim in
            hardClaim.base_claim?.reward?.forEach({ coin in
                if (!result.contains(coin.denom)) {
                    result.append(coin.denom)
                }
            })
        }
        return result
    }
    
    public func getDelegatorRewardDenoms() -> Array<String> {
        var result = Array<String>()
        delegator_claims.forEach { delegatorClaim in
            delegatorClaim.base_claim?.reward?.forEach({ coin in
                if (!result.contains(coin.denom)) {
                    result.append(coin.denom)
                }
            })
        }
        return result
    }
    
    public func getSwapRewardDenoms() -> Array<String> {
        var result = Array<String>()
        swap_claims.forEach { swapClaim in
            swapClaim.base_claim?.reward?.forEach({ coin in
                if (!result.contains(coin.denom)) {
                    result.append(coin.denom)
                }
            })
        }
        return result
    }
    
    
    
    
    
    public struct HardClaim {
        var base_claim: BaseClaim?
        
        init(_ dictionary: NSDictionary?) {
            if let rawBaseClaim = dictionary?["base_claim"] as? NSDictionary {
                self.base_claim = BaseClaim.init(rawBaseClaim)
            }
        }
    }
    
    
    
    public struct UsdxMintingClaim {
        var base_claim: MintBaseClaim?
        
        init(_ dictionary: NSDictionary?) {
            if let rawBaseClaim = dictionary?["base_claim"] as? NSDictionary {
                self.base_claim = MintBaseClaim.init(rawBaseClaim)
            }
        }
    }
    
    public struct DelegatorClaim {
        var base_claim: BaseClaim?
        
        init(_ dictionary: NSDictionary?) {
            if let rawBaseClaim = dictionary?["base_claim"] as? NSDictionary {
                self.base_claim = BaseClaim.init(rawBaseClaim)
            }
        }
        
    }
    
    public struct SwapClaim {
        var base_claim: BaseClaim?
        
        init(_ dictionary: NSDictionary?) {
            if let rawBaseClaim = dictionary?["base_claim"] as? NSDictionary {
                self.base_claim = BaseClaim.init(rawBaseClaim)
            }
        }
    }
    
    public struct MintBaseClaim {
        var owner: String?
        var reward: Coin?

        init(_ dictionary: NSDictionary?) {
            self.owner = dictionary?["owner"] as? String
            if let rawCoin = dictionary?["reward"] as? NSDictionary  {
                self.reward = Coin.init(rawCoin)
            }
        }
    }
    
    public struct BaseClaim {
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
}
