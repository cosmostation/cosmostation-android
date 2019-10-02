import Foundation
import HDWalletKit
import CryptoSwift

public class Wallet: CustomStringConvertible {

    public var endpoint: String = BinanceChain.Endpoint.testnet.rawValue
    public var privateKey: Data { return self.key.raw }
    public var publicKey: Data { return self.key.publicKey.data }
    public var mnemonic: String = ""
    public var sequence: Int = 0
    public var accountNumber: Int = 0
    public var chainId: String = ""

    private var key: PrivateKey!

    // MARK: - Constructors
    
    public required init() {
        self.initialise(mnemonic: Mnemonic.create())
    }

    public convenience init(endpoint: BinanceChain.Endpoint = .testnet) {
        self.init(mnemonic: Mnemonic.create(), endpoint: endpoint.rawValue)
    }

    public convenience init(endpoint: String? = nil) {
        self.init(mnemonic: Mnemonic.create(), endpoint: endpoint)
    }

    public convenience init(mnemonic: String, endpoint: BinanceChain.Endpoint) {
        self.init(mnemonic: mnemonic, endpoint: endpoint.rawValue)
    }

    public convenience init(mnemonic: String, endpoint: String? = nil) {
        self.init()
        if let endpoint = endpoint { self.endpoint = endpoint }
        self.initialise(mnemonic: mnemonic)
    }

    public convenience init(privateKey: String, endpoint: BinanceChain.Endpoint) {
        self.init(privateKey: privateKey, endpoint: endpoint.rawValue)
    }

    public convenience init(privateKey: String, endpoint: String? = nil) {
        self.init()
        if let endpoint = endpoint { self.endpoint = endpoint }
        self.key = PrivateKey(pk: privateKey, coin: .bitcoin)
    }

    private func initialise(mnemonic: String, completion: Completion? = nil) {
        self.mnemonic = mnemonic
        let seed = Mnemonic.createSeed(mnemonic: mnemonic)
        let key = PrivateKey(seed: seed, coin: .bitcoin)
        self.key = key.bip44PrivateKey
    }

    // MARK: - Wallet

    public typealias Completion = (_ error: Error?)->()

    public func synchronise(completion: Completion? = nil) {

        guard let url = URL(string: self.endpoint) else {
            if let completion = completion {
                completion(BinanceError(message: "Invalid endpoint URL"))
            }
            return
        }

        let binance = BinanceChain(endpoint: url)
        let group = DispatchGroup()
        var error: Error?

        // Update node info
        group.enter()
        binance.nodeInfo() { (response) in
            if let value = response.error {
                error = value
            } else {
                self.chainId = response.nodeInfo.network
            }
            group.leave()
        }

        // Update account sequence
        group.enter()
        binance.account(address: self.account) { (response) in
            if let value = response.error {
                error = value
            } else {
                self.accountNumber = response.account.accountNumber
                self.sequence = response.account.sequence
            }
            group.leave()
        }

        // Synchronise complete
        group.notify(queue: .main) {
            guard let completion = completion else { return }
            completion(error)
        }

    }

    public func incrementSequence() {
        self.sequence += 1
    }

    public func nextAvailableOrderId() -> String {
        return String(format: "%@-%d", self.address.uppercased(), self.sequence+1)
    }

    public var account: String {
        return self.account()
    }
    
    public func account(hrp: String? = nil) -> String {
        do {
            let hrp = hrp ?? ((self.endpoint == BinanceChain.Endpoint.testnet.rawValue) ? "tbnb" : "bnb")
            let sha = self.publicKey.sha256()
            let ripemd = RIPEMD160.hash(sha)
            let convertbits = try SegwitAddrCoder().convertBits(from: 8, to: 5, pad: false, idata: ripemd)
            let address = Bech32().encode(hrp, values: convertbits)
            return address
        } catch {
            return "Invalid Key"
        }
    }

    public var address: String {
        let sha = self.publicKey.sha256()
        let ripemd = RIPEMD160.hash(sha)
        return ripemd.hexlify
    }

    public func address(from account: String) -> String {
        do {
            let (_, data) = try Bech32().decode(account)
            let bits = try SegwitAddrCoder().convertBits(from: 5, to: 8, pad: false, idata: data)
            return bits.hexlify
        } catch {
            return "Invalid Key"
        }
    }
    
    public func sign(message: Data) -> Data {
        do {
            return try ECDSA.compactsign(message.sha256(), privateKey: self.privateKey)
        } catch let error {
            print(error)
        }
        return message
    }

    // MARK: - CustomStringConvertible

    public var description: String {
        return String(format: "Wallet [address=%@ accountNumber=%d, sequence=%d, chain_id=%@, mnemonic=%@, account=%@, publicKey=%@, privateKey=%@, endpoint=%@]",
                      address, accountNumber, sequence, chainId, mnemonic, account, publicKey.hexlify, privateKey.hexlify, endpoint)
    }

}


// MARK: - HDWalletKit

fileprivate extension HDWalletKit.PrivateKey {

    var bip44PrivateKey: PrivateKey {

        // BIP44 key derivation explained:
        // https://github.com/bitcoin/bips/blob/master/bip-0044.mediawiki

        // m/44'/714'/0'/0/0
        let bip44Purpose: UInt32 = 44
        let binanceChainCoin: UInt32 = 714
        let purpose = self.derived(at: .hardened(bip44Purpose))
        let coinType = purpose.derived(at: .hardened(binanceChainCoin))
        let account = coinType.derived(at: .hardened(0))
        let change = account.derived(at: .notHardened(0))
        let recieve = change.derived(at: .notHardened(0))
        return recieve

    }
    
}

