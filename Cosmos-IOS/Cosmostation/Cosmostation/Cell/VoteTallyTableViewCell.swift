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
    
    
    func onUpdateCards(_ tally:CosmosTally) {
        progressYes.progress = tally.getYes().floatValue / 100
        progressNo.progress = tally.getNo().floatValue / 100
        progressVeto.progress = tally.getVeto().floatValue / 100
        propressAbstain.progress = tally.getAbstain().floatValue / 100
        
        percentYes.attributedText = WUtils.displayPercent(tally.getYes(), font: percentYes.font)
        percentNo.attributedText = WUtils.displayPercent(tally.getNo(), font: percentYes.font)
        percentVeto.attributedText = WUtils.displayPercent(tally.getVeto(), font: percentYes.font)
        percentAbstain.attributedText = WUtils.displayPercent(tally.getAbstain(), font: percentYes.font)
    }
    
    
    func onCheckMyVote (_ myVote:CosmosVote?) {
        if (myVote == nil) { return }
        if (myVote?.result.option.caseInsensitiveCompare(CosmosVote.OPTION_YES) == .orderedSame) {
            self.myVoteYes.isHidden = false
            self.cardYes.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardYes.borderWidth = 1
            
        } else if (myVote?.result.option.caseInsensitiveCompare(CosmosVote.OPTION_NO) == .orderedSame) {
            self.myVoteNo.isHidden = false
            self.cardNo.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardNo.borderWidth = 1
            
        } else if (myVote?.result.option.caseInsensitiveCompare(CosmosVote.OPTION_VETO) == .orderedSame) {
            self.myVoteVeto.isHidden = false
            self.cardVeto.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardVeto.borderWidth = 1
            
        } else if (myVote?.result.option.caseInsensitiveCompare(CosmosVote.OPTION_ABSTAIN) == .orderedSame) {
            self.myVoteAbstain.isHidden = false
            self.cardAbstain.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardAbstain.borderWidth = 1
        }
    }
    
    func onUpdateIrisCards(_ irisProposal:IrisProposal, _ voters:Array<IrisVote>) {
        progressYes.progress = irisProposal.getYes().floatValue / 100
        progressNo.progress = irisProposal.getNo().floatValue / 100
        progressVeto.progress = irisProposal.getVeto().floatValue / 100
        propressAbstain.progress = irisProposal.getAbstain().floatValue / 100
        
        percentYes.attributedText = WUtils.displayPercent(irisProposal.getYes(), font: percentYes.font)
        percentNo.attributedText = WUtils.displayPercent(irisProposal.getNo(), font: percentYes.font)
        percentVeto.attributedText = WUtils.displayPercent(irisProposal.getVeto(), font: percentYes.font)
        percentAbstain.attributedText = WUtils.displayPercent(irisProposal.getAbstain(), font: percentYes.font)
        
        imgYes.isHidden = false
        imgNo.isHidden = false
        imgVeto.isHidden = false
        imgAbstain.isHidden = false
        cntYes.isHidden = false
        cntNo.isHidden = false
        cntVeto.isHidden = false
        cntAbstain.isHidden = false
        
        cntYes.text = WUtils.getIrisVoterTypeCnt(voters, IrisVote.OPTION_YES)
        cntNo.text = WUtils.getIrisVoterTypeCnt(voters, IrisVote.OPTION_NO)
        cntVeto.text = WUtils.getIrisVoterTypeCnt(voters, IrisVote.OPTION_VETO)
        cntAbstain.text = WUtils.getIrisVoterTypeCnt(voters, IrisVote.OPTION_ABSTAIN)
    }
    
    func onCheckIrisMyVote (_ myVote:IrisVote?) {
        if (myVote == nil) { return }
        if (myVote?.option.caseInsensitiveCompare(IrisVote.OPTION_YES) == .orderedSame) {
            self.myVoteYes.isHidden = false
            self.cardYes.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardYes.borderWidth = 1
            
        } else if (myVote?.option.caseInsensitiveCompare(IrisVote.OPTION_NO) == .orderedSame) {
            self.myVoteNo.isHidden = false
            self.cardNo.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardNo.borderWidth = 1
            
        } else if (myVote?.option.caseInsensitiveCompare(IrisVote.OPTION_VETO) == .orderedSame) {
            self.myVoteVeto.isHidden = false
            self.cardVeto.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardVeto.borderWidth = 1
            
        } else if (myVote?.option.caseInsensitiveCompare(IrisVote.OPTION_ABSTAIN) == .orderedSame) {
            self.myVoteAbstain.isHidden = false
            self.cardAbstain.borderColor = UIColor.init(hexString: "#e4185d", alpha: 1.0)
            self.cardAbstain.borderWidth = 1
        }
    }
    
}
