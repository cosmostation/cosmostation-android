//
//  MainTabRewardViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MainTabRewardViewController: BaseViewController {
    
    @IBOutlet weak var titleView: UIView!
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    @IBOutlet weak var titleAccountBtn: UIButton!
    @IBOutlet weak var titleSortBtn: UIButton!
    @IBOutlet weak var validatorSegment: UISegmentedControl!
    @IBOutlet weak var myValidatorView: UIView!
    @IBOutlet weak var allValidatorView: UIView!
    
    var mainTabVC: MainTabViewController!
    
    @IBAction func switchView(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            myValidatorView.alpha = 1
            allValidatorView.alpha = 0
        } else {
            myValidatorView.alpha = 0
            allValidatorView.alpha = 1
        }
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        myValidatorView.alpha = 1
        allValidatorView.alpha = 0
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.updateTitle()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") { titleWalletName.text = NSLocalizedString("wallet_dash", comment: "") + String(mainTabVC.mAccount.account_id)
        } else { titleWalletName.text = mainTabVC.mAccount.account_nick_name }
        
        if(mainTabVC.mAccount.account_base_chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN.rawValue) {
            titleChainName.text = "(Cosmos Hub)"
        } else {
            titleChainName.text = ""
        }
    }
    
    @IBAction func onSortClick(_ sender: Any) {
        if (myValidatorView.alpha == 1) {
//            print("my val sort show")
            let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
            alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: UIAlertAction.Style.cancel, handler: nil))
            alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_name", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
                BaseData.instance.setMyValidatorSort(1)
                NotificationCenter.default.post(name: Notification.Name("onSortingMy"), object: nil, userInfo: nil)
            }))
            alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_my_delegate", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
                BaseData.instance.setMyValidatorSort(0)
                NotificationCenter.default.post(name: Notification.Name("onSortingMy"), object: nil, userInfo: nil)
            }))
            alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_reward", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
                BaseData.instance.setMyValidatorSort(2)
                NotificationCenter.default.post(name: Notification.Name("onSortingMy"), object: nil, userInfo: nil)
            }))
            self.present(alert, animated: true, completion: nil)
            
            
        } else {
            let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
            alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: nil))
            alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_name", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
                BaseData.instance.setAllValidatorSort(1)
                NotificationCenter.default.post(name: Notification.Name("onSorting"), object: nil, userInfo: nil)
            }))
            alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_power", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
                BaseData.instance.setAllValidatorSort(0)
                NotificationCenter.default.post(name: Notification.Name("onSorting"), object: nil, userInfo: nil)
            }))
            alert.addAction(UIAlertAction(title: NSLocalizedString("sort_by_commission", comment: ""), style: UIAlertAction.Style.default, handler: { (action) in
                BaseData.instance.setAllValidatorSort(2)
                NotificationCenter.default.post(name: Notification.Name("onSorting"), object: nil, userInfo: nil)
            }))
            self.present(alert, animated: true, completion: nil)
        }
    }

    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }
}
