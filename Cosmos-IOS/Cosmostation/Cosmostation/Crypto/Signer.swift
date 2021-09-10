//
//  Signer.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit
import secp256k1
import SwiftProtobuf

class Signer {
    
    static func genSignedSendTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                    _ toAddress: String, _ amount: Array<Coin>, _ fee: Fee, _ memo: String,
                                    _ privateKey: Data, _ publicKey: Data, _ chainId: String)  -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateSendTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                      _ toAddress: String, _ amount: Array<Coin>, _ fee: Fee, _ memo: String,
                                      _ privateKey: Data, _ publicKey: Data, _ chainId: String)  -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                        _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                        _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedUnDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateUnDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                            _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedReDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ fromValAddress: String, _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateReDelegateTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ fromValAddress: String, _ toValAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                            _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedClaimRewardsTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ validators: Array<Cosmos_Staking_V1beta1_Validator>, _ fee: Fee, _ memo: String,
                                            _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateClaimRewardsTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ validators: Array<Cosmos_Staking_V1beta1_Validator>, _ fee: Fee, _ memo: String,
                                              _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedReInvestTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                        _ valAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                        _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateReInvestTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                          _ valAddress: String, _ amount: Coin, _ fee: Fee, _ memo: String,
                                          _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedVoteTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                    _ proposalId: String, _ opinion: String, _ fee: Fee, _ memo: String,
                                    _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateVoteTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                      _ proposalId: String, _ opinion: String, _ fee: Fee, _ memo: String,
                                      _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedSetRewardAddressTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ newRewardAddress: String, _ fee: Fee, _ memo: String,
                                                _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let rewardAddressMsg = Cosmos_Distribution_V1beta1_MsgSetWithdrawAddress.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.withdrawAddress = newRewardAddress
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.distribution.v1beta1.MsgSetWithdrawAddress"
            $0.value = try! rewardAddressMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    
    static func genSimulateetRewardAddressTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ newRewardAddress: String, _ fee: Fee, _ memo: String,
                                                _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let rewardAddressMsg = Cosmos_Distribution_V1beta1_MsgSetWithdrawAddress.with {
            $0.delegatorAddress = WUtils.onParseAuthGrpc(auth).0!
            $0.withdrawAddress = newRewardAddress
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/cosmos.distribution.v1beta1.MsgSetWithdrawAddress"
            $0.value = try! rewardAddressMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    //for Starname custom msgs
    static func genSignedRegisterDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ domain: String, _ admin: String, _ type: String,
                                                 _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRegisterDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                   _ domain: String, _ admin: String, _ type: String,
                                                   _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedRegisterAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                  _ domain: String, _ name: String, _ owner: String, _ registerer: String, _ resources: Array<Starnamed_X_Starname_V1beta1_Resource>,
                                                  _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRegisterAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                    _ domain: String, _ name: String, _ owner: String, _ registerer: String, _ resources: Array<Starnamed_X_Starname_V1beta1_Resource>,
                                                    _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedDeleteDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                               _ domain: String, _ owner: String,
                                               _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateDeleteDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ domain: String, _ owner: String,
                                                 _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }

    static func genSignedDeleteAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ domain: String, _ name: String, _ owner: String,
                                                _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }

    static func genSimulateDeleteAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                  _ domain: String, _ name: String, _ owner: String,
                                                  _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
     
    static func genSignedRenewDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ domain: String, _ signer: String,
                                              _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRenewDomainMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ domain: String, _ signer: String,
                                                _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedRenewAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ domain: String, _ name: String, _ signer: String,
                                              _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateRenewAccountMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ domain: String, _ name: String, _ signer: String,
                                                 _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
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
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedReplaceResourceMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                  _ domain: String, _ name: String?, _ owner: String, _ resources: Array<Starnamed_X_Starname_V1beta1_Resource>,
                                                  _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let replaceResourceMsg = Starnamed_X_Starname_V1beta1_MsgReplaceAccountResources.with {
            if (name != nil) { $0.name = name! }
            else { $0.name = "" }
            $0.domain = domain
            $0.owner = owner
            $0.newResources = resources
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgReplaceAccountResources"
            $0.value = try! replaceResourceMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateReplaceResourceMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                    _ domain: String, _ name: String?, _ owner: String, _ resources: Array<Starnamed_X_Starname_V1beta1_Resource>,
                                                    _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let replaceResourceMsg = Starnamed_X_Starname_V1beta1_MsgReplaceAccountResources.with {
            if (name != nil) { $0.name = name! }
            else { $0.name = "" }
            $0.domain = domain
            $0.owner = owner
            $0.newResources = resources
            $0.payer = ""
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/starnamed.x.starname.v1beta1.MsgReplaceAccountResources"
            $0.value = try! replaceResourceMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    //for Osmosis custom msgs
    static func genSignedSwapInMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ swapRoutes: [Osmosis_Gamm_V1beta1_SwapAmountInRoute], _ inputDenom: String, _ inputAmount: String, _ outputAmount: String,
                                                 _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let inputCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = inputDenom
            $0.amount = inputAmount
        }
        let swapMsg = Osmosis_Gamm_V1beta1_MsgSwapExactAmountIn.with {
            $0.sender = WUtils.onParseAuthGrpc(auth).0!
            $0.routes = swapRoutes
            $0.tokenIn = inputCoin
            $0.tokenOutMinAmount = outputAmount
            
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.gamm.v1beta1.MsgSwapExactAmountIn"
            $0.value = try! swapMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateSwapInMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                           _ swapRoutes: [Osmosis_Gamm_V1beta1_SwapAmountInRoute], _ inputDenom: String, _ inputAmount: String, _ outputAmount: String,
                                           _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let inputCoin = Cosmos_Base_V1beta1_Coin.with {
            $0.denom = inputDenom
            $0.amount = inputAmount
        }
        let swapMsg = Osmosis_Gamm_V1beta1_MsgSwapExactAmountIn.with {
            $0.sender = WUtils.onParseAuthGrpc(auth).0!
            $0.routes = swapRoutes
            $0.tokenIn = inputCoin
            $0.tokenOutMinAmount = outputAmount
            
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.gamm.v1beta1.MsgSwapExactAmountIn"
            $0.value = try! swapMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedDepositPoolMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ poolId: String, _ deposit0Coin: Coin, _ deposit1Coin: Coin, _ shareAmount: String,
                                                 _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let input0Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = deposit0Coin.denom; $0.amount = deposit0Coin.amount }
        let input1Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = deposit1Coin.denom; $0.amount = deposit1Coin.amount }
        var tokenMax = Array<Cosmos_Base_V1beta1_Coin>()
        tokenMax.append(input0Coin)
        tokenMax.append(input1Coin)
        
        let joinPoolMsg = Osmosis_Gamm_V1beta1_MsgJoinPool.with {
            $0.sender = WUtils.onParseAuthGrpc(auth).0!
            $0.poolID = UInt64(poolId)!
            $0.tokenInMaxs = tokenMax
            $0.shareOutAmount = shareAmount
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.gamm.v1beta1.MsgJoinPool"
            $0.value = try! joinPoolMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateDepositPoolMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                _ poolId: String, _ deposit0Coin: Coin, _ deposit1Coin: Coin, _ shareAmount: String,
                                                _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let input0Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = deposit0Coin.denom; $0.amount = deposit0Coin.amount }
        let input1Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = deposit1Coin.denom; $0.amount = deposit1Coin.amount }
        var tokenMax = Array<Cosmos_Base_V1beta1_Coin>()
        tokenMax.append(input0Coin)
        tokenMax.append(input1Coin)
        
        let joinPoolMsg = Osmosis_Gamm_V1beta1_MsgJoinPool.with {
            $0.sender = WUtils.onParseAuthGrpc(auth).0!
            $0.poolID = UInt64(poolId)!
            $0.tokenInMaxs = tokenMax
            $0.shareOutAmount = shareAmount
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.gamm.v1beta1.MsgJoinPool"
            $0.value = try! joinPoolMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedWithdrawPoolMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                               _ poolId: String, _ withdraw0Coin: Coin, _ withdraw1Coin: Coin, _ shareAmount: String,
                                               _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let input0Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = withdraw0Coin.denom; $0.amount = withdraw0Coin.amount }
        let input1Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = withdraw1Coin.denom; $0.amount = withdraw1Coin.amount }
        var tokenMin = Array<Cosmos_Base_V1beta1_Coin>()
        tokenMin.append(input0Coin)
        tokenMin.append(input1Coin)
        
        let exitPoolMsg = Osmosis_Gamm_V1beta1_MsgExitPool.with {
            $0.sender = WUtils.onParseAuthGrpc(auth).0!
            $0.poolID = UInt64(poolId)!
            $0.tokenOutMins = tokenMin
            $0.shareInAmount = shareAmount
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.gamm.v1beta1.MsgExitPool"
            $0.value = try! exitPoolMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateWithdrawPoolMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                 _ poolId: String, _ withdraw0Coin: Coin, _ withdraw1Coin: Coin, _ shareAmount: String,
                                                 _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let input0Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = withdraw0Coin.denom; $0.amount = withdraw0Coin.amount }
        let input1Coin = Cosmos_Base_V1beta1_Coin.with { $0.denom = withdraw1Coin.denom; $0.amount = withdraw1Coin.amount }
        var tokenMin = Array<Cosmos_Base_V1beta1_Coin>()
        tokenMin.append(input0Coin)
        tokenMin.append(input1Coin)
        
        let exitPoolMsg = Osmosis_Gamm_V1beta1_MsgExitPool.with {
            $0.sender = WUtils.onParseAuthGrpc(auth).0!
            $0.poolID = UInt64(poolId)!
            $0.tokenOutMins = tokenMin
            $0.shareInAmount = shareAmount
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.gamm.v1beta1.MsgExitPool"
            $0.value = try! exitPoolMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    static func genSignedLockTokensMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                             _ lpCoin: Coin, _ duration: Int64,
                                             _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let lockupCoin = Cosmos_Base_V1beta1_Coin.with { $0.denom = lpCoin.denom; $0.amount = lpCoin.amount }
        var lockupTokens = Array<Cosmos_Base_V1beta1_Coin>()
        lockupTokens.append(lockupCoin)
        
        let lockTokensMsg = Osmosis_Lockup_MsgLockTokens.with {
            $0.owner = WUtils.onParseAuthGrpc(auth).0!
            $0.duration = SwiftProtobuf.Google_Protobuf_Duration.init(seconds: duration, nanos: 0)
            $0.coins = lockupTokens
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.lockup.MsgLockTokens"
            $0.value = try! lockTokensMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateLockTokensMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                               _ lpCoin: Coin, _ duration: Int64,
                                               _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let lockupCoin = Cosmos_Base_V1beta1_Coin.with { $0.denom = lpCoin.denom; $0.amount = lpCoin.amount }
        var lockupTokens = Array<Cosmos_Base_V1beta1_Coin>()
        lockupTokens.append(lockupCoin)
        
        let lockTokensMsg = Osmosis_Lockup_MsgLockTokens.with {
            $0.owner = WUtils.onParseAuthGrpc(auth).0!
            $0.duration = SwiftProtobuf.Google_Protobuf_Duration.init(seconds: duration, nanos: 0)
            $0.coins = lockupTokens
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/osmosis.lockup.MsgLockTokens"
            $0.value = try! lockTokensMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedBeginUnlockingsMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                  _ ids: Array<UInt64>,
                                                  _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        for id in ids {
            let unlockMsg = Osmosis_Lockup_MsgBeginUnlocking.with {
                $0.owner = WUtils.onParseAuthGrpc(auth).0!
                $0.id = id
            }
            let anyMsg = Google_Protobuf2_Any.with {
                $0.typeURL = "/osmosis.lockup.MsgBeginUnlocking"
                $0.value = try! unlockMsg.serializedData()
            }
            anyMsgs.append(anyMsg)
        }
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateBeginUnlockingsMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                    _ ids: Array<UInt64>,
                                                    _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        for id in ids {
            let unlockMsg = Osmosis_Lockup_MsgBeginUnlocking.with {
                $0.owner = WUtils.onParseAuthGrpc(auth).0!
                $0.id = id
            }
            let anyMsg = Google_Protobuf2_Any.with {
                $0.typeURL = "/osmosis.lockup.MsgBeginUnlocking"
                $0.value = try! unlockMsg.serializedData()
            }
            anyMsgs.append(anyMsg)
        }
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedUnlockPeriodLocksMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                    _ ids: Array<UInt64>,
                                                    _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        for id in ids {
            let unlockMsg = Osmosis_Lockup_MsgUnlockPeriodLock.with {
                $0.owner = WUtils.onParseAuthGrpc(auth).0!
                $0.id = id
            }
            let anyMsg = Google_Protobuf2_Any.with {
                $0.typeURL = "/osmosis.lockup.MsgUnlockPeriodLock"
                $0.value = try! unlockMsg.serializedData()
            }
            anyMsgs.append(anyMsg)
        }
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateUnlockPeriodLocksMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                                      _ ids: Array<UInt64>,
                                                      _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        var anyMsgs = Array<Google_Protobuf2_Any>()
        for id in ids {
            let unlockMsg = Osmosis_Lockup_MsgUnlockPeriodLock.with {
                $0.owner = WUtils.onParseAuthGrpc(auth).0!
                $0.id = id
            }
            let anyMsg = Google_Protobuf2_Any.with {
                $0.typeURL = "/osmosis.lockup.MsgUnlockPeriodLock"
                $0.value = try! unlockMsg.serializedData()
            }
            anyMsgs.append(anyMsg)
        }
        let txBody = getGrpcTxBody(anyMsgs, memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_SimulateRequest.with {
            $0.tx = simulateTx
        }
    }
    
    
    static func genSignedSwapBatchMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                            _ requester: String, _ poolId: String, _ swapType: String, _ offerCoin: Coin, _ offerCoinFee: Coin,
                                            _ demandCoinDenom: String, _ orderPrice: String,
                                            _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_BroadcastTxRequest {
        let swapBatchMsg = Tendermint_Liquidity_V1beta1_MsgSwapWithinBatch.with {
            $0.swapRequesterAddress = requester
            $0.poolID = UInt64(poolId)!
            $0.swapTypeID = UInt32("1")!
            $0.offerCoin = Cosmos_Base_V1beta1_Coin.with({ $0.denom = offerCoin.denom; $0.amount = offerCoin.amount })
            $0.offerCoinFee = Cosmos_Base_V1beta1_Coin.with({ $0.denom = offerCoinFee.denom; $0.amount = offerCoinFee.amount })
            $0.demandCoinDenom = demandCoinDenom
            $0.orderPrice = orderPrice
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/tendermint.liquidity.v1beta1.MsgSwapWithinBatch"
            $0.value = try! swapBatchMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let rawTx = getGrpcRawTx(auth, txBody, authInfo, privateKey, chainId);
        return Cosmos_Tx_V1beta1_BroadcastTxRequest.with {
            $0.mode = Cosmos_Tx_V1beta1_BroadcastMode.async
            $0.txBytes = try! rawTx.serializedData()
        }
    }
    
    static func genSimulateSwapBatchMsgTxgRPC(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse,
                                              _ requester: String, _ poolId: String, _ swapType: String, _ offerCoin: Coin, _ offerCoinFee: Coin,
                                              _ demandCoinDenom: String, _ orderPrice: String,
                                              _ fee: Fee, _ memo: String, _ privateKey: Data, _ publicKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_SimulateRequest {
        let swapBatchMsg = Tendermint_Liquidity_V1beta1_MsgSwapWithinBatch.with {
            $0.swapRequesterAddress = requester
            $0.poolID = UInt64(poolId)!
            $0.swapTypeID = UInt32("1")!
            $0.offerCoin = Cosmos_Base_V1beta1_Coin.with({ $0.denom = offerCoin.denom; $0.amount = offerCoin.amount })
            $0.offerCoinFee = Cosmos_Base_V1beta1_Coin.with({ $0.denom = offerCoinFee.denom; $0.amount = offerCoinFee.amount })
            $0.demandCoinDenom = demandCoinDenom
            $0.orderPrice = orderPrice
        }
        let anyMsg = Google_Protobuf2_Any.with {
            $0.typeURL = "/tendermint.liquidity.v1beta1.MsgSwapWithinBatch"
            $0.value = try! swapBatchMsg.serializedData()
        }
        let txBody = getGrpcTxBody([anyMsg], memo);
        let signerInfo = getGrpcSignerInfo(auth, publicKey);
        let authInfo = getGrpcAuthInfo(signerInfo, fee);
        let simulateTx = getGrpcSimulTx(auth, txBody, authInfo, privateKey, chainId);
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
    
    static func getGrpcSignerInfo(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ publicKey: Data) -> Cosmos_Tx_V1beta1_SignerInfo {
        let single = Cosmos_Tx_V1beta1_ModeInfo.Single.with {
            $0.mode = Cosmos_Tx_Signing_V1beta1_SignMode.direct
        }
        let mode = Cosmos_Tx_V1beta1_ModeInfo.with {
            $0.single = single
        }
        let pub = Cosmos_Crypto_Secp256k1_PubKey.with {
            $0.key = publicKey
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
    
    static func getGrpcRawTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ txBody: Cosmos_Tx_V1beta1_TxBody, _ authInfo: Cosmos_Tx_V1beta1_AuthInfo, _ privateKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_TxRaw {
        let signDoc = Cosmos_Tx_V1beta1_SignDoc.with {
            $0.bodyBytes = try! txBody.serializedData()
            $0.authInfoBytes = try! authInfo.serializedData()
            $0.chainID = chainId
            $0.accountNumber = WUtils.onParseAuthGrpc(auth).1!
        }
        let sigbyte = getGrpcByteSingleSignature(privateKey, try! signDoc.serializedData())
        return Cosmos_Tx_V1beta1_TxRaw.with {
            $0.bodyBytes = try! txBody.serializedData()
            $0.authInfoBytes = try! authInfo.serializedData()
            $0.signatures = [sigbyte]
        }
    }
    
    static func getGrpcSimulTx(_ auth: Cosmos_Auth_V1beta1_QueryAccountResponse, _ txBody: Cosmos_Tx_V1beta1_TxBody, _ authInfo: Cosmos_Tx_V1beta1_AuthInfo, _ privateKey: Data, _ chainId: String) -> Cosmos_Tx_V1beta1_Tx {
        let signDoc = Cosmos_Tx_V1beta1_SignDoc.with {
            $0.bodyBytes = try! txBody.serializedData()
            $0.authInfoBytes = try! authInfo.serializedData()
            $0.chainID = chainId
            $0.accountNumber = WUtils.onParseAuthGrpc(auth).1!
        }
        let sigbyte = getGrpcByteSingleSignature(privateKey, try! signDoc.serializedData())
        return Cosmos_Tx_V1beta1_Tx.with {
            $0.authInfo = authInfo
            $0.body = txBody
            $0.signatures = [sigbyte]
        }
    }
    
    static func getGrpcByteSingleSignature(_ privateKey: Data, _ toSignByte: Data) -> Data {
        let hash = toSignByte.sha256()
        let signedData = try! ECDSA.compactsign(hash, privateKey: privateKey)
        return signedData
    }
}


extension ECDSA {
    public static func compactsign(_ data: Data, privateKey: Data) throws -> Data {
        let ctx = secp256k1_context_create(UInt32(SECP256K1_CONTEXT_SIGN))!
        defer { secp256k1_context_destroy(ctx) }
        let signature = UnsafeMutablePointer<secp256k1_ecdsa_signature>.allocate(capacity: 1)
        defer { signature.deallocate() }
        let status = data.withUnsafeBytes { (ptr: UnsafePointer<UInt8>) in
            privateKey.withUnsafeBytes { secp256k1_ecdsa_sign(ctx, signature, ptr, $0, nil, nil) }
        }
        guard status == 1 else { throw HDWalletKitError.failedToSign }
        let normalizedsig = UnsafeMutablePointer<secp256k1_ecdsa_signature>.allocate(capacity: 1)
        defer { normalizedsig.deallocate() }
        secp256k1_ecdsa_signature_normalize(ctx, normalizedsig, signature)
        let length: size_t = 64
        var compact = Data(count: length)
        guard compact.withUnsafeMutableBytes({ return secp256k1_ecdsa_signature_serialize_compact(ctx, $0, normalizedsig) }) == 1 else { throw HDWalletKitError.noEnoughSpace }
        compact.count = length
        return compact
    }
}
