//
//  MainTabVoteViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class MainTabVoteViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    @IBOutlet weak var titleChainImg: UIImageView!
    @IBOutlet weak var titleWalletName: UILabel!
    @IBOutlet weak var titleChainName: UILabel!
    
    @IBOutlet weak var voteTableView: UITableView!
    @IBOutlet weak var emptyLabel: UILabel!
    
    var mProposals = Array<Proposal>()
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.updateTitle()
        
        self.voteTableView.delegate = self
        self.voteTableView.dataSource = self
        self.voteTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.voteTableView.register(UINib(nibName: "ProposalCell", bundle: nil), forCellReuseIdentifier: "ProposalCell")
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchProposals), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.voteTableView.addSubview(refresher)
        
        self.onFetchProposals()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }
    
    func updateTitle() {
        if (mainTabVC.mAccount.account_nick_name == "") { titleWalletName.text = "Wallet " + String(mainTabVC.mAccount.account_id)
        } else { titleWalletName.text = mainTabVC.mAccount.account_nick_name }
        
        if(mainTabVC.mAccount.account_base_chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN.rawValue) {
            titleChainName.text = "(Cosmos Hub)"
        } else {
            titleChainName.text = ""
        }
    }
    
    func onUpdateViews() {
        if(mProposals.count > 0) {
            self.emptyLabel.isHidden = true
            self.voteTableView.reloadData()
        } else {
            self.emptyLabel.isHidden = false
        }
        self.refresher.endRefreshing()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mProposals.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
        let proposal = mProposals[indexPath.row]
        cell?.proposalIdLabel.text = proposal.value.proposal_id
        cell?.proposalTitleLabel.text = proposal.value.title
        cell?.proposalMsgLabel.text = proposal.value.description
        cell?.proposalStateLabel.text = proposal.value.proposal_status
        cell?.proposalSubmitTime.text = WUtils.nodeTimetoString(input: proposal.value.submit_time)
        if(proposal.value.voting_start_time.starts(with: "0")) {
            cell?.proposalStartTime.text = "n/a"
        } else {
            cell?.proposalStartTime.text = WUtils.nodeTimetoString(input: proposal.value.voting_start_time)
        }
        
        if(proposal.value.voting_end_time.starts(with: "0")) {
            cell?.proposalEndTime.text = "n/a"
        } else {
            cell?.proposalEndTime.text = WUtils.nodeTimetoString(input: proposal.value.voting_end_time)
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 130;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let proposal = mProposals[indexPath.row]
        guard let url = URL(string: "https://www.mintscan.io/proposals/" + proposal.value.proposal_id) else { return }
        let safariViewController = SFSafariViewController(url: url)
        present(safariViewController, animated: true, completion: nil)
    }
    
    @objc func onFetchProposals() {
//        print("onFetchProposals")
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
//                print("mProposals size : ", self.mProposals.count)
                
            case .failure(let error):
                print(error)
            }
            self.onUpdateViews()
        }
    }
    
    @IBAction func onClickSwitchAccount(_ sender: Any) {
        self.mainTabVC.dropDown.show()
    }

}
