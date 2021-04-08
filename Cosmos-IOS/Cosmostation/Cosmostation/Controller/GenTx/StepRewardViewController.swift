//
//  StepRewardViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import GRPC
import NIO

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
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            mFetchCnt = 2
            self.onFetchRewards_gRPC(pageHolderVC.mAccount!.account_address)
            self.onFetchRewardAddress_gRPC(pageHolderVC.mAccount!.account_address)
            
        } else {
            pageHolderVC.mRewards.removeAll()
            mFetchCnt = 1 + pageHolderVC.mRewardTargetValidators.count;
            for val in pageHolderVC.mRewardTargetValidators {
                self.onFetchEachReward(pageHolderVC.mAccount!.account_address, val.operator_address)
            }
            self.onFetchRewardAddress(pageHolderVC.mAccount!.account_address)
        }
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            updateView()
        }
    }
    
    func updateView() {
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            var selectedRewardSum = NSDecimalNumber.zero
            for validator in pageHolderVC.mRewardTargetValidators_gRPC {
                let amount = BaseData.instance.getReward(WUtils.getMainDenom(pageHolderVC.chainType), validator.operatorAddress)
                selectedRewardSum = selectedRewardSum.adding(amount)
            }
            rewardAmountLabel.attributedText = WUtils.displayAmount2(selectedRewardSum.stringValue, rewardAmountLabel.font, 6, 6)
            
            var monikers = ""
            for validator in pageHolderVC.mRewardTargetValidators_gRPC {
                if (monikers.count > 0) {
                    monikers = monikers + ",   " + validator.description_p.moniker
                } else {
                    monikers = validator.description_p.moniker
                }
            }
            rewardFromLabel.text = monikers
            
        } else {
            var selectedRewardSum = NSDecimalNumber.zero
            pageHolderVC.mRewards.forEach { coin in
                if (coin.denom == WUtils.getMainDenom(pageHolderVC.chainType!)) {
                    selectedRewardSum = selectedRewardSum.adding(WUtils.plainStringToDecimal(coin.amount))
                }
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
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_REWARD_FROM_VAL + accountAddr + BAND_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_REWARD_FROM_VAL + accountAddr + SECRET_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_REWARD_FROM_VAL + accountAddr + IOV_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_REWARD_FROM_VAL + accountAddr + CERTIK_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_REWARD_FROM_VAL + accountAddr + SENTINEL_REWARD_FROM_VAL_TAIL + validatorAddr
        }
        else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_FROM_VAL + accountAddr + KAVA_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_FROM_VAL + accountAddr + IOV_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
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
                for rawReward in rawRewards {
                    self.pageHolderVC.mRewards.append(Coin(rawReward))
                }
                
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
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_REWARD_ADDRESS + accountAddr + BAND_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_REWARD_ADDRESS + accountAddr + SECRET_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_REWARD_ADDRESS + accountAddr + IOV_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_REWARD_ADDRESS + accountAddr + CERTIK_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_REWARD_ADDRESS + accountAddr + SENTINEL_REWARD_ADDRESS_TAIL
        }
        else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_ADDRESS + accountAddr + KAVA_TEST_REWARD_ADDRESS_TAIL
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_ADDRESS + accountAddr + IOV_TEST_REWARD_ADDRESS_TAIL
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
    
    
    
    
    func onFetchRewards_gRPC(_ address: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Distribution_V1beta1_QueryDelegationTotalRewardsRequest.with {
                $0.delegatorAddress = address
            }
            do {
                let response = try Cosmos_Distribution_V1beta1_QueryClient(channel: channel).delegationTotalRewards(req).response.wait()
//            print("onFetchgRPCRewards: \(response.rewards.count)")
                response.rewards.forEach { reward in
                    BaseData.instance.mMyReward_gRPC.append(reward)
                }
            } catch {
                print("onFetchgRPCRewards failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchRewardAddress_gRPC(_ address: String) {
        DispatchQueue.global().async {
            var responseAddress = ""
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.pageHolderVC.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Distribution_V1beta1_QueryDelegatorWithdrawAddressRequest.with {
                $0.delegatorAddress = address
            }
            do {
                let response = try Cosmos_Distribution_V1beta1_QueryClient(channel: channel).delegatorWithdrawAddress(req).response.wait()
                responseAddress = response.withdrawAddress.replacingOccurrences(of: "\"", with: "")
            } catch {
                print("onFetchRedelegation_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.pageHolderVC.mRewardAddress = responseAddress
                self.onFetchFinished()
            });
        }
    }
    
}
