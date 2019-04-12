//
//  MainTabSendViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import Floaty
import DropDown

class MainTabSendViewController: BaseViewController , FloatyDelegate{
    
    @IBOutlet weak var mainScrollView: UIScrollView!
    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    @IBOutlet weak var titleAccountBtn: UIButton!
    
    @IBOutlet weak var keyCard: CardView!
    @IBOutlet weak var keyTypeImg: UIImageView!
    @IBOutlet weak var keyAddressLabel: UILabel!
    @IBOutlet weak var keyQrcodeBtn: UIButton!
    
    @IBOutlet weak var AtomCard: CardView!
    @IBOutlet weak var atomPriceLabel: UILabel!
    @IBOutlet weak var atomTotalLabel: UILabel!
    @IBOutlet weak var atomAvailableAmount: UILabel!
    @IBOutlet weak var atomDelegatedAmount: UILabel!
    @IBOutlet weak var atomUnbondingAmount: UILabel!
    @IBOutlet weak var atomRewardAmount: UILabel!
    
    
    @IBOutlet weak var photonCard: CardView!
    @IBOutlet weak var photonPriceLabel: UILabel!
    @IBOutlet weak var photonTotalLabel: UILabel!
    @IBOutlet weak var photonAvailableAmount: UILabel!
    @IBOutlet weak var photonRewardAmount: UILabel!
    @IBOutlet weak var ConstraintPhoton: NSLayoutConstraint!
    @IBOutlet weak var ConstraintAtom: NSLayoutConstraint!
    
    @IBOutlet weak var rewardCard: CardView!
    @IBOutlet weak var rewardImg: UIImageView!
    
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    @IBOutlet weak var addPopupView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        rewardImg.image = rewardImg.image?.withRenderingMode(.alwaysTemplate)
//        rewardImg.tintColor = UIColor.white
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.updateTitle()
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        refresher.tintColor = UIColor.white
        mainScrollView.addSubview(refresher)
        
        let floaty = Floaty()
        floaty.buttonColor = UIColor.init(hexString: "9C6CFF")
        floaty.buttonImage = UIImage.init(named: "send_btn")
        floaty.fabDelegate = self
        self.view.addSubview(floaty)
        
        self.updateView()
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") { titleWalletName.text = "Wallet " + String(mainTabVC.mAccount.account_id)
        } else { titleWalletName.text = mainTabVC.mAccount.account_nick_name }
        
        if(mainTabVC.mAccount.account_has_private) { keyTypeImg.image = UIImage(named: "key_on")
        } else { keyTypeImg.image = UIImage(named: "key_off") }
        
        if(mainTabVC.mAccount.account_base_chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN.rawValue) {
            photonCard.isHidden = true
            ConstraintAtom.priority = UILayoutPriority(rawValue: 1000)
            ConstraintPhoton.priority = UILayoutPriority(rawValue: 500)
            titleChainName.text = "(Cosmos Hub)"
        } else {
            titleChainName.text = ""
        }
    }
    
    func updateView() {
        
        keyAddressLabel.text = mainTabVC.mAccount.account_address
        
        if(mainTabVC.mBalances.count > 0) {
            atomAvailableAmount.attributedText = WUtils.displayAmout(mainTabVC.mBalances[0].balance_amount, atomDelegatedAmount.font, 6)
        } else {
            atomAvailableAmount.attributedText = WUtils.displayAmout("0", atomDelegatedAmount.font, 6)
        }
        
        if(mainTabVC.mBondingList.count > 0) {
            var sum = NSDecimalNumber.zero
            for bonding in mainTabVC.mBondingList {
                sum = sum.adding(WUtils.stringToDecimal(bonding.bonding_shares))
            }
            atomDelegatedAmount.attributedText = WUtils.displayAmout(sum.stringValue, atomDelegatedAmount.font, 6)
            
        } else {
            atomDelegatedAmount.attributedText = WUtils.displayAmout("0", atomDelegatedAmount.font, 6)
        }
        
        if(mainTabVC.mUnbondingList.count > 0) {
            var sum = NSDecimalNumber.zero
            for unbonding in mainTabVC.mUnbondingList {
                sum = sum.adding(WUtils.stringToDecimal(unbonding.unbonding_balance))
            }
            atomUnbondingAmount.attributedText = WUtils.displayAmout(sum.stringValue, atomUnbondingAmount.font, 6)
            
        } else {
            atomUnbondingAmount.attributedText = WUtils.displayAmout("0", atomUnbondingAmount.font, 6)
        }
        
        if(mainTabVC.mAllRewards.count > 0) { atomRewardAmount.attributedText = WUtils.displayAmout(mainTabVC.mAllRewards[0].amount, atomRewardAmount.font, 6)
        } else { atomRewardAmount.attributedText = WUtils.displayAmout("0", atomRewardAmount.font, 6) }
        
        var totalSum = NSDecimalNumber.zero
        totalSum = totalSum.adding(WUtils.stringToDecimal(atomAvailableAmount.text!.replacingOccurrences(of: ",", with: "")))
            .adding(WUtils.stringToDecimal(atomDelegatedAmount.text!.replacingOccurrences(of: ",", with: "")))
            .adding(WUtils.stringToDecimal(atomUnbondingAmount.text!.replacingOccurrences(of: ",", with: "")))
            .adding(WUtils.stringToDecimal(atomRewardAmount.text!.replacingOccurrences(of: ",", with: "")))
        totalSum = totalSum.multiplying(by: 1000000)
        atomTotalLabel.attributedText = WUtils.displayAmout(totalSum.stringValue, atomTotalLabel.font, 6)
        
        var priceValue = NSDecimalNumber.zero
        guard let price = mainTabVC.mAtomTic?.value(forKeyPath: "data.quotes.USD.price") else {
            return
        }
        priceValue = NSDecimalNumber(string: String(describing: price))
        let dpPrice = priceValue.multiplying(by: WUtils.stringToDecimal(atomTotalLabel.text!.replacingOccurrences(of: ",", with: "")), withBehavior: WUtils.handler2)
        atomPriceLabel.attributedText = WUtils.displayUSD(dpPrice, font: atomPriceLabel.font)
        
        self.refresher.endRefreshing()
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        print("emptyFloatySelected")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
        
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
        
    }
    

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
    }
    
    
    @objc func onFetchDone(_ notification: NSNotification) {
        print("MainTabSendViewController onFetchDone")
        updateView();
    }
    
    @objc func onRequestFetch() {
        print("onRequestFetch")
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }
}
