//
//  ReInvestAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/06/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class ReInvestAmountViewController: BaseViewController {

    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var rewardAmountLabel: UILabel!
    @IBOutlet weak var rewardDenomLabel: UILabel!
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, rewardDenomLabel)
        
        self.loadingImg.onStartAnimation()
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            self.onFetchReward(pageHolderVC.mAccount!.account_address, pageHolderVC.mTargetValidator!.operator_address)
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            self.onFetchIrisReward(pageHolderVC.mAccount!)
        }
    }
    
    @IBAction func onClickCancel(_ sender: Any) {
        cancelBtn.isUserInteractionEnabled = false
        nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: Any) {
        cancelBtn.isUserInteractionEnabled = false
        nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    override func enableUserInteraction() {
        self.cancelBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    func updateView() {
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN && self.pageHolderVC.mReinvestReward != nil) {
            rewardAmountLabel.attributedText = WUtils.displayAmount(pageHolderVC.mReinvestReward!.amount, rewardAmountLabel.font, 6, pageHolderVC.chainType!)
            validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
            
            self.loadingImg.isHidden = true
            self.controlLayer.isHidden = false
            self.cardView.isHidden = false
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            rewardAmountLabel.attributedText = WUtils.displayAmount(pageHolderVC.mReinvestReward!.amount, rewardAmountLabel.font, 18, pageHolderVC.chainType!)
            validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
            
            self.loadingImg.isHidden = true
            self.controlLayer.isHidden = false
            self.cardView.isHidden = false
            
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN && self.pageHolderVC.mReinvestReward != nil) {
            rewardAmountLabel.attributedText = WUtils.displayAmount(pageHolderVC.mReinvestReward!.amount, rewardAmountLabel.font, 6, pageHolderVC.chainType!)
            validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
            
            self.loadingImg.isHidden = true
            self.controlLayer.isHidden = false
            self.cardView.isHidden = false
            
        } else {
            pageHolderVC.onBeforePage()
        }
    }

    func onFetchReward(_ accountAddr: String, _ validatorAddr:String) {
        var url: String?
        if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_REWARD_FROM_VAL + accountAddr + KAVA_REWARD_FROM_VAL_TAIL + validatorAddr
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
//                    guard let responseData = res as? NSDictionary,
//                        let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
//                        self.onFetchFinished()
//                        return;
//                    }
                    //TODO rollback cosmos-hub2
                    guard let rawRewards = res as? Array<NSDictionary> else {
                        self.updateView()
                        return;
                    }
                    for rawReward in rawRewards {
                        if let atomReward = rawReward.object(forKey: "denom") as? String, atomReward == COSMOS_MAIN_DENOM {
                            var coin = Coin(rawReward as! [String : Any])
                            coin.amount = NSDecimalNumber.init(string: coin.amount).rounding(accordingToBehavior: WUtils.handlerdown0).stringValue
                            self.pageHolderVC.mReinvestReward = coin
                        }
                    }
                    
                } else if (self.pageHolderVC.chainType! == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
                    guard let responseData = res as? NSDictionary,
                        let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.updateView()
                        return;
                    }
                    for rawReward in rawRewards {
                        if let atomReward = rawReward.object(forKey: "denom") as? String, atomReward == KAVA_MAIN_DENOM {
                            var coin = Coin(rawReward as! [String : Any])
                            coin.amount = NSDecimalNumber.init(string: coin.amount).rounding(accordingToBehavior: WUtils.handlerdown0).stringValue
                            self.pageHolderVC.mReinvestReward = coin
                        }
                    }
                }
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchEachReward ", error) }
            }
            self.updateView()
        }
    }
    
    func onFetchIrisReward(_ account: Account) {
        let url = IRIS_LCD_URL_REWARD + account.account_address + IRIS_LCD_URL_REWARD_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("res ", res)
                guard let irisRewards = res as? NSDictionary else {
                    self.updateView()
                    return
                }
                let rewards = IrisRewards(irisRewards as! [String : Any])
                self.pageHolderVC.mReinvestReward = rewards.getPerValRewardCoin(valOp: self.pageHolderVC.mTargetValidator!.operator_address)
                
            case .failure(let error):
                if(SHOW_LOG) { print("onFetchIrisReward ", error) }
            }
            self.updateView()
        }
        
    }
    
}
