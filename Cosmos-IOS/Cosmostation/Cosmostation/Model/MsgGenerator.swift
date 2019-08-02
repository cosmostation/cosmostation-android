//
//  MsgGenerator.swift
//  Cosmostation
//
//  Created by yongjoo on 09/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

class MsgGenerator {
    
    static func genDelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: Coin, _ chain: ChainType) -> Msg {
        var msg = Msg.init()
        var value = Msg.Value.init()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            value.delegator_address = fromAddress
            value.validator_address = toValAddress
            let data = try? JSONEncoder().encode(amount)
            do {
                value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
            } catch {
                print(error)
            }
            
            msg.type = COSMOS_MSG_TYPE_DELEGATE
            msg.value = value
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            value.delegator_addr = fromAddress
            value.validator_addr = toValAddress
            value.delegation = amount
            
            msg.type = IRIS_MSG_TYPE_DELEGATE
            msg.value = value
        }
        return msg
    }
    
    static func genUndelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: Coin, _ chain: ChainType) -> Msg {
        var msg = Msg.init()
        var value = Msg.Value.init()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            value.delegator_address = fromAddress
            value.validator_address = toValAddress
            let data = try? JSONEncoder().encode(amount)
            do {
                value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
            } catch {
                print(error)
            }
            
            msg.type = COSMOS_MSG_TYPE_UNDELEGATE2
            msg.value = value
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            
        }
        return msg
    }
    
    static func genGetRewardMsg(_ fromAddress: String, _ toValAddress: String, _ chain: ChainType) -> Msg {
        var msg = Msg.init()
        var value = Msg.Value.init()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            value.delegator_address = fromAddress
            value.validator_address = toValAddress
            
            msg.type = COSMOS_MSG_TYPE_WITHDRAW_DEL
            msg.value = value
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            
        }
        return msg
    }
    
    static func genGetSendMsg(_ fromAddress: String, _ toAddress: String, _ amount: Array<Coin>, _ chain: ChainType) -> Msg {
        var msg = Msg.init()
        var value = Msg.Value.init()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            value.from_address = fromAddress
            value.to_address = toAddress
            let data = try? JSONEncoder().encode(amount)
            do {
                value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
            } catch {
                print(error)
            }
            
            msg.type = COSMOS_MSG_TYPE_TRANSFER2
            msg.value = value
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            
        }
        return msg
    }
    
    static func genGetRedelegateMsg(_ address: String, _ fromValAddress: String, _ toValAddress: String, _ amount: Coin, _ chain: ChainType) -> Msg {
        var msg = Msg.init()
        var value = Msg.Value.init()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            value.delegator_address = address
            value.validator_src_address = fromValAddress
            value.validator_dst_address = toValAddress
            let data = try? JSONEncoder().encode(amount)
            do {
                value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
            } catch {
                print(error)
            }
            
            msg.type = COSMOS_MSG_TYPE_REDELEGATE2
            msg.value = value
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            
        }
        return msg
    }
    
    static func genGetModifyRewardAddressMsg(_ requestAddress: String, _ newRewardAddress: String) -> Msg {
        var msg = Msg.init()
        
        var value = Msg.Value.init()
        value.delegator_address = requestAddress
        value.withdraw_address = newRewardAddress
        
        msg.type = COSMOS_MSG_TYPE_WITHDRAW_MIDIFY
        msg.value = value
        
        return msg
    }
    
    static func genSignedTx(_ msgs: Array<Msg>, _ fee: Fee, _ memo: String, _ signatures: Array<Signature>) -> StdTx {
        let stdTx = StdTx.init()
        let value = StdTx.Value.init()
        
        value.msg = msgs
        value.fee = fee
        value.signatures = signatures
        value.memo = memo
        
        stdTx.type = COSMOS_AUTH_TYPE_STDTX
        stdTx.value = value
        
        return stdTx
    }
    
    static func getToSignMsg(_ chain: String, _ accountNum: String, _ sequenceNum: String, _ msgs: Array<Msg>, _ fee: Fee, _ memo: String) -> StdSignedMsg {
        var stdSignedMsg = StdSignedMsg.init()
        
        stdSignedMsg.chain_id = chain
        stdSignedMsg.account_number = accountNum
        stdSignedMsg.sequence = sequenceNum
        stdSignedMsg.msgs = msgs
        stdSignedMsg.fee = fee
        stdSignedMsg.memo = memo
        
        return stdSignedMsg
    }
}
