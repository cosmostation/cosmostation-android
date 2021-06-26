//
//  Signer.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit

class Signer {
    
    /*
    static func genSignedSendTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                  _ toAddress: String, _ amount: Array<Coin>, _ fee: Fee, _ memo: String,
                                  _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var msg = Msg.init()
        var value = Msg.Value.init()
        value.from_address = fromAddress
        value.to_address = toAddress
        let data = try? JSONEncoder().encode(amount)
        do { value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
        } catch { print(error) }
        
        msg.type = COSMOS_MSG_TYPE_TRANSFER2
        msg.value = value
        msgList.append(msg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    static func genSignedDelegateTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                  _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                  _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var msg = Msg.init()
        var value = Msg.Value.init()
        value.delegator_address = fromAddress
        value.validator_address = toValAddress
        let data = try? JSONEncoder().encode(amount)
        do { value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
        } catch { print(error) }
        
        msg.type = COSMOS_MSG_TYPE_DELEGATE
        msg.value = value
        msgList.append(msg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    static func genSignedUndelegateTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                  _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                  _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var msg = Msg.init()
        var value = Msg.Value.init()
        value.delegator_address = fromAddress
        value.validator_address = toValAddress
        let data = try? JSONEncoder().encode(amount)
        do { value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
        } catch { print(error) }
        
        msg.type = COSMOS_MSG_TYPE_UNDELEGATE2
        msg.value = value
        msgList.append(msg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    static func genSignedRedelegateTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                        _ fromValAddress: String, _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                        _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var msg = Msg.init()
        var value = Msg.Value.init()
        value.delegator_address = fromAddress
        value.validator_src_address = fromValAddress
        value.validator_dst_address = toValAddress
        let data = try? JSONEncoder().encode(amount)
        do { value.amount = try JSONDecoder().decode(AmountType.self, from:data!)
        } catch { print(error) }
        
        msg.type = COSMOS_MSG_TYPE_REDELEGATE2
        msg.value = value
        msgList.append(msg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    static func genSignedRewardTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                    _ validators: Array<Validator_V1>, _ fee: Fee, _ memo: String,
                                  _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        for validator in validators {
            var msg = Msg.init()
            var value = Msg.Value.init()
            
            value.delegator_address = fromAddress
            value.validator_address = validator.operator_address
            
            msg.type = COSMOS_MSG_TYPE_WITHDRAW_DEL
            msg.value = value
            msgList.append(msg)
        }
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    static func genSignedReInvestTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                      _ validators: Validator_V1, _ amount: Coin, _ fee: Fee, _ memo: String,
                                      _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var withDrawMsg = Msg.init()
        var withDrawValue = Msg.Value.init()
        withDrawValue.delegator_address = fromAddress
        withDrawValue.validator_address = validators.operator_address
        withDrawMsg.type = COSMOS_MSG_TYPE_WITHDRAW_DEL
        withDrawMsg.value = withDrawValue
        msgList.append(withDrawMsg)
        
        
        
        var delegateMsg = Msg.init()
        var delegateValue = Msg.Value.init()
        delegateValue.delegator_address = fromAddress
        delegateValue.validator_address = validators.operator_address
        let data = try? JSONEncoder().encode(amount)
        do { delegateValue.amount = try JSONDecoder().decode(AmountType.self, from:data!)
        } catch { print(error) }
        
        delegateMsg.type = COSMOS_MSG_TYPE_DELEGATE
        delegateMsg.value = delegateValue
        msgList.append(delegateMsg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    static func genSignedSetRewardAddressTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                  _ rewardAddress: String, _ fee: Fee, _ memo: String,
                                  _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var msg = Msg.init()
        var value = Msg.Value.init()
        value.delegator_address = fromAddress
        value.withdraw_address = rewardAddress
        
        msg.type = COSMOS_MSG_TYPE_WITHDRAW_MIDIFY
        msg.value = value
        msgList.append(msg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    
    
    static func genSignedVoteTxV1(_ fromAddress: String, _ accountNum: String, _ sequenceNum: String,
                                  _ proposalId: String, _ opinion: String, _ fee: Fee, _ memo: String,
                                  _ pKey: HDPrivateKey, _ chain: ChainType) -> StdTx {
        var msgList = Array<Msg>()
        var msg = Msg.init()
        var value = Msg.Value.init()
        
        value.voter = fromAddress
        value.proposal_id = proposalId
        
        var data: Data
        if (opinion == "Yes") {
            data = try! JSONEncoder().encode(1)
        } else if (opinion == "No") {
            data = try! JSONEncoder().encode(3)
        } else if (opinion == "NoWithVeto") {
            data = try! JSONEncoder().encode(4)
        } else if (opinion == "Abstain") {
            data = try! JSONEncoder().encode(2)
        } else {
            data = try! JSONEncoder().encode(0)
        }
        do { value.option = try JSONDecoder().decode(OptionType.self, from:data)
        } catch { print(error) }
        
        msg.type = COSMOS_MSG_TYPE_VOTE
        msg.value = value
        msgList.append(msg)
        
        let stdToSignMsg = getToSignMsg(WUtils.getChainId(chain), accountNum, sequenceNum, msgList, fee, memo)
        let signatureData = getSingleSignature(pKey, stdToSignMsg)
        let signatures = getSignatures(pKey, signatureData!, accountNum, sequenceNum)
        return genSignedTx(msgList, fee, memo, signatures)
    }
    
    
    
    static func getSingleSignature(_ pKey: HDPrivateKey, _ stdToSignMsg: StdSignMsg) -> Data? {
        let encoder = JSONEncoder()
        encoder.outputFormatting = .sortedKeys
        let data = try? encoder.encode(stdToSignMsg)
        let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
        let rawData: Data? = rawResult!.data(using: .utf8)
        let hash = Crypto.sha256(rawData!)
        return try? Crypto.sign(hash, privateKey: pKey.privateKey())
    }
    
    static func getSignatures(_ pKey: HDPrivateKey, _ signatureData: Data, _ accountNum: String, _ sequenceNum: String) -> Array<Signature> {
        var genedSignature = Signature.init()
        var genPubkey =  PublicKey.init()
        genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
        genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
        genedSignature.pub_key = genPubkey
        genedSignature.signature = WKey.convertSignature(signatureData)
        genedSignature.account_number = accountNum
        genedSignature.sequence = sequenceNum
        
        var signatures: Array<Signature> = Array<Signature>()
        signatures.append(genedSignature)
        return signatures
    }
    
    static func getToSignMsg(_ chain: String, _ accountNum: String, _ sequenceNum: String, _ msgs: Array<Msg>, _ fee: Fee, _ memo: String) -> StdSignMsg {
        var stdSignedMsg = StdSignMsg.init()
        
        stdSignedMsg.chain_id = chain
        stdSignedMsg.account_number = accountNum
        stdSignedMsg.sequence = sequenceNum
        stdSignedMsg.msgs = msgs
        stdSignedMsg.fee = fee
        stdSignedMsg.memo = memo
        
        return stdSignedMsg
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
    
    static func getBroadCastParam(_ stdTx: StdTx) -> [String : Any]? {
        let postTx = PostTx.init("sync", stdTx.value)
        let encoder = JSONEncoder()
        encoder.outputFormatting = .sortedKeys
        let data = try? encoder.encode(postTx)
        return try! JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
    }
    */
    
    
    
    static func genSignedSendTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                    _ toAddress: String, _ amount: Array<Coin>, _ fee: Fee, _ memo: String,
                                    _ pKey: PrivateKey, _ chainId: String)  -> Cosmos_Tx_V1beta1_BroadcastTxRequest{
        let sendCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount[0].denom
            $0.amount = amount[0].amount
        }
        let sendMsg = Cosmos_Bank_V1beta1_MsgSend.with {
            $0.fromAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.toAddress = toAddress
            $0.amount = [sendCoin]
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.bank.v1beta1.MsgSend"
            $0.value = try! sendMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateSendTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                      _ toAddress: String, _ amount: Array<Coin>, _ fee: Fee, _ memo: String,
                                      _ pKey: PrivateKey, _ chainId: String)  -> Cosmos_Tx_V1beta1_SimulateRequest {
        let sendCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount[0].denom
            $0.amount = amount[0].amount
        }
        let sendMsg = Cosmos_Bank_V1beta1_MsgSend.with {
            $0.fromAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.toAddress = toAddress
            $0.amount = [sendCoin]
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.bank.v1beta1.MsgSend"
            $0.value = try! sendMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                        _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                        _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let toCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let deleMsg = Cosmos_Staking_V1beta1_MsgDelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = toValAddress
            $0.amount = toCoin
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgDelegate"
            $0.value = try! deleMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let toCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let deleMsg = Cosmos_Staking_V1beta1_MsgDelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = toValAddress
            $0.amount = toCoin
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgDelegate"
            $0.value = try! deleMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedUnDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let toCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let undeleMsg = Cosmos_Staking_V1beta1_MsgUndelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = toValAddress
            $0.amount = toCoin
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgUndelegate"
            $0.value = try! undeleMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateUnDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                            _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let toCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let undeleMsg = Cosmos_Staking_V1beta1_MsgUndelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = toValAddress
            $0.amount = toCoin
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgUndelegate"
            $0.value = try! undeleMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedReDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ fromValAddress: String, _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let toCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let redeleMsg = Cosmos_Staking_V1beta1_MsgBeginRedelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorSrcAddress = fromValAddress
            $0.validatorDstAddress = toValAddress
            $0.amount = toCoin
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgBeginRedelegate"
            $0.value = try! redeleMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateReDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ fromValAddress: String, _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                            _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let toCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let redeleMsg = Cosmos_Staking_V1beta1_MsgBeginRedelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorSrcAddress = fromValAddress
            $0.validatorDstAddress = toValAddress
            $0.amount = toCoin
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgBeginRedelegate"
            $0.value = try! redeleMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedClaimRewardsTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ validators: Array<Cosmos_Staking_V1beta1_Validator>, _ fee: Fee, _ memo: String,
                                            _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        for validator in validators{
            let claimMsg = Cosmos_Distribution_V1beta1_MsgWithdrawDelegatorReward.with {
                $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
                $0.validatorAddress = validator.operatorAddress
            }
            let anyMsg = Google_Protobuf2_Any.with {
                $0.typeURL = "/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward"
                $0.value = try! claimMsg.serializedData()
            }
            anyMsgs.append(anyMsg)
        }
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateClaimRewardsTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ validators: Array<Cosmos_Staking_V1beta1_Validator>, _ fee: Fee, _ memo: String,
                                              _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        for validator in validators{
            let claimMsg = Cosmos_Distribution_V1beta1_MsgWithdrawDelegatorReward.with {
                $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
                $0.validatorAddress = validator.operatorAddress
            }
            let anyMsg = Google_Protobuf2_Any.with {
                $0.typeURL = "/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward"
                $0.value = try! claimMsg.serializedData()
            }
            anyMsgs.append(anyMsg)
        }
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedReInvestTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                        _ valAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                        _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        let claimMsg = Cosmos_Distribution_V1beta1_MsgWithdrawDelegatorReward.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = valAddress
        }
        let claimAnyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward"
            $0.value = try! claimMsg.serializedData()
        }
        anyMsgs.append(claimAnyMsg)
        let deleCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let deleMsg = Cosmos_Staking_V1beta1_MsgDelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = valAddress
            $0.amount = deleCoin
        }
        let deleAnyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgDelegate"
            $0.value = try! deleMsg.serializedData()
        }
        anyMsgs.append(deleAnyMsg)
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateReInvestTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ valAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        let claimMsg = Cosmos_Distribution_V1beta1_MsgWithdrawDelegatorReward.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = valAddress
        }
        let claimAnyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward"
            $0.value = try! claimMsg.serializedData()
        }
        anyMsgs.append(claimAnyMsg)
        let deleCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = amount.denom
            $0.amount = amount.amount
        }
        let deleMsg = Cosmos_Staking_V1beta1_MsgDelegate.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.validatorAddress = valAddress
            $0.amount = deleCoin
        }
        let deleAnyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.staking.v1beta1.MsgDelegate"
            $0.value = try! deleMsg.serializedData()
        }
        anyMsgs.append(deleAnyMsg)
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedVoteTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                    _ proposalId: String, _ opinion: String, _ fee: Fee, _ memo: String,
                                    _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let voteMsg = Cosmos_Gov_V1beta1_MsgVote.with {
            $0.voter = WUtils.onParseAuthGrpc(auth).0!
            $0.proposalID = UInt64(proposalId)!
            if (opinion == "Yes") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.yes
            } else if (opinion == "No") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.no
            } else if (opinion == "NoWithVeto") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.noWithVeto
            } else if (opinion == "Abstain") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.abstain
            }
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.gov.v1beta1.MsgVote"
            $0.value = try! voteMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateVoteTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                      _ proposalId: String, _ opinion: String, _ fee: Fee, _ memo: String,
                                      _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let voteMsg = Cosmos_Gov_V1beta1_MsgVote.with {
            $0.voter = WUtils.onParseAuthGrpc(auth).0!
            $0.proposalID = UInt64(proposalId)!
            if (opinion == "Yes") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.yes
            } else if (opinion == "No") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.no
            } else if (opinion == "NoWithVeto") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.noWithVeto
            } else if (opinion == "Abstain") {
                $0.option = Cosmos_Gov_V1beta1_VoteOption.abstain
            }
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.gov.v1beta1.MsgVote"
            $0.value = try! voteMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedSetRewardAddressTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ newRewardAddress: String, _ fee: Fee, _ memo: String,
                                                _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let rewardAddressMsg = Cosmos_Distribution_V1beta1_MsgSetWithdrawAddress.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.withdrawAddress = newRewardAddress
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.distribution.v1beta1.MsgSetWithdrawAddress"
            $0.value = try! rewardAddressMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    
    static func genSimulateetRewardAddressTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ newRewardAddress: String, _ fee: Fee, _ memo: String,
                                                _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let rewardAddressMsg = Cosmos_Distribution_V1beta1_MsgSetWithdrawAddress.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.withdrawAddress = newRewardAddress
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.distribution.v1beta1.MsgSetWithdrawAddress"
            $0.value = try! rewardAddressMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    //for Starname custom msgs
    static func genSignedRegisterDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ domain: String, _ admin: String, _ type: String,
                                                 _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let rgisterdomainMsg = Starnamed_X_Starname_V1beta1_MsgRegisterDomain.with {
            $0.name = domain
            $0.admin = admin
            $0.domainType = type
            $0.broker = ""
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRegisterDomain"
            $0.value = try! rgisterdomainMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
        
    }
    
    static func genSimulateRegisterDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                   _ domain: String, _ admin: String, _ type: String,
                                                   _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let registerdomainMsg = Starnamed_X_Starname_V1beta1_MsgRegisterDomain.with {
            $0.name = domain
            $0.admin = admin
            $0.domainType = type
            $0.broker = ""
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRegisterDomain"
            $0.value = try! registerdomainMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedRegisterAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                  _ domain: String, _ name: String, _ owner: String, _ registerer: String, _ resources: Array<Starnamed_X_Starname_V1beta1_Resource>,
                                                  _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let registerAccountMsg = Starnamed_X_Starname_V1beta1_MsgRegisterAccount.with {
            $0.domain = domain
            $0.name = name
            $0.owner = owner
            $0.registerer = registerer
            $0.resources = resources
            $0.broker = ""
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRegisterAccount"
            $0.value = try! registerAccountMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRegisterAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                    _ domain: String, _ name: String, _ owner: String, _ registerer: String, _ resources: Array<Starnamed_X_Starname_V1beta1_Resource>,
                                                    _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let registerAccountMsg = Starnamed_X_Starname_V1beta1_MsgRegisterAccount.with {
            $0.domain = domain
            $0.name = name
            $0.owner = owner
            $0.registerer = registerer
            $0.resources = resources
            $0.broker = ""
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRegisterAccount"
            $0.value = try! registerAccountMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedDeleteDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                               _ domain: String, _ owner: String,
                                               _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let deleteDomainMsg = Starnamed_X_Starname_V1beta1_MsgDeleteDomain.with {
            $0.domain = domain
            $0.owner = owner
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgDeleteDomain"
            $0.value = try! deleteDomainMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateDeleteDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ domain: String, _ owner: String,
                                                 _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let deleteDomainMsg = Starnamed_X_Starname_V1beta1_MsgDeleteDomain.with {
            $0.domain = domain
            $0.owner = owner
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgDeleteDomain"
            $0.value = try! deleteDomainMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }

    static func genSignedDeleteAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ domain: String, _ name: String, _ owner: String,
                                                _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let deleteAccountMsg = Starnamed_X_Starname_V1beta1_MsgDeleteAccount.with {
            $0.domain = domain
            $0.name = name
            $0.owner = owner
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgDeleteAccount"
            $0.value = try! deleteAccountMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }

    static func genSimulateDeleteAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                  _ domain: String, _ name: String, _ owner: String,
                                                  _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let deleteAccountMsg = Starnamed_X_Starname_V1beta1_MsgDeleteAccount.with {
            $0.domain = domain
            $0.name = name
            $0.owner = owner
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgDeleteAccount"
            $0.value = try! deleteAccountMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
     
    static func genSignedRenewDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ domain: String, _ signer: String,
                                              _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let renewDomainMsg =  Starnamed_X_Starname_V1beta1_MsgRenewDomain.with {
            $0.domain = domain
            $0.signer = signer
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRenewDomain"
            $0.value = try! renewDomainMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRenewDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ domain: String, _ signer: String,
                                                _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let renewDomainMsg = Starnamed_X_Starname_V1beta1_MsgRenewDomain.with {
            $0.domain = domain
            $0.signer = signer
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRenewDomain"
            $0.value = try! renewDomainMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedRenewAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ domain: String, _ name: String, _ signer: String,
                                              _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let renewAccountMsg = Starnamed_X_Starname_V1beta1_MsgRenewAccount.with {
            $0.domain = domain
            $0.name = name
            $0.signer = signer
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRenewAccount"
            $0.value = try! renewAccountMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRenewAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ domain: String, _ name: String, _ signer: String,
                                                _ fee: Fee, _ memo: String, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let renewAccountMsg = Starnamed_X_Starname_V1beta1_MsgRenewAccount.with {
            $0.domain = domain
            $0.name = name
            $0.signer = signer
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgRenewAccount"
            $0.value = try! renewAccountMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, pKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    
    
    
    
    
    
    
    static func getGrpcTxBody(_ msgAnys: Array<Google_Protobuf2_Any>, _ memo: String) -> Cosmos_Tx_V1beta1_TxBody {
        return Cosmos_Tx_V1beta1_TxBody.with {
            $0.memo = memo
            $0.messages = msgAnys
        }
    }
    
    static func getGrpcSignerInfo(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ pKey: PrivateKey) -> Cosmos_Tx_V1beta1_SignerInfo {
        let single = Cosmos_Tx_V1beta1_ModeInfo.Single.with {
            $0.mode = Cosmos_Tx_Signing_V1beta1_SignMode.direct
        }
        let mode = Cosmos_Tx_V1beta1_ModeInfo.with {
            $0.single = single
        }
        let pub = Cosmos_Crypto_Secp256k1_PubKey.with {
            $0.key = pKey.publicKey.data
        }
        let pubKey = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.crypto.secp256k1.PubKey"
            $0.value = try! pub.serializedData()
        }
        return Cosmos_Tx_V1beta1_SignerInfo.with {
            $0.publicKey = pubKey
            $0.modeInfo = mode
            $0.sequence = WUtils.onParseAuthGrpc(auth).2!
        }
    }
    
    static func getGrpcAuthInfo(_ signerInfo: Cosmos_Tx_V1beta1_SignerInfo, _ fee: Fee) -> Cosmos_Tx_V1beta1_AuthInfo{
        let feeCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = fee.amount[0].denom
            $0.amount = fee.amount[0].amount
        }
        let txFee = Cosmos_Tx_V1beta1_Fee.with {
            $0.amount = [feeCoin]
            $0.gasLimit = UInt64(fee.gas)!
        }
        return Cosmos_Tx_V1beta1_AuthInfo.with {
            $0.fee = txFee
            $0.signerInfos = [signerInfo]
        }
    }
    
    static func getGrpcRawTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ txBody: Cosmos_Tx_V1beta1_TxBody, _ authInfo: Cosmos_Tx_V1beta1_AuthInfo, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_TxRaw {
        let signDoc = Cosmos_Tx_V1beta1_SignDoc.with {
            $0.bodyBytes = try! txBody.serializedData()
            $0.authInfoBytes = try! authInfo.serializedData()
            $0.chainID = chainId
            $0.accountNumber = WUtils.onParseAuthGrpc(auth).1!
        }
        let sigbyte = getGrpcByteSingleSignature(pKey, try! signDoc.serializedData())
        return Cosmos_Tx_V1beta1_TxRaw.with {
            $0.bodyBytes = try! txBody.serializedData()
            $0.authInfoBytes = try! authInfo.serializedData()
            $0.signatures = [sigbyte]
        }
    }
    
    static func getGrpcSimulTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ txBody: Cosmos_Tx_V1beta1_TxBody, _ authInfo: Cosmos_Tx_V1beta1_AuthInfo, _ pKey: PrivateKey, _ chainId: String) -> Cosmos_Tx_V1beta1_Tx {
        let signDoc = Cosmos_Tx_V1beta1_SignDoc.with {
            $0.bodyBytes = try! txBody.serializedData()
            $0.authInfoBytes = try! authInfo.serializedData()
            $0.chainID = chainId
            $0.accountNumber = WUtils.onParseAuthGrpc(auth).1!
        }
        let sigbyte = getGrpcByteSingleSignature(pKey, try! signDoc.serializedData())
        return Cosmos_Tx_V1beta1_Tx.with {
            $0.authInfo = authInfo
            $0.body = txBody
            $0.signatures = [sigbyte]
        }
    }
    
    static func getGrpcByteSingleSignature(_ pKey: PrivateKey, _ toSignByte: Data) -> Data {
        let hash = toSignByte.sha256()
        let signedData = try! ECDSA.compactsign(hash, privateKey: pKey.raw)
        return signedData
    }
}
