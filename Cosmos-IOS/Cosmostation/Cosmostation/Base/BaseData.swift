//
//  BaseData.swift
//  Cosmostation
//
//  Created by yongjoo on 07/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import SQLite
import SwiftKeychainWrapper


final class BaseData : NSObject{
    
    static let instance = BaseData()
    
    var database: Connection!
    var copySalt: String?
    var mPrices = Array<Price>()
    var mParam: Param?
    var mIbcPaths = Array<IbcPath>()
    var mIbcTokens = Array<IbcToken>()
    
    var mNodeInfo: NodeInfo?
    var mBalances = Array<Balance>()
    var mMyDelegations = Array<BondingInfo>()
    var mMyUnbondings = Array<UnbondingInfo>()
    var mMyReward = Array<RewardInfo>()
    var mAllValidator = Array<Validator>()
    var mTopValidator = Array<Validator>()
    var mOtherValidator = Array<Validator>()
    var mMyValidator = Array<Validator>()
    
    var mHeight: Int = 0
    
    
    //kava-7
    var mKavaAccountResult = KavaAccountInfo.KavaAccountResult.init()
    var mKavaPrice = [String:KavaPriceFeedPrice]()
    var mKavaPriceMarkets: Array<KavaPriceMarket> = Array<KavaPriceMarket>()
    var mCdpParam: CdpParam?
    var mHardParam: HardParam?
    var mIncentiveParam: IncentiveParam?
    var mIncentiveRewards: IncentiveReward?
    var mMyHardDeposit: Array<HardMyDeposit>?
    var mMyHardBorrow: Array<HardMyBorrow>?
    var mModuleCoins: Array<Coin>?
    var mReserveCoins: Array<Coin>?
    var mKavaSwapParam: SwapParam!
    
    var mBnbTokenList = Array<BnbToken>()
    var mBnbTokenTicker = Array<BnbTicker>()
    
    var mOkAccountInfo: OkAccountInfo?
    var mOkTokenList: OkTokenList?
    var mOkTickerList: OkTickerList?
    var mOkStaking: OkStaking?
    var mOkUnbonding: OkUnbonding?
    var mOKBPrice: NSDecimalNumber = NSDecimalNumber.zero
        
    var mSifVsIncentive: SifIncentive?
    var mSifLmIncentive: SifIncentive?
    
    
    //For ProtoBuf and gRPC
    var mVestingAccountInfoResult: VestingAccountInfo.VestingAccountInfoResult?
    var mNodeInfo_gRPC: Tendermint_P2p_DefaultNodeInfo?
    var mAccount_gRPC: Google_Protobuf2_Any?
    var mAllValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    var mBondedValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    var mUnbondValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    var mMyValidators_gRPC = Array<Cosmos_Staking_V1beta1_Validator>()
    
    var mMyDelegations_gRPC = Array<Cosmos_Staking_V1beta1_DelegationResponse>()
    var mMyUnbondings_gRPC = Array<Cosmos_Staking_V1beta1_UnbondingDelegation>()
    var mMyBalances_gRPC = Array<Coin>()
    var mMyVestings_gRPC = Array<Coin>()
    var mMyReward_gRPC = Array<Cosmos_Distribution_V1beta1_DelegationDelegatorReward>()
    
    var mBandOracle_gRPC = Array<Oracle_V1_ActiveValidator>()
    
    var mStarNameFee_gRPC: Starnamed_X_Configuration_V1beta1_Fees?
    var mStarNameConfig_gRPC: Starnamed_X_Configuration_V1beta1_Config?
    
    
    var mGravityParam_gRPC: Tendermint_Liquidity_V1beta1_Params?
    var mGravityPools_gRPC = Array<Tendermint_Liquidity_V1beta1_Pool>()
    var mGravityPoolTokens_gRPC = Array<Coin>()
    var mGravityManager_gRPC = Array<GDexManager>()
    
    public override init() {
        super.init();
        if database == nil {
            self.initdb();
        }
    }
    
    func getPrice(_ denom: String) -> Price? {
        return mPrices.filter { $0.denom == denom.lowercased() }.first
    }
    
    func getIbcToken(_ hash: String?) -> IbcToken? {
        return mIbcTokens.filter { $0.hash == hash }.first
    }
    
    func getIbcPath(_ channelId: String?) -> Path? {
        for ibcPath in mIbcPaths {
            for path in ibcPath.paths {
                if (path.channel_id == channelId) {
                    return path
                }
            }
        }
        return nil
    }
    
//    func getIbcCounterChainId(_ channelId: String?) -> String {
//        for ibcPath in mIbcPaths {
//            for path in ibcPath.paths {
//                if (path.channel_id == channelId) {
//                    return ibcPath.chain_id ?? ""
//                }
//            }
//        }
//        return ""
//    }
    
    func getBaseDenom(_ denom: String) -> String {
        if (denom.starts(with: "ibc/")) {
            guard let ibcToken = getIbcToken(denom.replacingOccurrences(of: "ibc/", with: "")) else {
                return denom
            }
            if (ibcToken.auth == true) {
                return ibcToken.base_denom!
            }
        }
        return denom
    }
    
    func getChainId(_ chainType: ChainType?) -> String {
        if (WUtils.isGRPC(chainType)) {
            if (mNodeInfo_gRPC != nil) { return mNodeInfo_gRPC!.network }
        } else {
            if (mNodeInfo != nil) { return mNodeInfo!.network! }
        }
        return ""
    }
    
    func getBalance(_ symbol:String?) -> Balance? {
        return mBalances.filter {$0.balance_denom == symbol}.first
    }
    
    func availableAmount(_ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in mBalances {
            if (balance.balance_denom == symbol) {
                amount = WUtils.plainStringToDecimal(balance.balance_amount)
            }
        }
        return amount;
    }
    
    func frozenAmount(_ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in mBalances {
            if (balance.balance_denom == symbol) {
                amount = WUtils.plainStringToDecimal(balance.balance_frozen)
            }
        }
        return amount;
    }
    
    //locked or vesting
    func lockedAmount(_ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in mBalances {
            if (balance.balance_denom == symbol) {
                amount = WUtils.plainStringToDecimal(balance.balance_locked)
            }
        }
        return amount;
    }
    
    func delegatableAmount(_ symbol:String) -> NSDecimalNumber {
        return availableAmount(symbol).adding(lockedAmount(symbol))
    }
    
    func delegatedSumAmount() -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for bonding in mMyDelegations {
            amount = amount.adding(bonding.getAmount())
        }
        return amount
    }
    
    func delegatedAmountByValidator(_ opAddress: String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for bonding in mMyDelegations {
            if (bonding.validator_address == opAddress) {
                amount = amount.adding(bonding.getAmount())
            }
        }
        return amount
    }
    
    func unbondingSumAmount() -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        mMyUnbondings.forEach { unbondingInfo in
            unbondingInfo.entries.forEach { entry in
                amount = amount.adding(NSDecimalNumber.init(string: entry.balance))
            }
        }
        return amount
    }
    
    func getUnbondingEntrie() -> Array<UnbondingInfo.Entry> {
        var result = Array<UnbondingInfo.Entry>()
        mMyUnbondings.forEach { unbondingInfo in
            unbondingInfo.entries.forEach { entry in
                result.append(entry)
            }
        }
        result.sort{ return Int($0.creation_height)! < Int($1.creation_height)! }
        return result
    }
    
    func unbondingAmountByValidator(_ opAddress: String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for unbonding in mMyUnbondings {
            if (unbonding.validator_address == opAddress) {
                unbonding.entries.forEach { entry in
                    amount = amount.adding(NSDecimalNumber.init(string: entry.balance))
                }
            }
        }
        return amount
    }
    
    
    func rewardAmount(_ symbol: String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for reward in mMyReward {
            for coin in reward.reward {
                if (coin.denom == symbol) {
                    amount = amount.adding(NSDecimalNumber.init(string: coin.amount).rounding(accordingToBehavior: WUtils.handler0Down))
                }
            }
        }
        return amount
    }
    
    func rewardAmountByValidator(_ symbol: String, _ opAddress: String) -> NSDecimalNumber {
        if let reward = BaseData.instance.mMyReward.filter({ $0.validator_address == opAddress}).first {
            for coin in reward.reward {
                if (coin.denom == symbol) {
                    return NSDecimalNumber.init(string:coin.amount).rounding(accordingToBehavior: WUtils.handler0Down)
                }
            }
        }
        return NSDecimalNumber.zero
    }
    
    func okDepositAmount() -> NSDecimalNumber {
        return WUtils.plainStringToDecimal(mOkStaking?.tokens)
    }
    
    func okWithdrawAmount() -> NSDecimalNumber {
        return WUtils.plainStringToDecimal(mOkUnbonding?.quantity)
    }
    
    
    
    func getAvailable_gRPC(_ symbol:String) -> String {
        var amount = NSDecimalNumber.zero.stringValue
        for balance in mMyBalances_gRPC {
            if (balance.denom == symbol) {
                amount = balance.amount
            }
        }
        return amount;
    }
    
    func getAvailableAmount_gRPC(_ symbol:String) -> NSDecimalNumber {
        return WUtils.plainStringToDecimal(getAvailable_gRPC(symbol))
    }
    
    func getVesting_gRPC(_ symbol:String) -> String {
        var amount = NSDecimalNumber.zero.stringValue
        for balance in mMyVestings_gRPC {
            if (balance.denom == symbol) {
                amount = balance.amount
            }
        }
        return amount;
    }
    
    func getVestingAmount_gRPC(_ symbol:String) -> NSDecimalNumber {
        return WUtils.plainStringToDecimal(getVesting_gRPC(symbol))
    }
    
    func onParseRemainVestingsByDenom_gRPC(_ denom: String) -> Array<Cosmos_Vesting_V1beta1_Period> {
        var results = Array<Cosmos_Vesting_V1beta1_Period>()
        if (mAccount_gRPC?.typeURL.contains(Cosmos_Vesting_V1beta1_PeriodicVestingAccount.protoMessageName) == true) {
            let account = try! Cosmos_Vesting_V1beta1_PeriodicVestingAccount.init(serializedData: mAccount_gRPC!.value)
            return WUtils.onParsePeriodicRemainVestingsByDenom(account, denom)

        } else if (mAccount_gRPC?.typeURL.contains(Cosmos_Vesting_V1beta1_ContinuousVestingAccount.protoMessageName) == true) {
            let account = try! Cosmos_Vesting_V1beta1_ContinuousVestingAccount.init(serializedData: mAccount_gRPC!.value)
            let cTime = Date().millisecondsSince1970
            let vestingEnd = account.baseVestingAccount.endTime * 1000
            if (cTime < vestingEnd) {
                account.baseVestingAccount.originalVesting.forEach { (vp) in
                    if (vp.denom == denom) {
                        let temp = Cosmos_Vesting_V1beta1_Period.with {
                            $0.length = vestingEnd
                            $0.amount = account.baseVestingAccount.originalVesting
                        }
                        results.append(temp)
                    }
                }
            }
            
        } else if (mAccount_gRPC?.typeURL.contains(Cosmos_Vesting_V1beta1_DelayedVestingAccount.protoMessageName) == true) {
            let account = try! Cosmos_Vesting_V1beta1_DelayedVestingAccount.init(serializedData: mAccount_gRPC!.value)
            let cTime = Date().millisecondsSince1970
            let vestingEnd = account.baseVestingAccount.endTime * 1000
            if (cTime < vestingEnd) {
                account.baseVestingAccount.originalVesting.forEach { (vp) in
                    if (vp.denom == denom) {
                        let temp = Cosmos_Vesting_V1beta1_Period.with {
                            $0.length = vestingEnd
                            $0.amount = account.baseVestingAccount.originalVesting
                        }
                        results.append(temp)
                    }
                }
            }
            
        }
        return results
    }
    
    func onParseRemainVestingsAmountSumByDenom_gRPC(_ denom: String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        onParseRemainVestingsByDenom_gRPC(denom).forEach { (vp) in
            vp.amount.forEach { (coin) in
                if (coin.denom == denom) {
                    result = result.adding(NSDecimalNumber.init(string: coin.amount))
                }
            }
        }
        return result
    }
    
    func getDelegatable_gRPC(_ symbol:String) -> NSDecimalNumber {
        return getAvailableAmount_gRPC(symbol).adding(getVestingAmount_gRPC(symbol))
    }
    
    func getDelegatedSum_gRPC() -> String {
        var amount = NSDecimalNumber.zero
        for delegation in mMyDelegations_gRPC {
            amount = amount.adding(WUtils.plainStringToDecimal(delegation.balance.amount))
        }
        return amount.stringValue;
    }
    
    func getDelegated_gRPC(_ opAddress: String?) -> NSDecimalNumber {
        if let delegation = BaseData.instance.mMyDelegations_gRPC.filter({ $0.delegation.validatorAddress == opAddress}).first {
            return WUtils.plainStringToDecimal(delegation.balance.amount)
        } else {
            return NSDecimalNumber.zero
        }
    }
    
    func getUnbondingSumAmount_gRPC() -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for unbonding in mMyUnbondings_gRPC {
            for entry in unbonding.entries {
                amount = amount.adding(WUtils.plainStringToDecimal(entry.balance))
            }
        }
        return amount;
    }
    
    func getUnbondingSum_gRPC() -> String {
        return getUnbondingSumAmount_gRPC().stringValue;
    }
    
    func getUnbondingEntrie_gRPC() -> Array<Cosmos_Staking_V1beta1_UnbondingDelegationEntry> {
        var result = Array<Cosmos_Staking_V1beta1_UnbondingDelegationEntry>()
        for unbonding in mMyUnbondings_gRPC {
            for entry in unbonding.entries {
                result.append(entry)
            }
        }
        result.sort { return $0.completionTime.seconds < $1.completionTime.seconds }
        return result
    }
    
    func getUnbonding_gRPC(_ opAddress: String?) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for unbonding in mMyUnbondings_gRPC {
            if (unbonding.validatorAddress == opAddress) {
                for entry in unbonding.entries {
                    amount = amount.adding(WUtils.plainStringToDecimal(entry.balance))
                }
            }
        }
        return amount;
    }
    
    func getRewardSum_gRPC(_ symbol:String) -> String {
        var amount = NSDecimalNumber.zero
        for reward in mMyReward_gRPC {
            for coin in reward.reward {
                if (coin.denom == symbol) {
                    amount = amount.adding(WUtils.plainStringToDecimal(coin.amount).multiplying(byPowerOf10: -18))
                }
            }
        }
        return amount.stringValue;
    }
    
    func getReward_gRPC(_ symbol:String, _ opAddress: String?) -> NSDecimalNumber {
        if let reward = BaseData.instance.mMyReward_gRPC.filter({ $0.validatorAddress == opAddress}).first {
            for coin in reward.reward {
                if (coin.denom == symbol) {
                    return WUtils.plainStringToDecimal(coin.amount).multiplying(byPowerOf10: -18)
                }
            }
        }
        return NSDecimalNumber.zero
    }
    
    func getGravityPoolByDenom(_ denom: String) -> Tendermint_Liquidity_V1beta1_Pool? {
        return mGravityPools_gRPC.filter { $0.poolCoinDenom == denom }.first
    }
    
    func getGravityPoolById(_ id: String) -> Tendermint_Liquidity_V1beta1_Pool? {
        return mGravityPools_gRPC.filter { $0.id == UInt64(id) }.first
    }
    
    
    func setRecentAccountId(_ id : Int64) {
        UserDefaults.standard.set(id, forKey: KEY_RECENT_ACCOUNT)
    }
    
    func getRecentAccountId() -> Int64 {
        return Int64(UserDefaults.standard.integer(forKey: KEY_RECENT_ACCOUNT))
    }
    
    func setRecentChain(_ id : Int) {
        UserDefaults.standard.set(id, forKey: KEY_RECENT_CHAIN)
    }
    
    func getRecentChain() -> Int {
        let position = Int(UserDefaults.standard.integer(forKey: KEY_RECENT_CHAIN))
        if (ChainType.SUPPRT_CHAIN().count < position) {
            return 0
        } else {
            return position
        }
    }
    
    
    func setAllValidatorSort(_ sort : Int64) {
        UserDefaults.standard.set(sort, forKey: KEY_ALL_VAL_SORT)
    }
    
    func getAllValidatorSort() -> Int64 {
        return Int64(UserDefaults.standard.integer(forKey: KEY_ALL_VAL_SORT))
    }
    
    func setMyValidatorSort(_ sort : Int64) {
        UserDefaults.standard.set(sort, forKey: KEY_MY_VAL_SORT)
    }
    
    func getMyValidatorSort() -> Int64 {
        return Int64(UserDefaults.standard.integer(forKey: KEY_MY_VAL_SORT))
    }
    
    func setLastTab(_ index : Int) {
        UserDefaults.standard.set(index, forKey: KEY_LAST_TAB)
    }
    
    func getLastTab() -> Int {
        return UserDefaults.standard.integer(forKey: KEY_LAST_TAB)
    }
    
    func setNeedRefresh(_ refresh : Bool) {
        UserDefaults.standard.set(refresh, forKey: KEY_ACCOUNT_REFRESH_ALL)
    }
    
    func getNeedRefresh() -> Bool {
        return UserDefaults.standard.bool(forKey: KEY_ACCOUNT_REFRESH_ALL)
    }
    
    func setCurrency(_ currency : Int) {
        UserDefaults.standard.set(currency, forKey: KEY_CURRENCY)
    }

    func getCurrency() -> Int {
        return UserDefaults.standard.integer(forKey: KEY_CURRENCY)
    }
    
    func getCurrencyString() -> String {
        if (getCurrency() == 0) {
            return NSLocalizedString("currency_usd", comment: "")
        } else if (getCurrency() == 1) {
            return NSLocalizedString("currency_eur", comment: "")
        } else if (getCurrency() == 2) {
            return NSLocalizedString("currency_krw", comment: "")
        } else if (getCurrency() == 3) {
            return NSLocalizedString("currency_jpy", comment: "")
        } else if (getCurrency() == 4) {
            return NSLocalizedString("currency_cny", comment: "")
        } else if (getCurrency() == 5) {
            return NSLocalizedString("currency_rub", comment: "")
        } else if (getCurrency() == 6) {
            return NSLocalizedString("currency_gbp", comment: "")
        } else if (getCurrency() == 7) {
            return NSLocalizedString("currency_inr", comment: "")
        } else if (getCurrency() == 8) {
            return NSLocalizedString("currency_brl", comment: "")
        } else if (getCurrency() == 9) {
            return NSLocalizedString("currency_idr", comment: "")
        } else if (getCurrency() == 10) {
            return NSLocalizedString("currency_dkk", comment: "")
        } else if (getCurrency() == 11) {
            return NSLocalizedString("currency_nok", comment: "")
        } else if (getCurrency() == 12) {
            return NSLocalizedString("currency_sek", comment: "")
        } else if (getCurrency() == 13) {
            return NSLocalizedString("currency_chf", comment: "")
        } else if (getCurrency() == 14) {
            return NSLocalizedString("currency_aud", comment: "")
        }
        return ""
    }
    
    func getCurrencySymbol() -> String {
        if (getCurrency() == 0) {
            return NSLocalizedString("currency_usd_symbol", comment: "")
        } else if (getCurrency() == 1) {
            return NSLocalizedString("currency_eur_symbol", comment: "")
        } else if (getCurrency() == 2) {
            return NSLocalizedString("currency_krw_symbol", comment: "")
        } else if (getCurrency() == 3) {
            return NSLocalizedString("currency_jpy_symbol", comment: "")
        } else if (getCurrency() == 4) {
            return NSLocalizedString("currency_cny_symbol", comment: "")
        } else if (getCurrency() == 5) {
            return NSLocalizedString("currency_rub_symbol", comment: "")
        } else if (getCurrency() == 6) {
            return NSLocalizedString("currency_gbp_symbol", comment: "")
        } else if (getCurrency() == 7) {
            return NSLocalizedString("currency_inr_symbol", comment: "")
        } else if (getCurrency() == 8) {
            return NSLocalizedString("currency_brl_symbol", comment: "")
        } else if (getCurrency() == 9) {
            return NSLocalizedString("currency_idr_symbol", comment: "")
        } else if (getCurrency() == 10) {
            return NSLocalizedString("currency_dkk_symbol", comment: "")
        } else if (getCurrency() == 11) {
            return NSLocalizedString("currency_nok_symbol", comment: "")
        } else if (getCurrency() == 12) {
            return NSLocalizedString("currency_sek_symbol", comment: "")
        } else if (getCurrency() == 13) {
            return NSLocalizedString("currency_chf_symbol", comment: "")
        } else if (getCurrency() == 14) {
            return NSLocalizedString("currency_aud_symbol", comment: "")
        }
        return ""
    }
    
    func setUsingAppLock(_ using : Bool) {
        UserDefaults.standard.set(using, forKey: KEY_USING_APP_LOCK)
    }
    
    func getUsingAppLock() -> Bool {
        return UserDefaults.standard.bool(forKey: KEY_USING_APP_LOCK)
    }
    
    func setUsingBioAuth(_ using : Bool) {
        UserDefaults.standard.set(using, forKey: KEY_USING_BIO_AUTH)
    }
    
    func getUsingBioAuth() -> Bool {
        return UserDefaults.standard.bool(forKey: KEY_USING_BIO_AUTH)
    }
    
    func setUsingEnginerMode(_ using : Bool) {
        UserDefaults.standard.set(using, forKey: KEY_ENGINER_MODE)
    }
    
    func getUsingEnginerMode() -> Bool {
        return UserDefaults.standard.bool(forKey: KEY_ENGINER_MODE)
    }
    
    
    func setFCMToken(_ token : String) {
        UserDefaults.standard.set(token, forKey: KEY_FCM_TOKEN)
    }
    
    func getFCMToken() -> String {
        return UserDefaults.standard.string(forKey: KEY_FCM_TOKEN) ?? ""
    }
    
    func setKavaWarn() {
        let remindTime = Calendar.current.date(byAdding: .day, value: 3, to: Date())?.millisecondsSince1970
        UserDefaults.standard.set(String(remindTime!), forKey: KEY_KAVA_TESTNET_WARN)
    }
    
    func getKavaWarn() ->Bool {
        let reminTime = Int64(UserDefaults.standard.string(forKey: KEY_KAVA_TESTNET_WARN) ?? "0")
        if (Date().millisecondsSince1970 > reminTime!) {
            return true
        }
        return false
    }
    
    func setEventTime() {
        let remindTime = Calendar.current.date(byAdding: .day, value: 1, to: Date())?.millisecondsSince1970
        UserDefaults.standard.set(String(remindTime!), forKey: KEY_PRE_EVENT_HIDE)
    }
    
    func getEventTime() -> Bool {
        let reminTime = Int64(UserDefaults.standard.string(forKey: KEY_PRE_EVENT_HIDE) ?? "0")
        if (Date().millisecondsSince1970 > reminTime!) {
            return true
        }
        return false
    }
    
    
    func initdb() {
        do {
            let documentDirectory = try FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
            var fileUrl = documentDirectory.appendingPathComponent("cosmostation").appendingPathExtension("sqlite3")
            do {
                var resourceValues = URLResourceValues()
                resourceValues.isExcludedFromBackup = true
                try fileUrl.setResourceValues(resourceValues)
                
            } catch { print("failed to set resource value") }
            
            let database = try Connection(fileUrl.path)
            self.database = database
            
            let createAccountTable = DB_ACCOUNT.create(ifNotExists: true) { (table) in
                table.column(DB_ACCOUNT_ID, primaryKey: true)
                table.column(DB_ACCOUNT_UUID)
                table.column(DB_ACCOUNT_NICKNAME)
                table.column(DB_ACCOUNT_FAVO)
                table.column(DB_ACCOUNT_ADDRESS)
                table.column(DB_ACCOUNT_BASECHAIN)
                table.column(DB_ACCOUNT_HAS_PRIVATE)
                table.column(DB_ACCOUNT_RESOURCE)
                table.column(DB_ACCOUNT_FROM_MNEMONIC)
                table.column(DB_ACCOUNT_PATH)
                table.column(DB_ACCOUNT_IS_VALIDATOR)
                table.column(DB_ACCOUNT_SEQUENCE_NUMBER)
                table.column(DB_ACCOUNT_ACCOUNT_NUMBER)
                table.column(DB_ACCOUNT_FETCH_TIME)
                table.column(DB_ACCOUNT_M_SIZE)
                table.column(DB_ACCOUNT_IMPORT_TIME)
            }
            try self.database.run(createAccountTable)
            do {
                try self.database.run(DB_ACCOUNT.addColumn(DB_ACCOUNT_LAST_TOTAL, defaultValue: ""))
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            
            do {
                try self.database.run(DB_ACCOUNT.addColumn(DB_ACCOUNT_SORT_ORDER, defaultValue: 0))
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            
            do {
                try self.database.run(DB_ACCOUNT.addColumn(DB_ACCOUNT_PUSHALARM, defaultValue: false))
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            
            do {
                try self.database.run(DB_ACCOUNT.addColumn(DB_ACCOUNT_NEW_BIP, defaultValue: false))
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            do {
                try self.database.run(DB_ACCOUNT.addColumn(DB_ACCOUNT_CUSTOM_PATH, defaultValue: 0))
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            
//            let createPasswordTable = DB_PASSWORD.create { (table) in
//                table.column(DB_PASSWORD_ID, primaryKey: true)
//                table.column(DB_PASSWORD_RESOURCE)
//            }
//            try self.database.run(createPasswordTable)
            
            let createBalanceTable = DB_BALANCE.create(ifNotExists: true) { (table) in
                table.column(DB_BALANCE_ID, primaryKey: true)
                table.column(DB_BALANCE_ACCOUNT_ID)
                table.column(DB_BALANCE_DENOM)
                table.column(DB_BALANCE_AMOUNT)
                table.column(DB_BALANCE_FETCH_TIME)
                table.column(DB_BALANCE_FROZEN)
                table.column(DB_BALANCE_LOCKED)
            }
            try self.database.run(createBalanceTable)
            do {
                try self.database.run(DB_BALANCE.addColumn(DB_BALANCE_FROZEN, defaultValue: ""))
            } catch {
                if (SHOW_LOG) { print(error) }
            }
            do {
                try self.database.run(DB_BALANCE.addColumn(DB_BALANCE_LOCKED, defaultValue: ""))
            } catch {
                if (SHOW_LOG) { print(error) }
            }

            
            let createBondingTable = DB_BONDING.create(ifNotExists: true) { (table) in
                table.column(DB_BONDING_ID, primaryKey: true)
                table.column(DB_BONDING_ACCOUNT_ID)
                table.column(DB_BONDING_V_Address)
                table.column(DB_BONDING_SHARES)
                table.column(DB_BONDING_FETCH_TIME)
            }
            try self.database.run(createBondingTable)
            
            let createUnBondingTable = DB_UNBONDING.create(ifNotExists: true) { (table) in
                table.column(DB_UNBONDING_ID, primaryKey: true)
                table.column(DB_UNBONDING_ACCOUNT_ID)
                table.column(DB_UNBONDING_V_Address)
                table.column(DB_UNBONDING_CREATE_HEIGHT)
                table.column(DB_UNBONDING_COMPLETE_TIME)
                table.column(DB_UNBONDING_INITIAL_BALANCE)
                table.column(DB_UNBONDING_BALANCE)
                table.column(DB_UNBONDING_FETCH_TIME)
            }
            try self.database.run(createUnBondingTable)
            
        } catch {
            if(SHOW_LOG) { print(error) }
        }
    }
    
    
    public func selectAllAccounts() -> Array<Account> {
        var result = Array<Account>()
        do {
            for accountBD in try database.prepare(DB_ACCOUNT) {
                let account = Account(accountBD[DB_ACCOUNT_ID], accountBD[DB_ACCOUNT_UUID], accountBD[DB_ACCOUNT_NICKNAME], accountBD[DB_ACCOUNT_FAVO], accountBD[DB_ACCOUNT_ADDRESS], accountBD[DB_ACCOUNT_BASECHAIN], accountBD[DB_ACCOUNT_HAS_PRIVATE],  accountBD[DB_ACCOUNT_RESOURCE], accountBD[DB_ACCOUNT_FROM_MNEMONIC], accountBD[DB_ACCOUNT_PATH], accountBD[DB_ACCOUNT_IS_VALIDATOR], accountBD[DB_ACCOUNT_SEQUENCE_NUMBER], accountBD[DB_ACCOUNT_ACCOUNT_NUMBER], accountBD[DB_ACCOUNT_FETCH_TIME], accountBD[DB_ACCOUNT_M_SIZE], accountBD[DB_ACCOUNT_IMPORT_TIME], accountBD[DB_ACCOUNT_LAST_TOTAL], accountBD[DB_ACCOUNT_SORT_ORDER], accountBD[DB_ACCOUNT_PUSHALARM], accountBD[DB_ACCOUNT_NEW_BIP], accountBD[DB_ACCOUNT_CUSTOM_PATH]);
                account.setBalances(selectBalanceById(accountId: account.account_id))
                result.append(account);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        var result2 = Array<Account>()
        for account in result {
            if (ChainType.IS_SUPPORT_CHAIN(account.account_base_chain)) {
                result2.append(account)
            }
        }
        return result2;
    }
    
    public func selectAllAccountsByChain(_ chain:ChainType) -> Array<Account> {
        var result = Array<Account>()
        let allAccounts = selectAllAccounts()
        for account in allAccounts {
            if (WUtils.getChainType(account.account_base_chain) == chain) {
                result.append(account)
            }
        }
        return result;
    }
    
    public func selectAllAccountsByHtlcClaim(_ chain:ChainType?) -> Array<Account> {
        var result = Array<Account>()
        let allAccounts = selectAllAccounts()
        for account in allAccounts {
            if (WUtils.getChainType(account.account_base_chain) == chain && account.account_has_private) {
                if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
                    if (WUtils.getTokenAmount(account.account_balances, BNB_MAIN_DENOM).compare(NSDecimalNumber.init(string: FEE_BNB_TRANSFER)).rawValue >= 0) {
                        result.append(account)
                    }
                    
                } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
                    result.append(account)
                }
            }
        }
        return result;
    }
    
    public func selectAccountById(id: Int64) -> Account? {
        do {
            let query = DB_ACCOUNT.filter(DB_ACCOUNT_ID == id)
            if let accountBD = try database.pluck(query) {
                let account = Account(accountBD[DB_ACCOUNT_ID], accountBD[DB_ACCOUNT_UUID], accountBD[DB_ACCOUNT_NICKNAME], accountBD[DB_ACCOUNT_FAVO], accountBD[DB_ACCOUNT_ADDRESS], accountBD[DB_ACCOUNT_BASECHAIN], accountBD[DB_ACCOUNT_HAS_PRIVATE],  accountBD[DB_ACCOUNT_RESOURCE], accountBD[DB_ACCOUNT_FROM_MNEMONIC], accountBD[DB_ACCOUNT_PATH], accountBD[DB_ACCOUNT_IS_VALIDATOR], accountBD[DB_ACCOUNT_SEQUENCE_NUMBER], accountBD[DB_ACCOUNT_ACCOUNT_NUMBER], accountBD[DB_ACCOUNT_FETCH_TIME], accountBD[DB_ACCOUNT_M_SIZE], accountBD[DB_ACCOUNT_IMPORT_TIME], accountBD[DB_ACCOUNT_LAST_TOTAL], accountBD[DB_ACCOUNT_SORT_ORDER], accountBD[DB_ACCOUNT_PUSHALARM], accountBD[DB_ACCOUNT_NEW_BIP], accountBD[DB_ACCOUNT_CUSTOM_PATH])
                account.setBalances(selectBalanceById(accountId: account.account_id))
                if (!ChainType.IS_SUPPORT_CHAIN(account.account_base_chain)) {
                    if (selectAllAccounts().count > 0) {
                        return selectAllAccounts()[0]
                    } else {
                        return nil
                    }
                }
                return account
            }
            return nil
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return nil
    }
    
    public func selectAccountByAddress(address: String) -> Account? {
        do {
            let query = DB_ACCOUNT.filter(DB_ACCOUNT_ADDRESS == address)
            if let accountBD = try database.pluck(query) {
                return Account(accountBD[DB_ACCOUNT_ID], accountBD[DB_ACCOUNT_UUID], accountBD[DB_ACCOUNT_NICKNAME], accountBD[DB_ACCOUNT_FAVO], accountBD[DB_ACCOUNT_ADDRESS], accountBD[DB_ACCOUNT_BASECHAIN], accountBD[DB_ACCOUNT_HAS_PRIVATE],  accountBD[DB_ACCOUNT_RESOURCE], accountBD[DB_ACCOUNT_FROM_MNEMONIC], accountBD[DB_ACCOUNT_PATH], accountBD[DB_ACCOUNT_IS_VALIDATOR], accountBD[DB_ACCOUNT_SEQUENCE_NUMBER], accountBD[DB_ACCOUNT_ACCOUNT_NUMBER], accountBD[DB_ACCOUNT_FETCH_TIME], accountBD[DB_ACCOUNT_M_SIZE], accountBD[DB_ACCOUNT_IMPORT_TIME], accountBD[DB_ACCOUNT_LAST_TOTAL], accountBD[DB_ACCOUNT_SORT_ORDER], accountBD[DB_ACCOUNT_PUSHALARM], accountBD[DB_ACCOUNT_NEW_BIP], accountBD[DB_ACCOUNT_CUSTOM_PATH])
            }
            return nil
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return nil
    }
    
    public func selectExistAccount(address: String, chain: String ) -> Account? {
        do {
            let query = DB_ACCOUNT.filter(DB_ACCOUNT_ADDRESS == address && DB_ACCOUNT_BASECHAIN == chain)
            if let accountBD = try database.pluck(query) {
                return Account(accountBD[DB_ACCOUNT_ID], accountBD[DB_ACCOUNT_UUID], accountBD[DB_ACCOUNT_NICKNAME], accountBD[DB_ACCOUNT_FAVO], accountBD[DB_ACCOUNT_ADDRESS], accountBD[DB_ACCOUNT_BASECHAIN], accountBD[DB_ACCOUNT_HAS_PRIVATE],  accountBD[DB_ACCOUNT_RESOURCE], accountBD[DB_ACCOUNT_FROM_MNEMONIC], accountBD[DB_ACCOUNT_PATH], accountBD[DB_ACCOUNT_IS_VALIDATOR], accountBD[DB_ACCOUNT_SEQUENCE_NUMBER], accountBD[DB_ACCOUNT_ACCOUNT_NUMBER], accountBD[DB_ACCOUNT_FETCH_TIME], accountBD[DB_ACCOUNT_M_SIZE], accountBD[DB_ACCOUNT_IMPORT_TIME], accountBD[DB_ACCOUNT_LAST_TOTAL], accountBD[DB_ACCOUNT_SORT_ORDER], accountBD[DB_ACCOUNT_PUSHALARM], accountBD[DB_ACCOUNT_NEW_BIP], accountBD[DB_ACCOUNT_CUSTOM_PATH])
            }
            return nil
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return nil
    }
    
    public func isDupleAccount(_ address: String, _ chain: String) -> Bool {
        do {
            let query = DB_ACCOUNT.filter(DB_ACCOUNT_ADDRESS == address && DB_ACCOUNT_BASECHAIN == chain)
            if (try database.pluck(query)) != nil {
                return true
            } else {
                return false
            }
            
        } catch {
             if(SHOW_LOG) { print(error) }
        }
        return true;
    }
    
    public func insertAccount(_ account: Account) -> Int64 {
        let insertAccount = DB_ACCOUNT.insert(DB_ACCOUNT_UUID <- account.account_uuid,
                                              DB_ACCOUNT_NICKNAME <- account.account_nick_name,
                                              DB_ACCOUNT_FAVO <- account.account_favo,
                                              DB_ACCOUNT_ADDRESS <- account.account_address,
                                              DB_ACCOUNT_BASECHAIN <- account.account_base_chain,
                                              DB_ACCOUNT_HAS_PRIVATE <- account.account_has_private,
                                              DB_ACCOUNT_RESOURCE <- account.account_resource,
                                              DB_ACCOUNT_FROM_MNEMONIC <- account.account_from_mnemonic,
                                              DB_ACCOUNT_PATH <- account.account_path,
                                              DB_ACCOUNT_IS_VALIDATOR <- account.account_is_validator,
                                              DB_ACCOUNT_SEQUENCE_NUMBER <- account.account_sequence_number,
                                              DB_ACCOUNT_ACCOUNT_NUMBER <- account.account_account_numner,
                                              DB_ACCOUNT_FETCH_TIME <- account.account_fetch_time,
                                              DB_ACCOUNT_M_SIZE <- account.account_m_size,
                                              DB_ACCOUNT_IMPORT_TIME <- account.account_import_time,
                                              DB_ACCOUNT_LAST_TOTAL <- account.account_last_total,
                                              DB_ACCOUNT_SORT_ORDER <- account.account_sort_order,
                                              DB_ACCOUNT_PUSHALARM <- account.account_push_alarm,
                                              DB_ACCOUNT_NEW_BIP <- account.account_new_bip44,
                                              DB_ACCOUNT_CUSTOM_PATH <- account.account_custom_path)
        do {
            return try database.run(insertAccount)
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    public func updateAccount(_ account: Account) -> Int64 {
        let target = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account.account_id)
        do {
            return try Int64(database.run(target.update(DB_ACCOUNT_NICKNAME <- account.account_nick_name,
                                                        DB_ACCOUNT_FAVO <- account.account_favo,
                                                        DB_ACCOUNT_BASECHAIN <- account.account_base_chain,
                                                        DB_ACCOUNT_SEQUENCE_NUMBER <- account.account_sequence_number,
                                                        DB_ACCOUNT_ACCOUNT_NUMBER <- account.account_account_numner,
                                                        DB_ACCOUNT_RESOURCE <- account.account_resource,
                                                        DB_ACCOUNT_FETCH_TIME <- account.account_fetch_time)))
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    public func overrideAccount(_ account: Account) -> Int64 {
        let target = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account.account_id)
        do {
            return try Int64(database.run(target.update(DB_ACCOUNT_HAS_PRIVATE <- account.account_has_private,
                                                        DB_ACCOUNT_FROM_MNEMONIC <- account.account_from_mnemonic,
                                                        DB_ACCOUNT_PATH <- account.account_path,
                                                        DB_ACCOUNT_M_SIZE <- account.account_m_size,
                                                        DB_ACCOUNT_NEW_BIP <- account.account_new_bip44,
                                                        DB_ACCOUNT_CUSTOM_PATH <- account.account_custom_path)))
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    
    public func upgradeAaccountAddressforOk() {
        let allOkAccount = BaseData.instance.selectAllAccountsByChain(ChainType.OKEX_MAIN)
        for account in allOkAccount {
            if (account.account_address.starts(with: "okexchain")) {
                account.account_address = WKey.getUpgradeOKAddress(account.account_address)
                updateAccountAddress(account)
            }
        }
        let allOkTestAccount = BaseData.instance.selectAllAccountsByChain(ChainType.OKEX_TEST)
        for account in allOkTestAccount {
            if (account.account_address.starts(with: "okexchain")) {
                account.account_address = WKey.getUpgradeOKAddress(account.account_address)
                updateAccountAddress(account)
            }
        }
    }
    
    //for okchain display address
    public func updateAccountAddress(_ account: Account) -> Int64 {
        let target = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account.account_id)
        do {
            return try Int64(database.run(target.update(DB_ACCOUNT_ADDRESS <- account.account_address)))
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    public func updateLastTotal(_ account: Account?, _ amount: String) {
        if (account == nil) { return}
        let target = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account!.account_id)
        do {
            try database.run(target.update(DB_ACCOUNT_LAST_TOTAL <- amount))
        } catch {
            if(SHOW_LOG) { print(error) }
        }
    }
    
    public func updateSortOrder(_ accounts: Array<Account>) {
        for account in accounts {
            let target = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account.account_id)
            do {
                try database.run(target.update(DB_ACCOUNT_SORT_ORDER <- account.account_sort_order))
            } catch {
                if(SHOW_LOG) { print(error) }
            }
        }
   }
    
    public func updatePushAlarm(_ account: Account, _ enable: Bool){
        let target = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account.account_id)
        do {
            try database.run(target.update(DB_ACCOUNT_PUSHALARM <- enable))
        } catch {
            if(SHOW_LOG) { print(error) }
        }
    }
    
    public func deleteAccount(account: Account) -> Int {
        let query = DB_ACCOUNT.filter(DB_ACCOUNT_ID == account.account_id)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    
    
    
    public func hasPassword() -> Bool{
        if(KeychainWrapper.standard.hasValue(forKey: "password")) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
    public func selectPassword() -> Password? {
        do {
            for passwordBD in try database.prepare(DB_PASSWORD) {
                return Password(passwordBD[DB_PASSWORD_ID], passwordBD[DB_PASSWORD_RESOURCE])
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return nil;
    }
    
    public func hasPassword() -> Bool{
        do {
            for _ in try database.prepare(DB_PASSWORD) {
                return true;
            }
            return false;
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return false;
    }
    
    
    public func insertPassword(password: Password) -> Int64 {
        if(hasPassword()) { return -1; }
        let insertPassword = DB_PASSWORD.insert(DB_PASSWORD_ID <- password.password_id,
                                              DB_PASSWORD_RESOURCE <- password.password_resource)
        do {
            return try database.run(insertPassword)
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    */
    
    
    
    
    
    public func selectAllBalances() -> Array<Balance> {
        var result = Array<Balance>()
        do {
            for balanceBD in try database.prepare(DB_BALANCE) {
                let balance = Balance(balanceBD[DB_BALANCE_ID], balanceBD[DB_BALANCE_ACCOUNT_ID],
                                      balanceBD[DB_BALANCE_DENOM], balanceBD[DB_BALANCE_AMOUNT],
                                      balanceBD[DB_BALANCE_FETCH_TIME], balanceBD[DB_BALANCE_FROZEN],
                                      balanceBD[DB_BALANCE_LOCKED])
                result.append(balance);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return result;
    }

    public func selectBalanceById(accountId: Int64) -> Array<Balance> {
        var result = Array<Balance>()
        do {
            for balanceBD in try database.prepare(DB_BALANCE.filter(DB_BALANCE_ACCOUNT_ID == accountId)) {
                let balance = Balance(balanceBD[DB_BALANCE_ID], balanceBD[DB_BALANCE_ACCOUNT_ID],
                                      balanceBD[DB_BALANCE_DENOM], balanceBD[DB_BALANCE_AMOUNT],
                                      balanceBD[DB_BALANCE_FETCH_TIME], balanceBD[DB_BALANCE_FROZEN],
                                      balanceBD[DB_BALANCE_LOCKED])
                result.append(balance);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return result
    }
    
    public func deleteBalance(account: Account) -> Int {
        let query = DB_BALANCE.filter(DB_BALANCE_ACCOUNT_ID == account.account_id)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    public func deleteBalanceById(accountId: Int64) -> Int {
        let query = DB_BALANCE.filter(DB_BALANCE_ACCOUNT_ID == accountId)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    public func insertBalance(balance: Balance) -> Int64 {
        let insertBalance = DB_BALANCE.insert(DB_BALANCE_ACCOUNT_ID <- balance.balance_account_id,
                                              DB_BALANCE_DENOM <- balance.balance_denom,
                                              DB_BALANCE_AMOUNT <- balance.balance_amount,
                                              DB_BALANCE_FETCH_TIME <- balance.balance_fetch_time,
                                              DB_BALANCE_FROZEN <- balance.balance_frozen,
                                              DB_BALANCE_LOCKED <- balance.balance_locked)
        do {
            return try database.run(insertBalance)
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }
    
    public func updateBalances(_ accountId: Int64, _ newBalances: Array<Balance>) {
        if(newBalances.count == 0) {
            _ = deleteBalanceById(accountId: accountId)
            return
        }
        _ = deleteBalanceById(accountId: newBalances[0].balance_account_id)
        for balance in newBalances {
            _ = self.insertBalance(balance: balance)
        }
    }
    
    
    
    /*
    public func selectAllBondings() -> Array<Bonding> {
        var result = Array<Bonding>()
        do {
            for bondingBD in try database.prepare(DB_BONDING) {
                let bonding = Bonding(bondingBD[DB_BONDING_ID], bondingBD[DB_BONDING_ACCOUNT_ID],
                                      bondingBD[DB_BONDING_V_Address], bondingBD[DB_BONDING_SHARES],
                                      bondingBD[DB_BONDING_FETCH_TIME])
                result.append(bonding);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return result;
    }

    public func selectBondingById(accountId: Int64) -> Array<Bonding> {
        var result = Array<Bonding>()
        do {
            for bondingBD in try database.prepare(DB_BONDING.filter(DB_BONDING_ACCOUNT_ID == accountId)) {
                let bonding = Bonding(bondingBD[DB_BONDING_ID], bondingBD[DB_BONDING_ACCOUNT_ID],
                                      bondingBD[DB_BONDING_V_Address], bondingBD[DB_BONDING_SHARES],
                                      bondingBD[DB_BONDING_FETCH_TIME])
                result.append(bonding);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return result
    }

    public func selectBondingWithValAdd(_ accountId: Int64, _ valAddr: String) -> Bonding? {
        do {
            for bondingBD in try database.prepare(DB_BONDING.filter(DB_BONDING_ACCOUNT_ID == accountId && DB_BONDING_V_Address == valAddr)) {
                let bonding = Bonding(bondingBD[DB_BONDING_ID], bondingBD[DB_BONDING_ACCOUNT_ID],
                                      bondingBD[DB_BONDING_V_Address], bondingBD[DB_BONDING_SHARES],
                                      bondingBD[DB_BONDING_FETCH_TIME])
                return bonding
            }
        } catch {
            return nil
        }
        return nil
    }

    public func deleteBonding(account: Account) -> Int {
        let query = DB_BONDING.filter(DB_BONDING_ACCOUNT_ID == account.account_id)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }

    public func deleteBondingById(accountId: Int64) -> Int {
        let query = DB_BONDING.filter(DB_BONDING_ACCOUNT_ID == accountId)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }

    public func insertBonding(bonding: Bonding) -> Int64 {
        let insertBonding = DB_BONDING.insert(DB_BONDING_ACCOUNT_ID <- bonding.bonding_account_id,
                                              DB_BONDING_V_Address <- bonding.bonding_v_address,
                                              DB_BONDING_SHARES <- bonding.bonding_shares,
                                              DB_BONDING_FETCH_TIME <- bonding.bonding_fetch_time)
        do {
            return try database.run(insertBonding)
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }

    public func updateBondings(_ newBondings: Array<Bonding>) {
        if (newBondings.count > 0) {
            _ = deleteBondingById(accountId: newBondings[0].bonding_account_id)
            for bonding in newBondings {
                _ = self.insertBonding(bonding: bonding)
            }
        }
    }




    public func selectAllUnbondings() -> Array<Unbonding> {
        var result = Array<Unbonding>()
        do {
            for unbondingBD in try database.prepare(DB_UNBONDING) {
                let unbonding = Unbonding(unbondingBD[DB_UNBONDING_ID], unbondingBD[DB_UNBONDING_ACCOUNT_ID],
                                      unbondingBD[DB_UNBONDING_V_Address], unbondingBD[DB_UNBONDING_CREATE_HEIGHT],
                                      unbondingBD[DB_UNBONDING_COMPLETE_TIME], unbondingBD[DB_UNBONDING_INITIAL_BALANCE],
                                      unbondingBD[DB_UNBONDING_BALANCE], unbondingBD[DB_UNBONDING_FETCH_TIME])
                result.append(unbonding);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return result;
    }

    public func selectUnbondingById(accountId: Int64) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        do {
            for unbondingBD in try database.prepare(DB_UNBONDING.filter(DB_UNBONDING_ACCOUNT_ID == accountId)) {
                let unbonding = Unbonding(unbondingBD[DB_UNBONDING_ID], unbondingBD[DB_UNBONDING_ACCOUNT_ID],
                                          unbondingBD[DB_UNBONDING_V_Address], unbondingBD[DB_UNBONDING_CREATE_HEIGHT],
                                          unbondingBD[DB_UNBONDING_COMPLETE_TIME], unbondingBD[DB_UNBONDING_INITIAL_BALANCE],
                                          unbondingBD[DB_UNBONDING_BALANCE], unbondingBD[DB_UNBONDING_FETCH_TIME])
                result.append(unbonding);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }
        return result
    }

    public func selectUnBondingWithValAdd(_ accountId: Int64, _ valAddr: String) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        do {
            for unbondingBD in try database.prepare(DB_UNBONDING.filter(DB_UNBONDING_ACCOUNT_ID == accountId && DB_UNBONDING_V_Address == valAddr)) {
                let unbonding = Unbonding(unbondingBD[DB_UNBONDING_ID], unbondingBD[DB_UNBONDING_ACCOUNT_ID],
                                          unbondingBD[DB_UNBONDING_V_Address], unbondingBD[DB_UNBONDING_CREATE_HEIGHT],
                                          unbondingBD[DB_UNBONDING_COMPLETE_TIME], unbondingBD[DB_UNBONDING_INITIAL_BALANCE],
                                          unbondingBD[DB_UNBONDING_BALANCE], unbondingBD[DB_UNBONDING_FETCH_TIME])
                result.append(unbonding);
            }
        } catch {
            if(SHOW_LOG) { print(error) }
        }

        return result
    }

    public func deleteUnbonding(account: Account) -> Int {
        let query = DB_UNBONDING.filter(DB_UNBONDING_ACCOUNT_ID == account.account_id)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }

    public func deleteUnbondingById(accountId: Int64) -> Int {
        let query = DB_UNBONDING.filter(DB_UNBONDING_ACCOUNT_ID == accountId)
        do {
            return  try database.run(query.delete())
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }

    public func insertUnbonding(unbonding: Unbonding) -> Int64 {
        let insertUnbonding = DB_UNBONDING.insert(DB_UNBONDING_ACCOUNT_ID <- unbonding.unbonding_account_id,
                                                  DB_UNBONDING_V_Address <- unbonding.unbonding_v_address,
                                                  DB_UNBONDING_CREATE_HEIGHT <- unbonding.unbonding_create_height,
                                                  DB_UNBONDING_COMPLETE_TIME <- unbonding.unbonding_complete_time,
                                                  DB_UNBONDING_INITIAL_BALANCE <- unbonding.unbonding_inital_balance,
                                                  DB_UNBONDING_BALANCE <- unbonding.unbonding_balance,
                                                  DB_UNBONDING_FETCH_TIME <- unbonding.unbonding_fetch_time)
        do {
            return try database.run(insertUnbonding)
        } catch {
            if(SHOW_LOG) { print(error) }
            return -1
        }
    }

    public func updateUnbondings(_ accountId: Int64, _ newUnbondings: Array<Unbonding>) {
        _ = deleteUnbondingById(accountId: accountId)
        if (newUnbondings.count > 0) {
            for unbonding in newUnbondings {
                _ = self.insertUnbonding(unbonding: unbonding)
            }
        }
    }
    */
    
}

extension Connection {
    public var userVersion: Int32 {
        get { return Int32(try! scalar("PRAGMA user_version") as! Int64)}
        set { try! run("PRAGMA user_version = \(newValue)") }
    }
}
