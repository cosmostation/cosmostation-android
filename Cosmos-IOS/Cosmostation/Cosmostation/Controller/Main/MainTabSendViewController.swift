//
//  MainTabSendViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MainTabSendViewController: BaseViewController {
    
    
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
    
    @IBOutlet weak var rewardCard: CardView!
    @IBOutlet weak var rewardImg: UIImageView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
//        print("password ", BaseData.instance.hasPassword())
        print("password ", BaseData.instance.hasPassword())
        print("password ", BaseData.instance.getRecentAccountId())
        
        rewardImg.image = rewardImg.image?.withRenderingMode(.alwaysTemplate)
        rewardImg.tintColor = UIColor.white
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    
    

}
