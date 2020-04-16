//
//  StepHtlcSend0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/15.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class StepHtlcSend0ViewController: BaseViewController, SBCardPopupDelegate {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var fromChainImg: UIImageView!
    @IBOutlet weak var fromChainTxt: UILabel!
    @IBOutlet weak var toChainCard: CardView!
    @IBOutlet weak var toChainImg: UIImageView!
    @IBOutlet weak var toChainText: UILabel!
    
    var pageHolderVC: StepGenTxViewController!
    var toChainList = Array<ChainType>()
    var toChain: ChainType?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pageHolderVC = self.parent as? StepGenTxViewController
        
        self.toChainCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToChain (_:))))
        self.toChainList = ChainType.getHtlcSendable(pageHolderVC.chainType!)
        if (self.toChainList.count <= 0) { pageHolderVC.onBeforePage() }
        self.toChain = self.toChainList[0]
        self.updateView()
    }
    
    func updateView() {
        WUtils.dpChainInfo(pageHolderVC.chainType!, fromChainImg, fromChainTxt)
        WUtils.dpChainInfo(toChain!, toChainImg, toChainText)
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
        
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        pageHolderVC.mHtlcToChain = self.toChain
        self.btnCancel.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }
    
    @objc func onClickToChain (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = popupVC.SELECT_POPUP_HTLC_CHAIN
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
        
    }
    
    func SBCardPopupResponse(result: Int) {
        self.toChain = self.toChainList[result]
        self.updateView()
    }
    
}
