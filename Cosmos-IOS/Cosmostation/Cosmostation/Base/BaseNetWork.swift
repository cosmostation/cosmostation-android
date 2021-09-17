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
    
    static func nodeInfoUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/node-info"
        } else if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "node_info"
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "node_info"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "node_info"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "node_info"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "node_info"
        }
        
        else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/node-info"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "node_info"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "node_info"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "node_info"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "node_info"
        }
        return ""
    }
    
    static func accountInfoUrl(_ chain: ChainType?, _ address: String) -> String {
        if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/account/" + address
        } else if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "auth/accounts/" + address
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "auth/accounts/" + address
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "auth/accounts/" + address
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "auth/accounts/" + address
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "auth/accounts/" + address
        }
        
        else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/account/" + address
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "auth/accounts/" + address
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "auth/accounts/" + address
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "auth/accounts/" + address
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "auth/accounts/" + address
        }
        return ""
    }
    
    static func validatorsUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "staking/validators"
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/validators"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/validators"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/validators"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/validators"
        }
        
        else if (chain == ChainType.OKEX_TEST) {
            return OKEX_URL + "staking/validators"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/validators"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/validators"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/validators"
        }
        return ""
    }
    
    static func validatorUrl(_ chain: ChainType?, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/validators" + "/" + opAddress
        }
        
        if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/validators" + "/" + opAddress
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/validators" + "/" + opAddress
        }
        return ""
    }
    
    static func bondingsUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/delegators/" + address + "/delegations"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/delegations"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/delegators/" + address + "/delegations"
        }
        return ""
    }
    
    static func bondingUrl(_ chain: ChainType?, _ address: String, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/delegators/" + address + "/delegations/" + opAddress
        }
        return ""
    }
    
    static func unbondingsUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/delegators/" + address + "/unbonding_delegations"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations"
        }
        return ""
    }
    
    static func unbondingUrl(_ chain: ChainType?, _ address: String, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/delegators/" + address + "/unbonding_delegations/" + opAddress
        }
        return ""
    }
    
    static func redelegationsUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "staking/redelegations"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "staking/redelegations"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "staking/redelegations"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "staking/redelegations"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "staking/redelegations"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "staking/redelegations"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "staking/redelegations"
        }
        return ""
    }
    
    static func rewardsUrl(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "distribution/delegators/" + address + "/rewards"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "distribution/delegators/" + address + "/rewards"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "distribution/delegators/" + address + "/rewards"
        }
        return ""
    }
    
    static func rewardUrl(_ chain: ChainType?, _ address: String, _ opAddress: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "distribution/delegators/" + address + "/rewards/" + opAddress
        }
        return ""
    }
    
    static func rewardAddressUrl(_ chain: ChainType?, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "distribution/delegators/" + address + "/withdraw_address"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "distribution/delegators/" + address + "/withdraw_address"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "distribution/delegators/" + address + "/withdraw_address"
        }
        return ""
    }
    
    static func proposalsUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "gov/proposals"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "gov/proposals"
        }
        return ""
    }
    
    static func proposalUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "gov/proposals" + "/" + id
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "gov/proposals" + "/" + id
        }
        return ""
    }
    
    static func tallyUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "gov/proposals" + "/" + id + "/tally"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "gov/proposals" + "/" + id + "/tally"
        }
        return ""
    }
    
    static func voteUrl(_ chain: ChainType?, _ id: String, _ address: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" +  "/" + id + "/votes/" + address
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "gov/proposals" + "/" + id + "/votes/" + address
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/votes/" + address
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "gov/proposals" + "/" + id + "/votes/" + address
        }
        return ""
    }
    
    static func proposerUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "gov/proposals" + "/" + id + "/proposer"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/proposer"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "gov/proposals" + "/" + id + "/proposer"
        }
        return ""
    }
    
    static func votesUrl(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "gov/proposals" + "/" + id + "/votes"
        }
        
        else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "gov/proposals" + "/" + id + "/votes"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "gov/proposals" + "/" + id + "/votes"
        }
        return ""
    }
    
    
    static func txUrl(_ chain: ChainType?, _ txhash: String) -> String {
        if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/tx/" + txhash
        } else if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "txs/" + txhash
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "txs/" + txhash
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "txs/" + txhash
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "txs/" + txhash
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "txs/" + txhash
        }
        
        else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/tx/" + txhash
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "txs/" + txhash
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "txs/" + txhash
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "txs/" + txhash
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "txs/" + txhash
        }
        return ""
    }
    
    static func broadcastUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/broadcast"
        } else if (chain == ChainType.OKEX_MAIN) {
            return OKEX_URL + "txs"
        } else if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "txs"
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_URL + "txs"
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_URL + "txs"
        } else if (chain == ChainType.KI_MAIN) {
            return KI_URL + "txs"
        }
        
        else if (chain == ChainType.BINANCE_TEST) {
            return BNB_URL + "api/v1/broadcast"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "txs"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "txs"
        } else if (chain == ChainType.CERTIK_TEST) {
            return CERTIK_TEST_URL + "txs"
        } else if (chain == ChainType.MEDI_TEST) {
            return MEDI_TEST_URL + "txs"
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
    
    static func bnbTicUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.BINANCE_MAIN ) {
            return BNB_URL + "api/v1/ticker/24hr"
        } else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/ticker/24hr"
        }
        return ""
    }
    
    static func bnbMiniTicUrl(_ chain: ChainType) -> String {
        if (chain == ChainType.BINANCE_MAIN ) {
            return BNB_URL + "api/v1/mini/ticker/24hr"
        } else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/mini/ticker/24hr"
        }
        return ""
    }
    
    static func bnbHistoryUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.BINANCE_MAIN ) {
            return BNB_URL + "api/v1/transactions"
        } else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/transactions"
        }
        return ""
    }
    
    
    //for Kava
    static func paramPriceFeedUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "pricefeed/parameters"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "pricefeed/parameters"
        }
        return ""
    }
    
    static func priceFeedUrl(_ chain: ChainType?, _ market: String) -> String {
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
    
    static func paramCdpUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "cdp/parameters"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "cdp/parameters"
        }
        return ""
    }
    
    static func owenCdpUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "cdp/cdps"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "cdp/cdps"
        }
        return ""
    }
    
    static func depositCdpUrl(_ chain: ChainType?, _ address: String, _ collateralType: String) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "cdp/cdps/cdp/deposits/" + address + "/" + collateralType
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "cdp/cdps/cdp/deposits/" + address + "/" + collateralType
        }
        return ""
    }
    
    static func paramHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/parameters"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/parameters"
        }
        return ""
    }
    
    static func interestRateHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/interest-rate"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/interest-rate"
        }
        return ""
    }
    
    static func totalDepositHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/total-deposited"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/total-deposited"
        }
        return ""
    }
    
    static func totalBorrowHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/total-borrowed"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/total-borrowed"
        }
        return ""
    }
    
    static func depositHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/deposits"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/deposits"
        }
        return ""
    }
    
    static func borrowHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/borrows"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/borrows"
        }
        return ""
    }
    
    static func reservesHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/reserves"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/reserves"
        }
        return ""
    }
    
    static func managerHardPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "hard/accounts"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "hard/accounts"
        }
        return ""
    }
    
    static func incentiveUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "incentive/rewards"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "incentive/rewards"
        }
        return ""
    }
    
    static func paramSwapPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "swap/parameters"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "swap/parameters"
        }
        return ""
    }
    
    static func listSwapPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "swap/pools"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "swap/pools"
        }
        return ""
    }
    
    static func swapPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "swap/pool"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "swap/pool"
        }
        return ""
    }
    
    static func depositSwapPoolUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN ) {
            return KAVA_URL + "swap/deposits"
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "swap/deposits"
        }
        return ""
    }
    
    static func paramBep3Url(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN || chain == ChainType.BINANCE_MAIN) {
            return KAVA_URL + "bep3/parameters"
        } else if (chain == ChainType.KAVA_TEST || chain == ChainType.BINANCE_TEST) {
            return KAVA_TEST_URL + "bep3/parameters"
        }
        return ""
    }
    
    static func supplyBep3Url(_ chain: ChainType?) -> String {
        if (chain == ChainType.KAVA_MAIN || chain == ChainType.BINANCE_MAIN) {
            return KAVA_URL + "bep3/supplies"
        } else if (chain == ChainType.KAVA_TEST || chain == ChainType.BINANCE_TEST) {
            return KAVA_TEST_URL + "bep3/supplies"
        }
        return ""
    }
    
    static func swapIdBep3Url(_ chain: ChainType?, _ id: String) -> String {
        if (chain == ChainType.KAVA_MAIN) {
            return KAVA_URL + "bep3/swap/" + id
        } else if (chain == ChainType.KAVA_TEST) {
            return KAVA_TEST_URL + "bep3/swap/" + id
        } else if (chain == ChainType.BINANCE_MAIN) {
            return BNB_URL + "api/v1/atomic-swaps/" + id
        } else if (chain == ChainType.BINANCE_TEST) {
            return BNB_TEST_URL + "api/v1/atomic-swaps/" + id
        }
        return ""
    }
    
    
    //for Okex
    static func balanceOkUrl(_ chain: ChainType?, _ address: String) -> String {
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
    
    static func historyOkUrl(_ chain: ChainType?) -> String {
        if (chain == ChainType.OKEX_MAIN ) {
            return OKEX_URL + "transactions"
        } else if (chain == ChainType.OKEX_TEST) {
            return OKEX_TEST_URL + "transactions"
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
    
    //band
    static func oracleBandUrl() -> String {
        return BAND_URL + "oracle/active_validators"
    }
    
    //sif
    static func vsIncentiveUrl(_ address: String) -> String {
        return SIF_FINANCE_API + "api/vs?key=userData&address=" + address + "&timestamp=now"
    }
    
    static func lmIncentiveUrl(_ address: String) -> String {
        return SIF_FINANCE_API + "api/lm?key=userData&address=" + address + "&timestamp=now"
    }

    
    //rizon
    static func rizonSwapStatus(_ chain: ChainType?, _ address: String) -> String {
//        if (chain == ChainType.RIZON_MAIN) {
//            return RIZON_SWAP_STATUS_MAINNET + "swaps/rizon/" + address
//        } else
        if (chain == ChainType.RIZON_TEST) {
            return RIZON_SWAP_STATUS_TESTNET + "swaps/rizon/" + address
        }
        return ""
    }
    
    
    //hdac
    static func hdacTxDetail(_ chain: ChainType?, _ hash: String) -> String {
//        if (chain == ChainType.RIZON_MAIN) {
//            return HDAC_MAINNET + "tx/" + hash
//        } else
        if (chain == ChainType.RIZON_TEST) {
            return HDAC_TESTNET + "tx/" + hash
        }
        return ""
    }
    
    static func hdacBalance(_ chain: ChainType?, _ address: String) -> String {
        if (chain == ChainType.RIZON_TEST) {
            return HDAC_TESTNET + "addr/" + address + "/utxo"
        }
        return ""
    }
    
    static func hdacBroadcast(_ chain: ChainType?) -> String {
        if (chain == ChainType.RIZON_TEST) {
            return HDAC_TESTNET + "tx/send"
        }
        return ""
    }
    
    
    
    static func accountHistory(_ chain: ChainType, _ address: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_MAIN) {
            result = COSMOS_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.IRIS_MAIN) {
            result = IRIS_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.AKASH_MAIN) {
            result = AKASH_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.PERSIS_MAIN) {
            result = PERSIS_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.CRYPTO_MAIN) {
            result = CRYTO_API + "v1/account/txs/" + address
        } else if (chain == ChainType.OSMOSIS_MAIN) {
            result = OSMOSIS_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.BAND_MAIN) {
            result = BAND_API + "v1/account/txs/" + address
        } else if (chain == ChainType.IOV_MAIN) {
            result = IOV_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.SIF_MAIN) {
            result = SIF_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.MEDI_MAIN) {
            result = MEDI_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.CERTIK_MAIN) {
            result = CERTIK_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.EMONEY_MAIN) {
            result = EMONEY_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.FETCH_MAIN) {
            result = FETCH_API + "v1/account/new_txs/" + address
        }
        
        else if (chain == ChainType.KAVA_MAIN) {
            result = KAVA_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.SENTINEL_MAIN) {
            result = SENTINEL_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.KI_MAIN) {
            result = KI_API + "v1/account/txs/" + address
        }
        
        
        else if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.IRIS_TEST) {
            result = IRIS_TEST_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.RIZON_TEST) {
            result = RIZON_TEST_API + "v1/account/new_txs/" + address
        }
        else if (chain == ChainType.KAVA_TEST) {
            result = KAVA_TEST_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.CERTIK_TEST) {
            result = CERTIK_TEST_API + "v1/account/txs/" + address
        } else if (chain == ChainType.MEDI_TEST) {
            result = MEDI_TEST_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.ALTHEA_TEST) {
            result = ALTHEA_TEST_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.UMEE_TEST) {
            result = UMEE_TEST_API + "v1/account/new_txs/" + address
        } else if (chain == ChainType.AXELAR_TEST) {
            result = AXELAR_TEST_API + "v1/account/new_txs/" + address
        }
        return result
    }
    
    static func accountStakingHistory(_ chain: ChainType, _ address: String, _ valAddress: String) -> String {
        var result = ""
        if (chain == ChainType.COSMOS_MAIN) {
            result = COSMOS_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.IRIS_MAIN) {
            result = IRIS_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.AKASH_MAIN) {
            result = AKASH_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.PERSIS_MAIN) {
            result = PERSIS_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.CRYPTO_MAIN) {
            result = CRYTO_API + "v1/account/txs/" + address + "/" + valAddress
        } else if (chain == ChainType.OSMOSIS_MAIN) {
            result = OSMOSIS_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.BAND_MAIN) {
            result = BAND_API + "v1/account/txs/" + address + "/" + valAddress
        } else if (chain == ChainType.IOV_MAIN) {
            result = IOV_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.SIF_MAIN) {
            result = SIF_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.MEDI_MAIN) {
            result = MEDI_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.CERTIK_MAIN) {
            result = CERTIK_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.EMONEY_MAIN) {
            result = EMONEY_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.FETCH_MAIN) {
            result = FETCH_API + "v1/account/new_txs/" + address + "/" + valAddress
        }
        
        else if (chain == ChainType.KAVA_MAIN) {
            result = KAVA_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.SENTINEL_MAIN) {
            result = SENTINEL_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.KI_MAIN) {
            result = KI_API + "v1/account/txs/" + address + "/" + valAddress
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            result = COSMOS_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.IRIS_TEST) {
            result = IRIS_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.RIZON_TEST) {
            result = RIZON_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        }
        else if (chain == ChainType.KAVA_TEST) {
            result = KAVA_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.CERTIK_TEST) {
            result = CERTIK_TEST_API + "v1/account/txs/" + address + "/" + valAddress
        } else if (chain == ChainType.MEDI_TEST) {
            result = MEDI_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.ALTHEA_TEST) {
            result = ALTHEA_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.UMEE_TEST) {
            result = UMEE_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        } else if (chain == ChainType.AXELAR_TEST) {
            result = AXELAR_TEST_API + "v1/account/new_txs/" + address + "/" + valAddress
        }
        return result
    }
    
    
    
    static func getPriceList() -> String {
        return STATION_URL + "v1/market/prices"
    }
    
    static func getPrices() -> String {
        return STATION_URL + "v1/market/prices"
    }
    
    static func getPrice(_ denoms: String) -> String {
        return STATION_URL + "v1/market/price?id=" + denoms
    }
    
    static func getParams(_ chainId: String) -> String {
        return STATION_URL + "v1/params/" + chainId
    }
    
    static func getIbcPaths(_ chainId: String) -> String {
        return STATION_URL + "v1/ibc/paths/" + chainId
    }
    
    static func getIbcTokens(_ chainId: String) -> String {
        return STATION_URL + "v1/ibc/tokens/" + chainId
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
            return ClientConnection.insecure(group: group).connect(host: "lcd-cryptocom-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-sentinel-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.OSMOSIS_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-osmosis-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.IOV_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-iov-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.BAND_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 21700)
            
        } else if (chain == ChainType.SIF_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-sifchain-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.MEDI_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-medibloc-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.CERTIK_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-certik-app.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.EMONEY_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 40702)
//            return ClientConnection.insecure(group: group).connect(host: "lcd-emoney.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.FETCH_MAIN) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-fetchai-app.cosmostation.io", port: 9090)
        }
        
        
        
        else if (chain == ChainType.COSMOS_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 10000)
            
        } else if (chain == ChainType.IRIS_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 9095)
            
        } else if (chain == ChainType.RIZON_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-rizon-testnet.cosmostation.io", port: 9090)
            
        } else if (chain == ChainType.ALTHEA_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 20100)
            
        } else if (chain == ChainType.UMEE_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 40800)
            
        } else if (chain == ChainType.AXELAR_TEST) {
            return ClientConnection.insecure(group: group).connect(host: "lcd-office.cosmostation.io", port: 40600)
            
        }
        return nil
    }
    
    static func getCallOptions() -> CallOptions {
        var callOptions = CallOptions()
        callOptions.timeLimit = TimeLimit.timeout(TimeAmount.milliseconds(8000))
        return callOptions
    }
    
}

