//
//  StarnameAssets.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/12.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public func getStarnameAssets() -> Array<StarnameAsset> {
    var result = Array<StarnameAsset>()
    result.append(.ada)
    result.append(.akt)
    result.append(.algo)
    result.append(.atom)
    result.append(.avax)
    result.append(.band)
    result.append(.bat)
    result.append(.bch)
    result.append(.bnb)
    result.append(.btc)
    result.append(.comp)
    result.append(.cro)
    result.append(.ctk)
    result.append(.dai)
    result.append(.dash)
    result.append(.doge)
    result.append(.dot)
    result.append(.dsm)
    result.append(.dvpn)
    result.append(.eth)
    result.append(.ion)
    result.append(.iov)
    result.append(.iris)
    result.append(.kava)
    result.append(.ksm)
    result.append(.link)
    result.append(.ltc)
    result.append(.neo)
    result.append(.okb)
    result.append(.osmo)
    result.append(.regen)
    result.append(.ren)
    result.append(.req)
    result.append(.rowan)
    result.append(.scrt)
    result.append(.shib)
    result.append(.svt)
    result.append(.trx)
    result.append(.usdc)
    result.append(.usdt)
    result.append(.xlm)
    result.append(.xmr)
    result.append(.xprt)
    result.append(.xtz)
    result.append(.zec)
    return result
}

public struct StarnameAsset {
    public let name: String
    public let url: String
    public let symbol: String
    public let chainImg: String?
    public let chainType: ChainType?
    
    public init(_ name: String, _ url: String, _ symbol: String, _ chainImg: String?, _ chainType: ChainType?) {
        self.name = name
        self.url = url
        self.symbol = symbol
        self.chainType = chainType
        
        if (chainImg == nil) {
            self.chainImg = CHAIN_IMG_URL + "unknown.png"
        } else {
            self.chainImg = CHAIN_IMG_URL + chainImg!
        }
    }
}


public extension StarnameAsset {
    static let ada = StarnameAsset("Cardano", "asset:ada", "ADA", nil, nil)
    static let akt = StarnameAsset("Akash", "asset:akt", "AKT", "akash.png", ChainType.AKASH_MAIN)
    static let algo = StarnameAsset("Algorand", "asset:algo", "ALGO", nil, nil)
    static let atom = StarnameAsset("Cosmos", "asset:atom", "ATOM", "cosmos.png", ChainType.COSMOS_MAIN)
    static let avax = StarnameAsset("Avalanche", "asset:atom", "AVAX", nil, nil)
    static let band = StarnameAsset("Band Protocol", "asset:band", "BAND", "band.png", ChainType.BAND_MAIN)
    static let bat = StarnameAsset("Basic Attention Token", "asset:bat", "BAT", nil, nil)
    static let bch = StarnameAsset("Bitcoin Cash", "asset:bch", "BCH", nil, nil)
    static let bnb = StarnameAsset("BNB coin", "asset:bnb", "BNB", nil, ChainType.BINANCE_MAIN)
    static let btc = StarnameAsset("Bitcoin", "asset:btc", "BTC", nil, nil)
    static let comp = StarnameAsset("Compound", "asset:comp", "COMP", nil, nil)
    static let cro = StarnameAsset("Crypto.org", "asset:cro", "CRO", "crypto-org.png", ChainType.CRYPTO_MAIN)
    static let ctk = StarnameAsset("CertiK", "asset:ctk", "CTK", "certik.png", ChainType.CERTIK_MAIN)
    static let dai = StarnameAsset("Dai Stablecoin", "asset:dai", "DAI", nil, nil)
    static let dash = StarnameAsset("Dash", "asset:dash", "DASH", nil, nil)
    static let doge = StarnameAsset("Dogecoin", "asset:doge", "DOGE", nil, nil)
    static let dot = StarnameAsset("Polkadot", "asset:dot", "DOT", nil, nil)
    static let dsm = StarnameAsset("Desmos", "asset:dsm", "DSM", nil, nil)
    static let dvpn = StarnameAsset("Sentinel", "asset:dvpn", "DVPN", "sentinel.png", ChainType.SENTINEL_MAIN)
    static let eth = StarnameAsset("Ethereum", "asset:eth", "ETH", nil, nil)
    static let ion = StarnameAsset("ION", "asset:ion", "ION", "osmosis.png", ChainType.OSMOSIS_MAIN)
    static let iov = StarnameAsset("Starname (IOV)", "asset:iov", "IOV", "starname.png", ChainType.IOV_MAIN)
    static let iris = StarnameAsset("IRISnet", "asset:iris", "IRIS", "iris.png", ChainType.IRIS_MAIN)
    static let kava = StarnameAsset("Kava", "asset:kava", "KAVA", "kava.png", ChainType.KAVA_MAIN)
    static let ksm = StarnameAsset("Kusama", "asset:ksm", "KSM", nil, nil)
    static let link = StarnameAsset("Chainlink", "asset:link", "LINK", nil, nil)
    static let ltc = StarnameAsset("Litecoin", "asset:ltc", "LTC", nil, nil)
    static let neo = StarnameAsset("Neo", "asset:neo", "NEO", nil, nil)
    static let okb = StarnameAsset("OKB", "asset:okb", "OKB", nil, ChainType.OKEX_MAIN)
    static let osmo = StarnameAsset("Osmosis", "asset:osmo", "OSMO", "osmosis.png", ChainType.OSMOSIS_MAIN)
    static let regen = StarnameAsset("Regen Network", "asset:regen", "REGEN", "regen.png", ChainType.REGEN_MAIN)
    static let ren = StarnameAsset("Ren", "asset:ren", "REN", nil, nil)
    static let req = StarnameAsset("Request", "asset:req", "REQ", nil, nil)
    static let rowan = StarnameAsset("Sifchain", "asset:rowan", "ROWAN", "sifchain.png", ChainType.SIF_MAIN)
    static let scrt = StarnameAsset("Secret", "asset:scrt", "SCRT", nil, ChainType.SECRET_MAIN)
    static let shib = StarnameAsset("Shiba Inu", "asset:shib", "SHIB", nil, nil)
    static let svt = StarnameAsset("Savitar Token", "asset:svt", "SVT", nil, nil)
    static let trx = StarnameAsset("TRON", "asset:trx", "TRX", nil, nil)
    static let usdc = StarnameAsset("USD Coin", "asset:usdc", "USDC", nil, nil)
    static let usdt = StarnameAsset("Tether", "asset:usdt", "USDT", nil, nil)
    static let xlm = StarnameAsset("Stellar", "asset:xlm", "XLM", nil, nil)
    static let xmr = StarnameAsset("Monero", "asset:xmr", "XMR", nil, nil)
    static let xprt = StarnameAsset("Persistence", "asset:xprt", "XPRT", "persistence.png", ChainType.PERSIS_MAIN)
    static let xtz = StarnameAsset("Tezos", "asset:xtz", "XTZ", nil, nil)
    static let zec = StarnameAsset("Zcash", "asset:zec", "ZEC", nil, nil)
    
}
