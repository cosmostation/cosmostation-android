//
//  WCEvent.swift
//  WalletConnect
//
//  Created by Tao Xu on 4/1/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation

public enum WCEvent: String {
    case sessionRequest = "wc_sessionRequest"
    case sessionUpdate = "wc_sessionUpdate"
    case exchangeKey = "wc_exchangeKey"

    case ethSign = "eth_sign"
    case ethPersonalSign = "personal_sign"
    case ethSignTypeData = "eth_signTypedData"
    case ethSendTransaction = "eth_sendTransaction"

    case bnbSign = "bnb_sign"
    case bnbTransactionConfirm = "bnb_tx_confirmation"
}

extension WCEvent {
    func decode<T: Codable>(_ data: Data) throws -> JSONRPCRequest<T> {
        return try JSONDecoder().decode(JSONRPCRequest<T>.self, from: data)
    }
}
