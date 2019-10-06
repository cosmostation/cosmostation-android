//
//  WCBinanceTradePair.swift
//  WalletConnect
//
//  Created by Tao Xu on 4/18/19.
//

import Foundation

public struct WCBinanceTradePair {
    public let from: String
    public let to: String

    public static func from(_ symbol: String) -> WCBinanceTradePair? {
        let pair = symbol.split(separator: "_")
        guard pair.count > 1 else { return nil }
        let first_parts = pair[0].split(separator: "-")
        let second_parts = pair[1].split(separator: "-")
        return WCBinanceTradePair(from: String(first_parts[0]), to: String(second_parts[0]))
    }
}
