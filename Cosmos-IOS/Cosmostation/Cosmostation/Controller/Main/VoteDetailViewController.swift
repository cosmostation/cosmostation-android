//
//  VoteDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class VoteDetailViewController: BaseViewController {

    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var stateImg: UIImageView!
    @IBOutlet weak var stateMsg: UILabel!
    @IBOutlet weak var webLink: UIButton!
    
    @IBOutlet weak var voteTitle: UILabel!
    @IBOutlet weak var voteProposer: UILabel!
    @IBOutlet weak var voteProposalType: UILabel!
    @IBOutlet weak var voteStartTime: UILabel!
    @IBOutlet weak var voteEndTime: UILabel!
    @IBOutlet weak var voteDescription: UITextView!
    
    @IBOutlet weak var cardYes: CardView!
    @IBOutlet weak var titleYes: UILabel!
    @IBOutlet weak var sliderYes: CustomSlider!
    @IBOutlet weak var percentYes: UILabel!
    @IBOutlet weak var iconYes: UIImageView!
    @IBOutlet weak var cntYes: UILabel!
    @IBOutlet weak var doneYes: UIImageView!
    
    @IBOutlet weak var cardNo: CardView!
    @IBOutlet weak var titleNo: UILabel!
    @IBOutlet weak var sliderNo: CustomSlider!
    @IBOutlet weak var percentNo: UILabel!
    @IBOutlet weak var iconNo: UIImageView!
    @IBOutlet weak var cntNo: UILabel!
    @IBOutlet weak var doneNo: UIImageView!
    
    @IBOutlet weak var cardVeto: CardView!
    @IBOutlet weak var titleVeto: UILabel!
    @IBOutlet weak var sliderVeto: CustomSlider!
    @IBOutlet weak var percentVeto: UILabel!
    @IBOutlet weak var iconVeto: UIImageView!
    @IBOutlet weak var cntVeto: UILabel!
    @IBOutlet weak var doneVeto: UIImageView!
    
    @IBOutlet weak var cardAbstain: CardView!
    @IBOutlet weak var titleAbstain: UILabel!
    @IBOutlet weak var sliderAbstain: CustomSlider!
    @IBOutlet weak var percentAbstain: UILabel!
    @IBOutlet weak var iconAbstain: UIImageView!
    @IBOutlet weak var cntAbstain: UILabel!
    @IBOutlet weak var doneAbstain: UIImageView!
    
    var msg = ""
    var proposalId: String?
    var contentHeight: CGFloat?

    var mFetchCnt = 0
    var irisProposal: IrisProposal?
    var irisVotes = Array<IrisVote>()
    var myIrisVote: IrisVote?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
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
    
    func onFech() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mFetchCnt = 2
            self.irisVotes.removeAll()
            onFetchIrisProposalDetail(proposalId!)
            onFetchIrisVoteList(proposalId!)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            
        }
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if(mFetchCnt <= 0) {
            self.onUpdateView()
        }
    }
    
    func onUpdateView() {
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (irisProposal?.value?.basicProposal?.proposal_status == "DepositPeriod") {
                stateImg.image = UIImage.init(named: "depositImg")
            } else if (irisProposal?.value?.basicProposal?.proposal_status == "VotingPeriod") {
                stateImg.image = UIImage.init(named: "votingImg")
            } else if (irisProposal?.value?.basicProposal?.proposal_status == "Rejected") {
                stateImg.image = UIImage.init(named: "rejectedImg")
            } else if (irisProposal?.value?.basicProposal?.proposal_status == "Passed") {
                stateImg.image = UIImage.init(named: "passedImg")
            }
            
            stateMsg.text = irisProposal?.value?.basicProposal?.proposal_status
            voteTitle.text = "# " + (irisProposal?.value?.basicProposal?.proposal_id)! + ". "  + (irisProposal?.value?.basicProposal?.title)!
            voteProposer.text = irisProposal?.value?.basicProposal?.proposer
            voteProposalType.text = WUtils.proposalType(irisProposal!.type)
            
            if (irisProposal?.value?.basicProposal?.proposal_status == "DepositPeriod") {
                voteStartTime.text = NSLocalizedString("waiting_deposit", comment: "")
                voteStartTime.text = NSLocalizedString("waiting_deposit", comment: "")
                
            } else {
                voteStartTime.text = WUtils.nodeTimetoString(input: (irisProposal?.value?.basicProposal?.voting_start_time)!)
                voteEndTime.text = WUtils.nodeTimetoString(input: (irisProposal?.value?.basicProposal?.voting_end_time)!)
            }
    
            msg = (irisProposal?.value?.basicProposal?.description)!
            self.voteDescription.text = msg
            contentHeight = voteDescription.contentSize.height
            if (Int(contentHeight!) < 40) {
                contentHeight = 40;
            }
            
            cntYes.text = WUtils.getIrisVoterTypeCnt(irisVotes, "Yes")
            cntNo.text = WUtils.getIrisVoterTypeCnt(irisVotes, "No")
            cntVeto.text = WUtils.getIrisVoterTypeCnt(irisVotes, "NoWithVeto")
            cntAbstain.text = WUtils.getIrisVoterTypeCnt(irisVotes, "Abstain")
            
            if (irisProposal?.value?.basicProposal?.proposal_status == "DepositPeriod" ||
                irisProposal?.value?.basicProposal?.proposal_status == "VotingPeriod") {
                sliderYes.setValue(0, animated: false)
                sliderNo.setValue(0, animated: false)
                sliderVeto.setValue(0, animated: false)
                sliderAbstain.setValue(0, animated: false)
                percentYes.text = "??%"
                percentNo.text = "??%"
                percentVeto.text = "??%"
                percentAbstain.text = "??%"
                
            } else {
                sliderYes.setValue(irisProposal!.getYes().floatValue, animated: false)
                sliderNo.setValue(irisProposal!.getNo().floatValue, animated: false)
                sliderVeto.setValue(irisProposal!.getVeto().floatValue, animated: false)
                sliderAbstain.setValue(irisProposal!.getAbstain().floatValue, animated: false)
                percentYes.attributedText = WUtils.displayPercent(irisProposal!.getYes(), font: percentYes.font)
                percentNo.attributedText = WUtils.displayPercent(irisProposal!.getNo(), font: percentYes.font)
                percentVeto.attributedText = WUtils.displayPercent(irisProposal!.getVeto(), font: percentYes.font)
                percentAbstain.attributedText = WUtils.displayPercent(irisProposal!.getAbstain(), font: percentYes.font)
            }
            
            myIrisVote = WUtils.getMyIrisVote(irisVotes, account!.account_address)
            
            if let myOpinoin = myIrisVote?.option {
                titleYes.textColor = UIColor(hexString: "#424548")
                titleNo.textColor = UIColor(hexString: "#424548")
                titleVeto.textColor = UIColor(hexString: "#424548")
                titleAbstain.textColor = UIColor(hexString: "#424548")
                
                sliderYes.tintColor = UIColor(hexString: "#7a7f88")
                sliderNo.tintColor = UIColor(hexString: "#7a7f88")
                sliderVeto.tintColor = UIColor(hexString: "#7a7f88")
                sliderAbstain.tintColor = UIColor(hexString: "#7a7f88")
                
                percentYes.textColor = UIColor(hexString: "#424548")
                percentNo.textColor = UIColor(hexString: "#424548")
                percentVeto.textColor = UIColor(hexString: "#424548")
                percentAbstain.textColor = UIColor(hexString: "#424548")
                
                cntYes.textColor = UIColor(hexString: "#424548")
                cntNo.textColor = UIColor(hexString: "#424548")
                cntVeto.textColor = UIColor(hexString: "#424548")
                cntAbstain.textColor = UIColor(hexString: "#424548")
                
                iconYes.image = iconYes.image?.withRenderingMode(.alwaysTemplate)
                iconYes.tintColor = UIColor(hexString: "#424548")
                iconNo.image = iconNo.image?.withRenderingMode(.alwaysTemplate)
                iconNo.tintColor = UIColor(hexString: "#424548")
                iconVeto.image = iconVeto.image?.withRenderingMode(.alwaysTemplate)
                iconVeto.tintColor = UIColor(hexString: "#424548")
                iconAbstain.image = iconAbstain.image?.withRenderingMode(.alwaysTemplate)
                iconAbstain.tintColor = UIColor(hexString: "#424548")
                
                
                if (myOpinoin == "Yes") {
                    titleYes.textColor = UIColor(hexString: "#a67dfc")
                    sliderYes.tintColor = UIColor(hexString: "#a67dfc")
                    percentYes.textColor = UIColor.white
                    cntYes.textColor = UIColor.white
                    iconYes.image = iconYes.image?.withRenderingMode(.alwaysTemplate)
                    iconYes.tintColor = UIColor.white
                    doneYes.isHidden = false
                    cardYes.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
                    cardYes.borderWidth = 1
                    
                } else if (myOpinoin == "No") {
                    titleNo.textColor = UIColor(hexString: "#57dca8")
                    sliderNo.tintColor = UIColor(hexString: "#57dca8")
                    percentNo.textColor = UIColor.white
                    cntNo.textColor = UIColor.white
                    iconNo.image = iconNo.image?.withRenderingMode(.alwaysTemplate)
                    iconNo.tintColor = UIColor.white
                    doneNo.isHidden = false
                    cardNo.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
                    cardNo.borderWidth = 1
                    
                } else if (myOpinoin == "NoWithVeto") {
                    titleVeto.textColor = UIColor(hexString: "#4de8f1")
                    sliderVeto.tintColor = UIColor(hexString: "#4de8f1")
                    percentVeto.textColor = UIColor.white
                    cntVeto.textColor = UIColor.white
                    iconVeto.image = iconVeto.image?.withRenderingMode(.alwaysTemplate)
                    iconVeto.tintColor = UIColor.white
                    doneVeto.isHidden = false
                    cardVeto.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
                    cardVeto.borderWidth = 1
                                   
                } else if (myOpinoin == "Abstain") {
                    titleAbstain.textColor = UIColor(hexString: "#9ea1a7")
                    sliderAbstain.tintColor = UIColor(hexString: "#9ea1a7")
                    percentAbstain.textColor = UIColor.white
                    cntAbstain.textColor = UIColor.white
                    iconAbstain.image = iconAbstain.image?.withRenderingMode(.alwaysTemplate)
                    iconAbstain.tintColor = UIColor.white
                    doneAbstain.isHidden = false
                    cardAbstain.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
                    cardAbstain.borderWidth = 1
                    
                }
            }
            
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            
        }
    }
    
    @IBAction func onClickLink(_ sender: UIButton) {
        print("onClickLink")
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            guard let url = URL(string: "https://irishub.mintscan.io/proposals/" + (proposalId)!) else { return }
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true, completion: nil)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            
        }
    }
    
    @IBAction func onClickExpend(_ sender: UIButton) {
        if (voteDescription.isScrollEnabled) {
            sender.setImage(UIImage(named: "arrowUp"), for: .normal)
            voteDescription.isScrollEnabled = false
            
            var frame = self.voteDescription.frame;
            frame.size.height = contentHeight!
            self.voteDescription.frame = frame;
            
            voteDescription.text = msg
            
        } else {
            sender.setImage(UIImage(named: "arrowDown"), for: .normal)
            var temp = voteDescription.sizeThatFits(voteDescription.frame.size)
            temp.height = 40
            self.voteDescription.contentSize = temp
            
            voteDescription.isScrollEnabled = true
            voteDescription.translatesAutoresizingMaskIntoConstraints = true
            voteDescription.sizeToFit()
            self.voteDescription.text = msg
            
            var frame = self.voteDescription.frame;
            frame.size.height = 40
            self.voteDescription.frame = frame;
        }
    }
    
    @IBAction func onClickVote(_ sender: UIButton) {
        print("onClickVote")
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        
        if (chainType == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            return
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (irisProposal == nil) {
                self.onShowToast(NSLocalizedString("error_not_voting_not", comment: ""))
                return
            }
            
            if (irisProposal?.value?.basicProposal?.proposal_status != "VotingPeriod") {
                self.onShowToast(NSLocalizedString("error_not_voting_period", comment: ""))
                return
            }
            
            if (!account!.account_has_private) {
                self.onShowAddMenomicDialog()
                return
            }
            
            if (bondingList.count <= 0) {
                self.onShowToast(NSLocalizedString("error_no_bonding_no_vote", comment: ""))
                return
            }
            
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
            if (myIrisVote != nil) {
                self.onShowToast(NSLocalizedString("error_already_vote", comment: ""))
                return
            }
            
            let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
            txVC.mProposeId = proposalId
            txVC.mProposalTitle = voteTitle.text
            txVC.mProposer = voteProposer.text
            txVC.mType = IRIS_MSG_TYPE_VOTE
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txVC, animated: true)
            
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            return
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
                self.irisProposal = IrisProposal.init(rawProposal)
                
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
                    self.irisVotes.append(IrisVote.init(RawVote as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchIrisVoteList ", error) }
            }
            self.onFetchFinished()
        }
    }
}
