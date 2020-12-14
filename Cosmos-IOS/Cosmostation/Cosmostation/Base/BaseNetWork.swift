//
//  BaseNetWork.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation


class BaseNetWork {
    
    static func validatorUrl(_ chain: ChainType) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_VALIDATORS
        }
        return result
    }
    
    static func delegationUrl(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_DELEGATIONS + address
        }
        return result
    }
    
    static func undelegationUrl(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_UNDELEGATIONS + address + COSMOS_TEST_UNDELEGATIONS_T
        }
        return result
    }
    
    static func balanceUrl(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_BALANCE + address
        }
        return result
    }
    
    static func authUrl(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_AUTH + address
        }
        return result
    }
    
    static func rewardAddressUrl(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_REWARD_ADDRESS + address + COSMOS_TEST_REWARD_ADDRESS_T
        }
        return result
    }
    
    static func rewardsUrl(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_REWARDS + address + COSMOS_TEST_REWARDS_T
        }
        return result
    }
    
    static func mintParamUrl(_ chain: ChainType) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_MINT_PARAM
        }
        return result
    }
    
    static func inflationUrl(_ chain: ChainType) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_INFLATION
        }
        return result
    }
    
    static func provisionUrl(_ chain: ChainType) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_PROVISIONS
        }
        return result
    }
    
    static func stakingPoolUrl(_ chain: ChainType) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_STAKING_POOL
        }
        return result
    }
    
    static func singleValidatorUrl(_ chain: ChainType, _ address: String, _ opAddress: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_SINGLE_VALIDATOR + address + COSMOS_TEST_SINGLE_VALIDATOR_M + opAddress
        }
        return result
    }
    
    static func singleDelegationUrl(_ chain: ChainType, _ address: String, _ opAddress: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_SINGLE_DELEGATION + opAddress + COSMOS_TEST_SINGLE_DELEGATION_M + address
        }
        return result
    }
    
    static func postTxUrl(_ chain: ChainType) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_BORAD_TX
        }
        return result
    }
    
    
}
