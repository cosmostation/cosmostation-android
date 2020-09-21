//
//  WUtils.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import UIKit

class WUtils {
    
    static let handler18 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 18, raiseOnExactness: true, raiseOnOverflow: false, raiseOnUnderflow: true, raiseOnDivideByZero: true)
    
    static let handler12 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 12, raiseOnExactness: true, raiseOnOverflow: false, raiseOnUnderflow: true, raiseOnDivideByZero: true)
    
    static let handler8 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 8, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler6 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 6, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler4Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 4, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler2 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 2, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler2Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 2, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0Up = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.up, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    
    static func getDivideHandler(_ decimal:Int16) -> NSDecimalNumberHandler{
        return NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: decimal, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    }

    
    static func getAccountWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Account {
        let result = account
        if (accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY || accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) {
            result.account_address = accountInfo.value.address
            result.account_sequence_number = Int64(accountInfo.value.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.account_number) ?? 0
        } else {
            result.account_address = accountInfo.value.BaseVestingAccount.BaseAccount.address
            result.account_sequence_number = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.account_number) ?? 0
        }
        return result
    }
    
    static func getAccountWithBnbAccountInfo(_ account: Account, _ accountInfo: BnbAccountInfo) -> Account {
        let result = account
        result.account_address = accountInfo.address
        result.account_sequence_number = Int64(accountInfo.sequence)
        result.account_account_numner = Int64(accountInfo.account_number)
        return result
    }
    
    static func getAccountWithKavaAccountInfo(_ account: Account, _ accountInfo: KavaAccountInfo) -> Account {
        let result = account
        if (accountInfo.result.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            result.account_address = accountInfo.result.value.address
            result.account_sequence_number = Int64(accountInfo.result.value.sequence)!
            result.account_account_numner = Int64(accountInfo.result.value.account_number)!
        } else if (accountInfo.result.type == COSMOS_AUTH_TYPE_VESTING_ACCOUNT || accountInfo.result.type == COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT) {
            result.account_address = accountInfo.result.value.address
            result.account_sequence_number = Int64(accountInfo.result.value.sequence)!
            result.account_account_numner = Int64(accountInfo.result.value.account_number)!
        }
        return result
    }
    
    static func getBalancesWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT ||
            accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY ||
            accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) {
            for coin in accountInfo.value.coins {
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
            
        } else {
            for coin in accountInfo.value.BaseVestingAccount.BaseAccount.coins {
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    static func getBalancesWithAccountInfo(_ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT ||
            accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY ||
            accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) {
            for coin in accountInfo.value.coins {
                result.append(Balance.init(-1, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
            
        } else {
            for coin in accountInfo.value.BaseVestingAccount.BaseAccount.coins {
                result.append(Balance.init(-1, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    
    
    static func getBalancesWithBnbAccountInfo(_ account: Account, _ accountInfo: BnbAccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        for bnbBalance in accountInfo.balances {
            result.append(Balance(account.account_id, bnbBalance.symbol, bnbBalance.free, Date().millisecondsSince1970, bnbBalance.frozen, bnbBalance.locked))
        }
        return result;
    }
    
    static func getBalancesWithKavaAccountInfo(_ account: Account, _ accountInfo: KavaAccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if (accountInfo.result.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            accountInfo.result.value.coins.forEach({ (coin) in
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            })
            
        } else if (accountInfo.result.type == COSMOS_AUTH_TYPE_VESTING_ACCOUNT || accountInfo.result.type == COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT) {
            var dpBalance = NSDecimalNumber.zero
            var dpVesting = NSDecimalNumber.zero
            var originalVesting = NSDecimalNumber.zero
            var remainVesting = NSDecimalNumber.zero
            var delegatedVesting = NSDecimalNumber.zero
            
            accountInfo.result.value.coins.forEach({ (coin) in
                if (coin.denom == KAVA_MAIN_DENOM) {
                    dpBalance = NSDecimalNumber.init(string: coin.amount)
                    accountInfo.result.value.original_vesting.forEach({ (coin) in
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    })
                    
                    accountInfo.result.value.delegated_vesting.forEach({ (coin) in
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    })
                    
                    if (SHOW_LOG) {
                        print("dpBalance            ", dpBalance)
                        print("originalVesting      ", originalVesting)
                        print("delegatedVesting     ", delegatedVesting)
                    }
                    
                    remainVesting = accountInfo.result.getCVestingSum()
                    if (SHOW_LOG) { print("remainVesting            ", remainVesting)}
                    
                    dpVesting = remainVesting.subtracting(delegatedVesting);
                    if (SHOW_LOG) { print("dpVesting      ", dpVesting) }
                    
                    if (dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0) {
                        dpVesting = NSDecimalNumber.zero;
                    }
                    if (SHOW_LOG) { print("dpVesting1      ", dpVesting) }
                    
                    if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                        dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                    }
                    if (SHOW_LOG) { print("dpBalance      ", dpBalance) }
                    
                    result.append(Balance.init(account.account_id, coin.denom, dpBalance.stringValue, Date().millisecondsSince1970, delegatedVesting.stringValue, dpVesting.stringValue))
                    
                } else {
                    result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
                }

            })
        }
        return result;
    }
    
    static func getBalancesWithOkAccountInfo(_ account: Account, _ accountToken: OkAccountToken) -> Array<Balance> {
        var result = Array<Balance>()
        for okBalance in accountToken.data.currencies {
             result.append(Balance(account.account_id, okBalance.symbol, okBalance.available, Date().millisecondsSince1970, "0", okBalance.locked))
        }
        return result;
    }
    
    static func getBondingwithBondingInfo(_ account: Account, _ rawbondinginfos: Array<NSDictionary>, _ chain:ChainType) -> Array<Bonding> {
        var result = Array<Bonding>()
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST ||
            chain == ChainType.BAND_MAIN || chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST || chain == ChainType.CERTIK_TEST) {
            for raw in rawbondinginfos{
                let bondinginfo = BondingInfo(raw as! [String : Any])
                result.append(Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970))
            }
        } else if (chain == ChainType.IRIS_MAIN) {
            for raw in rawbondinginfos{
                let bondinginfo = BondingInfo(raw as! [String : Any])
                let shareAmount = plainStringToDecimal(bondinginfo.shares).multiplying(byPowerOf10: 18)
                result.append(Bonding(account.account_id, bondinginfo.validator_addr, shareAmount.stringValue, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    static func getUnbondingwithUnbondingInfo(_ account: Account, _ rawunbondinginfos: Array<NSDictionary>, _ chain:ChainType) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST ||
            chain == ChainType.BAND_MAIN || chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST || chain == ChainType.CERTIK_TEST) {
            for raw in rawunbondinginfos {
                let unbondinginfo = UnbondingInfo(raw as! [String : Any])
                for entry in unbondinginfo.entries {
                    result.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
                }
            }
        } else if (chain == ChainType.IRIS_MAIN) {
            for raw in rawunbondinginfos {
                let unbondinginfo = UnbondingInfo(raw as! [String : Any])
                let unbondingBalance = plainStringToDecimal(unbondinginfo.balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: handler0)
                let initialBalance = plainStringToDecimal(unbondinginfo.initial_balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: handler0)
                result.append(Unbonding(account.account_id, unbondinginfo.validator_addr, unbondinginfo.creation_height, nodeTimeToInt64(input: unbondinginfo.min_time).millisecondsSince1970, initialBalance.stringValue, unbondingBalance.stringValue, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    
    static func nodeTimeToInt64(input: String) -> Date {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        return nodeFormatter.date(from: input) ?? Date.init()
    }
    
    static func nodeTimetoString(input: String?) -> String {
        if (input == nil) { return ""}
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let fullDate = nodeFormatter.date(from: input!)
        return localFormatter.string(from: fullDate!)
    }
    
    static func txTimeToInt64(input: String) -> Date {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        return nodeFormatter.date(from: input) ?? Date.init()
    }
    
    static func txTimetoString(input: String) -> String {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let fullDate = nodeFormatter.date(from: input)
        return localFormatter.string(from: fullDate!)
    }
    
    static func longTimetoString(input: Int64) -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let fullDate = Date.init(milliseconds: Int(input))
        return localFormatter.string(from: fullDate)
    }
    
    static func vestingTimeToString(_ startTime:Int64, _ vesting: KavaAccountInfo.VestingPeriod) -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        let unlockTime = (startTime + vesting.length) * 1000
        
        let fullDate = Date.init(milliseconds: Int(unlockTime))
        return localFormatter.string(from: fullDate)
    }
    
    static func unbondingDateFromNow(_ date:Int) -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let afterDate = Calendar.current.date(
            byAdding: .day,
            value: +date,
            to: Date())
        return localFormatter.string(from: afterDate!)
    }
    
    static func getUnbondingTimeleft(_ input: Int64) -> String {
        let secondsLeft = Int(Date().timeIntervalSince(Date.init(milliseconds: Int(input)))) * -1
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        if secondsLeft < minute {
            return "Soon"
        } else if secondsLeft < hour {
            return "(\(secondsLeft / minute) minutes remaining)"
        } else if secondsLeft < day {
            return "(\(secondsLeft / hour) hours remaining)"
        } else {
            return "(\(secondsLeft / day) days remaining)"
        }
    }
    
    static func timeGap(input: String?) -> String {
        if (input == nil) { return "-"}
        let secondsAgo = Int(Date().timeIntervalSince(nodeTimeToInt64(input: input!)))
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        
        if secondsAgo < minute {
            return "(\(secondsAgo) seconds ago)"
        } else if secondsAgo < hour {
            return "(\(secondsAgo / minute) minutes ago)"
        } else if secondsAgo < day {
            return "(\(secondsAgo / hour) hours ago)"
        } else {
            return "(\(secondsAgo / day) days ago)"
        }
    }
    
    static func txTimeGap(input: String) -> String {
        let secondsAgo = Int(Date().timeIntervalSince(txTimeToInt64(input: input)))
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        
        if secondsAgo < minute {
            return "(\(secondsAgo) seconds ago)"
        } else if secondsAgo < hour {
            return "(\(secondsAgo / minute) minutes ago)"
        } else if secondsAgo < day {
            return "(\(secondsAgo / hour) hours ago)"
        } else {
            return "(\(secondsAgo / day) days ago)"
        }
    }
    
    static func vestingTimeGap(_ startTime:Int64, _ vesting: KavaAccountInfo.VestingPeriod) -> String {
        let unlockTime = (startTime + vesting.length) * 1000
        let secondsLeft = Int(Date().timeIntervalSince(Date.init(milliseconds: Int(unlockTime)))) * -1
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        if secondsLeft < minute {
            return "Soon"
        } else if secondsLeft < hour {
            return "(\(secondsLeft / minute) minutes remaining)"
        } else if secondsLeft < day {
            return "(\(secondsLeft / hour) hours remaining)"
        } else {
            return "(\(secondsLeft / day) days remaining)"
        }
    }
    
    
    
    static func historyTitle(_ msgs:Array<Msg>, _ myaddress:String) -> String {
        var resultMsg = NSLocalizedString("tx_known", comment: "")

        if (msgs == nil || msgs.count <= 0) {
            return resultMsg
        }
        
        if(msgs.count == 2) {
            if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_DEL && msgs[1].type == COSMOS_MSG_TYPE_DELEGATE) {
                resultMsg = NSLocalizedString("tx_reinvest", comment: "")
                return resultMsg
            }
            if (msgs[0].type == IRIS_MSG_TYPE_WITHDRAW && msgs[1].type == IRIS_MSG_TYPE_DELEGATE) {
                resultMsg = NSLocalizedString("tx_reinvest", comment: "")
                return resultMsg
            }
        }
        
        if (msgs[0].type == COSMOS_MSG_TYPE_TRANSFER || msgs[0].type == COSMOS_MSG_TYPE_TRANSFER2 || msgs[0].type == IRIS_MSG_TYPE_TRANSFER) {
            if (msgs[0].value.from_address != nil && msgs[0].value.from_address == myaddress) {
                resultMsg = NSLocalizedString("tx_send", comment: "")
            } else if (msgs[0].value.to_address != nil && msgs[0].value.to_address == myaddress) {
                resultMsg = NSLocalizedString("tx_receive", comment: "")
            } else {
                if (msgs[0].value.inputs != nil && msgs[0].value.inputs!.count > 0) {
                    for input in msgs[0].value.inputs! {
                        if (input.address == myaddress) {
                            resultMsg = NSLocalizedString("tx_send", comment: "")
                            return resultMsg
                        }
                    }
                }
                if (msgs[0].value.outputs != nil && msgs[0].value.outputs!.count > 0) {
                    for input in msgs[0].value.outputs! {
                        if (input.address == myaddress) {
                            resultMsg = NSLocalizedString("tx_receive", comment: "")
                            return resultMsg
                        }
                    }
                }
                resultMsg = NSLocalizedString("tx_transfer", comment: "")
            }
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_DELEGATE || msgs[0].type == IRIS_MSG_TYPE_DELEGATE ) {
            resultMsg = NSLocalizedString("tx_delegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_UNDELEGATE || msgs[0].type == COSMOS_MSG_TYPE_UNDELEGATE2 || msgs[0].type == IRIS_MSG_TYPE_UNDELEGATE) {
            resultMsg = NSLocalizedString("tx_undelegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_REDELEGATE || msgs[0].type == COSMOS_MSG_TYPE_REDELEGATE2 || msgs[0].type == IRIS_MSG_TYPE_REDELEGATE) {
            resultMsg = NSLocalizedString("tx_redelegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_DEL || msgs[0].type == IRIS_MSG_TYPE_WITHDRAW) {
            resultMsg = NSLocalizedString("tx_get_reward", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_VAL) {
            resultMsg = NSLocalizedString("tx_get_commission", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || msgs[0].type == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
            resultMsg = NSLocalizedString("tx_change_reward_address", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_VOTE || msgs[0].type == IRIS_MSG_TYPE_VOTE) {
            resultMsg = NSLocalizedString("tx_vote", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_SUBMIT_PROPOSAL || msgs[0].type == IRIS_MSG_TYPE_SUBMIT_PROPOSAL) {
            resultMsg = NSLocalizedString("tx_submit_proposal", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_DEPOSIT || msgs[0].type == IRIS_MSG_TYPE_DEPOSIT) {
            resultMsg = NSLocalizedString("tx_deposit", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_CREATE_VALIDATOR || msgs[0].type == IRIS_MSG_TYPE_CREATE_VALIDATOR) {
            resultMsg = NSLocalizedString("tx_create_validator", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_EDIT_VALIDATOR) {
            resultMsg = NSLocalizedString("tx_edit_validator", comment: "")
            
        } else if (msgs[0].type == IRIS_MSG_TYPE_WITHDRAW_ALL) {
            resultMsg = NSLocalizedString("tx_get_reward_all", comment: "")
            
        } else if (msgs[0].type == IRIS_MSG_TYPE_ISSUE_TOKEN) {
            resultMsg = NSLocalizedString("tx_issue_token", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_TRANSFER3) {
            resultMsg = NSLocalizedString("tx_transfer", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_POST_PRICE) {
            resultMsg = NSLocalizedString("tx_kava_post_price", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CREATE_CDP) {
            resultMsg = NSLocalizedString("tx_kava_create_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_DEPOSIT_CDP) {
            resultMsg = NSLocalizedString("tx_kava_deposit_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_WITHDRAW_CDP) {
            resultMsg = NSLocalizedString("tx_kava_withdraw_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
            resultMsg = NSLocalizedString("tx_kava_drawdebt_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
            resultMsg = NSLocalizedString("tx_kava_repaydebt_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CREATE_SWAP) {
            resultMsg = NSLocalizedString("tx_kava_bep3_create", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CLAIM_SWAP) {
            resultMsg = NSLocalizedString("tx_kava_bep3_claim", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_REFUND_SWAP) {
            resultMsg = NSLocalizedString("tx_kava_bep3_refund", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_INCENTIVE_REWARD) {
            resultMsg = NSLocalizedString("tx_kava_incentive_reward", comment: "")
        }
        
        if(msgs.count > 1) {
            resultMsg = resultMsg +  "\n+ " + String(msgs.count - 1)
        }
        return resultMsg
    }
    
    static func bnbHistoryTitle(_ bnbHistory:BnbHistory, _ myaddress:String) -> String {
        var resultMsg = NSLocalizedString("tx_known", comment: "")
        if (bnbHistory.txType == "NEW_ORDER") {
            resultMsg = NSLocalizedString("tx_new_order", comment: "")
            
        } else if (bnbHistory.txType == "CANCEL_ORDER") {
            resultMsg = NSLocalizedString("tx_cancel_order", comment: "")
            
        } else if (bnbHistory.txType == "TRANSFER") {
            if (bnbHistory.fromAddr == myaddress) {
                resultMsg = NSLocalizedString("tx_send", comment: "")
            } else {
                resultMsg = NSLocalizedString("tx_receive", comment: "")
            }
        } else if (bnbHistory.txType == "HTL_TRANSFER") {
            if (bnbHistory.fromAddr == myaddress) {
                resultMsg = NSLocalizedString("tx_send_htlc", comment: "")
            } else if (bnbHistory.toAddr == myaddress) {
                resultMsg = NSLocalizedString("tx_receive_htlc", comment: "")
            } else {
                resultMsg = NSLocalizedString("tx_create_htlc", comment: "")
            }
            
        } else if (bnbHistory.txType == "CLAIM_HTL") {
            resultMsg = NSLocalizedString("tx_claim_htlc", comment: "")
            
        } else if (bnbHistory.txType == "REFUND_HTL") {
            resultMsg = NSLocalizedString("tx_refund_htlc", comment: "")
        }
        return resultMsg
    }
    
    
    static func proposalType(_ type:String) -> String {
        if (type == IRIS_PROPOAL_TYPE_BasicProposal) {
            return NSLocalizedString("proposal_type_basic", comment: "")
            
        } else if (type == IRIS_PROPOAL_TYPE_ParameterProposal) {
            return NSLocalizedString("proposal_type_parameter", comment: "")
            
        } else if (type == IRIS_PROPOAL_TYPE_PlainTextProposal) {
            return NSLocalizedString("proposal_type_plaintext", comment: "")
                   
        } else if (type == IRIS_PROPOAL_TYPE_TokenAdditionProposal) {
            return NSLocalizedString("proposal_type_tokenaddition", comment: "")
                  
        } else if (type == IRIS_PROPOAL_TYPE_SoftwareUpgradeProposal) {
            return NSLocalizedString("proposal_type_softwareupgrade", comment: "")
                 
        } else if (type == IRIS_PROPOAL_TYPE_SystemHaltProposal) {
            return NSLocalizedString("proposal_type_systemhalt", comment: "")
                
        } else if (type == IRIS_PROPOAL_TYPE_CommunityTaxUsageProposal) {
            return NSLocalizedString("proposal_type_communitytaxusage", comment: "")
               
        }
        return ""
    }
    
    
    static func checkNAN(_ check: NSDecimalNumber) -> NSDecimalNumber{
        if(check.isEqual(to: NSDecimalNumber.notANumber)) {
            return NSDecimalNumber.zero
        }
        return check
    }
    
    static func decimalNumberToLocaleString(_ input: NSDecimalNumber, _ deciaml:Int16) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 0
        nf.maximumFractionDigits = Int(deciaml)
        nf.numberStyle = .decimal
        nf.locale = Locale.current
        nf.groupingSeparator = ""
        return nf.string(from: input)!
    }
    
    static func localeStringToDecimal(_ input: String?) -> NSDecimalNumber {
        let result = NSDecimalNumber(string: input, locale: Locale.current)
        if (NSDecimalNumber.notANumber == result) {
            return NSDecimalNumber.zero
        } else {
            return result
        }
    }
    
    static func plainStringToDecimal(_ input: String?) -> NSDecimalNumber {
        let result = NSDecimalNumber(string: input)
        if (NSDecimalNumber.notANumber == result) {
            return NSDecimalNumber.zero
        } else {
            return result
        }
    }
    
    static func displayAmount(_ amount: String?, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal

        let amount = localeStringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(deciaml), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)

        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else if (chain == ChainType.COSMOS_MAIN || chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST ||
            chain == ChainType.BAND_MAIN || chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            formatted = nf.string(from: amount.dividing(by: 1000000).rounding(accordingToBehavior: handler))
        } else if (chain == ChainType.IRIS_MAIN) {
            formatted = nf.string(from: amount.dividing(by: 1000000000000000000).rounding(accordingToBehavior: handler))
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            formatted = nf.string(from: amount.rounding(accordingToBehavior: handler))
        }

        let added       = formatted
        let endIndex    = added!.index(added!.endIndex, offsetBy: -deciaml)

        let preString   = added![..<endIndex]
        let postString  = added![endIndex...]

        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]

        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])

        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayAmount2(_ amount: String?, _ font:UIFont, _ inputPoint:Int16, _ dpPoint:Int16) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = Int(dpPoint)
        nf.maximumFractionDigits = Int(dpPoint)
        nf.numberStyle = .decimal
        
        let amount = plainStringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(dpPoint), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
        
        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else {
            formatted = nf.string(from: amount.multiplying(byPowerOf10: -Int16(inputPoint), withBehavior: handler))
        }
        
        let added       = formatted
        let endIndex    = added!.index(added!.endIndex, offsetBy: -dpPoint)
        
        let preString   = added![..<endIndex]
        let postString  = added![endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func getFormattedNumber(_ amount: NSDecimalNumber, _ dpPoint:Int16) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = Int(dpPoint)
        nf.locale = Locale(identifier: "en_US")
        nf.numberStyle = .decimal
        
        let formatted = nf.string(from: amount)?.replacingOccurrences(of: ",", with: "" )
        return formatted!
    }
    
    static func dpTokenAvailable(_ balances:Array<Balance>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_amount)
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    static func availableAmount(_ balances:Array<Balance>, _ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_amount)
            }
        }
        return amount;
    }
    
    static func lockedAmount(_ balances:Array<Balance>, _ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_locked)
            }
        }
        return amount;
    }
    
    static func okDepositAmount(_ deposit:OkDeposit) -> NSDecimalNumber {
        return plainStringToDecimal(deposit.tokens)
    }
    
    static func okWithdrawAmount(_ withdraw:OkWithdraw) -> NSDecimalNumber {
        return plainStringToDecimal(withdraw.quantity)
    }
    
    static func dpVestingCoin(_ balances:Array<Balance>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_locked)
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    
    static func dpDeleagted(_ bondings:Array<Bonding>, _ validators:Array<Validator>,_ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    static func deleagtedAmount(_ bondings:Array<Bonding>, _ validators:Array<Validator>, _ chain:ChainType) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        return amount
    }
    
    static func dpUnbondings(_ unbondings:Array<Unbonding>, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for unbonding in unbondings {
            amount = amount.adding(plainStringToDecimal(unbonding.unbonding_balance))
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    static func unbondingAmount(_ unbondings:Array<Unbonding>, _ chain:ChainType) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for unbonding in unbondings {
            amount = amount.adding(localeStringToDecimal(unbonding.unbonding_balance))
        }
        return amount
    }
    
    static func dpRewards(_ rewards:Array<Reward>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) ->  NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == symbol) {
                    amount = amount.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain)
    }
    
    static func rewardAmount(_ rewards:Array<Reward>, _ symbol:String, _ chain:ChainType) ->  NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == symbol) {
                    amount = amount.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return amount
    }
    
    static func dpIrisRewards(_ rewards:IrisRewards?, _ font:UIFont, _ deciaml:Int, _ chain:ChainType ) ->  NSMutableAttributedString {
        if (rewards != nil && (rewards?.delegations.count)! > 0) {
            return displayAmount((rewards?.getSimpleIrisReward().stringValue)!, font, deciaml, chain)
        } else {
            return displayAmount(NSDecimalNumber.zero.stringValue, font, deciaml, chain)
        }
    }
    
    
    
    static func dpAllAtom(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) ->  NSMutableAttributedString {
        return displayAmount(getAllAtom(balances, bondings, unbondings, rewards, validators).stringValue, font, deciaml, chain)
    }
    
    static func dpAllIris(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:IrisRewards?, _ validators:Array<Validator>, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        return displayAmount(getAllIris(balances, bondings, unbondings, rewards, validators).stringValue, font, deciaml, chain)
    }
    
    static func dpAllAtomValue(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>, _ price:Double?, _ font:UIFont) ->  NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        var amount = getAllAtom(balances, bondings, unbondings, rewards, validators)
        if (BaseData.instance.getCurrency() == 5) {
            amount = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000")).multiplying(by: amount, withBehavior: WUtils.handler8)
        } else {
            amount = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000")).multiplying(by: amount, withBehavior: WUtils.handler2Down)
        }
        return dpValue(amount, font)
    }
    
    static func dpAtomValue(_ amount:NSDecimalNumber, _ price:Double?, _ font:UIFont) ->  NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        var result = NSDecimalNumber.zero
        if (BaseData.instance.getCurrency() == 5) {
            result = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000")).multiplying(by: amount, withBehavior: WUtils.handler8)
        } else {
            result = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000")).multiplying(by: amount, withBehavior: WUtils.handler2Down)
        }
        return dpValue(result, font)
    }
    
    static func dpAllIrisValue(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:IrisRewards?, _ validators:Array<Validator>, _ price:Double?, _ font:UIFont) -> NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        var amount = getAllIris(balances, bondings, unbondings, rewards, validators)
        if (BaseData.instance.getCurrency() == 5) {
            amount = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000000000000000")).multiplying(by: amount, withBehavior: WUtils.handler8)
        } else {
            amount = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000000000000000")).multiplying(by: amount, withBehavior: WUtils.handler2Down)
        }
        return dpValue(amount, font)
    }
    
    static func dpIrisValue(_ amount:NSDecimalNumber, _ price:Double?, _ font:UIFont) ->  NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        var result = NSDecimalNumber.zero
        if (BaseData.instance.getCurrency() == 5) {
            result = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000000000000000")).multiplying(by: amount, withBehavior: WUtils.handler8)
        } else {
            result = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000000000000000")).multiplying(by: amount, withBehavior: WUtils.handler2Down)
        }
        return dpValue(result, font)
    }

    static func dpBnbValue(_ amount:NSDecimalNumber, _ price:Double?, _ font:UIFont) ->  NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        var result = NSDecimalNumber.zero
        if (BaseData.instance.getCurrency() == 5) {
            result = NSDecimalNumber(value: price!).multiplying(by: amount, withBehavior: WUtils.handler8)
        } else {
            result = NSDecimalNumber(value: price!).multiplying(by: amount, withBehavior: WUtils.handler2Down)
        }
        return dpValue(result, font)
    }
    
    static func dpPricePerUnit(_ price:Double?, _ font:UIFont) -> NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        return dpValue(NSDecimalNumber(value: price!), font)
    }
    
    static func dpTokenValue(_ amount: NSDecimalNumber, _ price:Double?, _ divide:Int16, _ font:UIFont) -> NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        var result = NSDecimalNumber.zero
        if (BaseData.instance.getCurrency() == 5) {
            result = NSDecimalNumber(value: price!).multiplying(byPowerOf10: -divide).multiplying(by: amount, withBehavior: WUtils.handler8)
        } else {
            result = NSDecimalNumber(value: price!).multiplying(byPowerOf10: -divide).multiplying(by: amount, withBehavior: WUtils.handler2Down)
        }
        return dpValue(result, font)
    }
    
    static func dpValue(_ amount: NSDecimalNumber, _ font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        
        var formatted = ""
        var endIndex:String.Index?
        if (BaseData.instance.getCurrency() == 5) {
            nf.minimumFractionDigits = 8
            nf.maximumFractionDigits = 8
            formatted = BaseData.instance.getCurrencySymbol() + " " + nf.string(from: amount)!
            endIndex    = formatted.index(formatted.endIndex, offsetBy: -8)
            
        } else {
            nf.minimumFractionDigits = 2
            nf.maximumFractionDigits = 2
            formatted = BaseData.instance.getCurrencySymbol() + " " + nf.string(from: amount)!
            endIndex    = formatted.index(formatted.endIndex, offsetBy: -2)
        }
        
        let preString   = formatted[..<endIndex!]
        let postString  = formatted[endIndex!...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    
    static func priceChanges(_ price:Double?) -> NSDecimalNumber {
        if (price == nil) {
            return NSDecimalNumber.zero
        } else {
            return NSDecimalNumber(value: price!)
        }
    }
    
    
    static func displayPrice(_ amount: NSDecimalNumber, _ currency:Int, _ symbol:String, _ font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        
        var formatted = ""
        var endIndex:String.Index?
        if(currency == 5) {
            nf.minimumFractionDigits = 8
            nf.maximumFractionDigits = 8
            formatted = symbol + " " + nf.string(from: amount)!
            endIndex    = formatted.index(formatted.endIndex, offsetBy: -8)
    
        } else {
            nf.minimumFractionDigits = 2
            nf.maximumFractionDigits = 2
            formatted = symbol + " " + nf.string(from: amount)!
            endIndex    = formatted.index(formatted.endIndex, offsetBy: -2)
        }
        
        let preString   = formatted[..<endIndex!]
        let postString  = formatted[endIndex!...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
        
    }
    
    
    static func displayGasRate(_ rate: NSDecimalNumber, font:UIFont, _ deciaml:Int) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate)!
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -(deciaml))
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayPriceUPdown(_ updown:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: updown.rounding(accordingToBehavior: handler2))! + "% (24h)"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -9)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayPercent(_ rate:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate.rounding(accordingToBehavior: handler2Down))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayInflation(_ rate:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate.multiplying(by: 100).rounding(accordingToBehavior: handler2Down))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayYield(_ bonded:NSDecimalNumber, _ provision:NSDecimalNumber, _ commission:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.one.subtracting(commission))).multiplying(by: 100))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
        
    }
    
    static func displayDailyReturns(_ bonded:NSDecimalNumber, _ provision:NSDecimalNumber, _ commission:NSDecimalNumber, _ delegated:NSDecimalNumber, font:UIFont, baseChain:ChainType) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        var formatted = ""
        var endIndex: String.Index?
        if (baseChain == ChainType.COSMOS_MAIN || baseChain == ChainType.KAVA_MAIN || baseChain == ChainType.KAVA_TEST ||
            baseChain == ChainType.BAND_MAIN || baseChain == ChainType.IOV_MAIN || baseChain == ChainType.IOV_TEST) {
            nf.minimumFractionDigits = 12
            nf.maximumFractionDigits = 12
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.one.subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "365000000"), withBehavior: handler12)) ?? "0"
            
            endIndex = formatted.index(formatted.endIndex, offsetBy: -12)
        } else if (baseChain == ChainType.IRIS_MAIN) {
            nf.minimumFractionDigits = 18
            nf.maximumFractionDigits = 18
            
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.one.subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "365000000000000000000"), withBehavior: handler18)) ?? "0"
            print("formatted ", formatted)
            endIndex = formatted.index(formatted.endIndex, offsetBy: -18)
        }
        
        let preString   = formatted[..<endIndex!]
        let postString  = formatted[endIndex!...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayMonthlyReturns(_ bonded:NSDecimalNumber, _ provision:NSDecimalNumber, _ commission:NSDecimalNumber, _ delegated:NSDecimalNumber, font:UIFont, baseChain:ChainType) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        var formatted = ""
        var endIndex: String.Index?
        if (baseChain == ChainType.COSMOS_MAIN || baseChain == ChainType.KAVA_MAIN || baseChain == ChainType.KAVA_TEST ||
            baseChain == ChainType.BAND_MAIN || baseChain == ChainType.IOV_MAIN || baseChain == ChainType.IOV_TEST) {
            nf.minimumFractionDigits = 12
            nf.maximumFractionDigits = 12
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.one.subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "12000000"), withBehavior: handler12)) ?? "0"
            endIndex = formatted.index(formatted.endIndex, offsetBy: -12)
        } else if (baseChain == ChainType.IRIS_MAIN) {
            nf.minimumFractionDigits = 18
            nf.maximumFractionDigits = 18
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.one.subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "12000000000000000000"), withBehavior: handler18)) ?? "0"
            endIndex = formatted.index(formatted.endIndex, offsetBy: -18)
        }
        
        let preString   = formatted[..<endIndex!]
        let postString  = formatted[endIndex!...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    
    static func displayCommission(_ rate:String, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: NSDecimalNumber.init(string: rate).multiplying(by: 100))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displaySelfBondRate(_ selfShare: String, _ totalShare: String, _ font:UIFont ) ->  NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let selfDecimal = localeStringToDecimal(selfShare)
        let totalDecimal = localeStringToDecimal(totalShare)
        
        let formatted   = nf.string(from: selfDecimal.multiplying(by: 100).dividing(by: totalDecimal, withBehavior: handler2))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func getAllRewardByDenom(_ rewards:Array<Reward>, _ denom:String) -> NSDecimalNumber {
        var rewardSum = NSDecimalNumber.zero
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == denom) {
                    rewardSum = rewardSum.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return rewardSum
    }
    
    static func getValidatorReward(_ rewards:Array<Reward>, _ valOpAddr:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for reward in rewards {
            if (reward.reward_v_address == valOpAddr && reward.reward_amount.count > 0) {
                result = localeStringToDecimal(reward.reward_amount[0].amount)
                break;
            }
        }
        return result
    }
    
    static func getAllAtom(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == COSMOS_MAIN_DENOM) {
                amount = localeStringToDecimal(balance.balance_amount)
            }
        }
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            amount = amount.adding(localeStringToDecimal(unbonding.unbonding_balance))
        }
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == COSMOS_MAIN_DENOM) {
                    amount = amount.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return amount
    }
    
    static func getAllBand(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == BAND_MAIN_DENOM) {
                amount = localeStringToDecimal(balance.balance_amount)
            }
        }
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            amount = amount.adding(localeStringToDecimal(unbonding.unbonding_balance))
        }
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == BAND_MAIN_DENOM) {
                    amount = amount.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return amount
    }
    
    static func getAllIov(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == IOV_MAIN_DENOM || balance.balance_denom == IOV_TEST_DENOM) {
                amount = localeStringToDecimal(balance.balance_amount)
            }
        }
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            amount = amount.adding(localeStringToDecimal(unbonding.unbonding_balance))
        }
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == IOV_MAIN_DENOM || coin.denom == IOV_TEST_DENOM) {
                    amount = amount.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return amount
    }
    
    static func getAllOkt(_ balances:Array<Balance>, _ deposit:OkDeposit, _ withdraw:OkWithdraw) -> NSDecimalNumber {
        var sum = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == OKEX_TEST_DENOM) {
                sum = sum.adding(localeStringToDecimal(balance.balance_amount))
                sum = sum.adding(localeStringToDecimal(balance.balance_locked))
            }
        }
        sum = sum.adding(localeStringToDecimal(deposit.tokens))
        sum = sum.adding(localeStringToDecimal(withdraw.quantity))
        return sum
    }
    
    static func getAllIris(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:IrisRewards?, _ validators:Array<Validator>) ->  NSDecimalNumber {
        var sum = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == IRIS_MAIN_DENOM) {
                sum = localeStringToDecimal(balance.balance_amount)
            }
        }
        for bonding in bondings {
            sum = sum.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            sum = sum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
        }
        if (rewards != nil) {
            sum = sum.adding(rewards!.getSimpleIrisReward())
        }
        return sum
    }
    
    static func getAllBnb(_ balance:Balance?) ->  NSDecimalNumber {
        if (balance == nil) {
            return NSDecimalNumber.zero
        }
        return plainStringToDecimal(balance!.balance_amount).adding(plainStringToDecimal(balance!.balance_locked))
    }
    
    static func getAllKava(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        
        for balance in balances {
            if (balance.balance_denom == KAVA_MAIN_DENOM) {
                amount = localeStringToDecimal(balance.balance_amount)
                amount = amount.adding(localeStringToDecimal(balance.balance_locked))
            }
        }
        
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        
        for unbonding in unbondings {
            amount = amount.adding(localeStringToDecimal(unbonding.unbonding_balance))
        }
        
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == KAVA_MAIN_DENOM) {
                    amount = amount.adding(localeStringToDecimal(coin.amount).rounding(accordingToBehavior: handler0Down))
                }
            }
        }
        return amount
    }
    
    static func getIrisToken(_ irisTokens:Array<IrisToken>, _ balance:Balance) -> IrisToken? {
        let split = balance.balance_denom.components(separatedBy: "-")
        for irisToken in irisTokens {
            if (split[0] == irisToken.base_token?.id) {
                return irisToken
            }
        }
        return nil
    }
    
    static func getIrisMainToken(_ irisTokens:Array<IrisToken>) -> IrisToken? {
        for irisToken in irisTokens {
            if (irisToken.base_token?.id == "iris") {
                return irisToken
            }
        }
        return nil
    }
    
    static func getBnbToken(_ bnbTokens:Array<BnbToken>, _ balance:Balance) -> BnbToken? {
        for bnbToken in bnbTokens {
            if (bnbToken.symbol == balance.balance_denom) {
                return bnbToken
            }
        }
        return nil
    }
    
    static func getBnbMainToken(_ bnbTokens:Array<BnbToken>) -> BnbToken? {
        for bnbToken in bnbTokens {
            if (bnbToken.symbol == BNB_MAIN_DENOM) {
                return bnbToken
            }
        }
        return nil
    }
    
    static func getOkToken(_ okTokenList:OkTokenList, _ symbol:String) -> OkToken? {
        for okToken in okTokenList.data {
            if (okToken.symbol == symbol) {
                return okToken
            }
        }
        return nil
    }
    
    static func displayIrisToken(_ amount: String, _ font:UIFont, _ deciaml:Int16, _ deciaml2:Int16) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = Int(deciaml)
        nf.maximumFractionDigits = Int(deciaml)
        nf.numberStyle = .decimal
        
        let amount = localeStringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(deciaml), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
        
        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else {
            formatted = nf.string(from: amount.dividing(by: NSDecimalNumber(decimal: pow(10,Int(deciaml2)))).rounding(accordingToBehavior: handler))
        }
        let added       = formatted
        let endIndex    = added!.index(added!.endIndex, offsetBy: -deciaml)
        
        let preString   = added![..<endIndex]
        let postString  = added![endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    
    static func getTokenBalace(_ balances:Array<Balance>, _ symbol:String) -> Balance? {
        for balance in balances {
            if (balance.balance_denom.caseInsensitiveCompare(symbol) == .orderedSame) {
                return balance
            }
        }
        return nil
    }
    
    
    static func getTokenAmount(_ balances:Array<Balance>?, _ symbol:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (balances != nil) {
            balances!.forEach({ (balance) in
                if (balance.balance_denom.caseInsensitiveCompare(symbol) == .orderedSame) {
                    result = result.adding(WUtils.plainStringToDecimal(balance.balance_amount))
                }
            })
        }
        return result
    }
    
    static func getDelegableAmount(_ balances:Array<Balance>?, _ symbol:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (balances != nil) {
            balances!.forEach({ (balance) in
                if (balance.balance_denom.caseInsensitiveCompare(symbol) == .orderedSame) {
                    result = result.adding(WUtils.plainStringToDecimal(balance.balance_amount))
                    result = result.adding(WUtils.plainStringToDecimal(balance.balance_locked))
                }
            })
        }
        return result
    }
    
    static func showCoinDp(_ coin:Coin, _ denomLabel:UILabel, _ amountLabel:UILabel, _ chainType:ChainType) {
        if (chainType == ChainType.COSMOS_MAIN) {
            if (coin.denom == COSMOS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (coin.denom == IRIS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 18, 18)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            if (coin.denom == BNB_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 8, 8)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (coin.denom == KAVA_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, getKavaCoinDecimal(coin.denom), getKavaCoinDecimal(coin.denom))
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (coin.denom == BAND_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (coin.denom == IOV_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (coin.denom == IOV_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.OKEX_TEST) {
            if (coin.denom == OKEX_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 0, 8)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (coin.denom == CERTIK_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
        }
    }
    
    static func showCoinDp(_ denom:String, _ amount:String, _ denomLabel:UILabel, _ amountLabel:UILabel, _ chainType:ChainType) {
        if (chainType == ChainType.COSMOS_MAIN) {
            if (denom == COSMOS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (denom == IRIS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 18, 18)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            if (denom == BNB_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 8, 8)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (denom == KAVA_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, getKavaCoinDecimal(denom), getKavaCoinDecimal(denom))
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (denom == BAND_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (denom == IOV_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (denom == IOV_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.OKEX_TEST) {
            if (denom == OKEX_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 0, 8)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (denom == CERTIK_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
        }
    }
    
    static func isBnbMArketToken(_ symbol:String) ->Bool {
        switch symbol {
        case "USDT.B-B7C":
            return true
        case "ETH.B-261":
            return true
        case "BTC.B-918":
            return true
        case "USDSB-1AC":
            return true
        case "THKDB-888":
            return true
        case "TUSDB-888":
            return true
        case "BTCB-1DE":
            return true
            
        case "ETH-1C9":
            return true
        case "BUSD-BD1":
            return true
        case "IDRTB-178":
            return true
        case "TAUDB-888":
            return true
            
        default:
            return false
        }
    }
    
    static func getBnbTicSymbol(_ symbol:String) -> String {
        if (isBnbMArketToken(symbol)) {
            return BNB_MAIN_DENOM + "_" + symbol
        } else {
            return  "" + symbol + "_" + BNB_MAIN_DENOM
        }
    }
    
    static func getTicData(_ ticSymbol:String, _ tics:[String : NSMutableDictionary]) ->NSMutableDictionary? {
        for (title, data) in tics {
            if (title == ticSymbol) {
                return data
            }
        }
        return nil
    }
    
    
    
    
    static func getChainColor(_ chain:ChainType?) -> UIColor {
        if (chain == ChainType.COSMOS_MAIN) {
            return COLOR_ATOM
        } else if (chain == ChainType.IRIS_MAIN) {
            return COLOR_IRIS
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return COLOR_BNB
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return COLOR_KAVA
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return COLOR_IOV
        } else if (chain == ChainType.BAND_MAIN) {
            return COLOR_BAND
        } else if (chain == ChainType.OKEX_TEST) {
            return COLOR_OK
        } else if (chain == ChainType.CERTIK_TEST) {
            return COLOR_CERTIK
        }
        return COLOR_ATOM
    }
    
    static func getChainDarkColor(_ chain:ChainType) -> UIColor {
        if (chain == ChainType.COSMOS_MAIN) {
            return COLOR_ATOM_DARK
        } else if (chain == ChainType.IRIS_MAIN) {
            return COLOR_IRIS_DARK
        } else if (chain == ChainType.BINANCE_MAIN) {
            return COLOR_BNB_DARK
        } else if (chain == ChainType.KAVA_MAIN) {
            return COLOR_KAVA_DARK
        } else if (chain == ChainType.IOV_MAIN) {
            return COLOR_IOV_DARK
        } else if (chain == ChainType.BAND_MAIN) {
            return COLOR_BAND_DARK
        } else if (chain == ChainType.KAVA_TEST || chain == ChainType.BINANCE_TEST || chain == ChainType.IOV_TEST || chain == ChainType.OKEX_TEST || chain == ChainType.CERTIK_TEST) {
            return COLOR_DARK_GRAY
        }
        return COLOR_ATOM_DARK
    }
    
    static func getChainBg(_ chain:ChainType) -> UIColor {
        if (chain == ChainType.COSMOS_MAIN) {
            return TRANS_BG_COLOR_COSMOS
        } else if (chain == ChainType.IRIS_MAIN) {
            return TRANS_BG_COLOR_IRIS
        } else if (chain == ChainType.BINANCE_MAIN) {
            return TRANS_BG_COLOR_BNB
        } else if (chain == ChainType.KAVA_MAIN) {
            return TRANS_BG_COLOR_KAVA
        } else if (chain == ChainType.IOV_MAIN) {
            return TRANS_BG_COLOR_IOV
        } else if (chain == ChainType.BAND_MAIN) {
            return TRANS_BG_COLOR_BAND
        } else if (chain == ChainType.KAVA_TEST || chain == ChainType.BINANCE_TEST || chain == ChainType.IOV_TEST || chain == ChainType.OKEX_TEST || chain == ChainType.CERTIK_TEST) {
            return COLOR_BG_GRAY
        }
        return TRANS_BG_COLOR_COSMOS
    }
    
    static func getMainDenom(_ chain:ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return "ATOM"
        } else if (chain == ChainType.IRIS_MAIN) {
            return "IRIS"
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return "BNB"
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return "KAVA"
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return "IOV"
        } else if (chain == ChainType.BAND_MAIN) {
            return "BAND"
        } else if (chain == ChainType.OKEX_TEST) {
            return "TOKT"
        } else if (chain == ChainType.CERTIK_TEST) {
            return "CTK"
        }
        return ""
    }
    
    static func setDenomTitle(_ chain: ChainType?, _ label: UILabel) {
        if (chain == ChainType.COSMOS_MAIN) {
            label.text = "ATOM"
            label.textColor = COLOR_ATOM
        } else if (chain == ChainType.IRIS_MAIN) {
            label.text = "IRIS"
            label.textColor = COLOR_IRIS
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            label.text = "BNB"
            label.textColor = COLOR_BNB
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            label.text = "KAVA"
            label.textColor = COLOR_KAVA
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            label.text = "IOV"
            label.textColor = COLOR_IOV
        } else if (chain == ChainType.BAND_MAIN) {
            label.text = "BAND"
            label.textColor = COLOR_BAND
        } else if (chain == ChainType.OKEX_TEST) {
            label.text = "TOKT"
            label.textColor = COLOR_OK
        } else if (chain == ChainType.CERTIK_TEST) {
            label.text = "CTK"
            label.textColor = COLOR_CERTIK
        }
    }
    
    static func getChainType(_ chainS:String) -> ChainType? {
        if (chainS == CHAIN_COSMOS_S ) {
            return ChainType.COSMOS_MAIN
        } else if (chainS == CHAIN_IRIS_S) {
            return ChainType.IRIS_MAIN
        } else if (chainS == CHAIN_BINANCE_S) {
            return ChainType.BINANCE_MAIN
        } else if (chainS == CHAIN_KAVA_S) {
           return ChainType.KAVA_MAIN
        } else if (chainS == CHAIN_IOV_S) {
            return ChainType.IOV_MAIN
        } else if (chainS == CHAIN_BAND_S) {
            return ChainType.BAND_MAIN
        } else if (chainS == CHAIN_KAVA_TEST_S) {
            return ChainType.KAVA_TEST
        } else if (chainS == CHAIN_BINANCE_TEST_S) {
            return ChainType.BINANCE_TEST
        } else if (chainS == CHAIN_IOV_TEST_S) {
            return ChainType.IOV_TEST
        } else if (chainS == CHAIN_OKEX_TEST_S) {
            return ChainType.OKEX_TEST
        } else if (chainS == CHAIN_CERTIK_TEST_S) {
            return ChainType.CERTIK_TEST
        }
        return nil
    }
    
    static func getChainDBName(_ chain:ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return CHAIN_COSMOS_S
        } else if (chain == ChainType.IRIS_MAIN) {
            return CHAIN_IRIS_S
        } else if (chain == ChainType.BINANCE_MAIN) {
            return CHAIN_BINANCE_S
        } else if (chain == ChainType.KAVA_MAIN) {
            return CHAIN_KAVA_S
        } else if (chain == ChainType.IOV_MAIN) {
            return CHAIN_IOV_S
        } else if (chain == ChainType.BAND_MAIN) {
            return CHAIN_BAND_S
        } else if (chain == ChainType.BINANCE_TEST) {
            return CHAIN_BINANCE_TEST_S
        } else if (chain == ChainType.KAVA_TEST) {
            return CHAIN_KAVA_TEST_S
        } else if (chain == ChainType.IOV_TEST) {
            return CHAIN_IOV_TEST_S
        } else if (chain == ChainType.OKEX_TEST) {
            return CHAIN_OKEX_TEST_S
        } else if (chain == ChainType.CERTIK_TEST) {
            return CHAIN_CERTIK_TEST_S
        }
        return ""
    }
    
    static func getChainTypeInt(_ chainS:String) -> Int {
        if (chainS == CHAIN_COSMOS_S ) {
            return 1
        } else if (chainS == CHAIN_IRIS_S) {
            return 2
        } else if (chainS == CHAIN_BINANCE_S) {
            return 3
        }
        return 0
    }
    
    static func getMonikerName(_ validators: Array<Validator>,  _ opAddr: String, _ bracket:Bool) -> String {
        for val in validators {
            if (val.operator_address == opAddr) {
                if (bracket) {
                    return "(" + val.description.moniker + ")"
                } else {
                    return val.description.moniker
                }
            }
        }
        return ""
    }
    
    //TODO check confirm starname regular express
    static func isValidStarName(_ starname: String) -> Bool {
        let starNameRegEx = "[0-9a-z.-]{0,64}+\\*[a-z0-9.-]{3,16}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static func getChainId(_ chainS:String) -> String {
        if (chainS == CHAIN_COSMOS_S) {
            return "cosmoshub-3"
        } else if (chainS == CHAIN_IRIS_S) {
            return "irishub"
        } else if (chainS == CHAIN_BINANCE_S) {
            return "Binance-Chain-Tigris"
        } else if (chainS == CHAIN_KAVA_S) {
            return "kava-3"
        } else if (chainS == CHAIN_IOV_S) {
            return "iov-mainnet-2"
        } else if (chainS == CHAIN_BAND_S) {
            return "band-wenchang-mainnet"
        } else if (chainS == CHAIN_BINANCE_TEST_S) {
            return "Binance-Chain-Nile"
        } else if (chainS == CHAIN_KAVA_TEST_S) {
            return "kava-testnet-9000"
        } else if (chainS == CHAIN_IOV_TEST_S) {
            return "iovns-galaxynet"
        } else if (chainS == CHAIN_OKEX_TEST_S) {
            return "okexchain-testnet1"
        } else if (chainS == CHAIN_CERTIK_TEST_S) {
            return "shentu-incentivized-3"
        }
        return ""
    }
    
    static func getChainId(_ chain:ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return "cosmoshub-3"
        } else if (chain == ChainType.IRIS_MAIN) {
            return "irishub"
        } else if (chain == ChainType.BINANCE_MAIN) {
            return "Binance-Chain-Tigris"
        } else if (chain == ChainType.KAVA_MAIN) {
            return "kava-3"
        } else if (chain == ChainType.IOV_MAIN) {
            return "iov-mainnet-2"
        } else if (chain == ChainType.BAND_MAIN) {
            return "band-wenchang-mainnet"
        } else if (chain == ChainType.BINANCE_TEST) {
            return "Binance-Chain-Nile"
        } else if (chain == ChainType.KAVA_TEST) {
            return "kava-testnet-9000"
        } else if (chain == ChainType.IOV_TEST) {
            return "iovns-galaxynet"
        } else if (chain == ChainType.OKEX_TEST) {
            return "okexchain-testnet1"
        } else if (chain == ChainType.CERTIK_TEST) {
            return "shentu-incentivized-3"
        }
        return ""
    }
    
//    static func getChainName(_ chain:ChainType) -> String {
//        return getChainName(getChainDBName(chain))
//    }
    
    static func clearBackgroundColor(of view: UIView) {
        if let effectsView = view as? UIVisualEffectView {
            effectsView.removeFromSuperview()
            return
        }
        view.backgroundColor = .clear
        view.subviews.forEach { (subview) in
            self.clearBackgroundColor(of: subview)
        }
    }
    
    
    
    static func getPasswordAni() -> CAAnimation{
        let transition:CATransition = CATransition()
        transition.duration = 0.3
        transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
        transition.type = CATransitionType.moveIn
        transition.subtype = CATransitionSubtype.fromTop
        return transition
    }
    
    
    
    
    static func getEstimateGasAmount(_ chain:ChainType, _ type:String,  _ valCnt:Int) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (chain == ChainType.COSMOS_MAIN) {
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_REDELE))
            } else if (type == COSMOS_MSG_TYPE_TRANSFER2 || type == KAVA_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_REINVEST))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            }
            
        } else if (chain == ChainType.IRIS_MAIN) {
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_MID))
            if (type == IRIS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_MID))
            } else if (type == IRIS_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_SEND))
            } else if (type == IRIS_MSG_TYPE_WITHDRAW || type == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                result = (NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_MUX).multiplying(by: NSDecimalNumber.init(value: valCnt))).adding(NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_BASE))
            } else if (type == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_REWARD_BASE))
            } else if (type == IRIS_MSG_TYPE_REDELEGATE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_REDELEGATE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_LOW))
            } else {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_REDELEGATE))
            }
            
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            //Notice! useless but make format!
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
        
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_AVERAGE))
            if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_AVERAGE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_AVERAGE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_REDELEGATE))
            } else if (type == KAVA_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = WUtils.getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_REINVEST))
            } else if (type == KAVA_MSG_TYPE_CREATE_CDP || type == KAVA_MSG_TYPE_DEPOSIT_CDP || type == KAVA_MSG_TYPE_WITHDRAW_CDP || type == KAVA_MSG_TYPE_DRAWDEBT_CDP || type == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_CDP))
            } else if (type == TASK_TYPE_HTLC_REFUND) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_BEP3))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_LOW))
            } else if (type == KAVA_MSG_TYPE_INCENTIVE_REWARD) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_HIGH))
            }
            
        } else if (chain == ChainType.BAND_MAIN) {
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_REDELE))
            } else if (type == COSMOS_MSG_TYPE_TRANSFER2 || type == KAVA_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = WUtils.getGasAmountForRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_REINVEST))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            }
            
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_STAKE))
            if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_REDELEGATE))
            } else if (type == IOV_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = WUtils.getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_LOW))
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_REINVEST))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_LOW))
            }
        } else if (chain == ChainType.OKEX_TEST) {
            result = NSDecimalNumber.init(string: String(OK_GAS_AMOUNT_SEND))
            if (type == OK_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(OK_GAS_AMOUNT_SEND))
            } else if (type == OK_MSG_TYPE_DEPOSIT || type == OK_MSG_TYPE_WITHDRAW) {
                result = (NSDecimalNumber.init(string: OK_GAS_AMOUNT_STAKE_MUX).multiplying(by: NSDecimalNumber.init(value: valCnt))).adding(NSDecimalNumber.init(string: OK_GAS_AMOUNT_STAKE))
            } else if (type == OK_MSG_TYPE_DIRECT_VOTE) {
                result = (NSDecimalNumber.init(string: OK_GAS_AMOUNT_VOTE_MUX).multiplying(by: NSDecimalNumber.init(value: valCnt))).adding(NSDecimalNumber.init(string: OK_GAS_AMOUNT_VOTE))
            }
        }
        return result
    }
    
    
    
    static func getAtomFees() -> Array<NSDecimalNumber> {
        var atomFees = Array<NSDecimalNumber>()
        atomFees.append(NSDecimalNumber.init(string: FEE_ATOM_TINY))
        atomFees.append(NSDecimalNumber.init(string: FEE_ATOM_LOW))
        atomFees.append(NSDecimalNumber.init(string: FEE_ATOM_MID))
        atomFees.append(NSDecimalNumber.init(string: FEE_ATOM_HIGH))
        return atomFees
    }
    
    static func getGasAmountForRewards() -> Array<NSDecimalNumber> {
        var gasAmounts = Array<NSDecimalNumber>()
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_1))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_2))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_3))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_4))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_5))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_6))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_7))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_8))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_9))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_10))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_11))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_12))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_13))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_14))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_15))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_16))
        return gasAmounts
    }
    
    static func getGasAmountForKavaRewards() -> Array<NSDecimalNumber> {
        var gasAmounts = Array<NSDecimalNumber>()
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_1))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_2))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_3))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_4))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_5))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_6))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_7))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_8))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_9))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_10))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_11))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_12))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_13))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_14))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_15))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_16))
        return gasAmounts
    }
    
    static func getKavaCoinDecimal(_ denom:String) -> Int16 {
        if (denom.caseInsensitiveCompare(KAVA_MAIN_DENOM) == .orderedSame) {
            return 6;
        } else if (denom.caseInsensitiveCompare("xrp") == .orderedSame) {
            return 6;
        } else if (denom.caseInsensitiveCompare("btc") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("usdx") == .orderedSame) {
            return 6;
        } else if (denom.caseInsensitiveCompare("bnb") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("btcb") == .orderedSame) {
            return 8;
        }
        return 100;
    }
    
//    static func getQuotient(_ value:String) -> NSDecimalNumber {
//        let dividend = WUtils.localeStringToDecimal(value)
//        return dividend.dividing(by: NSDecimalNumber.one, withBehavior: getDivideHandler(0))
//    }
//
//    static func getRemainder(_ value:String) -> NSDecimalNumber {
//        let dividend = WUtils.localeStringToDecimal(value)
//        let quotient = dividend.dividing(by: NSDecimalNumber.one, withBehavior: getDivideHandler(0))
//        return dividend.subtracting(quotient)
//    }
    
    
    static func getMyIrisVote(_ votes: Array<Vote>, _ address: String) -> Vote? {
        for vote in votes {
            if (vote.voter == address) {
                return vote
            }
        }
        return nil
    }
    
    static func getIrisVoterTypeCnt(_ votes: Array<Vote>, _ type: String) -> String {
        var result = 0
        for vote in votes {
            if (vote.option == type) {
                result = result + 1
            }
        }
        return String(result)
    }
    
    static func getDPRawDollor(_ price:String, _ scale:Int, _ font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = scale
        nf.maximumFractionDigits = scale
        nf.numberStyle = .decimal
        
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(scale), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
        let amount = plainStringToDecimal(price).rounding(accordingToBehavior: handler)
        
        let added       = "$ " + nf.string(from: amount)!
        let endIndex    = added.index(added.endIndex, offsetBy: -scale)
        
        let preString   = added[..<endIndex]
        let postString  = added[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func showRiskRate(_ riskRate: NSDecimalNumber, _ scoreLabel: UILabel, _rateIamg:UIImageView?) {
        scoreLabel.attributedText = displayAmount2(riskRate.stringValue, scoreLabel.font, 0, 2)
        if (riskRate.doubleValue <= 50) {
            scoreLabel.textColor = COLOR_CDP_SAFE
            _rateIamg?.image = UIImage(named: "safe")
            
        } else if (riskRate.doubleValue < 80) {
            scoreLabel.textColor = COLOR_CDP_STABLE
            _rateIamg?.image = UIImage(named: "stable")
            
        } else {
            scoreLabel.textColor = COLOR_CDP_DANGER
            _rateIamg?.image = UIImage(named: "danger")
        }
    }
    
    static func showRiskRate2(_ riskRate: NSDecimalNumber, _ scoreLabel: UILabel, _ textLabel:UILabel) {
        scoreLabel.attributedText = displayAmount2(riskRate.stringValue, scoreLabel.font, 0, 2)
        if (riskRate.doubleValue <= 50) {
            scoreLabel.textColor = COLOR_CDP_SAFE
            textLabel.textColor = COLOR_CDP_SAFE
            textLabel.text = "SAFE"
            
        } else if (riskRate.doubleValue < 80) {
            scoreLabel.textColor = COLOR_CDP_STABLE
            textLabel.textColor = COLOR_CDP_STABLE
            textLabel.text = "STABLE"
            
        } else {
            scoreLabel.textColor = COLOR_CDP_DANGER
            textLabel.textColor = COLOR_CDP_DANGER
            textLabel.text = "DANGER"
        }
    }
    
    static func showRiskRate3(_ riskRate: NSDecimalNumber, _ scoreLabel: UILabel, _ textLabel:UILabel, _ cardView:CardView) {
        scoreLabel.attributedText = displayAmount2(riskRate.stringValue, scoreLabel.font, 0, 2)
        if (riskRate.doubleValue <= 50) {
            textLabel.text = "SAFE"
            cardView.backgroundColor = COLOR_CDP_SAFE
            
        } else if (riskRate.doubleValue < 80) {
            textLabel.text = "STABLE"
            cardView.backgroundColor = COLOR_CDP_STABLE
            
        } else {
            textLabel.text = "DANGER"
            cardView.backgroundColor = COLOR_CDP_DANGER
        }
    }
    
    static func dpChainInfo(_ chain: ChainType, _ img: UIImageView?, _ label: UILabel) {
        if (chain == ChainType.COSMOS_MAIN) {
            label.text = NSLocalizedString("chain_title_cosmos", comment: "")
            img?.image = UIImage(named: "cosmosWhMain")
        } else if (chain == ChainType.IRIS_MAIN) {
            label.text = NSLocalizedString("chain_title_iris", comment: "")
            img?.image = UIImage(named: "irisWh")
        } else if (chain == ChainType.BINANCE_MAIN) {
            label.text = NSLocalizedString("chain_title_bnb", comment: "")
            img?.image = UIImage(named: "binanceChImg")
        } else if (chain == ChainType.KAVA_MAIN) {
            label.text = NSLocalizedString("chain_title_kava", comment: "")
           img?.image = UIImage(named: "kavaImg")
        } else if (chain == ChainType.IOV_MAIN) {
            label.text = NSLocalizedString("chain_title_iov", comment: "")
            img?.image = UIImage(named: "iovImg")
        } else if (chain == ChainType.BAND_MAIN) {
            label.text = NSLocalizedString("chain_title_band", comment: "")
            img?.image = UIImage(named: "bandChainImg")
        } else if (chain == ChainType.BINANCE_TEST) {
            label.text = NSLocalizedString("chain_title_test_bnb", comment: "")
            img?.image = UIImage(named: "binancetestnet")
        } else if (chain == ChainType.KAVA_TEST) {
            label.text = NSLocalizedString("chain_title_kava_test", comment: "")
            img?.image = UIImage(named: "kavaTestImg")
        }
    }
    
    static func dpChainName(_ chain: ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return NSLocalizedString("chain_title_cosmos", comment: "")
        } else if (chain == ChainType.IRIS_MAIN) {
            return NSLocalizedString("chain_title_iris", comment: "")
        } else if (chain == ChainType.BINANCE_MAIN) {
            return NSLocalizedString("chain_title_bnb", comment: "")
        } else if (chain == ChainType.KAVA_MAIN) {
            return NSLocalizedString("chain_title_kava", comment: "")
        } else if (chain == ChainType.IOV_MAIN) {
            return NSLocalizedString("chain_title_iov", comment: "")
        } else if (chain == ChainType.BAND_MAIN) {
            return NSLocalizedString("chain_title_band", comment: "")
        } else if (chain == ChainType.BINANCE_TEST) {
            return NSLocalizedString("chain_title_test_bnb", comment: "")
        } else if (chain == ChainType.KAVA_TEST) {
            return NSLocalizedString("chain_title_kava_test", comment: "")
        }
        return ""
    }
    
    static func onDpSwapChain(_ chain: ChainType, _ img: UIImageView?, _ label: UILabel) {
        if (chain == ChainType.BINANCE_MAIN) {
            label.text = "BINANCE"
            img?.image = UIImage(named: "binanceChImg")
        } else if (chain == ChainType.KAVA_MAIN) {
            label.text = "KAVA"
           img?.image = UIImage(named: "kavaImg")
        } else if (chain == ChainType.BINANCE_TEST) {
            label.text = "BINANCE"
            img?.image = UIImage(named: "binancetestnet")
        } else if (chain == ChainType.KAVA_TEST) {
            label.text = "KAVA"
            img?.image = UIImage(named: "kavaTestImg")
        }
    }
    
    static func getKavaHtlcStatus(_ txInfo:TxInfo, _ swap:KavaSwapInfo?) -> String {
        if (!txInfo.isSuccess()) {
            return NSLocalizedString("bep3_status_open", comment: "")
        }
        
        if (swap == nil) {
            return NSLocalizedString("bep3_status_completed", comment: "")
        }
        
        if (swap?.result.status == KavaSwapInfo.STATUS_EXPIRED) {
            return NSLocalizedString("bep3_status_expired", comment: "")
        } else if (swap?.result.status == KavaSwapInfo.STATUS_OPEN) {
            return NSLocalizedString("bep3_status_open", comment: "")
        } else {
            return NSLocalizedString("bep3_status_completed", comment: "")
        }
    }
    
    
    static func getBnbHtlcStatus(_ swap:BnbSwapInfo?, _ node:BnbNodeInfo?) -> String {
        if (swap == nil || node == nil) {
            return "-"
        }
        
        if (swap?.status == BnbSwapInfo.BNB_STATUS_REFUNDED) {
            return NSLocalizedString("bep3_status_refunded", comment: "")
        } else if (swap?.status == BnbSwapInfo.BNB_STATUS_COMPLETED) {
            return NSLocalizedString("bep3_status_completed", comment: "")
        } else if (swap?.status == BnbSwapInfo.BNB_STATUS_OPEN && swap!.expireHeight < node!.getCHeight()) {
            return NSLocalizedString("bep3_status_expired", comment: "")
        }
        return NSLocalizedString("bep3_status_open", comment: "")
    }
    
    
    static func getGuideList() -> Array<GuideCategory> {
        var result = Array<GuideCategory>()
        var cg_cosmos = GuideCategory.init()
        cg_cosmos.category = NSLocalizedString("guide_category_0", comment: "")
        cg_cosmos.titles.append(NSLocalizedString("guide_title_cosmos_0", comment: ""))
        cg_cosmos.titles.append(NSLocalizedString("guide_title_cosmos_1", comment: ""))
        cg_cosmos.titles.append(NSLocalizedString("guide_title_cosmos_2", comment: ""))
        cg_cosmos.details.append(NSLocalizedString("guide_msg_cosmos_0", comment: ""))
        cg_cosmos.details.append(NSLocalizedString("guide_msg_cosmos_1", comment: ""))
        cg_cosmos.details.append(NSLocalizedString("guide_msg_cosmos_2", comment: ""))
        result.append(cg_cosmos)
        
        var cg_wallet = GuideCategory.init()
        cg_wallet.category = NSLocalizedString("guide_category_1", comment: "")
        cg_wallet.titles.append(NSLocalizedString("guide_title_wallet_0", comment: ""))
        cg_wallet.titles.append(NSLocalizedString("guide_title_wallet_1", comment: ""))
        cg_wallet.titles.append(NSLocalizedString("guide_title_wallet_2", comment: ""))
        cg_wallet.titles.append(NSLocalizedString("guide_title_wallet_3", comment: ""))
        cg_wallet.titles.append(NSLocalizedString("guide_title_wallet_4", comment: ""))
        cg_wallet.details.append(NSLocalizedString("guide_msg_wallet_0", comment: ""))
        cg_wallet.details.append(NSLocalizedString("guide_msg_wallet_1", comment: ""))
        cg_wallet.details.append(NSLocalizedString("guide_msg_wallet_2", comment: ""))
        cg_wallet.details.append(NSLocalizedString("guide_msg_wallet_3", comment: ""))
        cg_wallet.details.append(NSLocalizedString("guide_msg_wallet_4", comment: ""))
        result.append(cg_wallet)
        
        var cg_reward = GuideCategory.init()
        cg_reward.category = NSLocalizedString("guide_category_2", comment: "")
        cg_reward.titles.append(NSLocalizedString("guide_title_reward_0", comment: ""))
        cg_reward.titles.append(NSLocalizedString("guide_title_reward_1", comment: ""))
        cg_reward.titles.append(NSLocalizedString("guide_title_reward_2", comment: ""))
        cg_reward.titles.append(NSLocalizedString("guide_title_reward_3", comment: ""))
        cg_reward.titles.append(NSLocalizedString("guide_title_reward_4", comment: ""))
        cg_reward.titles.append(NSLocalizedString("guide_title_reward_5", comment: ""))
        cg_reward.details.append(NSLocalizedString("guide_msg_reward_0", comment: ""))
        cg_reward.details.append(NSLocalizedString("guide_msg_reward_1", comment: ""))
        cg_reward.details.append(NSLocalizedString("guide_msg_reward_2", comment: ""))
        cg_reward.details.append(NSLocalizedString("guide_msg_reward_3", comment: ""))
        cg_reward.details.append(NSLocalizedString("guide_msg_reward_4", comment: ""))
        cg_reward.details.append(NSLocalizedString("guide_msg_reward_5", comment: ""))
        result.append(cg_reward)
        
        var cg_tx = GuideCategory.init()
        cg_tx.category = NSLocalizedString("guide_category_3", comment: "")
        cg_tx.titles.append(NSLocalizedString("guide_title_transaction_0", comment: ""))
        cg_tx.details.append(NSLocalizedString("guide_msg_transaction_0", comment: ""))
        result.append(cg_tx)
        
        var cg_vote = GuideCategory.init()
        cg_vote.category = NSLocalizedString("guide_category_4", comment: "")
        cg_vote.titles.append(NSLocalizedString("guide_title_vote_0", comment: ""))
        cg_vote.titles.append(NSLocalizedString("guide_title_vote_1", comment: ""))
        cg_vote.details.append(NSLocalizedString("guide_msg_vote_0", comment: ""))
        cg_vote.details.append(NSLocalizedString("guide_msg_vote_1", comment: ""))
        result.append(cg_vote)
        
        var cg_general = GuideCategory.init()
        cg_general.category = NSLocalizedString("guide_category_5", comment: "")
        cg_general.titles.append(NSLocalizedString("guide_title_general_0", comment: ""))
        cg_general.titles.append(NSLocalizedString("guide_title_general_1", comment: ""))
        cg_general.titles.append(NSLocalizedString("guide_title_general_2", comment: ""))
        cg_general.titles.append(NSLocalizedString("guide_title_general_3", comment: ""))
        cg_general.titles.append(NSLocalizedString("guide_title_general_4", comment: ""))
        cg_general.titles.append(NSLocalizedString("guide_title_general_5", comment: ""))
        cg_general.details.append(NSLocalizedString("guide_msg_general_0", comment: ""))
        cg_general.details.append(NSLocalizedString("guide_msg_general_1", comment: ""))
        cg_general.details.append(NSLocalizedString("guide_msg_general_2", comment: ""))
        cg_general.details.append(NSLocalizedString("guide_msg_general_3", comment: ""))
        cg_general.details.append(NSLocalizedString("guide_msg_general_4", comment: ""))
        cg_general.details.append(NSLocalizedString("guide_msg_general_5", comment: ""))
        result.append(cg_general)
        
        var cg_trouble = GuideCategory.init()
        cg_trouble.category = NSLocalizedString("guide_category_6", comment: "")
        cg_trouble.titles.append(NSLocalizedString("guide_title_trouble_0", comment: ""))
        cg_trouble.titles.append(NSLocalizedString("guide_title_trouble_1", comment: ""))
        cg_trouble.details.append(NSLocalizedString("guide_msg_trouble_0", comment: ""))
        cg_trouble.details.append(NSLocalizedString("guide_msg_trouble_1", comment: ""))
        result.append(cg_trouble)
        
        return result;
    }
}

extension Date {
    var millisecondsSince1970:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    var StringmillisecondsSince1970:String {
        return String((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    var Stringmilli3MonthAgo:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0) - TimeInterval(7776000000.0).rounded())
    }
    
    init(milliseconds:Int) {
        self = Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
    }
}


extension UIImage {
    
    public class func gifImageWithData(_ data: Data) -> UIImage? {
        guard let source = CGImageSourceCreateWithData(data as CFData, nil) else {
            return nil
        }
        
        return UIImage.animatedImageWithSource(source)
    }
    
    public class func gifImageWithURL(_ gifUrl:String) -> UIImage? {
        guard let bundleURL:URL? = URL(string: gifUrl)
            else {
                return nil
        }
        guard let imageData = try? Data(contentsOf: bundleURL!) else {
            return nil
        }
        
        return gifImageWithData(imageData)
    }
    
    public class func gifImageWithName(_ name: String) -> UIImage? {
        guard let bundleURL = Bundle.main
            .url(forResource: name, withExtension: "gif") else {
                return nil
        }
        guard let imageData = try? Data(contentsOf: bundleURL) else {
            return nil
        }
        
        return gifImageWithData(imageData)
    }
    
    class func delayForImageAtIndex(_ index: Int, source: CGImageSource!) -> Double {
        var delay = 0.1
        
        let cfProperties = CGImageSourceCopyPropertiesAtIndex(source, index, nil)
        let gifProperties: CFDictionary = unsafeBitCast(
            CFDictionaryGetValue(cfProperties,
                                 Unmanaged.passUnretained(kCGImagePropertyGIFDictionary).toOpaque()),
            to: CFDictionary.self)
        
        var delayObject: AnyObject = unsafeBitCast(
            CFDictionaryGetValue(gifProperties,
                                 Unmanaged.passUnretained(kCGImagePropertyGIFUnclampedDelayTime).toOpaque()),
            to: AnyObject.self)
        if delayObject.doubleValue == 0 {
            delayObject = unsafeBitCast(CFDictionaryGetValue(gifProperties,
                                                             Unmanaged.passUnretained(kCGImagePropertyGIFDelayTime).toOpaque()), to: AnyObject.self)
        }
        
        delay = delayObject as! Double
        
        if delay < 0.1 {
            delay = 0.1
        }
        
        return delay
    }
    
    class func gcdForPair(_ a: Int?, _ b: Int?) -> Int {
        var a = a
        var b = b
        if b == nil || a == nil {
            if b != nil {
                return b!
            } else if a != nil {
                return a!
            } else {
                return 0
            }
        }
        
        if a! < b! {
            let c = a
            a = b
            b = c
        }
        
        var rest: Int
        while true {
            rest = a! % b!
            
            if rest == 0 {
                return b!
            } else {
                a = b
                b = rest
            }
        }
    }
    
    class func gcdForArray(_ array: Array<Int>) -> Int {
        if array.isEmpty {
            return 1
        }
        
        var gcd = array[0]
        
        for val in array {
            gcd = UIImage.gcdForPair(val, gcd)
        }
        
        return gcd
    }
    
    class func animatedImageWithSource(_ source: CGImageSource) -> UIImage? {
        let count = CGImageSourceGetCount(source)
        var images = [CGImage]()
        var delays = [Int]()
        
        for i in 0..<count {
            if let image = CGImageSourceCreateImageAtIndex(source, i, nil) {
                images.append(image)
            }
            
            let delaySeconds = UIImage.delayForImageAtIndex(Int(i), source: source)
            delays.append(Int(delaySeconds * 3000.0)) // Seconds to ms
        }
        
        let duration: Int = {
            var sum = 0
            
            for val: Int in delays {
                sum += val
            }
            
            return sum
        }()
        
        let gcd = gcdForArray(delays)
        var frames = [UIImage]()
        
        var frame: UIImage
        var frameCount: Int
        for i in 0..<count {
            frame = UIImage(cgImage: images[Int(i)])
            frameCount = Int(delays[Int(i)] / gcd)
            
            for _ in 0..<frameCount {
                frames.append(frame)
            }
        }
        
        let animation = UIImage.animatedImage(with: frames,
                                              duration: Double(duration) / 3000.0)
        
        return animation
    }
}


open class CustomSlider : UISlider {
    @IBInspectable open var trackWidth:CGFloat = 2 {
        didSet {setNeedsDisplay()}
    }

    override open func trackRect(forBounds bounds: CGRect) -> CGRect {
        let defaultBounds = super.trackRect(forBounds: bounds)
        return CGRect(
            x: defaultBounds.origin.x,
            y: defaultBounds.origin.y + defaultBounds.size.height/2 - trackWidth/2,
            width: defaultBounds.size.width,
            height: trackWidth
        )
    }
}
