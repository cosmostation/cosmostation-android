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
        WUtils.setDenomTitle(pageHolderVC.chainType!, rewardDenomLabel)
        
        if (pageHolderVC.mRewardTargetValidators.count == 16) {
            self.onShowToast(NSLocalizedString("reward_claim_top_16", comment: ""))
        }
        
        self.loadingImg.onStartAnimation()
        self.onFetchRewardsInfoData()
    }
    
    func onFetchRewardsInfoData()  {
        if(self.mFetchCnt > 0)  {
            return
        }
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            pageHolderVC.mRewardList.removeAll()
            mFetchCnt = 1 + pageHolderVC.mRewardTargetValidators.count;
            for val in pageHolderVC.mRewardTargetValidators {
                self.onFetchEachReward(pageHolderVC.mAccount!.account_address, val.operator_address)
            }
            self.onFetchRewardAddress(pageHolderVC.mAccount!.account_address)
            
        } else if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.AKASH_MAIN ||
                    pageHolderVC.chainType! == ChainType.COSMOS_TEST || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            mFetchCnt = 2
            self.onFetchRewards(pageHolderVC.mAccount!.account_address)
            self.onFetchRewardAddressV1(pageHolderVC.mAccount!.account_address)
            
        }
        
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            updateView()
        }
    }
    
    func updateView() {
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.IRIS_MAIN || pageHolderVC.chainType! == ChainType.AKASH_MAIN ||
                pageHolderVC.chainType! == ChainType.COSMOS_TEST || pageHolderVC.chainType! == ChainType.IRIS_TEST) {
            var selectedRewardSum = NSDecimalNumber.zero
            for validator in pageHolderVC.mRewardTargetValidators_V1 {
                if let reward = BaseData.instance.mMyReward_V1.filter({ $0.validator_address == validator.operator_address}).first {
                    selectedRewardSum = selectedRewardSum.adding(reward.getRewardByDenom(WUtils.getMainDenom(pageHolderVC.chainType)))
                }
            }
            rewardAmountLabel.attributedText = WUtils.displayAmount2(selectedRewardSum.stringValue, rewardAmountLabel.font, 6, 6)
            
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators_V1 {
                if (monikers.count > 0) {
                    monikers = monikers + ",   " + (validator.description?.moniker)!
                } else {
                    monikers = (validator.description?.moniker)!
                }
            }
            rewardFromLabel.text = monikers
            
        } else {
            if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                rewardAmountLabel.attributedText = WUtils.dpRewards(pageHolderVC.mRewardList, rewardAmountLabel.font, 6, KAVA_MAIN_DENOM, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                rewardAmountLabel.attributedText = WUtils.dpRewards(pageHolderVC.mRewardList, rewardAmountLabel.font, 6, BAND_MAIN_DENOM, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                rewardAmountLabel.attributedText = WUtils.dpRewards(pageHolderVC.mRewardList, rewardAmountLabel.font, 6, SECRET_MAIN_DENOM, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
                rewardAmountLabel.attributedText = WUtils.dpRewards(pageHolderVC.mRewardList, rewardAmountLabel.font, 6, IOV_MAIN_DENOM, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
                rewardAmountLabel.attributedText = WUtils.dpRewards(pageHolderVC.mRewardList, rewardAmountLabel.font, 6, IOV_TEST_DENOM, pageHolderVC.chainType!)
                
            } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                rewardAmountLabel.attributedText = WUtils.dpRewards(pageHolderVC.mRewardList, rewardAmountLabel.font, 6, CERTIK_MAIN_DENOM, pageHolderVC.chainType!)
                
            }
            
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators {
                if (monikers.count > 0) {
                    monikers = monikers + ",   " + validator.description.moniker
                } else {
                    monikers = validator.description.moniker
                }
            }
            rewardFromLabel.text = monikers
        }
        
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
        var url: String?
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_REWARD_FROM_VAL + accountAddr + KAVA_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_FROM_VAL + accountAddr + KAVA_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_REWARD_FROM_VAL + accountAddr + BAND_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_REWARD_FROM_VAL + accountAddr + SECRET_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_REWARD_FROM_VAL + accountAddr + IOV_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_FROM_VAL + accountAddr + IOV_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_REWARD_FROM_VAL + accountAddr + CERTIK_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REWARD_FROM_VAL + accountAddr + CERTIK_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
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
                if(SHOW_LOG) { print("onFetchEachReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchRewardAddress(_ accountAddr: String) {
        var url = ""
        if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_REWARD_ADDRESS + accountAddr + KAVA_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_ADDRESS + accountAddr + KAVA_TEST_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_REWARD_ADDRESS + accountAddr + BAND_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_REWARD_ADDRESS + accountAddr + SECRET_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_REWARD_ADDRESS + accountAddr + IOV_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_ADDRESS + accountAddr + IOV_TEST_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_REWARD_ADDRESS + accountAddr + CERTIK_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REWARD_ADDRESS + accountAddr + CERTIK_TEST_REWARD_ADDRESS_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let address = responseData.object(forKey: "result") as? String else {
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
    
    
    
    
    func onFetchRewards(_ address: String) {
        let url = BaseNetWork.rewardsUrl(pageHolderVC.chainType!, address)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let rewards = responseData.object(forKey: "rewards") as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for reward in rewards {
                    BaseData.instance.mMyReward_V1.append(Reward_V1(reward))
                }
                self.onFetchFinished()
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchRewards ", error) }
                self.onFetchFinished()
            }
        }
    }
    
    func onFetchRewardAddressV1(_ address: String) {
        let url = BaseNetWork.rewardAddressUrl(pageHolderVC.chainType!, address)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let withdraw_address = responseData.object(forKey: "withdraw_address") as? String else {
                    self.onFetchFinished()
                    return;
                }
                self.pageHolderVC.mRewardAddress = withdraw_address.replacingOccurrences(of: "\"", with: "")
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchRewardAddressV1 ", error)
                }
            }
            self.onFetchFinished()
        }
    }
    
}
