import Foundation
import Alamofire
import SwiftyJSON

struct JSONRPCEncoding: ParameterEncoding {
    
    private let id: String
    private let method: String
    private let parameters: [String:Any]

    init(id: String, method: String, parameters: [String:Any]) {
        self.id = id
        self.method = method
        self.parameters = parameters
    }
    
    func encode(_ urlRequest: URLRequestConvertible, with parameters: Parameters?) throws -> URLRequest {

        let json: JSON = [
            "method": self.method,
            "jsonrpc": "2.0",
            "params": self.parameters,
            "id": id
        ]

        var urlRequest = try urlRequest.asURLRequest()
        urlRequest.setValue("application/json", forHTTPHeaderField: "Content-Type")
        guard let body = json.rawString() else { throw BinanceError(message: "JSON encoding error") }
        urlRequest.httpBody = body.data(using: .utf8)
        return urlRequest

    }
    
}
