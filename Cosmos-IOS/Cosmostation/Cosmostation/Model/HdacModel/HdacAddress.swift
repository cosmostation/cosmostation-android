//
//  HdacAddress.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/21.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit

//public struct HdacAddress: HDWalletKit.Address {
//    public var publicKey: Data?
//
//    public var coin: HDWalletKit.Coin
//    public var type: HDWalletKit.AddressType
//    public var data: Data
//    public var base58: String
//    public var cashaddr: String
//
//    public init(_ address: String, coin: HDWalletKit.Coin) throws {
//        guard let raw = Base58.decode(address) else {
//            throw AddressError.invalid
//        }
//        let checksum = raw.suffix(4)
//        let pubKeyHash = raw.dropLast(4)
//        let checksumConfirm = pubKeyHash.doubleSHA256.prefix(4)
//        self.coin = coin
//
//        let type: AddressType
//        let addressPrefix = pubKeyHash[0]
//        switch addressPrefix {
//        case coin.publicKeyHash:
//            type = .pubkeyHash
//        case coin.wifPrefix:
//            type = .wif
//        case coin.scripthash:
//            type = .scriptHash
//        default:
//            throw AddressError.invalidVersionByte
//        }
//
//        self.type = type
//        self.data = pubKeyHash.dropFirst()
//        self.base58 = address
//
//        // cashaddr
//        switch type {
//        case .pubkeyHash:
//            let payload = Data([coin.publicKeyHash]) + self.data
//            self.cashaddr = HDWalletKit.Bech32.encode(payload, prefix: coin.scheme)
//        case .wif:
//            let payload = Data([coin.wifPrefix]) + self.data
//            self.cashaddr = HDWalletKit.Bech32.encode(payload, prefix: coin.scheme)
//        default:
//            self.cashaddr = ""
//        }
//    }
//}

