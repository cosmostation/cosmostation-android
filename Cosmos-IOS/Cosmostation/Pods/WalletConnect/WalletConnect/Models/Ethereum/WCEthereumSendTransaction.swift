//
//  WCEthereumSendTransaction.swift
//  WalletConnect
//
//  Created by Tao Xu on 4/3/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation

public struct WCEthereumSendTransaction: Codable {
    public let from: String
    public let to: String
    public let nonce: String
    public let gasPrice: String
    public let gasLimit: String
    public let value: String
    public let data: String
}
