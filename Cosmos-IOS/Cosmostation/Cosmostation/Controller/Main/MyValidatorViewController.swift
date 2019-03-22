//
//  MyValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class MyValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    @IBOutlet weak var myValidatorTableView: UITableView!
    
    var mMyValidators = Array<Validator>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.myValidatorTableView.delegate = self
        self.myValidatorTableView.dataSource = self
        self.myValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.myValidatorTableView.register(UINib(nibName: "MyValidatorCell", bundle: nil), forCellReuseIdentifier: "MyValidatorCell")
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        let rewardTabVC = self.parent as! MainTabRewardViewController
        
        print("MyValidatorViewController ", rewardTabVC.mAllValidators.count)
        
    }
    
    
    func rewardViewUpdate() {
        let rewardTabVC = self.parent as! MainTabRewardViewController
        self.mMyValidators.removeAll()
        self.mMyValidators = rewardTabVC.mMyValidators
        print("AllValidatorViewController mMyValidators ", mMyValidators.count)
        self.myValidatorTableView.reloadData()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mMyValidators.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:MyValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"MyValidatorCell") as? MyValidatorCell
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
}
