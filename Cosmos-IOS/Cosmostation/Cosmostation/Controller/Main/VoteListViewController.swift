//
//  VoteListViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class VoteListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var voteTableView: UITableView!
    @IBOutlet weak var emptyLabel: UILabel!
    
    var mProposals = Array<Proposal>()
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mainTabVC = (self.parent)?.parent as? MainTabViewController
        self.chainType = WUtils.getChainType(mainTabVC.mAccount.account_base_chain)
        
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
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_vote_list", comment: "");
        self.navigationItem.title = NSLocalizedString("title_vote_list", comment: "");
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func onUpdateViews() {
        if(mProposals.count > 0) {
            self.emptyLabel.isHidden = true
            self.voteTableView.reloadData()
        } else {
            self.emptyLabel.isHidden = false
        }
        self.sortProposals()
        self.refresher.endRefreshing()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mProposals.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
        let proposal = mProposals[indexPath.row]
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            cell?.proposalIdLabel.text = "# ".appending(proposal.id)
            cell?.proposalTitleLabel.text = proposal.content?.value.title
            cell?.proposalMsgLabel.text = proposal.content?.value.description
            cell?.proposalStateLabel.text = proposal.proposal_status
            if (proposal.proposal_status == "DepositPeriod") {
                cell?.proposalStateImg.image = UIImage.init(named: "depositImg")
            } else if (proposal.proposal_status == "VotingPeriod") {
                cell?.proposalStateImg.image = UIImage.init(named: "votingImg")
            } else if (proposal.proposal_status == "Rejected") {
                cell?.proposalStateImg.image = UIImage.init(named: "rejectedImg")
            } else if (proposal.proposal_status == "Passed") {
                cell?.proposalStateImg.image = UIImage.init(named: "passedImg")
            } else {
                cell?.proposalStateImg.image = nil
            }
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            cell?.proposalIdLabel.text = "# ".appending(proposal.value!.basicProposal!.proposal_id)
            cell?.proposalTitleLabel.text = proposal.value?.basicProposal?.title
            cell?.proposalMsgLabel.text = proposal.value?.basicProposal?.description
            cell?.proposalStateLabel.text = proposal.value?.basicProposal?.proposal_status
            if (proposal.value?.basicProposal?.proposal_status == "DepositPeriod") {
                cell?.proposalStateImg.image = UIImage.init(named: "depositImg")
            } else if (proposal.value?.basicProposal?.proposal_status == "VotingPeriod") {
                cell?.proposalStateImg.image = UIImage.init(named: "votingImg")
            } else if (proposal.value?.basicProposal?.proposal_status == "Rejected") {
                cell?.proposalStateImg.image = UIImage.init(named: "rejectedImg")
            } else if (proposal.value?.basicProposal?.proposal_status == "Passed") {
                cell?.proposalStateImg.image = UIImage.init(named: "passedImg")
            } else {
                cell?.proposalStateImg.image = nil
            }
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            cell?.proposalIdLabel.text = "# ".appending(proposal.id)
            cell?.proposalTitleLabel.text = proposal.content?.value.title
            cell?.proposalMsgLabel.text = proposal.content?.value.description
            cell?.proposalStateLabel.text = proposal.proposal_status
            if (proposal.proposal_status == "DepositPeriod") {
                cell?.proposalStateImg.image = UIImage.init(named: "depositImg")
            } else if (proposal.proposal_status == "VotingPeriod") {
                cell?.proposalStateImg.image = UIImage.init(named: "votingImg")
            } else if (proposal.proposal_status == "Rejected") {
                cell?.proposalStateImg.image = UIImage.init(named: "rejectedImg")
            } else if (proposal.proposal_status == "Passed") {
                cell?.proposalStateImg.image = UIImage.init(named: "passedImg")
            } else {
                cell?.proposalStateImg.image = nil
            }
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 90;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let proposal = mProposals[indexPath.row]
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            guard let url = URL(string: "https://www.mintscan.io/proposals/" + proposal.proposal_id) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let proposal = mProposals[indexPath.row]
            let voteDetailVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailViewController") as! VoteDetailViewController
            voteDetailVC.proposalId = proposal.value!.basicProposal!.proposal_id
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(voteDetailVC, animated: true)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            guard let url = URL(string: "https://kava.mintscan.io/proposals/" + proposal.id) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
        }
    }
    
    @objc func onFetchProposals() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            let url = CSS_LCD_URL_PROPOSALS;
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let proposals = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                            self.onUpdateViews()
                            return
                    }
                    self.mProposals.removeAll()
                    for proposal in proposals {
                        self.mProposals.append(Proposal(proposal as! [String : Any]))
                    }
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchProposals ", error) }
                }
                self.onUpdateViews()
            }
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            let url = IRIS_LCD_URL_PROPOSALS;
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
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
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchProposals ", error) }
                }
                self.onUpdateViews()
            }
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            let url = KAVA_PROPOSALS;
            let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let proposals = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                            self.onUpdateViews()
                            return
                    }
                    self.mProposals.removeAll()
                    for proposal in proposals {
                        self.mProposals.append(Proposal(proposal as! [String : Any]))
                    }
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchProposals ", error) }
                }
                self.onUpdateViews()
            }
        }
    }
    
    func sortProposals() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            self.mProposals.sort{
                return Int($0.id)! < Int($1.id)! ? false : true
            }
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            self.mProposals.sort{
                return Int($0.value!.basicProposal!.proposal_id)! < Int($1.value!.basicProposal!.proposal_id)! ? false : true
            }
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            self.mProposals.sort{
                return Int($0.id)! < Int($1.id)! ? false : true
            }
        }
    }

}
