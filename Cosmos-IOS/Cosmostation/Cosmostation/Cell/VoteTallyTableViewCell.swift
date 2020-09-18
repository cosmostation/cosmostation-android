//
//  VoteTallyTableViewCell.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class VoteTallyTableViewCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    @IBOutlet weak var cardYes: CardView!
    @IBOutlet weak var progressYes: UIProgressView!
    @IBOutlet weak var percentYes: UILabel!
    @IBOutlet weak var myVoteYes: UIImageView!
    @IBOutlet weak var imgYes: UIImageView!
    @IBOutlet weak var cntYes: UILabel!
    
    @IBOutlet weak var cardNo: CardView!
    @IBOutlet weak var progressNo: UIProgressView!
    @IBOutlet weak var percentNo: UILabel!
    @IBOutlet weak var myVoteNo: UIImageView!
    @IBOutlet weak var imgNo: UIImageView!
    @IBOutlet weak var cntNo: UILabel!
    
    @IBOutlet weak var cardVeto: CardView!
    @IBOutlet weak var progressVeto: UIProgressView!
    @IBOutlet weak var percentVeto: UILabel!
    @IBOutlet weak var myVoteVeto: UIImageView!
    @IBOutlet weak var imgVeto: UIImageView!
    @IBOutlet weak var cntVeto: UILabel!
    
    @IBOutlet weak var cardAbstain: CardView!
    @IBOutlet weak var propressAbstain: UIProgressView!
    @IBOutlet weak var percentAbstain: UILabel!
    @IBOutlet weak var myVoteAbstain: UIImageView!
    @IBOutlet weak var imgAbstain: UIImageView!
    @IBOutlet weak var cntAbstain: UILabel!
    
    @IBOutlet weak var turnoutTitle: UILabel!
    @IBOutlet weak var turnoutRate: UILabel!
    
    func onCheckMyVote (_ myVote:Vote?) {
        if (myVote == nil) { return }
        self.cardYes.borderColor = UIColor.init(hexString: "#9CA2AC", alpha: 1.0)
        self.cardNo.borderColor = UIColor.init(hexString: "#9CA2AC", alpha: 1.0)
        self.cardVeto.borderColor = UIColor.init(hexString: "#9CA2AC", alpha: 1.0)
        self.cardAbstain.borderColor = UIColor.init(hexString: "#9CA2AC", alpha: 1.0)
        self.cardYes.borderWidth = 1
        self.cardNo.borderWidth = 1
        self.cardVeto.borderWidth = 1
        self.cardAbstain.borderWidth = 1
        
        if (myVote?.option.caseInsensitiveCompare(Vote.OPTION_YES) == .orderedSame) {
            self.myVoteYes.isHidden = false
            self.cardYes.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            
        } else if (myVote?.option.caseInsensitiveCompare(Vote.OPTION_NO) == .orderedSame) {
            self.myVoteNo.isHidden = false
            self.cardNo.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            
        } else if (myVote?.option.caseInsensitiveCompare(Vote.OPTION_VETO) == .orderedSame) {
            self.myVoteVeto.isHidden = false
            self.cardVeto.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            
        } else if (myVote?.option.caseInsensitiveCompare(Vote.OPTION_ABSTAIN) == .orderedSame) {
            self.myVoteAbstain.isHidden = false
            self.cardAbstain.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
        }
    }
    
    func onUpdateCards(_ tally:Tally, _ voters:Array<Vote>, _ status: String?) {
        progressYes.progress = tally.getYes().floatValue / 100
        progressNo.progress = tally.getNo().floatValue / 100
        progressVeto.progress = tally.getVeto().floatValue / 100
        propressAbstain.progress = tally.getAbstain().floatValue / 100

        percentYes.attributedText = WUtils.displayPercent(tally.getYes(), font: percentYes.font)
        percentNo.attributedText = WUtils.displayPercent(tally.getNo(), font: percentYes.font)
        percentVeto.attributedText = WUtils.displayPercent(tally.getVeto(), font: percentYes.font)
        percentAbstain.attributedText = WUtils.displayPercent(tally.getAbstain(), font: percentYes.font)
        
        if (status == Proposal.PROPOSAL_VOTING) {
            imgYes.isHidden = false
            imgNo.isHidden = false
            imgVeto.isHidden = false
            imgAbstain.isHidden = false
            cntYes.isHidden = false
            cntNo.isHidden = false
            cntVeto.isHidden = false
            cntAbstain.isHidden = false

            cntYes.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_YES)
            cntNo.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_NO)
            cntVeto.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_VETO)
            cntAbstain.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_ABSTAIN)
            
            turnoutRate.isHidden = false
            turnoutTitle.isHidden = false
            turnoutRate.attributedText = WUtils.displayPercent(tally.getTurnout(), font: turnoutRate.font)
            
        }
    }
    
    func onUpdateCardIris(_ tally:Tally, _ voters:Array<Vote>) {
        progressYes.progress = tally.getYes().floatValue / 100
        progressNo.progress = tally.getNo().floatValue / 100
        progressVeto.progress = tally.getVeto().floatValue / 100
        propressAbstain.progress = tally.getAbstain().floatValue / 100

        percentYes.attributedText = WUtils.displayPercent(tally.getYes(), font: percentYes.font)
        percentNo.attributedText = WUtils.displayPercent(tally.getNo(), font: percentYes.font)
        percentVeto.attributedText = WUtils.displayPercent(tally.getVeto(), font: percentYes.font)
        percentAbstain.attributedText = WUtils.displayPercent(tally.getAbstain(), font: percentYes.font)

        imgYes.isHidden = false
        imgNo.isHidden = false
        imgVeto.isHidden = false
        imgAbstain.isHidden = false
        cntYes.isHidden = false
        cntNo.isHidden = false
        cntVeto.isHidden = false
        cntAbstain.isHidden = false

        cntYes.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_YES)
        cntNo.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_NO)
        cntVeto.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_VETO)
        cntAbstain.text = WUtils.getIrisVoterTypeCnt(voters, Vote.OPTION_ABSTAIN)
    }
    
}
