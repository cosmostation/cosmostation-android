//
//  AllValidatorViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AllValidatorViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var allValidatorTableView: UITableView!
    var refresher: UIRefreshControl!
    
    var mAllValidators = Array<Validator>()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.allValidatorTableView.delegate = self
        self.allValidatorTableView.dataSource = self
        self.allValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.allValidatorTableView.register(UINib(nibName: "AllValidatorCell", bundle: nil), forCellReuseIdentifier: "AllValidatorCell")
    
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(handleRefresh), for: .valueChanged)
        refresher.tintColor = UIColor.white
        self.allValidatorTableView.addSubview(refresher)
    }
    
    
    @objc func handleRefresh() {
        print("handleRefresh")
    }
    
    func rewardViewUpdate() {
        let rewardTabVC = self.parent as! MainTabRewardViewController
        self.mAllValidators.removeAll()
        self.mAllValidators = rewardTabVC.mAllValidators
        print("AllValidatorViewController mAllValidators ", mAllValidators.count)
        self.allValidatorTableView.reloadData()
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mAllValidators.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let validator = mAllValidators[indexPath.row]
        let cell:AllValidatorCell? = tableView.dequeueReusableCell(withIdentifier:"AllValidatorCell") as? AllValidatorCell
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    
    
//    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        var selectedCell:UITableViewCell = tableView.cellForRow(at: indexPath)!
//        selectedCell.contentView.backgroundColor = UIColor.clear
//    }

}
