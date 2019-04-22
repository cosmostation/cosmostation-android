//
//  MsgGenerator.swift
//  Cosmostation
//
//  Created by yongjoo on 09/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

class MsgGenerator {
    
//    static func genDelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: Coin) -> Msg {
//        var msg = Msg.init()
//
//        var value = Msg.Value.init()
//        value.delegator_address = fromAddress
//        value.validator_address = toValAddress
//        value.value = amount
//
//        msg.type = COSMOS_MSG_TYPE_DELEGATE
//        msg.value = value
//
//        return msg
//    }
    
    static func genDelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: Coin) -> StakeMsg {
        var msg = StakeMsg.init()
        
        var value = StakeMsg.Value.init()
        value.delegator_address = fromAddress
        value.validator_address = toValAddress
        value.amount = amount
        
        msg.type = COSMOS_MSG_TYPE_DELEGATE
        msg.value = value
        
        return msg
    }
    
//    static func genUndelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: String) -> Msg {
//        var msg = Msg.init()
//
//        var value = Msg.Value.init()
//        value.delegator_address = fromAddress
//        value.validator_address = toValAddress
//        value.shares_amount = amount
//
//        msg.type = COSMOS_MSG_TYPE_UNDELEGATE2
//        msg.value = value
//
//        return msg
//    }
    
    static func genUndelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: Coin) -> StakeMsg {
        var msg = StakeMsg.init()
        
        var value = StakeMsg.Value.init()
        value.delegator_address = fromAddress
        value.validator_address = toValAddress
        value.amount = amount
        
        msg.type = COSMOS_MSG_TYPE_UNDELEGATE2
        msg.value = value
        
        return msg
    }
    
    
    static func genGetRewardMsg(_ fromAddress: String, _ toValAddress: String) -> Msg {
        var msg = Msg.init()
        
        var value = Msg.Value.init()
        value.delegator_address = fromAddress
        value.validator_address = toValAddress
        
        msg.type = COSMOS_MSG_TYPE_WITHDRAW_DEL
        msg.value = value
        
        return msg
    }
    
    static func genGetSendMsg(_ fromAddress: String, _ toAddress: String, _ amount: Array<Coin>) -> Msg {
        
        var msg = Msg.init()
        
        var value = Msg.Value.init()
        value.from_address = fromAddress
        value.to_address = toAddress
        value.amount = amount
        
        msg.type = COSMOS_MSG_TYPE_TRANSFER2
        msg.value = value
        
        return msg
    }
    
    
    
    
    
    static func genSignedTx(_ msgs: Array<Msg>, _ fee: Fee, _ memo: String, _ signatures: Array<Signature>) -> StdTx {
        var stdTx = StdTx.init()
        
        var value = StdTx.Value.init()
        value.msg = msgs
        value.fee = fee
        value.signatures = signatures
        value.memo = memo
        
        stdTx.type = COSMOS_AUTH_TYPE_STDTX
        stdTx.value = value
        
        return stdTx
    }
    
    static func genSignedTx(_ msgs: Array<StakeMsg>, _ fee: Fee, _ memo: String, _ signatures: Array<Signature>) -> StakeStdTx {
        var stdTx = StakeStdTx.init()
        
        var value = StakeStdTx.Value.init()
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
    
    
    static func getToSignMsg(_ chain: String, _ accountNum: String, _ sequenceNum: String, _ msgs: Array<StakeMsg>, _ fee: Fee, _ memo: String) -> StakeStdSignedMsg {
        var stdSignedMsg = StakeStdSignedMsg.init()
        
        stdSignedMsg.chain_id = chain
        stdSignedMsg.account_number = accountNum
        stdSignedMsg.sequence = sequenceNum
        stdSignedMsg.msgs = msgs
        stdSignedMsg.fee = fee
        stdSignedMsg.memo = memo
        
        return stdSignedMsg
    }
}
