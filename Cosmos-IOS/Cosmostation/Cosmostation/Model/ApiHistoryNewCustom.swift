//
//  ApiHistoryNewCustom.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/23.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct ApiHistoryNewCustom {
    var header: ApiHistoryNewCustomHeader?
    var data: ApiHistoryNewCustomData?
    
    init(_ dictionary: NSDictionary?) {
        if let rawHeader = dictionary?["header"] as? NSDictionary {
            self.header = ApiHistoryNewCustomHeader.init(rawHeader)
        }
        if let rawData = dictionary?["data"] as? NSDictionary {
            self.data = ApiHistoryNewCustomData.init(rawData)
        }
    }
    
    public func isSuccess() -> Bool {
        if let RawCode = self.data?.code {
            if (RawCode != 0) {
                return false
            }
        }
        return true
    }
    
    public func getMsgs() -> Array<NSDictionary>? {
        if let rawMsgs = self.data?.tx?.value(forKeyPath: "body.messages") as? Array<NSDictionary> {
            return rawMsgs
        }
        
        if let rawMsgs = self.data?.tx?.value(forKeyPath: "value.msg") as? Array<NSDictionary> {
            return rawMsgs
        }
        return nil
    }
    
    public func getMsgCnt() -> Int {
        guard let msgs = getMsgs() else {
            return 0
        }
        return msgs.count
    }
    
    public func getMsgType(_ address: String) -> String {
        var result = NSLocalizedString("tx_known", comment: "")
        if (getMsgCnt() == 0) {
            return result;
            
        } else {
            if (getMsgCnt() == 2) {
                var msgType0 = ""
                var msgType1 = ""
                if let rawMsgType = getMsgs()?[0].object(forKey: "@type") as? String {
                    msgType0 = rawMsgType
                }
                if let rawMsgType = getMsgs()?[0].object(forKey: "type") as? String {
                    msgType0 = rawMsgType
                }
                if let rawMsgType = getMsgs()?[1].object(forKey: "@type") as? String {
                    msgType1 = rawMsgType
                }
                if let rawMsgType = getMsgs()?[1].object(forKey: "type") as? String {
                    msgType1 = rawMsgType
                }
                if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate")) {
                    return NSLocalizedString("tx_reinvest", comment: "")
                }
                
                if (msgType1.contains("ibc") && msgType1.contains("MsgRecvPacket")) {
                    return NSLocalizedString("tx_ibc_receive", comment: "")
                }
                
            }
            
            var msgType = ""
            if let rawMsgType = getMsgs()?[0].object(forKey: "@type") as? String {
                msgType = rawMsgType
            }
            if let rawMsgType = getMsgs()?[0].object(forKey: "type") as? String {
                msgType = rawMsgType
            }
            
            if (msgType.contains("MsgDelegate")) {
                result = NSLocalizedString("tx_delegate", comment: "")
                
            } else if (msgType.contains("MsgUndelegate")) {
                result = NSLocalizedString("tx_undelegate", comment: "")
                
            } else if (msgType.contains("MsgWithdrawDelegatorReward") || msgType.contains("MsgWithdrawDelegationReward")) {
                result = NSLocalizedString("tx_get_reward", comment: "")
                
            } else if (msgType.contains("MsgSend")) {
                if let senderAddr = getMsgs()![0].object(forKey: "from_address") as? String, senderAddr == address {
                    result = NSLocalizedString("tx_send", comment: "")
                } else if let receiverAddr = getMsgs()![0].object(forKey: "to_address") as? String, receiverAddr == address {
                    result = NSLocalizedString("tx_receive", comment: "")
                } else {
                    result = NSLocalizedString("tx_transfer", comment: "")
                }
                
            } else if (msgType.contains("MsgMultiSend")) {
                result = NSLocalizedString("tx_transfer", comment: "")
                
            } else if (msgType.contains("MsgBeginRedelegate")) {
                result = NSLocalizedString("tx_redelegate", comment: "")
                
            } else if (msgType.contains("MsgSetWithdrawAddress") || msgType.contains("MsgModifyWithdrawAddress")) {
                result = NSLocalizedString("tx_change_reward_address", comment: "")
                
            } else if (msgType.contains("MsgCreateValidator")) {
                result = NSLocalizedString("tx_create_validator", comment: "")
                
            } else if (msgType.contains("MsgEditValidator")) {
                result = NSLocalizedString("tx_edit_validator", comment: "")
                
            } else if (msgType.contains("MsgUnjail")) {
                result = NSLocalizedString("tx_unjail_validator", comment: "")
                
            } else if (msgType.contains("MsgSubmitProposal")) {
                result = NSLocalizedString("tx_submit_proposal", comment: "")
                
            } else if (msgType.contains("MsgVote")) {
                result = NSLocalizedString("tx_vote", comment: "")
                
            } else if (msgType.contains("MsgDeposit")) {
                result = NSLocalizedString("tx_deposit", comment: "")
                
            } else if (msgType.contains("MsgWithdrawValidatorCommission")) {
                result = NSLocalizedString("tx_get_commission", comment: "")
                
            }
            
            
            
            else if (msgType.contains("MsgMintNFT")) {
                result = "NFT Mint"
                
            } else if (msgType.contains("MsgTransferNFT")) {
                result = "NFT Transfer"
                
            } else if (msgType.contains("MsgEditNFT")) {
                result = "NFT Edit"
                
            } else if (msgType.contains("MsgIssueDenom")) {
                result = "NFT Issue"
                
            } else if (msgType.contains("MsgRequestRandom")) {
                result = "Random Request"
                
            }
            
            else if (msgType.contains("ibc") && msgType.contains("MsgTransfer")) {
                result = NSLocalizedString("tx_ibc_send", comment: "")
                
            } else if (msgType.contains("ibc") && msgType.contains("MsgUpdateClient")) {
                result = NSLocalizedString("tx_ibc_client_update", comment: "")
                
            } else if (msgType.contains("ibc") && msgType.contains("MsgAcknowledgement")) {
                result = NSLocalizedString("tx_ibc_acknowledgement", comment: "")
                
            } else if (msgType.contains("ibc") && msgType.contains("MsgRecvPacket")) {
                result = NSLocalizedString("tx_ibc_receive", comment: "")
                
            }
            
            else if (msgType.contains("RegisterDomain")) {
                result = NSLocalizedString("tx_starname_registe_domain", comment: "")
                
            } else if (msgType.contains("RegisterAccount")) {
                result = NSLocalizedString("tx_starname_registe_account", comment: "")
                
            } else if (msgType.contains("DeleteDomain")) {
                result = NSLocalizedString("tx_starname_delete_domain", comment: "")
                
            } else if (msgType.contains("DeleteAccount")) {
                result = NSLocalizedString("tx_starname_delete_account", comment: "")
                
            } else if (msgType.contains("RenewDomain")) {
                result = NSLocalizedString("tx_starname_renew_domain", comment: "")
                
            } else if (msgType.contains("RenewAccount")) {
                result = NSLocalizedString("tx_starname_renew_account", comment: "")
                
            } else if (msgType.contains("ReplaceAccountResources")) {
                result = NSLocalizedString("tx_starname_update_resource", comment: "")
                
            }
            
            else if (msgType.contains("MsgCreatePool")) {
                result = NSLocalizedString("tx_osmosis_create_pool", comment: "")
                
            } else if (msgType.contains("MsgJoinPool")) {
                result = NSLocalizedString("tx_osmosis_join_pool", comment: "")
                
            } else if (msgType.contains("MsgExitPool")) {
                result = NSLocalizedString("tx_osmosis_exit_pool", comment: "")
                
            } else if (msgType.contains("MsgSwapExactAmountIn") || msgType.contains("MsgSwapExactAmountOut")) {
                result = NSLocalizedString("tx_osmosis_coin_swap", comment: "")
                
            } else if (msgType.contains("MsgJoinSwapExternAmountIn") || msgType.contains("MsgJoinSwapShareAmountOut") ||
                        msgType.contains("MsgExitSwapExternAmountOut") || msgType.contains("MsgExitSwapShareAmountIn")) {
                result = NSLocalizedString("tx_osmosis_coin_swap", comment: "")
            }
            
            if (getMsgCnt() > 1) {
                result = result +  "\n+ " + String(getMsgCnt() - 1)
            }
            
        }
        return result
    }
    
    public func getDpCoin() -> Coin? {
        if (getMsgCnt() == 0 || getMsgCnt() > 1) { return nil }
        
        var msgType = ""
        if let rawMsgType = getMsgs()?[0].object(forKey: "@type") as? String { msgType = rawMsgType }
        if let rawMsgType = getMsgs()?[0].object(forKey: "type") as? String { msgType = rawMsgType }
        
        if (msgType.contains("MsgSend")) {
            if let rawAmounts = getMsgs()?[0].object(forKey: "amount") as? Array<NSDictionary> {
                return Coin.init(rawAmounts[0])
            }
            if let rawAmounts = getMsgs()?[0].value(forKeyPath: "value.amount") as? Array<NSDictionary> {
                return Coin.init(rawAmounts[0])
            }
            
        } else if (msgType.contains("MsgDelegate") || msgType.contains("MsgUndelegate") || msgType.contains("MsgBeginRedelegate")) {
            if let rawAmount = getMsgs()?[0].object(forKey: "amount") as? NSDictionary {
                return Coin.init(rawAmount)
            }
            if let rawAmount = getMsgs()?[0].value(forKeyPath: "value.amount") as? NSDictionary {
                return Coin.init(rawAmount)
            }
            
        } else if (msgType.contains("ibc") && msgType.contains("MsgTransfer")) {
            if let rawAmount = getMsgs()?[0].object(forKey: "token") as? NSDictionary {
                return Coin.init(rawAmount)
            }
        }
        return nil
    }
    
    
}

public struct ApiHistoryNewCustomHeader {
    var id: Int64?
    var chain_id: String?
    var block_id: Int64?
    var timestamp: String?
    
    init(_ dictionary: NSDictionary?) {
        self.id = dictionary?["id"] as? Int64
        self.chain_id = dictionary?["chain_id"] as? String
        self.block_id = dictionary?["block_id"] as? Int64
        self.timestamp = dictionary?["timestamp"] as? String
    }
}

public struct ApiHistoryNewCustomData {
    var height: String?
    var txhash: String?
    var codespace: String?
    var code: Int?
    var data: String?
    var raw_log: String?
    var info: String?
    var gas_wanted: String?
    var gas_used: String?
    var timestamp: String?
    var tx: NSDictionary?
    var logs: NSDictionary?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        self.txhash = dictionary?["txhash"] as? String
        self.codespace = dictionary?["codespace"] as? String
        self.code = dictionary?["code"] as? Int
        self.data = dictionary?["data"] as? String
        self.raw_log = dictionary?["raw_log"] as? String
        self.info = dictionary?["info"] as? String
        self.gas_wanted = dictionary?["gas_wanted"] as? String
        self.gas_used = dictionary?["gas_used"] as? String
        self.timestamp = dictionary?["timestamp"] as? String
        self.tx = dictionary?["tx"] as? NSDictionary
        self.logs = dictionary?["logs"] as? NSDictionary
    }
    
}
