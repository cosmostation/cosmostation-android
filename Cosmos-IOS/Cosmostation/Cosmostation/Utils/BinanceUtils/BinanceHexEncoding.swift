//
//  BinanceHexEncoding.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/06/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import Alamofire

struct HexEncoding: ParameterEncoding {
    
    private let data: Data
    
    init(data: Data) {
        self.data = data
    }
    
    func encode(_ urlRequest: URLRequestConvertible, with parameters: Parameters?) throws -> URLRequest {
        var urlRequest = try urlRequest.asURLRequest()
        urlRequest.setValue("text/plain", forHTTPHeaderField: "Content-Type")
        urlRequest.httpBody = data.hexdata
        return urlRequest
    }

}
