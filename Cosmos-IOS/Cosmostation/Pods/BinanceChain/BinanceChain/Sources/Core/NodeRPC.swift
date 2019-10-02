import Foundation
import Alamofire
import SwiftyJSON
import XCGLogger

// NodeRPC implemented using JSONRPC/HTTP
// https://docs.binance.org/api-reference/node-rpc.html

public class NodeRPC {

    internal enum Path: String {
        case abciInfo = "abci_info"
        case consensusState = "consensus_state"
        case dumpConsensusState = "dump_consensus_state"
        case genesis = "genesis"
        case health = "health"
        case netInfo = "net_info"
        case numUnconfirmedTxs = "num_unconfirmed_txs"
        case status = "status"
        case abciQuery = "abci_query"
        case block = "block"
        case blockResult = "block_result"
        case blockchain = "blockchain"
        case broadcastTxAsync = "broadcast_tx_async"
        case broadcastTxCommit = "broadcast_tx_commit"
        case broadcastTxSync = "broadcast_tx_sync"
        case commit = "commit"
        case consensusParams = "consensus_params"
        case dialSeeds = "dial_seeds"
        case dialPersistentPeers = "dial_persistent_peers"
        case subscribe = "subscribe"
        case tx = "tx"
        case txSearch = "tx_search"
        case unsubscribe = "unsubscribe"
        case unsubscribeAll = "unsubscribe_all"
        case validators = "validators"
    }

    public class Response {
        public var id: String = ""
        public var isError: Bool = false
        public var error: Error?
        public var result: [String:Any] = [:]
    }

    public typealias ConnectComplete = (Error?)->()
    public typealias Completion = (NodeRPC.Response)->()

    private var endpoint: String = "http://localhost:27147"
    
    public init() {
    }

    public convenience init(endpoint: URL) {
        self.init()
        self.endpoint = endpoint.absoluteString
    }

    public convenience init(peers: [Peer]) throws {
        self.init()
        self.endpoint = try self.url(from: peers).absoluteString
    }

    // MARK: - Node Discovery
    
    public func connect(endpoint: BinanceChain.Endpoint, completion: @escaping ConnectComplete) {

        let binancechain = BinanceChain(endpoint: endpoint)
        binancechain.peers() { (response) in
            do {
                if let error = response.error { return completion(error) }
                self.endpoint = try self.url(from: response.peers).absoluteString
                completion(nil)
            } catch let error {
                completion(error)
            }
        }

    }

    private func url(from peers: [Peer]) throws -> URL {
        let nodes = peers.filter({ $0.capabilities.contains("node") })
        guard let node = nodes.first else { throw BinanceError(message: "No peer found supports JSONRPC/HTTP") }
        guard let url = URL(string: node.accessAddr) else { throw BinanceError(message: "Invalid peer URL: \(node.accessAddr)") }
        return url
    }
    
    // MARK: - HTTP API

    public func abciInfo(completion: Completion? = nil) {
        self.api(path: .abciInfo, completion: completion)
    }

    public func consensusState(completion: Completion? = nil) {
        self.api(path: .consensusState, completion: completion)
    }

    public func dumpConsensusState(completion: Completion? = nil) {
        self.api(path: .dumpConsensusState, completion: completion)
    }

    public func genesis(completion: Completion? = nil) {
        self.api(path: .genesis, completion: completion)
    }

    public func health(completion: Completion? = nil) {
        self.api(path: .health, completion: completion)
    }

    public func netInfo(completion: Completion? = nil) {
        self.api(path: .netInfo, completion: completion)
    }

    public func status(completion: Completion? = nil) {
        self.api(path: .status, completion: completion)
    }
    
    public func abciQuery(path: QueryPath? = nil, data: Data, height: Int? = nil, prove: Bool? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        parameters["data"] = data.hexlify
        if let path = path { parameters["path"] = path.rawValue }
        if let height = height { parameters["height"] = height }
        if let prove = prove { parameters["prove"] = prove }
        self.api(path: .abciQuery, parameters: parameters, completion: completion)
    }

    public func block(height: Int? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        if let height = height { parameters["height"] = height }
        self.api(path: .block, parameters: parameters, completion: completion)
    }

    public func blockResult(height: Int? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        if let height = height { parameters["height"] = height }
        self.api(path: .blockResult, parameters: parameters, completion: completion)
    }

    public func blockchain(minHeight: Int, maxHeight: Int, completion: Completion? = nil) {
        let parameters: [String:Any] = [ "minHeight": minHeight, "maxHeight": maxHeight ]
        self.api(path: .blockchain, parameters: parameters, completion: completion)
    }

    public func broadcastAsync(message: Message, completion: Completion? = nil) {
        return self.broadcast(path: .broadcastTxAsync, message: message, completion: completion)
    }

    public func broadcastCommit(message: Message, completion: Completion? = nil) {
        return self.broadcast(path: .broadcastTxCommit, message: message, completion: completion)
    }

    public func broadcastSync(message: Message, completion: Completion? = nil) {
        return self.broadcast(path: .broadcastTxSync, message: message, completion: completion)
    }

    private func broadcast(path: Path, message: Message, completion: Completion? = nil) {
        do {
            let data = try message.encode()
            let parameters: [String:Any] = [ "tx": data.hexlify ]
            self.api(path: path, parameters: parameters, completion: completion)
        } catch let error {
            let response = Response()
            response.isError = true
            response.error = error
            if let completion = completion { completion(response) }
        }
    }
    
    public func commit(height: Int? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        if let height = height { parameters["height"] = height }
        self.api(path: .commit, parameters: parameters, completion: completion)
    }

    public func consensusParams(height: Int? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        if let height = height { parameters["height"] = height }
        self.api(path: .consensusParams, parameters: parameters, completion: completion)
    }

    public func dialSeeds(seeds: String, completion: Completion? = nil) {
        let parameters: [String:Any] = [ "seeds": seeds ]
        self.api(path: .dialSeeds, parameters: parameters, completion: completion)
    }

    public func dialPersistentPeers(peers: String, completion: Completion? = nil) {
        let parameters: [String:Any] = [ "persistent_peers": peers ]
        self.api(path: .dialPersistentPeers, parameters: parameters, completion: completion)
    }

    public func subscribe(query: String, completion: Completion? = nil) {
        let parameters: [String:Any] = [ "query": query ]
        self.api(path: .subscribe, parameters: parameters, completion: completion)
    }

    public func tx(hash: String, prove: Bool? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        parameters["tx"] = tx
        parameters["prove"] = prove
        self.api(path: .tx, parameters: parameters, completion: completion)
    }

    public func txSearch(query: String, prove: Bool, page: Int, perPage: Int, completion: Completion? = nil) {
        let parameters: [String:Any] = [ "query": query, "prove": prove, "page": page, "per_page": perPage ]
        self.api(path: .txSearch, parameters: parameters, completion: completion)
    }

    public func numberUnconfirmedTxs(limit: Int? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        if let limit = limit { parameters["limit"] = limit }
        self.api(path: .numUnconfirmedTxs, parameters: parameters, completion: completion)
    }

    public func unsubscribe(query: String, completion: Completion? = nil) {
        let parameters: [String:Any] = [ "query": query ]
        self.api(path: .unsubscribe, parameters: parameters, completion: completion)
    }

    public func unsubscribeAll(completion: Completion? = nil) {
        self.api(path: .unsubscribeAll, completion: completion)
    }

    public func validators(height: Int? = nil, completion: Completion? = nil) {
        var parameters: [String:Any] = [:]
        if let height = height { parameters["height"] = height }
        self.api(path: .validators, parameters: parameters, completion: completion)
    }

    // MARK: - Utils

    @discardableResult
    internal func api(path: Path, parameters: Parameters = [:], completion: Completion? = nil) -> Request? {

        let encoding = JSONRPCEncoding(id: UUID().uuidString, method: path.rawValue, parameters: parameters)
        let request = Alamofire.request(self.endpoint, method: .post, parameters: parameters, encoding: encoding)
        request.validate(statusCode: 200..<300)
        request.responseData() { (http) -> Void in
            DispatchQueue.global(qos: .background).async {
                let response = NodeRPC.Response()
                
                switch http.result {
                case .success(let data):

                    do {
                        try JSONRPCParser().parse(response: response, data: data)
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
