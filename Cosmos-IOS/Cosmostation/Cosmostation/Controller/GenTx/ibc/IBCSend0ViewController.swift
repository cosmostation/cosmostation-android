//
//  IBCSend0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/24.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class IBCSend0ViewController: BaseViewController, SBCardPopupDelegate {

    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var fromChainImg: UIImageView!
    @IBOutlet weak var fromChainTxt: UILabel!
    @IBOutlet weak var toChainCard: CardView!
    @IBOutlet weak var toChainImg: UIImageView!
    @IBOutlet weak var toChainText: UILabel!
    
    @IBOutlet weak var relayerCard: CardView!
    @IBOutlet weak var relayerTxt: UILabel!
    @IBOutlet weak var relayerMsg: UILabel!
    @IBOutlet weak var relayerImg: UIImageView!
    
    var pageHolderVC: StepGenTxViewController!
    var ibcSendDenom: String!
    var ibcSendableRelayers = Array<IbcPath>()
    var ibcSelectedRelayer: IbcPath!
    var ibcSendablePaths = Array<Path>()
    var ibcSelectedPath: Path!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.ibcSendDenom = self.pageHolderVC.mIBCSendDenom
        
        //init select for relayer
        if (ibcSendDenom.starts(with: "ibc/")) {
            ibcSendableRelayers = BaseData.instance.getIbcRollbackRelayer(ibcSendDenom)
        } else {
            ibcSendableRelayers = BaseData.instance.getIbcSendableRelayers()
        }
//        print("ibcSendableRelayers ", ibcSendableRelayers.count)
        if (ibcSendableRelayers.count <= 0) {
            self.onForceBack()
            return
        }
        onSortRelayer()
        ibcSelectedRelayer = ibcSendableRelayers[0]
//        print("ibcSelectedRelayer ", ibcSelectedRelayer)
//        ibcSendableRelayers.forEach { relayer in
//            print("relayer ", relayer.chain_id)
//        }
        
        //init select for channel
        ibcSendablePaths = ibcSelectedRelayer.paths
        if (ibcSendablePaths.count <= 0) {
            self.onForceBack()
            return
        }
        onSortPath()
        ibcSelectedPath = ibcSendablePaths[0]
//        print("ibcSendablePaths ", ibcSendablePaths.count)
//        print("ibcSelectedPath ", ibcSelectedPath)
        
        self.onUpdateView()
        self.toChainCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickToChain (_:))))
        self.relayerCard.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.onClickRelayer (_:))))
    }
    
    override func enableUserInteraction() {
        self.btnCancel.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
    }
    
    func onUpdateView() {
        self.fromChainImg.image = WUtils.getChainImg(chainType)
        self.fromChainTxt.text = WUtils.getChainTitle2(chainType)
        
        let toChain = WUtils.getChainTypeByChainId(ibcSelectedRelayer.chain_id)
        self.toChainImg.image = WUtils.getChainImg(toChain)
        self.toChainText.text = WUtils.getChainTitle2(toChain)
        
        self.relayerTxt.text = ibcSelectedPath.channel_id
        self.relayerMsg.text = ""
        if (ibcSelectedPath.auth == true) {
            self.relayerImg.image = UIImage(named: "ibcauthed")
        } else {
            self.relayerImg.image = UIImage(named: "ibcunknown")
        }
//        if let description = ibcSelectedPath.description {
//            self.relayerMsg.text = "(" + description + ")"
//        } else {
//            self.relayerMsg.text = "(Unknown)"
//        }
    }
    
    @objc func onClickToChain (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_IBC_CHAIN
        popupVC.ibcToChain = ibcSendableRelayers
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    @objc func onClickRelayer (_ sender: UITapGestureRecognizer) {
        let popupVC = SelectPopupViewController(nibName: "SelectPopupViewController", bundle: nil)
        popupVC.type = SELECT_POPUP_IBC_RELAYER
        popupVC.ibcRelayer = ibcSendablePaths
        let cardPopup = SBCardPopupViewController(contentViewController: popupVC)
        cardPopup.resultDelegate = self
        cardPopup.show(onViewController: self)
    }
    
    func SBCardPopupResponse(type: Int, result: Int) {
        if (type == SELECT_POPUP_IBC_CHAIN) {
            ibcSelectedRelayer = ibcSendableRelayers[result]
            ibcSendablePaths = ibcSelectedRelayer.paths
            onSortPath()
            ibcSelectedPath = ibcSendablePaths[0]
            onUpdateView()
            
        } else if (type == SELECT_POPUP_IBC_RELAYER) {
            ibcSelectedPath = ibcSendablePaths[result]
            onUpdateView()
        }
    }
    
    @IBAction func onClickCancel(_ sender: UIButton) {
        btnCancel.isUserInteractionEnabled = false
        btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if (ibcSelectedPath.auth == true) {
            onGoNext()
        } else {
            onAlertUnAuthedChannel()
        }
    }
    
    func onGoNext() {
        pageHolderVC.mIBCSendRelayer = ibcSelectedRelayer
        pageHolderVC.mIBCSendPath = ibcSelectedPath
        btnCancel.isUserInteractionEnabled = true
        btnNext.isUserInteractionEnabled = true
        pageHolderVC.onNextPage()
    }
    
    func onAlertUnAuthedChannel() {
        let unAuthTitle = NSLocalizedString("str_notice", comment: "")
        let unAuthMsg = NSLocalizedString("str_msg_relayer_unauthed", comment: "")
        let noticeAlert = UIAlertController(title: unAuthTitle, message: unAuthMsg, preferredStyle: .alert)
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        noticeAlert.addAction(UIAlertAction(title: NSLocalizedString("continue", comment: ""), style: .default, handler: { _ in
            self.onGoNext()
        }))
        self.present(noticeAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            noticeAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onForceBack() {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
            self.btnCancel.isUserInteractionEnabled = false
            self.btnNext.isUserInteractionEnabled = false
            self.pageHolderVC.onBeforePage()
        })
    }
    
    func onSortRelayer() {
        self.ibcSendableRelayers.sort {
            if ($0.chain_id?.contains("cosmoshub-") == true) { return true }
            if ($0.chain_id?.contains("osmosis-") == true) { return true }
            if ($1.chain_id?.contains("cosmoshub-") == false) { return false }
            if ($1.chain_id?.contains("osmosis-") == false) { return false }
            return false
        }
    }
    
    func onSortPath() {
        self.ibcSendablePaths.sort {
            if ($0.auth == true) { return true }
            if ($1.auth != true) { return false }
            return false
        }
    }
}
