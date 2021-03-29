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
import GRPC
import NIO

class VoteListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var voteTableView: UITableView!
    @IBOutlet weak var emptyLabel: UILabel!
    
    var mProposals = Array<Proposal>()
    var mProposals_gRPC = Array<Cosmos_Gov_V1beta1_Proposal>()
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
        self.voteTableView.rowHeight = UITableView.automaticDimension
        self.voteTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchProposals), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.voteTableView.addSubview(refresher)
        
        if (WUtils.isGRPC(chainType!)) {
            self.onFetchProposals_gRPC()
        } else {
            self.onFetchProposals()
        }
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
        if(mProposals.count > 0 || mProposals_gRPC.count > 0) {
            self.emptyLabel.isHidden = true
            self.voteTableView.reloadData()
        } else {
            self.emptyLabel.isHidden = false
        }
        self.sortProposals()
        self.refresher.endRefreshing()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
         if (WUtils.isGRPC(chainType!)) {
            return self.mProposals_gRPC.count
         } else {
            return self.mProposals.count
         }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (WUtils.isGRPC(chainType!)) {
            return onBindProposal_gRPC(tableView, indexPath)
        } else {
            return onBindProposal(tableView, indexPath)
        }
    }
    
    func onBindProposal(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell  {
        let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
        let proposal = mProposals[indexPath.row]
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
        return cell!
    }
    
    func onBindProposal_gRPC(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell  {
        let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
        let proposal = mProposals_gRPC[indexPath.row]
        cell?.proposalIdLabel.text = "# ".appending(String(proposal.proposalID))
        cell?.proposalTitleLabel.text = WUtils.onParseProposalTitle(proposal)
        cell?.proposalMsgLabel.text = WUtils.onParseProposalDescription(proposal)
        cell?.proposalStateLabel.text = WUtils.onParseProposalStatusTxt(proposal)
        cell?.proposalStateImg.image = WUtils.onParseProposalStatusImg(proposal)
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (WUtils.isGRPC(chainType!)) {
            let proposal = mProposals_gRPC[indexPath.row]
            if (proposal.status  == Cosmos_Gov_V1beta1_ProposalStatus.passed || proposal.status  == Cosmos_Gov_V1beta1_ProposalStatus.rejected) {
                onExplorerLink(String(proposal.proposalID))
            } else {
                let voteDetailsVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailsViewController") as! VoteDetailsViewController
                voteDetailsVC.proposalId = String(proposal.proposalID)
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(voteDetailsVC, animated: true)
            }
            
        } else {
            let proposal = mProposals[indexPath.row]
            if (proposal.proposal_status == "Rejected" || proposal.proposal_status == "Passed") {
                onExplorerLink(proposal.id)
            } else {
                let voteDetailsVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailsViewController") as! VoteDetailsViewController
                voteDetailsVC.proposalId = proposal.id
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(voteDetailsVC, animated: true)
            }
        }
    }
    
    func onExplorerLink(_ proposalId: String) {
        let link = WUtils.getProposalExplorer(self.chainType!, proposalId)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    @objc func onFetchProposals() {
        if (chainType == ChainType.KAVA_MAIN) {
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
            
        } else if (chainType == ChainType.BAND_MAIN) {
            let url = BAND_PROPOSALS;
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
        } else if (chainType == ChainType.SECRET_MAIN) {
            let url = SECRET_PROPOSALS;
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
        } else if (chainType == ChainType.CERTIK_MAIN) {
            let url = CERTIK_PROPOSALS;
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
        } else if (chainType == ChainType.CERTIK_TEST) {
            let url = CERTIK_TEST_PROPOSALS;
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
            
        } else if (chainType == ChainType.IOV_MAIN) {
            let url = IOV_PROPOSALS;
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
            
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            let url = SENTINEL_PROPOSALS;
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
    
    @objc func onFetchProposals_gRPC() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Gov_V1beta1_QueryProposalsRequest.init()
            do {
                let response = try Cosmos_Gov_V1beta1_QueryClient(channel: channel).proposals(req).response.wait()
                self.mProposals_gRPC = response.proposals
                
            } catch {
                print("onFetchProposals_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onUpdateViews()
            });
        }
    }
    
    func sortProposals() {
        if (WUtils.isGRPC(chainType!)) {
            self.mProposals_gRPC.sort{
                return $0.proposalID < $1.proposalID ? false : true
            }
        } else {
            self.mProposals.sort{
                return Int($0.id)! < Int($1.id)! ? false : true
            }
        }
    }

}
