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

class VoteDetailsViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var voteDetailTableView: UITableView!
    @IBOutlet weak var btnVote: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    var proposalId: String?
    
    var mProposal: Proposal?
    var mTally: CosmosTally?
    var mMyVote: CosmosVote?
    
    var mIrisProposal: IrisProposal?
    var mIrisVotes = Array<IrisVote>()
    var mIrisMyVote: IrisVote?
    
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
    }
    
    
    func onClickLink() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            guard let url = URL(string: "https://www.mintscan.io/proposals/" + proposalId!) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            guard let url = URL(string: "https://iris.mintscan.io/proposals/" + proposalId!) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            guard let url = URL(string: "https://kava.mintscan.io/proposals/" + proposalId!) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        }
    }
    
    
    @IBAction func onClickVote(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }

            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            if (mIrisMyVote != nil) {
                self.onShowToast(NSLocalizedString("error_already_vote", comment: ""))
                return
            }

            let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "80000000000000000")).rawValue < 0) {
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
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return mProposal?.getTitle()
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return mIrisProposal?.getTitle()
        }
        return ""
    }
    
    func getProposer() -> String? {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return mIrisProposal?.value?.basicProposal?.proposer
        }
        return ""
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
        if ((chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) && mProposal != nil) {
            cell?.statusImg.image = mProposal?.getStatusImg()
            cell?.statusTitle.text = mProposal?.proposal_status
            cell?.proposalTitle.text = mProposal?.getTitle()
            cell?.proposalTitle.adjustsFontSizeToFitWidth = true
            cell?.proposerLabel.text = "-"
            cell?.proposalTypeLabel.text = String((mProposal?.content?.type)!.split(separator: "/").last!)
            cell?.voteStartTime.text = mProposal?.getStartTime()
            cell?.voteEndTime.text = mProposal?.getEndTime()
            cell?.voteDescription.text = mProposal?.content?.value.description
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN && mIrisProposal != nil) {
            cell?.statusImg.image = mIrisProposal?.getStatusImg()
            cell?.statusTitle.text = mIrisProposal?.value?.basicProposal?.proposal_status
            cell?.proposalTitle.text = mIrisProposal?.getTitle()
            cell?.proposalTitle.adjustsFontSizeToFitWidth = true
            cell?.proposerLabel.text = mIrisProposal?.value?.basicProposal?.proposer
            cell?.proposalTypeLabel.text = String((mIrisProposal?.type)!.split(separator: "/").last!)
            cell?.voteStartTime.text = mIrisProposal?.getStartTime()
            cell?.voteEndTime.text = mIrisProposal?.getEndTime()
            cell?.voteDescription.text = mIrisProposal?.value?.basicProposal?.description
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
        if ((chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) && mTally != nil) {
            cell?.onUpdateCards(mTally!)
            cell?.onCheckMyVote(mMyVote)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN && mIrisProposal != nil) {
            cell?.onUpdateIrisCards(mIrisProposal!, mIrisVotes)
            cell?.onCheckIrisMyVote(mIrisMyVote)
        }
        return cell!
    }
    
    func onFech() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            mFetchCnt = 3
            onFetchProposalDetail(proposalId!)
            onFetchTally(proposalId!)
            onFetchMyVote(proposalId!, account!.account_address)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mFetchCnt = 2
            self.mIrisVotes.removeAll()
            self.mIrisMyVote = nil
            onFetchIrisProposalDetail(proposalId!)
            onFetchIrisVoteList(proposalId!)
            
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
        var url = ""
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_PROPOSALS + "/" + id
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
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
        var url = ""
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_PROPOSALS + "/" + id + "/" + CSS_LCD_URL_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id + "/" + KAVA_PROPOSALS_TALLY_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawTally = res as? [String : Any], rawTally["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                self.mTally = CosmosTally.init(rawTally)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTally ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchMyVote(_ id: String, _ address: String) {
        var url = ""
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id +  "/votes/" + address
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawVote = res as? [String : Any], rawVote["error"] == nil else {
                    self.onFetchFinished()
                    return
                }
                self.mMyVote = CosmosVote.init(rawVote)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMyVote ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    
    func onFetchIrisProposalDetail(_ id: String) {
        let url = IRIS_LCD_URL_PROPOSALS + "/" + id
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawProposal = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                self.mIrisProposal = IrisProposal.init(rawProposal)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIrisProposalDetail ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIrisVoteList(_ id: String) {
        let url = IRIS_LCD_URL_PROPOSALS + "/" + id + "/votes"
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawVotes = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for RawVote in rawVotes {
                    self.mIrisVotes.append(IrisVote.init(RawVote as! [String : Any]))
                }
                self.mIrisMyVote = WUtils.getMyIrisVote(self.mIrisVotes, self.account!.account_address)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIrisVoteList ", error) }
            }
            self.onFetchFinished()
        }
    }
}
