import Foundation
import SwiftyJSON
import SwiftDate

class Parser {

    struct ParseError: Error, LocalizedError {

        var model: String = ""
        var property: String = ""
        
        public var errorDescription: String? {
            guard (!self.property.isEmpty) else { return "Invalid response" }
            return String(format: "Invalid response (%@:%@)", model, property)
        }

    }

    func parse(response: BinanceChain.Response, data: Data) throws {
        guard let json = try? JSON(data: data) else {
            guard let body = String(data: data, encoding: .utf8) else { throw ParseError() }
            response.error = BinanceError(message: body)
            return
        }
        try self.parse(json, response: response)
    }

    func parse(_ json: JSON, response: BinanceChain.Response) throws {
        // Subclasses to override
    }

    func parse(response: NodeRPC.Response, data: Data) throws {
        guard let json = try? JSON(data: data) else {
            guard let body = String(data: data, encoding: .utf8) else { throw ParseError() }
            response.error = BinanceError(message: body)
            return
        }
        try self.parse(json, response: response)
    }
    
    func parse(_ json: JSON, response: NodeRPC.Response) throws {
        // Subclasses to override
    }
    
    func parseError(_ json: JSON) -> Error {
        let code = json["code"].intValue
        let message = json["message"].stringValue
        if let nested = JSON(parseJSON: message)["message"].string {
            return BinanceError(code: code, message: nested)
        }
        return BinanceError(code: code, message: message)
    }

    // MARK: - API Responses
    
    func parseTimes(_ json: JSON) throws -> Times {
        guard let apTime = json["ap_time"].string?.toDate()?.date else { throw ParseError(model: "Times", property: "ap_time") }
        guard let blockTime = json["block_time"].string?.toDate()?.date else { throw ParseError(model: "Times", property: "block_time") }
        let times = Times()
        times.apTime = apTime
        times.blockTime = blockTime
        return times
    }

    func parsePeer(_ json: JSON) -> Peer {
        let peer = Peer()
        peer.id = json["id"].stringValue
        peer.listenAddr = json["listen_addr"].stringValue
        peer.originalListenAddr = json["original_listen_addr"].stringValue
        peer.accessAddr = json["access_addr"].stringValue
        peer.streamAddr = json["stream_addr"].stringValue
        peer.network = json["network"].stringValue
        peer.version = json["version"].stringValue
        peer.capabilities = json["capabilities"].map({ $0.1.stringValue })
        peer.accelerated = json["accelerated"].boolValue
        return peer
    }

//    func parseToken(_ json: JSON) -> Token {
//        let token = Token()
//        token.name = json["name"].stringValue
//        token.symbol = json["symbol"].stringValue
//        token.originalSymbol = json["original_symbol"].stringValue
//        token.totalSupply = json["total_supply"].doubleValue
//        token.owner = json["owner"].stringValue
//        return token
//    }

    func parseTrade(_ json: JSON) throws -> Trade {
        let trade = Trade()
        trade.baseAsset = json["baseAsset"].stringValue
        trade.blockHeight = json["blockHeight"].int ?? json["E"].intValue
        trade.buyFee = json["buyFee"].stringValue
        trade.buyerId = json["buyerId"].string ?? json["ba"].stringValue
        trade.price = json["price"].string ?? json["p"].stringValue
        trade.quantity = json["quantity"].string ?? json["q"].stringValue
        trade.quoteAsset = json["quoteAsset"].stringValue
        trade.sellFee = json["sellFee"].stringValue
        trade.sellerId = json["sellerId"].string ?? json["sa"].stringValue
        trade.symbol = json["symbol"].string ?? json["s"].stringValue
        trade.tradeId = json["tradeId"].string ?? json["t"].stringValue
        trade.time = Date(millisecondsSince1970: json["time"].doubleString ?? json["T"].doubleValue)
        return trade
    }

    func parseMarketDepth(_ json: JSON) -> MarketDepth {
        let marketDepth = MarketDepth()
        let asks = json["asks"].exists() ? json["asks"] : json["a"]
        let bids = json["bids"].exists() ? json["bids"] : json["b"]
        marketDepth.asks = asks.map({ self.parsePriceQuantity($0.1) })
        marketDepth.bids = bids.map({ self.parsePriceQuantity($0.1) })
        return marketDepth
    }

    func parseMarketDepthUpdate(_ json: JSON) -> MarketDepthUpdate {
        let marketDepthUpdate = MarketDepthUpdate()
        marketDepthUpdate.symbol = json["symbol"].string ?? json["s"].stringValue
        marketDepthUpdate.depth = self.parseMarketDepth(json)
        return marketDepthUpdate
    }
    
    func parsePriceQuantity(_ json: JSON) -> PriceQuantity {
        let priceQuantity = PriceQuantity()
        priceQuantity.price = json.arrayValue[0].doubleValue
        priceQuantity.quantity = json.arrayValue[1].doubleValue
        return priceQuantity
    }

    func parseValidators(_ json: JSON) -> Validators {
        let validators = Validators()
        validators.blockHeight = json["block_height"].intValue
        validators.validators = json["validators"].map({ self.parseValidator($0.1) })
        return validators
    }
    
    func parseValidator(_ json: JSON) -> Validator {
        let validator = Validator()
        validator.address = json["address"].stringValue
        validator.publicKey = self.parsePublicKey(json["pub_key"])
        validator.votingPower = json["voting_power"].intValue
        return validator
    }

    func parsePublicKey(_ json: JSON) -> Data {
        var key = json.arrayValue.map { UInt8($0.intValue) }
        return Data(bytes: &key, count: key.count * MemoryLayout<UInt8>.size)
    }

    func parseTransaction(_ json: JSON) throws -> Transaction {
        let transaction = Transaction()
        transaction.hash = json["hash"].stringValue
        transaction.log = json["log"].stringValue
        transaction.ok = json["ok"].boolValue
        let data = JSON(parseJSON: json["data"].stringValue)
        transaction.tx = try self.parseTx(data)
        return transaction
    }

    func parseTransactions(_ json: JSON) throws -> Transactions {
        let transactions = Transactions()
        transactions.total = json["total"].intValue
        transactions.tx = try json["tx"].map({ try self.parseTx($0.1) })
        return transactions
    }

    func parseTx(_ json: JSON) throws -> Tx {
        let tx = Tx()
        tx.blockHeight = json["blockHeight"].doubleValue
        tx.code = json["code"].intValue
        tx.confirmBlocks = json["confirm_blocks"].doubleValue
        tx.data = json["data"].stringValue
        tx.fromAddr = json["from_addr"].stringValue
        tx.orderId = json["orderId"].string ?? json["order_id"].stringValue
        tx.timestamp = json["timeStamp"].string?.toDate()?.date ?? Date()
        tx.toAddr = json["toAddr"].stringValue
        tx.txAge = json["tx_age"].doubleValue
        tx.txAsset = json["txAsset"].stringValue
        tx.txFee = json["txFee"].stringValue
        tx.txHash = json["txHash"].stringValue
        guard let txType = TxType(rawValue: json["txType"].stringValue) else { throw ParseError(model: "Tx", property: "txType") }
        tx.txType = txType
        tx.value = json["value"].stringValue
        return tx
    }

    func parseNodeInfo(_ json: JSON) throws -> NodeInfo {
        let nodeInfo = NodeInfo()
        nodeInfo.id = json["id"].stringValue
        nodeInfo.listenAddr = json["listen_addr"].stringValue
        nodeInfo.network = json["network"].stringValue
        nodeInfo.version = json["version"].stringValue
        nodeInfo.channels = json["channels"].stringValue
        nodeInfo.moniker = json["moniker"].stringValue
        if let other = json["other"].dictionaryObject as? [String:String] { nodeInfo.other = other }
        if let syncInfo = json["syncInfo"].dictionaryObject as? [String:String] { nodeInfo.syncInfo = syncInfo }
        nodeInfo.validatorInfo = self.parseValidator(json["validator_info"])
        return nodeInfo
    }

    func parseMarket(_ json: JSON) -> Market {
        let market = Market()
        market.baseAssetSymbol = json["base_asset_symbol"].stringValue
        market.quoteAssetSymbol = json["quote_asset_symbol"].stringValue
        market.price = json["list_price"].doubleValue
        market.tickSize = json["tick_size"].doubleValue
        market.lotSize = json["lot_size"].doubleValue
        return market
    }

    func parseAccount(_ json: JSON) -> Account {
        let account = Account()
        account.accountNumber = json["account_number"].intValue
        account.address = json["address"].stringValue
        account.balances = json["balances"].map({ self.parseBalance($0.1) })
        account.publicKey = self.parsePublicKey(json["public_key"])
        account.sequence = json["sequence"].intValue
        return account
    }
    
    func parseBalance(_ json: JSON) -> Balance {
        let balance = Balance()
        balance.symbol = json["symbol"].string ?? json["a"].stringValue
        balance.free = json["free"].doubleString ?? json["f"].doubleValue
        balance.locked = json["locked"].doubleString ?? json["l"].doubleValue
        balance.frozen = json["frozen"].doubleString ?? json["r"].doubleValue
        return balance
    }

    func parseCandlestick(_ json: JSON) throws -> Candlestick {
        let candlestick = Candlestick()
        candlestick.closeTime = Date(millisecondsSince1970: json["T"].doubleString ?? json.arrayValue[6].doubleValue)
        candlestick.close = json["c"].doubleString ?? json.arrayValue[4].doubleValue
        candlestick.high = json["h"].doubleString ?? json.arrayValue[2].doubleValue
        candlestick.low = json["l"].doubleString ?? json.arrayValue[3].doubleValue
        candlestick.numberOfTrades = json["n"].int ?? json.arrayValue[8].intValue
        candlestick.open = json["o"].doubleString ?? json.arrayValue[1].doubleValue
        candlestick.openTime = Date(millisecondsSince1970: json["t"].doubleString ?? json.arrayValue[0].doubleValue)
        candlestick.quoteAssetVolume = json["q"].doubleString ?? json.arrayValue[7].doubleValue
        candlestick.volume = json["q"].doubleString ?? json.arrayValue[5].doubleValue
        candlestick.closed = json["x"].boolValue
        return candlestick
    }

    func parseTickerStatistics(_ json: JSON) throws -> TickerStatistics {
        let ticker = TickerStatistics()
        ticker.askPrice = json["askPrice"].doubleString ?? json["a"].doubleValue
        ticker.askQuantity = json["askQuantity"].doubleString ?? json["A"].doubleValue
        ticker.bidPrice = json["bidPrice"].doubleString ?? json["b"].doubleValue
        ticker.bidQuantity = json["bidQuantity"].doubleString ?? json["B"].doubleValue
        ticker.count = json["count"].int ?? json["n"].intValue
        ticker.firstId = json["firstId"].string ?? json["F"].stringValue
        ticker.highPrice = json["high_price"].doubleString ?? json["h"].doubleValue
        ticker.lastId = json["lastId"].string ?? json["L"].stringValue
        ticker.lastPrice = json["lastPrice"].doubleString ?? json["o"].doubleValue
        ticker.lastQuantity = json["lastQuantity"].doubleValue
        ticker.lowPrice = json["lowPrice"].doubleString ?? json["l"].doubleValue
        ticker.openPrice = json["openPrice"].doubleString ?? json["o"].doubleValue
        ticker.prevClosePrice = json["prevClosePrice"].doubleString ?? json["x"].doubleValue
        ticker.priceChange = json["priceChange"].doubleString ?? json["p"].doubleValue
        ticker.priceChangePercent = json["priceChangePercent"].doubleString ?? json["P"].doubleValue
        ticker.quoteVolume = json["quoteVolume"].doubleString ?? json["q"].doubleValue
        ticker.symbol = json["symbol"].string ?? json["s"].stringValue
        ticker.volume = json["volume"].doubleString ?? json["v"].doubleValue
        ticker.weightedAvgPrice = json["weightedAvgPrice"].doubleValue
        ticker.openTime = Date(millisecondsSince1970: json["openTime"].doubleString ?? json["O"].doubleValue)
        ticker.closeTime = Date(millisecondsSince1970: json["closeTime"].doubleString ?? json["C"].doubleValue)
        return ticker
    }

    func parseOrder(_ json: JSON) throws -> Order {
        let order = Order()
        order.cumulateQuantity = json["cumulateQuantity"].string ?? json["z"].stringValue
        order.fee = json["fee"].string ?? json["n"].stringValue
        order.lastExecutedPrice = json["lastExecutedPrice"].string ?? json["L"].stringValue
        order.lastExecuteQuantity = json["lastExecutedQuantity"].string ?? json["l"].stringValue
        order.orderId = json["orderId"].string ?? json["i"].stringValue
        order.owner = json["owner"].stringValue
        order.price = json["price"].doubleString ?? json["p"].doubleValue
        order.symbol = json["symbol"].string ?? json["s"].stringValue
        order.tradeId = json["tradeId"].string ?? json["t"].stringValue
        order.transactionHash = json["transactionHash"].stringValue
        let orderCreateTimeValue = json["orderCreateTime"].string ?? json["O"].stringValue
        let transactionTimeValue = json["transactionTime"].string ?? json["T"].stringValue
        guard let orderCreateTime = orderCreateTimeValue.toDate()?.date else { throw ParseError(model: "Order", property: "orderCreateTime") }
        guard let transactionTime = transactionTimeValue.toDate()?.date else { throw ParseError(model: "Order", property: "transactionTime") }
        guard let side = Side(rawValue: json["side"].int ?? json["S"].intValue) else { throw ParseError(model: "Order", property: "side") }
        guard let status = Status(rawValue: json["status"].stringValue) else { throw ParseError(model: "Order", property: "status") }
        guard let timeInForce = TimeInForce(rawValue: json["timeInForce"].intValue) else { throw ParseError(model: "Order", property: "timeInForce") }
        guard let type = OrderType(rawValue: json["type"].intValue) else { throw ParseError(model: "Order", property: "type") }
        order.orderCreateTime = orderCreateTime
        order.transactionTime = transactionTime
        order.side = side
        order.status = status
        order.timeInForce = timeInForce
        order.type = type
        return order
    }

    func parseOrderList(_ json: JSON) throws -> OrderList {
        let orderList = OrderList()
        orderList.total = json["total"].intValue
        orderList.orders = try json["order"].map({ try self.parseOrder($0.1) })
        return orderList
    }
    
    func parseFee(_ json: JSON) throws -> Fee {
        let fee = Fee()
        fee.msgType = json["msg_type"].stringValue
        fee.fee = json["fee"].stringValue
        fee.multiTransferFee = json["multi_transfer_fee"].intValue
        fee.lowerLimitAsMulti = json["lower_limit_as_multi"].intValue
        if json["fixed_fee_params"].exists() { fee.fixedFeeParams = try self.parseFixedFeeParams(json["fixed_fee_params"]) }
        guard let feeFor = FeeFor(rawValue: json["fee_for"].intValue) else { throw ParseError(model: "Fee", property: "fee_for") }
        fee.feeFor = feeFor
        return fee
    }

    func parseFixedFeeParams(_ json: JSON) throws -> FixedFeeParams {
        let fixedFeeParams = FixedFeeParams()
        fixedFeeParams.msgType = json["msg_type"].stringValue
        fixedFeeParams.fee = json["fee"].stringValue
        guard let feeFor = FeeFor(rawValue: json["fee_for"].intValue) else { throw ParseError(model: "FixedFeeParams", property: "fee_for") }
        fixedFeeParams.feeFor = feeFor
        return fixedFeeParams
    }

    func parseTransfer(_ json: JSON) throws -> Transfer {
        let transfer = Transfer()
        transfer.fromAddr = json["f"].stringValue
        transfer.height = json["E"].intValue
        transfer.transactionHash = json["H"].stringValue
        transfer.transferred = try json["t"].map({ try self.parseTransferred($0.1) })
        return transfer
    }

    func parseTransferred(_ json: JSON) throws -> Transferred {
        let transferred = Transferred()
        transferred.toAddr = json["o"].stringValue
        transferred.amounts = try json["c"].map({ try self.parseAmount($0.1) })
        return transferred
    }
    
    func parseAmount(_ json: JSON) throws -> Amount {
        let amount = Amount()
        amount.asset = json["a"].stringValue
        amount.amount = json["A"].doubleValue
        return amount
    }
    
    func parseBlockHeight(_ json: JSON) -> Int {
        return json["h"].intValue
    }
    
}

class ErrorParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.error = self.parseError(json)
    }
}

class TokenParser: Parser {
//    override func parse(_ json: JSON, response: BinanceChain.Response) {
//        response.tokens = json.map({ self.parseToken($0.1) })
//    }
}

class PeerParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.peers = json.map({ self.parsePeer($0.1) })
    }
}

class TradeParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.trades = try json["trade"].map({ try self.parseTrade($0.1) })
    }
}

class MarketDepthParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.marketDepth = self.parseMarketDepth(json)
    }
}

class TimesParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.time = try self.parseTimes(json)
    }
}

class ValidatorsParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.validators = self.parseValidators(json)
    }
}

class BroadcastParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.broadcast = try json.map({ try self.parseTransaction($0.1) })
    }
}

class TransactionsParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.transactions = try self.parseTransactions(json)
    }
}

class NodeInfoParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.nodeInfo = try self.parseNodeInfo(json["node_info"])
    }
}

class MarketsParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.markets = json.map({ self.parseMarket($0.1) })
    }
}

class AccountParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.account = self.parseAccount(json)
    }
}

class SequenceParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) {
        response.sequence = json["sequence"].intValue
    }
}

class TxParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.tx = try self.parseTx(json)
    }
}

class CandlestickParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.candlesticks = [ try self.parseCandlestick(json) ]
    }
}

class CandlesticksParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.candlesticks = try json.map({ try self.parseCandlestick($0.1) })
    }
}

class TickerStatisticParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.ticker = [ try self.parseTickerStatistics(json) ]
    }
}

class TickerStatisticsParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.ticker = try json.map({ try self.parseTickerStatistics($0.1) })
    }
}

class OrderParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.order = try self.parseOrder(json)
    }
}

class OrdersParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.orders = try json.arrayValue.map({ try self.parseOrder($0) })
    }
}

class OrderListParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.orderList = try self.parseOrderList(json)
    }
}

class FeesParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.fees = try json.map({ try self.parseFee($0.1) })
    }
}

class MarketDepthUpdateParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.marketDepthUpdate = self.parseMarketDepthUpdate(json)
    }
}

class TransferParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.transfer = try self.parseTransfer(json)
    }
}

class BlockHeightParser: Parser {
    override func parse(_ json: JSON, response: BinanceChain.Response) throws {
        response.blockHeight = parseBlockHeight(json)
    }
}

class JSONRPCParser: Parser {

    override func parse(_ json: JSON, response: NodeRPC.Response) throws {
        response.id = json["id"].stringValue
        if let error = json["error"].string, !error.isEmpty {
            response.isError = true
            response.error = BinanceError(message: error)
        }
        if let result = json["result"].dictionaryObject {
            response.result = result
        }
    }

}
