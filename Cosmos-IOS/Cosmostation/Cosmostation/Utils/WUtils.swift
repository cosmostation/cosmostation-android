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
    
    static let handler2 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 2, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handlerdown0 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)

    
    static func getAccountWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Account {
        let result = account
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT ||
            accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY ||
            accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) {
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
            result.account_address = (accountInfo.result.value?.address)!
            result.account_sequence_number = Int64(accountInfo.result.value!.sequence)!
            result.account_account_numner = Int64(accountInfo.result.value!.account_number)!
        } else if (accountInfo.result.type == COSMOS_AUTH_TYPE_VESTING_ACCOUNT) {
            result.account_address = accountInfo.result.value!.PeriodicVestingAccount.BaseVestingAccount.BaseAccount.address
            result.account_sequence_number = Int64(accountInfo.result.value!.PeriodicVestingAccount.BaseVestingAccount.BaseAccount.sequence)!
            result.account_sequence_number = Int64(accountInfo.result.value!.PeriodicVestingAccount.BaseVestingAccount.BaseAccount.account_number)!
        }
        return result
    }
    
    static func getBalancesWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT ||
            accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY ||
            accountInfo.type == IRIS_BANK_TYPE_ACCOUNT) {
            for coin in accountInfo.value.coins {
                result.append(Balance.init( account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
            
        } else {
            for coin in accountInfo.value.BaseVestingAccount.BaseAccount.coins {
                result.append(Balance.init( account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
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
            accountInfo.result.value?.coins.forEach({ (coin) in
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            })

        } else if (accountInfo.result.type == COSMOS_AUTH_TYPE_VESTING_ACCOUNT) {
            //TODO 1 year after re-calculate logic
            var totalVestiong = NSDecimalNumber.zero
            var totalDeleagtedVesting = NSDecimalNumber.zero
            var dpVesting = NSDecimalNumber.zero
            var dpBalance = NSDecimalNumber.zero

            for i in 0 ..< accountInfo.result.value!.vesting_period_progress.count {
                if (!accountInfo.result.value!.vesting_period_progress[i].period_complete &&
                    !accountInfo.result.value!.vesting_period_progress[i].vesting_successful) {
                    totalVestiong = totalVestiong.adding(NSDecimalNumber.init(string: accountInfo.result.value!.PeriodicVestingAccount.vesting_periods[i].amount[0].amount))
                }
            }

            accountInfo.result.value!.PeriodicVestingAccount.BaseVestingAccount.delegated_vesting.forEach({ (coin) in
                totalDeleagtedVesting = totalDeleagtedVesting.adding(NSDecimalNumber.init(string: coin.amount))
            })
            if (totalVestiong.compare(NSDecimalNumber.zero).rawValue > 0) {
                dpVesting = totalVestiong.subtracting(totalDeleagtedVesting)
            }

            accountInfo.result.value!.PeriodicVestingAccount.BaseVestingAccount.BaseAccount.coins.forEach({ (coin) in
                dpBalance = dpBalance.adding(NSDecimalNumber.init(string: coin.amount))
            })
            
            
            if (accountInfo.result.value?.PeriodicVestingAccount.BaseVestingAccount.BaseAccount.sequence == "0") {
                dpBalance = dpBalance.subtracting(totalVestiong)
                result.append(Balance.init(account.account_id,
                                            KAVA_MAIN_DENOM,
                                            dpBalance.stringValue,
                                            Date().millisecondsSince1970,
                                            NSDecimalNumber.zero.stringValue,
                                            totalVestiong.stringValue))
                
            } else {
                result.append(Balance.init(account.account_id,
                                            KAVA_MAIN_DENOM,
                                            dpBalance.stringValue,
                                            Date().millisecondsSince1970,
                                            NSDecimalNumber.zero.stringValue,
                                            totalVestiong.stringValue))
            }

            if (SHOW_LOG) {
                print("totalVestiong", totalVestiong);
                print("totalDeleagtedVesting", totalDeleagtedVesting);
                print("dpVesting", dpVesting);
                print("dpBalance", dpBalance);
            }
//            result.append(Balance.init(account.account_id,
//                                        accountInfo.result.value!.PeriodicVestingAccount.BaseVestingAccount.BaseAccount.coins[0].denom,
//                                        dpBalance.stringValue,
//                                        Date().millisecondsSince1970,
//                                        NSDecimalNumber.zero.stringValue,
//                                        totalVestiong.stringValue))
            
        }
        return result;
    }
    
    static func getBalancesWithIov(_ account: Account, _ balanceInfo: IovBalanceInfo) -> Array<Balance> {
        var result = Array<Balance>()
        for iovBalance in balanceInfo.balance {
            result.append(Balance.init( account.account_id, iovBalance.tokenTicker, iovBalance.quantity, Date().millisecondsSince1970))
        }
        return result;
    }
    
    static func getBondingwithBondingInfo(_ account: Account, _ rawbondinginfos: Array<NSDictionary>, _ chain:ChainType) -> Array<Bonding> {
        var result = Array<Bonding>()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            for raw in rawbondinginfos{
                let bondinginfo = BondingInfo(raw as! [String : Any])
                result.append(Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970))
            }
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            for raw in rawbondinginfos{
                let bondinginfo = BondingInfo(raw as! [String : Any])
                let shareAmount = stringToDecimal(bondinginfo.shares).multiplying(byPowerOf10: 18)
                result.append(Bonding(account.account_id, bondinginfo.validator_addr, shareAmount.stringValue, Date().millisecondsSince1970))
            }
        }
        
        return result
    }
    
    static func getUnbondingwithUnbondingInfo(_ account: Account, _ rawunbondinginfos: Array<NSDictionary>, _ chain:ChainType) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            for raw in rawunbondinginfos {
                let unbondinginfo = UnbondingInfo(raw as! [String : Any])
                for entry in unbondinginfo.entries {
                    result.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
                }
            }
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            for raw in rawunbondinginfos {
                let unbondinginfo = UnbondingInfo(raw as! [String : Any])
                let unbondingBalance = stringToDecimal(unbondinginfo.balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: handler0)
                let initialBalance = stringToDecimal(unbondinginfo.initial_balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: handler0)
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
    
    static func nodeTimetoString(input: String) -> String {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let fullDate = nodeFormatter.date(from: input)
        return localFormatter.string(from: fullDate!)
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
    
    static func unbondingDateFromNow() -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let afterDate = Calendar.current.date(
            byAdding: .day,
            value: +21,
            to: Date())
        return localFormatter.string(from: afterDate!)
    }
    
    static func timeGap(input: String) -> String {
        let secondsAgo = Int(Date().timeIntervalSince(nodeTimeToInt64(input: input)))
        
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
        }
        return resultMsg
    }
    
    
    static func checkNAN(_ check: NSDecimalNumber) -> NSDecimalNumber{
        if(check.isEqual(to: NSDecimalNumber.notANumber)) {
            return NSDecimalNumber.zero
        }
        return check
    }
    
    static func DecimalToLocalString(_ input: NSDecimalNumber) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 0
        nf.maximumFractionDigits = 6
        nf.numberStyle = .decimal
        nf.locale = Locale.current
        nf.groupingSeparator = ""
        return nf.string(from: input)!
    }
    
    static func DecimalToLocalString(_ input: NSDecimalNumber, _ chain:ChainType) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 0
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            nf.maximumFractionDigits = 6
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            nf.maximumFractionDigits = 18
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            nf.maximumFractionDigits = 8
        }
        nf.numberStyle = .decimal
        nf.locale = Locale.current
        nf.groupingSeparator = ""
        return nf.string(from: input)!
    }
    
    
    static func stringToDecimal(_ input: String?) -> NSDecimalNumber {
        let result = NSDecimalNumber(string: input, locale: Locale.current)
        if (NSDecimalNumber.notANumber == result) {
            return NSDecimalNumber.zero
        } else {
            return result
        }
    }
    
    static func unDelegateFormat(_ amount: String) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 18
        nf.maximumFractionDigits = 18
        nf.numberStyle = .none
        
        let oriAmount = stringToDecimal(amount)
        return  nf.string(from: oriAmount)!
    }
    
    static func displayAmout(_ amount: String, _ font:UIFont, _ deciaml:Int) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal
        
        let amount = stringToDecimal(amount)
        var formatted: String?
        if(amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else {
            formatted = nf.string(from: amount.dividing(by: 1000000).rounding(accordingToBehavior: handler6))
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
    
    static func displayAmount(_ amount: String?, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal
        
        let amount = stringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(deciaml), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)

        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            formatted = nf.string(from: amount.dividing(by: 1000000).rounding(accordingToBehavior: handler))
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            formatted = nf.string(from: amount.dividing(by: 1000000000000000000).rounding(accordingToBehavior: handler))
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
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
    
    static func displayAmount2(_ amount: String?, _ font:UIFont, _ inputPoint:Int, _ dpPoint:Int) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = dpPoint
        nf.maximumFractionDigits = dpPoint
        nf.numberStyle = .decimal
        
        let amount = stringToDecimal(amount)
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
    
    static func dpTokenAvailable(_ balances:Array<Balance>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = stringToDecimal(balance.balance_amount)
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    static func dpVestingCoin(_ balances:Array<Balance>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = stringToDecimal(balance.balance_locked)
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
    
    static func dpUnbondings(_ unbondings:Array<Unbonding>, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for unbonding in unbondings {
            amount = amount.adding(stringToDecimal(unbonding.unbonding_balance))
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    static func dpRewards(_ rewards:Array<Reward>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) ->  NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == symbol) {
                    amount = amount.adding(stringToDecimal(coin.amount).rounding(accordingToBehavior: handlerdown0))
                }
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain)
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
            amount = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000")).multiplying(by: amount, withBehavior: WUtils.handler2)
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
            result = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000")).multiplying(by: amount, withBehavior: WUtils.handler2)
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
            amount = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000000000000000")).multiplying(by: amount, withBehavior: WUtils.handler2)
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
            result = NSDecimalNumber(value: price!).dividing(by: NSDecimalNumber(string: "1000000000000000000")).multiplying(by: amount, withBehavior: WUtils.handler2)
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
            result = NSDecimalNumber(value: price!).multiplying(by: amount, withBehavior: WUtils.handler2)
        }
        return dpValue(result, font)
    }
    
    static func dpPricePerUnit(_ price:Double?, _ font:UIFont) -> NSMutableAttributedString {
        if (price == nil) {
            return dpValue(NSDecimalNumber.zero, font)
        }
        return dpValue(NSDecimalNumber(value: price!), font)
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
    
    static func displayInflation(_ rate:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate.multiplying(by: 100).rounding(accordingToBehavior: handler2))! + "%"
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
        
        let formatted   = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.init(string: "1").subtracting(commission))).multiplying(by: 100))! + "%"
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
        if (baseChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || baseChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            nf.minimumFractionDigits = 12
            nf.maximumFractionDigits = 12
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.init(string: "1").subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "365000000"))) ?? "0"
            endIndex = formatted.index(formatted.endIndex, offsetBy: -12)
        } else if (baseChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            nf.minimumFractionDigits = 18
            nf.maximumFractionDigits = 18
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.init(string: "1").subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "365000000000000000000"))) ?? "0"
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
        if (baseChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || baseChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            nf.minimumFractionDigits = 12
            nf.maximumFractionDigits = 12
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.init(string: "1").subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "12000000"))) ?? "0"
            endIndex = formatted.index(formatted.endIndex, offsetBy: -12)
        } else if (baseChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            nf.minimumFractionDigits = 18
            nf.maximumFractionDigits = 18
            formatted = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.init(string: "1").subtracting(commission))).multiplying(by: delegated).dividing(by: NSDecimalNumber.init(string: "12000000000000000000"))) ?? "0"
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
        
        let selfDecimal = stringToDecimal(selfShare)
        let totalDecimal = stringToDecimal(totalShare)
        
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
    
    //TODO deprecate
    static func displayAllAtomReward(_ rewards:Array<Reward>, _ font:UIFont, _ deciaml:Int ) ->  NSMutableAttributedString {
        var rewardSum = NSDecimalNumber.zero
        for reward in rewards {
            rewardSum = rewardSum.adding(stringToDecimal(reward.reward_amount[0].amount).rounding(accordingToBehavior: handlerdown0))
        }
        return displayAmout(rewardSum.stringValue, font, deciaml)
    }
    
    //TODO deprecate
    static func getAllAtomReward(_ rewards:Array<Reward>) ->  NSDecimalNumber {
        var rewardSum = NSDecimalNumber.zero
        for reward in rewards {
            rewardSum = rewardSum.adding(stringToDecimal(reward.reward_amount[0].amount).rounding(accordingToBehavior: handlerdown0))
        }
        return rewardSum
    }
    
    static func getValidatorReward(_ rewards:Array<Reward>, _ valOpAddr:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for reward in rewards {
            if(reward.reward_v_address == valOpAddr) {
                result = stringToDecimal(reward.reward_amount[0].amount)
                break;
            }
        }
        return result
    }
    
    static func getAllAtom(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == COSMOS_MAIN_DENOM) {
                amount = stringToDecimal(balance.balance_amount)
            }
        }
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            amount = amount.adding(stringToDecimal(unbonding.unbonding_balance))
        }
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == COSMOS_MAIN_DENOM) {
                    amount = amount.adding(stringToDecimal(coin.amount))
                }
            }
        }
        return amount
    }
    
    static func getAllIris(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:IrisRewards?, _ validators:Array<Validator>) ->  NSDecimalNumber {
        var sum = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == IRIS_MAIN_DENOM) {
                sum = stringToDecimal(balance.balance_amount)
            }
        }
        
        for bonding in bondings {
            sum = sum.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            sum = sum.adding(WUtils.stringToDecimal(unbonding.unbonding_balance))
        }
        if (rewards != nil) {
            sum = sum.adding(rewards!.getSimpleIrisReward())
        }
        return sum
    }
    
    static func getAllKava(_ balances:Array<Balance>, _ bondings:Array<Bonding>, _ unbondings:Array<Unbonding>,_ rewards:Array<Reward>, _ validators:Array<Validator>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == KAVA_MAIN_DENOM) {
                amount = stringToDecimal(balance.balance_amount)
            }
        }
        for bonding in bondings {
            amount = amount.adding(bonding.getBondingAmount(validators))
        }
        for unbonding in unbondings {
            amount = amount.adding(stringToDecimal(unbonding.unbonding_balance))
        }
        for reward in rewards {
            for coin in reward.reward_amount {
                if (coin.denom == KAVA_MAIN_DENOM) {
                    amount = amount.adding(stringToDecimal(coin.amount))
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
    
    static func displayIrisToken(_ amount: String, _ font:UIFont, _ deciaml:Int, _ deciaml2:Int) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal
        
        let amount = stringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(deciaml), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
        
        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else {
            formatted = nf.string(from: amount.dividing(by: NSDecimalNumber(decimal: pow(10,deciaml2))).rounding(accordingToBehavior: handler))
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
            if (balance.balance_denom == symbol) {
                return balance
            }
        }
        return nil
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
    
    
    
    
    static func getChainColor(_ chain:ChainType) -> UIColor {
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return COLOR_ATOM
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return COLOR_IRIS
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return COLOR_BNB
        } else if (chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return COLOR_KAVA
        }else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
           return COLOR_IOV
       }
        return COLOR_ATOM
    }
    
    static func getChainDarkColor(_ chain:ChainType) -> UIColor {
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return COLOR_ATOM_DARK
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return COLOR_IRIS_DARK
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return COLOR_BNB_DARK
        } else if (chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return COLOR_KAVA_DARK
        } else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            return COLOR_IOV_DARK
        }
        return COLOR_ATOM_DARK
    }
    
    static func getChainBg(_ chain:ChainType) -> UIColor {
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return TRANS_BG_COLOR_COSMOS
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return TRANS_BG_COLOR_IRIS
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return TRANS_BG_COLOR_BNB
        } else if (chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return TRANS_BG_COLOR_KAVA
        } else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            return TRANS_BG_COLOR_IOV
        }
        return TRANS_BG_COLOR_COSMOS
    }
    
    static func getMainDenom(_ chain:ChainType) -> String {
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return "ATOM"
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return "IRIS"
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return "BNB"
        } else if (chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return "KAVA"
        } else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            return "IOV"
        }
        return ""
    }
    
    static func setDenomTitle(_ chain:ChainType, _ label:UILabel) {
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            label.text = "ATOM"
            label.textColor = COLOR_ATOM
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            label.text = "IRIS"
            label.textColor = COLOR_IRIS
        } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            label.text = "BNB"
            label.textColor = COLOR_BNB
        } else if (chain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
           label.text = "KAVA"
           label.textColor = COLOR_KAVA
        } else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            label.text = "IOV"
            label.textColor = COLOR_IOV
        }
    }
    
    static func getChainType(_ chainS:String) -> ChainType {
        if (chainS == CHAIN_COSMOS_S ) {
            return ChainType.SUPPORT_CHAIN_COSMOS_MAIN
        } else if (chainS == CHAIN_IRIS_S) {
            return ChainType.SUPPORT_CHAIN_IRIS_MAIN
        } else if (chainS == CHAIN_BINANCE_S) {
            return ChainType.SUPPORT_CHAIN_BINANCE_MAIN
        } else if (chainS == CHAIN_KAVA_S) {
           return ChainType.SUPPORT_CHAIN_KAVA_MAIN
        } else if (chainS == CHAIN_IOV_S) {
            return ChainType.SUPPORT_CHAIN_IOV_MAIN
        }
        return ChainType.SUPPORT_CHAIN_COSMOS_MAIN
    }
    
    static func getChainName(_ type:String) -> String {
        if (type == CHAIN_COSMOS_S) {
            return "cosmoshub-2"
        } else if (type == CHAIN_IRIS_S) {
            return "irishub"
        } else if (type == CHAIN_BINANCE_S) {
            return "Binance-Chain-Tigris"
        } else if (type == CHAIN_KAVA_S) {
            return "kava-1"
        } else if (type == CHAIN_IOV_S) {
            return "iov-mainnet"
        }
        return "cosmoshub-2"
    }
    
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
    
    
    
    static func getAtomFees() -> Array<NSDecimalNumber> {
        var atomFees = Array<NSDecimalNumber>()
        atomFees.append(WUtils.stringToDecimal(FEE_ATOM_TINY))
        atomFees.append(WUtils.stringToDecimal(FEE_ATOM_LOW))
        atomFees.append(WUtils.stringToDecimal(FEE_ATOM_MID))
        atomFees.append(WUtils.stringToDecimal(FEE_ATOM_HIGH))
        return atomFees
    }
    
    static func getGasAmountForRewards() -> Array<NSDecimalNumber> {
        var gasAmounts = Array<NSDecimalNumber>()
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_1))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_2))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_3))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_4))
        
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_5))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_6))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_7))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_8))
        
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_9))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_10))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_11))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_12))
        
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_13))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_14))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_15))
        gasAmounts.append(WUtils.stringToDecimal(FEE_REWARD_GAS_16))
        return gasAmounts
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
