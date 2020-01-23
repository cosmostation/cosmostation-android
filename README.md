<p align="center">
  <a href="https://www.cosmostation.io" target="_blank" rel="noopener noreferrer"><img width="100" src="https://user-images.githubusercontent.com/20435620/55696624-d7df2e00-59f8-11e9-9126-edf9a40b11a8.png" alt="Cosmostation logo"></a>
</p>
<h1 align="center">Cosmostation Mobile Wallet</h1>
<h3 align="center">Welcome to Cosmostation's Open Source Mobile Apps!</h3>

[![MIT](https://img.shields.io/apm/l/vim-mode.svg)](https://github.com/cosmostation/cosmosjs/blob/master/LICENSE)
[![Twitter Follow](https://img.shields.io/twitter/follow/CosmostationVD.svg?label=Follow&style=social)](https://twitter.com/CosmostationVD)

*:rocket: Developed / Developing by [Cosmostation](https://www.cosmostation.io/)*

## About

Cosmostation wallet apps are non-custodial tendermint-based wallet that supports [Cosmos Network](https://https://cosmos.network/). We will add more tendermint-based blockchain projects in the coming updates, such as [IRISnet](https://www.irisnet.org/), [IOV](https://iov.one/), [Kava](https://kava.io/).


## Notice1

for generate signature with swift you have to modify BicoinKey library after pod intalled.
please check below code for get correct signature in tendermint
/Pods/BitcoinKit/Source/Crypro.swift (line ~59)

```swift
  public static func sign(_ data: Data, privateKey: PrivateKey) throws -> Data {
      let ctx = secp256k1_context_create(UInt32(SECP256K1_CONTEXT_SIGN))!
      defer { secp256k1_context_destroy(ctx) }

      let signature = UnsafeMutablePointer<secp256k1_ecdsa_signature>.allocate(capacity: 1)
      defer { signature.deallocate() }

      var paddingKey = Data()
      let value: UInt8 = 0
      for i in privateKey.raw.count..<32 {
          paddingKey.append(value)
      }
      paddingKey.append(privateKey.raw)


      let status = data.withUnsafeBytes { (ptr: UnsafePointer<UInt8>) in
          paddingKey.withUnsafeBytes { secp256k1_ecdsa_sign(ctx, signature, ptr, $0, nil, nil) }
      }
      guard status == 1 else { throw CryptoError.signFailed }

      let normalizedsig = UnsafeMutablePointer<secp256k1_ecdsa_signature>.allocate(capacity: 1)
      defer { normalizedsig.deallocate() }
      secp256k1_ecdsa_signature_normalize(ctx, normalizedsig, signature)

      var length: size_t = 128
      var der = Data(count: length)
      guard der.withUnsafeMutableBytes({ return secp256k1_ecdsa_signature_serialize_der(ctx, $0, &length, normalizedsig) }) == 1 else { throw CryptoError.noEnoughSpace }
      der.count = length
      return der
  }
```


## Notice2

IOS to prevent Binance-libarary and Bitcoinkit from conflict with secp256k1, we have to modifiy dependancy or else.
instead modify dependancy, just change file name for simple using.

If you want local build, please check below steps.

1. becuase we uploaded full pod file, you have to re install pod files.
2. pod deintegrate(https://github.com/CocoaPods/cocoapods-deintegrate)
3. pod install
4. change your BitcoinKit's modle.modulemap (any name is ok instead "secp256k12")
```swift
module secp256k12 [system] {
    private header "include/secp256k1.h"
    link "secp256k1"
}
```
5. fix bitcoinkit's import name to new one.(Bitcoinkit/Cryto.swift line 32, Bitcoinkit/ScriptMachine.swift line 26)


## Notice3

For Xcode upper 11 and new swift version. we have local fixed with HDwalletKit and BitcoinKit 1.0.2
DHWalletKit/Script.swift line 454
```swift
    public static func buildPublicKeyHashOut(pubKeyHash: Data) -> Data {
//        let tmp: Data = Data() + OpCode.OP_DUP + OpCode.OP_HASH160 + UInt8(pubKeyHash.count) + pubKeyHash + OpCode.OP_EQUALVERIFY
        let tmp: Data = Data() + OpCode.OP_DUP + OpCode.OP_HASH160 + UInt8(pubKeyHash.count)
        let secondTmp = pubKeyHash + OpCode.OP_EQUALVERIFY
        return tmp + secondTmp + OpCode.OP_CHECKSIG
    }
```
BitCoinkit/Script.swift line 461
```swift
    public static func buildPublicKeyHashOut(pubKeyHash: Data) -> Data {
//        let tmp: Data = Data() + OpCode.OP_DUP + OpCode.OP_HASH160 + UInt8(pubKeyHash.count) + pubKeyHash + OpCode.OP_EQUALVERIFY
//        return tmp + OpCode.OP_CHECKSIG
        let tmp: Data = Data() + OpCode.OP_DUP + OpCode.OP_HASH160 + UInt8(pubKeyHash.count)
        let secondTmp = pubKeyHash + OpCode.OP_EQUALVERIFY
        return tmp + secondTmp + OpCode.OP_CHECKSIG
    }
```

## Notice4
for local build upper xcode 11.3 you have to change command line tools path update
```
sudo xcode-select --switch /Applications/Xcode.app/Contents/Developer/
```



## Downloads

* [Android](https://play.google.com/store/apps/details?id=wannabit.io.cosmostaion)

* [iOS](https://apps.apple.com/us/app/cosmostation/id1459830339)


## Cosmostation's Services and Community

- [Official Website](https://www.cosmostation.io)
- [Mintscan Explorer](https://www.mintscan.io)
- [Web Wallet](https://wallet.cosmostation.io)
- [Telegram - International](https://t.me/cosmostation)
- [Kakao - Korean](https://open.kakao.com/o/g6KKSe5)


## License

Copyright © Cosmostation, Inc. All rights reserved.

Licensed under the [MIT](LICENSE).
