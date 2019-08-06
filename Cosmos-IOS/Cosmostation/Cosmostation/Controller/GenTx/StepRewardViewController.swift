//
//  StepRewardViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepRewardViewController: BaseViewController {

    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var rewardAmountLabel: UILabel!
    @IBOutlet weak var rewardDenomLabel: UILabel!
    @IBOutlet weak var rewardFromLabel: UILabel!
    
    @IBOutlet weak var rewardToAddressTitle: UILabel!
    @IBOutlet weak var rewardToAddressLabel: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var mFetchCnt = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.userChain!, rewardDenomLabel)
        
        if(pageHolderVC.mRewardTargetValidators.count == 16) {
            self.onShowToast(NSLocalizedString("reward_claim_top_16", comment: ""))
        }
        
        self.loadingImg.onStartAnimation()
        self.onFetchRewardsInfoData()
    }
    
    func onFetchRewardsInfoData()  {
        if(self.mFetchCnt > 0)  {
            return
        }
        if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            pageHolderVC.mRewardList.removeAll()
            mFetchCnt = 1 + pageHolderVC.mRewardTargetValidators.count;
            for val in pageHolderVC.mRewardTargetValidators {
                self.onFetchEachReward(pageHolderVC.mAccount!.account_address, val.operator_address)
            }
        } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mFetchCnt = 2
            self.onFetchIrisReward(pageHolderVC.mAccount!)
        }
        self.onFetchRewardAddress(pageHolderVC.mAccount!.account_address)
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if(mFetchCnt <= 0) {
            updateView()
        }
    }
    
    func updateView() {
        if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            rewardAmountLabel.attributedText = WUtils.displayAllAtomReward(pageHolderVC.mRewardList, rewardAmountLabel.font, 6)
        } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            var selectedRewardSum = NSDecimalNumber.zero
            for delegation in pageHolderVC.mIrisRewards!.delegations {
                for validator in pageHolderVC.mRewardTargetValidators {
                    if (validator.operator_address == delegation.validator && delegation.reward[0].denom == IRIS_MAIN_DENOM) {
                        selectedRewardSum = selectedRewardSum.adding(NSDecimalNumber.init(string: delegation.reward[0].amount))
                    }
                }
            }
            print("selectedRewardSum ", selectedRewardSum)
            rewardAmountLabel.attributedText = WUtils.displayAmount(selectedRewardSum.stringValue, rewardAmountLabel.font, 18, pageHolderVC.userChain!)
        }
        
        var monikers = ""
        for validator in pageHolderVC.mRewardTargetValidators {
            if(monikers.count > 0) {
                monikers = monikers + ",   " + validator.description.moniker
            } else {
                monikers = validator.description.moniker
            }
        }
        rewardFromLabel.text = monikers
        
        rewardToAddressLabel.text = pageHolderVC.mRewardAddress
        rewardToAddressLabel.adjustsFontSizeToFitWidth = true
        if (pageHolderVC.mAccount?.account_address == pageHolderVC.mRewardAddress) {
            self.rewardToAddressTitle.isHidden = true
            self.rewardToAddressLabel.isHidden = true
        } else {
            self.rewardToAddressTitle.isHidden = false
            self.rewardToAddressLabel.isHidden = false
        }
        
        self.loadingImg.isHidden = true
        self.controlLayer.isHidden = false
        self.cardView.isHidden = false
        
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
        
    }
    
    override func enableUserInteraction() {
        self.cancelBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    func onFetchEachReward(_ accountAddr: String, _ validatorAddr:String) {
        let url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawRewards = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return;
                }
                let reward = Reward.init()
                reward.reward_v_address = validatorAddr
                for rawReward in rawRewards {
                    reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.pageHolderVC.mRewardList.append(reward)
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchEachReward ", error)
                }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIrisReward(_ account: Account) {
        let url = IRIS_LCD_URL_REWARD + account.account_address + IRIS_LCD_URL_REWARD_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let irisRewards = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                self.pageHolderVC.mIrisRewards = IrisRewards(irisRewards as! [String : Any])
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchIrisReward ", error)
                }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchRewardAddress(_ accountAddr: String) {
        var url = ""
        if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_REWARD_ADDRESS + accountAddr + CSS_LCD_URL_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_REWARD_ADDRESS + accountAddr + IRIS_LCD_URL_REWARD_ADDRESS_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseString { (response) in
            switch response.result {
            case .success(let res):
                guard let address = res as? String else {
                    self.onFetchFinished()
                    return;
                }
                self.pageHolderVC.mRewardAddress = address.replacingOccurrences(of: "\"", with: "")
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchRewardAddress ", error)
                }
            }
            self.onFetchFinished()
        }
        
    }
    
}
