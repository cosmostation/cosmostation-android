//
//  Param.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/10.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct Param {
    var chain_id: String?
    var params: Params?
    
    init(_ dictionary: NSDictionary?) {
        self.chain_id = dictionary?["chain_id"] as? String
        if let rawParams = dictionary?["Params"] as? NSDictionary {
            self.params = Params.init(rawParams)
        }
    }
    
    func getInflation(_ chainType: ChainType?) -> NSDecimalNumber {
        if (chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            return NSDecimalNumber.init(string: params?.minting_params?.inflation)
        }
        return NSDecimalNumber.init(string: params?.minting_inflation)
    }
    
    func getDpInflation(_ chainType: ChainType?) -> NSDecimalNumber {
        return getInflation(chainType).multiplying(byPowerOf10: 2, withBehavior: WUtils.handler2Down)
    }
    
    func getBondedAmount() -> NSDecimalNumber {
        if let pool = params?.staking_pool?.pool {
            return NSDecimalNumber.init(string: pool.bonded_tokens)
        }
        if let bonded_tokens = params?.staking_pool?.bonded_tokens {
            return NSDecimalNumber.init(string: bonded_tokens)
        }
        return NSDecimalNumber.zero
    }
    
    func getTax() -> NSDecimalNumber {
        if let params = params?.distribution_params?.params {
            return NSDecimalNumber.init(string: params.community_tax)
        }
        
        if let community_tax = params?.distribution_params?.community_tax {
            return NSDecimalNumber.init(string: community_tax)
        }
        return NSDecimalNumber.zero
    }
    
    func getMainSupply() -> NSDecimalNumber {
        if let denom = params?.staking_params?.getMainDenom() {
            return NSDecimalNumber.init(string: params?.supply?.filter { $0.denom == denom}.first?.amount)
        }
        return NSDecimalNumber.zero
    }
    
    func getApr(_ chain: ChainType?) -> NSDecimalNumber {
        let inflation = getInflation(chain)
        let calTax = NSDecimalNumber.one.subtracting(getTax())
        if (getMainSupply() == NSDecimalNumber.zero) { return NSDecimalNumber.zero}
        let bondingRate = getBondedAmount().dividing(by: getMainSupply(), withBehavior: WUtils.handler6)
        if (bondingRate == NSDecimalNumber.zero) { return NSDecimalNumber.zero}
        return inflation.multiplying(by: calTax).dividing(by: bondingRate, withBehavior: WUtils.handler6)
    }
    
    func getDpApr(_ chain: ChainType?) -> NSDecimalNumber {
        if (getApr(chain) == NSDecimalNumber.zero) { return NSDecimalNumber.zero}
        return getApr(chain).multiplying(byPowerOf10: 2, withBehavior: WUtils.handler2)
    }
    
    func getRealApr(_ chain: ChainType?) -> NSDecimalNumber {
        if (WUtils.getRealBlockPerYear(chain) == NSDecimalNumber.zero || getBlockPerYear(chain) == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return getApr(chain).multiplying(by: WUtils.getRealBlockPerYear(chain)).dividing(by: getBlockPerYear(chain), withBehavior: WUtils.handler6)
    }
    
    func getDpRealApr(_ chain: ChainType?) -> NSDecimalNumber {
        if (getRealApr(chain) == NSDecimalNumber.zero) { return NSDecimalNumber.zero }
        return getRealApr(chain).multiplying(byPowerOf10: 2, withBehavior: WUtils.handler2)
    }
    
    func getBlockPerYear(_ chain: ChainType?) -> NSDecimalNumber {
        if let blocks_per_year = params?.minting_params?.params?.blocks_per_year {
            return NSDecimalNumber.init(string: blocks_per_year)
        }
        
        if let blocks_per_year = params?.minting_params?.blocks_per_year {
            return NSDecimalNumber.init(string: blocks_per_year)
        }
        return NSDecimalNumber.zero
    }
    
    func getSupplyDenom(_ denom: String) -> Coin?{
        return params?.supply?.filter {$0.denom == denom }.first
    }
    
    func getQuorum() -> NSDecimalNumber {
        if let rawQuorum = params?.gov_tallying?.tally_params?.quorum {
            return NSDecimalNumber.init(string: rawQuorum)
        }
        if let rawQuorum = params?.gov_tallying?.default_tally?.quorum {
            return NSDecimalNumber.init(string: rawQuorum)
        }
        return NSDecimalNumber.zero
    }
    
}

public struct Params {
    var ibc_params: IbcParams?
    var minting_params: MintingParams?
    var minting_inflation: String?
    var minting_annual_provisions: String?
    var staking_pool: StakingPool?
    var staking_params: StakingParam?
    var distribution_params: DistributionParam?
    var supply: Array<Coin>?
    var gov_tallying: GovTallying?
    var iris_tokens: Array<IrisToken>?
    
    init(_ dictionary: NSDictionary?) {
        if let rawIbcParams = dictionary?["ibc_params"] as? NSDictionary {
            self.ibc_params = IbcParams.init(rawIbcParams)
        }
        if let rawMintingParams = dictionary?["minting_params"] as? NSDictionary {
            self.minting_params = MintingParams.init(rawMintingParams)
        }
        self.minting_inflation = "0"
        if let rawMintingInflation = dictionary?["minting_inflation"] as? NSDictionary {
            self.minting_inflation = MintingInflation.init(rawMintingInflation).inflation
        }
        if let rawMintingInflation = dictionary?["minting_inflation"] as? String {
            self.minting_inflation = rawMintingInflation
        }
        if let rawMintingAnnualProvisions = dictionary?["minting_annual_provisions"] as? NSDictionary {
            self.minting_annual_provisions = MintingAnnualProvisions.init(rawMintingAnnualProvisions).annual_provisions
        }
        if let rawMintingAnnualProvisions = dictionary?["minting_annual_provisions"] as? String {
            self.minting_annual_provisions = rawMintingAnnualProvisions
        }
        if let rawStakingPool = dictionary?["staking_pool"] as? NSDictionary {
            self.staking_pool = StakingPool.init(rawStakingPool)
        }
        if let rawStakingParam = dictionary?["staking_params"] as? NSDictionary {
            self.staking_params = StakingParam.init(rawStakingParam)
        }
        if let rawDistributionParam = dictionary?["distribution_params"] as? NSDictionary {
            self.distribution_params = DistributionParam.init(rawDistributionParam)
        }
        if let rawSupply = dictionary?["supply"] as? NSDictionary {
            self.supply = SupplyList.init(rawSupply).supply
        }
        if let rawSupplys = dictionary?["supply"] as? Array<NSDictionary> {
            self.supply = Array<Coin>()
            for rawSupply in rawSupplys {
                self.supply?.append(Coin.init(rawSupply))
            }
        }
        
        if let rawGovTallying = dictionary?["gov_tallying"] as? NSDictionary {
            self.gov_tallying = GovTallying.init(rawGovTallying)
        }
        if let rawIrisTokens = dictionary?["iris_tokens"] as? Array<NSDictionary> {
            self.iris_tokens = Array<IrisToken>()
            for rawIrisToken in rawIrisTokens {
                self.iris_tokens?.append(IrisToken.init(rawIrisToken))
            }
        }
    }
}

public struct IbcParams {
    var params: Params?
    
    init(_ dictionary: NSDictionary?) {
        if let rawParams = dictionary?["params"] as? NSDictionary {
            self.params = Params.init(rawParams)
        }
    }
    
    public struct Params {
        var send_enabled: Bool?
        var receive_enabled: Bool?
        
        init(_ dictionary: NSDictionary?) {
            self.send_enabled = dictionary?["send_enabled"] as? Bool
            self.receive_enabled = dictionary?["receive_enabled"] as? Bool
        }
    }
}

public struct MintingParams {
    var params: Params?
    var inflation: String?
    var mint_denom: String?
    var goal_bonded: String?
    var blocks_per_year: String?
    var inflation_min: String?
    var inflation_max: String?
    var inflation_rate_change: String?
    
    init(_ dictionary: NSDictionary?) {
        if let rawParams = dictionary?["params"] as? NSDictionary {
            self.params = Params.init(rawParams)
        }
        self.inflation = dictionary?["inflation"] as? String
        self.mint_denom = dictionary?["mint_denom"] as? String
        self.goal_bonded = dictionary?["goal_bonded"] as? String
        self.blocks_per_year = dictionary?["blocks_per_year"] as? String
        self.inflation_min = dictionary?["inflation_min"] as? String
        self.inflation_max = dictionary?["inflation_max"] as? String
        self.inflation_rate_change = dictionary?["inflation_rate_change"] as? String
    }
    
    
    public struct Params {
        var mint_denom: String?
        var goal_bonded: String?
        var blocks_per_year: String?
        var inflation_min: String?
        var inflation_max: String?
        var inflation_rate_change: String?
        
        init(_ dictionary: NSDictionary?) {
            self.mint_denom = dictionary?["mint_denom"] as? String
            self.goal_bonded = dictionary?["goal_bonded"] as? String
            self.blocks_per_year = dictionary?["blocks_per_year"] as? String
            self.inflation_min = dictionary?["inflation_min"] as? String
            self.inflation_max = dictionary?["inflation_max"] as? String
            self.inflation_rate_change = dictionary?["inflation_rate_change"] as? String
        }
    }
}

public struct MintingInflation {
    var inflation: String?
    
    init(_ dictionary: NSDictionary?) {
        self.inflation = dictionary?["inflation"] as? String
    }
}

public struct MintingAnnualProvisions {
    var annual_provisions: String?
    
    init(_ dictionary: NSDictionary?) {
        self.annual_provisions = dictionary?["annual_provisions"] as? String
    }
}

public struct StakingPool {
    var pool: Pool?
    var bonded_tokens: String?
    var not_bonded_tokens: String?
    
    init(_ dictionary: NSDictionary?) {
        if let rawPool = dictionary?["pool"] as? NSDictionary {
            self.pool = Pool.init(rawPool)
        }
        self.bonded_tokens = dictionary?["bonded_tokens"] as? String
        self.not_bonded_tokens = dictionary?["not_bonded_tokens"] as? String
    }
    
    public struct Pool {
        var bonded_tokens: String?
        var not_bonded_tokens: String?
        
        init(_ dictionary: NSDictionary?) {
            self.bonded_tokens = dictionary?["bonded_tokens"] as? String
            self.not_bonded_tokens = dictionary?["not_bonded_tokens"] as? String
        }
    }
}

public struct StakingParam {
    var params: Param?
    var bond_denom: String?
    var max_entries: Int?
    var max_validators: Int?
    var unbonding_time: String?
    var historical_entries: Int?
    
    func getMainDenom() -> String {
        if let result = params?.bond_denom {
            return result
        }
        if let result = bond_denom {
            return result
        }
        return ""
    }
    
    func getUnbondingTime() -> NSDecimalNumber {
        if let result = params?.unbonding_time {
            return NSDecimalNumber.init(string: result.filter{ $0.isNumber })
        }
        if let result = unbonding_time {
            return NSDecimalNumber.init(string: result).multiplying(byPowerOf10: -9, withBehavior: WUtils.handler0Down)
        }
        return NSDecimalNumber.zero
    }
    
    init(_ dictionary: NSDictionary?) {
        if let rawParam = dictionary?["params"] as? NSDictionary {
            self.params = Param.init(rawParam)
        }
        self.bond_denom = dictionary?["bond_denom"] as? String
        self.max_entries = dictionary?["max_entries"] as? Int
        self.max_validators = dictionary?["max_validators"] as? Int
        self.unbonding_time = dictionary?["unbonding_time"] as? String
        self.historical_entries = dictionary?["historical_entries"] as? Int
    }
    
    public struct Param {
        var bond_denom: String?
        var max_entries: Int?
        var max_validators: Int?
        var unbonding_time: String?
        var historical_entries: Int?
        
        init(_ dictionary: NSDictionary?) {
            self.bond_denom = dictionary?["bond_denom"] as? String
            self.max_entries = dictionary?["max_entries"] as? Int
            self.max_validators = dictionary?["max_validators"] as? Int
            self.unbonding_time = dictionary?["unbonding_time"] as? String
            self.historical_entries = dictionary?["historical_entries"] as? Int
        }
    }
}

public struct DistributionParam {
    var params: Param?
    var community_tax: String?
    var base_proposer_reward: String?
    var bonus_proposer_reward: String?
    var withdraw_addr_enabled: Bool?
    
    init(_ dictionary: NSDictionary?) {
        if let rawParam = dictionary?["params"] as? NSDictionary {
            self.params = Param.init(rawParam)
        }
        self.community_tax = dictionary?["community_tax"] as? String
        self.base_proposer_reward = dictionary?["base_proposer_reward"] as? String
        self.bonus_proposer_reward = dictionary?["bonus_proposer_reward"] as? String
        self.withdraw_addr_enabled = dictionary?["withdraw_addr_enabled"] as? Bool
    }
    
    public struct Param {
        var community_tax: String?
        var base_proposer_reward: String?
        var bonus_proposer_reward: String?
        var withdraw_addr_enabled: Bool?
        
        init(_ dictionary: NSDictionary?) {
            self.community_tax = dictionary?["community_tax"] as? String
            self.base_proposer_reward = dictionary?["base_proposer_reward"] as? String
            self.bonus_proposer_reward = dictionary?["bonus_proposer_reward"] as? String
            self.withdraw_addr_enabled = dictionary?["withdraw_addr_enabled"] as? Bool
        }
    }
}

public struct SupplyList {
    var supply: Array<Coin>?
    
    init(_ dictionary: NSDictionary?) {
        if let rawSupplys = dictionary?["supply"] as? Array<NSDictionary> {
            self.supply = Array<Coin>()
            for rawSupply in rawSupplys {
                self.supply?.append(Coin.init(rawSupply))
            }
        }
    }
}

public struct GovTallying {
    var tally_params: TallyParams?
    var default_tally: DefaultTally?
    var veto: String?
    var quorum: String?
    var threshold: String?
    
    init(_ dictionary: NSDictionary?) {
        if let rawTallyParams = dictionary?["tally_params"] as? NSDictionary {
            self.tally_params = TallyParams.init(rawTallyParams)
        }
        if let rawDefaultTally = dictionary?["DefaultTally"] as? NSDictionary {
            self.default_tally = DefaultTally.init(rawDefaultTally)
        }
        self.veto = dictionary?["veto"] as? String
        self.quorum = dictionary?["quorum"] as? String
        self.threshold = dictionary?["threshold"] as? String
    }
    
    public struct TallyParams {
        var quorum: String?
        var threshold: String?
        var veto_threshold: String?
        
        init(_ dictionary: NSDictionary?) {
            self.quorum = dictionary?["quorum"] as? String
            self.threshold = dictionary?["threshold"] as? String
            self.veto_threshold = dictionary?["veto_threshold"] as? String
        }
    }
    
    public struct DefaultTally {
        var veto: String?
        var quorum: String?
        var threshold: String?
        
        init(_ dictionary: NSDictionary?) {
            self.veto = dictionary?["veto"] as? String
            self.quorum = dictionary?["quorum"] as? String
            self.threshold = dictionary?["threshold"] as? String
        }
    }
}

public struct IrisToken {
    var type: String?
    var value: IrisTokenValue?
    
    init(_ dictionary: NSDictionary?) {
        self.type = dictionary?["type"] as? String
        if let rawValue = dictionary?["value"] as? NSDictionary {
            self.value = IrisTokenValue.init(rawValue)
        }
    }
    
    public struct IrisTokenValue {
        var name: String?
        var owner: String?
        var scale: String?
        var symbol: String?
        var min_unit: String?
        var mintable: Bool?
        var max_supply: String?
        var initial_supply: String?
        
        init(_ dictionary: NSDictionary?) {
            self.name = dictionary?["name"] as? String
            self.owner = dictionary?["owner"] as? String
            self.scale = dictionary?["scale"] as? String
            self.symbol = dictionary?["symbol"] as? String
            self.min_unit = dictionary?["min_unit"] as? String
            self.mintable = dictionary?["mintable"] as? Bool
            self.max_supply = dictionary?["max_supply"] as? String
            self.initial_supply = dictionary?["initial_supply"] as? String
        }
    }
}
