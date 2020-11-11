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
    var refresher: UIRefreshControl!
    
    var proposalId: String?
    var mProposal: Proposal?
    var mIrisProposal: IrisProposal?
    var mProposer: String?
    var mTally: Tally?
    var mVoters = Array<Vote>()
    var mMyVote: Vote?
    
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
        if (chainType == ChainType.COSMOS_MAIN) {
            guard let url = URL(string: EXPLORER_COSMOS_MAIN + "proposals/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            guard let url = URL(string: EXPLORER_IRIS_MAIN + "proposals/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.KAVA_MAIN) {
            guard let url = URL(string: EXPLORER_KAVA_MAIN + "proposals/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            guard let url = URL(string: EXPLORER_KAVA_MAIN + "proposal/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            guard let url = URL(string: EXPLORER_SECRET_MAIN + "proposals/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            guard let url = URL(string: EXPLORER_CERTIK + "governance/proposals/" + proposalId! + "?net=" + WUtils.getChainId(chainType!)) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.IOV_MAIN) {
            guard let url = URL(string: EXPLORER_IOV_MAIN + "proposals/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        } else if (chainType == ChainType.AKASH_MAIN) {
            guard let url = URL(string: EXPLORER_AKASH_MAIN + "proposals/" + proposalId!) else { return }
            self.onShowSafariWeb(url)
            
        }
    }
    
    
    @IBAction func onClickVote(_ sender: UIButton) {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }

            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            if (mMyVote != nil) {
                self.onShowToast(NSLocalizedString("error_already_vote", comment: ""))
                return
            }

            let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "80000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
            let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            if (WUtils.getTokenAmount(balances, SECRET_MAIN_DENOM).compare(NSDecimalNumber.init(string: "25000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
            let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            if (WUtils.getTokenAmount(balances, CERTIK_MAIN_DENOM).compare(NSDecimalNumber.init(string: "5000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
            let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "100000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.AKASH_MAIN) {
            if (mProposal?.proposal_status != Proposal.PROPOSAL_VOTING) {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
            let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
            if (WUtils.getTokenAmount(balances, AKASH_MAIN_DENOM).compare(NSDecimalNumber.init(string: "2500")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))
            return
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
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN ||
                chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN) {
            return mProposal?.getTitle()
        } else if (chainType == ChainType.IRIS_MAIN) {
            return mIrisProposal?.getTitle()
        }
        return ""
    }
    
    func getProposer() -> String? {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN ||
                chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN) {
            return self.mProposer
        } else if (chainType == ChainType.IRIS_MAIN) {
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
        if ((chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN || chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN) && mProposal != nil) {
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
            
        } else if (chainType == ChainType.IRIS_MAIN && mIrisProposal != nil) {
            cell?.statusImg.image = mIrisProposal?.getStatusImg()
            cell?.statusTitle.text = mIrisProposal?.value?.basicProposal?.proposal_status
            cell?.proposalTitle.text = mIrisProposal?.getTitle()
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
        if ((chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN ||
                chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN) && mTally != nil) {
            cell?.onUpdateCards(mTally!, mVoters, mProposal?.proposal_status)
            cell?.onCheckMyVote(mMyVote)
            
        } else if (chainType == ChainType.IRIS_MAIN && mIrisProposal != nil && mIrisProposal?.value?.basicProposal?.tally_result != nil) {
            cell?.onUpdateCardIris((mIrisProposal?.value?.basicProposal?.tally_result)!, mVoters)
            cell?.onCheckMyVote(mMyVote)
        }
        return cell!
    }
    
    @objc func onFech() {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.BAND_MAIN || chainType == ChainType.SECRET_MAIN ||
                chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST || chainType == ChainType.IOV_MAIN || chainType == ChainType.AKASH_MAIN) {
            mFetchCnt = 5
            onFetchProposalDetail(proposalId!)
            onFetchTally(proposalId!)
            onFetchMyVote(proposalId!, account!.account_address)
            onFetchProposer(proposalId!)
            onFetchVoteList(proposalId!)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            mFetchCnt = 2
            self.mVoters.removeAll()
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
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_PROPOSALS + "/" + id
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_PROPOSALS + "/" + id
        } else if (chainType == ChainType.SECRET_MAIN) {
            url = SECRET_PROPOSALS + "/" + id
        } else if (chainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_PROPOSALS + "/" + id
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_PROPOSALS + "/" + id
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_PROPOSALS + "/" + id
        } else if (chainType == ChainType.AKASH_MAIN) {
            url = AKASH_PROPOSALS + "/" + id
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
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_PROPOSALS + "/" + id + "/" + COSMOS_URL_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id + "/" + KAVA_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_PROPOSALS + "/" + id + "/" + BAND_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.SECRET_MAIN) {
            url = SECRET_PROPOSALS + "/" + id + "/" + SECRET_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_PROPOSALS + "/" + id + "/" + CERTIK_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_PROPOSALS + "/" + id + "/" + CERTIK_TEST_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_PROPOSALS + "/" + id + "/" + IOV_PROPOSALS_TALLY_TAIL
        } else if (chainType == ChainType.AKASH_MAIN) {
            url = AKASH_PROPOSALS + "/" + id + "/" + AKASH_PROPOSALS_TALLY_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
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
        var url = ""
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.SECRET_MAIN) {
            url = SECRET_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_PROPOSALS + "/" + id +  "/votes/" + address
        } else if (chainType == ChainType.AKASH_MAIN) {
            url = AKASH_PROPOSALS + "/" + id +  "/votes/" + address
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
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
        var url = ""
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.SECRET_MAIN) {
            url = SECRET_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_PROPOSALS + "/" + id +  "/proposer"
        } else if (chainType == ChainType.AKASH_MAIN) {
            url = AKASH_PROPOSALS + "/" + id +  "/proposer"
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
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
        var url = ""
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.SECRET_MAIN) {
            url = SECRET_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_PROPOSALS + "/" + id +  "/votes"
        } else if (chainType == ChainType.AKASH_MAIN) {
            url = AKASH_PROPOSALS + "/" + id +  "/votes"
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
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
                    self.mVoters.append(Vote.init(RawVote as! [String : Any]))
                }
                self.mMyVote = WUtils.getMyIrisVote(self.mVoters, self.account!.account_address)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIrisVoteList ", error) }
            }
            self.onFetchFinished()
        }
    }
}
