//
//  VoteDetailsViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices
import GRPC
import NIO

class VoteDetailsViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var voteDetailTableView: UITableView!
    @IBOutlet weak var btnVote: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    var refresher: UIRefreshControl!
    
    var proposalId: String?
    var mProposal: Proposal?
    var mProposer: String?
    var mTally: Tally?
    var mVoters = Array<Vote>()
    var mMyVote: Vote?
    
    var mProposalDetail_gRPC: Cosmos_Gov_V1beta1_Proposal?
    var mTally_gRPC: Cosmos_Gov_V1beta1_TallyResult?
    var mVoters_gRPC: Array<Cosmos_Gov_V1beta1_Vote>?
    var mMyVote_gRPC: Cosmos_Gov_V1beta1_Vote?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.voteDetailTableView.delegate = self
        self.voteDetailTableView.dataSource = self
        self.voteDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.voteDetailTableView.register(UINib(nibName: "VoteInfoTableViewCell", bundle: nil), forCellReuseIdentifier: "VoteInfoTableViewCell")
        self.voteDetailTableView.register(UINib(nibName: "VoteTallyTableViewCell", bundle: nil), forCellReuseIdentifier: "VoteTallyTableViewCell")
        self.voteDetailTableView.rowHeight = UITableView.automaticDimension
        self.voteDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(onFech), for: .valueChanged)
        refresher.tintColor = UIColor.white
        voteDetailTableView.addSubview(refresher)
        
        self.loadingImg.onStartAnimation()
        self.onFech()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_vote_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_vote_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    
    func onUpdateView() {
        self.loadingImg.onStopAnimation()
        self.loadingImg.isHidden = true
        self.voteDetailTableView.reloadData()
        self.voteDetailTableView.isHidden = false
        self.btnVote.isHidden = false
        self.refresher.endRefreshing()
    }
    
    func onClickLink() {
        let link = WUtils.getProposalExplorer(self.chainType!, proposalId!)
        guard let url = URL(string: link) else { return }
        self.onShowSafariWeb(url)
    }
    
    
    @IBAction func onClickVote(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let mainDenom = WUtils.getMainDenom(chainType)
        if (WUtils.isGRPC(chainType!)) {
            if (mProposalDetail_gRPC?.status != Cosmos_Gov_V1beta1_ProposalStatus.votingPeriod ) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (BaseData.instance.mMyDelegations_gRPC.count < 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, TASK_TYPE_VOTE, 0)
            if (BaseData.instance.getAvailableAmount_gRPC(mainDenom).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            let bondingList = BaseData.instance.mMyDelegations
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            let feeAmount = WUtils.getEstimateGasFeeAmount(chainType!, TASK_TYPE_VOTE, 0)
            if (BaseData.instance.availableAmount(mainDenom).compare(feeAmount).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mProposeId = proposalId
        txVC.mProposalTitle = getTitle()
        txVC.mProposer = getProposer()
        txVC.mType = TASK_TYPE_VOTE
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    func getTitle() -> String? {
        if (WUtils.isGRPC(chainType!)) {
            return WUtils.onParseProposalTitle(mProposalDetail_gRPC!)
        } else {
            return mProposal?.getTitle()
        }
    }
    
    func getProposer() -> String? {
        if (WUtils.isGRPC(chainType!)) {
            return ""
        } else {
            return self.mProposer
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.row == 0) {
            return onBindVoteInfo(tableView)
        } else {
            return onBindTally(tableView)
        }
    }
    
    func onBindVoteInfo(_ tableView: UITableView) -> UITableViewCell {
        let cell:VoteInfoTableViewCell? = tableView.dequeueReusableCell(withIdentifier:"VoteInfoTableViewCell") as? VoteInfoTableViewCell
        if (WUtils.isGRPC(chainType!) && mProposalDetail_gRPC != nil) {
            cell?.statusImg.image = WUtils.onParseProposalStatusImg(mProposalDetail_gRPC!)
            cell?.statusTitle.text = WUtils.onParseProposalStatusTxt(mProposalDetail_gRPC!)
            cell?.proposalTitle.text = WUtils.onParseProposalTitle(mProposalDetail_gRPC!)
            cell?.proposerLabel.text = getProposer()
            cell?.proposalTypeLabel.text = String((mProposalDetail_gRPC!.content.typeURL).split(separator: ".").last!)
            cell?.voteStartTime.text = WUtils.onParseProposalStartTime(mProposalDetail_gRPC!)
            cell?.voteEndTime.text = WUtils.onParseProposalEndTime(mProposalDetail_gRPC!)
            cell?.voteDescription.text = WUtils.onParseProposalDescription(mProposalDetail_gRPC!)
            if let coin = WUtils.onParseProposalRequestAmount(mProposalDetail_gRPC!) {
                WUtils.showCoinDp(coin, cell!.requestAmountDenom, cell!.requestAmount, chainType!)
            } else {
                cell!.requestAmountDenom.text = "N/A"
            }

        } else if (mProposal != nil) {
            cell?.statusImg.image = mProposal?.getStatusImg()
            cell?.statusTitle.text = mProposal?.proposal_status
            cell?.proposalTitle.text = mProposal?.getTitle()
            cell?.proposerLabel.text = self.mProposer
            cell?.proposalTypeLabel.text = String((mProposal?.content?.type)!.split(separator: "/").last!)
            cell?.voteStartTime.text = mProposal?.getStartTime()
            cell?.voteEndTime.text = mProposal?.getEndTime()
            cell?.voteDescription.text = mProposal?.content?.value.description
            if (mProposal?.content?.value.amount?.count ?? 0 > 0) {
                WUtils.showCoinDp((mProposal?.content?.value.amount![0])!, cell!.requestAmountDenom, cell!.requestAmount, chainType!)
            } else {
                cell!.requestAmountDenom.text = "N/A"
            }
        }
        cell?.actionLink = {
            self.onClickLink()
        }
        cell?.actionToggle = {
            cell?.voteDescription.isScrollEnabled = !(cell?.voteDescription.isScrollEnabled)!
            self.voteDetailTableView.reloadData()
        }
        return cell!
    }
    
    func onBindTally(_ tableView: UITableView) -> UITableViewCell {
        let cell:VoteTallyTableViewCell? = tableView.dequeueReusableCell(withIdentifier:"VoteTallyTableViewCell") as? VoteTallyTableViewCell
        if (WUtils.isGRPC(chainType!) && mTally_gRPC != nil) {
            cell?.onUpdateCards_gRPC(chainType!, mTally_gRPC!, mVoters_gRPC, mProposalDetail_gRPC)
            cell?.onCheckMyVote_gRPC(mMyVote_gRPC)

        } else if (mTally != nil) {
            cell?.onUpdateCards(chainType!, mTally!, mVoters, mProposal?.proposal_status)
            cell?.onCheckMyVote(mMyVote)
        }
        return cell!
    }
    
    @objc func onFech() {
        if (WUtils.isGRPC(chainType!)) {
            mFetchCnt = 4
            onFetchProposalDetail_gRPC(proposalId!)
            onFetchProposalTally_gRPC(proposalId!)
            onFetchProposalVoterList_gRPC(proposalId!)
            onFetchProposalMyVote_gRPC(proposalId!, account!.account_address)
            
        } else {
            mFetchCnt = 5
            onFetchProposalDetail(proposalId!)
            onFetchTally(proposalId!)
            onFetchMyVote(proposalId!, account!.account_address)
            onFetchProposer(proposalId!)
            onFetchVoteList(proposalId!)
        }
        
    }
    
    var mFetchCnt = 0
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if(mFetchCnt <= 0) {
            self.onUpdateView()
        }
    }
    
    func onFetchProposalDetail(_ id: String) {
        let request = Alamofire.request(BaseNetWork.proposalUrl(chainType, id), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let rawProposal = responseData.object(forKey: "result") as? [String : Any] else {
                        self.onFetchFinished()
                        return
                }
                self.mProposal = Proposal.init(rawProposal)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchProposalDetail ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchTally(_ id: String) {
        let request = Alamofire.request(BaseNetWork.tallyUrl(chainType, id), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawTally = res as? [String : Any], rawTally["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                let cosmosTally = CosmosTally.init(rawTally)
                self.mTally = cosmosTally.result
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTally ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchMyVote(_ id: String, _ address: String) {
        let request = Alamofire.request(BaseNetWork.voteUrl(chainType, id, address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawVote = res as? [String : Any], rawVote["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                let cosmosVote = CosmosVote.init(rawVote)
                self.mMyVote = cosmosVote.result
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyVote ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchProposer(_ id: String) {
        let request = Alamofire.request(BaseNetWork.proposerUrl(chainType, id), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawProposer = res as? [String : Any], rawProposer["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                let cosmosProposer = CosmosProposer.init(rawProposer)
                self.mProposer = cosmosProposer.result.proposer
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchProposer ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchVoteList(_ id: String) {
        let request = Alamofire.request(BaseNetWork.votesUrl(chainType, id), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let votesRaw = res as? [String : Any], let voters = votesRaw["result"] as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for RawVote in voters {
                    self.mVoters.append(Vote.init(RawVote as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchVoteList ", error) }
            }
            self.onFetchFinished()
        }
        
    }

    
    func onFetchProposalDetail_gRPC(_ proposal_id: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Gov_V1beta1_QueryProposalRequest.with {
                $0.proposalID = UInt64(proposal_id)!
            }
            do {
                let response = try Cosmos_Gov_V1beta1_QueryClient(channel: channel).proposal(req).response.wait()
                self.mProposalDetail_gRPC = response.proposal
                
            } catch {
                print("onFetchProposalDetail_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchProposalTally_gRPC(_ proposal_id: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Gov_V1beta1_QueryTallyResultRequest.with {
                $0.proposalID = UInt64(proposal_id)!
            }
            do {
                let response = try Cosmos_Gov_V1beta1_QueryClient(channel: channel).tallyResult(req).response.wait()
                self.mTally_gRPC = response.tally
                
            } catch {
                print("onFetchProposalTally_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchProposalVoterList_gRPC(_ proposal_id: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let page = Cosmos_Base_Query_V1beta1_PageRequest.with {
                $0.limit = 2000
            }
            let req = Cosmos_Gov_V1beta1_QueryVotesRequest.with {
                $0.pagination = page
                $0.proposalID = UInt64(proposal_id)!
            }
            do {
                let response = try Cosmos_Gov_V1beta1_QueryClient(channel: channel).votes(req).response.wait()
                self.mVoters_gRPC = response.votes
                
            } catch {
                print("onFetchProposalVoterList_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchProposalMyVote_gRPC(_ proposal_id: String, _ address: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.chainType!, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Gov_V1beta1_QueryVoteRequest.with {
                $0.voter = address
                $0.proposalID = UInt64(proposal_id)!
            }
            do {
                let response = try Cosmos_Gov_V1beta1_QueryClient(channel: channel).vote(req).response.wait()
                self.mMyVote_gRPC = response.vote
                
            } catch {
                print("onFetchProposalMyVote_gRPC failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
}
