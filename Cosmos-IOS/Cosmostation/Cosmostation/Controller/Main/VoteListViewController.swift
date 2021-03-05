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
    var mProposals_V1 = Array<Proposal_V1>()
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
        
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST || chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            self.onFetchProposals_V1()
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
        if(mProposals.count > 0 || mProposals_V1.count > 0) {
            self.emptyLabel.isHidden = true
            self.voteTableView.reloadData()
        } else {
            self.emptyLabel.isHidden = false
        }
        self.sortProposals()
        self.refresher.endRefreshing()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN ||
                chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN ) {
            return self.mProposals.count
        } else if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST || chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            return self.mProposals_V1.count
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN ||
                chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN ) {
            return onBindProposal(tableView, indexPath)
        } else if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST || chainType == ChainType.IRIS_MAIN  || chainType == ChainType.IRIS_TEST) {
            return onBindProposalV1(tableView, indexPath)
        } else {
            let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
            return cell!
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
    
    func onBindProposalV1(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell  {
        let cell:ProposalCell? = tableView.dequeueReusableCell(withIdentifier:"ProposalCell") as? ProposalCell
        let proposal = mProposals_V1[indexPath.row]
        cell?.proposalIdLabel.text = "# ".appending(proposal.proposal_id!)
        cell?.proposalTitleLabel.text = proposal.content?.title
        cell?.proposalMsgLabel.text = proposal.content?.description
        cell?.proposalStateLabel.text = proposal.getStatusText()
        cell?.proposalStateImg.image = proposal.getStatusImg()
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (chainType == ChainType.COSMOS_MAIN) {
            let proposal = mProposals_V1[indexPath.row]
            guard let url = URL(string: EXPLORER_COSMOS_MAIN + "proposals/" + proposal.proposal_id!) else { return }
            self.onShowSafariWeb(url)
            
        } else if(chainType == ChainType.COSMOS_TEST) {
            let proposal = mProposals_V1[indexPath.row]
            guard let url = URL(string: EXPLORER_COSMOS_TEST + "proposals/" + proposal.proposal_id!) else { return }
            self.onShowSafariWeb(url)
            
        } else if(chainType == ChainType.IRIS_TEST) {
            let proposal = mProposals_V1[indexPath.row]
            guard let url = URL(string: EXPLORER_IRIS_TEST + "proposals/" + proposal.proposal_id!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            let proposal = mProposals_V1[indexPath.row]
            guard let url = URL(string: EXPLORER_IRIS_MAIN + "proposals/" + proposal.proposal_id!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.KAVA_MAIN) {
            let proposal = mProposals[indexPath.row]
            if (Int(proposal.id)! >= 3) {
                let voteDetailsVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailsViewController") as! VoteDetailsViewController
                voteDetailsVC.proposalId = proposal.id
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(voteDetailsVC, animated: true)
                
            } else {
                guard let url = URL(string: EXPLORER_KAVA_MAIN + "proposals/" + proposal.id) else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            let proposal = mProposals[indexPath.row]
            let voteDetailsVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailsViewController") as! VoteDetailsViewController
            voteDetailsVC.proposalId = proposal.id
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(voteDetailsVC, animated: true)
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            let proposal = mProposals[indexPath.row]
            if (Int(proposal.id)! >= 15) {
                let voteDetailsVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailsViewController") as! VoteDetailsViewController
                voteDetailsVC.proposalId = proposal.id
                self.navigationItem.title = ""
                self.navigationController?.pushViewController(voteDetailsVC, animated: true)
                
            } else {
                guard let url = URL(string: EXPLORER_SECRET_MAIN + "governance/proposals/" + proposal.id) else { return }
                self.onShowSafariWeb(url)
            }
            
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN) {
            let proposal = mProposals[indexPath.row]
            let voteDetailsVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "VoteDetailsViewController") as! VoteDetailsViewController
            voteDetailsVC.proposalId = proposal.id
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(voteDetailsVC, animated: true)
        }
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
        } else if (chainType == ChainType.AKASH_MAIN) {
            let url = AKASH_PROPOSALS;
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
    
    @objc func onFetchProposals_V1() {
        let url = BaseNetWork.proposals(chainType!)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let proposals = responseData.object(forKey: "proposals") as? Array<NSDictionary> else {
                        self.onUpdateViews()
                        return
                }
                self.mProposals_V1.removeAll()
                for proposal in proposals {
                    self.mProposals_V1.append(Proposal_V1(proposal))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchProposals_V1 ", error) }
            }
            self.onUpdateViews()
        }
        
    }
    
    func sortProposals() {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST || chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            self.mProposals_V1.sort{
                return Int($0.proposal_id!)! < Int($1.proposal_id!)! ? false : true
            }
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.CERTIK_MAIN ||
                    chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN ) {
            self.mProposals.sort{
                return Int($0.id)! < Int($1.id)! ? false : true
            }
        }
    }

}
