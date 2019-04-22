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
import QRCode

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
    @IBOutlet weak var atomTotalLabel: UILabel!
    @IBOutlet weak var atomPriceLabel: UILabel!
    @IBOutlet weak var atomAvailableAmount: UILabel!
    @IBOutlet weak var atomDelegatedAmount: UILabel!
    @IBOutlet weak var atomUnbondingAmount: UILabel!
    @IBOutlet weak var atomRewardAmount: UILabel!
    
    @IBOutlet weak var priceCard: CardView!
    @IBOutlet weak var pricePerAtom: UILabel!
    @IBOutlet weak var priceUpDownLabel: UILabel!
    @IBOutlet weak var priceUpDownImg: UIImageView!
    
    @IBOutlet weak var photonCard: CardView!
    @IBOutlet weak var photonPriceLabel: UILabel!
    @IBOutlet weak var photonTotalLabel: UILabel!
    @IBOutlet weak var photonAvailableAmount: UILabel!
    @IBOutlet weak var photonRewardAmount: UILabel!
    
    
    @IBOutlet weak var ConstraintPhoton: NSLayoutConstraint!
    @IBOutlet weak var ConstaraintPrice: NSLayoutConstraint!
    
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
        floaty.buttonImage = UIImage.init(named: "sendImg")
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
            ConstaraintPrice.priority = UILayoutPriority(rawValue: 1000)
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
        
        if let change = mainTabVC.mAtomTic?.value(forKeyPath: "data.quotes.USD.percent_change_24h") as? Double,
            let price = mainTabVC.mAtomTic?.value(forKeyPath: "data.quotes.USD.price") as? Double {
            let changeValue = NSDecimalNumber(value: change)
            let priceValue = NSDecimalNumber(value: price)

            let dpPrice = priceValue.multiplying(by: WUtils.stringToDecimal(atomTotalLabel.text!.replacingOccurrences(of: ",", with: "")), withBehavior: WUtils.handler2)
            atomPriceLabel.attributedText = WUtils.displayUSD(dpPrice, font: atomPriceLabel.font)
            pricePerAtom.attributedText = WUtils.displayUSD(priceValue, font: pricePerAtom.font)
            
            if(changeValue.compare(NSDecimalNumber.zero).rawValue > 0) {
                priceUpDownImg.image = UIImage(named: "priceUp")
                priceUpDownLabel.text = changeValue.stringValue + "% (24h)"
            } else if (changeValue.compare(NSDecimalNumber.zero).rawValue < 0) {
                priceUpDownImg.image = UIImage(named: "priceDown")
                priceUpDownLabel.text = changeValue.stringValue + "% (24h)"
            } else {
                priceUpDownImg.image = nil
                priceUpDownLabel.text = ""
            }
        }
        
        self.refresher.endRefreshing()
    }
    
    func emptyFloatySelected(_ floaty: Floaty) {
        let alert = UIAlertController(title: "Unable to transfer", message: "Token transfer will be enabled following SDK upgrades after proposal #3 is passed.", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
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
//        print("MainTabSendViewController onFetchDone")
        updateView();
    }
    
    @objc func onRequestFetch() {
//        print("onRequestFetch")
        if(!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }
    
    @IBAction func onClickMyAddressShare(_ sender: UIButton) {
        var qrCode = QRCode(self.mainTabVC.mAccount.account_address)
        qrCode?.backgroundColor = CIColor(rgba: "EEEEEE")
        qrCode?.size = CGSize(width: 200, height: 200)
        
        var walletName: String?
        if (mainTabVC.mAccount.account_nick_name == "") {
            walletName = "Wallet " + String(mainTabVC.mAccount.account_id)
        } else {
            walletName = mainTabVC.mAccount.account_nick_name
        }
        
        let alert = UIAlertController(title: walletName, message: "\n\n\n\n\n\n\n\n", preferredStyle: .alert)
        alert.view.subviews.first?.subviews.first?.subviews.first?.backgroundColor = UIColor.init(hexString: "EEEEEE")
        alert.addAction(UIAlertAction(title: "Share", style: .default, handler:  { [weak alert] (_) in
            let shareTypeAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
            shareTypeAlert.addAction(UIAlertAction(title: "Share Text Address", style: .default, handler: { [weak shareTypeAlert] (_) in
                let text = self.mainTabVC.mAccount.account_address
                let textToShare = [ text ]
                let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
                activityViewController.popoverPresentationController?.sourceView = self.view
                self.present(activityViewController, animated: true, completion: nil)
            }))
            shareTypeAlert.addAction(UIAlertAction(title: "Share QrCode Image", style: .default, handler: { [weak shareTypeAlert] (_) in
                let image = qrCode?.image
                let imageToShare = [ image! ]
                let activityViewController = UIActivityViewController(activityItems: imageToShare, applicationActivities: nil)
                activityViewController.popoverPresentationController?.sourceView = self.view
                self.present(activityViewController, animated: true, completion: nil)
            }))
            self.present(shareTypeAlert, animated: true) {
                let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
                shareTypeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
            }
        }))
        
        alert.addAction(UIAlertAction(title: "Copy", style: .default, handler: { [weak alert] (_) in
            UIPasteboard.general.string = self.mainTabVC.mAccount.account_address
            self.onShowToast(NSLocalizedString("address_copied", comment: ""))
        }))
        
        let image = UIImageView(image: qrCode?.image)
        image.contentMode = .scaleAspectFit
        alert.view.addSubview(image)
        image.translatesAutoresizingMaskIntoConstraints = false
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerX, relatedBy: .equal, toItem: alert.view, attribute: .centerX, multiplier: 1, constant: 0))
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerY, relatedBy: .equal, toItem: alert.view, attribute: .centerY, multiplier: 1, constant: 0))
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .width, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 140.0))
        alert.view.addConstraint(NSLayoutConstraint(item: image, attribute: .height, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 140.0))
        self.present(alert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            alert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
        
    }
    
    @objc func dismissAlertController(){
        self.dismiss(animated: true, completion: nil)
    }
    
    
}
