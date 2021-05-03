//
//  BaseNetWork.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation
import GRPC
import NIO


class BaseNetWork {
    
    static func nodeInfoUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/node-info"
        } else if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "node_info"
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "node_info"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "node_info"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "node_info"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "node_info"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "node_info"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "node_info"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "node_info"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "node_info"
        }
        
        else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/node-info"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "node_info"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "node_info"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "node_info"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "node_info"
        }
        return ""
    }
    
    static func validatorsUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "staking/validators"
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/validators"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/validators"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/validators"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/validators"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/validators"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/validators"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/validators"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/validators"
        }
        
        else if (chain == ChainType.OKEX_TEST) {
            return OKEX_URL + "staking/validators"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/validators"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/validators"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/validators"
        }
        return ""
    }
    
    static func validatorUrl(_ chain: ChainType?, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/validators" + "/" + opAddress
        }
        
        if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/validators" + "/" + opAddress
        }
        return ""
    }
    
    static func accountUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/account/" + address
        } else if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "auth/accounts/" + address
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "auth/accounts/" + address
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "auth/accounts/" + address
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "auth/accounts/" + address
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "auth/accounts/" + address
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "auth/accounts/" + address
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "auth/accounts/" + address
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "auth/accounts/" + address
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "auth/accounts/" + address
        }
        
        else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/account/" + address
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "auth/accounts/" + address
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "auth/accounts/" + address
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "auth/accounts/" + address
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "auth/accounts/" + address
        }
        return ""
    }
    
    static func bondingsUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/delegators/" + address + "/delegations"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/delegations"
        }
        return ""
    }
    
    static func bondingUrl(_ chain: ChainType?, _ address: String, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "delegations/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        }
        return ""
    }
    
    static func unbondingsUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/delegators/" + address + "/unbonding_delegations"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        }
        return ""
    }
    
    static func unbondingUrl(_ chain: ChainType?, _ address: String, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        }
        return ""
    }
    
    static func redelegationsUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/redelegations"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/redelegations"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/redelegations"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/redelegations"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/redelegations"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/redelegations"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/redelegations"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/redelegations"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/redelegations"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/redelegations"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/redelegations"
        }
        return ""
    }
    
    static func rewardsUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "distribution/delegators/" + address + "/rewards"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "distribution/delegators/" + address + "/rewards"
        }
        return ""
    }
    
    static func rewardUrl(_ chain: ChainType?, _ address: String, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        }
        return ""
    }
    
    static func rewardAddressUrl(_ chain: ChainType?, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "distribution/delegators/" + address + "/withdraw_address"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "distribution/delegators/" + address + "/withdraw_address"
        }
        return ""
    }
    
    static func paramMintUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "minting/parameters"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "minting/parameters"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "minting/parameters"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "minting/parameters"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "minting/parameters"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "minting/parameters"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "minting/parameters"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "minting/parameters"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "minting/parameters"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "minting/parameters"
        }
        return ""
    }
    
    static func inflationUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "minting/inflation"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "minting/inflation"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "minting/inflation"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "minting/inflation"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "minting/inflation"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "minting/inflation"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "minting/inflation"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "minting/inflation"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "minting/inflation"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "minting/inflation"
        }
        return ""
    }
    
    static func provisionUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "minting/annual-provisions"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "minting/annual-provisions"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "minting/annual-provisions"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "minting/annual-provisions"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "minting/annual-provisions"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "minting/annual-provisions"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "minting/annual-provisions"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "minting/annual-provisions"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "minting/annual-provisions"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "minting/annual-provisions"
        }
        return ""
    }
    
    static func stakingPoolUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/pool"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/pool"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "staking/pool"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "staking/pool"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/pool"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "staking/pool"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "staking/pool"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "staking/pool"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/pool"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "staking/pool"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/pool"
        }
        return ""
    }
    
    static func proposalsUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "gov/proposals"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "gov/proposals"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "gov/proposals"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "gov/proposals"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "gov/proposals"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "gov/proposals"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals"
        }
        return ""
    }
    
    static func proposalUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "gov/proposals" + "/" + id
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id
        }
        return ""
    }
    
    static func tallyUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "gov/proposals" + "/" + id + "/tally"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        }
        return ""
    }
    
    static func voteUrl(_ chain: ChainType?, _ id: String, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" +  "/" + id + "/votes/" + address
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "gov/proposals" + "/" + id + "/votes/" + address
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        }
        return ""
    }
    
    static func proposerUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "gov/proposals" + "/" + id + "/proposer"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/proposer"
        }
        return ""
    }
    
    static func votesUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.IOV_MAIN) {
            return IOV_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CERTIK_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_URL + "gov/proposals" + "/" + id + "/votes"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/votes"
        }
        return ""
    }
    
    
    //for Binance
    static func bnbTokenUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.BINANCE_MAIN ) {
            return BNB_URL + "api/v1/tokens"
        } else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/tokens"
        }
        return ""
    }
    
    static func bnbMiniTokenUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.BINANCE_MAIN ) {
            return BNB_URL + "api/v1/mini/tokens"
        } else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/mini/tokens"
        }
        return ""
    }
    
    
    //for Kava
    static func paramPriceFeedUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "pricefeed/parameters"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "pricefeed/parameters"
        }
        return ""
    }
    
    static func priceFeedUrl(_ chain: ChainType, _ market: String) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "pricefeed/price/" + market
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "pricefeed/price/" + market
        }
        return ""
    }
    
    static func paramIncentiveUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "incentive/parameters"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "incentive/parameters"
        }
        return ""
    }
    
    
    //for Okex
    static func balanceOkUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.OKEX_MAIN ) {
            return OKEX_URL + "accounts/" + address
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "accounts/" + address
        }
        return ""
    }
    
    static func stakingOkUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.OKEX_MAIN ) {
            return OKEX_URL + "staking/delegators/" + address
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "staking/delegators/" + address
        }
        return ""
    }
    
    static func unbondingOkUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.OKEX_MAIN ) {
            return OKEX_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        }
        return ""
    }
    
    static func tokenListOkUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.OKEX_MAIN ) {
            return OKEX_URL + "tokens"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "tokens"
        }
        return ""
    }
    
    static func tickerListOkUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.OKEX_MAIN ) {
            return OKEX_URL + "tickers"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "tickers"
        }
        return ""
    }
    
    
    //starname
    static func feesStarnameUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.IOV_MAIN ) {
            return IOV_URL + "configuration/query/fees"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "configuration/query/fees"
        }
        return ""
    }
    
    static func configStarnameUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.IOV_MAIN ) {
            return IOV_URL + "configuration/query/configuration"
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_URL + "configuration/query/configuration"
        }
        return ""
    }
    
    
    //band
    static func oracleBandUrl() -> String {
        return BAND_URL + "oracle/active_validators"
    }

    
    
    
    
    static func accountHistory(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_MAIN) {
            result = COSMOS_MAIN_HISTORY + address
        } else if (chain == ChainType.IRIS_MAIN) {
            result = IRIS_MAIN_HISTORY + address
        } else if (chain == ChainType.AKASH_MAIN) {
            result = AKASH_MAIN_HISTORY + address
        } else if (chain == ChainType.PERSIS_MAIN) {
            result = PERSIS_MAIN_HISTORY + address
        } else if (chain == ChainType.CRYPTO_MAIN) {
            result = CRYTO_MAIN_HISTORY + address
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_HISTORY + address
        } else if (chain == ChainType.IRIS_TEST) {
            result = IRIS_TEST_HISTORY + address
        }
        return result
    }
    
    static func accountStakingHistory(_ chain: ChainType, _ address: String, _ valAddress: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_MAIN) {
            result = COSMOS_MAIN_HISTORY + address + "/" + valAddress
        } else if (chain == ChainType.IRIS_MAIN) {
            result = IRIS_MAIN_HISTORY + address + "/" + valAddress
        } else if (chain == ChainType.AKASH_MAIN) {
            result = AKASH_MAIN_HISTORY + address + "/" + valAddress
        } else if (chain == ChainType.PERSIS_MAIN) {
            result = PERSIS_MAIN_HISTORY + address + "/" + valAddress
        } else if (chain == ChainType.CRYPTO_MAIN) {
            result = CRYTO_MAIN_HISTORY + address + "/" + valAddress
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_HISTORY + address + "/" + valAddress
        } else if (chain == ChainType.IRIS_TEST) {
            result = IRIS_TEST_HISTORY + address + "/" + valAddress
        }
        return result
    }
    
    
    
    
    
    
    
    static func getConnection(_ chain: ChainType, _ group: MultiThreadedEventLoopGroup) -> ClientConnection? {
        if (chain == ChainType.COSMOS_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-cosmos-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.IRIS_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-iris-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.AKASH_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-akash-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-persistence-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-cryptocom.cosmostation.io", port: 9090)
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.IRIS_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 9095)
            
        }
        return nil
    }
    
    static func getCallOptions() -> CallOptions {
        var callOptions = CallOptions()
        callOptions.timeLimit = TimeLimit.timeout(TimeAmount.milliseconds(8000))
        return callOptions
    }
    
}

