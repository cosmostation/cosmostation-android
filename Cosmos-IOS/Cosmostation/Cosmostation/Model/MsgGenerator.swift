//
//  MsgGenerator.swift
//  Cosmostation
//
//  Created by yongjoo on 09/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

class MsgGenerator {
    
    static func genDelegateMsg(_ fromAddress: String, _ toValAddress: String, _ amount: Coin) -> Msg {
        var msg = Msg.init()
        
        var value = Msg.Value.init()
        value.delegator_address = fromAddress
        value.validator_address = toValAddress
        value.value = amount
        
        msg.type = COSMOS_MSG_TYPE_DELEGATE
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
