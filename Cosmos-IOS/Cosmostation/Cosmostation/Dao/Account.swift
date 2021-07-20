//
//  Account.swift
//  Cosmostation
//
//  Created by yongjoo on 20/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import MobileCoreServices

public class Account : NSObject, Codable, NSItemProviderReading, NSItemProviderWriting {
    
    var account_id: Int64 = -1;
    var account_uuid: String = "";
    var account_nick_name: String = "";
    var account_favo: Bool = false;
    var account_address: String = "";
    
    var account_base_chain: String = "";
    var account_has_private: Bool = false;
    var account_resource:String = "";
    var account_from_mnemonic:Bool = false;
    var account_path:String = "";
    
    var account_is_validator: Bool = false;
    var account_sequence_number: Int64 = -1;
    var account_account_numner: Int64 = -1;
    var account_fetch_time:Int64 = -1;
    var account_m_size:Int64 = -1;
    
    var account_import_time:Int64 = -1;
    var account_last_total:String = "";
    var account_sort_order:Int64 = 0;
    var account_push_alarm: Bool = false;
    var account_new_bip44: Bool = false;
    
    var account_custom_path: Int64 = 0;
    
    
    enum CodingKeys: String, CodingKey {
        case account_id
        case account_uuid
        case account_nick_name
        case account_favo
        case account_address
        case account_base_chain
        case account_has_private
        case account_resource
        case account_from_mnemonic
        case account_path
        case account_is_validator
        case account_sequence_number
        case account_account_numner
        case account_fetch_time
        case account_m_size
        case account_import_time
        case account_last_total
        case account_sort_order
        case account_push_alarm
        case account_new_bip44
        case account_custom_path
    }
    
    init(isNew: Bool) {
        account_uuid = UUID().uuidString
    }
    
    init(_ id:Int64, _ uuid:String, _ nickName:String, _ favo:Bool, _ address:String,
         _ baseChain:String, _ hasPrivate:Bool, _ resource:String, _ fromMnemonic:Bool, _ path:String,
         _ isValidator:Bool, _ sequenceNumber:Int64, _ accountNumber:Int64, _ fetchTime:Int64, _ mSize:Int64,
         _ importTime:Int64, _ lastTotal:String, _ sortOrder:Int64, _ pushAlarm:Bool, _ newbip:Bool, _ customPath:Int64) {
        
        self.account_id = id;
        self.account_uuid = uuid;
        self.account_nick_name = nickName;
        self.account_favo = favo;
        self.account_address = address;
        
        self.account_base_chain = baseChain;
        self.account_has_private = hasPrivate;
        self.account_resource = resource;
        self.account_from_mnemonic = fromMnemonic;
        self.account_path = path;
        
        self.account_is_validator = isValidator;
        self.account_sequence_number = sequenceNumber;
        self.account_account_numner = accountNumber;
        self.account_fetch_time = fetchTime;
        self.account_m_size = mSize;
        
        self.account_import_time = importTime;
        self.account_last_total = lastTotal
        self.account_sort_order = sortOrder;
        self.account_push_alarm = pushAlarm;
        self.account_new_bip44 = newbip;
        
        self.account_custom_path = customPath;
        
    }
    
    var account_balances = Array<Balance>()
    
    func getDpName() -> String {
        var nickName:String?
        if (account_nick_name == "") {
            nickName = NSLocalizedString("wallet_dash", comment: "") + String(account_id)
        } else {
            nickName = account_nick_name
        }
        return nickName!
    }
    
    func setBalances(_ balances:Array<Balance>) {
        self.account_balances = balances
    }
    
    func getAtomBalance() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == COSMOS_MAIN_DENOM) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getIrisBalance() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == IRIS_MAIN_DENOM) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getBnbBalance() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == BNB_MAIN_DENOM) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getKavaBalance() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == KAVA_MAIN_DENOM) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getIovBalance() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == IOV_MAIN_DENOM || balance.balance_denom == IOV_TEST_DENOM) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getBandBalance() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == BAND_MAIN_DENOM) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getTokenBalance(_ symbol:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for balance in self.account_balances {
            if (balance.balance_denom == symbol) {
                result = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return result
    }
    
    func getTokenCoin(_ symbol:String) -> Coin {
        var result = Coin.init()
        for balance in self.account_balances {
            if (balance.balance_denom == symbol) {
                result.amount = balance.balance_amount
                result.denom = balance.balance_denom
            }
        }
        return result
    }
    
    public static var readableTypeIdentifiersForItemProvider: [String] {
        return [(kUTTypeData) as String]
    }

    public static func object(withItemProviderData data: Data, typeIdentifier: String) throws -> Self {
        let decoder = JSONDecoder()
        do {
            let myJSON = try decoder.decode(Account.self, from: data)
            return myJSON as! Self
        } catch {
            fatalError("Err")
        }
    }

    public static var writableTypeIdentifiersForItemProvider: [String]{
        return [(kUTTypeData) as String]
    }

    public func loadData(withTypeIdentifier typeIdentifier: String, forItemProviderCompletionHandler completionHandler: @escaping (Data?, Error?) -> Void) -> Progress? {
        let progress = Progress(totalUnitCount: 100)
        do {
          let data = try JSONEncoder().encode(self)
          progress.completedUnitCount = 100
          completionHandler(data, nil)
        } catch {
          completionHandler(nil, error)
        }
        return progress
    }
}
