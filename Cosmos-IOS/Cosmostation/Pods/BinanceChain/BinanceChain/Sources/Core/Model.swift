import Foundation

public class Times: CustomStringConvertible {
    public var apTime: Date = Date()
    public var blockTime: Date = Date()
}

public class Validators: CustomStringConvertible {
    public var blockHeight: Int = 0
    public var validators: [Validator] = []
}

public class Validator: CustomStringConvertible {
    public var address: String = ""
    public var publicKey: Data = Data()
    public var votingPower: Int = 0
}

public class Peer: CustomStringConvertible {
    public var id: String = ""
    public var originalListenAddr: String = ""
    public var listenAddr: String = ""
    public var accessAddr: String = ""
    public var streamAddr: String = ""
    public var network: String = ""
    public var version: String = ""
    public var moniker: String = ""
    public var capabilities: [String] = []
    public var accelerated: Bool = false
}

public class NodeInfo: CustomStringConvertible {
    public var id: String = ""
    public var listenAddr: String = ""
    public var network: String = ""
    public var version: String = ""
    public var moniker: String = ""
    public var address: String = ""
    public var channels: String = ""
    public var other: [String:String] = [:]
    public var syncInfo: [String:String] = [:]
    public var validatorInfo: Validator = Validator()
}

public class Transactions: CustomStringConvertible {
    public var total: Int = 0
    public var tx: [Tx] = []
}

public class Transaction: CustomStringConvertible {
    public var hash: String = ""
    public var log: String = ""
    public var data: String = ""
    public var ok: Bool = false
    public var tx: Tx = Tx()
}

public class Account: CustomStringConvertible {
    public var accountNumber: Int = 0
    public var address: String = ""
    public var balances: [Balance] = []
    public var publicKey: Data = Data()
    public var sequence: Int = 0
}

public class AccountSequence: CustomStringConvertible {
    public var sequence: Int = 0
}

public class Balance: CustomStringConvertible {
    public var symbol: String = ""
    public var free: Double = 0
    public var locked: Double = 0
    public var frozen: Double = 0
}

public class Token: CustomStringConvertible {
    public var name: String = ""
    public var symbol: String = ""
    public var originalSymbol: String = ""
    public var totalSupply: Double = 0
    public var owner: String = ""
}

public class Market: CustomStringConvertible {
    public var baseAssetSymbol: String = ""
    public var quoteAssetSymbol: String = ""
    public var price: Double = 0
    public var tickSize: Double = 0
    public var lotSize: Double = 0
}

public class Fee: CustomStringConvertible {
    public var msgType: String = ""
    public var fee: String = ""
    public var feeFor: FeeFor = .all
    public var multiTransferFee: Int = 0
    public var lowerLimitAsMulti: Int = 0
    public var fixedFeeParams: FixedFeeParams?
}

public class FixedFeeParams: CustomStringConvertible {
    public var msgType: String = ""
    public var fee: String = ""
    public var feeFor: FeeFor = .all
}

public class PriceQuantity: CustomStringConvertible {
    public var price: Double = 0
    public var quantity: Double = 0
}

public class MarketDepth: CustomStringConvertible {
    public var asks: [PriceQuantity] = []
    public var bids: [PriceQuantity] = []
}

public class MarketDepthUpdate: CustomStringConvertible {
    public var symbol: String = ""
    public var depth: MarketDepth = MarketDepth()
}

public class BlockTradePage: CustomStringConvertible {
    public var total: Int = 0
    public var blockTrade: [BlockTrade] = []
}

public class BlockTrade: CustomStringConvertible {
    public var blockTime: TimeInterval = 0
    public var fee: Int = 0
    public var height: Int = 0
    public var trade: [Trade] = []
}

public class Candlestick: CustomStringConvertible {
    public var close: Double = 0
    public var closeTime: Date = Date()
    public var high: Double = 0
    public var low: Double = 0
    public var numberOfTrades: Int = 0
    public var open: Double = 0
    public var openTime: Date = Date()
    public var quoteAssetVolume: Double = 0
    public var volume: Double = 0
    public var closed: Bool = false
}

public class OrderList: CustomStringConvertible {
    public var total: Int = 0
    public var orders: [Order] = []
}

public class Order: CustomStringConvertible {
    public var cumulateQuantity: String = ""
    public var fee: String = ""
    public var lastExecutedPrice: String = ""
    public var lastExecuteQuantity: String = ""
    public var orderCreateTime: Date = Date()
    public var orderId: String = ""
    public var owner: String = ""
    public var price: Double = 0
    public var side: Side = .buy
    public var status: Status = .acknowledge
    public var symbol: String = ""
    public var timeInForce: TimeInForce = .immediateOrCancel
    public var tradeId: String = ""
    public var transactionHash: String = ""
    public var transactionTime: Date = Date()
    public var type: OrderType = .limit
}

public class TickerStatistics: CustomStringConvertible {
    public var askPrice: Double = 0
    public var askQuantity: Double = 0
    public var bidPrice: Double = 0
    public var bidQuantity: Double = 0
    public var closeTime: Date = Date()
    public var count: Int = 0
    public var firstId: String = ""
    public var highPrice: Double = 0
    public var lastId: String = ""
    public var lastPrice: Double = 0
    public var lastQuantity: Double = 0
    public var lowPrice: Double = 0
    public var openPrice: Double = 0
    public var openTime: Date = Date()
    public var prevClosePrice: Double = 0
    public var priceChange: Double = 0
    public var priceChangePercent: Double = 0
    public var quoteVolume: Double = 0
    public var symbol: String = ""
    public var volume: Double = 0
    public var weightedAvgPrice: Double = 0
}

public class TradePage: CustomStringConvertible {
    public var total: Int = 0
    public var trade: [Trade] = []
}

public class Trade: CustomStringConvertible {
    public var baseAsset: String = ""
    public var blockHeight: Int = 0
    public var buyFee: String = ""
    public var buyerId: String = ""
    public var buyerOrderId: String = ""
    public var price: String = ""
    public var quantity: String = ""
    public var quoteAsset: String = ""
    public var sellFee: String = ""
    public var sellerId: String = ""
    public var symbol: String = ""
    public var time: Date = Date()
    public var tradeId: String = ""
}

public class TxPage: CustomStringConvertible {
    public var total: Int = 0
    public var tx: [Tx] = []
}

public class Tx: CustomStringConvertible {
    public var blockHeight: Double = 0
    public var code: Int = 0
    public var confirmBlocks: Double = 0
    public var data: String = ""
    public var fromAddr: String = ""
    public var orderId: String = ""
    public var timestamp: Date = Date()
    public var toAddr: String = ""
    public var txAge: Double = 0
    public var txAsset: String = ""
    public var txFee: String = ""
    public var txHash: String = ""
    public var txType: TxType = .newOrder
    public var value: String = ""
}

public class Transfer: CustomStringConvertible {
    public var height: Int = 0
    public var transactionHash: String = ""
    public var fromAddr: String = ""
    public var transferred: [Transferred] = []
}

public class Transferred: CustomStringConvertible {
    public var toAddr: String = ""
    public var amounts: [Amount] = []
}

public class Amount: CustomStringConvertible {
    public var asset: String = ""
    public var amount: Double = 0
}
