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
    @IBOutlet weak var rewardFromLabel: UILabel!
    @IBOutlet weak var selfLabel: UILabel!
    @IBOutlet weak var rewardToAddressLabel: UILabel!
    
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        self.loadingImg.onStartAnimation()
        self.onFetchEachReward(pageHolderVC.mAccount!.account_address, pageHolderVC.mTargetValidator!.operator_address)
    }
    
    func updateView() {
//        print("updateView")
        rewardAmountLabel.attributedText = WUtils.displayAmout(pageHolderVC.mReward.reward_amount[0].amount, rewardAmountLabel.font, 6)
        rewardFromLabel.text = pageHolderVC.mTargetValidator!.description.moniker
        rewardToAddressLabel.text = pageHolderVC.mRewardAddress
        rewardToAddressLabel.adjustsFontSizeToFitWidth = true
        if (pageHolderVC.mAccount?.account_address == pageHolderVC.mRewardAddress) {
            self.selfLabel.isHidden = false
        } else {
            self.selfLabel.isHidden = true
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
//        print("onFetchEachReward")
        let url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchEachReward ", res)
                guard let rawRewards = res as? Array<NSDictionary> else {
//                    print("error no reward")
                    return;
                }
                self.pageHolderVC.mReward.reward_v_address = validatorAddr
                for rawReward in rawRewards {
                    self.pageHolderVC.mReward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.onFetchRewardAddress(accountAddr)
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchEachReward ", error)
                }
            }
        }
    }
    
    func onFetchRewardAddress(_ accountAddr: String) {
//        print("onFetchRewardAddress")
        let url = CSS_LCD_URL_REWARD_ADDRESS + accountAddr + CSS_LCD_URL_REWARD_ADDRESS_TAIL
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        
//        print("onFetchRewardAddress ", request.request?.url)
        
        request.responseString { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchRewardAddress ", res)
                guard let address = res as? String else {
//                    print("error no address")
                    return;
                }
                self.pageHolderVC.mRewardAddress = address.replacingOccurrences(of: "\"", with: "")
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchRewardAddress ", error)
                }
            }
            self.updateView()
        }
        
    }
    
}
