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
    
    static let handler6 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 6, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler2 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 2, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)

    
    static func getAccountWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Account {
        let result = account
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            result.account_address = accountInfo.value.address
            result.account_sequence_number = Int64(accountInfo.value.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.account_number) ?? 0
        } else {
            result.account_address = accountInfo.value.BaseVestingAccount.BaseAccount.address
            result.account_sequence_number = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.account_number) ?? 0
        }
        
        return account
    }
    
    static func getBalancesWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT) {
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
    
    static func getBondingwithBondingInfo(_ account: Account, _ bondinginfos: Array<BondingInfo>) -> Array<Bonding> {
        var result = Array<Bonding>()
        for bondinginfo in bondinginfos {
            result.append(Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970))
        }
        return result
    }
    
    static func getBondingwithBondingInfo(_ account: Account, _ rawbondinginfos: Array<NSDictionary>) -> Array<Bonding> {
        var result = Array<Bonding>()
        for raw in rawbondinginfos{
            let bondinginfo = BondingInfo(raw as! [String : Any])
            result.append(Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970))
        }
        return result
    }
    
    
    static func getUnbondingwithUnbondingInfo(_ account: Account, _ unbondinginfos: Array<UnbondingInfo>) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        for unbondinginfo in unbondinginfos {
            for entry in unbondinginfo.entries {
                result.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    static func getUnbondingwithUnbondingInfo(_ account: Account, _ rawunbondinginfos: Array<NSDictionary>) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        for raw in rawunbondinginfos {
            let unbondinginfo = UnbondingInfo(raw as! [String : Any])
            for entry in unbondinginfo.entries {
                result.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
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
    
    
    
    static func historyTitle(_ msgs:Array<HistoryMsg>, _ myaddress:String) -> String {
        var resultMsg = NSLocalizedString("tx_known", comment: "")
        
        if(msgs == nil || msgs.count <= 0) {
            return resultMsg
        }
        
        if (msgs[0].type == COSMOS_MSG_TYPE_TRANSFER || msgs[0].type == COSMOS_MSG_TYPE_TRANSFER2) {
            if (msgs[0].value.from_address != nil && msgs[0].value.from_address == myaddress) {
                resultMsg = NSLocalizedString("tx_send", comment: "")
            } else if (msgs[0].value.to_address != nil && msgs[0].value.to_address == myaddress) {
                resultMsg = NSLocalizedString("tx_receive", comment: "")
            } else {
                resultMsg = NSLocalizedString("tx_transfer", comment: "")
            }
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_DELEGATE) {
            resultMsg = NSLocalizedString("tx_delegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_UNDELEGATE || msgs[0].type == COSMOS_MSG_TYPE_UNDELEGATE2) {
            resultMsg = NSLocalizedString("tx_undelegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_REDELEGATE || msgs[0].type == COSMOS_MSG_TYPE_REDELEGATE2) {
            resultMsg = NSLocalizedString("tx_redelegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
            resultMsg = NSLocalizedString("tx_get_reward", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_VAL) {
            resultMsg = NSLocalizedString("tx_get_commission", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
            resultMsg = NSLocalizedString("tx_change_reward_address", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_VOTE) {
            resultMsg = NSLocalizedString("tx_vote", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_SUBMIT_PROPOSAL) {
            resultMsg = NSLocalizedString("tx_submit_proposal", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_DEPOSIT) {
            resultMsg = NSLocalizedString("tx_deposit", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_CREATE_VALIDATOR) {
            resultMsg = NSLocalizedString("tx_create_validator", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_EDIT_VALIDATOR) {
            resultMsg = NSLocalizedString("tx_edit_validator", comment: "")
            
        }
        
        if(msgs.count > 1) {
            resultMsg = resultMsg +  "\n+ " + String(msgs.count - 1)
        }
        return resultMsg
    }
    
    
    static func checkNAN(_ check: NSDecimalNumber) -> NSDecimalNumber{
        if(check.isEqual(to: NSDecimalNumber.notANumber)) {
            return NSDecimalNumber.zero
        }
        return check
    }
    
    static func stringToDecimal(_ input: String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        do{
            result = NSDecimalNumber(string: input)
        } catch { }
        return result
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
    
    static func displayUSD(_ usd: NSDecimalNumber, font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted = "$ " + nf.string(from: usd)!
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -2)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
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
        
        let formatted   = nf.string(from: stringToDecimal(rate).multiplying(by: 100))! + "%"
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
    
    
    static func getChainName(_ type:String) -> String {
        if (type == ChainType.SUPPORT_CHAIN_COSMOS_MAIN.rawValue) {
            return "cosmoshub-2"
        } else if (type == ChainType.SUPPORT_CHAIN_IRSI_MAIN.rawValue) {
            return "irishub"
        }
        return "cosmoshub-2"
    }
    
}

extension Date {
    var millisecondsSince1970:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    var StringmillisecondsSince1970:String {
        return String((self.timeIntervalSince1970 * 1000.0).rounded())
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
