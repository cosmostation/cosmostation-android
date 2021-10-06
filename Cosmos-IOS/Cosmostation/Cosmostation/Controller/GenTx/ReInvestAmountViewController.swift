//
//  ReInvestAmountViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/06/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import GRPC
import NIO

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
    var mDpDecimal:Int16 = 6
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        WUtils.setDenomTitle(pageHolderVC.chainType!, rewardDenomLabel)
        
        self.loadingImg.onStartAnimation()
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            self.onFetchRewards_gRPC(pageHolderVC.mAccount!.account_address)
            
        } else {
            self.onFetchReward(pageHolderVC.mAccount!.account_address, pageHolderVC.mTargetValidator!.operator_address)
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
        mDpDecimal = WUtils.mainDivideDecimal(pageHolderVC.chainType)
        
        if (WUtils.isGRPC(pageHolderVC.chainType!)) {
            let cReward = BaseData.instance.getReward_gRPC(WUtils.getMainDenom(pageHolderVC.chainType), pageHolderVC.mTargetValidator_gRPC?.operatorAddress)
            rewardAmountLabel.attributedText = WUtils.displayAmount2(cReward.stringValue, rewardAmountLabel.font, mDpDecimal, mDpDecimal)
            validatorLabel.text = pageHolderVC.mTargetValidator_gRPC?.description_p.moniker
            
            let coin = Coin(WUtils.getMainDenom(pageHolderVC.chainType), cReward.rounding(accordingToBehavior: WUtils.handler0Down).stringValue)
            self.pageHolderVC.mReinvestReward = coin
            
            self.loadingImg.isHidden = true
            self.controlLayer.isHidden = false
            self.cardView.isHidden = false
            
        } else if (self.pageHolderVC.mReinvestReward != nil) {
            rewardAmountLabel.attributedText = WUtils.displayAmount2(pageHolderVC.mReinvestReward!.amount, rewardAmountLabel.font, mDpDecimal, mDpDecimal)
            validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
            self.loadingImg.isHidden = true
            self.controlLayer.isHidden = false
            self.cardView.isHidden = false
            
        } else {
            pageHolderVC.onBeforePage()
            
        }
    }

    func onFetchReward(_ address: String, _ opAddress: String) {
        let request = Alamofire.request(BaseNetWork.rewardUrl(pageHolderVC.chainType, address, opAddress), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                    self.updateView()
                    return;
                }
                for rawReward in rawRewards {
                    if let mainReward = rawReward.object(forKey: "denom") as? String, mainReward == WUtils.getMainDenom(self.pageHolderVC.chainType!) {
                        var coin = Coin(rawReward as! [String : Any])
                        coin.amount = NSDecimalNumber.init(string: coin.amount).rounding(accordingToBehavior: WUtils.handler0Down).stringValue
                        self.pageHolderVC.mReinvestReward = coin
                    }
                }
                
            case .failure(let error):
                print("onFetchEachReward ", error)
            }
            self.updateView()
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
                self.updateView()
            });
        }
    }
    
}
