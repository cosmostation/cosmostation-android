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
    @IBOutlet weak var validatorLabel: UILabel!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    @IBOutlet weak var controlLayer: UIStackView!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        self.loadingImg.onStartAnimation()
        self.onFetchReward(pageHolderVC.mAccount!.account_address, pageHolderVC.mTargetValidator!.operator_address)
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
        print("updateView")
        if(self.pageHolderVC.mReinvestReward != nil) {
            rewardAmountLabel.attributedText = WUtils.displayAmout(pageHolderVC.mReinvestReward!.amount, rewardAmountLabel.font, 6)
            validatorLabel.text = pageHolderVC.mTargetValidator?.description.moniker
            
            self.loadingImg.isHidden = true
            self.controlLayer.isHidden = false
            self.cardView.isHidden = false
            
        } else {
            pageHolderVC.onBeforePage()
        }
        
    }

    func onFetchReward(_ accountAddr: String, _ validatorAddr:String) {
        let url = CSS_LCD_URL_REWARD_FROM_VAL + accountAddr + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + validatorAddr
        let request = Alamofire.request(url,
                                        method: .get,
                                        parameters: [:],
                                        encoding: URLEncoding.default,
                                        headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawRewards = res as? Array<NSDictionary> else {
                    self.updateView()
                    return;
                }
                for rawReward in rawRewards {
                    if let atomReward = rawReward.object(forKey: "denom") as? String, atomReward == "uatom" {
                        var coin = Coin(rawReward as! [String : Any])
                        coin.amount = NSDecimalNumber.init(string: coin.amount).rounding(accordingToBehavior: WUtils.handlerdown0).stringValue
                        self.pageHolderVC.mReinvestReward = coin
                    }
                }
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchEachReward ", error)
                }
            }
            self.updateView()
        }
        
    }
    
}
