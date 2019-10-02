import Foundation

extension Dictionary where Key == String, Value == Any {

    var query: String {
        let items: [URLQueryItem] = self.compactMap { URLQueryItem(name: $0.key, value: String(describing: $0.value)) }
        let url = NSURLComponents()
        url.queryItems = items
        return url.query ?? ""
    }

}

