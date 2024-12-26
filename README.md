<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-7-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
<p align="center">
  <a href="https://www.cosmostation.io" target="_blank" rel="noopener noreferrer"><img width="100" src="https://user-images.githubusercontent.com/20435620/55696624-d7df2e00-59f8-11e9-9126-edf9a40b11a8.png" alt="Cosmostation logo"></a>
</p>
<h1 align="center">Cosmostation Android Wallet</h1>
<h3 align="center">Welcome to Cosmostation's Open Source For Android</h3>

[![MIT](https://img.shields.io/badge/License-MIT-red.svg)](https://github.com/cosmostation/cosmostation-android/blob/master/LICENSE.md)
[![Twitter Follow](https://img.shields.io/twitter/follow/CosmostationVD.svg?label=Follow&style=social)](https://twitter.com/CosmostationVD)

*:rocket: Developed / Developing by [Cosmostation](https://www.cosmostation.io/)*



<details open>
<summary><h2 style='display: inline; font-size: 24px'>Key Features</h2></summary>
<br>
Non-custodial tendermint-based mobile wallet application designed for managing and staking Cosmos-based assets. It supports various Cosmos SDK-based blockchains, enabling users to store, send, receive, and stake their tokens directly from their mobile devices. The wallet features a user-friendly interface, real-time transaction history, and secure private key management. It also provides integration with decentralized applications

1. Fully open-source
2. We respect your privacy and ensure that no user data is collected, except for push notifications to keep you informed.
3. Supports all staking-related features (Delegate, Undelegate, Redelegate, CanceUnbonding)
4. Supports not only standard transactions but also IBC (Inter-Blockchain Communication) transfers through secure channels.
5. Supports realtime [governance data](https://www.mintscan.io/cosmos/proposals/) and provides the ability to vote on proposals.
6. By supporting the injection method(like [EIP-6963](https://eips.ethereum.org/EIPS/eip-6963)), it allows for faster and more convenient connections with DApps.
7. By utilizing gRPC, which boasts advantages such as performance, security, and scalability, we align with the Cosmos.

<br>

You can download each store.
* [Android](https://play.google.com/store/apps/details?id=wannabit.io.cosmostaion)

* [iOS](https://apps.apple.com/us/app/cosmostation/id1459830339)

</details>
<br>

<details >
<summary><h2 style='display: inline; font-size: 24px'>Supporting Chains (Cosmos)</h2></summary>
<br>
<table border="1">
  <tr>
    <th style="text-align:center">Image</th>
    <th style="text-align:center">Name</th>
    <th style="text-align:center" >HD Path</th>
    <th style="text-align:center">Public-Key Type</th>
    <th style="text-align:center">Call Method</th>
    <th style="text-align:center">Support</th>
  </tr>
  
  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/cosmos/resource/chain_cosmos.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">COSMOS</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/aaron/resource/chain_aaron.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">AARON</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/agoric/resource/chain_agoric.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">AGORIC</span></td>
    <td><span>m/44'/564'/0'/0/X</span><br/>
        <span>m/44'/118'/0'/0/X</span></td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/aioz/resource/chain_aioz.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">AIOZ</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 	
  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/akash/resource/chain_akash.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">AKASH</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/althea/resource/chain_althea.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ALTHEA</span></td>
    <td><span>m/44'/60'/0'/0/X</span><br/>
        <span>m/44'/118'/0'/0/X</span></td>
    <td><span>keccak256</span><br/>
        <span>secp256k1</span></td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/archway/resource/chain_archway.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ARCHYWAY</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_nft.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/asset-mantle/resource/chain_asset-mantle.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ASSETMANTLE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/axelar/resource/chain_axelar.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">AXELAR</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/band/resource/chain_band.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">BAND</span></td>
    <td>m/44'/494'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/bitcanna/resource/chain_bitcanna.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">BITCANNA</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/bitsong/resource/chain_bitsong.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">BITSONG</span></td>
    <td>m/44'/639'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/canto/resource/chain_canto.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CANTO</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/carbon/resource/chain_carbon.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CARBON</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/celestia/resource/chain_celestia.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CELESTIA</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/chain4energy/resource/chain_chain4energy.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CHAIN4ENERGY</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/cheqd/resource/chain_cheqd.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CHEQD</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/chihuahua/resource/chain_chihuahua.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CHIHUAHUA</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/comdex/resource/chain_comdex.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">COMDEX</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_ics.png?raw=true" width="42" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/coreum/resource/chain_coreum.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">COREUM</span></td>
    <td>m/44'/990'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/crypto-org/resource/chain_crypto-org.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CRONOS POS</span></td>
    <td>m/44'/394'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/desmos/resource/chain_desmos.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">DESMOS</span></td>
    <td>m/44'/852'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/doravota/resource/chain_doravota.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">DORAVOTA</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
  
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/dungeon/resource/chain_dungeon.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">DUNGEON</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/dydx/resource/chain_dydx.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">DYDX</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/dymension/resource/chain_dymension.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">DYMENSION</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 	
  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/elys/resource/chain_elys.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ELYS</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_ics.png?raw=true" width="42" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/evmos/resource/chain_evmos.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">EVMOS</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/fetchai/resource/chain_fetchai.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ASI Alliance (FETCH.Ai)</span></td>
    <td><span>m/44'/118'/0'/0/X</span><br/>
        <span>m/44'/60'/0'/0/X</span><br/>
        <span>m/44'/60'/0'/X</span></td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/finschia/resource/chain_finschia.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">FINSCHIA</span></td>
    <td>m/44'/438'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/firmachain/resource/chain_firmachain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">FIRMA CHAIN</span></td>
    <td>m/44'/7777777'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/govgen/resource/chain_govgen.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">GOVGEN</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/gravity-bridge/resource/chain_gravity-bridge.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold"><span>GRAVITY</span><br/>
        <span>-BRIDGE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/haqq/resource/chain_haqq.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">HAQQ</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/humans/resource/chain_humans.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">HUMANS</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/injective/resource/chain_injective.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">INJECTIVE</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/iris/resource/chain_iris.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">IRIS</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/ixo/resource/chain_ixo.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">IXO</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/jackal/resource/chain_jackal.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">JACKAL</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/juno/resource/chain_juno.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">JUNO</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/kava/resource/chain_kava.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">KAVA</span></td>
    <td><span>m/44'/60'/0'/0/X</span><br/>
        <span>m/44'/459'/0'/0/X</span><br/>
        <span>m/44'/118'/0'/0/X</span></td>
    <td><span>keccak256</span><br/>
        <span>secp256k1</span><br/>
        <span>secp256k1</span></td>
    <td>gRPC, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/ki-chain/resource/chain_ki-chain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">KI CHAIN</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/kyve/resource/chain_kyve.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">KYVE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/likecoin/resource/chain_likecoin.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">LIKE COIN</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/lum/resource/chain_lum.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">LUM NETWORK</span></td>
    <td><span>m/44'/880'/0'/0/X</span><br/>
        <span>m/44'/118'/0'/0/X</span></td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/mars-protocol/resource/chain_mars-protocol.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">MARS PROTOCOL</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/medibloc/resource/chain_medibloc.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">MEDIBLOC</span></td>
    <td>m/44'/371'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/migaloo/resource/chain_migaloo.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">MIGALOO</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/milkyway/resource/chain_milkyway.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">MILKYWAY</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/neutron/resource/chain_neutron.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">NEUTRON</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_ics.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/nibiru/resource/chain_nibiru.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">NIBIRU</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/nillion/resource/chain_nillion.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">NILLION</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/noble/resource/chain_noble.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">NOBLE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/nolus/resource/chain_nolus.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">NOLUS</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
	 	
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/nyx/resource/chain_nyx.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">NYX</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/okc/resource/chain_okc.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">OKT</span></td>
    <td><span>m/44'/60'/0'/0/X</span><br/>
        <span>m/44'/996'/0'/0/X</span><br/>
        <span>m/44'/996'/0'/0/X</span></td>
    <td><span>keccak256</span><br/>
        <span>keccak256</span><br/>
        <span>secp256k1</span></td>
    <td>Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/omniflix/resource/chain_omniflix.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">OMNIFLIX</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/onomy-protocol/resource/chain_onomy-protocol.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ONOMY</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/orai-chain/resource/chain_oraichain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ORAI CHAIN</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/osmosis/resource/chain_osmosis.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">OSMOSIS</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/passage/resource/chain_passage.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">PASSAGE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/persistence/resource/chain_persistence.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">PERSISTENCE</span></td>
    <td><span>m/44'/118'/0'/0/X</span><br/>
        <span>m/44'/750'/0'/0/X</span></td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/planq/resource/chain_planq.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">PLANQ</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/provenance/resource/chain_provenance.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">PROVENANCE</span></td>
    <td>m/44'/505'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/quasar/resource/chain_quasar.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">QUASAR</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/quicksilver/resource/chain_quicksilver.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">QUICKSILVER</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/realio/resource/chain_realio.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">REALIO</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/regen/resource/chain_regen.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">REGEN NETWORK</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/rizon/resource/chain_rizon.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">RIZON</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/routerchain/resource/chain_routerchain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ROUTER</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/saga/resource/chain_saga.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SAGA</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/secret/resource/chain_secret.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SECRET</span></td>
    <td><span>m/44'/529'/0'/0/X</span><br/>
        <span>m/44'/118'/0'/0/X</span></td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/seda/resource/chain_seda.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SEDA</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/sei/resource/chain_sei.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SEI</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/selfchain/resource/chain_selfchain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SELF CHAIN</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/sentinel/resource/chain_sentinel.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SENTINEL</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/sge/resource/chain_sge.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SGE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/shentu/resource/chain_shentu.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SHENTU</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/sommelier/resource/chain_sommelier.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SOMMELIER</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/source/resource/chain_source.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SOURCE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/stafi/resource/chain_stafi.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">STAFI</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/stargaze/resource/chain_stargaze.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">STARGAZE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_nft.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/stride/resource/chain_stride.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">STRIDE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_ics.png?raw=true" width="42" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/synternet/resource/chain_synternet.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SYNTERNET</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/tenet/resource/chain_tenet.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">TENET</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/teritori/resource/chain_teritori.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">TERITORRI</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/terra/resource/chain_terra.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">TERRA</span></td>
    <td>m/44'/330'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_cw20.png?raw=true" width="48" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/thorchain/resource/chain_thorchain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">THOR CHAIN</span></td>
    <td>m/44'/931'/0'/0/X</td>
    <td>secp256k1</td>
    <td>Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/umee/resource/chain_umee.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">UMEE</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/xion/resource/chain_xion.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">XION</span></td>
    <td>m/44'/118'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/xpla/resource/chain_xpla.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">XPLA</span></td>
    <td><span>m/44'/60'/0'/0/X</span><br/>
        <span>m/44'/60'/0'/0/X</span></td>
    <td><span>keccak256</span><br/>
        <span>secp256k1</span></td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
    
  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/unification/resource/chain_unification.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">UNIFICATION</span></td>
    <td>m/44'/5555'/0'/0/X</td>
    <td>secp256k1</td>
    <td>gRPC or Rest</td>
    <td></td>
  </tr>

  <tr>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/zeta/resource/chain_zeta.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ZETA CHAIN</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>gRPC or Rest, evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
    <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22"></td>
  </tr>
	 
</table>
</details>
<br>

<details >
<summary><h2 style='display: inline; font-size: 24px'>Supporting Chains (Ethereum & L1, L2)</h2></summary>
<br>
<table border="1">
  <tr>
    <th style="text-align:center">Image</th>
    <th style="text-align:center">Name</th>
    <th style="text-align:center" >HD Path</th>
    <th style="text-align:center">Public-Key Type</th>
    <th style="text-align:center">Call Method</th>
    <th style="text-align:center">Support</th>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/ethereum/resource/chain_ethereum.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ETHEREUM</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/arbitrum/resource/chain_arbitrum.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">ARBITRUM</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/avalanche/resource/chain_avalanche.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">AVALANCHE</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/base/resource/chain_base.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">BASE</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/bnb-smart-chain/resource/chain_bnb-smart-chain.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">BINANCE SMART</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/cronos/resource/chain_cronos.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">CRONOS</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/fantom/resource/chain_fantom.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">FANTOM</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/kaia/resource/chain_kaia.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">KAIA</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/optimism/resource/chain_optimism.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">OPTIMISM</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/polygon/resource/chain_polygon.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">POLYGON</span></td>
    <td>m/44'/60'/0'/0/X</td>
    <td>keccak256</td>
    <td>evmRPC</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_evm.png?raw=true" width="42" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_erc20.png?raw=true" width="52" height = "22">
        <img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
</table>
</details>
<br>


<details >
<summary><h2 style='display: inline; font-size: 24px'>Supporting Major Chains</h2></summary>
<br>
<table border="1">
  <tr>
    <th style="text-align:center">Image</th>
    <th style="text-align:center">Name</th>
    <th style="text-align:center" >HD Path</th>
    <th style="text-align:center">Public-Key Type</th>
    <th style="text-align:center">Call Method</th>
    <th style="text-align:center">Support</th>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/bitcoin/resource/chain_bitcoin.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">BITCOIN</span></td>
    <td><span>m/84'/0'/0'/0/X</span><br/>
        <span>m/49'/0'/0'/0/X</span><br/>
        <span>m/44'/0'/0'/0/X</span></td>
    <td><span>p2wpkh</span><br/>
        <span>p2sh</span><br/>
        <span>p2pkh</span></td>
    <td>Rest</td>
    <td></td>
  </tr>

  <tr> 
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/chain/sui/resource/chain_sui.png?raw=true" width="42" height = "42"></td>
    <td><span style="font-weight:bold">SUI</span></td>
    <td>m/44'/784'/0'/0'/X'</td>
    <td>ed25519</td>
    <td>Rest</td>
    <td><img src="https://github.com/cosmostation/chainlist/blob/main/resource/static/tag_dapp.png?raw=true" width="48" height = "22"></td>
  </tr>
</table>
</details>
<br>

<details>
<summary><h2 style='display: inline; font-size: 24px'>Cosmostation's Services and Community</h2></summary>
<br>

- [Official Website](https://www.cosmostation.io)
- [Mintscan Explorer](https://www.mintscan.io)
- [Web Wallet](https://wallet.cosmostation.io)
- [Telegram - International](https://t.me/cosmostation)
- [Kakao - Korean](https://open.kakao.com/o/g6KKSe5)
</details>
<br>


## License

Copyright © Cosmostation, Inc. All rights reserved.

Licensed under the [MIT](LICENSE).

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/wannabit-yongjoo"><img src="https://avatars3.githubusercontent.com/u/38899600?v=4?s=100" width="100px;" alt=""/><br /><sub><b>wannabit-yongjoo</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=wannabit-yongjoo" title="Code">💻</a> <a href="https://github.com/cosmostation/cosmostation-android/issues?q=author%3Awannabit-yongjoo" title="Bug reports">🐛</a> <a href="#maintenance-wannabit-yongjoo" title="Maintenance">🚧</a></td>
    <td align="center"><a href="https://jaybdev.net"><img src="https://avatars1.githubusercontent.com/u/20435620?v=4?s=100" width="100px;" alt=""/><br /><sub><b>JayB</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=kogisin" title="Documentation">📖</a> <a href="#projectManagement-kogisin" title="Project Management">📆</a></td>
    <td align="center"><a href="https://github.com/HeartBreaker"><img src="https://avatars3.githubusercontent.com/u/327096?v=4?s=100" width="100px;" alt=""/><br /><sub><b>HeartBreaker</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=HeartBreaker" title="Code">💻</a> <a href="https://github.com/cosmostation/cosmostation-android/issues?q=author%3AHeartBreaker" title="Bug reports">🐛</a> <a href="#maintenance-HeartBreaker" title="Maintenance">🚧</a></td>
    <td align="center"><a href="http://www.linkedin.com/in/orkunkulce"><img src="https://avatars0.githubusercontent.com/u/11277600?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Orkun Külçe</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=orkunkl" title="Code">💻</a></td>
    <td align="center"><a href="http://www.twitter.com/forwardsecrecy"><img src="https://avatars1.githubusercontent.com/u/6909088?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Ron Stoner</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/issues?q=author%3Aronaldstoner" title="Bug reports">🐛</a> <a href="https://github.com/cosmostation/cosmostation-android/commits?author=ronaldstoner" title="Documentation">📖</a></td>
    <td align="center"><a href="https://github.com/Kwonhyukjoon"><img src="https://avatars.githubusercontent.com/u/28729506?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Kwonhyukjoon</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=Kwonhyukjoon" title="Code">💻</a> <a href="https://github.com/cosmostation/cosmostation-android/issues?q=author%3AKwonhyukjoon" title="Bug reports">🐛</a> <a href="#maintenance-Kwonhyukjoon" title="Maintenance">🚧</a></td>
    <td align="center"><a href="https://github.com/kimheeje12"><img src="https://avatars.githubusercontent.com/u/85468864?v=4?s=100" width="100px;" alt=""/><br /><sub><b>TOM</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=kimheeje12" title="Code">💻</a> <a href="https://github.com/cosmostation/cosmostation-android/issues?q=author%3Akimheeje12" title="Bug reports">🐛</a> <a href="#maintenance-kimheeje12" title="Maintenance">🚧</a></td>
    <td align="center"><a href="https://github.com/soaryong-c"><img src="https://avatars.githubusercontent.com/u/91711862?v=4?s=100" width="100px;" alt=""/><br /><sub><b>soaryong-c</b></sub></a><br /><a href="https://github.com/cosmostation/cosmostation-android/commits?author=soaryong-c" title="Code">💻</a> <a href="https://github.com/cosmostation/cosmostation-android/issues?q=author%3Asoaryong-c" title="Bug reports">🐛</a> <a href="#maintenance-soaryong-c" title="Maintenance">🚧</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
