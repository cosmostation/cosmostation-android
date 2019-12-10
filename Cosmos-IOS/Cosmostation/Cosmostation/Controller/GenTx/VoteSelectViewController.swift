//
//  VoteSelectViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 10/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class VoteSelectViewController: BaseViewController {
    
    @IBOutlet weak var proposalTitle: UILabel!
    @IBOutlet weak var proposer: UILabel!
    @IBOutlet weak var btnYes: UIButton!
    @IBOutlet weak var btnNo: UIButton!
    @IBOutlet weak var btnVeto: UIButton!
    @IBOutlet weak var btnAbstain: UIButton!
    @IBOutlet weak var checkYes: UIImageView!
    @IBOutlet weak var checkNo: UIImageView!
    @IBOutlet weak var checkVeto: UIImageView!
    @IBOutlet weak var checkAbstain: UIImageView!
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var bntNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        proposalTitle.text = pageHolderVC.mProposalTitle
        proposer.text = pageHolderVC.mProposer
    }

    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.bntNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (pageHolderVC.mVoteOpinion == "Yes" ||
            pageHolderVC.mVoteOpinion == "No" ||
            pageHolderVC.mVoteOpinion == "Veto" ||
            pageHolderVC.mVoteOpinion == "Abstain") {
            self.btnCancel.isUserInteractionEnabled = false
            self.bntNext.isUserInteractionEnabled = false
            pageHolderVC.onNextPage()
            
        } else {
            self.onShowToast(NSLocalizedString("error_no_opinion", comment: ""))
            return
        }
    }
    
    @IBAction func onClickYes(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        checkYes.image = checkYes.image?.withRenderingMode(.alwaysTemplate)
        checkYes.tintColor = UIColor.white
        pageHolderVC.mVoteOpinion = "Yes"
        
    }
    
    @IBAction func onClickNo(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        checkNo.image = checkNo.image?.withRenderingMode(.alwaysTemplate)
        checkNo.tintColor = UIColor.white
        pageHolderVC.mVoteOpinion = "No"
        
    }
    
    @IBAction func onClickVeto(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        checkVeto.image = checkVeto.image?.withRenderingMode(.alwaysTemplate)
        checkVeto.tintColor = UIColor.white
        pageHolderVC.mVoteOpinion = "Veto"
        
    }
    
    @IBAction func onClickAbstain(_ sender: UIButton) {
        initBtns()
        sender.borderColor = UIColor.white
        checkAbstain.image = checkAbstain.image?.withRenderingMode(.alwaysTemplate)
        checkAbstain.tintColor = UIColor.white
        pageHolderVC.mVoteOpinion = "Abstain"
        
    }
    
    func initBtns() {
        btnYes.borderColor = UIColor(hexString: "#4b4f54")
        btnNo.borderColor = UIColor(hexString: "#4b4f54")
        btnVeto.borderColor = UIColor(hexString: "#4b4f54")
        btnAbstain.borderColor = UIColor(hexString: "#4b4f54")
        checkYes.image = checkYes.image?.withRenderingMode(.alwaysTemplate)
        checkYes.tintColor = UIColor(hexString: "#4b4f54")
        checkNo.image = checkNo.image?.withRenderingMode(.alwaysTemplate)
        checkNo.tintColor = UIColor(hexString: "#4b4f54")
        checkVeto.image = checkVeto.image?.withRenderingMode(.alwaysTemplate)
        checkVeto.tintColor = UIColor(hexString: "#4b4f54")
        checkAbstain.image = checkAbstain.image?.withRenderingMode(.alwaysTemplate)
        checkAbstain.tintColor = UIColor(hexString: "#4b4f54")
    }
    
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnCancel.isUserInteractionEnabled = true
    }
    
    
    
    
}
