//
//  StepDelegateFeeViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepFeeViewController: BaseViewController {

    @IBOutlet weak var feeTypeCardView: CardView!
    @IBOutlet weak var feeTypeDenomLabel: UILabel!
    @IBOutlet weak var minFeeCardView: CardView!
    @IBOutlet weak var minFeeAmountLabel: UILabel!
    @IBOutlet weak var minFeePriceLabel: UILabel!
    @IBOutlet weak var rateFeeCardView: CardView!
    @IBOutlet weak var rateFeeGasAmountLabel: UILabel!
    @IBOutlet weak var rateFeeGasRateLabel: UILabel!
    @IBOutlet weak var rateFeeAmountLabel: UILabel!
    @IBOutlet weak var rateFeePriceLabel: UILabel!
    @IBOutlet weak var feeSlider: UISlider!
    @IBOutlet weak var feesLabels: UIStackView!
    @IBOutlet weak var speedImg: UIImageView!
    @IBOutlet weak var speedMsg: UILabel!
    
    
    @IBOutlet weak var beforeBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var rewardAllGasAmounts: Array<NSDecimalNumber>!
    var feeAmount   = NSDecimalNumber.zero
    var feeCoin:Coin!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, feeTypeDenomLabel)
        feeSlider.tintColor = WUtils.getChainColor(pageHolderVC.chainType!)
        
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            rewardAllGasAmounts = WUtils.getGasAmountForRewards()
            
            let gesture = UITapGestureRecognizer(target: self, action:  #selector(self.tapFeeType(sender:)))
            self.feeTypeCardView.addGestureRecognizer(gesture)
            
            _ = updateView(0)
            self.speedImg.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.imageTap (_:))))
            self.speedImg.isUserInteractionEnabled = true
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            self.minFeeCardView.isHidden = true
            self.rateFeeCardView.isHidden = false
            
            self.feeSlider.isHidden = true
            self.feesLabels.isHidden = true
            
            self.speedImg.image = UIImage.init(named: "feeImg")
            self.speedMsg.text = NSLocalizedString("fee_speed_iris_title", comment: "")
            
            let gasAmount = getEstimateGasAmount()
            let gasRate = NSDecimalNumber.init(string: GAS_FEE_RATE_IRIS_AVERAGE)
            self.rateFeeGasAmountLabel.text = gasAmount.stringValue
            self.rateFeeGasRateLabel.attributedText = WUtils.displayGasRate(gasRate, font: rateFeeGasRateLabel.font, 6)
            feeAmount = gasAmount.multiplying(byPowerOf10: 18).multiplying(by: gasRate, withBehavior: WUtils.handler0)
            self.rateFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, rateFeeAmountLabel.font, 3, pageHolderVC.chainType!)
            self.rateFeePriceLabel.attributedText = WUtils.dpIrisValue(feeAmount, BaseData.instance.getLastPrice(), rateFeePriceLabel.font)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            self.minFeeCardView.isHidden = false
            self.rateFeeCardView.isHidden = true
            
            self.feeSlider.isHidden = true
            self.feesLabels.isHidden = true
            
            self.speedImg.image = UIImage.init(named: "feeImg")
            self.speedMsg.text = NSLocalizedString("fee_speed_bnb_title", comment: "")
            
            feeAmount = WUtils.stringToDecimalNoLocale(GAS_FEE_BNB_TRANSFER)
            self.minFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, minFeeAmountLabel.font, 0, 8)
            self.minFeePriceLabel.attributedText  = WUtils.dpBnbValue(feeAmount, BaseData.instance.getLastPrice(), minFeePriceLabel.font)
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            self.minFeeCardView.isHidden = false
            self.rateFeeCardView.isHidden = true
            
            self.feeSlider.isHidden = true
            self.feesLabels.isHidden = true
            
            self.speedImg.image = UIImage.init(named: "feeImg")
            self.speedMsg.text = NSLocalizedString("fee_speed_iov_title", comment: "")
            
            feeAmount = WUtils.stringToDecimalNoLocale(GAS_FEE_IOV_TRANSFER)
            self.minFeeAmountLabel.attributedText = WUtils.displayAmount2(feeAmount.stringValue, minFeeAmountLabel.font, 0, 9)
            self.minFeePriceLabel.attributedText  = WUtils.dpValue(NSDecimalNumber.zero, minFeePriceLabel.font)
        }
        
    }
    
    @objc func imageTap (_ sender: UITapGestureRecognizer) {
        var titleGas = ""
        var msgGas = ""
        if(feeSlider.value == 0) {
            titleGas = NSLocalizedString("fee_description_title_0", comment: "")
            msgGas = NSLocalizedString("fee_description_msg_0", comment: "")
            
        } else if (feeSlider.value == 1) {
            titleGas = NSLocalizedString("fee_description_title_1", comment: "")
            msgGas = NSLocalizedString("fee_description_msg_1", comment: "")
            
        } else {
            titleGas = NSLocalizedString("fee_description_title_2", comment: "")
            msgGas = NSLocalizedString("fee_description_msg_2", comment: "")
            
        }
        
        let noticeAlert = UIAlertController(title: titleGas, message: msgGas, preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let attributedMessage: NSMutableAttributedString = NSMutableAttributedString(
            string: msgGas,
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.systemFont(ofSize: 12.0)
            ]
        )
        noticeAlert.setValue(attributedMessage, forKey: "attributedMessage")
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { [weak noticeAlert] (_) in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }

    @IBAction func onSlideChanged(_ sender: UISlider) {
    }
    
    @IBAction func onSlideEnd(_ sender: UISlider) {
        if(sender.value < 0.5) {
            sender.value = 0.0
            _ = updateView(0)
        } else if (sender.value < 1.5) {
            sender.value = 1.0
            _ = updateView(1)
        } else {
            sender.value = 2.0
            _ = updateView(2)
        }
        
    }
    
    @objc func tapFeeType(sender : UITapGestureRecognizer) {
        self.onShowToast(NSLocalizedString("error_only_fee_with_atom", comment: ""))
    }
    
    @IBAction func onClickBefore(_ sender: Any) {
        self.beforeBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    func updateView(_ position: Int) -> Bool {
        var available   = NSDecimalNumber.zero
        var toSpend     = NSDecimalNumber.zero
        feeAmount       = NSDecimalNumber.zero
        
        if (position == 0) {
            self.minFeeCardView.isHidden = false
            self.rateFeeCardView.isHidden = true
            self.speedImg.image = UIImage.init(named: "bycicle")
            self.speedMsg.text = NSLocalizedString("fee_speed_title_0", comment: "")
            
            feeAmount = NSDecimalNumber.init(string: "1")
            self.minFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, minFeeAmountLabel.font, 6, pageHolderVC.chainType!)
            
        } else if (position == 1) {
            self.minFeeCardView.isHidden = true
            self.rateFeeCardView.isHidden = false
            self.speedImg.image = UIImage.init(named: "car")
            self.speedMsg.text = NSLocalizedString("fee_speed_title_1", comment: "")
            
            let gasAmount = getEstimateGasAmount()
            let gasRate = NSDecimalNumber.init(string: String(GAS_FEE_RATE_LOW))
            self.rateFeeGasAmountLabel.text = gasAmount.stringValue
            self.rateFeeGasRateLabel.attributedText = WUtils.displayGasRate(gasRate, font: rateFeeGasRateLabel.font, 4)

            feeAmount = gasAmount.multiplying(by: gasRate, withBehavior: WUtils.handler6)
            self.rateFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, rateFeeAmountLabel.font, 6, pageHolderVC.chainType!)
            
        } else if (position == 2) {
            self.minFeeCardView.isHidden = true
            self.rateFeeCardView.isHidden = false
            self.speedImg.image = UIImage.init(named: "roket")
            self.speedMsg.text = NSLocalizedString("fee_speed_title_2", comment: "")
            
            let gasAmount = getEstimateGasAmount()
            let gasRate = NSDecimalNumber.init(string: String(GAS_FEE_RATE_AVERAGE))
            self.rateFeeGasAmountLabel.text = gasAmount.stringValue
            self.rateFeeGasRateLabel.attributedText = WUtils.displayGasRate(gasRate, font: rateFeeGasRateLabel.font, 3)
            
            feeAmount = gasAmount.multiplying(by: gasRate, withBehavior: WUtils.handler6)
            self.rateFeeAmountLabel.attributedText = WUtils.displayAmount(feeAmount.stringValue, rateFeeAmountLabel.font, 6, pageHolderVC.chainType!)
        }
        
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (TESTNET) {
                available = WUtils.getTokenAmount(pageHolderVC.mBalances, "muon");
            } else {
                available = WUtils.getTokenAmount(pageHolderVC.mBalances, COSMOS_MAIN_DENOM);
            }
            toSpend = getSpendAmount()
            if(toSpend.adding(feeAmount).compare(available).rawValue > 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return false
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            available = WUtils.getTokenAmount(pageHolderVC.mBalances, KAVA_MAIN_DENOM);
            if (pageHolderVC.mKavaSendDenom == KAVA_MAIN_DENOM) {
                if(toSpend.adding(feeAmount).compare(available).rawValue > 0) {
                    self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                    return false
                }
            } else {
                if(feeAmount.compare(available).rawValue > 0) {
                    self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                    return false
                }
            }
        }
        self.minFeePriceLabel.attributedText = WUtils.dpAtomValue(feeAmount, BaseData.instance.getLastPrice(), minFeePriceLabel.font)
        self.rateFeePriceLabel.attributedText = WUtils.dpAtomValue(feeAmount, BaseData.instance.getLastPrice(), rateFeePriceLabel.font)
        return true;
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (NSDecimalNumber.init(string: "100000").compare(feeAmount).rawValue < 0) {return}
            if (self.updateView(Int(feeSlider!.value))) {
                feeCoin = Coin.init(COSMOS_MAIN_DENOM, feeAmount.stringValue)
                var fee = Fee.init()
                let estGas = getEstimateGasAmount().stringValue
                fee.gas = estGas
                
                var estAmount: Array<Coin> = Array<Coin>()
                estAmount.append(feeCoin)
                fee.amount = estAmount
                
                pageHolderVC.mFee = fee
                
                self.beforeBtn.isUserInteractionEnabled = false
                self.nextBtn.isUserInteractionEnabled = false
                pageHolderVC.onNextPage()
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (NSDecimalNumber.init(string: "1000000000000000000").compare(feeAmount).rawValue < 0) {return}
            feeCoin = Coin.init(IRIS_MAIN_DENOM, feeAmount.stringValue)
            var fee = Fee.init()
            let estGas = getEstimateGasAmount().stringValue
            fee.gas = estGas
            
            var estAmount: Array<Coin> = Array<Coin>()
            estAmount.append(feeCoin)
            fee.amount = estAmount
            
            pageHolderVC.mFee = fee
            
            self.beforeBtn.isUserInteractionEnabled = false
            self.nextBtn.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            //Notice! useless but make format!
            feeCoin = Coin.init(BNB_MAIN_DENOM, feeAmount.stringValue)
            var fee = Fee.init()
            let estGas = getEstimateGasAmount().stringValue
            fee.gas = estGas
            
            var estAmount: Array<Coin> = Array<Coin>()
            estAmount.append(feeCoin)
            fee.amount = estAmount
            
            pageHolderVC.mFee = fee
            
            self.beforeBtn.isUserInteractionEnabled = false
            self.nextBtn.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            if(NSDecimalNumber.init(string: "100000").compare(feeAmount).rawValue < 0) {return}
            if (self.updateView(Int(feeSlider!.value))) {
                feeCoin = Coin.init(KAVA_MAIN_DENOM, feeAmount.stringValue)
                var fee = Fee.init()
                let estGas = getEstimateGasAmount().stringValue
                fee.gas = estGas
                
                var estAmount: Array<Coin> = Array<Coin>()
                estAmount.append(feeCoin)
                fee.amount = estAmount
                
                pageHolderVC.mFee = fee
                
                self.beforeBtn.isUserInteractionEnabled = false
                self.nextBtn.isUserInteractionEnabled = false
                pageHolderVC.onNextPage()
            }
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            //TODO NOTICE NO need Fee set!!;
            feeCoin = Coin.init(IOV_MAIN_DENOM, feeAmount.multiplying(byPowerOf10: 9).stringValue)
            var fee = Fee.init()
            let estGas = getEstimateGasAmount().stringValue
            fee.gas = estGas
            
            var estAmount: Array<Coin> = Array<Coin>()
            estAmount.append(feeCoin)
            fee.amount = estAmount
            
            pageHolderVC.mFee = fee
            
            self.beforeBtn.isUserInteractionEnabled = false
            self.nextBtn.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        }
    }
    
    override func enableUserInteraction() {
        self.beforeBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    
    
    func getEstimateGasAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            if (pageHolderVC.mType == COSMOS_MSG_TYPE_DELEGATE) {
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_REDELE))
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_TRANSFER2 || pageHolderVC.mType == KAVA_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = rewardAllGasAmounts[pageHolderVC.mRewardTargetValidators.count-1]
                
            } else if (pageHolderVC.mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_REINVEST))
                
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_MID))
            if (pageHolderVC.mType == IRIS_MSG_TYPE_DELEGATE) {
                
            } else if (pageHolderVC.mType == IRIS_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_SEND))
                
            } else if (pageHolderVC.mType == IRIS_MSG_TYPE_WITHDRAW || pageHolderVC.mType == IRIS_MSG_TYPE_WITHDRAW_ALL) {
                result = (NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_MUX).multiplying(by: NSDecimalNumber.init(value: pageHolderVC.mRewardTargetValidators.count))).adding(NSDecimalNumber.init(string: GAS_FEE_AMOUNT_IRIS_REWARD_BASE))
                
            } else if (pageHolderVC.mType == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_REWARD_BASE))
                
            } else if (pageHolderVC.mType == IRIS_MSG_TYPE_REDELEGATE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_REDELEGATE))
                
            } else if (pageHolderVC.mType == IRIS_MSG_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_MID))
                           
            } else {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_IRIS_REDELEGATE))
            }
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_BINANCE_TEST) {
            //Notice! useless but make format!
            result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
        
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN ||
            pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_AVERAGE))
            if (pageHolderVC.mType == COSMOS_MSG_TYPE_DELEGATE) {
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_REDELEGATE))
                
            } else if (pageHolderVC.mType == KAVA_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_SEND))
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                
            } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                //only one
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_REWARD))
                
            } else if (pageHolderVC.mType == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_REINVEST))
                
            } else if (pageHolderVC.mType == KAVA_MSG_TYPE_CREATE_CDP ||
                        pageHolderVC.mType == KAVA_MSG_TYPE_DEPOSIT_CDP ||
                        pageHolderVC.mType == KAVA_MSG_TYPE_WITHDRAW_CDP ||
                        pageHolderVC.mType == KAVA_MSG_TYPE_DRAWDEBT_CDP ||
                        pageHolderVC.mType == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_FEE_AMOUNT_CDP))
            }
            
        }
        return result
    }
    
    func getSpendAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (pageHolderVC.mType == COSMOS_MSG_TYPE_DELEGATE) {
            result = WUtils.stringToDecimal(pageHolderVC.mToDelegateAmount!.amount)
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_TRANSFER2 || pageHolderVC.mType == KAVA_MSG_TYPE_TRANSFER) {
            result = WUtils.stringToDecimal(pageHolderVC.mToSendAmount[0].amount)
            
        } else if (pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
        } else if(pageHolderVC.mType == COSMOS_MSG_TYPE_REDELEGATE2) {
        } else if(pageHolderVC.mType == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
        }
        return result

    }
}
