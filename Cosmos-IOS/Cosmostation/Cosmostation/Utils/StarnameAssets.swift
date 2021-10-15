//
//  StarnameAssets.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/10/12.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public func getStarNameChainImgUrl(_ uri: String?) -> URL {
    if let asset = getStarnameAssets().filter { $0.uri == uri }.first {
        return URL(string: asset.chainImg ?? "")!
    }
    return URL(string: CHAIN_IMG_URL + "unknown.png")!
}

public func getStarNameChainName(_ uri: String?) -> String {
    if let asset = getStarnameAssets().filter { $0.uri == uri }.first {
        return asset.name
    }
    return "Unknown"
}
    

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
    result.append(.emy)
    result.append(.eos)
    result.append(.eth)
    result.append(.ion)
    result.append(.iov)
    result.append(.iris)
    result.append(.kava)
    result.append(.ksm)
    result.append(.link)
    result.append(.lsk)
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
    result.append(.sol)
    result.append(.svt)
    result.append(.ter)
    result.append(.trx)
    result.append(.usdc)
    result.append(.usdt)
    result.append(.xlm)
    result.append(.xmr)
    result.append(.xprt)
    result.append(.xtz)
    result.append(.zec)
    result.append(.wbtc)
    return result
}

public struct StarnameAsset {
    public let name: String
    public let uri: String
    public let symbol: String
    public let chainImg: String?
    public let chainType: ChainType?
    
    public init(_ name: String, _ uri: String, _ symbol: String, _ chainImg: String?, _ chainType: ChainType?) {
        self.name = name
        self.uri = uri
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
    static let ada = StarnameAsset("Cardano", "asset:ada", "ADA", "ada.png", nil)
    static let akt = StarnameAsset("Akash", "asset:akt", "AKT", "akash.png", ChainType.AKASH_MAIN)
    static let algo = StarnameAsset("Algorand", "asset:algo", "ALGO", "algo.png", nil)
    static let atom = StarnameAsset("Cosmos", "asset:atom", "ATOM", "cosmos.png", ChainType.COSMOS_MAIN)
    static let avax = StarnameAsset("Avalanche", "asset:avax", "AVAX", "avax.png", nil)
    static let band = StarnameAsset("Band Protocol", "asset:band", "BAND", "band.png", ChainType.BAND_MAIN)
    static let bat = StarnameAsset("Basic Attention Token", "asset:bat", "BAT", "bat.png", nil)
    static let bch = StarnameAsset("Bitcoin Cash", "asset:bch", "BCH", "bitcoincash.png", nil)
    static let bnb = StarnameAsset("BNB coin", "asset:bnb", "BNB", "binance.png", ChainType.BINANCE_MAIN)
    static let btc = StarnameAsset("Bitcoin", "asset:btc", "BTC", "bitcoin.png", nil)
    static let comp = StarnameAsset("Compound", "asset:comp", "COMP", "comp.png", nil)
    static let cro = StarnameAsset("Crypto.org", "asset:cro", "CRO", "crypto-org.png", ChainType.CRYPTO_MAIN)
    static let ctk = StarnameAsset("CertiK", "asset:ctk", "CTK", "certik.png", ChainType.CERTIK_MAIN)
    static let dai = StarnameAsset("Dai Stablecoin", "asset:dai", "DAI", "dai.png", nil)
    static let dash = StarnameAsset("Dash", "asset:dash", "DASH", "dash.png", nil)
    static let doge = StarnameAsset("Dogecoin", "asset:doge", "DOGE", "doge.png", nil)
    static let dot = StarnameAsset("Polkadot", "asset:dot", "DOT", "dot.png", nil)
    static let dsm = StarnameAsset("Desmos", "asset:dsm", "DSM", "dsm.png", nil)
    static let dvpn = StarnameAsset("Sentinel", "asset:dvpn", "DVPN", "sentinel.png", ChainType.SENTINEL_MAIN)
    static let eos = StarnameAsset("EOS", "asset:eos", "eos", "eos.png", nil)
    static let eth = StarnameAsset("Ethereum", "asset:eth", "ETH", "ethereum.png", nil)
    static let ion = StarnameAsset("ION", "asset:ion", "ION", "osmosis.png", ChainType.OSMOSIS_MAIN)
    static let iov = StarnameAsset("Starname (IOV)", "asset:iov", "IOV", "starname.png", ChainType.IOV_MAIN)
    static let iris = StarnameAsset("IRISnet", "asset:iris", "IRIS", "iris.png", ChainType.IRIS_MAIN)
    static let kava = StarnameAsset("Kava", "asset:kava", "KAVA", "kava.png", ChainType.KAVA_MAIN)
    static let ksm = StarnameAsset("Kusama", "asset:ksm", "KSM", "ksm.png", nil)
    static let link = StarnameAsset("Chainlink", "asset:link", "LINK", "link.png", nil)
    static let ltc = StarnameAsset("Litecoin", "asset:ltc", "LTC", "litecoin.png", nil)
    static let neo = StarnameAsset("Neo", "asset:neo", "NEO", "neo.png", nil)
    static let okb = StarnameAsset("OKB", "asset:okb", "OKB", "okex.png", ChainType.OKEX_MAIN)
    static let osmo = StarnameAsset("Osmosis", "asset:osmo", "OSMO", "osmosis.png", ChainType.OSMOSIS_MAIN)
    static let regen = StarnameAsset("Regen Network", "asset:regen", "REGEN", "regen.png", ChainType.REGEN_MAIN)
    static let ren = StarnameAsset("Ren", "asset:ren", "REN", "ren.png", nil)
    static let req = StarnameAsset("Request", "asset:req", "REQ", "req.png", nil)
    static let rowan = StarnameAsset("Sifchain", "asset:rowan", "ROWAN", "sifchain.png", ChainType.SIF_MAIN)
    static let scrt = StarnameAsset("Secret", "asset:scrt", "SCRT", "secret.png", ChainType.SECRET_MAIN)
    static let shib = StarnameAsset("Shiba Inu", "asset:shib", "SHIB", "shib.png", nil)
    static let svt = StarnameAsset("Savitar Token", "asset:svt", "SVT", "svt.png", nil)
    static let sol = StarnameAsset("Solana", "asset:sol", "SOL", "sol.png", nil)
    static let trx = StarnameAsset("TRON", "asset:trx", "TRX", "trx.png", nil)
    static let usdc = StarnameAsset("USD Coin", "asset:usdc", "USDC", "usdc.png", nil)
    static let usdt = StarnameAsset("Tether", "asset:usdt", "USDT", "usdt.png", nil)
    static let xlm = StarnameAsset("Stellar", "asset:xlm", "XLM", "xlm.png", nil)
    static let xmr = StarnameAsset("Monero", "asset:xmr", "XMR", "xmr.png", nil)
    static let xprt = StarnameAsset("Persistence", "asset:xprt", "XPRT", "persistence.png", ChainType.PERSIS_MAIN)
    static let xtz = StarnameAsset("Tezos", "asset:xtz", "XTZ", "tezos.png", nil)
    static let zec = StarnameAsset("Zcash", "asset:zec", "ZEC", "zec.png", nil)
    static let wbtc = StarnameAsset("Wrapped Bitcoin", "asset:wbtc", "WBTC", "wbtc.png", nil)
    
    static let ter = StarnameAsset("Terra", "asset:luna", "LUNA", "terra.png", nil)
    static let emy = StarnameAsset("E-Money", "asset:ngm", "NGM", "emoney.png", nil)
    static let lsk = StarnameAsset("LISK", "asset:lsk", "LSK", "lsk.png", nil)
}
