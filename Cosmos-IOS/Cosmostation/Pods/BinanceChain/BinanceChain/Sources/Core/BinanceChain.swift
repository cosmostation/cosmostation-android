import Foundation
import Alamofire
import SwiftyJSON
import XCGLogger

// https://binance-chain.github.io/api-reference/dex-api/paths.html

public class BinanceChain {

    public enum Endpoint: String {
        case mainnet = "https://dex.binance.org/api/v1"
        case testnet = "https://testnet-dex.binance.org/api/v1"
    }

    internal enum Path: String {
        case time = "time"
        case nodeInfo = "node-info"
        case validators = "validators"
        case peers = "peers"
        case account = "account"
        case sequence = "sequence"
        case tx = "tx"
//        case tokens = "tokens"
        case markets = "markets"
        case fees = "fees"
        case depth = "depth"
        case broadcast = "broadcast"
        case klines = "klines"
        case closedOrders = "orders/closed"
        case openOrders = "orders/open"
        case orders = "orders"
        case ticker = "ticker/24hr"
        case trades = "trades"
        case transactions = "transactions"
    }

    public class Response {
        public var isError: Bool = false
        public var error: Error?
        public var sequence: Int = 0
        public var blockHeight: Int = 0
        public var fees: [Fee] = []
        public var peers: [Peer] = []
//        public var tokens: [Token] = []
        public var trades: [Trade] = []
        public var markets: [Market] = []
        public var candlesticks: [Candlestick] = []
        public var ticker: [TickerStatistics] = []
        public var broadcast: [Transaction] = []
        public var orders: [Order] = []
        public var order: Order = Order()
        public var orderList: OrderList = OrderList()
        public var tx: Tx = Tx()
        public var transfer: Transfer = Transfer()
        public var time: Times = Times()
        public var account: Account = Account()
        public var validators: Validators = Validators()
        public var marketDepth: MarketDepth = MarketDepth()
        public var marketDepthUpdate: MarketDepthUpdate = MarketDepthUpdate()
        public var nodeInfo: NodeInfo = NodeInfo()
        public var transactions: Transactions = Transactions()
    }
    
    public typealias Completion = (BinanceChain.Response)->()

    private var endpoint: String = Endpoint.testnet.rawValue

    public init() {
    }

    public required convenience init(endpoint: URL) {
        self.init()
        self.endpoint = endpoint.absoluteString
    }

    public convenience init(endpoint: Endpoint) {
        self.init()
        self.endpoint = endpoint.rawValue
    }
    
    // MARK: - HTTP API

    public func time(completion: Completion? = nil) {
        self.api(path: .time, method: .get, parser: TimesParser(), completion: completion)
    }

    public func nodeInfo(completion: Completion? = nil) {
        self.api(path: .nodeInfo, method: .get, parser: NodeInfoParser(), completion: completion)
    }

    public func validators(completion: Completion? = nil) {
        self.api(path: .validators, method: .get, parser: ValidatorsParser(), completion: completion)
    }

    public func peers(completion: Completion? = nil) {
        self.api(path: .peers, method: .get, parser: PeerParser(), completion: completion)
    }

    public func account(address: String, completion: Completion? = nil) {
        let path = String(format: "%@/%@", Path.account.rawValue, address)
        self.api(path: path, method: .get, parser: AccountParser(), completion: completion)
    }

    public func sequence(address: String, completion: Completion? = nil) {
        let path = String(format: "%@/%@/%@", Path.account.rawValue, address, Path.sequence.rawValue)
        self.api(path: path, method: .get, parser: SequenceParser(), completion: completion)
    }

    public func tx(hash: String, completion: Completion? = nil) {
        let path = String(format: "%@/%@?format=json", Path.tx.rawValue, hash)
        self.api(path: path, method: .get, parser: TxParser(), completion: completion)
    }

//    public func tokens(limit: Limit? = nil, offset: Int? = nil, completion: Completion? = nil) {
//        var parameters: Parameters = [:]
//        if let limit = limit { parameters["limit"] = limit.rawValue }
//        if let offset = offset { parameters["offset"] = offset }
//        self.api(path: .tokens, method: .get, parameters: parameters, parser: TokenParser(), completion: completion)
//    }

    public func markets(limit: Limit? = nil, offset: Int? = nil, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        if let limit = limit { parameters["limit"] = limit.rawValue }
        if let offset = offset { parameters["offset"] = offset }
        self.api(path: .markets, method: .get, parameters: parameters, parser: MarketsParser(), completion: completion)
    }

    public func fees(completion: Completion? = nil) {
        self.api(path: .fees, method: .get, parser: FeesParser(), completion: completion)
    }

    public func marketDepth(symbol: String, limit: Limit? = nil, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        parameters["symbol"] = symbol
        if let limit = limit { parameters["limit"] = limit.rawValue }
        self.api(path: .depth, method: .get, parameters: parameters, parser: MarketDepthParser(), completion: completion)
    }

    public func broadcast(message: Message, sync: Bool = true, completion: Completion? = nil) {

        do {
            let bytes = try message.encode()
            self.broadcast(message: bytes, sync: sync, completion: completion)
        } catch let error {
            let response = Response()
            response.isError = true
            response.error = error
            if let completion = completion {
                completion(response)
            }
        }
            
    }

    public func broadcast(message bytes: Data, sync: Bool = true, completion: Completion? = nil) {
        var path = Path.broadcast.rawValue
        if (sync) { path += "/?sync=1" }
        self.api(path: path, method: .post, body: bytes, parser: BroadcastParser(), completion: completion)
    }

    public func klines(symbol: String, interval: Interval? = nil, limit: Limit? = nil, startTime: TimeInterval? = nil, endTime: TimeInterval? = nil, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        parameters["symbol"] = symbol
        if let interval = interval { parameters["interval"] = interval.rawValue }
        if let limit = limit { parameters["limit"] = limit }
        if let startTime = startTime { parameters["startTime"] = startTime }
        if let endTime = endTime { parameters["endTime"] = endTime }
        self.api(path: .klines, method: .get, parameters: parameters, parser: CandlestickParser(), completion: completion)
    }

    public func closedOrders(address: String, endTime: TimeInterval? = nil, limit: Limit? = nil, offset: Int? = nil, side: Side? = nil, startTime: TimeInterval? = nil, status: Status? = nil, symbol: String? = nil, total: Total = .required, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        parameters["address"] = address
        parameters["total"] = total.rawValue
        if let endTime = endTime { parameters["endTime"] = endTime }
        if let limit = limit { parameters["limit"] = limit.rawValue }
        if let offset = offset { parameters["offset"] = offset }
        if let side = side { parameters["side"] = side.rawValue }
        if let startTime = startTime { parameters["startTime"] = startTime }
        if let status = status { parameters["status"] = status.rawValue }
        if let symbol = symbol { parameters["symbol"] = symbol }
        let path = String(format: "%@/?%@", Path.closedOrders.rawValue, parameters.query)
        self.api(path: path, method: .get, parser: OrderListParser(), completion: completion)
    }
 
    public func openOrders(address: String, limit: Limit? = nil, offset: Int? = nil, symbol: String? = nil, total: Total = .required, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        parameters["address"] = address
        parameters["total"] = total.rawValue
        if let limit = limit { parameters["limit"] = limit.rawValue }
        if let offset = offset { parameters["offset"] = offset }
        if let symbol = symbol { parameters["symbol"] = symbol }
        let path = String(format: "%@/?%@", Path.openOrders.rawValue, parameters.query)
        self.api(path: path, method: .get, parser: OrderListParser(), completion: completion)
    }

    public func order(id: String, completion: Completion? = nil) {
        let path = String(format: "%@/%@", Path.orders.rawValue, id)
        self.api(path: path, method: .get, parser: OrderParser(), completion: completion)
    }

    public func ticker(symbol: String, completion: Completion? = nil) {
        let path = String(format: "%@/?symbol=%@", Path.ticker.rawValue, symbol)
        self.api(path: path, method: .get, parser: TickerStatisticsParser(), completion: completion)
    }
    
    public func trades(address: String? = nil, buyerOrderId: String? = nil, end: TimeInterval? = nil, height: Double? = nil, offset: Int? = nil, quoteAsset: String? = nil, sellerOrderId: String? = nil, side: Side? = nil, start: TimeInterval? = nil, symbol: String? = nil, total: Total? = nil, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        parameters["address"] = address
        if let end = end { parameters["end"] = end }
        if let height = height { parameters["height"] = height }
        if let offset = offset { parameters["offset"] = offset }
        if let quoteAsset = quoteAsset { parameters["quoteAsset"] = quoteAsset }
        if let sellerOrderId = sellerOrderId { parameters["sellerOrderId"] = sellerOrderId }
        if let side = side { parameters["side"] = side.rawValue }
        if let start = start { parameters["start"] = start }
        if let symbol = symbol { parameters["symbol"] = symbol }
        if let total = total { parameters["total"] = total.rawValue }
        self.api(path: .trades, method: .get, parameters: parameters, parser: TradeParser(), completion: completion)
    }

    public func transactions(address: String, blockHeight: Double? = nil, endTime: TimeInterval? = nil, limit: Limit? = nil, offset: Int? = nil, side: Side? = nil, startTime: TimeInterval? = nil, txAsset: String? = nil, txType: TxType? = nil, completion: Completion? = nil) {
        var parameters: Parameters = [:]
        parameters["address"] = address
        if let blockHeight = blockHeight { parameters["blockHeight"] = blockHeight }
        if let endTime = endTime { parameters["endTime"] = endTime }
        if let limit = limit { parameters["limit"] = limit }
        if let offset = offset { parameters["offset"] = offset }
        if let side = side { parameters["side"] = side.rawValue }
        if let startTime = startTime { parameters["startTime"] = startTime }
        if let txAsset = txAsset { parameters["txAsset"] = txAsset }
        if let txType = txType { parameters["txType"] = txType.rawValue }
        self.api(path: .transactions, method: .get, parameters: parameters, parser: TransactionsParser(), completion: completion)
    }

    // MARK: - Utils

    @discardableResult
    internal func api(path: Path, method: HTTPMethod = .get, parameters: Parameters = [:], body: Data? = nil, parser: Parser = Parser(), completion: Completion? = nil) -> Request? {
        return self.api(path: path.rawValue, method: method, parameters: parameters, parser: parser, completion: completion)
    }

    @discardableResult
    internal func api(path: String, method: HTTPMethod = .get, parameters: Parameters = [:], body: Data? = nil, parser: Parser = Parser(), completion: Completion? = nil) -> Request? {

        var encoding: ParameterEncoding = URLEncoding.default
        if let body = body { encoding = HexEncoding(data: body) }
        let url = String(format: "%@/%@", self.endpoint, path)
        let request = Alamofire.request(url, method: method, parameters: parameters, encoding: encoding)
        request.validate(statusCode: 200..<300)
        request.responseData() { (http) -> Void in
            DispatchQueue.global(qos: .background).async {
                let response = BinanceChain.Response()

                switch http.result {
                case .success(let data):

                    do {
                        try parser.parse(response: response, data: data)
                    } catch {
                        response.isError = true
                        response.error = error
                    }

                case .failure(let error):

                    response.isError = true
                    response.error = error
                    if let data = http.data {
                        try? ErrorParser().parse(response: response, data: data)
                    }
                    
                }

                DispatchQueue.main.async {
                    if let completion = completion {
                        completion(response)
                    }
                }
                
            }

        }
        return request

    }
    
}
