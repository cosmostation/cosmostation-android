//
//  EventStakeDropViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/23.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import BitcoinKit
import SwiftKeychainWrapper

class EventStakeDropViewController: BaseViewController, QrScannerDelegate, PasswordViewDelegate{
    
    @IBOutlet weak var eventTitleImg: UIImageView!
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnConfirm: UIButton!
    @IBOutlet weak var btnScan: UIButton!
    @IBOutlet weak var btnPaste: UIButton!
    @IBOutlet weak var EthAddressInput: AddressInputTextField!
    @IBOutlet weak var sendAmount: UILabel!
    @IBOutlet weak var sendAmountDenom: UILabel!
    @IBOutlet weak var feeAmount: UILabel!
    @IBOutlet weak var feeAmountDenom: UILabel!
    @IBOutlet weak var recipientAddress: UILabel!
    @IBOutlet weak var dataCard: CardView!
    
    var mToRecipientAddress:String?
    var mToSendAmount = Array<Coin>()
    var mFee: Fee?
    var mEthAddress = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.EthAddressInput.placeholder = "ETH address for Airdrop"
        self.setInitEventData()
        self.onInitView()
        
        self.dataCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard (_:))))
    }
    
    func onInitView() {
        WUtils.showCoinDp(mFee!.amount[0], feeAmountDenom, feeAmount, chainType!)
        WUtils.showCoinDp(mToSendAmount[0], sendAmountDenom, sendAmount, chainType!)
        recipientAddress.text = mToRecipientAddress
        recipientAddress.adjustsFontSizeToFitWidth = true
    }
    
    @objc func dismissKeyboard (_ sender: UITapGestureRecognizer) {
        self.view.endEditing(true)
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
    }
    
    @IBAction func onClickConfirm(_ sender: UIButton) {
        let userInput = EthAddressInput.text?.trimmingCharacters(in: .whitespaces)
        if (userInput == nil || userInput!.count < 40 || !userInput!.starts(with: "0x")) {
            self.onShowToast(NSLocalizedString("error_invalid_address_eth_target", comment: ""))
        } else {
            self.mEthAddress = userInput!
            self.onShowConfirmAlert(mEthAddress)
        }
    }
    
    @IBAction func onClickScan(_ sender: UIButton) {
        let qrScanVC = QRScanViewController(nibName: "QRScanViewController", bundle: nil)
        qrScanVC.hidesBottomBarWhenPushed = true
        qrScanVC.resultDelegate = self
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        self.navigationController?.pushViewController(qrScanVC, animated: false)
    }
    
    @IBAction func onClickPaste(_ sender: UIButton) {
        if let myString = UIPasteboard.general.string {
            self.EthAddressInput.text = myString.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))
        }
    }
    
    func scannedAddress(result: String) {
        self.EthAddressInput.text = result.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
    }
    
    func setInitEventData() {
        if (chainType! == ChainType.COSMOS_MAIN) {
            eventTitleImg.image = UIImage(named: "stakedropimgl")
            
            self.mToRecipientAddress = PERSISTENCE_COSMOS_EVENT_ADDRESS
            
            self.mToSendAmount.removeAll()
            let sendCoin = Coin.init(COSMOS_MAIN_DENOM, "1000")
            self.mToSendAmount.append(sendCoin)
            
            var fee = Fee.init()
            let feeCoin = Coin.init(COSMOS_MAIN_DENOM, "2500")
            var feeAmount: Array<Coin> = Array<Coin>()
            feeAmount.append(feeCoin)
            fee.amount = feeAmount
            fee.gas = GAS_FEE_AMOUNT_LOW
            self.mFee = fee
            
            
        } else if (chainType! == ChainType.KAVA_MAIN) {
            eventTitleImg.image = UIImage(named: "stakedropimglKava")
            
            self.mToRecipientAddress = PERSISTENCE_KAVA_EVENT_ADDRESS
            
            self.mToSendAmount.removeAll()
            let sendCoin = Coin.init(KAVA_MAIN_DENOM, "1000")
            self.mToSendAmount.append(sendCoin)
            
            var fee = Fee.init()
            let feeCoin = Coin.init(KAVA_MAIN_DENOM, "5000")
            var feeAmount: Array<Coin> = Array<Coin>()
            feeAmount.append(feeCoin)
            fee.amount = feeAmount
            fee.gas = KAVA_GAS_FEE_AMOUNT_SEND
            self.mFee = fee
        }
    }
    
    func onShowConfirmAlert(_ ethAddress: String) {
        let msg = String(format: NSLocalizedString("str_event_confirm", comment: ""), ethAddress)
        let alertController = UIAlertController(title: NSLocalizedString("str_event_confirm_title", comment: ""), message: msg, preferredStyle: .alert)
        let settingsAction = UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default) { (_) -> Void in
            let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
            self.navigationItem.title = ""
            self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
            passwordVC.mTarget = PASSWORD_ACTION_CHECK_TX
            passwordVC.resultDelegate = self
            self.navigationController?.pushViewController(passwordVC, animated: false)
            
        }
        let cancelAction = UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: nil)
        alertController.addAction(cancelAction)
        alertController.addAction(settingsAction)
        DispatchQueue.main.async {
            self.present(alertController, animated: true, completion: nil)
        }
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            self.onFetchAccountInfo(self.account!)
        }
    }
    
    func onFetchAccountInfo(_ account: Account) {
        self.showWaittingAlert()
        var url: String?
        if (chainType! == ChainType.COSMOS_MAIN) {
             url = COSMOS_MAIN_BALANCE + account.account_address
        } else if (chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType! == ChainType.COSMOS_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let info = responseData.object(forKey: "result") as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                    self.onGenSendTx()
                    
                } else if (self.chainType! == ChainType.KAVA_MAIN) {
                    guard  let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.hideWaittingAlert()
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                        return
                    }
                    let accountInfo = KavaAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, accountInfo))
                    self.onGenSendTx()
                    
                }
                
            case .failure(let error):
                self.hideWaittingAlert()
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
        }
    }
    
    func onGenSendTx() {
        DispatchQueue.global().async {
            var stdTx:StdTx!
            guard let words = KeychainWrapper.standard.string(forKey: self.account!.account_uuid.sha1())?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") else {
                return
            }
            do {
                let pKey = WKey.getHDKeyFromWords(words, self.account!)
                let msg = MsgGenerator.genGetSendMsg(self.account!.account_address,
                                                     self.mToRecipientAddress!,
                                                     self.mToSendAmount,
                                                     self.chainType!)
                var msgList = Array<Msg>()
                msgList.append(msg)
                
                let stdMsg = MsgGenerator.getToSignMsg(WUtils.getChainId(self.account!.account_base_chain),
                                                       String(self.account!.account_account_numner),
                                                       String(self.account!.account_sequence_number),
                                                       msgList,
                                                       self.mFee!,
                                                       self.mEthAddress)
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
                genedSignature.account_number = String(self.account!.account_account_numner)
                genedSignature.sequence = String(self.account!.account_sequence_number)
                
                var signatures: Array<Signature> = Array<Signature>()
                signatures.append(genedSignature)
                
                stdTx = MsgGenerator.genSignedTx(msgList, self.mFee!, self.mEthAddress, signatures)
                
                
            } catch {
                if(SHOW_LOG) { print(error) }
                
            }
            
            DispatchQueue.main.async(execute: {
                let postTx = PostTx.init("sync", stdTx.value)
                let encoder = JSONEncoder()
                encoder.outputFormatting = .sortedKeys
                let data = try? encoder.encode(postTx)
                do {
                    let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                    var url: String?
                    if (self.chainType! == ChainType.COSMOS_MAIN) {
                        url = COSMOS_MAIN_BORAD_TX
                    } else if (self.chainType! == ChainType.KAVA_MAIN) {
                        url = KAVA_BORAD_TX
                    }
                    let request = Alamofire.request(url!, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
                    request.responseJSON { response in
                        var txResult = [String:Any]()
                        switch response.result {
                        case .success(let res):
                            if(SHOW_LOG) { print("Send ", res) }
                            if let result = res as? [String : Any]  {
                                txResult = result
                            }
                        case .failure(let error):
                            if(SHOW_LOG) { print("send error ", error) }
                            if (response.response?.statusCode == 500) {
                                txResult["net_error"] = 500
                            }
                        }
                        if (self.waitAlert != nil) {
                            self.waitAlert?.dismiss(animated: true, completion: {
                                self.onStartTxDetail(txResult)
                            })
                        }
                    }

                } catch {
                    if(SHOW_LOG) { print(error) }
                }
            });
        }
    }
}
