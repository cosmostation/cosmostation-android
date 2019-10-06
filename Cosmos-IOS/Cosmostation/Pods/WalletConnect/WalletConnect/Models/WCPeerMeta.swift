//
//  WCClientMeta.swift
//  WalletConnect
//
//  Created by Tao Xu on 4/1/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation

public struct WCPeerMeta: Codable {
    public let name: String
    public let url: String
    public let description: String
    public let icons: [String]


    public init(name: String, url: String, description: String = "", icons: [String] = []) {
        self.name = name
        self.url = url
        self.description = description
        self.icons = icons
    }
}
