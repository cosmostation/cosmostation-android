//
//  ApiHistoryCustom.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/02/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct ApiHistoryCustom {
    var id: Int64?
    var chain_id: String?
    var height: Int64?
    var code: Int?
    var tx_hash: String?
    var messages: String?
    var timestamp: String?
    
    
    init(_ dictionary: NSDictionary?) {
        self.id = dictionary?["id"] as? Int64
        self.chain_id = dictionary?["chain_id"] as? String
        self.height = dictionary?["height"] as? Int64
        self.code = dictionary?["code"] as? Int
        self.tx_hash = dictionary?["tx_hash"] as? String
        self.messages = dictionary?["messages"] as? String
        self.timestamp = dictionary?["timestamp"] as? String
    }
    
    public func getMsgs() -> Array<NSDictionary>? {
        let start = messages!.index(messages!.startIndex, offsetBy: 0)
        let end = messages!.index(messages!.endIndex, offsetBy: 0)
        let range = start..<end
        let mySubstring = messages![range]
        return convertToArray(text: String(mySubstring))
    }
    
    public func getMsgCnt() -> Int {
        if (getMsgs() == nil) {
            return 0
        } else {
            return getMsgs()!.count;
        }
    }
    
    public func isSuccess() -> Bool {
        if (code != nil && code! > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public func getMsgType(_ address: String) -> String {
        var result = NSLocalizedString("tx_known", comment: "")
        if (getMsgCnt() == 0) {
            return result;
            
        } else {
            if (getMsgCnt() == 2) {
                let msgType0 = getMsgs()![1].object(forKey: "@type") as! String
                let msgType1 = getMsgs()![2].object(forKey: "@type") as! String
                if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate")) {
                    result = NSLocalizedString("tx_reinvest", comment: "")
                }
            }
            
            let msgType = getMsgs()![0].object(forKey: "@type") as! String
            if (msgType.contains("MsgDelegate")) {
                result = NSLocalizedString("tx_delegate", comment: "")
                
            } else if (msgType.contains("MsgUndelegate")) {
                result = NSLocalizedString("tx_undelegate", comment: "")
                
            } else if (msgType.contains("MsgWithdrawDelegatorReward")) {
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
                
            } else if (msgType.contains("MsgSetWithdrawAddress")) {
                result = NSLocalizedString("tx_change_reward_address", comment: "")
                
            } else if (msgType.contains("MsgCreateValidator")) {
                result = NSLocalizedString("tx_create_validator", comment: "")
                
            } else if (msgType.contains("MsgEditValidator")) {
                result = NSLocalizedString("tx_edit_validator", comment: "")
                
            } else if (msgType.contains("MsgSubmitProposal")) {
                result = NSLocalizedString("tx_submit_proposal", comment: "")
                
            } else if (msgType.contains("MsgUnjail")) {
                result = NSLocalizedString("tx_unjail_validator", comment: "")
                
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
            
        }
        
        return result
    }
        
    func convertToDictionary(text: String) -> [String: Any]? {
        if let data = text.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any]
            } catch {
                print(error.localizedDescription)
            }
        }
        return nil
    }
    
    func convertToArray(text: String) -> Array<NSDictionary>? {
        if let data = text.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as? Array<NSDictionary>
            } catch {
                print(error.localizedDescription)
            }
        }
        return nil
    }
}
