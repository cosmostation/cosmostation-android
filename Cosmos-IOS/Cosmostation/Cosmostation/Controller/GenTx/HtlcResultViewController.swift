//
//  HtlcResultViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper
import BinanceChain
import BitcoinKit
import Alamofire

class HtlcResultViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var htlcResultTableView: UITableView!
    @IBOutlet weak var bottomControlLayer: UIStackView!
    @IBOutlet weak var btnSentWallet: UIButton!
    @IBOutlet weak var btnReceievedWallet: UIButton!
    
    @IBOutlet weak var errorCard: CardView!
    @IBOutlet weak var errorMsgLabel: UILabel!
    @IBOutlet weak var errorCodeLabel: UILabel!
    
    @IBOutlet weak var loadingLayer: UIView!
    @IBOutlet weak var loadingImg: LoadingImageView!
    @IBOutlet weak var loadingProgressLabel: UILabel!
    
    var mHtlcDenom: String?
    var mHtlcToSendAmount = Array<Coin>()
    var mHtlcToChain: ChainType?
    var mHtlcToAccount: Account?
    var mHtlcSendFee: Fee?
    var mHtlcClaimFee: Fee?
    
    
    var mTimeStamp: Int64?
    var mRandomNumber: String?
    var mRandomNumberHash: String?
    
    
    var mSendHash: String?
    var mSendTxInfo: TxInfo?
    var mClaimHash: String?
    var mClaimTxInfo: TxInfo?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.htlcResultTableView.delegate = self
        self.htlcResultTableView.dataSource = self
        self.htlcResultTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.htlcResultTableView.register(UINib(nibName: "HtlcResultSentCell", bundle: nil), forCellReuseIdentifier: "HtlcResultSentCell")
        self.htlcResultTableView.register(UINib(nibName: "HtlcResultClaimCell", bundle: nil), forCellReuseIdentifier: "HtlcResultClaimCell")
        self.htlcResultTableView.rowHeight = UITableView.automaticDimension
        self.htlcResultTableView.estimatedRowHeight = UITableView.automaticDimension
        

        self.loadingProgressLabel.text = NSLocalizedString("htlc_swap_progress_0", comment: "")
        self.loadingImg.onStartAnimation()
        self.onCheckCreateHtlcSwap()
        
    }
    
    func onUpdateProgress(_ step: Int) {
        if (step == 1) {
            loadingProgressLabel.text = NSLocalizedString("htlc_swap_progress_1", comment: "")
        } else if (step == 2) {
            loadingProgressLabel.text = NSLocalizedString("htlc_swap_progress_2", comment: "")
        } else if (step == 3) {
            loadingProgressLabel.text = NSLocalizedString("htlc_swap_progress_3", comment: "")
        }
    }
    
    var mTxFetchCnt = 2
    func onUpdateView(_ errorMSg: String) {
        self.loadingLayer.isHidden = false
        if (!errorMSg.isEmpty) {
            //TODO handle error case
            self.bottomControlLayer.isHidden = false
            self.loadingLayer.isHidden = true
            self.errorCard.isHidden = false
            self.errorCodeLabel.text = errorMSg
            
        } else {
            mTxFetchCnt = mTxFetchCnt - 1
            if (mTxFetchCnt == 0) {
                if (mSendTxInfo != nil && mClaimTxInfo != nil) {
                    self.htlcResultTableView.reloadData()
                    self.htlcResultTableView.isHidden = false
                    self.bottomControlLayer.isHidden = false
                    self.loadingLayer.isHidden = true
                    
                } else {
                    //TODO handle error case
                    self.bottomControlLayer.isHidden = false
                    self.loadingLayer.isHidden = true
                    self.errorCard.isHidden = false
                    self.errorCodeLabel.text = errorMSg
                    
                }
            }
        }
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (mSendTxInfo != nil && mClaimTxInfo != nil) {
            return 2
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0 && mSendTxInfo != nil) {
            return onSetHtlcSentItems(tableView, indexPath);
        } else if (indexPath.row == 1 && mClaimTxInfo != nil) {
            return onSetHtlcClaimItems(tableView, indexPath);
        } else {
            let cell:HtlcResultSentCell? = tableView.dequeueReusableCell(withIdentifier:"HtlcResultSentCell") as? HtlcResultSentCell
            return cell!
        }
    }
    
    
    
    func onSetHtlcSentItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:HtlcResultSentCell? = tableView.dequeueReusableCell(withIdentifier:"HtlcResultSentCell") as? HtlcResultSentCell
        let msg = mSendTxInfo?.getMsgs()[0]
        cell?.sendImg.image = cell?.sendImg.image?.withRenderingMode(.alwaysTemplate)
        cell?.sendImg.tintColor = WUtils.getChainColor(chainType!)
        if (self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            cell?.blockHeightLabel.text = mSendTxInfo?.height
            cell?.txHashLabel.text = mSendTxInfo?.hash
            cell?.memoLabel.text = mSendTxInfo?.tx.value.memo
            
            let sendCoin = msg?.value.getAmounts()![0]
            cell?.sentAmountLabel.attributedText = WUtils.displayAmount2(sendCoin?.amount, cell!.sentAmountLabel.font!, 8, 8)
            WUtils.setDenomTitle(chainType!, cell!.sentDenom)
            
            cell?.feeLabel.attributedText = WUtils.displayAmount2(GAS_FEE_BNB_TRANSFER, cell!.feeLabel.font!, 0, 8)
            WUtils.setDenomTitle(chainType!, cell!.feeDenom)
            
            cell?.senderLabel.text = msg?.value.from
            cell?.relayRecipientLabel.text = msg?.value.to
            cell?.relaySenderLabel.text = msg?.value.sender_other_chain
            cell?.recipientLabel.text = msg?.value.recipient_other_chain
            cell?.randomHashLabel.text = msg?.value.random_number_hash
            
        } else if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            cell?.blockHeightLabel.text = mSendTxInfo?.height
            cell?.txHashLabel.text = mSendTxInfo?.txhash
            cell?.memoLabel.text = mSendTxInfo?.tx.value.memo
            
            let sendCoin = msg?.value.getAmounts()![0]
            cell?.sentAmountLabel.attributedText = WUtils.displayAmount2(sendCoin?.amount, cell!.sentAmountLabel.font!, WUtils.getKavaCoinDecimal(sendCoin!.denom), WUtils.getKavaCoinDecimal(sendCoin!.denom))
            cell?.sentDenom.text = sendCoin?.denom.uppercased()
            
            cell?.feeLabel.attributedText = WUtils.displayAmount2(mSendTxInfo!.getSimpleFee(), cell!.feeLabel.font!, 6, 6)
            WUtils.setDenomTitle(chainType!, cell!.feeDenom)
            
            cell?.senderLabel.text = msg?.value.from
            cell?.relayRecipientLabel.text = msg?.value.to
            cell?.relaySenderLabel.text = msg?.value.sender_other_chain
            cell?.recipientLabel.text = msg?.value.recipient_other_chain
            cell?.randomHashLabel.text = msg?.value.random_number_hash
        }
        return cell!
    }
    
    func onSetHtlcClaimItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:HtlcResultClaimCell? = tableView.dequeueReusableCell(withIdentifier:"HtlcResultClaimCell") as? HtlcResultClaimCell
        let msg = mClaimTxInfo?.getMsgs()[0]
        cell?.claimImg.image = cell?.claimImg.image?.withRenderingMode(.alwaysTemplate)
        cell?.claimImg.tintColor = WUtils.getChainColor(mHtlcToChain!)
        if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            cell?.blockHeightLabel.text = mClaimTxInfo?.height
            cell?.txHashLabel.text = mClaimTxInfo?.hash
            cell?.memoLabel.text = mClaimTxInfo?.tx.value.memo
            
            cell?.receivedAmountLabel.text = ""
            cell?.receivedDenom.text = ""
            
            cell?.feeLabel.attributedText = WUtils.displayAmount2(GAS_FEE_BNB_TRANSFER, cell!.feeLabel.font!, 0, 8)
            WUtils.setDenomTitle(mHtlcToChain!, cell!.feeDenomLabel)
            
            cell?.claimerAddress.text = msg?.value.from
            cell?.randomNumberLabel.text = msg?.value.random_number
            cell?.swapIdLabel.text = msg?.value.swap_id
            
            
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            cell?.blockHeightLabel.text = mClaimTxInfo?.height
            cell?.txHashLabel.text = mClaimTxInfo?.txhash
            cell?.memoLabel.text = mClaimTxInfo?.tx.value.memo
            
            let receiveCoin = mClaimTxInfo!.getSimpleSwapCoin()
            if (!receiveCoin.denom.isEmpty) {
                cell?.receivedAmountLabel.attributedText = WUtils.displayAmount2(receiveCoin.amount, cell!.receivedAmountLabel.font!, WUtils.getKavaCoinDecimal(receiveCoin.denom), WUtils.getKavaCoinDecimal(receiveCoin.denom))
                cell?.receivedDenom.text = receiveCoin.denom.uppercased()
            }
            
            cell?.feeLabel.attributedText = WUtils.displayAmount2(mClaimTxInfo!.getSimpleFee(), cell!.feeLabel.font!, 6, 6)
            WUtils.setDenomTitle(mHtlcToChain!, cell!.feeDenomLabel)
            
            cell?.claimerAddress.text = msg?.value.from
            cell?.randomNumberLabel.text = msg?.value.random_number
            cell?.swapIdLabel.text = msg?.value.swap_id
            
        }
        return cell!
    }
    
    
    @IBAction func onClickSentWallet(_ sender: UIButton) {
        self.onStartMainTab()
    }
    
    @IBAction func onClickReceivedWallet(_ sender: UIButton) {
        BaseData.instance.setRecentAccountId(mHtlcToAccount!.account_id)
        BaseData.instance.setLastTab(1)
        self.onStartMainTab()
    }
    
    
    
    func onCheckCreateHtlcSwap() {
//        print("onCheckCreateHtlcSwap")
        var url: String?
        if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account!.account_address
        } else if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_ACCOUNT_INFO + account!.account_address
        } else {
            onCreateHtlcSwap()
            return;
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
                    self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: self.account!)
                        //TODO error handle
                        self.onUpdateView(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    if (SHOW_LOG) { print("onCheckCreateHtlcSwap ", res) }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(self.account!, accountInfo))
                    BaseData.instance.updateBalances(self.account!.account_id, WUtils.getBalancesWithKavaAccountInfo(self.account!, accountInfo))
                    self.onCreateHtlcSwap()
                }
                
            case .failure(let error):
                //TODO error handle
                self.onUpdateView(error.localizedDescription)
                self.onShowToast(error.localizedDescription)
            }
        }
    }
    
    func onCreateHtlcSwap() {
//        print("onCreateHtlcSwap")
        DispatchQueue.global().async {
            guard let words = KeychainWrapper.standard.string(forKey: self.account!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                self.onUpdateView(NSLocalizedString("error_invalid_password", comment: ""))
                return
            }
            
            if (self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
                var binance: BinanceChain?
                var pKey: HDPrivateKey?
                var wallet = Wallet()
                
                if (self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                    
                } else if (self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
                    binance = BinanceChain(endpoint: BinanceChain.Endpoint.testnet)
                    pKey = WKey.getHDKeyFromWords(words, self.account!)
                    wallet = Wallet(privateKey: pKey!.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.testnet)
                }
                
                wallet.synchronise(){ (error) in
                    if let error = error {
                        if(SHOW_LOG) { print(error) }
                        self.onUpdateView(error.localizedDescription)
                        return
                    }
                    
                    self.mTimeStamp = Date().millisecondsSince1970 / 1000
                    self.mRandomNumber = WKey.generateRandomBytes()
                    self.mRandomNumberHash = WKey.getRandomNumnerHash(self.mRandomNumber!, self.mTimeStamp!)
                    
                    let sendAmount = NSDecimalNumber.init(string: self.mHtlcToSendAmount[0].amount).multiplying(byPowerOf10: 8)
//                    print("sendAmount ", sendAmount.int64Value)
                    
                    let bnbMsg = Message.createHtlc(toAddress: BNB_TEST_DEPUTY,
                                                    otherFrom: KAVA_TEST_DEPUTY,
                                                    otherTo: self.mHtlcToAccount!.account_address,
                                                    timestamp: self.mTimeStamp!,
                                                    randomNumberHash: self.mRandomNumberHash!,
                                                    sendAmount: sendAmount.int64Value,
                                                    sendDenom: BNB_MAIN_DENOM,
                                                    expectedIncom: sendAmount.stringValue + ":" + BNB_MAIN_DENOM,
                                                    heightSpan: 10001,
                                                    crossChain: true,
                                                    memo: "Create Atomic Swap via Cosmostation Wallet",
                                                    wallet: wallet)
                    
                    binance!.broadcast(message: bnbMsg, sync: true) { (response) in
                        print(response.broadcast)
                        if let error = response.error {
                            if(SHOW_LOG) { print(error.localizedDescription) }
                            self.onUpdateView(error.localizedDescription)
                        }
                        self.mSendHash = response.broadcast[0].hash
                        DispatchQueue.main.async(execute: {
                            self.onFetchSwapId()
                        });
                        
                    }
                }
                
                
            } else if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                var stdTx:StdTx!
                do {
                    if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                        
                    } else if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                        let pKey = WKey.getHDKeyFromWords(words, self.account!)
                        
                        self.mTimeStamp = Date().millisecondsSince1970 / 1000
                        self.mRandomNumber = WKey.generateRandomBytes()
                        self.mRandomNumberHash = WKey.getRandomNumnerHash(self.mRandomNumber!, self.mTimeStamp!)
                        
                        let msg = MsgGenerator.genCreateSwapMsg(self.chainType!, self.mHtlcToChain!,
                                                                self.account!, self.mHtlcToAccount!,
                                                                self.mHtlcToSendAmount, self.mTimeStamp!, self.mRandomNumberHash!)
                        
                        var msgList = Array<Msg>()
                        msgList.append(msg)
                        
                        let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainName(self.account!.account_base_chain),
                                                               String(self.account!.account_account_numner),
                                                               String(self.account!.account_sequence_number),
                                                               msgList,
                                                               self.mHtlcSendFee!,
                                                               "Create Atomic Swap via Cosmostation Wallet")
                        
                        let encoder = JSONEncoder()
                        encoder.outputFormatting = .sortedKeys
                        let data = try? encoder.encode(stdMsg)
//                        print("String " , String(data:data!, encoding:.utf8))
                        let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                        let rawData: Data? = rawResult!.data(using: .utf8)
                        let hash = Crypto.sha256(rawData!)
                        
                        let signedData: Data? = try Crypto.sign(hash, privateKey: pKey.privateKey())
                        
                        var genedSignature = Signature.init()
                        var genPubkey =  PublicKey.init()
                        genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
                        genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
                        genedSignature.pub_key = genPubkey
                        genedSignature.signature = WKey.convertSignature(signedData!)
                        genedSignature.account_number = String(self.account!.account_account_numner)
                        genedSignature.sequence = String(self.account!.account_sequence_number)
                        
                        var signatures: Array<Signature> = Array<Signature>()
                        signatures.append(genedSignature)
                        
                        stdTx = MsgGenerator.genSignedTx(msgList, self.mHtlcSendFee!, "Create Atomic Swap via Cosmostation Wallet", signatures)
                    }
                    
                } catch {
                    //TODO error handle
                    if(SHOW_LOG) { print(error) }
                    self.onUpdateView(error.localizedDescription)
                }
                
                DispatchQueue.main.async(execute: {
                    let postTx = PostTx.init("sync", stdTx.value)
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(postTx)
                    do {
                        let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                        var url: String?
                        if (self.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                            url = KAVA_BORAD_TX
                        } else if (self.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                            url = KAVA_TEST_BORAD_TX
                        }
                        let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                        request.validate().responseJSON { response in
                            switch response.result {
                            case .success(let res):
                                if let result = res as? [String : Any], let hash = result["txhash"] as? String  {
                                    if (SHOW_LOG) { print("onCreateHtlcSwap ok ", hash) }
                                    self.mSendHash = hash
                                    DispatchQueue.main.async(execute: {
                                        self.onFetchSwapId()
                                    });
                                }
                                
                            case .failure(let error):
                                //TODO error handle
                                if(SHOW_LOG) { print("onCreateHtlcSwap error ", error) }
                                self.onUpdateView(error.localizedDescription)
                            }
                        }

                    } catch {
                        //TODO error handle
                        if (SHOW_LOG) { print(error) }
                        self.onUpdateView(error.localizedDescription)
                    }
                });
            }
        }
    }
    
    var mSwapFetchCnt = 8
    func onFetchSwapId() {
        onUpdateProgress(1)
//        print("onFetchSwapId ", mSwapFetchCnt)
        var url = ""
        if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            let swapId = WKey.getSwapId(self.mRandomNumberHash!, BNB_TEST_DEPUTY, self.account!.account_address)
            url = BNB_TEST_URL_CHECK_SWAPID + swapId
            if (SHOW_LOG) { print("swapId url ", url) }
            
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            let swapId = WKey.getSwapId(self.mRandomNumberHash!, KAVA_TEST_DEPUTY, self.account!.account_address)
            url = KAVA_TEST_CHECK_SWAPID + swapId
            if (SHOW_LOG) { print("swapId url ", url) }
        }

        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (SHOW_LOG) { print("onFetchSwapId ", res) }
                self.mSwapFetchCnt = self.mSwapFetchCnt - 1
                guard let info = res as? [String : Any], info["error"] == nil else {
                    if (self.mSwapFetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(5000), execute: {
                            self.onFetchSwapId()
                        })
                    }
                    return
                }
                DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(5000), execute: {
                    self.onCheckClaimHtlcSwap()
                })
                
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchSwapId failure", error , " ", self.mSwapFetchCnt) }
                self.mSwapFetchCnt = self.mSwapFetchCnt - 1
                if (self.mSwapFetchCnt > 0) {
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(5000), execute: {
                        self.onFetchSwapId()
                    })
                } else {
                    self.onUpdateView(error.localizedDescription)
                }
                return
            }
        }
    }
    
    func onCheckClaimHtlcSwap() {
        onUpdateProgress(2)
//        print("onCheckClaimHtlcSwap")
        var url: String?
        if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + mHtlcToAccount!.account_address
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_ACCOUNT_INFO + mHtlcToAccount!.account_address
        } else {
            onClaimHtlcSwap()
            return
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
                    self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: self.mHtlcToAccount!)
                        //TODO error handle
                        self.onUpdateView(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(self.mHtlcToAccount!, accountInfo))
                    BaseData.instance.updateBalances(self.mHtlcToAccount!.account_id, WUtils.getBalancesWithKavaAccountInfo(self.mHtlcToAccount!, accountInfo))
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(5000), execute: {
                        self.onClaimHtlcSwap()
                    })
                }
                
            case .failure(let error):
                //TODO error handle
                self.onUpdateView(error.localizedDescription)
                self.onShowToast(error.localizedDescription)
            }
        }
    }
    
    func onClaimHtlcSwap() {
//        print("onClaimHtlcSwap")
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.mHtlcToAccount!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                self.onUpdateView(NSLocalizedString("error_invalid_password", comment: ""))
                return
            }
            
            if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
                var binance: BinanceChain?
                var pKey: HDPrivateKey?
                var wallet = Wallet()
                if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                    
                } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
                    binance = BinanceChain(endpoint: BinanceChain.Endpoint.testnet)
                    pKey = WKey.getHDKeyFromWords(words, self.mHtlcToAccount!)
                    wallet = Wallet(privateKey: pKey!.privateKey().raw.hexEncodedString(), endpoint: BinanceChain.Endpoint.testnet)
                }
                wallet.synchronise(){ (error) in
                    if let error = error {
                        if (SHOW_LOG) { print(error) }
                        self.onUpdateView(error.localizedDescription)
                        return
                    }
                    
                    let swapId = WKey.getSwapId(self.mRandomNumberHash!, BNB_TEST_DEPUTY, self.account!.account_address)
                    if (SHOW_LOG) { print("swapId ", swapId) }
                    if (SHOW_LOG) { print("randomNumber ", self.mRandomNumber!) }
                    let bnbMsg = Message.claimHtlc(randomNumber: self.mRandomNumber!,
                                                   swapId: swapId,
                                                   memo: "Claim Atomic Swap via Cosmostation Wallet",
                                                   wallet: wallet)
                    
                    binance!.broadcast(message: bnbMsg, sync: true) { (response) in
                        print(response.broadcast)
                        if let error = response.error {
                            if (SHOW_LOG) { print(error.localizedDescription) }
                            self.onUpdateView(error.localizedDescription)
                        }
                        if (SHOW_LOG) { print("onClaimHtlcSwap OK ", response.broadcast[0].hash) }
                        self.mClaimHash = response.broadcast[0].hash
                        self.onFetchSendTx()
                        self.onFetchClaimTx()
                        
                    }
                }
                
            } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN || self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                do {
                    if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {

                    } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                        let pKey = WKey.getHDKeyFromWords(words, self.mHtlcToAccount!)
                        
                        let swapId = WKey.getSwapId(self.mRandomNumberHash!, KAVA_TEST_DEPUTY, self.account!.account_address)
                        let msg = MsgGenerator.genClaimAtomicSwap(self.mHtlcToAccount!.account_address,
                                                                  swapId,
                                                                  self.mRandomNumber!)
                        
                        var msgList = Array<Msg>()
                        msgList.append(msg)
                        
                        let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainName(self.mHtlcToAccount!.account_base_chain),
                                                               String(self.mHtlcToAccount!.account_account_numner),
                                                               String(self.mHtlcToAccount!.account_sequence_number),
                                                               msgList,
                                                               self.mHtlcClaimFee!,
                                                               "Claim Atomic Swap via Cosmostation Wallet")
                        
                        let encoder = JSONEncoder()
                        encoder.outputFormatting = .sortedKeys
                        let data = try? encoder.encode(stdMsg)
                        let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
                        let rawData: Data? = rawResult!.data(using: .utf8)
                        let hash = Crypto.sha256(rawData!)
                        
                        let signedData: Data? = try Crypto.sign(hash, privateKey: pKey.privateKey())
                        
                        var genedSignature = Signature.init()
                        var genPubkey =  PublicKey.init()
                        genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
                        genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
                        genedSignature.pub_key = genPubkey
                        genedSignature.signature = WKey.convertSignature(signedData!)
                        genedSignature.account_number = String(self.mHtlcToAccount!.account_account_numner)
                        genedSignature.sequence = String(self.mHtlcToAccount!.account_sequence_number)
                        
                        var signatures: Array<Signature> = Array<Signature>()
                        signatures.append(genedSignature)
                        
                        stdTx = MsgGenerator.genSignedTx(msgList, self.mHtlcClaimFee!, "Claim Atomic Swap via Cosmostation Wallet", signatures)
                    }
                    
                } catch {
                    if(SHOW_LOG) { print(error) }
                    self.onUpdateView(error.localizedDescription)
                }
                
                DispatchQueue.main.async(execute: {
                    let postTx = PostTx.init("sync", stdTx.value)
                    let encoder = JSONEncoder()
                    encoder.outputFormatting = .sortedKeys
                    let data = try? encoder.encode(postTx)
                    do {
                        let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                        var url: String?
                        if (self.mHtlcToChain! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                            url = KAVA_BORAD_TX
                        } else if (self.mHtlcToChain! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
                            url = KAVA_TEST_BORAD_TX
                        }
                        let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                        request.validate().responseJSON { response in
                            switch response.result {
                            case .success(let res):
                                if (SHOW_LOG) { print("onClaimHtlcSwap ", res) }
                                if let result = res as? [String : Any], let hash = result["txhash"] as? String  {
                                    if (SHOW_LOG) { print("onClaimHtlcSwap OK ", hash) }
                                    self.mClaimHash = hash
                                    self.onFetchSendTx()
                                    self.onFetchClaimTx()
                                }
                            case .failure(let error):
                                if (SHOW_LOG) { print("onClaimHtlcSwap error ", error) }
                                self.onUpdateView(error.localizedDescription)
                            }
                        }
                    } catch {
                        if (SHOW_LOG) { print(error) }
                        self.onUpdateView(error.localizedDescription)
                    }
                });
            }
        }
    }
    
    
    
    
    func onFetchSendTx() {
//        print("onFetchSendTx")
        var url = ""
        var request:DataRequest?
        if (self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            url = BNB_URL_TX + mSendHash!
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
        } else if (self.chainType == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            url = BNB_TEST_URL_TX + mSendHash!
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
        } else if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_TX + mSendHash!
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        } else if (self.chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_TX + mSendHash!
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        }
        request!.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any], info["error"] == nil else {
                    if (SHOW_LOG) { print("onFetchSendTx error") }
                    self.onUpdateView(NSLocalizedString("error_network", comment: ""))
                    return
                }
                if (SHOW_LOG) { print("onFetchSendTx OK", res) }
                self.mSendTxInfo = TxInfo.init(info)
                self.onUpdateView("")
                
            case .failure(let error):
                if (SHOW_LOG) {  print("onFetchSendTx failure", error) }
                self.onUpdateView(error.localizedDescription)
                return
            }
        }
        
    }
    
    var mClaimTxFetchCnt = 6
    func onFetchClaimTx() {
        onUpdateProgress(3)
//        print("onFetchClaimTx")
        var url = ""
        var request:DataRequest?
        if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            url = BNB_URL_TX + mClaimHash!
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            url = BNB_TEST_URL_TX + mClaimHash!
            request = Alamofire.request(url, method: .get, parameters: ["format":"json"], encoding: URLEncoding.default, headers: [:])
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_TX + mClaimHash!
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        } else if (self.mHtlcToChain == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_TX + mClaimHash!
            request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        }
        request!.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (SHOW_LOG) { print("onFetchClaimTx OK", res) }
                self.mClaimTxFetchCnt = self.mClaimTxFetchCnt - 1
                guard let info = res as? [String : Any], info["error"] == nil else {
                    if (self.mClaimTxFetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(3000), execute: {
                            self.onFetchClaimTx()
                        })
                    }
                    return
                }
                self.mClaimTxInfo = TxInfo.init(info)
                self.onUpdateView("")
                
            case .failure(let error):
                self.mClaimTxFetchCnt = self.mClaimTxFetchCnt - 1
                if (self.mClaimTxFetchCnt > 0) {
                    DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(3000), execute: {
                        self.onFetchClaimTx()
                    })
                } else {
                    self.onUpdateView(error.localizedDescription)
                }
                if (SHOW_LOG) { print("onFetchClaimTx failure", error) }
                return
            }
        }
    }
}
