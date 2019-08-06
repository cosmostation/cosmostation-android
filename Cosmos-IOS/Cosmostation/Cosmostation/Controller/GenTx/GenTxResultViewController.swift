//
//  GenTxResultViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class GenTxResultViewController: BaseViewController {
    
    @IBOutlet weak var chainBg: UIImageView!
    
    @IBOutlet weak var sendResultView: CardView!
    @IBOutlet weak var sendResultType: UILabel!
    @IBOutlet weak var sendResultHash: UILabel!
    @IBOutlet weak var sendResultTime: UILabel!
    @IBOutlet weak var sendResultBlock: UILabel!
    @IBOutlet weak var sendResultAmount: UILabel!
    @IBOutlet weak var sendResultFee: UILabel!
    @IBOutlet weak var sendResultToAddress: UILabel!
    @IBOutlet weak var sendResultMemo: UILabel!
    @IBOutlet weak var sendDenomAmount: UILabel!
    @IBOutlet weak var sendDenomFee: UILabel!
    
    
    @IBOutlet weak var delegateResultView: CardView!
    @IBOutlet weak var delegateResultType: UILabel!
    @IBOutlet weak var delegateResultHash: UILabel!
    @IBOutlet weak var delegateResultTime: UILabel!
    @IBOutlet weak var delegateResultBlock: UILabel!
    @IBOutlet weak var delegateResultAmount: UILabel!
    @IBOutlet weak var delegateResultFee: UILabel!
    @IBOutlet weak var delegateResultValAddress: UILabel!
    @IBOutlet weak var delegateResultMemo: UILabel!
    @IBOutlet weak var delegateDenomAmount: UILabel!
    @IBOutlet weak var delegateDenomFee: UILabel!
    
    
    @IBOutlet weak var undelegateResultView: CardView!
    @IBOutlet weak var undelegateResultType: UILabel!
    @IBOutlet weak var undelegateResultHash: UILabel!
    @IBOutlet weak var undelegateResultTime: UILabel!
    @IBOutlet weak var undelegateResultBlock: UILabel!
    @IBOutlet weak var undelegateResultAmount: UILabel!
    @IBOutlet weak var undelegateResultFee: UILabel!
    @IBOutlet weak var undelegateResultValAddress: UILabel!
    @IBOutlet weak var undelegateResultMemo: UILabel!
    @IBOutlet weak var undelegateDenomAmount: UILabel!
    @IBOutlet weak var undelegateDenomFee: UILabel!
    
    
    @IBOutlet weak var redelegateResultView: CardView!
    @IBOutlet weak var redelegateResultType: UILabel!
    @IBOutlet weak var redelegateResultHash: UILabel!
    @IBOutlet weak var redelegateResultTime: UILabel!
    @IBOutlet weak var redelegateResultBlock: UILabel!
    @IBOutlet weak var redelegateResultAmount: UILabel!
    @IBOutlet weak var redelegateResultFee: UILabel!
    @IBOutlet weak var redelegateResultFromValAddress: UILabel!
    @IBOutlet weak var redelegateResultToValAddress: UILabel!
    @IBOutlet weak var redelegateResultMemo: UILabel!
    @IBOutlet weak var redelegateDenomAmount: UILabel!
    @IBOutlet weak var redelegateDenomFee: UILabel!
    
    
    @IBOutlet weak var rewardResultView: CardView!
    @IBOutlet weak var rewardResultType: UILabel!
    @IBOutlet weak var rewardResultHash: UILabel!
    @IBOutlet weak var rewardResultTime: UILabel!
    @IBOutlet weak var rewardResultBlock: UILabel!
    @IBOutlet weak var rewardResultFee: UILabel!
    @IBOutlet weak var rewardResultFromValAddress: UILabel!
    @IBOutlet weak var rewardResultMemo: UILabel!
    @IBOutlet weak var rewardDenomFee: UILabel!
    
    
    @IBOutlet weak var addressChangeResultView: CardView!
    @IBOutlet weak var addressChangeResultType: UILabel!
    @IBOutlet weak var addressChangeResultHash: UILabel!
    @IBOutlet weak var addressChangeResultTime: UILabel!
    @IBOutlet weak var addressChangeResultBlock: UILabel!
    @IBOutlet weak var addressChangeResultFee: UILabel!
    @IBOutlet weak var addressChangeResultAddress: UILabel!
    @IBOutlet weak var addressChangeResultMemo: UILabel!
    @IBOutlet weak var addressChangeDenomFee: UILabel!
    
    
    @IBOutlet weak var reInvestResultView: CardView!
    @IBOutlet weak var reInvestResultType: UILabel!
    @IBOutlet weak var reInvestResultHash: UILabel!
    @IBOutlet weak var reInvestResultTime: UILabel!
    @IBOutlet weak var reInvestResultBlock: UILabel!
    @IBOutlet weak var reInvestResultRewardAmount: UILabel!
    @IBOutlet weak var reInvestResultDelegateAmount: UILabel!
    @IBOutlet weak var reInvestResultFee: UILabel!
    @IBOutlet weak var reInvestValidatorAddress: UILabel!
    @IBOutlet weak var reInvestResultMemo: UILabel!
    @IBOutlet weak var reInvestDenomDelegate: UILabel!
    @IBOutlet weak var reInvestDenomFee: UILabel!
    
    var response:[String:Any]?

    @IBOutlet weak var txResultTitleLabel: UILabel!
    @IBOutlet weak var actionLayer: UIStackView!
    @IBOutlet weak var dismissBtn: UIButton!
    
    @IBOutlet weak var errorCardView: CardView!
    @IBOutlet weak var errorCode: UILabel!
    
    @IBOutlet weak var loadingView: UIView!
    @IBOutlet weak var loadingImgs: LoadingImageView!
    
    var mChain: ChainType?
    var mTxType: String?
    var mTxHash: String?
    var mTxInfo: TxInfo?
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mChain = WUtils.getChainType(BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())!.account_base_chain)
        WUtils.setDenomTitle(mChain!, sendDenomAmount)
        WUtils.setDenomTitle(mChain!, sendDenomFee)
        WUtils.setDenomTitle(mChain!, delegateDenomAmount)
        WUtils.setDenomTitle(mChain!, delegateDenomFee)
        WUtils.setDenomTitle(mChain!, undelegateDenomAmount)
        WUtils.setDenomTitle(mChain!, undelegateDenomFee)
        WUtils.setDenomTitle(mChain!, redelegateDenomAmount)
        WUtils.setDenomTitle(mChain!, redelegateDenomFee)
        WUtils.setDenomTitle(mChain!, rewardDenomFee)
        WUtils.setDenomTitle(mChain!, addressChangeDenomFee)
        WUtils.setDenomTitle(mChain!, reInvestDenomDelegate)
        WUtils.setDenomTitle(mChain!, reInvestDenomFee)
        
        if (mChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            chainBg.image = UIImage(named: "bg_cosmos")
            guard let txType = response?["type"] as? String, let txHash = response?["txhash"] as? String  else {
                self.onStartMainTab()
                return
            }
            mTxType = txType
            mTxHash = txHash
            
            if let code = response?["code"] as? Int {
                onShowErrorView(code)
                return
            }
            
        } else if (mChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            chainBg.image = UIImage(named: "bg_iris")
            guard let txType = response?["type"] as? String, let txHash = response?["hash"] as? String  else {
                self.onStartMainTab()
                return
            }
            mTxType = txType
            mTxHash = txHash
            
            if let check_tx = response?["check_tx"] as? [String:Any], let code = check_tx["code"] as? Int{
                onShowErrorView(code)
                return
            }
        }
    
        self.loadingImgs.onStartAnimation()
        self.onFetchTx(mTxHash!)
        
    }
    
    func onShowErrorView(_ code: Int) {
//        print("onShowErrorView")
        self.txResultTitleLabel.text = "Transaction Failed"
        self.txResultTitleLabel.textColor = UIColor.init(hexString: "f31963")
        self.errorCode.text =  "error code : " + String(code) + "\n" + ((response?["raw_log"] as? String)!)
        self.loadingView.isHidden = true
        self.errorCardView.isHidden = false
    }
    
    
    func onTxDetailView() {
//        print("onTxDetailView")
        if (mTxType == COSMOS_MSG_TYPE_DELEGATE || mTxType == IRIS_MSG_TYPE_DELEGATE) {
            self.delegateResultView.isHidden = false
            self.loadingView.isHidden = true
            
            if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                delegateResultType.text = NSLocalizedString("tx_delegate", comment: "")
                delegateResultHash.text = mTxInfo?.txhash
                delegateResultBlock.text = mTxInfo?.height
                delegateResultTime.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
                
                delegateResultAmount.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.msg[0].value.getAmount()?.amount)!, delegateResultAmount.font, 6, self.mChain!)
                delegateResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, delegateResultFee.font, 6, self.mChain!)
                delegateResultValAddress.text = mTxInfo?.tx.value.msg[0].value.validator_address
                delegateResultValAddress.adjustsFontSizeToFitWidth = true
                delegateResultMemo.text = mTxInfo?.tx.value.memo
                
            } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                delegateResultType.text = NSLocalizedString("tx_delegate", comment: "")
                delegateResultHash.text = mTxInfo?.hash
                delegateResultBlock.text = mTxInfo?.height
                delegateResultTime.text = "-"
                
                delegateResultAmount.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.msg[0].value.delegation?.amount)!, delegateResultAmount.font, 18, self.mChain!)
                delegateResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, delegateResultFee.font, 18, self.mChain!)
                delegateResultValAddress.text = mTxInfo?.tx.value.msg[0].value.validator_addr
                delegateResultValAddress.adjustsFontSizeToFitWidth = true
                delegateResultMemo.text = mTxInfo?.tx.value.memo
            }
            
        } else if (mTxType == COSMOS_MSG_TYPE_UNDELEGATE2 || mTxType == IRIS_MSG_TYPE_UNDELEGATE) {
            self.undelegateResultView.isHidden = false
            self.loadingView.isHidden = true
            
            if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                undelegateResultType.text = NSLocalizedString("tx_undelegate", comment: "")
                undelegateResultHash.text = mTxInfo?.txhash
                undelegateResultBlock.text = mTxInfo?.height
                undelegateResultTime.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
                
                undelegateResultAmount.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.msg[0].value.getAmount()?.amount)!, undelegateResultAmount.font, 6, self.mChain!)
                undelegateResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, undelegateResultFee.font, 6, self.mChain!)
                undelegateResultValAddress.text = mTxInfo?.tx.value.msg[0].value.validator_address
                undelegateResultValAddress.adjustsFontSizeToFitWidth = true
                undelegateResultMemo.text = mTxInfo?.tx.value.memo
                
            } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                undelegateResultType.text = NSLocalizedString("tx_undelegate", comment: "")
                undelegateResultHash.text = mTxInfo?.hash
                undelegateResultBlock.text = mTxInfo?.height
                undelegateResultTime.text = "-"
                
                undelegateResultAmount.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.msg[0].value.shares_amount)!, undelegateResultAmount.font, 18, self.mChain!)
                undelegateResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, undelegateResultFee.font, 18, self.mChain!)
                undelegateResultValAddress.text = mTxInfo?.tx.value.msg[0].value.validator_addr
                undelegateResultValAddress.adjustsFontSizeToFitWidth = true
                undelegateResultMemo.text = mTxInfo?.tx.value.memo
            }
            
        } else if (mTxType == COSMOS_MSG_TYPE_REDELEGATE2) {
            self.redelegateResultView.isHidden = false
            self.loadingView.isHidden = true
            
            redelegateResultType.text = NSLocalizedString("tx_redelegate", comment: "")
            redelegateResultHash.text = mTxInfo?.txhash
            redelegateResultBlock.text = mTxInfo?.height
            redelegateResultTime.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
            
//            redelegateResultAmount.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.msg[0].value.amount?.amount)!, redelegateResultAmount.font, 6)
            redelegateResultFee.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, redelegateResultFee.font, 6)
            redelegateResultFromValAddress.text = mTxInfo?.tx.value.msg[0].value.validator_src_address
            redelegateResultFromValAddress.adjustsFontSizeToFitWidth = true
            redelegateResultToValAddress.text = mTxInfo?.tx.value.msg[0].value.validator_dst_address
            redelegateResultToValAddress.adjustsFontSizeToFitWidth = true
            redelegateResultMemo.text = mTxInfo?.tx.value.memo
            
        } else if (mTxType == COSMOS_MSG_TYPE_TRANSFER2 || mTxType == IRIS_MSG_TYPE_TRANSFER) {
            self.sendResultView.isHidden = false
            self.loadingView.isHidden = true
            
            if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                sendResultType.text = NSLocalizedString("tx_transfer", comment: "")
                sendResultHash.text = mTxInfo?.txhash
                sendResultBlock.text = mTxInfo?.height
                sendResultTime.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
                
                sendResultAmount.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.msg[0].value.getAmounts()![0].amount)!, sendResultAmount.font, 6, self.mChain!)
                sendResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, sendResultFee.font, 6, self.mChain!)
                sendResultToAddress.text = mTxInfo?.tx.value.msg[0].value.to_address
                sendResultToAddress.adjustsFontSizeToFitWidth = true
                sendResultMemo.text = mTxInfo?.tx.value.memo
                
            } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                sendResultType.text = NSLocalizedString("tx_transfer", comment: "")
                sendResultHash.text = mTxInfo?.hash
                sendResultBlock.text = mTxInfo?.height
                sendResultTime.text = "-"
                
                sendResultAmount.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.msg[0].value.inputs![0].coins[0].amount)!, sendResultAmount.font, 18, self.mChain!)
                sendResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, delegateResultFee.font, 18, self.mChain!)
                sendResultToAddress.text = mTxInfo?.tx.value.msg[0].value.outputs![0].address
                sendResultToAddress.adjustsFontSizeToFitWidth = true
                sendResultMemo.text = mTxInfo?.tx.value.memo
            }
            
            
            
        } else if (mTxType == COSMOS_MSG_TYPE_WITHDRAW_DEL || mTxType == IRIS_MSG_TYPE_WITHDRAW) {
            self.rewardResultView.isHidden = false
            self.loadingView.isHidden = true
            
            if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                rewardResultType.text = NSLocalizedString("tx_get_reward", comment: "")
                rewardResultHash.text = mTxInfo?.txhash
                rewardResultBlock.text = mTxInfo?.height
                rewardResultTime.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
                
                rewardResultFee.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, rewardResultFee.font, 6)
                var rewardValAddress = ""
                if((mTxInfo?.tx.value.msg.count)! > 1) {
                    rewardValAddress = (mTxInfo?.tx.value.msg[0].value.validator_address)! + " \n + " + String((mTxInfo?.tx.value.msg.count)! - 1)
                } else {
                    rewardValAddress = (mTxInfo?.tx.value.msg[0].value.validator_address)!
                }
                rewardResultFromValAddress.text = rewardValAddress
                rewardResultFromValAddress.adjustsFontSizeToFitWidth = true
                rewardResultMemo.text = mTxInfo?.tx.value.memo
                
            } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                rewardResultHash.text = mTxInfo?.hash
                rewardResultBlock.text = mTxInfo?.height
                rewardResultTime.text = "-"
                
                rewardResultFee.attributedText = WUtils.displayAmount((mTxInfo?.tx.value.fee.amount[0].amount)!, rewardResultFee.font, 18, self.mChain!)
                if (mTxInfo?.tx.value.msg[0].type == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                    rewardResultType.text = NSLocalizedString("tx_get_reward_all", comment: "")
                    rewardResultFromValAddress.text = NSLocalizedString("from_all_my_val", comment: "")
                    
                } else if (mTxInfo?.tx.value.msg[0].type == IRIS_MSG_TYPE_WITHDRAW) {
                    rewardResultType.text = NSLocalizedString("tx_get_reward", comment: "")
                    rewardResultFromValAddress.text = (mTxInfo?.tx.value.msg[0].value.validator_addr)!
                }
                rewardResultFromValAddress.adjustsFontSizeToFitWidth = true
                rewardResultMemo.text = mTxInfo?.tx.value.memo
            }
            
        } else if (mTxType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
            self.addressChangeResultView.isHidden = false
            self.loadingView.isHidden = true
            
            addressChangeResultType.text = NSLocalizedString("tx_change_reward_address", comment: "")
            addressChangeResultHash.text = mTxInfo?.txhash
            addressChangeResultBlock.text = mTxInfo?.height
            addressChangeResultTime.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
            
            addressChangeResultFee.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, addressChangeResultFee.font, 6)
            addressChangeResultAddress.text = mTxInfo?.tx.value.msg[0].value.withdraw_address
            addressChangeResultAddress.adjustsFontSizeToFitWidth = true
            addressChangeResultMemo.text = mTxInfo?.tx.value.memo
            
        }  else if (mTxType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
            self.reInvestResultView.isHidden = false
            self.loadingView.isHidden = true
            
            reInvestResultType.text = NSLocalizedString("tx_reinvest", comment: "")
            reInvestResultHash.text = mTxInfo?.txhash
            reInvestResultTime.text = mTxInfo?.height
            reInvestResultBlock.text = WUtils.txTimetoString(input: (mTxInfo?.txTime)!)
            
//            reInvestResultDelegateAmount.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.msg[1].value.amount?.amount)!, reInvestResultDelegateAmount.font, 6)
            reInvestResultFee.attributedText = WUtils.displayAmout((mTxInfo?.tx.value.fee.amount[0].amount)!, reInvestResultFee.font, 6)
            reInvestValidatorAddress.text = mTxInfo?.tx.value.msg[0].value.validator_address
            reInvestValidatorAddress.adjustsFontSizeToFitWidth = true
            reInvestResultMemo.text = mTxInfo?.tx.value.memo
        }

        self.actionLayer.isHidden = false
        self.dismissBtn.isHidden = true
    }

    
    
    @IBAction func onClickDismiss(_ sender: UIButton) {
        self.onStartMainTab()
    }
    
    @IBAction func onClickExplorer(_ sender: UIButton) {
        if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            guard let url = URL(string: "https://www.mintscan.io/txs/" + mTxInfo!.txhash) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            guard let url = URL(string: "https://irishub.mintscan.io/txs/" + mTxInfo!.hash) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
        }
    }
    
    @IBAction func onClickShare(_ sender: UIButton) {
        if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let text = "https://www.mintscan.io/txs/" + mTxInfo!.txhash
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
            
        } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let text = "https://irishub.mintscan.io/txs/" + mTxInfo!.hash
            let textToShare = [ text ]
            let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
            activityViewController.popoverPresentationController?.sourceView = self.view
            self.present(activityViewController, animated: true, completion: nil)
        }
        
    }
    
    @IBAction func onClickOK(_ sender: UIButton) {
        self.onStartMainTab()
    }
    
    func onShowMoreWait() {
        let noticeAlert = UIAlertController(title: NSLocalizedString("more_wait_title", comment: ""), message: NSLocalizedString("more_wait_msg", comment: ""), preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
            self.onStartMainTab()
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("wait", comment: ""), style: .default, handler: { _ in
            self.fetchCnt = 10
            self.onFetchTx(self.mTxHash!)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    @objc func dismissAlertController(){
        self.dismiss(animated: true, completion: nil)
    }
    
    var fetchCnt = 10
    func onFetchTx(_ txHash: String) {
        var url = ""
        if (self.mChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_TX + txHash
            
        } else if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_TX + txHash
        }
        print("url ", url)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if(SHOW_LOG) { print("onFetchTx ", res) }
                guard let info = res as? [String : Any], info["error"] == nil else {
                    self.fetchCnt = self.fetchCnt - 1
                    if(self.fetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                            self.onFetchTx(txHash)
                        })
                    } else {
                        self.onShowMoreWait()
                    }
                    return
                }
                print("here")
                self.mTxInfo = TxInfo.init(info)
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchTx ", error)
                }
                if (self.mChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    self.fetchCnt = self.fetchCnt - 1
                    if(self.fetchCnt > 0) {
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(6000), execute: {
                            self.onFetchTx(txHash)
                        })
                    } else {
                        self.onShowMoreWait()
                    }
                }
                return
            }
            self.onTxDetailView()
        }
        
    }
}
