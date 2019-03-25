//
//  MainTabVoteViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class MainTabVoteViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    

    @IBOutlet weak var voteTableView: UITableView!
    
    var mProposals = Array<Proposal>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.voteTableView.delegate = self
        self.voteTableView.dataSource = self
        self.voteTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.voteTableView.register(UINib(nibName: "ProposalCell", bundle: nil), forCellReuseIdentifier: "ProposalCell")
        
        self.onFetchProposals()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    func onUpdateViews() {
        self.voteTableView.reloadData()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mProposals.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 130;
    }
    
    func onFetchProposals() {
        print("onFetchProposals")
        let request = Alamofire.request(CSS_LCD_URL_PROPOSALS, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let proposals = res as? Array<NSDictionary> else {
                    self.onUpdateViews()
                    return
                }
                self.mProposals.removeAll()
                for proposal in proposals {
                    self.mProposals.append(Proposal(proposal as! [String : Any]))
                }
                print("mProposals size : ", self.mProposals.count)
                
            case .failure(let error):
                print(error)
            }
            self.onUpdateViews()
        }
    }

}
